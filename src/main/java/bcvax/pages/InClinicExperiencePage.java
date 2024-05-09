package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static constansts.Header.SUPPLY_LOCATION_NAME;


public class InClinicExperiencePage extends BasePage {
	Tables tables;

	/*---------Constructor-------*/
	public InClinicExperiencePage(WebDriver driver) {
		super(driver);
		tables = new Tables(driver);
	}

	/*-------------Methods--------------*/

	public static void clickRegisterTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By register_tab_path = By.xpath("//a[@title='Register']/..");
		waitForElementToBeEnabled(driver, register_tab_path, 10);
		WebElement register_tab = driver.findElement(register_tab_path);
		register_tab.click();
	}

	public static void closeTabsHCA(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		try {
			waitForElementToBeLocated(driver, By.xpath("//div[@role='tablist']"), 5);
		} catch(TimeoutException ex_notfound) {
			List<WebElement> closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
			int count = closeButtons.size();
			int retry_count = 0;
			while (count == 0) {
				Thread.sleep(1000);
				closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
				retry_count++;
				if (retry_count > 5) {
					break;
				}
			}
			for (WebElement closeTabBtn : closeButtons) {
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
	}

	public static void clickRegisterButton(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By register_btn_path = By.xpath("//button[@title = ' Create New Profile']");
		waitForElementToBeEnabled(driver, register_btn_path, 30);
		WebElement register_btn = driver.findElement(register_btn_path);
		register_btn.click();
	}

	public static void clickTodayAppointments(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By today_appointments_path = By.xpath("//h2[text() = \"Today's Appointments\"] | //a[text() = \"Today's Appointments\"]");
		waitForElementToBeEnabled(driver, today_appointments_path, 10);
		WebElement todayAppointments = driver.findElement(today_appointments_path);
		scrollIfNeeded(driver, todayAppointments);
		Thread.sleep(500);
		todayAppointments.click();
	}

	public boolean validateVaccineAdminPageOpen() throws InterruptedException {
		Thread.sleep(500);
		By vaccine_administration_header_path  = By.xpath(".//h2[text() = 'Vaccine Administration']");
		try {
			waitForElementToBeLocated(driver, vaccine_administration_header_path, 30);
			System.out.println("/*---Vaccine admin ICE page shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---no Vaccine Admin ICE Page show up");
			return false;
		}
	}

	public static void clickUserDefaultsTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By user_defaults_tab_path = By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.BCH_In_Clinic_User_Defaults']");
		waitForElementToBeEnabled(driver, user_defaults_tab_path, 10);
		WebElement user_defaults_tab = driver.findElement(user_defaults_tab_path);
		user_defaults_tab.click();
	}

	public static void clickClientListTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By client_list_tab_path = By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.BCH_Client_List']");
		waitForElementToBeEnabled(driver, client_list_tab_path, 10);
		WebElement client_list_tab = driver.findElement(client_list_tab_path);
		client_list_tab.click();
	}

	public static boolean validateHomePageShownUp(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By home_title_path = By.xpath("//h1[text()='Client Search']");
		try {
			waitForElementToBeEnabled(driver, home_title_path, 10);
			System.out.println("/*---Home page-Client Search page shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---no Home page-Client Search page show up");
			return false;
		}
	}

	public static void clickOkForExpiredLot(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Check if the expired lot message appears. If yes click OK");
		try {
			WebElement modalBox = driver.findElement(By.xpath("//div[@class = 'slds-modal__container']"));
			modalBox.findElement(By.xpath("//button[text() = 'OK']")).click();
			Thread.sleep(2000);
		}
		catch(Exception ex) {
				System.out.println("No expired lots");
		}

	}

	public static String getCurrentTab(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By myXpath = By.xpath("//c-hc-lightning-progress-indicator/div/div");
		waitForElementToBeLocated(driver, myXpath, 10);
		List<WebElement> step_tabs = driver.findElements(myXpath);
		for(WebElement step_tab: step_tabs) {
			if(step_tab.getAttribute("class").contains("current-step")) {
				return step_tab.getText();
			}
		}
		return "";
	}
}
