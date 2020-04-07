/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.model;

import com.haad.ClaimSubmission;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
        name = "accounttransaction",
        indexes = {
            @Index(name = "accounttransaction_INDX_0", columnList = "transactiondate"),
            @Index(name = "accounttransaction_INDX_1", columnList = "senderid"),
            @Index(name = "accounttransaction_INDX_2", columnList = "receiverid"),
            @Index(name = "accounttransaction_INDX_3", columnList = "persist")})
@NamedQueries({
    @NamedQuery(name = "AccountTransaction.findAll", query = "SELECT a FROM AccountTransaction a"),
    @NamedQuery(name = "AccountTransaction.findById", query = "SELECT a FROM AccountTransaction a WHERE a.id = :id"),
    @NamedQuery(name = "AccountTransaction.findFirstUnprocessed", query = "SELECT a FROM AccountTransaction a WHERE a.persist=FALSE order by a.transactiondate desc"),
    @NamedQuery(name = "AccountTransaction.findFirstUnprocessedByAccountId", query = "SELECT a FROM AccountTransaction a WHERE a.account = :account AND a.persist=FALSE order by a.transactiondate desc"),
    @NamedQuery(name = "AccountTransaction.findByAccountId", query = "SELECT a FROM AccountTransaction a WHERE a.account = :account"),
    @NamedQuery(name = "AccountTransaction.getCountByAccountId", query = "SELECT COUNT(a) FROM AccountTransaction a WHERE a.account = :account"),
    @NamedQuery(name = "AccountTransaction.getPersistedCountByAccountId", query = "SELECT COUNT(a) FROM AccountTransaction a WHERE a.account = :account AND a.persist=TRUE")})

@XmlRootElement(name = "Files")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fileid",
    "filename",
    "senderid",
    "receiverid",
    "transactiondate",
    "recordcount",
    "isdownloaded",
    "persist"
})
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long id;

    @Column(name = "fileid", nullable = false)
    @XmlElement(name = "fileid")
    private String fileid;

    @Column(name = "filename", nullable = false)
    @XmlElement(name = "filename")
    private String filename;

    @Column(name = "senderid", nullable = false)
    @XmlElement(name = "senderid")
    private String senderid;

    @Column(name = "receiverid", nullable = false)
    @XmlElement(name = "receiverid")
    private String receiverid;

    @Column(name = "transactiondate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @XmlElement(name = "transactiondate")
    private Date transactiondate;

    @Column(name = "recordcount", nullable = false)
    @XmlElement(name = "recordcount")
    private Integer recordcount;

    @Column(name = "isdownloaded", nullable = false)
    @XmlElement(name = "isdownloaded")
    private Boolean isdownloaded;

    @Column(name = "persist", nullable = false)
    @XmlElement(name = "persist")
    private Boolean persist;

    @JoinColumn(name = "account", referencedColumnName = "ID")
    @ManyToOne
    @XmlTransient
    private Account account;

    @OneToOne(mappedBy = "accountTransaction", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @XmlTransient
    private ClaimSubmission claimsubmission;

    public AccountTransaction() {
    }

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

    public ClaimSubmission getClaimsubmission() {
        return claimsubmission;
    }

    public void setClaimsubmission(ClaimSubmission claimsubmission) {
        this.claimsubmission = claimsubmission;
    }

}
