package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AddAlertDialog extends BasePage {
    public AddAlertDialog(WebDriver driver) {
        super(driver);
    }

    public static List<String> getTypesOfAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_types_input_path = By.xpath("//button[@name='BCH_TypeOfAlert__c']");
        waitForElementToBeEnabled(driver, alert_types_input_path, 10);
        WebElement alert_types_input = driver.findElement(alert_types_input_path);
        alert_types_input.click();
        Thread.sleep(500);
        By alert_types_list_path = By.xpath("//div[@aria-label='Type of Alert']/lightning-base-combobox-item");
        List<WebElement> alert_type_list = driver.findElements(alert_types_list_path);
        List<String> my_types = new ArrayList<String>();
        for(WebElement alert_type : alert_type_list) {
            WebElement my_type_node = alert_type.findElement(By.xpath(".//span[@class='slds-media__body']/span"));
            my_types.add(my_type_node.getText());
        }
        alert_types_input.sendKeys(Keys.TAB);
        return my_types;
    }

    public static void setTypesOfAlert(WebDriver driver, String alert_type) throws InterruptedException {
        Thread.sleep(500);
        By alert_types_input_path = By.xpath("//button[@name='BCH_TypeOfAlert__c']");
        waitForElementToBeEnabled(driver, alert_types_input_path, 10);
        WebElement alert_types_input = driver.findElement(alert_types_input_path);
        alert_types_input.click();
        Thread.sleep(500);
        By alert_types_list_path = By.xpath("//div[@aria-label='Type of Alert']/lightning-base-combobox-item");
        List<WebElement> alert_type_list = driver.findElements(alert_types_list_path);
        List<String> my_types = new ArrayList<String>();
        for(WebElement my_alert_type : alert_type_list) {
            WebElement my_type_node = my_alert_type.findElement(By.xpath(".//span[@class='slds-media__body']/span"));
            if(my_type_node.getText().equals(alert_type)) {
                my_type_node.click();
            }
        }
    }

    public static String getAlertMessage(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_message_path = By.xpath("//textarea[@name='alertMessage']");
        waitForElementToBeEnabled(driver, alert_message_path, 10);
        WebElement alert_message = driver.findElement(alert_message_path);
        return alert_message.getText();
    }

    public static void setAlertMessage(WebDriver driver, String alert_message) throws InterruptedException {
        Thread.sleep(500);
        By alert_message_path = By.xpath("//textarea[@name='alertMessage']");
        waitForElementToBeEnabled(driver, alert_message_path, 10);
        WebElement alert_message_field = driver.findElement(alert_message_path);
        scrollCenter(driver, alert_message_field);
        Thread.sleep(500);
        alert_message_field.sendKeys(alert_message);
    }

    public static String getAlertEffectiveFrom(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By effective_from_path = By.xpath("//input[@name='effectiveFrom']");
        waitForElementToBeEnabled(driver, effective_from_path, 10);
        WebElement new_alert_effective_from = driver.findElement(effective_from_path);
        return new_alert_effective_from.getText();
    }

    public static void setAlertEffectiveFrom(WebDriver driver, String alert_date_from) throws InterruptedException {
        Thread.sleep(500);
        By effective_from_path = By.xpath("//input[@name='effectiveFrom']");
        waitForElementToBeEnabled(driver, effective_from_path, 10);
        WebElement new_alert_effective_from = driver.findElement(effective_from_path);
        scrollCenter(driver, new_alert_effective_from);
        new_alert_effective_from.sendKeys(alert_date_from);
    }
    public static String getAlertEffectiveTo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By effective_to_path = By.xpath("//input[@name='effectiveTo']");
        waitForElementToBeEnabled(driver, effective_to_path, 10);
        WebElement new_alert_effective_to = driver.findElement(effective_to_path);
        return new_alert_effective_to.getText();
    }

    public static void setAlertEffectiveTo(WebDriver driver, String alert_date_to) throws InterruptedException {
        Thread.sleep(500);
        By effective_to_path = By.xpath("//input[@name='effectiveTo']");
        waitForElementToBeEnabled(driver, effective_to_path, 10);
        WebElement new_alert_effective_to = driver.findElement(effective_to_path);
        scrollCenter(driver, new_alert_effective_to);
        Thread.sleep(500);
        new_alert_effective_to.sendKeys(alert_date_to);
    }

    public static List<String> getAlertReasonForUpdate(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_reason_for_update_input_path = By.xpath("//button[@aria-label='Reason for Update - Current Selection: --None--']");
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

    public static String getAlertReasonComments(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_update_comments_path = By.xpath("//input[@name='BCH_UpdateComments__c']");
        waitForElementToBeEnabled(driver, alert_update_comments_path, 10);
        WebElement alert_update_comments = driver.findElement(alert_update_comments_path);
        return alert_update_comments.getText();
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
