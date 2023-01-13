package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;
import static Constansts.Header.SUPPLY_LOCATION_NAME_;

public class CommunityPortalMainPage_as_Clinician extends BasePage{

    /*---------Properties-------*/
    @FindBy(xpath = "//div/h1[text()='Client Search']")
    private WebElement community_portal_home_page_displayed;

    //@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
    //private WebElement register_button;
    //private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");


    /*---------Constructor-------*/
    public CommunityPortalMainPage_as_Clinician(WebDriver driver) { super(driver);}

    /*-------------Methods--------------*/

    public void verifyIsCallCenterConsolePageDisplayed() {
        waitForElementToBeVisible(driver, community_portal_home_page_displayed, 10);
        community_portal_home_page_displayed.isDisplayed();
    }



}
