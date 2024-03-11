package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Locale;
import java.util.stream.IntStream;

public class ProfilesPage extends BasePage{
    /*---------Properties-------*/
    private By getValueOfHistoricalDoses1 = By.xpath("(//BUTTON[@class='slds-button slds-button_neutral'][text()='New'])[1]/../../../../../..//SPAN[@class='slds-page-header__title slds-truncate'][contains(text(),'Historical Immunization Records')]");

    private By create_Historical_Immunization_Record1 = By.xpath("//SPAN[@class='slds-page-header__title slds-truncate'][contains(text(),'Historical Immunization Records ')]/../../../../..//BUTTON[@class='slds-button slds-button_neutral'][text()='New']");

    //@FindBy(xpath = "(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[1]")
    @FindBy(xpath = "//option[@label='COVID-19 mRNA']")
    private WebElement select_agent;
    private By select_agent1 = By.xpath("//option[@label='COVID-19 mRNA']");

    private By yes_button_save_on_popup_window1 = By.xpath("//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]");

    @FindBy(xpath = "//*[@class='slds-icon slds-icon_large']")
    private WebElement close_button_diwa;

    @FindBy(xpath = ".//h1[text() = 'Oops...']")
    private WebElement vlidate_oops_message;
    private By click_agent_value1 = By.xpath("//select[@data-id='agent']");

    @FindBy(xpath = "(//button[normalize-space()='Continue Editing and Save'])")
    private WebElement continue_editing_btn;

    @FindBy(xpath = ".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']")
    private WebElement click_lot_number_dropdown;

    @FindBy(xpath = "//li[@title='300042698 - Exp. 2021 June 18']")
    private WebElement select_lot;

    private By selectSite1 = By.xpath("//button[@name='injectionSite']");

    private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");

    private By save_button_historical_dose1 = By.xpath("//button[contains(text(),'Save')]");

    private By select_dosage_field1 = By.xpath("//button[@name='dosePicklist']");

    private By select_dosage1 = By.xpath("//span[@title='0.5']");

    @FindBy(xpath = "//button[@title='Confirm & Save Administration']")
    private WebElement confirmAndSave;
    private By confirmAndSave1 = By.xpath("//button[@title='Confirm & Save Administration']");

    Tables tables;
    /*---------Constructor-------*/
    public ProfilesPage(WebDriver driver) {
        super(driver);
        tables = new Tables(driver);
    }


    /*-------------Methods--------------*/
    public void selectCitizenParticipantAcc(String name) throws InterruptedException {
        By citizen_path = By.xpath("//a[contains(text(),\""+ name + "\")]");
        waitForElementToBeEnabled(driver, citizen_path, 30);
        WebElement citizen_participant_acc = driver.findElement(citizen_path);
        citizen_participant_acc.click();
    }

    public void clickPotentialDuplicateYes() throws InterruptedException {
        Thread.sleep(500);
        By cancel_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']/footer//button[text()='Yes']");
        waitForElementToBeEnabled(driver, cancel_btn_path, 20);
        WebElement cnncel_btn = driver.findElement(cancel_btn_path);
        cnncel_btn.click();
    }

    public void clickCreateImmunizationRecord() throws InterruptedException {
        Thread.sleep(500);
        By create_immunization_record_btn_path = By.xpath("//button[contains(text(),'Create Immunization Record')]");
        waitForElementToBeEnabled(driver, create_immunization_record_btn_path, 10);
        WebElement create_immunization_record_btn = driver.findElement(create_immunization_record_btn_path);
        scrollCenter(create_immunization_record_btn);
        Thread.sleep(500);
        create_immunization_record_btn.click();
    }

    public boolean clickPopupYesButtonIfDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        if (!isDisplayed(yes_button_save_on_popup_window1)) {
            return false;
        }
        waitForElementToBeLocated(driver, yes_button_save_on_popup_window1, 10);
        WebElement element = driver.findElement(yes_button_save_on_popup_window1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
        return true;
    }

    public void clickToClose() throws InterruptedException {
        Thread.sleep(500);
        waitForElementToBeVisible(driver, close_button_diwa, 10);
        close_button_diwa.click();
    }

    public boolean validateoopsMessage() throws InterruptedException {
        try {
            waitForElementToBeVisible(driver, vlidate_oops_message, 10);
            System.out.println("/*---'There are unsaved changes!' message shown up");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("/*---the screen does not show up 'There are unsaved changes Message!'");
            return false;
        }
    }

    public void ContinueEditingButton() throws InterruptedException {
        waitForElementToBeVisible(driver, continue_editing_btn, 10);
        continue_editing_btn.click();
    }

