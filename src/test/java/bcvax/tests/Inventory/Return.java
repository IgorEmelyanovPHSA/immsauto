package bcvax.tests.Inventory;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class Return extends BaseTest {
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
        testData = Utils.getTestData(env);
        //Login as Admin
        orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        //Get Flu supplies using Receive Supplies feature
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        supplyConsolePage.closeTabsHCA();
        supplyConsolePage.clickSupplyConsoleAppNavigationMenu();
        supplyConsolePage.selectSupplyLocationFromDropdown();
        supplyConsolePage.selectSupplyLocationName(supply_location);
        supplyConsolePage.SelectDropDownToClickReceiveSuppliesButton();
        supplyConsolePage.ClickDropDownToClickReceiveSuppliesButton();
        supplyConsolePage.clickSupplyItemTextBox();
        supplyConsolePage.selectSupplyItem(supply_item);
        supplyConsolePage.enterTransferDosages(Double.toString(doses));
        //supplyConsolePage.selectSupplyDistributionFromDropdown(distribution_to);
        supplyConsolePage.selectIncomingSupplyDistributionReceive();
        supplyConsolePage.selectReasonForReception();
        supplyConsolePage.ClickSaveButton();

        //Create Wastage Record for Flu supply item
        supplyConsolePage.clickOnContainerDropDownMenu(supply_item, distribution_to);
        supplyConsolePage.selectWastageFromDropDown();
        supplyConsolePage.setDosesAmount(Double.toString(doses));
        supplyConsolePage.selectReasonForWastageDropDown();
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
                TestcaseID = "244853"; //C244853
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
        log("/*-- 3. Close all open tabs --*/");
        supplyConsolePage.closeTabsHCA();
        supplyConsolePage.clickSupplyConsoleAppNavigationMenu();
        supplyConsolePage.selectSupplyLocationFromDropdown();
        supplyConsolePage.selectSupplyLocationName(supply_location);
        supplyConsolePage.clickReturnBtn();

        ReturnDialog returnDialog = new ReturnDialog(driver);
        String supply_location_from_value = returnDialog.getReturnFromValue();
        returnDialog.selectReturnTo(supply_location_to);
        returnDialog.typeReturnComments("This is to Add Return");
        returnDialog.clickSaveBtn();
        String return_id = AlertDialog.clickAlertLink(driver);

        //supplyConsolePage.clickReturnsTab();
        //supplyConsolePage.openReturnDetails(return_id);
        ReturnPage returnPage = new ReturnPage(driver);
        String return_status = returnPage.getReturnStatus();
        String return_id_from_details = returnPage.getReturnId();
        String returned_from = returnPage.getReturnedFromValue();
        String returned_to = returnPage.getReturnedToValue();
        String sender_comment = returnPage.getSenderComment();

        returnPage.clickAddLineItemButton();
        String return_id_from_add_line_items = AddReturnLineItemsDialog.getReturnId(driver);
        String return_from_from_add_line_items = AddReturnLineItemsDialog.getReturnFrom(driver);

        //Select First Wastage from the list
        AddReturnLineItemsDialog.checkWastageItem(driver, 0);
        AddReturnLineItemsDialog.clickNextBtn(driver);
        AddReturnLineItemsDialog.clickSaveBtn(driver);

        returnPage.clickShipReturnButton();
        ShipReturnDialog.clickSaveBtn(driver);
        String return_status_shipped = returnPage.getReturnStatus();

        returnPage.clickReceiveReturnButton();

        ReceiveReturnDialog.typeReceiverComment(driver, "This is to test the Receiver Comment");
        ReceiveReturnDialog.clickSaveBtn(driver);
        String return_status_received = returnPage.getReturnStatus();
        System.out.println();
    }
}
