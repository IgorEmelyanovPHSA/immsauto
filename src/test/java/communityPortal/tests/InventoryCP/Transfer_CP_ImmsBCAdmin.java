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
import static constansts.Header.SUPPLY_CONTAINER_NAME_FULL;
import static constansts.Header.SUPPLY_ITEM_NAME;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Transfer_CP_ImmsBCAdmin extends BaseTest {
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
	public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another_as_ImmsBCAdmin_Community() throws Exception {
		TestcaseID = "223184";
		String vaccine = "JANSSEN COVID-19 VACCINE";
		double doses = 10;

		log("/----Count Remaining Supplies --*/");
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME_FULL, vaccine);
		double remainingDoses_before_Distribution_1_1 = tables.getRemainingDoses(supplyContainer);

		log("/---- Perform doses transfer to  location " + supplyLocationTo + "--*/");
		tables.getSupplyLocationActions(supplyContainer);
		supplyConsolePage.selectTransferFromDropDown();
		double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
		supplyConsolePage.enterTransferDosages(String.valueOf(doses)).transferDosesToSupplyLocation(supplyLocationTo);

		log("/---- Count and Validate Remaining Supplies After Transfer --*/");
		tables.hardWait(2); //needs couple seconds to refresh results
		double remainingDoses_after_Distribution_1_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQty_after_Distribution_1_1 = tables.getRemainingQty(supplyContainer);
		double remainingDoses_after_Calculation_Distribution_1_1 = remainingDoses_before_Distribution_1_1 - doses;
		assertEquals(remainingDoses_after_Calculation_Distribution_1_1, remainingDoses_after_Distribution_1_1);
		double remainingQty_after_Calculation_Distribution_1_1 =
				remainingDoses_after_Calculation_Distribution_1_1 / dose_conversation_factor;
		assertEquals(remainingQty_after_Calculation_Distribution_1_1, remainingQty_after_Distribution_1_1);

		log("/----Go to Supply Location Related Tab where Transferring To --*/");
		communityPortalMainPage.navigateToSupplyLocation(supplyLocationTo);

		log("/----Count Remaining Supplies Before Transaction --*/");
		double remainingDoses_before_Distribution_2_1 = tables.getRemainingDoses(supplyContainer);

		log("/----Go To Transaction Accept Incoming Transfer --*/");
		supplyConsolePage.clickTransactionsTab();
		tables.hardWait(2);
		tables.openShippedTransactionsIncomingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
		supplyConsolePage.acceptIncomingTransfer(SUPPLY_DISTRIBUTION_1_1);
		communityPortalMainPage.selectRelatedTab();

		log("/----Count And Validate Remaining Supplies After Transaction --*/");
		double remainingDoses_after_Distribution_2_1 = tables.getRemainingDoses(supplyContainer);
		double remainingQty_after_Distribution_2_1 = tables.getRemainingQty(supplyContainer);
		double remaining_after_Calculation_Distribution_2_1 = remainingDoses_before_Distribution_2_1 + doses;
		assertEquals(
				df.format(remaining_after_Calculation_Distribution_2_1),
				df.format(remainingDoses_after_Distribution_2_1));
		assertEquals(
				df.format((remaining_after_Calculation_Distribution_2_1 / dose_conversation_factor)),
				df.format(remainingQty_after_Distribution_2_1));
	}

}
