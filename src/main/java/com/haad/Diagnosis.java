//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.10 at 02:00:45 PM EET 
//


package com.haad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}DiagnosisType"/>
 *         &lt;element name="Code" type="{http://www.haad.ae/DataDictionary/CommonTypes}DiagnosisCode"/>
 *         &lt;element name="DxInfo" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}DxInfoType"/>
 *                   &lt;element name="Code" type="{http://www.haad.ae/DataDictionary/CommonTypes}DxInfoCode"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "type",
    "code",
    "dxInfo"
})
@Entity
@Table(name = "diagnosis")
public class Diagnosis implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient 
    private Long id;
    
    @Column(name = "type")
    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected DiagnosisType type;
    
    @Column(name = "code")
    @XmlElement(name = "Code", required = true)
    protected String code;
    
    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @XmlElement(name = "DxInfo")
    protected List<DxInfo> dxInfo;
    
    @JoinColumn(name = "claim_id", referencedColumnName = "claim_id")
    @ManyToOne
    @XmlTransient
    private Claim claim;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DiagnosisType }
     *     
     */
    public DiagnosisType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiagnosisType }
     *     
     */
    public void setType(DiagnosisType value) {
        this.type = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the dxInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dxInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDxInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DxInfo }
     * 
     * 
     */
    public List<DxInfo> getDxInfo() {
        if (dxInfo == null) {
            dxInfo = new ArrayList<DxInfo>();
        }
        return this.dxInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }
    
    

}
