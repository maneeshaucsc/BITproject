package com.fillingstationproject.controller;

import com.fillingstationproject.dao.BankDao;
import com.fillingstationproject.entity.Bank;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    @Autowired
    private BankDao dao;

    @RequestMapping(value = "/banks/list",method = RequestMethod.GET, produces = "application/json")
    public List<Bank> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/banks", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Bank> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.BANK,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }
}
