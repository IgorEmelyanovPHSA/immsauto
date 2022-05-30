package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallCenterConsolePage extends BasePage {

    /*---------Properties-------*/
    @FindBy(xpath = ".//span[@title='Call Center Console']")
    private WebElement callcenter_page_displayed;

    @FindBy(xpath = ".//button[text() = 'Register New Citizen']")
    private WebElement register_button;
    private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");

    @FindBy(xpath = "(.//input[@name = 'FirstName'])")
    private WebElement first_name;
    private By first_name1 = By.xpath("(.//input[@name = 'FirstName'])");


    /*---------Constructor-------*/
    public CallCenterConsolePage(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public void verifyIsCallCenterConsolePageDisplayed() {
        waitForElementToBeVisible(driver, callcenter_page_displayed, 10);
        callcenter_page_displayed.isDisplayed();
    }

    public void closeAllTabs() throws InterruptedException {
        do {
            try {
                WebElement close_tab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
                Thread.sleep(1000);
                close_tab.click();
                Thread.sleep(1000);
            } catch (NoSuchElementException e) {
                System.out.println("/*---there are no Tab's to close anymore");
            }
        } while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
    }

    public void clickRegisterButton() throws InterruptedException {
        waitForElementToBeVisible(driver, register_button, 10);
        WebElement element = driver.findElement(register_button_1);
        register_button.click();
    }

    public void enterFirstName(String firstname) throws InterruptedException {
        waitForElementToBeLocated(driver, first_name1, 10);
        first_name.sendKeys(firstname);
    }

}
