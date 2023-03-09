package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.CommonMethods;
import bcvax.pages.MainPageCP;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

import static constansts.Domain.*;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class DraftsCP extends BaseTest {

    private final String supplyLocationFrom = SUPPLY_LOCATION_1;

    @Test()
    public void CP_Can_Do_Single_Draft_ByDosages_Within_The_Same_Clinic() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case#1 save draft and transfer after");
        CommonMethods common = new CommonMethods(getDriver());
        MainPageCP cpMainPage = new MainPageCP(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        double amountOfDosesToAdjust = 10;
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login AS comunityqa_immsbc_admin");
                TestcaseID = "245092"; //C245092
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login AS default user (ClinicianInventory)");
                TestcaseID = "243118"; //C243118
                loginPage.loginIntoCommunityPortalAsClinicianInventory();
                Thread.sleep(10000);
        }

        log("/*2.----Navigate to Supply Console Page --*/");
        cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get a matching row for first row Lot number --*/");
        int matchedRow = common.getMatchedRowToLotInRow1();

        log("/*4.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[0];
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[1];
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[0];
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[1];
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*6.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*7.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        log("/*8.----Picked up the Dose Conversation Factor --*/");
        double dose_conversation_factor = remDosesQtyConversionFactorBefore_Distribution_1_2[2];
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*9.----Entering Doses amount, select supply location and Save as Draft -*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust)).
                draftToDistributionWithinSameClinic(supplyLocationFrom, SUPPLY_DISTRIBUTION_1_2);

        log("/*10.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*11.----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*12.----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions);
        Thread.sleep(3000);

        log("/*13.----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*14.----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[0];
        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
        double remainingQty_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[1];
        log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*15.----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[0];
        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
        double remainingQty_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[1];
        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*16.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        assertEquals((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjust), remainingDoses_after_Distribution_1_1);

        double remainingQuantityCalculationDist1 = Double.parseDouble(new DecimalFormat("##.####").format(
                (remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjust)/ dose_conversation_factor));
        assertEquals(remainingQuantityCalculationDist1, remainingQty_after_Distribution_1_1);

        log("/*17.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjust), remainingDoses_after_Distribution_1_2);

        double remainingQuantityCalculationDist2 = Double.parseDouble(new DecimalFormat("##.####").format(
                (remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjust)/ dose_conversation_factor));
        assertEquals(remainingQuantityCalculationDist2, remainingQty_after_Distribution_1_2);
    }

  
    @Test()
    public void CP_Can_Do_Single_Draft_Edit_ByDosages_Within_The_Same_Clinic() throws Exception {
        log("Test Case#2 Edit draft and transfer");
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        double amountOfDosesToAdjust = 10;
        double amountOfDosesToAdjustInDraftEdit = 5;
        CommonMethods common = new CommonMethods(getDriver());
        MainPageCP cpMainPage = new MainPageCP(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login AS comunityqa_immsbc_admin");
                TestcaseID = "245092"; //C245092
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login AS default user (ClinicianInventory)");
                TestcaseID = "243118"; //C243118
                loginPage.loginIntoCommunityPortalAsClinicianInventory();
                Thread.sleep(10000);
        }

        log("/*2.----Navigate to Supply Console Page --*/");
        cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get a matching row for first row Lot number --*/");
        int matchedRow = common.getMatchedRowToLotInRow1();

        log("/*4.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[0];
            log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[1];
            log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[0];
            log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[1];
            log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*6.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*7.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        log("/*8.----Picked up the Dose Conversation Factor --*/");
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*9.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*10.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocation_1_To();
        Thread.sleep(2000);

        log("/*11.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();
        Thread.sleep(2000);

        log("/*12.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
        Thread.sleep(2000);

        log("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();
        Thread.sleep(5000);

        log("/*14.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*15.----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*16.----Selecting the latest draft transactions and Edit, update doses: " +amountOfDosesToAdjustInDraftEdit +" --*/");
        supplyConsolePage.clickDropDownLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions, amountOfDosesToAdjustInDraftEdit);

        log("/*17.----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*18.----Refresh Page to get doses/quantities updated--*/");
        common.refreshBrowser();
        Thread.sleep(9000);

        log("/*19.----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[0];
            log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);

        double remainingQty_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[1];
            log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*20.----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[0];
            log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);

        double remainingQty_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[1];
            log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*21.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        log("Distribution_1_1 Compering before transfer doses " +remainingDoses_before_Distribution_1_1 + " amount to adjust upon edit "
                +amountOfDosesToAdjustInDraftEdit + " calculation of reminder " +(remainingDoses_before_Distribution_1_1- amountOfDosesToAdjust)
                + " vs actual doses after adjustment " +remainingDoses_after_Distribution_1_1);
        assertEquals((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjustInDraftEdit), remainingDoses_after_Distribution_1_1);


        log("Distribution_1_1 Compering before transfer quantities vs actual quantities after adjustment " +remainingDoses_after_Distribution_1_1);
        double remainingQuantitiesBeforeDist1 = Double.parseDouble(new DecimalFormat("##.####").format(
                (remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjustInDraftEdit) / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist1, remainingQty_after_Distribution_1_1);

        log("/*22.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjustInDraftEdit), remainingDoses_after_Distribution_1_2);

        double remainingQuantitiesBeforeDist2 = Double.parseDouble(new DecimalFormat("##.####").format(
                (remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjustInDraftEdit) / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist2, remainingQty_after_Distribution_1_2);
    }

    @Test()
    public void CP_Can_Do_Single_Draft_Cancel_ByDosages_Within_The_Same_Clinic() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case#3 Create draft and cancel it");
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        double amountOfDosesToAdjust = 10;
        CommonMethods common = new CommonMethods(getDriver());
        MainPageCP cpMainPage = new MainPageCP(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login AS comunityqa_immsbc_admin");
                TestcaseID = "245092"; //C245092
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login AS default user (ClinicianInventory)");
                TestcaseID = "243118"; //C243118
                loginPage.loginIntoCommunityPortalAsClinicianInventory();
                Thread.sleep(10000);
        }

        log("/*2.----Navigate to Supply Console Page --*/");
        cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get a matching row for first row Lot number --*/");
        int matchedRow = common.getMatchedRowToLotInRow1();

        log("/*4.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[0];
            log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[1];
            log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[0];
            log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[1];
            log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*6.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*7.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        log("/*8.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*9.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*10.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocation_1_To();;
        Thread.sleep(2000);

        log("/*11.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();
        Thread.sleep(2000);

        log("/*12.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
        Thread.sleep(2000);

        log("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();
        Thread.sleep(5000);

        log("/*14.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*15----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*16----Selecting the latest draft transactions and Edit, update doses: --*/");
        supplyConsolePage.clickDropDownLatestDraftTransactionsAndCancelTransfer(countDraftTransactions);

        log("/*17----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[0];
            log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);

        double remainingQty_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[1];
            log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*19----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[0];
            log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);

        double remainingQty_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[1];
            log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        assertEquals(remainingDoses_before_Distribution_1_1, remainingDoses_after_Distribution_1_1);

        double remainingQuantitiesBeforeDist1 = Double.parseDouble(new DecimalFormat("##.####").format(
                remainingDoses_before_Distribution_1_1 / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist1, remainingQty_after_Distribution_1_1);

        log("/*21.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals(remainingDoses_before_Distribution_1_2, remainingDoses_after_Distribution_1_2);

        double remainingQuantitiesBeforeDist2 = Double.parseDouble(new DecimalFormat("##.####").format(
                remainingDoses_before_Distribution_1_2 / dose_conversation_factor));
        assertEquals(remainingQuantitiesBeforeDist2, remainingQty_after_Distribution_1_2);
    }

    @Test()
    public void CP_Can_Do_Single_Draft_ByQuantity_Within_The_Same_Clinic() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("Test Case#4 save draft and transfer after by quantity");
        CommonMethods common = new CommonMethods(getDriver());
        MainPageCP cpMainPage = new MainPageCP(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        double amountOfQuantityToAdjust = 1;
        int firstRow = 1; //Default value for first row in the grid (Supply container)
        log("/*----Amount Adjustment Quantity " + amountOfQuantityToAdjust + " --*/");

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login AS comunityqa_immsbc_admin");
                TestcaseID = "245092"; //C245092
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login AS default user (ClinicianInventory)");
                TestcaseID = "243118"; //C243118
                loginPage.loginIntoCommunityPortalAsClinicianInventory();
                Thread.sleep(10000);
        }

        log("/*2.----Navigate to Supply Console Page --*/");
        cpMainPage.navigateToSupplyConsolePage();

        log("/*3.----Get a matching row for first row Lot number --*/");
        int matchedRow = common.getMatchedRowToLotInRow1();

        log("/*4.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[0];
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = remDosesQtyConversionFactorBefore_Distribution_1_1[1];
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*5.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorBefore_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[0];
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = remDosesQtyConversionFactorBefore_Distribution_1_2[1];
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*6.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnFirstContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*7.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        log("/*8.----Picked up the Dose Conversation Factor --*/");
        double dose_conversation_factor = remDosesQtyConversionFactorBefore_Distribution_1_2[2];
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*9.----Entering quantity in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferQuantity(String.valueOf(amountOfQuantityToAdjust));

        log("/*10.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocation_1_To();
        Thread.sleep(2000);

        log("/*11.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistribution();
        Thread.sleep(2000);

        log("/*12.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
        Thread.sleep(2000);

        log("/*13.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();
        Thread.sleep(5000);

        log("/*14.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*15----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*16----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions);
        Thread.sleep(3000);

        log("/*17----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*18----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_1 = common.getRemainingDosesQtyAndConversionFactor(firstRow);

        double remainingDoses_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[0];
        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
        double remainingQty_after_Distribution_1_1 = remDosesQtyConversionFactorAfter_Distribution_1_1[1];
        log("/*-- . remaining Quantity Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*19----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double[] remDosesQtyConversionFactorAfter_Distribution_1_2 = common.getRemainingDosesQtyAndConversionFactor(matchedRow);

        double remainingDoses_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[0];
        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
        double remainingQty_after_Distribution_1_2 = remDosesQtyConversionFactorAfter_Distribution_1_2[1];
        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*20.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        log("----Validation by Quantities --");
        assertEquals((remainingQty_before_Distribution_1_1 - amountOfQuantityToAdjust), remainingQty_after_Distribution_1_1);
        log("----Validation by Doses --");
        double remainingDosesCalculationDist1 = Double.parseDouble(new DecimalFormat("##.####").format(
                        (remainingQty_before_Distribution_1_1 - amountOfQuantityToAdjust) * dose_conversation_factor));
        assertEquals(remainingDosesCalculationDist1, remainingDoses_after_Distribution_1_1);

        log("/*21.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        log("----Validation by Quantities --");
        assertEquals((remainingQty_before_Distribution_1_2 + amountOfQuantityToAdjust), remainingQty_after_Distribution_1_2);
        log("----Validation by Doses --");
        double remainingDosesCalculationDist2 = Double.parseDouble(new DecimalFormat("##.####").format(
                (remainingQty_before_Distribution_1_2 + amountOfQuantityToAdjust) * dose_conversation_factor));
        assertEquals(remainingDosesCalculationDist2, remainingDoses_after_Distribution_1_2);
    }

}