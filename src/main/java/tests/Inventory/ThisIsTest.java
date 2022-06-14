package tests.Inventory;


import io.qameta.allure.Story;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SupplyConsolePage;
import tests.BaseTest;
import utility.TestListener;

import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class ThisIsTest extends BaseTest {

    @Story("C222357: Wastage single dose")
    @Test(groups = {"Smoke"})
    public void testThis(ITestContext context) throws Exception {
     TestcaseID = "222357";
       context.setAttribute("testrailID", TestcaseID);

        int numberOfRows = 1; //Default value, wasting from first row only
        double amountOfDosesToWaste = 3;
        log("/*1.----Login as an PPHIS_bcvaxdevit to Supply Console --*/");

        SupplyConsolePage supplyConsolePage = loginPage.loginAsPPHIS();
        Thread.sleep(5000);

        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        Thread.sleep(5000);

//        log("failed");
//        assertTrue(false);
        log("successful");
        assertTrue(false);
    }
}
