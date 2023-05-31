package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BulkWastages extends BaseTest {
	String supply_location_from;
	String env;
	Map<String, Object> testData;

	@BeforeMethod
	public void setUpClass() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		testData = Utils.getTestData(env);
		supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
	}
	@Story("C222356: Inventory Management - Wastage Bulk (Java)")
	@Test()
	public void Can_Do_Bulk_Wastage_By_Dosages_As_PPHIS() throws Exception {
		TestcaseID = "223361"; //C223361
		log("Target Environment: "+ Utils.getTargetEnvironment());
		int amountOfDosesToWaste = 1;
		String reasonForWastage = "CCI: Handling Error";
		log("/*1.----Login as an PPHIS to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();

		log("/*2.----Validate if Supply Console Page displayed --*/");log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();

		////// Supply Location_1 -> Outcoming
		log("/*5.----Click on Automation Supply Location_1 --*/");

		/////////////////////////////////////////////////
		//Try generic method
		/////////////////////////////////////////////////
		supplyConsolePage.selectSupplyLocationName(supply_location_from);
		//////////////////////////////////////////////////

		log("/*4.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
		log("/*---     count:" + countSupplyContainers);
		
		log("/*5.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= 3) {
			int k = 1;
			while (k <= 3) {
				supplyConsolePage.clickOnSupplyContainerCheckbox(k);
				log("/*---     containers record number: " + k);
				Thread.sleep(1000);
				k++;
			}
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}
		int numberOfRows = 3;  //Default COUNT limited to 3 rows as per step5
		//Remaining Doses and Quantity count // 3 rows, ref step5 containers count
		log("/*6.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
		log("/*7.----Click on bulk Wastage button on Supply page--*/");
		supplyConsolePage.clickBulkWastageButton();

		log("/*8.----Enter the Dosages values for 3 row and reason for wastage: " +reasonForWastage +"--*/");
		supplyConsolePage.enterBulkWastageByDosageWithReason(amountOfDosesToWaste, reasonForWastage);
		
		log("/*9.----Click button Wastage on Container - Wastage page --*/");
		supplyConsolePage.clickWastageButtonContainerWastagePage();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		log("/*10.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(3);
		
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
//            log("Row number " + i + " / Remaining Doses = " + remainingDoses + " / Remaining Quantity = " + remainingQuantity
//                    + " / Dose Conversion Factor = " + doseConversionFactor);
//            log("Row number " + i + " / Remaining Doses after deduction = " + afterDeductionDoses
//                    + " / Remaining Quantity after deduction = " + afterDeductionQuantity + " / Dose Conversion Factor = " + doseConversionFactor);
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
			assertEquals(remainingDosesAfterDeduction, calculatedDosesAfterDeduction);
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction, 0.011);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);
		}
	}

	@Test()
	public void Can_Do_Bulk_Wastage_ByQuantity_As_PPHIS() throws Exception {
		TestcaseID = "223361"; //C223361
		log("Target Environment: "+ Utils.getTargetEnvironment());
		int amountOfQuantityToWaste = 1;
		String reasonForWastage = "CCI: Handling Error";
		log("/*1.----Login as an PPHIS to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();

		log("/*2.----Validate if Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		log("/*-- 3. Close all open tabs --*/");
		supplyConsolePage.closeTabsHCA();
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		log("/*3.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.selectSupplyLocationName(supply_location_from);

		log("/*4.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
		log("/*---     count:" + countSupplyContainers);

		log("/*5.----Click on Container's records Checkboxes --*/");
		if (countSupplyContainers >= 3) {
			int k = 1;
			while (k <= 3) {
				supplyConsolePage.clickOnSupplyContainerCheckbox(k);
				log("/*---     containers record number: " + k);
				Thread.sleep(1000);
				k++;
			}
		} else {
			log("/*--not enough records for Bulk actions--*/");
		}
		int numberOfRows = 3;  //Default COUNT limited to 3 rows as per step5
		//Remaining Doses and Quantity count // 3 rows, ref BulkWastage step5 containers count
		log("/*6.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*7.----Click on bulk Wastage button on Supply page--*/");
		supplyConsolePage.clickBulkWastageButton();
		Thread.sleep(2000);
		log("/*8.----Enter the Quantity values for 3 row and reason for wastage:" +reasonForWastage +"--*/");
		supplyConsolePage.enterBulkWastageByQuantitiesWithReason(amountOfQuantityToWaste, reasonForWastage);

		log("/*9.----Click button Wastage on Container - Wastage page --*/");
		supplyConsolePage.clickWastageButtonContainerWastagePage();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		log("/*10.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(3);

		log("/*11.----Calculating Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfterDeduction = new HashMap<>();
		for (int i = 0; i < remainingDosesAndQuantityBeforeDeduction.size(); i++) {
			ArrayList<Double> writeToList = new ArrayList<>();
			ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeDeduction.get(i);
			double remainingDoses = readFromList.get(0);
			double remainingQuantity = readFromList.get(1);
			double doseConversionFactor = readFromList.get(2);
			//Actual calculation
			double afterDeductionDoses = remainingDoses - (amountOfQuantityToWaste*doseConversionFactor);
			double afterDeductionQuantity = remainingQuantity - amountOfQuantityToWaste;
//            log("Row number " + i + " / Remaining Doses = " + remainingDoses + " / Remaining Quantity = " + remainingQuantity
//                    + " / Dose Conversion Factor = " + doseConversionFactor);
//            log("Row number " + i + " / Remaining Doses after deduction = " + afterDeductionDoses
//                    + " / Remaining Quantity after deduction = " + afterDeductionQuantity + " / Dose Conversion Factor = " + doseConversionFactor);
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
			double calculatedDosesAfterDeduction = Double.valueOf(df.format(calculated.get(0)));
			double calculatedRemainingQuantityAfterDeduction = Double.valueOf(df.format(calculated.get(1)));
			double doseConversionAfterDeduction = Double.valueOf(df.format(calculated.get(2)));

			//Comparing results
			assertEquals(remainingDosesAfterDeduction, calculatedDosesAfterDeduction);
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction, 0.011);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);
		}
	}
}
