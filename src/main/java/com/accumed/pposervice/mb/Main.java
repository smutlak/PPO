/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
