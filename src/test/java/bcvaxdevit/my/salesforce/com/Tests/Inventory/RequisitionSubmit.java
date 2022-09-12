package bcvaxdevit.my.salesforce.com.Tests.Inventory;


import org.testng.annotations.Test;
import bcvaxdevit.my.salesforce.com.Pages.RequisitionPage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;


public class RequisitionSubmit extends BaseTest {

    @Test
    public void Submit_Requisition_as_an_PPHIS_BCVAXDEVIT() throws Exception {
        //TestcaseID = "220561"; //C220561
        System.out.println("/*----1. Login as an PPHIS_BCVAXDEVIT to Supply Console --*/");
        RequisitionPage requisitionPage = loginPage.loginAsPPHIS1WithParameters();
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
        System.out.println("/*----5. Locate on Automation Supply Location_1 --*/");
        requisitionPage.clickSupplyLocationName();
        Thread.sleep(4000);
        System.out.println("/*----6. Navigate to Request Supplies --*/");
        requisitionPage.clickRequestSupplies();
        Thread.sleep(3000);
        System.out.println("/*----7. Choose Ship To Address --*/");
        //requisitionPage.inputShipAddress();
        System.out.println("/*----8. Locate on Automation Supply Location_1 --*/");
        //requisitionPage.LocateAddress("Atlin Health Centre");
        System.out.println("/*----9. Choose Requested Delivery Date --*/");
        Thread.sleep(3000);
        requisitionPage.inputRequestDate();
        System.out.println("/*----10. Choose Urgency --*/");
        requisitionPage.clickNextButton();
        Thread.sleep(3000);
        System.out.println("/*----11. Add Requisition Line Items --*/");
        requisitionPage.clickLineItemCheckBox();
        Thread.sleep(3000);
        System.out.println("/*----12. Select requested Trades --*/");
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
    }
}