//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.10 at 02:00:45 PM EET 
//


package com.haad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SenderID" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderSenderID"/>
 *         &lt;element name="ReceiverID" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderReceiverID"/>
 *         &lt;element name="TransactionDate" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderTransactionDate"/>
 *         &lt;element name="RecordCount" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderRecordCount"/>
 *         &lt;element name="DispositionFlag" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderDispositionFlag"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "senderID",
    "receiverID",
    "transactionDate",
    "recordCount",
    "dispositionFlag"
})
@Entity
@Table(
        name = "header",
        indexes = {
            @Index(name = "header_INDX_0", columnList = "receiverID"),
            @Index(name = "header_INDX_1", columnList = "TransactionDate")})
public class Header implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient 
    private Long id;

    @Column(name = "senderID")
    @XmlElement(name = "SenderID", required = true)
    protected String senderID;
    
    @Column(name = "receiverID")
    @XmlElement(name = "ReceiverID", required = true)
    protected String receiverID;
    
    @Column(name = "TransactionDate")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(name = "TransactionDate", required = true)
    @XmlJavaTypeAdapter(com.accumed.pposervice.haad.service.model.XmlDateTimeAdapter.class)
    protected Date transactionDate;
    
    @Column(name = "RecordCount")
    @XmlElement(name = "RecordCount", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger recordCount;
    
    @Column(name = "DispositionFlag")
    @XmlElement(name = "DispositionFlag", required = true)
    @XmlSchemaType(name = "string")
    protected HeaderDispositionFlag dispositionFlag;
    
    @JoinColumn(name = "claim_submission_id", referencedColumnName = "ID")
    @ManyToOne
    @XmlTransient
    private ClaimSubmission claimSubmission;

    /**
     * Gets the value of the senderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderID() {
        return senderID;
    }

    /**
     * Sets the value of the senderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderID(String value) {
        this.senderID = value;
    }

    /**
     * Gets the value of the receiverID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiverID() {
        return receiverID;
    }

    /**
     * Sets the value of the receiverID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiverID(String value) {
        this.receiverID = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDate(Date value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the recordCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the value of the recordCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRecordCount(BigInteger value) {
        this.recordCount = value;
    }

    /**
     * Gets the value of the dispositionFlag property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderDispositionFlag }
     *     
     */
    public HeaderDispositionFlag getDispositionFlag() {
        return dispositionFlag;
    }

    /**
     * Sets the value of the dispositionFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderDispositionFlag }
     *     
     */
    public void setDispositionFlag(HeaderDispositionFlag value) {
        this.dispositionFlag = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaimSubmission getClaimSubmission() {
        return claimSubmission;
    }

    public void setClaimSubmission(ClaimSubmission claimSubmission) {
        this.claimSubmission = claimSubmission;
    }
    
    

}
