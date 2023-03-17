package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClinicInBoxPage extends BasePage {
	/*---------Properties-------*/
	@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
	private WebElement register_button;
	private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");
	
	@FindBy(xpath = ".//span[@title='Clinic in a Box (IPM)']")
	private WebElement clinicinbox_page_displayed;

	@FindBy(xpath = ".//span[@title='Clinic in a Box (IPM)']")
	private WebElement cib_App_displayed;
	private By cib_App_displayed1 = By.xpath(".//span[@title='Clinic in a Box (IPM)']");

	@FindBy(xpath = "//div[@class='slds-icon-waffle']")
	private WebElement select_app_launcher;
	private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");

	@FindBy(xpath = "//p[text()='Clinic in a Box (IPM)']")
	private WebElement click_cib_app;
	private By click_cib_app1 = By.xpath("//p[text()='Clinic in a Box (IPM)']");


	@FindBy(xpath = ".//span[text() = 'Register']")
	private WebElement register_tab;
	private By register_tab1 = By.xpath("(.//a[@title = 'Register'])");
	
	@FindBy(xpath = ".//button[@aria-label = 'Search']")
	private WebElement search_assistant;
	private By search_assistant1 = By.xpath(".//button[@aria-label = 'Search']");

	@FindBy(xpath = ".//lightning-input[@class = 'saInput slds-grow slds-form-element']")
	private WebElement search_input;
	private By search_input1 = By.xpath(".//lightning-input[@class = 'saInput slds-grow slds-form-element']");

	@FindBy(xpath = "(//a[@data-label='Related'])")
	private WebElement click_related_tab;
	private By click_related_tab1 = By.xpath("//a[@data-label='Related']");
	
	@FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
	private WebElement create_Immunization_Record;
	private By creat_Immunization_Record1 = By.xpath("//button[contains(text(),'Create Immunization Record')]");
	
	@FindBy(xpath = "//option[contains(text(),'Select an option')]")
	private WebElement select_an_option;
	private By select_an_option1 = By.xpath("//option[contains(text(),'Select an option')]");
	
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

	private By remediaation_needed1 = By.xpath("//span[text() = 'Remediation Needed']");
	
	@FindBy(xpath = ".//a[@title='Maegan Tanya bcvaxvillage']")
	private WebElement user_found;
	private By user_found1 = By.xpath(".//a[@title='Maegan Tanya bcvaxvillage']");
	
	@FindBy(xpath = "//input[@name=\"BCH_Requested_Delivery_Date__c\"]")
	private WebElement inputDiwaDate;
	
	@FindBy(xpath = "//option[contains(text(),'COVID-19 mRNA')]")
	private WebElement covidmRna;
	private By covidmRna2 = By.xpath("//option[contains(text(),'COVID-19 mRNA')]");
	
	@FindBy(xpath = ".//button[@title = 'icon']")
	private WebElement search_location;
	private By search_location1 = By.xpath(".//button[@title = 'icon']");
	
	@FindBy(xpath = ".//input[@data-id = 'userinput']")
	private WebElement search_clinic;
	private By search_clinic1 = By.xpath(".//input[@data-id = 'userinput']");
	
	@FindBy(xpath = "//li[@data-label='Age 5-11 Only - Indigenous Clinic - Victoria Native Friendship Center']")
	private WebElement finalClick;
	@FindBy(xpath = "//span[contains(text(),'Age 5-11 Only - Indigenous Clinic - Victoria Native Friendship Center')]")
	private WebElement finalClick1;
	
	@FindBy(xpath = "//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right']")
	private WebElement inputDate;
	private By inputDate1 = By.xpath("//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right']");
	
	@FindBy(xpath = "(.//input[@name = 'PersonEmail'])")
	private WebElement email;
	private By email1 = By.xpath("(.//input[@name = 'PersonEmail'])");
	
	@FindBy(xpath = "(.//input[@name = 'ConfirmEmail'])")
	private WebElement confirm_email;
	private By confirm_email1 = By.xpath("(.//input[@name = 'ConfirmEmail'])");
	
	@FindBy(xpath = ".//button[text()= 'Review Details']")
	private WebElement review_details;
	private By review_details1 = By.xpath("(.//button[text()= 'Review Details'])");
	
	@FindBy(xpath = ".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']")
	private WebElement select_covid19_option_from_dropdown;
	private By select_covid19_option_from_dropdown1 = By.xpath("(.//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination'])");
	
	@FindBy(xpath = ".//button[text() = 'Register']")
	private WebElement register_confirmation_page_button;
	private By register_confirmation_page_button1 = By.xpath(".//button[text() = 'Register']");
	
	@FindBy(xpath = ".//div[@aria-label = 'Profiles||List View']//a[contains(text(),'Maegan Tanya bcvaxvillage')]")
	private WebElement click_on_citizen;
	private By click_on_citizen1 = By.xpath(".//div[@aria-label = 'Profiles||List View']//a[contains(text(),'Maegan Tanya bcvaxvillage')]");
	
	@FindBy(xpath = "//a[@id='relatedListsTab__item']")
	private WebElement selectCitizenInTable; //
	
	@FindBy(xpath = "//button[text() = 'Confirm']")
	private WebElement confirm_button;
	private By confirm_button1 = By.xpath("//button[text() = 'Confirm']");
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]")
	private WebElement yes_button_save_on_popup_window;
	private By yes_button_save_on_popup_window1 = By.xpath("//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]");
	
	@FindBy(xpath = "//button[contains(text(),'Record Immunization')]")
	private WebElement recordImmunizationBtn;
	
	@FindBy(xpath = "//input[@placeholder = 'Search People...']")
	private WebElement informed_consent_provider_dropdown;
	private By informed_consent_provider_dropdown_ = By.xpath("//input[@placeholder = 'Search People...']");

	@FindBy(xpath = "//input[@placeholder = 'Search People...']")
	private WebElement immunizing_agent_provider_dropdown;
	private By immunizing_agent_provider_dropdown_ = By.xpath("//input[@placeholder = 'Search People...']");

	@FindBy(xpath = "//input[@name='effectiveToDate']")
	private WebElement consentEffectiveToDate;
	
	@FindBy(xpath = "//button[text() = 'Save Consent']")
	private WebElement saveConsentButton;
	private By saveConsentButton1 = By.xpath("//button[text() = 'Save Consent']");

	@FindBy(xpath = "//SPAN[@lightning-input_input=''][text()='Show all lot numbers.']/preceding-sibling::SPAN")
	private WebElement show_all_lot_numbers_checkbox;
	
	@FindBy(xpath = "//input[@data-id = 'select-sobject-id']")
	private WebElement click_lot_number_dropdown;
	
	@FindBy(xpath = "//button[@name='injectionSite']")
	private WebElement selectSite;
	private By selectSite1 = By.xpath("//button[@name='injectionSite']");
	
	@FindBy(xpath = "//button[@name='dosePicklist']")
	private WebElement select_dosage_field;
	private By select_dosage_field1 = By.xpath("//button[@name='dosePicklist']");

	@FindBy(xpath = "//span[@title='0.25']")
	private WebElement select_dosage;
	private By select_dosage1 = By.xpath("//span[@title='0.25']");
	
	@FindBy(xpath = "//div[1]/div[1]/div[2]/lightning-button[1]/button[1]")
	private WebElement editImmunizationInformation;
	
	@FindBy(xpath = "//li[@title='SPIKEVAX6-5Test001 - Exp. 2022 December 31']")
	private WebElement select_lot;
	private By select_lot_ = By.xpath("//li[@title='SPIKEVAX6-5Test001 - Exp. 2022 December 31']");
	
	@FindBy(xpath = "//span[@title='Arm - Left deltoid']")
	private WebElement select_injection_site_value;
	private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");
	
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	private WebElement saveAgain;
	
	@FindBy(xpath = "//button[@title='Confirm & Save Administration']")
	private WebElement confirmAndSave;
	
	//@FindBy(xpath = "//A[text()='06957433']/self::A")

	
	@FindBy(xpath = "//button[contains(text(),'Confirm and Save')]")
	private WebElement lastConfirmAndSave;
	private By lastConfirmAndSave1 = By.xpath( "//button[contains(text(),'Confirm and Save')]");
	
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
	
	@FindBy(xpath = "(.//span[text() = 'Covid-19 Vaccine'])")
	private WebElement click_on_covid19_vaccination_checkbox;
	private By click_on_covid19_vaccination_checkbox_ = By.xpath(".//span[text() = 'Covid-19 Vaccine']");
	
	@FindBy(xpath = "(.//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs'])")
	private WebElement click_more_search_tabs;
	private By click_more_search_tabs1 = By.xpath(".//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs']");

	@FindBy(xpath = ".//span[text() = 'Select One']")
	private WebElement click_early_booking_reason;
	private By click_early_booking_reason1 = By.xpath(".//span[text() = 'Select One']");

	@FindBy(xpath = ".//span[text() = 'Travel']")
	private WebElement select_early_booking_reason;
	private By select_early_booking_reason1 = By.xpath(".//span[text() = 'Travel']");

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
	private By vlidate_appointment_confirm_message1 = By.xpath(".//div[text() = 'Appointment Confirmed!']");
	
	@FindBy(xpath = "(.//button[@name='navigateToICE'])")
	private WebElement click_navigate_to_ICE_btn;
	private By click_navigate_to_ICE_btn1 = By.xpath(".//button[@name='navigateToICE']");
	
	@FindBy(xpath = ".//div[@aria-label = 'Profiles||List View']//a[contains(text(),'Hugues BCVaxLampard')]")
	private WebElement click_Dose2_citizen;
	private By click_Dose2_citizen1 = By.xpath(".//div[@aria-label = 'Profiles||List View']//a[contains(text(),'Hugues BCVaxLampard')]");
	
	@FindBy(xpath = "//span[contains(text(),'I verify')]/..//span[@class='slds-checkbox_faux']")
	private WebElement verify_contact_information_checkbox;
	private By verify_contact_information_checkbox1 = By.xpath("//span[contains(text(),'I verify')]/..//span[@class='slds-checkbox_faux']");
	
	@FindBy(xpath = "//p[text()='In-Clinic Experience']")
	private WebElement click_ice_app;
	private By click_ice_app1 = By.xpath("//p[text()='In-Clinic Experience']");
	
	@FindBy(xpath = ".//span[text() = 'Home']")
	private WebElement navigateHomePage;
	private By navigateHomePage1 = By.xpath(".//span[text() = 'Home']");
	
	@FindBy(xpath = ".//h1[text() = 'Client Search']")
	private WebElement validate_home_client_search_page_open;
	private By validate_home_client_search_page_open1 = By.xpath(".//h1[text() = 'Client Search']");
	
	@FindBy(xpath = ".//button[text()='Search']")
	private WebElement homePageSearchBtn;
	private By homePageSearchBtn1 = By.xpath(".//button[text()='Search']");
	
	@FindBy(xpath = "//button[text()='View']")
	private WebElement ClickUserHomePage;
	private By ClickUserHomePage1 = By.xpath("//button[text()='View']");
	
	@FindBy(xpath = ".//a[@title='Adams Innis BCVaxKenna']")
	private WebElement useradms;
	private By useradms1 = By.xpath(".//a[@title='Adams Innis BCVaxKenna']");
	
	@FindBy(xpath = "(//input[@placeholder='Input your search here'])[1]")
	private WebElement searchByPhn;
	private By searchByPhn1 = By.xpath("(//input[@placeholder='Input your search here'])[1]");

	@FindBy(xpath = "//span[text() = 'Covid-19 Vaccine']")
	private WebElement checkBoxCovid19Vaccine;

	@FindBy(xpath = "//span[text() = 'Influenza Vaccine']")
	private WebElement checkBoxInfluenzaVaccine;

	@FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
	private WebElement select_inform_consent_provider;
	private By select_inform_consent_provider_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");

	@FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
	private WebElement select_immunizing_agent_provider;
	private By select_immunizing_agent_provider_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");


	/*---------Constructor-------*/
	public ClinicInBoxPage(WebDriver driver) {
		super(driver);
	}
	
	/*-------------Methods--------------*/
	public void clickRegisterButton() throws InterruptedException {
		waitForElementToBeVisible(driver, register_button, 10);
		WebElement element = driver.findElement(register_button_1);
		register_button.click();
	}
	
	public void closeAllTabs() throws InterruptedException {
		do {
			try {
				WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
				Thread.sleep(1000);
				closetab.click();
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				System.out.println("/*---there are no Tab's to close anymore");
			}
		} while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
	}

	public boolean displayCIBApp() {
		return isDisplayed(cib_App_displayed1);
	}

	public void selectCIBApp() throws InterruptedException {
		waitForElementToBeLocated(driver, select_app_launcher1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(select_app_launcher1);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_cib_app1, 10);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(click_cib_app1);
		Thread.sleep(2000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
	}
	
	public void verifyIsClinicInBoxPageDisplayed() {
		waitForElementToBeVisible(driver, clinicinbox_page_displayed, 10);
		this.clinicinbox_page_displayed.isDisplayed();
	}
	
	public void SearchDIWACitizen(String citizen) throws InterruptedException {
		waitForElementToBeVisible(driver, search_assistant, 10);
		Thread.sleep(2000);
		search_assistant.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, search_input, 10);
		Thread.sleep(2000);
		search_input.click();
		Thread.sleep(2000);
		search_input.sendKeys(citizen);
		Thread.sleep(2000);
		search_input.sendKeys(Keys.RETURN);
		Thread.sleep(2000);
	}
	
	public void userClickCitizen() throws InterruptedException {
		waitForElementToBeVisible(driver, click_on_citizen, 10);
		Thread.sleep(5000);
		WebElement element = driver.findElement(click_on_citizen1);
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickConfirmButton() throws InterruptedException {
		waitForElementToBeVisible(driver, confirm_button, 10);
		this.confirm_button.click();
	}
	
	public boolean userFound() throws InterruptedException {
		waitForElementToBeLocated(driver, user_found1, 10);
		WebElement element = driver.findElement(user_found1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		return true;
	}
	
	public void clickRelatedTab() throws InterruptedException {
		waitForElementToBeLocated(driver, click_related_tab1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_related_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickCreatImmunizationRecord() throws InterruptedException {
		create_Immunization_Record.click();
	}
	
	public void clickSelectAnOptionDropdown() {
		this.select_an_option.click();
	}
	
	public void clickHomePage() throws InterruptedException {
		waitForElementToBeLocated(driver, navigateHomePage1, 10);
		WebElement element = driver.findElement(navigateHomePage1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void selectOption(String vaccine) throws InterruptedException {
		//waitForElementToBeLocated(driver,clinicName,10);
		waitForElementToBeVisible(driver, covidmRna, 10);
		WebElement search_input = driver.findElement(covidmRna2);
		search_input.click();
	}
	
	public void searchClinicLocation(String clinic) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, search_clinic, 10);
		Thread.sleep(2000);
		search_clinic.sendKeys(clinic);
		Thread.sleep(4000);
		By select_dropdown_option = By.xpath(".//div[@class = 'slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta']");
		driver.findElement(select_dropdown_option).click();
		Thread.sleep(2000);
	}
	
	public void clickTimeBox() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		waitForElementToBeVisible(driver, inputDate, 10);
		String todayAsString = dateFormat.format(today);
		waitForElementToBeVisible(driver, inputDate, 10);
		this.inputDate.click();
		Thread.sleep(2000);
		this.inputDate.sendKeys(todayAsString);
		Thread.sleep(2000);
		this.inputDate.sendKeys(Keys.ENTER);
	}
	
	public boolean selectDateOfAdministration() throws InterruptedException {
		if (!isDisplayed(inputDate1)) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", inputDate);
		waitForElementToBeVisible(driver, inputDate, 10);
		String todayAsString = dateFormat.format(today);
		waitForElementToBeVisible(driver, inputDate, 10);
		this.inputDate.click();
		Thread.sleep(2000);
		this.inputDate.sendKeys(todayAsString);
		Thread.sleep(2000);
		this.inputDate.sendKeys(Keys.ENTER);
		return true;
	}
	
	public void selectDateAndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		String todayAsString = dateFormat.format(today);
		this.inputDate.sendKeys(todayAsString, Keys.ENTER);
	}
	
	public void clickRecordImmunization() throws InterruptedException {
		waitForElementToBeVisible(driver, recordImmunizationBtn, 10);
		recordImmunizationBtn.click();
	}
	
	public boolean clickPopupYesButtonIfDisplayed() throws InterruptedException {
		if (!isDisplayed(yes_button_save_on_popup_window1)) {
			return false;
		}
		waitForElementToBeLocated(driver, yes_button_save_on_popup_window1, 10);
		WebElement element = driver.findElement(yes_button_save_on_popup_window1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		return true;
	}
	
	public void enterConsentEffectiveToDate() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date yesterday = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

		String yesterdayAsString = dateFormat.format(yesterday);
		this.consentEffectiveToDate.sendKeys(yesterdayAsString, Keys.ENTER);
	}
	
	public void clickSaveConsent() throws InterruptedException {
		waitForElementToBeVisible(driver, saveConsentButton, 10);
		WebElement search_input = driver.findElement(saveConsentButton1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", search_input);
		this.saveConsentButton.click();
		Thread.sleep(3000);
	}
	
	public void clickEditBtn() throws InterruptedException {
		editImmunizationInformation.click();
	}
	
	public void SearchDose2Citizen(String citizen) throws InterruptedException {
		waitForElementToBeVisible(driver, search_assistant, 10);
		Thread.sleep(2000);
		search_assistant.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, search_input, 10);
		Thread.sleep(2000);
		search_input.click();
		Thread.sleep(2000);
		search_input.sendKeys(citizen);
		Thread.sleep(2000);
		search_input.sendKeys(Keys.RETURN);
		Thread.sleep(2000);
	}
	
	public void clickDose2Citizen() throws InterruptedException {
		waitForElementToBeVisible(driver, click_Dose2_citizen, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_Dose2_citizen1);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		//click_Dose2_citizen.click();
	}

	public void selectInformedConsentProvider(String Provider) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", informed_consent_provider_dropdown);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, informed_consent_provider_dropdown, 10);
		Thread.sleep(2000);
		informed_consent_provider_dropdown.click();
		Thread.sleep(5000);
		informed_consent_provider_dropdown.sendKeys(Provider);
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, select_inform_consent_provider, 10);
		Thread.sleep(5000);
		select_inform_consent_provider.click();
		Thread.sleep(2000);
	}

	public void selectImmunizingAgentProvider(String Provider) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", immunizing_agent_provider_dropdown);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, immunizing_agent_provider_dropdown, 10);
		Thread.sleep(2000);
		immunizing_agent_provider_dropdown.click();
		Thread.sleep(5000);
		immunizing_agent_provider_dropdown.sendKeys(Provider);
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, select_immunizing_agent_provider, 10);
		Thread.sleep(5000);
		select_immunizing_agent_provider.click();
		Thread.sleep(2000);
	}

	public void selectLot() throws InterruptedException {
		waitForElementToBeVisible(driver, select_lot, 10);
		Thread.sleep(2000);
		select_lot.click();
	}

	public void saveImmunizationInformation() throws InterruptedException {
		saveAgain.click();
	}
	
	public void confirmAndSaveAdministration() throws InterruptedException {
		confirmAndSave.click();
	}
	
	public void summaryConfirmAndSave() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", lastConfirmAndSave);
		waitForElementToBeLocated(driver, lastConfirmAndSave1, 10);
		lastConfirmAndSave.click();
	}

	public void clickShowAllLotNumbersCheckBox()throws InterruptedException{
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, show_all_lot_numbers_checkbox, 10);
		Thread.sleep(2000);
		show_all_lot_numbers_checkbox.click();
		Thread.sleep(2000);
	}
	
	public void clickLotNumberDropDown() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", click_lot_number_dropdown);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_lot_number_dropdown, 10);
		Thread.sleep(2000);
		click_lot_number_dropdown.click();
		Thread.sleep(2000);
	}
	
	public void selectInjectionSite() throws InterruptedException {
		waitForElementToBeLocated(driver, selectSite1, 10);
		WebElement element = driver.findElement(selectSite1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_injection_site_value1, 10);
		WebElement element1 = driver.findElement(select_injection_site_value1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
	}
	
	public void selectInjectionSiteValue(String Injection) throws InterruptedException {
		waitForElementToBeVisible(driver, select_injection_site_value, 10);
		WebElement search_input = driver.findElement(select_injection_site_value1);
		search_input.click();
	}
	
	public void selectDosage() throws InterruptedException {
		waitForElementToBeLocated(driver, select_dosage_field1, 10);
		WebElement element = driver.findElement(select_dosage_field1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_dosage1, 10);
		WebElement element1 = driver.findElement(select_dosage1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
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
		waitForElementToBeVisible(driver, non_indigenous_radio_button, 10);
		scrollTop(non_indigenous_radio_button);
		click(non_indigenous_radio_button);
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
		System.out.println("/* ----the toast success message has been Appears");
	}
	
	public void clickNextButton() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", next_button);
		waitForElementToBeVisible(driver, next_button, 10);
		next_button.click();
	}
	
	//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='PIR Immunization ID']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']
	
	
	public void enterEmail(String enteremail) throws InterruptedException {
		waitForElementToBeLocated(driver, email1, 10);
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
		WebElement element = driver.findElement(register_confirmation_page_button1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
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
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,450)");
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, click_on_covid19_vaccination_checkbox, 10);
		Thread.sleep(2000);
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
	
	public void selectSearchByClinicNameTab() throws InterruptedException {
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
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
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
		Thread.sleep(2000);
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
	
	public InClinicExperiencePage ClickGoToInClinicExperienceButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_navigate_to_ICE_btn, 10);
		Thread.sleep(2000);
		click_navigate_to_ICE_btn.click();
		Thread.sleep(2000);
		return new InClinicExperiencePage(driver);
	}
	
	public void clickVerifyContactInformation() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, verify_contact_information_checkbox, 10);
		Thread.sleep(2000);
		verify_contact_information_checkbox.click();
		Thread.sleep(2000);
	}

	public void clickVerifyContactInformationNew() throws InterruptedException {
		Thread.sleep(1000);
		scrollTop(verify_contact_information_checkbox);
		Thread.sleep(1000);
		clickUsingJS(verify_contact_information_checkbox);
	}
	
	public boolean displayClinicInBoxApp() {
		return isDisplayed(cib_App_displayed1);
	}
	
	public void cIBApp() throws InterruptedException {
		waitForElementToBeLocated(driver, select_app_launcher1, 10);
		WebElement element = driver.findElement(select_app_launcher1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		waitForElementToBeLocated(driver, click_cib_app1, 10);
		WebElement element1 = driver.findElement(click_cib_app1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
	}
	
	public void selectICEFromApp() throws InterruptedException {
		waitForElementToBeLocated(driver, select_app_launcher1, 10);
		WebElement element = driver.findElement(select_app_launcher1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_ice_app1, 10);
		WebElement element1 = driver.findElement(click_ice_app1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
	}
	
	public void ClickSearchButtonHomePage() throws InterruptedException {
		waitForElementToBeVisible(driver, homePageSearchBtn, 10);
		homePageSearchBtn.click();;
		Thread.sleep(2000);
	}
	
	public boolean validateHomePageShownUp() throws InterruptedException {
		try {
			waitForElementToBeVisible(driver, validate_home_client_search_page_open, 10);
			log("/*---Home page-Client Search page shown up");
			return true;
		} catch (NoSuchElementException e) {
			log("/*---no Home page-Client Search page show up");
			return false;
		}
	}
	
	public void ClickUserOnHomePage() throws InterruptedException {
		waitForElementToBeLocated(driver, ClickUserHomePage1, 10);
		WebElement element = driver.findElement(ClickUserHomePage1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public void SearchForCitizen(String citizen) throws InterruptedException {
		waitForElementToBeVisible(driver, search_assistant, 10);
		//WebElement search_navigator = driver.findElement(search_assistant1);
		search_assistant.click();
		waitForElementToBeVisible(driver, search_input, 10);
		//WebElement search_input = driver.findElement(search_input1);
		search_input.sendKeys(citizen);
		search_input.sendKeys(Keys.RETURN);
		Thread.sleep(5000);
	}
	

	
	public void userAdamsFound() throws InterruptedException {
		waitForElementToBeLocated(driver, useradms1, 10);
		useradms.click();
		Thread.sleep(2000);
	}
	
	public void enterPNHToSearch(String phn_number) throws InterruptedException {
		waitForElementToBeLocated(driver, searchByPhn1, 10);
		searchByPhn.sendKeys(phn_number);
	}
	
	public void clickRegisterTab() throws InterruptedException {
		waitForElementToBeLocated(driver, register_tab1, 10);
		WebElement element = driver.findElement(register_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void selectOneOption(String vaccine) throws InterruptedException{
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
		Thread.sleep(3000);
		if(vaccine.equalsIgnoreCase("Covid19Vaccine")){
			click(checkBoxCovid19Vaccine);
		}
		else if(vaccine.equalsIgnoreCase("InfluenzaVaccine")){
			click(checkBoxInfluenzaVaccine);
		}
		else{
			click(checkBoxCovid19Vaccine);
			click(checkBoxInfluenzaVaccine);
		}
	}

}