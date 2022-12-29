package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class AdjustmentsCP extends BaseTest {

	@DataProvider(name = "dosesAmount")
	public static Object[][] primeNumbers() {
		return new Object[][]{{"25"}, {"-30"}};
	}

	@DataProvider(name = "quantitiesAmount")
	public static Object[][] primeNumbers2() {
		return new Object[][]{{"3"},{"-2"}};
	}

	//Login as an admin for now, needs to be updated to PPHIS
	//Needs to update TestcaseId for both test
	@Test(dataProvider = "dosesAmount")
	public void CP_Can_Do_Single_Adjustment_ByDosages_Positive_And_Negative_Value_AS_PPHIS(String dosesAmount) throws Exception {
		TestcaseID = "223357"; //C223357
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

		log("/*1.----Login as an PPHIS--*/");
		CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		Thread.sleep(10000);

		log("/*2.----Navigate to Supply Console Page --*/");
		SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyConsolePage();

		log("/*3.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*4.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnFirstContainerDropDownMenu();
		Thread.sleep(2000);

		log("/*5.----select Adjustment from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectAdjustmentFromDropDown();
		double remainingDosesBeforeAdjustment = supplyConsolePage.getActualRemainingDoses();
		double doseConversionFactorRead = supplyConsolePage.getDoseConversionFactor();
		log("/*----Remaining Doses Before Adjustment " + remainingDosesBeforeAdjustment + " --*/");
		log("/*----Dose Conversion Factor " + doseConversionFactorRead + " --*/");
		log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

		log("/*6.----set Adjustment Doses amount --*/");
		supplyConsolePage.setDosesAmount(Double.toString(amountOfDosesToAdjust));

		double remainingDosesAfterAdjustmentInPopUp = supplyConsolePage.getActualRemainingDoses();
		log("/*----Remaining Doses After Adjustment " + remainingDosesAfterAdjustmentInPopUp + " --*/");

		log("/*7.----Reason For Adjustment: 'Administered Vaccine' --*/");
		supplyConsolePage.selectReasonForAdjustmentDropDown();

		log("/*8----Clicking on btn Adjustment --*/");
		supplyConsolePage.clickBtnAdjustmentAtContainerAdjustmentPopUp();

		//Validation values in Container - Adjustment pop-up
		log("Validation values in Adjustment pop-up / Remaining doses before adjustment " + remainingDosesBeforeAdjustment
				+ " / Adjustment doses amount " + amountOfDosesToAdjust + " / Remaining doses after Adjustment " + remainingDosesAfterAdjustmentInPopUp);
		assertEquals((remainingDosesBeforeAdjustment + amountOfDosesToAdjust), remainingDosesAfterAdjustmentInPopUp);

		log("/*9.----Read Remaining Doses And Quantity After Adjustment --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*10.----Calculating Remaining Doses And Quantity After Adjustment --*/");
		HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfterAdjustment = new HashMap<>();
		for (int i = 0; i < remainingDosesAndQuantityBeforeAdjustment.size(); i++) {
			ArrayList<Double> writeToList = new ArrayList<>();
			ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeAdjustment.get(i);
			double remainingDoses = readFromList.get(0);
			double remainingQuantity = readFromList.get(1);
			double doseConversionFactor = readFromList.get(2);
			//Actual calculation
			double afterAdjustmentDoses = remainingDoses + amountOfDosesToAdjust;
			double afterAdjustmentQuantity = Double.parseDouble(new DecimalFormat("##.####").format(
					remainingQuantity + (amountOfDosesToAdjust / doseConversionFactor)));
			writeToList.add(afterAdjustmentDoses);
			writeToList.add(afterAdjustmentQuantity);
			writeToList.add(doseConversionFactor);
			calculatedRemainingDosesAndQuantityAfterAdjustment.put(i, writeToList);
		}

		log("/*11.----Compering Remaining Doses and Quantity actual vs calculated--*/");
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


	@Test(dataProvider = "quantitiesAmount")
	public void CP_Can_Do_Single_Adjustment_ByQuantities_Positive_And_Negative_Value_AS_PPHIS(String quantitiesAmount) throws Exception {
		TestcaseID = "223357"; //C223357
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

		log("/*1.----Login as an PPHIS--*/");
		CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		Thread.sleep(10000);

		log("/*2.----Navigate to Supply Console Page --*/");
		SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyConsolePage();
		CommonMethods common = new CommonMethods(getDriver());

		log("/*3.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double[] remDosesQtyConversionFactorBefore = common.getRemainingDosesQtyAndConversionFactor(firstRow);
		double remainingDosesBefore = remDosesQtyConversionFactorBefore[0];
		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesBefore);
		double remainingQuantitiesBefore = remDosesQtyConversionFactorBefore[1];
		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesBefore);
		double remainingConversionFactor = remDosesQtyConversionFactorBefore[2];
		log("/*----Dose Conversion Factor " + remainingConversionFactor + " --*/");

		log("/*4.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnFirstContainerDropDownMenu();
		Thread.sleep(2000);

		log("/*5.----Select Adjustment from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectAdjustmentFromDropDown();

		log("/*6.----Set Adjustment Quantities amount: " + amountOfQuantitiesToAdjust + "--*/");
		supplyConsolePage.setQuantityAmount(Double.toString(amountOfQuantitiesToAdjust));

		log("/*7.----Reason For Adjustment: 'Administered Vaccine' --*/");
		supplyConsolePage.selectReasonForAdjustmentDropDown();

		log("/*8.----Clicking on btn Adjustment --*/");
		supplyConsolePage.clickBtnAdjustmentAtContainerAdjustmentPopUp();

		log("/*9.----Quantity Remaining Doses/Remaining Quantity check After --*/");
		double[] remDosesQtyConversionFactorAfter = common.getRemainingDosesQtyAndConversionFactor(firstRow);
		double remainingDosesAfter = remDosesQtyConversionFactorAfter[0];
		log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDosesAfter);
		double remainingQuantitiesAfter = remDosesQtyConversionFactorAfter[1];
		log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQuantitiesAfter);
		double remainingConversionAfter = remDosesQtyConversionFactorAfter[2];
		log("/*----Dose Conversion Factor " + remainingConversionAfter + " --*/");

		log("/*10.----Validate Remaining Doses, Remaining Quantities and Conversion factor --*/");
		log("----Validation by Doses --");
		double remainingDosesCalculation = Double.parseDouble(new DecimalFormat("##.####").format(
				(remainingQuantitiesBefore + amountOfQuantitiesToAdjust) * remainingConversionFactor));
		assertEquals(remainingDosesAfter, remainingDosesCalculation);
		log("----Validation by Quantities --");
		assertEquals(remainingQuantitiesAfter, (remainingQuantitiesBefore + amountOfQuantitiesToAdjust));
		log("----Validation Conversion factor --");
		assertEquals(remainingConversionAfter, remainingConversionFactor);
	}

	public static boolean isNegative(double d) {
		return Double.compare(d, 0.0) < 0;
	}
}