package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class Transfer extends BaseTest {

    @Test
    public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "220550"; //C220550
        System.out.println("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        Thread.sleep(5000);
        //Assert.assertTrue(false);
        System.out.println("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);
        System.out.println("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeAutomationLocationTab();
        Thread.sleep(2000);
        System.out.println("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.clickOnSupplyLocation_1();
        Thread.sleep(5000);
        System.out.println("/*6.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        System.out.println("/*---     count:" + countSupplyContainers);
        System.out.println("/*7.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        // print quantity and doses before
        System.out.println("/*8.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();
        Thread.sleep(2000);
        System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        //System.out.println("/*8.----Click on bulk Transfer button --*/");
        //supplyConsolePage.clickBulkTransfersButton();
        //Thread.sleep(5000);
        //System.out.println("/*9.----Enter the Dosages values for 3 row Transfers --*/");
        //int k = 3;
        //while (k <= 7) {
        //    supplyConsolePage.enterBulkTransferDosages(k);
       //     int n = k - 2;
        //    System.out.println("/*---     dose slot N%: " + n);
       //     Thread.sleep(1000);
       //     k = k + 2;
       // }
        //System.out.println("/*10.----select 'To' Automation Supply Location_2  --*/");
        //supplyConsolePage.selectSupplyLocatonTo();
        //Thread.sleep(2000);
        //System.out.println("/*11.----click Transfer dialog Modal button --*/");
        //supplyConsolePage.clickBulkTransfersModalButton();
        //Thread.sleep(2000);
        //System.out.println("/*12.----click Close Modal button --*/");
       // supplyConsolePage.clickBulkTransfersCloseButton();
        //Thread.sleep(5000);
        //System.out.println("/*13.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        //supplyConsolePage.clickTransactionsTab();
        //Thread.sleep(5000);
        //System.out.println("/*14----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        //System.out.println("/*14.1----Get how many Outgoing Transactions 'From' count records --*/");
        //int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        //Thread.sleep(5000);
        //System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
        //int nn = 1;
        ///int kk = countOutgoingTransactions;
        // while (nn <= 3 ){
        //System.out.println("/*14.2---Get Outgoing Transaction id 'form' --*/");
        //String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
        //System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
        //System.out.println("/*14.3----Click on the latest created Outgoing Transactions --*/");
        //supplyConsolePage.clickOnOutgoingTransactions(kk);
        //System.out.println("/*--transactions record number --*/:" + kk);
        //Thread.sleep(3000);
        //System.out.println("/*15.----Close All Tab's --*/");
        //supplyConsolePage.closeAutomationLocationTab();
        //Thread.sleep(3000);
        //System.out.println("/*16.----Go to Supply Locations Tab --*/");
        //supplyConsolePage.clickSupplyLocationsTab();
        //Thread.sleep(2000);
       // System.out.println("/*17.----Click on Automation Supply Location_2 --*/");
       // supplyConsolePage.clickOnSupplyLocation_2();
        //Thread.sleep(2000);
       // System.out.println("/*18.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        //supplyConsolePage.clickTransactionsTab();
        //Thread.sleep(2000);
        //System.out.println("/*19----Get how many Incoming Transactions 'To' count records --*/");
        //int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
        //Thread.sleep(2000);
        //System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
        //Thread.sleep(2000);
        //System.out.println("/*20.----Click on Checkboxes Incoming Transactions --*/");

        //System.out.println("/*21----click Confirm Incoming button Transfer --*/");
        //supplyConsolePage.clickBulkConfirmIncomingTransfersButton();
        //Thread.sleep(2000);
        //System.out.println("/*22.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
        //supplyConsolePage.selectIncomingSupplyDistribution();
        //Thread.sleep(2000);
        //System.out.println("/*23.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
        //supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
        //Thread.sleep(1000);
        //System.out.println("/*24.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        //supplyConsolePage.successMessageAppear();
        //Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        //System.out.println("/*25----Close Automation_Supply_Location_2 Tab --*/");
        //supplyConsolePage.closeAutomationLocationTab();
        //Thread.sleep(5000);


    }
}
