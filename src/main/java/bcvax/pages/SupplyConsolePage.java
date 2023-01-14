package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static Constansts.Domain.SUPPLY_LOCATION_1;
import static Constansts.Domain.SUPPLY_LOCATION_2;


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
	
	@FindBy(xpath = "//button[contains(text(),\"Request Supplies\")]")
	private WebElement request_supplies;
	private By request_supplies_1 = By.xpath("//button[contains(text(),\"Request Supplies\")]");
	
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
	
	@FindBy(xpath = "//input[@name=\"BCH_Requested_Delivery_Date__c\"]")
	private WebElement inputDate;
	private By input_data = By.xpath("//input[@name=\"BCH_Requested_Delivery_Date__c\"]");
	
	@FindBy(xpath = "//button[contains(text(),\"Next\")]")
	private WebElement nextButton;
	private By next_button = By.xpath("//button[contains(text(),\"Next\")]");
	
	@FindBy(xpath = "button[@class=\"slds-button slds-button_brand cuf-publisherShareButton undefined uiButton\"]")
	private WebElement saveSubmitRequisition;
	private By save_Submit_Requisition = By.xpath("button[@class=\"slds-button slds-button_brand cuf-publisherShareButton undefined uiButton\"]");
	
	@FindBy(xpath = "span[contains(text(),\"Select a date for Expected Delivery Date\")]")
	private WebElement expectedDeliveryDateCalendar;
	private By expected_delivery_date_calendar = By.xpath("span[contains(text(),\"Select a date for Expected Delivery Date\")]");
	
	@FindBy(xpath = "//button[contains(text(),\"Submit Requisition\")]")
	private WebElement submitRequisition;
	private By submit_requisition = By.xpath("//button[contains(text(),\"Submit Requisition\")]");
	
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

	@FindBy(xpath = ".//a[text() = 'Related Items']")
	private WebElement click_on_related_item_tab;
	private By click_on_related_item_tab_1 = By.xpath(".//a[text() = 'Related Items']");

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
	//private By dropdownMenu = By.xpath("//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']");

