package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.beans.ExceptionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import org.testng.asserts.SoftAssert;
import static constansts.Domain.SUPPLY_LOCATION_1;
import static constansts.Domain.SUPPLY_LOCATION_2;
import static constansts.Header.*;
import static constansts.Apps.*;
import static constansts.Header.SUPPLY_TRANSACTION_NAME_FULL;
import static org.testng.Assert.assertTrue;


public class SupplyConsolePage extends BasePage {
	/*---------Properties-------*/
	private By rows_supply_containers_from_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)");

	@FindBy(xpath = "//h2[@class='slds-text-heading_medium slds-hyphenate']/../..//button[text() = 'Transfer']")
	private WebElement btnTransferDraftOnContainerTransferPage;

	@FindBy(xpath = "//label[contains(text(),'Comments')]")
	private WebElement labelComments;

	@FindBy(xpath = ".//button[text() = 'Transfer']")
	private WebElement bulk_transfers_button;

	@FindBy(xpath = ".//button[text() = 'Cancel Transfer']")
	private WebElement bulk_cancel_button;
	private By bulk_transfers_button_1 = By.xpath(".//button[text() = 'Transfer']");

	@FindBy(xpath = "//button[contains(text(),\"Save\")]")
	private WebElement saveButton;

	@FindBy(xpath = "//*[contains(text(), 'Success!')]")
	private WebElement successMessage;

	@FindBy(xpath = "//input[@name='BCH_Requested_Delivery_Date__c']")
	private WebElement inputDate;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextButton;

	@FindBy(xpath = "//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	private WebElement saveSubmitRequisition;
	private By save_Submit_Requisition = By.xpath("//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");

	@FindBy(xpath = "//button[text() = 'Submit Requisition'] | //a[@title = 'Submit Requisition']")
	private WebElement submitRequisition;

	@FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody)[2]")
	private WebElement rows_outgoing_transactions_count_path;

	@FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody)[1]")
	private WebElement rows_incoming_transactions_count_path;

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

	@FindBy(xpath = "//span[contains(text(),'Supply Distribution_1_2')]")
	private WebElement select_same_clinic_supply_distributor_1_2;

	@FindBy(xpath = ".//button[text() = 'Confirm Transaction']")
	private WebElement confirm_incoming_transfers_modal_button;

	@FindBy(xpath = ".//button[text() = 'Cancel Transaction']")
	private WebElement btnCancelTransaction;

	@FindBy(xpath = ".//button[text() = 'Cancel transactions']")
	private WebElement btnCancelTransaction2;
	private By confirm_incoming_transfers_modal_button_1 = By.xpath(".//button[text() = 'Confirm Transaction']");

	private By get_remaining_doses_ = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Doses']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

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

	private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");

	private By click_healthconnect_app1 = By.xpath("//p[text()='Health Connect - Supply Console']");

	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	private WebElement dropdownMenu;

	@FindBy(xpath = ".//*[@title='Receive Supplies']")
	private WebElement receiveSupplies;

	private By btnShowMoreAction = By.xpath("//a[@title='Show one more action']");

	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[6]/div/a/span[2]/span")
	private WebElement supplyItemsInDropdown;

	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']")
	private WebElement select_desired_supply_loc;

	private By get_supply_distribution_name1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Supply Distribution Name']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']");

	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
	private WebElement supplyLocationInDropdown;

	private By click_to_select_receive_supplies1 = By.xpath("//span[text()='Receive Supplies']");

	private By click_to_select_supply_item1 = By.xpath("//input[@placeholder='Search Supply Items...']");

	private By validate_qty_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Quantity']");

	private By click_to_select_quantity1 = By.xpath("(//input[@class='slds-input'])[2]");

	private By validate_dcf_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Dose Conversion Factor']");

	private By validate_doses_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Doses']");

	private By supply_distribution_to_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Distribution To']");

	private By click_reason1 = By.xpath("//button[@aria-label='Reason for Reception, --None--']");

	private By select_reason1 = By.xpath("//span[@title='Other']");

	private By get_dose_conversion_factor2 = By.xpath("//label[contains(text(),'Dose Conversion Factor')]/parent::div//input");
	private By get_dose_conversion_factor1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Dose Conversion Factor']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

	private By get_remaining_quantity1 = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Remaining Quantity']/../..//LIGHTNING-FORMATTED-NUMBER[@lightning-formattednumber_formattednumber-host='']");

	@FindBy(xpath = "//label[(text()='Dose Conversion Factor')]/..//input[@type='text']")
	private WebElement doseConversionFactorForSingleWastage;

	@FindBy(xpath = "//label[(text()='Doses')]/..//input[@type='text']")
	private WebElement dosesText;

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

	public void checkShowInStockCheckbox() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text() = 'Show trades in stock']/..//span[@part = 'input-checkbox']")).click();
	}

	public void clickSubmitRequisition() throws InterruptedException {
		waitForElementToBeVisible(driver, submitRequisition, 10);
		submitRequisition.click();
	}

	public void clickSaveSubmitRequisition() {
		waitForElementToBeVisible(driver, saveSubmitRequisition, 10);
		WebElement element = driver.findElement(save_Submit_Requisition);
		this.saveSubmitRequisition.click();
	}

	public void clickSupplyLocationsTab() throws InterruptedException {
		Thread.sleep(500);
		By supply_location_tab_path = By.xpath("(//span[@class = 'slds-truncate'])[2]/..");
		waitForElementToBeEnabled(driver, supply_location_tab_path, 10);
		WebElement supply_locations_tab = driver.findElement(supply_location_tab_path);
		if(!supply_locations_tab.getText().equals("Supply Locations")) {
			driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='menuLabel slds-listbox__option-text slds-listbox__option-text_entity' and text() = 'Supply Locations']")).click();
		}
		supply_locations_tab.click();
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
	}

	public void clickOnSupplyLocationCustom(String locationName) throws InterruptedException {
		WebElement customLocation = driver.findElement(By.xpath("(//a[contains(text(),'" + locationName + "')])[1]"));
		click(customLocation);
		Thread.sleep(5000);
	}

	public void clickRequestSupplies() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> request_supplies_btn = null;
		int num = 0;
		//Timeout in seconds
		int timeout = 10;
		long currentTimeStart = System.currentTimeMillis() / 1000;

		while(request_supplies_btn == null || num == 0) {
			request_supplies_btn = driver.findElements(By.xpath("//button[text() = 'Request Supplies'] | //a[@title = 'Request Supplies'] | //button[text() = 'Create Requisition'] | //a[@title = 'Create Requisition']"));
			num = request_supplies_btn.size();
			Thread.sleep(500);
			long currentTime = System.currentTimeMillis() / 1000;
			if(currentTime - currentTimeStart > timeout) {
				throw new NoSuchElementException("Request Supplies Button not found");
			}
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

	public void clickOnSupplyContainerCheckbox(int k) throws InterruptedException {
		tables.getSupplyContainerTable().getRowsMappedToHeadings().get(k).get("Choose a Row\n" +
				"Select All").click();
	}

	public void clickOnSupplyContainerCheckbox(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		String tabindex_before_check =  tables.getSupplyContainerRow(supplyContainer).get("Choose a Row\n" +
				"Select All").getAttribute("tabindex");
		System.out.println("DEBUG: Tabindex attribute before check:" + tabindex_before_check);
		WebElement myCheckbox = tables.getSupplyContainerRow(supplyContainer).get("Choose a Row\n" +
				"Select All");
		scrollTop(myCheckbox);
		Thread.sleep(1000);
		myCheckbox.click();
		Thread.sleep(1000);
		String tabindex_after_check =  tables.getSupplyContainerRow(supplyContainer).get("Choose a Row\n" +
				"Select All").getAttribute("tabindex");
		System.out.println("DEBUG: Tabindex attribute after check:" + tabindex_after_check);
		if(tabindex_before_check.equals(tabindex_after_check)) {
			System.out.println("DEBUG: !!!!!!!!!!!!!!------------------------------------");
			System.out.println("DEBUG: !!!!!!!!!!!!!!Container Checkbox is not checked!!!!!!!!!!!!!");
			System.out.println("DEBUG: !!!!!!!!!!!!!!------------------------------------");
		} else {
			System.out.println("DEBUG: ------------------------------------");
			System.out.println("DEBUG: Container Checkbox is checked");
			System.out.println("DEBUG: ------------------------------------");
		}
	}

	public int getRowsSupplyContainersFromCount() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBePresent(driver, rows_supply_containers_from_count_path_1, 10);
		List<WebElement> rows = driver.findElements(rows_supply_containers_from_count_path_1);
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

	public boolean verifyIsSupplyPageDisplayed() throws InterruptedException {
		int timeout = 30000;
		boolean found = false;
		Instant start = Instant.now();
		Instant end = Instant.now();
		while(!found) {
			try {
				found = driver.findElement(By.xpath(".//span[@title='Health Connect - Supply Console']")).isDisplayed();
				System.out.println("Health Connect found");
				System.out.println(end.toString());
			} catch (NotFoundException ex) {
				end = Instant.now();
				if(Duration.between(start, end).toMillis() > timeout) {
					throw new NotFoundException("Current APP tab not found");
				}
				Thread.sleep(200);
			}
		}
		return found;
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

	public void enterBulkTransferByDosages(List<String> containers, double dose) {
		//////DEBUG//////
		List<List<WebElement>> rows = tables.getContainerTransferTable().getRows();
		for(List<WebElement> row : rows) {
			System.out.println("DEBUG: ---------------------");
			System.out.println("DEBUG: " + row.get(1).getText());
			System.out.println("DEBUG: ---------------------");
		}
		/////////////////
		for (String container : containers) {
			Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container);
			tables.typeDosesIntoTransferRow(supplyContainer, Double.toString(dose));
		}
	}

	public void enterBulkTransferByQuantity(List<String> containers, double quantity) {
		//////DEBUG//////
		List<List<WebElement>> rows = tables.getContainerTransferTable().getRows();
		for(List<WebElement> row : rows) {
			System.out.println("DEBUG: ---------------------");
			System.out.println("DEBUG: " + row.get(1).getText());
			System.out.println("DEBUG: ---------------------");
		}
		/////////////////
		for (String container : containers) {
			Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container);
			tables.typeQtyIntoTransferRow(supplyContainer, Double.toString(quantity));
		}
	}

	public void selectSupplyLocationToFromDropdown(String supplyLocation) throws InterruptedException {
		log(" -- select 'To' " + supplyLocation + "  -");
		Thread.sleep(500);
		By search_supplu_location_path = By.xpath("//input[@placeholder='Search Supply Locations...']");
		waitForElementToBeEnabled(driver, search_supplu_location_path, 10);
		WebElement searchSupplyLocationCombobox = driver.findElement(search_supplu_location_path);
		log(" -- Combobox Supply Location To is found  -");
		searchSupplyLocationCombobox.sendKeys(supplyLocation);
		Thread.sleep(2000);
		log(" -- Start typing into Search Combobox  -");
		By supplyLocationItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title='" + supplyLocation + "']");
		waitForElementToBeEnabled(driver, supplyLocationItemPath, 10);
		WebElement supplyLocationItem = driver.findElement(supplyLocationItemPath);
		scrollTop(supplyLocationItem, true);
		log(" -- Drop down with supply required Supply location appeared  -");
		supplyLocationItem.click();
		//Probable delay when select supply location causing teh javascript error
		Thread.sleep(2000);
		log(" -- Selected Supply location successfully  -");
	}

	@Step
	public SupplyConsolePage clickBulkTransfersModalButton() throws InterruptedException {
		Thread.sleep(500);
		By transfer_btn_path = By.xpath("(//section[@role='dialog']//button[text() = 'Transfer'])");
		waitForElementToBeEnabled(driver, transfer_btn_path, 10);
		WebElement transfer_btn = driver.findElement(transfer_btn_path);
		scrollTop(transfer_btn);
		click(transfer_btn);
		return this;
	}
	@Step
	public void clickBulkTransfersDialogCloseButton() throws InterruptedException {
		Thread.sleep(2000);
		By bulk_dialog_close_button_path = By.xpath("//section[@role='dialog']//button[text()='Close']");
		waitForElementToBeEnabled(driver, bulk_dialog_close_button_path, 10);
		WebElement bulk_dialog_close_button = driver.findElement(bulk_dialog_close_button_path);
		bulk_dialog_close_button.click();
		try {
			Thread.sleep(500);
			WebElement javascript_error_close_button = driver.findElement(By.xpath("//button[@title='Close this window']"));
			javascript_error_close_button.click();
			System.out.println("***WARNING. Probable Javascript performance ***");
		} catch(Exception ex) {
			;
		}
		waitForElementNotToBeVisible(driver, bulk_dialog_close_button_path, 10);
	}

	@Step
	public void clickTransactionsTab() throws InterruptedException {
		Thread.sleep(500);
		By transactions_tab_path = By.xpath("//a[text() = 'Transactions' or @title = 'Transactions']");
		waitForElementToBeEnabled(driver, transactions_tab_path, 10);
		WebElement transactions_tab = driver.findElement(transactions_tab_path);
		scrollTop(transactions_tab);
		transactions_tab.click();
	}

	public int getRowsOutgoingTransactionsCount() throws InterruptedException {
		waitForElementToBeVisible(driver, rows_outgoing_transactions_count_path, 10);
		List<WebElement> rows = rows_outgoing_transactions_count_path.findElements(By.tagName("tr"));
		return (rows.size());
	}

	public String getOutgoingSupplyTransactionId(int kk) throws InterruptedException {
		String supplyTransactionId = tables.getSingleTransactionsTable("Outgoing").getRowsAsStringMappedToHeadings().get(kk).get(SUPPLY_TRANSACTION_NAME);
		return (supplyTransactionId);
	}

	public void clickOnOutgoingTransactions(int kk) throws InterruptedException {
		WebElement transaction = tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(kk).get(SUPPLY_TRANSACTION_NAME);
		waitForElementToBeVisible(driver, transaction, 10);
		transaction.click();
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
	}

	public int getRowsIncomingTransactionsCount() throws InterruptedException {
		waitForElementToBeVisible(driver, rows_incoming_transactions_count_path, 10);
		List<WebElement> rows = rows_incoming_transactions_count_path.findElements(By.tagName("tr"));
		return (rows.size());
	}

	public int getRowsDraftTransactionsCount() throws InterruptedException {
		Thread.sleep(500);
		By draft_transactions_path = By.xpath("//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux']");
		waitForElementToBeLocated(driver, draft_transactions_path, 10);
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
		Thread.sleep(200);
		By draft_transaction_element_path = By.xpath("(//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux'])[" + value + "]");
		waitForElementToBeEnabled(driver, draft_transaction_element_path, 10);
		WebElement draft_transaction_element = driver.findElement(draft_transaction_element_path);
		scrollTop(draft_transaction_element);
		draft_transaction_element.click();
		Thread.sleep(200);
		By transfer_draft_btn_path = By.xpath("//span[contains(text(),'Draft')]/../../../../../../..//button[text() = 'Transfer']");
		waitForElementToBeEnabled(driver, transfer_draft_btn_path, 10);
		WebElement transfer_draft_btn = driver.findElement(transfer_draft_btn_path);
		scrollTop(transfer_draft_btn);
		transfer_draft_btn.click();
		Thread.sleep(200);
		By transfer_transaction_btn_path = By.xpath("//button[text() = 'Transfer Transactions']");
		waitForElementToBeEnabled(driver, transfer_transaction_btn_path, 10);
		WebElement transfer_transaction_btn = driver.findElement(transfer_transaction_btn_path);
		transfer_transaction_btn.click();
	}

	public void clickCheckBoxLatestDraftBulkTransactionsAndConfirmTransfer(int countDraftTransactions, int numberOfRows) throws InterruptedException {

		for(int i=countDraftTransactions; i > (countDraftTransactions-numberOfRows); i--) {
			Thread.sleep(200);
			By draft_transaction_element_path = By.xpath("(//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux'])[" + Integer.toString(i) + "]");
			waitForElementToBeEnabled(driver, draft_transaction_element_path, 10);
			WebElement draft_transaction_element = driver.findElement(draft_transaction_element_path);
			draft_transaction_element.click();
		}

		Thread.sleep(200);
		By transfer_draft_btn_path = By.xpath("//span[contains(text(),'Draft')]/../../../../../../..//button[text() = 'Transfer']");
		waitForElementToBeEnabled(driver, transfer_draft_btn_path, 10);
		WebElement transfer_draft_btn = driver.findElement(transfer_draft_btn_path);
		scrollTop(transfer_draft_btn);
		click(transfer_draft_btn);

		Thread.sleep(200);
		By transfer_transaction_btn_path = By.xpath("//button[contains(text(),'Transfer Transactions')]");
		waitForElementToBeEnabled(driver, transfer_transaction_btn_path, 10);
		WebElement transfer_transaction_btn = driver.findElement(transfer_transaction_btn_path);
		transfer_transaction_btn.click();
		Thread.sleep(500);
		By close_modal_box_path = By.xpath("//div[@role = 'alertdialog']//button[@title = 'Close']");
		try {
			Thread.sleep(500);
			waitForElementToBeLocated(driver, close_modal_box_path, 10);
			driver.findElement(close_modal_box_path).click();
			System.out.println("Success message appered and closed...");
		} catch(Exception ex) {
			System.out.println("No modal box. Continue...");
		}
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
		clickBulkTransfersDialogCloseButton();
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
		WebElement checkbox = tables.getSingleTransactionsTable("Incoming").getRowsMappedToHeadings().get(k).get("Choose a Row\n" +
				"Select All");
		scrollTop(checkbox);
		checkbox.click();
		Thread.sleep(1000);
	}

	public void clickOnOutgoingTransactionsCheckbox(int k) throws InterruptedException {
		WebElement checkbox = tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(k).get("Choose a Row\n" +
				"Select All");
		scrollTop(checkbox);
		checkbox.click();
		Thread.sleep(1000);
	}

	public void clickBulkConfirmIncomingTransfersButton() throws InterruptedException {
		waitForElementToBeLocated(driver, bulk_confirm_incoming_transfers_button_1, 10);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", bulk_confirm_incoming_transfers_button);
		//moveToElement(driver.findElement(bulk_confirm_incoming_transfers_button_1));
		click(bulk_confirm_incoming_transfers_button);
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
	public void successMessageAppear() throws InterruptedException {
		waitForElementToBeVisible(driver, successMessage, 20);
		assertTrue(isElementPresent(successMessage));
		log(" -- Toast success message appears");
		By close_modal_box_path = By.xpath("//div[@role = 'alertdialog']//button[@title = 'Close']");
		try {
			Thread.sleep(500);
			waitForElementToBeLocated(driver, close_modal_box_path, 10);
			driver.findElement(close_modal_box_path).click();
			System.out.println("Success message appered and closed...");
		} catch(Exception ex) {
			System.out.println("No modal box. Continue...");
		}
	}

	public void clickOnContainerDropDownMenu(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		tables.getSupplyLocationActions(supplyContainer);
	}

	public void clickOnFirstContainerDropDownMenu() throws InterruptedException {
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		WebElement action = tables.getSupplyContainerTable().getRowsMappedToHeadings().get(1).get("Actions");
		action.click();
	}
	@Step
	public void selectTransferFromDropDown() throws InterruptedException {
		selectActionFromDropDown("Transfer");
	}

	@Step
	public void selectAdjustmentFromDropDown() throws InterruptedException {
		selectActionFromDropDown("Adjustment");
	}

	@Step
	public void selectWastageFromDropDown() throws InterruptedException {
		selectActionFromDropDown("Wastage");
	}

	public void selectActionFromDropDown(String action) throws InterruptedException {
		Thread.sleep(500);
		By transfer_dropdawn_item_path = By.xpath("//a/span[text() = '" + action + "']");
		waitForElementToBeEnabled(driver, transfer_dropdawn_item_path, 10);
		WebElement transfer_item = driver.findElement(transfer_dropdawn_item_path);
		scrollTop(transfer_item);
		Thread.sleep(500);
		transfer_item.click();
		Thread.sleep(500);
	}

	public Double getValueOfRemainingQuantity() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, get_remaining_quantity1, 10);
		WebElement element = driver.findElement(get_remaining_quantity1);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		String Quantity = element.getText();
		Double doses = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (doses);
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

	public Double getValueOfRemainingDoses(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		double doses = tables.getRemainingDoses(supplyContainer);
		return (doses);
	}

	public Double getValueOfRemainingQty(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		double quantity = tables.getRemainingQty(supplyContainer);
		return (quantity);
	}

	@Step
	public SupplyConsolePage enterTransferDosages(String doses) throws InterruptedException {
		Thread.sleep(500);
		By Doses = By.xpath("//lightning-input//label[text()='Doses']//following-sibling::div/input[@class='slds-input']");
		waitForElementToBePresent(driver, Doses,10);
		moveToElement(driver.findElement(Doses));
		click(Doses);
		type(doses, Doses);
		return this;

	}

	public SupplyConsolePage enterTransferQuantity(String quantity) throws InterruptedException {
		By quantity_path = By.xpath("//lightning-input//label[text()='Quantity']//following-sibling::div/input[@class='slds-input']");
		waitForElementToBePresent(driver, quantity_path,10);
		WebElement quantity_field = driver.findElement(quantity_path);
		scrollTop(quantity_field);
		quantity_field.click();
		quantity_field.sendKeys(quantity);
		return this;
	}

	public double getActualRemainingDoses() throws InterruptedException {
		Thread.sleep(500);
		By actual_remaining_doses_path = By.xpath("//input[@name='HC_Remaining_Measures__c']");
		waitForElementToBeLocated(driver, actual_remaining_doses_path, 10);
		WebElement actualRemainingDoses = driver.findElement(actual_remaining_doses_path);
		String value = getValue(actualRemainingDoses);
		Double actualDosage = Double.parseDouble(value.replaceAll(",", ""));
		return actualDosage;
	}

	public void setDosesAmount(String value) {
		typeIn(dosesText, value);
	}

	public void setQuantityAmount(String quantity) throws InterruptedException {
		By quantity_field_path = By.xpath("//label[(text()='Quantity')]/..//input[@type='text']");
		waitForElementToBeEnabled(driver, quantity_field_path, 10);
		WebElement quantity_field = driver.findElement(quantity_field_path);
		typeIn(quantity_field, quantity);
	}

	public double getDoseConversionFactor() {
		double doseConversionFactor = Double.parseDouble(getValue(doseConversionFactorForSingleWastage));
		return doseConversionFactor;
	}

	public void selectReasonForAdjustmentDropDown() throws InterruptedException {
		By reason_for_adjustment_path = By.xpath("//button[@name='BCH_Reason_for_Adjustment__c']");
		waitForElementToBeEnabled(driver, reason_for_adjustment_path, 10);
		WebElement reasonForAdjustmentFromDropDown = driver.findElement(reason_for_adjustment_path);
		clickUsingJS(reasonForAdjustmentFromDropDown);
		Thread.sleep(500);
		reasonForAdjustmentFromDropDown.sendKeys("a"); //Administered Vaccine
		reasonForAdjustmentFromDropDown.sendKeys(Keys.ENTER);
		String getSelectedReasonFromDropDown = reasonForAdjustmentFromDropDown.getText();
		log("Reason for adjustment is selected: " +getSelectedReasonFromDropDown);
	}

	public void selectReasonForWastageDropDown() throws InterruptedException {
		Thread.sleep(500);
		By reason_for_wastage_path = By.xpath("//button[@name='BCH_Reason_for_Wastage__c']");
		waitForElementToBeEnabled(driver, reason_for_wastage_path, 10);
		WebElement reasonForWastageValueFromDropDown = driver.findElement(reason_for_wastage_path);
		clickUsingJS(reasonForWastageValueFromDropDown);
		Thread.sleep(500);
		reasonForWastageValueFromDropDown.sendKeys("c"); //CCI: Equipment Malfunction
		reasonForWastageValueFromDropDown.sendKeys(Keys.ENTER);
		String getSelectedReasonFromDropDown = reasonForWastageValueFromDropDown.getText();
		log("Reason for wastage is selected: " +getSelectedReasonFromDropDown);
	}

	public void clickBtnWastageAtContainerWastagePopUp() throws InterruptedException {
		Thread.sleep(500);
		By wastage_btn_path = By.xpath("//h2[text()='Container - Wastage']/../..//button[(text()='Wastage')]");
		waitForElementToBeEnabled(driver, wastage_btn_path, 10);
		WebElement btnWastageOnContainerWastagePopUp = driver.findElement(wastage_btn_path);
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

	public HashMap countDosesAndQuantityMap(int numberOfRows) throws InterruptedException {
		Thread.sleep(500);
		By supplyContainerTablePath = By.xpath("//article[@class = 'slds-card']/div/header/div[@class = 'slds-media__body']//span[contains(text(), 'Supply Container')]");
		waitForElementToBePresent(driver, supplyContainerTablePath, 10);
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

	public void enterBulkWastageByDosageWithReason(double amount, String reason) throws InterruptedException {
		enterBulkValuesWithReason("Doses", amount, "Reason For Wastage", reason);
	}

	public void enterBulkWastageByQuantitiesWithReason(double amount, String reason) throws InterruptedException {
		enterBulkValuesWithReason("Quantity", amount, "Reason For Wastage", reason);
	}

	public void enterBulkAdjustmentByDosageWithReason(double amount, String reason) throws InterruptedException {
		enterBulkValuesWithReason("Doses", amount, "Reason For Adjustment\n" +
				"Help", reason);
	}

	public void enterBulkAdjustmentByQuantitiesWithReason(double amount, String reason) throws InterruptedException {
		enterBulkValuesWithReason("Quantity", amount, "Reason For Adjustment\n" +
				"Help", reason);
	}

	public void enterBulkValuesWithReason(String dose_qty_column, double amount, String reason_column, String reason) throws InterruptedException {
		Thread.sleep(500);
		List<Map<String, WebElement>> wastage_table = tables.getContainerAdjustmentWastageTable().getRowsMappedToHeadings();
		for(Map<String, WebElement> row : wastage_table) {
			if(row.get(dose_qty_column).getText().equals(dose_qty_column)) {
				continue;
			}
			row.get(dose_qty_column).findElement(By.xpath(".//input")).sendKeys(Double.toString(amount));
			row.get(reason_column).findElement(By.xpath(".//button")).click();
			Thread.sleep(1000);
			row.get(reason_column).findElement(By.xpath(".//span[@title='" + reason + "']")).click();
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
		waitForElementToBeLocated(driver, select_Confirm_in_dropdown1, 30);
		Thread.sleep(2000);
		select_Confirm_in_dropdown.click();
	}
	@Step
	public void selectCancelInDropDown() throws InterruptedException {
		waitForElementToBeVisible(driver, drdCancel, 10);
		scrollTop(drdCancel);
		click(drdCancel);
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

	public void selectSupplyDistributionFromDropdown(String supplyDistribution) throws InterruptedException {
		By searchSupplyDistributionPath = By.xpath(".//span[contains(text(),'Select an Option')]");
		waitForElementToBePresent(driver, searchSupplyDistributionPath, 10);
		WebElement searchDistributionField = driver.findElement(searchSupplyDistributionPath);
		scrollTop(searchDistributionField);
		searchDistributionField.click();
		Thread.sleep(2000);
		By supplyDistributor = By.xpath("//span[contains(text(),'" + supplyDistribution + "')]");
		waitForElementToBePresent(driver, supplyDistributor, 10);
		WebElement supplyDistributorItem = driver.findElement(supplyDistributor);
		supplyDistributorItem.click();
	}

	public void selectTransferSupplyDistributionFromDropdown(String supplyDistribution) throws InterruptedException {
		By searchSupplyDistributionPath = By.xpath("//span[text() = 'Select Supply Distributor']");
		waitForElementToBePresent(driver, searchSupplyDistributionPath, 10);
		WebElement searchDistributionField = driver.findElement(searchSupplyDistributionPath);
		scrollTop(searchDistributionField);
		searchDistributionField.click();
		Thread.sleep(2000);
		By supplyDistributor = By.xpath("//span[contains(text(),'" + supplyDistribution + "')]");
		waitForElementToBePresent(driver, supplyDistributor, 10);
		WebElement supplyDistributorItem = driver.findElement(supplyDistributor);
		supplyDistributorItem.click();
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

	public void clickSupplyConsoleAppNavigationMenu() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, dropdownMenu, 10);
		this.dropdownMenu.click();
	}

	public void clickBtnReceiveSuppliesCP() throws InterruptedException {
		try {
			click(receiveSupplies);
		} catch(Exception ex) {
			List<WebElement> listOfElements = driver.findElements(btnShowMoreAction);
			System.out.println("--- FOR DEBUG: Trying to Click More Actions button---");
			System.out.println("--- Found " + listOfElements.size() + " More button elements");
			if (listOfElements.size() >= 1) {
				click(btnShowMoreAction);
				System.out.println("--- FOR DEBUG: Clicked More Actions button---");
			} else {
				System.out.println("--- FOR DEBUG: Didn't find More Actions button---");
				System.out.println("--- FOR DEBUG: Try again find More Actions button after 2 seconds---");
				Thread.sleep(2000);
				listOfElements = driver.findElements(btnShowMoreAction);
				System.out.println("--- After Second attempt Found " + listOfElements.size() + " More button elements");
				click(btnShowMoreAction);
			}
			Thread.sleep(1000);
			click(receiveSupplies);
		}
	}

	public void selectSupplyItemsFromDropdown() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, supplyItemsInDropdown, 10);
		this.supplyItemsInDropdown.click();
	}

	public void clickSupplyItemName(String supply_item) throws InterruptedException {
		Thread.sleep(500);
		By supply_item_path = By.xpath("//a[@title='" + supply_item + "']");
		waitForElementToBeLocated(driver, supply_item_path, 10);
		WebElement my_supply_item = driver.findElement(supply_item_path);
		my_supply_item.click();
	}

	public void selectSupplyItemName(String item) throws InterruptedException {
		By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Items']");
		Thread.sleep(500);
		waitForElementToBeEnabled(driver, select_list_view_btn_path, 10);
		WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
		select_list_view_btn.click();
		Thread.sleep(500);
		By all_supply_items_path = By.xpath("//a[@role='option']/span[text() = 'All']");
		waitForElementToBeEnabled(driver, all_supply_items_path, 10);
		WebElement all_supply_items =  driver.findElement(all_supply_items_path);
		all_supply_items.click();
		Thread.sleep(2000);
		By search_field_path = By.xpath("//input[@name = 'HC_Supply_Item__c-search-input']");
		waitForElementToBeEnabled(driver, search_field_path, 10);
		WebElement search_location_field = driver.findElement(search_field_path);
		try {
			WebElement clear_btn = driver.findElement(By.xpath("//input[@name = 'HC_Supply_Item__c-search-input']/..//button[@data-element-id = 'searchClear']"));
			clear_btn.click();
			Thread.sleep(2000);
		} catch(Exception ex) {
			System.out.println("Search field is empty. Continue...");
		}
		search_location_field.sendKeys(item);
		Thread.sleep(1000);
		search_location_field.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		try {
			tables.clickOnSupplyItemTableRow(ImmutableMap.of("Supply Item Name", item));
		} catch (NullPointerException ex) {
			Thread.sleep(2000);
			tables.clickOnSupplyItemTableRow(ImmutableMap.of("Supply Item Name", item));
		}
	}

	public void selectSupplyLocationName() throws InterruptedException {
		waitForElementToBeVisible(driver, select_desired_supply_loc, 10);
		Thread.sleep(2000);
		this.select_desired_supply_loc.click();
	}

	public void selectSupplyLocationName(String location) throws InterruptedException {
		By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Locations']");
		Thread.sleep(500);
		waitForElementToBeEnabled(driver, select_list_view_btn_path, 10);
		WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
		select_list_view_btn.click();
		Thread.sleep(500);
		By active_supply_locations_path = By.xpath("//a/span[text() = 'Active Supply Locations']");
		waitForElementToBeEnabled(driver, active_supply_locations_path, 10);
		WebElement active_supply_locations_item =  driver.findElement(active_supply_locations_path);
		active_supply_locations_item.click();
		Thread.sleep(2000);
		By search_field_path = By.xpath("//input[@name = 'HC_Supply_Location__c-search-input']");
		waitForElementToBeEnabled(driver, search_field_path, 10);
		WebElement search_location_field = driver.findElement(search_field_path);
		try {
			WebElement clear_btn = driver.findElement(By.xpath("//input[@name = 'HC_Supply_Location__c-search-input']/..//button[@data-element-id = 'searchClear']"));
			clear_btn.click();
			Thread.sleep(2000);
		} catch(Exception ex) {
			System.out.println("Search field is empty. Continue...");
		}
		search_location_field.sendKeys(location);
		Thread.sleep(1000);
		search_location_field.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		try {
			tables.clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, location));
			Thread.sleep(2000);
		} catch (NullPointerException ex) {
			Thread.sleep(2000);
			tables.clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, location));
			Thread.sleep(2000);
		}
	}

	public String getSupplyDistributionName() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeLocated(driver, get_supply_distribution_name1, 10);
		WebElement element = driver.findElement(get_supply_distribution_name1);
		element.getText();
		return (element.getText());
	}

	public void selectSupplyLocationFromDropdown() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, supplyLocationInDropdown, 10);
		this.supplyLocationInDropdown.click();
	}

	public void SelectDropDownToClickReceiveSuppliesButton() throws InterruptedException {
		Thread.sleep(500);
		By receive_supplies_btn_path = By.xpath("//BUTTON[@class='slds-button slds-button_icon-border-filled']");
		waitForElementToBeLocated(driver, receive_supplies_btn_path, 10);
		WebElement element = driver.findElement(receive_supplies_btn_path);
		element.click();
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
		Thread.sleep(500);
		By supply_item_label = By.xpath("//label[@class='slds-form-element__label' and text() = 'Supply Item']");
		waitForElementToBeLocated(driver, supply_item_label, 10);
		WebElement element = driver.findElement(supply_item_label);
		return element.getText();
	}

	public void clickSupplyItemTextBox() throws InterruptedException {
		waitForElementToBeLocated(driver, click_to_select_supply_item1, 10);
		WebElement element1 = driver.findElement(click_to_select_supply_item1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
	}

	public void selectSupplyItem(String supplyItem) throws InterruptedException {
		By supply_item_field = By.xpath("//label[@class='slds-form-element__label' and text() = 'Supply Item']/..//input");
		waitForElementToBeLocated(driver, supply_item_field, 10);
		WebElement element = driver.findElement(supply_item_field);
		element.sendKeys(supplyItem);
		Thread.sleep(500);
		By supply_item = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + supplyItem + "']");
		waitForElementToBeLocated(driver, supply_item, 10);
		WebElement search_input = driver.findElement(supply_item);
		search_input.click();
		By close_modal_box_path = By.xpath("//button[@data-aura-class = 'uiButton--modal-closeBtn uiButton' and @title = 'Close this window']");
		try {
			Thread.sleep(500);
			driver.findElement(close_modal_box_path).click();
		} catch(Exception ex) {
			System.out.println("No modal box. Continue...");
		}
	}

	public SupplyConsolePage selectSupplyItemTo(String supplyItem) throws InterruptedException {
		Thread.sleep(2000);
		log(" -- select supply item  -  " + supplyItem);
		selectSupplyTo(searchSupplyItems, supplyItem);
		return this;
	}

	@Step
	private void selectSupplyTo(WebElement element, String location) throws InterruptedException {
		log(" -- select to  -  " + location);
		waitForElementToBeVisible(driver, element, 10);
		element.sendKeys(location);
		Thread.sleep(5000);
		By locationTo = By.xpath("//lightning-base-combobox-formatted-text[contains(@title, '" + location + "')]");
		waitForElementToBePresent(driver, locationTo, 30);
		click(driver.findElement(locationTo));
		waitForElementNotToBeVisible(driver, locationTo, 10);
	}
	@Step
	public SupplyConsolePage selectSupplyLocation(String location) throws InterruptedException {
		log(" -- select to location  -  " + location);
		By search_supply_location_field_path = By.xpath("//input[@placeholder='Search Supply Locations...']");
		waitForElementToBeLocated(driver, search_supply_location_field_path, 10);
		WebElement search_supply_location_field = driver.findElement(search_supply_location_field_path);
		selectSupplyTo(search_supply_location_field, location);
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
		element.clear();
		Thread.sleep(500);
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
		By save_receive_supplies_btn_path = By.xpath("//button[contains(text(),'Save')]");
		waitForElementToBeEnabled(driver, save_receive_supplies_btn_path, 10);
		WebElement save_receive_supplies_btn = driver.findElement(save_receive_supplies_btn_path);
		save_receive_supplies_btn.isDisplayed();
	}

	public void ValidateCancelButtonIsDisplayedOnReceiveSupplies() throws InterruptedException {
		By cancel_receive_supplies_btn_path = By.xpath("(//span[contains(text(),'Cancel')])[2]");
		waitForElementToBeEnabled(driver, cancel_receive_supplies_btn_path, 10);
		WebElement cancel_receive_supplies_btn = driver.findElement(cancel_receive_supplies_btn_path);
		cancel_receive_supplies_btn.isDisplayed();
	}

	public void ClickSaveButton() throws InterruptedException {
		Thread.sleep(500);
		By save_receive_supplies_btn_path = By.xpath("//button[contains(text(),'Save')]");
		waitForElementToBeEnabled(driver, save_receive_supplies_btn_path, 10);
		WebElement save_receive_supplies_btn = driver.findElement(save_receive_supplies_btn_path);
		scrollTop(save_receive_supplies_btn);
		Thread.sleep(500);
		save_receive_supplies_btn.click();
		Thread.sleep(500);
		//Try to find and close the Success Dialogue
		By success_message_close_btn_path = By.xpath("//div[@role='alertdialog']/button[@title='Close']");
		try {
			waitForElementToBeEnabled(driver, success_message_close_btn_path, 5);
			WebElement close_success_dialog = driver.findElement(success_message_close_btn_path);
			close_success_dialog.click();
			Thread.sleep(500);
		} catch(Exception ex) {
			System.out.println("*** Warning *** No Receive Supplies success message Appeared. Continue...");
		}
	}

	public double getDoseConversionFactorReceive() throws InterruptedException {
		WebElement element = driver.findElement(get_dose_conversion_factor1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		return Double.parseDouble(element.getText());
	}
	public double getDoseConversionFactorOnReceive() {
		waitForElementToBePresent(driver, get_dose_conversion_factor2, 10);
		double value = Double.parseDouble(driver.findElement(get_dose_conversion_factor2).getAttribute("value"));
		log(" -- dose conversation factore  -  " + value);
		return value;
	}

	public void selectIncomingSupplyDistributionReceive() throws InterruptedException {
		By supply_distribution_to_path = By.xpath("//button[@name='distributionBox']");
		waitForElementToBeEnabled(driver, supply_distribution_to_path, 10);
		WebElement element = driver.findElement(supply_distribution_to_path);
		scrollTop(element);
		element.click();
		Thread.sleep(500);
		By supply_distributor_path = By.xpath("(//span[contains(text(),'- SDST-000')])[1]");
		waitForElementToBeEnabled(driver, supply_distributor_path, 10);
		WebElement myDistributor = driver.findElement(supply_distributor_path);
		myDistributor.click();
	}

	@Step
	public void cancelIncomingTransfer() throws InterruptedException {
		selectCancelInDropDown();
		cancelTransfer();
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

	public SupplyConsolePage transferToDistributionOnSend(String distribution) throws InterruptedException {
		selectTransferToDistribution(search_incoming_supply_distributor_1_2, distribution);
		return this;
	}

	@Step
	public void draftToDistributionWithinSameClinic(String location, String distribution) throws InterruptedException {
		selectSupplyLocation(location);
		transferToDistributionOnSend(distribution);
		clickBtnSaveAsDraftAtContainerAdjustmentPopUp();
		clickBulkTransfersDialogCloseButton();
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

	public void selectShipped_From(String supply_location) throws InterruptedException {
		Thread.sleep(500);
		By search_supply_location_from_path = By.xpath("//label[text()='Shipped From']/..//input[@class='slds-combobox__input slds-input']");
		waitForElementToBeEnabled(driver, search_supply_location_from_path, 60);
		WebElement search_supply_location_from = driver.findElement(search_supply_location_from_path);
		search_supply_location_from.sendKeys(supply_location);
		Thread.sleep(500);
		By my_location_item_path = By.xpath("//lightning-base-combobox-formatted-text[contains(@title, '" + supply_location + "')]/../..");
		waitForElementToBeEnabled(driver, my_location_item_path, 10);
		WebElement my_location_item = driver.findElement(my_location_item_path);
		my_location_item.click();
	}

	public void clickLineItemCheckBox(int itemNum) throws InterruptedException {
		WebElement element = tables.getRequisitionLineItemsTable().getRowsMappedToHeadings().get(itemNum).get("").findElement(By.xpath(".//span[@part='indicator']"));
		//By check_box = By.xpath("//tbody/tr[7]/td[1]/lightning-input/div/span/label/span[@part='indicator']");
		//waitForElementToBeLocated(driver, check_box, 30);
		//WebElement element = driver.findElement(check_box);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(500);
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
		Thread.sleep(1000);
		By tradeLabelPath = By.xpath("//label[text() = 'Trade']");
		waitForElementToBePresent(driver, tradeLabelPath, 30);
		WebElement trade = driver.findElement(By.xpath("//label[text() = 'Trade']/..//input"));
		scrollTop(trade);
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