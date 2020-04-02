/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.haad.service.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author smutlak
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fileID",
    "fileName",
    "senderID",
    "receiverID",
    "transactionDate",
    "recordCount",
    "isDownloaded"
})
public class File implements Serializable {

    @XmlAttribute(name = "FileID", required = true)
    protected String fileID;
    @XmlAttribute(name = "FileName", required = true)
    protected String fileName;
    @XmlAttribute(name = "SenderID", required = true)
    protected String senderID;
    @XmlAttribute(name = "ReceiverID", required = true)
    protected String receiverID;
    @XmlAttribute(name = "TransactionDate", required = true)
    protected String transactionDate;
    @XmlAttribute(name = "RecordCount", required = true)
    protected String recordCount;
    @XmlAttribute(name = "IsDownloaded", required = false)
    protected String isDownloaded;

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIsDownloaded() {
        return isDownloaded;
    }

    public void setIsDownloaded(String isDownloaded) {
        this.isDownloaded = isDownloaded;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }
}
