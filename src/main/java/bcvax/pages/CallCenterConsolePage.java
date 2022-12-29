package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class CallCenterConsolePage extends BasePage {
	
	/*---------Properties-------*/
	@FindBy(xpath = ".//span[@title='Call Center Console']")
	private WebElement callcenter_page_displayed;
	
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
	private By verify_phn_button1 = By.xpath("(.//button[@title = 'Verify Personal Health Number'])");
	
	@FindBy(xpath = "(.//button[@title = 'Next'])")
	private WebElement next_button;
	private By next_button1 = By.xpath("(.//button[@title = 'Next'])");
	
	@FindBy(xpath = "(.//input[@name = 'PersonEmail'])")
	private WebElement email;
	private By email1 = By.xpath("(.//input[@name = 'PersonEmail'])");
	
	@FindBy(xpath = "(.//input[@name = 'ConfirmEmail'])")
	private WebElement confirm_email;
	private By confirm_email1 = By.xpath("(.//input[@name = 'ConfirmEmail'])");
	
	@FindBy(xpath = ".//button[text()= 'Review Details']")
	private WebElement review_details;
	private By review_details1 = By.xpath("(.//button[text()= 'Review Details'])");
	
	@FindBy(xpath = ".//button[text() = 'Register']")
	private WebElement register_confirmation_page_button;
	private By register_confirmation_page_button1 = By.xpath(".//button[text() = 'Register']");
	
	@FindBy(xpath = "(.//a[text() = 'Related'])")
	private WebElement person_account_Related_tab;
	private By person_account_Related_tab_1 = By.xpath("(.//a[text() = 'Related'])");
	
	@FindBy(xpath = "//button[@title='Check Eligibility']")
	private WebElement click_eligibility_button;
	private By click_eligibility_button1 = By.xpath("(//button[@title='Check Eligibility'])");
	
	@FindBy(xpath = ".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']")
	private WebElement covid_eligibility_option;
	private By covid_eligibility_option1 = By.xpath(".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']");
	
	@FindBy(xpath = ".//a[@id = 'customTab__item']")
	private WebElement appointment_tab;
	private By appointment_tab1 = By.xpath(".//a[@id = 'customTab__item']");
	
	@FindBy(xpath = ".//span[text() = 'Covid-19 Vaccine']")
	private WebElement click_on_covid19_vaccination_checkbox;
	private By click_on_covid19_vaccination_checkbox_ = By.xpath(".//span[text() = 'Covid-19 Vaccine']");

	@FindBy(xpath = "//span[text() = 'Influenza Vaccine']")
	private WebElement checkBoxInfluenzaVaccine;

	@FindBy(xpath = ".//span[text() = 'Select One']")
	private WebElement click_early_booking_reason;
	private By click_early_booking_reason1 = By.xpath(".//span[text() = 'Select One']");
	
	@FindBy(xpath = ".//span[text() = 'Travel']")
	private WebElement select_early_booking_reason;
	private By select_early_booking_reason1 = By.xpath(".//span[text() = 'Travel']");
	
	@FindBy(xpath = "(.//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs'])")
	private WebElement click_more_search_tabs;
	private By click_more_search_tabs1 = By.xpath(".//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs']");
	
	@FindBy(xpath = ".//a[text()='Search by clinic name']")
	private WebElement search_by_clinic_name_tab;
	private By search_by_clinic_name_tab1 = By.xpath(".//a[text()='Search by clinic name']");
	
	@FindBy(xpath = "//input[@name='clinicstag']")
	private WebElement search_clinic_name;
	private By select_clinic_name1 = By.xpath("//input[@name='clinicstag']");
	
	@FindBy(xpath = "(.//button[@name = 'facility'])[1]")
	private WebElement option_loc_facility;
	private By select_option_loc_facility1 = By.xpath("(.//button[@name = 'facility'])[1]");
	
	@FindBy(xpath = "(.//button[@class = 'slds-day active-day'])[1]")
	private WebElement booking_app_active_day;
	private By booking_app_active_day1 = By.xpath("(.//button[@class = 'slds-day active-day'])[1]");
	
	@FindBy(xpath = "(.//button[@name='timeslot'])[1]")
	private WebElement time_slot_appointment;
	private By time_slot_appointment1 = By.xpath("(.//button[@name='timeslot'])[1]");
	
	@FindBy(xpath = ".//button[text() = 'Next']")
	private WebElement click_next_button;
	private By click_next_button1 = By.xpath(".//button[text() = 'Next']");
	
	@FindBy(xpath = ".//button[text() = 'Confirm appointment']")
	private WebElement click_confirm_appointment_button;
	private By click_confirm_appointment_button1 = By.xpath(".//button[text() = 'Confirm appointment']");
	
	@FindBy(xpath = ".//div[text() = 'Appointment confirmed!']")
	private WebElement vlidate_appointment_confirm_message;
	private By vlidate_appointment_confirm_message1 = By.xpath(".//div[text() = 'Appointment confirmed!']");
	
	@FindBy(xpath = "(//a[@data-label='Related'])")
	private WebElement click_related_tab;
	private By click_related_tab1 = By.xpath("//a[@data-label='Related']");
	
	@FindBy(xpath = "//lightning-input[@class='slds-p-left_xxx-small verifyCheckbox slds-form-element']//span[@lightning-input_input=''][2]")
	private WebElement verify_contact_information_checkbox;
	private By verify_contact_information_checkbox1 = By.xpath("(//lightning-input[@class='slds-p-left_xxx-small verifyCheckbox slds-form-element']//span[@lightning-input_input=''][2]");
	
	@FindBy(xpath = "(//button[@title='Primary action'])[1]")
	private WebElement refresh_forecast_button;
	private By refresh_forecast_button1 = By.xpath("(//button[@title='Primary action'])[1]");
	
	@FindBy(xpath = "")
	private WebElement selectAppointment;
	private By selectAppointment1 = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-h-c_-participant_-record_-page22___-account___-v-i-e-w/forcegenerated-flexipage_hc_participant_record_page22_account__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[2]/div[1]/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[2]/slot/flexipage-component2[3]/slot/lst-related-list-container/div/div[1]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list-internal/div/div/lst-primary-display-manager/div/lst-primary-display/lst-primary-display-grid/lst-customized-datatable/div[2]/div/div/table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-primitive-custom-cell/force-lookup/div/records-hoverable-link/div");
	
	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose #']/../..//LIGHTNING-FORMATTED-NUMBER[@data-output-element-id='output-field']")
	private WebElement getDoseNumber;
	private By getDoseNumber1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose #']/../..//LIGHTNING-FORMATTED-NUMBER[@data-output-element-id='output-field']");
	
	/*---------Constructor-------*/
	public CallCenterConsolePage(WebDriver driver) {
		super(driver);
	}
	
	
	/*-------------Methods--------------*/
	public void verifyIsCallCenterConsolePageDisplayed() {
		waitForElementToBeVisible(driver, callcenter_page_displayed, 10);
		callcenter_page_displayed.isDisplayed();
	}
	
	public void closeAllTabs() throws InterruptedException {
		do {
			try {
				WebElement close_tab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
				Thread.sleep(1000);
				close_tab.click();
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				System.out.println("/*---there are no Tab's to close anymore");
			}
		} while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
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
	
	public void successMessageAppear() throws InterruptedException {
		//try {
		waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Success']"), 20);
		driver.findElement(By.xpath(".//div[text() = 'Success']"));
		Thread.sleep(2000);
		log("  -- success message has been Appears - /");
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
		waitForElementToBeVisible(driver, refresh_forecast_button, 10);
		WebElement element = driver.findElement(refresh_forecast_button1);
		refresh_forecast_button.click();
	}
	
	public void successRegisteredMessageAppear() throws InterruptedException {
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
	
	public void clickOnEligibilityButton() throws InterruptedException {
		waitForElementToBeLocated(driver, click_eligibility_button1, 10);
		WebElement element = driver.findElement(click_eligibility_button1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void selectEligibilityOption() throws InterruptedException {
		waitForElementToBeVisible(driver, covid_eligibility_option, 10);
		covid_eligibility_option.click();
	}
	
	public void successEligibilityMessageAppear() throws InterruptedException {
		waitForElementToBeLocated(driver, By.xpath(".//span[text() = 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.']"), 20);
		driver.findElement(By.xpath(".//span[text() = 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.']"));
		Thread.sleep(2000);
		System.out.println("/* ----the toast Eligibility success message has been Appears");
	}
	
	public void clickAppointmentTab() throws InterruptedException {
		waitForElementToBeVisible(driver, appointment_tab, 10);
		Thread.sleep(2000);
		appointment_tab.click();
	}
	
	public void clickOnVaccinationCheckbox() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_on_covid19_vaccination_checkbox, 10);
		click_on_covid19_vaccination_checkbox.click();
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


	public void clickOnFacilityOptionLocation() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", option_loc_facility);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,150)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, option_loc_facility, 10);
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
		try {
			waitForElementToBeVisible(driver, vlidate_appointment_confirm_message, 10);
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
	
	public void clickRelatedTab() throws InterruptedException {
		waitForElementToBeLocated(driver, click_related_tab1, 10);
		WebElement element = driver.findElement(click_related_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickVerifyContactInformation() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, verify_contact_information_checkbox, 10);
		Thread.sleep(2000);
		verify_contact_information_checkbox.click();
		Thread.sleep(2000);
	}
	
	public void selectAppointmentRecord() throws InterruptedException {
		//To scroll down the page to see RERN Record
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		waitForElementToBeLocated(driver, selectAppointment1, 10);
		WebElement element = driver.findElement(selectAppointment1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public Double getValueOfDosesNumber() throws InterruptedException {
		waitForElementToBeLocated(driver, getDoseNumber1, 10);
		WebElement element = driver.findElement(getDoseNumber1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public void selectOneOption(String vaccine) throws InterruptedException{
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
		Thread.sleep(3000);
		if(vaccine.equalsIgnoreCase("Covid19Vaccine")){
			click(click_on_covid19_vaccination_checkbox);
		}
		else if(vaccine.equalsIgnoreCase("InfluenzaVaccine")){
			click(checkBoxInfluenzaVaccine);
		}
		else{
			click(click_on_covid19_vaccination_checkbox);
			click(checkBoxInfluenzaVaccine);
		}
	}

}
