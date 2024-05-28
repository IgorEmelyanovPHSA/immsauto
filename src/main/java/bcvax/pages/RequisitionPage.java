package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RequisitionPage extends BasePage {
    public RequisitionPage(WebDriver driver) {
        super(driver);
    }

    public static void clickSubmitRequisition(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By submit_requisition_btn_path = By.xpath("//button[text() = 'Submit Requisition'] | //a[@title = 'Submit Requisition']");
        waitForElementToBeEnabled(driver, submit_requisition_btn_path, 10);
        WebElement submit_requisition_btn = driver.findElement(submit_requisition_btn_path);
        scrollCenter(driver, submit_requisition_btn);
        Thread.sleep(500);
        submit_requisition_btn.click();
    }

    public static void clickEditExpectedDeliveryDate(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);
        By edit_expected_delivery_date_btn_path = By.xpath("//button[@title='Edit Expected Delivery Date']");
        waitForElementToBeEnabled(driver, edit_expected_delivery_date_btn_path, 10);
        WebElement edit_expected_delivery_date_btn = driver.findElement(edit_expected_delivery_date_btn_path);
        edit_expected_delivery_date_btn.click();
    }

    public static void inputExpectedDate(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String tomorrowAsString = dateFormat.format(tomorrow);
        By expected_delivery_date_field_path = By.xpath("//*[text()='Expected Delivery Date']/../..//input");
        waitForElementToBeEnabled(driver, expected_delivery_date_field_path, 10);
        WebElement expected_delivery_date_field = driver.findElement(expected_delivery_date_field_path);
        expected_delivery_date_field.sendKeys(tomorrowAsString);
        Thread.sleep(500);
        expected_delivery_date_field.sendKeys(Keys.ENTER);
    }

    public static void clickSaveExpectedDeliveryDate(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_expected_delivery_date_btn_path = By.xpath("//button[text() = 'Save'] | //button[@title = 'Save']");
        waitForElementToBeEnabled(driver, save_expected_delivery_date_btn_path, 10);
        WebElement save_expected_delivery_date_btn = driver.findElement(save_expected_delivery_date_btn_path);
        scrollCenter(driver, save_expected_delivery_date_btn);
        save_expected_delivery_date_btn.click();
    }

    public static void clickApproveRequisition(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By approve_requisition_btn_path = By.xpath("//button[text() = 'Approve Requisition'] | //a[@title = 'Approve Requisition']");
        waitForElementToBeEnabled(driver, approve_requisition_btn_path, 10);
        WebElement approve_requisition_btn = driver.findElement(approve_requisition_btn_path);
        scrollCenter(driver, approve_requisition_btn);
        approve_requisition_btn.click();
    }

    public static void clickShipRequisition(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By ship_requisition_button_path = By.xpath("//button[text() = 'Ship Requisition'] | //a[@title = 'Ship Requisition']");
        waitForElementToBeEnabled(driver, ship_requisition_button_path, 10);
        WebElement ship_requisition_button = driver.findElement(ship_requisition_button_path);
        scrollCenter(driver, ship_requisition_button);
        ship_requisition_button.click();
    }

    public static void clickReceiveRequisitionButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By receive_request_btn_path = By.xpath("//button[text() = 'Receive Requisition'] | //a[@title = 'Receive Requisition']");
        waitForElementToBeEnabled(driver, receive_request_btn_path, 10);
        WebElement receive_request_btn = driver.findElement(receive_request_btn_path);
        scrollCenter(driver, receive_request_btn);
        Thread.sleep(500);
        try {
            receive_request_btn.click();
        } catch(StaleElementReferenceException ex) {
            Thread.sleep(2000);
            waitForElementToBeEnabled(driver, receive_request_btn_path, 10);
            receive_request_btn = driver.findElement(receive_request_btn_path);
            scrollCenter(driver, receive_request_btn);
            Thread.sleep(500);
            receive_request_btn.click();
        }
    }
}
