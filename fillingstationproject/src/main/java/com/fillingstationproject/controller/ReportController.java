package com.fillingstationproject.controller;




import com.fillingstationproject.dao.MeterDao;
import com.fillingstationproject.entity.Meter;
import com.fillingstationproject.entity.Reading;
import com.fillingstationproject.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class ReportController {

    @Autowired
    private MeterDao daometer;


    @RequestMapping(value = "/reports/systemaccessanalysis", method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntitySystemAccessAnalysis> systemaccessanalysis(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {
    LocalDateTime startdate = LocalDateTime.now().minusDays(35);
    LocalDateTime enddate = LocalDateTime.now().plusDays(1);
    return ReportProvider.getSystemAccessAnalysisReport(startdate, enddate);
}
else return  null;


    }

    @RequestMapping(value = "/reports/systemaccessanalysis", params = {"startdate", "enddate"}, method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntitySystemAccessAnalysis> systemaccessanalysis2(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime stdate=LocalDateTime.parse(startdate+" 00:00",formatter );
            LocalDateTime endate=LocalDateTime.parse(enddate +" 00:00",formatter);
            return ReportProvider.getSystemAccessAnalysisReport(stdate,endate);
        }
        else return  null;
    }

   @RequestMapping(value = "/reports/supplierdetails", method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityItembySupplier> suppliers(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.SUPPLIER,AuthProvider.SELECT)) {
            return ReportProvider.getSuppliersReport();
        }
        else return  null;


    }
   @RequestMapping(value = "/reports/readingReport", params = {"startdate", "enddate"}, method = RequestMethod.GET, produces = "application/json")
    public List<ReportReadingmeter> readingReport(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.READING,AuthProvider.SELECT)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate stdate=LocalDate.parse(startdate,formatter );
            LocalDate endate=LocalDate.parse(enddate,formatter);

            return ReportProvider.getreadingReport(stdate,endate);
        }
        else return  null;
    }




}
