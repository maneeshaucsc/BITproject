package com.fillingstationproject.controller;

import com.fillingstationproject.entity.Module;
import com.fillingstationproject.dao.ModuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ModuleController {

    @Autowired
    private ModuleDao dao;

    @RequestMapping(value = "/modules/list", method = RequestMethod.GET, produces = "application/json")
    public List<Module> gender() {
        return dao.list();
    }

    @RequestMapping(value = "/modules/list/unassignedtothisrole", params = {"roleid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Module> modulesnotassignedtotherole(Integer roleid) {
        return dao.listUnassignedToThisRole(roleid);

    }


}
