package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.MainPageCP;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.Map;

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Transfer_ImmsBCAdmin extends BaseTest {
	Boolean is_new_ui;
	String env;
	Map<String, Object> testData;
	SupplyConsolePage supplyConsolePage;
	MainPageCP communityPortalMainPage;
	String supply_location_from;
	String supply_location_to;
	String distribution_from;
	String distribution_to;
	String distribution_to_same_clinic;
	@BeforeMethod
	public void setUpClass() throws Exception {
		is_new_ui = Utils.isCommunityPortal();
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
		distribution_from = String.valueOf(testData.get("distributionFrom"));
		distribution_to = String.valueOf(testData.get("distributionTo"));
		distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));

		log("/*1.----Login to ORG (oldUI) --*/");
		supplyConsolePage = loginPage.loginAsImmsBCAdmin();
		Thread.sleep(10000);
		//Assert.assertTrue(false);
		log("/*2.----Supply Console Page displayed --*/");
		String currentApp = supplyConsolePage.currentApp();
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			supplyConsolePage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();

		////// Supply Location_1 -> Outcoming
		log("/*5.----Click on Automation Supply Location_1 --*/");

		/////////////////////////////////////////////////
		//Try generic method
		/////////////////////////////////////////////////
		supplyConsolePage.clickOnSupplyLocation(supply_location_from);
		//////////////////////////////////////////////////
		Thread.sleep(5000);
	}

	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_as_ImmsBCAdmin() throws Exception {
		TestcaseID = "223184"; //C223184
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));

		//log("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		Thread.sleep(2000);
		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
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
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);

		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);

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
		Thread.sleep(2000);
		System.out.println("/*18.----Close All Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(3000);
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
		supplyConsolePage.clickOnSupplyLocation(supply_location_to);
		Thread.sleep(2000);
		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
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
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(round(remainingDoses_before_Lot_EK4241_Distribution_2_1 + 10),2, remainingDoses_after_Lot_EK4241_Distribution_2_1);
		assertEquals(round((remainingDoses_before_Lot_EK4241_Distribution_2_1 + 10) / dose_conversation_factor),2, remainingQty_after_Lot_EK4241_Distribution_2_1);
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void Can_do_Transfer_by_Dosages_within_the_same_Clinic_as_ImmsBCAdmin() throws Exception {
		TestcaseID = "223184"; //C223184
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerToSameClinic"));

		//log("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		Thread.sleep(2000);
		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*7.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to_same_clinic);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		double remainingQty_before_Lot_EK4241_Distribution_1_2 =supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to_same_clinic);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		Thread.sleep(2000);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(2000);
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		Thread.sleep(2000);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		Thread.sleep(2000);
		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferDosages("10");
		System.out.println("/*11.----select 'To' Automation Supply Location_1  --*/");
		supplyConsolePage.selectSupplyLocation_1_To();
		Thread.sleep(2000);
		System.out.println("/*12.----select 'Supply Distribution_1_2' 'To'  --*/");
		supplyConsolePage.selectSameClinicSupplyDistribution();
		Thread.sleep(2000);
		System.out.println("/*13.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		Thread.sleep(2000);
		System.out.println("/*14.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersCloseButton();
		Thread.sleep(5000);
		System.out.println("/*15----Getting Remaining Doses/Remaining Quantity - After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*16.----Validate Remaining Doses and Remaining Quantities values --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10)));
		assertEquals(remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1, remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(new DecimalFormat("##.####").
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1, remainingQty_after_Lot_EK4241_Distribution_1_1);
		Thread.sleep(2000);
		System.out.println("/*19----Getting Remaining Doses/Remaining Quantity After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to_same_clinic);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to_same_clinic);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
		System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(round(remainingDoses_before_Lot_EK4241_Distribution_1_2 + 10),2, remainingDoses_after_Lot_EK4241_Distribution_1_2);
		assertEquals(round((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 10) / dose_conversation_factor),2, remainingQty_after_Lot_EK4241_Distribution_1_2);
		Thread.sleep(2000);
	}
}
