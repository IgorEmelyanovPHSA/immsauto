package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.MainPageCP;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class BulkAdjustmentsCP extends BaseTest {

    @DataProvider(name = "dosesAmount")
    public static Object[][] primeNumbers() {
        return new Object[][]{{"50"}, {"-30"}};
    }

    @DataProvider(name = "quantitiesAmount")
    public static Object[][] primeNumbers2() {
        return new Object[][]{{"3"},{"-2"}};
    }

    @Test(dataProvider = "dosesAmount")
    public void CP_Can_Do_Bulk_Adjustment_ByDosages_Positive_And_Negative_Value_AS_Clinician(String value) throws Exception {
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


        log("/*1.----Login as an PPHIS--*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianInventory();
        Thread.sleep(10000);

        log("/*2.----Navigate to Supply Console Page --*/");
        SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        log("/*---     count:" + countSupplyContainers);

        log("/*4.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
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
        //Remaining Doses and Quantity count // 3 rows, ref step7 containers count
        log("/*5.----Read Remaining Doses And Quantity Before Deduction --*/");
        HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

        log("/*6.----Click on bulk Adjustment button on Supply page--*/");
        supplyConsolePage.clickBulkAdjustmentButton();
        Thread.sleep(5000);

        log("/*7.----Enter the Dosages values for 3 row and reason for Adjustment: " +reasonForAdjustment +"--*/");
        //supplyConsolePage.enterBulkByDosageWithReason(amountOfDosesToAdjust,reasonForAdjustment, numberOfRows);
        supplyConsolePage.enterBulkByDosageWithReasonCP(amountOfDosesToAdjust, reasonForAdjustment, numberOfRows);

        log("/*8.----Click button Adjustment on Container - Adjustment page --*/");
        supplyConsolePage.clickAdjustmentButtonContainerAdjustmentPage();
        Thread.sleep(3000);

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
        }
    }

    @Test(dataProvider = "quantitiesAmount")
    public void Can_Do_Bulk_Adjustment_ByQuantities_Positive_And_Negative_Value_AS_Clinician(String quantity) throws Exception {
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

        log("/*1.----Login as an PPHIS--*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianInventory();
        Thread.sleep(10000);

        log("/*2.----Navigate to Supply Console Page --*/");
        SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        log("/*---     count:" + countSupplyContainers);

        log("/*4.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
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
        HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeAdjustment = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

        log("/*6.----Click on bulk Adjustment button on Supply page--*/");
        supplyConsolePage.clickBulkAdjustmentButton();
        Thread.sleep(5000);

        log("/*7.----Enter the Quantities values for 3 rows and reason for adjustment: " +reasonForAdjustment +"--*/");
        supplyConsolePage.enterBulkByQuantitiesWithReasonCP(amountOfQuantityToAdjust, reasonForAdjustment, numberOfRows);

        log("/*8.----Click button Adjustment on Container - Adjustment page --*/");
        supplyConsolePage.clickAdjustmentButtonContainerAdjustmentPage();
        Thread.sleep(3000);

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
            double afterAdjustmentDoses =  remainingDoses + (amountOfQuantityToAdjust * doseConversionFactor);
            double afterAdjustmentQuantity = remainingQuantity + amountOfQuantityToAdjust;
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
        }
    }

    public static boolean isNegative(double d) {
        return Double.compare(d, 0.0) < 0;
    }
}
