package com.fillingstationproject.controller;


public class ReportEntityItembySupplier {

    String code;
    String name;
    Integer quantity;
    Integer rop;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getRop() {
        return rop;
    }



    public ReportEntityItembySupplier(String code, String name, Integer quantity, Integer rop) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.rop = rop;
    }




}
