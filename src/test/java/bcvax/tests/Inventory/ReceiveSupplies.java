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
	String supply_location;
	String distribution;
	String supply_container;
	Map<String, Object> testData;
	String supply_item;

	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location = String.valueOf(testData.get("supplyLocationTo"));
		supply_container = String.valueOf(testData.get("containerTo"));
		distribution = String.valueOf(testData.get("distributionTo"));
		supply_item = String.valueOf(testData.get("supplyItem"));
	}

	@Test()
	public void Validate_Receive_Supplies_as_an_PPHIS() throws Exception {
		TestcaseID = "223642";
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("Test Case Id: " +"C"+TestcaseID);

		log("---1. Login AS default user (PPHIS)---");
		loginPage.orgLoginAsPPHIS();

		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

		int receive_supply_quantity = 1;
		log("---2. Close all open tabs---");
		SupplyConsolePage.closeTabsHCA(driver);
		log("---3. Click Supply Console App Navigation Menu---");
		SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
		log("---4. Select Supply Items Option from the Drop Down---");
		SupplyConsolePage.selectSupplyItemsFromDropdown(driver);
		log("---5. Click on 'COMIRNATY (Pfizer) - 35035BD-CC01' Supply Item---");
		SupplyConsolePage.switchToTableView(driver);
		Thread.sleep(1000);
		SupplyItemsPage.selectSupplyItemName(driver, supply_item);
		//Validation for Doses/Qty Before Receiving needs to be add from supply container
		log("/*-- . We need to see Dosages and Qty Before Receiving here to Validate at the end---*/");
		double remainingQty_before = SupplyItemDetailsPage.getRemainingQuantity(driver);
		log("/*-- . remaining Quantity are: -->" + remainingQty_before);
		double remainingDoses_before = SupplyItemDetailsPage.getRemainingDoses(driver);
		log("/*-- . remaining Doses are: -->" + remainingDoses_before);
		double doseConversionFactor = supplyConsolePage.getDoseConversionFactorReceive();
		log("---6. Dose Conversation factor are: -->" + doseConversionFactor);

		log("---7. Close all open tabs---");
		SupplyConsolePage.closeTabsHCA(driver);
		log("---8. Click Supply Console App Navigation Menu---");
		SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);

		log("---9. Navigate and Select Supply Locations---");
		SupplyConsolePage.selectSupplyLocationFromDropdown(driver);

		log("---10. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location---");
		try{
			SupplyConsolePage.switchToTableView(driver);
		} catch (Exception ex) {
			;
		}
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location);

		log("---11. Navigate and Select Dropdown to Receive Supplies Button---");
		SupplyLocationPage.clickReceiveSuppliesButton(driver);
		log("---12. Validate Supply Item Filed Present on Layout---");
		String supplyItem = ReceiveSuppliesDialog.validateSupplyItemField(driver);
		String expectedSupplyItemLabel = "*Supply Item";
		log("---13.: -->" + supplyItem + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((supplyItem), (expectedSupplyItemLabel));
		log("---14. Click to select the Supply Item---");
		ReceiveSuppliesDialog.clickSupplyItemTextBox(driver);
		log("---15. Select Supply Item: " +supply_item);
		ReceiveSuppliesDialog.selectSupplyItem(driver, supply_item);
		Thread.sleep(2000);
		log("---16. Validate Quantity Filed Present on Layout---");
		String qty = ReceiveSuppliesDialog.getQuantityLabel(driver);
		String expectedqtyLabel = "Quantity";
		log("---17.: -->" + qty + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((qty), (expectedqtyLabel));
		log("---18. Enter quantity as 1---");
		ReceiveSuppliesDialog.enterQuantity(driver, receive_supply_quantity);
		log("---19. Validate Dose Conversion Factor Filed Present on Layout --*/");
		String dcf = ReceiveSuppliesDialog.getDoseConversionFactorLabel(driver);
		String expecteddcfLabel = "Dose Conversion Factor";
		log("---20.: -->" + dcf + "Field is Present on Layout");
		Assert.assertEquals((dcf), (expecteddcfLabel));
		log("---21. Validate Doses Filed Present on Layout --*/");
		String doses = ReceiveSuppliesDialog.getDosesLabel(driver);
		String expecteddosesLabel = "*Doses";
		log("---22.: -->" + doses + "Field is Present on Layout");
		Assert.assertEquals((doses), (expecteddosesLabel));
		log("---23. Validate Supply Distribution To Filed Present on Layout---");
		String supplydistributionton = ReceiveSuppliesDialog.getSupplyDistributionToLabel(driver);
		String expectedsupplydistributiontoLabel = "*Supply Distribution To";
		log("---24.: -->" + supplydistributionton + "Field is Present on Layout");
		Assert.assertEquals((supplydistributionton), (expectedsupplydistributiontoLabel));
		log("---25. Select a Supply Distribution---");
		ReceiveSuppliesDialog.selectSupplyDistributionTo(driver);
		log("---26. Select a Reason For Reception --*/");
		ReceiveSuppliesDialog.selectReasonForReception(driver);
		log("---27. Save Button is Present on Layout --*/");
		boolean save_btn_exists = ReceiveSuppliesDialog.ValidateSaveButtonIsDisplayedOnReceiveSupplies(driver);
		Assert.assertTrue(save_btn_exists, "Save Button is displayed");
		log("---28. Cancel Button is Present on Layout---");
		boolean cancel_btn_exists = ReceiveSuppliesDialog.ValidateCancelButtonIsDisplayedOnReceiveSupplies(driver);
		Assert.assertTrue(cancel_btn_exists, "Cancel button is not displayed");
		log("---29. Click Save Button---");
		ReceiveSuppliesDialog.clickSaveButton(driver);
		boolean supply_page_displayed = SupplyConsolePage.verifyIsSupplyPageDisplayed(driver);
		if(supply_page_displayed) {
			System.out.println("Supply Console Is Displayed");
		} else {
			System.out.println("Supply Console Is NOT Displayed");
		}
		log("---30. Click Supply Console App Navigation Menu---");
		SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
		log("---31. Select Supply Items Option from the Drop Down---");
		SupplyConsolePage.selectSupplyItemsFromDropdown(driver);
		SupplyConsolePage.switchToTableView(driver);
		SupplyItemsPage.selectShowAllSupplyItems(driver);
		log("---32. Click on  Supply Item: " +supply_item);
		SupplyItemsPage.selectSupplyItemName(driver, supply_item);
		//SupplyConsolePage.clickSupplyItemName(driver, supply_item);

		///Validation for Doses/Qty Before and After Reciaving needs to be add.
		log("---Wee need to see Dosages and Qty After/Before Receiving here to Validate at the end---");
		double remainingQty_after = SupplyItemDetailsPage.getRemainingQuantity(driver);
		log("---remaining Quantity are: -->" + remainingQty_after);
		double remainingDoses_after = SupplyItemDetailsPage.getRemainingDoses(driver);
		log("---remaining Doses are: -->" + remainingDoses_after);

		Assert.assertEquals(remainingQty_after, remainingQty_before + 1);
		Assert.assertEquals(remainingDoses_after, remainingDoses_before + doseConversionFactor);
	}
	
}