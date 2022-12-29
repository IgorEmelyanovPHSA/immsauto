package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.CommunityPortalMainPage;
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
public class BulkWastagesCP extends BaseTest {

	//Login as an admin for now, needs to be updated to PPHIS
	//Needs to update TestcaseId for both CP tests
	@Test()
	public void CP_Can_Do_Bulk_Wastage_By_Dosages_As_PPHIS() throws Exception {
		TestcaseID = "223361"; //C223361
		log("Target Environment: "+ Utils.getTargetEnvironment());
		int amountOfDosesToWaste = 1;
		String reasonForWastage = "CCI: Handling Error";

		log("/*1.----Login as an PPHIS--*/");
		CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		Thread.sleep(10000);

		log("/*2.----Navigate to Supply Console Page --*/");
		SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyConsolePage();
		
		log("/*3.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
		log("/*---     count:" + countSupplyContainers);
		
		log("/*4.----Click on Container's records Checkboxes --*/");
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
		log("/*5.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
		log("/*6.----Click on bulk Wastage button on Supply page--*/");
		supplyConsolePage.clickBulkWastageButton();
		Thread.sleep(5000);
		
		log("/*7.----Enter the Dosages values for 3 row and reason for wastage: " +reasonForWastage +"--*/");
		supplyConsolePage.enterBulkByDosageWithReasonCP(amountOfDosesToWaste, reasonForWastage, numberOfRows);
		
		log("/*8.----Click button Wastage on Container - Wastage page --*/");
		supplyConsolePage.clickWastageButtonContainerWastagePage();
		Thread.sleep(3000);
		
		log("/*9.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(3);
		
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
//            log("Row number " + i + " / Remaining Doses = " + remainingDoses + " / Remaining Quantity = " + remainingQuantity
//                    + " / Dose Conversion Factor = " + doseConversionFactor);
//            log("Row number " + i + " / Remaining Doses after deduction = " + afterDeductionDoses
//                    + " / Remaining Quantity after deduction = " + afterDeductionQuantity + " / Dose Conversion Factor = " + doseConversionFactor);
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
			assertEquals(remainingDosesAfterDeduction, calculatedDosesAfterDeduction);
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);
		}
	}

	@Test()
	public void Can_Do_Bulk_Wastage_ByQuantity_As_PPHIS() throws Exception {
		TestcaseID = "223361"; //C223361
		log("Target Environment: "+ Utils.getTargetEnvironment());
		int amountOfQuantityToWaste = 1;
		String reasonForWastage = "CCI: Handling Error";
		log("/*1.----Login as an PPHIS--*/");
		CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
		Thread.sleep(10000);

		log("/*2.----Navigate to Supply Console Page --*/");
		SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyConsolePage();

		log("/*3.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
		log("/*---     count:" + countSupplyContainers);

		log("/*4.----Click on Container's records Checkboxes --*/");
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
		log("/*5.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

		log("/*6.----Click on bulk Wastage button on Supply page--*/");
		supplyConsolePage.clickBulkWastageButton();
		Thread.sleep(5000);

		log("/*7.----Enter the Quantity values for 3 row and reason for wastage:" +reasonForWastage +"--*/");
		supplyConsolePage.enterBulkByQuantitiesWithReasonCP(amountOfQuantityToWaste, reasonForWastage, numberOfRows);

		log("/*8.----Click button Wastage on Container - Wastage page --*/");
		supplyConsolePage.clickWastageButtonContainerWastagePage();
		Thread.sleep(3000);

		log("/*9.----Read Remaining Doses And Quantity After Deduction --*/");
		HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(3);

		log("/*10.----Calculating Remaining Doses And Quantity After Deduction --*/");
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
			assertEquals(remainingDosesAfterDeduction, calculatedDosesAfterDeduction);
			assertEquals(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction);
			assertEquals(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction);
		}
	}
}