package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static constansts.Domain.SUPPLY_LOCATION_1;
import static constansts.Domain.SUPPLY_LOCATION_2;
import static constansts.Header.*;
import static constansts.Header.SUPPLY_TRANSACTION_NAME_FULL;
import static org.testng.Assert.assertTrue;


public class SupplyConsolePage extends BasePage {
	/*---------Properties-------*/
	@FindBy(xpath = "(.//span[@class = 'slds-truncate'])[2]")
	private WebElement supply_locations_tab;
	private By supply_locations_tab1 = By.xpath("(.//span[@class = 'slds-truncate'])[2]");
	
	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']")
	private WebElement supply_supply_location_1;
	private By supply_supply_location_1_ = By.xpath(".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']");
	
	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Automation Supply Location_2']")
	private WebElement supply_supply_location_2;
	private By supply_supply_location_2_ = By.xpath(".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']");
	
	@FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)")
	private WebElement rows_supply_containers_from_count_path;
	private By rows_supply_containers_from_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)");
	
	@FindBy(xpath = "//span[contains(text(),'Draft')]/../../../../../../..//button[text() = 'Transfer']")
	private WebElement btnTransferDraftOnTransactionsPage;

	@FindBy(xpath = "//h2[@class='slds-text-heading_medium slds-hyphenate']/../..//button[text() = 'Transfer']")
	private WebElement btnTransferDraftOnContainerTransferPage;

	@FindBy(xpath = "//button[contains(text(),'Transfer Transactions')]")
	private WebElement btnTransferTransactionsDraftOnTransactionsPage;

	@FindBy(xpath = "//label[contains(text(),'Comments')]")
	private WebElement labelComments;

	@FindBy(xpath = ".//button[text() = 'Transfer']")
	private WebElement bulk_transfers_button;

	@FindBy(xpath = ".//button[text() = 'Cancel Transfer']")
	private WebElement bulk_cancel_button;
	private By bulk_transfers_button_1 = By.xpath(".//button[text() = 'Transfer']");
	
	@FindBy(xpath = "(//section[@role='dialog']//button[text() = 'Transfer'])")
	private WebElement bulk_transfers_dialog_button;
	private By bulk_transfers_dialog_button_1 = By.xpath("(//section[@role='dialog']//button[text() = 'Transfer'])");
	
	@FindBy(xpath = ".//input[@class='slds-combobox__input slds-input']")
	private WebElement SupplyLocations;
	
	public void clickSupplyLocations() {
		SupplyLocations.click();
	}
	
	@FindBy(xpath = "//input[@class=\"slds-combobox__input slds-input\"]")
	private WebElement shipTo_address;
	private By shipTo_address_ = By.xpath("//input[@class=\"slds-combobox__input slds-input\"]");
	
	@FindBy(xpath = "//input[@class=\"slds-input\"]")
	private WebElement requestedDeliveryDate;
	private By requested_delivery_date = By.xpath("//input[@class=\"slds-input\"]");
	
	@FindBy(xpath = "//div[@class=\"slds-form-element__control slds-grow\"]//input")
	private WebElement requestedDose;
	private By requested_dose = By.xpath("//div[@class=\"slds-form-element__control slds-grow\"]//input");
	
	@FindBy(xpath = "//button[contains(text(),\"Save\")]")
	private WebElement saveButton;
	private By save_button = By.xpath("//button[contains(text(),\"Save\")]");
	
	@FindBy(xpath = "//input[@class=\"slds-combobox__input slds-input\"]")
	private WebElement shipAddress;
	private By ship_address = By.xpath("//input[@class=\"slds-combobox__input slds-input\"]");
	
	@FindBy(xpath = "//tbody/tr[1]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]")
	private WebElement checkBox;
	private By check_box = By.xpath("//tbody/tr[1]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]");
	
	@FindBy(xpath = "//input[@name='BCH_Requested_Delivery_Date__c']")
	private WebElement inputDate;
	private By input_data = By.xpath("//input[@name='BCH_Requested_Delivery_Date__c']");
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextButton;
	private By next_button = By.xpath("//button[contains(text(),'Next')]");
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	private WebElement saveSubmitRequisition;
	private By save_Submit_Requisition = By.xpath("//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
	
	@FindBy(xpath = "//input[@name='BCH_Expected_Delivery_Date__c']")
	private WebElement expectedDeliveryDateCalendar;
	private By expected_delivery_date_calendar = By.xpath("//span[@name = 'BCH_Expected_Delivery_Date__c']");
	
	@FindBy(xpath = "//button[text() = 'Submit Requisition'] | //a[@title = 'Submit Requisition']")
	private WebElement submitRequisition;
	private By submit_requisition = By.xpath("//button[text() = 'Submit Requisition']");
	
	@FindBy(xpath = ".//span[@title='Health Connect - Supply Console']")
	private WebElement supply_page_displayed;

	@FindBy(xpath = ".//input[@placeholder='Search Supply Locations...']")
	private WebElement search_supply_location_2_To;
	private By search_supply_location_2_To_ = By.xpath(".//input[@placeholder='Search Supply Locations...']");
	
	@FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='Automation Supply Location_2']")
	private WebElement select_supply_location_2_To;
	private By select_supply_location_2_To_ = By.xpath("//lightning-base-combobox-formatted-text[@title='Automation Supply Location_2']");
	
	@FindBy(xpath = ".//input[@placeholder='Search Supply Locations...']")
	private WebElement search_supply_location_1_To;
	private By search_supply_location_1_To_ = By.xpath(".//input[@placeholder='Search Supply Locations...']");
	
	@FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='Automation Supply Location_1']")
	private WebElement select_supply_location_1_To;
	private By select_supply_location_1_To_ = By.xpath("//lightning-base-combobox-formatted-text[@title='Automation Supply Location_1']");
	
	@FindBy(xpath = "//section[@role='dialog']//button[text()='Close']")
	private WebElement bulk_dialog_close_button;
	private By bulk_dialog_close_button_1 = By.xpath("//section[@role='dialog']//button[text()='Close']");
	
	@FindBy(xpath = "(.//*[text() = 'Transactions'])")
	private WebElement transactions_tab;
	private By transactions_tab_1 = By.xpath("(.//a[text() = 'Transactions'])");
	//private By transactions_tab_1 = By.xpath("(.//span[text() = 'Automation Supply Location_1'] and .//a[text() = 'Transactions'])");
	
	@FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody)[2]")
	private WebElement rows_outgoing_transactions_count_path;
	//private By rows_outgoing_transactions_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)");
	
	@FindBy(xpath = "(.//a[text() = 'Related'])")
	private WebElement supply_transactions_Related_tab;
	private By supply_transactions_Related_tab_1 = By.xpath("(.//a[text() = 'Related'])");
	
	@FindBy(xpath = ".//span[contains(text(),'SSHP-00')]")
	private WebElement supply_shipment_name;
	private By supply_shipment_name_1 = By.xpath(".//span[contains(text(),'SSHP-00')]");
	
	@FindBy(xpath = ".//span[contains(text(),'Transaction From')] and .//span[contains(text(),'STRX-')]")
	private WebElement outgoing_supply_transaction_id;
	private By outgoing_supply_transaction_id_1 = By.xpath(".//span[contains(text(),'Transaction From')] and .//span[contains(text(),'STRX-')]");
	
	@FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody)[1]")
	private WebElement rows_incoming_transactions_count_path;
	
	@FindBy(xpath = "//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux']")
	private WebElement rows_draft_transactions_count_path;

	@FindBy(xpath = ".//button[text() = 'Confirm Transfer']")
	private WebElement bulk_confirm_incoming_transfers_button;
	private By bulk_confirm_incoming_transfers_button_1 = By.xpath(".//button[text() = 'Confirm Transfer']");
	
	@FindBy(xpath = ".//span[text() = 'Select Supply Distributor']")
	private WebElement search_incoming_supply_distributor;
	private By search_incoming_supply_distributor_ = By.xpath(".//span[text() = 'Select Supply Distributor']");
	
	@FindBy(xpath = ".//span[contains(text(),'Select an Option')]")
	private WebElement search_incoming_supply_distributor_1_2;

	@FindBy(xpath = "//input[contains(@placeholder,'Search Supply Items')]")
	private WebElement searchSupplyItems;
	private By search_incoming_supply_distributor_1_2_ = By.xpath(".//span[contains(text(),'Select an Option')]");
	
	@FindBy(xpath = "//span[contains(text(),'Supply Distribution_2_1')]")
	private WebElement select_incoming_supply_distributor;
	private By select_incoming_supply_distributor_ = By.xpath("//span[contains(text(),'Supply Distribution_2_1')]");
	
	@FindBy(xpath = "//span[contains(text(),'Supply Distribution_1_2')]")
	private WebElement select_same_clinic_supply_distributor_1_2;
	private By select_same_clinic_supply_distributor_1_2_ = By.xpath("//span[contains(text(),'Supply Distribution_1_2')]");
	
	
	@FindBy(xpath = ".//button[text() = 'Confirm Transaction']")
	private WebElement confirm_incoming_transfers_modal_button;

	@FindBy(xpath = ".//button[text() = 'Cancel Transaction']")
	private WebElement btnCancelTransaction;

	@FindBy(xpath = ".//button[text() = 'Cancel transactions']")
	private WebElement btnCancelTransaction2;
	private By confirm_incoming_transfers_modal_button_1 = By.xpath(".//button[text() = 'Confirm Transaction']");
	
	@FindBy(xpath = "(.//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[2]")
	private WebElement click_container_dropdown_menu;
	private By click_container_dropdown_menu1 = By.xpath("(.//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[2]");

	@FindBy(xpath = "(//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[1]")
	private WebElement dropDownMenuFirstContainer;

	@FindBy(xpath = ".//a/span[text() = 'Transfer']")
	private WebElement select_Transfer_in_dropdown;
	private By select_Transfer_in_dropdown1 = By.xpath(".//a/span[text() = 'Transfer']");
	
	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses;
	private By get_remaining_doses_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//div[@class='test-id__section slds-section  slds-is-open full forcePageBlockSection forcePageBlockSectionView'][3]//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']//span[text()])[3]")
	private WebElement get_remaining_doses_cp;
	private By get_remaining_doses_cp_ = By.xpath("(.//div[@class='test-id__section slds-section  slds-is-open full forcePageBlockSection forcePageBlockSectionView'][3]//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']//span[text()])[3]");


	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container1_distribution_1_1;
	private By get_remaining_doses_container1_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container2_distribution_1_1;
	private By get_remaining_doses_container2_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container3_distribution_1_2;
	private By get_remaining_doses_container3_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container4_distribution_1_2;
	private By get_remaining_doses_container4_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_distribution_1_2;
	private By get_remaining_doses_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");
	
	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty;
	private By get_remaining_Qty_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//div[@class='test-id__section slds-section  slds-is-open full forcePageBlockSection forcePageBlockSectionView'][3]//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']//span[text()])[4]")
	private WebElement get_remaining_Qty_cp;
	private By get_remaining_Qty_cp_ = By.xpath("(.//div[@class='test-id__section slds-section  slds-is-open full forcePageBlockSection forcePageBlockSectionView'][3]//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']//span[text()])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container1_distribution_1_1;
	private By get_remaining_Qty_container1_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container2_distribution_1_;
	private By get_remaining_Qty_container2_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container3_distribution_1_2;
	private By get_remaining_Qty_container3_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container4_distribution_1_2;
	private By get_remaining_Qty_container4_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_1_2;
	private By get_remaining_Qty_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = ".//input[@name = 'HC_Product_Measure__c']")
	private WebElement get_dose_conversation_factor;
	private By get_dose_conversation_factor1 = By.xpath(".//input[@name = 'HC_Product_Measure__c']");
	
	@FindBy(xpath = ".//input[@name = 'BCH_Product_Name__c']")
	private WebElement get_trade_name;
	private By get_trade_name1 = By.xpath(".//input[@name = 'BCH_Product_Name__c']");

	@FindBy(xpath = ".//a/span[text() = 'Confirm']")
	private WebElement select_Confirm_in_dropdown;
	private By select_Confirm_in_dropdown1 = By.xpath(".//a/span[text() = 'Confirm']");

	@FindBy(xpath = ".//a/span[text() = 'Cancel Transfer']")
	private WebElement drdCancel;

	@FindBy(xpath = ".//*[text() = 'Related Items']")
	private WebElement click_on_related_item_tab;
	private By click_on_related_item_tab_1 = By.xpath(".//*[text() = 'Related Items']");

	@FindBy(xpath = "//span[@class='slds-truncate' and contains(text(),'Edit')]")
	private WebElement btnEditOnTrasactionPage;

	@FindBy(xpath = "//span[@class='slds-truncate' and contains(text(),'Cancel')]")
	private WebElement btnCancelTransfer;

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container1_lot_EK4241_distribution_1_1;
	private By get_remaining_doses_container1_lot_EK4241_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container1_lot_EK4241_distribution_2_1;
	private By get_remaining_doses_container1_lot_EK4241_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container1_lot_EK4241_distribution_2_1;
	private By get_remaining_Qty_container1_lot_EK4241_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container1_lot_EK4241_distribution_1_1;
	private By get_remaining_Qty_container1_lot_EK4241_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container1_lot_MT0055_distribution_1_1;
	private By get_remaining_doses_container1_lot_MT0055_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container2_lot_EK4241_distribution_1_1;
	private By get_remaining_doses_container2_lot_EK4241_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container2_lot_EK4241_distribution_1_1;
	private By get_remaining_Qty_container2_lot_EK4241_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container1_lot_MT0055_distribution_1_1;
	private By get_remaining_Qty_container1_lot_MT0055_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_1_1;
	private By get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_1_1;
	private By get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_1_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][3]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container1_lot_MT0055_distribution_2_1;
	private By get_remaining_doses_container1_lot_MT0055_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container1_lot_MT0055_distribution_2_1;
	private By get_remaining_Qty_container1_lot_MT0055_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container2_lot_EK4241_distribution_2_1;
	private By get_remaining_doses_container2_lot_EK4241_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container2_lot_EK4241_distribution_2_1;
	private By get_remaining_Qty_container2_lot_EK4241_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_2_1;
	private By get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_2_1;
	private By get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_2_1_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container4_lot_MT0055_distribution_1_2;
	private By get_remaining_doses_container4_lot_MT0055_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container4_lot_MT0055_distribution_1_2;
	private By get_remaining_Qty_container4_lot_MT0055_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][4]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container5_lot_EK4241_distribution_1_2;
	private By get_remaining_doses_container5_lot_EK4241_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container5_lot_EK4241_distribution_1_2;
	private By get_remaining_Qty_container5_lot_EK4241_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][5]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][7]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_container6_lot_SPIKEVAX6_5Test001_distribution_1_2;
	private By get_remaining_doses_container6_lot_SPIKEVAX6_5Test001_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][7]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");

	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][7]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_container6_lot_SPIKEVAX6_5Test001_distribution_1_2;
	private By get_remaining_Qty_container6_lot_SPIKEVAX6_5Test001_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][7]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");

	@FindBy(xpath = "//div[@class='slds-icon-waffle']")
	private WebElement select_app_launcher;
	private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");

	@FindBy(xpath = "//p[text()='Health Connect - Supply Console']")
	private WebElement click_healthconnect_app;
	private By click_healthconnect_app1 = By.xpath("//p[text()='Health Connect - Supply Console']");

	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	private WebElement dropdownMenu;
	private By dropdownMenu1 = By.xpath("//*[@class='slds-grid slds-grid--vertical-align-center slds-grid--align-center sldsButtonHeightFix']");

	@FindBy(xpath = ".//*[@title='Receive Supplies']")
	private WebElement receiveSupplies;

	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[6]/div/a/span[2]/span")
	private WebElement supplyItemsInDropdown;

	@FindBy(xpath = "//a[@title='COMIRNATY (Pfizer) - 35035BD-CC01']")
	private WebElement select_desired_supply_item;
	private By select_desired_supply_item1 = By.xpath("//a[@title='COMIRNATY (Pfizer) - 35035BD-CC01']");

	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']")
	private WebElement select_desired_supply_loc;

	@FindBy(xpath = "//a[contains(text(),'Supply Distribution_1 - SDST-000')]")
	private WebElement click_supply_distribution;
	private By click_supply_distribution1 = By.xpath("//a[contains(text(),'Supply Distribution_1 - SDST-000')]");

	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']")
	private WebElement get_supply_distribution_name;
	private By get_supply_distribution_name1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']");

	@FindBy(xpath = "(//DIV[@records-recordlayoutitem_recordlayoutitem=''])[19]/..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='Supply Distribution_1']")
	private WebElement get_supply_distribution_description;
	private By get_supply_distribution_description1 = By.xpath("(//DIV[@records-recordlayoutitem_recordlayoutitem=''])[19]/..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field'][text()='Supply Distribution_1']");

	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
	private WebElement supplyLocationInDropdown;

	@FindBy(xpath = "//BUTTON[@class='slds-button slds-button_icon-border-filled']")
	private WebElement select_drpdown_to_receive_supplies;
	private By select_drpdown_to_receive_supplies1 = By.xpath("//BUTTON[@class='slds-button slds-button_icon-border-filled']");

	@FindBy(xpath = "//span[text()='Receive Supplies']")
	private WebElement click_to_select_receive_supplies;
	private By click_to_select_receive_supplies1 = By.xpath("//span[text()='Receive Supplies']");

	@FindBy(xpath = "//label[@class='slds-form-element__label'][text()='Supply Item']")
	private WebElement validate_supply_item_field;
	private By validate_supply_item_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Item']");

	@FindBy(xpath = "//input[@placeholder='Search Supply Items...']")
	private WebElement click_to_select_supply_item;
	private By click_to_select_supply_item1 = By.xpath("//input[@placeholder='Search Supply Items...']");

	@FindBy(xpath = "//span[contains(text(),'COMIRNATY (Pfizer) - 35035BD-CC01')]")
	private WebElement choose_supply_item;
	private By choose_supply_item1 = By.xpath("//span[contains(text(),'COMIRNATY (Pfizer) - 35035BD-CC01')]");

	@FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex'][text()='Quantity']")
	private WebElement validate_qty_field;
	private By validate_qty_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Quantity']");

	@FindBy(xpath = "(//input[@class='slds-input'])[2]")
	private WebElement click_to_select_quantity;
	private By click_to_select_quantity1 = By.xpath("(//input[@class='slds-input'])[2]");

	@FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex'][text()='Dose Conversion Factor']")
	private WebElement validate_dcf_field;
	private By validate_dcf_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Dose Conversion Factor']");

	@FindBy(xpath = "//label[@class='slds-form-element__label slds-no-flex'][text()='Doses']")
	private WebElement validate_doses_field;
	private By validate_doses_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Doses']");

	@FindBy(xpath = "//label[@class='slds-form-element__label'][text()='Supply Distribution To']")
	private WebElement supply_distribution_to_field;
	private By supply_distribution_to_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Distribution To']");

	@FindBy(xpath = "//button[@aria-label='Reason for Reception, --None--']")
	private WebElement click_reason;
	private By click_reason1 = By.xpath("//button[@aria-label='Reason for Reception, --None--']");

	@FindBy(xpath = "//span[@title='Other']")
	private WebElement select_reason;
	private By select_reason1 = By.xpath("//span[@title='Other']");

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement save_button_receive_supplies;
	private By save_button_receive_supplies1 = By.xpath("//button[contains(text(),'Save')]");

	@FindBy(xpath = "(//span[contains(text(),'Cancel')])[2]")
	private WebElement cancel_button_receive_supplies;
	private By cancel_button_receive_supplies1 = By.xpath("(//span[contains(text(),'Cancel')])[2]");

	@FindBy(xpath = "//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose Conversion Factor']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']")
	private WebElement get_dose_conversion_factor;
	private By get_dose_conversion_factor2 = By.xpath("//label[contains(text(),'Dose Conversion Factor')]/parent::div//input");
	private By get_dose_conversion_factor1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose Conversion Factor']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

	@FindBy(xpath = "//button[@name='distributionBox']")
	private WebElement supply_distribution_to;
	private By supply_distribution_to1 = By.xpath("//button[@name='distributionBox']");

	@FindBy(xpath = "(//span[contains(text(),'Supply Distribution_1 - SDST-000')])[1]")
	private WebElement select_supply_distributor;
	private By select_supply_distributor1 = By.xpath("(//span[contains(text(),'Supply Distribution_1 - SDST-000')])[1]");

	@FindBy(xpath = "//a[contains(text(),'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)')]")
	private WebElement select_desired_supply_container;
	private By select_desired_supply_container1 = By.xpath("//a[contains(text(),'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)')]");


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Wastage Tab //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@FindBy(xpath = "//a/span[text()='Wastage']")
	private WebElement selectWastageFromDropDown;
	
	@FindBy(xpath = "//a/span[text()='Adjustment']")
	private WebElement selectAdjustmentFromDropDown;
	
	@FindBy(xpath = "//input[@name='HC_Remaining_Measures__c']")
	private WebElement actualRemainingDoses;
	
	@FindBy(xpath = "//label[(text()='Dose Conversion Factor')]/..//input[@type='text']")
	private WebElement doseConversionFactorForSingleWastage;
	
	@FindBy(xpath = "//label[(text()='Doses')]/..//input[@type='text']")
	private WebElement dosesText;

	@FindBy(xpath = "//label[(text()='Quantity')]/..//input[@type='text']")
	private WebElement quantityText;

	@FindBy(xpath = "//button[@name='BCH_Reason_for_Adjustment__c']")
	private WebElement reasonForAdjustmentFromDropDown;
	
	@FindBy(xpath = "//button[@name='BCH_Reason_for_Wastage__c']")
	private WebElement reasonForWastageValueFromDropDown;
	
	@FindBy(xpath = "//span[@title='CCI: Handling Error']")
	private WebElement dropDownValueCCIHandlingError;
	
	@FindBy(xpath = "//h2[text()='Container - Wastage']/../..//button[(text()='Wastage')]")
	private WebElement btnWastageOnContainerWastagePopUp;
	
	@FindBy(xpath = "//h2[text()='Container - Adjustment']/../..//button[(text()='Adjustment')]")
	private WebElement btnAdjustmentOnContainerWastagePopUp;
	
	@FindBy(xpath = "//button[contains(text(),'draft')]")
	private WebElement btnSaveAsDraftOnContainerWastagePopUp;

	@FindBy(xpath = "//button[text() = 'Wastage']")
	private WebElement btnBulkWastageSupplyPage;
	
	@FindBy(xpath = "//button[text() = 'Adjustment']")
	private WebElement btnBulkAdjustmentSupplyPage;
	
	@FindBy(xpath = "//h2[text() = 'Container - Wastage']/../..//button[text() = 'Wastage']")
	private WebElement btnBulkWastageContainerWastagePage;
	
	@FindBy(xpath = "//h2[text() = 'Container - Adjustment']/../..//button[text() = 'Adjustment']")
	private WebElement btnBulkAdjustmentContainerAdjustmentPage;

	///////////////////////////////////////////////////////////////////////////////
	//Requisition elements
	///////////////////////////////////////////////////////////////////////////////
	@FindBy(xpath = "//input[@placeholder=\"Search Supply Locations...\"]")
	private WebElement search_supply_location_from;
	private By search_supply_location_from_ = By.xpath("//input[@placeholder=\"Search Supply Locations...\"]");

	@FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
	private WebElement select_supply_location_from;
	private By select_supply_location_from_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");

	@FindBy(xpath = "//button[@title='Edit Expected Delivery Date']")
	private WebElement editExpectedDeliveryDate;

	@FindBy(xpath = "//*[text()='Expected Delivery Date']/../..//input")
	private WebElement inputExpectedDate;

	@FindBy(xpath = "//button[text() = 'Save'] | //button[@title = 'Save']")
	private WebElement saveExpectedDeliveryDate;

	@FindBy(xpath = "//button[text() = 'Approve Requisition'] | //a[@title = 'Approve Requisition']")
	private WebElement approveRequisition;

	@FindBy(xpath = "//button[text() = 'Save']")
	private WebElement saveApprovedRequisition;
	private By saveApprovedRequisition_ = By.xpath("//button[text() = 'Save']");

	@FindBy(xpath = "//label[text() = 'Approved Doses']/..//input")
	private WebElement approvedDose;

	@FindBy(xpath = "//button[text() = 'Ship Requisition'] | //a[@title = 'Ship Requisition']")
	private WebElement shipRequisition;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveShipRequisition;

	@FindBy(xpath = "//h2[contains(text(),'Ship Requisition')]")
	private WebElement verified;

	@FindBy(xpath = "//button[text() = 'Receive Requisition'] | //a[@title = 'Receive Requisition']")
	private WebElement receiveRequestBtn;

	@FindBy(xpath = "//input[@placeholder='Search Supply Distributions...']")
	private WebElement click_on_search_supply_distributions_to_component;

	@FindBy(xpath = "(//div[@class='primaryLabel slds-truncate slds-lookup__result-text'])[1]")
	private WebElement select_supply_distributions_to;

	@FindBy(xpath = "//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	private WebElement saveReceiveRequisition;

	@FindBy(xpath = "//label[text()='Approver Comment']/..//input")
	private WebElement approverComment;
	//////////////////////////////////////////////////////////////////////////////////
	Tables tables;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Community Portal Methods //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void enterBulkByDosageWithReasonCP(double amount, String reason, int numberOfRows) throws InterruptedException {
		//By dosage
		int y = 0;
		int k = 3;
		while (y < numberOfRows) {
			WebElement dosesDynamicFiled = driver.findElement(By.xpath("(//input[@class = 'slds-input'])[" + k + "]"));
			typeIn(dosesDynamicFiled, String.valueOf(amount));
			WebElement reasonForWastageDynamicDropDown = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux']"));
			click(reasonForWastageDynamicDropDown);
			Thread.sleep(500);
			WebElement reasonForWastageDynamicFiled = driver.findElement(By.xpath("(//span[@title= '"+ reason +"'])[" + (y + 1) + "]"));
			click(reasonForWastageDynamicFiled);
			k = k + 3;
			y++;
		}
	}

	public void enterBulkByQuantitiesWithReasonCP(double amount,String reason, int numberOfRows) throws InterruptedException {
		//By Quantities
		int y = 0;
		int k = 2;
		while (y < numberOfRows) {
			WebElement dosesDynamicFiled = driver.findElement(By.xpath("(//input[@class = 'slds-input'])[" + k + "]"));
			typeIn(dosesDynamicFiled, Double.toString(amount));
			WebElement reasonDynamicDropDown = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux']"));
			clickUsingJS(reasonDynamicDropDown);
			Thread.sleep(500);
			WebElement selectReasonFromDynamicDropDown = driver.findElement(By.xpath("(//span[@title= '"+ reason +"'])[" + (y + 1) + "]"));
			clickUsingJS(selectReasonFromDynamicDropDown);
			k = k + 3;
			y++;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*---------Constructor-------*/
	public SupplyConsolePage(WebDriver driver) {

		super(driver);
		tables = new Tables(driver);
	}
	
	/*-------------Methods--------------*/
	public void inputRequestedQuantity(String inputQuantity) throws InterruptedException {
		Thread.sleep(1000);
		WebElement dosesInput = driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]//input"));
		dosesInput.clear();
		dosesInput.sendKeys(inputQuantity);
	}
	
	public void clickSaveButton() {
		moveToElement(saveButton);
		saveButton.click();
	}
	
	public void inputShipAddress(String inputAddress) {
		shipAddress.sendKeys(inputAddress);
	}
	
	public void clickInput() {
		WebElement products = driver.findElement(By.xpath(" //span[contains(text(),\"Atlin Health Centre\")]"));
		products.click();
	}
	
	public void clickInput1() {
		WebElement products = driver.findElement(By.xpath("//a[contains(text(),\"Atlin Health Centre\")]"));
		products.click();
	}
	
	public void clickCheckBox() {
		checkBox.click();
	}
	
	public void inputRequestDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		String tomorrowAsString = dateFormat.format(tomorrow);
		this.inputDate.sendKeys(tomorrowAsString, Keys.ENTER);
	}
	
	public void clickNextButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", nextButton);
		waitForElementToBeVisible(driver, nextButton, 10);
		this.nextButton.click();
	}
	
	public void clickSubmitRequisition() throws InterruptedException {
		waitForElementToBeVisible(driver, submitRequisition, 10);
		submitRequisition.click();
	}

