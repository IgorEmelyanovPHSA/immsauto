package bcvax.tests.Inventory;
import Utilities.TestListener;
import bcvax.pages.MainPageOrg;
import bcvax.tests.BaseTest;
import bcvax.pages.CommonMethods;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import constansts.Apps;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Drafts extends BaseTest {
    String env;
    Map<String, Object> testData;
    String supply_location_from;
    String supply_location_to;
    String distribution_from;
    String distribution_to;
    String distribution_to_same_clinic;
    MainPageOrg orgMainPage;
    SupplyConsolePage supplyConsolePage;
    String container_from ;
    String container_to_same_clinic;

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
        container_from = String.valueOf(testData.get("containerFrom"));
        container_to_same_clinic = String.valueOf(testData.get("containerToSameClinic"));
    }

    @Test()
    public void Can_Do_Single_Draft_ByDosages_Within_The_Same_Clinic() throws Exception {
        String container_from = String.valueOf(testData.get("containerFrom"));
        String container_to_same_clinic = String.valueOf(testData.get("containerToSameClinic"));
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case#1 save draft and transfer after");
        double amountOfDosesToAdjust = 10;
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login AS comunityqa_org_immsbc_admin");
                TestcaseID = "244847"; //C244847
                loginPage.loginAsImmsBCAdminICE();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "223358"; //C223358
                loginPage.loginAsPPHIS();
        }

        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        supplyConsolePage = new SupplyConsolePage(driver);
        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_from);
        //CommonMethods common = new CommonMethods(driver);
        log("/*4.----Get a matching row for first row Lot number --*/");
        //int matchedRow = common.getMatchedRowToLotInRow1();

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        //double[] remDosesQtyConversionFactorBefore_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        //double[] remDosesQtyConversionFactorBefore_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*7.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(500);
        log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
        try {
            supplyConsolePage.selectTransferFromDropDown();
        } catch (ElementNotInteractableException ex) {
            System.out.println("*** WARNING*** Couldn't Select the Action. Try to click Action button again...");
            supplyConsolePage.clickOnFirstContainerDropDownMenu();
            Thread.sleep(500);
            supplyConsolePage.selectTransferFromDropDown();
        }

        log("/*9.----Picked up the Dose Conversation Factor --*/");
        double dose_conversation_factor = Double.valueOf(df.format(remainingDoses_before_Distribution_1_1 / remainingQty_before_Distribution_1_1));
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*10.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*11.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);

        log("/*12.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();

        log("/*13.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();

        log("/*14.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();

        log("/*15.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*16----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*17----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions);

        log("/*18----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*19----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        //double[] remDosesQtyConversionFactorAfter_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*20----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        //double[] remDosesQtyConversionFactorAfter_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);;
        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*21.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        assertEquals((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjust), remainingDoses_after_Distribution_1_1);

        double remainingQuantityCalculationDist1 = Double.parseDouble(df.format(
                (remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjust)/ dose_conversation_factor));
        assertEquals(remainingQuantityCalculationDist1, remainingQty_after_Distribution_1_1);

        log("/*22.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjust), remainingDoses_after_Distribution_1_2);

        double remainingQuantityCalculationDist2 = Double.parseDouble(df.format(
                (remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjust)/ dose_conversation_factor));
        assertEquals(remainingQuantityCalculationDist2, remainingQty_after_Distribution_1_2);
    }

  
    @Test()
    public void Can_Do_Single_Draft_Edit_ByDosages_Within_The_Same_Clinic() throws Exception {
        log("Test Case#2 Edit draft and transfer");
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        double amountOfDosesToAdjust = 10;
        double amountOfDosesToAdjustInDraftEdit = 5;
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login AS comunityqa_org_immsbc_admin");
                TestcaseID = "244847"; //C244847
                loginPage.loginAsImmsBCAdminICE();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "223358"; //C223358
                loginPage.loginAsPPHIS();
        }

        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        supplyConsolePage = new SupplyConsolePage(driver);
        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_from);
        Thread.sleep(5000);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");

        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);
        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*7.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(500);
        log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
        try {
            supplyConsolePage.selectTransferFromDropDown();
        } catch (ElementNotInteractableException ex) {
            System.out.println("*** WARNING*** Couldn't Select the Action. Try to click Action button again...");
            supplyConsolePage.clickOnFirstContainerDropDownMenu();
            Thread.sleep(500);
            supplyConsolePage.selectTransferFromDropDown();
        }

        log("/*9.----Picked up the Dose Conversation Factor --*/");
        double dose_conversation_factor = Double.valueOf(df.format(remainingDoses_before_Distribution_1_1 / remainingQty_before_Distribution_1_1));
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*10.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*11.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);

        log("/*12.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();

        log("/*13.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();

        log("/*14.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();

        log("/*15.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*16----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*17----Selecting the latest draft transactions and Edit, update doses: " +amountOfDosesToAdjustInDraftEdit +" --*/");
        supplyConsolePage.clickDropDownLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions, amountOfDosesToAdjustInDraftEdit);

        log("/*18----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*18.1----Refresh Page to get doses/quantities updated--*/");
        supplyConsolePage.refreshBrowser();

        log("/*19----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
            log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);

        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
            log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*20----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");

        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
            log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);

        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
            log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*21.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        log("Distribution_1_1 Compering before transfer doses " +remainingDoses_before_Distribution_1_1 + " amount to adjust upon edit "
                +amountOfDosesToAdjustInDraftEdit + " calculation of reminder " +(remainingDoses_before_Distribution_1_1- amountOfDosesToAdjust)
                + " vs actual doses after adjustment " +remainingDoses_after_Distribution_1_1);
        assertEquals((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjustInDraftEdit), remainingDoses_after_Distribution_1_1);


        log("Distribution_1_1 Compering before transfer quantities vs actual quantities after adjustment " +remainingDoses_after_Distribution_1_1);
        double remainingQuantitiesBeforeDist1 = Double.parseDouble(df.format(
                (remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjustInDraftEdit) / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist1, remainingQty_after_Distribution_1_1);

        log("/*22.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjustInDraftEdit), remainingDoses_after_Distribution_1_2);

        double remainingQuantitiesBeforeDist2 = Double.parseDouble(df.format(
                (remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjustInDraftEdit) / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist2, remainingQty_after_Distribution_1_2);
    }

    @Test()
    public void Can_Do_Single_Draft_Cancel_ByDosages_Within_The_Same_Clinic() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case#3 Create draft and cancel it");
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        double amountOfDosesToAdjust = 10;
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login AS comunityqa_org_immsbc_admin");
                TestcaseID = "244847"; //C244847
                loginPage.loginAsImmsBCAdmin_DIWA_ICE();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "223358"; //C223358
                loginPage.loginAsPPHIS();
        }

        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_from);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
            log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
            log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
            log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
            log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*8.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(500);
        log("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
        try {
            supplyConsolePage.selectTransferFromDropDown();
        } catch (ElementNotInteractableException ex) {
            System.out.println("*** WARNING*** Couldn't Select the Action. Try to click Action button again...");
            supplyConsolePage.clickOnFirstContainerDropDownMenu();
            Thread.sleep(500);
            supplyConsolePage.selectTransferFromDropDown();
        }

        log("/*10.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = Double.valueOf(df.format(remainingDoses_before_Distribution_1_1 / remainingQty_before_Distribution_1_1));
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*11.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*12.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);;

        log("/*13.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();

        log("/*14.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();

        log("/*15.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();

        log("/*16.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*17----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*18----Selecting the latest draft transactions and Edit, update doses: --*/");
        supplyConsolePage.clickDropDownLatestDraftTransactionsAndCancelTransfer(countDraftTransactions);

        log("/*19----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*20----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
            log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);

        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
            log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*21----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
            log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);

        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
            log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*22.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        assertEquals(remainingDoses_before_Distribution_1_1, remainingDoses_after_Distribution_1_1);

        double remainingQuantitiesBeforeDist1 = Double.parseDouble(df.format(
                remainingDoses_before_Distribution_1_1 / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist1, remainingQty_after_Distribution_1_1);

        log("/*23.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals(remainingDoses_before_Distribution_1_2, remainingDoses_after_Distribution_1_2);

        double remainingQuantitiesBeforeDist2 = Double.parseDouble(df.format(
                remainingDoses_before_Distribution_1_2 / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist2, remainingQty_after_Distribution_1_2);
    }

    @Test()
    public void Can_Do_Single_Draft_ByQuantity_Within_The_Same_Clinic() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case#4 save draft and transfer after by quantity");
        double amountOfQuantityToAdjust = 1;
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        log("/*----Amount Adjustment Quantity " + amountOfQuantityToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login AS comunityqa_org_immsbc_admin");
                TestcaseID = "244847"; //C244847
                loginPage.loginAsImmsBCAdmin_DIWA_ICE();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "223358"; //C223358
                loginPage.loginAsPPHIS();
        }

        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(supply_location_from);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*7.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(500);
        log("/*8.----select Transfer from the DropDownMenu dropdown menu --*/");
        try {
            supplyConsolePage.selectTransferFromDropDown();
        } catch (ElementNotInteractableException ex) {
            System.out.println("*** WARNING*** Couldn't Select the Action. Try to click Action button again...");
            supplyConsolePage.clickOnFirstContainerDropDownMenu();
            Thread.sleep(500);
            supplyConsolePage.selectTransferFromDropDown();
        }

        log("/*9.----Picked up the Dose Conversation Factor --*/");
        double dose_conversation_factor = Double.valueOf(df.format(remainingDoses_before_Distribution_1_1 / remainingQty_before_Distribution_1_1));
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*10.----Entering quantity in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferQuantity(String.valueOf(amountOfQuantityToAdjust));

        log("/*11.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocationToFromDropdown(supply_location_from);

        log("/*12.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();

        log("/*13.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();

        log("/*14.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersDialogCloseButton();

        log("/*15.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*16----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*17----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions);

        log("/*18----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*19----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses(container_from, distribution_from);
        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty(container_from, distribution_from);
        log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*20----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDoses(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQty(container_to_same_clinic, distribution_to_same_clinic);
        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*21.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        log("----Validation by Quantities --");
        assertEquals((remainingQty_before_Distribution_1_1 - amountOfQuantityToAdjust), remainingQty_after_Distribution_1_1);
        log("----Validation by Doses --");
        double remainingDosesCalculationDist1 = Double.parseDouble(df.format(
                        (remainingQty_before_Distribution_1_1 - amountOfQuantityToAdjust) * dose_conversation_factor));
        assertEquals(remainingDosesCalculationDist1, remainingDoses_after_Distribution_1_1);

        log("/*22.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        log("----Validation by Quantities --");
        assertEquals((remainingQty_before_Distribution_1_2 + amountOfQuantityToAdjust), remainingQty_after_Distribution_1_2);
        log("----Validation by Doses --");
        double remainingDosesCalculationDist2 = Double.parseDouble(df.format(
                (remainingQty_before_Distribution_1_2 + amountOfQuantityToAdjust) * dose_conversation_factor));
        assertEquals(remainingDosesCalculationDist2, remainingDoses_after_Distribution_1_2);
    }

}