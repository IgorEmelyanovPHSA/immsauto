package communityPortal.tests.InventoryCP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class Return_CP extends BaseTest {
    String env;
    Map<String, Object> testData;
    MainPageCP cpMainPage;
    String supply_item = "FluMist-Tri - BK2024B";
    String lot_number = "BK2024B";
    String supply_location = "Automation Supply Location_1";
    String supply_location_to = "Automation Supply Location_2";
    double doses = 100;
    String distribution_to = "Automation Supply Distribution_1_1";
    String reason_for_wastage = "CCI: Equipment Malfunction";
    String receiver_comment = "This is to test the Receiver Comment";

    @Test()
    public void Validate_Return_Inventory_as_PPHIS() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        SoftAssert softAssert = new SoftAssert();
        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login AS comunityqa_org_immsbc_admin");
                TestcaseID = "261523"; //C261384
                loginPage.loginAsImmsBCAdminICE();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "261523";
                cpMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();;
        }

        log("/*4. ----Open Supply Location " + supply_location + " --*/");
        cpMainPage.selectSupplyLocationName(supply_location);
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
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

        softAssert.assertEquals(return_status, "Draft");

        String return_id_from_details = returnPage.getReturnId();
        String returned_from = returnPage.getReturnedFromValue();
        String returned_to = returnPage.getReturnedToValue();
        String sender_comment = returnPage.getSenderComment();

        softAssert.assertEquals(sender_comment, "This is to Add Return");

        log("/*11. ----Click Add Line Item Button --*/");
        returnPage.clickAddLineItemButton();

        log("/*12. ----Verify the Add Return Line Item popup window is displayed with correct Return ID and Supply Location --*/");
        String return_id_from_add_line_items = AddReturnLineItemsDialog.getReturnId(driver);
        softAssert.assertEquals(return_id_from_add_line_items, return_id);
        String return_from_from_add_line_items = AddReturnLineItemsDialog.getReturnFrom(driver);
        softAssert.assertEquals(return_from_from_add_line_items, supply_location_from_value);
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

        softAssert.assertEquals(alert_content, "Success\nReturn Line Items added successfully.");

        log("/*17. ----Verify Return Line Item record is created and Info is correct --*/");
        Map<String, WebElement> line_items = returnPage.getReturnLineItemsTableCP();
        String return_line_item_number = line_items.get("Return Line Item Number").getText();
        String supply_transaction_name = returnPage.getLinkTextFromCellValue(line_items.get("Supply Transaction Name"));
        String trade_description = line_items.get("Trade Description").getText();
        String return_lot_number = line_items.get("Lot Number").getText();
        String expiry_date = line_items.get("Expiry Date").getText();
        String returned_doses = line_items.get("Returned Doses").getText();
        String return_reason_for_wastage = line_items.get("Reason for Wastage").getText();
        String return_line_item_comments = line_items.get("Return Line Item Comments").getText();
        softAssert.assertTrue(!return_line_item_number.isEmpty(), "Return Line Item ID is empty");
        softAssert.assertTrue(!supply_transaction_name.isEmpty(), "Supply Transaction Name is empty");
        softAssert.assertTrue(!trade_description.isEmpty(), "Trade Description is empty");
        softAssert.assertEquals(return_lot_number, lot_number, "Incorrect Lot Number");
        softAssert.assertEquals(Double.parseDouble(returned_doses), doses, "Incorrect Doses");
        softAssert.assertEquals(return_reason_for_wastage, reason_for_wastage, "Incorrect Reason for Wastage");
        ////////***May later use
        //String cancelled = line_items.get("Cancelled").getText();
        ////////

        log("/*18. ----Verify Print Return Form --*/");
        returnPage.clickPrintButtonCP();
        boolean print_label_btn_exists = PrintReturnFormDialog.printReturnLabelBtnExists(driver);
        boolean print_manifest_btn_exists = PrintReturnFormDialog.printReturnManifestBtnExists(driver);
        boolean close_btn_exists = PrintReturnFormDialog.closeBtnExists(driver);
        softAssert.assertTrue(print_label_btn_exists, "Print Label Button not found");
        softAssert.assertTrue(print_manifest_btn_exists, "Print Manifest Button not found");
        softAssert.assertTrue(close_btn_exists, "Close Button not found");

        log("/*19. ----Verify Close Print Return Form --*/");
        PrintReturnFormDialog.clickCloseBtn(driver);

        log("/*20. ----Verify Ship Return Form --*/");
        returnPage.clickShipReturnButton();

        log("/*21. ----Verify Save Ship Return Form --*/");
        ShipReturnDialog.clickSaveBtnCP(driver);

        log("/*22. ----Verify Success Dialog --*/");
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);
        softAssert.assertEquals(alert_content, "You have successfully Shipped the Return.");

        log("/*23. ----Verify Return Status is changed to Shipped --*/");
        String return_status_shipped = returnPage.getReturnStatus();

        softAssert.assertEquals(return_status_shipped, "Shipped");

        log("/*24. ----Click Receive Return Button --*/");
        returnPage.clickReceiveReturnButtonCP();

        log("/*25. ----Enter Receiver Comment --*/");
        ReceiveReturnDialog.typeReceiverComment(driver, receiver_comment);

        log("/*26. ----Save Receive Return Form --*/");
        ReceiveReturnDialog.clickSaveBtnCP(driver);

        log("/*27. ----Verify Success Dialog --*/");
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        softAssert.assertEquals(alert_content, "You have successfully received the Return.");

        log("/*28. ----Verify Return Status is changed to Received --*/");
        String return_status_received = returnPage.getReturnStatus();
        softAssert.assertEquals(return_status_received, "Received");

        log("/*29. ----Verify Return Location History --*/");
        Map<String, WebElement> location_history = returnPage.getReturnLocationHistoryTableCP();
        String history_return_id = returnPage.getReturnLocationHistoryIdCP(location_history.get("Return Location History ID"));
        String history_receive_date = location_history.get("Received Date").getText();
        String history_received_by = location_history.get("Received By").getText();
        String history_from_location = returnPage.getLinkTextFromCellValue(location_history.get("From Location"));
        String history_to_location = returnPage.getLinkTextFromCellValue(location_history.get("To Location"));
        String history_receiver_comment = location_history.get("Receiver Comment").getText();
        softAssert.assertTrue(!history_return_id.isEmpty(), "History Return ID is Empty");
        softAssert.assertTrue(!history_receive_date.isEmpty(), "History Return Receive Date is Empty");
        softAssert.assertTrue(!history_received_by.isEmpty(), "History Return Receive By is Empty");
        softAssert.assertEquals(history_from_location, supply_location, "Supply Location From doesn't match");
        softAssert.assertEquals(history_to_location, supply_location_to, "Supply Location To doesn't match");
        softAssert.assertEquals(history_receiver_comment, receiver_comment, "History Receiver Comment doesn't match");

        log("/*30. ----Verify Forward Return Dialog --*/");
        returnPage.clickForwardReturnButtonCP();
        String forward_return_id = ForwardReturnDialog.getReturnId(driver);
        String forward_supply_location = ForwardReturnDialog.getOriginalSupplyLocation(driver);
        String forward_returned_to = ForwardReturnDialog.getReturnedTo(driver);

        softAssert.assertEquals(forward_return_id, return_id);
        softAssert.assertEquals(forward_supply_location, returned_from);
        softAssert.assertAll();
    }
}
