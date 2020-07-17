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
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "invoiceno")
    private String invoiceno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalamount")
    private BigDecimal totalamount;
    @Column(name = "date")
    private LocalDate date;
    @JoinColumn(name = "corder_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Corder corderId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "invoicestatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Invoicestatus invoicestatusId;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customer customerId;
    @Column(name = "totalbalance")
    private BigDecimal totalbalance;
    @Column(name = "paidtotal")
    @RegexPattern(regexp = "^([0-9]{1,6}.[0-9]{2})$",message = "Invalid Price")
    private BigDecimal paidtotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId", fetch = FetchType.LAZY,orphanRemoval = true)
   // @JsonIgnore
    private List<Invoiceitem> invoiceitemList;


    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Invoice(Integer id,String invoiceno) {
        this.id = id;
        this.invoiceno = invoiceno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Invoicestatus getInvoicestatusId() {
        return invoicestatusId;
    }

    public void setInvoicestatusId(Invoicestatus invoicestatusId) {
        this.invoicestatusId = invoicestatusId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Corder getCorderId() {
        return corderId;
    }

    public void setCorderId(Corder corderId) {
        this.corderId = corderId;
    }

    public BigDecimal getPaidtotal() {
        return paidtotal;
    }

    public void setPaidtotal(BigDecimal paidtotal) {
        this.paidtotal = paidtotal;
    }

    public BigDecimal getTotalbalance() {
        return totalbalance;
    }

    public void setTotalbalance(BigDecimal totalbalance) {
        this.totalbalance = totalbalance;
    }

    @XmlTransient
    public List<Invoiceitem> getInvoiceitemList() {
        return invoiceitemList;
    }

    public void setInvoiceitemList(List<Invoiceitem> invoiceitemList) {
        this.invoiceitemList = invoiceitemList;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Invoice[ id=" + id + " ]";
    }
    
}
