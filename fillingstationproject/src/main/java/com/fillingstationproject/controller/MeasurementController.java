package com.fillingstationproject.controller;


import com.fillingstationproject.dao.MeasurementDao;
import com.fillingstationproject.entity.Measurement;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeasurementController {

    @Autowired
    private MeasurementDao dao;

    @RequestMapping(value = "/measurements/list", method = RequestMethod.GET, produces = "application/json")
    public List<Measurement> measurements() {
        return dao.list();
    }

    @RequestMapping(value = "/measurements", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Measurement> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.MEASUREMENT,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/measurements", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Measurement measurement) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.MEASUREMENT, AuthProvider.INSERT)) {
            Measurement msmnt = dao.findByMeasuretime(measurement.getMeasuredtime());
            if (msmnt != null)
                return "Error-Validation : Time Exists";
            else if (msmnt != null)
                return "Error-Validation : Time Exists";
            else
                try {
                    dao.save(measurement);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/measurements", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Measurement measurement) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.MEASUREMENT, AuthProvider.UPDATE)) {

            try {
                dao.save(measurement);
                return "0";
            } catch (Exception e) {
                return "Error-Updating : " + e.getMessage();
            }
        }
        return "Error-Updating : You have no Permission";
        //return "0";
    }

    @RequestMapping(value = "/measurements", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Measurement measurement) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.MEASUREMENT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(measurement.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }
}
