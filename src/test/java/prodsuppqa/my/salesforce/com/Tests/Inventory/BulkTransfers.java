package prodsuppqa.my.salesforce.com.Tests.Inventory;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.InClinicExperiencePage;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class BulkTransfers extends BaseTest {
    
    @Test
    public void Can_do_Bulk_transfers_by_Dosages_as_PPHIS_PRODSUPPQA() throws InterruptedException {
        System.out.println("/*1.----Login as an PPHIS_prodsuppqa to Supply Console --*/");
        SupplyConsolePage supplyConsolePagePage = loginPage.loginAsPPHIS();
        System.out.println("/*2.----Go to Supply Locations Tab --*/");
        Thread.sleep(5000);
        supplyConsolePagePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);
        supplyConsolePagePage.clickSupplyLocationsTab();
        System.out.println("/*3.----Click on Automation Supply Location_1 --*/");
        supplyConsolePagePage.clickOnSupplyLocation_1();
        Thread.sleep(5000);
        System.out.println("/*4.----Get Supply Containers count records --*/");
        int countSupplyContainers = supplyConsolePagePage.getRowsSupplyContainersFromCount();
        System.out.println("/*---     count:" +countSupplyContainers);
        System.out.println("/*5.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3){
            int k=2;
            while (k <= 4){
                supplyConsolePagePage.clickOnCheckbox(k);
                int n=k-1;
                System.out.println("/*---     record number: " +n);
                Thread.sleep(1000);
                k++;
            }
        }else {
            System.out.println("/*--not enough records for Bulk actions--*/");
        }
        System.out.println("/*6.----Click on bulk Transfer button --*/");
        supplyConsolePagePage.clickBulkTransfersButton();
        Thread.sleep(5000);
        System.out.println("/*7.----Enter the Dosages values for 3 row Transfers --*/");
        int k=3;
        while (k <= 7){
            supplyConsolePagePage.enterBulkTransferDosages(k);
            int n=k-2;
            System.out.println("/*---     dose slot N%: " +n);
            Thread.sleep(1000);
            k=k+2;
        }
        System.out.println("/*8.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePagePage.selectSupplyLocatonTo();
        Thread.sleep(2000);
        System.out.println("/*9.----click Transfer dialog Modal button --*/");
        supplyConsolePagePage.clickBulkTransfersModalButton();
        Thread.sleep(2000);
        System.out.println("/*10.----click Close Modal button --*/");
        supplyConsolePagePage.clickBulkTransfersCloseButton();
        Thread.sleep(2000);
        System.out.println("/*11.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePagePage.clickTransactionsTab();
        Thread.sleep(5000);
        System.out.println("/*12----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        System.out.println("/*12.1----Get how many Outgoing Transactions 'From' count records --*/");
        int countTransactions = supplyConsolePagePage.getRowsTransactionsFromCount();
        Thread.sleep(5000);
        System.out.println("/*---  Outgoing transactions 'from' count:" +countTransactions);
        int nn = 1;
        int kk = countTransactions;
        // while (nn <= 3 ){
        System.out.println("/*12.2---Get Outgoing Transaction id 'form' --*/");
        String outgoingSupplyTransactionId = supplyConsolePagePage.getOutgoingSupplyTransactionId(kk);
        System.out.println("/*--outgoing Supply Transaction From id --*/:" +outgoingSupplyTransactionId);
        System.out.println("/*12.3----Click on the latest created Outgoing Transactions --*/");
        supplyConsolePagePage.clickOnTransactionsFrom(kk);
        System.out.println("/*--transactions record number --*/:" +kk);
        Thread.sleep(5000);


        //kk=countTransactions-1;
        //System.out.println("/*---     record number: " +kk);

        //System.out.println("/*12.3---Click on Supply Transaction STRX-... Related Tab --*/");
        //supplyConsolePagePage.clickSupplyTransactionRelatedTab();
        //Thread.sleep(2000);
        //System.out.println("/*12.4---Get Outgoing Transaction id 'form' --*/");
        //String outgoingShipmentTransactionId = supplyConsolePagePage.getSupplyShipmentTransactionId();
        //System.out.println("/*--outgoing Shipment Transaction id --*/:" +outgoingShipmentTransactionId);
        //System.out.println("/*12.5---Click on Supply Shipment name SSHP-... --*/");
        //supplyConsolePagePage.clickOnSupplyShipmentName();
        //Thread.sleep(2000);
        //System.out.println("/*12.6---Get Outgoing Transaction id 'form' --*/");
        ////String outgoingId;
        ////String outgoingSupplyTransactionId = supplyConsolePagePage.getOutgoingSupplyTransactionId();
        ////System.out.println("/*--outgoing Supply Transaction From id --*/:" +outgoingSupplyTransactionId);
        //Thread.sleep(2000);
        //System.out.println("/*12.7---Get Incoming Transaction id 'to' --*/");
        //Thread.sleep(2000);
        ////nn++;
        ////}

        Thread.sleep(2000);
        System.out.println("/*13.----Go to Supply Locations Tab --*/");
        supplyConsolePagePage.clickSupplyLocationsTab();
        System.out.println("/*14.----Click on Automation Supply Location_2 --*/");
        supplyConsolePagePage.clickOnSupplyLocation_2();
        Thread.sleep(2000);
        System.out.println("/*15.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        //supplyConsolePagePage.clickTransactionsTab();
        Thread.sleep(5000);

        
        
        
        
        
        
    }
    
    
    
    
    
}