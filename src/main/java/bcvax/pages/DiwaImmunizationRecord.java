package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiwaImmunizationRecord extends BasePage {
    public DiwaImmunizationRecord(WebDriver driver) {
        super(driver);
    }
    public static void clickSelectAnOptionDropdown(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By agent_option_path = By.xpath("//select[@c-createimmunizationrecordmodal_createimmunizationrecordmodal and @data-id='agent']");
        waitForElementToBeEnabled(driver, agent_option_path, 10);
        WebElement agent_option = driver.findElement(agent_option_path);
        agent_option.click();
        Thread.sleep(500);
    }

    public static void selectOption(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By select_agent_path = By.xpath("//select[@c-createimmunizationrecordmodal_createimmunizationrecordmodal and @data-id='agent']");
        waitForElementToBeEnabled(driver, select_agent_path, 10);
        WebElement select_agent = driver.findElement(select_agent_path);
        Select my_select_option = new Select(select_agent);
        List<WebElement> my_options = my_select_option.getOptions();
        for(WebElement my_option: my_options) {
            if(my_option.getAttribute("label").equals(vaccine)) {
                my_option.click();
                break;
            }
        }
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
        List<WebElement> dropdown_list = driver.findElements(select_dropdown_option);
        WebElement my_option = dropdown_list.get(0);
        String my_clinic = my_option.getText();
        for(int i = 0; i < 10; i++) {
            Thread.sleep(500);
            my_option = driver.findElements(select_dropdown_option).get(0);
            try {
                my_clinic = my_option.getText();
            } catch(StaleElementReferenceException ex) {
                Thread.sleep(2000);
                my_option = driver.findElements(select_dropdown_option).get(0);
                my_clinic = my_option.getText();
            }
            if(my_clinic.equals(clinic)) {
                break;
            }
        }
        scrollCenter(driver, my_option);
        Thread.sleep(500);
        my_option.click();
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

    public static boolean recordConsentBtnExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_consent_btn_path = By.xpath("//button[@title='Primary action' and text()='Record Consent']");
        try {
            waitForElementToBeEnabled(driver, record_consent_btn_path, 10);
            driver.findElement(record_consent_btn_path);
            return true;
        } catch(NotFoundException ex) {
            return false;
        }
    }

    public static boolean recordConsentBtnIsActive(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_consent_btn_path = By.xpath("//button[@title='Primary action' and text()='Record Consent']");
        waitForElementToBeEnabled(driver, record_consent_btn_path, 10);
        WebElement record_consent_btn = driver.findElement(record_consent_btn_path);
        return record_consent_btn.isEnabled();
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

    public static boolean confirmAndSaveButtonIsActive(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("//button[@c-createimmunizationrecordmodal_createimmunizationrecordmodal and @title='Confirm & Save Administration']");
        waitForElementToBeLocated(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        return confirm_and_save_btn.isEnabled();
    }

    public static void clickConfirmAndSaveAdministration(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("//button[@c-createimmunizationrecordmodal_createimmunizationrecordmodal and @title='Confirm & Save Administration']");
        waitForElementToBeLocated(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        confirm_and_save_btn.click();
    }

    public static void selectProvider(WebDriver driver, String provider) throws InterruptedException {
        By provider_input_field_path = By.xpath("//label[@c-bchcimmunizationinfo_bchcimmunizationinfo and text()='Provider']/..//input");
        waitForElementToBeEnabled(driver, provider_input_field_path, 10);
        WebElement provider_input_field = driver.findElement(provider_input_field_path);
        provider_input_field.sendKeys(provider);
        By my_provider_item_path = By.xpath("//lightning-base-combobox-formatted-text[@title='" + provider + "']");
        waitForElementToBeEnabled(driver, my_provider_item_path, 10);
        WebElement my_provider_item = driver.findElement(my_provider_item_path);
        my_provider_item.click();
    }

    public static void clickSaveImmunizationInfo(WebDriver driver) throws InterruptedException {
        By save_btn_path = By.xpath("//c-bc-hc-immunization-info//button[text()='Save']");
        waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        scrollCenter(driver, save_btn);
        Thread.sleep(500);
        save_btn.click();
    }

    public static void clickOkForExpiredLot(WebDriver driver) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Check if the expired lot message appears. If yes click OK");
        try {
            WebElement modalBox = driver.findElement(By.xpath("//div[@class = 'slds-modal__container']"));
            modalBox.findElement(By.xpath("//button[text() = 'OK']")).click();
            Thread.sleep(2000);
        }
        catch(Exception ex) {
            System.out.println("No expired lots");
        }
    }

    public static void clickSaveAdministrationSummary(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']//button[text()='Confirm and Save']");
        waitForElementToBeEnabled(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        confirm_and_save_btn.click();
    }

    public static void clickCancelAndCloseImmunization(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By cancel_btn_path = By.xpath("//div[@c-createimmunizationrecordmodal_createimmunizationrecordmodal and @class='slds-modal__container']//button[@title='Cancel and Close']");
        waitForElementToBeEnabled(driver, cancel_btn_path, 10);
        WebElement cancel_btn = driver.findElement(cancel_btn_path);
        scrollCenter(driver, cancel_btn);
        Thread.sleep(500);
        try {
            cancel_btn.click();
        } catch(ElementClickInterceptedException ex) {
            Thread.sleep(2000);
            cancel_btn.click();
        }
    }

    public static void clickOopsCancelAndClose(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By cancel_and_close_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']//button[text()='Cancel and close']");
        waitForElementToBeEnabled(driver, cancel_and_close_btn_path, 10);
        WebElement cancel_and_close_btn = driver.findElement(cancel_and_close_btn_path);
        cancel_and_close_btn.click();
    }

    public static void checkExistingConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By existing_consent_checkbox_path = By.xpath("//span[@part='label' and text()='Consent Previously Obtained (per BCCDC Standard)']/../../../..");
        waitForElementToBeEnabled(driver, existing_consent_checkbox_path, 10);
        WebElement existing_consent_checkbox = driver.findElement(existing_consent_checkbox_path);
        scrollCenter(driver, existing_consent_checkbox);
        Thread.sleep(500);
        if(existing_consent_checkbox.getAttribute("checked") == null) {
            WebElement chkbox = existing_consent_checkbox.findElement(By.xpath(".//span[@part='indicator']"));
            chkbox.click();
        }
    }

    public static void clickEditImmunizationInformation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By edit_immunization_info_btn_path = By.xpath("//c-bc-hc-immunization-info//button[@title='Edit']");
        waitForElementToBeEnabled(driver, edit_immunization_info_btn_path, 10);
        WebElement edit_immunization_info_btn = driver.findElement(edit_immunization_info_btn_path);
        scrollIfNeeded(driver, edit_immunization_info_btn);
        Thread.sleep(500);
        edit_immunization_info_btn.click();
    }
}
