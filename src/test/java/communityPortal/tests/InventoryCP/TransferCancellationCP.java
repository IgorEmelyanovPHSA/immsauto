package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static java.lang.Math.round;

@Listeners({TestListener.class})
public class TransferCancellationCP extends BaseTest {
	private static final DecimalFormat df = new DecimalFormat("#.##");
	MainPageCP cpMainPage;
	SupplyConsolePage supplyConsolePage;
	String env;
	Map<String, Object> testData;
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
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_And_Cancel_CP() throws Exception {
		//TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "223184"; //C223184
		TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "243105"; //C243105
		precondition();

		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
		double doses = 5;

		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
		Map<String, Double> doses_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDosesBeforeDistribution1_1 = doses_before.get("Remaining Doses");
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution1_1);
		double remainingQtyBeforeDistribution1_1 = doses_before.get("Remaining Quantity");
		log("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution1_1);

		log("/---- Perform doses transfer to  location " + supply_location_to + "--*/");
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
		ContainerTransferForm.enterTransferDosages(driver, Double.toString(doses));
		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
		ContainerTransferForm.selectSupplyLocationToFromDropdown(driver, supply_location_to);
		System.out.println("/*12.----click Transfer dialog Modal button --*/");
		ContainerTransferForm.clickTransferButton(driver);
		System.out.println("/*13.----click Close Modal button --*/");
		ContainerPrintDialog.clickCloseButton(driver);
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
		Map<String, Double> doses_after = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDosesAfterDistribution1_1 = doses_after.get("Remaining Doses");
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterDistribution1_1);
		double remainingQtyAfterDistribution1_1 = doses_after.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterDistribution1_1);
		double remainingDosesAfterCalculationDistribution1_1 = Double.parseDouble(df.
				format(remainingDosesBeforeDistribution1_1 - doses));
		assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);
		double remainingQtyAfterCalculationDistribution1_1 =
				Double.parseDouble(df.
						format(remainingDosesAfterCalculationDistribution1_1 / dose_conversation_factor));
		assertEquals(remainingQtyAfterDistribution1_1, remainingQtyAfterCalculationDistribution1_1);

		System.out.println("/*19.----Go to Supply Locations Tab --*/");
		MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);

		driver.navigate().refresh();
		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		Map<String, Double> doses_destination_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to);
		double remainingDosesBeforeDistribution2_1 = doses_destination_before.get("Remaining Doses");
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution2_1);
		double remainingQtyBeforeDistribution2_1 = doses_destination_before.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution2_1);

		log("/*22.----Go to Supply Location Related Tab where Transferring From --*/");
		MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

		driver.navigate().refresh();
		SupplyLocationPage.clickTransactionsTab(driver);
		System.out.println("/*23----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		System.out.println("/*23.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = SupplyLocationTransactions.getRowsOutgoingTransactionsCount(driver);
		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		int kk = countOutgoingTransactions;
		System.out.println("/*23.2---Get Outgoing Transaction id 'from' --*/");
		String outgoingSupplyTransactionId = SupplyLocationTransactions.getOutgoingTransactionId(driver, kk);
		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
		System.out.println("/*23.3----Click on the latest created Outgoing Transactions --*/");
		SupplyLocationTransactions.clickOnOutgoingTransactionsDropDownMenu(driver, kk);
		log("/*23.----Cancel Transfer --*/");
		//tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.cancelIncomingTransfer();
		SupplyLocationPage.clickOnRelatedItemTab(driver);

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		Map<String, Double> doses_after_cancel = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
		double remainingDosesAfterCancelDistribution1_1 = doses_after_cancel.get("Remaining Doses");
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution1_1);
		double remainingQtyAfterCancelDistribution1_1 = doses_after_cancel.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution1_1);
		assertEquals(remainingDosesAfterCancelDistribution1_1, remainingDosesBeforeDistribution1_1);
		assertEquals(remainingQtyAfterCancelDistribution1_1, remainingQtyBeforeDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);

		driver.navigate().refresh();
		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		Map<String, Double> doses_destinastion_after_cancel = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to);
		double remainingDosesAfterCancelDistribution2_1 = doses_destinastion_after_cancel.get("Remaining Doses");
		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution2_1);
		double remainingQtyAfterCancelDistribution2_1 = doses_destinastion_after_cancel.get("Remaining Quantity");
		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution2_1);
		assertEquals(remainingDosesAfterCancelDistribution2_1, remainingDosesBeforeDistribution2_1);
		assertEquals(remainingQtyAfterCancelDistribution2_1, remainingQtyBeforeDistribution2_1);
	}

	//We don't use Quantity field anymore it will be in READ ONLY mode, Oct 12,2023 as per Sheila Artes
	//Testcase will be disabled
