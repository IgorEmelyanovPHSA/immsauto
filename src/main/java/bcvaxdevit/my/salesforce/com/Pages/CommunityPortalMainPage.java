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
        goToSupplyLocation();
        click(supplyLocationNameAbby);
        selectRelatedTab();
        Thread.sleep(2000);
        return new SupplyConsolePage(driver);
    }

    public CommunityPortalMainPage openMenu() throws InterruptedException {
        click(btnMenu);
        waitForElementToBeClickable(dropdownMenu);
        return this;
    }

    @Step
    public SupplyConsolePage goToSupplyLocation() throws InterruptedException {
        click(tabSupplyLocation);
        return new SupplyConsolePage(driver);
    }

    @Step
    public CommunityPortalMainPage selectRelatedTab() throws InterruptedException {
        click(tabRelatedItems);
        return this;
    }

}
