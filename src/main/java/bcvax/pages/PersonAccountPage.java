package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonAccountPage extends BasePage {
    public PersonAccountPage(WebDriver driver) {
        super(driver);
    }

    public static void goToRelatedTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By related_tab_path = By.xpath("//a[text() = 'Related'] | //span[text() = 'Related']");
        waitForElementToBeEnabled(driver, related_tab_path, 2);
        WebElement related_tab = driver.findElement(related_tab_path);
        //scrollCenter(driver, related_tab);
        //Thread.sleep(500);
        //waitForElementToBeEnabled(driver, related_tab_path, 5);
        try {
            related_tab.click();
        } catch(ElementNotInteractableException ex) {
            Thread.sleep(2000);
            related_tab.click();
        }
    }
    public static void goToVaccineScheduleTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By vaccine_schedule_tab_path = By.xpath("//a[@data-label='Vaccine Scheduling'] | //a[@title = 'Vaccine Scheduling']");
        waitForElementToBeEnabled(driver, vaccine_schedule_tab_path, 30);
        WebElement vaccine_schedule_tab = driver.findElement(vaccine_schedule_tab_path);
        waitForElementToBeVisible(driver, vaccine_schedule_tab, 10);
        try {
            vaccine_schedule_tab.click();
        } catch(ElementClickInterceptedException ex) {
            Thread.sleep(2000);
            vaccine_schedule_tab.click();
        }
        Thread.sleep(500);
        waitForAttribute(driver, vaccine_schedule_tab_path, "aria-selected", "true", 10);
    }

    public static void clickRefreshForecastButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By refresh_forecast_btn_path = By.xpath("//button[@title='Primary action' and text()='Refresh Forecast']");
        waitForElementToBeEnabled(driver, refresh_forecast_btn_path, 10);
        WebElement refresh_forecast_btn = driver.findElement(refresh_forecast_btn_path);
        refresh_forecast_btn.click();
        Thread.sleep(500);
    }

    public static void cancelProfileNotLinkedToPIRWarning(WebDriver driver) throws InterruptedException{
        By cancel_btn_path = By.xpath("//button[@c-bchcmodal_bchcmodal and text()='Cancel']");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, cancel_btn_path, 10);
        WebElement cancel_btn = driver.findElement(cancel_btn_path);
        cancel_btn.click();
        Thread.sleep(500);
    }

    public static void confirmNoForecastWarning(WebDriver driver) throws InterruptedException{
        By confirm_btn_path = By.xpath("//button[@c-bchcmodal_bchcmodal and text()='Confirm']");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, confirm_btn_path, 10);
        WebElement confirm_btn = driver.findElement(confirm_btn_path);
        confirm_btn.click();
    }

    public static void clickVerifyPHNButton(WebDriver driver) throws InterruptedException {
        By verify_phn_btn_path = By.xpath("//button[@title='Verify Personal Health Number']");
        waitForElementToBeEnabled(driver, verify_phn_btn_path, 10);
        WebElement verify_phn_btn = driver.findElement(verify_phn_btn_path);
        verify_phn_btn.click();
    }

    public static void successMessageAppear(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By message_path = By.xpath("//div[text() = 'Success'] | //h2[@c-bchcvacinnepreregistrationinternal_bchcvacinnepreregistrationinternal and text() = 'Match Unsuccessful']");
        log("  -- success message has been Appears. Closing... - /");
        try {
            waitForElementToBeEnabled(driver, message_path, 10);
            String message = driver.findElement(message_path).getText();
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("Probably alert already closed. Continue...");
        }
    }

    public static boolean checkInButtonAvailable(WebDriver driver) throws  InterruptedException {
        Thread.sleep(500);
        By checkin_btn_path = By.xpath("//button[@class = 'slds-button slds-button_brand' and @title = 'Check-in Client']");
        waitForElementToBeEnabled(driver, checkin_btn_path, 10);
        WebElement check_in_button = driver.findElement(checkin_btn_path);
        waitForElementToBeVisible(driver, check_in_button, 10);
        return check_in_button.isDisplayed();
    }

    public static void clickCheckInButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By checkin_btn_path = By.xpath("//button[@class = 'slds-button slds-button_brand' and @title = 'Check-in Client']");
        waitForElementToBeEnabled(driver, checkin_btn_path, 10);
        WebElement check_in_button = driver.findElement(checkin_btn_path);
        for(int i = 0; i < 10; i++) {
            try {
                check_in_button.click();
                break;
            } catch(ElementClickInterceptedException ex) {
                System.out.println("Element not available yet. Wait 1 sec and try again. Attempt # " + i);
                Thread.sleep(1000);
                if(i == 9) {
                    throw ex;
                }
            }
        }
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }
        By identification_tab_path = By.xpath("//h2[text()='Identification']");
        waitForElementToBeEnabled(driver, identification_tab_path, 30);
    }

    public static String getClientAlerts(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By client_alerts_path = By.xpath("//p[@title='Client Alerts']/..//lightning-formatted-rich-text/span");
        waitForElementToBeEnabled(driver, client_alerts_path, 10);
        WebElement client_alerts = driver.findElement(client_alerts_path);
        return client_alerts.getText();
    }

    public static List<String> getClientAlertImages(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By client_alerts_path = By.xpath("//p[@title='Client Alerts']/..//lightning-formatted-rich-text/span/img");
        waitForElementToBeEnabled(driver, client_alerts_path, 10);
        List<WebElement> client_alert_images = driver.findElements(client_alerts_path);
        List<String> images = new ArrayList<>();
        for(WebElement my_image: client_alert_images) {
            String my_src = my_image.getAttribute("src");
            String[] my_src_splitted = my_src.split("/");
            images.add(my_src_splitted[my_src_splitted.length -1]);
        }
        return images;
    }
}
