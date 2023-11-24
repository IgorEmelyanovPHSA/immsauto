package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiwaImmunizationRecord extends BasePage {
    public DiwaImmunizationRecord(WebDriver driver) {
        super(driver);
    }
    public static void clickSelectAnOptionDropdown(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By agent_option_path = By.xpath("//option[contains(text(),'Select an option')]");
        waitForElementToBeEnabled(driver, agent_option_path, 10);
        WebElement agent_option = driver.findElement(agent_option_path);
        agent_option.click();
    }

    public static void selectOption(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By agent_option_path = By.xpath("//option[contains(text(),'COVID-19 mRNA')]");
        waitForElementToBeEnabled(driver, agent_option_path, 10);
        WebElement covidmRna = driver.findElement(agent_option_path);
        covidmRna.click();
    }

    public static void searchClinicLocation(WebDriver driver, String clinic) throws InterruptedException {
        Thread.sleep(500);
        By clinic_location_path = By.xpath("//input[@data-id = 'userinput']");
        waitForElementToBeEnabled(driver, clinic_location_path, 10);
        WebElement search_clinic = driver.findElement(clinic_location_path);
        search_clinic.sendKeys(clinic);
        Thread.sleep(500);
        By select_dropdown_option = By.xpath(".//div[@class = 'slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta']");
        waitForElementToBeEnabled(driver, select_dropdown_option, 10);
        WebElement dropdown_field = driver.findElements(select_dropdown_option).get(0);
        String my_clinic = dropdown_field.getText();
        for(int i = 0; i < 10; i++) {
            Thread.sleep(500);
            dropdown_field = driver.findElements(select_dropdown_option).get(0);
            my_clinic = dropdown_field.getText();
            if(my_clinic.equals(clinic)) {
                break;
            }
        }
        scrollIfNeeded(driver, dropdown_field);
        Thread.sleep(500);
        dropdown_field.click();
        Thread.sleep(1000);
    }

    public static void clickTimeBox(WebDriver driver) throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        By input_date_path = By.xpath("//lightning-input[@class='dateCmp slds-form-element']/lightning-datepicker");
        waitForElementToBeEnabled(driver, input_date_path, 10);
        String todayAsString = dateFormat.format(today);
        WebElement inputDate = driver.findElement(input_date_path);
        waitForElementToBeVisible(driver, inputDate, 10);
        inputDate.click();
        Thread.sleep(2000);
        inputDate.sendKeys(todayAsString);
        Thread.sleep(2000);
        inputDate.sendKeys(Keys.ENTER);
    }

    public static void clickRecordImmunization(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_immunization_btn_path = By.xpath("//button[contains(text(),'Record Immunization')]");
        waitForElementToBeEnabled(driver, record_immunization_btn_path, 10);
        WebElement recordImmunizationBtn = driver.findElement(record_immunization_btn_path);
        recordImmunizationBtn.click();
    }

    public static void clickRecordConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_consent_btn_path = By.xpath("//button[@title='Primary action' and text()='Record Consent']");
        waitForElementToBeEnabled(driver, record_consent_btn_path, 10);
        WebElement record_consent_btn = driver.findElement(record_consent_btn_path);
        record_consent_btn.click();
    }
}
