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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class BulkTransfersCancellation extends BaseTest {

    MainPageOrg orgMainPage;
    SupplyConsolePage supplyConsolePage;
    String env;
    Map<String, Object> testData;
    String supply_location_from;
    String supply_location_to;
    String distribution_from;
    String distribution_to;
    String distribution_to_same_clinic;
    List<String> containers_from;
    List<String> containers_to;
    List<String> containers_to_same_clinic;

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
        distribution_from = String.valueOf(testData.get("distributionFrom"));
        distribution_to = String.valueOf(testData.get("distributionTo"));
        distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));
        containers_from = (ArrayList)testData.get("bulkContainersFrom");
        containers_to = (ArrayList)testData.get("bulkContainersTo");
        containers_to_same_clinic = (ArrayList)testData.get("bulkContainersToSameClinic");
    }

    @Test(priority = 1)
    public void Can_doBulk_transfers_Cancellation_By_Doses_form_one_Clinic_to_Another() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244849" : "223359";
        precondition();
        double doses = 10;

        log("/----Count Remaining Supplies --*/");
        double remainingDosesBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
        double remainingQtyBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
        double remainingDosesBeforeDistribution1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
        double remainingQtyBeforeDistribution1_2 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);
        double remainingDosesBeforeDistribution1_3 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);
        double remainingQtyBeforeDistribution1_3 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);

        log("/----Select Items to Transfer and Submit --*/");
        log("/*7.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        log("/*---     count:" + countSupplyContainers);
        log("/*8.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            for(String container : containers_from) {
                supplyConsolePage.clickOnSupplyContainerCheckbox(container, distribution_from);
                log("/*---     containers: " + container);
                Thread.sleep(1000);
            }
        } else {
            log("/*--not enough records for Bulk actions--*/");
        }
        log("/*9.----Click on bulk Transfer button --*/");
        supplyConsolePage.clickBulkTransfersButton();

        log("/*10.----Enter the Dosages values (1 dose) for 3 row Transfers --*/");

        supplyConsolePage.enterBulkTransferByDosages(containers_from, doses);

        log("/*11.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
        log("/*12.----click Transfer dialog Modal button --*/");
        supplyConsolePage.clickBulkTransfersModalButton();
        log("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();

        /////////////////////Doses and Quantity AFTER Automation Location_1//////////////////////////////////
        log("/*14.----Getting Remaining Doses/Quantity - AFTER - Automation Location_1 --*/");
        double remainingDosesAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
        double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - doses;
        assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);

        double remainingDosesAfterDistribution1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
        double remainingDosesAfterCalculationDistribution1_2 = remainingDosesBeforeDistribution1_2 - doses;
        assertEquals(remainingDosesAfterDistribution1_2, remainingDosesAfterCalculationDistribution1_2);

        double remainingDosesAfterDistribution1_3 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);
        double remainingDosesAfterCalculationDistribution1_3 = remainingDosesBeforeDistribution1_3 - doses;
        assertEquals(remainingDosesAfterDistribution1_3, remainingDosesAfterCalculationDistribution1_3);

        log("/*19.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation(supply_location_to);
        supplyConsolePage.refreshBrowser();
        Thread.sleep(2000);
        log("/----Count Remaining Supplies Before Transaction --*/");
        System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        double remainingDosesBeforeLocationDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(0), distribution_to);
        double remainingDosesBeforeLocationDistribution2_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(1), distribution_to);
        double remainingDosesBeforeLocationDistribution2_3 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(2), distribution_to);

        log("/----Go to Supply Location Related Tab where Transferring From --*/");

        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation(supply_location_from);

        supplyConsolePage.refreshBrowser();
        supplyConsolePage.clickTransactionsTab();

        log("/*23----Get how many Outgoing Transactions 'To' count records --*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        log("/*---  Outgoing transactions 'to' count:" + countOutgoingTransactions);
        log("/*24.----Click on Checkboxes Outgoing Transactions --*/");
        if (countOutgoingTransactions >= 3) {
            for (int i = 0; i < containers_from.size(); i++) {
                supplyConsolePage.clickOnOutgoingTransactionsCheckbox(countOutgoingTransactions - i);
            }
        } else {
            log("/*--not all 3 Incoming Transaction records are there--*/");
        }
        supplyConsolePage.clickBulkCancelButton().cancelBulkTransfer();

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        supplyConsolePage.clickOnRelatedItemTab();
        double remainingDosesAfterLocationDistribution1_1 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(0), distribution_from);
        double remainingDosesAfterLocationDistribution1_2 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(1), distribution_from);
        double remainingDosesAfterLocationDistribution1_3 = supplyConsolePage.getValueOfRemainingDoses(containers_from.get(2), distribution_from);

        assertEquals(remainingDosesAfterLocationDistribution1_1, remainingDosesBeforeDistribution1_1);
        assertEquals(remainingDosesAfterLocationDistribution1_2, remainingDosesBeforeDistribution1_2);
        assertEquals(remainingDosesAfterLocationDistribution1_3, remainingDosesBeforeDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");

        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation(supply_location_to);
        supplyConsolePage.refreshBrowser();
        Thread.sleep(2000);
        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        double remainingDosesAfterLocationDistribution2_1 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(0), distribution_to);
        double remainingDosesAfterLocationDistribution2_2 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(1), distribution_to);
        double remainingDosesAfterLocationDistribution2_3 = supplyConsolePage.getValueOfRemainingDoses(containers_to.get(2), distribution_to);

        assertEquals(remainingDosesAfterLocationDistribution2_1, remainingDosesBeforeLocationDistribution2_1);
        assertEquals(remainingDosesAfterLocationDistribution2_2, remainingDosesAfterLocationDistribution2_2);
        assertEquals(remainingDosesAfterLocationDistribution2_3, remainingDosesBeforeLocationDistribution2_3);
    }

    @Test(priority = 2)
    public void Can_doBulk_transfers_Cancellation_By_Quantity_form_one_Clinic_to_Another() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244849" : "223359";
        precondition();
        double quantity = 1;

        log("/----Count Remaining Supplies --*/");
        double remainingQtyBeforeDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
        double remainingQtyBeforeDistribution1_2 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);
        double remainingQtyBeforeDistribution1_3 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);

        log("/----Select Items to Transfer and Submit --*/");
        log("/*7.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = supplyConsolePage.getRowsSupplyContainersFromCount();
        log("/*---     count:" + countSupplyContainers);
        log("/*8.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            for(String container : containers_from) {
                supplyConsolePage.clickOnSupplyContainerCheckbox(container, distribution_from);
                log("/*---     containers: " + container);
            }
        } else {
            log("/*--not enough records for Bulk actions--*/");
        }
        log("/*9.----Click on bulk Transfer button --*/");
        supplyConsolePage.clickBulkTransfersButton();

        log("/*10.----Enter the Quantity values (1 quantity) for 3 row Transfers --*/");

        supplyConsolePage.enterBulkTransferByQuantity(containers_from, quantity);
        log("/*11.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
        log("/*12.----click Transfer dialog Modal button --*/");
        supplyConsolePage.clickBulkTransfersModalButton();
        log("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();

        /////////////////////Doses and Quantity AFTER Automation Location_1//////////////////////////////////
        log("/*14.----Getting Remaining Doses/Quantity - AFTER - Automation Location_1 --*/");
        double remainingQtyAfterDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
        double remainingQtyAfterCalculationDistribution1_1 = remainingQtyBeforeDistribution1_1 - quantity;
        assertEquals(remainingQtyAfterDistribution1_1, remainingQtyAfterCalculationDistribution1_1);

        double remainingQtyAfterDistribution1_2 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);
        double remainingQtyAfterCalculationDistribution1_2 = remainingQtyBeforeDistribution1_2 - quantity;
        assertEquals(remainingQtyAfterDistribution1_2, remainingQtyAfterCalculationDistribution1_2);

        double remainingQtyAfterDistribution1_3 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);
        double remainingQtyAfterCalculationDistribution1_3 = remainingQtyBeforeDistribution1_3 - quantity;
        assertEquals(remainingQtyAfterDistribution1_3, remainingQtyAfterCalculationDistribution1_3);

        log("/*19.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation(supply_location_to);
        supplyConsolePage.refreshBrowser();
        Thread.sleep(2000);
        log("/----Count Remaining Supplies Before Transaction --*/");
        System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        double remainingQtyBeforeLocationDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(0), distribution_to);
        double remainingQtyBeforeLocationDistribution2_2 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(1), distribution_to);
        double remainingQtyBeforeLocationDistribution2_3 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(2), distribution_to);

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation(supply_location_from);
        supplyConsolePage.refreshBrowser();
        Thread.sleep(2000);
        supplyConsolePage.clickTransactionsTab();

        log("/*23----Get how many Outgoing Transactions 'To' count records --*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        log("/*---  Outgoing transactions 'to' count:" + countOutgoingTransactions);
        log("/*24.----Click on Checkboxes Outgoing Transactions --*/");
        if (countOutgoingTransactions >= 3) {
            for (int i = 0; i < containers_from.size(); i++) {
                supplyConsolePage.clickOnOutgoingTransactionsCheckbox(countOutgoingTransactions - i);
            }
        } else {
            log("/*--not all 3 Incoming Transaction records are there--*/");
        }
        supplyConsolePage.clickBulkCancelButton().cancelBulkTransfer();
        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        supplyConsolePage.clickOnRelatedItemTab();
        double remainingQtyAfterCancelLocationDistribution1_1 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(0), distribution_from);
        double remainingQtyAfterCancelLocationDistribution1_2 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(1), distribution_from);
        double remainingQtyAfterCancelLocationDistribution1_3 = supplyConsolePage.getValueOfRemainingQty(containers_from.get(2), distribution_from);

        assertEquals(remainingQtyAfterCancelLocationDistribution1_1, remainingQtyBeforeDistribution1_1);
        assertEquals(remainingQtyAfterCancelLocationDistribution1_2, remainingQtyBeforeDistribution1_2);
        assertEquals(remainingQtyAfterCancelLocationDistribution1_3, remainingQtyBeforeDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        supplyConsolePage.clickOnSupplyLocation(supply_location_to);

        supplyConsolePage.refreshBrowser();
        Thread.sleep(2000);

        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        double remainingQtyAfterCancelLocationDistribution2_1 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(0), distribution_to);
        double remainingQtyAfterCancelLocationDistribution2_2 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(1), distribution_to);
        double remainingQtyAfterCancelLocationDistribution2_3 = supplyConsolePage.getValueOfRemainingQty(containers_to.get(2), distribution_to);

        assertEquals(remainingQtyAfterCancelLocationDistribution2_1, remainingQtyBeforeLocationDistribution2_1);
        assertEquals(remainingQtyAfterCancelLocationDistribution2_2, remainingQtyBeforeLocationDistribution2_2);
        assertEquals(remainingQtyAfterCancelLocationDistribution2_3, remainingQtyBeforeLocationDistribution2_3);

    }

    public void precondition() throws Exception {
        log("/*1.----Login ----*/");

        log("/----Login to ORG (oldUI) --*/");
        orgMainPage = (env.contains("immsbc_admin")) ? loginPage.orgLoginAsImmsBCAdmin() : loginPage.orgLoginAsPPHIS();
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        supplyConsolePage = new SupplyConsolePage(driver);
        Thread.sleep(2000);
        //Assert.assertTrue(false);
        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();
        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        ////// Supply Location_1 -> Outcoming
        log("/*5.----Click on Automation Supply Location_1 --*/");

        /////////////////////////////////////////////////
        //Try generic method
        /////////////////////////////////////////////////
        supplyConsolePage.clickOnSupplyLocation(supply_location_from);
        //////////////////////////////////////////////////
    }
}