/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fillingstationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fillingstationproject.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maneesha
 */
@Entity
@Table(name = "reading")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reading.findAll", query = "SELECT r FROM Reading r")})
public class Reading implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "time")
    private LocalTime time;
    @Column(name = "entereddate")
    private LocalDate entereddate;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    @RegexPattern(regexp = "^([1-9]{1,6})$",message = "Invalid Amount")
    private BigDecimal amount;
    @Column(name = "income")
    @RegexPattern(regexp = "^([1-9]{1,6}.[0-9]{2})$",message = "Invalid Income")
    private BigDecimal income;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "readingId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Record> recordList;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "meter_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Meter meterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "afternoonreadingId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Measurement> measurementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nightreadingId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Measurement> measurementList1;


    public Reading() {
    }

    public Reading(Integer id) {
        this.id = id;
    }

    public Reading(Integer id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    @XmlTransient
    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Meter getMeterId() {
        return meterId;
    }

    public void setMeterId(Meter meterId) {
        this.meterId = meterId;
    }

    public LocalDate getEntereddate() {
        return entereddate;
    }

    public void setEntereddate(LocalDate entereddate) {
        this.entereddate = entereddate;
    }

    @XmlTransient
    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }

    @XmlTransient
    public List<Measurement> getMeasurementList1() {
        return measurementList1;
    }

    public void setMeasurementList1(List<Measurement> measurementList1) {
        this.measurementList1 = measurementList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reading)) {
            return false;
        }
        Reading other = (Reading) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Reading[ id=" + id + " ]";
    }
    
}
