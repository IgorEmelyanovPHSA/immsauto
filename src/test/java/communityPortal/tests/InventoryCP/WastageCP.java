package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.MainPageCP;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class WastageCP extends BaseTest {

	@Test()
	public void Can_Do_Single_Wastage_ByDosagesCP() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		int numberOfRows = 1; //Default value, wasting from first row only
		double amountOfDosesToWaste = 3;
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
		MainPageCP cpMainPage = new MainPageCP(getDriver());

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin":
				log("Login AS comunityqa_immsbc_admin");
				TestcaseID = "245090"; //C245090
				loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
				break;
			default:
				log("Login AS default user (ClinicianInventory)");
				TestcaseID = "243116"; //C243116
				loginPage.loginIntoCommunityPortalAsClinicianInventory();
				Thread.sleep(10000);
		}

		log("/2.----Navigate to Supply Console Page --*/");
		cpMainPage.navigateToSupplyConsolePage();

		log("/*3.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*4.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnFirstContainerDropDownMenu();
		Thread.sleep(2000);

		log("/*5.----select Wastage from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectWastageFromDropDown();
		double remainingDosesBeforeWastage = supplyConsolePage.getActualRemainingDoses();
		double doseConversionFactorValue = supplyConsolePage.getDoseConversionFactor();
		log("/*----Remaining Doses Before Wastage " + remainingDosesBeforeWastage + " --*/");
		log("/*----Dose Conversion Factor " + doseConversionFactorValue + " --*/");
		log("/*----Amount Wastage Doses " + amountOfDosesToWaste + " --*/");

		log("/*6.----set Wastage Doses amount: " +amountOfDosesToWaste +"--*/");
		supplyConsolePage.setDosesAmount(Double.toString(amountOfDosesToWaste));
		double remainingDosesAfterWastage = supplyConsolePage.getActualRemainingDoses();
		log("/*----Quantity Remaining Doses After Wastage " + remainingDosesAfterWastage + " --*/");

		log("/*7.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
		supplyConsolePage.selectReasonForWastageDropDown();

		log("/*8.----Clicking on btn Wastage --*/");
		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();

		//Verification values in Container - Wastage pop-up
		assertEquals((remainingDosesBeforeWastage - amountOfDosesToWaste), remainingDosesAfterWastage);

		log("/*9.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

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
			double afterDeductionQuantity = Double.parseDouble(new DecimalFormat("##.####").format(
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
		supplyConsolePage.clickOnFirstContainerDropDownMenu();

		log("/*13.----select Wastage from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectWastageFromDropDown();

		double actualDosesAmount = supplyConsolePage.getActualRemainingDoses();
		log("/*----Actual Quantity Doses " + actualDosesAmount + " --*/");
		assertEquals(actualDosesAmount, remainingDosesAfterWastage);
	}

	@Test()
	public void Can_Do_Single_Wastage_ByQuantityCP() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		int firstRow = 1; //Default value for first row in the grid (Supply container)
		double amountOfQuantityToWaste = 1;
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
		MainPageCP cpMainPage = new MainPageCP(getDriver());
		CommonMethods common = new CommonMethods(getDriver());

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin":
				log("Login AS comunityqa_immsbc_admin");
				TestcaseID = "245090"; //C245090
				loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
				break;
			default:
				log("Login AS default user (ClinicianInventory)");
				TestcaseID = "243116"; //C243116
				loginPage.loginIntoCommunityPortalAsClinicianInventory();
				Thread.sleep(10000);
		}

		log("/*2.----Navigate to Supply Console Page --*/");
		cpMainPage.navigateToSupplyConsolePage();

		log("/*3.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
		double[] remDosesQtyConversionFactorBefore = common.getRemainingDosesQtyAndConversionFactor(firstRow);
		double remainingDosesBefore = remDosesQtyConversionFactorBefore[0];
		log("/*-- . remaining doses Distribution_1_1 Before are: -->" + remainingDosesBefore);
		double remainingQuantitiesBefore = remDosesQtyConversionFactorBefore[1];
		log("/*-- . remaining Quantity Distribution_1_1 Before are: -->" + remainingQuantitiesBefore);
		double remainingConversionFactor = remDosesQtyConversionFactorBefore[2];
		log("/*----Dose Conversion Factor " + remainingConversionFactor + " --*/");

		log("/*4.----Click on Container's dropdown --*/");
		supplyConsolePage.clickOnFirstContainerDropDownMenu();
		Thread.sleep(2000);

		log("/*5.----select Wastage from the DropDownMenu dropdown menu --*/");
		supplyConsolePage.selectWastageFromDropDown();

		log("/*6.----set Wastage Quantity amount: " +amountOfQuantityToWaste +"--*/");
		supplyConsolePage.setQuantityAmount(Double.toString(amountOfQuantityToWaste));

		log("/*7.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
		supplyConsolePage.selectReasonForWastageDropDown();

		log("/*8.----Clicking on btn Wastage --*/");
		supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();

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
		double remainingDosesBeforeCalculation = (remainingQuantitiesBefore - amountOfQuantityToWaste) * remainingConversionFactor;
		assertEquals(remainingDosesBeforeCalculation, remainingDosesAfter, 0.1);
		log("----Validation by Quantities --");
		assertEquals((remainingQuantitiesBefore - amountOfQuantityToWaste), remainingQuantitiesAfter);
		log("----Validation Conversion factor --");
		assertEquals(remainingConversionFactor,remainingConversionAfter);
	}
}