package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.awt.SystemColor.window;


public class SupplyConsolePage extends BasePage {

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

    @FindBy (xpath = "button[@class=\"slds-button slds-button_brand cuf-publisherShareButton undefined uiButton\"]")
    private WebElement saveSubmitRequisition;
    private By save_Submit_Requisition = By.xpath("button[@class=\"slds-button slds-button_brand cuf-publisherShareButton undefined uiButton\"]");

    @FindBy (xpath = "span[contains(text(),\"Select a date for Expected Delivery Date\")]" )
    private WebElement expectedDeliveryDateCalendar;
    private By expected_delivery_date_calendar = By.xpath("span[contains(text(),\"Select a date for Expected Delivery Date\")]" );

    @FindBy(xpath = "//button[contains(text(),\"Submit Requisition\")]")
    private WebElement submitRequisition;
    private By submit_requisition = By.xpath("//button[contains(text(),\"Submit Requisition\")]");

    @FindBy(xpath = ".//span[@title='Health Connect - Supply Console']")
    private WebElement supply_page_displayed;

    @FindBy(xpath = ".//input[@placeholder='Search Supply Locations...']")
    private WebElement search_supply_location_To;
    private By search_supply_location_To_ = By.xpath(".//input[@placeholder='Search Supply Locations...']");
    
    @FindBy(xpath = "//lightning-base-combobox-formatted-text[@title='Automation Supply Location_2']")
    private WebElement select_supply_location_To;
    private By select_supply_location_To_ = By.xpath("//lightning-base-combobox-formatted-text[@title='Automation Supply Location_2']");

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

    @FindBy(xpath = ".//button[text() = 'Confirm Transfer']")
    private WebElement bulk_confirm_incoming_transfers_button;
    private By bulk_confirm_incoming_transfers_button_1 = By.xpath(".//button[text() = 'Confirm Transfer']");

    @FindBy(xpath = ".//span[text() = 'Select Supply Distributor']")
    private WebElement search_incoming_supply_distributor;
    private By search_incoming_supply_distributor_ = By.xpath(".//span[text() = 'Select Supply Distributor']");

    @FindBy(xpath = "//span[@title='Supply Distribution_2_1 - SDST-0000000398']")
    private WebElement select_incoming_supply_distributor;
    private By select_incoming_supply_distributor_ = By.xpath("//span[@title='Supply Distribution_2_1 - SDST-0000000398']");

    @FindBy(xpath = ".//button[text() = 'Confirm Transaction']")
    private WebElement confirm_incoming_transfers_modal_button;
    private By confirm_incoming_transfers_modal_button_1 = By.xpath(".//button[text() = 'Confirm Transaction']");

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
    
    public void clickSubmitRequisition(){
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
        waitForElementToBeVisible(driver,supply_locations_tab,10);
        WebElement element = driver.findElement(supply_locations_tab1);
        //waitForElementToBeVisible(driver, element, 10);
        //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].click();", element);
        //click(supply_locations_tab1);
        this.supply_locations_tab.click();
    }
    
    public void clickOnSupplyLocation_1() throws InterruptedException {
        //waitForElementToBeLocated(driver, supply_supply_location_1_, 10);
        waitForElementToBeVisible(driver,supply_supply_location_1,10);
        WebElement element = driver.findElement(supply_supply_location_1_);
        this.supply_supply_location_1.click();
    }

    public void clickOnSupplyLocation_2() throws InterruptedException {
        //waitForElementToBeLocated(driver, supply_supply_location_1_, 10);
        waitForElementToBeVisible(driver,supply_supply_location_2,10);
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
        By container_checkbox_1_ = By.xpath("(.//flexipage-component2[@data-component-id='hcCrossObjectRelationRecordsList']//tbody//span[@class = 'slds-checkbox_faux'])[" + k + "]");
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
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
        return(element.getText());
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
        return(element.getText());
     }

    public void closeAutomationLocationTab() throws InterruptedException {
        do {
            try{
                WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
                closetab.click();
                Thread.sleep(2000);
            }catch (NoSuchElementException e){
                System.out.println("/*---there are no Tab's to close anymore");
            }

        } while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));

    }

    public int getRowsIncomingTransactionsCount() throws InterruptedException {
        waitForElementToBeVisible(driver, rows_incoming_transactions_count_path, 10);
        List<WebElement> rows = rows_incoming_transactions_count_path.findElements(By.tagName("tr"));
        return (rows.size());
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





}