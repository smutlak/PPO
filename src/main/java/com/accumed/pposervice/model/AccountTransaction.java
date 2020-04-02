/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author smutlak
 */
@Entity
public class AccountTransaction  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "fileid", nullable = false)
    private String fileid;
    
    @Column(name = "filename", nullable = false)
    private String filename;
    
    @Column(name = "senderid", nullable = false)
    private String senderid;
    
    @Column(name = "receiverid", nullable = false)
    private String receiverid;
    
    @Column(name = "transactiondate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactiondate;
    
    @Column(name = "recordcount", nullable = false)
    private Integer recordcount;
    
    @Column(name = "isdownloaded", nullable = false)
    private Boolean isdownloaded;
    
    @Column(name = "persist", nullable = false)
    private Boolean persist;
    
    @JoinColumn(name = "account", referencedColumnName = "ID")
    @ManyToOne
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public Integer getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(Integer recordcount) {
        this.recordcount = recordcount;
    }

    public Boolean getIsdownloaded() {
        return isdownloaded;
    }

    public void setIsdownloaded(Boolean isdownloaded) {
        this.isdownloaded = isdownloaded;
    }

    public Boolean getPersist() {
        return persist;
    }

    public void setPersist(Boolean persist) {
        this.persist = persist;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
}
