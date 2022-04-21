package prodsuppqa.my.salesforce.com.Tests.Inventory;


import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.RequisitionPage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

public class Requisition extends BaseTest {

	@Test
	public void Create_Requisition_as_PPHIS_PRODSUPPQA() throws InterruptedException {

		System.out.println("/*----1. Login as an PPHIS_prodsuppqa to Supply Console --*/");
		RequisitionPage requisitionPage = loginPage.loginAsPPHIS1();
//        System.out.println("/*----000. Close Other Tabs --*/");
//        requisitionFlowPage.closeTab();
		Thread.sleep(4000);
		System.out.println("/*----2. Locate Dropdown Menu --*/");
		requisitionPage.displaySupplyConsolePage();
		Thread.sleep(4000);
		requisitionPage.clickDropdownMenu();
		Thread.sleep(6000);
		System.out.println("/*----3. Navigate to Supply Locations --*/");
		requisitionPage.clickSupplyLocationInDropdown();
		Thread.sleep(6000);
		System.out.println("/*----4. Locate on Automation Supply Location_1 --*/");
		requisitionPage.clickSupplyLocationName();
		Thread.sleep(4000);
		System.out.println("/*----5. Navigate to Request Supplies --*/");
		requisitionPage.clickRequestSupplies();
		Thread.sleep(3000);
		System.out.println("/*----6. Choose Ship To Address --*/");
		requisitionPage.inputShipAddress();
		System.out.println("/*----7. Locate on Automation Supply Location_1 --*/");
		requisitionPage.LocateAddress("Atlin Health Centre");
		System.out.println("/*----8. Choose Requested Delivery Date --*/");
		Thread.sleep(3000);
		requisitionPage.inputRequestDate();
		System.out.println("/*----9. Choose Urgency --*/");
		requisitionPage.clickNextButton();
		Thread.sleep(3000);
		System.out.println("/*----10. Add Requisition Line Items --*/");
		requisitionPage.clickLineItemCheckBox();
		Thread.sleep(3000);
		System.out.println("/*----11. Select requested Trades --*/");
		requisitionPage.clickNextButton();
		Thread.sleep(3000);
		System.out.println("/*----12. Input Requested Quantity and Doses --*/");
		requisitionPage.inputRequestedDose("1");
		Thread.sleep(3000);
		System.out.println("/*----13. Save Quantity and Doses --*/");
		requisitionPage.clickSaveButton();
		Thread.sleep(3000);
		System.out.println("/*----14. Submit Requisition --*/");
		requisitionPage.clickSubmitRequisition();
		Thread.sleep(5000);
		System.out.println("/*----15. Confirm and Save Requisition --*/");
		requisitionPage.clickSaveSubmitRequisition();
		Thread.sleep(5000);
		System.out.println("/*----16. Click Edit Expected Delivery Date--*/");
		requisitionPage.clickEditExpectedDeliveryDate();
		Thread.sleep(3000);
		System.out.println("/*----17. Click Calender of Expected Delivery Date--*/");
		requisitionPage.clickExpectedDeliveryDateCalendar();
		Thread.sleep(3000);
		System.out.println("/*----18. Choose The Expected Delivery Date--*/");
		requisitionPage.inputExpectedDate();
		Thread.sleep(3000);
		System.out.println("/*----19. Save Chosen Expected Delivery Date--*/");
		requisitionPage.clickSaveExpectedDeliveryDate();
		Thread.sleep(3000);
		System.out.println("/*----20. Approve Requisition--*/");
		requisitionPage.clickApproveRequisition();
		Thread.sleep(3000);
		System.out.println("/*----21. Select Supply Container With Entering Approved Request Dose--*/");
		requisitionPage.enterApprovedDose("1");
		Thread.sleep(5000);
		System.out.println("/*----22. Save Approved Request Dose--*/");
		//requisitionPage.clickSaveApprovedRequisition();
		Thread.sleep(3000);
		System.out.println("/*----23. Ship Requisition--*/");
		//requisitionPage.clickShipRequisition();
		System.out.println("/*----24. Save Shipping Requisition--*/");
		//requisitionPage.clickSaveShipRequisition();
		Thread.sleep(3000);
		System.out.println("/*----25. Receive Requisition--*/");
		//requisitionPage.clickReceiveRequisition.click();




	}

}