package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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

	@FindBy(xpath = ".//button[text() = 'Transfer']")
	private WebElement bulk_transfers_button;
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
	
	@FindBy(xpath = "(.//a[text() = 'Transactions'])")
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
	private By search_incoming_supply_distributor_1_2_ = By.xpath(".//span[contains(text(),'Select an Option')]");
	
	@FindBy(xpath = "//span[@title='Supply Distribution_2_1 - SDST-0000001484']")
	private WebElement select_incoming_supply_distributor;
	private By select_incoming_supply_distributor_ = By.xpath("//span[@title='Supply Distribution_2_1 - SDST-0000001484']");
	
	@FindBy(xpath = "//span[@title='Automation Supply Distribution_1_2 - SDST-0000001499']")
	private WebElement select_same_clinic_supply_distributor_1_2;
	private By select_same_clinic_supply_distributor_1_2_ = By.xpath("//span[@title='Automation Supply Distribution_1_2 - SDST-0000001499']");
	
	
	@FindBy(xpath = ".//button[text() = 'Confirm Transaction']")
	private WebElement confirm_incoming_transfers_modal_button;
	private By confirm_incoming_transfers_modal_button_1 = By.xpath(".//button[text() = 'Confirm Transaction']");
	
	@FindBy(xpath = "(.//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[1]")
	private WebElement click_container_dropdown_menu;
	private By click_container_dropdown_menu1 = By.xpath("(.//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[1]");
	
	@FindBy(xpath = ".//a/span[text() = 'Transfer']")
	private WebElement select_Transfer_in_dropdown;
	private By select_Transfer_in_dropdown1 = By.xpath(".//a/span[text() = 'Transfer']");
	
	@FindBy(xpath = "//a[contains(text(),'Pfizer mRNA BNT162b2 - EK4241')]")
	private WebElement select_desired_supply_container;
	private By select_desired_supply_container1 = By.xpath("//a[contains(text(),'Pfizer mRNA BNT162b2 - EK4241')]");
	
	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses;
	private By get_remaining_doses_ = By.xpath("(.//tr[@class='slds-hint-parent'][1]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");
	
	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]")
	private WebElement get_remaining_doses_distribution_1_2;
	private By get_remaining_doses_distribution_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[3]");
	
	
	@FindBy(xpath = "(.//lightning-primitive-cell-factory//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty;
	private By get_remaining_Qty_ = By.xpath("(.//lightning-primitive-cell-factory//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");
	
	@FindBy(xpath = "(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]")
	private WebElement get_remaining_Qty_1_2;
	private By get_remaining_Qty_1_2_ = By.xpath("(.//tr[@class='slds-hint-parent'][2]//td//div//lightning-formatted-number[@lightning-formattednumber_formattednumber-host=''])[4]");
	
	
	@FindBy(xpath = ".//input[@name = 'HC_Product_Measure__c']")
	private WebElement get_dose_conversation_factor;
	private By get_dose_conversation_factor1 = By.xpath(".//input[@name = 'HC_Product_Measure__c']");
	
	@FindBy(xpath = ".//input[@name = 'BCH_Product_Name__c']")
	private WebElement get_trade_name;
	private By get_trade_name1 = By.xpath(".//input[@name = 'BCH_Product_Name__c']");
	
	@FindBy(xpath = ".//a/span[text() = 'Confirm']")
	private WebElement select_Confirm_in_dropdown;
	private By select_Confirm_in_dropdown1 = By.xpath(".//a/span[text() = 'Confirm']");
	
	@FindBy(xpath = ".//a[text() = 'Related Items']")
	private WebElement click_on_related_item_tab;
	private By click_on_related_item_tab_1 = By.xpath(".//a[text() = 'Related Items']");

	@FindBy(xpath = "//span[@class='slds-truncate' and contains(text(),'Edit')]")
	private WebElement btnEditOnTrasactionPage;

	@FindBy(xpath = "//span[@class='slds-truncate' and contains(text(),'Cancel')]")
	private WebElement btnCancelTransfer;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		By container_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='c_hcCrossObjectRelationRecordsList']//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
		waitForElementToBeLocated(driver, container_checkbox_1_, 10);
		//waitForElementToBeLocated(driver, container_checkbox_1_, 10);
		WebElement element = driver.findElement(container_checkbox_1_);
		//this.container_checkbox_1.click();
		click(container_checkbox_1_);
	}
	
	public int getRowsSupplyContainersFromCount() throws InterruptedException {
		//waitForElementToBeClickable(driver, container_checkbox_1, 10);
		//waitForElementToBeLocated(driver, container_checkbox_1_, 10);
		List<WebElement> rows = driver.findElements(rows_supply_containers_from_count_path_1);
		//rows.size();
		return (rows.size());
	}
	
	public void clickBulkTransfersButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_transfers_button_1, 10);
		WebElement element = driver.findElement(bulk_transfers_button_1);
		this.bulk_transfers_button.click();
		//click(bulk_transfers_button_1);
	}
	
	public void verifyIsSupplyPageDisplayed() {
		waitForElementToBeVisible(driver, supply_page_displayed, 10);
		this.supply_page_displayed.isDisplayed();
	}
	
	public void enterBulkTransferDosages(int k) throws InterruptedException {
		//private By doses_1 = By.xpath("(.//input[@class = 'slds-input'])[2]");
		By dose_1_ = By.xpath("(.//input[@class = 'slds-input'])[" + k + "]");
		waitForElementToBeLocated(driver, dose_1_, 10);
		WebElement element = driver.findElement(dose_1_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollRight = arguments[0].scrollWidth", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
		click(dose_1_);
		element.sendKeys("1");
	}
	
	public void selectSupplyLocation_2_To() throws InterruptedException {
		waitForElementToBeVisible(driver, search_supply_location_2_To, 10);
		search_supply_location_2_To.sendKeys("Automation Supply Location_2");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_supply_location_2_To, 10);
		select_supply_location_2_To.click();
		Thread.sleep(2000);
		//#search_supply_location_To.sendKeys(Keys.ARROW_DOWN);
		//#search_supply_location_To.sendKeys(Keys.ENTER);
	}
	
	public void selectSupplyLocation_1_To() throws InterruptedException {
		waitForElementToBeVisible(driver, search_supply_location_1_To, 10);
		search_supply_location_1_To.sendKeys("Automation Supply Location_1");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, select_supply_location_1_To, 10);
		select_supply_location_1_To.click();
		Thread.sleep(2000);
	}
	
	public void clickBulkTransfersModalButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_transfers_dialog_button_1, 10);
		click(bulk_transfers_dialog_button_1);
	}
	
	public void clickBulkTransfersCloseButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_dialog_close_button_1, 10);
		click(bulk_dialog_close_button_1);
	}
	
	public void clickTransactionsTab() throws InterruptedException {
		//scroll up
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-300)");
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, transactions_tab, 10);
		//WebElement element = driver.findElement(transactions_tab_1);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
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
	
	public void closeAutomationLocationTab() throws InterruptedException {
		do {
			try {
				WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
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

	public void clickDropDownLatestDraftTransactionsAndConfirmTransfer(int countDraftTransactions, double amountOfDosesToAdjustInDraftEdit) throws InterruptedException {
		//Offset due to 0 is not a real value
		int offset = countDraftTransactions-1;
		WebElement draftTransactionElement = driver.findElement
				(By.xpath("(//span[contains(text(),'Draft')]/../../../../..//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[" + offset + "]"));
		click(draftTransactionElement);
		click(btnEditOnTrasactionPage);
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
	
	public void clickOnConfirmModalIncomingTransactionButton() throws InterruptedException {
		waitForElementToBeLocated(driver, confirm_incoming_transfers_modal_button_1, 10);
		WebElement element = driver.findElement(confirm_incoming_transfers_modal_button_1);
		//this.bulk_confirm_incoming_transfers_button.click();
		click(confirm_incoming_transfers_modal_button_1);
	}
	
	public void successMessageAppear() throws InterruptedException {
		try {
			waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Success!']"), 10);
			WebElement successMessage = driver.findElement(By.xpath(".//div[text() = 'Success!']"));
			Thread.sleep(2000);
			System.out.println("/* ----the toast success message has been Appears");
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
	
	public void selectTransferFromDropDown() throws InterruptedException {
		waitForElementToBeLocated(driver, select_Transfer_in_dropdown1, 10);
		Thread.sleep(2000);
		select_Transfer_in_dropdown.click();
	}
	
	public void selectSupplyContainer() throws InterruptedException {
		waitForElementToBeLocated(driver, select_desired_supply_container1, 10);
		WebElement element = driver.findElement(select_desired_supply_container1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}
	
	public Double getValueOfRemainingDoses() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,150)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_doses_, 10);
		WebElement element = driver.findElement(get_remaining_doses_);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
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
	
	public void enterTransferDosages(String doses) throws InterruptedException {
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
		
	}

	public void enterTransferQuantity(String quantity) throws InterruptedException {
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
	
	public double getDoseConversionFactor() {
		double doseConversionFactor = Double.parseDouble(getValue(doseConversionFactorForSingleWastage));
		return doseConversionFactor;
	}
	
	public void selectReasonForAdjustmentDropDown() throws InterruptedException {
		click(reasonForAdjustmentFromDropDown);
		reasonForAdjustmentFromDropDown.sendKeys("a"); //Administered Vaccine
		reasonForAdjustmentFromDropDown.sendKeys(Keys.ENTER);
	}
	
	public void selectReasonForWastageDropDown() throws InterruptedException {
		click(reasonForWastageValueFromDropDown);
		reasonForWastageValueFromDropDown.sendKeys("c"); //CCI: Equipment Malfunction
		reasonForWastageValueFromDropDown.sendKeys(Keys.ENTER);
//		click(dropDownValueCCIHandlingError); // Working on local but have difficulty clicking on Jenkins
	}
	
	public void clickBtnWastageAtContainerWastagePopUp() throws InterruptedException {
		scrollTop(btnWastageOnContainerWastagePopUp);
		click(btnWastageOnContainerWastagePopUp);
		Thread.sleep(2000); //To handle success message
		//Need to add validation for successful mess
	}
	
	public void clickBtnAdjustmentAtContainerAdjustmentPopUp() throws InterruptedException {
		scrollTop(btnAdjustmentOnContainerWastagePopUp);
		click(btnAdjustmentOnContainerWastagePopUp);
		Thread.sleep(2000); //To handle success message
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
		HashMap<Integer, ArrayList<Double>> remainingDosesAndQuantityMap = new HashMap<>();
		int d = 3;
		int q = 4;
		for (int i = 0; i < numberOfRows; i++) {
			ArrayList<Double> value = new ArrayList<>();
			WebElement remainingDosesWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + d + "]"));
			WebElement remainingQuantityWebElement = driver.findElement(By.xpath("(//lightning-formatted-number)[" + q + "]"));
			Double remainingDoses = Double.parseDouble(getValue(remainingDosesWebElement));
			Double remainingQuantity = Double.parseDouble(getValue(remainingQuantityWebElement));
			Double doseConversionFactor = remainingDoses / remainingQuantity;
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
	
	public void enterBulkWastageByDosageWithReasonForWastage(int amount, int numberOfRows) throws InterruptedException {
		//By dosage wastage
		int y = 0;
		int k = 4;
		while (y < numberOfRows) {
			WebElement dosesDynamicFiled = driver.findElement(By.xpath("(//input[@class = 'slds-input'])[" + k + "]"));
			typeIn(dosesDynamicFiled, String.valueOf(amount));
			WebElement reasonForWastageDynamicDropDown = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux']"));
			click(reasonForWastageDynamicDropDown);
			WebElement reasonForWastageDynamicFiled = driver.findElement(By.xpath("(//span[@title='CCI: Handling Error'])[" + (y + 1) + "]"));
			click(reasonForWastageDynamicFiled);
			k = k + 3;
			y++;
		}
	}
	
	public void enterBulkAdjustmentByDosageWithReasonForAdjustment(double amount, int numberOfRows) throws InterruptedException {
		//By dosage
		int y = 0;
		int k = 4;
		while (y < numberOfRows) {
			WebElement dosesDynamicFiled = driver.findElement(By.xpath("(//input[@class = 'slds-input'])[" + k + "]"));
			typeIn(dosesDynamicFiled, Double.toString(amount));
			WebElement reasonDynamicDropDown = driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux']"));
			click(reasonDynamicDropDown);
			WebElement selectReasonFromDynamicDropDown = driver.findElement(By.xpath("(//span[@title='Administered Vaccine'])[" + (y + 1) + "]"));
			click(selectReasonFromDynamicDropDown);
			k = k + 3;
			y++;
		}
	}
	
	public String getVaccineName() throws InterruptedException {
		waitForElementToBeLocated(driver, get_trade_name1, 10);
		WebElement element = driver.findElement(get_trade_name1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String tradeName = getValue(get_trade_name);
		return (tradeName);
	}
	
	public double getDoseConversationFactor() throws InterruptedException {
		waitForElementToBeLocated(driver, get_dose_conversation_factor1, 10);
		WebElement element = driver.findElement(get_dose_conversation_factor1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String conversationFactor = getValue(get_dose_conversation_factor);
		Double Factor = Double.parseDouble(conversationFactor.replaceAll(",", ""));
		return (Factor);
	}
	
	public void clickOnIncomingTransactionsDropDownMenu(int j) throws InterruptedException {
		By incoming_transaction_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='hcShippedSupplyTransactions']//tbody//button[@class = 'slds-button slds-button_icon-border slds-button_icon-x-small'])[" + j + "]");
		waitForElementToBeLocated(driver, incoming_transaction_checkbox_1_, 10);
		WebElement element = driver.findElement(incoming_transaction_checkbox_1_);
		click(incoming_transaction_checkbox_1_);
	}
	
	public void selectConfirmIncomingDropDown() throws InterruptedException {
		waitForElementToBeLocated(driver, select_Confirm_in_dropdown1, 10);
		Thread.sleep(2000);
		select_Confirm_in_dropdown.click();
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
	
	
}