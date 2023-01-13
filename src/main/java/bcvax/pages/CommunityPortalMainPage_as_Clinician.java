package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static Constansts.Header.SUPPLY_LOCATION_NAME_;

public class CommunityPortalMainPage_as_Clinician extends BasePage{

    /*---------Properties-------*/
    @FindBy(xpath = "//div/h1[text()='Client Search']")
    private WebElement community_portal_home_page_displayed;

    @FindBy(xpath = ".//a[text()='User Defaults']")
    private WebElement user_defaults_main_menu;
    private By user_defaults_main_menu1 = By.xpath(".//a[text()='User Defaults']");

    @FindBy(xpath = "//input[@name='BCH_Date__c']")
    private WebElement input_current_date;
    private By input_current_date1 = By.xpath("//input[@name='BCH_Date__c']");

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement click_save_defaults_button;
    private By click_save_defaults_button_ = By.xpath(".//button[text()='Save']");

    @FindBy(xpath = "//button[text() = 'More']")
    private WebElement main_menu_btn_More;

    @FindBy(xpath = "//a[@title = 'Register']")
    private WebElement sub_menu_Register;

    /*---------Constructor-------*/
    public CommunityPortalMainPage_as_Clinician(WebDriver driver) { super(driver);}

    /*-------------Methods--------------*/

    public void verifyIsCallCenterConsolePageDisplayed() {
        waitForElementToBeVisible(driver, community_portal_home_page_displayed, 10);
        community_portal_home_page_displayed.isDisplayed();
    }

    public void clickUserDefaultsTab() throws InterruptedException {
        waitForElementToBeLocated(driver, user_defaults_main_menu1, 10);
        WebElement element = driver.findElement(user_defaults_main_menu1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void inputCurrentDateUserDefaults() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        waitForElementToBeVisible(driver, input_current_date, 10);
        String todayAsString = dateFormat.format(today);
        input_current_date.click();
        Thread.sleep(2000);
        input_current_date.clear();
        Thread.sleep(2000);
        input_current_date.sendKeys(todayAsString);
        Thread.sleep(2000);
        input_current_date.sendKeys(Keys.ENTER);
    }

    public void clickSaveDefaultsButton() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, click_save_defaults_button, 10);
        WebElement element = driver.findElement(click_save_defaults_button_);
        click_save_defaults_button.click();
    }

    public InClinicExperiencePage navigateToRegisterClientPage() throws InterruptedException {
        waitForElementToBeClickable(main_menu_btn_More);
        Thread.sleep(2000);
        click(main_menu_btn_More);
        Thread.sleep(2000);
        waitForElementToBeClickable(sub_menu_Register);
        Thread.sleep(2000);
        click(sub_menu_Register);
        Thread.sleep(2000);
        return new InClinicExperiencePage(driver);
    }



}
