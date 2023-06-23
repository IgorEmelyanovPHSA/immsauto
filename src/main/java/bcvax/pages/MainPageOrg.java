package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;
import org.apache.commons.lang3.StringEscapeUtils;


public class MainPageOrg extends BasePage {
    @FindBy(xpath = "//h3[text()='Apps']")
    private WebElement appsLauncherHeader;

    @FindBy(xpath = "//span[text()='App Launcher']")
    private WebElement appsLauncher;

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
                String currentApp = currentApp();
                while(!currentApp.equals(app)) {
                    currentApp = currentApp();
                    Thread.sleep(200);
                }
            }
        }
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        if(windows.size() > 1) {
            driver.switchTo().window(windows.get(1));
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
        driver.findElement(By.xpath("//span[@search_dialog-instantresultitem_instantresultitem and @title='" + search_value + "']")).click();
        //search_input.sendKeys(Keys.RETURN);
    }
}
