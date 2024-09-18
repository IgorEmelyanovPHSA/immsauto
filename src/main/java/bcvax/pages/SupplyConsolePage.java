package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import static constansts.Header.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SupplyConsolePage extends BasePage {
	/*---------Properties-------*/
	@FindBy(xpath = "//label[contains(text(),'Comments')]")
	private WebElement labelComments;

	@FindBy(xpath = "//label[(text()='Dose Conversion Factor')]/..//input[@type='text']")
	private WebElement doseConversionFactorForSingleWastage;

	@FindBy(xpath = "//h2[text()='Container - Adjustment']/../..//button[(text()='Adjustment')]")
	private WebElement btnAdjustmentOnContainerWastagePopUp;

	@FindBy(xpath = "//h2[text() = 'Container - Wastage']/../..//button[text() = 'Wastage']")
	private WebElement btnBulkWastageContainerWastagePage;

	@FindBy(xpath = "//h2[text() = 'Container - Adjustment']/../..//button[text() = 'Adjustment']")
	private WebElement btnBulkAdjustmentContainerAdjustmentPage;

	///////////////////////////////////////////////////////////////////////////////
	//Requisition elements
	///////////////////////////////////////////////////////////////////////////////

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
	public static void clickSupplyLocationsTab(WebDriver driver) throws InterruptedException {
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
	}

	public static boolean verifyIsSupplyPageDisplayed(WebDriver driver) throws InterruptedException {
		int timeout = 30000;
		boolean found = false;
		Instant start = Instant.now();
		Instant end = Instant.now();
		while(!found) {
			try {
				By supply_console_title_path = By.xpath("//span[@title='Health Connect - Supply Console']");
				WebElement supply_console_title = driver.findElement(supply_console_title_path);
				found = supply_console_title.isDisplayed();
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

	public void clickOnOutgoingTransactions(int kk) throws InterruptedException {
		WebElement transaction = tables.getSingleTransactionsTable("Outgoing").getRowsMappedToHeadings().get(kk).get(SUPPLY_TRANSACTION_NAME);
		waitForElementToBeVisible(driver, transaction, 10);
		scrollCenter(transaction);
		Thread.sleep(500);
		transaction.click();
	}

	public static void closeTabsHCA(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, By.xpath("//div[@role='tablist']"), 30);
		List<WebElement> closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
		int count = closeButtons.size();
		int retry_count = 0;
		while(count == 0) {
			Thread.sleep(1000);
			closeButtons = driver.findElements(By.xpath("//div[@role='tablist']//button[@type='button']"));
			retry_count++;
			if(retry_count > 5) {
				break;
			}
		}
		for(WebElement closeTabBtn : closeButtons) {
			try {
				closeTabBtn.click();
				Thread.sleep(2000);
			} catch (ElementNotInteractableException ex) {
				System.out.println(ex.getMessage());
				AlertDialog.closeAllAlerts(driver);
				Thread.sleep(500);
				closeTabBtn.click();
			} catch (StaleElementReferenceException ex) {
				System.out.println(ex.getMessage());
			}
		}
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

	public double getActualRemainingDoses() throws InterruptedException {
		Thread.sleep(500);
		By actual_remaining_doses_path = By.xpath("//input[@name='HC_Remaining_Measures__c']");
		waitForElementToBeLocated(driver, actual_remaining_doses_path, 10);
		WebElement actualRemainingDoses = driver.findElement(actual_remaining_doses_path);
		String value = getValue(actualRemainingDoses);
		Double actualDosage = Double.parseDouble(value.replaceAll(",", ""));
		return actualDosage;
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
		AlertDialog.closeAlert(driver);
	}

	public void clickBtnAdjustmentAtContainerAdjustmentPopUp() throws InterruptedException {
		scrollCenter(btnAdjustmentOnContainerWastagePopUp);
		click(btnAdjustmentOnContainerWastagePopUp);
		Thread.sleep(500);
		AlertDialog.closeAlert(driver);
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
	public void selectCancelInDropDown() throws InterruptedException {
		Thread.sleep(500);
		By drd_cancel_btn_path = By.xpath("//a/span[text() = 'Cancel Transfer']");
		waitForElementToBeEnabled(driver, drd_cancel_btn_path, 10);
		WebElement drd_cance_btn = driver.findElement(drd_cancel_btn_path);
		scrollCenter(driver, drd_cance_btn);
		Thread.sleep(500);
		drd_cance_btn.click();
	}

	public static void clickSupplyConsoleAppNavigationMenu(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By app_navigation_menu_path = By.xpath("//div[@class='oneConsoleNav navexConsoleNav']//button[@title='Show Navigation Menu']");
		WebElement app_navigation_menu = driver.findElement(app_navigation_menu_path);
		waitForElementToBeEnabled(driver, app_navigation_menu_path, 10);
		app_navigation_menu.click();
	}

	public static void selectSupplyItemsFromDropdown(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By supply_items_option_path = By.xpath("//a[@role='menuitem' and @data-itemid='HC_Supply_Item__c']");
		waitForElementToBeEnabled(driver, supply_items_option_path, 10);
		WebElement supply_items_option = driver.findElement(supply_items_option_path);
		supply_items_option.click();
	}

	public static void clickSupplyItemName(WebDriver driver, String supply_item) throws InterruptedException {
		try {
			SupplyConsolePage.switchToTableView(driver);
		} catch(Exception ex) {
			System.out.println("Cannot switch to Table view");
		}
		Thread.sleep(500);
		By supply_item_path = By.xpath("//a[@title='" + supply_item + "']");
		waitForElementToBeLocated(driver, supply_item_path, 10);
		WebElement my_supply_item = driver.findElement(supply_item_path);
		my_supply_item.click();
	}

	public static void switchToTableView(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By view_option_btn_path = By.xpath("//button[@title='Select list display']");
		waitForElementToBeEnabled(driver, view_option_btn_path, 10);
		WebElement view_option_btn = driver.findElement(view_option_btn_path);
		view_option_btn.click();
		Thread.sleep(500);
		By select_table_view_option_path = By.xpath("//lightning-menu-item[@title='Display as table'] | //li[@title='Display as table']");
		waitForElementToBeEnabled(driver, select_table_view_option_path, 10);
		WebElement table_view_option = driver.findElement(select_table_view_option_path);
		table_view_option.click();
		Thread.sleep(500);
	}

	public static void selectSupplyLocationFromDropdown(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		By supply_location_item_path = By.xpath("//a[@role='menuitem' and @data-itemid='HC_Supply_Location__c']");
		waitForElementToBeEnabled(driver, supply_location_item_path, 10);
		WebElement supply_location_item = driver.findElement(supply_location_item_path);
		supply_location_item.click();
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

	public double getDoseConversionFactorReceive() throws InterruptedException {
		By dose_conversion_factor_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Supply_Item__c.HC_Product_Measure__c']//lightning-formatted-number");
		waitForElementToBeEnabled(driver, dose_conversion_factor_path, 10);
		WebElement element = driver.findElement(dose_conversion_factor_path);
		String dose_conversion_factor_string = element.getText();
		Double dose_conversion_factor = Double.parseDouble(dose_conversion_factor_string.replaceAll(",", ""));
		return (dose_conversion_factor);
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
		List<String> all_alerts = AlertDialog.getAllAlertsText(driver);
		Assert.assertTrue(all_alerts.get(0).contains("You have successfully Cancelled the Transaction"));
	}

}