package bcvax.Inventory;

import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvax.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ReceiveSupplies extends BaseTest {
	@Test()
	public void Validate_Receive_Supplies_as_an_PPHIS_BCVAXDEVIT() throws Exception {
		TestcaseID = "223642"; //C223642
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*-- 1.Login as an PPHIS user to Supply Location on BCVAXDEVIT --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(10000);
		if (supplyConsolePage.displaySupplyConsolePage()) {
			log("/*-- 2. User already on Health Connect - Supply Console --*/");
		} else {
			log("/*-- 2.1. Navigate to Health Connect - Supply Console --*/");
			supplyConsolePage.selectHealthConnectApp();
			Thread.sleep(2000);
		}
		log("/*-- 3. Close all open tabs --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(5000);
		log("/*-- 4. Click Dropdown Menu --*/");
		supplyConsolePage.clickDropdownMenu();
		Thread.sleep(5000);
		log("/*-- 5. Select Supply Items Option from the Drop Down --*/");
		supplyConsolePage.selectSupplyItemsFromDropdown();
		Thread.sleep(2000);
		log("/*-- 6. Click on 'COMIRNATY (Pfizer) - 35035BD-CC01' Supply Item--*/");
		supplyConsolePage.selectSupplyItemName();
		Thread.sleep(2000);
		///Validation for Doses/Qty Before Receiving needs to be add.
		log("/*-- . We need to see Dosages and Qty Before Receiving here to Validate at the end---*/");
		String doseConversionFactor = supplyConsolePage.getDoseConversionFactorReceive();
		log("/*-- 7. Dose Conversation factor are: -->" + doseConversionFactor);
		Thread.sleep(2000);
		log("/*-- 8. Close all open tabs --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		log("/*-- 9. Click Dropdown Menu --*/");
		supplyConsolePage.clickDropdownMenu();
		Thread.sleep(5000);
		log("/*-- 10. Navigate and Select Supply Locations --*/");
		supplyConsolePage.selectSupplyLocationFromDropdown();
		Thread.sleep(2000);
		log("/*-- 11. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		supplyConsolePage.selectSupplyLocationName();
		Thread.sleep(5000);
		log("/*-- 12. Click on Supply Distribution with - Supply Distribution_1 --*/");
		supplyConsolePage.clickSupplyDistribution();
		Thread.sleep(2000);
		String supplyName = supplyConsolePage.getSupplyDistributionName();
		log("/*-- 13. Supply Distribution Name is: -->" + supplyName);
		Thread.sleep(2000);
		String supplyDesc = supplyConsolePage.getSupplyDistributionDescription();
		log("/*-- 14. Supply Distribution Description is: -->" + supplyDesc);
		Thread.sleep(2000);
		log("/*-- 15. Close all open tabs --*/");
		supplyConsolePage.closeTabsHCA();
		Thread.sleep(2000);
		if (supplyConsolePage.displaySupplyConsolePage()) {
			log("/*-- 16. User is already on Supply loc--*/");
		} else {
			log("/*-- 16.1. Click Dropdown Menu --*/");
			supplyConsolePage.clickDropdownMenu();
			Thread.sleep(5000);
			log("/*-- 16.2. Navigate and Select Supply Locations --*/");
			supplyConsolePage.selectSupplyLocationFromDropdown();
			Thread.sleep(2000);
		}
		log("/*-- 17. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		supplyConsolePage.selectSupplyLocationName();
		Thread.sleep(2000);
		log("/*-- 18. Navigate and Select Dropdown to Receive Supplies Button --*/");
		supplyConsolePage.SelectDropDownToClickReceiveSuppliesButton();
		Thread.sleep(2000);
		log("/*-- 19. Click to Receive Supplies Button --*/");
		supplyConsolePage.ClickDropDownToClickReceiveSuppliesButton();
		Thread.sleep(5000);
		log("/*-- 20. Validate Supply Item Filed Present on Layout --*/");
		String supplyItem = supplyConsolePage.validateSupplyItemField();
		String expectedSupplyItemLabel = "*Supply Item";
		log("/*-- 20.1.: -->" + supplyItem + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((supplyItem), (expectedSupplyItemLabel));
		Thread.sleep(2000);
		log("/*-- 21. click to select the Supply Item --*/");
		supplyConsolePage.clickSupplyItemTextBox();
		Thread.sleep(2000);
		log("/*-- 22. Select Supply Item COMIRNATY (Pfizer) - 35035BD-CC01  --*/");
		supplyConsolePage.selectSupplyItem("COMIRNATY (Pfizer) - 35035BD-CC01");
		Thread.sleep(2000);
		log("/*-- 23. Validate Quantity Filed Present on Layout --*/");
		String qty = supplyConsolePage.validateQTYField();
		String expectedqtyLabel = "Quantity";
		log("/*-- 23.1.: -->" + qty + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((qty), (expectedqtyLabel));
		Thread.sleep(2000);
		log("/*-- 24. Enter quantity as 1 --*/");
		supplyConsolePage.enterQuantity();
		Thread.sleep(2000);
		log("/*-- 25. Validate Dose Conversion Factor Filed Present on Layout --*/");
		String dcf = supplyConsolePage.validateDCFField();
		String expecteddcfLabel = "Dose Conversion Factor";
		log("/*-- 25.1.: -->" + dcf + "Field is Present on Layout");
		Assert.assertEquals((dcf), (expecteddcfLabel));
		Thread.sleep(2000);
		log("/*-- 26. Validate Doses Filed Present on Layout --*/");
		String doses = supplyConsolePage.validateDosesField();
		String expecteddosesLabel = "*Doses";
		log("/*-- 26.1.: -->" + doses + "Field is Present on Layout");
		Assert.assertEquals((doses), (expecteddosesLabel));
		Thread.sleep(2000);
		log("/*-- 27. Validate Supply Distribution To Filed Present on Layout --*/");
		String supplydistributionton = supplyConsolePage.validateSupplyDistributionToField();
		String expectedsupplydistributiontoLabel = "*Supply Distribution To";
		log("/*-- 27.1.: -->" + supplydistributionton + "Field is Present on Layout");
		Assert.assertEquals((supplydistributionton), (expectedsupplydistributiontoLabel));
		Thread.sleep(2000);
		log("/*-- 28. Select a Supply Distribution --*/");
		supplyConsolePage.selectIncomingSupplyDistributionReceive();
		Thread.sleep(2000);
		log("/*-- 29. Select a Reason For Reception --*/");
		supplyConsolePage.selectReasonForReception();
		Thread.sleep(2000);
		log("/*-- 30. Save Button is Present on Layout --*/");
		supplyConsolePage.ValidateSaveButtonIsDisplayedOnReceiveSupplies();
		Thread.sleep(2000);
		log("/*-- 31. Cancel Button is Present on Layout --*/");
		supplyConsolePage.ValidateCancelButtonIsDisplayedOnReceiveSupplies();
		Thread.sleep(2000);
		log("/*-- 32. Click Save Button --*/");
		supplyConsolePage.ClickSaveButton();
		Thread.sleep(2000);
		///Validation for Doses/Qty Before and After Reciaving needs to be add.
		log("/*-- . Wee need to see Dosages and Qty After/Before Receiving here to Validate at the end---*/");
	}
	
}