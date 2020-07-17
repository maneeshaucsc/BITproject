package com.fillingstationproject.controller;

import com.fillingstationproject.dao.SupplierstatusDao;
import com.fillingstationproject.entity.Supplierstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierstatusController {

    @Autowired
    private SupplierstatusDao dao;

    @RequestMapping(value = "/supplierstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Supplierstatus> list(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
            return dao.list();
        }
        return null;
    }
}
