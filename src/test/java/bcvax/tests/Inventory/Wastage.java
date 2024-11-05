package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Wastage extends BaseTest {
	String env;
	Map<String, Object> testData;
	String supply_location_from;
	String supply_location_to;
	String distribution_from;
	String distribution_to;
	MainPageOrg orgMainPage;
	SupplyConsolePage supplyConsolePage;

	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
		distribution_from = String.valueOf(testData.get("distributionFrom"));
		distribution_to = String.valueOf(testData.get("distributionTo"));
	}

	@Test()
	public void Can_Do_Single_Wastage_ByDosages() throws Exception {
		TestcaseID = "223356"; //C223356
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("Test Case Id: " + "C" + TestcaseID);
		supplyConsolePage = new SupplyConsolePage(driver);
		int numberOfRows = 1; //Default value, wasting from first row only
		double amountOfDosesToWaste = 3;

		log("/*1.----Login as PPHIS--*/");
		loginPage.loginAsPPHIS();

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
		
		log("/*4.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
		log("/*5.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);
		
		log("/*6.----select Wastage from the DropDownMenu dropdown menu --*/");
		try {
			SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		} catch(ElementNotInteractableException ex) {
			Thread.sleep(500);
			SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);
			Thread.sleep(500);
			SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		}
		double remainingDosesBeforeWastage = supplyConsolePage.getActualRemainingDoses();
		double doseConversionFactorValue = supplyConsolePage.getDoseConversionFactor();
		log("/*----Remaining Doses Before Wastage " + remainingDosesBeforeWastage + " --*/");
		log("/*----Dose Conversion Factor " + doseConversionFactorValue + " --*/");
		log("/*----Amount Wastage Doses " + amountOfDosesToWaste + " --*/");
		
		log("/*7.----set Wastage Doses amount: " +amountOfDosesToWaste +"--*/");
		ContainerWastageForm.enterAdjustmentDosages(driver, Double.toString(amountOfDosesToWaste));
		double remainingDosesAfterWastage = supplyConsolePage.getActualRemainingDoses();
		log("/*----Quantity Remaining Doses After Wastage " + remainingDosesAfterWastage + " --*/");
		
		log("/*8.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
		supplyConsolePage.selectReasonForWastageDropDown();
		
		log("/*9----Clicking on btn Wastage --*/");
		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();

		//Verification values in Container - Wastage pop-up
		assertEquals((remainingDosesBeforeWastage - amountOfDosesToWaste), remainingDosesAfterWastage);
		Thread.sleep(2000);

		log("/*10.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
		log("/*11.----Calculating Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfterDeduction = new HashMap<>();
		for (int i = 0; i < remainingDosesAndQuantityBeforeDeduction.size(); i++) {
			ArrayList<Double> writeToList = new ArrayList<>();
			ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeDeduction.get(i);
			double remainingDoses = readFromList.get(0);
			double remainingQuantity = readFromList.get(1);
			double doseConversionFactor = readFromList.get(2);
			//Actual calculation
			double afterDeductionDoses = remainingDoses - amountOfDosesToWaste;
			double afterDeductionQuantity = Double.parseDouble(df.format(
					remainingQuantity - (amountOfDosesToWaste / doseConversionFactor)));
			writeToList.add(afterDeductionDoses);
			writeToList.add(afterDeductionQuantity);
			writeToList.add(doseConversionFactor);
			calculatedRemainingDosesAndQuantityAfterDeduction.put(i, writeToList);
		}
		
		log("/*12.----Compering Remaining Doses and Quantity actual vs calculated--*/");
		//Comparing 2 objects actualRemainingDosesAndQuantityAfterDeduction vs calculatedRemainingDosesAndQuantityAfterDeduction
		for (int i = 0; i < actualRemainingDosesAndQuantityAfterDeduction.size(); i++) {
			ArrayList<Double> afterDeduction = actualRemainingDosesAndQuantityAfterDeduction.get(i);
			double remainingDosesAfterDeduction = afterDeduction.get(0);
			double remainingQuantityAfterDeduction = afterDeduction.get(1);
			double doseConversionFactorBeforeDeduction = afterDeduction.get(2);
			ArrayList<Double> calculated = calculatedRemainingDosesAndQuantityAfterDeduction.get(i);
			double calculatedDosesAfterDeduction = calculated.get(0);
			double calculatedRemainingQuantityAfterDeduction = calculated.get(1);
			double doseConversionAfterDeduction = calculated.get(2);

			//Comparing results
			log("Compering remaining doses after adjustment " + remainingDosesAfterDeduction + " vs calculated doses after adjustment " + calculatedDosesAfterDeduction);
			assertEquals(remainingDosesAfterDeduction, calculatedDosesAfterDeduction);

			log("Compering remaining quantity after adjustment " + remainingQuantityAfterDeduction + " vs calculated quantity after adjustment " + calculatedRemainingQuantityAfterDeduction);
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction);

			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeDeduction + " vs dose conversion factor after adjustment " + doseConversionAfterDeduction);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);

			log("Compering dose conversion factor value " + doseConversionFactorValue + " vs dose conversion factor after adjustment " + doseConversionAfterDeduction);
			assertEquals(doseConversionFactorValue, doseConversionAfterDeduction);
		}
		
		log("/*.----Extra steps to check the actual amount of doses wastage on Container - Wastage page --*/");
		log("/*13.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);
		
		log("/*14.----select Wastage from the DropDownMenu dropdown menu --*/");
		try {
			SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		} catch(ElementNotInteractableException ex) {
			Thread.sleep(500);
			SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);
			Thread.sleep(500);
			SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		}
		//SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		
		double actualDosesAmount = supplyConsolePage.getActualRemainingDoses();
		log("/*----Actual Quantity Doses " + actualDosesAmount + " --*/");
		assertEquals(actualDosesAmount, remainingDosesAfterWastage);
	}

	//@Test()
	public void Can_Do_Single_Wastage_ByQuantity() throws Exception {
		TestcaseID = "223356"; //C223356
		log("Target Environment: "+ Utils.getTargetEnvironment());
		supplyConsolePage = new SupplyConsolePage(driver);
		int numberOfRows = 1; //Default value, wasting from first row only
		double amountOfDosesToWaste = 3;

		log("/*1.----Login as PPHIS--*/");
		loginPage.loginAsPPHIS();

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

		log("/*4.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*5.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);

		log("/*6.----select Wastage from the DropDownMenu dropdown menu --*/");
		try {
			SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		} catch(ElementNotInteractableException ex) {
			Thread.sleep(500);
			SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);
			Thread.sleep(500);
			SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		}
		double remainingDosesBeforeWastage = supplyConsolePage.getActualRemainingDoses();
		double doseConversionFactorValue = supplyConsolePage.getDoseConversionFactor();
		log("/*----Remaining Doses Before Wastage " + remainingDosesBeforeWastage + " --*/");
		log("/*----Dose Conversion Factor " + doseConversionFactorValue + " --*/");
		log("/*----Amount Wastage Doses " + amountOfDosesToWaste + " --*/");

		log("/*7.----set Wastage Doses amount: " +amountOfDosesToWaste +"--*/");
		ContainerWastageForm.enterAdjustmentQuantity(driver, Double.toString(amountOfDosesToWaste / doseConversionFactorValue));
		double remainingDosesAfterWastage = supplyConsolePage.getActualRemainingDoses();
		log("/*----Quantity Remaining Doses After Wastage " + remainingDosesAfterWastage + " --*/");

		log("/*8.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
		supplyConsolePage.selectReasonForWastageDropDown();

		log("/*9----Clicking on btn Wastage --*/");
		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();

		//Verification values in Container - Wastage pop-up
		assertEquals((remainingDosesBeforeWastage - amountOfDosesToWaste), remainingDosesAfterWastage);

		log("/*10.----Read Remaining Doses And Quantity After Deduction --*/");
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*11.----Calculating Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfterDeduction = new HashMap<>();
		for (int i = 0; i < remainingDosesAndQuantityBeforeDeduction.size(); i++) {
			ArrayList<Double> writeToList = new ArrayList<>();
			ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeDeduction.get(i);
			double remainingDoses = readFromList.get(0);
			double remainingQuantity = readFromList.get(1);
			double doseConversionFactor = readFromList.get(2);
			//Actual calculation
			double afterDeductionDoses = remainingDoses - amountOfDosesToWaste;
			double afterDeductionQuantity = Double.parseDouble(df.format(
					remainingQuantity - (amountOfDosesToWaste / doseConversionFactor)));
			writeToList.add(afterDeductionDoses);
			writeToList.add(afterDeductionQuantity);
			writeToList.add(doseConversionFactor);
			calculatedRemainingDosesAndQuantityAfterDeduction.put(i, writeToList);
		}

		log("/*12.----Compering Remaining Doses and Quantity actual vs calculated--*/");
		//Comparing 2 objects actualRemainingDosesAndQuantityAfterDeduction vs calculatedRemainingDosesAndQuantityAfterDeduction
		for (int i = 0; i < actualRemainingDosesAndQuantityAfterDeduction.size(); i++) {
			ArrayList<Double> afterDeduction = actualRemainingDosesAndQuantityAfterDeduction.get(i);
			double remainingDosesAfterDeduction = afterDeduction.get(0);
			double remainingQuantityAfterDeduction = afterDeduction.get(1);
			double doseConversionFactorBeforeDeduction = afterDeduction.get(2);
			ArrayList<Double> calculated = calculatedRemainingDosesAndQuantityAfterDeduction.get(i);
			double calculatedDosesAfterDeduction = calculated.get(0);
			double calculatedRemainingQuantityAfterDeduction = calculated.get(1);
			double doseConversionAfterDeduction = calculated.get(2);

			//Comparing results
			log("Compering remaining doses after adjustment " + remainingDosesAfterDeduction + " vs calculated doses after adjustment " + calculatedDosesAfterDeduction);
			assertEquals(remainingDosesAfterDeduction, calculatedDosesAfterDeduction);

			log("Compering remaining quantity after adjustment " + remainingQuantityAfterDeduction + " vs calculated quantity after adjustment " + calculatedRemainingQuantityAfterDeduction);
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction);

			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeDeduction + " vs dose conversion factor after adjustment " + doseConversionAfterDeduction);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);

			log("Compering dose conversion factor value " + doseConversionFactorValue + " vs dose conversion factor after adjustment " + doseConversionAfterDeduction);
			assertEquals(doseConversionFactorValue, doseConversionAfterDeduction);
		}

		log("/*.----Extra steps to check the actual amount of doses wastage on Container - Wastage page --*/");
		log("/*13.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);

		log("/*14.----select Wastage from the DropDownMenu dropdown menu --*/");
		SupplyLocationRelatedItems.selectWastageFromDropDown(driver);

		double actualDosesAmount = supplyConsolePage.getActualRemainingDoses();
		log("/*----Actual Quantity Doses " + actualDosesAmount + " --*/");
		assertEquals(actualDosesAmount, remainingDosesAfterWastage);
	}

