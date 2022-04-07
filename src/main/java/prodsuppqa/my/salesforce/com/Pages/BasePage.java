package prodsuppqa.my.salesforce.com.Pages;
// All Pages are inheriting from this class
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void waitForElementToAppear(By locator) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement find (By locator) {
        return driver.findElement(locator);
    }

    protected void type (String text, By locator){
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected void click (By locator){
        find(locator).click();
    }

    protected Boolean isDisplayed (By locator){
        try {
            return find(locator).isDisplayed();
        }
        catch (NoSuchElementException exc){
            return false;
        }
    }

    // explicit wait - to wait for the compose button to be click-able
    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement webElement, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
        return element;
    }

    protected static WebElement waitForElementToBeLocated(WebDriver driver, By xpath, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        return element;
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement webElement, int seconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return element;
    }






}
