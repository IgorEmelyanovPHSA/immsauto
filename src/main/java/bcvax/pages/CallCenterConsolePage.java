package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CallCenterConsolePage extends BasePage {
	
	/*---------Properties-------*/
	@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
	private WebElement register_button;
	private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");
	
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
	
	@FindBy(xpath = "(.//button[@title = 'Next'])")
	private WebElement next_button;
	
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

	private By search_by_clinic_name_tab1 = By.xpath(".//a[text()='Search by clinic name']");
	
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
	
	@FindBy(xpath = ".//div[text() = 'Appointment confirmed!']")
	private WebElement vlidate_appointment_confirm_message;

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
		waitForElementToBeVisible(driver, register_button, 10);
		WebElement element = driver.findElement(register_button_1);
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
	
	public void clickNonIndigenousRadioButton() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,150)");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", non_indigenous_radio_button);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, non_indigenous_radio_button, 10);
		non_indigenous_radio_button.click();
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
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", next_button);
		waitForElementToBeVisible(driver, next_button, 10);
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
	
	public void clickOnPersonAccountRelatedTab() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = driver.findElement(person_account_Related_tab_1);
		isDisplayed(person_account_Related_tab_1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void navigateToVaccineSchedulingTab() throws InterruptedException {
		try {
			PersonAccountPage.goToVaccineScheduleTab(driver);
		} catch(NotFoundException ex) {
			System.out.println("Vaccine Scheduling tab not found. Try Appointment Scheduling Tab...");
			PersonAccountPage.goToAppointmentScheduleTab(driver);
		}
	}

	public void clickAppointmentTab() throws InterruptedException {
		try {
			PersonAccountPage.goToVaccineScheduleTab(driver);
		} catch(NotFoundException ex) {
			System.out.println("Vaccine Scheduling tab not found. Try Appointment Scheduling Tab...");
			PersonAccountPage.goToAppointmentScheduleTab(driver);
		}
	}
	
	public void clickOnVaccinationCheckbox() throws InterruptedException {
		PersonAccountPage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
	}
	
	public void selectEarlyBookingReason() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_early_booking_reason, 10);
		Thread.sleep(2000);
		click_early_booking_reason.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_early_booking_reason, 10);
		Thread.sleep(2000);
		select_early_booking_reason.click();
	}
	
	public void clickOnMoreSearchTabs() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_more_search_tabs, 10);
		click_more_search_tabs.click();
	}
	
	public void selectSearchClinicNameTab() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, search_by_clinic_name_tab1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(search_by_clinic_name_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void searchClinicName(String clinicNameToSearch) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		waitForElementToBeVisible(driver, search_clinic_name, 10);
		search_clinic_name.click();
		Thread.sleep(2000);
		search_clinic_name.sendKeys(clinicNameToSearch);
		search_clinic_name.sendKeys(Keys.RETURN);
	}


//	public void clickOnFacilityOptionLocation() throws InterruptedException {
//		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", option_loc_facility);
//		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,150)");
//		Thread.sleep(2000);
//		waitForElementToBeVisible(driver, option_loc_facility, 10);
//		option_loc_facility.click();
//	}

	public void clickOnFacilityOptionLocation() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, option_loc_facility, 10);
		scrollTop(option_loc_facility, true);
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
	
	public void clickRelatedTab() throws InterruptedException {
		waitForElementToBeLocated(driver, click_related_tab1, 10);
		WebElement element = driver.findElement(click_related_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickVerifyContactInformation() throws InterruptedException {
		Thread.sleep(500);
		By verify_contact_information_checkbox_path = By.xpath("//lightning-input[@class='slds-p-left_xxx-small verifyCheckbox slds-form-element']//span[@lightning-input_input=''][2]");
		waitForElementToBeEnabled(driver, verify_contact_information_checkbox_path, 10);
		WebElement verify_contact_information_checkbox = driver.findElement(verify_contact_information_checkbox_path);
		Thread.sleep(1000);
		scrollTop(verify_contact_information_checkbox, true);
		Thread.sleep(1000);
		verify_contact_information_checkbox.click();
		Thread.sleep(500);
	}

	public void selectOneOption(String vaccine) throws InterruptedException {
		PersonAccountPage.checkBookingVaccineCheckbox(driver, vaccine);
	}

}

