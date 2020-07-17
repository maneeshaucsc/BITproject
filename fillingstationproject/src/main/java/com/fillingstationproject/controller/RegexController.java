package com.fillingstationproject.controller;

import com.fillingstationproject.entity.*;

import com.fillingstationproject.util.RegexPattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

@RestController
@RequestMapping("/regexes")
public class RegexController {

    @RequestMapping(value = "/employee", produces = "application/json")
    public HashMap<String, HashMap<String, String>> employee() {
        return getRegex(new Employee());
    }

    @RequestMapping(value = "/user", produces = "application/json")
    public HashMap<String, HashMap<String, String>> user() {
        return getRegex(new User());
    }

    @RequestMapping(value = "/designation", produces = "application/json")
    public HashMap<String, HashMap<String, String>> designation() {
        return getRegex(new Designation());
    }

    @RequestMapping(value = "/item", produces = "application/json")
    public HashMap<String, HashMap<String, String>> item() {
        return getRegex(new Item());
    }

    @RequestMapping(value = "/supplier", produces = "application/json")
    public HashMap<String, HashMap<String, String>> supplier() {
        return getRegex(new Supplier());
    }

    @RequestMapping(value = "/porder", produces = "application/json")
    public HashMap<String, HashMap<String, String>> porder() {
        return getRegex(new Porder());
    }

       @RequestMapping(value = "/customer", produces = "application/json")
    public HashMap<String, HashMap<String, String>> customer() {
        return getRegex(new Customer());
    }

    @RequestMapping(value = "/corder", produces = "application/json")
    public HashMap<String, HashMap<String, String>> corder() {
        return getRegex(new Corder());
    }

    @RequestMapping(value = "/brand", produces = "application/json")
    public HashMap<String, HashMap<String, String>> brand() {
        return getRegex(new Brand());
    }

    @RequestMapping(value = "/category", produces = "application/json")
    public HashMap<String, HashMap<String, String>> category() {
        return getRegex(new Category());
    }

    @RequestMapping(value = "/subcategory", produces = "application/json")
    public HashMap<String, HashMap<String, String>> subcategory() {
        return getRegex(new Subcategory());
    }

    @RequestMapping(value = "/itemstatus", produces = "application/json")
    public HashMap<String, HashMap<String, String>> itemstatus() {
        return getRegex(new Itemstatus());
    }

    @RequestMapping(value = "/unitamount", produces = "application/json")
    public HashMap<String, HashMap<String, String>> unitamount() {
        return getRegex(new Unitamount());
    }

    @RequestMapping(value = "/reading", produces = "application/json")
    public HashMap<String, HashMap<String, String>> reading() {
        return getRegex(new Reading());
    }

    @RequestMapping(value = "/measurement", produces = "application/json")
    public HashMap<String, HashMap<String, String>> measurement() {
        return getRegex(new Measurement());
    }

    @RequestMapping(value = "/itemtype", produces = "application/json")
    public HashMap<String, HashMap<String, String>> itemtype() {
        return getRegex(new Itemtype());
    }

    @RequestMapping(value = "/payment", produces = "application/json")
    public HashMap<String, HashMap<String, String>> payment() {
        return getRegex(new Payment());
    }

    @RequestMapping(value = "/invoice", produces = "application/json")
    public HashMap<String, HashMap<String, String>> invoice() {
        return getRegex(new Invoice());
    }




    public static <T> HashMap<String, HashMap<String, String>> getRegex(T t) {
        try {
            Class<? extends Object> aClass = t.getClass();
            HashMap<String, HashMap<String, String>> regex = new HashMap<>();

            for (Field field : aClass.getDeclaredFields()) {

                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {

                    if (annotation instanceof Pattern) {
                        Pattern myAnnotation = (Pattern) annotation;
                        HashMap<String, String> map = new HashMap<>();
                        map.put("regex", myAnnotation.regexp());
                        map.put("message", myAnnotation.message());
                        regex.put(field.getName(), map);
                    }

                    if (annotation instanceof RegexPattern) {
                        RegexPattern myAnnotation2 = (RegexPattern) annotation;
                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("regex", myAnnotation2.regexp());
                        map2.put("message", myAnnotation2.message());
                        regex.put(field.getName(), map2);
                    }
                }
            }
            return regex;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


