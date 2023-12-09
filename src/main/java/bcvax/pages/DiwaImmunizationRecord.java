package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
            try {
                my_clinic = dropdown_field.getText();
            } catch(StaleElementReferenceException ex) {
                Thread.sleep(2000);
                dropdown_field = driver.findElements(select_dropdown_option).get(0);
                my_clinic = dropdown_field.getText();
            }
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

    public static List<Map<String, WebElement>> getInformedConsentTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_table_path = By.xpath("//c-bchc-active-consent-table[@c-createimmunizationrecordmodal_createimmunizationrecordmodal]");
        waitForElementToBeEnabled(driver, informed_consent_table_path, 10);
        WebElement informed_consent_table_node = driver.findElement(informed_consent_table_path);
        GenericTable informed_consent_table = new GenericTable(informed_consent_table_node);
        List<Map<String, WebElement>> table = informed_consent_table.getRowsMappedToHeadings();
        int tries = 0;
        while(table.size() < 2) {
            Thread.sleep(500);
            table = informed_consent_table.getRowsMappedToHeadings();
            if(tries > 5) {
                break;
            }
            tries++;
        }
        return table;
    }

    public static boolean recordConsentMessageExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_consent_1_path = By.xpath("//lightning-formatted-text[text()='This Client has already provided Consent for the selected agent']");
        By record_consent_2_path = By.xpath("//lightning-formatted-text[text()='To record a new Consent, click the button below']");
        try {
            driver.findElement(record_consent_1_path);
            driver.findElement(record_consent_2_path);
            return true;
        } catch(NotFoundException ex) {
            return false;
        }
    }

    public static boolean confirm_and_save_button_is_active(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("//button[@c-createimmunizationrecordmodal_createimmunizationrecordmodal and @title='Confirm & Save Administration']");
        waitForElementToBeLocated(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        return confirm_and_save_btn.isEnabled();
    }
}
