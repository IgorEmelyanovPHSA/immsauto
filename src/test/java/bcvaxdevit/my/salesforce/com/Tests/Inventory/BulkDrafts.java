package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BulkDrafts extends BaseTest {

    @Test
    public void Can_do_Bulk_draft_by_Dosages_form_one_Clinic_to_Another_as_PPHIS_BCVAXDEVIT() throws Exception {
        TestcaseID = "222374"; //C222374
        log("Target Environment: "+ Utils.getTargetEnvironment());
        double amountOfDosesToTransfer = 1; //Hardcoded in bulktransfer method in step 9 need some refactoring in the future
        log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHISWithParameters();
        Thread.sleep(5000);

        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();
        Thread.sleep(2000);

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.clickOnSupplyLocation_1();
        Thread.sleep(3000);

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
        log("/*8.----Read Remaining Doses And Quantity Before transfer --*/");
        HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityBeforeTransfer = supplyConsolePage.countDosesAndQuantityMap(numberOfRows);

        log("/*8.----Click on bulk Transfer button --*/");
        supplyConsolePage.clickBulkTransfersButton();
        Thread.sleep(5000);

        log("/*9.----Enter the Dosages values for 3 row Transfers --*/");
        int k = 3;
        while (k <= 7) {
            supplyConsolePage.enterBulkTransferByDosages(k);
            int n = k - 2;
            log("/*---     dose slot N%: " + n);
            Thread.sleep(1000);
            k = k + 2;
        }

        log("/*10.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePage.selectSupplyLocation_2_To();
        Thread.sleep(1000);

        log("/*11.----click Save as draft dialog Modal button --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
        Thread.sleep(2000);

        log("/*12.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();
        Thread.sleep(2000);

        log("/*13.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        for(int i=countDraftTransactions; i > (countDraftTransactions-numberOfRows); i--) {
            String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(i);
            log("/*----Getting id for the latest created Transaction Draft " + latestDraftTransactionId + " --*/");
        }

        log("/*14----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftBulkTransactionsAndConfirmTransfer(countDraftTransactions, numberOfRows);
        Thread.sleep(3000);

        log("/*15----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        for(int i=countOutgoingTransactions; i > (countOutgoingTransactions-numberOfRows); i--) {
            String latestDraftTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(i);
            log("/*----Getting id for the latest created Outgoing Transaction " + latestDraftTransactionId + " --*/");
        }

        log("/*15.1----Click on the latest created Outgoing Transactions --*/");
        supplyConsolePage.clickOnOutgoingTransactions(countOutgoingTransactions);
        log("/*--transactions record number --*/:" + countOutgoingTransactions);
        Thread.sleep(5000);

        log("/*16.----Close All Tab's --*/");
        supplyConsolePage.closeTabsHCA();
        Thread.sleep(2000);

        log("/*17.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        Thread.sleep(2000);

        log("/*18.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation_2();
        Thread.sleep(2000);

        log("/*19.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(2000);

        log("/*20.----Get how many Incoming Transactions 'To' count records --*/");
        int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
        Thread.sleep(2000);

        log("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
        Thread.sleep(2000);

        log("/*21.----Click on Checkboxes Incoming Transactions --*/");
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

        log("/*22----click Confirm Incoming button Transfer --*/");
        supplyConsolePage.clickBulkConfirmIncomingTransfersButton();
        Thread.sleep(2000);

        log("/*23.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
        supplyConsolePage.selectIncomingSupplyDistribution();
        Thread.sleep(2000);

        log("/*24.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
        supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
        Thread.sleep(1000);

        log("/*25.----Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        supplyConsolePage.successMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*26.----Close Automation_Supply_Location_2 Tab --*/");
        supplyConsolePage.closeTabsHCA();
        Thread.sleep(2000);

        log("/*27.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.clickOnSupplyLocation_1();
        Thread.sleep(3000);

        log("/*28.----Read Remaining Doses And Quantity After transfer is completed in Location_1--*/");
        HashMap<Integer, ArrayList<Double>> actualRemainingDosesAndQuantityAfterTransfer = supplyConsolePage.countDosesAndQuantityMap(3);

        log("/*29.----Calculating Remaining Doses And Quantity After Transfer --*/");
        HashMap<Integer, ArrayList<Double>> calculatedRemainingDosesAndQuantityAfter = new HashMap<>();
        for (int i = 0; i < remainingDosesAndQuantityBeforeTransfer.size(); i++) {
            ArrayList<Double> writeToList = new ArrayList<>();
            ArrayList<Double> readFromList = remainingDosesAndQuantityBeforeTransfer.get(i);
            double remainingDoses = readFromList.get(0);
            double remainingQuantity = readFromList.get(1);
            double doseConversionFactor = readFromList.get(2);
            //Actual calculation
            double afterAdjustmentDoses = remainingDoses - amountOfDosesToTransfer;
            double afterAdjustmentQuantity = Double.parseDouble(new DecimalFormat("##.####").format(
                    remainingQuantity - (amountOfDosesToTransfer / doseConversionFactor)));
            writeToList.add(afterAdjustmentDoses);
            writeToList.add(afterAdjustmentQuantity);
            writeToList.add(doseConversionFactor);
            calculatedRemainingDosesAndQuantityAfter.put(i, writeToList);
        }

        log("/*30.----Compering Remaining Doses and Quantity actual vs calculated--*/");
        //Comparing 2 objects actualRemainingDosesAndQuantityAfterTransfer vs calculatedRemainingDosesAndQuantityAfterTransfer
        for (int i = 0; i < actualRemainingDosesAndQuantityAfterTransfer.size(); i++) {
            ArrayList<Double> afterDeduction = actualRemainingDosesAndQuantityAfterTransfer.get(i);
            double remainingDosesAfter = afterDeduction.get(0);
            double remainingQuantityAfterTransfer = afterDeduction.get(1);
            double doseConversionFactorBeforeTransfer = afterDeduction.get(2);
            ArrayList<Double> calculated = calculatedRemainingDosesAndQuantityAfter.get(i);
            double calculatedDosesAfterTransfer = calculated.get(0);
            double calculatedRemainingQuantityAfterTransfer = calculated.get(1);
            double doseConversionAfterTransfer = calculated.get(2);

            //Comparing results
            log("Compering remaining doses after transfer " + remainingDosesAfter + " vs calculated doses after transfer " + calculatedDosesAfterTransfer);
            assertEquals(remainingDosesAfter, calculatedDosesAfterTransfer);
           //assertTrue(Double.compare(remainingDosesAfter, calculatedDosesAfterTransfer) == 0, "Values are different!");

            log("Compering remaining quantity after transfer " + remainingQuantityAfterTransfer + " vs calculated quantity after transfer " + calculatedRemainingQuantityAfterTransfer);
            assertEquals(remainingQuantityAfterTransfer, calculatedRemainingQuantityAfterTransfer);
            //assertTrue(Double.compare(remainingQuantityAfterTransfer, calculatedRemainingQuantityAfterTransfer) == 0, "Values are different!");

            log("Compering dose conversion factor before transfer " + doseConversionFactorBeforeTransfer + " vs dose conversion factor after transfer " + doseConversionAfterTransfer);
            assertEquals(doseConversionFactorBeforeTransfer, doseConversionAfterTransfer);
            //assertTrue(Double.compare(doseConversionFactorBeforeTransfer, doseConversionAfterTransfer) == 0, "Values are different!");
        }
    }

}