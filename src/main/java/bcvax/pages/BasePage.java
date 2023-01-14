package bcvax.pages;
// All Pages are inheriting from this class

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public abstract class BasePage<T> {
	protected WebDriver driver;
	public WebDriverWait wait;
	
	public final static SimpleDateFormat LOG_TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	protected void waitForElementToAppear(By locator) {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}
	
	protected void type(String text, By locator) {
		find(locator).clear();
		find(locator).sendKeys(text);
	}
	
	protected void click(By locator) {
		find(locator).click();
	}
	
	protected Boolean isDisplayed(By locator) {
		try {
			return find(locator).isDisplayed();
		} catch (NoSuchElementException exc) {
			return false;
		}
	}
	public void hardWait(int sec) {
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public Boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException exc) {
			return false;
		}
	}

	public Boolean isClickable(WebElement element){
		try{
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public Boolean isInputActive(WebElement element){
		//This method to be used ONLY when isClickable method is not successful
		try{
			scrollTop(element);
			click(element);
			element.sendKeys("T");
			element.sendKeys(Keys.BACK_SPACE);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	// explicit wait - to wait for the compose button to be click-able
	public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement webElement, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element;
	}

	public static void waitForElementToBePresent(WebDriver driver, By xpath, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
	}
	
	protected static WebElement waitForElementToBeLocated(WebDriver driver, By xpath, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
		return element;
	}
	protected static void waitForElementNotToBeVisible(WebDriver driver, By xpath, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
	}
	
	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement webElement, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
		return element;
	}
	
	public String getValue(WebElement element) {
		return element.getAttribute("value");
	}

	public boolean isElementPresent(WebElement element){
		boolean isPresent;
		try {
			element.getTagName();
			isPresent = true;
		}catch (NoSuchElementException | StaleElementReferenceException e){
			isPresent = false;
		}
		return isPresent;
	}
	
	public T click(WebElement element) throws InterruptedException {
		waitForVisibility(element);
		waitForElementToBeClickable(element);
		element.click();
		return (T) this;
	}

	public T clickUsingJS(WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return (T) this;
	}

	public T typeIn(WebElement element, String text) {
		waitForVisibility(element);
		element.clear();
		element.sendKeys(text);
		return (T) this;
	}
	
	public T waitForElementToBeClickable(WebElement element) throws InterruptedException {
		int tries = 0;
		
		while (tries < 5) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				return (T) this;
			} catch (StaleElementReferenceException e) {
				log("StaleElementReferenceException occurred while waiting for element to be clickable: " + e.getMessage());
				Thread.sleep(1000);
			}
			tries = tries + 1;
		}
		
		throw new RuntimeException("Element is not clickable.");
	}
	
	public T waitForVisibility(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
		} catch (WebDriverException e) {
			log("WebDriverException occurred while waiting for visibility:" + e.getMessage());
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
		}
		return (T) this;
	}
	
	public T scrollTop(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (WebDriverException e) {
			log("WebDriverException occurred while scrolling: " + e.getMessage());
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			log(e.toString());
		}
		return (T) this;
	}
	
	public static String getLogTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return LOG_TIMESTAMP_FORMAT.format(timestamp);
	}

	@Step
	public static void log(String msg) {
		System.out.println(getLogTime() + " " + msg);
	}
	
}
