package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShipReturnDialog extends BasePage{
    Tables tables;
    public ShipReturnDialog(WebDriver driver) {
        super(driver);
        tables = new Tables(driver);
    }
    public static void clickSaveBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//h2[text() = 'Ship Return']/../../footer/button[@class = 'slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }

    public static void clickSaveBtnCP(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//h2[text() = 'Ship Return']/../../div[@class='modal-footer slds-modal__footer']/button[@class = 'slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }
    public void selectSupplyLocationFromDropdown(String supply_location_to) throws InterruptedException {
        Thread.sleep(500);
        By loc_field_path = By.xpath("//input[@class=' default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']");
        waitForElementToBeEnabled(driver, loc_field_path, 10);
        WebElement loc_field = driver.findElement(loc_field_path);
        loc_field.click();
        loc_field.sendKeys(supply_location_to);

        Thread.sleep(500);
        By location_path = By.xpath("//div[@title='"+supply_location_to+"']"); //click dropdown
        waitForElementToBeEnabled(driver, location_path, 10);
        WebElement location = driver.findElement(location_path);
        location.click();

    }

    public void removeReturnedTo() throws InterruptedException {
        Thread.sleep(500);
        By return_to_path = By.xpath("//a[@class='deleteAction']");
        waitForElementToBeEnabled(driver, return_to_path, 10);
        WebElement return_to = driver.findElement(return_to_path);
        return_to.click();

    }
}
