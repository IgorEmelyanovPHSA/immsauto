package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.MainPageOrg;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import constansts.Apps;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class Adjustments extends BaseTest {
	String env;
	MainPageOrg orgMainPage;
	Map<String, Object> testData;
	String supply_location_from;
	String supply_location_to;
	String distribution_from;
	String distribution_to;
	String container_from;
	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
		distribution_from = String.valueOf(testData.get("distributionFrom"));
		distribution_to = String.valueOf(testData.get("distributionTo"));
		container_from = String.valueOf(testData.get("containerFrom"));
	}

	@DataProvider(name = "dosesAmount")
	public static Object[][] primeNumbers() {
		return new Object[][]{{"25"}, {"-30"}};
	}

	@DataProvider(name = "quantitiesAmount")
	public static Object[][] primeNumbers2() {
		return new Object[][]{{"3"},{"-2"}};
	}

	@Test(dataProvider = "dosesAmount")
	public void Can_Do_Single_Adjustment_ByDosages_Positive_And_Negative_Value(String dosesAmount) throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		AllureLifecycle lifecycle = Allure.getLifecycle();

		double amountOfDosesToAdjust = Double.parseDouble(dosesAmount);
		boolean isNegativeFlag = isNegative(amountOfDosesToAdjust);
		if (isNegativeFlag == false) {
			log("/*0.----Positive Scenario: Can_Do_Single_Adjustment_ByDosages_Positive_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Single_Adjustment_ByDosages_Positive_Value_AS_PPHIS"));
		} else {
			log("/*0.----Negative Scenario: Can_Do_Single_Adjustment_ByDosages_Negative_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Single_Adjustment_ByDosages_Negative_Value_AS_PPHIS"));
		}
		log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");
		int numberOfRows = 1; //Default dosesAmount, adjustment from first row only

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				log("Login as ImmsBCAdmin");
				TestcaseID = "244846"; //C244846
				loginPage.loginAsImmsBCAdmin();
				break;
			default:
				log("Login AS default user (PPHIS)");
				TestcaseID = "223357"; //C223357
				loginPage.loginAsPPHIS();
		}
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();

		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();

		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();

		log("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.selectSupplyLocationName(supply_location_from);
		log("/*6.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*7.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnFirstContainerDropDownMenu();
		Thread.sleep(1000);
		log("/*8.----select Adjustment from the DropDownMenu dropdown menu --*/");
		try {
			supplyConsolePage.selectAdjustmentFromDropDown();
		} catch (ElementNotInteractableException ex) {
			System.out.println("*** WARNING*** Couldn't Select the Action. Try to click Action button again...");
			supplyConsolePage.clickOnFirstContainerDropDownMenu();
			Thread.sleep(1000);
			supplyConsolePage.selectAdjustmentFromDropDown();
		}
		double remainingDosesBeforeAdjustment = supplyConsolePage.getActualRemainingDoses();
		double doseConversionFactorRead = supplyConsolePage.getDoseConversionFactor();
		log("/*----Remaining Doses Before Adjustment " + remainingDosesBeforeAdjustment + " --*/");
		log("/*----Dose Conversion Factor " + doseConversionFactorRead + " --*/");
		log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

		log("/*9.----set Adjustment Doses amount --*/");
		supplyConsolePage.setDosesAmount(Double.toString(amountOfDosesToAdjust));

		double remainingDosesAfterAdjustmentInPopUp = supplyConsolePage.getActualRemainingDoses();
		log("/*----Remaining Doses After Adjustment " + remainingDosesAfterAdjustmentInPopUp + " --*/");

		log("/*10.----Reason For Adjustment: 'Administered Vaccine' --*/");
		supplyConsolePage.selectReasonForAdjustmentDropDown();

		log("/*11----Clicking on btn Adjustment --*/");
		supplyConsolePage.clickBtnAdjustmentAtContainerAdjustmentPopUp();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		//Validation values in Container - Adjustment pop-up
		log("Validation values in Adjustment pop-up / Remaining doses before adjustment " + remainingDosesBeforeAdjustment
				+ " / Adjustment doses amount " + amountOfDosesToAdjust + " / Remaining doses after Adjustment " + remainingDosesAfterAdjustmentInPopUp);
		assertEquals((remainingDosesBeforeAdjustment + amountOfDosesToAdjust), remainingDosesAfterAdjustmentInPopUp);

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
			double calculatedDosesAfterAdjustment = calculated.get(0);
			double calculatedRemainingQuantityAfterAdjustment = calculated.get(1);
			double doseConversionAfterAdjustment = calculated.get(2);

			//Comparing results
			log("Compering remaining doses after adjustment " + remainingDosesAfterAdjustment + " vs calculated doses after adjustment " + calculatedDosesAfterAdjustment);
			assertEquals(remainingDosesAfterAdjustment, calculatedDosesAfterAdjustment);

			log("Compering remaining quantity after adjustment " + remainingQuantityAfterAdjustment + " vs calculated quantity after adjustment " + calculatedRemainingQuantityAfterAdjustment);
			assertEquals(remainingQuantityAfterAdjustment, calculatedRemainingQuantityAfterAdjustment);

			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeAdjustment + " vs dose conversion factor after adjustment " + doseConversionAfterAdjustment);
			assertEquals(doseConversionFactorBeforeAdjustment, doseConversionAfterAdjustment);

			log("Compering dose conversion factor dosesAmount from pop-up " + doseConversionFactorBeforeAdjustment + " vs dose conversion factor after adjustment " + doseConversionAfterAdjustment);
			assertEquals(doseConversionFactorRead, doseConversionAfterAdjustment);
		}
	}


	//@Test(dataProvider = "quantitiesAmount")
	public void Can_Do_Single_Adjustment_ByQuantities_Positive_And_Negative_Value(String quantitiesAmount) throws Exception {
		log("Target Environment: " + Utils.getTargetEnvironment());
		AllureLifecycle lifecycle = Allure.getLifecycle();

		double amountOfQuantitiesToAdjust = Double.parseDouble(quantitiesAmount);
		boolean isNegativeFlag = isNegative(amountOfQuantitiesToAdjust);
		int firstRow = 1; //Default value for first row in the grid (Supply container)

		if (isNegativeFlag == false) {
			log("/*0.----Positive Scenario: Can_Do_Single_Adjustment_ByQuantities_Positive_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Single_Adjustment_ByQuantities_Positive_Value_AS_PPHIS"));
		} else {
			log("/*0.----Negative Scenario: Can_Do_Single_Adjustment_ByQuantities_Negative_Value_AS_PPHIS--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Single_Adjustment_ByQuantities_Negative_Value_AS_PPHIS"));
		}

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				log("Login as ImmsBCAdmin");
				TestcaseID = "244846"; //C244846
				loginPage.loginAsImmsBCAdmin();
				break;
			default:
				log("Login AS default user (PPHIS)");
				TestcaseID = "223357"; //C223357
				loginPage.loginAsPPHIS();
		}
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		CommonMethods common = new CommonMethods(getDriver());
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();

		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();

		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();

		log("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.selectSupplyLocationName(supply_location_from);

		log("/*4.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		//double[] remDosesQtyConversionFactorBefore = common.getRemainingDosesQtyAndConversionFactor(firstRow);
		double remainingDosesBefore = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesBefore);
		double remainingQuantitiesBefore = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesBefore);
		double remainingConversionFactor = Double.valueOf(df.format(remainingDosesBefore / remainingQuantitiesBefore));
		log("/*----Dose Conversion Factor " + remainingConversionFactor + " --*/");

		log("/*5.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
		Thread.sleep(2000);

		log("/*6.----Select Adjustment from the DropDownMenu dropdown menu --*/");
		try {
			supplyConsolePage.selectAdjustmentFromDropDown();
		} catch (ElementNotInteractableException ex) {
			System.out.println("*** WARNING*** Couldn't Select the Action. Try to click Action button again...");
			supplyConsolePage.clickOnContainerDropDownMenu(container_from, distribution_from);
			supplyConsolePage.selectAdjustmentFromDropDown();
		}

		log("/*7.----Set Adjustment Quantities amount: " + amountOfQuantitiesToAdjust + "--*/");
		supplyConsolePage.setQuantityAmount(Double.toString(amountOfQuantitiesToAdjust));

		log("/*8.----Reason For Adjustment: 'Administered Vaccine' --*/");
		supplyConsolePage.selectReasonForAdjustmentDropDown();

		log("/*9.----Clicking on btn Adjustment --*/");
		supplyConsolePage.clickBtnAdjustmentAtContainerAdjustmentPopUp();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		log("/*10.----Quantity Remaining Doses/Remaining Quantity check After --*/");
		//double[] remDosesQtyConversionFactorAfter = common.getRemainingDosesQtyAndConversionFactor(firstRow);
		double remainingDosesAfter = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesAfter);
		double remainingQuantitiesAfter = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesAfter);
		double remainingConversionAfter = Double.valueOf(df.format(remainingDosesAfter / remainingQuantitiesAfter));
		log("/*----Dose Conversion Factor " + remainingConversionAfter + " --*/");

		log("/*11.----Validate Remaining Doses, Remaining Quantities and Conversion factor --*/");
		log("----Validation by Doses --");
		double remainingDosesCalculation = Double.parseDouble(df.format(
				(remainingQuantitiesBefore + amountOfQuantitiesToAdjust) * remainingConversionFactor));
		assertEquals(remainingDosesAfter, remainingDosesCalculation);
		log("----Validation by Quantities --");
		double remainingQuantitiesCalculation = Double.parseDouble(df.format(remainingQuantitiesBefore + amountOfQuantitiesToAdjust));
		assertEquals(remainingQuantitiesAfter, remainingQuantitiesCalculation);
		log("----Validation Conversion factor --");
		assertEquals(remainingConversionAfter, remainingConversionFactor);
	}

	public static boolean isNegative(double d) {
		return Double.compare(d, 0.0) < 0;
	}
}
