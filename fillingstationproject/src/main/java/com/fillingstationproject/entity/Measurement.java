/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fillingstationproject.entity;

import com.fillingstationproject.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maneesha
 */
@Entity
@Table(name = "measurement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurement.findAll", query = "SELECT m FROM Measurement m")})
public class Measurement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "measuredtime")
    private LocalTime measuredtime;
    @Column(name = "entereddate")
    private LocalDate entereddate;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    @RegexPattern(regexp = "^([1-9]{1,6})$",message = "Invalid Amount")
    private BigDecimal amount;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "afternoonreading_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Reading afternoonreadingId;
    @JoinColumn(name = "nightreading_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Reading nightreadingId;
    @JoinColumn(name = "tank_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tank tankId;

    public Measurement() {
    }

    public Measurement(Integer id) {
        this.id = id;
    }

    public Measurement(Integer id, LocalTime measuredtime) {
        this.id = id;
        this.measuredtime = measuredtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getMeasuredtime() {
        return measuredtime;
    }

    public void setMeasuredtime(LocalTime measuredtime) {
        this.measuredtime = measuredtime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Reading getAfternoonreadingId() {
        return afternoonreadingId;
    }

    public void setAfternoonreadingId(Reading afternoonreadingId) {
        this.afternoonreadingId = afternoonreadingId;
    }

    public Reading getNightreadingId() {
        return nightreadingId;
    }

    public void setNightreadingId(Reading nightreadingId) {
        this.nightreadingId = nightreadingId;
    }

    public Tank getTankId() {
        return tankId;
    }

    public void setTankId(Tank tankId) {
        this.tankId = tankId;
    }

    public LocalDate getEntereddate() {
        return entereddate;
    }

    public void setEntereddate(LocalDate entereddate) {
        this.entereddate = entereddate;
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
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Measurement[ id=" + id + " ]";
    }
    
}
