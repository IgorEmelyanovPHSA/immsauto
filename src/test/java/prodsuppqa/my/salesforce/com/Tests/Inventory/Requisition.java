package prodsuppqa.my.salesforce.com.Tests.Inventory;

import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class Requisition extends BaseTest {

    @Test
    public void Create_Requisition_as_PPHIS_PRODSUPPQA() throws InterruptedException{

        System.out.println("/*----Login as an PPHIS_prodsuppqa to Supply Console --*/");
        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        sleep(10000);
        System.out.println("/*----Login as an PPHIS_prodsuppqa to Supply Console --*/");
        supplyConsolePage.clickSupplyLocations();
        System.out.println("/*----Locate Supply Location --*/");
        //supplyConsolePage.clickClickViewAllBtn();
        //System.out.println("/*----View all Supply Location --*/");
        //supplyConsolePage.clickFirstRow();
        //System.out.println("/*----Choose One Supply Location --*/");







    }





}
