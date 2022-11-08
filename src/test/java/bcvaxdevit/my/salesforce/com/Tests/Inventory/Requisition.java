package bcvaxdevit.my.salesforce.com.Tests.Inventory;


import bcvaxdevit.my.salesforce.com.Pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import bcvaxdevit.my.salesforce.com.Pages.RequisitionPage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;


public class Requisition extends BaseTest {
	
	@Test
	public void Create_Requisition_as_an_PPHIS_BCVAXDEVIT() throws Exception {
		TestcaseID = "222344"; //C222344
		log("Target Environment: "+ Utils.getTargetEnvironment());
		System.out.println("/*----1. Login as an PPHIS_BCVAXDEVIT to Supply Console --*/");
		RequisitionPage requisitionPage = loginPage.loginAsPPHIS1();
		Thread.sleep(5000);
		System.out.println("/*----2. Locate Dropdown Menu --*/");
		requisitionPage.displaySupplyConsolePage();
		Thread.sleep(4000);
		System.out.println("/*----3. Close Other Tabs --*/");
		requisitionPage.closeTabs();
		Thread.sleep(4000);
		requisitionPage.clickDropdownMenu();
		Thread.sleep(6000);
		System.out.println("/*----4. Navigate to Supply Locations --*/");
		requisitionPage.clickSupplyLocationInDropdown();
		Thread.sleep(6000);
		System.out.println("/*----5. Locate on Age 12 and Above - Abbotsford - Abby Pharmacy --*/");
		requisitionPage.clickSupplyLocationName();
		Thread.sleep(4000);
		System.out.println("/*----6. Navigate to Request Supplies --*/");
		requisitionPage.clickRequestSupplies();
		Thread.sleep(3000);
		System.out.println("/*----7. select Shipped From-'All ages-Atlin Health Centre' --*/");
		requisitionPage.selectShipped_From();
		Thread.sleep(3000);
		//requisitionPage.inputShipAddress();
		//System.out.println("/*----8. Locate on Automation Supply Location_1 --*/");
		//requisitionPage.LocateAddress("Atlin Health Centre");
		System.out.println("/*----9. Choose Requested Delivery Date --*/");
		requisitionPage.inputRequestDate();
		Thread.sleep(3000);
		System.out.println("/*----10. Choose Urgency --*/");
		requisitionPage.clickNextButton();
		Thread.sleep(3000);
		log("/*----11. Select requested Trades from Add Requisition Line Items  --*/");
		log("/*--SPIKEVAX (Moderna) COVID-19 mRNA Moderna mRNA-1273 7mL 14-dose vial Lot 016F21A-CC07--*/");
		//log("/*for prodsuppqa --SPIKEVAX (Moderna) COVID-19 mRNA Moderna mRNA-1273 7mL 14-dose vial Lot 016F21A-CC07--*/");
		//log("/*for bcvaxdevit --COVID-19 mRNA SPIKEVAX (Moderna) Red Cap 5mL multi-dose 10 vials/box Lot 3001176-CC04--*/");
		requisitionPage.clickLineItemCheckBox();
		Thread.sleep(3000);
		log("/*----12. click Next button --*/");
		requisitionPage.clickNextButton();
		Thread.sleep(3000);
		System.out.println("/*----13. Input Requested Quantity and Doses --*/");
		requisitionPage.inputRequestedDose("1");
		Thread.sleep(3000);
		System.out.println("/*----14. Save Quantity and Doses --*/");
		requisitionPage.clickSaveButton();
		Thread.sleep(3000);
		System.out.println("/*----15. Submit Requisition --*/");
		requisitionPage.clickSubmitRequisition();
		Thread.sleep(5000);
		System.out.println("/*----16. Confirm and Save Requisition --*/");
		requisitionPage.clickSaveSubmitRequisition();
		Thread.sleep(5000);
		System.out.println("/*----17. Click Edit Expected Delivery Date--*/");
		requisitionPage.clickEditExpectedDeliveryDate();
		Thread.sleep(3000);
		System.out.println("/*----18. Click Calender of Expected Delivery Date--*/");
		requisitionPage.clickExpectedDeliveryDateCalendar();
		Thread.sleep(3000);
		System.out.println("/*----19. Choose The Expected Delivery Date--*/");
		requisitionPage.inputExpectedDate();
		Thread.sleep(3000);
		System.out.println("/*----20. Save Chosen Expected Delivery Date--*/");
		requisitionPage.clickSaveExpectedDeliveryDate();
		Thread.sleep(3000);
		System.out.println("/*----21. Approve Requisition--*/");
		requisitionPage.clickApproveRequisition();
		Thread.sleep(5000);
		requisitionPage.clickSaveApprovedRequisition();
		Thread.sleep(3000);
		System.out.println("/*----22. Select Supply Container With Entering Approved Request Dose--*/");
		requisitionPage.enterApprovedDose("1");
		Thread.sleep(5000);
		System.out.println("/*----23. Save Approved Request Dose--*/");
		requisitionPage.clickSaveApprovedRequisition();
		Thread.sleep(4000);
		System.out.println("/*----24. Ship Requisition--*/");
		requisitionPage.clickShipRequisition();
		Thread.sleep(7000);
		
		String actual = "Ship Requisition";
		Assert.assertEquals(requisitionPage.ShipRequisition(), actual);
		
		System.out.println("/*----25. Save Shipping Requisition--*/");
		requisitionPage.clickSaveShipRequisition();
		Thread.sleep(3000);
		System.out.println("/*----26. Receive Requisition--*/");
		requisitionPage.clickReceiveRequestBtn();
		Thread.sleep(3000);
		System.out.println("/*----27. click On Search Distribution component--*/");
		requisitionPage.clickOnSearchSupplyDistributions();
		Thread.sleep(3000);
		System.out.println("/*----28. Select Distribution -'SDST...'- --*/");
		requisitionPage.SelectSupplyDistributionTo();
		Thread.sleep(3000);
		System.out.println("/*----29. click Save ReceiveRequisition--*/");
		requisitionPage.clickSaveReceiveRequisition();
		Thread.sleep(5000);
		
	}
	
}
	
