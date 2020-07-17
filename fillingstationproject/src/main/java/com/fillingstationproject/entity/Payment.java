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
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maneesha
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "payno")
    private String payno;
    @Column(name = "date")
    private LocalDate date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nettotal")
    @RegexPattern(regexp = "^([1-9]{1,6}.[0-9]{2})$",message = "Invalid Total Price")
    private BigDecimal nettotal;
    @Column(name = "paidtotal")
    @RegexPattern(regexp = "^([1-9]{1,6}.[0-9]{2})$",message = "Invalid Paid Price")
    private BigDecimal paidtotal;
    @Column(name = "totalbalance")
    @RegexPattern(regexp = "^([1-9]{1,6}.[0-9]{2})$",message = "Invalid Total Balance")
    private BigDecimal totalbalance;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "discountprice")
    private BigDecimal discountprice;
    @JoinColumn(name = "paymenttype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Paymenttype paymenttypeId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "grnnote_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Grnnote grnnoteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Chequepayment> chequepaymentList;

    public Payment() {
    }

    public Payment(Integer id) {
        this.id = id;
    }

    public Payment(Integer id,String payno) {
        this.id = id;
        this.payno = payno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getNettotal() {
        return nettotal;
    }

    public void setNettotal(BigDecimal nettotal) {
        this.nettotal = nettotal;
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

    public Paymenttype getPaymenttypeId() {
        return paymenttypeId;
    }

    public void setPaymenttypeId(Paymenttype paymenttypeId) {
        this.paymenttypeId = paymenttypeId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Grnnote getGrnnoteId() {
        return grnnoteId;
    }

    public void setGrnnoteId(Grnnote grnnoteId) {
        this.grnnoteId = grnnoteId;
    }


    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }

    @XmlTransient
    public List<Chequepayment> getChequepaymentList() {
        return chequepaymentList;
    }

    public void setChequepaymentList(List<Chequepayment> chequepaymentList) {
        this.chequepaymentList = chequepaymentList;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
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
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Payment[ id=" + id + " ]";
    }
    
}
