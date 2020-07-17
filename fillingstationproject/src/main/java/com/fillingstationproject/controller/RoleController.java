package com.fillingstationproject.controller;

import com.fillingstationproject.dao.RoleDao;
import com.fillingstationproject.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleDao dao;

    @RequestMapping(value = "/roles/list", method = RequestMethod.GET, produces = "application/json")
    public List<Role> gender() {
        return dao.list();
    }


}
