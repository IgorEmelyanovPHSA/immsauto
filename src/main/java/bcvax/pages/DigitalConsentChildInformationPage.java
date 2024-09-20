package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class DigitalConsentChildInformationPage extends BasePage {
    public DigitalConsentChildInformationPage(WebDriver driver) {
        super(driver);
    }

    public static boolean consentForPersonToBeImmunizedPageLoaded(WebDriver driver) throws InterruptedException {
        By site_title_path = By.xpath("//div[@class='siteTitle']");
        By person_immunized_path = By.xpath("//c-bc-hc-digital-consent-person-tobe-immunized//div[@class='header-line ']");
        waitForElementToBeEnabled(driver, site_title_path, 10);
        waitForElementToBeEnabled(driver, person_immunized_path, 10);
        WebElement site_title = driver.findElement(site_title_path);
        WebElement person_immunized = driver.findElement(person_immunized_path);
        if(site_title.getText().equals("Consent for Immunization") && person_immunized.getText().equals("Person to be immunized")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean consentConfirmContactPageLoaded(WebDriver driver) throws InterruptedException {
        By site_title_path = By.xpath("//div[@class='siteTitle']");
        By person_immunized_path = By.xpath("//c-bc-h-c-digital-consent-contact-information//div[@class='main-heading-step3']");
        waitForElementToBeEnabled(driver, site_title_path, 10);
        waitForElementToBeEnabled(driver, person_immunized_path, 10);
        WebElement site_title = driver.findElement(site_title_path);
        WebElement person_immunized = driver.findElement(person_immunized_path);
        if(site_title.getText().equals("Consent for Immunization") && person_immunized.getText().equals("Confirm contact information")) {
            return true;
        } else {
            return false;
        }
    }

    public static void enterFirstName(WebDriver driver, String first_name) throws InterruptedException {
        By first_name_field_path = By.xpath("//input[@name='FirstName']");
        waitForElementToBeEnabled(driver, first_name_field_path, 10);
        WebElement first_name_field = driver.findElement(first_name_field_path);
        scrollCenter(driver, first_name_field);
        first_name_field.sendKeys(first_name);
    }

    public static String getFirstNameError(WebDriver driver) throws InterruptedException {
        By first_name_error_path = By.xpath("//lightning-input[@data-name='FirstName']//div[@role='status']");
        waitForElementToBeEnabled(driver, first_name_error_path, 10);
        WebElement first_name_error = driver.findElement(first_name_error_path);
        return first_name_error.getText();
    }

    public static void enterLastName(WebDriver driver, String last_name) throws InterruptedException {
        By last_name_field_path = By.xpath("//input[@name='LastName']");
        WebElement last_name_field = driver.findElement(last_name_field_path);
        waitForElementToBeEnabled(driver, last_name_field_path, 10);
        scrollCenter(driver, last_name_field);
        last_name_field.sendKeys(last_name);
    }

    public static String getLastNameError(WebDriver driver) throws InterruptedException {
        By last_name_error_path = By.xpath("//lightning-input[@data-name='LastName']//div[@role='status']");
        waitForElementToBeEnabled(driver, last_name_error_path, 10);
        WebElement last_name_error = driver.findElement(last_name_error_path);
        return last_name_error.getText();
    }

    public static void enterPHN(WebDriver driver, String phn) throws InterruptedException {
        By phn_field_path = By.xpath("//input[@name='HC_Personal_Health_Number']");
        WebElement phn_field = driver.findElement(phn_field_path);
        waitForElementToBeEnabled(driver, phn_field_path, 10);
        phn_field.clear();
        scrollCenter(driver, phn_field);
        phn_field.sendKeys(phn);
    }

    public static String getPhnError(WebDriver driver) throws InterruptedException {
        By phn_error_path = By.xpath("//input[@name='PersonBirthdate']/../..//div[@role='status']");
        waitForElementToBeEnabled(driver, phn_error_path, 10);
        WebElement phn_error = driver.findElement(phn_error_path);
        return phn_error.getText();
    }

    public static void enterDOB(WebDriver driver, String dob) throws InterruptedException, ParseException {
        By dob_field_path = By.xpath("//input[@name='PersonBirthdate']");
        waitForElementToBeEnabled(driver, dob_field_path, 10);
        WebElement dob_field = driver.findElement(dob_field_path);
        scrollCenter(driver, dob_field);
        dob_field.sendKeys(Utils.convertDate(dob,"MMM dd, yyyy"));
    }

    public static String getDOBError(WebDriver driver) throws InterruptedException {
        By dob_error_path = By.xpath("//lightning-input[@data-name='PersonBirthdate']//div[@data-error-message]");
        waitForElementToBeEnabled(driver, dob_error_path, 10);
        WebElement dob_error = driver.findElement(dob_error_path);
        return dob_error.getText();
    }

    public static void clickContinue(WebDriver driver) throws InterruptedException {
        By continue_btn_path = By.xpath("//button[text()='Continue']");
        waitForElementToBeEnabled(driver, continue_btn_path, 10);
        WebElement continue_btn = driver.findElement(continue_btn_path);
        scrollCenter(driver, continue_btn);
        continue_btn.click();
    }
}
