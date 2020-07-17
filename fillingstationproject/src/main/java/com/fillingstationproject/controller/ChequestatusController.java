package com.fillingstationproject.controller;


import com.fillingstationproject.dao.ChequestatusDao;
import com.fillingstationproject.entity.Chequestatus;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChequestatusController {

    @Autowired
    private ChequestatusDao dao;

    @RequestMapping(value = "/chequestatuses/list",method = RequestMethod.GET, produces = "application/json")
    public List<Chequestatus> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/chequestatuses", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Chequestatus> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.CHEQUESTATUS,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }



}
