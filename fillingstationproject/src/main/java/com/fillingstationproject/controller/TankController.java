package com.fillingstationproject.controller;

import com.fillingstationproject.dao.TankDao;
import com.fillingstationproject.entity.Tank;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TankController {
    @Autowired
    private TankDao dao;

    @RequestMapping(value = "/tanks/list",method = RequestMethod.GET, produces = "application/json")
    public List<Tank> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/tanks", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Tank> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.TANK,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/tanks", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Tank tank) {

        if(AuthProvider.isAuthorized(username,password, ModuleList.TANK,AuthProvider.INSERT)) {

            Tank tnk = dao.findByCode(tank.getCode());
            if (tnk != null)
                return "Error-Validation : Tank Exists";

            else
                try {
                    dao.save(tank);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/tanks", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Tank tank) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.TANK,AuthProvider.UPDATE)) {
            Tank tnk = dao.findByCode(tank.getCode());
            if(tnk==null || tnk.getCode()==tank.getCode()) {
                try {
                    dao.save(tank);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Tank Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/tanks", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Tank tank ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.TANK,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(tank.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/tanks", params = {"page", "size","code"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Tank> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("code") String code) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.TANK,AuthProvider.SELECT)) {

            List<Tank> tanks = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Tank> tankstream = tanks.stream();

            tankstream = tankstream.filter(e -> e.getCode().contains(code));

            List<Tank> tanks2 = tankstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < tanks2.size() ? start + size : tanks2.size();
            Page<Tank> tnkpage = new PageImpl<>(tanks2.subList(start, end), PageRequest.of(page, size), tanks2.size());

            return tnkpage;
        }

        return null;

    }



}