    public static void checkExistingConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By existing_consent_checkbox_path = By.xpath("//span[@part='label' and text()='Consent Previously Obtained (per BCCDC Standard)']/../../../..");
        waitForElementToBeEnabled(driver, existing_consent_checkbox_path, 10);
        WebElement existing_consent_checkbox = driver.findElement(existing_consent_checkbox_path);
        scrollIfNeeded(driver, existing_consent_checkbox);
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

    public void selectImmunizingAgentProvider(String provider) throws InterruptedException {
        Thread.sleep(1000);
        By providerFieldPath = By.xpath("//label[text() = 'Provider' and @c-bchcimmunizationinfo_bchcimmunizationinfo]/..//input");
        waitForElementToBePresent(driver, providerFieldPath, 30);
        WebElement providerField =  driver.findElement(providerFieldPath);
        scrollCenter(providerField);
        providerField.sendKeys(provider);
        Thread.sleep(1000);
        By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
        waitForElementToBeLocated(driver, providerItemPath, 10);
        WebElement providerItem = driver.findElement(providerItemPath);
        scrollCenter(providerItem);
        Thread.sleep(500);
        providerItem.click();

    }

    public void clickShowAllLotNumbersCheckBox() throws InterruptedException {
        By show_all_chkbox_path = By.xpath("//span[@part='label' and text()='Show all lot numbers.']/../..");
        waitForElementToBeEnabled(driver, show_all_chkbox_path, 10);
        WebElement show_all_lot_numbers_checkbox = driver.findElement(show_all_chkbox_path);
        scrollIfNeeded(driver, show_all_lot_numbers_checkbox);
        Thread.sleep(1000);
        show_all_lot_numbers_checkbox.click();
    }

