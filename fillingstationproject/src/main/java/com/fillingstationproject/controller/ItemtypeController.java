package com.fillingstationproject.controller;

import com.fillingstationproject.dao.ItemtypeDao;
import com.fillingstationproject.entity.Itemtype;
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
public class ItemtypeController {

    @Autowired
    private ItemtypeDao dao;

    @RequestMapping(value = "/itemtypes/list",method = RequestMethod.GET, produces = "application/json")
    public List<Itemtype> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/itemtypes/listbysupplier",params = "supplierid", method = RequestMethod.GET, produces = "application/json")
    public List<Itemtype> listbysupplier(@RequestParam("supplierid")Integer supplierid) {

        return dao.listbysupplier(supplierid);
    }


//    @RequestMapping(value = "/itemtypes/listbyporder",params = "porderid", method = RequestMethod.GET, produces = "application/json")
//    public List<Itemtype> listbyporder(@RequestParam("porderid")Integer porderid) {
//
//        return dao.listbyporder(porderid);
//    }

    @RequestMapping(value = "/itemtypes", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Itemtype> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEMTYPE,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }



    @RequestMapping(value = "/itemtypes", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Itemtype itemtype) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEMTYPE,AuthProvider.INSERT)) {

            Itemtype pcht = dao.findByType(itemtype.getType());
            if (pcht != null)
                return "Error-Validation : Itemtype Exists";

            else
                try {
                    dao.save(itemtype);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/itemtypes", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Itemtype itemtype) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEMTYPE,AuthProvider.UPDATE)) {
            Itemtype pcht = dao.findByType(itemtype.getType());
            if(pcht==null || pcht.getType()==itemtype.getType()) {
                try {
                    dao.save(itemtype);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Itemtype Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/itemtypes", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Itemtype itemtype ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.ITEMTYPE,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(itemtype.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/itemtypes", params = {"page", "size","type"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Itemtype> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("type") String type) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEMTYPE,AuthProvider.SELECT)) {

            List<Itemtype> itemtypes = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Itemtype> itemtypesream = itemtypes.stream();

            itemtypesream = itemtypesream.filter(e -> e.getType().contains(type));

            List<Itemtype> itemtypes2 = itemtypesream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < itemtypes2.size() ? start + size : itemtypes2.size();
            Page<Itemtype> itypage = new PageImpl<>(itemtypes2.subList(start, end), PageRequest.of(page, size), itemtypes2.size());

            return itypage;
        }

        return null;

    }

}
