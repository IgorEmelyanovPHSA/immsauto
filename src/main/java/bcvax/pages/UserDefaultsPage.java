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

    @FindBy(xpath = "//div[contains(text(),'Advanced Settings')]")
    private WebElement btnAdvancedSettingsUserDefaults;

    @FindBy(xpath = "(//span[@title='Agent']/../../../../../../..//span[@class='slds-grid slds-grid_align-spread'])[1]")
    private WebElement agentUserDefaults;

    @FindBy(xpath = "//span[@title='Lot#']/../../../../../../..//input[@class='slds-input slds-combobox__input slds-combobox__input-value combobox-input-class']")
    private WebElement lotUserDefaults;

    @FindBy(xpath = "(//span[@title='Trade Name']/../../../../../../..//span[@class='slds-grid slds-grid_align-spread'])[2]")
    private WebElement tradeNameUserDefaults;

//    @FindBy(xpath = "//input[@name='BCH_Date__c']")
//    private WebElement input_current_date;

    @FindBy(xpath = "//label[contains(text(),'Clinic Location')]/..//div[@role='none']//input[@type='text' and @role='textbox']")
    private WebElement clinicLocationUserDefaults;

    @FindBy(xpath = "//button[@name='DeleteLot']")
    private WebElement btnDeleteLot;

    @FindBy(xpath = "//button [@class='slds-button' and @title='Add']")
    private WebElement btnAddNew;

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement btnSave;

    private By toastMessageSuccess = By.xpath("//span[contains(text(),'User defaults successfully updated.')]");


    @Step
    public UserDefaultsPage clickOnAdvancedSettings() throws InterruptedException {
        click(btnAdvancedSettingsUserDefaults);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
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
        //defaultSite = "Arm - Left deltoid";
        int count = 1;
        for(int i=0; i < lots.length; i ++){
            click(btnAddNew);
            WebElement drpDownLot = driver.findElement(By.xpath("(//span[@title='Lot#']/../../../../../../..//input[@class='slds-input slds-combobox__input slds-combobox__input-value combobox-input-class'])["+ count +"]"));
            click(drpDownLot);
            Thread.sleep(500);

            WebElement textEditableLot = driver.findElement(By.xpath("(//lightning-primitive-cell-factory[@data-label='Lot#']//input[@class='slds-input search-input-class' and @type='text'])["+ count +"]"));
            typeInWithoutClear(textEditableLot, lots[i]);
            Thread.sleep(500);

            WebElement textConfirmSelection = driver.findElement(By.xpath("(//lightning-primitive-cell-factory[@data-label='Lot#']//input[@class='slds-input search-input-class' and @type='text'])["+ count +"]/../../..//span[contains(text(), '"+ lots[i] +"')]"));
            click(textConfirmSelection);
            Thread.sleep(500);

            WebElement drpDownSiteLastSelected = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux' and@type='button']"));
            click(drpDownSiteLastSelected);

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

//    public void inputCurrentDateUserDefaults() throws InterruptedException {
//        Calendar calendar = Calendar.getInstance();
//        Date today = calendar.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
//        waitForElementToBeVisible(driver, input_current_date, 10);
//        String todayAsString = dateFormat.format(today);
//        click(input_current_date);
//        typeIn(input_current_date,todayAsString);
//        clickBtnSaveWithSuccessMsgValidation();
//    }

    public void inputCurrentDateUserDefaults() throws InterruptedException {
        Thread.sleep(500);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        By input_current_date_path = By.xpath("//input[@name='BCH_Date__c']");
        waitForElementToBeEnabled(driver, input_current_date_path, 10);
        String todayAsString = dateFormat.format(today);
        WebElement input_current_date = driver.findElement(input_current_date_path);

        try {
            input_current_date.click();
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, input_current_date_path, 10);
            input_current_date = driver.findElement(input_current_date_path);
            input_current_date.isEnabled();
        } catch(StaleElementReferenceException ex) {
            System.out.println("***DEBUG*** Stale element exception ***");
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, input_current_date_path, 10);
            input_current_date = driver.findElement(input_current_date_path);
        }
        input_current_date.clear();
        input_current_date.click();
        Thread.sleep(2000);
        input_current_date.sendKeys(todayAsString);
        Thread.sleep(500);
        input_current_date.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        closeSuccessDialog();
        Thread.sleep(500);
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
        Thread.sleep(2000);
        driver.findElement(By.xpath("//c-bc-hc-input-search-drop-down//input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//ul[@class='slds-listbox slds-listbox_vertical']")).sendKeys(location);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text() = '" + location + "']")).click();
        Thread.sleep(1000);
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