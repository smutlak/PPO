/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author smutlak
 */
@Entity
public class RegLoginDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "usr", nullable = false)
    private String usr;
    
    @Column(name = "pass", nullable = false)
    private String pass;
    
    @Column(name = "facilityLicense", nullable = false)
    private String facilityLicense;
    
    @JoinColumn(name = "account", referencedColumnName = "ID")
    @OneToOne
    private Account account;
    
    @JoinColumn(name = "regulator", referencedColumnName = "ID")
    @ManyToOne
    private Regulator regulator;

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
        if (!(object instanceof RegLoginDetails)) {
            return false;
        }
        RegLoginDetails other = (RegLoginDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accumed.pposervice.model.RegLoginDetails[ id=" + id + " ]";
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFacilityLicense() {
        return facilityLicense;
    }

    public void setFacilityLicense(String facilityLicense) {
        this.facilityLicense = facilityLicense;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Regulator getRegulator() {
        return regulator;
    }

    public void setRegulator(Regulator regulator) {
        this.regulator = regulator;
    }
    
    
}
