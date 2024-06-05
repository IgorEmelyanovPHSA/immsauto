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
        By agent_option_path = By.xpath("//select[@data-id='agent']");
        waitForElementToBeEnabled(driver, agent_option_path, 10);
        WebElement agent_option = driver.findElement(agent_option_path);
        agent_option.click();
        Thread.sleep(500);
    }

    public static void selectOption(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By select_agent_path = By.xpath("//select[@data-id='agent']");
        waitForElementToBeEnabled(driver, select_agent_path, 10);
        WebElement select_agent = driver.findElement(select_agent_path);
        Select my_select_option = new Select(select_agent);
        List<WebElement> my_options = my_select_option.getOptions();
        for(WebElement my_option: my_options) {
            if(my_option.getAttribute("label").equals(vaccine)) {
                select_agent.click();
                my_option.click();
                Thread.sleep(500);
                select_agent.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    public static void searchClinicLocation(WebDriver driver, String clinic) throws InterruptedException {
        Thread.sleep(500);
        By clinic_location_path = By.xpath("//input[@data-id = 'userinput']");
        waitForElementToBeEnabled(driver, clinic_location_path, 10);
        WebElement search_clinic = driver.findElement(clinic_location_path);
        By clear_button_path = By.xpath("//input[@data-id = 'userinput']/..//button");
        WebElement clear_button = driver.findElement(clear_button_path);
        clear_button.click();
        Thread.sleep(500);
        search_clinic.sendKeys(clinic);
        Thread.sleep(500);
        By select_dropdown_option = By.xpath("//div[@c-bchcgenericcustomlookup_bchcgenericcustomlookup and @role='listbox']");
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
        Thread.sleep(500);
        inputDate.findElement(By.xpath(".//input")).clear();
        Thread.sleep(500);
        inputDate.sendKeys(todayAsString);
        Thread.sleep(500);
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
        waitForElementToBeLocated(driver, record_consent_btn_path, 10);
        WebElement record_consent_btn = driver.findElement(record_consent_btn_path);
        return record_consent_btn.isEnabled();
    }

    public static List<Map<String, WebElement>> getInformedConsentTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_table_path = By.xpath("//section[@role='dialog']//c-bchc-active-consent-table");
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

    public static boolean recordExistingConsentMessageExists(WebDriver driver) throws InterruptedException {
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

    public static boolean recordNewConsentMessageExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_consent_1_path = By.xpath("//lightning-formatted-text[text()='This Client does not have active Consent for the selected Agent']");
        By record_consent_2_path = By.xpath("//lightning-formatted-text[text()='Select the appropriate response below']");
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
        By confirm_and_save_btn_path = By.xpath("//section[@role='dialog']//button[@title='Confirm & Save Administration']");
        waitForElementToBeLocated(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        return confirm_and_save_btn.isEnabled();
    }

    public static void clickConfirmAndSaveAdministration(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("//section[@role='dialog']//button[@title='Confirm & Save Administration']");
        waitForElementToBeLocated(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        confirm_and_save_btn.click();
    }

    public static void setProvider(WebDriver driver, String provider) throws InterruptedException {
        Thread.sleep(500);
        By providerFieldPath = By.xpath("//label[text() = 'Provider' and @c-bchcimmunizationinfo_bchcimmunizationinfo]/..//input");
        By providerClearFieldPath = By.xpath("//label[text() = 'Provider' and @c-bchcimmunizationinfo_bchcimmunizationinfo]/..//button[@title='Clear Selection']");
        waitForElementToBeEnabled(driver, providerFieldPath, 10);
        WebElement providerField =  driver.findElement(providerFieldPath);
        scrollCenter(driver, providerField);
        Thread.sleep(500);
        try {
            WebElement provider_field_clear_button = driver.findElement(providerClearFieldPath);
            provider_field_clear_button.click();
        } catch(NotFoundException ex) {
            System.out.println("Continue...");
        }

        Thread.sleep(500);
        providerField.sendKeys(provider);
        By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
        waitForElementToBeLocated(driver, providerItemPath, 10);
        WebElement provider_item = driver.findElement(providerItemPath);
        provider_item.click();
    }

    public static void setRoute(WebDriver driver, String route) throws InterruptedException {
        driver.findElement(By.xpath("//label[text() = 'Route']/..//button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@title = '" + route + "'] ")).click();
    }

    public static void setSite(WebDriver driver, String site) throws InterruptedException {
        WebElement siteBtn = driver.findElement(By.xpath("//label[text() = 'Site']/..//button"));
        scrollCenter(driver, siteBtn);
        siteBtn.click();
        Thread.sleep(2000);
        WebElement mySite = driver.findElement(By.xpath("//span[@title = '" + site + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", mySite );
        mySite.click();
    }

    public static void setLotNumber(WebDriver driver, String lot) throws InterruptedException {
        By lot_item_path = By.xpath("//li[contains(@title, '" + lot + "')]");
        By lot_field_path = By.xpath("//span[text() = 'Lot Number']/..//input");
        WebElement lotSearchInputField = driver.findElement(By.xpath("//span[text() = 'Lot Number']/..//input[@class = 'slds-input search-input-class']"));
        if(!lotSearchInputField.isDisplayed()) {
            waitForElementToBeEnabled(driver, lot_field_path, 10);
            WebElement lot_field = driver.findElement(lot_field_path);
            lot_field.click();
        }
        Thread.sleep(1000);
        lotSearchInputField.sendKeys(lot);
        Thread.sleep(1000);
        waitForElementToBeEnabled(driver, lot_item_path, 10);
        WebElement lot_item = driver.findElement(lot_item_path);
        lot_item.click();
    }

    public static void setDosage(WebDriver driver, String dose) throws InterruptedException {
        By dosage_field_path = By.xpath("//label[text() = 'Dosage']/..//button");
        waitForElementToBeEnabled(driver, dosage_field_path, 10);
        WebElement dosage_input_field = driver.findElement(dosage_field_path);
        dosage_input_field.click();
        Thread.sleep(500);
        By my_dosage_path = By.xpath("//span[@title = '" + dose + "']");
        waitForElementToBeEnabled(driver, my_dosage_path, 10);
        WebElement my_dosage = driver.findElement(my_dosage_path);
        my_dosage.click();
    }

    public static void clickShowAllLotNumbersCheckBox(WebDriver driver) throws InterruptedException {
        By show_all_chkbox_path = By.xpath("//span[@part='label' and text()='Show all lot numbers.']/../..");
        waitForElementToBeEnabled(driver, show_all_chkbox_path, 10);
        WebElement show_all_lot_numbers_checkbox = driver.findElement(show_all_chkbox_path);
        scrollIfNeeded(driver, show_all_lot_numbers_checkbox);
        Thread.sleep(1000);
        show_all_lot_numbers_checkbox.click();
    }

    public static void clickSaveImmunizationInfo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//c-bc-hc-immunization-info//button[text()='Save']/..");
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
        By cancel_btn_path = By.xpath("//section[@role='dialog']//button[@title='Cancel and Close']");
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

    public static void clickOopsContinueEditingAndSave(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By continue_editing_and_save_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']//button[text()='Continue Editing and Save']");
        waitForElementToBeEnabled(driver, continue_editing_and_save_btn_path, 10);
        WebElement continue_editing_and_save_btn = driver.findElement(continue_editing_and_save_btn_path);
        continue_editing_and_save_btn.click();
    }
    public static void checkExistingConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By existing_consent_checkbox_path = By.xpath("//span[@part='label' and text()='Consent Previously Obtained (per BCCDC Standard)']/../../../../..");
        waitForElementToBeEnabled(driver, existing_consent_checkbox_path, 10);
        WebElement existing_consent_checkbox = driver.findElement(existing_consent_checkbox_path);
        scrollCenter(driver, existing_consent_checkbox);
        Thread.sleep(500);
        if(existing_consent_checkbox.getAttribute("checked") == null) {
            WebElement chkbox = existing_consent_checkbox.findElement(By.xpath(".//span[@part='indicator']"));
            chkbox.click();
        }
    }

    public static void uncheckExistingConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By existing_consent_checkbox_path = By.xpath("//span[@part='label' and text()='Consent Previously Obtained (per BCCDC Standard)']/../../../../..");
        waitForElementToBeEnabled(driver, existing_consent_checkbox_path, 10);
        WebElement existing_consent_checkbox = driver.findElement(existing_consent_checkbox_path);
        scrollCenter(driver, existing_consent_checkbox);
        Thread.sleep(500);
        if(existing_consent_checkbox.getAttribute("checked") != null) {
            WebElement chkbox = existing_consent_checkbox.findElement(By.xpath(".//span[@part='indicator']"));
            chkbox.click();
        }
    }

    public static boolean usePrviousConsentChkboxExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By previous_consent_chkbox_path = By.xpath("//span[@part='label' and text()='Consent Previously Obtained (per BCCDC Standard)']");
        try {
            waitForElementToBeEnabled(driver, previous_consent_chkbox_path, 10);
            driver.findElement(previous_consent_chkbox_path);
            return true;
        } catch(NotFoundException ex) {
            return false;
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

    public static boolean getEditImmunizationInfoButtonDisabled(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By edit_immunization_info_btn_path = By.xpath("//c-bc-hc-immunization-info//button[@title='Edit']");
        waitForElementToBeEnabled(driver, edit_immunization_info_btn_path, 10);
        WebElement edit_immunization_info_btn = driver.findElement(edit_immunization_info_btn_path);
        return Boolean.parseBoolean(edit_immunization_info_btn.getAttribute("aria-disabled"));
    }

    public static void clickPotentialDuplicateYes(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By yes_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']/footer//button[text()='Yes']");
        waitForElementToBeEnabled(driver, yes_btn_path, 20);
        WebElement yes_btn = driver.findElement(yes_btn_path);
        yes_btn.click();
    }
}
