package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallCenterConsolePage extends BasePage {

    /*---------Properties-------*/
    @FindBy(xpath = ".//span[@title='Call Center Console']")
    private WebElement callcenter_page_displayed;


    /*---------Constructor-------*/
    public CallCenterConsolePage(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public void verifyIsCallCenterConsolePageDisplayed() {
        waitForElementToBeVisible(driver, callcenter_page_displayed, 10);
        callcenter_page_displayed.isDisplayed();
    }

}
