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
    @FindBy(xpath = "//h3[text()='Apps']")
    private WebElement appsLauncherHeader;

    @FindBy(xpath = "//span[text()='App Launcher']")
    private WebElement appsLauncher;
    Tables tables;
    public MainPageOrg(WebDriver driver) {
        super(driver);
    }
    public String currentApp() throws InterruptedException {
        Thread.sleep(500);
        By current_app_path = By.xpath("//div[@class='appName slds-context-bar__label-action slds-context-bar__app-name'] | //span[@class='appName slds-context-bar__label-action slds-context-bar__app-name']/span");
        waitForElementToBeEnabled(driver, current_app_path, 30);
        WebElement current_app = driver.findElement(current_app_path);
        int timeout = 30000;
        boolean found = false;
        Instant start = Instant.now();
        Instant end = Instant.now();
        while(!found) {
            try {
                found = current_app.isDisplayed();
                System.out.println("Current App found");
                System.out.println(end.toString());
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
                if(Duration.between(start, end).toMillis() > timeout) {
                    throw new NotFoundException("Current APP tab not found");
                }
                Thread.sleep(200);
            }
        }
        return current_app.getText();
    }

    public void switchApp(String app) throws InterruptedException {
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, appsLauncher, 60);
        driver.findElement(By.xpath("//span[text()='App Launcher']/..")).click();
        waitForElementToBeVisible(driver, appsLauncherHeader, 30);
        driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys(app);
        Thread.sleep(2000);
        List<WebElement> apps = driver.findElements(By.xpath("//div[@class='al-menu-dropdown-list']//a"));
        for(WebElement appElement : apps) {
            if(StringEscapeUtils.unescapeHtml4(appElement.getAttribute("data-label")).equals(app)) {
                WebElement myApp = appElement.findElement(By.xpath("./.."));
                myApp.click();
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
    }

    public void closeAllTabs() throws InterruptedException {
        Thread.sleep(2000);
        waitForElementToBeLocated(driver, By.xpath("//div[@role='tablist']"), 30);
        Thread.sleep(5000);
        List<WebElement> closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
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
        By search_field_path = By.xpath("//input[@lightning-input_input and @type='search']");
        waitForElementToBeEnabled(driver, search_field_path, 10);
        WebElement search_input = driver.findElement(search_field_path);
        search_input.sendKeys(search_value);
        Thread.sleep(500);
        By found_client_path = By.xpath("//span[@search_dialog-instantresultitem_instantresultitem and @title=\"" + search_value + "\"]");
        waitForElementToBeEnabled(driver, found_client_path, 10);
        WebElement found_client = driver.findElement(found_client_path);
        scrollTop(found_client);
        Thread.sleep(500);
        found_client.click();
    }

    public void logout() throws InterruptedException {
        By logout_link_path = By.xpath("//a[@href='/secur/logout.jsp']");
        WebElement logout_link = driver.findElement(logout_link_path);
        logout_link.click();
    }
}
