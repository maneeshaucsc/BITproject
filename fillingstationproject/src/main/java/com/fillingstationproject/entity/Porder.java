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
@Table(name = "porder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porder.findAll", query = "SELECT p FROM Porder p")})
public class Porder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pono")
    private String pono;

    @Column(name = "deldate")
    private LocalDate deldate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "totalprice")
//    @RegexPattern(regexp = "^([0-9]{0,6}.[0-9]{2})$",message = "Invalid Total")
//    private BigDecimal totalprice;
    @Column(name = "date")
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porderId", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Porderitem> porderitemList;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "porderstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Porderstatus porderstatusId;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Supplier supplierId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porderId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Grnnote> grnnoteList;

    public Porder() {
    }

    public Porder(Integer id) {
        this.id = id;
    }

    public Porder(Integer id,String pono) {
        this.id = id;
        this.pono = pono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

//    public BigDecimal getTotalprice() {
//        return totalprice;
//    }
//
//    public void setTotalprice(BigDecimal totalprice) {
//        this.totalprice = totalprice;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDeldate() {
        return deldate;
    }

    public void setDeldate(LocalDate deldate) {
        this.deldate = deldate;
    }

    @XmlTransient
    public List<Porderitem> getPorderitemList() {
        return porderitemList;
    }

    public void setPorderitemList(List<Porderitem> porderitemList) {
        this.porderitemList = porderitemList;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Porderstatus getPorderstatusId() {
        return porderstatusId;
    }

    public void setPorderstatusId(Porderstatus porderstatusId) {
        this.porderstatusId = porderstatusId;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }

    @XmlTransient
    public List<Grnnote> getGrnnoteList() {
        return grnnoteList;
    }

    public void setGrnnoteList(List<Grnnote> grnnoteList) {
        this.grnnoteList = grnnoteList;
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
        if (!(object instanceof Porder)) {
            return false;
        }
        Porder other = (Porder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Porder[ id=" + id + " ]";
    }
    
}
