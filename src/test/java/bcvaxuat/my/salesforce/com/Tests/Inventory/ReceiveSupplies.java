package bcvaxuat.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class ReceiveSupplies extends BaseTest {
	@Test()
	public void Validate_Receive_Supplies_as_an_PPHIS_BCVAXUAT() throws InterruptedException {
		TestcaseID = "111334"; //C111334
		log("/*-- 1.Login as an PPHIS user to Supply Location on BCVAXUAT --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
		Thread.sleep(5000);
		if (inClinicExperiencePage.displaySupplyConsolePage()) {
			log("/*-- 2. User already on Health Connect - Supply Console --*/");
		} else {
			log("/*-- 2.1. Navigate to Health Connect - Supply Console --*/");
			inClinicExperiencePage.selectHealthConnectApp();
			Thread.sleep(2000);
		}
		log("/*-- 3. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		log("/*-- 4. Click Dropdown Menu --*/");
		inClinicExperiencePage.clickDropdownMenu();
		Thread.sleep(5000);
		log("/*-- 5. Select Supply Items Option from the Drop Down --*/");
		inClinicExperiencePage.selectSupplyItemsFromDropdown();
		Thread.sleep(2000);
		log("/*-- 6. Click on 'AstraZeneca ChAdOx1-S - ABX3120' Supply Item--*/");
		inClinicExperiencePage.selectSupplyItemName();
		Thread.sleep(2000);
		String doseConversionFactor = inClinicExperiencePage.getDoseConversionFactor();
		log("/*-- 7. remaining doses are: -->" + doseConversionFactor);
		Thread.sleep(2000);
		log("/*-- 8. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		log("/*-- 9. Click Dropdown Menu --*/");
		inClinicExperiencePage.clickDropdownMenu();
		Thread.sleep(5000);
		log("/*-- 10. Navigate and Select Supply Locations --*/");
		inClinicExperiencePage.selectSupplyLocationFromDropdown();
		Thread.sleep(2000);
		log("/*-- 11. Locate and click Age 12 and Above - Abbotsford - Abby Pharmacy location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		log("/*-- 12. Click and select supply distribution Pharmanet Distribution - SDST-0000000348 --*/");
		inClinicExperiencePage.clickSupplyDistribution();
		Thread.sleep(2000);
		String supplyName = inClinicExperiencePage.getSupplyDistributionName();
		log("/*-- 13. Supply Destribution Name is: -->" + supplyName);
		Thread.sleep(2000);
		String supplyDesc = inClinicExperiencePage.getSupplyDistributionDescription();
		log("/*-- 14. Supply Destribution Description is: -->" + supplyDesc);
		Thread.sleep(2000);
		log("/*-- 15. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		if (inClinicExperiencePage.supplyLocDisplayed()) {
			log("/*-- 16. User is already on Supply loc--*/");
		} else {
			log("/*-- 16.1. Click Dropdown Menu --*/");
			inClinicExperiencePage.clickDropdownMenu();
			Thread.sleep(5000);
			log("/*-- 16.2. Navigate and Select Supply Locations --*/");
			inClinicExperiencePage.selectSupplyLocationFromDropdown();
			Thread.sleep(2000);
		}
		log("/*-- 17. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		log("/*-- 18. Navigate and Select Dropdown to Receive Supplies Button --*/");
		inClinicExperiencePage.SelectDropDownToClickReceiveSuppliesButton();
		Thread.sleep(2000);
		log("/*-- 19. Click to Receive Supplies Button --*/");
		inClinicExperiencePage.ClickDropDownToClickReceiveSuppliesButton();
		Thread.sleep(2000);
		log("/*-- 20. Validate Supply Item Filed Present on Layout --*/");
		String supplyItem = inClinicExperiencePage.validateSupplyItemField();
		String expectedSupplyItemLabel = "*Supply Item";
		log("/*-- 20.1.: -->" + supplyItem + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((supplyItem), (expectedSupplyItemLabel));
		Thread.sleep(2000);
		log("/*-- 21. click to select the Supply Item --*/");
		inClinicExperiencePage.clickSupplyItemTextBox();
		Thread.sleep(2000);
		log("/*-- 22. Select AstraZeneca ChAdOx1-S - ABX3120 Supply Item --*/");
		inClinicExperiencePage.selectSupplyItem("AstraZeneca ChAdOx1-S - ABX3120");
		Thread.sleep(2000);
		log("/*-- 23. Validate Quantity Filed Present on Layout --*/");
		String qty = inClinicExperiencePage.validateQTYField();
		String expectedqtyLabel = "Quantity";
		log("/*-- 23.1.: -->" + qty + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((qty), (expectedqtyLabel));
		Thread.sleep(2000);
		log("/*-- 24. Enter quantity as 1 --*/");
		inClinicExperiencePage.enterQuantity();
		Thread.sleep(2000);
		log("/*-- 25. Validate Dose Conversion Factor Filed Present on Layout --*/");
		String dcf = inClinicExperiencePage.validateDCFField();
		String expecteddcfLabel = "Dose Conversion Factor";
		log("/*-- 25.1.: -->" + dcf + "Field is Present on Layout");
		Assert.assertEquals((dcf), (expecteddcfLabel));
		Thread.sleep(2000);
		log("/*-- 26. Validate Doses Filed Present on Layout --*/");
		String doses = inClinicExperiencePage.validateDosesField();
		String expecteddosesLabel = "*Doses";
		log("/*-- 26.1.: -->" + doses + "Field is Present on Layout");
		Assert.assertEquals((doses), (expecteddosesLabel));
		Thread.sleep(2000);
		log("/*-- 27. Validate Supply Distribution To Filed Present on Layout --*/");
		String supplydistributionto = inClinicExperiencePage.validateSupplyDistributionToField();
		String expectedsupplydistributiontoLabel = "*Supply Distribution To";
		log("/*-- 27.1.: -->" + supplydistributionto + "Field is Present on Layout");
		Assert.assertEquals((supplydistributionto), (expectedsupplydistributiontoLabel));
		Thread.sleep(2000);
		log("/*-- 28. Select a Supply Distribution --*/");
		inClinicExperiencePage.selectIncomingSupplyDistribution();
		Thread.sleep(2000);
		log("/*-- 29. Select a Reason For Reception --*/");
		inClinicExperiencePage.selectReasonForReception();
		Thread.sleep(2000);
		log("/*-- 30. Save Button is Present on Layout --*/");
		inClinicExperiencePage.ValidateSaveButtonIsDisplayedOnReceiveSupplies();
		Thread.sleep(2000);
		log("/*-- 31. Cancel Button is Present on Layout --*/");
		inClinicExperiencePage.ValidateCancelButtonIsDisplayedOnReceiveSupplies();
		Thread.sleep(2000);
		log("/*-- 32. Click Save Button --*/");
		inClinicExperiencePage.ClickSaveButton();
		Thread.sleep(2000);
	}
	
}