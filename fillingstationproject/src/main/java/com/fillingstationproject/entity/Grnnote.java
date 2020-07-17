/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fillingstationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "grnnote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grnnote.findAll", query = "SELECT g FROM Grnnote g")})
public class Grnnote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "grnno")
    private String grnno;
    @Column(name = "issuedate")
    private LocalDate issuedate;
    @Column(name = "totalamount")
    private BigDecimal totalamount;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "grnstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Grnstatus grnstatusId;
    @JoinColumn(name = "porder_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Porder porderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grnnoteId", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Grnnoteitem> grnnoteitemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grnnoteId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payment> paymentList;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Supplier supplierId;

    public Grnnote() {
    }

    public Grnnote(Integer id) {
        this.id = id;
    }

    public Grnnote(Integer id,String grnno,BigDecimal totalamount) {
        this.id = id;
        this.grnno = grnno;
        this.totalamount = totalamount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrnno() {
        return grnno;
    }

    public void setGrnno(String grnno) {
        this.grnno = grnno;
    }

    public LocalDate getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(LocalDate issuedate) {
        this.issuedate = issuedate;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }


    @XmlTransient

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Grnstatus getGrnstatusId() {
        return grnstatusId;
    }

    public void setGrnstatusId(Grnstatus grnstatusId) {
        this.grnstatusId = grnstatusId;
    }

    public Porder getPorderId() {
        return porderId;
    }

    public void setPorderId(Porder porderId) {
        this.porderId = porderId;
    }


    @XmlTransient
    public List<Grnnoteitem> getGrnnoteitemList() {
        return grnnoteitemList;
    }

    public void setGrnnoteitemList(List<Grnnoteitem> grnnoteitemList) {
        this.grnnoteitemList = grnnoteitemList;
    }

    @XmlTransient
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
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
        if (!(object instanceof Grnnote)) {
            return false;
        }
        Grnnote other = (Grnnote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Grnnote[ id=" + id + " ]";
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }
}
