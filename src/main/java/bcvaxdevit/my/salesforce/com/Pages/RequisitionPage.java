package bcvaxdevit.my.salesforce.com.Pages;


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
	
	
	@FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']")
	
	private WebElement supply_supply_location_1;
	
	
	@FindBy(xpath = "//button[contains(text(),\"Request Supplies\")]")
	private WebElement request_supplies;
	
	@FindBy(xpath = "//input[@placeholder=\"Search Supply Locations...\"]")
	private WebElement shipAddress;
	private By shipAddress1 = By.xpath("//input[@placeholder=\"Search Supply Locations...\"]");
	
	@FindBy(xpath = "//span[contains(text(),\"Atlin Health Centre\")]")
	private WebElement clinicName;
	private By clinicName2 = By.xpath("//span[contains(text(),\"Atlin Health Centre\")]");

//    @FindBy (xpath ="//a[contains(text(),\"Atlin Health Centre\")]")
//    private WebElement clinicName;
	
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
	
	@FindBy(xpath = "//button[contains(text(),'Ship Requisition')]")
	private WebElement shipRequisition;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveShipRequisition;
	
	@FindBy(xpath = "//input[@class ='default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']")
	private WebElement distributionName;
	private By distributionName2 = By.xpath("//input[@class ='default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']");
	
	
	@FindBy(xpath = "//input[@placeholder='Search Supply Distributions...']")
	private WebElement SearchSupplyDistributions;
	
	//private By SearchSupplyDistributions1 = By.xpath("//input[@placeholder=\"Search Supply Distributions...\"]");
	public void clickSearchSupplyDistributions() {
		this.SearchSupplyDistributions.click();
	}
	
	
	@FindBy(xpath = "//input[@placeholder=\"Search Supply Distributions...\"]")
	private WebElement searchByName;
	private By searchByName2 = By.xpath("//div[@class='primaryLabel slds-truncate slds-lookup__result-text']");
	
	@FindBy(xpath = "//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	private WebElement saveReceiveRequisition;
	
	public void SelectSupplyDistributionTo2(String DistributionName) throws InterruptedException {
		//waitForElementToBeLocated(driver,distributionName,10);
		waitForElementToBeVisible(driver, searchByName, 10);
		WebElement search_input = driver.findElement(searchByName2);
		this.searchByName.sendKeys(DistributionName);
		search_input.click();
		/*waitForElementToBeVisible(driver, clinicName, 10);
		WebElement search_input = driver.findElement(clinicName2);
		search_input.click();*/
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
		
		//this.shipAddress.sendKeys(inputAddress);
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
	
	public void clickSupplyLocationInDropdown() {
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
	
	public void inputShipAddress() {
		this.shipAddress.click();
		
		//this.shipAddress.sendKeys(inputAddress);
	}
	
	public void LocateAddress(String clinic) throws InterruptedException {
		//waitForElementToBeLocated(driver,clinicName,10);
		waitForElementToBeVisible(driver, clinicName, 10);
		WebElement search_input = driver.findElement(clinicName2);
		search_input.click();
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

//    public void closeTab() {
//        driver.findElement(By.xpath("//*[@data-key='close'][@class='slds-button__icon']")).click();
//    }
	
	public void enterApprovedDose(String inputDose) throws InterruptedException {
		
		//WebElement jusbox= driver.findElement(By.xpath("//input[@name='a3Y4m0000005v13EAA/a2j4m000000B6i8AAC']"));
		//
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", ApprovedDose);
		
		this.ApprovedDose.sendKeys(inputDose);
	}
	
	public void clickSaveApprovedRequisition() {
		this.saveApprovedRequisition.click();
	}
	
	public void clickShipRequisition() {
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
}