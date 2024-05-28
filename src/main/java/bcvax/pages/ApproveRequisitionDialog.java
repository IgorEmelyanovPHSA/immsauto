package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApproveRequisitionDialog extends BasePage {
    public ApproveRequisitionDialog(WebDriver driver) {
        super(driver);
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_approved_requisition_btn_path = By.xpath("//button[text() = 'Save']");
        waitForElementToBeEnabled(driver, save_approved_requisition_btn_path, 10);
        WebElement save_approved_requisition_btn = driver.findElement(save_approved_requisition_btn_path);
        scrollCenter(driver, save_approved_requisition_btn);
        save_approved_requisition_btn.click();
    }

    public static void enterApprovedDose(WebDriver driver, String inputDose) throws InterruptedException {
        Thread.sleep(500);
        By tradeLabelPath = By.xpath("//label[text() = 'Trade']");
        waitForElementToBePresent(driver, tradeLabelPath, 30);
        WebElement trade = driver.findElement(By.xpath("//label[text() = 'Trade']/..//input"));
        scrollCenter(driver, trade);
        String tradeValue = trade.getAttribute("value");

        By select_supply_container_table_path = By.xpath("//span[text()='Select supply container']/../../../../..//table");
        WebElement select_supply_container_table_node = driver.findElement(select_supply_container_table_path);
        GenericTable select_supply_container_table = new GenericTable(select_supply_container_table_node);

        WebElement approve_dose_cell = select_supply_container_table.getRowsMappedToHeadings().get(1).get("Doses");
        WebElement approve_dose_field = approve_dose_cell.findElement(By.xpath(".//input"));
        approve_dose_field.sendKeys(inputDose);
    }

    public static void enterApproverComments(WebDriver driver, String comment) throws InterruptedException {
        Thread.sleep(500);
        By approver_comment_path = By.xpath("//label[text()='Approver Comment']/..//input");
        waitForElementToBeEnabled(driver, approver_comment_path, 10);
        WebElement approver_comment = driver.findElement(approver_comment_path);
        scrollCenter(driver, approver_comment);
        Thread.sleep(500);
        approver_comment.sendKeys(comment);
    }
}
