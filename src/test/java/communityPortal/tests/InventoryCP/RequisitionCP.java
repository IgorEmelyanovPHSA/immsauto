package communityPortal.tests.InventoryCP;

import bcvax.pages.CommunityPortalMainPage;
import bcvax.tests.BaseTest;
import bcvax.pages.Utils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import bcvax.pages.SupplyConsolePage;

import java.util.Map;


public class RequisitionCP extends BaseTest {
    CommunityPortalMainPage communityPortalMainPage;
    SupplyConsolePage supplyConsolePage;
    Map<String, Object> testData;
    String env;
    @Test
    public void Create_Requisition_as_an_PPHIS() throws Exception {
        TestcaseID = "222344"; //C222344
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        //String supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        String supply_location_from = "Age 12 and Above - Abbotsford - Abby Pharmacy";
        testData = Utils.getTestData(env);
        log("Target Environment: "+ Utils.getTargetEnvironment());
        System.out.println("/*----1. Login as an PPHIS_BCVAXDEVIT to Supply Console --*/");
        communityPortalMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();supplyConsolePage = communityPortalMainPage.navigateToSupplyLocation(supply_location_from);
        System.out.println("/*----2. Locate Dropdown Menu --*/");
        //supplyConsolePage.verifyIsSupplyPageDisplayed();
        //Thread.sleep(4000);
        System.out.println("/*----3. Close Other Tabs --*/");
        //supplyConsolePage.closeTabsHCA();
        //Thread.sleep(4000);
        System.out.println("/*----4. Navigate to Supply Locations --*/");
        //supplyConsolePage.clickSupplyLocationsTab();
        //Thread.sleep(6000);
        System.out.println("/*----5. Locate on Age 12 and Above - Abbotsford - Abby Pharmacy --*/");
        //supplyConsolePage.clickOnSupplyLocation(supply_location_from);
        //Thread.sleep(4000);
        System.out.println("/*----6. Navigate to Request Supplies --*/");
        supplyConsolePage.clickRequestSupplies();
        System.out.println("/*----7. select Shipped From-'All ages-Atlin Health Centre' --*/");
        supplyConsolePage.selectShipped_From();
        //requisitionPage.inputShipAddress();
        //System.out.println("/*----8. Locate on Automation Supply Location_1 --*/");
        //requisitionPage.LocateAddress("Atlin Health Centre");
        System.out.println("/*----9. Choose Requested Delivery Date --*/");
        supplyConsolePage.inputRequestDate();
        System.out.println("/*----10. Choose Urgency --*/");
        supplyConsolePage.clickNextButton();
        log("/*----11. Select requested Trades from Add Requisition Line Items  --*/");
        log("/*--SPIKEVAX (Moderna) COVID-19 mRNA Moderna mRNA-1273 7mL 14-dose vial Lot 016F21A-CC07--*/");
        //log("/*for prodsuppqa --SPIKEVAX (Moderna) COVID-19 mRNA Moderna mRNA-1273 7mL 14-dose vial Lot 016F21A-CC07--*/");
        //log("/*for bcvaxdevit --COVID-19 mRNA COMIRNATY Pediatric 10mcg (Pfizer) Orange Cap 2mL 10-dose vial - FK5618-CC03 (2022-12-13 16:07:54) Lot FK5618-CC0 --*/");
        int itemNum = 7;
        supplyConsolePage.clickLineItemCheckBox(itemNum);
        log("/*----12. click Next button --*/");
        supplyConsolePage.clickNextButton();
        System.out.println("/*----13. Input Requested Quantity and Doses --*/");
        supplyConsolePage.inputRequestedQuantity("1");
        System.out.println("/*----14. Save Quantity and Doses --*/");
        supplyConsolePage.clickSaveButton();
        System.out.println("/*----15. Submit Requisition --*/");
        supplyConsolePage.clickSubmitRequisition();
        System.out.println("/*----16. Confirm and Save Requisition --*/");
        supplyConsolePage.clickSaveSubmitRequisition();
        System.out.println("/*----17. Click Edit Expected Delivery Date--*/");
        supplyConsolePage.clickEditExpectedDeliveryDate();
        //System.out.println("/*----18. Click Calender of Expected Delivery Date--*/");
        //supplyConsolePage.clickExpectedDeliveryDateCalendar();
        //Thread.sleep(3000);
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

