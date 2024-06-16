package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BulkAdjustments extends BaseTest {
	String supply_location_from;
	String env;
	MainPageOrg orgMainPage;
	Map<String, Object> testData;

	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
	}

	@DataProvider(name = "dosesAmount")
	public static Object[][] primeNumbers() {
		return new Object[][]{{"50"}, {"-30"}};
	}

	@DataProvider(name = "quantitiesAmount")
	public static Object[][] primeNumbers2() {
		return new Object[][]{{"3"},{"-2"}};
	}
	
	@Test(dataProvider = "dosesAmount")
	public void Can_Do_Bulk_Adjustment_ByDosages_Positive_And_Negative_Value_AS_PPHIS(String value) throws Exception {
		TestcaseID = "223360"; //C223360
		log("Target Environment: "+ Utils.getTargetEnvironment());
		AllureLifecycle lifecycle = Allure.getLifecycle();
		double amountOfDosesToAdjust = Double.parseDouble(value);
		boolean isNegativeFlag = isNegative(amountOfDosesToAdjust);
		String reasonForAdjustment = "Administered Vaccine";

		if (isNegativeFlag == false) {
			log("/*0.----Positive Scenario: Can_Do_Bulk_Adjustment_ByDosages_Positive_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Bulk_Adjustment_ByDosages_Positive_Value_AS_PPHIS"));
		} else {
			log("/*0.----Negative Scenario: Can_Do_Bulk_Adjustment_ByDosages_Negative_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Bulk_Adjustment_ByDosages_Negative_Value_AS_PPHIS"));
		}
		log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");
		
		log("/*1.----Login as an PPHIS to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		orgMainPage = new MainPageOrg(driver);
		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}

		log("/*3.----Close All previously opened Tab's --*/");
		SupplyConsolePage.closeTabsHCA(driver);

		log("/*4.----Go to Supply Locations Tab --*/");
		SupplyConsolePage.clickSupplyLocationsTab(driver);
		
		log("/*5.----Click on Automation Supply Location_1 --*/");
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

		log("/*6.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
		log("/*---     count:" + countSupplyContainers);

		int numberOfRows = 3;
		Map<String, Map<String, Double>> my_containers = new HashMap<>();
		log("/*4.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= numberOfRows) {
			for (int k = 1; k <= numberOfRows; k++) {
				Map<String, Map<String, Double>> my_container_data = SupplyLocationRelatedItems.checkSupplyContainer(driver, k);
				String my_cont_name = my_container_data.keySet().toArray()[0].toString();
				my_containers.put(my_cont_name, my_container_data.get(my_cont_name));
			}
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}

		//Remaining Doses and Quantity count // 3 rows, ref step7 containers count
		log("/*8.----Read Remaining Doses And Quantity Before Deduction --*/");
		List<String> my_conts = new ArrayList<>();
		for(String cont_name: my_containers.keySet()) {
			my_conts.add(cont_name);
		}

		log("/*9.----Click on bulk Adjustment button on Supply page--*/");
		SupplyLocationRelatedItems.clickAdjustmentButton(driver);
		Thread.sleep(5000);
		
		log("/*10.----Enter the Dosages values for 3 row and reason for Adjustment: " +reasonForAdjustment +"--*/");
		supplyConsolePage.enterBulkAdjustmentByDosageWithReason(amountOfDosesToAdjust,reasonForAdjustment);
		
		log("/*11.----Click button Adjustment on Container - Adjustment page --*/");
		supplyConsolePage.clickAdjustmentButtonContainerAdjustmentPage();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

		//Map<String, Map<String, String>> doses_after = SupplyLocationRelatedItems.getSupplyContainerDoses(driver, my_conts);
		log("/*12.----Read Remaining Doses And Quantity After Adjustment --*/");
		Map<String, Map<String, Double>> my_containers_after = SupplyLocationRelatedItems.getSupplyContainers(driver, my_conts);
		
		log("/*13.----Calculating Remaining Doses And Quantity After Adjustment --*/");

		
		log("/*14.----Compering Remaining Doses and Quantity actual vs calculated--*/");
		//Comparing 2 objects actualRemainingDosesAndQuantityAfterAdjustment vs calculatedRemainingDosesAndQuantityAfterAdjustment
		for (String my_container_after: my_containers_after.keySet()) {
			//Comparing results
			log("Compering remaining doses after adjustment");
			double dose_before = my_containers.get(my_container_after).get("Remaining Doses");
			double dose_after = my_containers_after.get(my_container_after).get("Remaining Doses");
			double conversion_factor_before = my_containers.get(my_container_after).get("Conversion Factor");
			Assert.assertEquals(dose_after, dose_before + amountOfDosesToAdjust);

			double qty_before = my_containers.get(my_container_after).get("Remaining Quantity");
			double qty_after = my_containers_after.get(my_container_after).get("Remaining Quantity");
			double conversion_factor_after = my_containers_after.get(my_container_after).get("Conversion Factor");
			log("Compering remaining quantity after adjustment " + qty_after + " vs calculated quantity after adjustment " + qty_before);
			assertEquals(qty_after, qty_before + amountOfDosesToAdjust/conversion_factor_after, 0.011);
			//***
			log("Compering dose conversion factor before adjustment");
			assertEquals(conversion_factor_after, conversion_factor_before);
			}
	}
	
	public static boolean isNegative(double d) {
		return Double.compare(d, 0.0) < 0;
	}
}
