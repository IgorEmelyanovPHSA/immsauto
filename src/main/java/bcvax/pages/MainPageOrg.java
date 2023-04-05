package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;


public class MainPageOrg extends BasePage {
    @FindBy(xpath = "//div[@class='appName slds-context-bar__label-action slds-context-bar__app-name'] | //span[@class='appName slds-context-bar__label-action slds-context-bar__app-name']/span")
    private WebElement currentApp;

    @FindBy(xpath = "//h3[text()='Apps']")
    private WebElement appsLauncherHeader;

    @FindBy(xpath = "//span[text()='App Launcher']")
    private WebElement appsLauncher;

    public MainPageOrg(WebDriver driver) {
        super(driver);
    }
    public String currentApp() throws InterruptedException {
        int timeout = 30000;
        boolean found = false;
        Instant start = Instant.now();
        Instant end = Instant.now();
        while(!found) {
            try {
                found = currentApp.isDisplayed();
            } catch (NotFoundException ex) {
                end = Instant.now();
                if(Duration.between(start, end).toMillis() > timeout) {
                    break;
                }
                Thread.sleep(200);
            }
        }
        return currentApp.getText();
    }

    public void switchApp(String app) throws InterruptedException {
        waitForElementToBeVisible(driver, appsLauncher, 60);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()='App Launcher']/..")).click();
        Thread.sleep(3000);
        waitForElementToBeVisible(driver, appsLauncherHeader, 30);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Search apps and items...']")).sendKeys(app);
        Thread.sleep(3000);
        List<WebElement> apps = driver.findElements(By.xpath("//div[@class='al-menu-dropdown-list']//a"));
        for(WebElement appElement : apps) {
            if(appElement.getAttribute("data-label").equals(app)) {
                WebElement myApp = appElement.findElement(By.xpath("./.."));
                myApp.click();
            }
        }
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        if(windows.size() > 1) {
            driver.switchTo().window(windows.get(1));
        }

    }
}
