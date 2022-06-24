package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;


@Listeners({TestListener.class})
public class BulkTransfers extends BaseTest {
	
	@Test
	public void Can_do_Bulk_transfers_by_Dosages_form_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "222358";
		log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeAutomationLocationTab();
		Thread.sleep(2000);
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		log("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.clickOnSupplyLocation_1();
		Thread.sleep(5000);
		log("/*6.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
		log("/*---     count:" + countSupplyContainers);
		log("/*7.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= 3) {
			int k = 1;
			while (k <= 3) {
				supplyConsolePage.clickOnSupplyContainerCheckbox(k);
				//int n=k-1;
				log("/*---     containers record number: " + k);
				Thread.sleep(1000);
				k++;
			}
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}
		log("/*8.----Click on bulk Transfer button --*/");
		supplyConsolePage.clickBulkTransfersButton();
		Thread.sleep(5000);
		log("/*9.----Enter the Dosages values for 3 row Transfers --*/");
		int k = 3;
		while (k <= 7) {
			supplyConsolePage.enterBulkTransferDosages(k);
			int n = k - 2;
			log("/*---     dose slot N%: " + n);
			Thread.sleep(1000);
			k = k + 2;
		}
		log("/*10.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocation_2_To();
		Thread.sleep(2000);
		log("/*11.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		log("/*12.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		log("/*13.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(5000);
		log("/*14----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		log("/*14.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		Thread.sleep(5000);
		log("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int nn = 1;
		int kk = countOutgoingTransactions;
		// while (nn <= 3 ){
		log("/*14.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
		log("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		log("/*14.3----Click on the latest created Outgoing Transactions --*/");
		supplyConsolePage.clickOnOutgoingTransactions(kk);
		log("/*--transactions record number --*/:" + kk);
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
		Thread.sleep(3000);
		log("/*15.----Close All Tab's --*/");
		supplyConsolePage.closeAutomationLocationTab();
		Thread.sleep(3000);
		log("/*16.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		Thread.sleep(2000);
		log("/*17.----Click on Automation Supply Location_2 --*/");
		supplyConsolePage.clickOnSupplyLocation_2();
		Thread.sleep(2000);
		log("/*18.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(2000);
		log("/*19----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		Thread.sleep(2000);
		log("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		Thread.sleep(2000);
		log("/*20.----Click on Checkboxes Incoming Transactions --*/");
		if (countIncomingTransactions >= 3) {
			//int j=countIncomingTransactions + 5;
			int j = countIncomingTransactions;
			int i = 1;
			//int jj;
			while (i <= 3) {
				supplyConsolePage.clickOnIncomingTransactionsCheckbox(j);
				//jj=j-5;
				log("/*---     incoming transaction record number: " + j);
				j = --j;
				Thread.sleep(1000);
				i++;
			}
		} else {
			log("/*--not all 3 Incoming Transaction records are there--*/");
		}
		log("/*21----click Confirm Incoming button Transfer --*/");
		supplyConsolePage.clickBulkConfirmIncomingTransfersButton();
		Thread.sleep(2000);
		log("/*22.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
		supplyConsolePage.selectIncomingSupplyDistribution();
		Thread.sleep(2000);
		log("/*23.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
		supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
		Thread.sleep(1000);
		log("/*24.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		supplyConsolePage.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*25----Close Automation_Supply_Location_2 Tab --*/");
		supplyConsolePage.closeAutomationLocationTab();
		Thread.sleep(5000);
		
		
	}


	//@Test
	//public void Can_do_Bulk_transfers_by_Dosages_within_the_same_Clinic_as_PPHIS_BCVAXDEVIT() throws InterruptedException {
		//TestcaseID = "222360";
		//System.out.println("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		//SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		//Thread.sleep(5000);

	//}

}