//    public void clickSaveSubmitRequisition() {
//        waitForElementToBeVisible(driver, saveSubmitRequisition, 10);
//        WebElement element = driver.findElement(save_Submit_Requisition);
//        this.saveSubmitRequisition.click();
//    }
//    public void clickExpectedDeliveryDateCalendar() {
//        waitForElementToBeVisible(driver, expectedDeliveryDateCalendar, 10);
//        WebElement element = driver.findElement(expected_delivery_date_calendar);
//        this.expectedDeliveryDateCalendar.click();
//    }
	
	
	public void clickSaveSubmitRequisition() {
		waitForElementToBeVisible(driver, saveSubmitRequisition, 10);
		WebElement element = driver.findElement(save_Submit_Requisition);
		this.saveSubmitRequisition.click();
	}
	
	public void clickExpectedDeliveryDateCalendar() {
		waitForElementToBeVisible(driver, expectedDeliveryDateCalendar, 10);
		WebElement element = driver.findElement(expected_delivery_date_calendar);
		this.expectedDeliveryDateCalendar.click();
	}
	
	public void SupplyLocations() throws InterruptedException {
		waitForElementToBeLocated(driver, By.xpath("//span[contains(text(),\"Supply Locations\")]"), 10);
		WebElement supplyLocationPath = driver.findElement(By.xpath("//span[contains(text(),\"Supply Locations\")]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", supplyLocationPath);
	}
	
	public void clickSupplyLocationsTab() throws InterruptedException {
		waitForElementToBeVisible(driver, supply_locations_tab, 10);
		WebElement element = driver.findElement(supply_locations_tab1);
		this.supply_locations_tab.click();
		boolean loaded = false;
		while(!loaded) {
			try {
				loaded = tables.getSupplyLocationTable().getHeadings().get(0).isDisplayed();
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
				Thread.sleep(1000);
			}
		}
		System.out.println("Here");
	}

	public void clickOnSupplyLocationCustom(String locationName) throws InterruptedException {
		WebElement customLocation = driver.findElement(By.xpath("(//a[contains(text(),'" + locationName + "')])[1]"));
		click(customLocation);
		Thread.sleep(5000);
	}

	public void clickOnSupplyLocation_1() throws InterruptedException {
		//waitForElementToBeLocated(driver, supply_supply_location_1_, 10);
		waitForElementToBeVisible(driver, supply_supply_location_1, 10);
		WebElement element = driver.findElement(supply_supply_location_1_);
		this.supply_supply_location_1.click();
	}
	
	public void clickOnSupplyLocation_2() throws InterruptedException {
		//waitForElementToBeLocated(driver, supply_supply_location_1_, 10);
		waitForElementToBeVisible(driver, supply_supply_location_2, 10);
		WebElement element = driver.findElement(supply_supply_location_2_);
		this.supply_supply_location_2.click();
	}

	public void clickOnSupplyLocation(String supply_location_name) throws InterruptedException {
		Map<String,String> supplyLocation = ImmutableMap.of(SUPPLY_LOCATION_NAME, supply_location_name);
		tables.getSupplyLocationRow(supplyLocation).get(SUPPLY_LOCATION_NAME).findElement(By.tagName("a")).click();
	}

	public void clickRequestSupplies() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> request_supplies_btn = null;
		int num = 0;
		while(request_supplies_btn == null || num == 0) {
			request_supplies_btn = driver.findElements(By.xpath("//button[text() = 'Request Supplies'] | //a[@title = 'Request Supplies']"));
			num = request_supplies_btn.size();
			Thread.sleep(500);
		}
		System.out.println(request_supplies_btn.size());
		Thread.sleep(1000);
		for(WebElement element : request_supplies_btn) {
			if(element.isDisplayed()) {
				element.click();
				break;
			}
		}
		Thread.sleep(1000);
	}
	
	public void clickShipToAddress() throws InterruptedException {
		waitForElementToBeLocated(driver, shipTo_address_, 10);
		WebElement element = driver.findElement(shipTo_address_);
		this.shipTo_address.click();
	}
	
	public void clickRequestedDeliveryDate() throws InterruptedException {
		waitForElementToBeLocated(driver, requested_delivery_date, 10);
		WebElement element = driver.findElement(requested_delivery_date);
		this.requestedDeliveryDate.click();
	}
	
