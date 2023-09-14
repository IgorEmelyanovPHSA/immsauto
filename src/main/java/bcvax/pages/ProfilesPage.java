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
import java.util.Locale;
import java.util.stream.IntStream;

public class ProfilesPage extends BasePage{
    /*---------Properties-------*/
    @FindBy(xpath = "//button[@title='Select a List View']")
    private WebElement selectListViewBtn;

    @FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
    private WebElement btn_create_Immunization_Record;
    private By btn_create_Immunization_Record1 = By.xpath("//button[contains(text(),'Create Immunization Record')]");
    @FindBy(xpath = "//button[text() = 'Confirm']")
    private WebElement confirm_button;
    private By getValueOfHistoricalDoses1 = By.xpath("(//BUTTON[@class='slds-button slds-button_neutral'][text()='New'])[1]/../../../../../..//SPAN[@class='slds-page-header__title slds-truncate'][contains(text(),'Historical Immunization Records')]");

    private By create_Historical_Immunization_Record1 = By.xpath("//SPAN[@class='slds-page-header__title slds-truncate'][contains(text(),'Historical Immunization Records ')]/../../../../..//BUTTON[@class='slds-button slds-button_neutral'][text()='New']");
    @FindBy(xpath = "//option[contains(text(),'Select an option')]")
    private WebElement select_an_option;

    @FindBy(xpath = "//option[contains(text(),'COVID-19 mRNA')]")
    private WebElement covidmRna;
    private By covidmRna2 = By.xpath("//option[contains(text(),'COVID-19 mRNA')]");

    @FindBy(xpath = ".//input[@data-id = 'userinput']")
    private WebElement search_clinic;
    //@FindBy(xpath = "(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[1]")
    @FindBy(xpath = "//lightning-input[@class='dateCmp slds-form-element']/lightning-datepicker")
    private WebElement inputDate;
    @FindBy(xpath = "//button[contains(text(),'Record Immunization')]")
    private WebElement recordImmunizationBtn;

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
    @FindBy(xpath = "(//input[@placeholder = 'Search People...'])[1]")
    private WebElement informed_consent_provider_dropdown;

    @FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
    private WebElement select_inform_consent_provider;

    @FindBy(xpath = "//SPAN[@lightning-input_input=''][text()='Show all lot numbers.']/preceding-sibling::SPAN")
    private WebElement show_all_lot_numbers_checkbox;

    @FindBy(xpath = ".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']")
    private WebElement click_lot_number_dropdown;

    @FindBy(xpath = "//li[@title='300042698 - Exp. 2021 June 18']")
    private WebElement select_lot;

    private By selectSite1 = By.xpath("//button[@name='injectionSite']");

    private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");

    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[2]")
    private WebElement inputDateVaccineAdmin;

    private By save_button_historical_dose1 = By.xpath("//button[contains(text(),'Save')]");

    private By select_dosage_field1 = By.xpath("//button[@name='dosePicklist']");

    private By select_dosage1 = By.xpath("//span[@title='0.5']");

    private By UserDetailsHomePage1 = By.xpath("//lightning-formatted-name[@class='slds-form-element__static']");

    private By save_button_historical_dose_vaccineAdmin1 = By.xpath("(//button[contains(text(),'Save')])[2]");

    private By clickAddBtn1 = By.xpath("(//button[text()='Add'])[1]");

    @FindBy(xpath = "//button[@title='Confirm & Save Administration']")
    private WebElement confirmAndSave;
    private By confirmAndSave1 = By.xpath("//button[@title='Confirm & Save Administration']");

    @FindBy(xpath = "//button[contains(text(),'Confirm and Save')]")
    private WebElement lastConfirmAndSave;
    private By lastConfirmAndSave1 = By.xpath("//button[contains(text(),'Confirm and Save')]");

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

    public void selectCitizenParticipant(String name) throws InterruptedException {
        Thread.sleep(500);
        By citizen_participant_acc_path = By.xpath("//a[contains(text(),'"+ name +"')]");
        waitForElementToBePresent(driver, citizen_participant_acc_path, 30);
        WebElement my_citizen = driver.findElement(citizen_participant_acc_path);
        scrollTop(my_citizen);
        my_citizen.click();
    }

