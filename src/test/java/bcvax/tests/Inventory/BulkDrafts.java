package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    String supply_distribution_from;
    String supply_distribution_to;
    String supply_distribution_to_same_clinic;

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
        supply_distribution_from = String.valueOf(testData.get("distributionFrom"));
        supply_distribution_to = String.valueOf(testData.get("distributionTo"));
        supply_distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));
    }

    @Test
    public void Can_do_Bulk_draft_by_Dosages_form_one_Clinic_to_Another_as_PPHIS() throws Exception {
        TestcaseID = "222374"; //C222374
        log("Target Environment: "+ Utils.getTargetEnvironment());
        double amountOfDosesToTransfer = 1; //Hardcoded in bulktransfer method in step 9 need some refactoring in the future
        log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }

        log("/*3.----Close All previously opened Tab's --*/");
        SupplyConsolePage.closeTabsHCA(driver);

        log("/*4.----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);

        log("/*5.----Click on Automation Supply Location_1 --*/");
        SupplyConsolePage.selectSupplyLocationName(driver, supply_location_from);

        log("/*6.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
        log("/*---     count:" + countSupplyContainers);

        Map<String, Map<String, String>> my_containers = new HashMap<>();
        log("/*4.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            for (int k = 1; k <= 3; k++) {
                Map<String, Map<String, String>> my_container_data = SupplyLocationRelatedItems.checkSupplyContainer(driver, k);
                my_containers.put(my_container_data.keySet().toArray()[0].toString(), my_container_data.get(my_container_data.keySet().toArray()[0].toString()));
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
        for(String my_container: my_containers.keySet()) {
            ContainerTransferPage.enterTransferDose(driver, my_container, "1");
            //supplyConsolePage.enterBulkTransferByDosages(k);
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
        SupplyLocationPage.clickTransactionsTab(driver);

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
        SupplyConsolePage.closeTabsHCA(driver);

        log("/*17.----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);

        log("/*18.----Click on Automation Supply Location_2 --*/");
        SupplyConsolePage.selectSupplyLocationName(driver, supply_location_to);

        log("/*19.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        SupplyLocationPage.clickTransactionsTab(driver);

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
        supplyConsolePage.selectIncomingSupplyDistribution(supply_distribution_to);

        log("/*24.----click on Confirm Incoming Transfer Modal Bulk in the screen --*/");
        supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();

        log("/*25.----Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
        Assert.assertTrue(all_alerts.get(0).contains("You have successfully Confirmed the Transaction"));

        log("/*26.----Close Automation_Supply_Location_2 Tab --*/");
        SupplyConsolePage.closeTabsHCA(driver);

        log("/*27.----Click on Automation Supply Location_1 --*/");
        SupplyConsolePage.selectSupplyLocationName(driver, supply_location_from);

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