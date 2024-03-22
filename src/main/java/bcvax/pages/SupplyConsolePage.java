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
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SupplyConsolePage extends BasePage {
	/*---------Properties-------*/
	@FindBy(xpath = "//h2[@class='slds-text-heading_medium slds-hyphenate']/../..//button[text() = 'Transfer']")
	private WebElement btnTransferDraftOnContainerTransferPage;

	@FindBy(xpath = "//label[contains(text(),'Comments')]")
	private WebElement labelComments;

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

	@FindBy(xpath = ".//span[contains(text(),'Select an Option')]")
	private WebElement search_incoming_supply_distributor_1_2;

	@FindBy(xpath = ".//input[@name = 'HC_Product_Measure__c']")
	private WebElement get_dose_conversation_factor;
	private By get_dose_conversation_factor1 = By.xpath(".//input[@name = 'HC_Product_Measure__c']");

	@FindBy(xpath = ".//input[@name = 'BCH_Product_Name__c']")
	private WebElement get_trade_name;
	private By get_trade_name1 = By.xpath(".//input[@name = 'BCH_Product_Name__c']");

	@FindBy(xpath = "//span[@class='slds-truncate' and contains(text(),'Edit')]")
	private WebElement btnEditOnTrasactionPage;

	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	private WebElement dropdownMenu;

	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[6]/div/a/span[2]/span")
	private WebElement supplyItemsInDropdown;

	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic']")
	private WebElement select_desired_supply_loc;

	@FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
	private WebElement supplyLocationInDropdown;

	private By click_to_select_receive_supplies1 = By.xpath("//span[text()='Receive Supplies']");

	private By click_to_select_supply_item1 = By.xpath("//input[@placeholder='Search Supply Items...']");

	private By validate_qty_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Quantity']");

	private By click_to_select_quantity1 = By.xpath("(//input[@class='slds-input'])[2]");

	private By validate_dcf_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Dose Conversion Factor']");

	private By validate_doses_field1 = By.xpath("//label[@class='slds-form-element__label slds-no-flex'][text()='Doses']");

	private By supply_distribution_to_field1 = By.xpath("//label[@class='slds-form-element__label'][text()='Supply Distribution To']");

	private By get_dose_conversion_factor2 = By.xpath("//label[contains(text(),'Dose Conversion Factor')]/parent::div//input");

	@FindBy(xpath = "//label[(text()='Dose Conversion Factor')]/..//input[@type='text']")
	private WebElement doseConversionFactorForSingleWastage;

	@FindBy(xpath = "//label[(text()='Doses')]/..//input[@type='text']")
	private WebElement dosesText;

	@FindBy(xpath = "//h2[text()='Container - Adjustment']/../..//button[(text()='Adjustment')]")
	private WebElement btnAdjustmentOnContainerWastagePopUp;

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

	@FindBy(xpath = "//button[text() = 'Ship Requisition'] | //a[@title = 'Ship Requisition']")
	private WebElement shipRequisition;

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
	public void inputRequestedDose(String inputQuantity) throws InterruptedException {
		Thread.sleep(1000);
		WebElement dosesInput = driver.findElement(By.xpath("//table/tbody/tr[1]/td[5]//input"));
		dosesInput.clear();
		dosesInput.sendKeys(inputQuantity);
	}

	public void clickSaveButton() throws InterruptedException {
		By save_btn_path = By.xpath("//button[contains(text(),'Save')]");
		waitForElementToBeEnabled(driver, save_btn_path, 10);
		WebElement save_btn = driver.findElement(save_btn_path);
		scrollCenter(save_btn);
		save_btn.click();
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
		Thread.sleep(500);
		By show_trades_in_stock_ckbox_path = By.xpath("//div[text() = 'Show trades in stock']/..//span[@part = 'input-checkbox']");
		waitForElementToBeEnabled(driver, show_trades_in_stock_ckbox_path, 10);
		WebElement show_trades_in_stock_ckbox = driver.findElement(show_trades_in_stock_ckbox_path);
		show_trades_in_stock_ckbox.click();
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
		try {
			supply_locations_tab.click();
		} catch(ElementClickInterceptedException ex) {
			Thread.sleep(5000);
			supply_locations_tab.click();
		}
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
		scrollCenter(myCheckbox);
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
		return (tables.getSupplyContainerTable().getRows().size() - 1);
	}

	public void clickBulkTransfersButton() throws InterruptedException{
		By transfer_button_path = By.xpath(".//button[text() = 'Transfer']");
		waitForElementToBeEnabled(driver, transfer_button_path, 10);
		WebElement transfer_button = driver.findElement(transfer_button_path);
		scrollIfNeeded(driver, transfer_button);
		transfer_button.click();
	}
	public SupplyConsolePage clickBulkCancelButton() throws InterruptedException {
		By cancel_transfer_button_path = By.xpath("//button[text() = 'Cancel Transfer']");
		By transfer_button_path = By.xpath(".//button[text() = 'Transfer']");
		waitForElementToBeEnabled(driver, cancel_transfer_button_path, 10);
		WebElement bulk_cancel_button = driver.findElement(cancel_transfer_button_path);
		waitForElementToBeEnabled(driver, transfer_button_path, 10);
		scrollCenter(driver, bulk_cancel_button);
		Thread.sleep(1000);
		bulk_cancel_button.click();
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
		By search_supplu_location_path = By.xpath("//label[@class='slds-form-element__label' and text()='Supply Location']/..//input[@class='slds-combobox__input slds-input']");
		waitForElementToBeEnabled(driver, search_supplu_location_path, 30);
		WebElement searchSupplyLocationCombobox = driver.findElement(search_supplu_location_path);
		log(" -- Combobox Supply Location To is found  -");
		searchSupplyLocationCombobox.sendKeys(supplyLocation);
		log(" -- Start typing into Search Combobox  -");
		By supplyLocationItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title='" + supplyLocation + "']");
		waitForElementToBeEnabled(driver, supplyLocationItemPath, 10);
		WebElement supplyLocationItem = driver.findElement(supplyLocationItemPath);
		scrollCenter(supplyLocationItem);
		log(" -- Drop down with supply required Supply location appeared  -");
		try {
			supplyLocationItem.click();
		} catch(Exception ex) {
			searchSupplyLocationCombobox.click();
			supplyLocationItem.click();
		}
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
		scrollCenter(transfer_btn);
		transfer_btn.click();
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
		Thread.sleep(2000);
	}

	@Step
	public void clickTransactionsTab() throws InterruptedException {
		Thread.sleep(500);
		By transactions_tab_path = By.xpath("//a[text() = 'Transactions' or @title = 'Transactions']");
		waitForElementToBeEnabled(driver, transactions_tab_path, 10);
		WebElement transactions_tab = driver.findElement(transactions_tab_path);
		scrollCenter(transactions_tab);
		Thread.sleep(500);
		for(int i = 0; i < 10; i++) {
			try {
				transactions_tab.click();
				break;
			} catch (ElementClickInterceptedException ex) {
				System.out.println("Exception: " + ex.getMessage());
				Thread.sleep(1000);
			}
		}
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
		scrollCenter(transaction);
		Thread.sleep(500);
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
		Thread.sleep(500);
		By draft_transaction_element_path = By.xpath("(//span[contains(text(),'Draft')]/../../../../..//span[@class='slds-checkbox_faux'])[" + value + "]");
		waitForElementToBeEnabled(driver, draft_transaction_element_path, 10);
		WebElement draft_transaction_element = driver.findElement(draft_transaction_element_path);
		scrollCenter(draft_transaction_element);
		draft_transaction_element.click();
		Thread.sleep(500);
		By transfer_draft_btn_path = By.xpath("//span[contains(text(),'Draft')]/../../../../../../..//button[text() = 'Transfer']");
		waitForElementToBeEnabled(driver, transfer_draft_btn_path, 10);
		WebElement transfer_draft_btn = driver.findElement(transfer_draft_btn_path);
		scrollCenter(transfer_draft_btn);
		transfer_draft_btn.click();
		Thread.sleep(500);
		By transfer_transaction_btn_path = By.xpath("//button[text() = 'Transfer Transactions']");
		waitForElementToBeEnabled(driver, transfer_transaction_btn_path, 10);
		WebElement transfer_transaction_btn = driver.findElement(transfer_transaction_btn_path);
		transfer_transaction_btn.click();
		try {
			clickCloseAlert();
			Thread.sleep(500);
		} catch(Exception ex) {
			System.out.println("Success Dialog not found. Continue...");
		}
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
		scrollCenter(transfer_draft_btn);
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

	public void clickOnIncomingTransactionsCheckbox(int k) throws InterruptedException {
		WebElement checkbox = tables.getSingleTransactionsTable("Incoming").getRowsMappedToHeadings().get(k).get("Choose a Row\n" +
				"Select All");
		scrollCenter(checkbox);
		checkbox.click();
		Thread.sleep(1000);
	}

	public void clickOnOutgoingTransactionsCheckbox(int k) throws InterruptedException {
		WebElement checkbox = tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(k).get("Choose a Row\n" +
				"Select All");
		scrollCenter(checkbox);
		checkbox.click();
		Thread.sleep(1000);
	}

	public void clickBulkConfirmIncomingTransfersButton() throws InterruptedException {
		Thread.sleep(500);
		By bulk_confirm_incoming_transfers_button_path = By.xpath(".//button[text() = 'Confirm Transfer']");
		waitForElementToBeEnabled(driver, bulk_confirm_incoming_transfers_button_path, 10);
		WebElement bulk_confirm_incoming_transfers_button = driver.findElement(bulk_confirm_incoming_transfers_button_path);
		scrollCenter(bulk_confirm_incoming_transfers_button);
		Thread.sleep(500);
		bulk_confirm_incoming_transfers_button.click();
	}

	public void selectIncomingSupplyDistribution(String distribution) throws InterruptedException {
		Thread.sleep(500);
		By search_incoming_supply_distributor_path = By.xpath(".//span[text() = 'Select Supply Distributor']");
		waitForElementToBeEnabled(driver, search_incoming_supply_distributor_path, 10);
		WebElement search_incoming_supply_distributor = driver.findElement(search_incoming_supply_distributor_path);
		scrollCenter(search_incoming_supply_distributor);
		Thread.sleep(500);
		search_incoming_supply_distributor.click();
		Thread.sleep(500);
		By select_incoming_supply_distributor_path = By.xpath("//span[contains(text(), '" + distribution + "')]");
		waitForElementToBeEnabled(driver, select_incoming_supply_distributor_path, 10);
		WebElement select_incoming_supply_distributor = driver.findElement(select_incoming_supply_distributor_path);
		select_incoming_supply_distributor.click();
	}


	@Step
	public void clickOnConfirmModalIncomingTransactionButton() throws InterruptedException {
		Thread.sleep(500);
		By confirm_incoming_transfers_modal_button_path = By.xpath("//button[text() = 'Confirm Transaction']");
		waitForElementToBeEnabled(driver, confirm_incoming_transfers_modal_button_path, 10);
		WebElement confirm_incoming_transfers_modal_button = driver.findElement(confirm_incoming_transfers_modal_button_path);
		moveToElement(confirm_incoming_transfers_modal_button);
		//handle issue when popup not fully loaded and button is partially hidden
		if (isElementPresent(labelComments)) {
			labelComments.click();
		}
		Thread.sleep(500);
		confirm_incoming_transfers_modal_button.click();
	}

	public void clickOnButtonInModalTransaction(WebElement element) throws InterruptedException {
		waitForElementToBeVisible(driver, element, 10);
		scrollCenter(element);
		click(element);
	}
	@Step
	public void successMessageAppear() throws InterruptedException {
		By alert_popup_path = By.xpath("//div[@role='alertdialog']");
		waitForElementToBeEnabled(driver, alert_popup_path, 10);
		WebElement alert_popup = driver.findElement(alert_popup_path);
		String success_message = alert_popup.findElement(By.xpath(".//div[@class='toastTitle slds-text-heading--small']")).getText();
		assertEquals(success_message, "Success!");
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
		boolean menu_visible = false;
		Thread.sleep(1000);
		for (int i = 0; i < 10; i++) {
			try {
				menu_visible = driver.findElement(By.xpath("//div[@part='overlay dropdown']")).isDisplayed();
				if(menu_visible) {
					break;
				} else {
					action.click();
					Thread.sleep(1000);
				}
			} catch (Exception ex) {
				menu_visible = false;
			}
		}
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
		scrollCenter(transfer_item);
		Thread.sleep(500);
		try {
			transfer_item.click();
		} catch(ElementNotInteractableException ex) {
			Thread.sleep(500);
			transfer_item.click();
		}
		Thread.sleep(500);
	}

	public Double getValueOfRemainingQuantity() throws InterruptedException {
		By remaining_quantity_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Supply_Item__c.HC_Remaining_Quantity__c']//lightning-formatted-number");
		waitForElementToBeEnabled(driver, remaining_quantity_path, 10);
		WebElement element = driver.findElement(remaining_quantity_path);
		String Quantity = element.getText();
		Double doses = Double.parseDouble(Quantity.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses() throws InterruptedException {
		By remaining_dose_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Supply_Item__c.HC_Remaining_Measures__c']//lightning-formatted-number");
		waitForElementToBeEnabled(driver, remaining_dose_path, 10);
		WebElement element = driver.findElement(remaining_dose_path);
		String Doses = element.getText();
		Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
		return (doses);
	}

	public Double getValueOfRemainingDoses(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		double doses = 0;
		int tries = 0;
		while(tries < 10) {
			try {
				doses = tables.getRemainingDoses(supplyContainer);
				break;
			} catch (Exception ex) {
				driver.navigate().refresh();
				Thread.sleep(1000);
				tries++;
			}
		}
		return (doses);
	}

	public Double getValueOfRemainingQty(String container, String distribution) throws InterruptedException {
		Map<String,String> supplyContainer = ImmutableMap.of(SUPPLY_CONTAINER_NAME, container, SUPPLY_DISTRIBUTION_DESCRIPTION, distribution);
		double quantity = 0;
		int tries = 0;
		while(tries < 10) {
			try {
				quantity = tables.getRemainingQty(supplyContainer);
				break;
			} catch (Exception ex) {
				driver.navigate().refresh();
				Thread.sleep(1000);
				tries++;
			}
		}

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
		scrollCenter(quantity_field);
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
		quantity_field.clear();
		Thread.sleep(500);
		quantity_field.sendKeys(quantity);
		Thread.sleep(2000);
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
		scrollCenter(btnWastageOnContainerWastagePopUp);
		click(btnWastageOnContainerWastagePopUp);
		Thread.sleep(500);
		clickCloseAlert();
	}

	public void clickBtnAdjustmentAtContainerAdjustmentPopUp() throws InterruptedException {
		scrollCenter(btnAdjustmentOnContainerWastagePopUp);
		click(btnAdjustmentOnContainerWastagePopUp);
		Thread.sleep(500);
		clickCloseAlert();
	}

	public void clickBtnSaveAsDraftAtContainerAdjustmentPopUp() throws InterruptedException {
		Thread.sleep(500);
		By save_draft_btn_path = By.xpath("//button[text()='Save as draft']");
		waitForElementToBeEnabled(driver, save_draft_btn_path, 10);
		WebElement btnSaveAsDraftOnContainerWastagePopUp = driver.findElement(save_draft_btn_path);
		scrollIfNeeded(driver, btnSaveAsDraftOnContainerWastagePopUp);
		Thread.sleep(500);
		btnSaveAsDraftOnContainerWastagePopUp.click();
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
		By my_lot_path = By.xpath("//lightning-base-formatted-text/..//*[contains(text(),'"+ lot +"')]");
		try {
			waitForElementToBeLocated(driver, my_lot_path, 10);
			return true;
		} catch (Exception ex) {
			return false;
		}
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
			WebElement quantity_field = row.get(dose_qty_column).findElement(By.xpath(".//input"));
			quantity_field.sendKeys(Double.toString(amount));
			WebElement reason_btn = row.get(reason_column).findElement(By.xpath(".//button"));
			reason_btn.click();
			Thread.sleep(1000);
			WebElement reason_link = row.get(reason_column).findElement(By.xpath(".//span[@title='" + reason + "']"));
			reason_link.click();
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

	public void clickOnDraftTransactionsDropDownMenu(int j) throws InterruptedException {
		tables.getSingleTransactionsTable("Draft").getRowsMappedToHeadings().get(j).get("Actions").click();
	}

	@Step
	public void selectConfirmIncomingDropDown() throws InterruptedException {
		Thread.sleep(500);
		By select_confirm_in_dropdown_path = By.xpath("//span[text() = 'Confirm']/..");
		waitForElementToBeEnabled(driver, select_confirm_in_dropdown_path, 10);
		WebElement select_confirm_in_dropdown = driver.findElement(select_confirm_in_dropdown_path);
		scrollCenter(driver, select_confirm_in_dropdown);
		Thread.sleep(500);
		select_confirm_in_dropdown.click();
	}
	@Step
	public void selectCancelInDropDown() throws InterruptedException {
		Thread.sleep(500);
		By drd_cancel_btn_path = By.xpath("//a/span[text() = 'Cancel Transfer']");
		waitForElementToBeEnabled(driver, drd_cancel_btn_path, 10);
		WebElement drd_cance_btn = driver.findElement(drd_cancel_btn_path);
		scrollCenter(driver, drd_cance_btn);
		Thread.sleep(500);
		drd_cance_btn.click();
	}

	public void clickOnRelatedItemTab() throws InterruptedException {
		Thread.sleep(500);
		By related_tab_path = By.xpath("//a[text() = 'Related'] | //span[text() = 'Related Items']");
		waitForElementToBeEnabled(driver, related_tab_path, 10);
		WebElement related_tab = driver.findElement(related_tab_path);
		scrollCenter(driver, related_tab);
		Thread.sleep(500);
		related_tab.click();
		Thread.sleep(2000);
	}

	public void selectSupplyDistributionFromDropdown(String supplyDistribution) throws InterruptedException {
		By searchSupplyDistributionPath = By.xpath(".//span[contains(text(),'Select an Option')]");
		waitForElementToBePresent(driver, searchSupplyDistributionPath, 10);
		WebElement searchDistributionField = driver.findElement(searchSupplyDistributionPath);
		scrollCenter(searchDistributionField);
		searchDistributionField.click();
		Thread.sleep(2000);
		By supplyDistributor = By.xpath("//span[contains(text(),'" + supplyDistribution + "')]");
		waitForElementToBePresent(driver, supplyDistributor, 10);
		WebElement supplyDistributorItem = driver.findElement(supplyDistributor);
		scrollCenter(driver, supplyDistributorItem);
		supplyDistributorItem.click();
	}

	public void selectTransferSupplyDistributionFromDropdown(String supplyDistribution) throws InterruptedException {
		By searchSupplyDistributionPath = By.xpath("//span[text() = 'Select Supply Distributor']");
		waitForElementToBePresent(driver, searchSupplyDistributionPath, 10);
		WebElement searchDistributionField = driver.findElement(searchSupplyDistributionPath);
		scrollCenter(searchDistributionField);
		searchDistributionField.click();
		Thread.sleep(2000);
		By supplyDistributor = By.xpath("//span[contains(text(),'" + supplyDistribution + "')]");
		waitForElementToBePresent(driver, supplyDistributor, 10);
		WebElement supplyDistributorItem = driver.findElement(supplyDistributor);
		supplyDistributorItem.click();
	}

	public void clickSupplyConsoleAppNavigationMenu() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, dropdownMenu, 10);
		this.dropdownMenu.click();
	}

	public void clickBtnReceiveSuppliesCP() throws InterruptedException {
		Thread.sleep(500);
		By receive_supplies_btn_path = By.xpath("//*[@title='Receive Supplies']");
		try {
			WebElement receive_supplies_btn = driver.findElement(receive_supplies_btn_path);
			receive_supplies_btn.click();
		} catch(Exception ex) {
			By show_more_action_btn_path = By.xpath("//li[contains(@data-target-reveals, 'sfdc:QuickAction.HC_Supply_Location__c.HC_Receive_Supplies')]//a | //lightning-button-menu[contains(@data-target-reveals, 'sfdc:QuickAction.HC_Supply_Location__c.HC_Receive_Supplies')]//button");
			List<WebElement> listOfElements = driver.findElements(show_more_action_btn_path);
			System.out.println("--- FOR DEBUG: Trying to Click More Actions button---");
			System.out.println("--- Found " + listOfElements.size() + " More button elements");
			if (listOfElements.size() >= 1) {
				listOfElements.get(0).click();
				System.out.println("--- FOR DEBUG: Clicked More Actions button---");
			} else {
				System.out.println("--- FOR DEBUG: Didn't find More Actions button---");
				for(int i = 0; i < 10; i++) {
					System.out.println("--- FOR DEBUG: Try again find More Actions button after 1 seconds---");
					Thread.sleep(1000);
					listOfElements = driver.findElements(show_more_action_btn_path);
					System.out.println("--- After " + i + " attempt Found " + listOfElements.size() + " More button elements");
					if(listOfElements.size() > 0) {
						break;
					}
				}
				listOfElements.get(0).click();
			}
			Thread.sleep(1000);
			WebElement receive_supplies_btn = driver.findElement(receive_supplies_btn_path);
			receive_supplies_btn.click();
		}
	}

	public void selectSupplyItemsFromDropdown() throws InterruptedException {
		Thread.sleep(500);
		waitForElementToBeVisible(driver, supplyItemsInDropdown, 10);
		this.supplyItemsInDropdown.click();
	}

	public void clickSupplyItemName(String supply_item) throws InterruptedException {
		try {
			switchToTableView();
		} catch(Exception ex) {
			System.out.println("Cannot switch to Table view");
		}
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
		try {
			switchToTableView();
		} catch (Exception ex) {
			System.out.println("Cannot switch the view");
		}
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

	public void switchToTableView() throws InterruptedException {
		Thread.sleep(500);
		By view_option_btn_path = By.xpath("//div[@class='test-listviewdisplayswitcher forceListViewManagerDisplaySwitcher']");
		waitForElementToBeEnabled(driver, view_option_btn_path, 10);
		By selected_view_option_path = By.xpath("//div[@class='test-listviewdisplayswitcher forceListViewManagerDisplaySwitcher']/div");
		WebElement selected_view_option = driver.findElement(selected_view_option_path);
		String selected_view_option_title = selected_view_option.getAttribute("title");
		if(!selected_view_option_title.equals("Display as Table")) {
			WebElement view_switch_btn = driver.findElement(view_option_btn_path);
			view_switch_btn.click();
			Thread.sleep(500);
			By select_table_view_option_path = By.xpath("//div[@class='test-listviewdisplayswitcher forceListViewManagerDisplaySwitcher']//li[@role='presentation'][@title='Display as table' or @title='Table']/a");
			waitForElementToBeEnabled(driver, view_option_btn_path, 10);
			WebElement table_view_option = driver.findElement(select_table_view_option_path);
			table_view_option.click();
			Thread.sleep(500);
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
		try {
			switchToTableView();
		} catch(Exception ex) {
			System.out.println("---Cannot switch the view---");
		}
		Thread.sleep(1000);
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
			System.out.println("Couldn't click on Supply Location " + SUPPLY_LOCATION_NAME + " and location " + location);
			Thread.sleep(2000);
			tables.clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, location));
			Thread.sleep(2000);
		}
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
		Thread.sleep(500);
		log(" -- select supply item  -  " + supplyItem);
		By search_supply_item_path = By.xpath("//input[contains(@placeholder,'Search Supply Items')]");
		waitForElementToBeEnabled(driver, search_supply_item_path, 10);
		WebElement search_supply_items = driver.findElement(search_supply_item_path);
		selectSupplyTo(search_supply_items, supplyItem);
		return this;
	}

	@Step
	private void selectSupplyTo(WebElement element, String location) throws InterruptedException {
		log(" -- select to  -  " + location);
		waitForElementToBeVisible(driver, element, 10);
		element.sendKeys(location);
		Thread.sleep(5000);
		By locationTo = By.xpath("//lightning-base-combobox-formatted-text[contains(@title, '" + location + "')]");
		waitForElementToBeEnabled(driver, locationTo, 30);
		WebElement my_location = driver.findElement(locationTo);
		scrollCenter(driver, my_location);
		Thread.sleep(500);
		my_location.click();
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
		Thread.sleep(500);
		By select_reason_path = By.xpath("//button[@name='BCH_Reason_for_Reception__c']");
		waitForElementToBeEnabled(driver, select_reason_path, 10);
		WebElement select_reason_btn = driver.findElement(select_reason_path);
		select_reason_btn.click();
		Thread.sleep(500);
		By select_other_reason_path = By.xpath("//lightning-base-combobox-item[@data-value='Other']");
		waitForElementToBeEnabled(driver, select_other_reason_path, 10);
		WebElement reason_other_item = driver.findElement(select_other_reason_path);
		reason_other_item.click();
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
		scrollCenter(save_receive_supplies_btn);
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
		By dose_conversion_factor_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Supply_Item__c.HC_Product_Measure__c']//lightning-formatted-number");
		waitForElementToBeEnabled(driver, dose_conversion_factor_path, 10);
		WebElement element = driver.findElement(dose_conversion_factor_path);
		String dose_conversion_factor_string = element.getText();
		Double dose_conversion_factor = Double.parseDouble(dose_conversion_factor_string.replaceAll(",", ""));
		return (dose_conversion_factor);
	}
	public double getDoseConversionFactorOnReceive() {
		waitForElementToBePresent(driver, get_dose_conversion_factor2, 10);
		double value = Double.parseDouble(driver.findElement(get_dose_conversion_factor2).getAttribute("value"));
		log(" -- dose conversation factore  -  " + value);
		return value;
	}

	public void selectIncomingSupplyDistributionReceive() throws InterruptedException {
		Thread.sleep(500);
		By supply_distribution_to_path = By.xpath("//button[@name='distributionBox']");
		waitForElementToBeEnabled(driver, supply_distribution_to_path, 5);
		WebElement element = driver.findElement(supply_distribution_to_path);
		scrollCenter(element);
		element.click();
		Thread.sleep(500);
		By supply_distributor_path = By.xpath("(//span[contains(text(),'- SDST-000')])[1]");
		try {
			waitForElementToBeEnabled(driver, supply_distributor_path, 5);
			WebElement myDistributor = driver.findElement(supply_distributor_path);
			myDistributor.click();
		} catch(Exception ex) {
			element.click();
			Thread.sleep(500);
			waitForElementToBeEnabled(driver, supply_distributor_path, 10);
			WebElement myDistributor = driver.findElement(supply_distributor_path);
			myDistributor.click();
		}
	}

	@Step
	public void cancelIncomingTransfer() throws InterruptedException {
		selectCancelInDropDown();
		cancelTransfer();
	}

	@Step
	public void cancelTransfer() throws InterruptedException {
		By cancel_transaction_btn_path = By.xpath("//button[text() = 'Cancel transactions' or text() = 'Cancel Transaction']");
		waitForElementToBeEnabled(driver, cancel_transaction_btn_path, 10);
		WebElement cancel_transaction_btn = driver.findElement(cancel_transaction_btn_path);
		clickOnButtonInModalTransaction(cancel_transaction_btn);
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
		scrollCenter(driver.findElement(locationTo));
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
		waitForElementToBeEnabled(driver, my_location_item_path, 20);
		WebElement my_location_item = driver.findElement(my_location_item_path);
		my_location_item.click();
	}

	public void clickLineItemCheckBox(int itemNum) throws InterruptedException {
		WebElement element = tables.getRequisitionLineItemsTable().getRowsMappedToHeadings().get(itemNum).get("").findElement(By.xpath(".//span[@part='indicator']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(500);
		element.click();
	}

	public void clickEditExpectedDeliveryDate() throws InterruptedException {
		Thread.sleep(500);
		driver.navigate().refresh();
		Thread.sleep(500);
		By edit_expected_delivery_date_btn_path = By.xpath("//button[@title='Edit Expected Delivery Date']");
		waitForElementToBeEnabled(driver, edit_expected_delivery_date_btn_path, 10);
		WebElement edit_expected_delivery_date_btn = driver.findElement(edit_expected_delivery_date_btn_path);
		edit_expected_delivery_date_btn.click();
	}

	public void inputExpectedDate() throws InterruptedException {
		Thread.sleep(500);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String tomorrowAsString = dateFormat.format(tomorrow);
		By expected_delivery_date_field_path = By.xpath("//*[text()='Expected Delivery Date']/../..//input");
		waitForElementToBeEnabled(driver, expected_delivery_date_field_path, 10);
		WebElement expected_delivery_date_field = driver.findElement(expected_delivery_date_field_path);
		expected_delivery_date_field.sendKeys(tomorrowAsString);
		Thread.sleep(500);
		expected_delivery_date_field.sendKeys(Keys.ENTER);
	}

	public void clickSaveExpectedDeliveryDate() throws InterruptedException {
		Thread.sleep(500);
		By save_expected_delivery_date_btn_path = By.xpath("//button[text() = 'Save'] | //button[@title = 'Save']");
		waitForElementToBeEnabled(driver, save_expected_delivery_date_btn_path, 10);
		WebElement save_expected_delivery_date_btn = driver.findElement(save_expected_delivery_date_btn_path);
		save_expected_delivery_date_btn.click();
	}

	public void clickApproveRequisition() throws InterruptedException {
		Thread.sleep(500);
		By approve_requisition_btn_path = By.xpath("//button[text() = 'Approve Requisition'] | //a[@title = 'Approve Requisition']");
		waitForElementToBeEnabled(driver, approve_requisition_btn_path, 10);
		WebElement approve_requisition_btn = driver.findElement(approve_requisition_btn_path);
		scrollCenter(approve_requisition_btn);
		approve_requisition_btn.click();
	}

	public void clickSaveApprovedRequisition() throws InterruptedException {
		Thread.sleep(500);
		By save_approved_requisition_btn_path = By.xpath("//button[text() = 'Save']");
		waitForElementToBeEnabled(driver, save_approved_requisition_btn_path, 10);
		WebElement save_approved_requisition_btn = driver.findElement(save_approved_requisition_btn_path);
		scrollCenter(save_approved_requisition_btn);
		save_approved_requisition_btn.click();
	}

	public void enterApprovedDose(String inputDose) throws InterruptedException {
		Thread.sleep(1000);
		By tradeLabelPath = By.xpath("//label[text() = 'Trade']");
		waitForElementToBePresent(driver, tradeLabelPath, 30);
		WebElement trade = driver.findElement(By.xpath("//label[text() = 'Trade']/..//input"));
		scrollCenter(trade);
		String tradeValue = trade.getAttribute("value");
		WebElement approveDoseField = driver.findElements(By.xpath("//div[contains(text(), '" + tradeValue + "')]/../../td[7]//input")).get(0);
		approveDoseField.sendKeys(inputDose);
	}

	public void clickShipRequisition() throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, shipRequisition, 30);
		shipRequisition.click();
	}

	public void clickSaveShipRequisition() throws InterruptedException {
		Thread.sleep(500);
		By save_ship_requisition_btn_path = By.xpath("//button[contains(text(),'Save')]");
		waitForElementToBeEnabled(driver, save_ship_requisition_btn_path, 10);
		WebElement save_ship_requisition_btn = driver.findElement(save_ship_requisition_btn_path);
		scrollCenter(save_ship_requisition_btn);
		save_ship_requisition_btn.click();
	}

	public String ShipRequisition() throws InterruptedException {
		Thread.sleep(500);
		By verified_label_path = By.xpath("//h2[contains(text(),'Ship Requisition')]");
		waitForElementToBeEnabled(driver, verified_label_path, 10);
		WebElement verified_label = driver.findElement(verified_label_path);
		return verified_label.getText();
	}

	public void clickReceiveRequestBtn() throws InterruptedException {
		Thread.sleep(500);
		By receive_request_btn_path = By.xpath("//button[text() = 'Receive Requisition'] | //a[@title = 'Receive Requisition']");
		waitForElementToBeEnabled(driver, receive_request_btn_path, 30);
		WebElement receive_request_btn = driver.findElement(receive_request_btn_path);
		receive_request_btn.click();
	}

	public void clickReturnBtn() throws InterruptedException {
		Thread.sleep(500);
		By return_btn_path = By.xpath("//button[@name='HC_Supply_Location__c.Return'] "
				.concat(" | ")
				.concat("//li[@data-target-selection-name='sfdc:QuickAction.HC_Supply_Location__c.Return']"));
		waitForElementToBeEnabled(driver, return_btn_path, 10);
		WebElement return_btn = driver.findElement(return_btn_path);
		return_btn.click();
	}

	public void clickReturnsTab() throws InterruptedException {
		Thread.sleep(500);
		By return_tab_path = By.xpath("//a[@nclass='slds-tabs_default__link' and @data-label='Returns']");
		waitForElementToBeEnabled(driver, return_tab_path, 10);
		WebElement return_tab = driver.findElement(return_tab_path);
		return_tab.click();
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
		catch(ElementNotInteractableException ex) {
			System.out.println("***DEBUG*** Element not clickable. Wait 1 sec and try again");
			Thread.sleep(1000);
			alert_close_btn = driver.findElement(alert_close_btn_path);
			alert_close_btn.click();
		}
	}
}