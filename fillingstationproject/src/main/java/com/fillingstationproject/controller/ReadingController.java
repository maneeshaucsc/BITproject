package com.fillingstationproject.controller;


import com.fillingstationproject.dao.ReadingDao;
import com.fillingstationproject.entity.Reading;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ReadingController {

    @Autowired
    private ReadingDao dao;

    @RequestMapping(value = "/readings/list", method = RequestMethod.GET, produces = "application/json")
    public List<Reading> readings() {
        return dao.list();
    }

    @RequestMapping(value = "/readings", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Reading> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.READING,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/readings", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Reading reading) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.READING, AuthProvider.INSERT)) {
            Reading redng = dao.findByReadingtime(reading.getTime());

                try {
                    dao.save(reading);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/readings", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Reading reading) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.READING, AuthProvider.UPDATE)) {

            try {
                dao.save(reading);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/readings", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Reading reading) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.READING, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(reading.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    /*@RequestMapping(value = "/readings", params = {"page", "size", "time","meterid",}, method = RequestMethod.GET, produces = "application/json")
    public Page<Reading> findAll(@CookieValue(value = "username") String username,
                              @CookieValue(value = "password") String password,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size,
                              @RequestParam("time") LocalTime time,
                              @RequestParam("meterid") Integer meterid) {

        //System.out.println(brand + "-" + itemtypeid);

        //checking the authentication
        if (AuthProvider.isAuthorized(username, password, ModuleList.READING, AuthProvider.SELECT)) {

            //bring all the data from the database
            List<Reading> readings = dao.findAll(Sort.by(Sort.Direction.DESC,"id"));

            //converting the item list to a stream to filter them
            Stream<Reading> readingstream = items.stream();

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

    }*/
}
