package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupplyConsolePage extends BasePage {
    @FindBy(id = "Automation Supply Location_1")
    private WebElement clinician_prodsuppqa;

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

    public SupplyConsolePage(WebDriver driver) {
        super(driver);
    }



   //public void SupplyLocations() throws InterruptedException {
      //waitForElementToBeLocated(driver, supply_Locations1, 10);
      //WebElement element = driver.findElement(click_user_Locate1);
      //JavascriptExecutor executor = (JavascriptExecutor) driver;
      //executor.executeScript("arguments[0].click();", element);


    }

