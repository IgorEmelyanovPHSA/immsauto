package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

import java.util.List;
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
		log("Target Environment: " + env);
		log("Test Case Id: " +"C"+TestcaseID);
		precondition();
		int doses = 10;
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		Map<String, Double> doses_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = doses_before.get("Remaining Doses");
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = doses_before.get("Remaining Quantity");
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*7.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
		SupplyLocationRelatedItems.selectTransferFromDropDown(driver);
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		String tradeName = ContainerTransferForm.getVaccineName(driver);//Pfizer mRNA BNT162b2 - EK4241
		log("/*--  the Trade Name is:  " + tradeName);
		log("/*10.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = ContainerTransferForm.getConversationFactor(driver);
		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
		ContainerTransferForm.enterTransferDosages(driver, "10");
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		ContainerTransferForm.selectSupplyLocationToFromDropdown(driver, supply_location_to);

		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		ContainerTransferForm.clickTransferButton(driver);
		System.out.println("/*13.----click Close Modal button --*/");
		ContainerPrintDialog.clickCloseButton(driver);
		System.out.println("/*14----Getting Remaining Doses/Remaining Quantity - After --*/");
		Map<String, Double> doses_after = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = doses_after.get("Remaining Doses");

		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = doses_after.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - doses)));
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
				Double.parseDouble(df.format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - doses) / dose_conversation_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
		System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		SupplyLocationPage.clickTransactionsTab(driver);
		System.out.println("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		System.out.println("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = SupplyLocationTransactions.getRowsOutgoingTransactionsCount(driver);
		Thread.sleep(5000);
		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int kk = countOutgoingTransactions;
		System.out.println("/*17.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = SupplyLocationTransactions.getOutgoingTransactionId(driver, kk);
		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		System.out.println("/*17.3----Click on the latest created Outgoing Transactions --*/");
		supplyConsolePage.clickOnOutgoingTransactions(kk);
		Thread.sleep(3000);
		System.out.println("/*18.----Close All Tab's --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);


		///////////////// Supply Location_2 -> Incoming //////////////////////////

		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		Map<String, Double> doses_destination_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to);
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = doses_destination_before.get("Remaining Doses");
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = doses_destination_before.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		SupplyLocationPage.clickTransactionsTab(driver);
		System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = SupplyLocationTransactions.getRowsIncomingTransactionsCount(driver);
		System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
		int j = countIncomingTransactions;
		SupplyLocationTransactions.clickOnIncomingTransactionsDropDownMenu(driver, j);
		System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
		SupplyLocationTransactions.selectConfirmIncomingDropDown(driver);
		System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
		supplyConsolePage.selectIncomingSupplyDistribution(distribution_to);
		System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
		supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
		System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
		Assert.assertTrue(all_alerts.get(0).contains("You have successfully Confirmed the Transaction"));
		System.out.println("/*29.----click on Related Item Tab --*/");
		SupplyLocationPage.clickOnRelatedItemTab(driver);
		System.out.println("/*14----Getting Remaining Doses/Remaining Quantity After --*/");
		Map<String, Double> doses_destination_after = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to);
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = doses_destination_after.get("Remaining Doses");
		System.out.println("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = doses_destination_after.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_2_1, Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_2_1 + doses)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_2_1, Double.parseDouble(df.
				format((remainingDoses_before_Lot_EK4241_Distribution_2_1 + doses) / dose_conversation_factor)));
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
		Map<String, Double> doses_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = doses_before.get("Remaining Doses");
		System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = doses_before.get("Remaining Quantity");
		System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
		Map<String, Double> doses_destination_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to_same_clinic);
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = doses_destination_before.get("Remaining Doses");
		System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		double remainingQty_beforeLot_EK4241_Distribution_1_2 = doses_destination_before.get("Remaining Quantity");
		System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_beforeLot_EK4241_Distribution_1_2);
		/////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
		System.out.println("/*8.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
		System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
		SupplyLocationRelatedItems.selectTransferFromDropDown(driver);
		System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
		String tradeName = ContainerTransferForm.getVaccineName(driver);//Pfizer mRNA BNT162b2 - EK4241
		System.out.println("/*--  the Trade Name is:  " + tradeName);
		System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
		//Double dose_conversation_factor = 5.0;
		double dose_conversation_factor = ContainerTransferForm.getConversationFactor(driver);
		System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
		System.out.println("/*12.----Entering 10 Doses in the Container-Transfer Form --*/");
		ContainerTransferForm.enterTransferDosages(driver, "10");
		System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
		ContainerTransferForm.selectSupplyLocationToFromDropdown(driver, supply_location_from);
		System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
		ContainerTransferForm.selectSupplyDistributionFromDropdown(driver, distribution_to_same_clinic);
		System.out.println("/*15.----click Transfer dialog Modal button --*/");
		ContainerTransferForm.clickTransferButton(driver);
		System.out.println("/*16.----click Close Modal button --*/");
		ContainerPrintDialog.clickCloseButton(driver);
		driver.navigate().refresh();
		/////////////////////Doses and Quantity AFTER///////////////////////////////////
		System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
		Map<String, Double> doses_after = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = doses_after.get("Remaining Doses");
		System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = doses_after.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
		Map<String, Double> doses_destination_after = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to_same_clinic);
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = doses_destination_after.get("Remaining Doses");
		System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = doses_destination_after.get("Remaining Quantity");
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

	public void precondition() throws Exception {
		log("/*1.----Login As Clinician --*/");
		cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

		Thread.sleep(3000);
		supplyConsolePage = MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);
		SupplyLocationPage.clickOnRelatedItemTab(driver);
	}
}
