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
	String env;
	Map<String, Object> testData;
	MainPageCP communityPortalMainPage;
	SupplyConsolePage supplyConsolePage;
	Tables tables;
	String vaccine;
	String lot;
	String supply_location;
	String distribution;

	@BeforeMethod
	public void setUpClass() throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		communityPortalMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
		tables = loginPage.getTables();
		vaccine = String.valueOf(testData.get("containerTo"));
		log("/----Go to Supply Location Related Tab where Transferring From --*/");
		supply_location = String.valueOf(testData.get("supplyLocationTo"));
		supplyConsolePage = communityPortalMainPage.selectSupplyLocationName(supply_location);
		distribution = String.valueOf(testData.get("distributionTo"));
		lot = vaccine.substring(vaccine.indexOf("-") + 2, vaccine.indexOf("(", vaccine.indexOf("-")) - 1);
	}


	@Test()
	public void Validate_Receive_Supplies_By_Doses_as_Clinician_CP() throws Exception {
		TestcaseID = "243133"; //C243133
		log("TestRail test case ID: C" +TestcaseID);
		double doses = 10;

		log("/----Count Remaining Supplies --*/");
		double remainingDosesBeforeReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeReceiving);
		double remainingQtyBeforeReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . remaining qty are: -->" + remainingQtyBeforeReceiving);

		log("/*-- Receive Supplies --*/");
		supplyConsolePage.clickBtnReceiveSuppliesCP();
		Thread.sleep(2000);
		supplyConsolePage.selectSupplyItemTo(lot).
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

		supplyConsolePage.transferToDistributionOnSend(distribution);
		Thread.sleep(2000);
		supplyConsolePage.clickSaveButton();
		try {
			supplyConsolePage.successMessageAppear();
		} catch(Exception ex) {
			System.out.println("---Warning! Success message didn't apper. Continue anyway---");
		}

		log("/----Count Supplies After Receiving--*/");
		tables.hardWait(2);//needs couple seconds to refresh results

		double remainingDosesAfterReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . after doses are: -->" + remainingDosesAfterReceiving);
		double remainingQtyAfterReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . after qty are: -->" + remainingQtyAfterReceiving);

		double dosesToQty =Double.parseDouble(df.format(doses / doseConversionFactor));

		assertEquals(remainingDosesAfterReceiving, remainingDosesBeforeReceiving + doses);
		assertEquals(remainingQtyAfterReceiving, remainingQtyBeforeReceiving + dosesToQty, 0.011);
	}

	@Test()
	public void Validate_Receive_Supplies_By_Qty_as_Clinician_CP() throws Exception {
		TestcaseID = "243133"; //C243133
		double qty = 1;

		log("/----Count Remaining Supplies --*/");
		double remainingDosesBeforeReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeReceiving);
		double remainingQtyBeforeReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . remaining qty are: -->" + remainingQtyBeforeReceiving);

		log("/*-- Receive Supplies --*/");
		supplyConsolePage.clickBtnReceiveSuppliesCP();

		supplyConsolePage.selectSupplyItemTo(lot).
				selectReasonForReception().
				enterTransferQuantity(String.valueOf(qty));
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorOnReceive();
		supplyConsolePage.transferToDistributionOnSend(distribution).
				clickSaveButton();
		supplyConsolePage.successMessageAppear();

		log("/----Count Supplies After Receiving--*/");
		tables.hardWait(2);//needs couple seconds to refresh results
		double remainingDosesAfterReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . after doses are: -->" + remainingDosesAfterReceiving);
		double remainingQtyAfterReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . after qty are: -->" + remainingQtyAfterReceiving);

		double dosesToQty = qty * doseConversionFactor;

		assertEquals(remainingQtyAfterReceiving, remainingQtyBeforeReceiving + qty, 0.011);
		assertEquals(remainingDosesAfterReceiving, remainingDosesBeforeReceiving + dosesToQty);
	}
}