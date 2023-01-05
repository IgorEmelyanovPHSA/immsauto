package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import static Constansts.Header.SUPPLY_LOCATION_NAME_;

public class CommunityPortalMainPage extends BasePage{

    public CommunityPortalMainPage(WebDriver driver) { super(driver);}

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand' and contains(text(),'Camera')]")
    private WebElement btnScanUsingCamera;

    @FindBy(xpath = "//a[text()='Supply Locations']")
    private WebElement tabSupplyLocation;

    @FindBy(xpath = "//a[@title='Age 12 and Above - Abbotsford - Abby Pharmacy' and contains(@href, 's/hc-supply-location')]")
    private WebElement supplyLocationNameAbby;

    @FindBy(xpath = "//a[@title='Automation Supply Location_1' and contains(@href, 's/hc-supply-location')]")
    private WebElement automationSupplyLocation_1;

    @FindBy(xpath = "//span[@class='title' and text()='Related Items']")
    private WebElement tabRelatedItems;

    @FindBy(xpath = "//button[text() = 'More']")
    private WebElement main_menu_btn_More;

    @FindBy(xpath = "//a[@title = 'All Client']")
    private WebElement sub_menu_AllClients;

    @FindBy(xpath = "//a[@title = 'Participants']")
    private WebElement sub_menu_Participants;

    @FindBy(xpath = "//a[text() = 'Participants']")
    private WebElement main_menu_btn_Participants;



    public void verifyYouAreOnTheMainPageCP(){

    }

    public SupplyConsolePage navigateToSupplyConsolePage() throws InterruptedException {
        log("/*4.----Go to Supply Locations Tab --*/");
        goToSupplyLocation();
        click(automationSupplyLocation_1);
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
        //waitForElementToBeClickable(sub_menu_AllClients);
        waitForElementToBeClickable(sub_menu_Participants);
        Thread.sleep(2000);
        //click(sub_menu_AllClients);
        click(sub_menu_Participants);
        Thread.sleep(2000);
        return new ProfilesPage(driver);
    }

    //public ProfilesPage navigateToProfilesPage() throws InterruptedException {
       // waitForElementToBeClickable(main_menu_btn_Participants);
       //Thread.sleep(2000);
        //main_menu_btn_Participants.click();
        //Thread.sleep(2000);
       // return new ProfilesPage(driver);
    //}

    @Step
    public SupplyConsolePage navigateToSupplyLocationRelatedTab( String location) throws InterruptedException {
        SupplyConsolePage supplyConsolePage = goToSupplyLocation();
        Thread.sleep(2000);
        new Tables(driver).clickOnSupplyLocationTableRow(ImmutableMap.of(SUPPLY_LOCATION_NAME_, location));
        selectRelatedTab();
        return supplyConsolePage;
    }

}
