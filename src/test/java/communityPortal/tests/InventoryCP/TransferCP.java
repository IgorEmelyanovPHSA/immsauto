package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.tests.BaseTest;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import bcvax.pages.MainPageCP;
import bcvax.pages.MainPageOrg;
import java.text.DecimalFormat;
import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;
import java.util.Map;

@Listeners({TestListener.class})
public class TransferCP extends BaseTest {
	private static final DecimalFormat df = new DecimalFormat("#.##");
	String env;
	Map<String, Object> testData;
	SupplyConsolePage supplyConsolePage;
	MainPageCP cpMainPage;
	MainPageOrg orgMainPage;
	String supply_location_from;
	String supply_location_to;
	String distribution_from;
	String distribution_to;
	String distribution_to_same_clinic;
	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
		distribution_from = String.valueOf(testData.get("distributionFrom"));
		distribution_to = String.valueOf(testData.get("distributionTo"));
		distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));
	}

	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_CP() throws Exception {
		//TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "223184"; //C223184
		TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "243105"; //C243105
		precondition();
		int doses = 10;
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferDosages("10");
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		System.out.println("/*13.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersDialogCloseButton();
		System.out.println("/*14----Getting Remaining Doses/Remaining Quantity - After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);

		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - doses)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - doses) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
		System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		supplyConsolePage.clickTransactionsTab();
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
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location_to);


		///////////////// Supply Location_2 -> Incoming //////////////////////////

		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		supplyConsolePage.clickTransactionsTab();
		System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
		int j = countIncomingTransactions;
		supplyConsolePage.clickOnIncomingTransactionsDropDownMenu(j);
		System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
		supplyConsolePage.selectConfirmIncomingDropDown();
		System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
		supplyConsolePage.selectIncomingSupplyDistribution();
		System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
		supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
		System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		supplyConsolePage.successMessageAppear();
		System.out.println("/*29.----click on Related Item Tab --*/");
		supplyConsolePage.clickOnRelatedItemTab();
		System.out.println("/*14----Getting Remaining Doses/Remaining Quantity After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_2_1, Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_2_1 + doses)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_2_1, Double.parseDouble(df.
				format((remainingDoses_before_Lot_EK4241_Distribution_2_1 + doses) / dose_conversation_factor)));
	}

	@Test(priority = 2)
	public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another() throws Exception {
		//TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "223184"; //C223184
		TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "243105"; //C243105
		precondition();

		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
		int quantity = 1;

		////// Supply Location_1 -> Outcoming
		//log("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		log("/*10.----Entering 10 Quantity in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferQuantity(Integer.toString(quantity));
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		System.out.println("/*13.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersDialogCloseButton();
		Thread.sleep(5000);
		System.out.println("/*14----Quantity Remaining Doses/Remaining Quantity check After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1 );
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * dose_conversation_factor)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * dose_conversation_factor) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
		System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		supplyConsolePage.clickTransactionsTab();
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
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location_to);

		///////////////// Supply Location_2 -> Incoming //////////////////////////

		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		supplyConsolePage.clickTransactionsTab();
		System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
		int j = countIncomingTransactions;
		supplyConsolePage.clickOnIncomingTransactionsDropDownMenu(j);
		System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
		supplyConsolePage.selectConfirmIncomingDropDown();
		System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
		supplyConsolePage.selectIncomingSupplyDistribution();
		System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
		supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
		System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		supplyConsolePage.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*29.----click on Related Item Tab --*/");
		supplyConsolePage.clickOnRelatedItemTab();
		System.out.println("/*14----Quantity Remaining Doses/Remaining Quantity check After --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_2_1, Double.parseDouble(df.
				format(remainingDoses_before_Lot_EK4241_Distribution_2_1 + quantity * dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_2_1,Double.parseDouble(df.
				format(remainingQty_before_Lot_EK4241_Distribution_2_1 + quantity)));
	}

	@Test(priority = 3)
	public void Can_do_Transfer_by_Dosages_within_the_same_Clinic() throws Exception {
		//TestcaseID = (env.contains("immsbc_admin")) ? "245094" : "223185"; //C223185
		TestcaseID = (env.contains("immsbc_admin")) ? "245094" : "243106"; //C243106
		precondition();

		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to_same_clinic = String.valueOf(testData.get("containerToSameClinic"));

		//System.out.println("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		/////////////////////Doses and Quantity BEFORE//////////////////////////////////
		System.out.println("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		double remainingQty_beforeLot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_beforeLot_EK4241_Distribution_1_2);
		/////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
		System.out.println("/*8.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		System.out.println("/*--  the Trade Name is:  " + tradeName);
		System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		System.out.println("/*12.----Entering 10 Doses in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferDosages("10");
		System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
		supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);
		System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
		supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to_same_clinic);
		System.out.println("/*15.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		System.out.println("/*16.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersDialogCloseButton();
		supplyConsolePage.refreshBrowser();
		/////////////////////Doses and Quantity AFTER///////////////////////////////////
		System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
		//////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
		System.out.println("/*19.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - 10) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
		System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(df.
						format((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 10)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_2, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(df.
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_2 + 10) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_2, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2);
	}

	@Test(priority = 4)
	public void Can_do_Transfer_by_Quantity_within_the_same_Clinic() throws Exception {
		//TestcaseID = (env.contains("immsbc_admin")) ? "245094" : "223185"; //C223185
		TestcaseID = (env.contains("immsbc_admin")) ? "245094" : "243106"; //C243106
		precondition();

		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to_same_clinic = String.valueOf(testData.get("containerToSameClinic"));
		int quantity = 10;

		//System.out.println("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
		//supplyConsolePage.selectSupplyContainer();
		/////////////////////Doses and Quantity BEFORE//////////////////////////////////
		System.out.println("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		double remainingQty_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_2);
		/////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
		System.out.println("/*8.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectTransferFromDropDown();
		System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
		System.out.println("/*--  the Trade Name is:  " + tradeName);
		System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		System.out.println("/*12.----Entering 10 Quantity in the Container-Transfer Form --*/");
		supplyConsolePage.enterTransferQuantity(Integer.toString(quantity));
		System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
		supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);
		System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
		supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to_same_clinic);
		System.out.println("/*15.----click Transfer dialog Modal button --*/");
		supplyConsolePage.clickBulkTransfersModalButton();
		System.out.println("/*16.----click Close Modal button --*/");
		supplyConsolePage.clickBulkTransfersDialogCloseButton();
		supplyConsolePage.refreshBrowser();
		/////////////////////Doses and Quantity AFTER///////////////////////////////////
		System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
		System.out.println("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
		//////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
		System.out.println("/*19.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.
						format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * dose_conversation_factor)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * dose_conversation_factor) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
		System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(df.
						format((remainingDoses_before_Lot_EK4241_Distribution_1_2 + quantity * dose_conversation_factor)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_2, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2 =
				Double.parseDouble(df.
						format(((remainingDoses_before_Lot_EK4241_Distribution_1_2 + quantity * dose_conversation_factor) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_2, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2);
	}

	public void precondition() throws Exception {
		if(env.contains("immsbc_admin")) {
			log("/*1.----Login to CP (newUI) as ImmsBC_Admin --*/");
			orgMainPage = loginPage.orgLoginAsImmsBCAdminCP();
			Thread.sleep(1000);
			orgMainPage.switchApp(Apps.BCH_VACCINATION_PORTAL.value);
			Thread.sleep(3000);
			cpMainPage = new MainPageCP(driver);
			cpMainPage.clickGoToUserDefaultsButton();
		} else {
			log("/*1.----Login to CP (newUI) as Clinician --*/");
			cpMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
		}
		Thread.sleep(3000);
		supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location_from);
	}
}
