package com.fillingstationproject.controller;

import com.fillingstationproject.dao.UnitamountDao;
import com.fillingstationproject.entity.Unitamount;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnitamountController {

    @Autowired
    private UnitamountDao dao;

    @RequestMapping(value = "/unitamounts/list",method = RequestMethod.GET, produces = "application/json")
    public List<Unitamount> list()  {
        return dao.list();
    }



//    @RequestMapping(value = "/unitamounts/listbyscategory",params ="subcategoryId",method = RequestMethod.GET, produces = "application/json")
//    public List<Unitamount> unitamounts(@Param("subcategoryId") Integer subcategoryId) {
//        return dao.listbyscategory(subcategoryId);
//    }

    @RequestMapping(value = "/unitamounts", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Unitamount> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.UNITAMOUNT,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }
}
