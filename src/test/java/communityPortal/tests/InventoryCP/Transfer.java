package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Tables;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static Constansts.Domain.*;
import static Constansts.Header.*;
import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class Transfer extends BaseTest {
	public CommunityPortalMainPage communityPortalMainPage;
	public SupplyConsolePage supplyConsolePage;
	public Tables tables;
	@BeforeMethod
	public void setUpClass() throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		communityPortalMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		supplyConsolePage = communityPortalMainPage.goToSupplyLocation();
		tables = loginPage.getTables();

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		communityPortalMainPage.navigateToSupplyLocationRelatedTab(SUPPLY_LOCATION_1);
	}

	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_as_PPHIS_Community() throws Exception {
		TestcaseID = "223184";
		String vaccine = "JANSSEN COVID-19 VACCINE";
		String doses = "10";

		log("/----Count Remaining Supplies --*/");
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME_, vaccine);
		Thread.sleep(3000);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainer);

		log("/---- Perform doses transfer to another location --*/");
		tables.getSupplyLocationActions(supplyContainer);
		supplyConsolePage.selectTransferFromDropDown();
		Thread.sleep(3000);
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage.enterTransferDosages(doses).transferDosesToSupplyLocation2();

		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
		Thread.sleep(3000);
		double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQty_after_Distribution_1_1 = tables.getRemainingQty(supplyContainer);
		double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - 10.0;
		assertEquals(remainingDoses_after_Calculation_Distribution_1_1, remainingDoses_after_Distribution_1_1);
		double remainingQty_after_Calculation_Distribution_1_1 =
				remainingDoses_after_Calculation_Distribution_1_1 / dose_conversation_factor;
		assertEquals(remainingQty_after_Calculation_Distribution_1_1, remainingQty_after_Distribution_1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		communityPortalMainPage.navigateToSupplyLocationRelatedTab(SUPPLY_LOCATION_2);

		log("/----Count Remaining Supplies Before Transaction --*/");
		Thread.sleep(3000);
		double remainingDoses_before_Distribution_2_1 = tables.getRemainingDoses(supplyContainer);

		log("/----Go To Transaction Accept Incoming Transfer --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(3000);
		tables.openShippedTransactionsIncomingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.acceptIncomingTransfer();
		communityPortalMainPage.selectRelatedTab();

		log("/----Count And Validate Remaining Supplies After Transaction --*/");
		Thread.sleep(3000);
		double remainingDoses_after_Distribution_2_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQty_after_Distribution_2_1 = tables.getRemainingQty(supplyContainer);

		assertEquals(round(remainingDoses_before_Distribution_2_1 + 10),2, remainingDoses_after_Distribution_2_1);
		assertEquals(round((remainingDoses_before_Distribution_2_1 + 10) / dose_conversation_factor),2, remainingQty_after_Distribution_2_1);
	}

	@Test()
	public void Can_do_Transfer_by_Dosages_within_the_same_Clinic_as_PPHIS_Community() throws Exception {
		TestcaseID = "223185"; //C223185
		String vaccine = "VAXZEVRIA";

		log("/----Count Remaining Supplies in Both Distribution locations Before Transaction --*/");
		Map<String, String> supplyContainerFromDistributionLocation = searchCriterias(vaccine, SUPPLY_DISTRIBUTION_1_1);
		Map<String, String> supplyContainerToDistributionLocation = searchCriterias(vaccine, SUPPLY_DISTRIBUTION_1_2);
		Thread.sleep(3000);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromDistributionLocation);
		double remainingDoses_before_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToDistributionLocation);

		log("/---- Perform doses transfer to another distribution --*/");
		tables.getSupplyLocationActions(supplyContainerFromDistributionLocation);
		supplyConsolePage.selectTransferFromDropDown();
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage.enterTransferDosages("10").transferDosesToSupplyLocation1SameClinic();

		log("/----Count And Validate Remaining Supplies After Transaction --*/");
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

	@Test()
	public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another_as_PPHIS_Community() throws Exception {
		TestcaseID = "220550"; //C220550
		String vaccine = "JANSSEN COVID-19 VACCINE";

		log("/----Count Remaining Supplies --*/");
		Map<String,String> supplyContainerName = ImmutableMap.of(SUPPLY_CONTAINER_NAME_, vaccine);
		Thread.sleep(3000);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerName);

		log("/---- Perform Quantity transfer to another location --*/");
		tables.getSupplyLocationActions(supplyContainerName);
		supplyConsolePage.selectTransferFromDropDown();
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage.enterTransferQuantity("10").transferDosesToSupplyLocation2();

		log("/---- Count Remaining Supplies After Transaction --*/");
		Thread.sleep(2000);
		double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQty_after_Distribution_1_1 = tables.getRemainingQty(supplyContainerName);
		double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - 50;
		assertEquals(remainingDoses_after_Calculation_Distribution_1_1, remainingDoses_after_Distribution_1_1);
		double remainingQty_after_Calculation_Distribution_1_1 =
				(remainingDoses_before_Distribution_1_1 - 50) / dose_conversation_factor;
		assertEquals(remainingQty_after_Calculation_Distribution_1_1, remainingQty_after_Distribution_1_1);

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		communityPortalMainPage.navigateToSupplyLocationRelatedTab(SUPPLY_LOCATION_2);

		Thread.sleep(3000);
		double remainingDoses_before_Distribution_2_1 = tables.getRemainingDoses(supplyContainerName);

		log("/----Go To Transaction Accept Incoming Transfer --*/");
		supplyConsolePage.clickTransactionsTab();
		Thread.sleep(3000);
		tables.openShippedTransactionsIncomingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.acceptIncomingTransfer();
		communityPortalMainPage.selectRelatedTab();

		log("/----Count And Validate Remaining Supplies After Transaction --*/");
		double remainingDoses_after_Distribution_2_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQty_after_Distribution_2_1 = tables.getRemainingQty(supplyContainerName);
		assertEquals(round(remainingDoses_before_Distribution_2_1 + 10),2, remainingDoses_after_Distribution_2_1);
		assertEquals(round((remainingDoses_before_Distribution_2_1 + 10) / dose_conversation_factor),2, remainingQty_after_Distribution_2_1);
	}
	@Test()
	public void Can_do_Transfer_by_Quantity_within_the_same_Clinic_as_PPHIS_Community() throws Exception {
		TestcaseID = "220550"; //C220550
		String vaccine = "VAXZEVRIA";

		log("/----Count Remaining Supplies in Both Distribution locations Before Transcation --*/");
		Map<String, String> supplyContainerFromDistributionLocation = searchCriterias(vaccine, SUPPLY_DISTRIBUTION_1_1);
		Map<String, String> supplyContainerToDistributionLocation = searchCriterias(vaccine, SUPPLY_DISTRIBUTION_1_2);
		Thread.sleep(3000);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromDistributionLocation);
		double remainingDoses_before_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToDistributionLocation);

		log("/---- Perform Transfer From Same Location To Another Distribution within same location --*/");
		tables.getSupplyLocationActions(supplyContainerFromDistributionLocation);
		supplyConsolePage.selectTransferFromDropDown();
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage.enterTransferQuantity("10").transferDosesToSupplyLocation1SameClinic();

		log("/----Count And Validate Remaining Supplies After Transaction --*/");
		Thread.sleep(3000);
		double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainerFromDistributionLocation);
		double remainingQty_after_Distribution_1_1 = tables.getRemainingQty(supplyContainerFromDistributionLocation);
		double remainingDoses_after_Distribution_1_2 = tables.getRemainingDoses(supplyContainerToDistributionLocation);
		double remainingQty_after_Distribution_1_2 = tables.getRemainingQty(supplyContainerToDistributionLocation);

		double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - 100;
		assertEquals(remainingDoses_after_Calculation_Distribution_1_1, remainingDoses_after_Distribution_1_1);

		double remainingDoses_after_Calculation_Distribution_1_2 = remainingDoses_before_Distribution_1_2 + 100;
		assertEquals(remainingDoses_after_Calculation_Distribution_1_2, remainingDoses_after_Distribution_1_2);

		double remainingQty_after_Calculation_Distribution_1_1 =
				remainingDoses_after_Calculation_Distribution_1_1 / dose_conversation_factor;

		double remainingQty_after_Calculation_Distribution_1_2 =
				remainingDoses_after_Calculation_Distribution_1_2 / dose_conversation_factor;

		assertEquals(remainingQty_after_Calculation_Distribution_1_1, remainingQty_after_Distribution_1_1);
		assertEquals(remainingQty_after_Calculation_Distribution_1_2, remainingQty_after_Distribution_1_2);
	}

	private static Map<String, String> searchCriterias(String vaccine, String fromDistributionLocation) {
		return ImmutableMap.of(SUPPLY_CONTAINER_NAME_, vaccine, SUPPLY_DISTRIBUTION_DESCRIPTION_, fromDistributionLocation);
	}
}
