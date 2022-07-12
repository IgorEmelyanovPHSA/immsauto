package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BulkAdjustments extends BaseTest {
	
	
	@DataProvider(name = "value")
	public static Object[][] primeNumbers() {
		return new Object[][]{{"50"}, {"-30"}};
	}
	
	@Test(dataProvider = "value")
	public void Can_Do_Bulk_Adjustment_ByDosages_Positive_And_Negative_Value_AS_PPHIS_BCVAXDEVIT(String value) throws InterruptedException {
		TestcaseID = "222370"; //C222370
		AllureLifecycle lifecycle = Allure.getLifecycle();
		double amountOfDosesToAdjust = Double.parseDouble(value);
		boolean isNegativeFlag = isNegative(amountOfDosesToAdjust);
		if (isNegativeFlag == false) {
			log("/*0.----Positive Scenario: Can_Do_Bulk_Adjustment_ByDosages_Positive_Value_AS_PPHIS_BCVAXDEVIT--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Bulk_Adjustment_ByDosages_Positive_Value_AS_PPHIS_BCVAXDEVIT"));
		} else {
			log("/*0.----Negative Scenario: Can_Do_Bulk_Adjustment_ByDosages_Negative_Value_AS_PPHIS_BCVAXDEVIT--*/");
			lifecycle.updateTestCase(testResult -> testResult.setName("Can_Do_Bulk_Adjustment_ByDosages_Negative_Value_AS_PPHIS_BCVAXDEVIT"));
		}
		log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");
		
		log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
		SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
		Thread.sleep(5000);
		
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		Thread.sleep(5000);
		
		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeAutomationLocationTab();
		Thread.sleep(2000);
		
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();
		
		log("/*5.----Click on Automation Supply Location_1 --*/");
		supplyConsolePage.clickOnSupplyLocation_1();
		Thread.sleep(5000);
		
		log("/*6.----Get Supply Containers count outcoming records --*/");
		int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
		log("/*---     count:" + countSupplyContainers);
		
		log("/*7.----Click on Container's records Checkboxes --*/");
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
		int numberOfRows = 3;  //Default COUNT limited to 3 rows as per step7
		//Remaining Doses and Quantity count // 3 rows, ref BulkWastage step7 containers count
		log("/*8.----Read Remaining Doses And Quantity Before Deduction --*/");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);
		
		log("/*9.----Click on bulk Adjustment button on Supply page--*/");
		supplyConsolePage.clickBulkAdjustmentButton();
		Thread.sleep(5000);
		
		log("/*10.----Enter the Dosages values for 3 row and reason for wastage --*/");
		supplyConsolePage.enterBulkAdjustmentByDosageWithReasonForAdjustment(amountOfDosesToAdjust, numberOfRows);
		
		log("/*11.----Click button Adjustment on Container - Adjustment page --*/");
		supplyConsolePage.clickAdjustmentButtonContainerAdjustmentPage();
		Thread.sleep(3000);
		
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
			double afterAdjustmentQuantity = Double.parseDouble(new DecimalFormat("##.####").format(
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
			assertTrue(Double.compare(remainingDosesAfterAdjustment, calculatedDosesAfterAdjustment) == 0, "Values are different!");
			
			log("Compering remaining quantity after adjustment " + remainingQuantityAfterAdjustment + " vs calculated quantity after adjustment " + calculatedRemainingQuantityAfterAdjustment);
			assertTrue(Double.compare(remainingQuantityAfterAdjustment, calculatedRemainingQuantityAfterAdjustment) == 0, "Values are different!");
			
			log("Compering dose conversion factor before adjustment " + doseConversionFactorBeforeAdjustment + " vs dose conversion factor after adjustment " + doseConversionAfterAdjustment);
			assertTrue(Double.compare(doseConversionFactorBeforeAdjustment, doseConversionAfterAdjustment) == 0, "Values are different!");
		}
	}
	
	public static boolean isNegative(double d) {
		return Double.compare(d, 0.0) < 0;
	}
}
