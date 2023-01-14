package communityPortal.tests.InventoryCP;

import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Tables;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static Constansts.Domain.*;
import static org.testng.Assert.assertEquals;


public class ReceiveSupplies extends BaseTest {
	CommunityPortalMainPage communityPortalMainPage;
	SupplyConsolePage supplyConsolePage;
	Tables tables;
	private final String supplyLocationFrom = SUPPLY_LOCATION_2;
	private final String supplyLocationTo = SUPPLY_LOCATION_1;
	private final String supplyDistribution = SUPPLY_LOCATION_1;

	@BeforeMethod
	public void setUpClass() throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		communityPortalMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		tables = loginPage.getTables();

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		supplyConsolePage = communityPortalMainPage.navigateToSupplyLocationRelatedTab(supplyLocationFrom);
	}


	@Test()
	public void Validate_Receive_Supplies_By_Doses_as_an_PPHIS_Community() throws Exception {
		TestcaseID = "223642"; //C223642
		//String vaccine = "COMIRNATY (Pfizer) - 35035BD-CC01";
		String vaccine = "VAXZEVRIA (AstraZeneca) - MT0055";
		double doses = 10;

		log("/----Count Remaining Supplies --*/");
		tables.hardWait(2);
		Map<String, String> supplyContainerLocation = searchCriteria(vaccine, SUPPLY_DISTRIBUTION_2_1);
		double remainingDosesBeforeReceiving = tables.getRemainingDoses(supplyContainerLocation);
		double remainingQtyBeforeReceiving = tables.getRemainingQty(supplyContainerLocation);

		log("/*-- Receive Items by Doses From Receive Menu --*/");
		supplyConsolePage.selectReceiveFromDropdownMenu();

		supplyConsolePage.selectSupplyItemTo(vaccine).
				enterTransferDosages(String.valueOf(doses)).
				selectReasonForReception();
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorOnReceive();

		log("/*-- Validate Layout Text --*/");
		String supplyItem = supplyConsolePage.validateSupplyItemField();
		String expectedSupplyItemLabel = "*Supply Item";
		Assert.assertEquals((supplyItem), (expectedSupplyItemLabel));

		String qty = supplyConsolePage.validateQTYField();
		String expectedQtyLabel = "Quantity";
		Assert.assertEquals(qty, expectedQtyLabel);

		String dcf = supplyConsolePage.validateDCFField();
		String expectedDcfLabel = "Dose Conversion Factor";
		Assert.assertEquals(dcf, expectedDcfLabel);

		String dosesField = supplyConsolePage.validateDosesField();
		String expectedDosesLabel = "*Doses";
		Assert.assertEquals(dosesField, expectedDosesLabel);

		String supplyDistribution = supplyConsolePage.validateSupplyDistributionToField();
		String expectedSupplyDistributionToLabel = "*Supply Distribution To";
		Assert.assertEquals(supplyDistribution, (expectedSupplyDistributionToLabel));

		supplyConsolePage.transferToDistributionOnSend(SUPPLY_DISTRIBUTION_2_1).
				clickSaveButton();
		supplyConsolePage.successMessageAppear();

		log("/----Count Supplies After Receiving--*/");
		tables.hardWait(2);
		double remainingDosesAfterReceiving = tables.getRemainingDoses(supplyContainerLocation);
		double remainingQtyAfterReceiving = tables.getRemainingQty(supplyContainerLocation);
		double dosesToQty =Double.parseDouble(df.format(doses / doseConversionFactor));

		assertEquals(remainingDosesAfterReceiving, remainingDosesBeforeReceiving + doses);
		assertEquals(remainingQtyBeforeReceiving + dosesToQty, remainingQtyAfterReceiving);
	}

	@Test()
	public void Validate_Receive_Supplies_By_Qty_as_an_PPHIS_Community() throws Exception {
		TestcaseID = "223642"; //C223642
		String vaccine = "COMIRNATY (Pfizer) - 35035BD-CC01";
		double qty = 1;

		log("/----Count Remaining Supplies --*/");
		tables.hardWait(2);
		Map<String, String> supplyContainerLocation = searchCriteria(vaccine, SUPPLY_DISTRIBUTION_2_1);
		double dosesBeforeReceiving = tables.getRemainingDoses(supplyContainerLocation);
		double qtyBeforeReceiving = tables.getRemainingQty(supplyContainerLocation);

		log("/*-- Receive Items by Quantity From Receive Menu --*/");
		supplyConsolePage.selectReceiveFromDropdownMenu();

		supplyConsolePage.selectSupplyItemTo(vaccine).
				enterTransferQuantity(String.valueOf(qty)).
				selectReasonForReception();
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorOnReceive();
		supplyConsolePage.transferToDistributionOnSend(SUPPLY_DISTRIBUTION_2_1).
				clickSaveButton();
		supplyConsolePage.successMessageAppear();

		log("/----Count Supplies After Receiving--*/");
		tables.hardWait(2);
		double dosesAfterReceiving = tables.getRemainingDoses(supplyContainerLocation);
		double qtyAfterReceiving = tables.getRemainingQty(supplyContainerLocation);
		double dosesToQty = qty * doseConversionFactor;

		assertEquals(qtyAfterReceiving, qtyBeforeReceiving + qty);
		assertEquals(dosesBeforeReceiving + dosesToQty, dosesAfterReceiving);


	}
}