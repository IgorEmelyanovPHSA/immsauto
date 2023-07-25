package bcvax.tests.Inventory;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class Returns extends BaseTest {
    String env;
    Map<String, Object> testData;
    MainPageOrg orgMainPage;
    String supply_item = "FluMist-Tri - BK2024B";
    String supply_location = "Automation Supply Location_1";
    String supply_location_to = "Automation Supply Location_2";
    double doses = 100;
    String distribution_to = "Automation Supply Distribution_1_1";

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        log("/*----Run Pre-conditions --*/");
        testData = Utils.getTestData(env);
        //Login as Admin
        log("/*----Login as Admin --*/");
        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = orgMainPage.currentApp();
        log("/*a.----Go to Health Connect Supply Location --*/");
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        //Get Flu supplies using Receive Supplies feature
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        supplyConsolePage.closeTabsHCA();
        supplyConsolePage.clickSupplyConsoleAppNavigationMenu();
        supplyConsolePage.selectSupplyLocationFromDropdown();
        supplyConsolePage.selectSupplyLocationName(supply_location);

        log("/*b.----Receive Supplies for Flu --*/");
        supplyConsolePage.SelectDropDownToClickReceiveSuppliesButton();
        supplyConsolePage.ClickDropDownToClickReceiveSuppliesButton();
        supplyConsolePage.clickSupplyItemTextBox();
        supplyConsolePage.selectSupplyItem(supply_item);
        supplyConsolePage.enterTransferDosages(Double.toString(doses));
        //supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to);
        supplyConsolePage.selectIncomingSupplyDistributionReceive();
        supplyConsolePage.selectReasonForReception();
        supplyConsolePage.ClickSaveButton();

        log("/*d.----Create Wastage for the Flu Container --*/");
        //Create Wastage Record for Flu supply item
        supplyConsolePage.clickOnContainerDropDownMenu(supply_item, distribution_to);
        supplyConsolePage.selectWastageFromDropDown();

        log("/*f.----Add Doses and Reason for Wastage --*/");
        supplyConsolePage.setDosesAmount(Double.toString(doses));
        supplyConsolePage.selectReasonForWastageDropDown();

        log("/*g.----Click Wastage Button--*/");
        supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
        orgMainPage.logout();
    }

    @Test()
    public void Validate_Return_Inventory_as_PPHIS() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login AS comunityqa_org_immsbc_admin");
                TestcaseID = "261384"; //C261384
                loginPage.loginAsImmsBCAdminICE();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "261384";
                orgMainPage = loginPage.orgLoginAsPPHIS();
        }

        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

        log("/*2.----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();

        log("/*3. ----Close all open tabs --*/");
        supplyConsolePage.closeTabsHCA();

        log("/*4. ----Open Supply Location " + supply_location + " --*/");
        supplyConsolePage.clickSupplyConsoleAppNavigationMenu();
        supplyConsolePage.selectSupplyLocationFromDropdown();
        supplyConsolePage.selectSupplyLocationName(supply_location);

        log("/*5. ----Click Return Button --*/");
        supplyConsolePage.clickReturnBtn();

        ReturnDialog returnDialog = new ReturnDialog(driver);
        String supply_location_from_value = returnDialog.getReturnFromValue();

        log("/*6. ----Select Supply Location Return To --*/");
        returnDialog.selectReturnTo(supply_location_to);

        log("/*7. ----Enter Sender Comment --*/");
        returnDialog.typeReturnComments("This is to Add Return");

        log("/*8. ----Save Return --*/");
        returnDialog.clickSaveBtn();

        log("/*9. ----Verify The Return with Return_ID is created and click Return_ID link from Toast Box --*/");
        String return_id = AlertDialog.clickAlertLink(driver);
        log("/* Result: ----The Return with Return_ID " + return_id +" is created --*/");
        //supplyConsolePage.clickReturnsTab();
        //supplyConsolePage.openReturnDetails(return_id);

        log("/*10. ----Verify The Return Status and Sender Comment --*/");
        Thread.sleep(2000);
        ReturnPage returnPage = new ReturnPage(driver);
        String return_status = returnPage.getReturnStatus();

        Assert.assertEquals(return_status, "Draft");

        String return_id_from_details = returnPage.getReturnId();
        String returned_from = returnPage.getReturnedFromValue();
        String returned_to = returnPage.getReturnedToValue();
        String sender_comment = returnPage.getSenderComment();

        Assert.assertEquals(sender_comment, "This is to Add Return");

        log("/*11. ----Click Add Line Item Button --*/");
        returnPage.clickAddLineItemButton();

        log("/*12. ----Verify the Add Return Line Item popup window is displayed with correct Return ID and Supply Location --*/");
        String return_id_from_add_line_items = AddReturnLineItemsDialog.getReturnId(driver);
        Assert.assertEquals(return_id_from_add_line_items, return_id);
        String return_from_from_add_line_items = AddReturnLineItemsDialog.getReturnFrom(driver);
        Assert.assertEquals(return_from_from_add_line_items, supply_location_from_value);
        //Select First Wastage from the list

        log("/*13. ----Select First Wastage --*/");
        AddReturnLineItemsDialog.checkWastageItem(driver, 0);

        log("/*14. ----Click Next Button --*/");
        AddReturnLineItemsDialog.clickNextBtn(driver);

        log("/*15. ----Click Save Button --*/");
        AddReturnLineItemsDialog.clickSaveBtn(driver);
        //Check if alert found

        log("/*16. ----Verify Add Line Item Success Dialog Appear --*/");
        boolean alert_found = AlertDialog.alertFound(driver);
        String alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        Assert.assertEquals(alert_content, "Success\nReturn Line Items added successfully.");

        Map<String, WebElement> line_items = returnPage.getReturnLineItemsTable();
        String return_line_item_number = line_items.get("Return Line Item Number").getText();
        String supply_transaction_name = returnPage.getLinkTextFromCellValue(line_items.get("Supply Transaction Name"));
        String trade_description = line_items.get("Trade Description").getText();
        String lot_number = line_items.get("Lot Number").getText();
        String expiry_date = line_items.get("Expiry Date").getText();
        String returned_doses = line_items.get("Returned Doses").getText();
        String reason_for_wastage = line_items.get("Reason for Wastage").getText();
        String return_line_item_comments = line_items.get("Return Line Item Comments").getText();

        ////////***May later use
        //String cancelled = line_items.get("Cancelled").getText();
        ////////

        returnPage.clickPrintButton();
        boolean print_label_btn_exists = PrintReturnFormDialog.printReturnLabelBtnExists(driver);
        boolean print_manifest_btn_exists = PrintReturnFormDialog.printReturnManifestBtnExists(driver);
        boolean close_btn_exists = PrintReturnFormDialog.closeBtnExists(driver);
        Assert.assertTrue(print_label_btn_exists, "Print Label Button not found");
        Assert.assertTrue(print_manifest_btn_exists, "Print Manifest Button not found");
        Assert.assertTrue(close_btn_exists, "Close Button not found");
        PrintReturnFormDialog.clickCloseBtn(driver);

        returnPage.clickShipReturnButton();
        ShipReturnDialog.clickSaveBtn(driver);
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        Assert.assertEquals(alert_content, "You have successfully Shipped the Return.");

        String return_status_shipped = returnPage.getReturnStatus();

        Assert.assertEquals(return_status_shipped, "Shipped");

        returnPage.clickReceiveReturnButton();

        ReceiveReturnDialog.typeReceiverComment(driver, "This is to test the Receiver Comment");
        ReceiveReturnDialog.clickSaveBtn(driver);
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        Assert.assertEquals(alert_content, "You have successfully received the Return.");

        String return_status_received = returnPage.getReturnStatus();

        Assert.assertEquals(return_status_received, "Received");

        Map<String, WebElement> location_history = returnPage.getReturnLocationHistoryTable();
        String history_return_id = returnPage.getReturnLocationHistoryId(location_history.get("Return Location History ID"));
        String history_receive_date = location_history.get("Received Date").getText();
        String history_received_by = location_history.get("Received By").getText();
        String history_from_location = returnPage.getLinkTextFromCellValue(location_history.get("From Location"));
        String history_to_location = returnPage.getLinkTextFromCellValue(location_history.get("To Location"));
        String history_receiver_comment = location_history.get("Receiver Comment").getText();

        returnPage.clickForwardReturnButton();
        String forward_return_id = ForwardReturnDialog.getReturnId(driver);
        String forward_supply_location = ForwardReturnDialog.getOriginalSupplyLocation(driver);
        String forward_returned_to = ForwardReturnDialog.getReturnedTo(driver);

        Assert.assertEquals(forward_return_id, return_id);
        Assert.assertEquals(forward_supply_location, returned_from);
    }
}
