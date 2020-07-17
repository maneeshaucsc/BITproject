package com.fillingstationproject.controller;

import com.fillingstationproject.dao.InvoiceDao;
import com.fillingstationproject.dao.InvoicestatusDao;
import com.fillingstationproject.entity.Invoicestatus;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoicestatusController {

    @Autowired
    private InvoicestatusDao dao;

    @RequestMapping(value = "/invoicestatuses/list",method = RequestMethod.GET, produces = "application/json")
    public List<Invoicestatus> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/invoicestatuses", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Invoicestatus> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.INVOICESTATUS,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }
}
