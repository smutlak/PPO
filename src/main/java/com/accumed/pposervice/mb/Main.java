/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.mb;

import https.www_shafafiya_org.v2.Webservices;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author smutlak
 */
@ManagedBean
@SessionScoped
public class Main {

    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/shafafiya.doh.gov.ae/v3/webservices.asmx.wsdl")
    private Webservices service;
    
    private String signupEmail;
    private String signupPass;
    private String signRegualtor;
    private String signupFacilityLicense;
    private String signupRegUsr;
    private String signupRegPass;
    private Boolean PPOConnectionTest;
    private String status;
                 

    /**
     * Creates a new instance of Main
     */
    public Main() {
        service = new Webservices();
    }

    public String getSignupEmail() {
        return signupEmail;
    }

    public void setSignupEmail(String signupEmail) {
        this.signupEmail = signupEmail;
    }

    public String getSignupPass() {
        return signupPass;
    }

    public void setSignupPass(String signupPass) {
        this.signupPass = signupPass;
    }

    public String getSignRegualtor() {
        return signRegualtor;
    }

    public void setSignRegualtor(String signRegualtor) {
        this.signRegualtor = signRegualtor;
    }

    public String getSignupFacilityLicense() {
        return signupFacilityLicense;
    }

    public void setSignupFacilityLicense(String signupFacilityLicense) {
        this.signupFacilityLicense = signupFacilityLicense;
    }

    public String getSignupRegUsr() {
        return signupRegUsr;
    }

    public void setSignupRegUsr(String signupRegUsr) {
        this.signupRegUsr = signupRegUsr;
    }

    public String getSignupRegPass() {
        return signupRegPass;
    }

    public void setSignupRegPass(String signupRegPass) {
        this.signupRegPass = signupRegPass;
    }
    
    public void signup(){
        
    }
    
    public void testRegConnection(){
        
        java.util.Date currDate = new java.util.Date();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(currDate);
        cal.add(java.util.Calendar.DAY_OF_YEAR, -95);
        java.util.Date fromDate = cal.getTime();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        
        javax.xml.ws.Holder<Integer> searchTransactionsResult = new javax.xml.ws.Holder<Integer>();
        javax.xml.ws.Holder<java.lang.String> foundTransactions = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> errorMessage = new javax.xml.ws.Holder<java.lang.String>();
        
        try { // Call Web Service Operation
            https.www_shafafiya_org.v2.WebservicesSoap port = service.getWebservicesSoap();
            // TODO initialize WS operation arguments here
            java.lang.String login = this.signupRegUsr;
            java.lang.String pwd = this.signupRegPass;
            int direction = 1;
            java.lang.String callerLicense = this.signupFacilityLicense;
            java.lang.String ePartner = null;
            int transactionID = 2;
            int transactionStatus = 2;
            java.lang.String transactionFileName = null;
            java.lang.String transactionFromDate = formatter.format(fromDate);
            java.lang.String transactionToDate = formatter.format(currDate);
            int minRecordCount = -1;
            int maxRecordCount = -1;
            
            port.searchTransactions(login, pwd, direction, callerLicense, ePartner, transactionID, transactionStatus, transactionFileName, transactionFromDate, transactionToDate, minRecordCount, maxRecordCount, searchTransactionsResult, foundTransactions, errorMessage);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Exception caught", ex);
            ex.printStackTrace();            
            this.status = ex.getMessage();
            this.PPOConnectionTest = false;
            return;
        }
        
        if(errorMessage.value != null && !errorMessage.value.isEmpty()){
            this.status = errorMessage.value;
            this.PPOConnectionTest = false;
            return;
        }
        
        if(searchTransactionsResult != null && searchTransactionsResult.value!=0){
            this.status = "searchTransactionsResult returned="+searchTransactionsResult.value;
            this.PPOConnectionTest = false;
            return;
        }

        this.status = "Regulator connection test completed successfully.";
        this.PPOConnectionTest = true;
    }

    public Boolean getPPOConnectionTest() {
        return PPOConnectionTest;
    }

    public void setPPOConnectionTest(Boolean PPOConnectionTest) {
        this.PPOConnectionTest = PPOConnectionTest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