    public void clickLotNumberDropDown() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", click_lot_number_dropdown);
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, click_lot_number_dropdown, 10);
        Thread.sleep(2000);
        click_lot_number_dropdown.click();
        Thread.sleep(2000);
    }

    public void selectLot() throws InterruptedException {
        waitForElementToBeVisible(driver, select_lot, 10);
        Thread.sleep(2000);
        select_lot.click();
    }

    public void selectLot(String lot_string) throws InterruptedException {
        By my_lot_path = By.xpath("//li[@data-value='" + lot_string + "']");
        waitForElementToBeEnabled(driver, my_lot_path, 10);
        WebElement my_lot = driver.findElement(my_lot_path);
        scrollCenter(my_lot);
        Thread.sleep(500);
        my_lot.click();
    }

    public void setRoute(String route) throws InterruptedException {
        driver.findElement(By.xpath("//label[text() = 'Route']/..//button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@title = '" + route + "'] ")).click();
    }

    public void selectInjectionSite() throws InterruptedException {
        waitForElementToBeLocated(driver, selectSite1, 10);
        WebElement element = driver.findElement(selectSite1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
        waitForElementToBeLocated(driver, select_injection_site_value1, 10);
        WebElement element1 = driver.findElement(select_injection_site_value1);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
    }

    public void selectDosage() throws InterruptedException {
        waitForElementToBeLocated(driver, select_dosage_field1, 10);
        WebElement element = driver.findElement(select_dosage_field1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
        waitForElementToBeLocated(driver, select_dosage1, 10);
        WebElement element1 = driver.findElement(select_dosage1);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
    }

    public void selectDosage(String dosage) throws InterruptedException {
        Thread.sleep(500);
        By select_dosage_field_path = By.xpath("//button[@name='dosePicklist']");
        waitForElementToBeEnabled(driver, select_dosage_field_path, 10);
        WebElement field_element = driver.findElement(select_dosage_field_path);
        scrollCenter(field_element);
        field_element.click();
        Thread.sleep(500);
        By select_dosage_value_path = By.xpath("//span[@title='" + dosage + "']");
        waitForElementToBeEnabled(driver, select_dosage_value_path, 10);
        WebElement item_element = driver.findElement(select_dosage_value_path);
        scrollCenter(item_element);
        item_element.click();
    }

    public void saveImmunizationInformation() throws InterruptedException {
        By save_immunization_btn_path = By.xpath("//div[@c-bchcimmunizationinfo_bchcimmunizationinfo]/lightning-button[@c-bchcimmunizationinfo_bchcimmunizationinfo]/button[@type='submit' and text()='Save']");
        waitForElementToBeEnabled(driver, save_immunization_btn_path, 10);
        WebElement save_immunization_btn = driver.findElement(save_immunization_btn_path);
        scrollIfNeeded(driver, save_immunization_btn);
        save_immunization_btn.click();
    }

    public void confirmAndSaveAdministration() throws InterruptedException {
        waitForElementToBeLocated(driver, confirmAndSave1, 10);
        Thread.sleep(2000);
        confirmAndSave.click();
    }

    public void summaryConfirmAndSave() throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("//button[text()='Confirm & Save Administration' or text()='Confirm and Save']");
        waitForElementToBeEnabled(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        confirm_and_save_btn.click();
    }

    public boolean userFoundWithParameters(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
        By userFoundWithParameter = By.xpath("//a[@title='" + legalFirstName + " " + legalMiddleName + " " + legalLastName + "']");
        if (!isDisplayed(userFoundWithParameter)) {
            return false;
        }
        WebElement userFoundWithParameterId = driver.findElement(userFoundWithParameter);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", userFoundWithParameterId);
        Thread.sleep(5000);
        return true;
    }

    public String pirSubmissionStatusFieldIsDisplayed() throws InterruptedException {
        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(0).get("PIR Submission Status");
        scrollCenter(element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public String pirSubmissionFieldStatus(int index) throws InterruptedException {

        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(index).get("PIR Submission Status");
        scrollCenter(element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public ImmunizationPage ClickHistoricalImmunizationRecord(int index) throws InterruptedException {
        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(index).get("Immunization Record");
        scrollCenter(element);
        element.findElement(By.xpath(".//a/..")).click();
        return new ImmunizationPage(driver);
    }

    public String getHistoricalDosesValue() throws InterruptedException {
        WebElement element = driver.findElement(getValueOfHistoricalDoses1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        element.getText().replaceAll("[^0-9]", "");
        return (element.getText()).replaceAll("[^0-9]", "");
    }

    public void clickToCreatHistoricalImmunizationRecord() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)");
        Thread.sleep(2000);
        waitForElementToBeLocated(driver, create_Historical_Immunization_Record1, 10);
        Thread.sleep(2000);
        WebElement element = driver.findElement(create_Historical_Immunization_Record1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void ClickAgentValue() throws InterruptedException {
        waitForElementToBeLocated(driver, click_agent_value1, 10);
        WebElement element = driver.findElement(click_agent_value1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
    }

    public void SelectAgent() throws InterruptedException {
        waitForElementToBeVisible(driver, select_agent, 10);
        WebElement search_input = driver.findElement(select_agent1);
        search_input.click();
    }

    public void selectHistoricalDateAndTime() throws InterruptedException {
        By input_date_path = By.xpath("//lightning-input[@class='dateCmp slds-form-element']/lightning-datepicker");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -180);
        Date historicalDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        String historicalDateasString = dateFormat.format(historicalDate);
        waitForElementToBeEnabled(driver, input_date_path, 10);
        WebElement inputDate = driver.findElement(input_date_path);
        inputDate.click();
        Thread.sleep(500);
        inputDate.sendKeys(historicalDateasString);
        Thread.sleep(500);
        inputDate.sendKeys(Keys.ENTER);
    }

    public void ClickSaveButton() throws InterruptedException {
        waitForElementToBeLocated(driver, save_button_historical_dose1, 10);
        WebElement element = driver.findElement(save_button_historical_dose1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void navigateToHistoricalImmunizationRecords() throws InterruptedException {
        Thread.sleep(2000);
        By historical_records_title = By.xpath("//span[@title and contains(text(), 'Historical Immunization Records')]");
        waitForElementToBePresent(driver, historical_records_title, 10);
        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(0).get("PIR Submission Status");

        for(int i = 0; i < 100; i++) {
            try {
                scrollCenter(element);
                Thread.sleep(1000);
                tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(1);
                break;
            } catch (Exception ex) {
                System.out.println("Try " + i + "; Table is still empty");
                //driver.navigate().refresh();
                waitForElementToBePresent(driver, historical_records_title, 10);
                Thread.sleep(1000);
                element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(0).get("PIR Submission Status");
            }
        }
    }

    public void expiredVaxHandler() throws InterruptedException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//h1[contains(text(),'Confirm')]"));
        if(listOfElements.size()>=1){
            WebElement btnOk = driver.findElement(By.xpath("//button[@data-ok-button]"));
            click(btnOk);
            Thread.sleep(2000);
        }
    }

    public void openProfile(String clientName) throws InterruptedException {
        WebElement profile = null;
        long timeout = 15000;
        Instant start = Instant.now();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        while(timeElapsed.toMillis() < timeout) {
            try {
                profile = driver.findElement(By.xpath("//a[@title = '" + clientName + "']"));
                break;
            } catch (Exception ex) {
                end = Instant.now();
                timeElapsed = Duration.between(start, end);
                Thread.sleep(1000);
            }
        }
        profile.click();
    }
}
