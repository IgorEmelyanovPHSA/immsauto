package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class InClinicExperiencePage extends BasePage {
	
	@FindBy(xpath = ".//span[text() = 'Register']")
	private WebElement register_tab;
	
	@FindBy(xpath = ".//button[text() = 'Register']")
	private WebElement register_confirmation_page_button;
	private By register_confirmation_page_button1 = By.xpath(".//button[text() = 'Register']");
	
	private By register_tab1 = By.xpath("(.//a[@title = 'Register'])");
	
	@FindBy(xpath = ".//button[@title = ' Create New Profile']")
	private WebElement register_button;
	private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");

	@FindBy(xpath = ".//button[text()='Save']")
	private WebElement click_save_defaults_button;
	private By click_save_defaults_button_ = By.xpath(".//button[text()='Save']");

	@FindBy(xpath = ".//button[@class='slds-button slds-button_brand saveBtn']")
	private WebElement click_save_modal_defaults_button;
	private By click_save_modal_defaults_button_ = By.xpath(".//button[@class='slds-button slds-button_brand saveBtn']");
	
	@FindBy(xpath = ".//button[@aria-label = 'Search']")
	private WebElement search_assistant;
	private By search_assistant1 = By.xpath(".//button[@aria-label = 'Search']");
	
	@FindBy(xpath = ".//input[@placeholder = 'Search...']")
	private WebElement search_input;
	private By search_input1 = By.xpath(".//input[@placeholder = 'Search...']");
	
	//@FindBy(xpath = "(.//A[@data-refid='recordId'])[1]")
	@FindBy(xpath = ".//a[@title='Ludovika BCVaxLimeburn']")
	private WebElement user_found;
	private By user_found1 = By.xpath(".//a[@title='Ludovika BCVaxLimeburn']");

	private WebElement user_Gill_found;
	private By user_Gill_found1 = By.xpath(".//a[@title='Gill BCVaxOrigan']");
	
	private WebElement user_Jodie_found;
	private By user_Jodie_found1 = By.xpath(".//a[@title='Jodie BCVaxCluff']");

	@FindBy(xpath = ".//a[@title='Dacia Bcvaxdod']")
	private WebElement user_dacia_found;
	private By user_dacia_found1 = By.xpath(".//a[@title='Dacia Bcvaxdod']");
	
	@FindBy(xpath = ".//a[@title='Hugues BCVaxLampard']")
	private WebElement user_Hugues_found;
	private By user_Hugues_found1 = By.xpath(".//a[@title='Hugues BCVaxLampard']");
	
	@FindBy(xpath = "(//a[@data-label='Related'])")
	private WebElement click_related_tab;
	private By click_related_tab1 = By.xpath("//a[@data-label='Related']");
	
	@FindBy(xpath = ".//th//lightning-primitive-cell-factory[@data-label='Immunization Record']//div[@class='slds-grid']//span[@force-lookup_lookup='']")
	private WebElement select_Imms_record;
	private By select_Imms_record1 = By.xpath(".//th//lightning-primitive-cell-factory[@data-label='Immunization Record']//div[@class='slds-grid']//span[@force-lookup_lookup='']");
	
	@FindBy(xpath = ".//button[@class='slds-button slds-button_icon-border-filled']")
	private WebElement imms_drop_down;
	private By imms_drop_down1 = By.xpath("//*[@id=\"brandBand_2\"]/div/div/div[4]/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-b-c-h_-clinical_-pathway_-record_-page___-case___-v-i-e-w/forcegenerated-flexipage_bch_clinical_pathway_record_page_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/flexipage-component2[2]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_case___0125w0000004kq6qam___compact___view___recordlayout2/records-highlights2/div[1]/div[1]/div[3]/div/runtime_platform_actions-actions-ribbon/ul/li[4]/lightning-button-menu/button");
	
	@FindBy(xpath = "//a[@runtime_platform_actions-ribbonmenuitem_ribbonmenuitem='']")
	private WebElement imms_drop_down_del;
	private By imms_drop_down_del1 = By.xpath(".//a[@runtime_platform_actions-ribbonmenuitem_ribbonmenuitem='']");
	
	@FindBy(xpath = "//span[@dir='ltr'][text()='Delete']")
	private WebElement delete_record_button;
	private By delete_record_button1 = By.xpath("//span[@dir='ltr'][text()='Delete']");
	
	@FindBy(xpath = "(//span[@id='window'])")
	private WebElement select_rern_record;
	private By select_rern_record1 = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[2]/div[2]/div[1]/div/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-b-c-h_-participant_-record_-page___-account___-v-i-e-w/forcegenerated-flexipage_bch_participant_record_page_account__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[2]/div[1]/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/flexipage-tab2[2]/slot/flexipage-component2[5]/slot/lst-related-list-container/div/div[8]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-app-builder-mapper/article/lst-related-list-view-manager/lst-common-list-internal/div/div/lst-primary-display-manager/div/lst-primary-display/lst-primary-display-grid/lst-customized-datatable/div[2]/div/div/table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-primitive-custom-cell/force-lookup/div/records-hoverable-link/div");
	
	@FindBy(xpath = "//BUTTON[@name='Delete'][text()='Delete']")
	private WebElement delete_rern_record;
	private By delete_rern_record1 = By.xpath("//*[@id='brandBand_2']/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___flexipage__default_rec_-l___-d-d-h__-h-c_-rules_-engine_-response__c___-v-i-e-w/forcegenerated-flexipage_default_rec_l_ddh__hc_rules_engine_response__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/flexipage-component2/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_ddh__hc_rules_engine_response__c___012000000000000aaa___compact___view___recordlayout2/records-highlights2/div[1]/div/div[3]/div/runtime_platform_actions-actions-ribbon/ul/li[2]/runtime_platform_actions-action-renderer/runtime_platform_actions-executor-aura-legacy/slot/slot/lightning-button/button");
	
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement delete_person_account;
	private By delete_person_account1 = By.xpath("//button[text()='Delete']");
	
	@FindBy(xpath = "//div[@class='slds-icon-waffle']")
	private WebElement select_app_launcher;
	private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");
	
	@FindBy(xpath = "//p[text()='Health Connect - Supply Console']")
	private WebElement click_healthconnect_app;
	private By click_healthconnect_app1 = By.xpath("//p[text()='Health Connect - Supply Console']");
	
	@FindBy(xpath = "//p[text()='In-Clinic Experience']")
	private WebElement click_ice_app;
	private By click_ice_app1 = By.xpath("//p[text()='In-Clinic Experience']");
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	private WebElement dropdownMenu;
	
	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
	private WebElement supplyLocationInDropdown;
	
	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[6]/div/a/span[2]/span")
	private WebElement supplyItemsInDropdown;
	
	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']")
	private WebElement select_desired_supply_loc;
	
	@FindBy(xpath = "//a[@title='AstraZeneca ChAdOx1-S - ABX3120']")
	private WebElement select_desired_supply_item;
	private By select_desired_supply_item1 = By.xpath("//a[@title='AstraZeneca ChAdOx1-S - ABX3120']");
	
	@FindBy(xpath = "(//button[@class='slds-combobox__input slds-input_faux'])[1]")
	private WebElement click_agent_value;
	private By click_agent_value1 = By.xpath("(//button[@class='slds-combobox__input slds-input_faux'])[1]");

	@FindBy(xpath = "(//span[text()='COVID-19 mRNA'])[1]")
	private WebElement select_agent_name;
	private By select_agent_name1 = By.xpath("(//span[text()='COVID-19 mRNA'])[1]");

	@FindBy(xpath = "//a[contains(text(),'Pfizer mRNA BNT162b2 - EL0203')]")
	private WebElement select_desired_supply_container;
	private By select_desired_supply_container1 = By.xpath("//a[contains(text(),'Pfizer mRNA BNT162b2 - EL0203')]");
	
	@FindBy(xpath = ".//span[@title='Health Connect - Supply Console']")
	private WebElement supply_console_App_displayed;
	private By supply_console_App_displayed1 = By.xpath(".//span[@title='Health Connect - Supply Console']");
	
	@FindBy(xpath = ".//span[@title='In-Clinic Experience']")
	private WebElement ice_App_displayed;
	private By ice_App_displayed1 = By.xpath(".//span[@title='In-Clinic Experience']");
	
	@FindBy(xpath = "//a[@title='Supply Locations']")
	private WebElement supply_location_displayed;
	private By supply_location_displayed1 = By.xpath("//a[@title='Supply Locations']");
	
	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Doses']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']")
	private WebElement get_remaining_doses;
	private By get_remaining_doses1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Doses']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");
	
	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose Conversion Factor']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']")
	private WebElement get_dose_conversion_factor;
	private By get_dose_conversion_factor1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose Conversion Factor']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");
	
	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='SDST-0000001558']")
	private WebElement get_supply_distribution_name;
	private By get_supply_distribution_name1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='SDST-0000001558']");
	
	@FindBy(xpath = "(//DIV[@records-recordlayoutitem_recordlayoutitem=''])[19]/..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='Automation Java']")
	private WebElement get_supply_distribution_description;
	private By get_supply_distribution_description1 = By.xpath("(//DIV[@records-recordlayoutitem_recordlayoutitem=''])[19]/..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='Automation Java']");
	
	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Quantity']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']")
	private WebElement get_remaining_Qty;
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
	
	@FindBy(xpath = "(.//input[@name = 'PersonEmail'])")
	private WebElement email;
	private By email1 = By.xpath("(.//input[@name = 'PersonEmail'])");
	
	@FindBy(xpath = "(.//input[@name = 'ConfirmEmail'])")
	private WebElement confirm_email;
	private By confirm_email1 = By.xpath("(.//input[@name = 'ConfirmEmail'])");
	
	@FindBy(xpath = ".//button[@title = 'Verify Personal Health Number']")
	private WebElement verify_phn_button;
	private By verify_phn_button1 = By.xpath("(.//button[@title = 'Verify Personal Health Number'])");
	
	@FindBy(xpath = ".//button[@title='Next']")
	private WebElement click_next_button;
	private By click_next_button1 = By.xpath(".//button[@title='Next']");
	
	@FindBy(xpath = "(.//input[@name = 'BCH_Indigenous'])[2]")
	private WebElement non_indigenous_radio_button;
	private By non_indigenous_radio_button1 = By.xpath("(.//input[@name = 'BCH_Indigenous'])[2]");
	
	@FindBy(xpath = ".//button[text()= 'Review Details']")
	private WebElement review_details;
	private By review_details1 = By.xpath("(.//button[text()= 'Review Details'])");
	
	@FindBy(xpath = "//button[@title='Check Eligibility']")
	private WebElement click_eligibility_button;
	private By click_eligibility_button1 = By.xpath("(//button[@title='Check Eligibility'])");
	
	@FindBy(xpath = ".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']")
	private WebElement click_eligibility_dropdown;
	private By click_eligibility_dropdown1 = By.xpath(".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']");
	
	@FindBy(xpath = ".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']")
	private WebElement select_covid19_option_from_dropdown;
	private By select_covid19_option_from_dropdown1 = By.xpath("(.//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination'])");
	
	@FindBy(xpath = ".//a[@data-label= 'Appointment Scheduling']")
	private WebElement appointment_scheduling_tab;
	private By appointment_scheduling_tab1 = By.xpath(".//a[@data-label= 'Appointment Scheduling']");
	
	@FindBy(xpath = ".//a[text()='Search clinic name']")
	private WebElement click_select_search_clinic;
	private By click_select_search_clinic1 = By.xpath(".//a[text()='Search clinic name']");
	
	@FindBy(xpath = "//input[@name='clinicstag']")
	private WebElement select_clinic;
	private By select_clinic1 = By.xpath("//input[@name='clinicstag']");
	
	@FindBy(xpath = "(.//button[@name = 'facility'][1])")
	private WebElement option_loc_facility;
	private By select_option_loc_facility1 = By.xpath("(.//button[@name = 'facility'][1])");
	
	@FindBy(xpath = "(.//button[@class = 'slds-day active-day'][1])")
	private WebElement booking_app_active_day;
	private By booking_app_active_day1 = By.xpath("(.//button[@class = 'slds-day active-day'][1])");
	
	@FindBy(xpath = "(.//button[@name='timeslot'][1])")
	private WebElement time_slot_appointment;
	private By time_slot_appointment1 = By.xpath("(.//button[@name='timeslot'][1])");
	
	@FindBy(xpath = "(.//button[text()='Next'])")
	private WebElement click_Next_button_appt;
	private By click_Next_button_appt1 = By.xpath("(.//button[text()='Next'])");
	
	@FindBy(xpath = "(.//button[text() = 'Confirm appointment'])")
	private WebElement appt_confirm_btn;
	private By appt_confirm_btn1 = By.xpath(".//button[text() = 'Confirm appointment']");
	
	@FindBy(xpath = "(.//button[@name='navigateToICE'])")
	private WebElement click_navigate_to_ICE_btn;
	private By click_navigate_to_ICE_btn1 = By.xpath(".//button[@name='navigateToICE']");
	
	@FindBy(xpath = "(//SPAN[@lightning-input_input=''])[47]")
	private WebElement verify_contact_information_checkbox;
	private By verify_contact_information_checkbox1 = By.xpath("(//SPAN[@lightning-input_input=''])[47]");
	
	@FindBy(xpath = "(.//button[@title='Confirm & Save Identification'])")
	private WebElement confirm_and_save_btn_home;
	private By confirm_and_save_btn_home1 = By.xpath(".//button[@title='Confirm & Save Identification']");
	
	@FindBy(xpath = "(//button[normalize-space()='Save Consent'])")
	private WebElement save_consent_btn;
	private By save_consent_btn1 = By.xpath("//button[normalize-space()='Save Consent']");
	
	@FindBy(xpath = "(//button[@title='Confirm & Save Administration'])")
	private WebElement confirm_save_adm_btn;
	private By confirm_save_adm_btn1 = By.xpath("//button[@title='Confirm & Save Administration']");
	
	@FindBy(xpath = ".//h2[text() = 'Vaccine Administration']")
	private WebElement validate_vaccine_admin_page_open;
	private By validate_vaccine_admin_page_open1 = By.xpath(".//h2[text() = 'Vaccine Administration']");
	
	@FindBy(xpath = ".//span[@title='In-Clinic Experience']")
	private WebElement ice_page_displayed;
	
	@FindBy(xpath = "(.//span[text() = 'Covid-19 Vaccine'])")
	private WebElement click_reason_radiobutton;
	private By click_reason_radiobutton1 = By.xpath(".//span[text() = 'Covid-19 Vaccine']");

	@FindBy(xpath = ".//span[text() = 'Select One']")
	private WebElement click_early_booking_reason;
	private By click_early_booking_reason1 = By.xpath(".//span[text() = 'Select One']");

	@FindBy(xpath = ".//span[text() = 'Travel']")
	private WebElement select_early_booking_reason;
	private By select_early_booking_reason1 = By.xpath(".//span[text() = 'Travel']");
	
	@FindBy(xpath = "(.//a[text() = 'Related'])")
	private WebElement person_account_Related_tab;
	private By person_account_Related_tab_1 = By.xpath("(.//a[text() = 'Related'])");
	
	@FindBy(xpath = "(.//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs'])")
	private WebElement click_more_search_tabs;
	private By click_more_search_tabs1 = By.xpath(".//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs']");
	
	@FindBy(xpath = ".//a[text()='Search clinic name']")
	private WebElement search_clinic_name_tab;
	private By search_clinic_name_tab1 = By.xpath(".//a[text()='Search clinic name']");
	
	@FindBy(xpath = ".//div[text() = 'Appointment Confirmed!']")
	private WebElement validate_appointment_confirm_message;
	private By validate_appointment_confirm_message1 = By.xpath(".//div[text() = 'Appointment Confirmed!']");
	
	@FindBy(xpath = "//input[@name='BCH_Date__c']")
	private WebElement input_current_date;
	private By input_current_date1 = By.xpath("//input[@name='BCH_Date__c']");
	
	@FindBy(xpath = ".//span[text() = 'User Defaults']")
	private WebElement user_defaults_tab;
	private By user_defaults_tab1 = By.xpath(".//span[text() = 'User Defaults']");
	
	@FindBy(xpath = ".//button[@aria-label = 'Agent, Select an option']")
	private WebElement click_vaccine_agent_dropdown;
	private By click_vaccine_agent_dropdown1 = By.xpath(".//button[@aria-label = 'Agent, Select an option']");
	
	@FindBy(xpath = ".//span[text() = 'COVID-19 mRNA']")
	private WebElement select_vaccine_agent_dropdown;
	private By select_vaccine_agent_dropdown1 = By.xpath(".//span[text() = 'COVID-19 mRNA']");
	
	@FindBy(xpath = ".//h1[text() = 'Client Search']")
	private WebElement validate_home_client_search_page_open;
	private By validate_home_client_search_page_open1 = By.xpath(".//h1[text() = 'Client Search']");
	
	@FindBy(xpath = "(//a[text()='Automation Java - SDST-0000001558'])[1]")
	private WebElement click_supply_distribution;
	private By click_supply_distribution1 = By.xpath("(//a[text()='Automation Java - SDST-0000001558'])[1]");
	
	@FindBy(xpath = "//BUTTON[@class='slds-button slds-button_icon-border-filled']")
	private WebElement select_drpdown_to_receive_supplies;
	private By select_drpdown_to_receive_supplies1 = By.xpath("//BUTTON[@class='slds-button slds-button_icon-border-filled']");
	
	@FindBy(xpath = "//span[text()='Receive Supplies']")
	private WebElement click_to_select_receive_supplies;
	private By click_to_select_receive_supplies1 = By.xpath("//span[text()='Receive Supplies']");
	
	@FindBy(xpath = "//label[@class='slds-form-element__label'][text()='Supply Item']")
	private WebElement validate_supply_item_field;
	private By validate_supply_item_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Item']");
	
	@FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex'][text()='Quantity']")
	private WebElement validate_qty_field;
	private By validate_qty_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Quantity']");
	
	@FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex'][text()='Dose Conversion Factor']")
	private WebElement validate_dcf_field;
	private By validate_dcf_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Dose Conversion Factor']");
	
	@FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex'][text()='Doses']")
	private WebElement validate_doses_field;
	private By validate_doses_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Doses']");
	
	@FindBy(xpath = "//label[@class='slds-form-element__label'][text()='Supply Distribution To']")
	private WebElement supply_distribution_to_field;
	private By supply_distribution_to_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Distribution To']");
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement save_button_receive_supplies;
	private By save_button_receive_supplies1 = By.xpath("//button[contains(text(),'Save')]");
	
	@FindBy(xpath = "(//span[contains(text(),'Cancel')])[2]")
	private WebElement cancel_button_receive_supplies;
	private By cancel_button_receive_supplies1 = By.xpath("(//span[contains(text(),'Cancel')])[2]");
	
	@FindBy(xpath = "//input[@placeholder='Search Supply Items...']")
	private WebElement click_to_select_supply_item;
	private By click_to_select_supply_item1 = By.xpath("//input[@placeholder='Search Supply Items...']");
	
	@FindBy(xpath = "//span[contains(text(),'AstraZeneca ChAdOx1-S - ABX3120')]")
	private WebElement choose_supply_item;
	private By choose_supply_item1 = By.xpath("//span[contains(text(),'AstraZeneca ChAdOx1-S - ABX3120')]");
	
	@FindBy(xpath = "(//input[@class='slds-input'])[2]")
	private WebElement click_to_select_quantity;
	private By click_to_select_quantity1 = By.xpath("(//input[@class='slds-input'])[2]");
	
	@FindBy(xpath = "//button[@name='distributionBox']")
	private WebElement supply_distribution_to;
	private By supply_distribution_to1 = By.xpath("//button[@name='distributionBox']");
	
	@FindBy(xpath = "//span[@title='Automation Java - SDST-0000001558']")
	private WebElement select_supply_distributor;
	private By select_supply_distributor1 = By.xpath("//span[@title='Automation Java - SDST-0000001558']");
	
	@FindBy(xpath = "//button[@aria-label='Reason for Reception, --None--']")
	private WebElement click_reason;
	private By click_reason1 = By.xpath("//button[@aria-label='Reason for Reception, --None--']");
	
	@FindBy(xpath = "//span[@title='Other']")
	private WebElement select_reason;
	private By select_reason1 = By.xpath("//span[@title='Other']");
	
	/*---------Constructor-------*/
	public InClinicExperiencePage(WebDriver driver) {
		super(driver);
	}
	
	/*-------------Methods--------------*/
	public void verifyIsICEpageDisplayed() {
		waitForElementToBeVisible(driver, ice_page_displayed, 10);
		ice_page_displayed.isDisplayed();
	}
	
	public void SearchForCitizen(String citizen) throws InterruptedException {
		//waitForElementToBeLocated(driver,search_assistant1,10);
		waitForElementToBeVisible(driver, search_assistant, 10);
		WebElement search_navigator = driver.findElement(search_assistant1);
		search_navigator.click();
		waitForElementToBeVisible(driver, search_input, 10);
		WebElement search_input = driver.findElement(search_input1);
		search_input.sendKeys(citizen);
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
		waitForElementToBeLocated(driver, register_confirmation_page_button1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(register_confirmation_page_button1);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
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
	
	public boolean userLampardFound() throws InterruptedException {
		if (!isDisplayed(user_Hugues_found1)) {
			return false;
		}
		waitForElementToBeLocated(driver, user_Hugues_found1, 10);
		WebElement element = driver.findElement(user_Hugues_found1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		return true;
	}
	
	public boolean userDaciaFound() throws InterruptedException {
		if (!isDisplayed(user_dacia_found1)) {
			return false;
		}
		waitForElementToBeLocated(driver, user_dacia_found1, 10);
		WebElement element = driver.findElement(user_dacia_found1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		return true;
	}

	public boolean userGillFound() throws InterruptedException {
		if (!isDisplayed(user_Gill_found1)) {
			return false;
		}
		waitForElementToBeLocated(driver, user_Gill_found1, 10);
		WebElement element = driver.findElement(user_Gill_found1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		return true;
	}
	
	public boolean userJodieFound() throws InterruptedException {
		if (!isDisplayed(user_Jodie_found1)) {
			return false;
		}
		waitForElementToBeLocated(driver, user_Jodie_found1, 10);
		WebElement element = driver.findElement(user_Jodie_found1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		return true;
	}
	
	public void clickRelatedTab() throws InterruptedException {
		waitForElementToBeLocated(driver, click_related_tab1, 10);
		WebElement element = driver.findElement(click_related_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public boolean selectImmsRecord() throws InterruptedException {
		//To scroll down the page to see Imms Record
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1300)", "");
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
	
	public void clickDropdownMenu() {
		this.dropdownMenu.click();
	}
	
	public void selectSupplyLocationFromDropdown() {
		this.supplyLocationInDropdown.click();
	}
	
	public void selectSupplyItemsFromDropdown() {
		this.supplyItemsInDropdown.click();
	}
	
	public void selectSupplyLocationName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_loc, 10);
		this.select_desired_supply_loc.click();
	}
	
	public void selectSupplyItemName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_item, 10);
		this.select_desired_supply_item.click();
		waitForElementToBeLocated(driver, select_desired_supply_item1, 10);
		WebElement element = driver.findElement(select_desired_supply_item1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void selectSupplyContainer() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)", "");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_desired_supply_container1, 10);
		WebElement element = driver.findElement(select_desired_supply_container1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public boolean displaySupplyConsolePage() {
		return isDisplayed(supply_console_App_displayed1);
	}
	
	public boolean displayIceApp() {
		return isDisplayed(ice_App_displayed1);
	}
	
	public boolean supplyLocDisplayed() {
		return isDisplayed(supply_location_displayed1);
	}
	
	public Double getValueOfRemainingDoses() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_doses1, 10);
		WebElement element = driver.findElement(get_remaining_doses1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",",""));
		return (doses);
	}

	public String getDoseConversionFactor() throws InterruptedException {
		WebElement element = driver.findElement(get_dose_conversion_factor1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String getSupplyDistributionName() throws InterruptedException {
		WebElement element = driver.findElement(get_supply_distribution_name1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String getSupplyDistributionDescription() throws InterruptedException {
		WebElement element = driver.findElement(get_supply_distribution_description1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}

	public Double getValueOfRemainingQty() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty1, 10);
		WebElement element = driver.findElement(get_remaining_Qty1);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",",""));
		return (quantity);
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
	
	public void clickRegisterButton() throws InterruptedException {
		waitForElementToBeVisible(driver, register_button, 10);
		WebElement element = driver.findElement(register_button_1);
		register_button.click();
	}

	public void clickSaveDefaultsButton() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_save_defaults_button, 10);
		WebElement element = driver.findElement(click_save_defaults_button_);
		click_save_defaults_button.click();
	}

	public void clickSaveModalDefaultsButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_save_modal_defaults_button, 10);
		WebElement element = driver.findElement(click_save_modal_defaults_button_);
		click_save_modal_defaults_button.click();
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
		WebElement element = driver.findElement(non_indigenous_radio_button1);
		non_indigenous_radio_button.click();
	}
	
	public void clickVerifyPHNButton() throws InterruptedException {
		waitForElementToBeVisible(driver, verify_phn_button, 10);
		WebElement element = driver.findElement(verify_phn_button1);
		verify_phn_button.click();
	}
	
	public void successMessage() throws InterruptedException {
		waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Success']"), 20);
		driver.findElement(By.xpath(".//div[text() = 'Success']"));
		Thread.sleep(2000);
	}
	
	public void clickNextButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_next_button, 10);
		WebElement element = driver.findElement(click_next_button1);
		click_next_button.click();
	}
	
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
		WebElement element = driver.findElement(review_details1);
		review_details.click();
	}
	
	public void clickEligibilityButton() throws InterruptedException {
		waitForElementToBeLocated(driver, click_eligibility_button1, 10);
		WebElement element = driver.findElement(click_eligibility_button1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void selectCovid19option() throws InterruptedException {
		waitForElementToBeVisible(driver, click_eligibility_dropdown, 10);
		click_eligibility_dropdown.click();
	}
	
	public void userIsEligibleSuccessMsg() throws InterruptedException {
		waitForElementToBeLocated(driver, By.xpath(".//span[text() = 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.']"), 20);
		driver.findElement(By.xpath(".//span[text() = 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.']"));
		Thread.sleep(2000);
		System.out.println("/* ----the Eligibility success toast message is displayed");
	}
	
	public void navigateAppointmentSchedulingTab() throws InterruptedException {
		waitForElementToBeLocated(driver, appointment_scheduling_tab1, 10);
		WebElement element = driver.findElement(appointment_scheduling_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickReasonForVisit() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_reason_radiobutton, 10);
		click_reason_radiobutton.click();
	}

	public void selectEarlyBookingReason() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,80)");
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
	
	public void clickToSearchClinic() throws InterruptedException {
		waitForElementToBeLocated(driver, click_select_search_clinic1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_select_search_clinic1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void SearchForClinic() throws InterruptedException {
		waitForElementToBeVisible(driver, select_clinic, 10);
		select_clinic.click();
		Thread.sleep(2000);
		select_clinic.sendKeys("Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic");
		select_clinic.sendKeys(Keys.RETURN);
	}
	
	public void clickFacilityOptionLocation() throws InterruptedException {
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
	
	public void selectTimeSlotForAppointment() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", time_slot_appointment);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		WebElement element = driver.findElement(time_slot_appointment1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickNextButtonApptSchedulingPage() throws InterruptedException {
		waitForElementToBeVisible(driver, click_Next_button_appt, 10);
		click_Next_button_appt.click();
	}
	
	
	public void clickAppointmentConfirmButton() throws InterruptedException {
		waitForElementToBeVisible(driver, appt_confirm_btn, 10);
		appt_confirm_btn.click();
	}
	
	public boolean AppointmentConfirmationMessage() throws InterruptedException {
		try {
			waitForElementToBeVisible(driver, validate_appointment_confirm_message, 10);
			System.out.println("/*---'Appointment Confirmed!' message shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---the screen does not show up 'Appointment Confirmed!'");
			return false;
		}
	}
	
	public void ClickGoToInClinicExperienceButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_navigate_to_ICE_btn, 10);
		click_navigate_to_ICE_btn.click();
	}
	
	public void HomePageClickConfirmAndSaveButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, confirm_and_save_btn_home, 10);
		confirm_and_save_btn_home.click();
	}
	
	public void clickVerifyContactInformation() throws InterruptedException {
		//Scrolling to bottom of the page
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, verify_contact_information_checkbox, 10);
		Thread.sleep(2000);
		verify_contact_information_checkbox.click();
		Thread.sleep(2000);
	}
		
	public void selectVaccineAgent () throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_vaccine_agent_dropdown, 10);
		click_vaccine_agent_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_vaccine_agent_dropdown, 10);
		select_vaccine_agent_dropdown.click();
	}
		
		public void ClickSaveConsentButton () throws InterruptedException {
			waitForElementToBeVisible(driver, save_consent_btn, 10);
			save_consent_btn.click();
		}
		
		public void ClickConfirmAndSaveAdministrationButton () throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)", "");
			Thread.sleep(2000);
			waitForElementToBeVisible(driver, confirm_save_adm_btn, 10);
			confirm_save_adm_btn.click();
		}
		
		public boolean validateVaccineAdminPageOpen () throws InterruptedException {
			try {
				waitForElementToBeVisible(driver, validate_vaccine_admin_page_open, 10);
				System.out.println("/*---Vaccine admin ICE page shown up");
				return true;
			} catch (NoSuchElementException e) {
				System.out.println("/*---no Vaccine Admin ICE Page show up");
				return false;
			}
		}
		
		public void successRegisteredMessageAppear () throws InterruptedException {
			waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Citizen Successfully Registered']"), 20);
			driver.findElement(By.xpath(".//div[text() = 'Citizen Successfully Registered']"));
			Thread.sleep(2000);
			System.out.println("/* ----the toast success Citizen Registered message has been Appears");
		}
		
		public void clickOnPersonAccountRelatedTab () throws InterruptedException {
			Thread.sleep(2000);
			WebElement element = driver.findElement(person_account_Related_tab_1);
			isDisplayed(person_account_Related_tab_1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		}
		
		public void selectSearchClinicNameTab () throws InterruptedException {
			waitForElementToBeLocated(driver, search_clinic_name_tab1, 10);
			Thread.sleep(2000);
			WebElement element = driver.findElement(search_clinic_name_tab1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		}
		
		public void searchClinicName () throws InterruptedException {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
			Thread.sleep(2000);
			waitForElementToBeVisible(driver, select_clinic, 10);
			select_clinic.click();
			Thread.sleep(2000);
			select_clinic.sendKeys("Age 5-11 Only - Indigenous Clinic - Victoria Native Friendship Center");
			select_clinic.sendKeys(Keys.RETURN);
		}
		
		public void clickOnFacilityOptionLocation () throws InterruptedException {
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", option_loc_facility);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
			Thread.sleep(2000);
			waitForElementToBeVisible(driver, option_loc_facility, 10);
			option_loc_facility.click();
		}
		
		public void refreshBrowser () throws InterruptedException {
			driver.navigate().refresh();
		}
		
		public void clickUserDefaultsTab () throws InterruptedException {
			waitForElementToBeLocated(driver, user_defaults_tab1, 10);
			WebElement element = driver.findElement(user_defaults_tab1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		}
		
		public void inputCurrentDateUserDefaults () throws InterruptedException {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 0);
			Date today = calendar.getTime();
			DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
			waitForElementToBeVisible(driver, input_current_date, 10);
			String todayAsString = dateFormat.format(today);
			input_current_date.click();
			Thread.sleep(2000);
			input_current_date.clear();
			Thread.sleep(2000);
			input_current_date.sendKeys(todayAsString);
			Thread.sleep(2000);
			input_current_date.sendKeys(Keys.ENTER);
		}
	
	public void inputPreviousDateUserDefaults () throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date previousDay = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		waitForElementToBeVisible(driver, input_current_date, 10);
		String previousDateAsString = dateFormat.format(previousDay);
		input_current_date.click();
		Thread.sleep(2000);
		input_current_date.clear();
		Thread.sleep(2000);
		input_current_date.sendKeys(previousDateAsString);
		Thread.sleep(2000);
		input_current_date.sendKeys(Keys.ENTER);
	}
		
		public boolean validateHomePageShownUp () throws InterruptedException {
			try {
				waitForElementToBeVisible(driver, validate_home_client_search_page_open, 10);
				System.out.println("/*---Home page-Client Search page shown up");
				return true;
			} catch (NoSuchElementException e) {
				System.out.println("/*---no Home page-Client Search page show up");
				return false;
			}
		}
		
		public void ClickAgentValue () throws InterruptedException {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
			Thread.sleep(2000);
			waitForElementToBeLocated(driver, click_agent_value1, 10);
			WebElement element = driver.findElement(click_agent_value1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
		}

		public void SelectAgentValue () throws InterruptedException {
			waitForElementToBeVisible(driver, select_agent_name, 10);
			WebElement search_input = driver.findElement(select_agent_name1);
			search_input.click();
		}

		public void clickSupplyDistribution () throws InterruptedException {
			waitForElementToBeLocated(driver, click_supply_distribution1, 10);
			WebElement element = driver.findElement(click_supply_distribution1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		}
		
		public void SelectDropDownToClickReceiveSuppliesButton () throws InterruptedException {
			waitForElementToBeLocated(driver, select_drpdown_to_receive_supplies1, 10);
			WebElement element = driver.findElement(select_drpdown_to_receive_supplies1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
		}
		
		public void ClickDropDownToClickReceiveSuppliesButton () throws InterruptedException {
			waitForElementToBeLocated(driver, click_to_select_receive_supplies1, 10);
			WebElement element1 = driver.findElement(click_to_select_receive_supplies1);
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
		}
		
		public String validateSupplyItemField () throws InterruptedException {
			WebElement element = driver.findElement(validate_supply_item_field1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			Thread.sleep(2000);
			element.getText();
			return (element.getText());
		}
		
		public String validateQTYField () throws InterruptedException {
			WebElement element = driver.findElement(validate_qty_field1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			Thread.sleep(2000);
			element.getText();
			return (element.getText());
		}
		
		public String validateDCFField () throws InterruptedException {
			WebElement element = driver.findElement(validate_dcf_field1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			Thread.sleep(2000);
			element.getText();
			return (element.getText());
		}
		
		public String validateDosesField () throws InterruptedException {
			WebElement element = driver.findElement(validate_doses_field1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			Thread.sleep(2000);
			element.getText();
			return (element.getText());
		}
		
		public String validateSupplyDistributionToField () throws InterruptedException {
			WebElement element = driver.findElement(supply_distribution_to_field1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			Thread.sleep(2000);
			element.getText();
			return (element.getText());
		}
		
		public void ValidateSaveButtonIsDisplayedOnReceiveSupplies () throws InterruptedException {
			waitForElementToBeLocated(driver, save_button_receive_supplies1, 10);
			save_button_receive_supplies.isDisplayed();
		}
		
		public void ClickSaveButton () throws InterruptedException {
			waitForElementToBeLocated(driver, save_button_receive_supplies1, 10);
			save_button_receive_supplies.click();
		}
		
		public void ValidateCancelButtonIsDisplayedOnReceiveSupplies () throws InterruptedException {
			waitForElementToBeLocated(driver, cancel_button_receive_supplies1, 10);
			cancel_button_receive_supplies.isDisplayed();
		}
		
		public void clickSupplyItemTextBox () throws InterruptedException {
			waitForElementToBeLocated(driver, click_to_select_supply_item1, 10);
			WebElement element1 = driver.findElement(click_to_select_supply_item1);
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
		}
		
		public void selectSupplyItem (String supplyItem) throws InterruptedException {
			WebElement element = driver.findElement(choose_supply_item1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
			waitForElementToBeVisible(driver, choose_supply_item, 10);
			WebElement search_input = driver.findElement(choose_supply_item1);
			search_input.click();
		}
		
		public void enterQuantity () throws InterruptedException {
			waitForElementToBeLocated(driver, click_to_select_quantity1, 10);
			WebElement element = driver.findElement(click_to_select_quantity1);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
			click(click_to_select_quantity1);
			element.sendKeys("1");
		}
		
		public void selectIncomingSupplyDistribution () throws InterruptedException {
			waitForElementToBeVisible(driver, supply_distribution_to, 10);
			WebElement element = driver.findElement(supply_distribution_to1);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
			Thread.sleep(1000);
			supply_distribution_to.click();
			Thread.sleep(2000);
			waitForElementToBeVisible(driver, select_supply_distributor, 10);
			Thread.sleep(2000);
			select_supply_distributor.click();
		}
		
		public void selectReasonForReception () throws InterruptedException {
			waitForElementToBeLocated(driver, click_reason1, 10);
			WebElement element = driver.findElement(click_reason1);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
			waitForElementToBeLocated(driver, select_reason1, 10);
			WebElement element1 = driver.findElement(select_reason1);
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click();", element1);
		}
		
	}

