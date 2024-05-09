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

    @Step
    public static void clickOnAdvancedSettings(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By advanced_settings_btn_path  = By.xpath("//div[contains(text(),'Advanced Settings')]");
        waitForElementToBeEnabled(driver, advanced_settings_btn_path, 10);
        WebElement advanced_settings_btn = driver.findElement(advanced_settings_btn_path);
        try {
            advanced_settings_btn.click();
        } catch(ElementClickInterceptedException ex) {
            Thread.sleep(2000);
            waitForElementToBeEnabled(driver, advanced_settings_btn_path, 10);
            advanced_settings_btn.click();
        }
    }

    public static void clickBtnSave(WebDriver driver) throws InterruptedException {
            Thread.sleep(500);
            By save_defaults_button_path = By.xpath("//button[text()='Save']");
            waitForElementToBeEnabled(driver, save_defaults_button_path, 10);
            WebElement save_defaults_button = driver.findElement(save_defaults_button_path);
            scrollCenter(driver, save_defaults_button);
            Thread.sleep(500);
            save_defaults_button.click();
            Thread.sleep(500);
            AlertDialog.closeAllAlerts(driver);
    }

    public static void clickBtnSaveWithSuccessMsgValidation(WebDriver driver) throws InterruptedException {
        clickBtnSave(driver);
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

    public static void deleteAllLotsIfAnyHasBeenSavedPreviously(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By delete_lot_button_path = By.xpath("//button[@name='DeleteLot']");
        waitForElementToBeEnabled(driver, delete_lot_button_path, 10);
        List<WebElement> delete_lot_buttons = driver.findElements(delete_lot_button_path);
        for(WebElement delete_lot_button: delete_lot_buttons) {
            delete_lot_button.click();
        }
    }

    public static void populateLotsAndSite(WebDriver driver, String [] lots) throws InterruptedException {
        for(int i=0; i < lots.length; i ++){
            By add_new_btn_path = By.xpath("//button [@class='slds-button' and @title='Add']");
            waitForElementToBeEnabled(driver, add_new_btn_path, 10);
            WebElement add_new_btn = driver.findElement(add_new_btn_path);
            scrollCenter(driver, add_new_btn);
            Thread.sleep(500);
            add_new_btn.click();
            By lot_dropdown_btn_path = By.xpath("//span[@title='Lot#']/../../../../../../..//input[@class='slds-input slds-combobox__input slds-combobox__input-value combobox-input-class']");
            waitForElementToBeEnabled(driver, lot_dropdown_btn_path, 10);
            WebElement drpDownLot = driver.findElements(lot_dropdown_btn_path).get(i);
            drpDownLot.click();
            Thread.sleep(500);

            By lot_edit_field_path = By.xpath("(//lightning-primitive-cell-factory[@data-label='Lot#']//input[@class='slds-input search-input-class' and @type='text'])");
            waitForElementToBeEnabled(driver, lot_edit_field_path, 10);
            WebElement textEditableLot = driver.findElements(lot_edit_field_path).get(i);
            textEditableLot.sendKeys(lots[i]);
            Thread.sleep(500);
            By my_lot_path = By.xpath("(//lightning-primitive-cell-factory[@data-label='Lot#']//input[@class='slds-input search-input-class' and @type='text'])");
            waitForElementToBeEnabled(driver, my_lot_path, 10);
            WebElement textConfirmSelection = driver.findElements(my_lot_path).get(i).findElement(By.xpath("./../../..//span[contains(text(), '"+ lots[i] +"')]"));
            textConfirmSelection.click();
            Thread.sleep(500);

            By drop_down_site_path = By.xpath("//lightning-primitive-cell-factory[@data-label='Site']//button");
            waitForElementToBeEnabled(driver, drop_down_site_path, 10);
            WebElement drpDownSiteLastSelected = driver.findElements(drop_down_site_path).get(i);
            drpDownSiteLastSelected.click();
            Thread.sleep(500);
            By drop_down_site_arm_path = By.xpath("//span[@title='Arm - Left deltoid']");
            waitForElementToBeEnabled(driver, drop_down_site_arm_path, 10);
            WebElement dropDownSiteArmLeftDeltoid = driver.findElements(drop_down_site_arm_path).get(i);
            dropDownSiteArmLeftDeltoid.click();
            Thread.sleep(500);
        }
    }

    public static boolean isToastMessageDisplayed(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By toast_message_success_path = By.xpath("//span[contains(text(),'User defaults successfully updated.')]");
        waitForElementToBeEnabled(driver, toast_message_success_path, 10);
        boolean flag = false;
        List countOfFoundLot = driver.findElements(toast_message_success_path);
        if(countOfFoundLot.size()>0){
            flag = true;
        }
        return flag;
    }

    public static boolean isAnyLotsPresent(WebDriver driver) {
        boolean flag = false;
        List countOfFoundLot = driver.findElements(By.xpath("//button[@name='DeleteLot']"));
        if(countOfFoundLot.size()>0){
            flag = true;
        }
        return flag;
    }

    public static void inputCurrentDateUserDefaults(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date today = calendar.getTime();
        inputDateUserDefaults(driver, today);
    }

    public static void inputDateUserDefaults(WebDriver driver, Date date) throws InterruptedException {
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
        input_date.sendKeys(todayAsString);
        Thread.sleep(2000);
        input_date.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        try {
            closeSuccessDialog(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("No Success Dialog. Continue...");
        }
    }

    public static void closeSuccessDialog(WebDriver driver) throws InterruptedException {
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

    public static void selectUserDefaultLocation(WebDriver driver, String location) throws InterruptedException {
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
}