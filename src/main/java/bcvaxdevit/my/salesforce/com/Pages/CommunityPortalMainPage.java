package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

import java.util.Map;

public class CommunityPortalMainPage extends BasePage{

    public CommunityPortalMainPage(WebDriver driver) { super(driver);}

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand' and contains(text(),'Camera')]")
    private WebElement btnScanUsingCamera;

    @FindBy(xpath = "//a[text()='Supply Location']")
    private WebElement tabSupplyLocation;

    @FindBy(xpath = "//a[@title='Age 12 and Above - Abbotsford - Abby Pharmacy' and contains(@href, '/provider/s/hc-supply-location')]")
    private WebElement supplyLocationNameAbby;

    @FindBy(xpath = "//span[@class='title' and text()='Related Items']")
    private WebElement tabRelatedItems;

    @FindBy(xpath = "//button[text()='More']")
    private WebElement btnMenu;

    @FindBy(xpath = "//div[@class='mainNavItem hasSubNav themeNavMoreLink uiMenu expanded']")
    private WebElement dropdownMenu;

    public void verifyYouAreOnTheMainPageCP(){

    }

    @Step
    public SupplyConsolePage navigateToSupplyConsolePage() throws InterruptedException {
        log("/*4.----Go to Supply Locations Tab --*/");
        click(tabSupplyLocation);
        click(supplyLocationNameAbby);
        waitForElementToBeClickable(tabSupplyLocation);
        click(tabRelatedItems);
        Thread.sleep(2000);
        return new SupplyConsolePage(driver);
    }

    public CommunityPortalMainPage openMenu() throws InterruptedException {
        click(btnMenu);
        waitForElementToBeClickable(dropdownMenu);

        return this;
    }

    public Map<String, WebElement> getSupplyContainerRow(Map<String, String> searchCriteria) {
        WebElement supplyContainer = driver.findElement(By.xpath("//div[@class='slds-table_header-fixed_container slds-scrollable_x']"));
        ConsoleTable consoleTable = new ConsoleTable(supplyContainer);
        waitForVisibility(supplyContainer);
        return  consoleTable.getMappedRow(searchCriteria);
    }

    @Step
    public String getRemainingDoses(Map<String,String> searchCriteria)  {
        Map<String, WebElement> stringWebElementMap = getSupplyContainerRow(searchCriteria);
     //   stringWebElementMap.forEach((k,v)-> System.out.println("Item : " + k + " Count : " + v.getText()));
        String doses = stringWebElementMap.get("Sort by:\nRemaining Doses").getText();
        log("/*4.----Remaining Doses " + doses + "--*/");
        return doses;
    }
    @Step
    public String getRemainingQty(Map<String,String> searchCriteria)  {
        Map<String, WebElement> stringWebElementMap = getSupplyContainerRow(searchCriteria);
        String qty = stringWebElementMap.get("Sort by:\nRemaining Quantity").getText();
        log("/* ----Remaining Qty " + qty + "--*/");
        return qty;

    }
    @Step
    public void getActions(Map<String,String> searchCriteria)  {
     //   Map<String, WebElement> stringWebElementMap = getRow(searchCriteria);
       // System.out.println(stringWebElementMap.get("Sort by:\nRemaining Doses").getText());
        //Map<String, WebElement> stringWebElementMap = getRow(searchCriteria);
        getSupplyContainerRow(searchCriteria).get("").click();
    }

}
