/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author smutlak
 */
@XmlRootElement(name = "TotalsVSLabs")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "month",
    "receiverid",
    "senderid",
    "total",
    "totalLab",
    "claimsCount"
})
public class TotalsVSLabs implements Serializable {
    
    @XmlElement(name = "month")
    private Integer month;
    
    @XmlElement(name = "receiverid")
    private String receiverid;
    
    @XmlElement(name = "senderid")
    private String senderid;
    
    @XmlElement(name = "total")
    private Double total;
    
    @XmlElement(name = "totalLab")
    private Double totalLab;
    
    @XmlElement(name = "claimsCount")
    private Integer claimsCount;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalLab() {
        return totalLab;
    }

    public void setTotalLab(Double totalLab) {
        this.totalLab = totalLab;
    }

    public Integer getClaimsCount() {
        return claimsCount;
    }

    public void setClaimsCount(Integer claimsCount) {
        this.claimsCount = claimsCount;
    }
    
    
    
    
}
