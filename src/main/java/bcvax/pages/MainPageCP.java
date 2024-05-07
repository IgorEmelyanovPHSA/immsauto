package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import static constansts.Header.SUPPLY_LOCATION_NAME;
import java.util.List;
import java.util.Map;

public class MainPageCP extends BasePage{
    private Tables tables;

    public MainPageCP(WebDriver driver) {
        super(driver);
        tables = new Tables(driver);
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
        scrollCenter(tab_supply_location);
        Thread.sleep(1000);
        tab_supply_location.click();
        Thread.sleep(1000);
        By supply_location_header_path = By.xpath("//lst-breadcrumbs//span[text()='Supply Locations']");
        waitForElementToBeEnabled(driver, supply_location_header_path, 10);
        return new SupplyConsolePage(driver);
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
        waitForElementToBeLocated(driver, list_view_btn_path, 30);
        WebElement list_view_btn = driver.findElement(list_view_btn_path);
        list_view_btn.click();
        Thread.sleep(500);
        By active_supply_pocation_item_path = By.xpath("//a/span[text() = 'Active Supply Locations']");
        waitForElementToBeEnabled(driver, active_supply_pocation_item_path, 10);
        System.out.println("/*---- Select Active Supply Locations --*/");
        driver.findElement(active_supply_pocation_item_path).click();
        Thread.sleep(2000);
        By search_location_field_path = By.xpath("//input[@name = 'HC_Supply_Location__c-search-input']");
        waitForElementToBeEnabled(driver, search_location_field_path, 10);
        WebElement search_location_field = driver.findElement(search_location_field_path);
        System.out.println("/*---- Locate " + supplyLocation + " --*/");
        search_location_field.sendKeys(supplyLocation);
        Thread.sleep(500);
        search_location_field.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        System.out.println("/*---- Go to " + supplyLocation + " --*/");
        tables.clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, supplyLocation));
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

    public static void clickUserDefaultsTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By user_default_tab_path = By.xpath("//a[text()='User Defaults']");
        waitForElementToBeEnabled(driver, user_default_tab_path, 10);
        WebElement element = driver.findElement(user_default_tab_path);
        element.click();
        //Try to avoid stale elelemnt exception
        Thread.sleep(2000);
    }

    public static void clickClientListTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By client_list_tab_path = By.xpath("//a[text()='Client List']");
        waitForElementToBeEnabled(driver, client_list_tab_path, 10);
        WebElement element = driver.findElement(client_list_tab_path);
        scrollCenter(driver, element);
        Thread.sleep(500);
        try {
            element.click();
        } catch(ElementClickInterceptedException ex) {
            Thread.sleep(2000);
            element.click();
        }
        //Try to avoid stale elelemnt exception
        Thread.sleep(2000);
    }

    public static void navigateToRegisterClientPage(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By main_menu_more_btn_path = By.xpath("//button[text() = 'More']");
        waitForElementToBeEnabled(driver, main_menu_more_btn_path, 30);
        By registerBtnPath = By.xpath("//a[@class='comm-navigation__top-level-item-link js-top-level-menu-item linkBtn' and text()='Register']");
        try {
            waitForElementToBeEnabled(driver, registerBtnPath, 10);
            WebElement register_btn = driver.findElement(registerBtnPath);
            register_btn.click();
        } catch(ElementClickInterceptedException ex) {
            System.out.println("Tried to click Register Tab " + ex.getMessage());
            Thread.sleep(3000);
            WebElement register_btn = driver.findElement(registerBtnPath);
            register_btn.click();
        } catch(Exception ex) {
            System.out.println("Tried to click Register Tab " + ex.getMessage());
            WebElement main_menu_more_btn = driver.findElement(main_menu_more_btn_path);
            main_menu_more_btn.click();
            Thread.sleep(2000);
            By submenu_register_item_path = By.xpath("//a[@title = 'Register']");
            waitForElementToBeEnabled(driver, submenu_register_item_path, 10);
            WebElement sub_menu_register = driver.findElement(submenu_register_item_path);
            sub_menu_register.click();
        }
        Thread.sleep(2000);
    }

    public static void search(WebDriver driver, String criteria) throws InterruptedException {
        Thread.sleep(500);
        By search_field_path = By.xpath("//input[@class='search-input search-input--left']");
        waitForElementToBeEnabled(driver, search_field_path, 10);
        WebElement search_field = driver.findElement(search_field_path);
        search_field.clear();
        Thread.sleep(1000);
        search_field.sendKeys(criteria);
        Thread.sleep(500);
        search_field.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        By table_path = By.xpath("//a[text()='Profiles']/../../../../..//table[@data-aura-class='uiVirtualDataGrid--default uiVirtualDataGrid']");

        //In case not found

        int attempt = 0;
        while(attempt < 10) {
            try {
                waitForElementToBeEnabled(driver, table_path, 10);
                System.out.println("Table is found");
                break;
            } catch (Exception ex) {
                System.out.println("-------------");
                System.out.println("Search Attempt #" + attempt);
                System.out.println(ex.getMessage());
                System.out.println("-------------");
                boolean not_found_screen = clientNotFoundScreenDisplayed(driver);
                if(not_found_screen) {
                    search_field = driver.findElement(search_field_path);
                    search_field.clear();
                    Thread.sleep(1000);
                    search_field.sendKeys(criteria);
                    Thread.sleep(500);
                    search_field.sendKeys(Keys.ENTER);
                    Thread.sleep(500);
                    search_field = driver.findElement(search_field_path);
                    search_field.clear();
                    Thread.sleep(1000);
                    search_field.sendKeys(criteria);
                    Thread.sleep(500);
                    search_field.sendKeys(Keys.ENTER);
                    Thread.sleep(500);
                }
                attempt++;
            }
        }
        WebElement found_client_table_node = driver.findElement(table_path);

        GenericTable found_client_table = new GenericTable(found_client_table_node);
        List<Map<String, WebElement>> my_records = found_client_table.getRowsMappedToHeadings();
        int row_count = my_records.size();
        int loop_count = 0;
        while(row_count < 2) {
            System.out.println("-----------");
            System.out.println("Found Client rows: " + row_count);
            if(row_count < 2) {
                boolean not_found_screen = clientNotFoundScreenDisplayed(driver);
                if(not_found_screen) {
                    search_field = driver.findElement(search_field_path);
                    search_field.clear();
                    Thread.sleep(1000);
                    search_field.sendKeys(criteria);
                    Thread.sleep(500);
                    search_field.sendKeys(Keys.ENTER);
                    Thread.sleep(500);
                    search_field = driver.findElement(search_field_path);
                    search_field.clear();
                    Thread.sleep(1000);
                    search_field.sendKeys(criteria);
                    Thread.sleep(500);
                    search_field.sendKeys(Keys.ENTER);
                    Thread.sleep(500);
                }
            }
            System.out.println("-----------");
            Thread.sleep(1000);
            waitForElementToBeEnabled(driver, table_path, 10);
            found_client_table_node = driver.findElement(table_path);
            found_client_table = new GenericTable(found_client_table_node);
            my_records = found_client_table.getRowsMappedToHeadings();
            row_count = my_records.size();
            loop_count++;
            if(loop_count > 10) {
                break;
            }
        }
        for(Map<String, WebElement> my_record: my_records) {
            try {
                    my_record.get("Email").findElement(By.xpath(".//a"));
                    WebElement my_link = my_record.get("Name");
                    my_link.click();
                    break;
            } catch(Exception ex) {
                ;
            }
        }
    }

    public static boolean clientNotFoundScreenDisplayed(WebDriver driver) {
        By not_found_message_path = By.xpath("//div[@data-aura-class='forceSearchNoResults']");
        try {
            waitForElementToBeEnabled(driver, not_found_message_path, 2);
            return true;
        } catch(Exception ex) {
            System.out.println("---THERE IS NO Not Found ERROR---");
            return false;
        }
    }

    public void logout() throws InterruptedException {
        Thread.sleep(500);
        By profile_menu_btn_path = By.xpath("//community_user-user-profile-menu//button");
        waitForElementToBeEnabled(driver, profile_menu_btn_path, 10);
        WebElement profile_menu_button = driver.findElement(profile_menu_btn_path);
        profile_menu_button.click();
        Thread.sleep(500);
        By logout_link_path = By.xpath("//community_user-user-profile-menu//span[@title='Log Out']/..");
        waitForElementToBeEnabled(driver, logout_link_path, 10);
        WebElement logout_link = driver.findElement(logout_link_path);
        try {
            logout_link.click();
        } catch(ElementNotInteractableException ex) {
            profile_menu_button.click();
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, logout_link_path, 10);
            logout_link = driver.findElement(logout_link_path);
            logout_link.click();
        } catch(StaleElementReferenceException ex) {
            Thread.sleep(2000);
            waitForElementToBeEnabled(driver, logout_link_path, 10);
            logout_link = driver.findElement(logout_link_path);
            logout_link.click();
        }
    }
}
