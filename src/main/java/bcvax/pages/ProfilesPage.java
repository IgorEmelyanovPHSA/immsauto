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
    private By yes_button_save_on_popup_window1 = By.xpath("//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]");

    @FindBy(xpath = "//*[@class='slds-icon slds-icon_large']")
    private WebElement close_button_diwa;

    @FindBy(xpath = ".//h1[text() = 'Oops...']")
    private WebElement vlidate_oops_message;

    @FindBy(xpath = "(//button[normalize-space()='Continue Editing and Save'])")
    private WebElement continue_editing_btn;

    @FindBy(xpath = ".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']")
    private WebElement click_lot_number_dropdown;

    @FindBy(xpath = "//li[@title='300042698 - Exp. 2021 June 18']")
    private WebElement select_lot;

    private By selectSite1 = By.xpath("//button[@name='injectionSite']");

    private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");

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

    public void expiredVaxHandler() throws InterruptedException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//h1[contains(text(),'Confirm')]"));
        if(listOfElements.size()>=1){
            WebElement btnOk = driver.findElement(By.xpath("//button[@data-ok-button]"));
            click(btnOk);
            Thread.sleep(2000);
        }
    }
}
