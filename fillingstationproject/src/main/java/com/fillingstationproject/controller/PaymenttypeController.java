package com.fillingstationproject.controller;


import com.fillingstationproject.dao.PaymenttypeDao;
import com.fillingstationproject.entity.Paymenttype;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymenttypeController {

    @Autowired
    private PaymenttypeDao dao;

    @RequestMapping(value = "/paymenttypes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Paymenttype> paymenttypes() {
        return dao.list();
    }

    @RequestMapping(value = "/paymenttypes", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Paymenttype> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.PAYMETSTATUS,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }
}
