package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SupplyConsolePage extends BasePage {
    @FindBy(xpath = "(.//span[@class = 'slds-truncate'])[2]")
    private WebElement supply_locations_tab;
    private By supply_locations_tab1 = By.xpath("(.//span[@class = 'slds-truncate'])[2]");

    @FindBy(xpath = ".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']")
    //@FindBy(linkText = "Automation Supply Location_1")
    private WebElement supply_supply_location_1;
    //private By supply_supply_location_1_ = By.linkText("Automation Supply Location_1");
    private By supply_supply_location_1_ = By.xpath(".//th//a[@data-refid='recordId' and @title='Automation Supply Location_1']");

    @FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)")
    private WebElement rows_count_path;
    private By rows_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)");

    //@FindBy(xpath = ".//span[text() = 'Select Item 1']")
    //@FindBy(xpath = "(.//span[@class = 'slds-checkbox_faux'])[2]")
    //private WebElement container_checkbox_1;
    //private By container_checkbox_1_ = By.xpath("(.//span[@class = 'slds-checkbox_faux'])[2]");

    @FindBy(xpath = ".//button[text() = 'Transfer']")
    private WebElement bulk_transfers_button;
    private By bulk_transfers_button_1 = By.xpath(".//button[text() = 'Transfer']");


    @FindBy(xpath = "//span[contains(text(),\"Supply Locations\")]")
    private WebElement  SupplyLocations;

    public void clickSupplyLocations(){
        SupplyLocations.click();
    }

    @FindBy(xpath = "//button[@class=\"slds-button slds-button--reset downIcon slds-m-top_xxx-small slds-p-right_xxx-small\"]")
    private WebElement  clickViewAllBtn;

    public void clickClickViewAllBtn(){
        clickViewAllBtn.click();
    }

    @FindBy(xpath = "//body/div[4]/div[2]/div[7]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]/span[1]")
    private WebElement  clickAllBtn;

    public void clickClickAllBtn(){
        clickAllBtn.click();
    }

    @FindBy(xpath = "//a[contains(text(),\"Atlin Health Centre\")]")
    private WebElement  firstRow;

    public void clickFirstRow(){
        firstRow.click();
    }
    @FindBy(xpath = "")
    private WebElement  RequestSupplies;

    public void clickRequestSupplies(){
        RequestSupplies.click();
    }

    @FindBy(xpath = ".//span[@title='Health Connect - Supply Console']")
    private WebElement supply_page_displayed;



    public SupplyConsolePage(WebDriver driver) {
        super(driver);
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

    public void clickOnCheckbox(int k) throws InterruptedException {
        By container_checkbox_1_ = By.xpath("(.//span[@class = 'slds-checkbox_faux'])["+ k +"]");
        waitForElementToBeLocated(driver, container_checkbox_1_, 10);
        //waitForElementToBeLocated(driver, container_checkbox_1_, 10);
        WebElement element = driver.findElement(container_checkbox_1_);
        //this.container_checkbox_1.click();
        click(container_checkbox_1_);
    }

    public int getRowsCount() throws InterruptedException {
        //waitForElementToBeClickable(driver, container_checkbox_1, 10);
        //waitForElementToBeLocated(driver, container_checkbox_1_, 10);
        List<WebElement> rows = driver.findElements(rows_count_path_1);
        //rows.size();
        return(rows.size());
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


}

