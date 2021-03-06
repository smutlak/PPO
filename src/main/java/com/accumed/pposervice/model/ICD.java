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
        name = "icd",
        indexes = {
            @Index(name = "icd_INDX_0", columnList = "code"),
            @Index(name = "icd_INDX_1", columnList = "short_description"),
            @Index(name = "icd_INDX_2", columnList = "long_description")})
@NamedQueries({
        @NamedQuery(name = "ICD.findAll", query = "SELECT a FROM ICD a"),
        @NamedQuery(name = "ICD.findByCodeLike", query = "SELECT a FROM ICD a WHERE lower(a.code) LIKE lower(:code)"),
        @NamedQuery(name = "ICD.findByDescLike", query = "SELECT a FROM ICD a WHERE lower(a.short_description) LIKE lower(:short_description)"),
        @NamedQuery(name = "ICD.findByCodeOrDescLike", query = "SELECT a FROM ICD a WHERE lower(a.code) LIKE lower(:code) OR lower(a.short_description) LIKE lower(:short_description)"),
        @NamedQuery(name = "ICD.findByCodeOrLongLike", query = "SELECT a FROM ICD a WHERE lower(a.code) LIKE lower(:code) OR lower(a.long_description) LIKE lower(:long_description)")})


@XmlRootElement(name = "icd")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "code",
    "short_description",
    "long_description"
})
public class ICD implements Serializable {

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
    
    @Column(name = "long_description", length=2048)
    @XmlElement(name = "long_description")
    private String long_description;

   
    public ICD() {
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

    
}
