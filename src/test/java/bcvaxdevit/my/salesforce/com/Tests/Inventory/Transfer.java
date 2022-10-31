package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class Transfer extends BaseTest {
	
	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws Exception {
		TestcaseID = "220550"; //C220550
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(10000);
		//Assert.assertTrue(false);
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		////// Supply Location_1 -> Outcoming
		log("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.clickOnSupplyLocation_1();
		Thread.sleep(5000);
		//log("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		Thread.sleep(2000);
		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Distribution_1_1();
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Distribution_1_1();
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu();
		Thread.sleep(2000);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferDosages("10");
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocation_2_To();
		Thread.sleep(2000);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*13.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		System.out.println("/*14----Getting Remaining Doses/Remaining Quantity - After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container2_Distribution_1_1();
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container2_Distribution_1_1();
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1, remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1, remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(5000);
		System.out.println("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		System.out.println("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		Thread.sleep(5000);
		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int kk = countOutgoingTransactions;
		System.out.println("/*17.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		System.out.println("/*17.3----Click on the latest created Outgoing Transactions --*/");
		supplyConsolePage.clickOnOutgoingTransactions(kk);
		Thread.sleep(3000);
		System.out.println("/*18.----Close All Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(3000);
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		Thread.sleep(2000);
		///////////////// Supply Location_2 -> Incoming //////////////////////////
		System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
		supplyConsolePage.clickOnSupplyLocation_2();
		Thread.sleep(2000);
		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(2000);
		System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		Thread.sleep(2000);
		System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		Thread.sleep(2000);
		System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
		int j = countIncomingTransactions;
		supplyConsolePage.clickOnIncomingTransactionsDropDownMenu(j);
		Thread.sleep(2000);
		System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
		supplyConsolePage.selectConfirmIncomingDropDown();
		Thread.sleep(2000);
		System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
		supplyConsolePage.selectIncomingSupplyDistribution();
		Thread.sleep(2000);
		System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
		supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
		Thread.sleep(1000);
		System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		supplyConsolePage.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*29.----click on Related Item Tab --*/");
		supplyConsolePage.clickOnRelatedItemTab();
		Thread.sleep(2000);
		System.out.println("/*14----Getting Remaining Doses/Remaining Quantity After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(round(remainingDoses_before_Lot_EK4241_Distribution_2_1 + 10),2, remainingDoses_after_Lot_EK4241_Distribution_2_1);
		assertEquals(round((remainingDoses_before_Lot_EK4241_Distribution_2_1 + 10) / dose_conversation_factor),2, remainingQty_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		
		
	}

	@Test(priority = 2)
	public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws Exception {
		TestcaseID = "220550"; //C220550
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(10000);
		//Assert.assertTrue(false);
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		////// Supply Location_1 -> Outcoming
		log("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.clickOnSupplyLocation_1();
		Thread.sleep(5000);
		//log("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		Thread.sleep(2000);
		log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1();
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1();
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu();
		Thread.sleep(2000);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		log("/*10.----Entering 10 Quantity in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferQuantity("10");
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocation_2_To();
		Thread.sleep(2000);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*13.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		System.out.println("/*14----Quantity Remaining Doses/Remaining Quantity check After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1 );
		Thread.sleep(2000);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 100)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1, remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 100) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1, remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(5000);
		System.out.println("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		System.out.println("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		Thread.sleep(5000);
		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int kk = countOutgoingTransactions;
		System.out.println("/*17.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		System.out.println("/*17.3----Click on the latest created Outgoing Transactions --*/");
		supplyConsolePage.clickOnOutgoingTransactions(kk);
		Thread.sleep(3000);
		System.out.println("/*18.----Close All Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(3000);
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		Thread.sleep(2000);
		///////////////// Supply Location_2 -> Incoming //////////////////////////
		System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
		supplyConsolePage.clickOnSupplyLocation_2();
		Thread.sleep(2000);
		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(2000);
		System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		Thread.sleep(2000);
		System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		Thread.sleep(2000);
		System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
		int j = countIncomingTransactions;
		supplyConsolePage.clickOnIncomingTransactionsDropDownMenu(j);
		Thread.sleep(2000);
		System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
		supplyConsolePage.selectConfirmIncomingDropDown();
		Thread.sleep(2000);
		System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
		supplyConsolePage.selectIncomingSupplyDistribution();
		Thread.sleep(2000);
		System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
		supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
		Thread.sleep(1000);
		System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		supplyConsolePage.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*29.----click on Related Item Tab --*/");
		supplyConsolePage.clickOnRelatedItemTab();
		Thread.sleep(2000);
		System.out.println("/*14----Quantity Remaining Doses/Remaining Quantity check After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_2_1();
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(round(remainingDoses_before_Lot_EK4241_Distribution_2_1 + 100),2, remainingDoses_after_Lot_EK4241_Distribution_2_1);
		assertEquals(round((remainingDoses_before_Lot_EK4241_Distribution_2_1 + 100) / dose_conversation_factor),2, remainingQty_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);


	}

	@Test(priority = 3)
	public void Can_do_Transfer_by_Dosages_within_the_same_Clinic_as_PPHIS_BCVAXDEVIT() throws Exception {
		TestcaseID = "220557";
		log("Target Environment: "+ Utils.getTargetEnvironment());
		System.out.println("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		System.out.println("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		System.out.println("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		System.out.println("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.clickOnSupplyLocation_1();
		Thread.sleep(5000);
		//System.out.println("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		Thread.sleep(2000);
		/////////////////////Doses and Quantity BEFORE//////////////////////////////////
		System.out.println("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		double remainingQty_beforeLot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_beforeLot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		/////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
		System.out.println("/*8.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu();
		Thread.sleep(2000);
		System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);
		System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		System.out.println("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		System.out.println("/*12.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferDosages("10");
		System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
		supplyConsolePage.selectSupplyLocation_1_To();
		Thread.sleep(2000);
		System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
		supplyConsolePage.selectSameClinicSupplyDistribution();
		Thread.sleep(2000);
		System.out.println("/*15.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*16.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		/////////////////////Doses and Quantity AFTER///////////////////////////////////
		System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		//////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
		System.out.println("/*19.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1, remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1, remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 10)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2, remainingDoses_after_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 10) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2, remainingQty_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		
	}

	@Test(priority = 4)
	public void Can_do_Transfer_by_Quantity_within_the_same_Clinic_as_PPHIS_BCVAXDEVIT() throws Exception {
		TestcaseID = "220557";
		log("Target Environment: "+ Utils.getTargetEnvironment());
		System.out.println("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(5000);
		//Assert.assertTrue(false);
		System.out.println("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		System.out.println("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		System.out.println("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.clickOnSupplyLocation_1();
		Thread.sleep(5000);
		//System.out.println("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		Thread.sleep(2000);
		/////////////////////Doses and Quantity BEFORE//////////////////////////////////
		System.out.println("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		/////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
		System.out.println("/*8.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu();
		Thread.sleep(2000);
		System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);
		System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		System.out.println("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		System.out.println("/*12.----Entering 10 Quantity in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferQuantity("10");
		System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
		supplyConsolePage.selectSupplyLocation_1_To();
		Thread.sleep(2000);
		System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
		supplyConsolePage.selectSameClinicSupplyDistribution();
		Thread.sleep(2000);
		System.out.println("/*15.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*16.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		/////////////////////Doses and Quantity AFTER///////////////////////////////////
		System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1();
		System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_1_2();
		System.out.println("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		//////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
		System.out.println("/*19.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 100)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1, remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 100) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1, remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 100)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2, remainingDoses_after_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 100) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2, remainingQty_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);

	}



}
