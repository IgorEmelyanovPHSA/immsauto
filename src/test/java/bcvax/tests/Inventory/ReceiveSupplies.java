package bcvax.tests.Inventory;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;


public class ReceiveSupplies extends BaseTest {
	String env;
	String supply_location = "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic";
	String distribution = "Supply Distribution_1";
	String supply_container = "COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)";
	Map<String, Object> testData;
	MainPageOrg orgMainPage;
	String supply_item = "COMIRNATY (Pfizer) - EK4241";

	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
	}

	@Test()
	public void Validate_Receive_Supplies_as_an_PPHIS() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				log("Login as ImmsBCAdmin");
				TestcaseID = "244853"; //C244853
				loginPage.loginAsImmsBCAdmin();
				break;
			default:
				log("Login AS default user (PPHIS)");
				TestcaseID = "223642"; //C223642 pphis
				//TestcaseID = "244853"; //C244853 immsbc
				orgMainPage = loginPage.orgLoginAsPPHIS();
				//loginPage.orgLoginAsImmsBCAdminCP();
		}

		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

		log("/*-- 3. Close all open tabs --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*-- 4. Click Supply Console App Navigation Menu --*/");
		SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
		log("/*-- 5. Select Supply Items Option from the Drop Down --*/");
		SupplyConsolePage.selectSupplyItemsFromDropdown(driver);
		log("/*-- 6. Click on 'COMIRNATY (Pfizer) - 35035BD-CC01' Supply Item--*/");
		SupplyConsolePage.switchToTableView(driver);
		Thread.sleep(1000);
		SupplyItemsPage.selectShowAllSupplyItems(driver);
		SupplyItemsPage.selectSupplyItemName(driver, supply_item);
		//Validation for Doses/Qty Before Receiving needs to be add from supply container
		log("/*-- . We need to see Dosages and Qty Before Receiving here to Validate at the end---*/");
		double remainingQty_before = SupplyItemDetailsPage.getRemainingQuantity(driver);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before);
		double remainingDoses_before = SupplyItemDetailsPage.getRemainingDoses(driver);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before);
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorReceive();
		log("/*-- 7. Dose Conversation factor are: -->" + doseConversionFactor);

		log("/*-- 8. Close all open tabs --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*-- 9. Click Supply Console App Navigation Menu --*/");
		SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);

		log("/*-- 10. Navigate and Select Supply Locations --*/");
		SupplyConsolePage.selectSupplyLocationFromDropdown(driver);

		log("/*-- 11. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location);

		log("/*-- 18. Navigate and Select Dropdown to Receive Supplies Button --*/");
		SupplyLocationPage.clickReceiveSuppliesButton(driver);
		log("/*-- 20. Validate Supply Item Filed Present on Layout --*/");
		String supplyItem = supplyConsolePage.validateSupplyItemField();
		String expectedSupplyItemLabel = "*Supply Item";
		log("/*-- 20.1.: -->" + supplyItem + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((supplyItem), (expectedSupplyItemLabel));
		log("/*-- 21. click to select the Supply Item --*/");
		supplyConsolePage.clickSupplyItemTextBox();
		log("/*-- 22. Select Supply Item COMIRNATY (Pfizer) - EK4241  --*/");
		SupplyConsolePage.selectSupplyItem(driver, supply_item);
		Thread.sleep(2000);
		log("/*-- 23. Validate Quantity Filed Present on Layout --*/");
		String qty = supplyConsolePage.validateQTYField();
		String expectedqtyLabel = "Quantity";
		log("/*-- 23.1.: -->" + qty + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((qty), (expectedqtyLabel));
		log("/*-- 24. Enter quantity as 1 --*/");
		supplyConsolePage.enterQuantity();
		log("/*-- 25. Validate Dose Conversion Factor Filed Present on Layout --*/");
		String dcf = supplyConsolePage.validateDCFField();
		String expecteddcfLabel = "Dose Conversion Factor";
		log("/*-- 25.1.: -->" + dcf + "Field is Present on Layout");
		Assert.assertEquals((dcf), (expecteddcfLabel));
		log("/*-- 26. Validate Doses Filed Present on Layout --*/");
		String doses = supplyConsolePage.validateDosesField();
		String expecteddosesLabel = "*Doses";
		log("/*-- 26.1.: -->" + doses + "Field is Present on Layout");
		Assert.assertEquals((doses), (expecteddosesLabel));
		log("/*-- 27. Validate Supply Distribution To Filed Present on Layout --*/");
		String supplydistributionton = supplyConsolePage.validateSupplyDistributionToField();
		String expectedsupplydistributiontoLabel = "*Supply Distribution To";
		log("/*-- 27.1.: -->" + supplydistributionton + "Field is Present on Layout");
		Assert.assertEquals((supplydistributionton), (expectedsupplydistributiontoLabel));
		log("/*-- 28. Select a Supply Distribution --*/");
		supplyConsolePage.selectIncomingSupplyDistributionReceive();
		log("/*-- 29. Select a Reason For Reception --*/");
		supplyConsolePage.selectReasonForReception();
		log("/*-- 30. Save Button is Present on Layout --*/");
		supplyConsolePage.ValidateSaveButtonIsDisplayedOnReceiveSupplies();
		log("/*-- 31. Cancel Button is Present on Layout --*/");
		supplyConsolePage.ValidateCancelButtonIsDisplayedOnReceiveSupplies();
		log("/*-- 32. Click Save Button --*/");
		supplyConsolePage.ClickSaveButton();
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		log("/*-- 35. Click Supply Console App Navigation Menu --*/");
		SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
		log("/*-- 36. Select Supply Items Option from the Drop Down --*/");
		SupplyConsolePage.selectSupplyItemsFromDropdown(driver);
		log("/*-- 37. Click on 'COMIRNATY (Pfizer) - 35035BD-CC01' Supply Item--*/");
		SupplyConsolePage.clickSupplyItemName(driver, supply_item);

		///Validation for Doses/Qty Before and After Reciaving needs to be add.
		log("/*-- . Wee need to see Dosages and Qty After/Before Receiving here to Validate at the end---*/");
		double remainingQty_after = SupplyItemDetailsPage.getRemainingQuantity(driver);
		log("/*-- . remaining Quantity are: -->" + remainingQty_after);
		double remainingDoses_after = SupplyItemDetailsPage.getRemainingDoses(driver);
		log("/*-- . remaining Doses are: -->" + remainingDoses_after);

		Assert.assertEquals(remainingQty_after, remainingQty_before + 1);
		Assert.assertEquals(remainingDoses_after, remainingDoses_before + doseConversionFactor);
	}
	
}