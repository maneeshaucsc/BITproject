package com.fillingstationproject.controller;

import com.fillingstationproject.dao.MeterDao;
import com.fillingstationproject.entity.Meter;
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
public class MeterController {
    @Autowired
    private MeterDao dao;

    @RequestMapping(value = "/meters/list",method = RequestMethod.GET, produces = "application/json")
    public List<Meter> list()  {
        return dao.list();
    }

    @RequestMapping(value = "/meters", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Meter> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.METER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/meters", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Meter meter) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.METER,AuthProvider.INSERT)) {

            Meter mtr = dao.findByCode(meter.getCode());
            if (mtr != null)
                return "Error-Validation : Meter Exists";

            else
                try {
                    dao.save(meter);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/meters", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Meter meter) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.METER,AuthProvider.UPDATE)) {
            Meter mtr = dao.findByCode(meter.getCode());
            if(mtr==null || mtr.getCode()==meter.getCode()) {
                try {
                    dao.save(meter);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Meter Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/meters", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Meter meter ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.METER,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(meter.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/meters", params = {"page", "size","code"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Meter> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("code") String code) {


        if(AuthProvider.isAuthorized(username,password, ModuleList.METER,AuthProvider.SELECT)) {

            List<Meter> meters = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Meter> meterstream = meters.stream();

            meterstream = meterstream.filter(e -> e.getCode().contains(code));

            List<Meter> meters2 = meterstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < meters2.size() ? start + size : meters2.size();
            Page<Meter> mtrpage = new PageImpl<>(meters2.subList(start, end), PageRequest.of(page, size), meters2.size());

            return mtrpage;
        }

        return null;

    }


}
