package primarycare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private WebElement caase_reason_actual_field_value;
    private By case_reason_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordReasonField']//slot[1]//slot[1]/lightning-formatted-text");

    @FindBy(xpath = "//span[text() ='A person in care registration']")
    private WebElement person_in_care_radiobutton;
    private By person_in_care_radiobutton_1 = By.xpath("//span[text() ='A person in care registration']");

    @FindBy(xpath = "(//span[text()='Next'])[1]")
    private WebElement next_button;
    private By next_button_1 = By.xpath("(//span[text()='Next'])[1]");

    @FindBy(xpath = "//span[contains(text(),'The caller is registering on the patient')]")
    private WebElement caller_registering_patients_behalf_checkbox;
    private By caller_registering_patients_behalf_checkbox_1 = By.xpath("//span[contains(text(),'The caller is registering on the patient')]");

    @FindBy(xpath = "(//input[@class = 'slds-input slds-listbox__option-text_entity'])[1]")
    private WebElement caller_relationship_dropdown;
    private By caller_relationship_dropdown_1 = By.xpath("(//input[@class = 'slds-input slds-listbox__option-text_entity'])[1]");

    @FindBy(xpath = "//span[text() ='Social worker']")
    private WebElement select_caller_relationship;
    private By select_caller_relationship_1 = By.xpath("//span[text() ='Social worker']");





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
        priority_actual_field_value.isDisplayed();
        return (caase_reason_actual_field_value.getText());
    }

    public void selectRegistrationTypePersonInCare() throws InterruptedException {
        waitForElementToBeLocated(driver, person_in_care_radiobutton_1, 10);
        Thread.sleep(2000);
        log("/*----scroll down a bit --*/");
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        WebElement element = driver.findElement(person_in_care_radiobutton_1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        person_in_care_radiobutton.click();
    }

    public void clickNext() throws InterruptedException {
        waitForElementToBeLocated(driver, next_button_1, 10);
        Thread.sleep(1000);
        next_button.click();
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




}
