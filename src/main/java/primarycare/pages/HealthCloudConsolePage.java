package primarycare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import primarycare.pages.BasePage;

public class HealthCloudConsolePage extends BasePage {

    /*---------Properties-------*/
    @FindBy(xpath = ".//a[text() = 'Related']")
    private WebElement patient_account_Related_tab;
    private By patient_account_Related_tab_1 = By.xpath(".//a[text() = 'Related']");

    @FindBy(xpath = ".//a[@title='Attachment Registry']")
    private WebElement case_record;
    private By case_record_1 = By.xpath(".//a[@title='Attachment Registry']");

    @FindBy(xpath = ".//flexipage-field[@data-field-id='RecordOriginField']//slot[1]//slot[1]/lightning-formatted-text")
    private WebElement case_origin_actual_field_value;
    private By case_origin_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordOriginField']//slot[1]//slot[1]/lightning-formatted-text");

    @FindBy(xpath = ".//flexipage-field[@data-field-id='RecordPriorityField']//slot[1]//slot[1]/lightning-formatted-text")
    private WebElement priority_actual_field_value;
    private By priority_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordPriorityField']//slot[1]//slot[1]/lightning-formatted-text");

    @FindBy(xpath = ".//flexipage-field[@data-field-id='RecordStatusField']//slot[1]//slot[1]/lightning-formatted-text")
    private WebElement status_actual_field_value;
    private By status_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordStatusField']//slot[1]//slot[1]/lightning-formatted-text");

    @FindBy(xpath = ".//flexipage-field[@data-field-id='RecordAccountIdField']//slot[1]//slot[1]//slot[1]//span[text()]")
    private WebElement account_name_actual_field_value;
    private By account_name_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordAccountIdField']//slot[1]//slot[1]//slot[1]//span[text()]");

    @FindBy(xpath = ".//flexipage-field[@data-field-id='RecordReasonField']//slot[1]//slot[1]/lightning-formatted-text")
    private WebElement case_reason_actual_field_value;
    private By case_reason_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordReasonField']//slot[1]//slot[1]/lightning-formatted-text");

    @FindBy(xpath = "//span[text() ='A person in my care']")
    private WebElement person_in_my_care_radiobutton;
    private By person_in_my_care_radiobutton_1 = By.xpath("//span[text() ='A person in my care']");

    @FindBy(xpath = "(//span[text()='Continue'])[1]")
    private WebElement continue_button;
    private By continue_button_1 = By.xpath("(//span[text()='Continue'])[1]");

    @FindBy(xpath = "//span[contains(text(),'The caller is registering on the patient')]")
    private WebElement caller_registering_patients_behalf_checkbox;
    private By caller_registering_patients_behalf_checkbox_1 = By.xpath("//span[contains(text(),'The caller is registering on the patient')]");

    @FindBy(xpath = "(//input[@class = 'slds-input slds-listbox__option-text_entity'])[1]")
    private WebElement caller_relationship_dropdown;
    private By caller_relationship_dropdown_1 = By.xpath("(//input[@class = 'slds-input slds-listbox__option-text_entity'])[1]");

    @FindBy(xpath = "//span[text() ='Social worker']")
    private WebElement select_caller_relationship;
    private By select_caller_relationship_1 = By.xpath("//span[text() ='Social worker']");

    @FindBy(xpath = "(//span[@class = 'slds-checkbox_faux'])[1]")
    private WebElement caller_obtained_consent_checkbox;
    private By caller_obtained_consent_checkbox_1 = By.xpath("(//span[@class = 'slds-checkbox_faux'])[1]");

    @FindBy(xpath = "//input[@placeholder = 'First and Last Name']")
    private WebElement caller_name;
    private By caller_name_1 = By.xpath("//input[@placeholder = 'First and Last Name']");

    @FindBy(xpath = "(//input[@placeholder = 'Must match provincial health records'])[1]")
    private WebElement patient_first_name;
    private By patient_first_name_1 = By.xpath("(//input[@placeholder = 'Must match provincial health records'])[1]");

    @FindBy(xpath = "(//input[@placeholder = 'Must match provincial health records'])[2]")
    private WebElement patient_last_name;
    private By patient_last_name_1 = By.xpath("(//input[@placeholder = 'Must match provincial health records'])[2]");

    @FindBy(xpath = "(//input[@omnistudio-maskedinput_maskedinput_slds])[1]")
    private WebElement patient_phn;
    private By patient_phn_1 = By.xpath("(//input[@omnistudio-maskedinput_maskedinput_slds])[1]");

    @FindBy(xpath = "//input[@placeholder = 'MM']")
    private WebElement patient_date_of_birth_MM;
    private By patient_date_of_birth_MM_1 = By.xpath("//input[@placeholder = 'MM']");

    @FindBy(xpath = "//input[@placeholder = 'DD']")
    private WebElement patient_date_of_birth_DD;
    private By patient_date_of_birth_DD_1 = By.xpath("//input[@placeholder = 'DD']");

    @FindBy(xpath = "//input[@placeholder = 'YYYY']")
    private WebElement patient_date_of_birth_YYYY;
    private By patient_date_of_birth_YYYY_1 = By.xpath("//input[@placeholder = 'YYYY']");

    @FindBy(xpath = "//span[text() ='Verify EMPI']")
    private WebElement verify_EMPI_button;
    private By verify_EMPI_button_1 = By.xpath("//span[text() ='Verify EMPI']");

    @FindBy(xpath = "(//omnistudio-omniscript-messaging//p[text()])[2]")
    private WebElement empi_actual_verification_status;
    private By empi_actual_verification_status_1 = By.xpath("(//omnistudio-omniscript-messaging//p[text()])[2]");

