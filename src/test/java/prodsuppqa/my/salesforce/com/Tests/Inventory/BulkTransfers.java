package prodsuppqa.my.salesforce.com.Tests.Inventory;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.InClinicExperiencePage;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class BulkTransfers extends BaseTest {

    @Test
    public void Can_do_Bulk_transfers_by_Dosages_as_PPHIS_PRODSUPPQA() throws InterruptedException {
        System.out.println("/*1.----Login as an PPHIS_prodsuppqa to Supply Console --*/");
        SupplyConsolePage supplyConsolePagePage = loginPage.loginAsPPHIS();
        System.out.println("/*2.----Go to Supply Locations Tab --*/");
        Thread.sleep(5000);
        supplyConsolePagePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);
        supplyConsolePagePage.clickSupplyLocationsTab();
        System.out.println("/*3.----Click on Automation Supply Location_1 --*/");
        supplyConsolePagePage.clickOnSupplyLocations();
        Thread.sleep(5000);
        System.out.println("/*4.----Get count records --*/");
        int count = supplyConsolePagePage.getRowsCount();
        System.out.println("/*---     count:" +count);
        System.out.println("/*5.----Click on Container's records Checkboxes --*/");
        if (count >= 3){
            int k=2;
            while (k <= 4){
                supplyConsolePagePage.clickOnCheckbox(k);
                int n=k-1;
                System.out.println("/*---     record number: " +n);
                Thread.sleep(1000);
                k++;
            }
        }else {
            System.out.println("/*--not enough records for Bulk actions--*/");
        }
        System.out.println("/*6.----Click on bulk Transfer button --*/");
        supplyConsolePagePage.clickBulkTransfersButton();
        Thread.sleep(5000);
        System.out.println("/*7.----Enter the Dosages values for 3 row Transfers --*/");
        int k=3;
        while (k <= 7){
            supplyConsolePagePage.enterBulkTransferDosages(k);
            int n=k-2;
            System.out.println("/*---     dose slot N%: " +n);
            Thread.sleep(1000);
            k=k+2;
        }
        System.out.println("/*8.----select 'To' Automation Supply Location_2  --*/");
        supplyConsolePagePage.selectSupplyLocatonTo();
        Thread.sleep(2000);
        System.out.println("/*9.----click Transfer dialog Modal button --*/");
        supplyConsolePagePage.clickBulkTransfersModalButton();
        Thread.sleep(2000);
        System.out.println("/*10.----click Close Modal button --*/");
        supplyConsolePagePage.clickBulkTransfersCloseButton();
        Thread.sleep(5000);




    }





}