//	public void clickOnSupplyContainerCheckbox(int k) throws InterruptedException {
//		//By container_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='c_hcCrossObjectRelationRecordsList']//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
//		By container_checkbox_1_ = By.xpath("(//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
//		waitForElementToBeLocated(driver, container_checkbox_1_, 10);
//		Thread.sleep(2000);
//		WebElement element = driver.findElement(container_checkbox_1_);
//		click(container_checkbox_1_);
//	}

	public void clickOnSupplyContainerCheckbox(int k) throws InterruptedException {
		tables.getSupplyContainerTable().getRowsMappedToHeadings().get(k).get("Select All").click();
	}

	public void clickOnSupplyContainerCheckbox(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		tables.getSupplyContainerRow(supplyContainer).get("Select All").click();
	}

	public int getRowsSupplyContainersFromCount() throws InterruptedException {
		//waitForElementToBeClickable(driver, container_checkbox_1, 10);
		//waitForElementToBeLocated(driver, container_checkbox_1_, 10);
		List<WebElement> rows = driver.findElements(rows_supply_containers_from_count_path_1);
		//rows.size();
		return (rows.size());
	}
	
	public void clickBulkTransfersButton() {
		waitForElementToBeLocated(driver, bulk_transfers_button_1, 10);
		this.bulk_transfers_button.click();
	}
	public SupplyConsolePage clickBulkCancelButton() throws InterruptedException {
		waitForElementToBePresent(driver, bulk_transfers_button_1, 10);
		moveToElement(bulk_cancel_button);
		click(bulk_cancel_button);
		return this;
	}
	public void verifyIsSupplyPageDisplayed() {
		waitForElementToBeVisible(driver, supply_page_displayed, 10);
		this.supply_page_displayed.isDisplayed();
	}

	public boolean displaySupplyConsolePage() {
		return isDisplayed(supply_page_displayed);
	}
	
	public void enterBulkTransferByDosages(int k) throws InterruptedException {
		//private By doses_1 = By.xpath("(.//input[@class = 'slds-input'])[2]");
		By dose_1_ = By.xpath("(.//input[@class = 'slds-input'])[" + k + "]");
		waitForElementToBeLocated(driver, dose_1_, 10);
		WebElement element = driver.findElement(dose_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollRight = arguments[0].scrollWidth", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		click(dose_1_);
		element.sendKeys("1");
	}

	public void enterBulkTransferByDosages(List<String> containers, int dose) {
		for (String container : containers) {
			Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container);
			tables.typeDosesIntoTransferRow(supplyContainer, Integer.toString(dose));
		}
	}

	public void enterBulkTransferByQuantity(int k) throws InterruptedException {
		By qty_1_ = By.xpath("(.//input[@class = 'slds-input'])[" + k + "]");
		waitForElementToBeLocated(driver, qty_1_, 10);
		WebElement element = driver.findElement(qty_1_);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		click(qty_1_);
		element.sendKeys("1");
	}

	public void enterBulkTransferByQuantity(List<String> containers, int quantity) {
		for (String container : containers) {
			Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container);
			tables.typeQtyIntoTransferRow(supplyContainer, Integer.toString(quantity));
		}
	}
	@Step
	public SupplyConsolePage selectSupplyLocation_2_To() throws InterruptedException {
		log(" -- select 'To' Automation Supply Location_2  -");
		waitForElementToBeVisible(driver, search_supply_location_2_To, 60);
		search_supply_location_2_To.sendKeys(SUPPLY_LOCATION_2);
		waitForElementToBeVisible(driver, select_supply_location_2_To, 120);
		select_supply_location_2_To.click();
		Thread.sleep(2000);
		return this;
	}
	
	public void selectSupplyLocation_1_To() throws InterruptedException {
		waitForElementToBeVisible(driver, search_supply_location_1_To, 10);
		search_supply_location_1_To.sendKeys(SUPPLY_LOCATION_1);
		Thread.sleep(5000);
		waitForElementToBeVisible(driver, select_supply_location_1_To, 10);
		Thread.sleep(5000);
		select_supply_location_1_To.click();
		Thread.sleep(2000);
	}
	@Step
	public SupplyConsolePage clickBulkTransfersModalButton(){
		waitForElementToBeLocated(driver, bulk_transfers_dialog_button_1, 10);
		moveToElement(driver.findElement(bulk_transfers_dialog_button_1));
		click(bulk_transfers_dialog_button_1);
		return this;
	}
	@Step
	public void clickBulkTransfersCloseButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_dialog_close_button_1, 10);
		Thread.sleep(1000);
		click(bulk_dialog_close_button_1);
		waitForElementNotToBeVisible(driver, bulk_dialog_close_button_1, 20);
	}

	@Step
	public void clickTransactionsTab() throws InterruptedException {
		//moveToElement(transactions_tab);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)");
		Thread.sleep(3000);
		waitForElementToBeVisible(driver, transactions_tab, 10);
		Thread.sleep(2000);
		transactions_tab.click();
	}
	
	public int getRowsOutgoingTransactionsCount() throws InterruptedException {
		waitForElementToBeVisible(driver, rows_outgoing_transactions_count_path, 10);
		List<WebElement> rows = rows_outgoing_transactions_count_path.findElements(By.tagName("tr"));
		//List<WebElement> rows = driver.findElements(rows_transactions_from_count_path_1);
		//int dataTableActualRowCount=0;
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//dataTableActualRowCount =  ((Number)js.executeScript("return $('#example').DataTable().rows().data().toArray().length;")).intValue();
		//System.out.println("coubt=" +dataTableActualRowCount);
		//return(dataTableActualRowCount);
		return (rows.size());
	}

	public String getOutgoingSupplyTransactionId(int kk) throws InterruptedException {
		String supplyTransactionId = tables.getSingleTransactionsTable("Outgoing").getRowsAsStringMappedToHeadings().get(kk).get(SUPPLY_TRANSACTION_NAME);
		return (supplyTransactionId);
	}

	public void clickOnOutgoingTransactions(int kk) throws InterruptedException {
		tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(kk).get(SUPPLY_TRANSACTION_NAME).click();
	}
	
	public void clickSupplyTransactionRelatedTab() throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = driver.findElement(supply_transactions_Related_tab_1);
		isDisplayed(supply_transactions_Related_tab_1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		//element.click();
		//waitForElementToBeLocated(driver, supply_transactions_Related_tab_1, 10);
		//Thread.sleep(2000);
		//click(transactions_tab_1);
		//waitForElementToBeVisible(driver, supply_transactions_Related_tab, 10);
		//Thread.sleep(2000);
		//driver.findElement(supply_transactions_Related_tab_1);
		//Thread.sleep(2000);
		//click(supply_transactions_Related_tab_1);
	}
	
	public void clickOnSupplyShipmentName() throws InterruptedException {
		waitForElementToBeLocated(driver, supply_shipment_name_1, 10);
		click(supply_shipment_name_1);
	}
	
	public String getSupplyShipmentTransactionId() throws InterruptedException {
		waitForElementToBeLocated(driver, supply_shipment_name_1, 10);
		WebElement element = driver.findElement(supply_shipment_name_1);
		element.getText();
		return (element.getText());
	}
	
	public void closeTabsHCA() throws InterruptedException {
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
//		do {
//			try {
//				WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
//				Thread.sleep(2000);
//				closetab.click();
//				Thread.sleep(2000);
//			} catch (NoSuchElementException e) {
//				System.out.println("/*---there are no Tab's to close anymore");
//			}
//		} while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
	}
	
	public int getRowsIncomingTransactionsCount() throws InterruptedException {
		waitForElementToBeVisible(driver, rows_incoming_transactions_count_path, 10);
		List<WebElement> rows = rows_incoming_transactions_count_path.findElements(By.tagName("tr"));
		return (rows.size());
	}
	
	public int getRowsDraftTransactionsCount() {
		scrollTop(rows_draft_transactions_count_path);
		List<WebElement> rows = driver.findElements(By.xpath("//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux']"));
		return (rows.size());
	}

	public String getLatestDraftTransactionId(int value) {
		//Offset due to 0 is not a real value
		int offset = value-1;
		WebElement draftTransactionElement = driver.findElement(By.xpath("(//a[@title='draftTransactionFromName'])[" + offset + "]"));
		String draftTransactionId = draftTransactionElement.getText();
		return draftTransactionId;
	}

	public void clickCheckBoxLatestDraftTransactionsAndConfirmTransfer(int value) throws InterruptedException {
		WebElement draftTransactionElement = driver.findElement(By.xpath("(//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux'])[" + value + "]"));
		click(draftTransactionElement);
		click(btnTransferDraftOnTransactionsPage);
		click(btnTransferTransactionsDraftOnTransactionsPage);
	}

	public void clickCheckBoxLatestDraftBulkTransactionsAndConfirmTransfer(int countDraftTransactions, int numberOfRows) throws InterruptedException {

		for(int i=countDraftTransactions; i > (countDraftTransactions-numberOfRows); i--) {
			WebElement draftTransactionElement = driver.findElement(By.xpath("(//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux'])[" + i + "]"));
			click(draftTransactionElement);
		}
		scrollTop(btnTransferDraftOnTransactionsPage);
		click(btnTransferDraftOnTransactionsPage);
		click(btnTransferTransactionsDraftOnTransactionsPage);
	}

	public void clickDropDownLatestDraftTransactionsAndConfirmTransfer(int countDraftTransactions, double amountOfDosesToAdjustInDraftEdit) throws InterruptedException {
		//Offset due to 0 is not a real value
		int offset = countDraftTransactions-1;
		WebElement draftTransactionElement = driver.findElement
				(By.xpath("(//span[contains(text(),'Draft')]/../../../../..//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[" + offset + "]"));
		click(draftTransactionElement);
		click(btnEditOnTrasactionPage);
		Thread.sleep(2000);
		setDosesAmount(String.valueOf(amountOfDosesToAdjustInDraftEdit));
		clickUsingJS(btnTransferDraftOnContainerTransferPage);
		//click(btnTransferDraftOnContainerTransferPage);
		Thread.sleep(2000);
		clickBulkTransfersCloseButton();
		Thread.sleep(2000);
	}

	public void clickDropDownLatestDraftTransactionsAndCancelTransfer(int countDraftTransactions) throws InterruptedException {
		//Offset due to 0 is not a real value
		int offset = countDraftTransactions-1;
		WebElement draftTransactionElement = driver.findElement
				(By.xpath("(//span[contains(text(),'Draft')]/../../../../..//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[" + offset + "]"));
		click(draftTransactionElement);
		click(btnCancelTransfer);
		Thread.sleep(2000);
	}

	public void clickOnIncomingTransactionsCheckbox(int k) throws InterruptedException {
		tables.getSingleTransactionsTable("Incoming").getRowsMappedToHeadings().get(k).get("Select All").click();
	}

	public void clickOnOutgoingTransactionsCheckbox(int k) throws InterruptedException {
		tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(k).get("Select All").click();
	}

	public void clickBulkConfirmIncomingTransfersButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_confirm_incoming_transfers_button_1, 10);
		moveToElement(driver.findElement(bulk_confirm_incoming_transfers_button_1));
		click(bulk_confirm_incoming_transfers_button_1);
	}
	
	public void selectIncomingSupplyDistribution() throws InterruptedException {
		waitForElementToBeVisible(driver, search_incoming_supply_distributor, 10);
		WebElement element = driver.findElement(search_incoming_supply_distributor_);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		Thread.sleep(1000);
		search_incoming_supply_distributor.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_incoming_supply_distributor, 10);
		Thread.sleep(2000);
		select_incoming_supply_distributor.click();
		//#search_supply_location_To.sendKeys(Keys.ARROW_DOWN);
		//#search_supply_location_To.sendKeys(Keys.ENTER);
	}
	
	@Step
	public void clickOnConfirmModalIncomingTransactionButton() throws InterruptedException {
		waitForElementToBeLocated(driver, confirm_incoming_transfers_modal_button_1, 10);
		moveToElement(confirm_incoming_transfers_modal_button);
		//handle issue when popup not fully loaded and button is partially hidden
		if (isElementPresent(labelComments)) {
			click(labelComments);
		}
		click(confirm_incoming_transfers_modal_button_1);
	}

	public void clickOnButtonInModalTransaction(WebElement element) throws InterruptedException {
		waitForElementToBeVisible(driver, element, 10);
		scrollTop(element);
		click(element);
	}
	@Step
	public void successMessageAppear() {
		By successMessage = By.xpath("//*[contains(text(), 'Success!')]");
		waitForElementToBePresent(driver, successMessage, 20);
		assertTrue(isElementPresent(driver.findElement(successMessage)));
		log(" -- Toast success message appears");
		waitForElementNotToBePresent(driver, successMessage, 20);
	}
	
	public void clickOnContainerDropDownMenu() throws InterruptedException {
		waitForElementToBeLocated(driver, click_container_dropdown_menu1, 10);
		WebElement element = driver.findElement(click_container_dropdown_menu1);
		Thread.sleep(2000);
		click_container_dropdown_menu.click();
	}

	public void clickOnContainerDropDownMenu(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		tables.getSupplyLocationActions(supplyContainer);
	}

	public void clickOnFirstContainerDropDownMenu() throws InterruptedException {
		click(dropDownMenuFirstContainer);
	}
	@Step
	public void selectTransferFromDropDown() throws InterruptedException {
		waitForElementToBeLocated(driver, select_Transfer_in_dropdown1, 10);
		Thread.sleep(2000);
		select_Transfer_in_dropdown.click();
	}

	public Double getValueOfRemainingDoses() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_, 10);
		WebElement element = driver.findElement(get_remaining_doses_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses_CP() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_cp_, 10);
		WebElement element = driver.findElement(get_remaining_doses_cp_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_1_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container1_lot_EK4241_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container1_lot_EK4241_distribution_1_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}
	@Step
	public Double getValueOfRemainingDoses_Container1_Distribution_1_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container1_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container1_distribution_1_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses_Container2_Distribution_1_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container2_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container2_distribution_1_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses_Container3_Distribution_1_2() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container3_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container3_distribution_1_2_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses_Container4_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_doses_container4_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container4_distribution_1_2_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		double doses = tables.getRemainingDoses(supplyContainer);
		return (doses);
	}

	public Double getValueOfRemainingQty(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		double quontity = tables.getRemainingQty(supplyContainer);
		return (quontity);
	}

	public Double getValueOfRemainingQty() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingQty_CP() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_cp_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_cp_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}
	
	public Double getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_1_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container1_lot_EK4241_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container1_lot_EK4241_distribution_1_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingQty_Container1_Distribution_1_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container1_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container1_distribution_1_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingQty_Container2_Distribution_1_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container2_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container2_distribution_1_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingQty_Container3_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container3_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container3_distribution_1_2_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingQty_Container4_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container4_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container4_distribution_1_2_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	@Step
	public SupplyConsolePage enterTransferDosages(String doses) throws InterruptedException {
		By Doses = By.xpath("//lightning-input//label[text()='Doses']//following-sibling::div/input[@class='slds-input']");
		//waitForElementToBeLocated(driver, Doses, 10);
		//Thread.sleep(2000);
		//WebElement element = driver.findElement(Doses);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		//Thread.sleep(2000);
		//Thread.sleep(2000);
		//element.clear();
		//Thread.sleep(2000);
		//element.sendKeys(doses);
		waitForElementToBePresent(driver, Doses,10);
		moveToElement(driver.findElement(Doses));
		click(Doses);
		type(doses, Doses);
		return this;

	}

	public SupplyConsolePage enterTransferQuantity(String quantity) throws InterruptedException {
		By Quantity = By.xpath("//lightning-input//label[text()='Quantity']//following-sibling::div/input[@class='slds-input']");
		waitForElementToBePresent(driver, Quantity,10);
		//waitForElementToBeLocated(driver, Quantity, 10);
		//Thread.sleep(2000);
		//WebElement element = driver.findElement(Quantity);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		//Thread.sleep(2000);
		moveToElement(driver.findElement(Quantity));
		click(Quantity);
		//Thread.sleep(2000);
		//element.clear();
		//Thread.sleep(2000);
		//element.sendKeys(quantity);
		type(quantity, Quantity);
		return this;
	}
	
	public void selectAdjustmentFromDropDown() throws InterruptedException {
		click(selectAdjustmentFromDropDown);
		Thread.sleep(2000);
	}
	
	public void selectWastageFromDropDown() throws InterruptedException {
		click(selectWastageFromDropDown);
		Thread.sleep(2000);
	}
	
	public double getActualRemainingDoses() {
		String value = getValue(actualRemainingDoses);
		Double actualDosage = Double.parseDouble(value.replaceAll(",", ""));
		return actualDosage;
	}
	
	public void setDosesAmount(String value) {
		typeIn(dosesText, value);
	}

	public void setQuantityAmount(String quantity) {
		typeIn(quantityText, quantity);
	}
	
	public double getDoseConversionFactor() {
		double doseConversionFactor = Double.parseDouble(getValue(doseConversionFactorForSingleWastage));
		return doseConversionFactor;
	}
	
	public void selectReasonForAdjustmentDropDown() throws InterruptedException {
		clickUsingJS(reasonForAdjustmentFromDropDown);
		//click(reasonForAdjustmentFromDropDown);
		Thread.sleep(500);
		reasonForAdjustmentFromDropDown.sendKeys("a"); //Administered Vaccine
		reasonForAdjustmentFromDropDown.sendKeys(Keys.ENTER);
		String getSelectedReasonFromDropDown = reasonForAdjustmentFromDropDown.getText();
		log("Reason for adjustment is selected: " +getSelectedReasonFromDropDown);
	}
	
	public void selectReasonForWastageDropDown() throws InterruptedException {
		clickUsingJS(reasonForWastageValueFromDropDown);
		Thread.sleep(500);
		reasonForWastageValueFromDropDown.sendKeys("c"); //CCI: Equipment Malfunction
		reasonForWastageValueFromDropDown.sendKeys(Keys.ENTER);
		String getSelectedReasonFromDropDown = reasonForWastageValueFromDropDown.getText();
		log("Reason for wastage is selected: " +getSelectedReasonFromDropDown);

//		click(reasonForWastageValueFromDropDown);
//		reasonForWastageValueFromDropDown.sendKeys("c"); //CCI: Equipment Malfunction
//		reasonForWastageValueFromDropDown.sendKeys(Keys.ENTER);
//		click(dropDownValueCCIHandlingError); // Working on local but have difficulty clicking on Jenkins
	}
	
	public void clickBtnWastageAtContainerWastagePopUp() throws InterruptedException {
		scrollTop(btnWastageOnContainerWastagePopUp);
		click(btnWastageOnContainerWastagePopUp);
		Thread.sleep(3000); //To handle success message
		//Need to add validation for successful mess
	}
	
	public void clickBtnAdjustmentAtContainerAdjustmentPopUp() throws InterruptedException {
		scrollTop(btnAdjustmentOnContainerWastagePopUp);
		click(btnAdjustmentOnContainerWastagePopUp);
		Thread.sleep(3000); //To handle success message
	}
	
	public void clickBtnSaveAsDraftAtContainerAdjustmentPopUp() throws InterruptedException {
		scrollTop(btnSaveAsDraftOnContainerWastagePopUp);
		click(btnSaveAsDraftOnContainerWastagePopUp);
		Thread.sleep(2000); //To handle success message
	}

	public void clickBulkWastageButton() throws InterruptedException {
		click(btnBulkWastageSupplyPage);
	}
	
	public void clickBulkAdjustmentButton() throws InterruptedException {
		click(btnBulkAdjustmentSupplyPage);
	}
	
	public void clickWastageButtonContainerWastagePage() throws InterruptedException {
		click(btnBulkWastageContainerWastagePage);
	}
	
	public void clickAdjustmentButtonContainerAdjustmentPage() throws InterruptedException {
		click(btnBulkAdjustmentContainerAdjustmentPage);
	}
	
	public HashMap countDosesAndQuantityMap(int numberOfRows) {
		DecimalFormat df = new DecimalFormat("0.00");
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityMap = new HashMap<>();
		int d = 3;
		int q = 4;
		for (int i = 0; i < numberOfRows; i++) {
			ArrayList<Double> value = new ArrayList<>();
			WebElement remainingDosesWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + d + "]"));
			WebElement remainingQuantityWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + q + "]"));
			Double remainingDoses = Double.parseDouble(getValue(remainingDosesWebElement));
			Double remainingQuantity = Double.parseDouble(getValue(remainingQuantityWebElement));
			Double doseConversionFactor = Double.valueOf(df.format(remainingDoses / remainingQuantity));
			value.add(remainingDoses);
			value.add(remainingQuantity);
			value.add(doseConversionFactor);
			remainingDosesAndQuantityMap.put(i, value);
			log("Row number " + i + " / Remaining Doses = " + remainingDoses + " / Remaining Quantity = " + remainingQuantity
					+ " / Dose Conversion Factor = " + doseConversionFactor);
			d = d + 4;
			q = q + 4;
		}
		return remainingDosesAndQuantityMap;
	}

	public boolean validateLotUserDefaults(String lot) {
		boolean flag = false;
		List countOfFoundLot = driver.findElements(By.xpath("//lightning-base-formatted-text/..//*[contains(text(),'"+ lot +"')]"));
			if(countOfFoundLot.size()>0){
				flag = true;
			}
		return flag;
	}

	public void enterBulkByDosageWithReason(double amount, String reason, int numberOfRows) throws InterruptedException {
		//By dosage
		int y = 0;
		int k = 4;
		while (y < numberOfRows) {
			WebElement dosesDynamicFiled = driver.findElement(By.xpath("(//input[@class = 'slds-input'])[" + k + "]"));
			typeIn(dosesDynamicFiled, String.valueOf(amount));
			WebElement reasonForWastageDynamicDropDown = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux']"));
			click(reasonForWastageDynamicDropDown);
			Thread.sleep(500);
			WebElement reasonForWastageDynamicFiled = driver.findElement(By.xpath("(//span[@title= '"+ reason +"'])[" + (y + 1) + "]"));
			click(reasonForWastageDynamicFiled);
			k = k + 3;
			y++;
		}
	}

	public void enterBulkByQuantitiesWithReason(double amount,String reason, int numberOfRows) throws InterruptedException {
		//By Quantities
		int y = 0;
		int k = 3;
		while (y < numberOfRows) {
			WebElement dosesDynamicFiled = driver.findElement(By.xpath("(//input[@class = 'slds-input'])[" + k + "]"));
			typeIn(dosesDynamicFiled, Double.toString(amount));
			WebElement reasonDynamicDropDown = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux']"));
			clickUsingJS(reasonDynamicDropDown);
			Thread.sleep(500);
			WebElement selectReasonFromDynamicDropDown = driver.findElement(By.xpath("(//span[@title= '"+ reason +"'])[" + (y + 1) + "]"));
			clickUsingJS(selectReasonFromDynamicDropDown);
			k = k + 3;
			y++;
		}
	}

	@Step
	public String getVaccineName() throws InterruptedException {
		log("/*9.----Picked up the Trade Vaccine Name --*/");
		waitForElementToBeLocated(driver, get_trade_name1, 10);
		WebElement element = driver.findElement(get_trade_name1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String tradeName = getValue(get_trade_name);
		return (tradeName);
	}
	@Step
	public double getDoseConversationFactor() throws InterruptedException {

		waitForElementToBeLocated(driver, get_dose_conversation_factor1, 10);
		WebElement element = driver.findElement(get_dose_conversation_factor1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String conversationFactor = getValue(get_dose_conversation_factor);
		double Factor = Double.parseDouble(conversationFactor.replaceAll(",", ""));
		log(" -- the Dose Conversation Factor is: " + Factor);
		return (Factor);
	}

	public void clickOnIncomingTransactionsDropDownMenu(int j) throws InterruptedException {
		tables.getSingleTransactionsTable("Incoming").getRowsMappedToHeadings().get(j).get("Actions").click();
	}

	public void clickOnOutgoingTransactionsDropDownMenu(int j) throws InterruptedException {
		tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(j).get("Actions").click();
	}

	@Step
	public void selectConfirmIncomingDropDown() throws InterruptedException {
		waitForElementToBeLocated(driver, select_Confirm_in_dropdown1, 10);
		Thread.sleep(2000);
		select_Confirm_in_dropdown.click();
	}
	@Step
	public void selectCancelInDropDown() throws InterruptedException {
		waitForElementToBeVisible(driver, drdCancel, 10);
		//Thread.sleep(2000);
		moveToElement(drdCancel);
		click(drdCancel);
		//drdCancel.click();
	}
	
	public void clickOnRelatedItemTab() throws InterruptedException {
		//scroll up
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, click_on_related_item_tab_1, 10);
		Thread.sleep(1000);
		WebElement element = driver.findElement(click_on_related_item_tab_1);
		Thread.sleep(2000);
		click_on_related_item_tab.click();
	}
	
	public void selectSameClinicSupplyDistribution() throws InterruptedException {
		waitForElementToBeVisible(driver, search_incoming_supply_distributor_1_2, 10);
		WebElement element = driver.findElement(search_incoming_supply_distributor_1_2_);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		Thread.sleep(1000);
		search_incoming_supply_distributor_1_2.click();
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_same_clinic_supply_distributor_1_2, 10);
		Thread.sleep(2000);
		select_same_clinic_supply_distributor_1_2.click();
		//#search_supply_location_To.sendKeys(Keys.ARROW_DOWN);
		//#search_supply_location_To.sendKeys(Keys.ENTER);
	}

	public Double getValueOfRemainingDosesDistribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_doses_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_distribution_1_2_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQtyDistribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_1_2_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}
	
	public Double getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_doses_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_distribution_1_2_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}
	public Double getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_1_2_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container1_Lot_EK4241_Distribution_2_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container1_lot_EK4241_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container1_lot_EK4241_distribution_2_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container1_Lot_EK4241_Distribution_2_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container1_lot_EK4241_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container1_lot_EK4241_distribution_2_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container1_Lot_MT0055_Distribution_1_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container1_lot_MT0055_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container1_lot_MT0055_distribution_1_1_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_1_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container2_lot_EK4241_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container2_lot_EK4241_distribution_1_1_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container1_Lot_MT0055_Distribution_1_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container1_lot_MT0055_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container1_lot_MT0055_distribution_1_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_1_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container2_lot_EK4241_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container2_lot_EK4241_distribution_1_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container3_Lot_SPIKEVAX6_5Test001_Distribution_1_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_1_1_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container3_Lot_SPIKEVAX6_5Test001_Distribution_1_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_1_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_1_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container1_Lot_MT0055_Distribution_2_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container1_lot_MT0055_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container1_lot_MT0055_distribution_2_1_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container1_Lot_MT0055_Distribution_2_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container1_lot_MT0055_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container1_lot_MT0055_distribution_2_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container2_Lot_EK4241_Distribution_2_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container2_lot_EK4241_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container2_lot_EK4241_distribution_2_1_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container2_Lot_EK4241_Distribution_2_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container2_lot_EK4241_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container2_lot_EK4241_distribution_2_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container3_Lot_SPIKEVAX6_5Test001_Distribution_2_1() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container3_lot_SPIKEVAX6_5Test001_distribution_2_1_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container3_Lot_SPIKEVAX6_5Test001_Distribution_2_1() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_2_1_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container3_lot_SPIKEVAX6_5Test001_distribution_2_1_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container4_Lot_MT0055_Distribution_1_2() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container4_lot_MT0055_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container4_lot_MT0055_distribution_1_2_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}
	public Double getValueOfRemainingQty_Container4_Lot_MT0055_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container4_lot_MT0055_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container4_lot_MT0055_distribution_1_2_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container5_Lot_EK4241_Distribution_1_2() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container5_lot_EK4241_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container5_lot_EK4241_distribution_1_2_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container5_Lot_EK4241_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container5_lot_EK4241_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container5_lot_EK4241_distribution_1_2_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
	}

	public Double getValueOfRemainingDoses_Container6_Lot_SPIKEVAX6_5Test001_Distribution_1_2() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_container6_lot_SPIKEVAX6_5Test001_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_doses_container6_lot_SPIKEVAX6_5Test001_distribution_1_2_);
		Thread.sleep(2000);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingQty_Container6_Lot_SPIKEVAX6_5Test001_Distribution_1_2() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_container6_lot_SPIKEVAX6_5Test001_distribution_1_2_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_container6_lot_SPIKEVAX6_5Test001_distribution_1_2_);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double quantity = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (quantity);
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

	public void clickDropdownMenu() throws InterruptedException {
		waitForElementToBeVisible(driver, dropdownMenu, 10);
		Thread.sleep(2000);
		this.dropdownMenu.click();
	}
	public void selectReceiveFromDropdownMenu() throws InterruptedException {
		waitForElementToBePresent(driver, dropdownMenu1, 10);
		click(dropdownMenu1);
		waitForElementToBeVisible(driver, receiveSupplies, 10);
		click(receiveSupplies);
	}

	public void selectSupplyItemsFromDropdown() throws InterruptedException {
		waitForElementToBeVisible(driver, supplyItemsInDropdown, 10);
		Thread.sleep(5000);
		this.supplyItemsInDropdown.click();
		Thread.sleep(2000);
	}

	public void selectSupplyItemName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_item, 10);
		Thread.sleep(2000);
		select_desired_supply_item.click();
		Thread.sleep(2000);
		//waitForElementToBeLocated(driver, select_desired_supply_item1, 10);
		//Thread.sleep(2000);
		//WebElement element = driver.findElement(select_desired_supply_item1);
		//JavascriptExecutor executor = (JavascriptExecutor) driver;
		//executor.executeScript("arguments[0].click();", element);
	}

	public void selectSupplyLocationName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_loc, 10);
		Thread.sleep(2000);
		this.select_desired_supply_loc.click();
	}

	public void clickSupplyDistribution() throws InterruptedException {
		waitForElementToBeLocated(driver, click_supply_distribution1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(click_supply_distribution1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		executor.executeScript("arguments[0].click();", element);
	}

	public String getSupplyDistributionName() throws InterruptedException {
		WebElement element = driver.findElement(get_supply_distribution_name1);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
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

	public String getSupplyDistributionDescription() throws InterruptedException {
		WebElement element = driver.findElement(get_supply_distribution_description1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}

	public void selectSupplyLocationFromDropdown() {
		this.supplyLocationInDropdown.click();
	}

	public void SelectDropDownToClickReceiveSuppliesButton() throws InterruptedException {
		waitForElementToBeLocated(driver, select_drpdown_to_receive_supplies1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(select_drpdown_to_receive_supplies1);
		Thread.sleep(2000);
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

	public SupplyConsolePage selectSupplyItemTo(String supplyItem) throws InterruptedException {
		log(" -- select supply item  -  " + supplyItem);
		selectSupplyTo(searchSupplyItems, supplyItem);
		return this;
	}

	@Step
	private void selectSupplyTo(WebElement element, String location) throws InterruptedException {
		log(" -- select to  -  " + location);
		waitForElementToBeVisible(driver, element, 10);
		element.sendKeys(location);
		By locationTo = By.xpath("//lightning-base-combobox-formatted-text[@title='" + location + "']");
		waitForElementToBePresent(driver, locationTo, 30);
		click(driver.findElement(locationTo));
		waitForElementNotToBeVisible(driver, locationTo, 10);
	}
	@Step
	public SupplyConsolePage selectSupplyLocation(String location) throws InterruptedException {
		log(" -- select to location  -  " + location);
		selectSupplyTo(search_supply_location_2_To, location);
		return this;
	}

	public String validateQTYField() throws InterruptedException {
		WebElement element = driver.findElement(validate_qty_field1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}

	public void enterQuantity() throws InterruptedException {
		waitForElementToBeLocated(driver, click_to_select_quantity1, 10);
		WebElement element = driver.findElement(click_to_select_quantity1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		click(click_to_select_quantity1);
		element.sendKeys("1");
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

	public SupplyConsolePage selectReasonForReception() throws InterruptedException {
		waitForElementToBeLocated(driver, click_reason1, 10);
		WebElement element = driver.findElement(click_reason1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_reason1, 10);
		WebElement element1 = driver.findElement(select_reason1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		return this;
	}

	public void ValidateSaveButtonIsDisplayedOnReceiveSupplies() throws InterruptedException {
		waitForElementToBeLocated(driver, save_button_receive_supplies1, 10);
		save_button_receive_supplies.isDisplayed();
	}

	public void ValidateCancelButtonIsDisplayedOnReceiveSupplies() throws InterruptedException {
		waitForElementToBeLocated(driver, cancel_button_receive_supplies1, 10);
		cancel_button_receive_supplies.isDisplayed();
	}

	public void ClickSaveButton() throws InterruptedException {
		waitForElementToBeLocated(driver, save_button_receive_supplies1, 10);
		save_button_receive_supplies.click();
	}

	public String getDoseConversionFactorReceive() throws InterruptedException {
		WebElement element = driver.findElement(get_dose_conversion_factor1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		return (element.getText());
	}
	public double getDoseConversionFactorOnReceive() {
		waitForElementToBePresent(driver, get_dose_conversion_factor2, 10);
		double value = Double.parseDouble(driver.findElement(get_dose_conversion_factor2).getAttribute("value"));
		log(" -- dose conversation factore  -  " + value);
		return value;
	}

	public void selectIncomingSupplyDistributionReceive() throws InterruptedException {
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
	@Step
	public void acceptIncomingTransfer(String distribution) throws InterruptedException {
		selectConfirmIncomingDropDown();
		acceptTransfer(distribution);
	}

	@Step
	public void cancelIncomingTransfer() throws InterruptedException {
		selectCancelInDropDown();
		cancelTransfer();
	}
	@Step
	public void acceptBulkTransferToDistribution(String distribution) throws InterruptedException {
		clickBulkConfirmIncomingTransfersButton();
		acceptTransfer(distribution);
	}

	@Step
	public void acceptTransfer(String distribution) throws InterruptedException {
		transferToDistributionOnReceive(distribution);
		clickOnConfirmModalIncomingTransactionButton();
		successMessageAppear();
	}
	@Step
	public void cancelTransfer() throws InterruptedException {
		clickOnButtonInModalTransaction(btnCancelTransaction);
		successMessageAppear();
	}

	@Step
	public void cancelBulkTransfer() throws InterruptedException {
		clickOnButtonInModalTransaction(btnCancelTransaction2);
		successMessageAppear();
	}

	@Step
	public void transferDosesToSupplyLocation(String location) throws InterruptedException {
		selectSupplyLocation(location).clickBulkTransfersModalButton()
				.clickBulkTransfersCloseButton();
	}

	@Step
	public void transferToDistributionWithinSameClinic(String location, String distribution) throws InterruptedException {
		selectSupplyLocation(location);
		transferToDistributionOnSend(distribution);
		clickBulkTransfersModalButton();
		clickBulkTransfersCloseButton();
	}

	public SupplyConsolePage transferToDistributionOnSend(String distribution) throws InterruptedException {
		selectTransferToDistribution(search_incoming_supply_distributor_1_2, distribution);
		return this;
	}

	public void transferToDistributionOnReceive(String distribution) throws InterruptedException {
		selectTransferToDistribution(search_incoming_supply_distributor, distribution);
	}

	@Step
	public void draftToDistributionWithinSameClinic(String location, String distribution) throws InterruptedException {
		selectSupplyLocation(location);
		transferToDistributionOnSend(distribution);
		clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
		clickBulkTransfersCloseButton();
	}

	@Step
	private void selectTransferToDistribution(WebElement element, String distribution) throws InterruptedException {
		log(" -- select to distribution  -  " + distribution);
		waitForElementToBeVisible(driver, element, 20);
		moveToElement(element);
		click(element);
		By locationTo = By.xpath("//*[contains(@title, '" + distribution + "')]");
		waitForElementToBePresent(driver, locationTo, 20);
		scrollTop(driver.findElement(locationTo));
		driver.findElement(locationTo).click();
		waitForElementNotToBeVisible(driver, locationTo, 10);
	}

	public void selectShipped_From() throws InterruptedException {
		waitForElementToBeVisible(driver, search_supply_location_from, 60);
		search_supply_location_from.sendKeys("Atlin Health Centre");
		waitForElementToBeVisible(driver, select_supply_location_from, 60);
		select_supply_location_from.click();
	}

	public void clickLineItemCheckBox() throws InterruptedException {
		By check_box = By.xpath("//tbody/tr[7]/td[1]/lightning-input/div/span/label/span[@part='indicator']");
		waitForElementToBeLocated(driver, check_box, 30);
		WebElement element = driver.findElement(check_box);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(500);
		element.click();
	}

	public void clickLineItemCheckBoxNotInStock() throws InterruptedException {
		By check_box = By.xpath("//tbody/tr[14]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]");
		WebElement element = driver.findElement(check_box);
		//Scroll to select the desired line item
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		element.click();
	}

	public void clickEditExpectedDeliveryDate() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, editExpectedDeliveryDate, 30);
		editExpectedDeliveryDate.click();
	}

	public void inputExpectedDate() throws InterruptedException {
		Thread.sleep(2000);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		//DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String tomorrowAsString = dateFormat.format(tomorrow);
		waitForElementToBeVisible(driver, inputExpectedDate, 30);
		inputExpectedDate.sendKeys(tomorrowAsString, Keys.ENTER);
	}

	public void clickSaveExpectedDeliveryDate() {
		saveExpectedDeliveryDate.click();
	}

	public void clickApproveRequisition() throws InterruptedException {
		//Scroll to top of the Screen to find approve button and Approve requisition
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,-400)", "");
//		Thread.sleep(5000);
		approveRequisition.click();
	}

	public void clickSaveApprovedRequisition() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, saveApprovedRequisition, 10);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", saveApprovedRequisition);
		saveApprovedRequisition.click();
	}

	public void enterApprovedDose(String inputDose) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, driver.findElement(By.xpath("//label[text() = 'Trade']")), 30);
		WebElement trade = driver.findElement(By.xpath("//label[text() = 'Trade']/..//input"));
		String tradeValue = trade.getAttribute("value");
		WebElement approveDoseField = driver.findElements(By.xpath("//div[contains(text(), '" + tradeValue + "')]/../../td[6]//input")).get(0);
		approveDoseField.sendKeys(inputDose);
	}

	public void clickShipRequisition() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, shipRequisition, 30);
		shipRequisition.click();
	}

	public void clickSaveShipRequisition() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)", "");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", saveShipRequisition);
		waitForElementToBeVisible(driver, saveShipRequisition, 10);
		saveShipRequisition.click();
	}

	public String ShipRequisition() throws InterruptedException {
		Thread.sleep(2000);
		return verified.getText();
	}

	public void clickReceiveRequestBtn() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, receiveRequestBtn, 30);
		receiveRequestBtn.click();
	}

	public void clickOnSearchSupplyDistributions() throws InterruptedException {
		waitForElementToBeVisible(driver, click_on_search_supply_distributions_to_component, 10);
		Thread.sleep(2000);
		this.click_on_search_supply_distributions_to_component.click();
	}

	public void SelectSupplyDistributionTo() throws InterruptedException {
		waitForElementToBeVisible(driver, click_on_search_supply_distributions_to_component, 10);
		Thread.sleep(3000);
		click_on_search_supply_distributions_to_component.click();
		Thread.sleep(3000);
		click_on_search_supply_distributions_to_component.sendKeys("SDST");
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(3000);
		select_supply_distributions_to.click();
	}

	public void clickSaveReceiveRequisition() {
		saveReceiveRequisition.click();
	}

	public void enterApproverComments(String comment) {
		approverComment.sendKeys(comment);
	}

	public void refreshBrowser() throws InterruptedException {
		driver.navigate().refresh();
	}
}