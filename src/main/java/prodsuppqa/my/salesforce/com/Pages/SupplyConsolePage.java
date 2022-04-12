package prodsuppqa.my.salesforce.com.Pages;

import org.checkerframework.common.reflection.qual.NewInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;


import java.security.Key;
import java.util.List;

public class SupplyConsolePage extends BasePage {
    @FindBy(xpath = "(.//span[@class = 'slds-truncate'])[2]")
    private WebElement supply_locations_tab;
    private By supply_locations_tab1 = By.xpath("(.//span[@class = 'slds-truncate'])[2]");

    @FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']")
    private WebElement supply_supply_location_1;
    private By supply_supply_location_1_ = By.xpath(".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']");

    @FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)")
    private WebElement rows_supply_containers_from_count_path;
    private By rows_supply_containers_from_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)");

    @FindBy(xpath = ".//button[text() = 'Transfer']")
    private WebElement bulk_transfers_button;
    private By bulk_transfers_button_1 = By.xpath(".//button[text() = 'Transfer']");

    @FindBy(xpath = "(//section[@role='dialog']//button[text() = 'Transfer'])")
    private WebElement bulk_transfers_dialog_button;
    private By bulk_transfers_dialog_button_1 = By.xpath("(//section[@role='dialog']//button[text() = 'Transfer'])");

    @FindBy(xpath = ".//input[@class='slds-combobox__input slds-input']")
    private WebElement  SupplyLocations;

    public void clickSupplyLocations(){
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
    private By input_data = By.xpath( "//input[@name=\"BCH_Requested_Delivery_Date__c\"]");

    @FindBy(xpath = "//button[contains(text(),\"Next\")]")
    private WebElement nextButton;
    private By next_button = By.xpath("//button[contains(text(),\"Next\")]");

    @FindBy(xpath = "//button[contains(text(),\"Submit Requisition\")]")
    private WebElement submitRequisition;
    private By submit_requisition = By.xpath("//button[contains(text(),\"Submit Requisition\")]");

    //@FindBy
    // //button[@class="slds-button slds-button_brand cuf-publisherShareButton undefined uiButton"]----Save
    ////span[contains(text(),"Select a date for Expected Delivery Date")]-----expected date

    @FindBy(xpath = ".//span[@title='Health Connect - Supply Console']")
    private WebElement supply_page_displayed;

    @FindBy(xpath = ".//input[@placeholder='Search Supply Locations...']")
    private WebElement search_supply_location_To;
    private By search_supply_location_To_ = By.xpath(".//input[@placeholder='Search Supply Locations...']");

    @FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='Automation Supply Location_2']")
    private WebElement select_supply_location_To;
    private By select_supply_location_To_ =
            By.xpath("//lightning-base-combobox-formatted-text[@title='Automation Supply Location_2']");

    @FindBy(xpath = "//section[@role='dialog']//button[text()='Close']")
    private WebElement bulk_dialog_close_button;
    private By bulk_dialog_close_button_1 = By.xpath("//section[@role='dialog']//button[text()='Close']");

    @FindBy(xpath = "(.//a[text() = 'Transactions'])")
    private WebElement transactions_tab;
    private By transactions_tab_1 = By.xpath("(.//a[text() = 'Transactions'])");


    @FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)")
    private WebElement rows_transactions_from_count_path;
    private By rows_transactions_from_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)");


    public SupplyConsolePage(WebDriver driver) {
        super(driver);
    }

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

    public void inputRequestDate(String inputDate) {
        this.inputDate.sendKeys(inputDate, Keys.ENTER);
    }

    public void clickNextButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", nextButton);
        waitForElementToBeVisible(driver, nextButton, 10);
        this.nextButton.click();
    }

    public void clickSubmitRequisition(){
        waitForElementToBeVisible(driver, submitRequisition, 10);
        WebElement element = driver.findElement(submit_requisition);
        this.submitRequisition.click();
    }

    public void SupplyLocations() throws InterruptedException {
        waitForElementToBeLocated(driver, By.xpath("//span[contains(text(),\"Supply Locations\")]"), 10);
        WebElement supplyLocationPath = driver.findElement(By.xpath("//span[contains(text(),\"Supply Locations\")]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", supplyLocationPath);
    }

    public void clickSupplyLocationsTab() throws InterruptedException {
        //waitForElementToBeLocated(driver, supply_locations_tab1, 10);
        waitForElementToBeVisible(driver,supply_locations_tab,10);
        WebElement element = driver.findElement(supply_locations_tab1);
        //waitForElementToBeVisible(driver, element, 10);
        //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].click();", element);
        //click(supply_locations_tab1);
        this.supply_locations_tab.click();
    }

    public void clickOnSupplyLocations() throws InterruptedException {
        //waitForElementToBeLocated(driver, supply_supply_location_1_, 10);
        waitForElementToBeVisible(driver,supply_supply_location_1,10);
        WebElement element = driver.findElement(supply_supply_location_1_);
        this.supply_supply_location_1.click();
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

    public void clickOnCheckbox(int k) throws InterruptedException {
        By container_checkbox_1_ = By.xpath("(.//span[@class = 'slds-checkbox_faux'])[" + k + "]");
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

    public void verifyIsSupplyPageDisplayed(){
        waitForElementToBeVisible(driver, supply_page_displayed, 10);
        this.supply_page_displayed.isDisplayed();
    }

    public void enterBulkTransferDosages(int k) throws InterruptedException {
        //private By doses_1 = By.xpath("(.//input[@class = 'slds-input'])[2]");
        By dose_1_ = By.xpath("(.//input[@class = 'slds-input'])["+ k +"]");
        waitForElementToBeLocated(driver, dose_1_, 10);
        WebElement element = driver.findElement(dose_1_);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollRight = arguments[0].scrollWidth", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        click(dose_1_);
        element.sendKeys("1");
    }

    public void selectSupplyLocatonTo() throws InterruptedException {
        waitForElementToBeVisible(driver, search_supply_location_To, 10);
        search_supply_location_To.sendKeys("Automation Supply Location_2");
        waitForElementToBeVisible(driver, select_supply_location_To, 10);
        select_supply_location_To.click();
        //#search_supply_location_To.sendKeys(Keys.ARROW_DOWN);
        //#search_supply_location_To.sendKeys(Keys.ENTER);
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
        waitForElementToBeLocated(driver, transactions_tab_1, 10);
        click(transactions_tab_1);
    }

    public int getRowsTransactionsFromCount() throws InterruptedException {
        waitForElementToBeVisible(driver, rows_transactions_from_count_path, 10);
        List<WebElement> rows = driver.findElements(rows_transactions_from_count_path_1);
        return (rows.size());
    }

    public void clickOnTransactionsFrom(int kk) throws InterruptedException {
        By transactions_from_1_ = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr)[" + kk + "]//a[@title='transactionFromName']");
        waitForElementToBeLocated(driver, transactions_from_1_, 10);
        //waitForElementToBeLocated(driver, container_checkbox_1_, 10);
        WebElement element = driver.findElement(transactions_from_1_);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        //element.click();
        //this.container_checkbox_1.click();
        //click(transactions_from_1_);
    }


}