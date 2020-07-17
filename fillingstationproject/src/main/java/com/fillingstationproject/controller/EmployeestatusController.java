package com.fillingstationproject.controller;

import com.fillingstationproject.dao.EmployeestatusDao;
import com.fillingstationproject.entity.Employeestatus;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeestatusController {

    @Autowired
    private EmployeestatusDao dao;

    @RequestMapping(value = "/employeestatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Employeestatus> list(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
            return dao.list();
        }
        return null;
    }

    @RequestMapping(value = "/employeestatuses", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Employeestatus> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEESTATUS,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }




}
