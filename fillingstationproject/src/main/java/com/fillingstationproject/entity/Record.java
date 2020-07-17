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
@Table(name = "record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")})
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "pumpedtime")
    private LocalTime pumpedtime;
    @Column(name = "entereddate")
    private LocalDate entereddate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "liters")
    @RegexPattern(regexp = "^([1-9]{1,6})$",message = "Invalid Volume")
    private BigDecimal liters;
    @Basic(optional = false)
    @Column(name = "price")
    @RegexPattern(regexp = "^([1-9]{1,6}.[0-9]{2})$",message = "Invalid Price")
    private BigDecimal price;
    @JoinColumn(name = "reading_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Reading readingId;

    public Record() {
    }

    public Record(Integer id) {
        this.id = id;
    }

    public Record(Integer id, LocalTime pumpedtime) {
        this.id = id;
        this.pumpedtime = pumpedtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getPumpedtime() {
        return pumpedtime;
    }

    public void setPumpedtime(LocalTime pumpedtime) {
        this.pumpedtime = pumpedtime;
    }

    public BigDecimal getLiters() {
        return liters;
    }

    public void setLiters(BigDecimal liters) {
        this.liters = liters;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Reading getReadingId() {
        return readingId;
    }

    public void setReadingId(Reading readingId) {
        this.readingId = readingId;
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
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Record[ id=" + id + " ]";
    }
    
}
