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

    public String getReturnId() throws InterruptedException {
        Thread.sleep(500);
        By return_id_path = By.xpath("//span[@class='test-id__field-label' and text()='Return ID']/../..//lightning-formatted-text[@slot='outputField']"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Return ID']/../../div[@class='slds-form-element__control slds-grid itemBody']/span/span"));
        waitForElementToBeEnabled(driver, return_id_path, 10);
        WebElement return_id = driver.findElement(return_id_path);
        return return_id.getText();
    }

    public String getReturnStatus() throws InterruptedException {
        Thread.sleep(500);
        By return_status_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Status__c']//lightning-formatted-text[@slot='outputField']"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Status']/../../div[@class='slds-form-element__control slds-grid itemBody']/span/span"));
        waitForElementToBeEnabled(driver, return_status_path, 10);
        WebElement return_status = driver.findElement(return_status_path);
        return return_status.getText();
    }

    public String getPageTitle() throws InterruptedException {
        return new String();
    }

    public String getReturnedFromValue() throws InterruptedException {
        Thread.sleep(500);
        By return_from_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Returned_From__c']//a");
                //.concat(" | ")
                //.concat("//span[@class='test-id__field-label' and text()='Returned From']//a"));
        waitForElementToBeEnabled(driver, return_from_path, 10);
        WebElement return_from = driver.findElement(return_from_path);
        if(return_from.getText().isEmpty()) {
            return return_from.findElement(By.xpath(".//span")).getText();
        } else {
            return return_from.getText();
        }
    }

    public Map<String, WebElement> getReturnLineItemsTable() throws InterruptedException {
        Thread.sleep(500);
        GenericTable return_line_items_table = tables.getReturnLineItemsTable();
        Map<String, WebElement> first_row = return_line_items_table.getRowsMappedToHeadings().get(1);
        return first_row;
    }

    public Map<String, WebElement> getReturnLineItemsTableCP() throws InterruptedException {
        Thread.sleep(500);
        GenericTable return_line_items_table = tables.getReturnLineItemsTableCP();
        Map<String, WebElement> first_row = return_line_items_table.getRowsMappedToHeadings().get(1);
        return first_row;
    }

    public String getLinkTextFromCellValue(WebElement table_value) {
        return table_value.findElement(By.xpath(".//a")).getAttribute("title");
    }

    public String getReturnLocationHistoryId(WebElement table_value) {
        return table_value.findElement(By.xpath(".//a//span")).getText();
    }

    public String getReturnLocationHistoryIdCP(WebElement table_value) {
        return table_value.findElement(By.xpath(".//a")).getText();
    }

    public Map<String, WebElement> getReturnLocationHistoryTable() throws InterruptedException {
        Thread.sleep(500);
        GenericTable return_location_history_table = tables.getReturnsLocationHistoryTable();
        int table_size = return_location_history_table.getRows().size();
        if(table_size > 0) {
            Map<String, WebElement> first_row = return_location_history_table.getRowsMappedToHeadings().get(1);
            return first_row;
        } else {
            Map<String, WebElement> empty_row = new HashMap<>();
            return empty_row;
        }
    }

    public Map<String, WebElement> getReturnLocationHistoryTableCP() throws InterruptedException {
        Thread.sleep(500);
        GenericTable return_location_history_table = tables.getReturnsLocationHistoryTableCP();
        int table_size = return_location_history_table.getRows().size();
        if(table_size > 0) {
            Map<String, WebElement> first_row = return_location_history_table.getRowsMappedToHeadings().get(1);
            return first_row;
        } else {
            Map<String, WebElement> empty_row = new HashMap<>();
            return empty_row;
        }
    }

    public String getReturnedToValue() throws InterruptedException {
        Thread.sleep(500);
        By return_to_path = By.xpath("//span[@class='test-id__field-label' and text()='Returned To']/../..//a//span[@force-lookup_lookup]"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Returned To']/../..//a"));
        waitForElementToBeEnabled(driver, return_to_path, 10);
        WebElement return_to = driver.findElement(return_to_path);
        return return_to.getText();
    }

    public String getSenderComment() throws InterruptedException {
        Thread.sleep(500);
        By sender_comment_path = By.xpath("//span[@class='test-id__field-label' and text()='Sender Comment']/../..//lightning-formatted-text[@slot='outputField']"
                .concat(" | ")
                .concat("//span[@class='test-id__field-label' and text()='Sender Comment']/../../div[@class='slds-form-element__control slds-grid itemBody']/span/span"));
        waitForElementToBeEnabled(driver, sender_comment_path, 10);
        WebElement sender_comment = driver.findElement(sender_comment_path);
        return sender_comment.getText();
    }

    public void clickAddLineItemButton() throws InterruptedException {
        Thread.sleep(500);
        By add_line_item_btn_path = By.xpath("//button[@name='HC_Return__c.Add_Line_Items']"
                .concat(" | ")
                .concat("//a[@title = 'Add Line Items']"));
        waitForElementToBeEnabled(driver, add_line_item_btn_path, 10);
        WebElement add_line_items_btn = driver.findElement(add_line_item_btn_path);
        add_line_items_btn.click();
    }

    public void clickShipReturnButton() throws InterruptedException {
        Thread.sleep(500);
        By ship_return_btn_path = By.xpath("//button[@name='HC_Return__c.Ship_Return']"
                .concat(" | ")
                .concat("//a[@title = 'Ship Return']"));
        waitForElementToBeEnabled(driver, ship_return_btn_path, 10);
        WebElement ship_return_btn = driver.findElement(ship_return_btn_path);
        ship_return_btn.click();
    }

    public void clickReceiveReturnButton() throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//button[@name='HC_Return__c.Receive_Return']");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public void clickReceiveReturnButtonCP() throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.HC_Return__c.Receive_Return']/a");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public void clickPrintButton() throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//button[@name='HC_Return__c.Print']");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public void clickPrintButtonCP() throws InterruptedException {
        Thread.sleep(500);
        By extend_list_btn_path = By.xpath("//a[@class='slds-grid slds-grid--vertical-align-center slds-grid--align-center sldsButtonHeightFix']");
        waitForElementToBeEnabled(driver, extend_list_btn_path, 10);
        WebElement extend_btn = driver.findElement(extend_list_btn_path);
        extend_btn.click();
        Thread.sleep(500);
        By print_return_btn_path = By.xpath("//a[@data-target-selection-name='sfdc:QuickAction.HC_Return__c.Print']");
        waitForElementToBeLocated(driver, print_return_btn_path, 10);
        WebElement print_return_btn = driver.findElement(print_return_btn_path);
        print_return_btn.click();
    }

    public void clickForwardReturnButton() throws InterruptedException {
        Thread.sleep(500);
        By receive_return_btn_path = By.xpath("//button[@name='HC_Return__c.Forward_Return_Flow']");
        waitForElementToBeEnabled(driver, receive_return_btn_path, 10);
        WebElement receive_return_btn = driver.findElement(receive_return_btn_path);
        receive_return_btn.click();
    }

    public void clickForwardReturnButtonCP() throws InterruptedException {
        Thread.sleep(500);
        By forward_return_btn_path = By.xpath("//li[@data-target-selection-name='sfdc:QuickAction.HC_Return__c.Forward_Return_Flow']/a");
        waitForElementToBeEnabled(driver, forward_return_btn_path, 10);
        WebElement forward_return_btn = driver.findElement(forward_return_btn_path);
        forward_return_btn.click();
    }
}
