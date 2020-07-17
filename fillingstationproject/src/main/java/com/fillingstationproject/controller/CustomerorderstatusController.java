package com.fillingstationproject.controller;


import com.fillingstationproject.dao.CustomerorderstatusDao;
import com.fillingstationproject.entity.Customerorderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerorderstatusController {

    @Autowired
    private CustomerorderstatusDao dao;

    @RequestMapping(value = "/customerorderstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Customerorderstatus> customerorderstatuses() {
        return dao.list();
    }
}
