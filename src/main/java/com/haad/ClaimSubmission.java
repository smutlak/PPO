//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.10 at 02:00:45 PM EET 
//


package com.haad;


import com.accumed.pposervice.model.AccountTransaction;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SenderID" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderSenderID"/>
 *                   &lt;element name="ReceiverID" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderReceiverID"/>
 *                   &lt;element name="TransactionDate" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderTransactionDate"/>
 *                   &lt;element name="RecordCount" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderRecordCount"/>
 *                   &lt;element name="DispositionFlag" type="{http://www.haad.ae/DataDictionary/CommonTypes}HeaderDispositionFlag"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Claim" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ID" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimID"/>
 *                   &lt;element name="IDPayer" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimIDPayer" minOccurs="0"/>
 *                   &lt;element name="MemberID" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimMemberID" minOccurs="0"/>
 *                   &lt;element name="PayerID" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimPayerID"/>
 *                   &lt;element name="ProviderID" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimProviderID"/>
 *                   &lt;element name="EmiratesIDNumber" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimEmiratesIDNumber"/>
 *                   &lt;element name="Gross" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimGross"/>
 *                   &lt;element name="PatientShare" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimPatientShare"/>
 *                   &lt;element name="Net" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimNet"/>
 *                   &lt;element name="VAT" type="{http://www.haad.ae/DataDictionary/CommonTypes}ClaimVAT" minOccurs="0"/>
 *                   &lt;element name="Encounter" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="FacilityID" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterFacilityID"/>
 *                             &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterType"/>
 *                             &lt;element name="PatientID" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterPatientID"/>
 *                             &lt;element name="EligibilityIDPayer" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterEligibilityIDPayer" minOccurs="0"/>
 *                             &lt;element name="Start" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterStart"/>
 *                             &lt;element name="End" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterEnd" minOccurs="0"/>
 *                             &lt;element name="StartType" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterStartType" minOccurs="0"/>
 *                             &lt;element name="EndType" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterEndType" minOccurs="0"/>
 *                             &lt;element name="TransferSource" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterTransferSource" minOccurs="0"/>
 *                             &lt;element name="TransferDestination" type="{http://www.haad.ae/DataDictionary/CommonTypes}EncounterTransferDestination" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Diagnosis" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}DiagnosisType"/>
 *                             &lt;element name="Code" type="{http://www.haad.ae/DataDictionary/CommonTypes}DiagnosisCode"/>
 *                             &lt;element name="DxInfo" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}DxInfoType"/>
 *                                       &lt;element name="Code" type="{http://www.haad.ae/DataDictionary/CommonTypes}DxInfoCode"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Activity" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ID" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityID" minOccurs="0"/>
 *                             &lt;element name="Start" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityStart"/>
 *                             &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityType"/>
 *                             &lt;element name="Code" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityCode"/>
 *                             &lt;element name="Quantity" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityQuantity"/>
 *                             &lt;element name="Net" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityNet"/>
 *                             &lt;element name="OrderingClinician" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityOrderingClinician" minOccurs="0"/>
 *                             &lt;element name="Clinician" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityClinician" minOccurs="0"/>
 *                             &lt;element name="PriorAuthorizationID" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityPriorAuthorizationID" minOccurs="0"/>
 *                             &lt;element name="VAT" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityVAT" minOccurs="0"/>
 *                             &lt;element name="VATPercent" type="{http://www.haad.ae/DataDictionary/CommonTypes}ActivityVATPercent" minOccurs="0"/>
 *                             &lt;element name="Observation" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}ObservationType"/>
 *                                       &lt;element name="Code" type="{http://www.haad.ae/DataDictionary/CommonTypes}ObservationCode"/>
 *                                       &lt;element name="Value" type="{http://www.haad.ae/DataDictionary/CommonTypes}ObservationValue" minOccurs="0"/>
 *                                       &lt;element name="ValueType" type="{http://www.haad.ae/DataDictionary/CommonTypes}ObservationValueType" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Resubmission" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Type" type="{http://www.haad.ae/DataDictionary/CommonTypes}ResubmissionType"/>
 *                             &lt;element name="Comment" type="{http://www.haad.ae/DataDictionary/CommonTypes}ResubmissionComment"/>
 *                             &lt;element name="Attachment" type="{http://www.haad.ae/DataDictionary/CommonTypes}ResubmissionAttachments" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Contract" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PackageName" type="{http://www.haad.ae/DataDictionary/CommonTypes}ContractPackageName" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
    "header",
    "claim"
})
@XmlRootElement(name = "Claim.Submission")
@Entity
@Table(name = "claim_submission")
public class ClaimSubmission implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient 
    private Long id;
    
    @OneToOne(mappedBy = "claimSubmission", cascade = CascadeType.PERSIST)
    @XmlElement(name = "Header", required = true)
    protected Header header;
    
    @OneToMany(mappedBy = "claimSubmission", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @XmlElement(name = "Claim", required = true)
    protected List<Claim> claim;
    
    @JoinColumn(name = "accountTransaction", referencedColumnName = "ID")
    @OneToOne
    @XmlTransient 
    private AccountTransaction accountTransaction;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the claim property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claim property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaim().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Claim }
     * 
     * 
     */
    public List<Claim> getClaim() {
        if (claim == null) {
            claim = new ArrayList<Claim>();
        }
        return this.claim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountTransaction getAccountTransaction() {
        return accountTransaction;
    }

    public void setAccountTransaction(AccountTransaction accountTransaction) {
        this.accountTransaction = accountTransaction;
    }
}
