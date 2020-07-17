package com.fillingstationproject.controller;


import com.fillingstationproject.dao.ChequepaymentDao;
import com.fillingstationproject.dao.ChequestatusDao;
import com.fillingstationproject.entity.Chequepayment;
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
public class ChequepaymentController {

    @Autowired
    private ChequepaymentDao dao;

    @Autowired
    private  ChequestatusDao daochequestatus;

    @RequestMapping(value = "/chequepayments/list",method = RequestMethod.GET, produces = "application/json")
    public List<Chequepayment> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/chequepayments", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Chequepayment> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.CHEQUEPAYMENT,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/chequepayments/cno", method = RequestMethod.GET, produces = "application/json")
    public String lastChequeno(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CHEQUEPAYMENT,AuthProvider.SELECT)) {
            String cno = dao.lastChequeno();
            // Integer porderNumber = Integer.parseInt(pono)+1;

            return "{\"no\":\""+cno+"\" }";//json format eken thyenn one nisa
        }
        return null;
    }

    @RequestMapping(value = "/chequepayments", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Chequepayment chequepayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PAYMENT, AuthProvider.INSERT)) {
            Chequepayment cno = dao.findByChNo(chequepayment.getCno());
            if (cno != null)
                return "Error-Validation : Chequepayment no Exists";
            else
                try {
                    dao.save(chequepayment);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/chequepayments", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Chequepayment chequepayment) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CHEQUEPAYMENT, AuthProvider.UPDATE)) {

            try {
                dao.save(chequepayment);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/chequepayments", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Chequepayment chequepayment) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CHEQUEPAYMENT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(chequepayment.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/chequepayments", params = {"page", "size", "cno","chequestatusid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Chequepayment> findAll(@CookieValue(value = "username") String username,
                                 @CookieValue(value = "password") String password,
                                 @RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 @RequestParam("cno") String cno,
                                 @RequestParam("chequestatusid") Integer chequestatusid) {


        if (AuthProvider.isAuthorized(username, password, ModuleList.CHEQUEPAYMENT, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Chequepayment> chequepayments = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the item list to a stream to filter them
            Stream<Chequepayment> chequepaymentstream = chequepayments.stream();

            if (chequestatusid != null)
                chequepaymentstream = chequepaymentstream.filter(i -> i.getId().equals(daochequestatus.getOne(chequestatusid)));

            chequepaymentstream = chequepaymentstream.filter(i -> i.getCno().startsWith(cno));

            List<Chequepayment> chequepayment2 = chequepaymentstream.collect(Collectors.toList());

            //so after filtered it converts to a page
            int start = page * size;
            int end = start + size < chequepayment2.size() ? start + size : chequepayment2.size();
            Page<Chequepayment> chepaypage = new PageImpl<>(chequepayment2.subList(start, end), PageRequest.of(page, size), chequepayment2.size());

            return chepaypage;
        }

        return null;

    }
}
