package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForwardReturnDialog extends BasePage{
    Tables tables;
    public ForwardReturnDialog(WebDriver driver) {
        super(driver);
        tables = new Tables(driver);
    }
    public static String getReturnId(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_id_path = By.xpath("//div[@class='modal-container slds-modal__container']//p[contains(text(), 'Return ID:')]");
        BasePage.waitForElementToBeEnabled(driver, return_id_path, 10);
        WebElement return_id_node = driver.findElement(return_id_path);
        String return_id = return_id_node.getText().split(": ")[1];
        return return_id;
    }

    public static String getOriginalSupplyLocation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By original_supply_location_path = By.xpath("//p[contains(text(), 'Original Supply Location:')]");
        BasePage.waitForElementToBeEnabled(driver, original_supply_location_path, 10);
        WebElement original_supply_location = driver.findElement(original_supply_location_path);
        String supply_location = original_supply_location.getText().split(": ")[1];
        return supply_location;
    }

    public static String getReturnedTo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By returned_to_path = By.xpath("//lightning-base-combobox[@class='slds-combobox_container']//input[@class='slds-combobox__input slds-input']");
        BasePage.waitForElementToBeEnabled(driver, returned_to_path, 10);
        WebElement returned_to_field = driver.findElement(returned_to_path);
        String returned_to_value = returned_to_field.getAttribute("data-value");
        return returned_to_value;
    }
    public void removeReturnedTo() throws InterruptedException {
        Thread.sleep(500);
        By return_to_path = By.xpath("//a[@tabindex='deleteAction']");
        waitForElementToBeEnabled(driver, return_to_path, 10);
        WebElement return_to = driver.findElement(return_to_path);
        return_to.findElement(By.xpath(".//button")).click();

    }
    public static void clickSaveBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//button[contains(text(),'Save')]");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }

    public String getReturnError() throws InterruptedException {
        Thread.sleep(500);
        By generic_error_path = By.xpath("(//lightning-formatted-rich-text[@class='errorContent slds-text-color_error slds-text-body_small slds-m-bottom--x-small slds-rich-text-editor__output'])");
        waitForElementToBeEnabled(driver, generic_error_path, 10);
        WebElement generic_error = driver.findElement(generic_error_path);
        return generic_error.getText();
    }

    public String successContent() throws InterruptedException {
        Thread.sleep(500);
        By success_path = By.xpath("(//p[normalize-space()='You have successfully Forwarded the Return.'])");
        waitForElementToBeEnabled(driver, success_path, 10);
        WebElement success = driver.findElement(success_path);
        return success.getText();
    }

    public void selectSupplyLocationFromDropdown(String supply_location_to) throws InterruptedException {
        Thread.sleep(500);
        By loc_field_path = By.xpath("//input[@placeholder='Search Supply Locations...']");
        waitForElementToBeEnabled(driver, loc_field_path, 10);
        WebElement loc_field = driver.findElement(loc_field_path);
        loc_field.click();
        loc_field.sendKeys(supply_location_to);

        Thread.sleep(500);
        By location_path = By.xpath("//ul[@role='group']"); //click dropdown
        waitForElementToBeEnabled(driver, location_path, 10);
        WebElement location = driver.findElement(location_path);
        location.click();

    }
}
