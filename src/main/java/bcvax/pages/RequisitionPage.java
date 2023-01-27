package bcvax.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RequisitionPage extends BasePage {
	
	@FindBy(xpath = ".//span[@title='Health Connect - Supply Console']")
	private WebElement supply_console_page_displayed;
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	private WebElement dropdownMenu;
	
	@FindBy(xpath = "//body/div[4]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/section[1]/div[1]/div[1]/ul[1]/li[7]/div[1]/a[1]/span[2]/span[1]")
	private WebElement supplyLocationInDropdown;
	
	@FindBy(xpath = ".//input[@class='slds-combobox__input slds-input']")
	private WebElement SupplyLocations;
	
	
	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Age 12 and Above - Abbotsford - Abby Pharmacy']")
	
	private WebElement supply_supply_location_1;
	
	
	@FindBy(xpath = "//*[contains(text(),\"Request Supplies\")]")
	private WebElement request_supplies;
	
	@FindBy(xpath = "//input[@placeholder=\"Search Supply Locations...\"]")
	private WebElement search_supply_location_from;
	private By search_supply_location_from_ = By.xpath("//input[@placeholder=\"Search Supply Locations...\"]");
	
	@FindBy(xpath = "//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text")
	private WebElement select_supply_location_from;
	private By select_supply_location_from_ = By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']//lightning-base-combobox-formatted-text");
	
	@FindBy(xpath = "//input[@class=\"slds-input\"]")
	private WebElement requestedDeliveryDate;
	
	@FindBy(xpath = "//input[@name=\"BCH_Requested_Delivery_Date__c\"]")
	private WebElement inputDate;
	
	@FindBy(xpath = "//button[contains(text(),\"Next\")]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//tbody/tr[10]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]")
	private WebElement checkBox;
	
	@FindBy(xpath = "(//div[@class=\"slds-form-element__control slds-grow\"]//input)[2]")
	private WebElement requestedDose;
	
	@FindBy(xpath = "//button[contains(text(),\"Save\")]")
	private WebElement saveButton;
	
	@FindBy(xpath = "//button[contains(text(),'Submit Requisition')]")
	private WebElement submitRequisition;
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	private WebElement saveSubmitRequisition;
	
	@FindBy(xpath = "//button[@title='Edit Expected Delivery Date']")
	private WebElement editExpectedDeliveryDate;
	
	@FindBy(xpath = "//input[@name ='BCH_Expected_Delivery_Date__c']")
	private WebElement expectedDeliveryDateCalendar;
	
	@FindBy(xpath = "//input[@name=\"BCH_Expected_Delivery_Date__c\"]")
	private WebElement inputExpectedDate;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveExpectedDeliveryDate;
	
	@FindBy(xpath = "//button[contains(text(),'Approve Requisition')]")
	private WebElement approveRequisition;
	
	@FindBy(xpath = "//tbody//tr//td[7]//div//input")
	private WebElement ApprovedDose;
	
	@FindBy(xpath = "//button[contains(text(),\"Save\")]")
	private WebElement saveApprovedRequisition;
	private By saveApprovedRequisition_ = By.xpath("//button[contains(text(),\"Save\")]");

	@FindBy(xpath = "//button[contains(text(),'Ship Requisition')]")
	private WebElement shipRequisition;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveShipRequisition;
	
	@FindBy(xpath = "//input[@class ='default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']")
	private WebElement distributionName;
	private By distributionName2 = By.xpath("//input[@class ='default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']");
	
	@FindBy(xpath = "//input[@placeholder='Search Supply Distributions...']")
	private WebElement click_on_search_supply_distributions_to_component;
	private By click_on_search_supply_distributions_to_component_ = By.xpath("//input[@placeholder='Search Supply Distributions...']");

	@FindBy(xpath = "(//div[@class='primaryLabel slds-truncate slds-lookup__result-text'])[1]")
	private WebElement select_supply_distributions_to;
	private By select_supply_distributions_to_ = By.xpath("(//div[@class='primaryLabel slds-truncate slds-lookup__result-text'])[1]");
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	private WebElement saveReceiveRequisition;
	
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
	
	public RequisitionPage(WebDriver driver) {
		super(driver);
	}
	
	public void displaySupplyConsolePage() {
		waitForElementToBeVisible(driver, supply_console_page_displayed, 10);
		this.supply_console_page_displayed.isDisplayed();
	}
	
	@FindBy(xpath = "//h2[contains(text(),'Ship Requisition')]")
	private WebElement verified;
	
	public String ShipRequisition() {
		return verified.getText();
	}
	
	@FindBy(xpath = "//button[contains(text(),'Receive Requisition')]")
	private WebElement receiveRequestBtn;
	
	public void clickReceiveRequestBtn() {
		this.receiveRequestBtn.click();
	}
	
	public void clickDropdownMenu() {
		this.dropdownMenu.click();
	}

//        public void LocateSupplyLocation() throws InterruptedException {
//            waitForElementToBeLocated(driver, dropdownMenu, 10);
//            WebElement element = driver.findElement(dropdownMenu);
//            JavascriptExecutor executor = (JavascriptExecutor) driver;
//            executor.executeScript("arguments[0].click();", element);
//            waitForElementToBeLocated(driver, supplyLocationInDropdown, 10);
//            WebElement element1 = driver.findElement(supplyLocationInDropdown);
//            JavascriptExecutor executor1 = (JavascriptExecutor) driver;
//            executor1.executeScript("arguments[0].click();", element1);
//            Thread.sleep(5000);
//        }
	
	public void clickSupplyLocationInDropdown() throws InterruptedException {
		waitForElementToBeVisible(driver, supplyLocationInDropdown, 10);
		Thread.sleep(2000);
		this.supplyLocationInDropdown.click();
	}
	
	public void clickSupplyLocations() {
		this.SupplyLocations.click();
	}
	
	public void clickSupplyLocationName() throws InterruptedException {
		this.supply_supply_location_1.click();
	}
	
	public void clickRequestSupplies() throws InterruptedException {
		this.request_supplies.click();
	}
	
	//public void inputShipAddress() {
	//	this.shipAddress.click();
	//}
	
	//public void LocateAddress(String clinic) throws InterruptedException {
		//waitForElementToBeLocated(driver,clinicName,10);
	//	waitForElementToBeVisible(driver, clinicName, 10);
		//WebElement search_input = driver.findElement(clinicName2);
	//	search_input.click();
	//}

	public void selectShipped_From() throws InterruptedException {
		waitForElementToBeVisible(driver, search_supply_location_from, 10);
		search_supply_location_from.sendKeys("Atlin Health Centre");
		Thread.sleep(3000);
		waitForElementToBeVisible(driver, select_supply_location_from, 10);
		Thread.sleep(5000);
		select_supply_location_from.click();
		Thread.sleep(2000);
	}
	
	public void clickRequestedDeliveryDate() throws InterruptedException {
		this.requestedDeliveryDate.click();
	}
	
	public void inputRequestDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		String tomorrowAsString = dateFormat.format(tomorrow);
		this.inputDate.sendKeys(tomorrowAsString, Keys.ENTER);
	}
	
	public void clickNextButton() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", nextButton);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, nextButton, 10);
		Thread.sleep(2000);
		this.nextButton.click();
	}
	
	public void clickLineItemCheckBox() throws InterruptedException {
		By check_box = By.xpath("//tbody/tr[7]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]");
		WebElement element = driver.findElement(check_box);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		Thread.sleep(2000);
		element.click();
	}

	public void clickLineItemCheckBoxNotInStock() throws InterruptedException {
		By check_box = By.xpath("//tbody/tr[14]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]");
		WebElement element = driver.findElement(check_box);
		//Scroll to select the desired line item
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		element.click();
	}
	
	public void inputRequestedDose(String inputDose) {
		this.requestedDose.sendKeys(inputDose);
	}
	
	public void clickSaveButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", saveButton);
		this.saveButton.click();
	}
	
	public void clickSubmitRequisition() throws InterruptedException {
		waitForElementToBeVisible(driver, submitRequisition, 10);
		Thread.sleep(2000);
		this.submitRequisition.click();
	}
	
	public void clickSaveSubmitRequisition() {
		this.saveSubmitRequisition.click();
	}
	
	public void clickEditExpectedDeliveryDate() {
		this.editExpectedDeliveryDate.click();
	}
	
	public void clickExpectedDeliveryDateCalendar() {
		this.expectedDeliveryDateCalendar.click();
	}
	
	public void inputExpectedDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		
		String tomorrowAsString = dateFormat.format(tomorrow);
		this.inputExpectedDate.sendKeys(tomorrowAsString, Keys.ENTER);
	}
	
	public void clickSaveExpectedDeliveryDate() {
		this.saveExpectedDeliveryDate.click();
	}
	
	public void clickApproveRequisition() throws InterruptedException {
		//Scroll to top of the Screen to find approve button and Approve requisition
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)", "");
		Thread.sleep(5000);
		this.approveRequisition.click();
	}

