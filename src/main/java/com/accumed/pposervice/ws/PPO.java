/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

import com.accumed.pposervice.model.*;
import com.haad.ClaimSubmission;
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

    @WebMethod(operationName = "signUp")
    public String signUp(@WebParam(name = "email") String email,
            @WebParam(name = "pass") String pass,
            @WebParam(name = "regualtorID") String regualtorID,
            @WebParam(name = "facilityLicense") String facilityLicense,
            @WebParam(name = "regUsr") String regUsr,
            @WebParam(name = "regPass") String regPass) {

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
            return "0";
        }

        RegLoginDetails regLoginDetails = new RegLoginDetails();
        regLoginDetails.setFacilityLicense(facilityLicense);
        regLoginDetails.setPass(regPass);
        regLoginDetails.setUsr(regUsr);
        regLoginDetails.setRegulator(regulator);

        regulator.getRegLoginDetails().add(regLoginDetails);

        Account account = new Account();
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
        return "1";
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

    private EntityManagerFactory getEMFactory() {
        return entityManagerFactory != null ? entityManagerFactory : Persistence.createEntityManagerFactory("PPOServicePU");
    }
}
