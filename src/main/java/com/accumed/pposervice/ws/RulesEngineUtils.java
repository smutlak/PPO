/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

import com.accumed.ws.wsinterface.rulesengine.service.AccumedValidatorWS;
import com.accumed.ws.wsinterface.rulesengine.service.AccumedValidatorWSProxy;
import com.accumed.ws.wsinterface.rulesengine.service.ScrubResponseReturn;
import com.accumed.ws.wsinterface.rulesengine.service.ScrubScrubbingRequest;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author accumeddev1
 */
public class RulesEngineUtils {
    
    protected static ScrubResponseReturn scrub(ScrubScrubbingRequest request,
            String user, String psw, Boolean debug){
        AccumedValidatorWS soap;
        AccumedValidatorWSProxy proxy = new AccumedValidatorWSProxy();
        soap = proxy.getAccumedValidatorWS();
        ScrubResponseReturn response = null;
        try {
            response= soap.scrub(request, user, psw, false);
        } catch (RemoteException ex) {
            Logger.getLogger(RulesEngineUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