    public void closePIRWarningDialog() throws InterruptedException {
        Thread.sleep(500);
        By cancel_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']/footer//button[text()='Cancel']");
        waitForElementToBeEnabled(driver, cancel_btn_path, 20);
        WebElement cnncel_btn = driver.findElement(cancel_btn_path);
        cnncel_btn.click();
    }

    public void clickPotentialDuplicateYes() throws InterruptedException {
        Thread.sleep(500);
        By cancel_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']/footer//button[text()='Yes']");
        waitForElementToBeEnabled(driver, cancel_btn_path, 20);
        WebElement cnncel_btn = driver.findElement(cancel_btn_path);
        cnncel_btn.click();
    }

    public void clickRelatedTab() throws InterruptedException {
        Thread.sleep(500);
        By related_tab_path = By.xpath("//a[text() = 'Related'] | //a[@title = 'Related']");
        waitForElementToBeEnabled(driver, related_tab_path, 10);
        WebElement related_tab = driver.findElement(related_tab_path);
        related_tab.click();
    }

    public void clickCreateImmunizationRecord() throws InterruptedException {
        waitForElementToBeLocated(driver, btn_create_Immunization_Record1, 10);
        Thread.sleep(2000);
        btn_create_Immunization_Record.click();
    }

    public void clickConfirmButton() throws InterruptedException {
        waitForElementToBeVisible(driver, confirm_button, 10);
        Thread.sleep(2000);
        confirm_button.click();
    }

    public void clickSelectAnOptionDropdown() throws InterruptedException {
        waitForElementToBeVisible(driver, select_an_option, 10);
        Thread.sleep(2000);
        select_an_option.click();
    }

    public void selectOption(String vaccine) throws InterruptedException {
        waitForElementToBeVisible(driver, covidmRna, 10);
        Thread.sleep(2000);
        WebElement search_input = driver.findElement(covidmRna2);
        Thread.sleep(2000);
        search_input.click();
    }

