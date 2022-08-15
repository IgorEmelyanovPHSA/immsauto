package bcvaxuat.my.salesforce.com.Tests.Inventory;


import Utilities.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import bcvaxuat.my.salesforce.com.Pages.RequisitionPage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;

@Listeners({TestListener.class})
public class Requisition extends BaseTest {

	@Test
	public void Create_Requisition_as_an_PPHIS_BCVAXUAT() throws InterruptedException {
		TestcaseID = "220561"; //C220561
		log("/*----1. Login as an PPHIS_BCVAXDEVIT to Supply Console --*/");
		RequisitionPage requisitionPage = loginPage.loginAsPPHIS1();
		log("/*----2. Locate Dropdown Menu --*/");
		requisitionPage.displaySupplyConsolePage();
		Thread.sleep(4000);
		log("/*----3. Close Other Tabs --*/");
		requisitionPage.closeTabs();
		Thread.sleep(4000);
		requisitionPage.clickDropdownMenu();
		Thread.sleep(6000);
		log("/*----4. Navigate to Supply Locations --*/");
		requisitionPage.clickSupplyLocationInDropdown();
		Thread.sleep(6000);
		log("/*----5. Locate on Automation Supply Location_1 --*/");
		requisitionPage.clickSupplyLocationName();
		Thread.sleep(4000);
		log("/*----6. Navigate to Request Supplies --*/");
		requisitionPage.clickRequestSupplies();
		Thread.sleep(3000);
		log("/*----7. Choose Ship To Address --*/");
		requisitionPage.inputShipAddress();
		log("/*----8. Locate on Automation Supply Location_1 --*/");
		requisitionPage.LocateAddress("All Ages - Atlin Health Centre");
		log("/*----9. Choose Requested Delivery Date --*/");
		Thread.sleep(3000);
		requisitionPage.inputRequestDate();
		log("/*----10. Choose Urgency --*/");
		requisitionPage.clickNextButton();
		Thread.sleep(3000);
		log("/*----11. Add Requisition Line Items --*/");
		requisitionPage.clickLineItemCheckBox();
		Thread.sleep(3000);
		log("/*----12. Select requested Trades --*/");
		requisitionPage.clickNextButton();
		Thread.sleep(3000);
		log("/*----13. Input Requested Quantity and Doses --*/");
		requisitionPage.inputRequestedDose("1");
		Thread.sleep(3000);
		log("/*----14. Save Quantity and Doses --*/");
		requisitionPage.clickSaveButton();
		Thread.sleep(3000);
		log("/*----15. Submit Requisition --*/");
		requisitionPage.clickSubmitRequisition();
		Thread.sleep(5000);
		log("/*----16. Confirm and Save Requisition --*/");
		requisitionPage.clickSaveSubmitRequisition();
		Thread.sleep(5000);
		log("/*----17. Click Edit Expected Delivery Date--*/");
		requisitionPage.clickEditExpectedDeliveryDate();
		Thread.sleep(3000);
		log("/*----18. Click Calender of Expected Delivery Date--*/");
		requisitionPage.clickExpectedDeliveryDateCalendar();
		Thread.sleep(3000);
		log("/*----19. Choose The Expected Delivery Date--*/");
		requisitionPage.inputExpectedDate();
		Thread.sleep(3000);
		log("/*----20. Save Chosen Expected Delivery Date--*/");
		requisitionPage.clickSaveExpectedDeliveryDate();
		Thread.sleep(3000);
		log("/*----21. Approve Requisition--*/");
		requisitionPage.clickApproveRequisition();
		Thread.sleep(4000);
		requisitionPage.clickSaveApprovedRequisition();
		Thread.sleep(3000);
		log("/*----22. Select Supply Container With Entering Approved Request Dose--*/");
		requisitionPage.enterApprovedDose("1");
		Thread.sleep(5000);
		log("/*----23. Save Approved Request Dose--*/");
		requisitionPage.clickSaveApprovedRequisition();
		Thread.sleep(4000);
		log("/*----24. Ship Requisition--*/");
		requisitionPage.clickShipRequisition();

		Thread.sleep(7000);

		String actual = "Ship Requisition";
		Assert.assertEquals(requisitionPage.ShipRequisition(), actual);

		log("/*----25. Save Shipping Requisition--*/");
		requisitionPage.clickSaveShipRequisition();
		Thread.sleep(3000);
		log("/*----26. Receive Requisition--*/");
		requisitionPage.clickReceiveRequestBtn();
		Thread.sleep(3000);
		log("/*----27. Search Distribution--*/");
		requisitionPage.clickSearchSupplyDistributions();
		Thread.sleep(3000);
		log("/*----28. Select Distribution--*/");
		requisitionPage.SelectSupplyDistributionTo2("SDST-0000001499");
		Thread.sleep(3000);
		log("/*----29. click Save ReceiveRequisition--*/");
		requisitionPage.clickSaveReceiveRequisition();
		Thread.sleep(5000);

	}

}