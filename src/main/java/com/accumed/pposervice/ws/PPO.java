/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

import com.accumed.pposervice.model.*;
import com.haad.ClaimSubmission;
import https.www_shafafiya_org.v2.Webservices;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author smutlak
 */
@WebService(serviceName = "PPO")
public class PPO {

    EntityManagerFactory entityManagerFactory;
    

    @WebMethod(operationName = "getRegulators")
    public java.util.List<Regulator> getRegulators() {
        java.util.List ret = null;
        EntityManager em = getEMFactory().createEntityManager();
        try {
            ret = em.createQuery("SELECT r FROM Regulator r").getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }
        return ret;
    }
    
    private https.www_shafafiya_org.v2.WebservicesSoap getWebServicePort(){
        Webservices service = new Webservices();
        return service.getWebservicesSoap();
    }
    
    @WebMethod(operationName = "testRegConnection")
    public String testRegConnection(@WebParam(name = "facilityLicense") String facilityLicense,
            @WebParam(name = "regUsr") String regUsr,
            @WebParam(name = "regPass") String regPass){
        
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
            https.www_shafafiya_org.v2.WebservicesSoap port = getWebServicePort();
            // TODO initialize WS operation arguments here
            java.lang.String login = regUsr;
            java.lang.String pwd = regPass;
            int direction = 1;
            java.lang.String callerLicense = facilityLicense;
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
            Logger.getLogger(PPO.class.getName()).log(Level.SEVERE, "Exception caught", ex);
            ex.printStackTrace();            
            return ex.getMessage();
        }
        
        if(errorMessage.value != null && !errorMessage.value.isEmpty()){
            return errorMessage.value;
        }
        
        if(searchTransactionsResult != null && searchTransactionsResult.value!=0){
            return "searchTransactionsResult returned="+searchTransactionsResult.value;
        }

        return "";
    }

    @WebMethod(operationName = "signUp")
    public Long signUp(@WebParam(name = "email") String email,
            @WebParam(name = "pass") String pass,
            @WebParam(name = "regualtorID") String regualtorID,
            @WebParam(name = "facilityLicense") String facilityLicense,
            @WebParam(name = "regUsr") String regUsr,
            @WebParam(name = "regPass") String regPass) {

        Account account;
        Long regID = Long.parseLong(regualtorID);

        Regulator regulator = null;
        java.util.List<Regulator> regs = getRegulators();
        for (Regulator reg : regs) {
            if (reg.getId().equals(regID)) {
                regulator = reg;
                break;
            }
        }
        if (regulator == null) {
            return -1L;
        }

        RegLoginDetails regLoginDetails = new RegLoginDetails();
        regLoginDetails.setFacilityLicense(facilityLicense);
        regLoginDetails.setPass(regPass);
        regLoginDetails.setUsr(regUsr);
        regLoginDetails.setRegulator(regulator);

        regulator.getRegLoginDetails().add(regLoginDetails);

        account = new Account();
        account.setEmail(email);
        account.setEnabled(Boolean.TRUE);
        account.setExpireDate(null);
        account.setPass(pass);
        account.setTyp(0);
        regLoginDetails.setAccount(account);
        account.setRegLoginDetails(regLoginDetails);

        account.setRegLoginDetails(regLoginDetails);

        EntityManager em = getEMFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }
        return account==null?-1L:account.getId();
    }

    @WebMethod(operationName = "readClaimSubmission")
    public ClaimSubmission readClaimSubmission(@WebParam(name = "fileName") String fileName) {
        return ClaimsReader.ReadXML(fileName);
    }

    @WebMethod(operationName = "saveClaimSubmission")
    public Boolean saveClaimSubmission(@WebParam(name = "fileName") String fileName) {
        ClaimSubmission claimSubmission = ClaimsReader.ReadXML(fileName);
        claimSubmission = Utils.setParents(claimSubmission);

        EntityManager em = getEMFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(claimSubmission);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }
        
        return true;
    }
    
    @WebMethod(operationName = "ProcessPendingTransactions")
    public Boolean ProcessPendingTransactions(){
        
        //get top facility
        //get top facility last job info
        //call ppo for remaining transactions
        //loop in the returned transactions & zip & parse & save each one.
        /*
        EntityManager em = getEMFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(claimSubmission);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }
    */
        return true;
    }
    
    @WebMethod(operationName = "getFacilityMonthTransaction")
    public Boolean getFacilityMonthTransaction(@WebParam(name = "accountId") Long accountId){
        Account account =null;
        
        EntityManager em = getEMFactory().createEntityManager();
        try {
           account = (Account)em.createNamedQuery("Account.findById").setParameter("id", accountId).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }
        
        if(account == null){
            return false;
        }
        
        Date currDate = new Date();
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date fromDate = c.getTime();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        
        javax.xml.ws.Holder<Integer> searchTransactionsResult = new javax.xml.ws.Holder<Integer>();
        javax.xml.ws.Holder<java.lang.String> foundTransactions = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> errorMessage = new javax.xml.ws.Holder<java.lang.String>();
        
        try { // Call Web Service Operation
            https.www_shafafiya_org.v2.WebservicesSoap port = getWebServicePort();
            // TODO initialize WS operation arguments here
            java.lang.String login = account.getRegLoginDetails().getUsr();
            java.lang.String pwd = account.getRegLoginDetails().getPass();
            int direction = 1;
            java.lang.String callerLicense = account.getRegLoginDetails().getFacilityLicense();
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
            Logger.getLogger(PPO.class.getName()).log(Level.SEVERE, "Exception caught", ex);
            ex.printStackTrace();            
            return false;
        }
        
        if(errorMessage.value != null && !errorMessage.value.isEmpty()){
            return false;
        }
        
        if(foundTransactions.value != null && !foundTransactions.value.isEmpty()){
            List<AccountTransaction> trans = convert(foundTransactions.value);
        }
        
//        if(searchTransactionsResult != null && searchTransactionsResult.value!=0){
//            return "searchTransactionsResult returned="+searchTransactionsResult.value;
//        }



        return true;
    }
    

    private EntityManagerFactory getEMFactory() {
        return entityManagerFactory != null ? entityManagerFactory : Persistence.createEntityManagerFactory("PPOServicePU");
    }
    
    
}
