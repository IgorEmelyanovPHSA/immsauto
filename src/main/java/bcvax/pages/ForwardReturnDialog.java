package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForwardReturnDialog {
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
        By original_supply_location_path = By.xpath("//div[@class='runtime_platform_actionsQuickActionWrapper']//p[contains(text(), 'Original Supply Location:')]");
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
}
