package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.MainPageOrg;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import constansts.Apps;
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
public class BulkDrafts extends BaseTest {
    String supply_location_from;
    MainPageOrg orgMainPage;
    String env;
    Map<String, Object> testData;
    String supply_location_to;

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
    }

    @Test
    public void Can_do_Bulk_draft_by_Dosages_form_one_Clinic_to_Another_as_PPHIS() throws Exception {
        TestcaseID = "222374"; //C222374
        log("Target Environment: "+ Utils.getTargetEnvironment());
        double amountOfDosesToTransfer = 1; //Hardcoded in bulktransfer method in step 9 need some refactoring in the future
        log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_from);

        log("/*6.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        log("/*---     count:" + countSupplyContainers);

        log("/*7.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            int k = 1;
            while (k <= 3) {
                supplyConsolePage.clickOnSupplyContainerCheckbox(k);
                log("/*---     containers record number: " + k);
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

        log("/*9.----Enter the Dosages values for 3 row Transfers --*/");
        int k = 3;
        while (k <= 7) {
            supplyConsolePage.enterBulkTransferByDosages(k);
            int n = k - 2;
            log("/*---     dose slot N%: " + n);
            k = k + 2;
        }

        log("/*10.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);

        log("/*11.----click Save as draft dialog Modal button --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();

        //Need some wait time otherwise the java script error will be thrown
        Thread.sleep(2000);
        log("/*12.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();

        log("/*13.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();

        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        for(int i=countDraftTransactions; i > (countDraftTransactions-numberOfRows); i--) {
            String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(i);
            log("/*----Getting id for the latest created Transaction Draft " + latestDraftTransactionId + " --*/");
        }

        log("/*14----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftBulkTransactionsAndConfirmTransfer(countDraftTransactions, numberOfRows);

        log("/*15----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        for(int i=countOutgoingTransactions; i > (countOutgoingTransactions-numberOfRows); i--) {
            String latestDraftTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(i);
            log("/*----Getting id for the latest created Outgoing Transaction " + latestDraftTransactionId + " --*/");
        }

        log("/*15.1----Click on the latest created Outgoing Transactions --*/");
        supplyConsolePage.clickOnOutgoingTransactions(countOutgoingTransactions);
        log("/*--transactions record number --*/:" + countOutgoingTransactions);

        log("/*16.----Close All Tab's --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*17.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*18.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_to);

        log("/*19.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        supplyConsolePage.clickTransactionsTab();

        log("/*20.----Get how many Incoming Transactions 'To' count records --*/");
        int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();

        log("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);

        log("/*21.----Click on Checkboxes Incoming Transactions --*/");
        if (countIncomingTransactions >= 3) {
            int j = countIncomingTransactions;
            int i = 1;
            while (i <= 3) {
                supplyConsolePage.clickOnIncomingTransactionsCheckbox(j);
                log("/*---     incoming transaction record number: " + j);
                j = --j;
                i++;
            }
        } else {
            log("/*--not all 3 Incoming Transaction records are there--*/");
        }

        log("/*22----click Confirm Incoming button Transfer --*/");
        supplyConsolePage.clickBulkConfirmIncomingTransfersButton();

        log("/*23.----select incoming Supply Distribution for Automation Supply Location_2  --*/");
        supplyConsolePage.selectIncomingSupplyDistribution();

        log("/*24.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
        supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();

        log("/*25.----Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        supplyConsolePage.successMessageAppear();

        log("/*26.----Close Automation_Supply_Location_2 Tab --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*27.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_from);

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
            double afterAdjustmentQuantity = Double.parseDouble(df.format(
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
            double calculatedDosesAfterTransfer = Double.valueOf(df.format(calculated.get(0)));
            double calculatedRemainingQuantityAfterTransfer = Double.valueOf(df.format(calculated.get(1)));
            double doseConversionAfterTransfer = Double.valueOf(df.format(calculated.get(2)));

            //Comparing results
            log("Compering remaining doses after transfer " + remainingDosesAfter + " vs calculated doses after transfer " + calculatedDosesAfterTransfer);
            assertEquals(remainingDosesAfter, calculatedDosesAfterTransfer);
           //assertTrue(Double.compare(remainingDosesAfter, calculatedDosesAfterTransfer) == 0, "Values are different!");

            log("Compering remaining quantity after transfer " + remainingQuantityAfterTransfer + " vs calculated quantity after transfer " + calculatedRemainingQuantityAfterTransfer);
            assertEquals(remainingQuantityAfterTransfer, calculatedRemainingQuantityAfterTransfer, 0.011);
            //assertTrue(Double.compare(remainingQuantityAfterTransfer, calculatedRemainingQuantityAfterTransfer) == 0, "Values are different!");

            log("Compering dose conversion factor before transfer " + doseConversionFactorBeforeTransfer + " vs dose conversion factor after transfer " + doseConversionAfterTransfer);
            assertEquals(doseConversionFactorBeforeTransfer, doseConversionAfterTransfer);
            //assertTrue(Double.compare(doseConversionFactorBeforeTransfer, doseConversionAfterTransfer) == 0, "Values are different!");
        }
    }

}