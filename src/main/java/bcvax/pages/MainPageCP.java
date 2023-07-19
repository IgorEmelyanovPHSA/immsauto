package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import static constansts.Header.SUPPLY_LOCATION_NAME;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

public class MainPageCP extends BasePage{

    public MainPageCP(WebDriver driver) { super(driver);}

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand' and contains(text(),'Camera')]")
    private WebElement btnScanUsingCamera;

    @FindBy(xpath = "//a[text()='Profiles']")
    private WebElement tabProfiles;

    @FindBy(xpath = "//a[@title='Age 12 and Above - Abbotsford - Abby Pharmacy' and contains(@href, 's/hc-supply-location')]")
    private WebElement supplyLocationNameAbby;

    @FindBy(xpath = "//a[@title='Automation Supply Location_1' and contains(@href, 's/hc-supply-location')]")
    private WebElement automationSupplyLocation_1;

    @FindBy(xpath = "//span[@class='title' and text()='Related Items']")
    private WebElement tabRelatedItems;

    @FindBy(xpath = "//a[@title = 'All Client']")
    private WebElement sub_menu_AllClients;

    @FindBy(xpath = "//a[@title = 'Profiles']")
    private WebElement sub_menu_profiles;

    @FindBy(xpath = "//a[text() = 'Participants']")
    private WebElement main_menu_btn_Participants;

    @FindBy(xpath = ".//button[text()='Save']")
    private WebElement click_save_defaults_button;
    private By click_save_defaults_button_ = By.xpath(".//button[text()='Save']");

    @FindBy(xpath = "//a[@title = 'Register']")
    private WebElement sub_menu_Register;

    @FindBy(xpath = "//a[@title = 'Submit Requisition']")
    private WebElement submitRequisitionButton;
    private By submit_requisition_button = By.xpath("//a[@title = 'Submit Requisition']");
    @FindBy(xpath = "//a[@title = 'Request Supplies']")
    private WebElement request_supplies;

    @FindBy(xpath = "//button[text() = 'Go to User Defaults']")
    private WebElement go_to_user_defaults_btn;
    private By request_supplies_1 = By.xpath("//a[@title = 'Request Supplies']");

    @FindBy(xpath = "//div[@aria-modal='true']")
    private WebElement modal_dialog;

    @FindBy(xpath = "//input[@placeholder = 'Search...']")
    private WebElement searchAssistant;

    @FindBy(xpath = "//input[@placeholder = 'Search...']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@title='Select a List View']")
    private WebElement select_list_view_btn;

    @FindBy(xpath = "//input[@name = 'HC_Supply_Location__c-search-input']")
    private WebElement search_location_field;

    @FindBy(xpath = "//table[@data-aura-class='uiVirtualDataGrid--default uiVirtualDataGrid']")
    private WebElement participantsTable;

    public void verifyYouAreOnTheMainPageCP(){

    }

    public SupplyConsolePage navigateToSupplyConsolePage() throws InterruptedException {
        log("/----Go to Supply Locations Tab --*/");
        goToSupplyLocation();
        click(automationSupplyLocation_1);
        selectRelatedTab();
        Thread.sleep(2000);
        return new SupplyConsolePage(driver);
    }

    @Step
    public SupplyConsolePage goToSupplyLocation() throws InterruptedException {
        Thread.sleep(500);
        By tab_supply_location_path = By.xpath("//a[text()='Supply Locations']");
        System.out.println("/*----Locate Dropdown Menu --*/");
        try {
            waitForElementToBeEnabled(driver, tab_supply_location_path, 30);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("--- Try again... ---");
            driver.navigate().refresh();
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, tab_supply_location_path, 30);
        }
        WebElement tab_supply_location = driver.findElement(tab_supply_location_path);
        scrollTop(tab_supply_location);
        tab_supply_location.click();
        return new SupplyConsolePage(driver);
    }

    @Step
    public MainPageCP selectRelatedTab() throws InterruptedException {
        waitForElementToBeVisible(driver, tabRelatedItems, 10);
        moveToElement(tabRelatedItems);
        click(tabRelatedItems);
        return this;
    }

