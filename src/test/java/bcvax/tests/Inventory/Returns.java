package bcvax.tests.Inventory;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class Returns extends BaseTest {
    String env;
    Map<String, Object> testData;
    MainPageOrg orgMainPage;
    String supply_item;
    String lot_number;
    String supply_location_from;
    String supply_location_to;
    double doses = 100;
    String distribution_to;
    String reason_for_wastage = "CCI: Equipment Malfunction";
    String receiver_comment = "This is to test the Receiver Comment";


    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        log("/*----Run Pre-conditions --*/");
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
        distribution_to = String.valueOf(testData.get("distributionTo"));
        supply_item = String.valueOf(testData.get("supplyItemInfluenza"));
        lot_number = String.valueOf(testData.get("influenzaLot"));
        //Login as Admin
        log("/*----Login as Admin --*/");
        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = MainPageOrg.currentApp(driver);
        log("/*a.----Go to Health Connect Supply Location --*/");
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        //Get Flu supplies using Receive Supplies feature
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        SupplyConsolePage.closeTabsHCA(driver);
        SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
        SupplyConsolePage.selectSupplyLocationFromDropdown(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

        log("/*b.----Receive Supplies for Flu --*/");
        SupplyLocationPage.clickReceiveSuppliesButton(driver);

        ReceiveSuppliesDialog.clickSupplyItemTextBox(driver);
        ReceiveSuppliesDialog.selectSupplyItem(driver, supply_item);
        ReceiveSuppliesDialog.enterTransferDosages(driver, Double.toString(doses));
        //supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to);
        ReceiveSuppliesDialog.selectSupplyDistributionTo(driver);
        ReceiveSuppliesDialog.selectReasonForReception(driver);
        ReceiveSuppliesDialog.clickSaveButton(driver);

        log("/*d.----Create Wastage for the Flu Container --*/");
        //Create Wastage Record for Flu supply item
        scrollDown(driver, 200);
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, supply_item);
        SupplyLocationRelatedItems.selectWastageFromDropDown(driver);

        log("/*f.----Add Doses and Reason for Wastage --*/");
        ContainerWastageForm.enterAdjustmentDosages(driver, Double.toString(doses));
        supplyConsolePage.selectReasonForWastageDropDown();

        log("/*g.----Click Wastage Button--*/");
        supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
        orgMainPage.logout();
    }

    @Test()
    public void Validate_Return_Inventory_as_PPHIS() throws Exception {
        env = Utils.getTargetEnvironment();
        TestcaseID = "261384";
        log("Target Environment: " + env);
        log("Test Case Id: " + "C" + TestcaseID);
        SoftAssert softAssert = new SoftAssert();

        log("/*----Run Pre-conditions --*/");
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
        distribution_to = String.valueOf(testData.get("distributionTo"));
        supply_item = String.valueOf(testData.get("supplyItemInfluenza"));
        lot_number = String.valueOf(testData.get("influenzaLot"));

        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = MainPageOrg.currentApp(driver);
        log("/*a.----Go to Health Connect Supply Location --*/");
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        //Get Flu supplies using Receive Supplies feature
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        SupplyConsolePage.closeTabsHCA(driver);
        SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
        SupplyConsolePage.selectSupplyLocationFromDropdown(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

        log("/*b.----Receive Supplies for Flu --*/");
        SupplyLocationPage.clickReceiveSuppliesButton(driver);

        ReceiveSuppliesDialog.clickSupplyItemTextBox(driver);
        ReceiveSuppliesDialog.selectSupplyItem(driver, supply_item);
        ReceiveSuppliesDialog.enterTransferDosages(driver, Double.toString(doses));
        //supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to);
        ReceiveSuppliesDialog.selectSupplyDistributionTo(driver);
        ReceiveSuppliesDialog.selectReasonForReception(driver);
        ReceiveSuppliesDialog.clickSaveButton(driver);

        log("/*d.----Create Wastage for the Flu Container --*/");
        //Create Wastage Record for Flu supply item
        scrollDown(driver, 200);
        SupplyLocationRelatedItems.clickOnContainerDropDownMenu(driver, supply_item);
        SupplyLocationRelatedItems.selectWastageFromDropDown(driver);

        log("/*f.----Add Doses and Reason for Wastage --*/");
        ContainerWastageForm.enterAdjustmentDosages(driver, Double.toString(doses));
        supplyConsolePage.selectReasonForWastageDropDown();

        log("/*g.----Click Wastage Button--*/");
        supplyConsolePage.clickBtnWastageAtContainerWastagePopUp();
        orgMainPage.logout();

        //TC itself
        log("/*1.----Login --*/");
        orgMainPage = loginPage.orgLoginAsPPHIS();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }

        log("/*3. ----Close all open tabs --*/");
        SupplyConsolePage.closeTabsHCA(driver);

        log("/*4. ----Open Supply Location " + supply_location_from + " --*/");
        SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
        SupplyConsolePage.selectSupplyLocationFromDropdown(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location_from);

        log("/*5. ----Click Return Button --*/");
        SupplyLocationPage.clickReturnButton(driver);

        String supply_location_from_value = ReturnDialog.getReturnFromValue(driver);

        log("/*6. ----Select Supply Location Return To --*/");
        ReturnDialog.selectReturnTo(driver, supply_location_to);

        log("/*7. ----Enter Sender Comment --*/");
        ReturnDialog.typeReturnComments(driver, "This is to Add Return");

        log("/*8. ----Save Return --*/");
        ReturnDialog.clickSaveBtn(driver);
        log("/*9. ----Verify The Return with Return_ID is created and click Return_ID link from Toast Box --*/");
        String return_id = AlertDialog.clickAlertLink(driver);
        log("/* Result: ----The Return with Return_ID " + return_id +" is created --*/");
        //supplyConsolePage.clickReturnsTab();
        //supplyConsolePage.openReturnDetails(return_id);

        log("/*10. ----Verify The Return Status and Sender Comment --*/");
        Thread.sleep(2000);
        String return_status = ReturnPage.getReturnStatus(driver);

        softAssert.assertEquals(return_status, "Draft");

        String return_id_from_details = ReturnPage.getReturnId(driver);
        String returned_from = ReturnPage.getReturnedFromValue(driver);
        String returned_to = ReturnPage.getReturnedToValue(driver);
        String sender_comment = ReturnPage.getSenderComment(driver);

        softAssert.assertEquals(sender_comment, "This is to Add Return");

        log("/*11. ----Click Add Line Item Button --*/");
        ReturnPage.clickAddLineItemButton(driver);

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
        boolean alert_found = false;
        String alert_content = null;
        log("/*16. ----Verify Add Line Item Success Dialog Appear --*/");
        alert_found = AlertDialog.alertFound(driver);
        if(alert_found) {
            alert_content = AlertDialog.getAlertContent(driver).getText();
            System.out.println(alert_content);
            AlertDialog.closeAlert(driver);
            softAssert.assertEquals(alert_content, "Success\nReturn Line Items added successfully.");
        }
        log("/*17. ----Verify Return Line Item record is created and Info is correct --*/");
        Map<String, WebElement> line_items = ReturnPage.getReturnLineItemsTable(driver);
        String return_line_item_number = line_items.get("Return Line Item Number").getText();
        String supply_transaction_name = ReturnPage.getLinkTextFromCellValue(line_items.get("Supply Transaction Name"));
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
        ReturnPage.clickPrintButton(driver);
        boolean print_label_btn_exists = PrintReturnFormDialog.printReturnLabelBtnExists(driver);
        boolean print_manifest_btn_exists = PrintReturnFormDialog.printReturnManifestBtnExists(driver);
        boolean close_btn_exists = PrintReturnFormDialog.closeBtnExists(driver);
        softAssert.assertTrue(print_label_btn_exists, "Print Label Button not found");
        softAssert.assertTrue(print_manifest_btn_exists, "Print Manifest Button not found");
        softAssert.assertTrue(close_btn_exists, "Close Button not found");

        log("/*19. ----Verify Close Print Return Form --*/");
        PrintReturnFormDialog.clickCloseBtn(driver);

        log("/*20. ----Verify Ship Return Form --*/");
        ReturnPage.clickShipReturnButton(driver);

        log("/*21. ----Verify Save Ship Return Form --*/");
        ShipReturnDialog.clickSaveBtn(driver);

        log("/*22. ----Verify Success Dialog --*/");
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);
        softAssert.assertTrue(alert_content.contains("You have successfully Shipped the Return."));

        log("/*23. ----Verify Return Status is changed to Shipped --*/");
        String return_status_shipped = ReturnPage.getReturnStatus(driver);

        softAssert.assertEquals(return_status_shipped, "Shipped");

        log("/*24. ----Click Receive Return Button --*/");
        ReturnPage.clickReceiveReturnButton(driver);

        log("/*25. ----Enter Receiver Comment --*/");
        ReceiveReturnDialog.typeReceiverComment(driver, receiver_comment);

        log("/*26. ----Save Receive Return Form --*/");
        ReceiveReturnDialog.clickSaveBtn(driver);

        log("/*27. ----Verify Success Dialog --*/");
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        softAssert.assertTrue(alert_content.contains( "You have successfully received the Return."));

        log("/*28. ----Verify Return Status is changed to Received --*/");
        String return_status_received = ReturnPage.getReturnStatus(driver);
        softAssert.assertEquals(return_status_received, "Received");
        log("/*29. ----Verify Return Location History --*/");
        Map<String, WebElement> location_history = ReturnPage.getReturnLocationHistoryTable(driver);
        String history_return_id = ReturnPage.getReturnLocationHistoryId(location_history.get("Return Location History ID"));
        WebElement history_receive_date_row = location_history.get("Received Date");
        String history_receive_date = history_receive_date_row.getText();
        WebElement history_actioned_by_row = location_history.get("Actioned By");
        if(history_actioned_by_row == null) {
            history_actioned_by_row = location_history.get("Received By");
        }
        String history_actioned_by = history_actioned_by_row.getText();
        String history_from_location = ReturnPage.getLinkTextFromCellValue(location_history.get("From Location"));
        String history_to_location = ReturnPage.getLinkTextFromCellValue(location_history.get("To Location"));
        WebElement history_receiver_comment_row = location_history.get("Comment");
        if(history_receiver_comment_row == null) {
            history_receiver_comment_row = location_history.get("Receiver Comment");
        }
        String history_receiver_comment = history_receiver_comment_row.getText();

        softAssert.assertTrue(!history_return_id.isEmpty(), "History Return ID is Empty");
        softAssert.assertTrue(!history_receive_date.isEmpty(), "History Return Receive Date is Empty");
        softAssert.assertTrue(!history_actioned_by.isEmpty(), "History Return Actioned By is Empty");
        softAssert.assertEquals(history_from_location, supply_location_from, "Supply Location From doesn't match");
        softAssert.assertEquals(history_to_location, supply_location_to, "Supply Location To doesn't match");
        softAssert.assertEquals(history_receiver_comment, receiver_comment, "History Receiver Comment doesn't match");

        log("/*30. ----Verify Forward Return Dialog --*/");
        ReturnPage.clickForwardReturnButton(driver);
        String forward_return_id = ForwardReturnDialog.getReturnId(driver);
        String forward_supply_location = ForwardReturnDialog.getOriginalSupplyLocation(driver);
        String forward_returned_to = ForwardReturnDialog.getReturnedTo(driver);

        softAssert.assertEquals(forward_return_id, return_id, "Return IDs not match");
        softAssert.assertEquals(forward_supply_location, returned_from, "Forward Supply Locations don't match");
        softAssert.assertAll();
    }
}
