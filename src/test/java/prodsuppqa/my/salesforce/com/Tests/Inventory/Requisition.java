package prodsuppqa.my.salesforce.com.Tests.Inventory;


import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.RequisitionFlowPage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

public class Requisition extends BaseTest {
	
	@Test
	public void Create_Requisition_as_PPHIS_PRODSUPPQA() throws InterruptedException {
		
		RequisitionFlowPage requisitionFlowPage = loginPage.loginAsPPHIS1();
		System.out.println("/*----1. Login as an PPHIS_prodsuppqa to Supply Console --*/");
		Thread.sleep(3000);
		requisitionFlowPage.closeTab();
		System.out.println("/*----2. Close open Tabs --*/");
		Thread.sleep(3000);
		requisitionFlowPage.clickDropdownMenu();
		System.out.println("/*----3. Locate and click on Dropdown Menu --*/");
		Thread.sleep(3000);
		requisitionFlowPage.clickSupplyLocationInDropdown();
		System.out.println("/*----4. Navigate to Supply Locations --*/");
		Thread.sleep(3000);
		requisitionFlowPage.clickSupplyLocationName();
		System.out.println("/*----5. Locate on Automation Supply Location_1 --*/");
		Thread.sleep(3000);
		requisitionFlowPage.clickRequestSupplies();
		System.out.println("/*----6. Navigate to Request Supplies --*/");
		Thread.sleep(3000);
		//requisitionFlowPage.clickShipToAddress();
		Thread.sleep(3000);
		System.out.println("/*----6. Locate on Automation Supply Location_2 --*/");
		requisitionFlowPage.inputShipAddress("Atlin Health Centre");
		//  supplyConsolePagePage.clickOnSupplyLocations();
		System.out.println("/*----7. Choose Requested Delivery Date --*/");
		Thread.sleep(3000);
		requisitionFlowPage.clickInput();
		Thread.sleep(3000);
		requisitionFlowPage.clickInput1();
		Thread.sleep(3000);
		requisitionFlowPage.inputRequestDate();
		Thread.sleep(3000);
		System.out.println("/*----8. Choose Urgency --*/");
		requisitionFlowPage.clickNextButton();
		Thread.sleep(3000);
		System.out.println("/*----9. Add Requisition Line Items --*/");
		requisitionFlowPage.clickLineItemCheckBox();
		Thread.sleep(3000);
		System.out.println("/*----10. Select requested Trades --*/");
		requisitionFlowPage.clickNextButton();
		Thread.sleep(3000);
		System.out.println("/*----11. Input Requested Quantity and Doses --*/");
		requisitionFlowPage.inputRequestedDose("1");
		Thread.sleep(3000);
		System.out.println("/*----12. Save Quantity and Doses --*/");
		requisitionFlowPage.clickSaveButton();
		Thread.sleep(3000);
		System.out.println("/*----13. Submit Requisition --*/");
		requisitionFlowPage.clickSubmitRequisition();
		Thread.sleep(5000);
		System.out.println("/*----14. Confirm and Save Requisition --*/");
		requisitionFlowPage.clickSaveSubmitRequisition();
		Thread.sleep(5000);
		System.out.println("/*----15. Click Edit Expected Delivery Date--*/");
		requisitionFlowPage.clickEditExpectedDeliveryDate();
		Thread.sleep(3000);
		System.out.println("/*----16. Click Calender of Expected Delivery Date--*/");
		requisitionFlowPage.clickExpectedDeliveryDateCalendar();
		Thread.sleep(3000);
		System.out.println("/*----17. Choose The Expected Delivery Date--*/");
		requisitionFlowPage.inputExpectedDate();
		Thread.sleep(3000);
		System.out.println("/*----18. Save Chosen Expected Delivery Date--*/");
		requisitionFlowPage.clickSaveExpectedDeliveryDate();
		Thread.sleep(3000);
		System.out.println("/*----19. Approve Requisition--*/");
		requisitionFlowPage.clickApproveRequisition();
		Thread.sleep(5000);
		System.out.println("/*----20. Select Supply Container With Entering Approved Request Dose--*/");
		requisitionFlowPage.enterApprovedDose("1");
		Thread.sleep(9000);
		//requisitionFlowPage.closeTabs();
		
	}
	
}