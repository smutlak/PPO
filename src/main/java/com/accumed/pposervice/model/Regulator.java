/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author smutlak
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name"
})
@XmlRootElement(name = "Regulator")
@Entity
public class Regulator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement(name = "Id")
    private Long id;
    
    @Column(name = "name", nullable = false)
    @XmlElement(name = "Name")
    private String name;
    
    @OneToMany(mappedBy = "regulator", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @XmlTransient 
    private List<RegLoginDetails> regLoginDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Regulator)) {
            return false;
        }
        Regulator other = (Regulator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accumed.pposervice.model.Regulator[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RegLoginDetails> getRegLoginDetails() {
        if(regLoginDetails == null){
            return new java.util.ArrayList();
        }
        return regLoginDetails;
    }

    public void setRegLoginDetails(List<RegLoginDetails> regLoginDetails) {
        this.regLoginDetails = regLoginDetails;
    }

}
