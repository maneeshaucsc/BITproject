package com.fillingstationproject.controller;

import com.fillingstationproject.dao.ItemstatusDao;
import com.fillingstationproject.entity.Itemstatus;
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
public class ItemstatusController {
    @Autowired
    private ItemstatusDao dao;

    @RequestMapping(value = "/itemstatuses/list",method = RequestMethod.GET, produces = "application/json")
    public List<Itemstatus> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/itemstatuses", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Itemstatus> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEMSTATUS,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/itemstatuses", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Itemstatus itemstatus) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEMSTATUS,AuthProvider.INSERT)) {

            Itemstatus fuestatus = dao.findByStatus(itemstatus.getStatus());
            if (fuestatus != null)
                return "Error-Validation : Status Exists";

            else
                try {
                    dao.save(itemstatus);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/itemstatuses", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Itemstatus itemstatus) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEMSTATUS,AuthProvider.UPDATE)) {
            Itemstatus fuesta = dao.findByStatus(itemstatus.getStatus());
            if(fuesta==null || fuesta.getStatus()==itemstatus.getStatus()) {
                try {
                    dao.save(itemstatus);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Status Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/itemstatuses", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Itemstatus itemstatus ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEMSTATUS,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(itemstatus.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/itemstatuses", params = {"page", "size","status"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Itemstatus> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("status") String status) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEMSTATUS,AuthProvider.SELECT)) {

            List<Itemstatus> itemstatuses = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Itemstatus> itemstatusstream = itemstatuses.stream();

            itemstatusstream = itemstatusstream.filter(e -> e.getStatus().contains(status));

            List<Itemstatus> itemstatus2 = itemstatusstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < itemstatus2.size() ? start + size : itemstatus2.size();
            Page<Itemstatus> itmstapage = new PageImpl<>(itemstatus2.subList(start, end), PageRequest.of(page, size), itemstatus2.size());

            return itmstapage;
        }

        return null;

    }



}
