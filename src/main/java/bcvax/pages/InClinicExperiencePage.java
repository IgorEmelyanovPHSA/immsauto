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
	private By register_tab1 = By.xpath("(.//a[@title = 'Register'])");

	@FindBy(xpath = ".//button[text()='Save']")
	private WebElement click_save_defaults_button;
	private By click_save_defaults_button_ = By.xpath(".//button[text()='Save']");

	@FindBy(xpath = ".//button[@aria-label = 'Search']")
	private WebElement search_assistant;
	private By search_assistant1 = By.xpath(".//button[@aria-label = 'Search']");

	@FindBy(xpath = ".//input[@placeholder = 'Search...']")
	private WebElement search_input;
	private By search_input1 = By.xpath(".//input[@placeholder = 'Search...']");

	private By user_found1 = By.xpath(".//a[@title='Ludovika BcvaxLimeburn']");

	private By select_Imms_record1 = By.xpath(".//th//lightning-primitive-cell-factory[@data-label='Immunization Record']//div[@class='slds-grid']//span[@force-lookup_lookup='']");

	private By imms_drop_down1 = By.xpath("//*[@id=\"brandBand_2\"]/div/div/div[4]/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-b-c-h_-clinical_-pathway_-record_-page___-case___-v-i-e-w/forcegenerated-flexipage_bch_clinical_pathway_record_page_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/flexipage-component2[2]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_case___0125w0000004kq6qam___compact___view___recordlayout2/records-highlights2/div[1]/div[1]/div[3]/div/runtime_platform_actions-actions-ribbon/ul/li[4]/lightning-button-menu/button");

	private By imms_drop_down_del1 = By.xpath(".//a[@runtime_platform_actions-ribbonmenuitem_ribbonmenuitem='']");

	private By delete_record_button1 = By.xpath("//span[@dir='ltr'][text()='Delete']");

	private By select_rern_record1 = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[2]/div[2]/div[1]/div/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-b-c-h_-participant_-record_-page___-account___-v-i-e-w/forcegenerated-flexipage_bch_participant_record_page_account__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[2]/div[1]/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[2]/slot/flexipage-component2[5]/slot/lst-related-list-container/div/div[8]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list-internal/div/div/lst-primary-display-manager/div/lst-primary-display/lst-primary-display-grid/lst-customized-datatable/div[2]/div/div/table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-primitive-custom-cell/force-lookup/div/records-hoverable-link/div");

	private By delete_rern_record1 = By.xpath("//*[@id='brandBand_2']/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___flexipage__default_rec_-l___-d-d-h__-h-c_-rules_-engine_-response__c___-v-i-e-w/forcegenerated-flexipage_default_rec_l_ddh__hc_rules_engine_response__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/flexipage-component2/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_ddh__hc_rules_engine_response__c___012000000000000aaa___compact___view___recordlayout2/records-highlights2/div[1]/div/div[3]/div/runtime_platform_actions-actions-ribbon/ul/li[2]/runtime_platform_actions-action-renderer/runtime_platform_actions-executor-aura-legacy/slot/slot/lightning-button/button");

	private By delete_person_account1 = By.xpath("//button[text()='Delete']");

	private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");

	private By click_cib_app1 = By.xpath("//p[text()='Clinic in a Box (IPM)']");

	private By click_healthconnect_app1 = By.xpath("//p[text()='Health Connect - Supply Console']");

	private By click_ice_app1 = By.xpath("//p[text()='In-Clinic Experience']");


	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']")
	private WebElement select_desired_supply_loc;

	@FindBy(xpath = "(//span[text()='COVID-19 mRNA'])[1]")
	private WebElement select_agent_name;
	private By select_agent_name1 = By.xpath("(//span[text()='COVID-19 mRNA'])[1]");

	private By supply_console_App_displayed1 = By.xpath(".//span[@title='Health Connect - Supply Console']");

	private By ice_App_displayed1 = By.xpath(".//span[@title='In-Clinic Experience']");

	private By get_remaining_doses1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Doses']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

	private By get_remaining_Qty1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Quantity']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

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

	@FindBy(xpath = "//input[@name='clinicstag']")
	private WebElement select_clinic;

	@FindBy(xpath = "(.//button[@name = 'facility'][1])")
	private WebElement option_loc_facility;

	@FindBy(xpath = "//button[contains(text(),'Cancel Appointment')]")
	private WebElement btnCancelAppointment;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	private WebElement btnYes;

	@FindBy(xpath = "//button[text()='Close Window']")
	private WebElement btnCloseWindowAppointment;

	@FindBy(xpath = "//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']")
	private WebElement appointment_status_cancel;
	private By appointment_status_cance1 = By.xpath("//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']");

	@FindBy(xpath = "//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']")
	private WebElement appointment_status_confirm;
	private By appointment_status_confirm1 = By.xpath("//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']");
	private By appointment_status_confirm_CP = By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//span[text()='Confirmed']");

	@FindBy(xpath = "(//SPAN[@class='slds-page-header__title slds-truncate'][text()='Immunization Records (1)']/../../../../../../../../..//LIGHTNING-BASE-FORMATTED-TEXT)[2]")
	private WebElement status_after_care;
	private By status_after_care1 = By.xpath("(//SPAN[@class='slds-page-header__title slds-truncate'][text()='Immunization Records (1)']/../../../../../../../../..//LIGHTNING-BASE-FORMATTED-TEXT)[2]");

	@FindBy(xpath = ".//h1[text() = 'Oops...']")
	private WebElement vlidate_oops_message;

	@FindBy(xpath = "//*[@class='slds-icon slds-icon_large']")
	private WebElement close_button_diwa;

	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement confirm_admin_another_vaccine_modal_screen_btn;

	@FindBy(xpath = "(//button[@title='Save Administration & Record Another Vaccine'])")
	private WebElement save_and_record_another_vaccine_btn;

	@FindBy(xpath = ".//div[@class = 'slds-form-element__control']//span[text() = 'Influenza Vaccine']")
	private WebElement click_on_influenza_vaccination_checkbox;

	private By search_by_clinic_name_tab1 = By.xpath(".//a[text()='Search by clinic name']");

	@FindBy(xpath = ".//button[@aria-label = 'Route, Select an Option']")
	private WebElement click_route_dropdown;

	@FindBy(xpath = ".//span[text() = 'Intranasal']")
	private WebElement select_route_intranasal_dropdown;

	@FindBy(xpath = ".//button[@name='dosePicklist']")
	private WebElement click_dosage_dropdown;
	private By click_dosage_dropdown1 = By.xpath(".//button[@name='dosePicklist']");

	@FindBy(xpath = ".//span[@title='0.2']")
	private WebElement select_dosage_from_dropdown;
	private By select_dosage_from_dropdown1 = By.xpath(".//span[@title='0.2']");


	@FindBy(xpath = ".//button[@name = 'injectionSite']")
	private WebElement click_site_dropdown;

	@FindBy(xpath = ".//span[text() = 'Arm - Left deltoid']")
	private WebElement select_site_left_deltoid_from_dropdown;

	@FindBy(xpath = ".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']")
	private WebElement click_lot_number_dropdown;

	@FindBy(xpath = ".//li[@title = 'T005729-CC07 - Exp. 2021 November 16']")
	private WebElement select_pneumo_lot_number_dropdown;

	@FindBy(xpath = ".//h1[text() = 'Client Search']")
	private WebElement validate_home_client_search_page_open;

	private By click_select_search_clinic1 = By.xpath(".//a[text()='Search by clinic name']");

	@FindBy(xpath = "//span[@title='Appointments']")
	private WebElement appointmentSection;

	private By selectSite1 = By.xpath("//button[@name='injectionSite']");

	@FindBy(xpath = "//li[@title='300042698 - Exp. 2021 June 18']")
	private WebElement select_lot;

	private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");

	private By select_dosage_field1 = By.xpath("//button[@name='dosePicklist']");

	private By select_dosage1 = By.xpath("//span[@title='0.5']");

	@FindBy(xpath = "//button[@title='Confirm & Save Administration']")
	private WebElement confirmAndSave;

	@FindBy(xpath = "//button[contains(text(),'Confirm and Save')]")
	private WebElement lastConfirmAndSave;

	@FindBy(xpath = ".//button[text() = 'Save']")
	private WebElement save_immune_info_btn;

	@FindBy(xpath = ".//lightning-base-formatted-text[text() = 'Influenza-LAIV']")
	private WebElement validate_create_immunization_record_Influenza;
	private By validate_create_immunization_record_Influenza_ = By.xpath(".//lightning-base-formatted-text[text() = 'Influenza-LAIV']");

	@FindBy(xpath = "(.//lightning-base-formatted-text[text() = 'After Care'])[2]")
	private WebElement validate_after_care_status_immunization_record_Influenza;
	private By validate_after_care_status_immunization_record_Influenza_ = By.xpath("(.//lightning-base-formatted-text[text() = 'After Care'])[2]");

	@FindBy(xpath = "(.//lightning-base-formatted-text[text() = 'After Care'])[1]")
	private WebElement validate_after_care_status_immunization_record_Pneumo;
	private By validate_after_care_status_immunization_record_Pneumo_ = By.xpath("(.//lightning-base-formatted-text[text() = 'After Care'])[2]");

	@FindBy(xpath = "//label[text() = 'Date']/../../../lightning-formatted-text")
	private WebElement appointmentDate;

	@FindBy(xpath = "//label[text() = 'Appointment Time']/../../../lightning-formatted-text")
	private WebElement appointmentTime;

	@FindBy(xpath = "//label[text() = 'Clinic Name']/../../../lightning-formatted-text")
	private WebElement appointmentLocation;

	@FindBy(xpath = "//span[@title and contains(text(), 'Appointments')]")
	private WebElement appointmentsRecordsTitle;

	Tables tables;

	/*---------Constructor-------*/
	public InClinicExperiencePage(WebDriver driver) {

		super(driver);
		tables = new Tables(driver);
	}

	/*-------------Methods--------------*/
	public void SearchForCitizen(String citizen) throws InterruptedException {
		waitForElementToBeVisible(driver, search_assistant, 10);
		Thread.sleep(2000);
		WebElement search_navigator = driver.findElement(search_assistant1);
		Thread.sleep(3000);
		search_navigator.click();
		Thread.sleep(3000);
		waitForElementToBeVisible(driver, search_input, 10);
		Thread.sleep(2000);
		WebElement search_input = driver.findElement(search_input1);
		search_input.sendKeys(citizen);
		Thread.sleep(2000);
		search_input.sendKeys(Keys.RETURN);
		Thread.sleep(5000);
	}

	public void SearchForCitizen_CP(String citizen) throws InterruptedException {
		waitForElementToBeVisible(driver, search_input, 10);
		Thread.sleep(2000);
		WebElement search_input = driver.findElement(search_input1);
		search_input.sendKeys(citizen);
		Thread.sleep(2000);
		search_input.sendKeys(Keys.RETURN);
		Thread.sleep(5000);
	}

	public void clickRegisterTab() throws InterruptedException {
		waitForElementToBeLocated(driver, register_tab1, 10);
		WebElement element = driver.findElement(register_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickRegisterButtonOnConfirmationPage() throws InterruptedException {
		Thread.sleep(500);
		By register_btn_path = By.xpath("//button[text() = 'Register']");
		waitForElementToBeEnabled(driver, register_btn_path, 10);
		WebElement register_btn = driver.findElement(register_btn_path);
		register_btn.click();
		Thread.sleep(500);
//		try {
//			clickCloseAlert();
//		} catch(Exception ex) {
//			System.out.println("No alert message found. Continue...");
//		}
//		Thread.sleep(500);
	}

	public boolean userFound() throws InterruptedException {
		if (!isDisplayed(user_found1)) {
			return false;
		}
		waitForElementToBeLocated(driver, user_found1, 10);
		WebElement element = driver.findElement(user_found1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		return true;
	}

	public boolean userFoundWithParameters(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
		Thread.sleep(500);
		By user_link_path = By.xpath("//a[@title='" + legalFirstName + " " + legalMiddleName + " " + legalLastName + "']");
		waitForElementToBeEnabled(driver, user_link_path, 10);
		WebElement user_link = driver.findElement(user_link_path);
		if (!isDisplayed(user_link_path)) {
			return false;
		}
		user_link.click();
		return true;
	}

	public void successMessageAppear() throws InterruptedException {
		Thread.sleep(500);
		By message_path = By.xpath("//div[text() = 'Success'] | //h2[@c-bchcvacinnepreregistrationinternal_bchcvacinnepreregistrationinternal and text() = 'Match Unsuccessful']");


		log("  -- success message has been Appears. Closing... - /");
		try {

			waitForElementToBeEnabled(driver, message_path, 10);
			String message = driver.findElement(message_path).getText();
//		Assert.assertEquals(message, "Success", "Expected PHN Match Success but found '" + message + "'");
			clickCloseAlert();
			Thread.sleep(500);
		} catch(Exception ex) {
			System.out.println("Probably alert already closed. Continue...");
		}
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

	public boolean selectImmsRecord() throws InterruptedException {
		//To scroll down the page to see Imms Record
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)", "");
		Thread.sleep(5000);
		if (!isDisplayed(select_Imms_record1)) {
			return false;
		}
		waitForElementToBeLocated(driver, select_Imms_record1, 10);
		WebElement element = driver.findElement(select_Imms_record1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return true;
	}

	public void userClickCitizen(String name) throws InterruptedException {
		Thread.sleep(500);
		By my_profile_path = By.xpath("//div[@aria-label = 'Profiles||List View']//a[@title=\"" + name + "\"]");
		waitForElementToBeEnabled(driver, my_profile_path, 10);
		WebElement my_profile = driver.findElement(my_profile_path);
		my_profile.click();
	}

	public void userClickCitizenNew_CP(String name) throws InterruptedException {
		WebElement fullNameWebElement = driver.findElement(By.xpath("(//a[@title = '" + name + "'])[1]"));
		waitForElementToBeVisible(driver, fullNameWebElement, 10);
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", fullNameWebElement);
	}

	public void deleteImmsRecord() throws InterruptedException {
		waitForElementToBeLocated(driver, imms_drop_down1, 10);
		WebElement element = driver.findElement(imms_drop_down1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		waitForElementToBeLocated(driver, imms_drop_down_del1, 10);
		WebElement element1 = driver.findElement(imms_drop_down_del1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(5000);
		waitForElementToBeLocated(driver, delete_record_button1, 10);
		WebElement element2 = driver.findElement(delete_record_button1);
		JavascriptExecutor executor2 = (JavascriptExecutor) driver;
		executor2.executeScript("arguments[0].click();", element2);
	}

	public boolean selectRERNRecord() throws InterruptedException {
		//To scroll down the page to see RERN Record
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)", "");
		Thread.sleep(5000);
		if (!isDisplayed(select_rern_record1)) {
			return false;
		}
		waitForElementToBeLocated(driver, select_rern_record1, 10);
		WebElement element = driver.findElement(select_rern_record1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return true;
	}

	public void deleteRERNRecord() throws InterruptedException {
		waitForElementToBeLocated(driver, delete_rern_record1, 10);
		WebElement element = driver.findElement(delete_rern_record1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		waitForElementToBeLocated(driver, delete_record_button1, 10);
		WebElement element1 = driver.findElement(delete_record_button1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
	}

	public void deletePersonAccount() throws InterruptedException {
		waitForElementToBeLocated(driver, delete_person_account1, 10);
		WebElement element = driver.findElement(delete_person_account1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		waitForElementToBeLocated(driver, delete_record_button1, 10);
		WebElement element1 = driver.findElement(delete_record_button1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
	}

	public void closeOpenTabs() throws InterruptedException {
		do {
			WebElement closetab = driver.findElement(By.xpath("//*[@data-key='close'][@class='slds-icon slds-icon-text-default slds-icon_xx-small']"));
			closetab.click();
			Thread.sleep(2000);
		} while (isDisplayed(By.xpath("//*[@data-key='close'][@class='slds-icon slds-icon-text-default slds-icon_xx-small']")));
	}

	public void closeTabsHCA() throws InterruptedException {
		do {
			try {
				WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
				closetab.click();
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				System.out.println("/*---there are no Tab's to close");
			}

		} while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));

	}

	public void selectHealthConnectApp() throws InterruptedException {
		waitForElementToBeLocated(driver, select_app_launcher1, 10);
		Thread.sleep(5000);
		WebElement element = driver.findElement(select_app_launcher1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		waitForElementToBeLocated(driver, click_healthconnect_app1, 10);
		WebElement element1 = driver.findElement(click_healthconnect_app1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
	}

	public void selectIceApp() throws InterruptedException {
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

	public void selectSupplyLocationName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_loc, 10);
		Thread.sleep(2000);
		this.select_desired_supply_loc.click();
	}

	public void selectSupplyLocationName(String supplyLocation) throws InterruptedException {
		Thread.sleep(500);
		By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Locations']");
		waitForElementToBeLocated(driver, select_list_view_btn_path, 10);
		WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
		select_list_view_btn.click();
		Thread.sleep(500);
		By active_supply_location_path = By.xpath("//a/span[text() = 'Active Supply Locations']");
		waitForElementToBeLocated(driver, active_supply_location_path, 10);
		driver.findElement(active_supply_location_path).click();
		Thread.sleep(500);
		By search_location_field_path = By.xpath("//input[@name = 'HC_Supply_Location__c-search-input']");
		waitForElementToBeLocated(driver, search_location_field_path, 10);
		WebElement search_location_field = driver.findElement(search_location_field_path);
		search_location_field.sendKeys(supplyLocation);
		search_location_field.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		tables.clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, supplyLocation));
	}

	public boolean displaySupplyConsolePage() {
		return isDisplayed(supply_console_App_displayed1);
	}

	public boolean displayIceApp() {
		return isDisplayed(ice_App_displayed1);
	}

	public Double getValueOfRemainingDoses() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_doses1, 10);
		WebElement element = driver.findElement(get_remaining_doses1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public void clickToSearchClinic() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_select_search_clinic1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_select_search_clinic1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public Double getValueOfRemainingQty() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty1, 10);
		WebElement element = driver.findElement(get_remaining_Qty1);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public void selectICEFromApp() throws InterruptedException {
		waitForElementToBeLocated(driver, select_app_launcher1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(select_app_launcher1);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		waitForElementToBeLocated(driver, click_ice_app1, 10);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(click_ice_app1);
		Thread.sleep(2000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
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

	public void clickRegisterButton() throws InterruptedException {
		Thread.sleep(500);
		By register_btn_path = By.xpath("//button[@title = ' Create New Profile']");
		waitForElementToBeEnabled(driver, register_btn_path, 30);
		WebElement register_btn = driver.findElement(register_btn_path);
		register_btn.click();
	}

	public void clickSaveDefaultsButton() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_save_defaults_button, 10);
		WebElement element = driver.findElement(click_save_defaults_button_);
		click_save_defaults_button.click();
		Thread.sleep(2000);
		clickCloseAlert();
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
	}

	public void clickNonIndigenousRadioButton() throws InterruptedException {
		Thread.sleep(500);
		By non_indigenous_radio_button_path = By.xpath("(//input[@name = 'BCH_Indigenous'])[2]");
		waitForElementToBeEnabled(driver, non_indigenous_radio_button_path, 10);
		WebElement non_indigenous_radio_button = driver.findElement(non_indigenous_radio_button_path);
		non_indigenous_radio_button.click();
	}

	public void clickVerifyPHNButton() throws InterruptedException {
		Thread.sleep(500);
		By verify_phn_button_path = By.xpath("//button[@title = 'Verify Personal Health Number']");
		waitForElementToBeEnabled(driver, verify_phn_button_path, 30);
		WebElement verify_phn_button = driver.findElement(verify_phn_button_path);
		verify_phn_button.click();
	}

	public void clickNextButton() throws InterruptedException {
		Thread.sleep(500);
		By next_btn_path = By.xpath("//button[@title='Next']");
		waitForElementToBeEnabled(driver, next_btn_path, 10);
		WebElement next_btn = driver.findElement(next_btn_path);
		scrollIfNeeded(driver, next_btn);
		Thread.sleep(500);
		next_btn.click();
	}

	public void enterEmail(String enteremail) throws InterruptedException {
		Thread.sleep(500);
		By email_field_path = By.xpath("//input[@name = 'PersonEmail']");
		waitForElementToBeEnabled(driver, email_field_path, 10);
		WebElement email_field = driver.findElement(email_field_path);
		try {
			email_field.sendKeys(enteremail);
		} catch (ElementNotInteractableException ex) {
			Thread.sleep(2000);
			email_field = driver.findElement(email_field_path);
			email_field.sendKeys(enteremail);
		}
	}

	public void confirmEmail(String confirmemail) throws InterruptedException {
		By confirm_email_field_path = By.xpath("//input[@name = 'ConfirmEmail']");
		waitForElementToBeEnabled(driver, confirm_email_field_path, 10);
		WebElement confirm_email_field = driver.findElement(confirm_email_field_path);
		confirm_email_field.sendKeys(confirmemail);
	}

	public void clickReviewDetails() throws InterruptedException {
		By review_details_btn_path = By.xpath("//button[text()= 'Review Details']");
		waitForElementToBeEnabled(driver, review_details_btn_path, 10);
		WebElement review_details_btn = driver.findElement(review_details_btn_path);
		review_details_btn.click();
	}

	public void navigateToVaccineSchedulingTab() throws InterruptedException {
		try {
			PersonAccountPage.goToVaccineScheduleTab(driver);
		} catch(NotFoundException ex) {
			System.out.println("Vaccine Scheduling tab not found. Try Appointment Scheduling Tab...");
			PersonAccountPage.goToAppointmentScheduleTab(driver);
		} catch(ElementClickInterceptedException ex) {
			Thread.sleep(2000);
			PersonAccountPage.goToVaccineScheduleTab(driver);
		}
	}

	public void clickOnVaccinationCheckbox() throws InterruptedException {
		PersonAccountPage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
	}

	public void clickOnVaccinationInfluenzaCheckbox() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, click_on_influenza_vaccination_checkbox, 10);
		Thread.sleep(5000);
		click_on_influenza_vaccination_checkbox.click();
	}

	public void selectBookingAppointmentDay() throws InterruptedException {
		Thread.sleep(500);
		By booking_days_path = By.xpath("//button[@class = 'slds-day active-day']");
		waitForElementToBeEnabled(driver, booking_days_path, 10);
		List<WebElement> booking_days = driver.findElements(booking_days_path);
		scrollCenter(booking_days.get(0));
		booking_days.get(0).click();
	}

	public void selectBookingAppointmentDay(int day) throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> myDays = driver.findElements(By.xpath("//button[@class = 'slds-day active-day']"));
		scrollCenter(driver, myDays.get(day));
		myDays.get(day).click();
	}


	public void selectTimeSlotForAppointment() throws InterruptedException {
		Thread.sleep(500);
		By time_slot_appointment_path = By.xpath("//button[@name='timeslot']");
		waitForElementToBeEnabled(driver, time_slot_appointment_path, 10);
		List<WebElement> elements = driver.findElements(time_slot_appointment_path);
		scrollCenter(elements.get(0));
		Thread.sleep(500);
		elements.get(0).click();
	}

	public void clickNextButtonApptSchedulingPage() throws InterruptedException {
		Thread.sleep(500);
		By next_btn_path = By.xpath("//button[@c-bchappointmentscheduler_bchappointmentscheduler and text()='Next']");
		waitForElementToBeEnabled(driver, next_btn_path, 10);
		WebElement next_btn = driver.findElement(next_btn_path);
		next_btn.click();
		//Wait for Confirm button to appear
		Thread.sleep(500);
		By appt_confirm_btn_path = By.xpath("//button[text() = 'Confirm appointment']");
		waitForElementToBeEnabled(driver, appt_confirm_btn_path, 10);
	};


	public void clickAppointmentConfirmButton() throws InterruptedException {
		Thread.sleep(500);
		By appt_confirm_btn_path = By.xpath("//button[text() = 'Confirm appointment']");
		waitForElementToBeEnabled(driver, appt_confirm_btn_path, 10);
		WebElement appt_confirm_btn = driver.findElement(appt_confirm_btn_path);
		appt_confirm_btn.click();
	}

	public boolean AppointmentConfirmationMessage() throws InterruptedException {
		Thread.sleep(500);
		try {
			By appointment_confirm_message_path = By.xpath("//div[@role = 'heading']/h1[text() = 'Appointment confirmed!']");
			waitForElementToBeLocated(driver, appointment_confirm_message_path, 30);
			System.out.println("/*---'Appointment confirmed!' message shown up");
			return true;
		} catch (TimeoutException e) {
			System.out.println("/*---the screen does not show up 'Appointment confirmed!'");
			return false;
		}
	}

	public void ClickGoToInClinicExperienceButton() throws InterruptedException {
		Thread.sleep(500);
		By in_clinic_experience_app_path = By.xpath("//button[@name='navigateToICE' and @aria-disabled='false']");
		waitForElementToBeEnabled(driver, in_clinic_experience_app_path, 30);
		WebElement in_clinic_experience_app = driver.findElement(in_clinic_experience_app_path);
		in_clinic_experience_app.click();
		Thread.sleep(500);
	}

	public void ClickRebookAppointment() throws InterruptedException {
		Thread.sleep(500);
		By rebook_at_current_location_btn_path = By.xpath("//button[text() = 'Rebook at Current Location']");
		waitForElementToBeEnabled(driver, rebook_at_current_location_btn_path, 10);
		WebElement rebook_at_current_location_btn = driver.findElement(rebook_at_current_location_btn_path);
		rebook_at_current_location_btn.click();
	}

	public void ValidateClickRebookAppointmentButtonIsDisabled() throws InterruptedException {
		Thread.sleep(500);
		By rebook_at_current_location_btn_path = By.xpath("//button[text() = 'Rebook at Current Location']");
		waitForElementToBeLocated(driver, rebook_at_current_location_btn_path, 10);
		WebElement rebook_at_current_location_btn = driver.findElement(rebook_at_current_location_btn_path);
		boolean is_enabled = true;
		while(is_enabled) {
			is_enabled = rebook_at_current_location_btn.isEnabled();
			Thread.sleep(500);
			rebook_at_current_location_btn = driver.findElement(rebook_at_current_location_btn_path);
		}
	}

	public void ValidateAppointmentCancelledIsPresentCP() throws InterruptedException {
		Thread.sleep(500);
		By appointment_status_cancel_CP_path = By.xpath("//span[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//span[text()='Cancelled']");
		waitForElementToBeEnabled(driver, appointment_status_cancel_CP_path, 30);
		WebElement element = driver.findElement(appointment_status_cancel_CP_path);
		element.isDisplayed();
	}

	public void ValidateAppointmentConfirmIsPresentCP() throws InterruptedException {
		WebElement element = driver.findElement(appointment_status_confirm_CP);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(1000);
		element.isDisplayed();
	}

	public void ValidateAppointmentCancelledIsPresent() throws InterruptedException {
		WebElement element = driver.findElement(appointment_status_cance1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		appointment_status_cancel.isDisplayed();
	}

	public void ValidateAppointmentConfirmIsPresent() throws InterruptedException {
		WebElement element = driver.findElement(appointment_status_confirm1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		appointment_status_confirm.isDisplayed();
	}

	public String ValidateStatusisInAftercare() throws InterruptedException {
		waitForElementToBeLocated(driver, status_after_care1, 10);
		status_after_care.isDisplayed();
		status_after_care.getText();
		return status_after_care.getText();
	}

	public String getAppointmentClinicName() throws InterruptedException {
		By appointment_clinic_name_path = By.xpath("//c-bc-hc-appointment-details//label[@aria-label='Clinic Name']/../../..//lightning-formatted-text");
		waitForElementToBeLocated(driver, appointment_clinic_name_path, 10);
		WebElement appointment_clinic_name = driver.findElement(appointment_clinic_name_path);
		String clinic_name = appointment_clinic_name.getText();
		return clinic_name;
	}

	public void HomePageClickConfirmAndSaveButton() throws InterruptedException {
		Thread.sleep(500);
		By confirm_and_save_btn_path = By.xpath("(//button[@title='Confirm & Save Identification'])");
//		try {
//			waitForElementToBeEnabled(driver, confirm_and_save_btn_path, 10);
//		} catch(NotFoundException ex) {
//			System.out.println(ex.getMessage());
//			System.out.println("Confirm and Save button is not available. Try to Rebook at current Location button");
//			By rebook_btn_path = By.xpath("//button[text() ='Rebook at Current Location']");
//			waitForElementToBeEnabled(driver, rebook_btn_path, 10);
//			WebElement rebook_btn = driver.findElement(rebook_btn_path);
//			rebook_btn.click();
//			Thread.sleep(500);
//		}
		waitForElementToBeEnabled(driver, confirm_and_save_btn_path, 10);
		WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
		scrollCenter(confirm_and_save_btn);
		Thread.sleep(500);
		confirm_and_save_btn.click();
	}

	public void clickVerifyContactInformation() throws InterruptedException {
		Thread.sleep(500);
		By verify_contact_information_checkbox_path = By.xpath("//span[text()='I verify that the contact information (email address and phone number) entered is accurate and up to date.']/../span[@class='slds-checkbox_faux']");
		waitForElementToBeEnabled(driver, verify_contact_information_checkbox_path, 10);
		WebElement verify_contact_information_checkbox = driver.findElement(verify_contact_information_checkbox_path);
		scrollIfNeeded(driver, verify_contact_information_checkbox);
		Thread.sleep(500);
		try {
			verify_contact_information_checkbox.click();
		} catch(ElementClickInterceptedException ex) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
			Thread.sleep(500);
			verify_contact_information_checkbox.click();
		}
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

	public void clickTodayAppointmentCaseViewButton() throws InterruptedException {
		By view_case_btn_path = By.xpath("//button[@title = 'View Case']");
		waitForElementToBeEnabled(driver, view_case_btn_path, 10);
		List<WebElement> todayAppointments = driver.findElements(By.xpath("//button[@title = 'View Case']"));
		WebElement myAppointment = todayAppointments.get(todayAppointments.size() - 1);
		myAppointment.click();
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


	public String selectConsentProvider() throws InterruptedException {
		Thread.sleep(500);
		By consent_provider_field_path = By.xpath("//input[contains(@class, 'slds-combobox__input slds-input')]");
		By consent_provider_item_path = By.xpath("//span[@class = 'slds-listbox__option-text slds-listbox__option-text_entity']");
		waitForElementToBeEnabled(driver, consent_provider_field_path, 10);
		WebElement consentProviderField = driver.findElement(consent_provider_field_path);
		scrollCenter(consentProviderField);
		Thread.sleep(500);
		consentProviderField.click();
		String provider = "Auto Bchcomclinician";
		consentProviderField.sendKeys(provider);
		Thread.sleep(1000);
		By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
		waitForElementToBeLocated(driver, providerItemPath, 10);
		driver.findElement(providerItemPath).click();
		Thread.sleep(500);
		try {
			String consent_provider_selected = consentProviderSelected();
			if(consent_provider_selected.equals("")) {
				waitForElementToBeEnabled(driver, consent_provider_item_path, 10);
				driver.findElement(consent_provider_item_path).click();
			}
		} catch(Exception ex) {
			System.out.println("***DEBUG*** Tried to select Consent Provider. Error: " + ex.getMessage());
			waitForElementToBeEnabled(driver, consent_provider_field_path, 10);
			consentProviderField = driver.findElement(consent_provider_field_path);
			scrollCenter(consentProviderField);
			consentProviderField.click();
			Thread.sleep(500);
			waitForElementToBeEnabled(driver, consent_provider_item_path, 10);
			driver.findElement(consent_provider_item_path).click();
		}
		Thread.sleep(2000);
		return driver.findElement(By.xpath("//input[contains(@class, 'slds-combobox__input slds-input')]")).getAttribute("data-value");
	}

	public void selectVaccineAgent(String agent) throws InterruptedException {
		Thread.sleep(500);
		By vaccine_agent_dropdown_path = By.xpath("//button[@aria-label = 'Agent, Select an option' or @aria-label='Agent - Current Selection: Select an option']");
		waitForElementToBeEnabled(driver, vaccine_agent_dropdown_path, 30);
		WebElement click_vaccine_agent_dropdown = driver.findElement(vaccine_agent_dropdown_path);
		scrollCenter(click_vaccine_agent_dropdown);
		click_vaccine_agent_dropdown.click();
		Thread.sleep(500);
		By my_vaccine_path = By.xpath("//span[text() = '" + agent + "']");
		waitForElementToBeEnabled(driver, my_vaccine_path, 10);
		WebElement my_vaccine = driver.findElement(my_vaccine_path);
		try {
			my_vaccine.click();
		} catch(Exception ex) {
			click_vaccine_agent_dropdown.click();
			waitForElementToBeEnabled(driver, my_vaccine_path, 10);
			my_vaccine = driver.findElement(my_vaccine_path);
			my_vaccine.click();
		}
	}

	public void selectVaccineAgentValue(String vaccineAgent) throws InterruptedException {
		selectVaccineAgent(vaccineAgent);
	}

	public void selectVaccineAgent() throws InterruptedException {
		selectVaccineAgent("COVID-19 mRNA");
	}

	public void selectVaccineAgentInfluenza() throws InterruptedException {
		selectVaccineAgent("Influenza-LAIV");
	}

	public void selectVaccineAgentPneumo() throws InterruptedException {
		selectVaccineAgent("Pneumo-P-23");
	}

	public void selectVaccineAgentPneumo_after_Influenza_CP() throws InterruptedException {
		selectVaccineAgent("Pneumo-P-23");
	}

	public void selectRouteIntranasal() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		//Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_route_dropdown, 10);
		Thread.sleep(2000);
		click_route_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_route_intranasal_dropdown, 10);
		Thread.sleep(2000);
		select_route_intranasal_dropdown.click();
	}

	public void selectPneumoLotNumber() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_lot_number_dropdown, 10);
		Thread.sleep(2000);
		click_lot_number_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_pneumo_lot_number_dropdown, 10);
		Thread.sleep(2000);
		select_pneumo_lot_number_dropdown.click();
	}

	public void selectSiteLeftDeltoid() throws InterruptedException {
		waitForElementToBeVisible(driver, click_site_dropdown, 10);
		Thread.sleep(2000);
		click_site_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_site_left_deltoid_from_dropdown, 10);
		Thread.sleep(2000);
		select_site_left_deltoid_from_dropdown.click();
	}

	public void ClickSaveConsentButton() throws InterruptedException {
		Thread.sleep(500);
		By save_consent_btn_path = By.xpath("//button[normalize-space()='Save Consent']");
		waitForElementToBeEnabled(driver, save_consent_btn_path, 10);
		WebElement save_consent_btn = driver.findElement(save_consent_btn_path);
		scrollIfNeeded(driver, save_consent_btn);
		Thread.sleep(1000);
		save_consent_btn.click();
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

	public void ClickSaveAdministratorAndRecordAnotherVaccineButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, save_and_record_another_vaccine_btn, 10);
		Thread.sleep(2000);
		save_and_record_another_vaccine_btn.click();
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

	public void ClickConfirmAdminAnotherVaccineModalScreenButton() throws InterruptedException {
		waitForElementToBeVisible(driver, confirm_admin_another_vaccine_modal_screen_btn, 10);
		Thread.sleep(2000);
		confirm_admin_another_vaccine_modal_screen_btn.click();
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

	public void successRegisteredMessageAppear() throws InterruptedException {
		Thread.sleep(500);
		boolean alert_found = AlertDialog.alertFound(driver);
		WebElement alert_content = AlertDialog.getAlertContent(driver);
		String alert_text = alert_content.getText();
		Assert.assertTrue(alert_text.contains( "Citizen Successfully Registered"));
		try {
			AlertDialog.closeAlert(driver);
		} catch(ElementClickInterceptedException ex) {
			AlertDialog.closeAlert(driver);
		}
	}

	public void clickOnPersonAccountRelatedTab() throws InterruptedException {
		PersonAccountPage.goToRelatedTab(driver);
	}

	public void selectSearchByClinicNameTab() throws InterruptedException {
		waitForElementToBeLocated(driver, search_by_clinic_name_tab1, 10);
		WebElement element = driver.findElement(search_by_clinic_name_tab1);
		scrollCenter(element);
		Thread.sleep(1000);
		int footerSize = driver.findElement(By.xpath("//div[@class = 'bch-scheduler-footer']")).getSize().getHeight();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + footerSize + ")");
		element.click();
	}

	public void searchClinicName(String clinicNameToSearch) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_clinic, 10);
		Thread.sleep(2000);
		select_clinic.click();
		Thread.sleep(2000);
		select_clinic.sendKeys(clinicNameToSearch);
		select_clinic.sendKeys(Keys.RETURN);
	}

	public void clickOnFacilityOptionLocation() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, option_loc_facility, 10);
		scrollCenter(option_loc_facility);
		option_loc_facility.click();
	}

	public void refreshBrowser() throws InterruptedException {
		driver.navigate().refresh();
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

	public boolean validateHomePageShownUp() throws InterruptedException {
		try {
			waitForElementToBeVisible(driver, validate_home_client_search_page_open, 10);
			System.out.println("/*---Home page-Client Search page shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---no Home page-Client Search page show up");
			return false;
		}
	}

	public void SelectAgentValue() throws InterruptedException {
		waitForElementToBeVisible(driver, select_agent_name, 10);
		WebElement search_input = driver.findElement(select_agent_name1);
		search_input.click();
	}

	public void ValidateCreateImmunizationRecordInfluenzaDisplayed() throws InterruptedException {
		waitForElementToBeLocated(driver, validate_create_immunization_record_Influenza_, 10);
		Thread.sleep(2000);
		validate_create_immunization_record_Influenza.isDisplayed();
	}

	public void ValidateAfterCareStatusImmunizationRecordInfluenza() throws InterruptedException {
		waitForElementToBeLocated(driver, validate_after_care_status_immunization_record_Influenza_, 10);
		Thread.sleep(2000);
		validate_after_care_status_immunization_record_Influenza.isDisplayed();
	}

	public void ValidateCreateImmunizationRecordPneumoDisplayed() throws InterruptedException {
		waitForElementToBeLocated(driver, validate_create_immunization_record_Influenza_, 10);
		Thread.sleep(2000);
		validate_create_immunization_record_Influenza.isDisplayed();
	}

	public void ValidateAfterCareStatusImmunizationRecordPneumo() throws InterruptedException {
		waitForElementToBeLocated(driver, validate_after_care_status_immunization_record_Pneumo_, 10);
		Thread.sleep(2000);
		validate_after_care_status_immunization_record_Pneumo.isDisplayed();
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

	public void selectLot() throws InterruptedException {
		waitForElementToBeVisible(driver, select_lot, 10);
		Thread.sleep(2000);
		select_lot.click();
	}

	public void selectDosageDIWA() throws InterruptedException {
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

	public void selectDosageVaccineAdmin() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,450)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_dosage_dropdown1, 10);
		Thread.sleep(2000);
		click_dosage_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_dosage_from_dropdown1, 10);
		Thread.sleep(2000);
		select_dosage_from_dropdown.click();
		Thread.sleep(2000);
	}


	public void saveImmunizationInformation() throws InterruptedException {
		By save_immunization_btn_path = By.xpath("//div[@c-bchcimmunizationinfo_bchcimmunizationinfo]/lightning-button[@c-bchcimmunizationinfo_bchcimmunizationinfo]/button[@type='submit' and text()='Save']");
		waitForElementToBeEnabled(driver, save_immunization_btn_path, 10);
		WebElement save_immunization_btn = driver.findElement(save_immunization_btn_path);
		scrollIfNeeded(driver, save_immunization_btn);
		save_immunization_btn.click();
	}

	public void confirmAndSaveAdministration() throws InterruptedException {
		confirmAndSave.click();
	}

	public void summaryConfirmAndSave() throws InterruptedException {
		lastConfirmAndSave.click();
	}

	public void clickShowAllLotNumbersCheckBox() throws InterruptedException {
		By show_all_chkbox_path = By.xpath("//span[@part='label' and text()='Show all lot numbers.']/../..");
		waitForElementToBeEnabled(driver, show_all_chkbox_path, 10);
		WebElement show_all_lot_numbers_checkbox = driver.findElement(show_all_chkbox_path);
		scrollIfNeeded(driver, show_all_lot_numbers_checkbox);
		Thread.sleep(1000);
		show_all_lot_numbers_checkbox.click();
	}

	public void clickToClose() throws InterruptedException {
		waitForElementToBeVisible(driver, close_button_diwa, 10);
		Thread.sleep(2000);
		close_button_diwa.click();
		Thread.sleep(2000);
	}

	public boolean validateoopsMessage() throws InterruptedException {
		try {
			waitForElementToBeVisible(driver, vlidate_oops_message, 10);
			System.out.println("/*---'There are unsaved changes!' message shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---the screen does not show up 'There are unsaved changes Message!'");
			return false;
		}
	}

	public void ClickSaveImmuneInfoSaveButton() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, save_immune_info_btn, 10);
		Thread.sleep(2000);
		save_immune_info_btn.click();
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
	public void selectOneOption(String vaccine) throws InterruptedException {
		PersonAccountPage.checkBookingVaccineCheckbox(driver, vaccine);
	}

	public void NavigateToAppointmentsSection() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1100)", "");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, appointmentSection, 10);
		Thread.sleep(2000);
	}

	public boolean checkInButtonAvailable() throws  InterruptedException {
		Thread.sleep(500);
		By checkin_btn_path = By.xpath("//button[@class = 'slds-button slds-button_brand' and @title = 'Check-in Client']");
		waitForElementToBeEnabled(driver, checkin_btn_path, 10);
		WebElement check_in_button = driver.findElement(checkin_btn_path);
		waitForElementToBeVisible(driver, check_in_button, 10);
		return check_in_button.isDisplayed();
	}

	public String getCurrentTab() throws InterruptedException {
		Thread.sleep(500);
		By myXpath = By.xpath("//div[@class='slds-col slds-size_1-of-3 slds-align_absolute-center step first-step current-step']");
		waitForElementToBeLocated(driver, myXpath, 10);
		WebElement currentTab = driver.findElement(myXpath);
		return currentTab.getText();
	}

	public String getAppointmentDate() {
		waitForElementToBeVisible(driver, appointmentDate, 30);
		return appointmentDate.getText();
	}

	public String getAppointmentTime() {
		waitForElementToBeVisible(driver, appointmentTime, 30);
		return appointmentTime.getText();
	}

	public String getAppointmentLocation() {
		return appointmentLocation.getText();
	}

	public String getVaccineAgent() throws InterruptedException {
		By vaccine_agent_path = By.xpath("//label[text()='Agent']/..//button");
		waitForElementToBeEnabled(driver, vaccine_agent_path, 5);
		return driver.findElement(vaccine_agent_path).getAttribute("data-value");
	}

	public String consentProviderSelected() throws InterruptedException {
		Thread.sleep(500);
		By providerFieldPath = By.xpath("//label[text()='Informed Consent Provider (User)']/..//input[contains(@class, 'slds-combobox__input slds-input')]");
		waitForElementToBeEnabled(driver, providerFieldPath, 10);
		WebElement consent_provider_selected = driver.findElement(providerFieldPath);
		return consent_provider_selected.getAttribute("data-value");
	}
	public String getProvider() throws InterruptedException {
		By provider_path = By.xpath("//label[text() = 'Provider']/..//input");
		waitForElementToBeEnabled(driver, provider_path, 10);
		return driver.findElement(provider_path).getAttribute("data-value");
	}

	public String getRoute() throws InterruptedException {
		By route_path = By.xpath("//label[text() = 'Route']/..//button");
		waitForElementToBeEnabled(driver, route_path, 10);
		return driver.findElement(route_path).getAttribute("data-value");
	}

	public String getSite() throws InterruptedException {
		By site_path = By.xpath("//label[text() = 'Site']/..//button");
		waitForElementToBeEnabled(driver, site_path, 5);
		return driver.findElement(site_path).getAttribute("data-value");
	}

	public String getLotNumber() throws InterruptedException {
		By lot_number_path = By.xpath("//span[text() = 'Lot Number']/..//input");
		waitForElementToBeEnabled(driver, lot_number_path, 5);
		driver.findElement(lot_number_path).click();
		Thread.sleep(1000);
		return driver.findElement(lot_number_path).getAttribute("title");
	}

	public String getDosage() throws InterruptedException {
		By dosage_path = By.xpath("//label[text() = 'Dosage']/..//button");
		waitForElementToBeEnabled(driver, dosage_path, 5);
		return driver.findElement(dosage_path).getAttribute("data-value");
	}

	public void setVaccineAgent(String agent) {
		driver.findElement(By.xpath("//label[text()='Agent']/..//button")).getAttribute("data-value");
	}

	public void setProvider(String provider) throws InterruptedException {
		Thread.sleep(500);
		By providerFieldPath = By.xpath("//label[text() = 'Provider' and @c-bchcimmunizationinfo_bchcimmunizationinfo]/..//input");
		waitForElementToBeEnabled(driver, providerFieldPath, 10);
		WebElement providerField =  driver.findElement(providerFieldPath);
		scrollCenter(providerField);
		providerField.sendKeys(provider);
		By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
		waitForElementToBeLocated(driver, providerItemPath, 10);
		WebElement provider_item = driver.findElement(providerItemPath);
		provider_item.click();

	}

	public void setInformedConsentProvider  (String provider) throws InterruptedException {
		Thread.sleep(500);
		By informed_consent_provider_path = By.xpath("//label[text() = 'Informed Consent Provider (User)']/..//input");
		By informed_consent_provider_clear_btn_path = By.xpath("//label[text() = 'Informed Consent Provider (User)']/..//button[@title='Clear Selection']");
		waitForElementToBeEnabled(driver, informed_consent_provider_path, 10);
		WebElement informed_consent_provider = driver.findElement(informed_consent_provider_path);
		if(!informed_consent_provider.getAttribute("title").equals("")) {
			WebElement informed_consent_provider_clear_btn = driver.findElement(informed_consent_provider_clear_btn_path);
			scrollIfNeeded(driver, informed_consent_provider_clear_btn);
			Thread.sleep(500);
			informed_consent_provider_clear_btn.click();
			Thread.sleep(500);
		}
		informed_consent_provider.sendKeys(provider);
		By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
		waitForElementToBeLocated(driver, providerItemPath, 10);
		WebElement provider_item = driver.findElement(providerItemPath);
		provider_item.click();
	}

	public void setRoute(String route) throws InterruptedException {
		driver.findElement(By.xpath("//label[text() = 'Route']/..//button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@title = '" + route + "'] ")).click();
	}

	public void setSite(String site) throws InterruptedException {
		WebElement siteBtn = driver.findElement(By.xpath("//label[text() = 'Site']/..//button"));
		scrollCenter(siteBtn);
		siteBtn.click();
		Thread.sleep(2000);
		WebElement mySite = driver.findElement(By.xpath("//span[@title = '" + site + "']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", mySite );
		mySite.click();
	}

	public void setLotNumber(String lot) throws InterruptedException {
		By lot_item_path = By.xpath("//li[contains(@title, '" + lot + "')]");
		By lot_field_path = By.xpath("//span[text() = 'Lot Number']/..//input");
		WebElement lotSearchInputField = driver.findElement(By.xpath("//span[text() = 'Lot Number']/..//input[@class = 'slds-input search-input-class']"));
		if(!lotSearchInputField.isDisplayed()) {
			waitForElementToBeEnabled(driver, lot_field_path, 10);
			WebElement lot_field = driver.findElement(lot_field_path);
			lot_field.click();
		}
		Thread.sleep(1000);
		lotSearchInputField.sendKeys(lot);
		Thread.sleep(1000);
		waitForElementToBeEnabled(driver, lot_item_path, 10);
		WebElement lot_item = driver.findElement(lot_item_path);
		lot_item.click();
	}

	public void setDosage(String dose) throws InterruptedException {
		By dosage_field_path = By.xpath("//label[text() = 'Dosage']/..//button");
		waitForElementToBeEnabled(driver, dosage_field_path, 10);
		WebElement dosage_input_field = driver.findElement(dosage_field_path);
		dosage_input_field.click();
		Thread.sleep(500);
		By my_dosage_path = By.xpath("//span[@title = '" + dose + "']");
		waitForElementToBeEnabled(driver, my_dosage_path, 10);
		WebElement my_dosage = driver.findElement(my_dosage_path);
		my_dosage.click();
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

	public void navigateToAppointmentRecords() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, appointmentsRecordsTitle, 10);
		scrollCenter(appointmentsRecordsTitle);
		Thread.sleep(3000);

		for(int i = 0; i < 10; i++) {
			try {
				WebElement element = tables.getAppointmentsRecordsTable().getRowsMappedToHeadings().get(0).get("Subject");
				scrollCenter(element);
				Thread.sleep(1000);
				tables.getAppointmentsRecordsTable().getRowsMappedToHeadings().get(1);
				break;
			} catch (Exception ex) {
				log("Try " + i + "; Table is still empty");
			}
		}
		By view_all_appointments_btn_path = By.xpath("//div[@aria-label='Appointments|Appointments|List View']/..//span[@class='view-all-label']");
		waitForElementToBeEnabled(driver, view_all_appointments_btn_path, 10);
		WebElement btnViewAllAppointments = driver.findElement(view_all_appointments_btn_path);
		scrollCenter(driver, btnViewAllAppointments);
		Thread.sleep(500);
		click(btnViewAllAppointments);
	}

	public void openAppointmentRecord(String appointmentDataTime) throws InterruptedException {
		Thread.sleep(500);

		By titleAppointments = By.xpath("//h1[@title='Appointments']");
		waitForElementToBeEnabled(driver, titleAppointments, 10);
		By appointments_table_path = By.xpath("//div[@data-aura-class='forceListViewManagerGrid']");
		WebElement appointments_tbl_html = driver.findElement(appointments_table_path);
		GenericTable appointment_table = new GenericTable(appointments_tbl_html);
		int tries = 0;
		while(appointment_table.getRows().size() < 1) {
			appointments_tbl_html = driver.findElement(appointments_table_path);
			appointment_table = new GenericTable(appointments_tbl_html);
			if(tries > 10) {
				break;
			} else {
				Thread.sleep(500);
				tries++;
			}
		}
		List<Map<String, WebElement>> appointment_table_map = appointment_table.getRowsMappedToHeadings();
		String formatAppointmentDataTime = appointmentDataTime.replaceAll(",", "").replaceAll("at ", "");

		WebElement appointmentDataTimeWebElement = appointment_table_map.get(1).get("Appointment Code").findElement(By.xpath(".//a"));
		appointmentDataTimeWebElement.click();
	}

	public String getReasonForVisit(){
		By clientReasonForVisitElement = By.xpath("//span[contains(text(),'Client Reason for Visit')]/../..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']  | //span[@class='test-id__field-label' and text()='Client Reason for Visit']/../../..//lightning-formatted-text");
		waitForElementToBePresent(driver, clientReasonForVisitElement, 10);
		String getClientReasonForVisitString = driver.findElement(clientReasonForVisitElement).getText();
		return getClientReasonForVisitString;
	}

	public String getMinorAilmentsAndContraceptionAppointmentType(){
		By minorAilmentsAndContraceptionAppointmentTypeElement = By.xpath("//span[contains(text(),'Minor Ailments and Contraception')]/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']");
		waitForElementToBePresent(driver, minorAilmentsAndContraceptionAppointmentTypeElement, 10);
		String getMinorAilmentsAndContraceptionAppointmentTypeString = driver.findElement(minorAilmentsAndContraceptionAppointmentTypeElement).getText();
		return getMinorAilmentsAndContraceptionAppointmentTypeString;
	}

	public String getCitizenComment(){
		By citizenCommentElement = By.xpath("//span[text()='Citizen Comment']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only'] | //span[@class='test-id__field-label' and text()='Citizen Comment']/../../..//lightning-formatted-text");
		waitForElementToBePresent(driver, citizenCommentElement, 10);
		String getCitizenCommentString = driver.findElement(citizenCommentElement).getText();
		return getCitizenCommentString;
	}

	public String getAppointmentStatus(){
		By appointmentStatusElement = By.xpath("//span[contains(text(),'Status')]/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']  | //span[@class='test-id__field-label' and text()='Status']/../../..//lightning-formatted-text");
		waitForElementToBePresent(driver, appointmentStatusElement, 10);
		String getAppointmentStatusString = driver.findElement(appointmentStatusElement).getText();
		return getAppointmentStatusString;
	}

	public void clickBtnCancelAppointment() throws InterruptedException {
		click(btnCancelAppointment);
		click(btnYes);
		Thread.sleep(2000);
		click(btnCloseWindowAppointment);
		Thread.sleep(2000);
	}

}