//	@FindBy(xpath = "//*[@class='slds-grid slds-grid--vertical-align-center slds-grid--align-center sldsButtonHeightFix']")
//	private WebElement dropdownMenu1;
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
	private By get_dose_conversion_factor1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose Conversion Factor']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

	@FindBy(xpath = "//button[@name='distributionBox']")
	private WebElement supply_distribution_to;
	private By supply_distribution_to1 = By.xpath("//button[@name='distributionBox']");

	@FindBy(xpath = "(//span[contains(text(),'Supply Distribution_1 - SDST-000')])[1]")
	private WebElement select_supply_distributor;
	private By select_supply_distributor1 = By.xpath("(//span[contains(text(),'Supply Distribution_1 - SDST-000')])[1]");


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
	}
	
	/*-------------Methods--------------*/
	public void inputRequestedDose(String inputDose) {
		this.requestedDose.sendKeys(inputDose);
	}
	
	public void clickSaveButton() {
		this.saveButton.click();
	}
	
	public void inputShipAddress(String inputAddress) {
		this.shipAddress.sendKeys(inputAddress);
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
	
	public void clickSubmitRequisition() {
		//waitForElementToBeVisible(driver, submitRequisition, 10);
		//WebElement element = driver.findElement(submit_requisition);
		this.submitRequisition.click();
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
		//waitForElementToBeLocated(driver, supply_locations_tab1, 10);
		waitForElementToBeVisible(driver, supply_locations_tab, 10);
		WebElement element = driver.findElement(supply_locations_tab1);
		//waitForElementToBeVisible(driver, element, 10);
		//JavascriptExecutor executor = (JavascriptExecutor) driver;
		//executor.executeScript("arguments[0].click();", element);
		//click(supply_locations_tab1);
		this.supply_locations_tab.click();
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
	
	public void clickRequestSupplies() throws InterruptedException {
		waitForElementToBeLocated(driver, request_supplies_1, 10);
		WebElement element = driver.findElement(request_supplies_1);
		this.request_supplies.click();
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
	
	public void clickOnSupplyContainerCheckbox(int k) throws InterruptedException {
		//By container_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='c_hcCrossObjectRelationRecordsList']//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
		By container_checkbox_1_ = By.xpath("(//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
		waitForElementToBeLocated(driver, container_checkbox_1_, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(container_checkbox_1_);
		click(container_checkbox_1_);
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

	public void enterBulkTransferByQuantity(int k) throws InterruptedException {
		By qty_1_ = By.xpath("(.//input[@class = 'slds-input'])[" + k + "]");
		waitForElementToBeLocated(driver, qty_1_, 10);
		WebElement element = driver.findElement(qty_1_);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		click(qty_1_);
		element.sendKeys("1");
	}

	@Step
	public SupplyConsolePage selectSupplyLocation(String location) throws InterruptedException {
		log(" -- select to location  -  " + location);
		waitForElementToBeVisible(driver, search_supply_location_2_To, 10);
		search_supply_location_2_To.sendKeys(location);
		By locationTo = By.xpath("//lightning-base-combobox-formatted-text[@title='" + location + "']");
		waitForElementToBePresent(driver, locationTo, 20);
		click(driver.findElement(locationTo));
		waitForElementNotToBeVisible(driver, locationTo, 10);
		return this;
	}

	@Step
	public SupplyConsolePage selectSupplyLocation_2_To() throws InterruptedException {
		log(" -- select 'To' Automation Supply Location_2  -");
		waitForElementToBeVisible(driver, search_supply_location_2_To, 10);
		search_supply_location_2_To.sendKeys(SUPPLY_LOCATION_2);
		Thread.sleep(3000);
		waitForElementToBeVisible(driver, select_supply_location_2_To, 10);
		Thread.sleep(5000);
		select_supply_location_2_To.click();
		Thread.sleep(2000);
		//#search_supply_location_To.sendKeys(Keys.ARROW_DOWN);
		//#search_supply_location_To.sendKeys(Keys.ENTER);
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
		click(bulk_transfers_dialog_button_1);
		return this;
	}
	@Step
	public void clickBulkTransfersCloseButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_dialog_close_button_1, 10);
		click(bulk_dialog_close_button_1);
		waitForElementNotToBeVisible(driver, bulk_dialog_close_button_1, 10);
	}

	@Step
	public void clickTransactionsTab() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-300)");
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
		By outgoing_transactions_id = By.xpath("(.//flexipage-component2[@data-component-id='hcShippedSupplyTransactions2']//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)[" + kk + "]//a[@title='transactionFromName']");
		waitForElementToBeLocated(driver, outgoing_transactions_id, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(outgoing_transactions_id);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.getText();
		//element.getAttribute("value");
		return (element.getText());
	}
	
	public void clickOnOutgoingTransactions(int kk) throws InterruptedException {
		By transactions_from_1_ = By.xpath("(.//flexipage-component2[@data-component-id='hcShippedSupplyTransactions2']//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)[" + kk + "]//a[@title='transactionFromName']");
		waitForElementToBeLocated(driver, transactions_from_1_, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(transactions_from_1_);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		//waitForElementToBeLocated(driver, transactions_from_1_, 10);
		Thread.sleep(2000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
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
		do {
			try {
				WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
				Thread.sleep(2000);
				closetab.click();
				Thread.sleep(2000);
			} catch (NoSuchElementException e) {
				System.out.println("/*---there are no Tab's to close anymore");
			}
		} while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
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
		click(btnTransferDraftOnContainerTransferPage);
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
		By incoming_transaction_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='hcShippedSupplyTransactions']//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
		waitForElementToBeLocated(driver, incoming_transaction_checkbox_1_, 10);
		//waitForElementToBeLocated(driver, incoming_transaction_checkbox_1_, 10);
		WebElement element = driver.findElement(incoming_transaction_checkbox_1_);
		//this.incoming_transaction_checkbox_1.click();
		click(incoming_transaction_checkbox_1_);
	}
	
	public void clickBulkConfirmIncomingTransfersButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_confirm_incoming_transfers_button_1, 10);
		Thread.sleep(1000);
		WebElement element = driver.findElement(bulk_confirm_incoming_transfers_button_1);
		Thread.sleep(2000);
		//scroll up
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1500)");
		Thread.sleep(1000);
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-350)");
		//Thread.sleep(1000);
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-350)");
		//Thread.sleep(1000);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		//Thread.sleep(1000);
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
		scrollTop(confirm_incoming_transfers_modal_button);
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
	public void successMessageAppear() throws InterruptedException {
		try {
			waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Success!']"), 20);
			WebElement successMessage = driver.findElement(By.xpath(".//div[text() = 'Success!']"));
			Thread.sleep(2000);
			log(" -- Toast success message has been Appears");
		} catch (NoSuchElementException e) {
			System.out.println("/*---there are no success confirmation Message for Bulk Transfers to be Appears");
			throw new RuntimeException("/*---there are no success confirmation Message to be Appears--*/");
		}
	}
	
	public void clickOnContainerDropDownMenu() throws InterruptedException {
		waitForElementToBeLocated(driver, click_container_dropdown_menu1, 10);
		WebElement element = driver.findElement(click_container_dropdown_menu1);
		Thread.sleep(2000);
		click_container_dropdown_menu.click();
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

	public Double getValueOfRemainingQty() throws InterruptedException {
		waitForElementToBeLocated(driver, get_remaining_Qty_, 10);
		WebElement element = driver.findElement(get_remaining_Qty_);
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
		waitForElementToBeLocated(driver, Doses, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(Doses);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		Thread.sleep(2000);
		click(Doses);
		Thread.sleep(2000);
		element.clear();
		Thread.sleep(2000);
		element.sendKeys(doses);
		return this;

	}

	public SupplyConsolePage enterTransferQuantity(String quantity) throws InterruptedException {
		By Quantity = By.xpath("//lightning-input//label[text()='Quantity']//following-sibling::div/input[@class='slds-input']");
		waitForElementToBeLocated(driver, Quantity, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(Quantity);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		Thread.sleep(2000);
		click(Quantity);
		Thread.sleep(2000);
		element.clear();
		Thread.sleep(2000);
		element.sendKeys(quantity);
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
		By incoming_transaction_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='hcShippedSupplyTransactions']//tbody//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[" + j + "]");
		waitForElementToBeLocated(driver, incoming_transaction_checkbox_1_, 10);
		WebElement element = driver.findElement(incoming_transaction_checkbox_1_);
		click(incoming_transaction_checkbox_1_);
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
		this.select_desired_supply_item.click();
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_desired_supply_item1, 10);
		Thread.sleep(2000);
		WebElement element = driver.findElement(select_desired_supply_item1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
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

	public SupplyConsolePage selectSupplyItem1(String supplyItem) throws InterruptedException {

		log(" -- select supply item  -  " + supplyItem);
		waitForElementToBeVisible(driver, searchSupplyItems, 20);
		scrollTop(searchSupplyItems);
		searchSupplyItems.sendKeys(supplyItem);
		//click(searchSupplyItems);
		By locationTo = By.xpath("//lightning-base-combobox-formatted-text[contains(@title, '" + supplyItem + "')]");
		//hardWait(100);
		waitForElementToBePresent(driver, locationTo, 20);
		click(driver.findElement(locationTo));
		waitForElementNotToBeVisible(driver, locationTo, 10);
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
		System.out.println(driver.findElement(get_dose_conversion_factor1).getAttribute("value"));
		element.getText();
		return (element.getText());
	}
	public double getDoseConversionFactorOnReceive() {
		waitForElementToBePresent(driver, get_dose_conversion_factor1, 10);
		double value = Double.parseDouble(driver.findElement(get_dose_conversion_factor1).getAttribute("value"));
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
	private void selectTransferToDistribution(WebElement element, String distribution) throws InterruptedException {
		log(" -- select to distribution  -  " + distribution);
		waitForElementToBeVisible(driver, element, 20);
		scrollTop(element);
		click(element);
		By locationTo = By.xpath("//*[contains(@title, '" + distribution + "')]");
		waitForElementToBePresent(driver, locationTo, 20);
		click(driver.findElement(locationTo));
		waitForElementNotToBeVisible(driver, locationTo, 10);
	}
}