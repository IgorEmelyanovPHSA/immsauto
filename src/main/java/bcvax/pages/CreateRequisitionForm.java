package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateRequisitionForm extends BasePage {
    public CreateRequisitionForm(WebDriver driver) {
        super(driver);
    }

    public static void selectShipped_From(WebDriver driver, String supply_location) throws InterruptedException {
        Thread.sleep(500);
        By search_supply_location_from_path = By.xpath("//label[text()='Shipped From']/..//input[@class='slds-combobox__input slds-input']");
        waitForElementToBeEnabled(driver, search_supply_location_from_path, 60);
        WebElement search_supply_location_from = driver.findElement(search_supply_location_from_path);
        search_supply_location_from.sendKeys(supply_location);
        Thread.sleep(500);
        By my_location_item_path = By.xpath("//lightning-base-combobox-formatted-text[contains(@title, '" + supply_location + "')]/../..");
        waitForElementToBeEnabled(driver, my_location_item_path, 20);
        WebElement my_location_item = driver.findElement(my_location_item_path);
        my_location_item.click();
    }

    public static void inputRequestDate(WebDriver driver) throws InterruptedException {
        By date_input_path = By.xpath("//input[@name='BCH_Requested_Delivery_Date__c']");
        waitForElementToBeEnabled(driver, date_input_path, 10);
        WebElement input_date = driver.findElement(date_input_path);
        scrollCenter(driver, input_date);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        String tomorrowAsString = dateFormat.format(tomorrow);
        input_date.sendKeys(tomorrowAsString, Keys.ENTER);
    }

    public static void clickNextButton(WebDriver driver) throws InterruptedException{
        Thread.sleep(500);
        By next_button_path = By.xpath("//c-hc-create-requisition//button[text()='Next']");
        waitForElementToBeEnabled(driver, next_button_path, 10);
        WebElement next_button = driver.findElement(next_button_path);
        scrollCenter(driver, next_button);
        next_button.click();
    }
}
