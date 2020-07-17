package com.fillingstationproject.controller;

import com.fillingstationproject.dao.SubcategoryDao;
import com.fillingstationproject.entity.Subcategory;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class SubcategoryController {
    @Autowired
    private SubcategoryDao dao;

    @RequestMapping(value = "/subcategories/list",method = RequestMethod.GET, produces = "application/json")
    public List<Subcategory> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/subcategories/listbycategory",params ="categoryId",method = RequestMethod.GET, produces = "application/json")
    public List<Subcategory> subcategories(@Param("categoryId") Integer categoryId) {
        return dao.listByCategory(categoryId);
    }

    @RequestMapping(value = "/subcategories", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Subcategory> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.SUBCATEGORY,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/subcategories", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Subcategory subcategory) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.SUBCATEGORY,AuthProvider.INSERT)) {

            Subcategory subname = dao.findByName(subcategory.getName());
            if (subname != null)
                return "Error-Validation : Name Exists";

            else
                try {
                    dao.save(subcategory);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/subcategories", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Subcategory subcategory) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.SUBCATEGORY,AuthProvider.UPDATE)) {
            Subcategory scat = dao.findByName(subcategory.getName());
            if(scat==null || scat.getName()==subcategory.getName()) {
                try {
                    dao.save(subcategory);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Name Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/subcategories", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Subcategory subcategory ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.SUBCATEGORY,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(subcategory.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/subcategories", params = {"page", "size","name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Subcategory> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.SUBCATEGORY,AuthProvider.SELECT)) {

            List<Subcategory> subcategories = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Subcategory> subcategorystream = subcategories.stream();

            subcategorystream = subcategorystream.filter(e -> e.getName().contains(name));

            List<Subcategory> subcategories2 = subcategorystream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < subcategories2.size() ? start + size : subcategories2.size();
            Page<Subcategory> scatpage = new PageImpl<>(subcategories2.subList(start, end), PageRequest.of(page, size), subcategories2.size());

            return scatpage;
        }

        return null;

    }

}
