package bcvax.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

import java.util.Map;

public class CommunityPortalMainPage extends BasePage{

    public CommunityPortalMainPage(WebDriver driver) { super(driver);}

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand' and contains(text(),'Camera')]")
    private WebElement btnScanUsingCamera;

    @FindBy(xpath = "//a[text()='Supply Locations']")
    private WebElement tabSupplyLocation;

    @FindBy(xpath = "//a[@title='Age 12 and Above - Abbotsford - Abby Pharmacy' and contains(@href, '/provider/s/hc-supply-location')]")
    private WebElement supplyLocationNameAbby;

    @FindBy(xpath = "//a[@title='Automation Supply Location_1' and contains(@href, '/provider/s/hc-supply-location')]")
    private WebElement automationSupplyLocation_1;

    @FindBy(xpath = "//span[@class='title' and text()='Related Items']")
    private WebElement tabRelatedItems;

    @FindBy(xpath = "//button[text() = 'More']")
    private WebElement main_menu_btn_More;

    @FindBy(xpath = "//a[@title = 'All Client']")
    private WebElement sub_menu_AllClients;



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

    public ProfilesPage navigateToProfilesPage() throws InterruptedException {
        waitForElementToBeClickable(main_menu_btn_More);
        Thread.sleep(2000);
        click(main_menu_btn_More);
        Thread.sleep(2000);
        waitForElementToBeClickable(sub_menu_AllClients);
        Thread.sleep(2000);
        click(sub_menu_AllClients);
        //waitForElementToBeClickable(tabSupplyLocation);
        //click(tabRelatedItems);
        Thread.sleep(2000);
        return new ProfilesPage(driver);
    }

}
