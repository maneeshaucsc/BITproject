package com.fillingstationproject.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui/report")
public class ReportUiController {

    @RequestMapping("/systemaccessanalysis")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("systemaccessanalysis.html");
        return model;
    }

    @RequestMapping("/suppliersreport")
    public ModelAndView suppliers(){
        ModelAndView model = new ModelAndView();
        model.setViewName("suppliersreport.html");
        return model;
    }
    @RequestMapping("/readingReport")
    public ModelAndView readingReport(){
        ModelAndView model = new ModelAndView();
        model.setViewName("readingReport.html");
        return model;
    }

    @RequestMapping("/invoices")
    public ModelAndView invoices(){
        ModelAndView model = new ModelAndView();
        model.setViewName("reportinvoices.html");
        return model;
    }

    @RequestMapping("/meterials")
    public ModelAndView meterials(){
        ModelAndView model = new ModelAndView();
        model.setViewName("reportmeterials.html");
        return model;
    }

    @RequestMapping("/purchasorders")
    public ModelAndView purchasorders(){
        ModelAndView model = new ModelAndView();
        model.setViewName("reportpurchasorders.html");
        return model;
    }

    @RequestMapping("/grns")
    public ModelAndView grns(){
        ModelAndView model = new ModelAndView();
        model.setViewName("reportgrns.html");
        return model;
    }

}