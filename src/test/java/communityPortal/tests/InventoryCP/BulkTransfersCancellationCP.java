package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.*;

import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class BulkTransfersCancellationCP extends BaseTest {
    private static final DecimalFormat df = new DecimalFormat("#.##");
    MainPageCP cpMainPage;
    SupplyConsolePage supplyConsolePage;
    String env;
    Map<String, Object> testData;
    String supply_location_from;
    String supply_location_to;
    String distribution_from;
    String distribution_to;
    String distribution_to_same_clinic;
    List<String> containers_from;
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
        TestcaseID = (env.contains("immsbc_admin")) ? "245096" : "243119";
        log("Target Environment: "+ env);
        log("Test Case Id: " +"C"+TestcaseID);
        precondition();
        double doses = 10;

        log("/----Select Items to Transfer and Submit --*/");
        log("/*7.----Get Supply Containers count outcoming records --*/");
        int countSupplyContainers = SupplyLocationRelatedItems.countSupplyContainers(driver);
        Map<String, Map<String, Double>> selected_containers_from_before = new HashMap<>();
        log("/*---     count:" + countSupplyContainers);
        log("/*8.----Click on Container's records Checkboxes --*/");
        if (countSupplyContainers >= 3) {
            selected_containers_from_before = SupplyLocationRelatedItems.checkSupplyContainers(driver, containers_from);
        } else {
            log("/*--not enough records for Bulk actions--*/");
        }
        log("/----Count Remaining Supplies --*/");
        double remainingDosesBeforeDistribution1_1 = selected_containers_from_before.get(containers_from.get(0)).get("Remaining Doses");
        double remainingQtyBeforeDistribution1_1 = selected_containers_from_before.get(containers_from.get(0)).get("Remaining Quantity");
        double remainingDosesBeforeDistribution1_2 = selected_containers_from_before.get(containers_from.get(1)).get("Remaining Doses");
        double remainingQtyBeforeDistribution1_2 = selected_containers_from_before.get(containers_from.get(1)).get("Remaining Quantity");
        double remainingDosesBeforeDistribution1_3 = selected_containers_from_before.get(containers_from.get(2)).get("Remaining Doses");
        double remainingQtyBeforeDistribution1_3 = selected_containers_from_before.get(containers_from.get(2)).get("Remaining Quantity");

        log("/*9.----Click on bulk Transfer button --*/");
        SupplyLocationRelatedItems.clickTransfersButton(driver);

        log("/*10.----Enter the Dosages values (1 dose) for 3 row Transfers --*/");

        supplyConsolePage.enterBulkTransferByDosages(containers_from, doses);

        log("/*11.----select 'To' Automation Supply Location_2  --*/");
        ContainerTransferPage.selectSupplyLocationToFromDropdown(driver, supply_location_to);
        Thread.sleep(2000);
        log("/*12.----click Transfer dialog Modal button --*/");
        ContainerTransferPage.clickTransferButton(driver);
        Thread.sleep(2000);
        log("/*13.----click Close Modal button --*/");
        ContainerPrintDialog.clickCloseButton(driver);
        Thread.sleep(2000);

        Map<String, Map<String, Double>> selected_containers_from_after =  SupplyLocationRelatedItems.getSupplyContainers(driver, containers_from);
        /////////////////////Doses and Quantity AFTER Automation Location_1//////////////////////////////////
        log("/*14.----Getting Remaining Doses/Quantity - AFTER - Automation Location_1 --*/");
        double remainingDosesAfterDistribution1_1 = selected_containers_from_after.get(containers_from.get(0)).get("Remaining Doses");
        double remainingDosesAfterCalculationDistribution1_1 = remainingDosesBeforeDistribution1_1 - doses;
        assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);

        double remainingDosesAfterDistribution1_2 = selected_containers_from_after.get(containers_from.get(1)).get("Remaining Doses");
        double remainingDosesAfterCalculationDistribution1_2 = remainingDosesBeforeDistribution1_2 - doses;
        assertEquals(remainingDosesAfterDistribution1_2, remainingDosesAfterCalculationDistribution1_2);

        double remainingDosesAfterDistribution1_3 = selected_containers_from_after.get(containers_from.get(2)).get("Remaining Doses");
        double remainingDosesAfterCalculationDistribution1_3 = remainingDosesBeforeDistribution1_3 - doses;
        assertEquals(remainingDosesAfterDistribution1_3, remainingDosesAfterCalculationDistribution1_3);

        log("/*19.----Go to Supply Locations Tab --*/");
        MainPageCP.goToSupplyLocation(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        log("/----Count Remaining Supplies Before Transaction --*/");
        driver.navigate().refresh();
        Thread.sleep(2000);
        Map<String, Map<String, Double>> selected_containers_to_before =  SupplyLocationRelatedItems.getSupplyContainers(driver, containers_to);
        double remainingDosesBeforeLocationDistribution2_1 = selected_containers_to_before.get(containers_to.get(0)).get("Remaining Doses");
        double remainingDosesBeforeLocationDistribution2_2 = selected_containers_to_before.get(containers_to.get(1)).get("Remaining Doses");
        double remainingDosesBeforeLocationDistribution2_3 = selected_containers_to_before.get(containers_to.get(2)).get("Remaining Doses");

        log("/----Go to Supply Location Related Tab where Transferring From --*/");
        MainPageCP.goToSupplyLocation(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);
        Thread.sleep(2000);

        driver.navigate().refresh();
        Thread.sleep(2000);
        SupplyLocationPage.clickTransactionsTab(driver);

        log("/*23----Get how many Outgoing Transactions 'To' count records --*/");
        int countOutgoingTransactions = SupplyLocationTransactions.getRowsOutgoingTransactionsCount(driver);
        log("/*---  Outgoing transactions 'to' count:" + countOutgoingTransactions);
        log("/*24.----Click on Checkboxes Outgoing Transactions --*/");
        SupplyLocationTransactions.checkLastOutgoingTransactions(driver, 3);
        SupplyLocationTransactions.clickCancelButton(driver);
        supplyConsolePage.cancelTransfer();

        log("/----Count And Validate Remaining Supplies After Transaction --*/");
        SupplyLocationPage.clickOnRelatedItemTab(driver);

        Map<String, Map<String, Double>> selected_containers_location_from_after =  SupplyLocationRelatedItems.getSupplyContainers(driver, containers_from);
        double remainingDosesAfterLocationDistribution1_1 = selected_containers_location_from_after.get(containers_from.get(0)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution1_2 = selected_containers_location_from_after.get(containers_from.get(1)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution1_3 = selected_containers_location_from_after.get(containers_from.get(2)).get("Remaining Doses");

        assertEquals(remainingDosesAfterLocationDistribution1_1, remainingDosesBeforeDistribution1_1);
        assertEquals(remainingDosesAfterLocationDistribution1_2, remainingDosesBeforeDistribution1_2);
        assertEquals(remainingDosesAfterLocationDistribution1_3, remainingDosesBeforeDistribution1_3);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        MainPageCP.goToSupplyLocation(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);

        driver.navigate().refresh();
        Thread.sleep(2000);
        Map<String, Map<String, Double>> selected_containers_location_to_after =  SupplyLocationRelatedItems.getSupplyContainers(driver, containers_to);
        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        double remainingDosesAfterLocationDistribution2_1 = selected_containers_location_to_after.get(containers_to.get(0)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution2_2 = selected_containers_location_to_after.get(containers_to.get(1)).get("Remaining Doses");
        double remainingDosesAfterLocationDistribution2_3 = selected_containers_location_to_after.get(containers_to.get(2)).get("Remaining Doses");

        assertEquals(remainingDosesAfterLocationDistribution2_1, remainingDosesBeforeLocationDistribution2_1);
        assertEquals(remainingDosesAfterLocationDistribution2_2, remainingDosesAfterLocationDistribution2_2);
        assertEquals(remainingDosesAfterLocationDistribution2_3, remainingDosesBeforeLocationDistribution2_3);
    }

    public void precondition() throws Exception {
        log("/*1.----Login to CP (newUI) as Clinician --*/");
        cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();
        MainPageCP.goToSupplyLocation(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);
        SupplyLocationPage.clickOnRelatedItemTab(driver);
        supplyConsolePage = new SupplyConsolePage(driver);
    }
}