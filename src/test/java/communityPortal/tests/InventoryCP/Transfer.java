package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Tables;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class Transfer extends BaseTest {


	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_as_PPHIS_Community() throws Exception {
		TestcaseID = "220550"; //C220550
		log("Target Environment: " + Utils.getTargetEnvironment());
		String vaccine = "JANSSEN COVID-19 VACCINE";
		String fromLocation = "Automation Supply Location_1";
		String toLocation = "Automation Supply Location_2";


		CommunityPortalMainPage communityPortalMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		SupplyConsolePage supplyConsolePage = communityPortalMainPage.goToSupplyLocation();
		Tables tables = loginPage.getTables();

		Map<String,String> supplyLocation_1 = new HashMap<>();
		supplyLocation_1.put("Sort\nSupply Location Name", fromLocation);
		Thread.sleep(4000);
		tables.clickOnSupplyLocationTableRow(supplyLocation_1);

		//navigate to related tab
		communityPortalMainPage.selectRelatedTab();

		Map<String,String> supplyContainerAstraZeneca = new HashMap<>();
		supplyContainerAstraZeneca.put("Sort by:\nSupply Container Name", vaccine);

		Thread.sleep(3000);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerAstraZeneca);
		tables.getSupplyLocationActions(supplyContainerAstraZeneca);

		supplyConsolePage.selectTransferFromDropDown();

		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();

		supplyConsolePage.enterTransferDosages("10").transferDosesToSupplyLocation2();

		Thread.sleep(2000);
		double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainerAstraZeneca);
		double remainingQty_after_Distribution_1_1 = tables.getRemainingQty(supplyContainerAstraZeneca);


		double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - 10.0;
		assertEquals(remainingDoses_after_Calculation_Distribution_1_1, remainingDoses_after_Distribution_1_1);

		double remainingQty_after_Calculation_Distribution_1_1 =
				remainingDoses_after_Calculation_Distribution_1_1 / dose_conversation_factor;

		assertEquals(remainingQty_after_Calculation_Distribution_1_1, remainingQty_after_Distribution_1_1);

		//go to transaction tab
		supplyConsolePage.clickTransactionsTab();

		Map<String,String> supplyItemName = new HashMap<>();
		supplyItemName.put("Sort by:\nSupply Item Name", vaccine);

		communityPortalMainPage.goToSupplyLocation();

		Map<String,String> supplyLocation_2 = new HashMap<>();
		supplyLocation_2.put("Sort\nSupply Location Name", toLocation);

		Thread.sleep(3000);
		tables.clickOnSupplyLocationTableRow(supplyLocation_2);
		communityPortalMainPage.selectRelatedTab();

//		///////////////// Supply Location_2 -> Incoming //////////////////////////

		Thread.sleep(3000);
		double remainingDoses_before_Distribution_2_1 = tables.getRemainingDoses(supplyContainerAstraZeneca);
		double remainingQty_before_Lot_EK4241_Distribution_2_1 = tables.getRemainingQty(supplyContainerAstraZeneca);

		System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
		supplyConsolePage.clickTransactionsTab();

		Thread.sleep(3000);
		tables.openShippedTransactionsIncomingActions(supplyItemName);

		supplyConsolePage.acceptIncomingTransfer();
		communityPortalMainPage.selectRelatedTab();

		Thread.sleep(3000);
		double remainingDoses_after_Distribution_2_1 = tables.getRemainingDoses(supplyContainerAstraZeneca);
		double remainingQty_after_Distribution_2_1 = tables.getRemainingQty(supplyContainerAstraZeneca);

		System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
		assertEquals(round(remainingDoses_before_Distribution_2_1 + 10),2, remainingDoses_after_Distribution_2_1);
		assertEquals(round((remainingDoses_before_Distribution_2_1 + 10) / dose_conversation_factor),2, remainingQty_after_Distribution_2_1);

	}
	@Test()
	public void Can_do_Transfer_by_Dosages_within_the_same_Clinic_as_PPHIS_Community() throws Exception {
		TestcaseID = "220550"; //C220550
		log("Target Environment: "+ Utils.getTargetEnvironment());
		String vaccine = "VAXZEVRIA";
		String location = "Automation Supply Location_1";
		String fromDistributionLocation = "Automation Supply Distribution_1_1";
		String toDistributionLocation = "Automation Supply Distribution_1_2";


		CommunityPortalMainPage communityPortalMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		SupplyConsolePage supplyConsolePage = communityPortalMainPage.goToSupplyLocation();
		Tables tables = loginPage.getTables();

		Map<String,String> supplyLocation_1 = new HashMap<>();
		supplyLocation_1.put("Sort\nSupply Location Name", location);
		Thread.sleep(5000);
		tables.clickOnSupplyLocationTableRow(supplyLocation_1);

		//navigate to related tab
		communityPortalMainPage.selectRelatedTab();

		Map<String, String> supplyContainerFromDistributionLocation = searchCriterias(vaccine, fromDistributionLocation);
		Map<String, String> supplyContainerToDistributionLocation = searchCriterias(vaccine, toDistributionLocation);

		Thread.sleep(3000);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromDistributionLocation);
		double remainingDoses_before_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToDistributionLocation);

		tables.getSupplyLocationActions(supplyContainerFromDistributionLocation);
		supplyConsolePage.selectTransferFromDropDown();

		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();

		supplyConsolePage.enterTransferDosages("10").transferDosesToSupplyLocation1SameClinic();

		Thread.sleep(2000);
		double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromDistributionLocation);
		double remainingQty_after_Distribution_1_1 = tables.getRemainingQty(supplyContainerFromDistributionLocation);
		double remainingDoses_after_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToDistributionLocation);
		double remainingQty_after_Distribution_1_2 = tables.getRemainingQty(supplyContainerToDistributionLocation);
		double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - 10;
		double remainingDoses_after_Calculation_Distribution_1_2 = remainingDoses_before_Distribution_1_2 + 10;

		assertEquals(remainingDoses_after_Calculation_Distribution_1_1, remainingDoses_after_Distribution_1_1);
		assertEquals(remainingDoses_after_Calculation_Distribution_1_2, remainingDoses_after_Distribution_1_2);

		double remainingQty_after_Calculation_Distribution_1_1 =
				remainingDoses_after_Calculation_Distribution_1_1 / dose_conversation_factor;

		double remainingQty_after_Calculation_Distribution_1_2 =
				remainingDoses_after_Calculation_Distribution_1_2 / dose_conversation_factor;

		assertEquals(remainingQty_after_Calculation_Distribution_1_1, remainingQty_after_Distribution_1_1);
		assertEquals(remainingQty_after_Calculation_Distribution_1_2, remainingQty_after_Distribution_1_2);
	}

	private static Map<String, String> searchCriterias(String vaccine, String fromDistributionLocation) {
		Map<String,String> supplyContainer = new HashMap<>();
		supplyContainer.put("Sort by:\nSupply Container Name", vaccine);
		supplyContainer.put("Sort by:\nSupply Distribution Description", fromDistributionLocation);
		return supplyContainer;
	}
}
