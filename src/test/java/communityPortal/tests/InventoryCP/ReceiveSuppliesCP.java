package communityPortal.tests.InventoryCP;

import bcvax.pages.*;
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
		log("Login as Clinician");
		communityPortalMainPage = loginPage.loginIntoCommunityPortalAsClinician();
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
		SupplyLocationPage.clickReceiveSuppliesButton(driver);
		Thread.sleep(2000);
		supplyConsolePage.selectSupplyItemTo(lot);
		ContainerTransferForm.enterTransferDosages(driver, String.valueOf(doses));
		supplyConsolePage.selectReasonForReception();
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

		double remainingDosesAfterReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . after doses are: -->" + remainingDosesAfterReceiving);
		double remainingQtyAfterReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . after qty are: -->" + remainingQtyAfterReceiving);

		double dosesToQty =Double.parseDouble(df.format(doses / doseConversionFactor));

		assertEquals(remainingDosesAfterReceiving, remainingDosesBeforeReceiving + doses);
		assertEquals(remainingQtyAfterReceiving, remainingQtyBeforeReceiving + dosesToQty, 0.011);
	}

	//@Test()
	public void Validate_Receive_Supplies_By_Qty_as_Clinician_CP() throws Exception {
		TestcaseID = "243133"; //C243133
		double qty = 1;

		log("/----Count Remaining Supplies --*/");
		double remainingDosesBeforeReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . remaining doses are: -->" + remainingDosesBeforeReceiving);
		double remainingQtyBeforeReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . remaining qty are: -->" + remainingQtyBeforeReceiving);

		log("/*-- Receive Supplies --*/");
		SupplyLocationPage.clickReceiveSuppliesButton(driver);

		supplyConsolePage.selectSupplyItemTo(lot);
		Thread.sleep(500);
		supplyConsolePage.selectReasonForReception();
		Thread.sleep(500);
		ContainerTransferForm.enterTransferQuantity(driver, String.valueOf(qty));
		Thread.sleep(500);
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorOnReceive();
		supplyConsolePage.transferToDistributionOnSend(distribution);
		supplyConsolePage.clickSaveButton();
		supplyConsolePage.successMessageAppear();

		log("/----Count Supplies After Receiving--*/");
		double remainingDosesAfterReceiving = supplyConsolePage.getValueOfRemainingDoses(vaccine, distribution);
		log("/*-- . after doses are: -->" + remainingDosesAfterReceiving);
		double remainingQtyAfterReceiving = supplyConsolePage.getValueOfRemainingQty(vaccine, distribution);
		log("/*-- . after qty are: -->" + remainingQtyAfterReceiving);

		double dosesToQty = qty * doseConversionFactor;

		assertEquals(remainingQtyAfterReceiving, remainingQtyBeforeReceiving + qty, 0.011);
		assertEquals(remainingDosesAfterReceiving, remainingDosesBeforeReceiving + dosesToQty);
	}
}