package communityPortal.tests.InventoryCP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        log("/*1.----Login to CP (newUI) as Clinician --*/");
        cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        //cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        supplyConsolePage = cpMainPage.selectSupplyLocationName(supply_location);

        System.out.println("/*----6. Navigate to Request Supplies --*/");
        SupplyLocationPage.clickCreateRequisitionButton(driver);
        Thread.sleep(2000);
        System.out.println("/*----7. select Shipped From-'All ages-Atlin Health Centre' --*/");
        CreateRequisitionForm.selectShipped_From(driver, supply_location_from);
        System.out.println("/*----9. Choose Requested Delivery Date --*/");
        CreateRequisitionForm.inputRequestDate(driver);
        System.out.println("/*----10. Choose Urgency --*/");
        CreateRequisitionForm.clickNextButton(driver);
        log("/*----11. Select requested Trades from Add Requisition Line Items  --*/");
        log("/*--SPIKEVAX (Moderna) COVID-19 mRNA Moderna mRNA-1273 7mL 14-dose vial Lot 016F21A-CC07--*/");

        int itemNum = 1;
        AddRequisitionLineItemsForm.checkShowInStockCheckbox(driver);
        AddRequisitionLineItemsForm.clickLineItemCheckBox(driver, itemNum);
        log("/*----12. click Next button --*/");
        AddRequisitionLineItemsForm.clickNextButton(driver);
        System.out.println("/*----13. Input Requested Quantity and Doses --*/");
        AddRequisitionLineItemsForm.inputRequestedDose(driver, "1");
        System.out.println("/*----14. Save Quantity and Doses --*/");
        AddRequisitionLineItemsForm.clickSaveButton(driver);
        System.out.println("/*----15. Submit Requisition --*/");
        RequisitionPage.clickSubmitRequisition(driver);
        System.out.println("/*----16. Confirm and Save Requisition --*/");
        SubmitRequisitionDialog.clickSaveButton(driver);
        System.out.println("/*----17. Click Edit Expected Delivery Date--*/");
        RequisitionPage.clickEditExpectedDeliveryDate(driver);

        System.out.println("/*----19. Choose The Expected Delivery Date--*/");
        RequisitionPage.inputExpectedDate(driver);
        System.out.println("/*----20. Save Chosen Expected Delivery Date--*/");
        RequisitionPage.clickSaveExpectedDeliveryDate(driver);
        System.out.println("/*----21. Approve Requisition--*/");
        try {
            RequisitionPage.clickApproveRequisition(driver);
        } catch(StaleElementReferenceException ex) {
            Thread.sleep(1000);
            RequisitionPage.clickApproveRequisition(driver);
        }
        ApproveRequisitionDialog.clickSaveButton(driver);
        System.out.println("/*----22. Select Supply Container With Entering Approved Request Dose--*/");
        ApproveRequisitionDialog.enterApprovedDose(driver, "1");
        ApproveRequisitionDialog.enterApproverComments(driver, "test test");
        System.out.println("/*----23. Save Approved Request Dose--*/");
        ApproveRequisitionDialog.clickSaveButton(driver);
        System.out.println("/*----24. Ship Requisition--*/");
        RequisitionPage.clickShipRequisition(driver);

        Assert.assertTrue(ShipRequisitionDialog.opened(driver), "Ship Requisition Dialog did not open");

        System.out.println("/*----25. Save Shipping Requisition--*/");
        ShipRequisitionDialog.clickSaveShipRequisition(driver);
        System.out.println("/*----26. Receive Requisition--*/");
        RequisitionPage.clickReceiveRequisitionButton(driver);
        System.out.println("/*----27. click On Search Distribution component--*/");
        ReceiveRequisitionDialog.clickSupplyDistributionsTo(driver);
        System.out.println("/*----28. Select Distribution -'SDST...'- --*/");
        ReceiveRequisitionDialog.selectSupplyDistributionTo(driver);
        System.out.println("/*----29. click Save ReceiveRequisition--*/");
        ReceiveRequisitionDialog.clickSaveButton(driver);
    }

}

