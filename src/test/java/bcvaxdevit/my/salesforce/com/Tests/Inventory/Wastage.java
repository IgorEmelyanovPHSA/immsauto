package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class Wastage extends BaseTest {

    @Story("C222357: Inventory Management - Wastage(Java)")
    @Test(groups = {"Smoke"})
    public void Can_Do_Single_Wastage_ByDosages_AS_PPHIS_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "222357"; //222357
        int numberOfRows = 1; //Default value, wasting from first row only
        double amountOfDosesToWaste = 3;
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

        log("/*6.----Read Remaining Doses And Quantity Before Deduction --*/");
        HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

        log("/*7.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*8.----select Wastage from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectWastageFromDropDown();
        double remainingDosesBeforeWastage = supplyConsolePage.getActualRemainingDoses();
        double doseConversionFactorRead = supplyConsolePage.getDoseConversionFactor();
        log("/*----Remaining Doses Before Wastage " +remainingDosesBeforeWastage +" --*/");
        log("/*----Dose Conversion Factor " +doseConversionFactorRead +" --*/");
        log("/*----Amount Wastage Doses " +amountOfDosesToWaste +" --*/");

        log("/*9.----set Wastage Doses amount --*/");
        supplyConsolePage.setDosesAmount(Double.toString(amountOfDosesToWaste));
        double remainingDosesAfterWastage = supplyConsolePage.getActualRemainingDoses();
        log("/*----Quantity Remaining Doses After Wastage " +remainingDosesAfterWastage +" --*/");

        log("/*10.----Reason For Wastage: 'CCI: Equipment Malfunction' --*/");
        supplyConsolePage.selectReasonForWastageDropDown();

        log("/*11----Clicking on btn Wastage --*/");
        supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();

        //Verification values in Container - Wastage pop-up
        assertEquals((remainingDosesBeforeWastage-amountOfDosesToWaste), remainingDosesAfterWastage);

        log("/*12.----Read Remaining Doses And Quantity After Deduction --*/");
        HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterDeduction = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

        log("/*13.----Calculating Remaining Doses And Quantity After Deduction --*/");
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

        log("/*14.----Compering Remaining Doses and Quantity actual vs calculated--*/");
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
            assertTrue(Double.compare(remainingDosesAfterDeduction, calculatedDosesAfterDeduction) == 0, "Values are different!");
            assertTrue(Double.compare(remainingQuantityAfterDeduction, calculatedRemainingQuantityAfterDeduction) == 0, "Values are different!");
            assertTrue(Double.compare(doseConversionFactorBeforeDeduction, doseConversionAfterDeduction) == 0, "Values are different!");
            assertTrue(Double.compare(doseConversionFactorRead, doseConversionAfterDeduction) == 0, "Values are different!"); //Actual read UI value
        }

        log("/*.----Extra steps to check the actual amount of doses wastage on Container - Wastage page --*/");
        log("/*15.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();

        log("/*16.----select Wastage from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectWastageFromDropDown();

        double actualDosesAmount = supplyConsolePage.getActualRemainingDoses();
        log("/*----Acutla Quantity Doses " +actualDosesAmount +" --*/");
        assertEquals(actualDosesAmount, remainingDosesAfterWastage);
    }
}