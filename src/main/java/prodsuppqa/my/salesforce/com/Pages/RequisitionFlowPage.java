package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RequisitionFlowPage extends BasePage {
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	private WebElement dropdownMenu;
	
	@FindBy(xpath = "(/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/button/lightning-primitive-icon")
	private WebElement supply_location_dropdown;
	private By supply_location_dropdown1 = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/button/lightning-primitive-icon");
	
	@FindBy(xpath = "(//*[text()='Supply Locations']")
	private WebElement select_supply_location;
	private By select_supply_location1 = By.xpath("//*[text()='Supply Locations']");
	
	@FindBy(xpath = "/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/button/lightning-primitive-icon")
	private WebElement supplyLocationInDropdown;
	
	@FindBy(xpath = ".//input[@class='slds-combobox__input slds-input']")
	private WebElement SupplyLocations;
	
	@FindBy(xpath = "//tbody/tr[1]/th[1]/span[1]/a[1]")
	private WebElement supply_supply_location_1;
	
	@FindBy(xpath = "//button[contains(text(),\"Request Supplies\")]")
	private WebElement request_supplies;
	
	@FindBy(xpath = "//input[@class=\"slds-combobox__input slds-input\"]")
	private WebElement shipAddress;
	
	@FindBy(xpath = "//input[@class=\"slds-input\"]")
	private WebElement requestedDeliveryDate;
	
	@FindBy(xpath = "//input[@name=\"BCH_Requested_Delivery_Date__c\"]")
	private WebElement inputDate;
	
	@FindBy(xpath = "//button[contains(text(),\"Next\")]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//tbody/tr[13]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]")
	private WebElement checkBox;
	
	@FindBy(xpath = "//div[@class=\"slds-form-element__control slds-grow\"]//input")
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
	
	@FindBy(xpath = "//*[@data-key='close'][@class='slds-button__icon']")
	private WebElement closeTab;
	
	@FindBy(xpath = "//input[@name=\"BCH_Expected_Delivery_Date__c\"]")
	private WebElement inputExpectedDate;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveExpectedDeliveryDate;
	
	@FindBy(xpath = "//button[contains(text(),'Approve Requisition')]")
	private WebElement approveRequisition;
	
	@FindBy(xpath = "//div[@class=\"slds-form-element__control slds-grow\"]//input")
	private WebElement ApprovedDose;
	
	public RequisitionFlowPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickDropdownMenu() {
		this.dropdownMenu.click();
	}

        /*public void LocateSupplyLocation() throws InterruptedException {
           waitForElementToBeLocated(driver, dropdownMenu, 10);
           WebElement element = driver.findElement(dropdownMenu);
           JavascriptExecutor executor = (JavascriptExecutor) driver;
           executor.executeScript("arguments[0].click();", element);
            waitForElementToBeLocated(driver, supplyLocationInDropdown, 10);
            WebElement element1 = driver.findElement(supplyLocationInDropdown);
            JavascriptExecutor executor1 = (JavascriptExecutor) driver;
            executor1.executeScript("arguments[0].click();", element1);
            Thread.sleep(5000);
        }*/
	
	/*public void clickSupplyLocationInDropdown() {
		this.supplyLocationInDropdown.click();
	}*/
	
	public void clickSupplyLocationInDropdown() throws InterruptedException {
		waitForElementToBeLocated(driver, supply_location_dropdown1, 10);
		WebElement element = driver.findElement(supply_location_dropdown1);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitForElementToBeLocated(driver, select_supply_location1, 10);
		WebElement element1 = driver.findElement(select_supply_location1);
		JavascriptExecutor executor1 = (JavascriptExecutor) driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
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
	
	public void inputShipAddress(String inputAddress) {
		this.shipAddress.sendKeys(inputAddress);
	}
	
	public void clickInput() throws InterruptedException {
		WebElement products = driver.findElement(By.xpath("//span[contains(text(),\"Atlin Health Centre\")]"));
		
		products.click();
	}
	
	public void clickInput1() throws InterruptedException {
		WebElement products = driver.findElement(By.xpath("//a[contains(text(),\"Atlin Health Centre\")]"));
		
		products.click();
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
	
	public void clickNextButton() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", nextButton);
		waitForElementToBeVisible(driver, nextButton, 10);
		this.nextButton.click();
	}
	
	public void clickLineItemCheckBox() throws InterruptedException {
		By check_box = By.xpath("//tbody/tr[13]/td[1]/lightning-input[1]/div[1]/span[1]/label[1]/span[1]");
		WebElement element = driver.findElement(check_box);
		//Scroll to select the desired line item
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
		element.click();
	}
	
	public void inputRequestedDose(String inputDose) {
		this.requestedDose.sendKeys(inputDose);
	}
	
	
	public void clickSaveButton() {
		this.saveButton.click();
	}
	
	public void clickSubmitRequisition() {
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
	
	public void enterApprovedDose(String inputDose) {
		this.ApprovedDose.sendKeys(inputDose);
	}
	
	public void closeTab() {
		driver.findElement(By.xpath("//*[@data-key='close'][@class='slds-button__icon']")).click();
	}
	
}