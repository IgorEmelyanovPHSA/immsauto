package bcvaxuat.my.salesforce.com.Tests.Inventory;
import Utilities.TestListener;
import bcvaxuat.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Drafts extends BaseTest {

    @Test()
    public void Can_Do_Single_Draft_ByDosages_Within_The_Same_ClinicAS_PPHIS_BCVAXUAT() throws InterruptedException {
        TestcaseID = "223358"; //C223358
        log("Test Case#1 save draft and transfer after");
        double amountOfDosesToAdjust = 10;
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login as an PPHIS_bcvaxuat to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        Thread.sleep(5000);

        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeAutomationLocationTab();
        Thread.sleep(2000);

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.clickOnSupplyLocation_1();
        Thread.sleep(5000);

        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses();
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty();
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDosesDistribution_1_2();
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQtyDistribution_1_2();
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*8.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        log("/*11.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*12.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocation_1_To();
        Thread.sleep(2000);

        log("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistributionNewMethod();
        Thread.sleep(2000);

        log("/*15.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
        Thread.sleep(2000);

        log("/*16.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();
        Thread.sleep(5000);

        log("/*17.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*18----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*19----Selecting the latest draft transactions and confirm transfer --*/");
        supplyConsolePage.clickCheckBoxLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions);
        Thread.sleep(3000);

        log("/*20----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*21----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses();
        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty();
        log("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);
        log("/*22----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDosesDistribution_1_2();
        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQtyDistribution_1_2();
        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*23.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        assertEquals((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjust), remainingDoses_after_Distribution_1_1);
        assertEquals(((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjust) / dose_conversation_factor), remainingQty_after_Distribution_1_1);

        log("/*24.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjust), remainingDoses_after_Distribution_1_2);
        assertEquals(((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjust) / dose_conversation_factor), remainingQty_after_Distribution_1_2);
    }

//    TestCase is disabled due to the bug with validation, until the fix is implemented
//    @Test()
//    public void Can_Do_Single_Draft_Edit_ByDosages_Within_The_Same_ClinicAS_PPHIS_BCVAXDEVIT() throws InterruptedException {
//        TestcaseID = "222371"; //C222371
//        log("Test Case#2 Edit draft and transfer");
//        double amountOfDosesToAdjust = 10;
//        double amountOfDosesToAdjustInDraftEdit = 5;
//
//        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");
//
//        log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
//        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
//        Thread.sleep(5000);
//
//        log("/*2.----Supply Console Page displayed --*/");
//        supplyConsolePage.verifyIsSupplyPageDisplayed();
//        Thread.sleep(5000);
//
//        log("/*3.----Close All previously opened Tab's --*/");
//        supplyConsolePage.closeAutomationLocationTab();
//        Thread.sleep(2000);
//
//        log("/*4.----Go to Supply Locations Tab --*/");
//        supplyConsolePage.clickSupplyLocationsTab();
//
//        log("/*5.----Click on Automation Supply Location_1 --*/");
//        supplyConsolePage.clickOnSupplyLocation_1();
//        Thread.sleep(5000);
//
//        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
//        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses();
//        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);
//
//        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty();
//        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);
//
//        log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
//        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDosesDistribution_1_2();
//        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);
//
//        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQtyDistribution_1_2();
//        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);
//
//        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
//        log("/*8.----Click on Container's dropdown --*/");
//        supplyConsolePage.clickOnContainerDropDownMenu();
//        Thread.sleep(2000);
//
//        log("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
//        supplyConsolePage.selectTransferFromDropDown();
//        Thread.sleep(2000);
//
//        log("/*11.----Picked up the Dose Conversation Factor --*/");
//        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
//        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);
//
//        log("/*12.----Entering Doses in the Container-Transfer Form --*/");
//        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));
//
//        log("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
//        supplyConsolePage.selectSupplyLocation_1_To();;
//        Thread.sleep(2000);
//
//        log("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
//        supplyConsolePage.selectSameClinicSupplyDistribution();
//        Thread.sleep(2000);
//
//        log("/*15.----click btn Save as Draft --*/");
//        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
//        Thread.sleep(2000);
//
//        log("/*16.----click Close Modal button --*/");
//        supplyConsolePage.clickBulkTransfersCloseButton();
//        Thread.sleep(5000);
//
//        log("/*17.----Go to Transactions Tab of Automation Supply Location_1 --*/");
//        supplyConsolePage.clickTransactionsTab();
//        Thread.sleep(5000);
//
//        //Draft transaction count, offset -1
//        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
//        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
//        log("/*18----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");
//
//        log("/*19----Selecting the latest draft transactions and Edit, update doses: " +amountOfDosesToAdjustInDraftEdit +" --*/");
//        supplyConsolePage.clickDropDownLatestDraftTransactionsAndConfirmTransfer(countDraftTransactions, amountOfDosesToAdjustInDraftEdit);
//
//        log("/*20----Navigate to Related Item tab --*/");
//        supplyConsolePage.clickOnRelatedItemTab();
//
//        log("/*21----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
//        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses();
//        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
//        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty();
//        log("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);
//        log("/*22----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
//        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDosesDistribution_1_2();
//        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
//        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQtyDistribution_1_2();
//        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);
//
//        log("/*23.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
//        log("Distribution_1_1 Compering before transfer doses " +remainingDoses_before_Distribution_1_1 + " amount to adjust upon edit "
//                +amountOfDosesToAdjustInDraftEdit + " calculation of reminder " +(remainingDoses_before_Distribution_1_1- amountOfDosesToAdjust)
//                + " vs actual doses after adjustment " +remainingDoses_after_Distribution_1_1);
//        assertEquals((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjustInDraftEdit), remainingDoses_after_Distribution_1_1);
//
//        log("Distribution_1_1 Compering before transfer quantities " +remainingDoses_before_Distribution_1_1 + "amount to adjust upon edit "
//                +amountOfDosesToAdjustInDraftEdit + " calculation of reminder " +((remainingDoses_before_Distribution_1_1- amountOfDosesToAdjust)/dose_conversation_factor)
//                + " vs actual quantities after adjustment " +remainingDoses_after_Distribution_1_1);
//        assertEquals(((remainingDoses_before_Distribution_1_1 - amountOfDosesToAdjustInDraftEdit) / dose_conversation_factor), remainingQty_after_Distribution_1_1);
//
//        log("/*24.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
//        assertEquals((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjustInDraftEdit), remainingDoses_after_Distribution_1_2);
//        assertEquals(((remainingDoses_before_Distribution_1_2 + amountOfDosesToAdjustInDraftEdit) / dose_conversation_factor), remainingQty_after_Distribution_1_2);
//    }

    @Test()
    public void Can_Do_Single_Draft_Cancel_ByDosages_Within_The_Same_ClinicAS_PPHIS_BCVAXUAT() throws InterruptedException {
        TestcaseID = "222371"; //C222371
        log("Test Case#3 Create draft and cancel it");
        double amountOfDosesToAdjust = 10;
        log("/*----Amount Adjustment Doses " + amountOfDosesToAdjust + " --*/");

        log("/*1.----Login as an PPHIS_bcvaxuat to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        Thread.sleep(5000);

        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);

        log("/*3.----Close All previously opened Tab's --*/");
        supplyConsolePage.closeAutomationLocationTab();
        Thread.sleep(2000);

        log("/*4.----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();

        log("/*5.----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.clickOnSupplyLocation_1();
        Thread.sleep(5000);

        log("/*6.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_1 --*/");
        double remainingDoses_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses();
        log("/*-- . Distribution_1_1 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_1);

        double remainingQty_before_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty();
        log("/*-- . Distribution_1_1 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_1);

        log("/*7.----Quantity Remaining Doses/Remaining Quantity check Before for Distribution_1_2 --*/");
        double remainingDoses_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDosesDistribution_1_2();
        log("/*-- . Distribution_1_2 remaining doses Before are: -->" + remainingDoses_before_Distribution_1_2);

        double remainingQty_before_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQtyDistribution_1_2();
        log("/*-- . Distribution_1_2 remaining Quantity Before are: -->" + remainingQty_before_Distribution_1_2);

        /////////Do Transfer from Distribution_1_1 to Distribution_1_2/////////
        log("/*8.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();
        Thread.sleep(2000);

        log("/*9.----select Transfer from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectTransferFromDropDown();
        Thread.sleep(2000);

        log("/*11.----Picked up the Dose Conversation Factor --*/");
        //Double dose_conversation_factor = 5.0;
        double dose_conversation_factor = supplyConsolePage.getDoseConversationFactor();
        log("/*--  the Dose Conversation Factor is:  " + dose_conversation_factor);

        log("/*12.----Entering Doses in the Container-Transfer Form --*/");
        supplyConsolePage.enterTransferDosages(String.valueOf(amountOfDosesToAdjust));

        log("/*13.----select 'To' 'Automation Supply Location_1'  --*/");
        supplyConsolePage.selectSupplyLocation_1_To();
        Thread.sleep(2000);

        log("/*14.----select 'Supply Distribution_1_2' 'To'  --*/");
        supplyConsolePage.selectSameClinicSupplyDistributionNewMethod();
        Thread.sleep(2000);

        log("/*15.----click btn Save as Draft --*/");
        supplyConsolePage.clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
        Thread.sleep(2000);

        log("/*16.----click Close Modal button --*/");
        supplyConsolePage.clickBulkTransfersCloseButton();
        Thread.sleep(5000);

        log("/*17.----Go to Transactions Tab of Automation Supply Location_1 --*/");
        supplyConsolePage.clickTransactionsTab();
        Thread.sleep(5000);

        //Draft transaction count, offset -1
        int countDraftTransactions = supplyConsolePage.getRowsDraftTransactionsCount();
        String latestDraftTransactionId = supplyConsolePage.getLatestDraftTransactionId(countDraftTransactions);
        log("/*18----Getting id for the latest created Transaction Draft " +latestDraftTransactionId +" --*/");

        log("/*19----Selecting the latest draft transactions and Edit, update doses: --*/");
        supplyConsolePage.clickDropDownLatestDraftTransactionsAndCancelTransfer(countDraftTransactions);

        log("/*20----Navigate to Related Item tab --*/");
        supplyConsolePage.clickOnRelatedItemTab();

        log("/*21----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_1 --*/");
        double remainingDoses_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingDoses();
        log("/*-- . remaining doses Distribution_1_1 After are: -->" + remainingDoses_after_Distribution_1_1);
        double remainingQty_after_Distribution_1_1 = supplyConsolePage.getValueOfRemainingQty();
        log("/*-- . remaining Quantity  Distribution_1_1 After are: -->" + remainingQty_after_Distribution_1_1);

        log("/*22----Quantity Remaining Doses/Remaining Quantity check After for Distribution_1_2 --*/");
        double remainingDoses_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingDosesDistribution_1_2();
        log("/*-- . remaining doses  Distribution_1_2 After are: -->" + remainingDoses_after_Distribution_1_2);
        double remainingQty_after_Distribution_1_2 = supplyConsolePage.getValueOfRemainingQtyDistribution_1_2();
        log("/*-- . remaining Quantity  Distribution_1_2 After are: -->" + remainingQty_after_Distribution_1_2);

        log("/*23.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_1 --*/");
        assertEquals(remainingDoses_before_Distribution_1_1, remainingDoses_after_Distribution_1_1);
        assertEquals((remainingDoses_before_Distribution_1_1 / dose_conversation_factor), remainingQty_after_Distribution_1_1);

        log("/*24.----Validate Remaining Doses and Remaining Quantities values for Distribution_1_2 --*/");
        assertEquals(remainingDoses_before_Distribution_1_2, remainingDoses_after_Distribution_1_2);
        assertEquals((remainingDoses_before_Distribution_1_2 / dose_conversation_factor), remainingQty_after_Distribution_1_2);
    }

}
