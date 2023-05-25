package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.MainPageCP;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static constansts.Domain.SUPPLY_LOCATION_1;
import static constansts.Domain.SUPPLY_LOCATION_2;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class BulkDraftsCP extends BaseTest {

    private final String supplyLocationFrom = SUPPLY_LOCATION_1;
    private final String supplyLocationTo = SUPPLY_LOCATION_2;

    @Test
    public void CP_Can_do_Bulk_draft_by_Dosages_form_one_Clinic_to_Another() throws Exception {
        //TestcaseID = "222374"; //C222374
        log("Target Environment: "+ Utils.getTargetEnvironment());
        MainPageCP cpMainPage = new MainPageCP(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        double amountOfDosesToTransfer = 1; //Hardcoded in bulktransfer method in step 7 need some refactoring in the future

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login AS comunityqa_immsbc_admin");
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login AS default user (ClinicianInventory)");
                TestcaseID = "245220"; //C245220
                loginPage.loginIntoCommunityPortalAsClinicianInventory();
        }

        log("/*2.----Navigate to Supply Console Page --*/");
        cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        log("/*---     count:" + countSupplyContainers);

        log("/*4.----Click on Container's records Checkboxes --*/");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
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
        log("/*5.----Read Remaining Doses And Quantity Before transfer --*/");
        HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeTransfer = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

        log("/*6.----Click on bulk Transfer button --*/");
        supplyConsolePage.clickBulkTransfersButton();

        log("/*7.----Enter the Dosages values for 3 row Transfers --*/");
        int k = 2;
        while (k <= 7) {
            supplyConsolePage.enterBulkTransferByDosages(k);
            Thread.sleep(1000);
            k = k + 2;
        }

        log("/*8.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePage.selectSupplyLocation(supplyLocationTo);

        log("/*9.----click Save as draft dialog Modal button --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();

        log("/*10.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();

        log("/*11.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();

        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        for(int i=countDraftTransactions; i > (countDraftTransactions-numberOfRows); i--) {
            String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(i);
            log("/*----Getting id for the latest created Transaction Draft " + latestDraftTransactionId + " --*/");
        }

        log("/*12----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftBulkTransactionsAndConfirmTransfer(countDraftTransactions, numberOfRows);

        log("/*13----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
//        for(int i=countOutgoingTransactions; i > (countOutgoingTransactions-numberOfRows); i--) {
//            String latestDraftTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(i);
//            log("/*----Getting id for the latest created Outgoing Transaction " + latestDraftTransactionId + " --*/");
//        }

        log("/*14----Click on the latest created Outgoing Transactions --*/");
        supplyConsolePage.clickOnOutgoingTransactions(countOutgoingTransactions);
        log("/*--transactions record number --*/:" + countOutgoingTransactions);
        Thread.sleep(2000);
        log("/*15.----Go to Supply Locations Tab --*/");
        cpMainPage.selectSupplyLocationName(supplyLocationTo);

        log("/*16.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        supplyConsolePage.clickTransactionsTab();

        log("/*17.----Get how many Incoming Transactions 'To' count records --*/");
        int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();

        log("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);

        log("/*18.----Click on Checkboxes Incoming Transactions --*/");
        if (countIncomingTransactions >= 3) {
            int j = countIncomingTransactions;
            int i = 1;
            while (i <= 3) {
                supplyConsolePage.clickOnIncomingTransactionsCheckbox(j);
                log("/*---     incoming transaction record number: " + j);
                j = --j;
                Thread.sleep(1000);
                i++;
            }
        } else {
            log("/*--not all 3 Incoming Transaction records are there--*/");
        }

        log("/*19----click Confirm Incoming button Transfer --*/");
        supplyConsolePage.clickBulkConfirmIncomingTransfersButton();

        log("/*20.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
        supplyConsolePage.selectIncomingSupplyDistribution();

        log("/*21.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
        supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();

        log("/*22.----Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        supplyConsolePage.successMessageAppear();

        log("/*23.----Click on Automation Supply Location_1 --*/");
        cpMainPage.selectSupplyLocationName(supplyLocationFrom);
        Thread.sleep(2000);

        log("/*24.----Read Remaining Doses And Quantity After transfer is completed in Location_1--*/");
        HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterTransfer = supplyConsolePage.countDosesAndQuantityMap(3);

        log("/*25.----Calculating Remaining Doses And Quantity After Transfer --*/");
        HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfter = new HashMap<>();
        for (int i = 0; i < remainingDosesAndQuantityBeforeTransfer.size(); i++) {
            ArrayList<Double> writeToList = new ArrayList<>();
            ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeTransfer.get(i);
            double remainingDoses = readFromList.get(0);
            double remainingQuantity = readFromList.get(1);
            double doseConversionFactor = readFromList.get(2);
            //Actual calculation
            double afterAdjustmentDoses = remainingDoses - amountOfDosesToTransfer;
            double afterAdjustmentQuantity = Double.parseDouble(df.format(
                    remainingQuantity - (amountOfDosesToTransfer / doseConversionFactor)));
            writeToList.add(afterAdjustmentDoses);
            writeToList.add(afterAdjustmentQuantity);
            writeToList.add(doseConversionFactor);
            calculatedRemainingDosesAndQuantityAfter.put(i, writeToList);
        }

        log("/*26.----Compering Remaining Doses and Quantity actual vs calculated--*/");
        //Comparing 2 objects actualRemainingDosesAndQuantityAfterTransfer vs calculatedRemainingDosesAndQuantityAfterTransfer
        for (int i = 0; i < actualRemainingDosesAndQuantityAfterTransfer.size(); i++) {
            ArrayList<Double> afterDeduction = actualRemainingDosesAndQuantityAfterTransfer.get(i);
            double remainingDosesAfter = afterDeduction.get(0);
            double remainingQuantityAfterTransfer = afterDeduction.get(1);
            double doseConversionFactorBeforeTransfer = afterDeduction.get(2);
            ArrayList<Double> calculated = calculatedRemainingDosesAndQuantityAfter.get(i);
            double calculatedDosesAfterTransfer = Double.parseDouble(df.format(calculated.get(0)));
            double calculatedRemainingQuantityAfterTransfer = Double.parseDouble(df.format(calculated.get(1)));
            double doseConversionAfterTransfer = calculated.get(2);

            //Comparing results
            log("Compering remaining doses after transfer " + remainingDosesAfter + " vs calculated doses after transfer " + calculatedDosesAfterTransfer);
            assertEquals(remainingDosesAfter, calculatedDosesAfterTransfer);

            log("Compering remaining quantity after transfer " + remainingQuantityAfterTransfer + " vs calculated quantity after transfer " + calculatedRemainingQuantityAfterTransfer);
            assertEquals(remainingQuantityAfterTransfer, calculatedRemainingQuantityAfterTransfer, 0.01);

            log("Compering dose conversion factor before transfer " + doseConversionFactorBeforeTransfer + " vs dose conversion factor after transfer " + doseConversionAfterTransfer);
            assertEquals(doseConversionFactorBeforeTransfer, doseConversionAfterTransfer);
        }
    }

}