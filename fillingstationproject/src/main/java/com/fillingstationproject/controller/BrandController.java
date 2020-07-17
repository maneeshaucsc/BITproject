package com.fillingstationproject.controller;

import com.fillingstationproject.dao.BrandDao;
import com.fillingstationproject.entity.Brand;

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
public class BrandController {

    @Autowired
    private BrandDao dao;

   /* @RequestMapping(value = "/brands/listbyitype",params ="itemtypeId",method = RequestMethod.GET, produces = "application/json")
    public List<Brand> brands(@Param("itemtypeId") Integer itemtypeId) {
        return dao.listByItype(itemtypeId);
    }
*/
   @RequestMapping(value = "/brands/list", method = RequestMethod.GET, produces = "application/json")
   public List<Brand> brands() {
       return dao.list();
   }

    @RequestMapping(value = "/brands", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Brand> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.BRAND,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/brands", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Brand brand) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.BRAND,AuthProvider.INSERT)) {

            Brand brnname = dao.findByName(brand.getName());
            if (brnname != null)
                return "Error-Validation : Name Exists";

            else
                try {
                    dao.save(brand);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/brands", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Brand brand) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.BRAND,AuthProvider.UPDATE)) {
            Brand brn = dao.findByName(brand.getName());
            if(brn==null || brn.getName()==brand.getName()) {
                try {
                    dao.save(brand);
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

    @RequestMapping(value = "/brands", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Brand brand ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.BRAND,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(brand.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/brands", params = {"page", "size","name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Brand> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("name") String name) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.BRAND,AuthProvider.SELECT)) {

            List<Brand> brands = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Brand> brandstream = brands.stream();

            brandstream = brandstream.filter(e -> e.getName().contains(name));

            List<Brand> brands2 = brandstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < brands2.size() ? start + size : brands2.size();
            Page<Brand> brnpage = new PageImpl<>(brands2.subList(start, end), PageRequest.of(page, size), brands2.size());

            return brnpage;
        }

        return null;

    }
}
