package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import bcvax.tests.BaseTest;

import java.text.DecimalFormat;
import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@Listeners({TestListener.class})
public class BulkTransfers extends BaseTest {
	private static final DecimalFormat df = new DecimalFormat("#.##");
	String env;
	Map<String, Object> testData;
	SupplyConsolePage supplyConsolePage;
	MainPageOrg orgMainPage;
	String supply_location_from;
	String supply_location_to;
	String distribution_from;
	String distribution_to;
	String distribution_to_same_clinic;
	HashMap<String, String> containers_from_map;
	ArrayList<String> containers_from;
	List<String> containers_to;
	List<String> containers_to_same_clinic;
	double doses;
	double quantity;
	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		doses = 1;
		quantity = 1;
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
		distribution_from = String.valueOf(testData.get("distributionFrom"));
		distribution_to = String.valueOf(testData.get("distributionTo"));
		distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));
		ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> containers_from_with_cf = (ArrayList)testData.get("bulkContainersFrom");
		containers_from_map = new HashMap<String, String>();
		containers_from = new ArrayList<>();
		for(LinkedHashMap<String, LinkedHashMap<String, String>> container_from_with_cf: containers_from_with_cf) {
			String my_key = container_from_with_cf.keySet().toArray(new String[0])[0];
			containers_from.add(my_key);
			LinkedHashMap<String, String> my_value = container_from_with_cf.get(my_key);
			containers_from_map.put(my_key, my_value.get("conversionFactor"));
		}
		//containers_from = (ArrayList)testData.get("bulkContainersFrom");
		containers_to = (ArrayList)testData.get("bulkContainersTo");
		containers_to_same_clinic = (ArrayList)testData.get("bulkContainersToSameClinic");
	}

	@Test(priority = 1)
	public void Can_do_Bulk_transfers_by_Dosages_form_one_Clinic_to_Another() throws Exception {
		TestcaseID = (env.contains("immsbc_admin")) ? "244849" : "223359";
		precondition();
		testData.get("bulkContainersFrom");

		/////////////////////Doses and Quantity BEFORE Automation Location_1//////////////////////////////////
		log("/*6.----Getting Remaining Doses/Remaining Quantity - BEFORE - Automation Location_1 --*/");
		log("/*7.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
		log("/*---     count:" + countSupplyContainers);
		log("/*8.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers < 3) {
			Assert.assertTrue(false, "Not enough containers");
		}
		Map<String, Map<String, Double>> selected_containers_doses_qty_before =  SupplyLocationRelatedItems.checkSupplyContainers(driver, new ArrayList<>(containers_from_map.keySet()));


		log("/*9.----Click on bulk Transfer button --*/");
		SupplyLocationRelatedItems.clickTransfersButton(driver);
		log("/*10.----Enter the Dosages values (1 dose) for 3 row Transfers --*/");

		supplyConsolePage.enterBulkTransferByDosages(containers_from, doses);

		log("/*11.----select 'To' Automation Supply Location_2  --*/");
		ContainerTransferPage.selectSupplyLocationToFromDropdown(driver, supply_location_to);
		log("/*12.----click Transfer dialog Modal button --*/");
		ContainerTransferPage.clickTransferButton(driver);
		log("/*13.----click Close Modal button --*/");
		ContainerPrintDialog.clickCloseButton(driver);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		/////////////////////Doses and Quantity AFTER Automation Location_1//////////////////////////////////
		log("/*14.----Getting Remaining Doses/Quantity - AFTER - Automation Location_1 --*/");
		Map<String, Map<String, Double>> selected_containers_doses_qty_after =  SupplyLocationRelatedItems.getSupplyContainers(driver, containers_from);
		for(String container: containers_from) {
			log("/*----Validation for container " + container);
			assertEquals(df.format(selected_containers_doses_qty_after.get(container).get("Remaining Doses")),
					df.format(selected_containers_doses_qty_before.get(container).get("Remaining Doses") - doses));
			//assertEquals(Double.parseDouble(df.format(selected_containers_doses_qty_after.get(container).get("Remaining Quantity"))), (Double.parseDouble(df.format(selected_containers_doses_qty_after.get(container).get("Remaining Doses"))) - doses)/);
		}

		log("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		SupplyLocationPage.clickTransactionsTab(driver);
		log("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		log("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		log("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
//		int nn = 1;
//		int kk = countOutgoingTransactions;
//		log("/*17.2---Get Outgoing Transaction id 'from' --*/");
//		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
//		log("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
//		log("/*17.3----Click on the latest created Outgoing Transactions --*/");
//		supplyConsolePage.clickOnOutgoingTransactions(kk);
//		log("/*--transactions record number --*/:" + kk);
		log("/*18.----Close All Tab's --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*19.----Go to Supply Locations Tab --*/");
		SupplyConsolePage.clickSupplyLocationsTab(driver);
		System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);
		//supplyConsolePage.clickOnSupplyLocation_2();

		///////////////////// Doses/Qty BEFORE Automation Location_2//////////////////////////////////
		log("/*21.----Getting Remaining Doses/Remaining Quantity - BEFORE - Automation Location_2 --*/");
		log("/*- container#1 - Supply Distribution_2_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_before_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(0), distribution_to);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_MT0055_Distribution_2_1);
		double remainingQty_before_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(0), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_MT0055_Distribution_2_1);
		log("/*- container#2 - Supply Distribution_2_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(1), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(1), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		log("/*- container#3 - Supply Distribution_2_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(2), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		double remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(2), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		log("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		SupplyLocationPage.clickTransactionsTab(driver);
		log("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		log("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		log("/*24.----Click on Checkboxes Incoming Transactions --*/");
		if (countIncomingTransactions >= 3) {
			for (int i = 0; i < containers_from.size(); i++) {
				supplyConsolePage.clickOnIncomingTransactionsCheckbox(countIncomingTransactions - i);
			}
		} else {
			log("/*--not all 3 Incoming Transaction records are there--*/");
		}
		log("/*25----click Confirm Incoming button Transfer --*/");
		SupplyLocationTransactions.clickConfirmIncomingTransfersButton(driver);
		log("/*26.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
		supplyConsolePage.selectIncomingSupplyDistribution(distribution_to);
		log("/*27.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
		ConfirmTransferPage.clickConfirmTransactionButton(driver);
		log("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
		Assert.assertTrue(all_alerts.get(0).contains("You have successfully Confirmed the Transaction"));
		log("/*29.----Click on Related Tab--*/");
		SupplyLocationPage.clickOnRelatedItemTab(driver);
		///////////////////// Doses/Qty AFTER Automation Location_2//////////////////////////////////
		log("/*30.----Getting Remaining Doses/Remaining Quantity - AFTER - Automation Location_2 --*/");
		log("/*- container#1 - Supply Distribution_2_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_after_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(0), distribution_to);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_MT0055_Distribution_2_1);
		double remainingQty_after_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(0), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_MT0055_Distribution_2_1);
		log("/*- container#2 - Supply Distribution_2_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(1), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(1), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		log("/*- container#3 - Supply Distribution_2_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(2), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		double remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(2), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		log("/*31.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		log("/*32.----Validate Remaining Doses/Quantities values BEFORE<->AFTER - Automation Location_1 --*/");
		log("/*----Validation for container#1 Distribution_2_1 - MT0055");
//		assertEquals(remainingDoses_after_Lot_MT0055_Distribution_2_1,
//				Double.parseDouble(df.format(remainingDoses_before_Lot_MT0055_Distribution_2_1 + doses)));
//		assertEquals(remainingQty_after_Lot_MT0055_Distribution_2_1,
//				Double.parseDouble(df.format((remainingDoses_before_Lot_MT0055_Distribution_2_1 + doses) / lot_MT0055_conversion_factor)));
//		log("/*----Validation for container#2 Distribution_2_1 - EK4241");
//		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_2_1,
//				Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_2_1 + doses)));
//		assertEquals(remainingQty_after_Lot_EK4241_Distribution_2_1,
//				Double.parseDouble(df.format((remainingDoses_before_Lot_EK4241_Distribution_2_1 + doses) / lot_EK4241_conversion_factor)));
//		log("/*----Validation for container#3 Distribution_2_1 - SPIKEVAX6-5Test001");
//		assertEquals(remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1,
//				Double.parseDouble(df.format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 + doses)));
//		assertEquals(remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1,
//				Double.parseDouble(df.format((remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 + doses) / lot_SPIKEVAX6_5Test001_conversion_factor)));
//		log("/*33----Close Automation_Supply_Location_2 Tab --*/");
		SupplyConsolePage.closeTabsHCA(driver);
	}

	//@Test(priority = 2)
	public void Can_do_Bulk_transfers_by_Quantity_form_one_Clinic_to_Another() throws Exception {
		TestcaseID = (env.contains("immsbc_admin")) ? "244849" : "223359";
		precondition();
		/////////////////////Doses and Quantity BEFORE Automation Location_1//////////////////////////////////
		log("/*6.----Getting Remaining Doses/Remaining Quantity - BEFORE - Automation Location_1 --*/");
		log("/*- container#1 -Automation Supply Distribution_1_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_before_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_MT0055_Distribution_1_1);
		double remainingQty_before_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);

		double lot_MT0055_conversion_factor = Double.parseDouble(new DecimalFormat("##.##").format(remainingDoses_before_Lot_MT0055_Distribution_1_1 / remainingQty_before_Lot_MT0055_Distribution_1_1));
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_MT0055_Distribution_1_1);
		log("/*- container#2 -Automation Supply Distribution_1_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);

		double lot_EK4241_conversion_factor = Double.parseDouble(new DecimalFormat("##.##").format(remainingDoses_before_Lot_EK4241_Distribution_1_1 / remainingQty_before_Lot_EK4241_Distribution_1_1));
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*- container#3 -Automation Supply Distribution_1_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		double remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);

		double lot_SPIKEVAX6_5Test001_conversion_factor = Double.parseDouble(new DecimalFormat("##.##").format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 / remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1));
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		log("/*7.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
		log("/*---     count:" + countSupplyContainers);
		log("/*8.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= 3) {
				SupplyLocationRelatedItems.checkSupplyContainers(driver, containers_from);
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}
		log("/*9.----Click on bulk Transfer button --*/");
		SupplyLocationRelatedItems.clickTransfersButton(driver);
		log("/*10.----Enter the Quantity values for 3 row Transfers --*/");
		supplyConsolePage.enterBulkTransferByQuantity(containers_from, doses);
		log("/*11.----select 'To' Automation Supply Location_2  --*/");
		ContainerTransferPage.selectSupplyLocationToFromDropdown(driver, supply_location_to);
		log("/*12.----click Transfer dialog Modal button --*/");
		ContainerTransferPage.clickTransferButton(driver);
		log("/*13.----click Close Modal button --*/");
		ContainerPrintDialog.clickCloseButton(driver);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		/////////////////////Doses and Quantity AFTER Automation Location_1//////////////////////////////////
		log("/*14.----Getting Remaining Doses/Quantity - AFTER - Automation Location_1 --*/");
		log("/*- container#1 -Automation Supply Distribution_1_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_after_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_MT0055_Distribution_1_1);
		double remainingQty_after_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_MT0055_Distribution_1_1);
		log("/*- container#2 -Automation Supply Distribution_1_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		log("/*- container#3 -Automation Supply Distribution_1_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		double remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		log("/*15.----Validate Remaining Doses/Quantities values BEFORE<->AFTER - Automation Location_1 --*/");
		log("/*----Validation for container#1 Distribution_1_1 - VAXZEVRIA (AstraZeneca) - MT0055");
		assertEquals(remainingDoses_after_Lot_MT0055_Distribution_1_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_MT0055_Distribution_1_1 - lot_MT0055_conversion_factor)));
		assertEquals(remainingQty_after_Lot_MT0055_Distribution_1_1,
				Double.parseDouble(df.format(remainingQty_before_Lot_MT0055_Distribution_1_1 - quantity)));
		log("/*----Validation for container#2 Distribution_1_1 - Pfizer mRNA BNT162b2 - EK4241");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * lot_EK4241_conversion_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1,
				Double.parseDouble(df.format(remainingQty_before_Lot_EK4241_Distribution_1_1 - quantity)));
		log("/*----Validation for container#3 Distribution_1_1 - SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001");
		assertEquals(remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 - quantity * lot_SPIKEVAX6_5Test001_conversion_factor)));
		assertEquals(remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1,
				Double.parseDouble(df.format(remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 - quantity)));
		log("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
		SupplyLocationPage.clickTransactionsTab(driver);
		log("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
		log("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
		int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
		log("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
		log("/*18.----Close All Tab's --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*19.----Go to Supply Locations Tab --*/");
		SupplyConsolePage.clickSupplyLocationsTab(driver);
		System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);

		/////////////////////Doses/Qty BEFORE Automation Location_2//////////////////////////////////
		log("/*21.----Getting Remaining Doses/Remaining Quantity - BEFORE - Automation Location_2 --*/");
		log("/*- container#1 - Supply Distribution_2_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_before_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(0), distribution_to);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_MT0055_Distribution_2_1);
		double remainingQty_before_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(0), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_MT0055_Distribution_2_1);
		log("/*- container#2 - Supply Distribution_2_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(1), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_2_1);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(1), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_2_1);
		log("/*- container#3 - Supply Distribution_2_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(2), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		double remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(2), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		log("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		SupplyLocationPage.clickTransactionsTab(driver);
		log("/*23----Get how many Incoming Transactions 'To' count records --*/");
		int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
		log("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
		log("/*24.----Click on Checkboxes Incoming Transactions --*/");
		if (countIncomingTransactions >= 3) {
			for (int i = 0; i < containers_from.size(); i++) {
				supplyConsolePage.clickOnIncomingTransactionsCheckbox(countIncomingTransactions - i);
			}
		} else {
			log("/*--not all 3 Incoming Transaction records are there--*/");
		}
		log("/*25----click Confirm Incoming button Transfer --*/");
		SupplyLocationTransactions.clickConfirmIncomingTransfersButton(driver);
		log("/*26.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
		supplyConsolePage.selectIncomingSupplyDistribution(distribution_to);
		log("/*27.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
		ConfirmTransferPage.clickConfirmTransactionButton(driver);
		log("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
		List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
		Assert.assertTrue(all_alerts.get(0).contains("You have successfully Confirmed the Transaction"));
		log("/*29.----Click on Related Tab--*/");
		SupplyLocationPage.clickOnRelatedItemTab(driver);
		///////////////////// Doses/Qty AFTER Automation Location_2//////////////////////////////////
		log("/*30.----Getting Remaining Doses/Remaining Quantity - AFTER - Automation Location_2 --*/");
		log("/*- container#1 - Supply Distribution_2_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_after_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(0), distribution_to);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_MT0055_Distribution_2_1);
		double remainingQty_after_Lot_MT0055_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(0), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_MT0055_Distribution_2_1);
		log("/*- container#2 - Supply Distribution_2_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(1), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_2_1);
		double remainingQty_after_Lot_EK4241_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(1), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_2_1);
		log("/*- container#3 - Supply Distribution_2_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(2), distribution_to);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		double remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(2), distribution_to);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1);
		log("/*31.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		log("/*32.----Validate Remaining Doses/Quantities values BEFORE<->AFTER - Automation Location_1 --*/");
		log("/*----Validation for container#1 Distribution_2_1 - MT0055");
		assertEquals(remainingDoses_after_Lot_MT0055_Distribution_2_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_MT0055_Distribution_2_1 + quantity * lot_MT0055_conversion_factor)));
		assertEquals(remainingQty_after_Lot_MT0055_Distribution_2_1,
				Double.parseDouble(df.format(remainingQty_before_Lot_MT0055_Distribution_2_1 + quantity)));
		log("/*----Validation for container#2 Distribution_2_1 - EK4241");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_2_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_2_1 + quantity * lot_EK4241_conversion_factor)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_2_1,
				Double.parseDouble(df.format(remainingQty_before_Lot_EK4241_Distribution_2_1 + quantity)));
		log("/*----Validation for container#3 Distribution_2_1 - SPIKEVAX6-5Test001");
		assertEquals(remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 + quantity * lot_SPIKEVAX6_5Test001_conversion_factor)));
		assertEquals(remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_2_1,
				Double.parseDouble(df.format(remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_2_1 + quantity)));
		log("/*33----Close Automation_Supply_Location_2 Tab --*/");
		SupplyConsolePage.closeTabsHCA(driver);
	}

	@Test(priority = 3)
	public void Can_do_Bulk_transfers_by_Dosages_within_the_same_Clinic() throws Exception {
		TestcaseID = (env.contains("immsbc_admin")) ? "244852" : "223363";
		precondition();
		/////////////////////Doses and Quantity BEFORE Automation Location_1//////////////////////////////////
		log("/*6.----Getting Remaining Doses/Remaining Quantity - BEFORE - Automation Location_1 --*/");
		//// Supply Distribution_1_1 - containers#1 and #2, #3
		log("/*- container#1 -Automation Supply Distribution_1_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_before_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_MT0055_Distribution_1_1);
		double remainingQty_before_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_MT0055_Distribution_1_1);
		log("/*- container#2 -Automation Supply Distribution_1_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
		double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);

		double lot_MT0055_conversion_factor = Double.parseDouble(new DecimalFormat("##.##").format(remainingDoses_before_Lot_MT0055_Distribution_1_1 / remainingQty_before_Lot_MT0055_Distribution_1_1));
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
		log("/*- container#3 -Automation Supply Distribution_1_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		double remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);

		double lot_EK4241_conversion_factor = Double.parseDouble(new DecimalFormat("##.##").format(remainingDoses_before_Lot_EK4241_Distribution_1_1 / remainingQty_before_Lot_EK4241_Distribution_1_1));
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		//// for the same Clinic, but - Supply Distribution_1_2 - containers#4 and #5, #6
		log("/*- container#4 -Automation Supply Distribution_1_2 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_before_Lot_MT0055_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to_same_clinic.get(0), distribution_to_same_clinic);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_MT0055_Distribution_1_2);
		double remainingQty_before_Lot_MT0055_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(containers_to_same_clinic.get(0), distribution_to_same_clinic);

		double lot_SPIKEVAX6_5Test001_conversion_factor = Double.parseDouble(new DecimalFormat("##.##").format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 / remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1));
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_MT0055_Distribution_1_2);
		log("/*- container#5 -Automation Supply Distribution_1_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to_same_clinic.get(1), distribution_to_same_clinic);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
		double remainingQty_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(containers_to_same_clinic.get(1), distribution_to_same_clinic);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_2);
		log("/*- container#6 -Automation Supply Distribution_1_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to_same_clinic.get(2), distribution_to_same_clinic);
		log("/*-- . remaining doses are: -->" + remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_2);
		double remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(containers_to_same_clinic.get(2), distribution_to_same_clinic);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before_Lot_SPIKEVAX6_5Test001_Distribution_1_2);
		/////////Do Transfer from Distribution_1_1 to Distribution_1_2 for the same Clinic/////////
		log("/*7.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
		log("/*---     count:" + countSupplyContainers);
		log("/*8.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= 3) {
			SupplyLocationRelatedItems.checkSupplyContainers(driver, containers_from);
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}
		log("/*9.----Click on bulk Transfer button --*/");
		SupplyLocationRelatedItems.clickTransfersButton(driver);
		log("/*10.----Enter the Dosages values (1 Dose) for 3 row Transfers --*/");
		supplyConsolePage.enterBulkTransferByDosages(containers_from, doses);
		log("/*11.----select 'To' Automation Supply Location_1  --*/");
		ContainerTransferPage.selectSupplyLocationToFromDropdown(driver, supply_location_from);
		log("/*12.----select 'To' Distribution_1_2 for the same Clinic  --*/");
		ContainerTransferPage.selectSupplyDistributionFromDropdown(driver, distribution_to_same_clinic);
		log("/*13.----click Transfer dialog Modal button --*/");
		ContainerTransferPage.clickTransferButton(driver);
		log("/*14.----click Close Modal button --*/");
		ContainerPrintDialog.clickCloseButton(driver);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		///////////////////// Doses&Qty AFTER for the same Automation Location_1///////////////////////////////////
		log("/*15.----Getting Remaining Doses/Remaining Quantity - AFTER - for the same Clinic --*/");
		//// Supply Distribution_1_1 - containers#1 and #2, #3
		log("/*- container#1 -Automation Supply Distribution_1_1 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_after_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_MT0055_Distribution_1_1);
		double remainingQty_after_Lot_MT0055_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_MT0055_Distribution_1_1);
		log("/*- container#2 -Automation Supply Distribution_1_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
		double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
		log("/*- container#3 -Automation Supply Distribution_1_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		double remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1);
		//// for the same Clinic, but - Supply Distribution_1_2 - containers#4 and #5, #6
		log("/*- container#4 -Automation Supply Distribution_1_2 & VAXZEVRIA (AstraZeneca) - MT0055*/");
		double remainingDoses_after_Lot_MT0055_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to_same_clinic.get(0), distribution_to_same_clinic);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_MT0055_Distribution_1_2);
		double remainingQty_after_Lot_MT0055_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(containers_to_same_clinic.get(0), distribution_to_same_clinic);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_MT0055_Distribution_1_2);
		log("/*- container#5 -Automation Supply Distribution_1_1 & Pfizer mRNA BNT162b2 - EK4241*/");
		double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to_same_clinic.get(1), distribution_to_same_clinic);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
		double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(containers_to_same_clinic.get(1), distribution_to_same_clinic);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
		log("/*- container#6 -Automation Supply Distribution_1_1 & SPIKEVAX 6mo-5y 0.1mg/mL (Moderna) - SPIKEVAX6-5Test001*/");
		double remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to_same_clinic.get(2), distribution_to_same_clinic);
		log("/*-- . remaining doses are: -->" + remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_2);
		double remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(containers_to_same_clinic.get(2), distribution_to_same_clinic);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_2);
		//////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
		log("/*16.----Validate Remaining Doses/Quantities values BEFORE<->AFTER - Automation Location_1 --*/");
		////////////Distribution_1_1 Validation "FROM" after
		log("/*----Validation for container#1 Distribution_1_1 - MT0055");
		assertEquals(remainingDoses_after_Lot_MT0055_Distribution_1_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_MT0055_Distribution_1_1 - doses)));
		assertEquals(remainingQty_after_Lot_MT0055_Distribution_1_1,
				Double.parseDouble(df.format((remainingDoses_before_Lot_MT0055_Distribution_1_1 - doses) / lot_MT0055_conversion_factor)));
		log("/*----Validation for container#2 Distribution_1_1 - EK4241");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_1_1 - doses)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1,
				Double.parseDouble(df.format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - doses) / lot_EK4241_conversion_factor)));
		log("/*----Validation for container#3 Distribution_1_1 - SPIKEVAX6-5Test001");
		assertEquals(remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1,
				Double.parseDouble(df.format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 - doses)));
		assertEquals(remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_1,
				Double.parseDouble(df.format((remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_1 - doses) / lot_SPIKEVAX6_5Test001_conversion_factor)));
		////////////Distribution_1_2 Validation "TO" after
		log("/*----Validation for container#4 Distribution_1_2 - MT0055");
		assertEquals(remainingDoses_after_Lot_MT0055_Distribution_1_2,
				Double.parseDouble(df.format(remainingDoses_before_Lot_MT0055_Distribution_1_2 + doses)));
		assertEquals(remainingQty_after_Lot_MT0055_Distribution_1_2,
				Double.parseDouble(df.format((remainingDoses_before_Lot_MT0055_Distribution_1_2 + doses) / lot_MT0055_conversion_factor)));
		log("/*----Validation for container#5 Distribution_1_2 - EK4241");
		assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_2,
				Double.parseDouble(df.format(remainingDoses_before_Lot_EK4241_Distribution_1_2 + doses)));
		assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_2,
				Double.parseDouble(df.format((remainingDoses_before_Lot_EK4241_Distribution_1_2 + doses) / lot_EK4241_conversion_factor)));
		log("/*----Validation for container#6 Distribution_1_2 - SPIKEVAX6-5Test001");
		assertEquals(remainingDoses_after_Lot_SPIKEVAX6_5Test001_Distribution_1_2,
				Double.parseDouble(df.format(remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_2 + doses)));
		assertEquals(remainingQty_after_Lot_SPIKEVAX6_5Test001_Distribution_1_2,
				Double.parseDouble(df.format((remainingDoses_before_Lot_SPIKEVAX6_5Test001_Distribution_1_2 + doses) / lot_SPIKEVAX6_5Test001_conversion_factor)));
		log("/*37----Close Automation_Supply_Location_1 Tab --*/");
		SupplyConsolePage.closeTabsHCA(driver);
	}

	public void precondition() throws Exception {
		log("/*1.----Login to ORG (oldUI) --*/");
		orgMainPage = loginPage.orgLoginAsPPHIS();
		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		supplyConsolePage = new SupplyConsolePage(driver);

		log("/*3.----Close All previously opened Tab's --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*4.----Go to Supply Locations Tab --*/");
		SupplyConsolePage.clickSupplyLocationsTab(driver);

		////// Supply Location_1 -> Outcoming
		log("/*5.----Click on Automation Supply Location_1 --*/");

		/////////////////////////////////////////////////
		//Try generic method
		/////////////////////////////////////////////////
		SupplyLocationsPage.selectSupplyLocationName(driver,supply_location_from);
		//////////////////////////////////////////////////
	}
}