//    public void closeTab() {
//        driver.findElement(By.xpath("//*[@data-key='close'][@class='slds-button__icon']")).click();
//    }
	
	public void enterApprovedDose(String inputDose) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", ApprovedDose);
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, ApprovedDose, 10);
		Thread.sleep(2000);
		this.ApprovedDose.sendKeys(inputDose);
	}
	
	public void clickSaveApprovedRequisition() throws InterruptedException {
		waitForElementToBeVisible(driver, saveApprovedRequisition, 10);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", saveApprovedRequisition);
		Thread.sleep(2000);
		this.saveApprovedRequisition.click();
	}
	
	public void clickShipRequisition() throws InterruptedException {
		Thread.sleep(2000);
		this.shipRequisition.click();
	}
	
	public void clickSaveShipRequisition() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-400)", "");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", saveShipRequisition);
		waitForElementToBeVisible(driver, saveShipRequisition, 10);
		this.saveShipRequisition.click();
	}
	
	public void SelectSupplyDistributionTo(String distribution) throws InterruptedException {
		//waitForElementToBeLocated(driver,distributionName,10);
		waitForElementToBeVisible(driver, distributionName, 10);
		WebElement search_input = driver.findElement(distributionName2);
		search_input.click();
	}
	
	public void clickSaveReceiveRequisition() {
		this.saveReceiveRequisition.click();
	}
	
	public void closeTabs() throws InterruptedException {
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

	public void clickOnSearchSupplyDistributions() throws InterruptedException {
		waitForElementToBeVisible(driver, click_on_search_supply_distributions_to_component, 10);
		Thread.sleep(2000);
		this.click_on_search_supply_distributions_to_component.click();
	}

}