package com.fillingstationproject.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui")
public class UiController {



    @RequestMapping("/config")
    public ModelAndView config(){
        ModelAndView model = new ModelAndView();
        model.setViewName("config.html");
        return model;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("login.html");
        return model;
    }

    @RequestMapping("/mainwindow")
    public ModelAndView mainwindow(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("new.html",username,password);
    }


    @RequestMapping("/brand")
    public ModelAndView brand(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("brand.html");
        return model;
    }

    @RequestMapping("/category")
    public ModelAndView category(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("category.html");
        return model;
    }

    @RequestMapping("/subcategory")
    public ModelAndView subcategory(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("subcategory.html");
        return model;
    }

    @RequestMapping("/itemstatus")
    public ModelAndView fuelstatus(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("itemstatus.html");
        return model;
    }

    @RequestMapping("/meter")
    public ModelAndView meter(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("meter.html");
        return model;
    }

    @RequestMapping("/tank")
    public ModelAndView tank(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("tank.html");
        return model;
    }

    @RequestMapping("/itemtype")
    public ModelAndView purchasingtype(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("itemtype.html");
        return model;
    }

    @RequestMapping("/unitamount")
    public ModelAndView unittype(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("unitamount.html");
        return model;
    }

    @RequestMapping("/supplier")
    public ModelAndView supplier(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("supplier.html");
        return model;
    }

    @RequestMapping("/supplierstatus")
    public ModelAndView supplierstatus(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("supplierstatus.html");
        return model;
    }

    @RequestMapping("/porder")
    public ModelAndView porder(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("porder.html");
        return model;
    }

    @RequestMapping("/customer")
    public ModelAndView customer(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("customer.html");
        return model;
    }

    @RequestMapping("/suppayment")
    public ModelAndView suppayment(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("suppayment.html");
        return model;
    }

    @RequestMapping("/chepayment")
    public ModelAndView chepayment(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("chequepayment.html");
        return model;
    }

    @RequestMapping("/reading")
    public ModelAndView reading(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("reading.html");
        return model;
    }

    @RequestMapping("/measurement")
    public ModelAndView measurement(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("measurement.html");
        return model;
    }




    @RequestMapping("/grn")
    public ModelAndView grn(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("grn.html");
        return model;
    }

    @RequestMapping("/corder")
    public ModelAndView corder(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("corder.html");
        return model;
    }

    @RequestMapping("/invoice")
    public ModelAndView invoice(){
        //System.out.println("lala");
        ModelAndView model = new ModelAndView();
        model.setViewName("invoice.html");
        return model;
    }



    @RequestMapping("/home")
    public ModelAndView home(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("homi.html",username,password);
    }


    @RequestMapping("/employee")
    public ModelAndView employee(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("employee.html",username,password);
    }


    @RequestMapping("/user")
    public ModelAndView user(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("user.html",username,password);
    }

    @RequestMapping("/previlage")
    public ModelAndView previlage(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("previlage.html",username,password);

    }


    @RequestMapping("/changepassword")
    public ModelAndView changepassword(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("changepassword.html",username,password);
    }


    @RequestMapping("/designation")
    public ModelAndView designation(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("designation.html",username,password);
    }

    @RequestMapping("/item")
    public ModelAndView fuel(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("item.html",username,password);
    }

//    @RequestMapping("/report/systemaccessanalysis")
//    public ModelAndView systemaccessanalysis(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
//        return getModelView("systemaccessanalysis.html",username,password);
//    }




    public ModelAndView getModelView(String page,String username, String password){

        ModelAndView model = new ModelAndView();

        if(AuthProvider.isUser(username,password)) {

            model.setViewName(page);
        }
        else {
            model.setViewName("noprivilage.html");

        }
        return model;

    }



}