    /*---------Constructor-------*/
    public HealthCloudConsolePage(WebDriver driver) {
        super(driver);
    }



    /*-------------Methods--------------*/
    public void clickOnRelatedTab() throws InterruptedException {
        WebElement element = driver.findElement(patient_account_Related_tab_1);
        Thread.sleep(2000);
        isDisplayed(patient_account_Related_tab_1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickOnCaseRecord() throws InterruptedException {
        waitForElementToBeLocated(driver, case_record_1, 10);
        Thread.sleep(2000);
        case_record.click();
    }

    public String getCaseOriginActualForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, case_origin_actual_field_value_1, 10);
        Thread.sleep(2000);
        case_origin_actual_field_value.isDisplayed();
        return (case_origin_actual_field_value.getText());
    }

    public String getPriorityActualForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, priority_actual_field_value_1, 10);
        Thread.sleep(2000);
        priority_actual_field_value.isDisplayed();
        return (priority_actual_field_value.getText());
    }

    public String getStatusActualForValidation() throws InterruptedException {
        //scroll down to control element
        //WebElement element = driver.findElement(status_actual_field_value_1);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        case_origin_actual_field_value.click();//let's change the focused on actual Case Detail window frame
        Thread.sleep(2000);
        //log("/*----scroll down a bit --*/");
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        //Thread.sleep(2000);
        WebElement element = driver.findElement(status_actual_field_value_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        waitForElementToBeLocated(driver, status_actual_field_value_1, 10);
        Thread.sleep(2000);
        priority_actual_field_value.isDisplayed();
        return (status_actual_field_value.getText());
    }

    public String getAccountNameActualForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, account_name_actual_field_value_1, 10);
        Thread.sleep(2000);
        account_name_actual_field_value.isDisplayed();
        return (account_name_actual_field_value.getText());
    }

    public String getCaseReasonActualForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, case_reason_actual_field_value_1, 10);
        Thread.sleep(2000);
        case_reason_actual_field_value.isDisplayed();
        return (case_reason_actual_field_value.getText());
    }

    public void selectRegistrationType() throws InterruptedException {
        waitForElementToBeLocated(driver, person_in_my_care_radiobutton_1, 10);
        Thread.sleep(2000);
        log("/*----scroll down a bit --*/");
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        WebElement element = driver.findElement(person_in_my_care_radiobutton_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        person_in_my_care_radiobutton.click();
    }

    public void clickObtainedConsent() throws InterruptedException {
        waitForElementToBeLocated(driver, caller_obtained_consent_checkbox_1, 10);
        Thread.sleep(1000);
        log("/*----scroll down --*/");
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        WebElement element = driver.findElement(caller_obtained_consent_checkbox_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        caller_obtained_consent_checkbox.click();
    }

    public void clickContinue() throws InterruptedException {
        waitForElementToBeLocated(driver, continue_button_1, 10);
        Thread.sleep(1000);
        continue_button.click();
    }

    public void selectCallerOnThePatientBehalf() throws InterruptedException {
        waitForElementToBeLocated(driver, caller_registering_patients_behalf_checkbox_1, 10);
        Thread.sleep(1000);
        log("/*----scroll up a bit --*/");
        WebElement element = driver.findElement(caller_registering_patients_behalf_checkbox_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        caller_registering_patients_behalf_checkbox.click();
    }

    public void selectCallerRelationshipWithPatient(String relationship) throws InterruptedException {
        waitForElementToBeLocated(driver, caller_relationship_dropdown_1, 10);
        caller_relationship_dropdown.click();
        Thread.sleep(1000);
        waitForElementToBeVisible(driver, select_caller_relationship, 10);
        Thread.sleep(1000);
        log("/*----scroll a bit --*/");
        WebElement element = driver.findElement(select_caller_relationship_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(1000);
        select_caller_relationship.click();
        Thread.sleep(1000);
    }

    public void enterNameOfCaller(String nameOfCaller) throws InterruptedException {
        waitForElementToBeLocated(driver, caller_name_1, 10);
        Thread.sleep(1000);
        caller_name.sendKeys(nameOfCaller);
    }
    public void enterPatientFirstName(String patientFirstName) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_first_name_1, 10);
        Thread.sleep(1000);
        patient_first_name.sendKeys(patientFirstName);
    }

    public void enterPatientLastName(String patientLastName) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_last_name_1, 10);
        Thread.sleep(1000);
        patient_last_name.sendKeys(patientLastName);
    }

    public void enterPatientPHN(String patientPHN) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_phn_1, 10);
        Thread.sleep(1000);
        patient_phn.click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(patientPHN).build().perform();
        //patient_phn.sendKeys(patientPHN);
        Thread.sleep(1000);
    }

    public void enterMonth(String dateOfBirth_mm) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_date_of_birth_MM_1, 10);
        Thread.sleep(1000);
        patient_date_of_birth_MM.sendKeys(dateOfBirth_mm);
    }
    public void enterDay(String dateOfBirth_dd) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_date_of_birth_DD_1, 10);
        Thread.sleep(1000);
        patient_date_of_birth_DD.sendKeys(dateOfBirth_dd);
    }
    public void enterYear(String dateOfBirth_yy) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_date_of_birth_YYYY_1, 10);
        Thread.sleep(1000);
        patient_date_of_birth_YYYY.sendKeys(dateOfBirth_yy);
    }

    public void clickVerifyPHN() throws InterruptedException {
        waitForElementToBeLocated(driver, verify_EMPI_button_1, 10);
        Thread.sleep(1000);
        verify_EMPI_button.click();
    }

    public String getEMPIStatusActualForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, empi_actual_verification_status_1, 10);
        Thread.sleep(2000);
        empi_actual_verification_status.isDisplayed();
        return (empi_actual_verification_status.getText());
    }

}