//	@Test()
//	public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another_And_Cancel() throws Exception {
//		//TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "223184"; //C223184
//		TestcaseID = (env.contains("immsbc_admin")) ? "245093" : "243105"; //C243105
//		precondition();
//
//		String container_from = String.valueOf(testData.get("containerFrom"));
//		String container_to = String.valueOf(testData.get("containerTo"));
//		double quantity = 1;
//
//		log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
//		double remainingDosesBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
//		log("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution1_1);
//		double remainingQtyBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);;
//		log("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution1_1);
//
//		log("/---- Perform Quantity transfer to another location --*/");
//		log("/*7.----Click on Container's dropdown --*/");
//		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
//		log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
//		supplyConsolePage.selectTransferFromDropDown();
//
//		log("/*9.----Picked up the Trade Vaccine Name --*/");
//		String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
//		log("/*--  the Trade Name is:  " + tradeName);
//		log("/*10.----Picked up the Dose Conversation Factor --*/");
//		//Double dose_conversation_factor = 5.0;
//		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
//		log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
//		log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
//		supplyConsolePage.enterTransferQuantity(Double.toString(quantity));
//		System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
//		supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
//		System.out.println("/*12.----click Transfer dialog Modal button --*/");
//		supplyConsolePage.clickBulkTransfersModalButton();
//		System.out.println("/*13.----click Close Modal button --*/");
//		supplyConsolePage.clickBulkTransfersDialogCloseButton();
//		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
//		double remainingDosesAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
//		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterDistribution1_1);
//		double remainingQtyAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
//		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterDistribution1_1);
//		double remainingDosesAfterCalculationDistribution1_1 = Double.parseDouble(df.
//				format(remainingDosesBeforeDistribution1_1 - quantity * dose_conversation_factor));
//		assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);
//		double remainingQtyAfterCalculationDistribution1_1 = Double.parseDouble(df.
//				format(remainingQtyBeforeDistribution1_1 - quantity));
//		assertEquals(remainingQtyAfterDistribution1_1, remainingQtyAfterCalculationDistribution1_1);
//		System.out.println("/*19.----Go to Supply Locations Tab --*/");
//		supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location_to);
//
//		supplyConsolePage.refreshBrowser();
//		System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
//		double remainingDosesBeforeDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
//		System.out.println("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution2_1);
//		double remainingQtyBeforeDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
//		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution2_1);
//
//		log("/*22.----Go to Supply Location Related Tab where Transferring From --*/");
//		supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location_from);
//		Thread.sleep(2000);
//		supplyConsolePage.refreshBrowser();
//		Thread.sleep(2000);
//		supplyConsolePage.clickTransactionsTab();
//
//		System.out.println("/*23----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
//		System.out.println("/*23.1----Get how many Outgoing Transactions 'From' count records --*/");
//		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
//		System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
//		int kk = countOutgoingTransactions;
//		System.out.println("/*23.2---Get Outgoing Transaction id 'from' --*/");
//		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
//		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
//		System.out.println("/*23.3----Click on the latest created Outgoing Transactions --*/");
//		supplyConsolePage.clickOnOutgoingTransactionsDropDownMenu(kk);
//		log("/*23.----Cancel Transfer --*/");
//		//tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
//		supplyConsolePage.cancelIncomingTransfer();
//		supplyConsolePage.clickOnRelatedItemTab();
//		Thread.sleep(1000);
//		driver.navigate().refresh();
//		Thread.sleep(1000);
//		log("/----Count Remaining Supplies After Cancel Transaction --*/");
//		double remainingDosesAfterCancelDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
//		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution1_1);
//		double remainingQtyAfterCancelDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
//		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution1_1);
//		assertEquals(remainingDosesAfterCancelDistribution1_1, remainingDosesBeforeDistribution1_1);
//		assertEquals(remainingQtyAfterCancelDistribution1_1, remainingQtyBeforeDistribution1_1);
//
//		log("/----Go to Supply Location Related Tab where Transferring To --*/");
//		supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location_to);
//
//		supplyConsolePage.refreshBrowser();
//		log("/----Count Remaining Supplies After Cancel Transaction --*/");
//		double remainingDosesAfterCancelDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
//		System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution2_1);
//		double remainingQtyAfterCancelDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
//		System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution2_1);
//		assertEquals(remainingDosesAfterCancelDistribution2_1, remainingDosesBeforeDistribution2_1);
//		assertEquals(remainingQtyAfterCancelDistribution2_1, remainingQtyBeforeDistribution2_1);
//	}

	public void precondition() throws Exception {
		log("/*1.----Login to CP (newUI) as Clinician --*/");
		cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();
		MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);
		SupplyLocationPage.clickOnRelatedItemTab(driver);
		supplyConsolePage = new SupplyConsolePage(driver);
	}
}
