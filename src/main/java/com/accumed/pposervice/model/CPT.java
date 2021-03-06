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
        name = "cpt",
        indexes = {
            @Index(name = "cpt_INDX_0", columnList = "code"),
            @Index(name = "cpt_INDX_1", columnList = "short_description"),
            @Index(name = "cpt_INDX_2", columnList = "long_description")})

@NamedQueries({
    @NamedQuery(name = "CPT.findAll", query = "SELECT a FROM CPT a"),
    @NamedQuery(name = "CPT.findByCodeLike", query = "SELECT a FROM CPT a WHERE lower(a.code) LIKE lower(:code)"),
    @NamedQuery(name = "CPT.findByDescLike", query = "SELECT a FROM CPT a WHERE lower(a.short_description) LIKE lower(:short_description)"),
    @NamedQuery(name = "CPT.findByCodeOrDescLike", query = "SELECT a FROM CPT a WHERE lower(a.code) LIKE lower(:code) OR lower(a.short_description) LIKE lower(:short_description)"),
    @NamedQuery(name = "CPT.findByCodeOrLongLike", query = "SELECT a FROM CPT a WHERE lower(a.code) LIKE lower(:code) OR lower(a.long_description) LIKE lower(:long_description)")})

@XmlRootElement(name = "cpt")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "code",
    "short_description",
    "long_description"
})
public class CPT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long id;

    @Column(name = "code", nullable = false)
    @XmlElement(name = "code")
    private String code;

    @Column(name = "short_description", nullable = false, length=1024)
    @XmlElement(name = "short_description")
    private String short_description;
    
    @Column(name = "long_description", nullable = false, length=2048)
    @XmlElement(name = "long_description")
    private String long_description;
    
    @Column(name = "full_description", columnDefinition="TEXT")
    //@XmlElement(name = "full_description")
    @XmlTransient
    private String full_description;

   
    public CPT() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }
}
