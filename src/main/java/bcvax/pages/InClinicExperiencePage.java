package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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

	@FindBy(xpath = "(.//lightning-formatted-rich-text[@class = 'primary slds-truncate slds-rich-text-editor__output'])[1]")
	private WebElement profile_in_search_dropdown;
	private By profile_in_search_dropdown_ = By.xpath("(.//lightning-formatted-rich-text[@class = 'primary slds-truncate slds-rich-text-editor__output'])[1]");

	//@FindBy(xpath = "(.//A[@data-refid='recordId'])[1]")
	@FindBy(xpath = ".//a[@title='Ludovika BcvaxLimeburn']")
	private WebElement user_found;
	private By user_found1 = By.xpath(".//a[@title='Ludovika BcvaxLimeburn']");
	
	private WebElement user_Gill_found;
	private By user_Gill_found1 = By.xpath(".//a[@title='Gill Ashely BCVaxOrigan']");
	
	private WebElement user_Jodie_found;
	private By user_Jodie_found1 = By.xpath(".//a[@title='Jodie Morten BCVaxCluff']");
	
	@FindBy(xpath = ".//a[@title='Dacia Edeline Bcvaxdod']")
	private WebElement user_dacia_found;
	private By user_dacia_found1 = By.xpath(".//a[@title='Dacia Edeline Bcvaxdod']");
	
	@FindBy(xpath = ".//a[@title='Hugues Fawn BCVaxLampard']")
	private WebElement user_Hugues_found;
	private By user_Hugues_found1 = By.xpath(".//a[@title='Hugues Fawn BCVaxLampard']");
	
	@FindBy(xpath = ".//a[@title='Alexandro Corry BCVaxDa Costa']")
	private WebElement user_Costa_found;
	private By user_Costa_found1 = By.xpath(".//a[@title='Alexandro Corry BCVaxDa Costa']");
	
	@FindBy(xpath = "(//a[@data-label='Related'])")
	private WebElement click_related_tab;
	private By click_related_tab1 = By.xpath("//a[@data-label='Related']");
	
	@FindBy(xpath = ".//th//lightning-primitive-cell-factory[@data-label='Immunization Record']//div[@class='slds-grid']//span[@force-lookup_lookup='']")
	private WebElement select_Imms_record;
	private By select_Imms_record1 = By.xpath(".//th//lightning-primitive-cell-factory[@data-label='Immunization Record']//div[@class='slds-grid']//span[@force-lookup_lookup='']");
	
	@FindBy(xpath = ".//div[@aria-label = 'Profiles||List View']//a[contains(text(),'Maegan Tanya bcvaxvillage')]")
	private WebElement click_on_citizen;
	private By click_on_citizen1 = By.xpath(".//div[@aria-label = 'Profiles||List View']//a[contains(text(),'Maegan Tanya bcvaxvillage')]");
	
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
	
	@FindBy(xpath = "//a[@title='COMIRNATY (Pfizer) - 35035BD-CC01']")
	private WebElement select_desired_supply_item;
	private By select_desired_supply_item1 = By.xpath("//a[@title='COMIRNATY (Pfizer) - 35035BD-CC01']");
	
	@FindBy(xpath = "(//button[@class='slds-combobox__input slds-input_faux'])[1]")
	private WebElement click_agent_value;
	private By click_agent_value1 = By.xpath("(//button[@class='slds-combobox__input slds-input_faux'])[1]");
	
	@FindBy(xpath = "(//span[text()='COVID-19 mRNA'])[1]")
	private WebElement select_agent_name;
	private By select_agent_name1 = By.xpath("(//span[text()='COVID-19 mRNA'])[1]");

	@FindBy(xpath = "//a[contains(text(),'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)')]")
	private WebElement select_desired_supply_container;
	private By select_desired_supply_container1 = By.xpath("//a[contains(text(),'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)')]");
	
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
	
	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']")
	private WebElement get_supply_distribution_name;
	private By get_supply_distribution_name1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']");
	
	@FindBy(xpath = "(//DIV[@records-recordlayoutitem_recordlayoutitem=''])[19]/..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='Supply Distribution_1']")
	private WebElement get_supply_distribution_description;
	private By get_supply_distribution_description1 = By.xpath("(//DIV[@records-recordlayoutitem_recordlayoutitem=''])[19]/..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='Supply Distribution_1']");
	
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

	@FindBy(xpath = ".//span[text() = 'Appointment Scheduling']")
	private WebElement appointment_scheduling_tab_CP;
	private By appointment_scheduling_tab1_CP = By.xpath(".//span[text() = 'Appointment Scheduling']");

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
	
	@FindBy(xpath = ".//button[text() = 'Rebook at Current Location']")
	private WebElement click_to_rebook_button;
	private By click_to_rebook_button1 = By.xpath(".//button[text() = 'Rebook at Current Location']");
	
	@FindBy(xpath = "//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']")
	private WebElement appointment_status_cancel;
	private By appointment_status_cance1 = By.xpath("//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']");
	
	@FindBy(xpath = "//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']")
	private WebElement appointment_status_confirm;
	private By appointment_status_confirm1 = By.xpath("//SPAN[@lst-listviewmanagerheader_listviewmanagerheader=''][text()='Appointments']/../../../../../../../../..//LST-FORMATTED-TEXT[text()='Cancelled']");
	
	@FindBy(xpath = "(//SPAN[@class='slds-page-header__title slds-truncate'][text()='Immunization Records (1)']/../../../../../../../../..//LIGHTNING-BASE-FORMATTED-TEXT)[2]")
	private WebElement status_after_care;
	private By status_after_care1 = By.xpath("(//SPAN[@class='slds-page-header__title slds-truncate'][text()='Immunization Records (1)']/../../../../../../../../..//LIGHTNING-BASE-FORMATTED-TEXT)[2]");
	
	@FindBy(xpath = "(//SPAN[@lightning-input_input=''])[47]")
	private WebElement verify_contact_information_checkbox;
	private By verify_contact_information_checkbox1 = By.xpath("(//SPAN[@lightning-input_input=''])[47]");

	@FindBy(xpath = "(//span[@lightning-input_input=''])[2]")
	private WebElement verify_contact_information_checkbox_CP;
	private By verify_contact_information_checkbox1_CP = By.xpath("(//span[@lightning-input_input=''])[2]");

	@FindBy(xpath = ".//h1[text() = 'Oops...']")
	private WebElement vlidate_oops_message;
	private By vlidate_oops_message1 = By.xpath(".//h1[text() = 'Oops...']");
	
	@FindBy(xpath = "//SPAN[@lightning-input_input=''][text()='Show all lot numbers.']/preceding-sibling::SPAN")
	private WebElement show_all_lot_numbers_checkbox;
	private By show_all_lot_numbers_checkbox_ = By.xpath("//SPAN[@lightning-input_input=''][text()='Show all lot numbers.']/preceding-sibling::SPAN");
	
	@FindBy(xpath = "//*[@class='slds-icon slds-icon_large']")
	private WebElement close_button_diwa;
	private By close_button_diwa1 = By.xpath("//*[@class='slds-icon slds-icon_large']");
	
	@FindBy(xpath = "(.//button[@title='Confirm & Save Identification'])")
	private WebElement confirm_and_save_btn_home;
	private By confirm_and_save_btn_home1 = By.xpath(".//button[@title='Confirm & Save Identification']");
	
	@FindBy(xpath = "(//button[normalize-space()='Save Consent'])")
	private WebElement save_consent_btn;
	private By save_consent_btn1 = By.xpath("//button[normalize-space()='Save Consent']");
	
	@FindBy(xpath = "(//button[normalize-space()='Continue Editing and Save'])")
	private WebElement continue_editing_btn;
	private By continue_editing_btn1 = By.xpath("//button[normalize-space()='Continue Editing and Save']");
	
	@FindBy(xpath = "(//button[@title='Confirm & Save Administration'])")
	private WebElement confirm_save_adm_btn;
	private By confirm_save_adm_btn1 = By.xpath("//button[@title='Confirm & Save Administration']");
	
	@FindBy(xpath = "//button[text()='Confirm & Save Administration']")
	private WebElement confirm_save_adm_btn_modal_screen;
	private By confirm_save_adm_btn_modal_screen_ = By.xpath("//button[text()='Confirm & Save Administration']");

	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement confirm_admin_another_vaccine_modal_screen_btn;
	private By confirm_admin_another_vaccine_modal_screen_btn_ = By.xpath("//button[text()='Confirm']");

	@FindBy(xpath = "(//button[@title='Save Administration & Record Another Vaccine'])")
	private WebElement save_and_record_another_vaccine_btn;
	private By save_and_record_another_vaccine_btn1 = By.xpath("//button[@title='Save Administration & Record Another Vaccine']");

	@FindBy(xpath = ".//h2[text() = 'Vaccine Administration']")
	private WebElement validate_vaccine_admin_page_open;
	private By validate_vaccine_admin_page_open1 = By.xpath(".//h2[text() = 'Vaccine Administration']");
	
	@FindBy(xpath = ".//span[@title='In-Clinic Experience']")
	private WebElement ice_page_displayed;
	
	@FindBy(xpath = ".//div[@class = 'slds-card__body']//span[text() = 'Covid-19 Vaccine']")
	private WebElement click_on_covid19_vaccination_checkbox;
	private By click_on_covid19_vaccination_checkbox_ = By.xpath(".//div[@class = 'slds-card__body']//span[text() = 'Covid-19 Vaccine']");

	@FindBy(xpath = ".//div[@class = 'slds-card__body']//span[text() = 'Influenza Vaccine']")
	private WebElement click_on_influenza_vaccination_checkbox;
	private By click_on_influenza_vaccination_checkbox_ = By.xpath(".//div[@class = 'slds-card__body']//span[text() = 'Influenza Vaccine']");


	@FindBy(xpath = ".//span[text() = 'Select One']")
	private WebElement click_early_booking_reason;
	private By click_early_booking_reason1 = By.xpath(".//span[text() = 'Select One']");
	
	@FindBy(xpath = ".//span[text() = 'Travel']") //(.//span[text() = 'Travel'])[2]
	private WebElement select_early_booking_reason;
	private By select_early_booking_reason1 = By.xpath(".//span[text() = 'Travel']");
	
	@FindBy(xpath = "(.//span[text() = 'Travel'])[2]")
	private WebElement select_early_booking_reason_for_prodsup;
	
	@FindBy(xpath = ".//a[text() = 'Related']")
	private WebElement person_account_Related_tab;
	private By person_account_Related_tab_1 = By.xpath("(.//a[text() = 'Related'])");

	@FindBy(xpath = ".//span[text() = 'Related']")
	private WebElement person_account_Related_tab_CP;
	private By person_account_Related_tab_1_CP = By.xpath("(.//span[text() = 'Related'])");


	@FindBy(xpath = "(.//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs'])")
	private WebElement click_more_search_tabs;
	private By click_more_search_tabs1 = By.xpath(".//div[@class = 'slds-tabs_scoped']//button[@title = 'More Tabs']");
	
	@FindBy(xpath = ".//a[text()='Search by Clinic name']")
	private WebElement search_by_clinic_name_tab;
	private By search_by_clinic_name_tab1 = By.xpath(".//a[text()='Search by clinic name']");
	
	@FindBy(xpath = ".//div[text() = 'Appointment confirmed!']")
	private WebElement validate_appointment_confirm_message;
	private By validate_appointment_confirm_message1 = By.xpath(".//div[text() = 'Appointment confirmed!']");
	
	@FindBy(xpath = "//input[@name='BCH_Date__c']")
	private WebElement input_current_date;
	private By input_current_date1 = By.xpath("//input[@name='BCH_Date__c']");

	@FindBy(xpath = "//label[contains(text(),'Clinic Location')]/..//div[@role='none']//input[@type='text' and @role='textbox']")
	private WebElement clinicLocationUserDefaults;

	@FindBy(xpath = "//div[contains(text(),'Advanced Settings')]")
	private WebElement btnAdvancedSettingsUserDefaults;

	@FindBy(xpath = "(//span[@title='Agent']/../../../../../../..//span[@class='slds-grid slds-grid_align-spread'])[1]")
	private WebElement agentUserDefaults;

	@FindBy(xpath = "//span[@title='Lot#']/../../../../../../..//input[@class='slds-input slds-combobox__input slds-combobox__input-value combobox-input-class']")
	private WebElement lotUserDefaults;

	@FindBy(xpath = "(//span[@title='Trade Name']/../../../../../../..//span[@class='slds-grid slds-grid_align-spread'])[2]")
	private WebElement tradeNameUserDefaults;

	@FindBy(xpath = ".//span[text() = 'User Defaults']")
	private WebElement user_defaults_tab;
	private By user_defaults_tab1 = By.xpath(".//span[text() = 'User Defaults']");
	
	@FindBy(xpath = ".//button[@aria-label = 'Agent, Select an option']")
	private WebElement click_vaccine_agent_dropdown;
	private By click_vaccine_agent_dropdown1 = By.xpath(".//button[@aria-label = 'Agent, Select an option']");
	
	@FindBy(xpath = ".//span[text() = 'COVID-19 mRNA']")
	private WebElement select_vaccine_agent_dropdown;
	private By select_vaccine_agent_dropdown1 = By.xpath(".//span[text() = 'COVID-19 mRNA']");

	@FindBy(xpath = ".//span[text() = 'Influenza-LAIV']")
	private WebElement select_vaccine_agent_influenza_dropdown;
	private By select_vaccine_agent_influenza_dropdown1 = By.xpath(".//span[text() = 'Influenza-LAIV']");

	@FindBy(xpath = ".//span[text() = 'Pneumo-P-23']")
	private WebElement select_vaccine_agent_pneumo_dropdown;
	private By select_vaccine_agent_pneumo_dropdown1 = By.xpath(".//span[text() = 'Pneumo-P-23']");

	@FindBy(xpath = ".//button[@aria-label = 'Route, Select an Option']")
	private WebElement click_route_dropdown;
	private By click_route_dropdown1 = By.xpath(".//button[@aria-label = 'Route, Select an Option']");

	@FindBy(xpath = ".//span[text() = 'Intranasal']")
	private WebElement select_route_intranasal_dropdown;
	private By select_route_intranasal_dropdown1 = By.xpath(".//span[text() = 'Intranasal']");

	//@FindBy(xpath = ".//span[text() = 'Select an Option']")
	//private WebElement click_dosage_dropdown;
	//private By click_dosage_dropdown1 = By.xpath(".//span[text() = 'Select an Option']");
	@FindBy(xpath = ".//button[@name='dosePicklist']")
	private WebElement click_dosage_dropdown;
	private By click_dosage_dropdown1 = By.xpath(".//button[@name='dosePicklist']");

	//@FindBy(xpath = "(.//lightning-combobox[@data-id='dosage']//span[@class='slds-truncate'])[2]")
	//private WebElement select_dosage_from_dropdown;
	//private By select_dosage_from_dropdown1 = By.xpath("(.//lightning-combobox[@data-id='dosage']//span[@class='slds-truncate'])[2]");
	@FindBy(xpath = ".//span[@title='0.2']")
	private WebElement select_dosage_from_dropdown;
	private By select_dosage_from_dropdown1 = By.xpath(".//span[@title='0.2']");


	@FindBy(xpath = ".//button[@name = 'injectionSite']")
	private WebElement click_site_dropdown;
	private By click_site_dropdown1 = By.xpath(".//button[@name = 'injectionSite']");

	@FindBy(xpath = ".//span[text() = 'Arm - Left deltoid']")
	private WebElement select_site_left_deltoid_from_dropdown;
	private By select_site_left_deltoid_from_dropdown1 = By.xpath(".//span[text() = 'Arm - Left deltoid']");

	//@FindBy(xpath = "(.//div[@class = 'slds-form-element'])[2]")
	//private WebElement click_lot_number_dropdown;
	//private By click_lot_number_dropdown1 = By.xpath("(.//div[@class = 'slds-form-element'])[2]");

	@FindBy(xpath = ".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']")
	private WebElement click_lot_number_dropdown;
	private By click_lot_number_dropdown1 = By.xpath(".//div[@class = 'slds-col slds-size_1-of-2 dropdown-container']//div[@class = 'slds-form-element']");

	@FindBy(xpath = ".//li[@title = 'T005729-CC07 - Exp. 2021 November 16']")
	private WebElement select_pneumo_lot_number_dropdown;
	private By select_pneumo_lot_number_dropdown1 = By.xpath(".//li[@title = 'T005729-CC07 - Exp. 2021 November 16']");


	@FindBy(xpath = ".//h1[text() = 'Client Search']")
	private WebElement validate_home_client_search_page_open;
	private By validate_home_client_search_page_open1 = By.xpath(".//h1[text() = 'Client Search']");
	
	@FindBy(xpath = ".//a[text()='Search clinic name']")
	private WebElement click_select_search_clinic;
	private By click_select_search_clinic1 = By.xpath(".//a[text()='Search by clinic name']");
	
	@FindBy(xpath = "//a[contains(text(),'Supply Distribution_1 - SDST-000')]")
	private WebElement click_supply_distribution;
	private By click_supply_distribution1 = By.xpath("//a[contains(text(),'Supply Distribution_1 - SDST-000')]");
	
	@FindBy(xpath = "//BUTTON[@class='slds-button slds-button_icon-border-filled']")
	private WebElement select_drpdown_to_receive_supplies;
	private By select_drpdown_to_receive_supplies1 = By.xpath("//BUTTON[@class='slds-button slds-button_icon-border-filled']");
	
	@FindBy(xpath = "//span[text()='Receive Supplies']")
	private WebElement click_to_select_receive_supplies;
	private By click_to_select_receive_supplies1 = By.xpath("//span[text()='Receive Supplies']");
	
	@FindBy(xpath = "//label[@class='slds-form-element__label'][text()='Supply Item']")
	private WebElement validate_supply_item_field;
	private By validate_supply_item_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Item']");
	
	@FindBy(xpath = "//lightning-formatted-text[text()='All Ages - Atlin Health Centre']")
	private WebElement validate_clinic_name_before_booking;
	private By validate_clinic_name_before_booking1 = By.xpath("//lightning-formatted-text[text()='All Ages - Atlin Health Centre']");
	
	@FindBy(xpath = "//lightning-formatted-text[text()='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']")
	private WebElement validate_clinic_name_after_booking;
	private By validate_clinic_name_after_booking1 = By.xpath("//lightning-formatted-text[text()='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']");
	
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
	
	@FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
	private WebElement Create_Immunization_Record;
	private By Create_Immunization_Record1 = By.xpath("//button[contains(text(),'Create Immunization Record')]");
	
	@FindBy(xpath = "//button[text() = 'Confirm']")
	private WebElement confirm_button;
	private By confirm_button1 = By.xpath("//button[text() = 'Confirm']");
	
	@FindBy(xpath = "//option[contains(text(),'Select an option')]")
	private WebElement select_an_option;
	private By select_an_option1 = By.xpath("//option[contains(text(),'Select an option')]");
	
	@FindBy(xpath = "//option[contains(text(),'COVID-19 mRNA')]")
	private WebElement covidmRna;
	private By covidmRna2 = By.xpath("//option[contains(text(),'COVID-19 mRNA')]");
	
	@FindBy(xpath = "(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[2]")
	private WebElement inputDate;
	private By inputDate1 = By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon_right'])[2]");
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]")
	private WebElement yes_button_save_on_popup_window;
	private By yes_button_save_on_popup_window1 = By.xpath("//button[@class='slds-button slds-button_brand'][contains(text(),'Yes')]");
	
	@FindBy(xpath = "//button[contains(text(),'Record Immunization')]")
	private WebElement recordImmunizationBtn;
	
	@FindBy(xpath = "(//input[@placeholder = 'Search People...'])[2]")
	private WebElement informed_consent_provider_dropdown;
	private By informed_consent_provider_dropdown_ = By.xpath("(//input[@placeholder = 'Search People...'])[2]");

	@FindBy(xpath = "(//input[@placeholder = 'Search People...'])[2]")
	private WebElement immunizing_agent_provider_dropdown;
	private By immunizing_agent_provider_dropdown_ = By.xpath("(//input[@placeholder = 'Search People...'])[2]");

	@FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
	private WebElement select_inform_consent_provider;
	private By select_inform_consent_provider_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");

	@FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
	private WebElement select_immunizing_agent_provider;
	private By select_immunizing_agent_provider_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");


	@FindBy(xpath = "//input[@name='effectiveToDate']")
	private WebElement consentEffectiveToDate;
	
	@FindBy(xpath = "//button[contains(text(),'Save Consent')]")
	private WebElement saveConsentButton;
	
	@FindBy(xpath = "//span[@title='Appointments']")
	private WebElement appointmentSection;
	
	@FindBy(xpath = "//button[@name='injectionSite']")
	private WebElement selectSite;
	private By selectSite1 = By.xpath("//button[@name='injectionSite']");
	
	@FindBy(xpath = "//li[@title='300042698 - Exp. 2021 June 18']")
	private WebElement select_lot;
	private By select_lot_ = By.xpath("//li[@title='300042698 - Exp. 2021 June 18']");

	@FindBy(xpath = "//span[@title='Arm - Left deltoid']")
	private WebElement select_injection_site_value;
	private By select_injection_site_value1 = By.xpath("//span[@title='Arm - Left deltoid']");
	
	
	@FindBy(xpath = "//button[@name='dosePicklist']")
	private WebElement select_dosage_field;
	private By select_dosage_field1 = By.xpath("//button[@name='dosePicklist']");
	
	@FindBy(xpath = "//span[@title='0.3']")
	private WebElement select_dosage;
	private By select_dosage1 = By.xpath("//span[@title='0.5']");
	
	@FindBy(xpath = "//label[contains(text(),'Site')]/../../../..//button[@type='submit']")
	private WebElement saveAgain;
	
	@FindBy(xpath = "//button[@title='Confirm & Save Administration']")
	private WebElement confirmAndSave;
	
	@FindBy(xpath = "//button[contains(text(),'Confirm and Save')]")
	private WebElement lastConfirmAndSave;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement save_button_historical_dose;
	private By save_button_historical_dose1 = By.xpath("//button[contains(text(),'Save')]");
	
	@FindBy(xpath = ".//input[@data-id = 'userinput']")
	private WebElement search_clinic;
	private By search_clinic1 = By.xpath(".//input[@data-id = 'userinput']");
	
	@FindBy(xpath = "(//span[contains(text(),'Cancel')])[2]")
	private WebElement cancel_button_receive_supplies;
	private By cancel_button_receive_supplies1 = By.xpath("(//span[contains(text(),'Cancel')])[2]");
	
	@FindBy(xpath = "//input[@placeholder='Search Supply Items...']")
	private WebElement click_to_select_supply_item;
	private By click_to_select_supply_item1 = By.xpath("//input[@placeholder='Search Supply Items...']");
	
	@FindBy(xpath = "//span[contains(text(),'COMIRNATY (Pfizer) - 35035BD-CC01')]")
	private WebElement choose_supply_item;
	private By choose_supply_item1 = By.xpath("//span[contains(text(),'COMIRNATY (Pfizer) - 35035BD-CC01')]");
	
	@FindBy(xpath = "(//input[@class='slds-input'])[2]")
	private WebElement click_to_select_quantity;
	private By click_to_select_quantity1 = By.xpath("(//input[@class='slds-input'])[2]");
	
	@FindBy(xpath = "//button[@name='distributionBox']")
	private WebElement supply_distribution_to;
	private By supply_distribution_to1 = By.xpath("//button[@name='distributionBox']");
	
	@FindBy(xpath = "(//span[contains(text(),'Supply Distribution_1 - SDST-000')])[1]")
	private WebElement select_supply_distributor;
	private By select_supply_distributor1 = By.xpath("(//span[contains(text(),'Supply Distribution_1 - SDST-000')])[1]");
	
	@FindBy(xpath = "//button[@aria-label='Reason for Reception, --None--']")
	private WebElement click_reason;
	private By click_reason1 = By.xpath("//button[@aria-label='Reason for Reception, --None--']");
	
	@FindBy(xpath = "//span[@title='Other']")
	private WebElement select_reason;
	private By select_reason1 = By.xpath("//span[@title='Other']");
	
	@FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
	private WebElement create_Immunization_Record;
	private By creat_Immunization_Record1 = By.xpath("//button[contains(text(),'Create Immunization Record')]");
	
	@FindBy(xpath = ".//button[text() = 'Save']")
	private WebElement save_immune_info_btn;
	private By save_immune_info_btn_ = By.xpath(".//button[text() = 'Save']");
	
	@FindBy(xpath = "//span[text() = 'Covid-19 Vaccine']")
	private WebElement checkBoxCovid19Vaccine;
	
	@FindBy(xpath = "//span[text() = 'Influenza Vaccine']")
	private WebElement checkBoxInfluenzaVaccine;

	@FindBy(xpath = ".//lightning-base-formatted-text[text() = 'Influenza-LAIV']")
	private WebElement validate_create_immunization_record_Influenza;
	private By validate_create_immunization_record_Influenza_ = By.xpath(".//lightning-base-formatted-text[text() = 'Influenza-LAIV']");

	@FindBy(xpath = "(.//lightning-base-formatted-text[text() = 'After Care'])[2]")
	private WebElement validate_after_care_status_immunization_record_Influenza;
	private By validate_after_care_status_immunization_record_Influenza_ = By.xpath("(.//lightning-base-formatted-text[text() = 'After Care'])[2]");

	@FindBy(xpath = ".//lightning-base-formatted-text[text() = 'Pneumo-P-23']")
	private WebElement validate_create_immunization_record_Pneumo;
	private By validate_create_immunization_record_Pneumo_ = By.xpath(".//lightning-base-formatted-text[text() = 'Influenza-LAIV']");

	@FindBy(xpath = "(.//lightning-base-formatted-text[text() = 'After Care'])[1]")
	private WebElement validate_after_care_status_immunization_record_Pneumo;
	private By validate_after_care_status_immunization_record_Pneumo_ = By.xpath("(.//lightning-base-formatted-text[text() = 'After Care'])[2]");



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

	public void SearchForCitizenAlternativeWay(String citizen) throws InterruptedException {
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
		waitForElementToBeVisible(driver, profile_in_search_dropdown, 10);
		Thread.sleep(2000);
		profile_in_search_dropdown.click();
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
	
	public boolean userFoundWithParameters(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
		By userFoundWithParameter = By.xpath("//a[@title='" + legalFirstName + " " + legalMiddleName + " " + legalLastName + "']");
		if (!isDisplayed(userFoundWithParameter)) {
			return false;
		}
		WebElement userFoundWithParameterId = driver.findElement(userFoundWithParameter);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", userFoundWithParameterId);
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
	
	public boolean userCostaFound() throws InterruptedException {
		if (!isDisplayed(user_Costa_found1)) {
			return false;
		}
		waitForElementToBeLocated(driver, user_Costa_found1, 10);
		WebElement element = driver.findElement(user_Costa_found1);
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
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_related_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
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
	
	public void userClickCitizen() throws InterruptedException {
		waitForElementToBeVisible(driver, click_on_citizen, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_on_citizen1);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		
	}
	
	public void userClickCitizenNew(String name) throws InterruptedException {
		WebElement fullNameWebElement = driver.findElement(By.xpath("//div[@aria-label = 'Profiles||List View']//a[contains(text(),'" + name + "')]"));
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
	
	public void clickDropdownMenu() throws InterruptedException {
		waitForElementToBeVisible(driver, dropdownMenu, 10);
		Thread.sleep(2000);
		this.dropdownMenu.click();
		Thread.sleep(2000);
	}
	
	public void selectSupplyLocationFromDropdown() {
		this.supplyLocationInDropdown.click();
	}
	
	public void selectSupplyItemsFromDropdown() throws InterruptedException {
		waitForElementToBeVisible(driver, supplyItemsInDropdown, 10);
		Thread.sleep(2000);
		this.supplyItemsInDropdown.click();
		Thread.sleep(2000);
	}
	
	public void selectSupplyLocationName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_loc, 10);
		Thread.sleep(2000);
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
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
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
	
	public void clickToSearchClinic() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_select_search_clinic1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_select_search_clinic1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
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
		Thread.sleep(2000);
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
		Thread.sleep(2000);
		WebElement element = driver.findElement(appointment_scheduling_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void navigateAppointmentSchedulingTab_CP() throws InterruptedException {
		waitForElementToBeLocated(driver, appointment_scheduling_tab1_CP, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(appointment_scheduling_tab1_CP);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickOnVaccinationCheckbox() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)");
		Thread.sleep(3000);
		waitForElementToBeVisible(driver, click_on_covid19_vaccination_checkbox, 10);
		Thread.sleep(2000);
		click_on_covid19_vaccination_checkbox.click();
	}

	public void clickOnVaccinationInfluenzaCheckbox() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, click_on_influenza_vaccination_checkbox, 10);
		Thread.sleep(5000);
		click_on_influenza_vaccination_checkbox.click();
	}
	
	public void selectEarlyBookingReason() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_early_booking_reason, 10);
		Thread.sleep(2000);
		click_early_booking_reason.click();
		Thread.sleep(2000);
		if (isDisplayed(select_early_booking_reason)) {
			//waitForElementToBeVisible(driver, select_early_booking_reason, 10);
			Thread.sleep(2000);
			select_early_booking_reason.click();
		}
		else {//it's never go here - it's the same xpath for devit and prodsuppqa
			waitForElementToBeVisible(driver, select_early_booking_reason_for_prodsup, 10);
			Thread.sleep(2000);
			select_early_booking_reason_for_prodsup.click();
		}
	}
	
	public void clickOnMoreSearchTabs() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_more_search_tabs, 10);
		click_more_search_tabs.click();
	}
	
	public void clickFacilityOptionLocation() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", option_loc_facility);
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
			System.out.println("/*---'Appointment confirmed!' message shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---the screen does not show up 'Appointment confirmed!'");
			return false;
		}
	}
	
	public void ClickGoToInClinicExperienceButton() throws InterruptedException {
		waitForElementToBeVisible(driver, click_navigate_to_ICE_btn, 10);
		Thread.sleep(2000);
		click_navigate_to_ICE_btn.click();
	}
	
	public void ClickRebookAppointment() throws InterruptedException {
		waitForElementToBeVisible(driver, click_to_rebook_button, 10);
		click_to_rebook_button.click();
	}
	
	public void ValidateClickRebookAppointmentButtonIsDisabled() throws InterruptedException {
		waitForElementToBeLocated(driver, click_to_rebook_button1, 10);
		click_to_rebook_button.isDisplayed();
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
	
	public String ValidateclinicNameAfterRebook() throws InterruptedException {
		WebElement element = driver.findElement(validate_clinic_name_after_booking1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String ValidateClinicNameBeforeRebook() throws InterruptedException {
		waitForElementToBeVisible(driver, validate_clinic_name_before_booking, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(validate_clinic_name_before_booking1);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	
	public void ContinueEditingButton() throws InterruptedException {
		waitForElementToBeVisible(driver, continue_editing_btn, 10);
		continue_editing_btn.click();
	}
	
	public void HomePageClickConfirmAndSaveButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, confirm_and_save_btn_home, 10);
		Thread.sleep(2000);
		confirm_and_save_btn_home.click();
	}
	
	public void clickVerifyContactInformation() throws InterruptedException {
		Thread.sleep(5000);
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

	public void clickVerifyContactInformation_CP() throws InterruptedException {
		Thread.sleep(5000);
		//Scrolling to bottom of the page
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, verify_contact_information_checkbox_CP, 10);
		Thread.sleep(2000);
		verify_contact_information_checkbox_CP.click();
		Thread.sleep(2000);
	}
	
	public void selectVaccineAgent() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,550)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_vaccine_agent_dropdown, 10);
		click_vaccine_agent_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_vaccine_agent_dropdown, 10);
		Thread.sleep(2000);
		select_vaccine_agent_dropdown.click();
	}

	public void selectVaccineAgentInfluenza() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, click_vaccine_agent_dropdown, 10);
		click_vaccine_agent_dropdown.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_vaccine_agent_influenza_dropdown, 10);
		Thread.sleep(2000);
		select_vaccine_agent_influenza_dropdown.click();
	}

	public void selectVaccineAgentPneumo() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, click_vaccine_agent_dropdown, 10);
		Thread.sleep(5000);
		click_vaccine_agent_dropdown.click();
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, select_vaccine_agent_pneumo_dropdown, 10);
		Thread.sleep(5000);
		select_vaccine_agent_pneumo_dropdown.click();
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

	public void selectDosge() throws InterruptedException {
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
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,450)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, save_consent_btn, 10);
		Thread.sleep(2000);
		save_consent_btn.click();
	}
	
	public void ClickConfirmAndSaveAdministrationButton() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, confirm_save_adm_btn, 10);
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
		waitForElementToBeVisible(driver, confirm_save_adm_btn_modal_screen, 10);
		Thread.sleep(2000);
		confirm_save_adm_btn_modal_screen.click();
	}

	public void ClickConfirmAdminAnotherVaccineModalScreenButton() throws InterruptedException {
		waitForElementToBeVisible(driver, confirm_admin_another_vaccine_modal_screen_btn, 10);
		Thread.sleep(2000);
		confirm_admin_another_vaccine_modal_screen_btn.click();
	}
	
	
	public boolean validateVaccineAdminPageOpen() throws InterruptedException {
		try {
			waitForElementToBeVisible(driver, validate_vaccine_admin_page_open, 10);
			Thread.sleep(2000);
			System.out.println("/*---Vaccine admin ICE page shown up");
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("/*---no Vaccine Admin ICE Page show up");
			return false;
		}
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

	public void clickOnPersonAccountRelatedTab_CP() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = driver.findElement(person_account_Related_tab_1_CP);
		isDisplayed(person_account_Related_tab_1_CP);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void selectSearchByClinicNameTab() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,550)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, search_by_clinic_name_tab1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(search_by_clinic_name_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void searchClinicName(String clinicNameToSearch) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_clinic, 10);
		Thread.sleep(2000);
		select_clinic.click();
		Thread.sleep(2000);
		select_clinic.sendKeys(clinicNameToSearch);
		select_clinic.sendKeys(Keys.RETURN);
	}
	
	public void clickOnFacilityOptionLocation() throws InterruptedException {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", option_loc_facility);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, option_loc_facility, 10);
		option_loc_facility.click();
	}
	
	public void refreshBrowser() throws InterruptedException {
		driver.navigate().refresh();
	}
	
	public void clickUserDefaultsTab() throws InterruptedException {
		waitForElementToBeLocated(driver, user_defaults_tab1, 10);
		WebElement element = driver.findElement(user_defaults_tab1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void selectClinicUserDefaults(String clinicLocation) throws InterruptedException {
		click(clinicLocationUserDefaults);
		clinicLocationUserDefaults.sendKeys(clinicLocation);
	}

	public HashMap clickBtnAdvancedSettingsAndSaveData() throws InterruptedException {
		HashMap<String, String> agentLotTradeNameMap = new HashMap<>();
		click(btnAdvancedSettingsUserDefaults);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		Thread.sleep(1000);
		String agent = agentUserDefaults.getText();
		String lot = getValue(lotUserDefaults);
		String tradeName = tradeNameUserDefaults.getText();
		agentLotTradeNameMap.put("agent",agent);
		agentLotTradeNameMap.put("lot",lot);
		agentLotTradeNameMap.put("tradeName",tradeName);
		return agentLotTradeNameMap;
	}
	
	public void inputCurrentDateUserDefaults() throws InterruptedException {
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
	
	public void inputPreviousDateUserDefaults() throws InterruptedException {
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
	
	public void ClickAgentValue() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_agent_value1, 10);
		WebElement element = driver.findElement(click_agent_value1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public void SelectAgentValue() throws InterruptedException {
		waitForElementToBeVisible(driver, select_agent_name, 10);
		WebElement search_input = driver.findElement(select_agent_name1);
		search_input.click();
	}
	
	public void clickSupplyDistribution() throws InterruptedException {
		waitForElementToBeLocated(driver, click_supply_distribution1, 10);
		WebElement element = driver.findElement(click_supply_distribution1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void SelectDropDownToClickReceiveSuppliesButton() throws InterruptedException {
		waitForElementToBeLocated(driver, select_drpdown_to_receive_supplies1, 10);
		WebElement element = driver.findElement(select_drpdown_to_receive_supplies1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public void ClickDropDownToClickReceiveSuppliesButton() throws InterruptedException {
		waitForElementToBeLocated(driver, click_to_select_receive_supplies1, 10);
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(click_to_select_receive_supplies1);
		Thread.sleep(2000);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
	}
	
	public String validateSupplyItemField() throws InterruptedException {
		WebElement element = driver.findElement(validate_supply_item_field1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String validateQTYField() throws InterruptedException {
		WebElement element = driver.findElement(validate_qty_field1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String validateDCFField() throws InterruptedException {
		WebElement element = driver.findElement(validate_dcf_field1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String validateDosesField() throws InterruptedException {
		WebElement element = driver.findElement(validate_doses_field1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public String validateSupplyDistributionToField() throws InterruptedException {
		WebElement element = driver.findElement(supply_distribution_to_field1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	
	public void ValidateSaveButtonIsDisplayedOnReceiveSupplies() throws InterruptedException {
		waitForElementToBeLocated(driver, save_button_receive_supplies1, 10);
		save_button_receive_supplies.isDisplayed();
	}
	
	public void ClickSaveButton() throws InterruptedException {
		waitForElementToBeLocated(driver, save_button_receive_supplies1, 10);
		save_button_receive_supplies.click();
	}
	
	public void ValidateCancelButtonIsDisplayedOnReceiveSupplies() throws InterruptedException {
		waitForElementToBeLocated(driver, cancel_button_receive_supplies1, 10);
		cancel_button_receive_supplies.isDisplayed();
	}
	
	public void clickSupplyItemTextBox() throws InterruptedException {
		waitForElementToBeLocated(driver, click_to_select_supply_item1, 10);
		WebElement element1 = driver.findElement(click_to_select_supply_item1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
	}
	
	public void selectSupplyItem(String supplyItem) throws InterruptedException {
		WebElement element = driver.findElement(choose_supply_item1);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, choose_supply_item, 10);
		Thread.sleep(2000);
		WebElement search_input = driver.findElement(choose_supply_item1);
		Thread.sleep(2000);
		search_input.click();
	}
	
	public void enterQuantity() throws InterruptedException {
		waitForElementToBeLocated(driver, click_to_select_quantity1, 10);
		WebElement element = driver.findElement(click_to_select_quantity1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		click(click_to_select_quantity1);
		element.sendKeys("1");
	}
	
	public void selectIncomingSupplyDistribution() throws InterruptedException {
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
	
	public void selectReasonForReception() throws InterruptedException {
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
	
	public void clickCreatImmunizationRecord() throws InterruptedException {
		create_Immunization_Record.click();
	}

	public void ValidateCreateImmunizationRecordButtonIsDisplayed() throws InterruptedException {
		waitForElementToBeLocated(driver, Create_Immunization_Record1, 10);
		Thread.sleep(2000);
		Create_Immunization_Record.isDisplayed();
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
	
	public void clickConfirmButton() throws InterruptedException {
		waitForElementToBeVisible(driver, confirm_button, 10);
		this.confirm_button.click();
	}
	
	public void clickSelectAnOptionDropdown() {
		this.select_an_option.click();
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
	
	public void clickRecordImmunization() throws InterruptedException {
		waitForElementToBeVisible(driver, recordImmunizationBtn, 10);
		click(recordImmunizationBtn);
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

	public boolean selectDateOfAdministration() throws InterruptedException {
		if (!isInputActive(inputDate)) {
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
	
	public void enterConsentEffectiveToDate() throws InterruptedException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date yesterday = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		String yesterdayAsString = dateFormat.format(yesterday);
		this.consentEffectiveToDate.sendKeys(yesterdayAsString, Keys.ENTER);
	}
	
	public void clickSaveConsent() throws InterruptedException {
		saveConsentButton.click();
	}

	public void selectInformedConsentProvider(String Provider) throws InterruptedException {
		//scrolling up
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
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
		saveAgain.click();
	}
	
	public void confirmAndSaveAdministration() throws InterruptedException {
		confirmAndSave.click();
	}
	
	public void summaryConfirmAndSave() throws InterruptedException {
		lastConfirmAndSave.click();
	}
	
	public void clickShowAllLotNumbersCheckBox() throws InterruptedException {
		waitForElementToBeVisible(driver, show_all_lot_numbers_checkbox, 10);
		Thread.sleep(2000);
		show_all_lot_numbers_checkbox.click();
		Thread.sleep(2000);
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
	
	public void selectOneOption(String vaccine) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
		if (vaccine.equalsIgnoreCase("Covid19Vaccine")) {
			Thread.sleep(5000);
			click(checkBoxCovid19Vaccine);
		} else if (vaccine.equalsIgnoreCase("InfluenzaVaccine")) {
			Thread.sleep(5000);
			click(checkBoxInfluenzaVaccine);
		} else {
			Thread.sleep(5000);
			click(checkBoxCovid19Vaccine);
			click(checkBoxInfluenzaVaccine);
		}
	}
	
	public void NavigateToAppointmentsSection() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1100)", "");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, appointmentSection, 10);
		Thread.sleep(2000);
	}
	
}

