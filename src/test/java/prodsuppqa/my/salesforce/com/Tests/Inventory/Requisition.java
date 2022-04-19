package prodsuppqa.my.salesforce.com.Tests.Inventory;



import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.RequisitionFlowPage;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class Requisition extends BaseTest {

	@Test
	public void Create_Requisition_as_PPHIS_PRODSUPPQA() throws InterruptedException {

		System.out.println("/*----1. Login as an PPHIS_prodsuppqa to Supply Console --*/");
		RequisitionFlowPage requisitionFlowPage = loginPage.loginAsPPHIS1();
//        System.out.println("/*----000. Close Other Tabs --*/");
//        requisitionFlowPage.closeOtherTabs();
//        Thread.sleep(4000);
//        System.out.println("/*----2. Locate Dropdown Menu --*/");
		Thread.sleep(4000);
		requisitionFlowPage.clickDropdownMenu();
		Thread.sleep(6000);
		System.out.println("/*----3. Navigate to Supply Locations --*/");
		requisitionFlowPage.clickSupplyLocationInDropdown();
		Thread.sleep(6000);
		System.out.println("/*----3. Locate on Automation Supply Location_1 --*/");
		requisitionFlowPage.clickSupplyLocationName();
		Thread.sleep(4000);
		System.out.println("/*----4. Navigate to Request Supplies --*/");
		requisitionFlowPage.clickRequestSupplies();
		Thread.sleep(3000);
		System.out.println("/*----5. Choose Ship To Address --*/");
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
		Thread.sleep(3000);
		System.out.println("/*----20. Select Supply Container With Entering Approved Request Dose--*/");
		requisitionFlowPage.enterApprovedDose("1");
		Thread.sleep(9000);


	}

}