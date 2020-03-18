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
public class POJob implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "typ", nullable = false)
    private String typ;
    
    @Column(name = "claimsCnt", nullable = false)
    private Integer claimsCnt;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "startedAt", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startedAt;
    
    @Column(name = "completedAt", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date completedAt;
    
    
    @JoinColumn(name = "account", referencedColumnName = "ID")
    @ManyToOne
    private Account account;
    

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
        if (!(object instanceof POJob)) {
            return false;
        }
        POJob other = (POJob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accumed.pposervice.model.POJob[ id=" + id + " ]";
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getClaimsCnt() {
        return claimsCnt;
    }

    public void setClaimsCnt(Integer claimsCnt) {
        this.claimsCnt = claimsCnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
}
