package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class WastageCP extends BaseTest {
	String supplyLocation;
	String env;
	Map<String, Object> testData;
	int firstRow = 1; //Default value, wasting from first row only
	@Test()
	public void Can_Do_Single_Wastage_ByDosagesCP() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		supplyLocation = String.valueOf(testData.get("supplyLocationFrom"));
		double amountOfDosesToWaste = 3;
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
		MainPageCP cpMainPage = new MainPageCP(getDriver());

		log("/*1.----Login as Clinician --*/");
		log("TestCase: C243116");
		TestcaseID = "243116"; //C243116
		loginPage.loginIntoCommunityPortalAsClinician();


		log("/2.----Navigate to Supply Console Page --*/");
		MainPageCP.goToSupplyLocation(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supplyLocation);
		log("/*3.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(firstRow);

		log("/*4.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);
		Thread.sleep(2000);

		log("/*5.----select Wastage from the DropDownMenu dropdown menu --*/");
		SupplyLocationRelatedItems.selectWastageFromDropDown(driver);
		double remainingDosesBeforeWastage = supplyConsolePage.getActualRemainingDoses();
		double doseConversionFactorValue = supplyConsolePage.getDoseConversionFactor();
		log("/*----Remaining Doses Before Wastage " + remainingDosesBeforeWastage + " --*/");
		log("/*----Dose Conversion Factor " + doseConversionFactorValue + " --*/");
		log("/*----Amount Wastage Doses " + amountOfDosesToWaste + " --*/");

		log("/*6.----set Wastage Doses amount: " +amountOfDosesToWaste +"--*/");
		ContainerWastageForm.enterAdjustmentDosages(driver, Double.toString(amountOfDosesToWaste));
		double remainingDosesAfterWastage = supplyConsolePage.getActualRemainingDoses();
		log("/*----Quantity Remaining Doses After Wastage " + remainingDosesAfterWastage + " --*/");

		log("/*7.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
		supplyConsolePage.selectReasonForWastageDropDown();

		log("/*8.----Clicking on btn Wastage --*/");
		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		//Verification values in Container - Wastage pop-up
		assertEquals((remainingDosesBeforeWastage - amountOfDosesToWaste), remainingDosesAfterWastage);

		log("/*9.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(firstRow);

		log("/*10.----Calculating Remaining Doses And Quantity After Deduction --*/");
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

		log("/*11.----Compering Remaining Doses and Quantity actual vs calculated--*/");
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
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction, 0.01);

			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeDeduction + " vs dose conversion factor after adjustment " + doseConversionAfterDeduction);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);

			log("Compering dose conversion factor value " + doseConversionFactorValue + " vs dose conversion factor after adjustment " + doseConversionAfterDeduction);
			assertEquals(doseConversionFactorValue, doseConversionAfterDeduction);
		}

		log("/*.----Extra steps to check the actual amount of doses wastage on Container - Wastage page --*/");
		log("/*12.----Click on Container's dropdown --*/");
		SupplyLocationRelatedItems.clickOnFirstContainerDropDownMenu(driver);

		log("/*13.----select Wastage from the DropDownMenu dropdown menu --*/");
		SupplyLocationRelatedItems.selectWastageFromDropDown(driver);

		double actualDosesAmount = supplyConsolePage.getActualRemainingDoses();
		log("/*----Actual Quantity Doses " + actualDosesAmount + " --*/");
		assertEquals(actualDosesAmount, remainingDosesAfterWastage);
	}


	//We don't use Quantity field anymore it will be in READ ONLY mode, Oct 12,2023 as per Sheila Artes
	//Testcase will be disabled
//	@Test()
//	public void Can_Do_Single_Wastage_ByQuantityCP() throws Exception {
//		log("Target Environment: "+ Utils.getTargetEnvironment());
//		env = Utils.getTargetEnvironment();
//		testData = Utils.getTestData(env);
//		supplyLocation = String.valueOf(testData.get("supplyLocationFrom"));
//		double amountOfQuantityToWaste = 1;
//		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
//		MainPageCP cpMainPage = new MainPageCP(getDriver());
//
//		log("/*1.----Login --*/");
//		switch (Utils.getTargetEnvironment()) {
//			case "comunityqa_immsbc_admin":
//				log("Login AS comunityqa_immsbc_admin");
//				TestcaseID = "245090"; //C245090
//				loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
//				break;
//			default:
//				log("Login as Clinician");
//				TestcaseID = "243116"; //C243116
//				loginPage.loginIntoCommunityPortalAsClinician();
//		}
//
//		log("/*2.----Navigate to Supply Console Page --*/");
//		//cpMainPage.navigateToSupplyConsolePage();
//		cpMainPage.selectSupplyLocationName(supplyLocation);
//		log("/*3.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
//		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(firstRow);
//		double remainingDosesBefore = remainingDosesAndQuantityBeforeDeduction.get(0).get(0);
//		log("/*-- . remaining doses Distribution_1_1 Before are: -->" + remainingDosesBefore);
//		double remainingQuantitiesBefore = remainingDosesAndQuantityBeforeDeduction.get(0).get(1);
//		log("/*-- . remaining Quantity Distribution_1_1 Before are: -->" + remainingQuantitiesBefore);
//		double remainingConversionFactor = remainingDosesAndQuantityBeforeDeduction.get(0).get(2);
//		log("/*----Dose Conversion Factor " + remainingConversionFactor + " --*/");
//
//		log("/*4.----Click on Container's dropdown --*/");
//		supplyConsolePage.clickOnFirstContainerDropDownMenu();
//		Thread.sleep(2000);
//
//		log("/*5.----select Wastage from the DropDownMenu dropdown menu --*/");
//		supplyConsolePage.selectWastageFromDropDown();
//
//		log("/*6.----set Wastage Quantity amount: " +amountOfQuantityToWaste +"--*/");
//		supplyConsolePage.setQuantityAmount(Double.toString(amountOfQuantityToWaste));
//
//		log("/*7.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
//		supplyConsolePage.selectReasonForWastageDropDown();
//
//		log("/*8.----Clicking on btn Wastage --*/");
//		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
//		Thread.sleep(2000);
//		driver.navigate().refresh();
//		Thread.sleep(2000);
//		log("/*9.----Quantity Remaining Doses/Remaining Quantity check After --*/");
//		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(firstRow);
//		double remainingDosesAfter = remainingDosesAndQuantityAfterDeduction.get(0).get(0);
//		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesAfter);
//		double remainingQuantitiesAfter = remainingDosesAndQuantityAfterDeduction.get(0).get(1);
//		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesAfter);
//		double remainingConversionAfter = remainingDosesAndQuantityAfterDeduction.get(0).get(2);
//		log("/*----Dose Conversion Factor " + remainingConversionAfter + " --*/");
//
//		log("/*10.----Validate Remaining Doses, Remaining Quantities and Conversion factor --*/");
//		log("----Validation by Doses --");
//		double remainingDosesBeforeCalculation = (remainingQuantitiesBefore - amountOfQuantityToWaste) * remainingConversionFactor;
//		assertEquals(remainingDosesBeforeCalculation, remainingDosesAfter, 0.1);
//		log("----Validation by Quantities --");
//		assertEquals((remainingQuantitiesBefore - amountOfQuantityToWaste), remainingQuantitiesAfter);
//		log("----Validation Conversion factor --");
//		assertEquals(remainingConversionFactor,remainingConversionAfter);
//	}

}