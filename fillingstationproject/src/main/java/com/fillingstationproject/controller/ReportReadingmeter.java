package com.fillingstationproject.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReportReadingmeter {
        String code;
        BigDecimal amount;
        BigDecimal income;
        String date;


    public ReportReadingmeter(String code, BigDecimal amount, BigDecimal income, String date) {
        this.code = code;
        this.amount = amount;
        this.income = income;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
