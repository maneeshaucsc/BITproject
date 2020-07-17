package com.fillingstationproject.controller;

import com.fillingstationproject.dao.CategoryDao;
import com.fillingstationproject.entity.Category;
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

public class CategoryController {
    @Autowired
    private CategoryDao dao;

    @RequestMapping(value = "/categories/list",method = RequestMethod.GET, produces = "application/json")
    public List<Category> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/categories/listbyitype",params ="itemtypeId",method = RequestMethod.GET, produces = "application/json")
    public List<Category> categories(@Param("itemtypeId") Integer itemtypeId) {
        return dao.listByItype(itemtypeId);
    }

    @RequestMapping(value = "/categories", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Category> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.CATEGORY,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
        public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Category category) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.CATEGORY,AuthProvider.INSERT)) {

            Category catname = dao.findByName(category.getName());
            if (catname != null)
                return "Error-Validation : Name Exists";

            else
                try {
                    dao.save(category);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Category category) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.BRAND,AuthProvider.UPDATE)) {
            Category cat = dao.findByName(category.getName());
            if(cat==null || cat.getName()==category.getName()) {
                try {
                    dao.save(category);
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

    @RequestMapping(value = "/categories", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Category category ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.CATEGORY,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(category.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/categories", params = {"page", "size","name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Category> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.CATEGORY,AuthProvider.SELECT)) {

            List<Category> categories = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Category> categorystream = categories.stream();

            categorystream = categorystream.filter(e -> e.getName().contains(name));

            List<Category> categories2 = categorystream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < categories2.size() ? start + size : categories2.size();
            Page<Category> catpage = new PageImpl<>(categories2.subList(start, end), PageRequest.of(page, size), categories2.size());

            return catpage;
        }

        return null;

    }
}
