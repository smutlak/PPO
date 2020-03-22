package org.shafafiya.www.v2;

public class WebservicesSoapProxy implements org.shafafiya.www.v2.WebservicesSoap {
  private String _endpoint = null;
  private org.shafafiya.www.v2.WebservicesSoap webservicesSoap = null;
  
  public WebservicesSoapProxy() {
    _initWebservicesSoapProxy();
  }
  
  public WebservicesSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebservicesSoapProxy();
  }
  
  private void _initWebservicesSoapProxy() {
    try {
      webservicesSoap = (new org.shafafiya.www.v2.WebservicesLocator()).getWebservicesSoap();
      if (webservicesSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webservicesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webservicesSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webservicesSoap != null)
      ((javax.xml.rpc.Stub)webservicesSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.shafafiya.www.v2.WebservicesSoap getWebservicesSoap() {
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    return webservicesSoap;
  }
  
  public void uploadTransaction(java.lang.String login, java.lang.String pwd, byte[] fileContent, java.lang.String fileName, javax.xml.rpc.holders.IntHolder uploadTransactionResult, javax.xml.rpc.holders.StringHolder errorMessage, javax.xml.rpc.holders.ByteArrayHolder errorReport) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.uploadTransaction(login, pwd, fileContent, fileName, uploadTransactionResult, errorMessage, errorReport);
  }
  
  public void getNewTransactions(java.lang.String login, java.lang.String pwd, javax.xml.rpc.holders.IntHolder getNewTransactionsResult, javax.xml.rpc.holders.StringHolder xmlTransactions, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.getNewTransactions(login, pwd, getNewTransactionsResult, xmlTransactions, errorMessage);
  }
  
  public void downloadTransactionFile(java.lang.String login, java.lang.String pwd, java.lang.String fileId, javax.xml.rpc.holders.IntHolder downloadTransactionFileResult, javax.xml.rpc.holders.StringHolder fileName, javax.xml.rpc.holders.ByteArrayHolder file, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.downloadTransactionFile(login, pwd, fileId, downloadTransactionFileResult, fileName, file, errorMessage);
  }
  
  public void searchTransactions(java.lang.String login, java.lang.String pwd, int direction, java.lang.String callerLicense, java.lang.String ePartner, int transactionID, int transactionStatus, java.lang.String transactionFileName, java.lang.String transactionFromDate, java.lang.String transactionToDate, int minRecordCount, int maxRecordCount, javax.xml.rpc.holders.IntHolder searchTransactionsResult, javax.xml.rpc.holders.StringHolder foundTransactions, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.searchTransactions(login, pwd, direction, callerLicense, ePartner, transactionID, transactionStatus, transactionFileName, transactionFromDate, transactionToDate, minRecordCount, maxRecordCount, searchTransactionsResult, foundTransactions, errorMessage);
  }
  
  public void setTransactionDownloaded(java.lang.String login, java.lang.String pwd, java.lang.String fileId, javax.xml.rpc.holders.IntHolder setTransactionDownloadedResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.setTransactionDownloaded(login, pwd, fileId, setTransactionDownloadedResult, errorMessage);
  }
  
  public void getPrescriptions(java.lang.String login, java.lang.String pwd, java.lang.String payerID, java.lang.String memberID, javax.xml.rpc.holders.IntHolder getPrescriptionsResult, javax.xml.rpc.holders.ByteArrayHolder prescription, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.getPrescriptions(login, pwd, payerID, memberID, getPrescriptionsResult, prescription, errorMessage);
  }
  
  public void addDRGToEClaim(java.lang.String login, java.lang.String pwd, byte[] originalFileContent, java.lang.String originalFileName, java.math.BigDecimal baseRate, javax.xml.rpc.holders.IntHolder addDRGToEClaimResult, javax.xml.rpc.holders.ByteArrayHolder drgFileContent, javax.xml.rpc.holders.StringHolder drgFileName, javax.xml.rpc.holders.ByteArrayHolder auditFileContent, javax.xml.rpc.holders.ByteArrayHolder reportFileContent, javax.xml.rpc.holders.StringHolder errorMessage, javax.xml.rpc.holders.ByteArrayHolder errorReport) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.addDRGToEClaim(login, pwd, originalFileContent, originalFileName, baseRate, addDRGToEClaimResult, drgFileContent, drgFileName, auditFileContent, reportFileContent, errorMessage, errorReport);
  }
  
  public void getNewPriorAuthorizationTransactions(java.lang.String login, java.lang.String pwd, javax.xml.rpc.holders.IntHolder getNewPriorAuthorizationTransactionsResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException{
    if (webservicesSoap == null)
      _initWebservicesSoapProxy();
    webservicesSoap.getNewPriorAuthorizationTransactions(login, pwd, getNewPriorAuthorizationTransactionsResult, errorMessage);
  }
  
  
}