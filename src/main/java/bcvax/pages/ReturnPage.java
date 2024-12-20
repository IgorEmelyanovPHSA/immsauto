package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class ReturnPage extends BasePage {
    Tables tables;
    public ReturnPage(WebDriver driver) {
        super(driver);
        tables = new Tables(driver);
    }

    public static String getReturnId(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_id_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.Name']//lightning-formatted-text"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Return ID']/../../div[@class='slds-form-element__control slds-grid itemBody']/span/span"));
        waitForElementToBeEnabled(driver, return_id_path, 10);
        WebElement return_id = driver.findElement(return_id_path);
        return return_id.getText();
    }

    public static String getReturnStatus(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_status_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Status__c']//lightning-formatted-text[@slot='outputField']"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Status']/../../div[@class='slds-form-element__control slds-grid itemBody']/span/span"));
        waitForElementToBeEnabled(driver, return_status_path, 10);
        WebElement return_status = driver.findElement(return_status_path);
        return return_status.getText();
    }

    public static String getReturnedFromValue(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_from_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Returned_From__c']//a"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Returned From']//a"));
        waitForElementToBeEnabled(driver, return_from_path, 10);
        WebElement return_from = driver.findElement(return_from_path);
        return return_from.getAttribute("innerText");
    }

    public static Map<String, WebElement> getReturnLineItemsTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        GenericTable return_line_items_table = getReturnLineItemsGenericTable(driver);
        Map<String, WebElement> first_row = return_line_items_table.getRowsMappedToHeadings().get(1);
        return first_row;
    }

    public static String getLinkTextFromCellValue(WebElement table_value) {
        return table_value.findElement(By.xpath(".//a")).getAttribute("title");
    }

    public static String getReturnLocationHistoryId(WebElement table_value) {
        return table_value.findElement(By.xpath(".//a//span")).getAttribute("innerText");
    }

    public static String getReturnLocationHistoryIdCP(WebElement table_value) {
        return table_value.findElement(By.xpath(".//a")).getText();
    }

    public static Map<String, WebElement> getReturnLocationHistoryTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        GenericTable return_location_history_table = getReturnsLocationHistoryGenericTable(driver);
        int table_size = return_location_history_table.getRows().size();
        if(table_size > 0) {
            Map<String, WebElement> first_row = return_location_history_table.getRowsMappedToHeadings().get(1);
            return first_row;
        } else {
            Map<String, WebElement> empty_row = new HashMap<>();
            return empty_row;
        }
    }

    public static String getReturnedToValue(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_to_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Returned_To__c']//a//span"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Returned To']/../..//a"));
        waitForElementToBeEnabled(driver, return_to_path, 10);
        WebElement return_to = driver.findElement(return_to_path);
        return return_to.getText();
    }

    public static String getSenderComment(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By sender_comment_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Sender_Comment__c']//lightning-formatted-text"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Sender Comment']/../../div[@class='slds-form-element__control slds-grid itemBody']/span/span"));
        waitForElementToBeEnabled(driver, sender_comment_path, 10);
        WebElement sender_comment = driver.findElement(sender_comment_path);
        return sender_comment.getText();
    }

    public static void clickAddLineItemButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By add_line_item_btn_path = By.xpath("//button[@name='HC_Return__c.Add_Line_Items']"
                .concat(" | ")
                .concat("//a[@title = 'Add Line Items']"));
        waitForElementToBeEnabled(driver, add_line_item_btn_path, 10);
        WebElement add_line_items_btn = driver.findElement(add_line_item_btn_path);
        add_line_items_btn.click();
    }

    public static void clickAdHocLineItemButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By adHocLineItemBtnPath = By.xpath("//button[@name='HC_Return__c.Add_Ad_Hoc_Line_Item']"
                .concat(" | ")
                .concat("//a[@title = 'Add Ad hoc Line Item']"));
        waitForElementToBeEnabled(driver, adHocLineItemBtnPath, 10);
        WebElement adHocLineItemBtn = driver.findElement(adHocLineItemBtnPath);
        adHocLineItemBtn.click();
    }

    public static void clickShipReturnButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By ship_return_btn_path = By.xpath("//button[@name='HC_Return__c.Ship_Return']"
                .concat(" | ")
                .concat("//a[@title = 'Ship Return']"));
        waitForElementToBeEnabled(driver, ship_return_btn_path, 10);
        WebElement ship_return_btn = driver.findElement(ship_return_btn_path);
        ship_return_btn.click();
    }

    public static void clickReceiveReturnButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//button[@name='HC_Return__c.Receive_Return']");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public static void clickPrintButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//button[@name='HC_Return__c.Print']");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public static void clickPrintButtonFromExtended(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By extend_list_btn_path = By.xpath("//li[contains(@data-target-reveals, 'sfdc:QuickAction.HC_Return__c.Print')]//a | //lightning-button-menu[contains(@data-target-reveals, 'sfdc:QuickAction.HC_Return__c.Print')]//button");
        waitForElementToBeEnabled(driver, extend_list_btn_path, 10);
        WebElement extend_btn = driver.findElement(extend_list_btn_path);
        extend_btn.click();
        Thread.sleep(500);
        By print_return_btn_path = By.xpath("//lightning-menu-item[@data-target-selection-name='sfdc:QuickAction.HC_Return__c.Print']//a | //a[@data-target-selection-name='sfdc:QuickAction.HC_Return__c.Print']");
        waitForElementToBeLocated(driver, print_return_btn_path, 10);
        WebElement print_return_btn = driver.findElement(print_return_btn_path);
        print_return_btn.click();
    }

    public static void clickForwardReturnButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//button[@name='HC_Return__c.Forward_Return_Flow']");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public static GenericTable getReturnLineItemsGenericTable(WebDriver driver) {
        WebElement singleTable = null;
        By returns_line_items_table_path = By.xpath("//article[@aria-label='Return Line Items'] | //table[@aria-label='Return Line Items']");
        waitForElementToBeLocated(driver, returns_line_items_table_path, 10);
        singleTable = driver.findElement(returns_line_items_table_path);
        return new GenericTable(singleTable);
    }

    public static GenericTable getReturnsLocationHistoryGenericTable(WebDriver driver) {
        WebElement singleTable = null;
        By returns_location_history_table_path = By.xpath("//article[@aria-label='Returns Location History'] | //table[@aria-label='Returns Location History']");
        waitForElementToBeLocated(driver, returns_location_history_table_path, 10);
        singleTable = driver.findElement(returns_location_history_table_path);
        return new GenericTable(singleTable);
    }
}