//    public ProfilesPage navigateToProfilesPage() throws InterruptedException {
//        Thread.sleep(2000);
//        if (tabProfiles.isDisplayed()) {
//            tabProfiles.click();
//        } else {
//            waitForElementToBeClickable(main_menu_btn_More);
//            Thread.sleep(2000);
//            click(main_menu_btn_More);
//            Thread.sleep(2000);
//            sub_menu_profiles.click();
//            Thread.sleep(2000);
//        }
//        return new ProfilesPage(driver);
//    }

    public ProfilesPage globalSearch_CP(String textToSearch) throws InterruptedException {
        waitForElementToBeVisible(driver, searchAssistant, 10);
        click(searchAssistant);
        waitForElementToBeVisible(driver, searchInput, 10);
        typeIn(searchInput,textToSearch);
        searchInput.sendKeys(Keys.RETURN);
        Thread.sleep(5000);
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
        new Tables(driver).clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, location));
        return supplyConsolePage;
    }

    public SupplyConsolePage selectSupplyLocationName(String supplyLocation) throws InterruptedException {
        SupplyConsolePage supplyConsolePage = goToSupplyLocation();
        System.out.println("/*-- Choose List View --*/");
        Thread.sleep(2000);
        By list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Locations']");
        waitForElementToBeLocated(driver, list_view_btn_path, 10);
        WebElement list_view_btn = driver.findElement(list_view_btn_path);
        list_view_btn.click();
        Thread.sleep(500);
        By active_supply_pocation_item_path = By.xpath("//a/span[text() = 'Active Supply Locations']");
        waitForElementToBeEnabled(driver, active_supply_pocation_item_path, 10);
        System.out.println("/*---- Select Active Supply Locations --*/");
        driver.findElement(active_supply_pocation_item_path).click();
        Thread.sleep(2000);
        System.out.println("/*---- Locate " + supplyLocation + " --*/");
        search_location_field.sendKeys(supplyLocation);
        search_location_field.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println("/*---- Go to " + supplyLocation + " --*/");
        new Tables(driver).clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, supplyLocation));
        return supplyConsolePage;
    }

    public void verifyIsCommunityPortalHomePageDisplayed() throws InterruptedException{
        Thread.sleep(500);
        By client_search_label_path = By.xpath("//div/h1[text()='Client Search']");
        try {
            waitForElementToBeLocated(driver, client_search_label_path, 30);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            driver.navigate().refresh();
            waitForElementToBeLocated(driver, client_search_label_path, 30);
        }
        WebElement client_search_label = driver.findElement(client_search_label_path);
        client_search_label.isDisplayed();
    }

    public UserDefaultsPage clickUserDefaultsTab() throws InterruptedException {
        Thread.sleep(500);
        By user_default_tab_path = By.xpath("//a[text()='User Defaults']");
        waitForElementToBeEnabled(driver, user_default_tab_path, 10);
        WebElement element = driver.findElement(user_default_tab_path);
        element.click();
        //Try to avoid stale elelemnt exception
        Thread.sleep(2000);
        return new UserDefaultsPage(driver);
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
    public void clickGoToUserDefaultsButton() throws InterruptedException {
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        Thread.sleep(2000);
        try {
            waitForElementToBeVisible(driver, modal_dialog, 20);
            //driver.findElement(By.xpath("//div[@aria-modal='true']")).isDisplayed();
            waitForElementToBeVisible(driver, go_to_user_defaults_btn, 10);
            go_to_user_defaults_btn.click();
            Thread.sleep(2000);
        } catch(Exception ex) {
            System.out.println("The Modal Dialog not thrown");
            Thread.sleep(2000);
        }
    }
    public InClinicExperiencePage navigateToRegisterClientPage() throws InterruptedException {
        Thread.sleep(500);
        By main_menu_more_btn_path = By.xpath("//button[text() = 'More']");
        waitForElementToBeEnabled(driver, main_menu_more_btn_path, 30);
        By registerBtnPath = By.xpath("//a[@class='comm-navigation__top-level-item-link js-top-level-menu-item linkBtn' and text()='Register']");
        try {
            waitForElementToBeEnabled(driver, registerBtnPath, 10);
            WebElement register_btn = driver.findElement(registerBtnPath);
            register_btn.click();
        } catch(ElementClickInterceptedException ex) {
            Thread.sleep(3000);
            WebElement register_btn = driver.findElement(registerBtnPath);
            register_btn.click();
        } catch(Exception ex) {
            WebElement main_menu_more_btn = driver.findElement(main_menu_more_btn_path);
            click(main_menu_more_btn);
            Thread.sleep(2000);
            waitForElementToBeClickable(sub_menu_Register);
            Thread.sleep(2000);
            click(sub_menu_Register);
        }
        Thread.sleep(2000);
        return new InClinicExperiencePage(driver);
    }

    public void clickRequestSupplies() throws InterruptedException {
        waitForElementToBeLocated(driver, request_supplies_1, 10);
        WebElement element = driver.findElement(request_supplies_1);
        request_supplies.click();
    }

    public void clickSubmitRequisition() throws InterruptedException {
        waitForElementToBeLocated(driver, submit_requisition_button, 10);
        submitRequisitionButton.click();
    }

    public void search(String criteria) throws InterruptedException {
        Thread.sleep(500);
        By search_field_path = By.xpath("//input[@class='search-input search-input--left']");
        waitForElementToBeEnabled(driver, search_field_path, 10);
        WebElement search_field = driver.findElement(search_field_path);
        search_field.sendKeys(criteria);
        Thread.sleep(500);
        search_field.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        By table_path = By.xpath("//table[@data-aura-class='uiVirtualDataGrid--default uiVirtualDataGrid']");
        waitForElementToBeEnabled(driver, table_path, 60);
    }
    public void refreshBrowser() throws InterruptedException {
        driver.navigate().refresh();
    }
}
