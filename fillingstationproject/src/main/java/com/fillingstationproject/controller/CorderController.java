package com.fillingstationproject.controller;

import com.fillingstationproject.dao.CorderDao;
import com.fillingstationproject.dao.CustomerDao;
import com.fillingstationproject.dao.CustomerorderstatusDao;
import com.fillingstationproject.entity.Corder;
import com.fillingstationproject.entity.Corderitem;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CorderController {

    @Autowired
    private CorderDao dao;

    @Autowired
    private CustomerDao daocustomer;

    @Autowired
    private CustomerorderstatusDao daocustomerorder;

    @RequestMapping(value = "/corders/list", method = RequestMethod.GET, produces = "application/json")
    public List<Corder> corders() {
        return dao.list();
    }

    @RequestMapping(value = "/corders", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Corder> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CORDER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }
    @RequestMapping(value = "/corders/orderno", method = RequestMethod.GET, produces = "application/json")
    public String orderno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CORDER,AuthProvider.SELECT)) {
            String orderno = dao.lastCorderno();
            // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+orderno+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

   @RequestMapping(value = "/corders/ordernobycus", params = "customerId", method = RequestMethod.GET, produces = "application/json")
   public List<Corder> ordernobycus(@RequestParam("customerId") Integer customerId) {
       return dao.ordernobycus(customerId);
   }


    @RequestMapping(value = "/corders", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Corder corder) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.CORDER,AuthProvider.INSERT)) {
            Corder co = dao.findByOrderno(corder.getOrderno());

            if (co != null)
                return "Error-Validation : Corder No Exists";

            else
                try {
                    for (Corderitem coitm:corder.getCorderitemList())
                        coitm.setCorderId(corder);
                    dao.save(corder);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/corders", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Corder corder) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CORDER, AuthProvider.UPDATE)) {

            try {
                dao.save(corder);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/corders", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Corder corder) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CORDER, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(corder.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/corders", params = {"page", "size", "orderno","customerid","statusid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Corder> findAll(@CookieValue(value = "username") String username,
                                  @CookieValue(value = "password") String password,
                                  @RequestParam("page") int page,
                                  @RequestParam("size") int size,
                                  @RequestParam("orderno") String orderno,
                                  @RequestParam("customerid") Integer customerid,
                                  @RequestParam("statusid") Integer statusid) {

        //System.out.println(brand + "-" + itemtypeid);

        //checking the authentication
        if (AuthProvider.isAuthorized(username, password, ModuleList.CORDER, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Corder> corders = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the customer list to a stream to filter them
            Stream<Corder> corderstream = corders.stream();

            corderstream = corderstream.filter(c -> c.getOrderno().contains(orderno));

            if (customerid != null)
                corderstream = corderstream.filter(f -> f.getCustomerId().equals(daocustomer.getOne(customerid)));

            if (statusid != null)
                corderstream = corderstream.filter(f -> f.getCustomerorderstatusId().equals(daocustomerorder.getOne(statusid)));


            List<Corder> corder2 = corderstream.collect(Collectors.toList());

            //so after filtered it converts to a page
            int start = page * size;
            int end = start + size < corder2.size() ? start + size : corder2.size();
            Page<Corder> crdrpage = new PageImpl<>(corder2.subList(start, end), PageRequest.of(page, size), corder2.size());

            return crdrpage;
        }

        return null;

    }
}
