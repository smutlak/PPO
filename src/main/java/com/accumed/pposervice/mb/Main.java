/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.mb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import org.shafafiya.www.v2.WebservicesLocator;
import org.shafafiya.www.v2.WebservicesSoap;

/**
 *
 * @author smutlak
 */
@ManagedBean
@SessionScoped
public class Main {
    
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
    
    public void testRegConnection(){
        WebservicesSoap soap;

        java.util.Date currDate = new java.util.Date();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(currDate);
        cal.add(java.util.Calendar.DAY_OF_YEAR, -95);
        java.util.Date fromDate = cal.getTime();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        IntHolder searchTransactionsResult = new IntHolder();
        StringHolder foundTransactions = new StringHolder();
        StringHolder errorMessage = new StringHolder();

        try { // Call Web Service Operation

            soap = new WebservicesLocator().getWebservicesSoap();
            soap.searchTransactions(this.signupRegUsr, this.signupRegPass,
                    1, this.signupFacilityLicense,
                    null, 2, 2, null, formatter.format(fromDate),
                    formatter.format(currDate), -1, -1, searchTransactionsResult, foundTransactions, errorMessage);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Exception caught", ex);
            ex.printStackTrace();
            this.status = ex.getMessage();
            this.PPOConnectionTest = false;
            return;
        }

        if (errorMessage.value != null) {
            this.status = errorMessage.value;
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
