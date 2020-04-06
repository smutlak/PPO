/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accumed.pposervice.ws;

import com.accumed.pposervice.model.*;
import com.haad.ClaimSubmission;
import https.www_shafafiya_org.v2.Webservices;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author smutlak
 */
@WebService(serviceName = "PPO")
public class PPO {

    EntityManagerFactory entityManagerFactory;

    static final AtomicLong PersistPendingTransactionsListThread_NEXT_ID = new AtomicLong(0);
    public static ThreadPoolExecutor PersistPendingTransactionsListFixedPool
            = //(ThreadPoolExecutor)Executors.newFixedThreadPool(1);
            new ThreadPoolExecutor(0, 3,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());

    static final AtomicLong ProcessPendingTransactionsListThread_NEXT_ID = new AtomicLong(0);
    public static ThreadPoolExecutor ProcessPendingTransactionsListFixedPool
            = //(ThreadPoolExecutor)Executors.newFixedThreadPool(1);
            new ThreadPoolExecutor(0, 3,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());

    static final AtomicLong extractionFolder_NEXT_ID = new AtomicLong(0);

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

    private https.www_shafafiya_org.v2.WebservicesSoap getWebServicePort() {
        Webservices service = new Webservices();
        return service.getWebservicesSoap();
    }

    @WebMethod(operationName = "testRegConnection")
    public String testRegConnection(@WebParam(name = "facilityLicense") String facilityLicense,
            @WebParam(name = "regUsr") String regUsr,
            @WebParam(name = "regPass") String regPass) {

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

        if (errorMessage.value != null && !errorMessage.value.isEmpty()) {
            return errorMessage.value;
        }

        if (searchTransactionsResult != null && searchTransactionsResult.value != 0) {
            return "searchTransactionsResult returned=" + searchTransactionsResult.value;
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

        PersistPendingTransactionsListThread persistPendingTransactionsListThread
                = new PersistPendingTransactionsListThread(account);
        PersistPendingTransactionsListFixedPool.submit(persistPendingTransactionsListThread);

        return account == null ? -1L : account.getId();
    }

    @WebMethod(operationName = "downloadClaimSubmissionFile")
    public String downloadClaimSubmissionFile(@WebParam(name = "accountId") Long accountId,
            @WebParam(name = "fileId") String fileId) {

        String sFileName = "";
        String sTargetFileName = "";
        Account account = null;

        EntityManager em = getEMFactory().createEntityManager();
        try {
            account = (Account) em.createNamedQuery("Account.findById").setParameter("id", accountId).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }

        if (account == null) {
            return null;
        }

        try { // Call Web Service Operation
            https.www_shafafiya_org.v2.WebservicesSoap port = getWebServicePort();
            // TODO initialize WS operation arguments here
            java.lang.String login = account.getRegLoginDetails().getUsr();
            java.lang.String pwd = account.getRegLoginDetails().getPass();
            java.lang.String fileId1 = fileId;
            javax.xml.ws.Holder<Integer> downloadTransactionFileResult = new javax.xml.ws.Holder<Integer>();
            javax.xml.ws.Holder<java.lang.String> fileName = new javax.xml.ws.Holder<java.lang.String>();
            javax.xml.ws.Holder<byte[]> file = new javax.xml.ws.Holder<byte[]>();
            javax.xml.ws.Holder<java.lang.String> errorMessage = new javax.xml.ws.Holder<java.lang.String>();
            port.downloadTransactionFile(login, pwd, fileId1, downloadTransactionFileResult, fileName, file, errorMessage);
            if (downloadTransactionFileResult != null && downloadTransactionFileResult.value != null && downloadTransactionFileResult.value == 0) {
                //save file
                String TMP_DIR = System.getProperty("java.io.tmpdir");
                sFileName = TMP_DIR + java.io.File.separator + fileName.value;
                FileOutputStream fos = new FileOutputStream(sFileName);
                fos.write(file.value);
                fos.close();

                if (fileName.value.endsWith(".zip")) {
                    String sTargetDir = TMP_DIR + java.io.File.separator
                            + extractionFolder_NEXT_ID.getAndIncrement()
                            + java.io.File.separator;
                    File fTargetDir = new File(sTargetDir);
                    fTargetDir.mkdirs();
                    unzip(sFileName, sTargetDir);
                    if (fTargetDir.listFiles().length < 0 || fTargetDir.listFiles().length > 1) {
                        Logger.getLogger(PPO.class.getName()).log(Level.SEVERE, "Error",
                                "Extracted File cntains no files OR more than one file" + fTargetDir);
                        return "";
                    } else {
                        String fileNameNoExt = fileName.value.substring(0, fileName.value.lastIndexOf('.'));
                        sTargetFileName = TMP_DIR + java.io.File.separator + fileNameNoExt + ".xml";
                        Files.copy(fTargetDir.listFiles()[0].toPath(), new File(sTargetFileName).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                        //delete extraction dir
                        deleteRecursive(fTargetDir);
                        //delete zip file
                        new File(sFileName).delete();
                    }
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(PPO.class.getName()).log(Level.SEVERE, "Exception caught", ex);
            ex.printStackTrace();
            return null;
        }
        //TestChanges1
        return sTargetFileName;
    }

    private void deleteRecursive(File path) {
        File[] c = path.listFiles();
        Logger.getLogger(PPO.class.getName()).log(Level.INFO,
                "DocuNetWeb deleteRecursive", "Cleaning out folder:" + path.toString());
        System.out.println("Cleaning out folder:" + path.toString());
        for (File file : c) {
            if (file.isDirectory()) {
                Logger.getLogger(PPO.class.getName()).log(Level.INFO,
                        "DocuNetWeb deleteRecursive", "Deleting file:" + file.toString());
                System.out.println("Deleting file:" + file.toString());
                deleteRecursive(file);
                file.delete();
            } else {
                file.delete();
            }
        }
        path.delete();
    }

    @WebMethod(operationName = "readClaimSubmissionFile")
    public ClaimSubmission readClaimSubmissionFile(@WebParam(name = "fileName") String fileName) {
        return ClaimsReader.ReadXML(fileName);
    }

    @WebMethod(operationName = "saveClaimSubmissionFile")
    public Boolean saveClaimSubmissionFile(@WebParam(name = "fileName") String fileName) {
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
    public Boolean ProcessPendingTransactions() {

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

    @WebMethod(operationName = "getAccountTransactionStatus")
    public String getAccountTransactionStatus(@WebParam(name = "accountId") Long accountId) {
        Account account = null;

        EntityManager em = getEMFactory().createEntityManager();
        try {
            account = (Account) em.createNamedQuery("Account.findById").setParameter("id", accountId).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }

        if (account == null) {
            return null;
        }

        em = getEMFactory().createEntityManager();
        Long totalCount = new Long(0);
        Long persistedCount = new Long(0);

        try {
            totalCount = (Long) em.createNamedQuery("AccountTransaction.getCountByAccountId").setParameter("account", account).getSingleResult();
            persistedCount = (Long) em.createNamedQuery("AccountTransaction.getPersistedCountByAccountId").setParameter("account", account).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);

        } finally {
            em.close();
        }

        if (totalCount.equals(persistedCount)) {
            return "Completed";
        }

        return "" + persistedCount + "/" + totalCount;
    }

    @WebMethod(operationName = "getFacilityMonthTransaction")
    public java.util.List<AccountTransaction> getFacilityMonthTransaction(@WebParam(name = "accountId") Long accountId) {
        Account account = null;

        EntityManager em = getEMFactory().createEntityManager();
        try {
            account = (Account) em.createNamedQuery("Account.findById").setParameter("id", accountId).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
        } finally {
            em.close();
        }

        if (account == null) {
            return null;
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
            return null;
        }

        if (errorMessage.value != null && !errorMessage.value.isEmpty()) {
            return null;
        }

        java.util.List<AccountTransaction> trans = null;
        if (foundTransactions.value != null && !foundTransactions.value.isEmpty()) {
            trans = convert(foundTransactions.value);
        }

        if (trans != null) {
            em = getEMFactory().createEntityManager();

            try {
                em.getTransaction().begin();
                for (AccountTransaction tran : trans) {
                    tran.setAccount(account);
                    em.persist(tran);
                }
                em.getTransaction().commit();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
                em.getTransaction().rollback();
            } finally {
                em.close();
            }
        }

        return trans;
    }

    private java.util.List<AccountTransaction> convert(String sXML) {
        JAXBContext jaxbContext;
        com.accumed.pposervice.haad.service.model.Files files = null;
        java.util.List<AccountTransaction> ret = new java.util.ArrayList();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            jaxbContext = JAXBContext.newInstance(com.accumed.pposervice.haad.service.model.Files.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            files = (com.accumed.pposervice.haad.service.model.Files) jaxbUnmarshaller.unmarshal(new StringReader(sXML));

            System.out.println(files);
        } catch (JAXBException e) {
            Logger.getLogger(PPO.class.getName()).log(Level.SEVERE, null, e);
        }

        if (files != null && files.getFile() != null && !files.getFile().isEmpty()) {
            for (com.accumed.pposervice.haad.service.model.File f : files.getFile()) {
                AccountTransaction tran = null;
                try {
                    tran = new AccountTransaction();

                    tran.setAccount(null);
                    tran.setPersist(Boolean.FALSE);
                    tran.setFileid(f.getFileID());
                    tran.setFilename(f.getFileName());
                    tran.setSenderid(f.getSenderID());
                    tran.setReceiverid(f.getReceiverID());
                    tran.setTransactiondate(formatter.parse(f.getTransactionDate()));
                    tran.setRecordcount(Integer.parseInt(f.getRecordCount()));
                    tran.setIsdownloaded(Boolean.parseBoolean(f.getIsDownloaded()));
                } catch (ParseException ex) {
                    Logger.getLogger(PPO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (tran != null) {
                    ret.add(tran);
                }
            }
        }
        return ret;
    }

    private EntityManagerFactory getEMFactory() {
        return entityManagerFactory != null ? entityManagerFactory : Persistence.createEntityManagerFactory("PPOServicePU");
    }

    protected class PersistPendingTransactionsListThread implements Runnable {

        private Account account;
//        private com.accumed.model.scrubRequest.ScrubRequest req = null;
        final long PROCESS_PENDING_TRANSACTIONS_THREAD_UNIQUE_ID = PersistPendingTransactionsListThread_NEXT_ID.getAndIncrement();

        public PersistPendingTransactionsListThread(Account account) {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            this.account = account;
        }

        @Override
        public void run() {
            EntityManager em = null;
            Logger.getLogger(PersistPendingTransactionsListThread.class.getName()).
                    log(Level.INFO, "PersistPendingTransactionsListThread started '{'{0}'}'{1}",
                            new Object[]{PROCESS_PENDING_TRANSACTIONS_THREAD_UNIQUE_ID,
                                account != null
                                        ? account.getId() + " " + account.getEmail()
                                        : "account is null"});
            try {
                //getPendingTransactionsList
                em = getEMFactory().createEntityManager();
                java.util.List<AccountTransaction> trans = getFacilityMonthTransaction(account.getId());
                //Persist Transactions List
                em.getTransaction().begin();
                for (AccountTransaction tran : trans) {
                    tran.setAccount(account);
                    em.merge(tran);
                }
                em.getTransaction().commit();
                //start processing pending transactions
                ProcessPendingTransactionsListThread processPendingTransactionsListThread
                        = new ProcessPendingTransactionsListThread(account);
                ProcessPendingTransactionsListFixedPool.submit(processPendingTransactionsListThread);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
                if (em != null) {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    em.close();
                    em = null;
                }
            } finally {
                if (em != null) {
                    em.close();
                    em = null;
                }
            }
        }
    }

    protected class ProcessPendingTransactionsListThread implements Runnable {

        private Account account;
//        private com.accumed.model.scrubRequest.ScrubRequest req = null;
        final long PROCESS_PENDING_TRANSACTIONS_THREAD_UNIQUE_ID = ProcessPendingTransactionsListThread_NEXT_ID.getAndIncrement();

        public ProcessPendingTransactionsListThread(Account account) {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            this.account = account;
        }

        @Override
        public void run() {
            EntityManager em = null;
            Logger.getLogger(ProcessPendingTransactionsListThread.class.getName()).
                    log(Level.INFO, "ProcessPendingTransactionsListThread started '{'{0}'}'{1}",
                            new Object[]{PROCESS_PENDING_TRANSACTIONS_THREAD_UNIQUE_ID,
                                account != null
                                        ? account.getId() + " " + account.getEmail()
                                        : "account is null"});
            try {
                em = getEMFactory().createEntityManager();
                //get first unprocessed transaction according to date/download/parse/save
                Query q = em.createNamedQuery("AccountTransaction.findFirstUnprocessed");
                q.setParameter("account", account);
                q.setMaxResults(1);
                AccountTransaction trans = (AccountTransaction) q.getSingleResult();

                if (trans != null) {

                    Logger.getLogger(ProcessPendingTransactionsListThread.class.getName()).
                            log(Level.INFO, "ProcessPendingTransactionsListThread started "
                                    + PROCESS_PENDING_TRANSACTIONS_THREAD_UNIQUE_ID + account != null
                                            ? account.getId() + " " + account.getEmail()
                                            : "account is null" + " fileId = " + trans.getFileid());

                    //download
                    String sXmlFile = downloadClaimSubmissionFile(account.getId(), trans.getFileid());
                    File fXmlFile = new File(sXmlFile);
                    if (fXmlFile.exists()) {
                        //parse
                        ClaimSubmission submission = ClaimsReader.ReadXML(sXmlFile);
                        //save
                        submission = Utils.setParents(submission);
                        submission.setAccountTransaction(trans);
                        trans.setPersist(Boolean.TRUE);
                        em.getTransaction().begin();
                        em.merge(submission);
                        em.getTransaction().commit();
                    } else {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                                "Error", "File Not Exist:" + sXmlFile);
                    }
                }

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "an exception was thrown", e);
                if (em != null) {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    em.close();
                    em = null;
                }
            } finally {
                if (em != null) {
                    em.close();
                    em = null;
                }
            }
        }
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
