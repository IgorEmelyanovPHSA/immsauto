package prodsuppqa.my.salesforce.com.Tests.Inventory;

import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class BulkTransfers extends BaseTest {

    @Test
    public void Can_do_Bulk_transfers_as_PPHIS_PRODSUPPQA() throws InterruptedException {
        System.out.println("/*----Login as an PPHIS_prodsuppqa to Supply Console --*/");
        SupplyConsolePage supplyConsolePagePage = loginPage.loginAsPPHIS();
        sleep(10000);
        System.out.println("/*----Login as an PPHIS_prodsuppqa to Supply Console --*/");

    }




}
