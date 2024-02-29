package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CallCenterConsolePage extends BasePage {
	
	/*---------Properties-------*/
	@FindBy(xpath = "(.//input[@name = 'FirstName'])")
	private WebElement first_name;
	private By first_name1 = By.xpath("(.//input[@name = 'FirstName'])");
	
	@FindBy(xpath = "(.//input[@name = 'LastName'])")
	private WebElement last_name;
	private By last_name1 = By.xpath("(.//input[@name = 'LastName'])");
	
	@FindBy(xpath = "(.//input[@name = 'PersonBirthdate'])")
	private WebElement date_of_birth;
	private By date_of_birth1 = By.xpath("(.//input[@name = 'PersonBirthdate'])");
	
	@FindBy(xpath = "(.//input[@name = 'DDH_HC_Zip_Code'])")
	private WebElement postal_code;
	private By postal_code1 = By.xpath("(.//input[@name = 'DDH_HC_Zip_Code'])");
	
	@FindBy(xpath = "(.//input[@name = 'HC_Personal_Health_Number'])")
	private WebElement phn;
	private By phn1 = By.xpath("(.//input[@name = 'HC_Personal_Health_Number'])");
	
	@FindBy(xpath = "(.//input[@name = 'BCH_Indigenous'])[2]")
	private WebElement non_indigenous_radio_button;
	private By non_indigenous_radio_button1 = By.xpath("(.//input[@name = 'BCH_Indigenous'])[2]");
	
	@FindBy(xpath = ".//button[@title = 'Verify Personal Health Number']")
	private WebElement verify_phn_button;
	
	@FindBy(xpath = "(.//input[@name = 'PersonEmail'])")
	private WebElement email;
	private By email1 = By.xpath("(.//input[@name = 'PersonEmail'])");
	
	@FindBy(xpath = "(.//input[@name = 'ConfirmEmail'])")
	private WebElement confirm_email;
	private By confirm_email1 = By.xpath("(.//input[@name = 'ConfirmEmail'])");
	
	@FindBy(xpath = ".//button[text()= 'Review Details']")
	private WebElement review_details;

	private By register_confirmation_page_button1 = By.xpath(".//button[text() = 'Register']");

	private By person_account_Related_tab_1 = By.xpath("(.//a[text() = 'Related'])");

	@FindBy(xpath = ".//span[text() = 'Select One']")
	private WebElement click_early_booking_reason;
	
	@FindBy(xpath = ".//span[text() = 'Travel']")
	private WebElement select_early_booking_reason;
	
	@FindBy(xpath = "(.//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs'])")
	private WebElement click_more_search_tabs;
	
	@FindBy(xpath = "//input[@name='clinicstag']")
	private WebElement search_clinic_name;
	
	@FindBy(xpath = "(.//button[@name = 'facility'])[1]")
	private WebElement option_loc_facility;
	
	@FindBy(xpath = "(.//button[@class = 'slds-day active-day'])[1]")
	private WebElement booking_app_active_day;
	
	@FindBy(xpath = "(.//button[@name='timeslot'])[1]")
	private WebElement time_slot_appointment;
	
	@FindBy(xpath = ".//button[text() = 'Next']")
	private WebElement click_next_button;
	
	@FindBy(xpath = ".//button[text() = 'Confirm appointment']")
	private WebElement click_confirm_appointment_button;

	private By click_related_tab1 = By.xpath("//a[@data-label='Related']");

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
	
	public void enterFirstName(String firstname) throws InterruptedException {
		waitForElementToBeLocated(driver, first_name1, 10);
		first_name.sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) throws InterruptedException {
		waitForElementToBeLocated(driver, last_name1, 10);
		last_name.sendKeys(lastname);
	}
	
	public void enterDateOfBirth(String dateofbirth) throws InterruptedException {
		waitForElementToBeLocated(driver, date_of_birth1, 10);
		date_of_birth.sendKeys(dateofbirth);
	}
	
	public void enterPostalCode(String postalcode) throws InterruptedException {
		waitForElementToBeLocated(driver, postal_code1, 10);
		postal_code.sendKeys(postalcode);
	}
	
	public void enterPNH(String phn_number) throws InterruptedException {
		waitForElementToBeLocated(driver, phn1, 10);
		phn.sendKeys(phn_number);
		Thread.sleep(500);
		phn.sendKeys(Keys.TAB);
		Thread.sleep(500);
	}
	
	public void clickVerifyPHNButton() throws InterruptedException {
		waitForElementToBeVisible(driver, verify_phn_button, 10);
		verify_phn_button.click();
	}
	
	public String successMessageAppear() throws InterruptedException {
		Thread.sleep(500);
		By message_path = By.xpath("//div[text() = 'Success'] | //h2[@c-bchcvacinnepreregistrationinternal_bchcvacinnepreregistrationinternal and text() = 'Match Unsuccessful']");
		waitForElementToBeEnabled(driver, message_path, 10);
		WebElement message = driver.findElement(message_path);
		log("  -- success message has been Appears - /");
		return message.getText();
	}
	
	public void clickNextButton() throws InterruptedException {
		Thread.sleep(500);
		By btn_next_path = By.xpath("(//button[@title = 'Next'])");
		waitForElementToBeEnabled(driver, btn_next_path, 10);
		WebElement next_button = driver.findElement(btn_next_path);
		scrollIfNeeded(driver, next_button);
		next_button.click();
	}
	
	public void enterEmail(String enteremail) throws InterruptedException {
		waitForElementToBeLocated(driver, email1, 10);
		Thread.sleep(2000);
		email.sendKeys(enteremail);
	}
	
	public void confirmEmail(String confirmemail) throws InterruptedException {
		waitForElementToBeLocated(driver, confirm_email1, 10);
		confirm_email.sendKeys(confirmemail);
	}
	
	public void clickReviewDetails() throws InterruptedException {
		waitForElementToBeVisible(driver, review_details, 10);
		review_details.click();
	}
	
	public void clickRegisterButtonOnConfirmationPage() throws InterruptedException {
		waitForElementToBeLocated(driver, register_confirmation_page_button1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(register_confirmation_page_button1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickRefreshForecastButton() throws InterruptedException {
		PersonAccountPage.clickRefreshForecastButton(driver);
	}
	
	public void successRegisteredMessageAppear() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Citizen Successfully Registered']"), 20);
		driver.findElement(By.xpath(".//div[text() = 'Citizen Successfully Registered']"));
		Thread.sleep(2000);
		System.out.println("/* ----the toast success Citizen Registered message has been Appears");
	}
	
	public void clickOnVaccinationCheckbox() throws InterruptedException {
		PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
	}

	public void clickOnFacilityOptionLocation() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, option_loc_facility, 10);
		scrollCenter(option_loc_facility);
		option_loc_facility.click();
	}

	public void selectBookingAppointmentDay() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, booking_app_active_day, 10);
		booking_app_active_day.click();
	}
	
	
	public void selectTimeSlotAppointment() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", time_slot_appointment);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, time_slot_appointment, 10);
		Thread.sleep(2000);
		time_slot_appointment.click();
	}
	
	public void clickOnNextButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_next_button, 10);
		Thread.sleep(2000);
		click_next_button.click();
	}
	
	public void clickOnConfirmButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_confirm_appointment_button, 10);
		click_confirm_appointment_button.click();
	}
	
	public boolean validateAppointmentConfirmedScreen() throws InterruptedException {
		By appointment_confirm_message_path = By.xpath("//div[@role = 'heading']/h1[text() = 'Appointment confirmed!']");
		try {
			waitForElementToBeLocated(driver, appointment_confirm_message_path, 10);
			System.out.println("/*---'Appointment confirmed!' message shown up");
			return true;
		} catch (Exception e) {
			System.out.println("/*---the screen does not show up 'Appointment confirmed!'");
			return false;
		}
	}
	
	public void refreshBrowser() throws InterruptedException {
		driver.navigate().refresh();
	}
	
	public void clickVerifyContactInformation() throws InterruptedException {
		Thread.sleep(500);
		By verify_contact_information_checkbox_path = By.xpath("//lightning-input[@class='slds-p-left_xxx-small verifyCheckbox slds-form-element']//span[@class='slds-checkbox_faux']");
		waitForElementToBeEnabled(driver, verify_contact_information_checkbox_path, 10);
		WebElement verify_contact_information_checkbox = driver.findElement(verify_contact_information_checkbox_path);
		Thread.sleep(1000);
		scrollCenter(verify_contact_information_checkbox);
		Thread.sleep(1000);
		verify_contact_information_checkbox.click();
		Thread.sleep(500);
	}
}

