package com.fillingstationproject.controller;



public class ReportEntitySupplier {

    String companyname;
    String itemname;
    String phonenumber;

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }




    public ReportEntitySupplier(String companyname, String itemname, String phonenumber) {
        this.companyname = companyname;
        this.itemname = itemname;
        this.phonenumber = phonenumber;

    }

    public String getCompanyname() {
        return companyname;
    }

    public String getItemname() {
        return itemname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }










}
