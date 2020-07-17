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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maneesha
 */
@Entity
@Table(name = "tank")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Tank.findAll", query = "SELECT t FROM Tank t")
        , @NamedQuery(name = "Tank.findById", query = "SELECT t FROM Tank t WHERE t.id = :id")
        , @NamedQuery(name = "Tank.findByCode", query = "SELECT t FROM Tank t WHERE t.code = :code")})
public class Tank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;
    @Column(name = "Maxvolume")
    private Integer maxvolume;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tankId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tankId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Measurement> measurementList;


    public Tank() {
    }

    public Tank(Integer id) {
        this.id = id;
    }

    public Tank(Integer id,String code) {
        this.id = id;
        this.code = code;
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

    public Integer getMaxvolume() {
        return maxvolume;
    }

    public void setMaxvolume(Integer maxvolume) {
        this.maxvolume = maxvolume;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @XmlTransient
    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
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
        if (!(object instanceof Tank)) {
            return false;
        }
        Tank other = (Tank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fillingstationproject.entity.Tank[ id=" + id + " ]";
    }

}
