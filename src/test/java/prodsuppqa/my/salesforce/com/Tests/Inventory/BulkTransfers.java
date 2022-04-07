package prodsuppqa.my.salesforce.com.Tests.Inventory;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.InClinicExperiencePage;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class BulkTransfers extends BaseTest {

    @Test
    public void Can_do_Bulk_transfers_as_PPHIS_PRODSUPPQA() throws InterruptedException {
        System.out.println("/*----Login as an PPHIS_prodsuppqa to Supply Console --*/");
        SupplyConsolePage supplyConsolePagePage = loginPage.loginAsPPHIS();
        System.out.println("/*----Go to Supply Locations Tab --*/");
        supplyConsolePagePage.clickSupplyLocationsTab();
        //Thread.sleep(10000);
        System.out.println("/*----Click on Automation Supply Location_1 --*/");
        supplyConsolePagePage.clickOnSupplyLocations();
        Thread.sleep(5000);
        System.out.println("/*----Get count records --*/");
        int count = supplyConsolePagePage.getRowsCount();
        System.out.println("count:" +count);
        System.out.println("/*----Click on Containers records Checkbox --*/");
        if (count >= 3){
            int k=2;
            while (k <= 4){
                supplyConsolePagePage.clickOnCheckbox(k);
                System.out.println("/*--- record number --*/" +k);
                Thread.sleep(5000);
                k++;
            }
        }else {
            System.out.println("/*--not enough records for Bulk actions--*/");
        }

        //Thread.sleep(10000);




    }




}
