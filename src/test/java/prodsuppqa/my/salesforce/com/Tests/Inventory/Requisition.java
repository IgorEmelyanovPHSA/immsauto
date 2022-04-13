package prodsuppqa.my.salesforce.com.Tests.Inventory;



import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.SupplyConsolePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

import static java.lang.Thread.*;

public class Requisition extends BaseTest {

    @Test
    public void Create_Requisition_as_PPHIS_PRODSUPPQA() throws InterruptedException {


        System.out.println("/*----1. Login as an PPHIS_prodsuppqa to Supply Console --*/");
        SupplyConsolePage supplyConsolePagePage = loginPage.loginAsPPHIS();
        System.out.println("/*----2. Go to Supply Locations Tab --*/");
        supplyConsolePagePage.clickSupplyLocationsTab();
        Thread.sleep(4000);
        System.out.println("/*----3. Locate on Automation Supply Location_1 --*/");
        supplyConsolePagePage.clickOnSupplyLocations();
        Thread.sleep(5000);
        System.out.println("/*----4. Navigate to Request Supplies --*/");
        supplyConsolePagePage.clickRequestSupplies();
        System.out.println("/*----5. Choose Ship To Address --*/");
        // supplyConsolePagePage.clickShipToAddress();
        Thread.sleep(5000);
        System.out.println("/*----6. Locate on Automation Supply Location_2 --*/");
        supplyConsolePagePage.inputShipAddress("Atlin Health Centre");
        //  supplyConsolePagePage.clickOnSupplyLocations();
        System.out.println("/*----7. Choose Requested Delivery Date --*/");
        Thread.sleep(3000);
        supplyConsolePagePage.clickInput();
        Thread.sleep(3000);
        supplyConsolePagePage.clickInput1();
        Thread.sleep(3000);
        supplyConsolePagePage.inputRequestDate("Apr 22, 2022");
        //   supplyConsolePagePage.clickRequestedDeliveryDate();
        Thread.sleep(3000);
        System.out.println("/*----8. Choose Urgency --*/");
        supplyConsolePagePage.clickNextButton();
        Thread.sleep(3000);
        System.out.println("/*----9. Add Requisition Line Items --*/");
        supplyConsolePagePage.clickCheckBox();
        Thread.sleep(3000);
        System.out.println("/*----10. Select requested Trades --*/");
        supplyConsolePagePage.clickNextButton();
        Thread.sleep(3000);
        System.out.println("/*----11. Input Requested Quantity and Doses --*/");
        supplyConsolePagePage.inputRequestedDose("1");
        Thread.sleep(3000);
        System.out.println("/*----12. Save Quantity and Doses --*/");
        supplyConsolePagePage.clickSaveButton();
        Thread.sleep(3000);
        System.out.println("/*----13. Submit Requisition --*/");
        supplyConsolePagePage.clickSubmitRequisition();
        Thread.sleep(5000);
        System.out.println("/*----14. Confirm and Save Requisition --*/");
//        supplyConsolePagePage.clickSaveSubmitRequisition();
//        Thread.sleep(3000);
//        System.out.println("/*----15. Click Calender of Expected Delivery Date--*/");
//        supplyConsolePagePage.clickExpectedDeliveryDateCalendar();
//        Thread.sleep(3000);
//        System.out.println("/*----16. Choose The Expected Delivery Date--*/");
//        System.out.println("/*----17. Save Chosen Expected Delivery Date--*/");
//        System.out.println("/*----18. Approve Requisition--*/");




    }


}