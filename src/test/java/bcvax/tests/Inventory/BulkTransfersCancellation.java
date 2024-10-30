package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.*;

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
    ArrayList<String> containers_from;
    HashMap<String, String> containers_from_map;
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
        ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> containers_from_with_cf = (ArrayList)testData.get("bulkContainersFrom");
        containers_from_map = new HashMap<String, String>();
        containers_from = new ArrayList<>();
        for(LinkedHashMap<String, LinkedHashMap<String, String>> container_from_with_cf: containers_from_with_cf) {
            String my_key = container_from_with_cf.keySet().toArray(new String[0])[0];
            containers_from.add(my_key);
            LinkedHashMap<String, String> my_value = container_from_with_cf.get(my_key);
            containers_from_map.put(my_key, my_value.get("conversionFactor"));
        }
        containers_to = (ArrayList)testData.get("bulkContainersTo");
        containers_to_same_clinic = (ArrayList)testData.get("bulkContainersToSameClinic");
    }

    @Test(priority = 1)
    public void Can_doBulk_transfers_Cancellation_By_Doses_form_one_Clinic_to_Another() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244849" : "223359";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case Id: " +"C"+TestcaseID);
        precondition();
        double doses = 10;

        log("/----Count Remaining Supplies --*/");
        log("/----Select Items to Transfer and Submit --*/");
        log("/*7.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
        log("/*---     count:" + countSupplyContainers);
        log("/*8.----Click on Container's records Checkboxes --*/");
        Map<String, Map<String, Double>> containers__from_before_response = new HashMap<String, Map<String, Double>>();
        if (countSupplyContainers >= 3) {
            containers__from_before_response = SupplyLocationRelatedItems.checkSupplyContainers(driver, containers_from);
        } else {
            log("/*--not enough records for Bulk actions--*/");
        }
        double remainingDosesBeforeDistribution1_1 = containers__from_before_response.get(containers_from.get(0)).get("Remaining Doses");
        double remainingQtyBeforeDistribution1_1 = containers__from_before_response.get(containers_from.get(0)).get("Remaining Quantity");
        double remainingDosesBeforeDistribution1_2 = containers__from_before_response.get(containers_from.get(1)).get("Remaining Doses");
        double remainingQtyBeforeDistribution1_2 = containers__from_before_response.get(containers_from.get(1)).get("Remaining Quantity");
        double remainingDosesBeforeDistribution1_3 = containers__from_before_response.get(containers_from.get(2)).get("Remaining Doses");
        double remainingQtyBeforeDistribution1_3 = containers__from_before_response.get(containers_from.get(2)).get("Remaining Quantity");
        log("/*9.----Click on bulk Transfer button --*/");
        SupplyLocationRelatedItems.clickTransfersButton(driver);

        log("/*10.----Enter the Dosages values (1 dose) for 3 row Transfers --*/");

        supplyConsolePage.enterBulkTransferByDosages(containers_from, doses);

        log("/*11.----select 'To' Automation Supply Location_2  --*/");
        ContainerTransferPage.selectSupplyLocationToFromDropdown(driver, supply_location_to);
        log("/*12.----click Transfer dialog Modal button --*/");
        ContainerTransferPage.clickTransferButton(driver);
        Thread.sleep(2000);
        log("/*13.----click Close Modal button --*/");
        ContainerPrintDialog.clickCloseButton(driver);
        Thread.sleep(2000);

        Map<String, Map<String, Double>> containers_from_after_response = SupplyLocationRelatedItems.getSupplyContainers(driver, containers_from);
        /////////////////////Doses and Quantity AFTER Automation Location_1//////////////////////////////////
        log("/*14.----Getting Remaining Doses/Quantity - AFTER - Automation Location_1 --*/");

        double remainingDosesAfterDistribution1_1 = containers_from_after_response.get(containers_from.get(0)).get("Remaining Doses");
        double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - doses;
        assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);

        double remainingDosesAfterDistribution1_2 = containers_from_after_response.get(containers_from.get(1)).get("Remaining Doses");
        double remainingDosesAfterCalculationDistribution1_2 = remainingDosesBeforeDistribution1_2 - doses;
        assertEquals(remainingDosesAfterDistribution1_2, remainingDosesAfterCalculationDistribution1_2);

        double remainingDosesAfterDistribution1_3 = containers_from_after_response.get(containers_from.get(2)).get("Remaining Doses");
        double remainingDosesAfterCalculationDistribution1_3 = remainingDosesBeforeDistribution1_3 - doses;
        assertEquals(remainingDosesAfterDistribution1_3, remainingDosesAfterCalculationDistribution1_3);

        log("/*19.----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        SupplyLocationsPage.selectSupplyLocationName(driver,supply_location_to);
        driver.navigate().refresh();
        Thread.sleep(2000);
        log("/----Count Remaining Supplies Before Transaction --*/");
        System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        Map<String, Map<String, Double>> containers_to_before_response = SupplyLocationRelatedItems.getSupplyContainers(driver, containers_to);
        double remainingDosesBeforeLocationDistribution2_1 = containers_to_before_response.get(containers_to.get(0)).get("Remaining Doses");
        double remainingDosesBeforeLocationDistribution2_2 = containers_to_before_response.get(containers_to.get(1)).get("Remaining Doses");
        double remainingDosesBeforeLocationDistribution2_3 = containers_to_before_response.get(containers_to.get(2)).get("Remaining Doses");

        log("/----Go to Supply Location Related Tab where Transferring From --*/");

        SupplyConsolePage.clickSupplyLocationsTab(driver);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

        driver.navigate().refresh();
        SupplyLocationPage.clickTransactionsTab(driver);

        log("/*23----Get how many Outgoing Transactions 'To' count records --*/");
        int countOutgoingTransactions = SupplyLocationTransactions.getRowsOutgoingTransactionsCount(driver);
        log("/*---  Outgoing transactions 'to' count:" + countOutgoingTransactions);
        log("/*24.----Click on Checkboxes Outgoing Transactions --*/");
        SupplyLocationTransactions.checkLastOutgoingTransactions(driver, 3);
        Thread.sleep(1000);
        SupplyLocationTransactions.clickCancelButton(driver);
        supplyConsolePage.cancelTransfer();

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        SupplyLocationPage.clickOnRelatedItemTab(driver);
        Map<String, Map<String, Double>> containers_from_after_cancel_response = SupplyLocationRelatedItems.getSupplyContainers(driver, containers_from);
        double remainingDosesAfterLocationDistribution1_1 = containers_from_after_cancel_response.get(containers_from.get(0)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution1_2 = containers_from_after_cancel_response.get(containers_from.get(1)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution1_3 = containers_from_after_cancel_response.get(containers_from.get(2)).get("Remaining Doses");

        assertEquals(remainingDosesAfterLocationDistribution1_1, remainingDosesBeforeDistribution1_1);
        assertEquals(remainingDosesAfterLocationDistribution1_2, remainingDosesBeforeDistribution1_2);
        assertEquals(remainingDosesAfterLocationDistribution1_3, remainingDosesBeforeDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");

        SupplyConsolePage.clickSupplyLocationsTab(driver);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);
        driver.navigate().refresh();
        Thread.sleep(2000);
        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        Map<String, Map<String, Double>> containers_to_after_cancel_response = SupplyLocationRelatedItems.getSupplyContainers(driver, containers_to);
        double remainingDosesAfterLocationDistribution2_1 = containers_to_after_cancel_response.get(containers_to.get(0)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution2_2 = containers_to_after_cancel_response.get(containers_to.get(1)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution2_3 = containers_to_after_cancel_response.get(containers_to.get(2)).get("Remaining Doses");

        assertEquals(remainingDosesAfterLocationDistribution2_1, remainingDosesBeforeLocationDistribution2_1);
        assertEquals(remainingDosesAfterLocationDistribution2_2, remainingDosesAfterLocationDistribution2_2);
        assertEquals(remainingDosesAfterLocationDistribution2_3, remainingDosesBeforeLocationDistribution2_3);
    }

    public void precondition() throws Exception {
        log("/*1.----Login ----*/");

        log("/----Login to ORG (oldUI) --*/");
        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        supplyConsolePage = new SupplyConsolePage(driver);
        Thread.sleep(2000);

        log("/*3.----Close All previously opened Tab's --*/");
        SupplyConsolePage.closeTabsHCA(driver);
        log("/*4.----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);

        ////// Supply Location_1 -> Outcoming
        log("/*5.----Click on Automation Supply Location_1 --*/");

        /////////////////////////////////////////////////
        //Try generic method
        /////////////////////////////////////////////////
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);
        //////////////////////////////////////////////////
    }
}