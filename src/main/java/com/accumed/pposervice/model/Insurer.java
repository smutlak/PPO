/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Entity
@Table(
        name = "insurer",
        indexes = {
            @Index(name = "insurer_INDX_0", columnList = "auth"),
            @Index(name = "insurer_INDX_1", columnList = "insurer_name")})
@NamedQueries({
    @NamedQuery(name = "Insurer.findAll", query = "SELECT a Insurer FROM CPT a"),
    @NamedQuery(name = "Insurer.findByAuthLike", query = "SELECT a FROM Insurer a WHERE a.auth LIKE :auth"),
    @NamedQuery(name = "Insurer.findByNameLike", query = "SELECT a FROM Insurer a WHERE a.insurer_name LIKE :insurer_name"),
    @NamedQuery(name = "Insurer.findByAuthOrNameLike", query = "SELECT a FROM Insurer a WHERE a.auth LIKE :code OR a.insurer_name LIKE :insurer_name")})

@XmlRootElement(name = "cpt")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "auth",
    "insurer_type",
    "insurer_name"
})
public class Insurer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long id;

    @Column(name = "auth", nullable = false)
    @XmlElement(name = "auth")
    private String auth;
    
    @Column(name = "insurer_type", nullable = false)
    @XmlElement(name = "insurer_type")
    private String insurer_type;
    
    @Column(name = "insurer_name", nullable = false)
    @XmlElement(name = "insurer_name")
    private String insurer_name;
    

   
    public Insurer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getInsurer_type() {
        return insurer_type;
    }

    public void setInsurer_type(String insurer_type) {
        this.insurer_type = insurer_type;
    }

    
}
