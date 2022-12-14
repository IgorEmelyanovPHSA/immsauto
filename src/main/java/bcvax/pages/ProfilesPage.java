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

    @FindBy(xpath = "(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[2]")
    private WebElement inputDate;
    private By inputDate1 = By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[2]");

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
        WebElement element = driver.findElement(click_related_tab1);
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


}
