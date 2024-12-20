package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.Map;
import static org.testng.Assert.assertEquals;
import static java.lang.Math.round;

@Listeners({TestListener.class})
public class TransferCancellation extends BaseTest {
    MainPageOrg orgMainPage;
    SupplyConsolePage supplyConsolePage;
    String env;
    Map<String, Object> testData;
    String supply_location_from;
    String supply_location_to;
    String distribution_from;
    String distribution_to;
    String distribution_to_same_clinic;

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
        distribution_from = String.valueOf(testData.get("distributionFrom"));
        distribution_to = String.valueOf(testData.get("distributionTo"));
        distribution_to_same_clinic = String.valueOf(testData.get("distributionToSameClinic"));
    }

    @Test(priority = 1)
    public void Can_do_Transfer_Cancellation_by_Dosages_from_one_Clinic_to_Another() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244845" : "223184"; //C223184
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case Id: " +"C"+TestcaseID);
        precondition();
		String container_from = String.valueOf(testData.get("containerFrom"));
		String container_to = String.valueOf(testData.get("containerTo"));
        double doses = 5;

        log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
        Map<String, Double> doses_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
        double remainingDosesBeforeDistribution1_1 = doses_before.get("Remaining Doses");
        log("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution1_1);
        double remainingQtyBeforeDistribution1_1 = doses_before.get("Remaining Quantity");
        log("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution1_1);

        log("/---- Perform doses transfer to  location " + supply_location_to + "--*/");
        log("/*7.----Click on Container's dropdown --*/");
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
        log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
        SupplyLocationRelatedItems.selectTransferFromDropDown(driver);

        log("/*9.----Picked up the Trade Vaccine Name --*/");
        String tradeName = ContainerTransferForm.getVaccineName(driver);//Pfizer mRNA BNT162b2 - EK4241
        log("/*--  the Trade Name is:  " + tradeName);
        log("/*10.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = ContainerTransferForm.getConversationFactor(driver);
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
        log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
        ContainerTransferForm.enterTransferDosages(driver, Double.toString(doses));
        System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
        ContainerTransferForm.selectSupplyLocationToFromDropdown(driver, supply_location_to);
        System.out.println("/*12.----click Transfer dialog Modal button --*/");
        ContainerTransferForm.clickTransferButton(driver);
        System.out.println("/*13.----click Close Modal button --*/");
        ContainerPrintDialog.clickCloseButton(driver);
        Thread.sleep(1000);
        log("/---- Count and Validate Remaining Supplies After Transfer --*/");
        Map<String, Double> doses_after = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
        double remainingDosesAfterDistribution1_1 = doses_after.get("Remaining Doses");
        System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterDistribution1_1);
        double remainingQtyAfterDistribution1_1 = doses_after.get("Remaining Quantity");
        System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterDistribution1_1);
        double remainingDosesAfterCalculationDistribution1_1 = Double.parseDouble(new DecimalFormat("##.##").
                format((remainingDosesBeforeDistribution1_1 - doses)));
        assertEquals(remainingDosesAfterDistribution1_1, remainingDosesAfterCalculationDistribution1_1);
        double remainingQtyAfterCalculationDistribution1_1 = Double.parseDouble(new DecimalFormat("##.##").
                format((remainingDosesAfterCalculationDistribution1_1 / dose_conversation_factor)));
        assertEquals(remainingQtyAfterDistribution1_1, remainingQtyAfterCalculationDistribution1_1);

        System.out.println("/*19.----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);
        driver.navigate().refresh();
        System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        Map<String, Double> doses_destination_before = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to);
        double remainingDosesBeforeDistribution2_1 = doses_destination_before.get("Remaining Doses");
        System.out.println("/*-- . remaining doses are: -->" + remainingDosesBeforeDistribution2_1);
        double remainingQtyBeforeDistribution2_1 = doses_destination_before.get("Remaining Quantity");
        System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyBeforeDistribution2_1);

        log("/*22.----Go to Supply Location Related Tab where Transferring From --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

        driver.navigate().refresh();
        SupplyLocationPage.clickTransactionsTab(driver);
        System.out.println("/*23----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        System.out.println("/*23.1----Get how many Outgoing Transactions 'From' count records --*/");
        int countOutgoingTransactions = SupplyLocationTransactions.getRowsOutgoingTransactionsCount(driver);
        System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
        int kk = countOutgoingTransactions;
        System.out.println("/*23.2---Get Outgoing Transaction id 'from' --*/");
        String outgoingSupplyTransactionId = SupplyLocationTransactions.getOutgoingTransactionId(driver, kk);
        System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
        System.out.println("/*23.3----Click on the latest created Outgoing Transactions --*/");
        SupplyLocationTransactions.clickOnOutgoingTransactionsDropDownMenu(driver, kk);
        log("/*23.----Cancel Transfer --*/");
        //tables.openShippedTransactionsOutgoingActions(ImmutableMap.of(SUPPLY_ITEM_NAME, vaccine));
        supplyConsolePage.cancelIncomingTransfer();
        SupplyLocationPage.clickOnRelatedItemTab(driver);

        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        Map<String, Double> doses_after_cancel = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_from);
        double remainingDosesAfterCancelDistribution1_1 = doses_after_cancel.get("Remaining Doses");
        System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution1_1);
        double remainingQtyAfterCancelDistribution1_1 = doses_after_cancel.get("Remaining Quantity");
        System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution1_1);
        assertEquals(remainingDosesAfterCancelDistribution1_1, remainingDosesBeforeDistribution1_1);
        assertEquals(remainingQtyAfterCancelDistribution1_1, remainingQtyBeforeDistribution1_1);

        log("/----Go to Supply Location Related Tab where Transferring To --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_to);

        driver.navigate().refresh();
        log("/----Count Remaining Supplies After Cancel Transaction --*/");
        Map<String, Double> doses_destination_after_cancel = SupplyLocationRelatedItems.getSupplyContainerDoseQuantity(driver, container_to);
        double remainingDosesAfterCancelDistribution2_1 = doses_destination_after_cancel.get("Remaining Doses");
        System.out.println("/*-- . remaining doses are: -->" + remainingDosesAfterCancelDistribution2_1);
        double remainingQtyAfterCancelDistribution2_1 = doses_destination_after_cancel.get("Remaining Quantity");
        System.out.println("/*-- . remaining Quantity are: -->" + remainingQtyAfterCancelDistribution2_1);
        assertEquals(remainingDosesAfterCancelDistribution2_1, remainingDosesBeforeDistribution2_1);
        assertEquals(remainingQtyAfterCancelDistribution2_1, remainingQtyBeforeDistribution2_1);
    }

    public void precondition() throws Exception {
        log("/*1.----Login to ORG (oldUI) --*/");
        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = MainPageOrg.currentApp(driver);
        log("/*1.1.----Current App " + currentApp + "--*/");
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        supplyConsolePage = new SupplyConsolePage(driver);

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
