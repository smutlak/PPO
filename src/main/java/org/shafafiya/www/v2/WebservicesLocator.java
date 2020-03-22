/**
 * WebservicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.shafafiya.www.v2;

public class WebservicesLocator extends org.apache.axis.client.Service implements org.shafafiya.www.v2.Webservices {

    public WebservicesLocator() {
    }


    public WebservicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebservicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebservicesSoap12
    private java.lang.String WebservicesSoap12_address = "https://shafafiya.haad.ae/v2/webservices.asmx";

    public java.lang.String getWebservicesSoap12Address() {
        return WebservicesSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebservicesSoap12WSDDServiceName = "WebservicesSoap12";

    public java.lang.String getWebservicesSoap12WSDDServiceName() {
        return WebservicesSoap12WSDDServiceName;
    }

    public void setWebservicesSoap12WSDDServiceName(java.lang.String name) {
        WebservicesSoap12WSDDServiceName = name;
    }

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebservicesSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebservicesSoap12(endpoint);
    }

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.shafafiya.www.v2.WebservicesSoap12Stub _stub = new org.shafafiya.www.v2.WebservicesSoap12Stub(portAddress, this);
            _stub.setPortName(getWebservicesSoap12WSDDServiceName());
            _stub.setTimeout(5000);
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebservicesSoap12EndpointAddress(java.lang.String address) {
        WebservicesSoap12_address = address;
    }


    // Use to get a proxy class for WebservicesSoap
    private java.lang.String WebservicesSoap_address = "https://shafafiya.haad.ae/v2/webservices.asmx";

    public java.lang.String getWebservicesSoapAddress() {
        return WebservicesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebservicesSoapWSDDServiceName = "WebservicesSoap";

    public java.lang.String getWebservicesSoapWSDDServiceName() {
        return WebservicesSoapWSDDServiceName;
    }

    public void setWebservicesSoapWSDDServiceName(java.lang.String name) {
        WebservicesSoapWSDDServiceName = name;
    }

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebservicesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebservicesSoap(endpoint);
    }

    public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.shafafiya.www.v2.WebservicesSoapStub _stub = new org.shafafiya.www.v2.WebservicesSoapStub(portAddress, this);
            _stub.setPortName(getWebservicesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebservicesSoapEndpointAddress(java.lang.String address) {
        WebservicesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.shafafiya.www.v2.WebservicesSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.shafafiya.www.v2.WebservicesSoap12Stub _stub = new org.shafafiya.www.v2.WebservicesSoap12Stub(new java.net.URL(WebservicesSoap12_address), this);
                _stub.setPortName(getWebservicesSoap12WSDDServiceName());
                return _stub;
            }
            if (org.shafafiya.www.v2.WebservicesSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.shafafiya.www.v2.WebservicesSoapStub _stub = new org.shafafiya.www.v2.WebservicesSoapStub(new java.net.URL(WebservicesSoap_address), this);
                _stub.setPortName(getWebservicesSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WebservicesSoap12".equals(inputPortName)) {
            return getWebservicesSoap12();
        }
        else if ("WebservicesSoap".equals(inputPortName)) {
            return getWebservicesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.shafafiya.org/v2/", "Webservices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.shafafiya.org/v2/", "WebservicesSoap12"));
            ports.add(new javax.xml.namespace.QName("https://www.shafafiya.org/v2/", "WebservicesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebservicesSoap12".equals(portName)) {
            setWebservicesSoap12EndpointAddress(address);
        }
        else 
if ("WebservicesSoap".equals(portName)) {
            setWebservicesSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
