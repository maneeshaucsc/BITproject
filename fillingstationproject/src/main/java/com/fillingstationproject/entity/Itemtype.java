/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fillingstationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
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
@Table(name = "itemtype")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Itemtype.findAll", query = "SELECT i FROM Itemtype i")})
public class Itemtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "type")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemtypeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemtypeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Category> categoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemtypeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Supply> supplyList;
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Brand brandId;


    public Itemtype() {
    }

    public Itemtype(Integer id) {
        this.id = id;
    }



    public Itemtype(Integer id,String type,Brand brandId) {
        this.id = id;
        this.type = type;
        this.brandId = brandId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @XmlTransient
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
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
        if (!(object instanceof Itemtype)) {
            return false;
        }
        Itemtype other = (Itemtype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Itemtype[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Supply> getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(List<Supply> supplyList) {
        this.supplyList = supplyList;
    }

}




