/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

import com.haad.ClaimSubmission;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author smutlak
 */
public class ClaimsReader {

    public static ClaimSubmission ReadXML(String fileName) {
        ClaimSubmission claimSubmission = null;
        File xmlFile = new File(fileName);
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ClaimSubmission.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            claimSubmission = (ClaimSubmission) jaxbUnmarshaller.unmarshal(xmlFile);

            System.out.println(claimSubmission);
        } catch (JAXBException e) {
            Logger.getLogger(ClaimsReader.class.getName()).log(Level.SEVERE, null, e);
        } 
        return claimSubmission;
    }

}
