package com.fillingstationproject.controller;

import com.fillingstationproject.dao.CustomerstatusDao;
import com.fillingstationproject.entity.Customerstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerstatusController {

    @Autowired
    private CustomerstatusDao dao;

    @RequestMapping(value = "/customerstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Customerstatus> customerstatuses() {
        return dao.list();
    }

}
