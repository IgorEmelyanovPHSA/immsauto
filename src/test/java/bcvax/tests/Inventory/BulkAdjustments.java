package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
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
		SupplyConsolePage.selectSupplyLocationName(driver, supply_location_from);

		log("/*6.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
		log("/*---     count:" + countSupplyContainers);

		int numberOfRows = 3;
		ArrayList<Map<String, Map<String, String>>> my_containers = new ArrayList<>();
		log("/*4.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= numberOfRows) {
			for (int k = 1; k <= numberOfRows; k++) {
				Map<String, Map<String, String>> my_container_data = SupplyLocationRelatedItems.checkSupplyContainer(driver, k);
				my_containers.add(my_container_data);
			}
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}

		//Remaining Doses and Quantity count // 3 rows, ref step7 containers count
		log("/*8.----Read Remaining Doses And Quantity Before Deduction --*/");
		List<String> my_conts = new ArrayList<>();
		for(Map<String, Map<String, String>> cont: my_containers) {
			for(String my_key: cont.keySet()) {
				my_conts.add(my_key);
			}
		}

		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
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
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
		log("/*13.----Calculating Remaining Doses And Quantity After Adjustment --*/");
		HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfterAdjustment = new HashMap<>();
		for (int i = 0; i < remainingDosesAndQuantityBeforeAdjustment.size(); i++) {
			ArrayList<Double> writeToList = new ArrayList<>();
			ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeAdjustment.get(i);
			double remainingDoses = readFromList.get(0);
			double remainingQuantity = readFromList.get(1);
			double doseConversionFactor = readFromList.get(2);
			//Actual calculation
			double afterAdjustmentDoses = remainingDoses + amountOfDosesToAdjust;
			double afterAdjustmentQuantity = Double.parseDouble(df.format(
					remainingQuantity + (amountOfDosesToAdjust / doseConversionFactor)));
			writeToList.add(afterAdjustmentDoses);
			writeToList.add(afterAdjustmentQuantity);
			writeToList.add(doseConversionFactor);
			calculatedRemainingDosesAndQuantityAfterAdjustment.put(i, writeToList);
		}
		
		log("/*14.----Compering Remaining Doses and Quantity actual vs calculated--*/");
		//Comparing 2 objects actualRemainingDosesAndQuantityAfterAdjustment vs calculatedRemainingDosesAndQuantityAfterAdjustment
		for (int i = 0; i < actualRemainingDosesAndQuantityAfterAdjustment.size(); i++) {
			ArrayList<Double> afterDeduction = actualRemainingDosesAndQuantityAfterAdjustment.get(i);
			double remainingDosesAfterAdjustment = afterDeduction.get(0);
			double remainingQuantityAfterAdjustment = afterDeduction.get(1);
			double doseConversionFactorBeforeAdjustment = afterDeduction.get(2);
			ArrayList<Double> calculated = calculatedRemainingDosesAndQuantityAfterAdjustment.get(i);
			double calculatedDosesAfterAdjustment = Double.valueOf(df.format(calculated.get(0)));
			double calculatedRemainingQuantityAfterAdjustment = Double.valueOf(df.format(calculated.get(1)));
			double doseConversionAfterAdjustment = Double.valueOf(df.format(calculated.get(2)));
			
			//Comparing results
			log("Compering remaining doses after adjustment " + remainingDosesAfterAdjustment + " vs calculated doses after adjustment " + calculatedDosesAfterAdjustment);
			assertEquals(remainingDosesAfterAdjustment, calculatedDosesAfterAdjustment);

			log("Compering remaining quantity after adjustment " + remainingQuantityAfterAdjustment + " vs calculated quantity after adjustment " + calculatedRemainingQuantityAfterAdjustment);
			assertEquals(remainingQuantityAfterAdjustment, calculatedRemainingQuantityAfterAdjustment, 0.011);
			//***
			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeAdjustment + " vs dose conversion factor after adjustment " + doseConversionAfterAdjustment);
			assertEquals(doseConversionFactorBeforeAdjustment, doseConversionAfterAdjustment);
			}
	}

	//@Test(dataProvider = "quantitiesAmount")
	public void Can_Do_Bulk_Adjustment_ByQuantities_Positive_And_Negative_Value_AS_PPHIS(String quantity) throws Exception {
		TestcaseID = "223360"; //C223360
		log("Target Environment: "+ Utils.getTargetEnvironment());
		AllureLifecycle lifecycle = Allure.getLifecycle();
		double amountOfQuantityToAdjust = Double.parseDouble(quantity);
		boolean isNegativeFlag = isNegative(amountOfQuantityToAdjust);
		String reasonForAdjustment = "Administered Vaccine";

		if (isNegativeFlag == false) {
			log("/*0.----Positive Scenario: Can_Do_Bulk_Adjustment_ByQuantities_Positive_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Bulk_Adjustment_ByQuantities_Positive_Value_AS_PPHIS"));
		} else {
			log("/*0.----Negative Scenario: Can_Do_Bulk_Adjustment_ByQuantities_Negative_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Bulk_Adjustment_ByQuantities_Negative_Value_AS_PPHIS"));
		}
		log("/*----Amount Adjustment Quantities " + amountOfQuantityToAdjust + " --*/");

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
		SupplyConsolePage.selectSupplyLocationName(driver, supply_location_from);
		log("/*4.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
		log("/*---     count:" + countSupplyContainers);

		int numberOfRows = 3;
		ArrayList<Map<String, Map<String, String>>> my_containers = new ArrayList<>();
		log("/*4.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= numberOfRows) {
			for (int k = 1; k <= numberOfRows; k++) {
				Map<String, Map<String, String>> my_container_data = SupplyLocationRelatedItems.checkSupplyContainer(driver, k);
				my_containers.add(my_container_data);
			}
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}

		  //Default COUNT limited to 3 rows as per step5
		//Remaining Doses and Quantity count // 3 rows, ref step5 containers count
		log("/*6.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*7.----Click on bulk Adjustment button on Supply page--*/");
		SupplyLocationRelatedItems.clickAdjustmentButton(driver);

		log("/*8.----Enter the Quantities values for 3 rows and reason for adjustment: " +reasonForAdjustment +"--*/");
		supplyConsolePage.enterBulkAdjustmentByQuantitiesWithReason(amountOfQuantityToAdjust, reasonForAdjustment);

		log("/*9.----Click button Adjustment on Container - Adjustment page --*/");
		supplyConsolePage.clickAdjustmentButtonContainerAdjustmentPage();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

		log("/*10.----Read Remaining Doses And Quantity After Adjustment --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*11.----Calculating Remaining Doses And Quantity After Adjustment --*/");
		HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfterAdjustment = new HashMap<>();
		for (int i = 0; i < remainingDosesAndQuantityBeforeAdjustment.size(); i++) {
			ArrayList<Double> writeToList = new ArrayList<>();
			ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeAdjustment.get(i);
			double remainingDoses = readFromList.get(0);
			double remainingQuantity = readFromList.get(1);
			double doseConversionFactor = readFromList.get(2);
			//Actual calculation
			double afterAdjustmentDoses =  remainingDoses + (amountOfQuantityToAdjust * doseConversionFactor);
			double afterAdjustmentQuantity = remainingQuantity + amountOfQuantityToAdjust;
			writeToList.add(afterAdjustmentDoses);
			writeToList.add(afterAdjustmentQuantity);
			writeToList.add(doseConversionFactor);
			calculatedRemainingDosesAndQuantityAfterAdjustment.put(i, writeToList);
		}

		log("/*12.----Compering Remaining Doses and Quantity actual vs calculated--*/");
		//Comparing 2 objects actualRemainingDosesAndQuantityAfterAdjustment vs calculatedRemainingDosesAndQuantityAfterAdjustment
		for (int i = 0; i < actualRemainingDosesAndQuantityAfterAdjustment.size(); i++) {
			ArrayList<Double> afterDeduction = actualRemainingDosesAndQuantityAfterAdjustment.get(i);
			double remainingDosesAfterAdjustment = afterDeduction.get(0);
			double remainingQuantityAfterAdjustment = afterDeduction.get(1);
			double doseConversionFactorBeforeAdjustment = afterDeduction.get(2);
			ArrayList<Double> calculated = calculatedRemainingDosesAndQuantityAfterAdjustment.get(i);
			double calculatedDosesAfterAdjustment = Double.valueOf(df.format(calculated.get(0)));
			double calculatedRemainingQuantityAfterAdjustment = Double.valueOf(df.format(calculated.get(1)));
			double doseConversionAfterAdjustment = Double.valueOf(df.format(calculated.get(2)));

			//Comparing results
			log("Compering remaining doses after adjustment " + remainingDosesAfterAdjustment + " vs calculated doses after adjustment " + calculatedDosesAfterAdjustment);
			assertEquals(remainingDosesAfterAdjustment, calculatedDosesAfterAdjustment);

			log("Compering remaining quantity after adjustment " + remainingQuantityAfterAdjustment + " vs calculated quantity after adjustment " + calculatedRemainingQuantityAfterAdjustment);
			assertEquals(remainingQuantityAfterAdjustment, calculatedRemainingQuantityAfterAdjustment, 0.011);
			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeAdjustment + " vs dose conversion factor after adjustment " + doseConversionAfterAdjustment);
			assertEquals(doseConversionFactorBeforeAdjustment, doseConversionAfterAdjustment);
		}
	}
	
	public static boolean isNegative(double d) {
		return Double.compare(d, 0.0) < 0;
	}
}
