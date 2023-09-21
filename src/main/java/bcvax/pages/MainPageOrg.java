package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.Duration;
import java.time.Instant;
import org.apache.commons.lang3.StringEscapeUtils;


public class MainPageOrg extends BasePage {
    Tables tables;
    public MainPageOrg(WebDriver driver) {
        super(driver);
    }
    public String currentApp() throws InterruptedException {
        Thread.sleep(500);
        By current_app_path = By.xpath("//div[@class='appName slds-context-bar__label-action slds-context-bar__app-name'] | //span[@class='appName slds-context-bar__label-action slds-context-bar__app-name']/span");
        waitForElementToBeEnabled(driver, current_app_path, 30);
        WebElement current_app = driver.findElement(current_app_path);
        String current_app_text = new String();
        int timeout = 30000;
        boolean found = false;
        Instant start = Instant.now();
        Instant end = Instant.now();
        while(!found) {
            try {
                found = current_app.isDisplayed();
                System.out.println("Current App found");
                System.out.println(end.toString());
                current_app_text = current_app.getText();
            } catch (NotFoundException ex) {
                end = Instant.now();
                if(Duration.between(start, end).toMillis() > timeout) {
                    throw new NotFoundException("Current APP tab not found");
                }
                Thread.sleep(200);
            }
            catch (StaleElementReferenceException ex) {
                end = Instant.now();
                current_app = driver.findElement(current_app_path);
                found = current_app.isDisplayed();
                current_app_text = current_app.getText();
                if(Duration.between(start, end).toMillis() > timeout) {
                    throw new NotFoundException("Current APP tab not found");
                }
                Thread.sleep(200);
            }
        }
        return current_app_text;
    }

    public void switchApp(String app) throws InterruptedException {
        String currentAppBefore = currentApp();
        System.out.println("Current App Before: " + currentAppBefore);
        Thread.sleep(2000);
        By apps_launcher_path = By.xpath("//div[@role='navigation' and @aria-label='App']//button");
        waitForElementToBeEnabled(driver, apps_launcher_path, 60);
        WebElement app_launcher = driver.findElement(apps_launcher_path);
        app_launcher.click();
        Thread.sleep(500);
        By apps_launcher_input_path = By.xpath("//input[@placeholder='Search apps and items...']");
        waitForElementToBeEnabled(driver, apps_launcher_input_path, 10);

        WebElement app_launcher_input = driver.findElement(apps_launcher_input_path);
        app_launcher_input.sendKeys(app);
        Thread.sleep(500);
        By apps_items_path = By.xpath("//div[@class='al-menu-dropdown-list']//a");
        try {
            waitForElementToBeEnabled(driver, apps_items_path, 10);
        } catch(Exception ex) {
            app_launcher = driver.findElement(apps_launcher_path);
            app_launcher.click();
            waitForElementToBeEnabled(driver, apps_launcher_input_path, 10);
            app_launcher_input = driver.findElement(apps_launcher_input_path);
            app_launcher_input.sendKeys(app);
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, apps_items_path, 10);
        }
        List<WebElement> apps = driver.findElements(apps_items_path);
        System.out.println("Found " + apps.size() + " apps");
        for(WebElement appElement : apps) {
            if(StringEscapeUtils.unescapeHtml4(appElement.getAttribute("data-label")).equals(app)) {
                WebElement myApp = appElement.findElement(By.xpath("./.."));
                myApp.click();
                Thread.sleep(2000);
                List<String> windows = new ArrayList<String>(driver.getWindowHandles());
                if(windows.size() > 1) {
                    driver.switchTo().window(windows.get(1));
                }
                String currentApp = currentApp();
                By breadcrump_path = By.xpath("//div[@class='slds-breadcrumb__item slds-line-height--reset']/span");
                while(!currentApp.equals(app)) {
                    List<WebElement> breadcrump_list = driver.findElements(breadcrump_path);
                    if(breadcrump_list.size() > 1) {
                        String breadcrump_text = breadcrump_list.get(1).getText();
                        if (breadcrump_text.equals(app)) {
                            break;
                        }
                    }
                    currentApp = currentApp();
                    Thread.sleep(200);
                }
            }
        }
        String currentAppAfter = currentApp();
        System.out.println("Current App After: " + currentAppAfter);
    }

    public void closeAllTabs() throws InterruptedException {
        Thread.sleep(500);
        waitForElementToBeLocated(driver, By.xpath("//div[@role='tablist']"), 30);
        List<WebElement> closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
        int counter = 0;
        while(closeButtons.size() < 1) {
            Thread.sleep(1000);
            closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
            counter++;
            if(counter > 5) {
                break;
            }
        }
        for(WebElement closeTabBtn : closeButtons) {
            try {
                closeTabBtn.click();
                Thread.sleep(2000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeLastTab() throws InterruptedException {
        Thread.sleep(500);
        waitForElementToBeLocated(driver, By.xpath("//div[@role='tablist']"), 30);
        By hca_tabs_path = By.xpath("//div[@role='tablist']/ul[@role='presentation' and @class='tabBarItems slds-grid']/li[@role='presentation']/div[@class='close slds-col--bump-left slds-p-left--none slds-context-bar__icon-action ']/button");
        List<WebElement> closeButtons = driver.findElements(hca_tabs_path);
        int my_last_button = closeButtons.size() - 1;
        closeButtons.get(my_last_button).click();
    }

    public void globalSearch(String search_value) throws InterruptedException {
        Thread.sleep(500);
        By search_btn_path = By.xpath("//button[@aria-label = 'Search']");
        waitForElementToBeEnabled(driver, search_btn_path, 10);
        WebElement search_btn = driver.findElement(search_btn_path);
        search_btn.click();
        Thread.sleep(500);
        By search_field_path = By.xpath("//div[@class='forceSearchAssistantDialog']//input[@type='search']");
        waitForElementToBeEnabled(driver, search_field_path, 10);
        WebElement search_input = driver.findElement(search_field_path);
        try {
            search_input.sendKeys(search_value);
            //Thread.sleep(500);
            //search_input.sendKeys(Keys.ENTER);
        } catch(ElementNotInteractableException ex) {
            //////Retry
            Thread.sleep(2000);
            search_input.sendKeys(search_value);
            //Thread.sleep(500);
            //search_input.sendKeys(Keys.ENTER);
        }
        Thread.sleep(500);
        By found_client_path = By.xpath("//search_dialog-instant-result-item//span[@title=\"" + search_value + "\"]");
        waitForElementToBeEnabled(driver, found_client_path, 10);
        WebElement found_client = driver.findElement(found_client_path);
        scrollTop(found_client);
        Thread.sleep(500);
        try {
            found_client.click();
        } catch(ElementNotInteractableException ex) {
            //Re-try after 2 seconds
            Thread.sleep(2000);
            found_client.click();
        }
    }

    public void logout() throws InterruptedException {
        By logout_link_path = By.xpath("//a[@href='/secur/logout.jsp']");
        WebElement logout_link = driver.findElement(logout_link_path);
        logout_link.click();
    }
}
