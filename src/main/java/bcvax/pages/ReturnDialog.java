package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReturnDialog extends BasePage {
    public ReturnDialog(WebDriver driver) {
        super(driver);
    }

    public String getReturnFromValue() throws InterruptedException {
        By return_from_path = By.xpath("//span[@class='test-id__field-label' and text()='Returned From']/../..//span[@class='uiOutputText forceOutputLookup']");
        waitForElementToBeEnabled(driver, return_from_path, 5);
        WebElement return_from_element = driver.findElement(return_from_path);
        return return_from_element.getText();
    };

    public void selectReturnTo(String return_to_location) throws InterruptedException {
        Thread.sleep(500);
        By return_to_location_field_path = By.xpath("//input[@title='Search Supply Locations']");
        waitForElementToBeEnabled(driver, return_to_location_field_path, 10);
        WebElement return_to_location_field = driver.findElement(return_to_location_field_path);
        return_to_location_field.sendKeys(return_to_location);
        Thread.sleep(500);
        By location_item_path = By.xpath("//div[@title='" + return_to_location + "']/../../../a[@role='option']");
        waitForElementToBeEnabled(driver, location_item_path, 10);
        WebElement my_item = driver.findElement(location_item_path);
        my_item.click();
    }

    public void typeReturnComments(String comment) throws InterruptedException {
        Thread.sleep(500);
        By comment_field_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Return__c.HC_Sender_Comment__c']//textarea");
        waitForElementToBeEnabled(driver, comment_field_path, 10);
        WebElement comment_field = driver.findElement(comment_field_path);
        comment_field.sendKeys(comment);
    }

    public void clickSaveBtn() throws InterruptedException {
        By save_btn_path = By.xpath("//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
        Thread.sleep(2000);
    }

    public String getReturnError() throws InterruptedException {
        Thread.sleep(500);
        By generic_error_path = By.xpath("//ul[@class='errorsList']");
        waitForElementToBeEnabled(driver, generic_error_path, 10);
        WebElement generic_error = driver.findElement(generic_error_path);
        return generic_error.getText();
    }
    public String getReturnWastageError() throws InterruptedException {
        Thread.sleep(500);
        By generic_error_path = By.xpath("//div[@class='slds-form-element__help']");
        waitForElementToBeEnabled(driver, generic_error_path, 10);
        WebElement generic_error = driver.findElement(generic_error_path);
        return generic_error.getText();
    }
}
