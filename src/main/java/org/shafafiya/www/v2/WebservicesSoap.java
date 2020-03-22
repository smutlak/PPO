/**
 * WebservicesSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.shafafiya.www.v2;

public interface WebservicesSoap extends java.rmi.Remote {
    public void uploadTransaction(java.lang.String login, java.lang.String pwd, byte[] fileContent, java.lang.String fileName, javax.xml.rpc.holders.IntHolder uploadTransactionResult, javax.xml.rpc.holders.StringHolder errorMessage, javax.xml.rpc.holders.ByteArrayHolder errorReport) throws java.rmi.RemoteException;
    public void getNewTransactions(java.lang.String login, java.lang.String pwd, javax.xml.rpc.holders.IntHolder getNewTransactionsResult, javax.xml.rpc.holders.StringHolder xmlTransactions, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;
    public void downloadTransactionFile(java.lang.String login, java.lang.String pwd, java.lang.String fileId, javax.xml.rpc.holders.IntHolder downloadTransactionFileResult, javax.xml.rpc.holders.StringHolder fileName, javax.xml.rpc.holders.ByteArrayHolder file, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;
    public void searchTransactions(java.lang.String login, java.lang.String pwd, int direction, java.lang.String callerLicense, java.lang.String ePartner, int transactionID, int transactionStatus, java.lang.String transactionFileName, java.lang.String transactionFromDate, java.lang.String transactionToDate, int minRecordCount, int maxRecordCount, javax.xml.rpc.holders.IntHolder searchTransactionsResult, javax.xml.rpc.holders.StringHolder foundTransactions, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;
    public void setTransactionDownloaded(java.lang.String login, java.lang.String pwd, java.lang.String fileId, javax.xml.rpc.holders.IntHolder setTransactionDownloadedResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;
    public void getPrescriptions(java.lang.String login, java.lang.String pwd, java.lang.String payerID, java.lang.String memberID, javax.xml.rpc.holders.IntHolder getPrescriptionsResult, javax.xml.rpc.holders.ByteArrayHolder prescription, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;
    public void addDRGToEClaim(java.lang.String login, java.lang.String pwd, byte[] originalFileContent, java.lang.String originalFileName, java.math.BigDecimal baseRate, javax.xml.rpc.holders.IntHolder addDRGToEClaimResult, javax.xml.rpc.holders.ByteArrayHolder drgFileContent, javax.xml.rpc.holders.StringHolder drgFileName, javax.xml.rpc.holders.ByteArrayHolder auditFileContent, javax.xml.rpc.holders.ByteArrayHolder reportFileContent, javax.xml.rpc.holders.StringHolder errorMessage, javax.xml.rpc.holders.ByteArrayHolder errorReport) throws java.rmi.RemoteException;
    public void getNewPriorAuthorizationTransactions(java.lang.String login, java.lang.String pwd, javax.xml.rpc.holders.IntHolder getNewPriorAuthorizationTransactionsResult, javax.xml.rpc.holders.StringHolder errorMessage) throws java.rmi.RemoteException;
}
