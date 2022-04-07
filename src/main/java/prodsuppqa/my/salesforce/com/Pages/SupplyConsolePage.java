package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SupplyConsolePage extends BasePage {
    @FindBy(xpath = ".//span[text() = 'Supply Locations']")
    private WebElement supply_locations_tab;
    private By supply_locations_tab1 = By.xpath("(.//a[@title = 'Supply Locations'])");

    @FindBy(linkText = "Automation Supply Location_1")
    private WebElement supply_supply_location_1;
    private By supply_supply_location_1_ = By.linkText("Automation Supply Location_1");

    @FindBy(xpath = "(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)")
    private WebElement rows_count_path;
    private By rows_count_path_1 = By.xpath("(//table[@class = 'slds-table slds-table_header-fixed slds-table_bordered slds-table_edit']/tbody/tr)");

    //@FindBy(xpath = ".//span[text() = 'Select Item 1']")
    //@FindBy(xpath = "(.//span[@class = 'slds-checkbox_faux'])[2]")
    //private WebElement container_checkbox_1;
    //private By container_checkbox_1_ = By.xpath("(.//span[@class = 'slds-checkbox_faux'])[2]");


    public SupplyConsolePage(WebDriver driver) {
        super(driver);
    }

    public void clickSupplyLocationsTab() throws InterruptedException {
        waitForElementToBeLocated(driver, supply_locations_tab1, 10);
        WebElement element = driver.findElement(supply_locations_tab1);
        //waitForElementToBeVisible(driver, element, 10);
        //JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].click();", element);
        //click(supply_locations_tab1);
        this.supply_locations_tab.click();
    }

    public void clickOnSupplyLocations() throws InterruptedException {
        waitForElementToBeLocated(driver, supply_supply_location_1_, 10);
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


}
