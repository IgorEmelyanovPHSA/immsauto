package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ViewEditAlertPage extends BasePage {
    public ViewEditAlertPage(WebDriver driver) {
        super(driver);
    }

    public static void setAlertEffectiveTo(WebDriver driver, String alert_date_to) throws InterruptedException {
        Thread.sleep(500);
        By effective_to_path = By.xpath("//input[@name='BCH_EffectiveTo__c']");
        waitForElementToBeEnabled(driver, effective_to_path, 10);
        WebElement new_alert_effective_to = driver.findElement(effective_to_path);
        scrollCenter(driver, new_alert_effective_to);
        Thread.sleep(500);
        new_alert_effective_to.clear();
        Thread.sleep(500);
        new_alert_effective_to.sendKeys(alert_date_to);
    }

    public static List<String> getAlertReasonForUpdate(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_reason_for_update_input_path = By.xpath("//button[@name='BCH_ReasonForUpdate__c']");
        waitForElementToBeEnabled(driver, alert_reason_for_update_input_path, 10);
        WebElement alert_reason_for_update_input = driver.findElement(alert_reason_for_update_input_path);
        scrollCenter(driver, alert_reason_for_update_input);
        Thread.sleep(500);
        alert_reason_for_update_input.click();
        Thread.sleep(500);
        By alert_reason_for_update_list_path = By.xpath("//div[@aria-label='Reason for Update']/lightning-base-combobox-item");
        List<WebElement> alert_reason_for_update_list = driver.findElements(alert_reason_for_update_list_path);
        List<String> my_reasons = new ArrayList<String>();
        for(WebElement reason : alert_reason_for_update_list) {
            WebElement my_type_node = reason.findElement(By.xpath(".//span[@class='slds-media__body']/span"));
            my_reasons.add(my_type_node.getText());
        }
        alert_reason_for_update_input.sendKeys(Keys.TAB);
        return my_reasons;
    }

    public static void selectAlertReasonForUpdate(WebDriver driver, String reason_for_update) throws InterruptedException {
        Thread.sleep(500);
        //By alert_reason_for_update_input_path = By.xpath("//button[@aria-label='Reason for Update - Current Selection: --None--' or @aria-label='Reason for Update']");
        By alert_reason_for_update_input_path = By.xpath("//button[@name='BCH_ReasonForUpdate__c']");
        waitForElementToBeEnabled(driver, alert_reason_for_update_input_path, 10);
        WebElement alert_reason_for_update_input = driver.findElement(alert_reason_for_update_input_path);
        scrollCenter(driver, alert_reason_for_update_input);
        Thread.sleep(500);
        alert_reason_for_update_input.click();
        Thread.sleep(500);
        By alert_reason_for_update_item_path = By.xpath("//div[@aria-label='Reason for Update']//span[@title='" + reason_for_update + "']");
        WebElement alert_reason_for_update_item = driver.findElement(alert_reason_for_update_item_path);
        scrollCenter(driver, alert_reason_for_update_item);
        alert_reason_for_update_item.click();
    }

    public static void setAlertUpdateComments(WebDriver driver, String comment) throws InterruptedException {
        Thread.sleep(500);
        By alert_update_comments_path = By.xpath("//textarea[@name='newAlertMessage']");
        waitForElementToBeEnabled(driver, alert_update_comments_path, 10);
        WebElement alert_update_comments = driver.findElement(alert_update_comments_path);
        scrollCenter(driver, alert_update_comments);
        Thread.sleep(500);
        alert_update_comments.sendKeys(comment);
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_save_btn_path = By.xpath("//lightning-record-edit-form//button[text()='Save']");
        waitForElementToBeEnabled(driver, alert_save_btn_path, 10);
        WebElement alert_save_btn = driver.findElement(alert_save_btn_path);
        scrollCenter(driver, alert_save_btn);
        Thread.sleep(500);
        alert_save_btn.click();
        Thread.sleep(500);
    }
}
