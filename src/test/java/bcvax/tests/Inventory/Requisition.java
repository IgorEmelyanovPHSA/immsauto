package bcvax.tests.Inventory;


import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class Requisition extends BaseTest {
	MainPageOrg orgMainPage;
	SupplyConsolePage supplyConsolePage;
	Map<String, Object> testData;
	String env;
	@Test
	public void Create_Requisition_as_an_PPHIS() throws Exception {
		env = Utils.getTargetEnvironment();
		TestcaseID = (env.contains("immsbc_admin")) ? "244848" : "222344";
		testData = Utils.getTestData(env);
		String supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
		String supply_location = String.valueOf(testData.get("supplyLocationTo"));

		log("Target Environment: "+ Utils.getTargetEnvironment());
		System.out.println("/*----1. Login as an PPHIS_BCVAXDEVIT to Supply Console --*/");
		orgMainPage = loginPage.orgLoginAsPPHIS();
		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		supplyConsolePage = new SupplyConsolePage(driver);

		SupplyConsolePage.closeTabsHCA(driver);
		SupplyConsolePage.clickSupplyLocationsTab(driver);
		SupplyLocationsPage.selectSupplyLocationName(driver, supply_location);
		System.out.println("/*----2. Locate Dropdown Menu --*/");
		System.out.println("/*----3. Close Other Tabs --*/");
		System.out.println("/*----4. Navigate to Supply Locations --*/");
		System.out.println("/*----5. Locate on Age 12 and Above - Abbotsford - Abby Pharmacy --*/");
		System.out.println("/*----6. Navigate to Request Supplies --*/");
		SupplyLocationPage.clickCreateRequisitionButton(driver);
		System.out.println("/*----7. select Shipped From-'All ages-Atlin Health Centre' --*/");
		CreateRequisitionForm.selectShipped_From(driver, supply_location_from);
		System.out.println("/*----9. Choose Requested Delivery Date --*/");
		CreateRequisitionForm.inputRequestDate(driver);
		System.out.println("/*----10. Choose Urgency --*/");
		CreateRequisitionForm.clickNextButton(driver);
		Thread.sleep(2000);
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
		RequisitionPage.clickApproveRequisition(driver);
		ApproveRequisitionDialog.clickSaveButton(driver);
		System.out.println("/*----22. Select Supply Container With Entering Approved Request Dose--*/");
		ApproveRequisitionDialog.enterApprovedDose(driver, "1");
		ApproveRequisitionDialog.enterApproverComments(driver, "test test");
		System.out.println("/*----23. Save Approved Request Dose--*/");
		ApproveRequisitionDialog.clickSaveButton(driver);
		Thread.sleep(2000);
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
		Thread.sleep(5000);
	}
	
}
	