    public void searchClinicLocation(String clinic) throws InterruptedException {
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, search_clinic, 10);
        Thread.sleep(2000);
        search_clinic.sendKeys(clinic);
        Thread.sleep(4000);
        By select_dropdown_option = By.xpath(".//div[@class = 'slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta']");
        driver.findElement(select_dropdown_option).click();
        Thread.sleep(2000);
    }

    public void clickTimeBox() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        waitForElementToBeVisible(driver, inputDate, 10);
        String todayAsString = dateFormat.format(today);
        waitForElementToBeVisible(driver, inputDate, 10);
        inputDate.click();
        Thread.sleep(2000);
        inputDate.sendKeys(todayAsString);
        Thread.sleep(2000);
        inputDate.sendKeys(Keys.ENTER);
    }

    public void clickRecordImmunization() throws InterruptedException {
        waitForElementToBeVisible(driver, recordImmunizationBtn, 10);
        Thread.sleep(2000);
        recordImmunizationBtn.click();
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

    public static void confirm_warning(WebDriver driver) throws  InterruptedException {
        Thread.sleep(500);
        By confirm_btn_path = By.xpath("//div[@c-bchcmodal_bchcmodal and @class='slds-modal__container']//button[text()='Confirm']");
        waitForElementToBeEnabled(driver, confirm_btn_path, 10);
        WebElement confirm_btn = driver.findElement(confirm_btn_path);
        confirm_btn.click();
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

    public boolean selectDateOfAdministration() throws InterruptedException {
        Thread.sleep(500);
        By input_date_field_path = By.xpath("//label[text()='Date of Administration']/../..");
        waitForElementToBeEnabled(driver, input_date_field_path, 10);
        WebElement input_date_field = driver.findElement(input_date_field_path);
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.DATE, -1);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        String todayAsString = dateFormat.format(today);
        input_date_field.sendKeys(todayAsString);
        Thread.sleep(500);
        //input_date_field.sendKeys(Keys.ENTER);
        return true;
    }

    public boolean selectConsentEffectiveDate() throws InterruptedException {
        By input_consent_date_field_path = By.xpath("//label[text()='Consent Effective To Date']/../..");
        waitForElementToBeEnabled(driver, input_consent_date_field_path, 10);
        WebElement input_consent_date_field = driver.findElement(input_consent_date_field_path);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        scrollIfNeeded(driver, input_consent_date_field);
        String todayAsString = dateFormat.format(today);
        input_consent_date_field.sendKeys(todayAsString);
        Thread.sleep(2000);
        input_consent_date_field.sendKeys(Keys.ENTER);
        return true;
    }
    public String consentProviderSelected() {
        By providerFieldPath = By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]");
        waitForElementToBeLocated(driver, providerFieldPath, 10);
        return driver.findElement(providerFieldPath).getAttribute("data-value");
    }

    public String selectConsentProvider() throws InterruptedException {
        WebElement consentProviderField = driver.findElement(By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]"));
        scrollTop(consentProviderField);
        consentProviderField.click();
        Thread.sleep(2000);
        try {
            driver.findElement(By.xpath("//span[@class = 'slds-listbox__option-text slds-listbox__option-text_entity']")).click();
        } catch(Exception ex) {
            driver.findElement(By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[@class = 'slds-listbox__option-text slds-listbox__option-text_entity']")).click();
        }
        Thread.sleep(2000);
        return driver.findElement(By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]")).getAttribute("data-value");
    }

    public String selectConsentProvider(String consentProvider) throws InterruptedException {
        Thread.sleep(500);
        By consent_provider_field_path = By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]");
        By consent_provider_item_path = By.xpath("//span[@class = 'slds-listbox__option-text slds-listbox__option-text_entity']");
        waitForElementToBeEnabled(driver, consent_provider_field_path, 10);
        WebElement consentProviderField = driver.findElement(consent_provider_field_path);
        scrollTop(consentProviderField);
        Thread.sleep(500);
        consentProviderField.click();
        consentProviderField.sendKeys(consentProvider);
        Thread.sleep(1000);
        By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + consentProvider + "']");
        waitForElementToBeLocated(driver, providerItemPath, 10);
        driver.findElement(providerItemPath).click();
        Thread.sleep(500);
        try {
            String consent_provider_selected = consentProviderSelected();
            if(consent_provider_selected.equals("")) {
                waitForElementToBeEnabled(driver, consent_provider_item_path, 10);
                driver.findElement(consent_provider_item_path).click();
            }
        } catch(Exception ex) {
            System.out.println("***DEBUG*** Tried to select Consent Provider. Error: " + ex.getMessage());
            waitForElementToBeEnabled(driver, consent_provider_field_path, 10);
            consentProviderField = driver.findElement(consent_provider_field_path);
            scrollTop(consentProviderField);
            consentProviderField.click();
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, consent_provider_item_path, 10);
            driver.findElement(consent_provider_item_path).click();
        }
        Thread.sleep(2000);
        return driver.findElement(By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]")).getAttribute("data-value");
    }

    public void confirmConsentProvider(String consentPorvider) throws InterruptedException {
        By consent_provider_link_path = By.xpath("//a[text()='" + consentPorvider + "']");
        waitForElementToBeEnabled(driver, consent_provider_link_path, 10);
        WebElement consent_provider_link = driver.findElement(consent_provider_link_path);
        consent_provider_link.click();
    }

    public void clickSaveConsent() throws InterruptedException {
        Thread.sleep(500);
        By save_consent_btn_path = By.xpath("//button[text()='Save Consent']");
        waitForElementToBeEnabled(driver, save_consent_btn_path, 10);
        WebElement save_consent_btn = driver.findElement(save_consent_btn_path);
        scrollIfNeeded(driver, save_consent_btn);
        save_consent_btn.click();
    }

    public void selectImmunizingAgentProvider(String provider) throws InterruptedException {
        Thread.sleep(1000);
        By providerFieldPath = By.xpath("//label[text() = 'Provider' and @c-bchcimmunizationinfo_bchcimmunizationinfo]/..//input");
        waitForElementToBePresent(driver, providerFieldPath, 30);
        WebElement providerField =  driver.findElement(providerFieldPath);
        scrollTop(providerField);
        providerField.sendKeys(provider);
        Thread.sleep(1000);
        By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
        waitForElementToBeLocated(driver, providerItemPath, 10);
        WebElement providerItem = driver.findElement(providerItemPath);
        scrollTop(providerItem);
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
        waitForElementToBeLocated(driver, lastConfirmAndSave1, 10);
        Thread.sleep(2000);
        lastConfirmAndSave.click();
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
        scrollTop(element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public String pirSubmissionFieldStatus(int index) throws InterruptedException {

        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(index).get("PIR Submission Status");
        scrollTop(element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public ImmunizationPage ClickHistoricalImmunizationRecord(int index) throws InterruptedException {
        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(index).get("Immunization Record");
        scrollTop(element);
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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -180);
        Date historicalDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        String historicalDateasString = dateFormat.format(historicalDate);
        waitForElementToBeVisible(driver, inputDate, 10);
        this.inputDate.click();
        Thread.sleep(2000);
        this.inputDate.sendKeys(historicalDateasString);
        Thread.sleep(2000);
        this.inputDate.sendKeys(Keys.ENTER);
    }

    public void selectConsentEffectiveToDate() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date effectiveToDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        String historicalDateasString = dateFormat.format(effectiveToDate);
        By efective_to_date_field_path = By.xpath("//input[@name='effectiveToDate']");
        waitForElementToBeEnabled(driver, efective_to_date_field_path, 10);
        WebElement efective_to_date_field = driver.findElement(efective_to_date_field_path);
        scrollTop(efective_to_date_field);
        Thread.sleep(500);
        efective_to_date_field.click();
        Thread.sleep(2000);
        efective_to_date_field.sendKeys(historicalDateasString);
        Thread.sleep(2000);
        efective_to_date_field.sendKeys(Keys.ENTER);
    }

    public void ClickSaveButton() throws InterruptedException {
        waitForElementToBeLocated(driver, save_button_historical_dose1, 10);
        WebElement element = driver.findElement(save_button_historical_dose1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public String getUserDetailsOnVaccineAdministration() throws InterruptedException {
        WebElement element = driver.findElement(UserDetailsHomePage1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public void ClickAddBtntoAddHistoricalImmunization() throws InterruptedException {
        waitForElementToBeLocated(driver, clickAddBtn1, 10);
        WebElement element = driver.findElement(clickAddBtn1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);
    }

    public void selectHistoricalDateAndTimeOnVaccineAdministration() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -180);
        Date historicalDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        String historicalDateasString = dateFormat.format(historicalDate);
        waitForElementToBeVisible(driver, inputDateVaccineAdmin, 10);
        this.inputDateVaccineAdmin.click();
        Thread.sleep(2000);
        this.inputDateVaccineAdmin.sendKeys(historicalDateasString);
        Thread.sleep(2000);
        this.inputDateVaccineAdmin.sendKeys(Keys.ENTER);
    }

    public void ClickSaveButtonOnVaccineAdministrationPage() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
        Thread.sleep(2000);
        waitForElementToBeLocated(driver, save_button_historical_dose_vaccineAdmin1, 10);
        Thread.sleep(2000);
        WebElement element = driver.findElement(save_button_historical_dose_vaccineAdmin1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }


    public void selectAllParticipantsOption() throws InterruptedException {
        waitForElementToBeVisible(driver, selectListViewBtn, 10);
        selectListViewBtn.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul[@role='listbox']//*[text()='All Participants']")).click();
        Thread.sleep(2000);
    }

    public void navigateToHistoricalImmunizationRecords() throws InterruptedException {
        Thread.sleep(2000);
        By historical_records_title = By.xpath("//span[@title and contains(text(), 'Historical Immunization Records')]");
        waitForElementToBePresent(driver, historical_records_title, 10);
        WebElement element = tables.getHistoricalImmunizationRecordsTable().getRowsMappedToHeadings().get(0).get("PIR Submission Status");

        for(int i = 0; i < 100; i++) {
            try {
                scrollTop(element);
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
