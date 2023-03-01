package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Tables;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static constansts.Domain.*;
import static constansts.Header.*;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class TransferCancellation extends BaseTest {
	CommunityPortalMainPage communityPortalMainPage;
	SupplyConsolePage supplyConsolePage;
	Tables tables;
	private final String supplyLocationFrom = SUPPLY_LOCATION_2;
	private final String supplyLocationTo = SUPPLY_LOCATION_1;

	@BeforeMethod
	public void setUpClass() throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		communityPortalMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
		tables = loginPage.getTables();

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);
	}

	@Test(priority = 1)
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_And_Cancel_as_Clinician_Community() throws Exception {
		TestcaseID = "223184";
		String vaccine = "JANSSEN COVID-19 VACCINE";
		double doses = 5;

		log("/----Count Remaining Supplies --*/");
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME_FULL, vaccine);
		double remainingDosesBeforeDistribution1_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQtyBeforeDistribution1_1 = tables.getRemainingQty(supplyContainer);

		log("/---- Perform doses transfer to  location " + supplyLocationTo + "--*/");
		tables.getSupplyLocationActions(supplyContainer);
		supplyConsolePage.selectTransferFromDropDown();

		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage.enterTransferDosages(String.valueOf(doses)).transferDosesToSupplyLocation(supplyLocationTo);

		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
		tables.hardWait(2); //needs couple seconds to refresh results
		double remainingDosesAfterDistribution1_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQtyAfterDistribution1_1 = tables.getRemainingQty(supplyContainer);
		double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - doses;
		assertEquals(remainingDosesAfterCalculationDistribution1_1, remainingDosesAfterDistribution1_1);
		double remainingQtyAfterCalculationDistribution1_1 =
				remainingDosesAfterCalculationDistribution1_1 / dose_conversation_factor;
		assertEquals(remainingQtyAfterCalculationDistribution1_1, remainingQtyAfterDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

		log("/----Count Remaining Supplies Before Transaction --*/");
		double remainingDosesBeforeDistribution2_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQtyBeforeDistribution2_1 = tables.getRemainingQty(supplyContainer);


		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);
		supplyConsolePage.clickTransactionsTab();

		log("/----Cancel Transfer --*/");
		tables.hardWait(2);
		tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.cancelIncomingTransfer();
		communityPortalMainPage.selectRelatedTab();

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution1_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQtyAfterCancelDistribution1_1 = tables.getRemainingQty(supplyContainer);
		assertEquals(remainingDosesBeforeDistribution1_1, remainingDosesAfterCancelDistribution1_1);
		assertEquals(remainingQtyBeforeDistribution1_1, remainingQtyAfterCancelDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution2_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQtyAfterCancelDistribution2_1 = tables.getRemainingQty(supplyContainer);
		assertEquals(remainingDosesBeforeDistribution2_1, remainingDosesAfterCancelDistribution2_1);
		assertEquals(remainingQtyBeforeDistribution2_1, remainingQtyAfterCancelDistribution2_1);
	}

	@Test()
	public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another_And_Cancel_as_Clinician_Community() throws Exception {
		TestcaseID = "223184"; //C220550
		String vaccine = "JANSSEN COVID-19 VACCINE";
		double quantity = 1;

		log("/----Count Remaining Supplies --*/");
		Map<String,String> supplyContainerName = ImmutableMap.of(SUPPLY_CONTAINER_NAME_FULL, vaccine);
		double remainingDosesBeforeDistribution1_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQtyBeforeDistribution1_1 = tables.getRemainingQty(supplyContainerName);

		log("/---- Perform Quantity transfer to another location --*/");
		tables.getSupplyLocationActions(supplyContainerName);
		supplyConsolePage.selectTransferFromDropDown();
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage
				.enterTransferQuantity(String.valueOf(quantity))
				.transferDosesToSupplyLocation(supplyLocationTo);

		log("/---- Count Remaining Supplies After Transaction --*/");
		tables.hardWait(2); //needs couple seconds to refresh results
		double remainingDosesAfterDistribution1_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQtyAfterDistribution1_1 = tables.getRemainingQty(supplyContainerName);
		double dosesToQty = quantity * dose_conversation_factor;
		double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - dosesToQty;
		assertEquals(remainingDosesAfterCalculationDistribution1_1, remainingDosesAfterDistribution1_1);
		double remainingQtyAfterCalculationDistribution1_1 =
				remainingDosesAfterCalculationDistribution1_1 / dose_conversation_factor;
		assertEquals(remainingQtyAfterCalculationDistribution1_1, remainingQtyAfterDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

		double remainingDosesBeforeDistribution2_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQtyBeforeDistribution2_1 = tables.getRemainingQty(supplyContainerName);

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationFrom);
		supplyConsolePage.clickTransactionsTab();

		log("/----Cancel Transfer --*/");
		tables.hardWait(2);
		tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.cancelIncomingTransfer();
		communityPortalMainPage.selectRelatedTab();

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution1_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQtyAfterCancelDistribution1_1 = tables.getRemainingQty(supplyContainerName);
		assertEquals(remainingDosesBeforeDistribution1_1, remainingDosesAfterCancelDistribution1_1);
		assertEquals(remainingQtyBeforeDistribution1_1, remainingQtyAfterCancelDistribution1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

		log("/----Count Remaining Supplies After Cancel Transaction --*/");
		double remainingDosesAfterCancelDistribution2_1 = tables.getRemainingDoses(supplyContainerName);
		double remainingQtyAfterCancelDistribution2_1 = tables.getRemainingQty(supplyContainerName);
		assertEquals(remainingDosesBeforeDistribution2_1, remainingDosesAfterCancelDistribution2_1);
		assertEquals(remainingQtyBeforeDistribution2_1, remainingQtyAfterCancelDistribution2_1);
	}

}
