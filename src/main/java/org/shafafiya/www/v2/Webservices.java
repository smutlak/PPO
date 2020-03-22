/**
 * Webservices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.shafafiya.www.v2;

public interface Webservices extends javax.xml.rpc.Service {
    public java.lang.String getWebservicesSoap12Address();

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap12() throws javax.xml.rpc.ServiceException;

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getWebservicesSoapAddress();

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap() throws javax.xml.rpc.ServiceException;

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
