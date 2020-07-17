/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fillingstationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fillingstationproject.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maneesha
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
//    @Pattern(regexp = "^([A-Z0-9a-z]{4})$", message = "Invalid Code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "currentsalesprice")
    @RegexPattern(regexp = "^([0-9]{1,6}.[0-9]{2})$",message = "Invalid Current Price")
    private BigDecimal currentsalesprice;
    @Column(name = "quantity")
    @RegexPattern(regexp = "^([1-9]{1,10})$",message = "Invalid Quantity")
    private Integer quantity;
    @Column(name = "roq")
    @RegexPattern(regexp = "^([1-9]{1,10})$",message = "Invalid ROQ")
    private Integer roq;
    @Column(name = "rop")
    @RegexPattern(regexp = "^([1-9]{1,5})$",message = "Invalid ROP")
    private Integer rop;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "itemstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Itemstatus itemstatusId;
    @JoinColumn(name = "itemtype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Itemtype itemtypeId;
    @JoinColumn(name = "meter_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Meter meterId;
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subcategory subcategoryId;
    @JoinColumn(name = "tank_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tank tankId;
    @JoinColumn(name = "unitamount_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Unitamount unitamountId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Grnnoteitem> grnnoteitemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Porderitem> porderitemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Corderitem> corderitemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Invoiceitem> invoiceitemList;


    public Item() {
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(Integer id,String name,Integer quantity,Integer rop,Integer roq) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.rop = rop;
        this.roq = roq;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentsalesprice() {
        return currentsalesprice;
    }

    public void setCurrentsalesprice(BigDecimal currentsalesprice) {
        this.currentsalesprice = currentsalesprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRop() {
        return rop;
    }

    public void setRop(Integer rop) {
        this.rop = rop;
    }

    public Integer getRoq() {
        return roq;
    }

    public void setRoq(Integer roq) {
        this.roq = roq;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Itemstatus getItemstatusId() {
        return itemstatusId;
    }

    public void setItemstatusId(Itemstatus itemstatusId) {
        this.itemstatusId = itemstatusId;
    }

    public Itemtype getItemtypeId() {
        return itemtypeId;
    }

    public void setItemtypeId(Itemtype itemtypeId) {
        this.itemtypeId = itemtypeId;
    }

    public Meter getMeterId() {
        return meterId;
    }

    public void setMeterId(Meter meterId) {
        this.meterId = meterId;
    }

    public Subcategory getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Subcategory subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Tank getTankId() {
        return tankId;
    }

    public void setTankId(Tank tankId) {
        this.tankId = tankId;
    }

    public Unitamount getUnitamountId() {
        return unitamountId;
    }

    public void setUnitamountId(Unitamount unitamountId) {
        this.unitamountId = unitamountId;
    }

    @XmlTransient
    public List<Grnnoteitem> getGrnnoteitemList() {
        return grnnoteitemList;
    }

    public void setGrnnoteitemList(List<Grnnoteitem> grnnoteitemList) {
        this.grnnoteitemList = grnnoteitemList;
    }

    @XmlTransient
    public List<Invoiceitem> getInvoiceitemList() {
        return invoiceitemList;
    }

    public void setInvoiceitemList(List<Invoiceitem> invoiceitemList) {
        this.invoiceitemList = invoiceitemList;
    }

    @XmlTransient
    public List<Porderitem> getPorderitemList() {
        return porderitemList;
    }

    public void setPorderitemList(List<Porderitem> porderitemList) {
        this.porderitemList = porderitemList;
    }

    @XmlTransient
    public List<Corderitem> getCorderitemList() {
        return corderitemList;
    }

    public void setCorderitemList(List<Corderitem> corderitemList) {
        this.corderitemList = corderitemList;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Item[ id=" + id + " ]";
    }



}
