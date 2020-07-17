package com.fillingstationproject.controller;

import com.fillingstationproject.dao.PorderDao;
import com.fillingstationproject.dao.PorderstatusDao;
import com.fillingstationproject.dao.SupplierDao;
import com.fillingstationproject.entity.Porder;
import com.fillingstationproject.entity.Porderitem;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PorderController {

    @Autowired
    private PorderDao dao;

    @Autowired
    private SupplierDao daosupplier;

    @Autowired
    private PorderstatusDao daoporderstatus;

    @RequestMapping(value = "/porders/pono", method = RequestMethod.GET, produces = "application/json")
    public String lastPorderno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PORDER,AuthProvider.SELECT)) {
            String pono = dao.lastPorderno();
           // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+pono+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

    @RequestMapping(value = "/porders/porderbysup", params = "supplierId", method = RequestMethod.GET, produces = "application/json")
    public List<Porder> porderbysup(@RequestParam("supplierId") Integer supplierId) {
        return dao.porderbysup(supplierId);
    }

    @RequestMapping(value = "/porders/list", method = RequestMethod.GET, produces = "application/json")
    public List<Porder> list() {
        return dao.list();
    }

    @RequestMapping(value = "/porders", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Porder> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PORDER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/porders", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Porder porder) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.PORDER,AuthProvider.INSERT)) {
            Porder po = dao.findByPoNo(porder.getPono());

            if (po != null)
                return "Error-Validation : Porder No Exists";

            else
                try {
                    for (Porderitem poitm:porder.getPorderitemList())
                        poitm.setPorderId(porder);
                    dao.save(porder);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/porders", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Porder porder) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.PORDER,AuthProvider.UPDATE)) {

            try {
                for (Porderitem po : porder.getPorderitemList())
                    po.setPorderId(porder);


                dao.save(porder);
                return "0";
            }
            catch(Exception e) {
                return "Error-Updating : "+e.getMessage();
            }
        }
        else {  return "Error-Updating : Reg No Exists"; }

    }

    @RequestMapping(value = "/porders", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Porder porder ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.PORDER,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(porder.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/porders", params = {"page", "size","pono","supplierid","statusid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Porder> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password,
                                @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("pono") String pono, @RequestParam("supplierid") Integer supplierid, @RequestParam("statusid") Integer statusid) {

        // System.out.println(name+"-"+nic+"-"+designationid+"-"+employeestatusid);


        if(AuthProvider.isAuthorized(username,password, ModuleList.PORDER,AuthProvider.SELECT)) {

            List<Porder> porders = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Porder> porderstream = porders.stream();
            //adminwa search nowenn  object ekkt nm equals,
            // supplierstream = supplierstream.filter(e -> !(e.getCallingname().equals("Admin")));

            if (supplierid != null)
                porderstream = porderstream.filter(p -> p.getSupplierId().equals(daosupplier.getOne(supplierid)));
            if (statusid != null)
                porderstream = porderstream.filter(p -> p.getPorderstatusId().equals(daoporderstatus.getOne(statusid)));
            porderstream = porderstream.filter(p -> p.getPono().contains(pono));


            List<Porder> porders2 = porderstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < porders2.size() ? start + size : porders2.size();
            Page<Porder> suppage = new PageImpl<>(porders2.subList(start, end), PageRequest.of(page, size), porders2.size());

            return suppage;
        }

        return null;

    }




}
