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

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

@Listeners({TestListener.class})
public class Transfer extends BaseTest {
    String env;
    Map<String, Object> testData;
    SupplyConsolePage supplyConsolePage;
    MainPageOrg orgMainPage;
    String supply_location_from;
    String supply_location_to;
    String distribution_from;
    String distribution_to;
    String distribution_to_same_clinic;

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
    }

    @Test(priority = 1)
    public void Can_do_Transfer_by_Dosages_from_one_Clinic_to_Another() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244845" : "223184"; //C223184
        precondition();
        String container_from = String.valueOf(testData.get("containerFrom"));
        String container_to = String.valueOf(testData.get("containerTo"));

        log("/*6.----Getting Remaining Doses/Remaining Quantity - Before --*/");
        double doses_before_distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);

        log("/*-- . remaining doses are: -->" + doses_before_distribution_1_1);
        double qty_before_distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        ;
        log("/*-- . remaining Quantity are: -->" + qty_before_distribution_1_1);
        log("/*7.----Click on Container's dropdown --*/");
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
        log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
        SupplyLocationRelatedItems.selectTransferFromDropDown(driver);
        log("/*9.----Picked up the Trade Vaccine Name --*/");
        String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
        log("/*--  the Trade Name is:  " + tradeName);
        log("/*10.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;

        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
        log("/*10.----Entering 10 Doses in the Container-Transfer Form --*/");
        ContainerTransferForm.enterTransferDosages(driver, "10");
        System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
        System.out.println("/*12.----click Transfer dialog Modal button --*/");
        supplyConsolePage.clickBulkTransfersModalButton();
        System.out.println("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);

        System.out.println("/*14----Getting Remaining Doses/Remaining Quantity - After --*/");
        double doses_after_distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);

        System.out.println("/*-- . remaining doses are: -->" + doses_after_distribution_1_1);
        double qty_after_distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);

        System.out.println("/*-- . remaining Quantity are: -->" + qty_after_distribution_1_1);
        System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
        double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format((doses_before_distribution_1_1 - 10)));
        assertEquals(doses_after_distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);

        double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format(((doses_before_distribution_1_1 - 10) / dose_conversation_factor)));
        assertEquals(qty_after_distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);

        System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        SupplyLocationPage.clickTransactionsTab(driver);
        System.out.println("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        System.out.println("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);

        System.out.println("/*18.----Close All Tab's --*/");
        SupplyConsolePage.closeTabsHCA(driver);
        System.out.println("/*19.----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        SupplyConsolePage.selectSupplyLocationName(driver, supply_location_to);
        //supplyConsolePage.clickOnSupplyLocation_2();
        ///////////////// Supply Location_2 -> Incoming //////////////////////////

        System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        double doses_before_distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);

        System.out.println("/*-- . remaining doses are: -->" + doses_before_distribution_2_1);
        double qty_before_distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);

        System.out.println("/*-- . remaining Quantity are: -->" + qty_before_distribution_2_1);
        System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        SupplyLocationPage.clickTransactionsTab(driver);
        System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
        int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
        System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
        System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
        int j = countIncomingTransactions;
        supplyConsolePage.clickOnIncomingTransactionsDropDownMenu(j);
        System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
        supplyConsolePage.selectConfirmIncomingDropDown();
        System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
        supplyConsolePage.selectIncomingSupplyDistribution(distribution_to);
        System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
        supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
        System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
        Assert.assertTrue(all_alerts.get(0).contains("You have successfully Confirmed the Transaction"));
        System.out.println("/*29.----click on Related Item Tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        System.out.println("/*14----Getting Remaining Doses/Remaining Quantity After --*/");
        double doses_after_distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);

        System.out.println("/*-- . remaining doses are: -->" + doses_after_distribution_2_1);
        double qty_after_distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);

        System.out.println("/*-- . remaining Quantity are: -->" + qty_after_distribution_2_1);
        System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
        assertEquals(doses_after_distribution_2_1, round(doses_before_distribution_2_1 + 10), 2);
        assertEquals(qty_after_distribution_2_1, round((doses_before_distribution_2_1 + 10) / dose_conversation_factor), 2);
    }

    //@Test(priority = 2)
    public void Can_do_Transfer_by_Quantity_from_one_Clinic_to_Another() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244845" : "223184"; //C223184 //C223184
        precondition();
        String container_from = String.valueOf(testData.get("containerFrom"));
        String container_to = String.valueOf(testData.get("containerTo"));
        int quantity = 10;

        ////// Supply Location_1 -> Outcoming
        //log("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
        //supplyConsolePage.selectSupplyContainer();
        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        double doses_before_distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        log("/*-- . remaining doses are: -->" + doses_before_distribution_1_1);
        double qty_before_distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        ;
        log("/*-- . remaining Quantity are: -->" + qty_before_distribution_1_1);
        log("/*7.----Click on Container's dropdown --*/");
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
        log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
        SupplyLocationRelatedItems.selectTransferFromDropDown(driver);
        log("/*9.----Picked up the Trade Vaccine Name --*/");
        String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
        log("/*--  the Trade Name is:  " + tradeName);
        log("/*10.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
        log("/*10.----Entering 10 Quantity in the Container-Transfer Form --*/");
        ContainerTransferForm.enterTransferQuantity(driver, Integer.toString(quantity));
        System.out.println("/*11.----select 'To' Automation Supply Location_2  --*/");
        Thread.sleep(1000);
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_to);
        System.out.println("/*12.----click Transfer dialog Modal button --*/");
        supplyConsolePage.clickBulkTransfersModalButton();
        System.out.println("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();
        System.out.println("/*14----Quantity Remaining Doses/Remaining Quantity check After --*/");
        double doses_after_distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        System.out.println("/*-- . remaining doses are: -->" + doses_after_distribution_1_1);
        double qty_after_distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        System.out.println("/*-- . remaining Quantity are: -->" + qty_after_distribution_1_1);
        System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
        double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format((doses_before_distribution_1_1 - quantity * dose_conversation_factor)));
        assertEquals(doses_after_distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
        double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format(((doses_before_distribution_1_1 - quantity * dose_conversation_factor) / dose_conversation_factor)));
        assertEquals(qty_after_distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
        System.out.println("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        SupplyLocationPage.clickTransactionsTab(driver);
        System.out.println("/*17----Getting id for the latest created Transaction Outgoing 'From' and Incoming 'To'--*/");
        System.out.println("/*17.1----Get how many Outgoing Transactions 'From' count records --*/");
        int countOutgoingTransactions = supplyConsolePage.getRowsOutgoingTransactionsCount();
        System.out.println("/*---  Outgoing transactions 'from' count:" + countOutgoingTransactions);
//		int kk = countOutgoingTransactions;
//		System.out.println("/*17.2---Get Outgoing Transaction id 'from' --*/");
//		String outgoingSupplyTransactionId = supplyConsolePage.getOutgoingSupplyTransactionId(kk);
//		System.out.println("/*--outgoing Supply Transaction From id --*/:" + outgoingSupplyTransactionId);
//		System.out.println("/*17.3----Click on the latest created Outgoing Transactions --*/");
//		supplyConsolePage.clickOnOutgoingTransactions(kk);
        System.out.println("/*18.----Close All Tab's --*/");
        SupplyConsolePage.closeTabsHCA(driver);
        System.out.println("/*19.----Go to Supply Locations Tab --*/");

        SupplyConsolePage.clickSupplyLocationsTab(driver);
        System.out.println("/*20.----Click on Automation Supply Location_2 --*/");
        SupplyConsolePage.selectSupplyLocationName(driver, supply_location_to);
        //supplyConsolePage.clickOnSupplyLocation_2();

        ///////////////// Supply Location_2 -> Incoming //////////////////////////

        System.out.println("/*21.----Quantity Remaining Doses/Remaining Quantity check Before --*/");
        double doses_before_distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
        System.out.println("/*-- . remaining doses are: -->" + doses_before_distribution_2_1);
        double qty_before_distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
        System.out.println("/*-- . remaining Quantity are: -->" + qty_before_distribution_2_1);
        System.out.println("/*22.----Go to Transactions Tab of Automation Supply Location_2 --*/");
        SupplyLocationPage.clickTransactionsTab(driver);
        System.out.println("/*23----Get how many Incoming Transactions 'To' count records --*/");
        int countIncomingTransactions = supplyConsolePage.getRowsIncomingTransactionsCount();
        System.out.println("/*---  Incoming transactions 'to' count:" + countIncomingTransactions);
        System.out.println("/*24----Click on the latest created Incoming Transactions DropDown Menu --*/");
        int j = countIncomingTransactions;
        supplyConsolePage.clickOnIncomingTransactionsDropDownMenu(j);
        System.out.println("/*25.----select Confirm from the Incoming dropdown menu --*/");
        supplyConsolePage.selectConfirmIncomingDropDown();
        System.out.println("/*26.----select Incoming Supply Distributor 2_1 --*/");
        supplyConsolePage.selectIncomingSupplyDistribution(distribution_to);
        System.out.println("/*27.----click on Confirm Incoming Transfer button in the Modal screen --*/");
        supplyConsolePage.clickOnConfirmModalIncomingTransactionButton();
        System.out.println("/*28.--Expecting to see the toast success message - 'You have successfully Confirmed the Transaction' --*/");
        List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
        Assert.assertTrue(all_alerts.get(0).contains("You have successfully Confirmed the Transaction"));
        System.out.println("/*29.----click on Related Item Tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();
        System.out.println("/*14----Quantity Remaining Doses/Remaining Quantity check After --*/");
        double doses_after_distribution_2_1 = supplyConsolePage.getValueOfRemainingDoses(container_to, distribution_to);
        System.out.println("/*-- . remaining doses are: -->" + doses_after_distribution_2_1);
        double qty_after_distribution_2_1 = supplyConsolePage.getValueOfRemainingQty(container_to, distribution_to);
        System.out.println("/*-- . remaining Quantity are: -->" + qty_after_distribution_2_1);
        System.out.println("/*15.----Validate Remaining Doses and Remaining Quantities values --*/");
        assertEquals(doses_after_distribution_2_1, round(doses_before_distribution_2_1 + quantity * dose_conversation_factor));
        assertEquals(qty_after_distribution_2_1, qty_before_distribution_2_1 + quantity);
    }

    @Test(priority = 3)
    public void Can_do_Transfer_by_Dosages_within_the_same_Clinic() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244850" : "223185"; //C223184; //C223185
        precondition();
        String container_from = String.valueOf(testData.get("containerFrom"));
        String container_to_same_clinic = String.valueOf(testData.get("containerToSameClinic"));

        //System.out.println("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
        //supplyConsolePage.selectSupplyContainer();
        /////////////////////Doses and Quantity BEFORE//////////////////////////////////
        System.out.println("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double doses_before_distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + doses_before_distribution_1_1);
        double qty_before_distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + qty_before_distribution_1_1);
        log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double doses_before_distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + doses_before_distribution_1_2);
        double qty_before_distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + qty_before_distribution_1_2);
        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        System.out.println("/*8.----Click on Container's dropdown --*/");
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
        System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
        SupplyLocationRelatedItems.selectTransferFromDropDown(driver);
        System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
        String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
        System.out.println("/*--  the Trade Name is:  " + tradeName);
        System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
        System.out.println("/*12.----Entering 10 Doses in the Container-Transfer Form --*/");
        ContainerTransferForm.enterTransferDosages(driver, "10");
        System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);
        System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to_same_clinic);
        System.out.println("/*15.----click Transfer dialog Modal button --*/");
        supplyConsolePage.clickBulkTransfersModalButton();
        System.out.println("/*16.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();
        driver.navigate().refresh();
        /////////////////////Doses and Quantity AFTER///////////////////////////////////
        System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double doses_after_distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + doses_after_distribution_1_1);
        double qty_after_distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + qty_after_distribution_1_1);
        System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double doses_after_distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + doses_after_distribution_1_2);
        double qty_after_distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + qty_after_distribution_1_2);
        //////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
        System.out.println("/*19.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format((doses_before_distribution_1_1 - 10)));
        assertEquals(doses_after_distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
        double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format(((doses_before_distribution_1_1 - 10) / dose_conversation_factor)));
        assertEquals(qty_after_distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
        System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format((doses_before_distribution_1_2 + 10)));
        assertEquals(doses_after_distribution_1_2, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2);
        double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format(((doses_before_distribution_1_2 + 10) / dose_conversation_factor)));
        assertEquals(qty_after_distribution_1_2, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2);
    }

    //@Test(priority = 4)
    public void Can_do_Transfer_by_Quantity_within_the_same_Clinic() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "244850" : "223185"; //C223185
        precondition();
        String container_from = String.valueOf(testData.get("containerFrom"));
        String container_to_same_clinic = String.valueOf(testData.get("containerToSameClinic"));
        int quantity = 10;

        //System.out.println("/*-- 6. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EK4241' --*/");
        //supplyConsolePage.selectSupplyContainer();
        /////////////////////Doses and Quantity BEFORE//////////////////////////////////
        System.out.println("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double remainingDoses_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        System.out.println("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_1);
        double remainingQty_before_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        System.out.println("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_1);
        log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double remainingDoses_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Lot_EK4241_Distribution_1_2);
        double remainingQty_before_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Lot_EK4241_Distribution_1_2);
        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        System.out.println("/*8.----Click on Container's dropdown --*/");
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, container_from);
        System.out.println("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
        SupplyLocationRelatedItems.selectTransferFromDropDown(driver);
        System.out.println("/*10.----Picked up the Trade Vaccine Name --*/");
        String tradeName = supplyConsolePage.getVaccineName();//Pfizer mRNA BNT162b2 - EK4241
        System.out.println("/*--  the Trade Name is:  " + tradeName);
        System.out.println("/*11.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        System.out.println("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
        System.out.println("/*12.----Entering 10 Quantity in the Container-Transfer Form --*/");
        ContainerTransferForm.enterTransferQuantity(driver, Integer.toString(quantity));
        System.out.println("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);
        System.out.println("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to_same_clinic);
        System.out.println("/*15.----click Transfer dialog Modal button --*/");
        supplyConsolePage.clickBulkTransfersModalButton();
        System.out.println("/*16.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();
        driver.navigate().refresh();
        /////////////////////Doses and Quantity AFTER///////////////////////////////////
        System.out.println("/*17----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double remainingDoses_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        System.out.println("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_1);
        double remainingQty_after_Lot_EK4241_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        System.out.println("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_1);
        System.out.println("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double remainingDoses_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Lot_EK4241_Distribution_1_2);
        double remainingQty_after_Lot_EK4241_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        System.out.println("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Lot_EK4241_Distribution_1_2);
        //////////Validation for Distribution_1_1(From) and Distribution_1_2(To)
        System.out.println("/*19.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format((remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * dose_conversation_factor)));
        assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_1, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_1);
        double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format(((remainingDoses_before_Lot_EK4241_Distribution_1_1 - quantity * dose_conversation_factor) / dose_conversation_factor)));
        assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_1, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_1);
        System.out.println("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        double remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format((remainingDoses_before_Lot_EK4241_Distribution_1_2 + quantity * dose_conversation_factor)));
        assertEquals(remainingDoses_after_Lot_EK4241_Distribution_1_2, remainingDoses_after_Calculation_Lot_EK4241_Distribution_1_2);
        double remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2 =
                Double.parseDouble(new DecimalFormat("##.##").
                        format(((remainingDoses_before_Lot_EK4241_Distribution_1_2 + quantity * dose_conversation_factor) / dose_conversation_factor)));
        assertEquals(remainingQty_after_Lot_EK4241_Distribution_1_2, remainingQty_after_Calculation_Lot_EK4241_Distribution_1_2);
    }

    public void precondition() throws Exception {
        log("/*----Login to ORG (oldUI) --*/");
        loginPage.orgLoginAsPPHIS();
        String currentApp = MainPageOrg.currentApp(driver);
        if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
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
        SupplyConsolePage.selectSupplyLocationName(driver, supply_location_from);
        //////////////////////////////////////////////////
    }
}
