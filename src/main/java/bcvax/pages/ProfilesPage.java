package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProfilesPage extends BasePage{
    /*---------Properties-------*/
    @FindBy(xpath = "//span[text() = 'Related']")
    private WebElement click_related_tab;
    private By click_related_tab1 = By.xpath("//span[text() = 'Related']");

    @FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
    private WebElement btn_create_Immunization_Record;
    private By btn_create_Immunization_Record1 = By.xpath("//button[contains(text(),'Create Immunization Record')]");

    @FindBy(xpath = "//button[text() = 'Confirm']")
    private WebElement confirm_button;
    private By confirm_button1 = By.xpath("//button[text() = 'Confirm']");

    @FindBy(xpath = "//option[contains(text(),'Select an option')]")
    private WebElement select_an_option;
    private By select_an_option1 = By.xpath("//option[contains(text(),'Select an option')]");

    @FindBy(xpath = "//option[contains(text(),'COVID-19 mRNA')]")
    private WebElement covidmRna;
    private By covidmRna2 = By.xpath("//option[contains(text(),'COVID-19 mRNA')]");

    @FindBy(xpath = ".//input[@data-id = 'userinput']")
    private WebElement search_clinic;
    private By search_clinic1 = By.xpath(".//input[@data-id = 'userinput']");

    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[1]")
    private WebElement inputDate;
    private By inputDate1 = By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[1]");

    @FindBy(xpath = "//button[contains(text(),'Record Immunization')]")
    private WebElement recordImmunizationBtn;
    private By recordImmunizationBtn1 = By.xpath("//button[contains(text(),'Record Immunization')]");

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]")
    private WebElement yes_button_save_on_popup_window;
    private By yes_button_save_on_popup_window1 = By.xpath("//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]");

    @FindBy(xpath = "//*[@class='slds-icon slds-icon_large']")
    private WebElement close_button_diwa;
    private By close_button_diwa1 = By.xpath("//*[@class='slds-icon slds-icon_large']");

    @FindBy(xpath = ".//h1[text() = 'Oops...']")
    private WebElement vlidate_oops_message;
    private By vlidate_oops_message1 = By.xpath(".//h1[text() = 'Oops...']");

    @FindBy(xpath = "(//button[normalize-space()='Continue Editing and Save'])")
    private WebElement continue_editing_btn;
    private By continue_editing_btn1 = By.xpath("//button[normalize-space()='Continue Editing and Save']");

    @FindBy(xpath = "(//input[@placeholder = 'Search People...'])[1]")
    private WebElement informed_consent_provider_dropdown;
    private By informed_consent_provider_dropdown_ = By.xpath("(//input[@placeholder = 'Search People...'])[1]");

    @FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
    private WebElement select_inform_consent_provider;
    private By select_inform_consent_provider_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");

    @FindBy(xpath = "//button[contains(text(),'Save Consent')]")
    private WebElement saveConsentButton;

    @FindBy(xpath = "(//input[@placeholder = 'Search People...'])[1]")
    private WebElement immunizing_agent_provider_dropdown;
    private By immunizing_agent_provider_dropdown_ = By.xpath("(//input[@placeholder = 'Search People...'])[1]");

    @FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
    private WebElement select_immunizing_agent_provider;
    private By select_immunizing_agent_provider_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");

    @FindBy(xpath = "//SPAN[@lightning-input_input=''][text()='Show all lot numbers.']/preceding-sibling::SPAN")
    private WebElement show_all_lot_numbers_checkbox;
    private By show_all_lot_numbers_checkbox_ = By.xpath("//SPAN[@lightning-input_input=''][text()='Show all lot numbers.']/preceding-sibling::SPAN");

    @FindBy(xpath = ".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']")
    private WebElement click_lot_number_dropdown;
    private By click_lot_number_dropdown1 = By.xpath(".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']");

    @FindBy(xpath = "//li[@title='300042698 - Exp. 2021 June 18']")
    private WebElement select_lot;
    private By select_lot_ = By.xpath("//li[@title='300042698 - Exp. 2021 June 18']");

    @FindBy(xpath = "//button[@name='injectionSite']")
    private WebElement selectSite;
    private By selectSite1 = By.xpath("//button[@name='injectionSite']");

    @FindBy(xpath = "//span[@title='Arm - Left deltoid']")
    private WebElement select_injection_site_value;
    private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");

    @FindBy(xpath = "//button[@name='dosePicklist']")
    private WebElement select_dosage_field;
    private By select_dosage_field1 = By.xpath("//button[@name='dosePicklist']");

    @FindBy(xpath = "//span[@title='0.3']")
    private WebElement select_dosage;
    private By select_dosage1 = By.xpath("//span[@title='0.5']");

    @FindBy(xpath = "//label[contains(text(),'Site')]/../../../..//button[@type='submit']")
    private WebElement saveAgain;
    private By saveAgain1 = By.xpath("//label[contains(text(),'Site')]/../../../..//button[@type='submit']");

    @FindBy(xpath = "//button[@title='Confirm & Save Administration']")
    private WebElement confirmAndSave;
    private By confirmAndSave1 = By.xpath("//button[@title='Confirm & Save Administration']");

    @FindBy(xpath = "//button[contains(text(),'Confirm and Save')]")
    private WebElement lastConfirmAndSave;
    private By lastConfirmAndSave1 = By.xpath("//button[contains(text(),'Confirm and Save')]");


    /*---------Constructor-------*/
    public ProfilesPage(WebDriver driver) {super(driver);}


    /*-------------Methods--------------*/
    public void selectCitizenParticipantAcc(String name) throws InterruptedException {
        WebElement citizen_participant_acc = driver.findElement(By.xpath("//a[contains(text(),'Benoite Denna BCVaxD')]"));
        waitForElementToBeVisible(driver, citizen_participant_acc, 10);
        Thread.sleep(5000);
        citizen_participant_acc.click();
    }

    public void clickRelatedTab() throws InterruptedException {
        waitForElementToBeLocated(driver, click_related_tab1, 10);
        Thread.sleep(2000);
        click_related_tab.click();
        //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].click();", element);
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
        waitForElementToBeVisible(driver, close_button_diwa, 10);
        Thread.sleep(2000);
        close_button_diwa.click();
        Thread.sleep(2000);
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
        if (!isInputActive(inputDate)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", inputDate);
        waitForElementToBeVisible(driver, inputDate, 10);
        String todayAsString = dateFormat.format(today);
        waitForElementToBeVisible(driver, inputDate, 10);
        this.inputDate.click();
        Thread.sleep(2000);
        this.inputDate.sendKeys(todayAsString);
        Thread.sleep(2000);
        this.inputDate.sendKeys(Keys.ENTER);
        return true;
    }

    public void selectInformedConsentProvider(String Provider) throws InterruptedException {
        //scrolling up
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", informed_consent_provider_dropdown);
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, informed_consent_provider_dropdown, 10);
        Thread.sleep(2000);
        informed_consent_provider_dropdown.click();
        Thread.sleep(5000);
        informed_consent_provider_dropdown.sendKeys(Provider);
        Thread.sleep(5000);
        waitForElementToBeVisible(driver, select_inform_consent_provider, 10);
        Thread.sleep(5000);
        select_inform_consent_provider.click();
        Thread.sleep(2000);
    }

    public void clickSaveConsent() throws InterruptedException {
        waitForElementToBeVisible(driver, saveConsentButton, 10);
        Thread.sleep(2000);
        saveConsentButton.click();
    }

    public void selectImmunizingAgentProvider(String Provider) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", immunizing_agent_provider_dropdown);
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, immunizing_agent_provider_dropdown, 10);
        Thread.sleep(2000);
        immunizing_agent_provider_dropdown.click();
        Thread.sleep(5000);
        immunizing_agent_provider_dropdown.sendKeys(Provider);
        Thread.sleep(5000);
        waitForElementToBeVisible(driver, select_immunizing_agent_provider, 10);
        Thread.sleep(5000);
        select_immunizing_agent_provider.click();
        Thread.sleep(2000);
    }

    public void clickShowAllLotNumbersCheckBox() throws InterruptedException {
        waitForElementToBeVisible(driver, show_all_lot_numbers_checkbox, 10);
        Thread.sleep(2000);
        show_all_lot_numbers_checkbox.click();
        Thread.sleep(2000);
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
        waitForElementToBeLocated(driver, saveAgain1, 10);
        Thread.sleep(2000);
        saveAgain.click();
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





}