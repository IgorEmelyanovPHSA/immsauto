package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserDefaultsPage extends BasePage{

    public UserDefaultsPage(WebDriver driver) {super(driver);}
    @FindBy(xpath = "//label[contains(text(),'Clinic Location')]/..//div[@role='none']//input[@type='text' and @role='textbox']")
    private WebElement clinicLocationUserDefaults;

    @FindBy(xpath = "//button[@name='DeleteLot']")
    private WebElement btnDeleteLot;

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement btnSave;

    private By toastMessageSuccess = By.xpath("//span[contains(text(),'User defaults successfully updated.')]");


    @Step
    public UserDefaultsPage clickOnAdvancedSettings() throws InterruptedException {
        Thread.sleep(500);
        By advanced_settings_btn_path  = By.xpath("//div[contains(text(),'Advanced Settings')]");
        waitForElementToBeEnabled(driver, advanced_settings_btn_path, 10);
        WebElement advanced_settings_btn = driver.findElement(advanced_settings_btn_path);
        advanced_settings_btn.click();
        return this;
    }

    public void clickBtnSave() throws InterruptedException {
        click(btnSave);
       Thread.sleep(1000); //Save function
    }

    public UserDefaultsPage clickBtnSaveWithSuccessMsgValidation() throws InterruptedException {
        clickBtnSave();
        validateSuccessfullyUpdatedMsg();
        return this;
    }

    public void clickOkForExpiredLot() throws InterruptedException {
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

    public UserDefaultsPage deleteAllLotsIfAnyHasBeenSavedPreviously() throws InterruptedException {
        while(isAnyLotsPresent() == true){
            click(btnDeleteLot);
        }
        return this;
    }

    public UserDefaultsPage populateLotsAndSite(String [] lots) throws InterruptedException {
        int count = 1;
        for(int i=0; i < lots.length; i ++){
            By add_new_btn_path = By.xpath("//button [@class='slds-button' and @title='Add']");
            waitForElementToBeEnabled(driver, add_new_btn_path, 10);
            WebElement add_new_btn = driver.findElement(add_new_btn_path);
            scrollCenter(add_new_btn);
            Thread.sleep(500);
            add_new_btn.click();
            By lot_dropdown_btn_path = By.xpath("(//span[@title='Lot#']/../../../../../../..//input[@class='slds-input slds-combobox__input slds-combobox__input-value combobox-input-class'])["+ count +"]");
            waitForElementToBeEnabled(driver, lot_dropdown_btn_path, 10);
            WebElement drpDownLot = driver.findElement(lot_dropdown_btn_path);
            drpDownLot.click();
            Thread.sleep(500);

            By lot_edit_field_path = By.xpath("(//lightning-primitive-cell-factory[@data-label='Lot#']//input[@class='slds-input search-input-class' and @type='text'])["+ count +"]");
            waitForElementToBeEnabled(driver, lot_edit_field_path, 10);
            WebElement textEditableLot = driver.findElement(lot_edit_field_path);
            textEditableLot.sendKeys(lots[i]);
            Thread.sleep(500);
            By my_lot_path = By.xpath("(//lightning-primitive-cell-factory[@data-label='Lot#']//input[@class='slds-input search-input-class' and @type='text'])["+ count +"]/../../..//span[contains(text(), '"+ lots[i] +"')]");
            waitForElementToBeEnabled(driver, my_lot_path, 10);
            WebElement textConfirmSelection = driver.findElement(my_lot_path);
            textConfirmSelection.click();
            Thread.sleep(500);

            WebElement drpDownSiteLastSelected = driver.findElement(By.xpath("(//lightning-primitive-cell-factory[@data-label='Site']//button)[" + count + "]"));
            drpDownSiteLastSelected.click();
            Thread.sleep(500);
            WebElement dropDownSiteArmLeftDeltoid = driver.findElement(By.xpath("(//span[@title='Arm - Left deltoid'])["+ count +"]"));
            click(dropDownSiteArmLeftDeltoid);
            Thread.sleep(500);
            count++;
        }
        return this;
    }

    public boolean isToastMessageDisplayed() {
        boolean flag = false;
        List countOfFoundLot = driver.findElements(toastMessageSuccess);
        if(countOfFoundLot.size()>0){
            flag = true;
        }
        return flag;
    }

    public boolean successfullyUpdatedMsgDisplayed() throws InterruptedException {
        boolean flag = false;
        for (int i = 0; i < 12; i++) {
            if(isToastMessageDisplayed() == true) {
                flag = true;
                Thread.sleep(500);
        }
            else{
                log("NOT DISPLAYED: User Defaults Successfully Updated");
                break;
            }
        }
        return flag;
    }

    public void validateSuccessfullyUpdatedMsg() throws InterruptedException {
        if (isToastMessageDisplayed() == false) {
            throw new RuntimeException("NOT DISPLAYED: User Defaults Successfully Updated");
        }
        for (int i = 0; i < 10; i++) {
            if (isToastMessageDisplayed() == true) {
                Thread.sleep(500);
            }
        }
            log("User Defaults Successfully Updated message displayed");
    }

    public boolean isAnyLotsPresent() {
        boolean flag = false;
        List countOfFoundLot = driver.findElements(By.xpath("//button[@name='DeleteLot']"));
        if(countOfFoundLot.size()>0){
            flag = true;
        }
        return flag;
    }

    public void inputCurrentDateUserDefaults() throws InterruptedException {
        Thread.sleep(500);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date today = calendar.getTime();
        inputDateUserDefaults(today);
    }

    public void inputPreviousDateUserDefaults() throws InterruptedException {
        Thread.sleep(500);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date today = calendar.getTime();
        inputDateUserDefaults(today);
    }

    public void inputDateUserDefaults(Date date) throws InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        By input_date_path = By.xpath("//input[@name='BCH_Date__c']");
        waitForElementToBeEnabled(driver, input_date_path, 10);
        String todayAsString = dateFormat.format(date);
        WebElement input_date = driver.findElement(input_date_path);

        try {
            input_date.click();
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, input_date_path, 10);
            input_date = driver.findElement(input_date_path);
            input_date.isEnabled();
            input_date.clear();
        } catch(StaleElementReferenceException ex) {
            System.out.println("***DEBUG*** Stale element exception ***");
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, input_date_path, 10);
            input_date = driver.findElement(input_date_path);
            input_date.clear();
        }

        //input_date.click();
        //Thread.sleep(2000);
        input_date.sendKeys(todayAsString);
        Thread.sleep(2000);
        input_date.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        try {
            closeSuccessDialog();
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("No Success Dialog. Continue...");
        }
    }

    public void closeSuccessDialog() throws InterruptedException {
        try {
            WebElement alertCloseBtn = driver.findElement(By.xpath("//div[@role='alertdialog']/button[@title='Close']"));
            alertCloseBtn.click();
            System.out.println("Alert dialog found and Closed.");
        } catch(Exception ex) {
            System.out.println("Alert Dialog not found, try again");
            System.out.println("Exception: " + ex.getMessage());
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@role='alertdialog']/button[@title='Close']")).click();
            System.out.println("Alert dialog found and Closed.");
        } finally {
            System.out.println("Continue ....");
        }
    }

    public void selectClinicUserDefaults(String clinicLocation) throws InterruptedException {
        click(clinicLocationUserDefaults);
        clinicLocationUserDefaults.sendKeys(clinicLocation);
        validateSuccessfullyUpdatedMsg();
    }

    public void selectUserDefaultLocation(String location) throws InterruptedException {
        Thread.sleep(500);
        By location_input_field_path = By.xpath("//c-bc-hc-input-search-drop-down//input");
        waitForElementToBeEnabled(driver, location_input_field_path, 10);
        WebElement location_input_field = driver.findElement(location_input_field_path);
        location_input_field.click();
        By combo_box_path = By.xpath("//ul[@class='slds-listbox slds-listbox_vertical']");
        waitForElementToBeEnabled(driver, combo_box_path, 10);
        WebElement combo_box = driver.findElement(combo_box_path);
        combo_box.sendKeys(location);
        Thread.sleep(500);
        By my_location_path = By.xpath("//span[text() = '" + location + "']");
        waitForElementToBeEnabled(driver, my_location_path, 10);
        WebElement my_location = driver.findElement(my_location_path);
        my_location.click();
    }

//    public void inputUserDefaultsCurrentDate() throws InterruptedException {
//        Calendar calendar = Calendar.getInstance();
//        Date today = calendar.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
//        waitForElementToBeVisible(driver, input_current_date, 10);
//        String todayAsString = dateFormat.format(today);
//        click(input_current_date);
//        typeIn(input_current_date,todayAsString);
//    }
}