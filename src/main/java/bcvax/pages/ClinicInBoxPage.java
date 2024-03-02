package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClinicInBoxPage extends BasePage {
	/*---------Constructor-------*/
	public ClinicInBoxPage(WebDriver driver) {
		super(driver);
	}
	
	/*-------------Methods--------------*/
	public void clickRegisterButton() throws InterruptedException {
		Thread.sleep(500);
		By register_button_path = By.xpath("//button[text() = 'Register New Citizen']");
		waitForElementToBeEnabled(driver, register_button_path, 10);
		WebElement register_button = driver.findElement(register_button_path);
		register_button.click();
	}
	
	public void closeAllTabs() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, By.xpath("//div[@role='tablist']"), 30);
		List<WebElement> closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
		int count = closeButtons.size();
		int retry_count = 0;
		while(count == 0) {
			Thread.sleep(1000);
			closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
			retry_count++;
			if(retry_count > 5) {
				break;
			}
		}
		for(WebElement closeTabBtn : closeButtons) {
			try {
				closeTabBtn.click();
				Thread.sleep(2000);
			} catch (ElementNotInteractableException ex) {
				System.out.println(ex.getMessage());
				AlertDialog.closeAllAlerts(driver);
				Thread.sleep(500);
				closeTabBtn.click();
			} catch (StaleElementReferenceException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public boolean verifyIsClinicInBoxPageDisplayed() throws InterruptedException {
		int timeout = 30000;
		boolean found = false;
		Instant start = Instant.now();
		Instant end = Instant.now();
		while(!found) {
			try {
				found = driver.findElement(By.xpath("//span[@title='Clinic in a Box (IPM)']")).isDisplayed();
				System.out.println("Clinic in a Box");
				System.out.println(end.toString());
			} catch (NotFoundException ex) {
				end = Instant.now();
				if(Duration.between(start, end).toMillis() > timeout) {
					throw new NotFoundException("Current APP tab not found");
				}
				Thread.sleep(200);
			}
		}
		return found;
	}
	
	public boolean validateAppointmentConfirmedScreen() throws InterruptedException {
		By appointment_confirm_message_path = By.xpath("//div[@role = 'heading']/h1[text() = 'Appointment confirmed!']");
		try {
			waitForElementToBeLocated(driver, appointment_confirm_message_path, 10);
			System.out.println("/*---'Appointment confirmed!' message shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---the screen does not show up 'Appointment confirmed!'");
			return false;
		}
	}
	
	public void refreshBrowser() throws InterruptedException {
		driver.navigate().refresh();
	}
}