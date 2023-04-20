package communityPortal.tests.InventoryCP;

import bcvax.pages.MainPageCP;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Tables;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static constansts.Domain.*;
import static org.testng.Assert.assertEquals;


public class ReceiveSuppliesCP extends BaseTest {
	MainPageCP communityPortalMainPage;
	SupplyConsolePage supplyConsolePage;
	Tables tables;

	@BeforeMethod
	public void setUpClass() throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		communityPortalMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
		tables = loginPage.getTables();

		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(SUPPLY_LOCATION_2);
	}


	@Test()
	public void Validate_Receive_Supplies_By_Doses_as_Clinician_CP() throws Exception {
		TestcaseID = "243133"; //C243133
		log("TestRail test case ID: C" +TestcaseID);
		String vaccine = "VAXZEVRIA (AstraZeneca) - MT0055";
		double doses = 10;

		log("/----Count Remaining Supplies --*/");
		double remainingDosesBeforeReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeReceiving);
		double remainingQtyBeforeReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . remaining qty are: -->" + remainingQtyBeforeReceiving);

		log("/*-- Receive Supplies --*/");
		supplyConsolePage.clickBtnReceiveSuppliesCP();

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

		supplyConsolePage.transferToDistributionOnSend(SUPPLY_DISTRIBUTION_2_1);
		Thread.sleep(2000);
		supplyConsolePage.clickSaveButton();
		supplyConsolePage.successMessageAppear();

		log("/----Count Supplies After Receiving--*/");
		tables.hardWait(2);//needs couple seconds to refresh results

		double remainingDosesAfterReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . after doses are: -->" + remainingDosesAfterReceiving);
		double remainingQtyAfterReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . after qty are: -->" + remainingQtyAfterReceiving);

		double dosesToQty =Double.parseDouble(df.format(doses / doseConversionFactor));

		assertEquals(remainingDosesAfterReceiving, remainingDosesBeforeReceiving + doses);
		assertEquals(remainingQtyBeforeReceiving + dosesToQty, remainingQtyAfterReceiving);
	}

	@Test()
	public void Validate_Receive_Supplies_By_Qty_as_Clinician_Community() throws Exception {
		TestcaseID = "243133"; //C243133
		String vaccine = "VAXZEVRIA (AstraZeneca) - MT0055";
		double qty = 1;

		log("/----Count Remaining Supplies --*/");
		double remainingDosesBeforeReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeReceiving);
		double remainingQtyBeforeReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . remaining qty are: -->" + remainingQtyBeforeReceiving);

		log("/*-- Receive Supplies --*/");
		supplyConsolePage.clickBtnReceiveSuppliesCP();

		supplyConsolePage.selectSupplyItemTo(vaccine).
				selectReasonForReception().
				enterTransferQuantity(String.valueOf(qty));
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorOnReceive();
		supplyConsolePage.transferToDistributionOnSend(SUPPLY_DISTRIBUTION_2_1).
				clickSaveButton();
		supplyConsolePage.successMessageAppear();

		log("/----Count Supplies After Receiving--*/");
		tables.hardWait(2);//needs couple seconds to refresh results
		double remainingDosesAfterReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . after doses are: -->" + remainingDosesAfterReceiving);
		double remainingQtyAfterReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, SUPPLY_DISTRIBUTION_2_1);
		log("/*-- . after qty are: -->" + remainingQtyAfterReceiving);

		double dosesToQty = qty * doseConversionFactor;

		assertEquals(remainingQtyAfterReceiving, remainingQtyBeforeReceiving + qty);
		assertEquals(remainingDosesBeforeReceiving + dosesToQty, remainingDosesAfterReceiving);
	}
}