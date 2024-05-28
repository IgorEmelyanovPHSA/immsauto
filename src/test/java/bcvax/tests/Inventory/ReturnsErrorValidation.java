package bcvax.tests.Inventory;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ReturnsErrorValidation extends BaseTest {
    String env;
    Map<String, Object> testData;
    MainPageOrg orgMainPage;
    String supply_item = "FluMist-Tri - BK2024B";
    String lot_number = "BK2024B";
    String supply_location = "Automation Supply Location_1";

    String supply_location_from;
    String supply_location_to = "Automation Supply Location_2";
    double doses = 100;
    String distribution_to = "Automation Supply Distribution_1_1";
    String reason_for_wastage = "CCI: Equipment Malfunction";
    String receiver_comment = "This is to test the Receiver Comment";

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        log("/*----Run Pre-conditions --*/");
        testData = Utils.getTestData(env);
        supply_location_from = String.valueOf(testData.get("supplyLocationFrom"));
        supply_location_to = String.valueOf(testData.get("supplyLocationTo"));
        distribution_to = String.valueOf(testData.get("distributionTo"));
        //Login as Admin
        log("/*----Login as Admin --*/");
        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = MainPageOrg.currentApp(driver);
        log("/*a.----Go to Health Connect Supply Location --*/");
        if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
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
        supplyConsolePage.clickSupplyItemTextBox();
        SupplyConsolePage.selectSupplyItem(driver, supply_item);
        ContainerTransferForm.enterTransferDosages(driver, Double.toString(doses));
        //supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to);
        supplyConsolePage.selectIncomingSupplyDistributionReceive();
        supplyConsolePage.selectReasonForReception();
        supplyConsolePage.ClickSaveButton();

        log("/*d.----Create Wastage for the Flu Container --*/");
        //Create Wastage Record for Flu supply item
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
        log("Target Environment: "+ Utils.getTargetEnvironment());
        SoftAssert softAssert = new SoftAssert();
        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin_org":
                log("Login as ImmsBCAdmin");
                TestcaseID = "261439"; //C261439
                loginPage.loginAsImmsBCAdmin();
                break;
            default:
                log("Login AS default user (PPHIS)");
                TestcaseID = "261384";
                orgMainPage = loginPage.orgLoginAsPPHIS();
        }

        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

        log("/*3. ----Close all open tabs --*/");
        SupplyConsolePage.closeTabsHCA(driver);

        log("/*4. ----Open Supply Location " + supply_location + " --*/");
        SupplyConsolePage.clickSupplyConsoleAppNavigationMenu(driver);
        SupplyConsolePage.selectSupplyLocationFromDropdown(driver);
        SupplyLocationsPage.selectSupplyLocationName(driver, supply_location);

        log("/*5. ----Click Return Button --*/");
        SupplyLocationPage.clickReturnButton(driver);

        log("/*6. ----Leave the Supply Location for the Returned To blank --*/");

        log("/*7. ----Enter Sender Comment --*/");
        ReturnDialog returnDialog = new ReturnDialog(driver);
        String supply_location_from_value = returnDialog.getReturnFromValue();
        returnDialog.typeReturnComments("This is to Add Return");

        log("/*8. ----Save Return --*/");
        returnDialog.clickSaveBtn();

        log("/*9. ----Verify the error message --*/");
        String errorMsg = returnDialog.getReturnError();
        softAssert = new SoftAssert();
        softAssert.assertEquals(errorMsg, "These required fields must be completed: Returned To", "Error message mismatch");

        log("/*10. ----Select Supply Location Return To --*/");
        returnDialog.selectReturnTo(supply_location_to);

        log("/*11. ----Save Return --*/");
        returnDialog.clickSaveBtn();

        log("/*11. ----Verify The Return with Return_ID is created and click Return_ID link from Toast Box --*/");
        String return_id = AlertDialog.clickAlertLink(driver);

        log("/* Result: ----The Return with Return_ID " + return_id +" is created --*/");
        //supplyConsolePage.clickReturnsTab();
        //supplyConsolePage.openReturnDetails(return_id);

        log("/*12. ----Verify The Return Status and Sender Comment --*/");
        Thread.sleep(2000);
        ReturnPage returnPage = new ReturnPage(driver);
        String return_status = returnPage.getReturnStatus();

        softAssert.assertEquals(return_status, "Draft");

        String return_id_from_details = returnPage.getReturnId();
        String returned_from = returnPage.getReturnedFromValue();
        String returned_to = returnPage.getReturnedToValue();
        String sender_comment = returnPage.getSenderComment();

        softAssert.assertEquals(sender_comment, "This is to Add Return");

        log("/*13. ----Click Add Line Item Button --*/");
        returnPage.clickAddLineItemButton();

        log("/*14. ----Verify the Add Return Line Item popup window is displayed with correct Return ID and Supply Location --*/");
        String return_id_from_add_line_items = AddReturnLineItemsDialog.getReturnId(driver);
        softAssert.assertEquals(return_id_from_add_line_items, return_id);
        String return_from_from_add_line_items = AddReturnLineItemsDialog.getReturnFrom(driver);
        softAssert.assertEquals(return_from_from_add_line_items, supply_location_from_value);
        //Select First Wastage from the list

        log("/*15. ----Select First Wastage --*/");
        AddReturnLineItemsDialog.checkWastageItem(driver, 0);

        log("/*16. ----Click Next Button --*/");
        AddReturnLineItemsDialog.clickNextBtn(driver);

        log("/*17. ----Enter a value greater than the Wastage Doses --*/");
        Double wastageDoses = AddReturnLineItemsDialog.getWastageDoses(driver);
        AddReturnLineItemsDialog.enterReturnedDosesValue(driver, wastageDoses + 1);
        log("/*18. ----Click Save Button --*/");
        AddReturnLineItemsDialog.clickSaveBtn(driver);
        //Check if errors found
        String errorMsgForWastage = returnDialog.getReturnWastageError();
        Assert.assertEquals(errorMsgForWastage, "Returned Doses must be less than or equal to Wastage Doses.", "Error message mismatch for excessive doses");

        // e. Enter a value of 0
        log("/*19. ----Enter a value of 0 --*/");

        AddReturnLineItemsDialog.enterReturnedDosesValue(driver, 0);
        log("/*20. ----Click Save Button --*/");
        AddReturnLineItemsDialog.clickSaveBtn(driver);
        //Check if errors found
        errorMsgForWastage = returnDialog.getReturnWastageError();
        Assert.assertEquals(errorMsgForWastage, "Returned Doses cannot be zero or negative. Cancel the line item instead.", "Error message mismatch for excessive doses");

        // f. Enter a negative value
        log("/*21. ---- Enter a negative value --*/");
        AddReturnLineItemsDialog.enterReturnedDosesValue(driver, -1);
        log("/*22. ----Click Save Button --*/");
        AddReturnLineItemsDialog.clickSaveBtn(driver);
        //Check if errors found
        errorMsgForWastage = returnDialog.getReturnWastageError();
        Assert.assertEquals(errorMsgForWastage, "Returned Doses cannot be zero or negative. Cancel the line item instead.", "Error message mismatch for excessive doses");

        // f. Enter a value with two decimal places that is greater than 0 but less than or equal to with the Wastage Doses
        log("/*23. ----Enter a value with two decimal places that is greater than 0 but less than or equal to with the Wastage Doses --*/");
        wastageDoses = AddReturnLineItemsDialog.getWastageDoses(driver);
        AddReturnLineItemsDialog.enterReturnedDosesValue(driver, wastageDoses - 1);
        log("/*24. ----Click Save Button --*/");
        AddReturnLineItemsDialog.clickSaveBtn(driver);

        log("/*25. ----Verify Add Line Item Success Dialog Appear --*/");
        boolean alert_found = AlertDialog.alertFound(driver);
        String alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        softAssert.assertEquals(alert_content, "Success\nReturn Line Items added successfully.");

        log("/*26. ----Click Ad hoc Line Item Button --*/");
        returnPage.clickAdHocLineItemButton();

        log("/*27. ----Click the Save button --*/");
        AddAdHocLineItemsDialog.clickSaveBtn(driver);

        log("/*28. ----Verify the error message: 'These required fields must be completed: Returned Doses' --*/");
        errorMsg = returnDialog.getReturnError();
        softAssert.assertEquals(errorMsg, "These required fields must be completed: Returned Doses", "Error message mismatch");

        log("/*29. ---- Enter a value with two decimal places that is greater than 0 --*/");
        AddAdHocLineItemsDialog.enterReturnedDosesValue(driver, 5.25);
        AddAdHocLineItemsDialog.clickSaveBtn(driver);

        log("/*30. ----Verify that a successful toast box is displayed --*/");
        log("/*31. ----Verify The Return with Return_ID is created and click Return_ID link from Toast Box --*/");
        return_id = AlertDialog.clickAlertLink(driver);
        log("/* Result: ----The Return with Return_ID " + return_id +" is created --*/");
        // Insert the steps to verify each record's field value

        //go to previous page in chrome
        Thread.sleep(500);
        driver.navigate().back();
        log("/*32. ----Click Ship Return button --*/");
        returnPage.clickShipReturnButton();
        ShipReturnDialog shipReturnDialog = new ShipReturnDialog(driver);

        shipReturnDialog.removeReturnedTo();
        log("/*33. ----Remove the value in the Returned To field --*/");
        shipReturnDialog.clickSaveBtn(driver);

        //The error message "These required fields must be completed: Returned To" is displayed
        String errorMsgForShipReturn = returnDialog.getReturnError();
        Assert.assertEquals(errorMsgForShipReturn, "These required fields must be completed: Returned To", "Error message mismatch for excessive doses");
        //put returned to again
        shipReturnDialog.selectSupplyLocationFromDropdown(supply_location_to);
        shipReturnDialog.clickSaveBtn(driver);

        log("/*34. ----Verify Success Dialog --*/");
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);
        softAssert.assertEquals(alert_content, "You have successfully Shipped the Return.");

        log("/*35. ----Verify Return Status is changed to Shipped --*/");
        String return_status_shipped = returnPage.getReturnStatus();
        softAssert.assertEquals(return_status_shipped, "Shipped");

        log("/*36. ----Click Receive Return Button --*/");
        returnPage.clickReceiveReturnButton();

        log("/*37. ----Enter Receiver Comment --*/");
        ReceiveReturnDialog.typeReceiverComment(driver, receiver_comment);

        log("/*38. ----Save Receive Return Form --*/");
        ReceiveReturnDialog.clickSaveBtn(driver);

        log("/*39. ----Verify Success Dialog --*/");
        alert_found = AlertDialog.alertFound(driver);
        alert_content = AlertDialog.getAlertContent(driver).getText();
        System.out.println(alert_content);
        AlertDialog.closeAlert(driver);

        softAssert.assertEquals(alert_content, "You have successfully received the Return.");

        log("/*40. ----Verify Return Status is changed to Received --*/");
        String return_status_received = returnPage.getReturnStatus();
        softAssert.assertEquals(return_status_received, "Received");

        log("/*41. ----Verify Forward Return Dialog --*/");
        returnPage.clickForwardReturnButton();
        String forward_return_id = ForwardReturnDialog.getReturnId(driver);
        String forward_supply_location = ForwardReturnDialog.getOriginalSupplyLocation(driver);
        String forward_returned_to = ForwardReturnDialog.getReturnedTo(driver);
        //softAssert.assertEquals(forward_return_id, return_id, "Return IDs not match");
        softAssert.assertEquals(forward_supply_location, returned_from, "Forward Supply Locations don't match");
        softAssert.assertAll();
        ForwardReturnDialog forwardReturnDialog = new ForwardReturnDialog(driver);
        log("/*42. ----Click on Save button --*/");
        forwardReturnDialog.clickSaveBtn(driver);
        log("/*43. ----The error message A value is required. is displayed --*/");
        String errorMsgForForwardReturn = forwardReturnDialog.getReturnError();
        Assert.assertEquals(errorMsgForForwardReturn, "A value is required.", "Error message mismatch for excessive doses");
        log("/*44. ----Put the value back --*/");
        forwardReturnDialog.selectSupplyLocationFromDropdown(supply_location);
        log("/*45. ----Click on Save button --*/");
        forwardReturnDialog.clickSaveBtn(driver);
        log("/*46. ----The message You have successfully Forwarded the Return. is displayed --*/");
        alert_content = forwardReturnDialog.successContent();
        System.out.println(alert_content);
        softAssert.assertEquals(alert_content, "You have successfully Forwarded the Return.");




    }
}
