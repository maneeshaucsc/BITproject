/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fillingstationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fillingstationproject.util.RegexPattern;

import java.io.Serializable;
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
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maneesha
 */
@Entity
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "company")
    @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid Company name")
    private String company;
    @Column(name = "regno")
    @Pattern(regexp = "^([A-Za-z0-9]{6})$", message = "Invalid Reg no")
    private String regno;
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address")
    private String address;
    @Column(name = "phone")
    @RegexPattern(regexp = "^0\\d{9}$", message = "Invalid phone Number")
    private Integer phone;
    @Column(name = "contactname")
    @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid Contact name")
    private String contactname;
    @Column(name = "contactno")
    @RegexPattern(regexp = "^0\\d{9}$", message = "Invalid Mobilephone Number")
    private Integer contactno;
    @Column(name = "email")
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})$",message = "Invalid Email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Porder> porderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId", fetch = FetchType.LAZY)
    private List<Supply> supplyList;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "supplierstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Supplierstatus supplierstatusId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplierId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Grnnote> grnnoteList;

    public Supplier() {
    }

    public Supplier(Integer id) {
        this.id = id;
    }

    public Supplier(Integer id,String company) {
        this.id = id;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public Integer getContactno() {
        return contactno;
    }

    public void setContactno(Integer contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Supply> getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(List<Supply> supplyList) {
        this.supplyList = supplyList;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Supplierstatus getSupplierstatusId() {
        return supplierstatusId;
    }

    public void setSupplierstatusId(Supplierstatus supplierstatusId) {
        this.supplierstatusId = supplierstatusId;
    }

    @XmlTransient
    public List<Porder> getPorderList() {
        return porderList;
    }

    public void setPorderList(List<Porder> porderList) {
        this.porderList = porderList;
    }

    @XmlTransient
    public List<Grnnote> getGrnnoteList() {
        return grnnoteList;
    }

    public void setGrnnoteList(List<Grnnote> grnnoteList) {
        this.grnnoteList = grnnoteList;
    }



    /* @XmlTransient
    public List<Porder> getPorderList() {
        return porderList;
    }

    public void setPorderList(List<Porder> porderList) {
        this.porderList = porderList;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Supplier[ id=" + id + " ]";
    }

    
}
