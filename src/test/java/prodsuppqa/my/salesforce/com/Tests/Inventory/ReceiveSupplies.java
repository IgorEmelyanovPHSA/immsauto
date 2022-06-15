package prodsuppqa.my.salesforce.com.Tests.Inventory;

import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.InClinicExperiencePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;

public class ReceiveSupplies extends BaseTest {
	
	@Test
	public void Validate_Receive_Supplies_as_an_PPHIS_PRODSUPPQA() throws InterruptedException {
		TestcaseID = "111334"; //C111334
		System.out.println("/*-- 1.System.out.printlnin as an PPHIS user to Supply Location on PRODSUPPQA --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
		Thread.sleep(5000);
		if (inClinicExperiencePage.displaySupplyConsolePage()) {
			System.out.println("/*-- 2. User already on Health Connect - Supply Console --*/");
		} else {
			System.out.println("/*-- 2.1. Navigate to Health Connect - Supply Console --*/");
			inClinicExperiencePage.selectHealthConnectApp();
			Thread.sleep(2000);
		}
		System.out.println("/*-- 3. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		System.out.println("/*-- 4. Click Dropdown Menu --*/");
		inClinicExperiencePage.clickDropdownMenu();
		Thread.sleep(5000);
		System.out.println("/*-- 5. Select Supply Items Option from the Drop Down --*/");
		inClinicExperiencePage.selectSupplyItemsFromDropdown();
		Thread.sleep(2000);
		System.out.println("/*-- 6. Click on 'AstraZeneca ChAdOx1-S - ABX3120' Supply Item--*/");
		inClinicExperiencePage.selectSupplyItemName();
		Thread.sleep(2000);
		String doseConversionFactor = inClinicExperiencePage.getDoseConversionFactor();
		System.out.println("/*-- 7. remaining doses are: -->" + doseConversionFactor);
		Thread.sleep(2000);
		System.out.println("/*-- 8. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		System.out.println("/*-- 9. Click Dropdown Menu --*/");
		inClinicExperiencePage.clickDropdownMenu();
		Thread.sleep(5000);
		System.out.println("/*-- 10. Navigate and Select Supply Locations --*/");
		inClinicExperiencePage.selectSupplyLocationFromDropdown();
		Thread.sleep(2000);
		System.out.println("/*-- 11. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		System.out.println("/*-- 12. Click and select supply distribution SDST-0000001558 --*/");
		inClinicExperiencePage.clickSupplyDistribution();
		Thread.sleep(2000);
		String supplyName = inClinicExperiencePage.getSupplyDistributionName();
		System.out.println("/*-- 13. Supply Destribution Name is: -->" + supplyName);
		Thread.sleep(2000);
		System.out.println("/*-- 15. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		if (inClinicExperiencePage.supplyLocDisplayed()) {
			System.out.println("/*-- 16. User is already on Supply loc--*/");
		} else {
			System.out.println("/*-- 16.1. Click Dropdown Menu --*/");
			inClinicExperiencePage.clickDropdownMenu();
			Thread.sleep(5000);
			System.out.println("/*-- 16.2. Navigate and Select Supply Locations --*/");
			inClinicExperiencePage.selectSupplyLocationFromDropdown();
			Thread.sleep(2000);
		}
		System.out.println("/*-- 17. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		System.out.println("/*-- 18. Navigate and Select Dropdown to Receive Supplies Button --*/");
		inClinicExperiencePage.SelectDropDownToClickReceiveSuppliesButton();
		Thread.sleep(2000);
		System.out.println("/*-- 19. Click to Receive Supplies Button --*/");
		inClinicExperiencePage.ClickDropDownToClickReceiveSuppliesButton();
		Thread.sleep(2000);
		System.out.println("/*-- 20. Validate Supply Item Filed Present on Layout --*/");
		String supplyItem = inClinicExperiencePage.validateSupplyItemField();
		String expectedSupplyItemLabel = "*Supply Item";
		System.out.println("/*-- 20.1.: -->" + supplyItem + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((supplyItem), (expectedSupplyItemLabel));
		Thread.sleep(2000);
		System.out.println("/*-- 21. click to select the Supply Item --*/");
		inClinicExperiencePage.clickSupplyItemTextBox();
		Thread.sleep(2000);
		System.out.println("/*-- 22. Select AstraZeneca ChAdOx1-S - ABX3120 Supply Item --*/");
		inClinicExperiencePage.selectSupplyItem("AstraZeneca ChAdOx1-S - ABX3120");
		Thread.sleep(2000);
		System.out.println("/*-- 23. Validate Quantity Filed Present on Layout --*/");
		String qty = inClinicExperiencePage.validateQTYField();
		String expectedqtyLabel = "Quantity";
		System.out.println("/*-- 23.1.: -->" + qty + "Field is Present on Layout and is Mandatory");
		Assert.assertEquals((qty), (expectedqtyLabel));
		Thread.sleep(2000);
		System.out.println("/*-- 24. Enter quantity as 1 --*/");
		inClinicExperiencePage.enterQuantity();
		Thread.sleep(2000);
		System.out.println("/*-- 25. Validate Dose Conversion Factor Filed Present on Layout --*/");
		String dcf = inClinicExperiencePage.validateDCFField();
		String expecteddcfLabel = "Dose Conversion Factor";
		System.out.println("/*-- 25.1.: -->" + dcf + "Field is Present on Layout");
		Assert.assertEquals((dcf), (expecteddcfLabel));
		Thread.sleep(2000);
		System.out.println("/*-- 26. Validate Doses Filed Present on Layout --*/");
		String doses = inClinicExperiencePage.validateDosesField();
		String expecteddosesLabel = "*Doses";
		System.out.println("/*-- 26.1.: -->" + doses + "Field is Present on Layout");
		Assert.assertEquals((doses), (expecteddosesLabel));
		Thread.sleep(2000);
		System.out.println("/*-- 27. Validate Supply Distribution To Filed Present on Layout --*/");
		String supplydistributionto = inClinicExperiencePage.validateSupplyDistributionToField();
		String expectedsupplydistributiontoLabel = "*Supply Distribution To";
		System.out.println("/*-- 27.1.: -->" + supplydistributionto + "Field is Present on Layout");
		Assert.assertEquals((supplydistributionto), (expectedsupplydistributiontoLabel));
		Thread.sleep(2000);
		System.out.println("/*-- 28. Select a Supply Distribution --*/");
		inClinicExperiencePage.selectIncomingSupplyDistribution();
		Thread.sleep(2000);
		System.out.println("/*-- 29. Select a Reason For Reception --*/");
		inClinicExperiencePage.selectReasonForReception();
		Thread.sleep(2000);
		System.out.println("/*-- 30. Save Button is Present on Layout --*/");
		inClinicExperiencePage.ValidateSaveButtonIsDisplayedOnReceiveSupplies();
		Thread.sleep(2000);
		System.out.println("/*-- 31. Cancel Button is Present on Layout --*/");
		inClinicExperiencePage.ValidateCancelButtonIsDisplayedOnReceiveSupplies();
		Thread.sleep(2000);
		System.out.println("/*-- 32. Click Save Button --*/");
		inClinicExperiencePage.ClickSaveButton();
		Thread.sleep(2000);
	}
	
}

