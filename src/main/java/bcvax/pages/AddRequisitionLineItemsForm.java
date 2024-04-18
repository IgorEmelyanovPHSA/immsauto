package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;
import java.util.List;

public class AddRequisitionLineItemsForm extends BasePage {
    public AddRequisitionLineItemsForm(WebDriver driver) {
        super(driver);
    }

    public static void checkShowInStockCheckbox(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By show_trades_in_stock_ckbox_path = By.xpath("//div[text() = 'Show trades in stock']/..//span[@part = 'input-checkbox']");
        waitForElementToBeEnabled(driver, show_trades_in_stock_ckbox_path, 10);
        WebElement show_trades_in_stock_ckbox = driver.findElement(show_trades_in_stock_ckbox_path);
        show_trades_in_stock_ckbox.click();
    }

    public static void clickLineItemCheckBox(WebDriver driver, int itemNum) throws InterruptedException {
        Thread.sleep(500);
        By requisition_line_items_table_path = By.xpath("//table[@c-tradeselectionmodal_tradeselectionmodal]");
        waitForElementToBeEnabled(driver, requisition_line_items_table_path, 10);
        WebElement requisition_line_items_table_node = driver.findElement(requisition_line_items_table_path);
        GenericTable requisition_line_items_table = new GenericTable(requisition_line_items_table_node);

        WebElement my_checkbox = requisition_line_items_table.getRowsMappedToHeadings().get(itemNum).get("").findElement(By.xpath(".//span[@part='indicator']"));
        scrollCenter(driver, my_checkbox);
        Thread.sleep(500);
        my_checkbox.click();
    }

    public static void clickNextButton(WebDriver driver) throws InterruptedException{
        Thread.sleep(500);
        By next_button_path = By.xpath("//c-trade-selection-modal//button[text()='Next']");
        waitForElementToBeEnabled(driver, next_button_path, 10);
        WebElement next_button = driver.findElement(next_button_path);
        scrollCenter(driver, next_button);
        next_button.click();
    }

    public static void inputRequestedDose(WebDriver driver, String inputQuantity) throws InterruptedException {
        Thread.sleep(500);
        By requisition_line_items_table_path = By.xpath("//table[@c-requisitionlineitemmodal_requisitionlineitemmodal]");
        waitForElementToBeEnabled(driver, requisition_line_items_table_path, 10);
        WebElement requisition_line_items_table_node = driver.findElement(requisition_line_items_table_path);
        GenericTable requisition_line_items_table = new GenericTable(requisition_line_items_table_node);
        List<Map<String, WebElement>> requisition_line_items =  requisition_line_items_table.getRowsMappedToHeadings();
        if(requisition_line_items.size() < 2) {
            Thread.sleep(2000);
            requisition_line_items_table_node = driver.findElement(requisition_line_items_table_path);
            requisition_line_items_table = new GenericTable(requisition_line_items_table_node);
            requisition_line_items =  requisition_line_items_table.getRowsMappedToHeadings();
        }
        WebElement doses_input = requisition_line_items.get(1).get("Requested Doses").findElement(By.xpath(".//input"));
        scrollCenter(driver, doses_input);
        Thread.sleep(500);
        doses_input.clear();
        doses_input.sendKeys(inputQuantity);
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        By save_btn_path = By.xpath("//button[text()='Save']");
        waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        scrollCenter(driver, save_btn);
        save_btn.click();
    }
}
