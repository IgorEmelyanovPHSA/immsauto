package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

import static bcvaxdevit.my.salesforce.com.Pages.BasePage.log;
import static org.testng.Assert.assertEquals;

public class Wastage extends BaseTest {

    @Test
    public void inventoryManagementWastage_ByDosagesAS_PPHIS_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "222357"; //Need to update TestCaseID //C219942 Python -> Java
        double wastageDosesAmount = 3;
        log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");
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
        log("/*6.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();
        Thread.sleep(2000);
        log("/*7.----select Wastage from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectWastageFromDropDown();

        double remainingDosesBeforeWestage = supplyConsolePage.getActualRemainingDoses();
        log("/*----Quantity Remaining Doses Before Wastage " +remainingDosesBeforeWestage +" --*/");
        log("/*----Quantity Wastage Doses " +wastageDosesAmount +" --*/");
        log("/*8.----set Wastage Doses amount --*/");
        supplyConsolePage.setDosesAmountToWaste(Double.toString(wastageDosesAmount));
        double remainingDosesAfterWestage = supplyConsolePage.getActualRemainingDoses();
        log("/*----Quantity Remaining Doses After Wastage " +remainingDosesAfterWestage +" --*/");
        log("/*9.----Reason For Wastage Wastage: 'CCI: Handling Error' --*/");
        supplyConsolePage.selectReasonForWastageDropDown();
        supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
        log("/*10----Clicking on btn Wastage --*/");
        assertEquals((remainingDosesBeforeWestage-wastageDosesAmount), remainingDosesAfterWestage);
        log("/*.----Extra steps to double check the actual amount of doses --*/");
        log("/*11.----Click on Container's dropdown --*/");
        supplyConsolePage.clickOnContainerDropDownMenu();
        log("/*12.----select Wastage from the DropDownMenu dropdown menu --*/");
        supplyConsolePage.selectWastageFromDropDown();
        double actualDosesAmount = supplyConsolePage.getActualRemainingDoses();
        log("/*----Acutla Quantity Doses " +actualDosesAmount +" --*/");
        assertEquals(actualDosesAmount, remainingDosesAfterWestage);
    }
}