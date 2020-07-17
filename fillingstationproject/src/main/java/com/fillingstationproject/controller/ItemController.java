package com.fillingstationproject.controller;

import com.fillingstationproject.dao.BrandDao;
import com.fillingstationproject.dao.ItemDao;
import com.fillingstationproject.dao.ItemtypeDao;
import com.fillingstationproject.entity.Item;
import com.fillingstationproject.entity.Itemtype;
import com.fillingstationproject.entity.Unitamount;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ItemController {

    @Autowired
    private ItemDao dao;

    @Autowired
    private ItemtypeDao daoitemtype;


    @RequestMapping(value = "/items/list", method = RequestMethod.GET, produces = "application/json")
    public List<Item> list() {
        return dao.list();
    }

    @RequestMapping(value = "/items", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.ITEM, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));

        }
        return null;
    }

    @RequestMapping(value = "/items/listbyporder", params = "porderId", method = RequestMethod.GET, produces = "application/json")
    public List<Item> listbyporder(@RequestParam("porderId") Integer porderId) {
        return dao.listbyporder(porderId);
    }


    @RequestMapping(value = "/items/listbycategory", params = "categoryId", method = RequestMethod.GET, produces = "application/json")
    public List<Item> listbycategory(@RequestParam("categoryId") Integer categoryId) {
        return dao.listbycategory(categoryId);
    }


    @RequestMapping(value = "/items/listbycorder", params = "corderId", method = RequestMethod.GET, produces = "application/json")
    public List<Item> listByCorder(@RequestParam("corderId") Integer corderId) {
        return dao.listbycorder(corderId);
    }

//    @RequestMapping(value = "/items/listbycorder", params = "corderId", method = RequestMethod.GET, produces = "application/json")
//    public List<Item> listbycorder(@RequestParam("corderId") Integer corderId) {
//        return dao.listbycorder(corderId);
//    }

    @RequestMapping(value = "/items/listbysupplier",params = "supplierid", method = RequestMethod.GET, produces = "application/json")
    public List<Item> listbysupplier(@RequestParam("supplierid")Integer supplierid) {

        return dao.listbysupplier(supplierid);
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Item item) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.ITEM, AuthProvider.INSERT)) {
            Item itemcode = dao.findByCode(item.getCode());
            if (itemcode != null)
                return "Error-Validation : Code Exists";
            else if (itemcode != null)
                return "Error-Validation : Code Exists";
            else
                try {
                    dao.save(item);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


    @RequestMapping(value = "/items", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Item item) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.ITEM, AuthProvider.UPDATE)) {

            try {
                dao.save(item);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/items", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Item item) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.ITEM, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(item.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }
   @RequestMapping(value = "/items", params = {"page", "size", "brandid","itemtypeid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Item> findAll(@CookieValue(value = "username") String username,
                              @CookieValue(value = "password") String password,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size,
                              @RequestParam("brandid") Integer brandid,
                              @RequestParam("itemtypeid") Integer itemtypeid) {

         //System.out.println(brand + "-" + itemtypeid);

        //checking the authentication
        if (AuthProvider.isAuthorized(username, password, ModuleList.ITEM, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Item> items = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the item list to a stream to filter them
            Stream<Item> itemstream = items.stream();

            if (itemtypeid != null)
                itemstream = itemstream.filter(f -> f.getItemtypeId().equals(daoitemtype.getOne(itemtypeid)));

            if (brandid != null)
            itemstream = itemstream.filter(f -> f.getItemtypeId().getBrandId().equals(daoitemtype.getOne(brandid)));


            List<Item> item2 = itemstream.collect(Collectors.toList());

            //so after filtered it converts to a page
            int start = page * size;
            int end = start + size < item2.size() ? start + size : item2.size();
            Page<Item> itmpage = new PageImpl<>(item2.subList(start, end), PageRequest.of(page, size), item2.size());

            return itmpage;
        }

        return null;

    }



    @RequestMapping(value = "/items/code", method = RequestMethod.GET, produces = "application/json")
    public String lastCode(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.ITEM,AuthProvider.SELECT)) {
            String code = dao.lastItemno();
            // System.out.println(porderno);

            return "{\"no\":"+"\""+code+"\"}";
        }
        return null;
    }
}
