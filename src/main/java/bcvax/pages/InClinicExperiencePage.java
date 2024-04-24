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

	public void newDeferral() throws InterruptedException {
		Thread.sleep(2000);
		boolean referralNewButtonFound = false;
		WebElement newReferralBtn = null;
		while (!referralNewButtonFound) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0, 200)");
				newReferralBtn = driver.findElement(By.xpath("//li[@data-target-selection-name = 'sfdc:StandardButton.Deferrals__c.New']/a"));
				referralNewButtonFound = true;
			} catch(Exception ex) {
				Thread.sleep(2000);
			}
		}
		newReferralBtn.click();
	}

	public void scrollToDeferrals() throws InterruptedException {
		Thread.sleep(2000);
		boolean referralNewButtonFound = false;
		WebElement newReferralBtn = null;
		while (!referralNewButtonFound) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0, 200)");
				newReferralBtn = driver.findElement(By.xpath("//li[@data-target-selection-name = 'sfdc:StandardButton.Deferrals__c.New']/a"));
				referralNewButtonFound = true;
			} catch(Exception ex) {
				Thread.sleep(2000);
			}
		}
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

	public void clickSaveDefaultsButton() throws InterruptedException {
		Thread.sleep(500);
		By save_defaults_button_path = By.xpath("//button[text()='Save']");
		waitForElementToBeEnabled(driver, save_defaults_button_path, 10);
		WebElement save_defaults_button = driver.findElement(save_defaults_button_path);
		scrollCenter(save_defaults_button);
		Thread.sleep(500);
		save_defaults_button.click();
		Thread.sleep(500);
		clickCloseAlert();
	}

	public void clickRecordConsent() throws InterruptedException {
		Thread.sleep(500);
		By record_consent_btn_path = By.xpath("//button[@title='Primary action' and text()='Record Consent']");
		waitForElementToBeEnabled(driver, record_consent_btn_path, 10);
		WebElement record_consent_btn = driver.findElement(record_consent_btn_path);
		record_consent_btn.click();
	}
	public void clickTodayAppointments() throws InterruptedException {
		Thread.sleep(500);
		By today_appointments_path = By.xpath("//h2[text() = \"Today's Appointments\"] | //a[text() = \"Today's Appointments\"]");
		waitForElementToBeEnabled(driver, today_appointments_path, 10);
		WebElement todayAppointments = driver.findElement(today_appointments_path);
		scrollIfNeeded(driver, todayAppointments);
		Thread.sleep(500);
		todayAppointments.click();
	}

	public GenericTable getTodayAppointmentTable() throws InterruptedException {
		Thread.sleep(1000);

		GenericTable today_Appointments = tables.getTodayAppointmentsTable();
		int counter = 0;
		while(today_Appointments.getRows().size() < 1) {
			Thread.sleep(1000);
			today_Appointments = tables.getTodayAppointmentsTable();
			counter++;
			if(counter > 5) {
				break;
			}
		}
		return today_Appointments;
	}

	public void clickTodayAppointmentCaseViewButton(String client_name) throws InterruptedException {
		Thread.sleep(1000);

		GenericTable today_Appointments = getTodayAppointmentTable();
		List<Map<String, WebElement>> my_table = today_Appointments.getRowsMappedToHeadings();
		System.out.println("Found " + my_table.size() + "rows");
		for (Map<String, WebElement> my_row: my_table) {
			if(my_row.get("Profile Name").getText().equals(client_name)) {
				WebElement my_view = my_row.get("View");
				Thread.sleep(500);
				scrollIfNeeded(driver, my_view);
				Thread.sleep(1000);
				WebElement my_button = my_view.findElement(By.xpath(".//button"));
				my_button.click();
				Thread.sleep(1000);
				break;
			}
		}
	}

	public void clickTodayAppointmentCheckinButton(String client_name) throws InterruptedException {
		Thread.sleep(1000);

		GenericTable today_Appointments = getTodayAppointmentTable();
		List<Map<String, WebElement>> my_table = today_Appointments.getRowsMappedToHeadings();
		System.out.println("Found " + my_table.size() + "rows");
		for (Map<String, WebElement> my_row: my_table) {
			if(my_row.get("Profile Name").getText().equals(client_name)) {
				WebElement my_view = my_row.get("Check-in Client");
				Thread.sleep(500);
				scrollIfNeeded(driver, my_view);
				Thread.sleep(1000);
				WebElement my_button = my_view.findElement(By.xpath(".//button"));
				my_button.click();
				Thread.sleep(1000);
				break;
			}
		}
	}

	public String getTodayAppointmentPathwayStatus(String client_name) throws InterruptedException {
		Thread.sleep(1000);
		String my_pathway_status = "";
		GenericTable today_Appointments = getTodayAppointmentTable();
		List<Map<String, WebElement>> my_table = today_Appointments.getRowsMappedToHeadings();
		System.out.println("Found " + my_table.size() + "rows");
		for (Map<String, WebElement> my_row: my_table) {
			if(my_row.get("Profile Name").getText().equals(client_name)) {
				WebElement my_view = my_row.get("Pathway Status");
				my_pathway_status = my_view.getText();
				break;
			}
		}
		return my_pathway_status;
	}

	public boolean todayAppointmentViewButtonExists(String client_name) throws InterruptedException {
		Thread.sleep(1000);
		boolean button_exist = false;
		GenericTable today_Appointments = getTodayAppointmentTable();
		List<Map<String, WebElement>> my_table = today_Appointments.getRowsMappedToHeadings();
		System.out.println("Found " + my_table.size() + "rows");
		for (Map<String, WebElement> my_row: my_table) {
			if(my_row.get("Profile Name").getText().equals(client_name)) {
				WebElement my_view = my_row.get("View");
				if(my_view.findElement(By.xpath(".//button")).isDisplayed()) {
					button_exist = true;
					break;
				} else {
					button_exist = false;
					break;
				}
			}
		}
		return button_exist;
	}

	public boolean todayAppointmentCheckinButtonExists(String client_name) throws InterruptedException {
		Thread.sleep(1000);
		boolean button_exist = false;
		GenericTable today_Appointments = getTodayAppointmentTable();
		List<Map<String, WebElement>> my_table = today_Appointments.getRowsMappedToHeadings();
		System.out.println("Found " + my_table.size() + "rows");
		for (Map<String, WebElement> my_row: my_table) {
			if(my_row.get("Profile Name").getText().equals(client_name)) {
				WebElement my_view = my_row.get("Check-in Client");
				if(my_view.findElement(By.xpath(".//button")).isDisplayed()) {
					button_exist = true;
					break;
				} else {
					button_exist = false;
					break;
				}
			}
		}
		return button_exist;
	}

	public void ClickConfirmAndSaveAdministrationButton() throws InterruptedException {
		Thread.sleep(500);
		By confirm_save_adm_btn_path = By.xpath("//button[@title='Confirm & Save Administration']");
		waitForElementToBeEnabled(driver, confirm_save_adm_btn_path, 10);
		WebElement confirm_save_adm_btn = driver.findElement(confirm_save_adm_btn_path);
		scrollIfNeeded(driver, confirm_save_adm_btn);
		Thread.sleep(1000);
		confirm_save_adm_btn.click();
	}

	public void ClickModalConfirmAndSaveAdministrationButton() throws InterruptedException {
		By confirm_save_btn_path = By.xpath("//button[text()='Confirm & Save Administration']");
		waitForElementToBeEnabled(driver, confirm_save_btn_path, 10);
		WebElement confirm_save_btn = driver.findElement(confirm_save_btn_path);
		scrollCenter(confirm_save_btn);
		Thread.sleep(500);
		confirm_save_btn.click();
		Thread.sleep(500);
		By go_to_user_default_dialog_path = By.xpath("//button[@c-bchcmodal_bchcmodal and text()='Go to User Defaults']");

		try {
			clickCloseAlert();
			Thread.sleep(500);
		} catch(Exception ex) {
			System.out.println("Couldn't close the success dialog. Continue...");
		}

		try {
			waitForElementToBeEnabled(driver, go_to_user_default_dialog_path, 10);
			System.out.println("Dialog Go to User Default is found. Wait until disappear...");
			waitForElementNotToBePresent(driver, go_to_user_default_dialog_path, 5);
		} catch(Exception ex) {
			System.out.println("Go to User default dialog didn't appear or already gone. Continue...");
		}
//Try again to close success dialog
		try {
			System.out.println("Try again to close the success dialog...");
			clickCloseAlert();
			Thread.sleep(500);
		} catch(Exception ex) {
			System.out.println("Success dialog didn't appear or already gone. Continue...");
		}
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

	public void clickUserDefaultsTab() throws InterruptedException {
		Thread.sleep(500);
		By user_defaults_tab_path = By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.BCH_In_Clinic_User_Defaults']");
		waitForElementToBeEnabled(driver, user_defaults_tab_path, 10);
		WebElement user_defaults_tab = driver.findElement(user_defaults_tab_path);
		user_defaults_tab.click();
	}

	public void clickClientListTab() throws InterruptedException {
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

	public void clickOkForExpiredLot() throws InterruptedException {
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

	public boolean checkInButtonAvailable() throws  InterruptedException {
		Thread.sleep(500);
		By checkin_btn_path = By.xpath("//button[@class = 'slds-button slds-button_brand' and @title = 'Check-in Client']");
		waitForElementToBeEnabled(driver, checkin_btn_path, 10);
		WebElement check_in_button = driver.findElement(checkin_btn_path);
		waitForElementToBeVisible(driver, check_in_button, 10);
		return check_in_button.isDisplayed();
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

	public void selectNotApprovedAdministrationReason() throws InterruptedException {
		Thread.sleep(500);
		By administration_reason_path = By.xpath("//input[@type='radio' and @value='Intentional administration']/..//span[@part='indicator']");
		waitForElementToBeEnabled(driver, administration_reason_path, 2);
		WebElement administration_reason = driver.findElement(administration_reason_path);
		scrollCenter(administration_reason);
		Thread.sleep(500);
		administration_reason.click();
	}

	public void clickCloseAlert() throws InterruptedException {
		Thread.sleep(500);
		By alert_close_btn_path = By.xpath("//div[@role='alertdialog']/button[@title='Close']");
		waitForElementToBeEnabled(driver, alert_close_btn_path, 10);
		System.out.println("***Debug-- Alert Close Button is found");
		WebElement alert_close_btn = driver.findElement(alert_close_btn_path);
		try {
			alert_close_btn.click();
		} catch(ElementClickInterceptedException ex) {
			System.out.println("***DEBUG*** Element not clickable. Wait 1 sec and try again");
			Thread.sleep(1000);
			alert_close_btn = driver.findElement(alert_close_btn_path);
			alert_close_btn.click();
		}
	}

	public int getDeferralsCount() {
		int count = tables.getDeferralsTable().getRows().size();
		return count;
	}
}
