package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CallCenterConsolePage extends BasePage {
	/*---------Constructor-------*/
	public CallCenterConsolePage(WebDriver driver) {
		super(driver);
	}
	
	
	/*-------------Methods--------------*/
	public void verifyIsCallCenterConsolePageDisplayed() throws InterruptedException {
		Thread.sleep(500);
		By callcenter_page_title_path = By.xpath(".//span[@title='Call Center Console']");
		waitForElementToBeLocated(driver, callcenter_page_title_path, 30);
		WebElement callcenter_page_title = driver.findElement(callcenter_page_title_path);
		callcenter_page_title.isDisplayed();
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
	
	public void clickRegisterButton() throws InterruptedException {
		Thread.sleep(500);
		By register_button_path = By.xpath("//button[text() = 'Register New Citizen']");
		waitForElementToBeEnabled(driver, register_button_path, 10);
		WebElement register_button = driver.findElement(register_button_path);
		register_button.click();
	}
}