//	@Test()
//	public void Can_Do_Single_Wastage_ByQuantity() throws Exception {
//		log("Target Environment: "+ Utils.getTargetEnvironment());
//		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
//		CommonMethods common = new CommonMethods(getDriver());
//		int firstRow = 1; //Default value for first row in the grid (Supply container)
//		double amountOfQuantityToWaste = 1;
//
//		log("/*1.----Login --*/");
//		switch (Utils.getTargetEnvironment()) {
//			case "comunityqa_immsbc_admin_org":
//				log("Login AS comunityqa_org_immsbc_admin");
//				TestcaseID = "244844"; //C244844
//				loginPage.loginAsImmsBCAdminICE();
//				break;
//			default:
//				log("Login AS default user (PPHIS)");
//				TestcaseID = "223356"; //C223356
//				loginPage.loginAsPPHIS();
//		}
//		Thread.sleep(5000);
//
//		log("/*2.----Supply Console Page displayed --*/");
//		supplyConsolePage.verifyIsSupplyPageDisplayed();
//
//		log("/*3.----Close All previously opened Tab's --*/");
//		supplyConsolePage.closeTabsHCA();
//
//		log("/*4.----Go to Supply Locations Tab --*/");
//		supplyConsolePage.clickSupplyLocationsTab();
//
//		log("/*5.----Click on Automation Supply Location_1 --*/");
//		supplyConsolePage.selectSupplyLocationName(supply_location_from);
//		Thread.sleep(5000);
//
//		log("/*4.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
//		double[] remDosesQtyConversionFactorBefore = common.getRemainingDosesQtyAndConversionFactor(firstRow);
//		double remainingDosesBefore = remDosesQtyConversionFactorBefore[0];
//		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesBefore);
//		double remainingQuantitiesBefore = remDosesQtyConversionFactorBefore[1];
//		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesBefore);
//		double remainingConversionFactor = remDosesQtyConversionFactorBefore[2];
//		log("/*----Dose Conversion Factor " + remainingConversionFactor + " --*/");
//
//		log("/*5.----Click on Container's dropdown --*/");
//		supplyConsolePage.clickOnFirstContainerDropDownMenu();
//		Thread.sleep(2000);
//
//		log("/*6.----select Wastage from the DropDownMenu dropdown menu --*/");
//		supplyConsolePage.selectWastageFromDropDown();
//
//		log("/*7.----set Wastage Quantity amount: " +amountOfQuantityToWaste +"--*/");
//		supplyConsolePage.setQuantityAmount(Double.toString(amountOfQuantityToWaste));
//
//		log("/*8.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
//		supplyConsolePage.selectReasonForWastageDropDown();
//
//		log("/*9----Clicking on btn Wastage --*/");
//		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
//		Thread.sleep(2000);
//		driver.navigate().refresh();
//		Thread.sleep(2000);
//		log("/*10.----Quantity Remaining Doses/Remaining Quantity check After --*/");
//		double[] remDosesQtyConversionFactorAfter = common.getRemainingDosesQtyAndConversionFactor(firstRow);
//		double remainingDosesAfter = remDosesQtyConversionFactorAfter[0];
//		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesAfter);
//		double remainingQuantitiesAfter = remDosesQtyConversionFactorAfter[1];
//		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesAfter);
//		double remainingConversionAfter = remDosesQtyConversionFactorAfter[2];
//		log("/*----Dose Conversion Factor " + remainingConversionAfter + " --*/");
//
//		log("/*11.----Validate Remaining Doses, Remaining Quantities and Conversion factor --*/");
//		log("----Validation by Doses --");
//		double remainingDosesBeforeCalculation = Double.parseDouble(df.format(
//				(remainingQuantitiesBefore - amountOfQuantityToWaste) * remainingConversionFactor));
//		assertEquals(remainingDosesBeforeCalculation, remainingDosesAfter);
//		log("----Validation by Quantities --");
//		assertEquals((remainingQuantitiesBefore - amountOfQuantityToWaste), remainingQuantitiesAfter);
//		log("----Validation Conversion factor --");
//		assertEquals(remainingConversionFactor,remainingConversionAfter);
//	}
}