package com.fillingstationproject.controller;

import com.fillingstationproject.dao.GrnstatusDao;
import com.fillingstationproject.entity.Grnstatus;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrnstatusController {

   @Autowired
    private GrnstatusDao dao;

    @RequestMapping(value = "/grnstatuses/list",method = RequestMethod.GET, produces = "application/json")
    public List<Grnstatus> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/grnstatuses", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Grnstatus> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.GRNNOTE,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }
}
