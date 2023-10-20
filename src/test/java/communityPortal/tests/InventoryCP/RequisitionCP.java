package communityPortal.tests.InventoryCP;

import bcvax.pages.MainPageCP;
import bcvax.pages.MainPageOrg;
import bcvax.tests.BaseTest;
import bcvax.pages.Utils;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.Test;
import bcvax.pages.SupplyConsolePage;

import java.util.Map;


public class RequisitionCP extends BaseTest {
    MainPageCP cpMainPage;
    MainPageOrg orgMainPage;
    SupplyConsolePage supplyConsolePage;
    Map<String, Object> testData;
    String env;
    @Test
    public void Create_Requisition_as_an_Clinician_CP() throws Exception {
        env = Utils.getTargetEnvironment();
        TestcaseID = (env.contains("immsbc_admin")) ? "245095" : "243087"; //C243087
        testData = Utils.getTestData(env);
        String supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        String supply_location = String.valueOf(testData.get("supplyLocationTo"));
//        String supply_location = "Age 12 and Above - Abbotsford - Abby Pharmacy";
//        String supply_location_from = "Atlin Health Centre";
        testData = Utils.getTestData(env);
        log("Target Environment: "+ Utils.getTargetEnvironment());

        if(env.contains("immsbc_admin")) {
            log("/*1.----Login to CP (newUI) as ImmsBC_Admin --*/");
            orgMainPage = loginPage.orgLoginAsImmsBCAdminCP();
            Thread.sleep(1000);
            orgMainPage.switchApp(Apps.BCH_VACCINATION_PORTAL.value);
            Thread.sleep(1000);
            cpMainPage = new MainPageCP(driver);
            cpMainPage.clickGoToUserDefaultsButton();
        } else {
            log("/*1.----Login to CP (newUI) as Clinician --*/");
            cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();
        }
        //cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location);

        System.out.println("/*----6. Navigate to Request Supplies --*/");
        supplyConsolePage.clickRequestSupplies();
        Thread.sleep(2000);
        System.out.println("/*----7. select Shipped From-'All ages-Atlin Health Centre' --*/");
        supplyConsolePage.selectShipped_From(supply_location_from);
        System.out.println("/*----9. Choose Requested Delivery Date --*/");
        supplyConsolePage.inputRequestDate();
        System.out.println("/*----10. Choose Urgency --*/");
        supplyConsolePage.clickNextButton();
        log("/*----11. Select requested Trades from Add Requisition Line Items  --*/");
        log("/*--SPIKEVAX (Moderna) COVID-19 mRNA Moderna mRNA-1273 7mL 14-dose vial Lot 016F21A-CC07--*/");

        int itemNum = 1;
        supplyConsolePage.checkShowInStockCheckbox();
        supplyConsolePage.clickLineItemCheckBox(itemNum);
        log("/*----12. click Next button --*/");
        supplyConsolePage.clickNextButton();
        System.out.println("/*----13. Input Requested Quantity and Doses --*/");
        supplyConsolePage.inputRequestedDose("1");
        System.out.println("/*----14. Save Quantity and Doses --*/");
        supplyConsolePage.clickSaveButton();
        System.out.println("/*----15. Submit Requisition --*/");
        supplyConsolePage.clickSubmitRequisition();
        Thread.sleep(1000);
        System.out.println("/*----16. Confirm and Save Requisition --*/");
        supplyConsolePage.clickSaveSubmitRequisition();
        System.out.println("/*----17. Click Edit Expected Delivery Date--*/");
        supplyConsolePage.clickEditExpectedDeliveryDate();

        System.out.println("/*----19. Choose The Expected Delivery Date--*/");
        supplyConsolePage.inputExpectedDate();
        System.out.println("/*----20. Save Chosen Expected Delivery Date--*/");
        supplyConsolePage.clickSaveExpectedDeliveryDate();
        System.out.println("/*----21. Approve Requisition--*/");
        supplyConsolePage.clickApproveRequisition();
        supplyConsolePage.clickSaveApprovedRequisition();
        System.out.println("/*----22. Select Supply Container With Entering Approved Request Dose--*/");
        supplyConsolePage.enterApprovedDose("1");
        supplyConsolePage.enterApproverComments("test test");
        System.out.println("/*----23. Save Approved Request Dose--*/");
        supplyConsolePage.clickSaveApprovedRequisition();
        System.out.println("/*----24. Ship Requisition--*/");
        supplyConsolePage.clickShipRequisition();

        String actual = "Ship Requisition";
        Assert.assertEquals(actual, supplyConsolePage.ShipRequisition());

        System.out.println("/*----25. Save Shipping Requisition--*/");
        supplyConsolePage.clickSaveShipRequisition();
        System.out.println("/*----26. Receive Requisition--*/");
        supplyConsolePage.clickReceiveRequestBtn();
        System.out.println("/*----27. click On Search Distribution component--*/");
        supplyConsolePage.clickOnSearchSupplyDistributions();
        System.out.println("/*----28. Select Distribution -'SDST...'- --*/");
        supplyConsolePage.SelectSupplyDistributionTo();
        System.out.println("/*----29. click Save ReceiveRequisition--*/");
        supplyConsolePage.clickSaveReceiveRequisition();
        Thread.sleep(5000);
    }

}

