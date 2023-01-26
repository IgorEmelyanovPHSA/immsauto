package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import static constansts.Header.SUPPLY_LOCATION_NAME_FULL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CommunityPortalMainPage extends BasePage{

    public CommunityPortalMainPage(WebDriver driver) { super(driver);}

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand' and contains(text(),'Camera')]")
    private WebElement btnScanUsingCamera;

    @FindBy(xpath = "//a[text()='Supply Locations']")
    private WebElement tabSupplyLocation;

    @FindBy(xpath = "//a[@title='Age 12 and Above - Abbotsford - Abby Pharmacy' and contains(@href, 's/hc-supply-location')]")
    private WebElement supplyLocationNameAbby;

    @FindBy(xpath = "//a[@title='Automation Supply Location_1' and contains(@href, 's/hc-supply-location')]")
    private WebElement automationSupplyLocation_1;

    @FindBy(xpath = "//span[@class='title' and text()='Related Items']")
    private WebElement tabRelatedItems;

    @FindBy(xpath = "//button[text() = 'More']")
    private WebElement main_menu_btn_More;

    @FindBy(xpath = "//a[@title = 'All Client']")
    private WebElement sub_menu_AllClients;

    @FindBy(xpath = "//a[@title = 'Participants']")
    private WebElement sub_menu_Participants;

    @FindBy(xpath = "//a[text() = 'Participants']")
    private WebElement main_menu_btn_Participants;

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

    @FindBy(xpath = "//a[@title = 'Register']")
    private WebElement sub_menu_Register;

    public void verifyYouAreOnTheMainPageCP(){

    }

    public SupplyConsolePage navigateToSupplyConsolePage() throws InterruptedException {
        log("/*4.----Go to Supply Locations Tab --*/");
        goToSupplyLocation();
        click(automationSupplyLocation_1);
        selectRelatedTab();
        Thread.sleep(2000);
        return new SupplyConsolePage(driver);
    }

    @Step
    public SupplyConsolePage goToSupplyLocation() throws InterruptedException {
        waitForElementToBeVisible(driver, tabSupplyLocation, 30);
        click(tabSupplyLocation);
        return new SupplyConsolePage(driver);
    }

    @Step
    public CommunityPortalMainPage selectRelatedTab() throws InterruptedException {
        waitForElementToBeVisible(driver, tabRelatedItems, 10);
        moveToElement(tabRelatedItems);
        click(tabRelatedItems);
        return this;
    }

    public ProfilesPage navigateToProfilesPage() throws InterruptedException {
        waitForElementToBeClickable(main_menu_btn_More);
        Thread.sleep(2000);
        click(main_menu_btn_More);
        Thread.sleep(2000);
        //waitForElementToBeClickable(sub_menu_AllClients);
        waitForElementToBeClickable(sub_menu_Participants);
        Thread.sleep(2000);
        //click(sub_menu_AllClients);
        click(sub_menu_Participants);
        Thread.sleep(2000);
        return new ProfilesPage(driver);
    }

    //This method is mostly used to navigate under admin role
    @Step
    public SupplyConsolePage navigateToSupplyLocationRelatedTab( String location) throws InterruptedException {
        SupplyConsolePage supplyConsolePage = navigateToSupplyLocation(location);
        selectRelatedTab();
        return supplyConsolePage;
    }
    @Step
    public SupplyConsolePage navigateToSupplyLocation( String location) throws InterruptedException {
        SupplyConsolePage supplyConsolePage = goToSupplyLocation();
        new Tables(driver).clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME_FULL, location));
        return supplyConsolePage;
    }

    public void verifyIsCommunityPortalHomePageDisplayed() {
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
