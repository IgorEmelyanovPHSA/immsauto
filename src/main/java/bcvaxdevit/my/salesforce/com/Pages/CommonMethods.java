package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class CommonMethods extends BasePage{

    public CommonMethods(WebDriver driver) {
        super(driver);
    }

    private By supplyConsoleApp = By.xpath(".//span[@title='Health Connect - Supply Console']");
    private By select_app_launcher1 = By.xpath("//div[@class='slds-icon-waffle']");
    private By healthconnectApp1 = By.xpath("//p[text()='Health Connect - Supply Console']");
    private By supplyLocation = By.xpath("//a[@title='Supply Locations']");

    @FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
    private WebElement dropdownMenu;

    //WHAT KIND OF XPATH IS THIS?
    @FindBy(xpath = "//html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[1]/div/div[3]/div/section/div/div/ul/li[7]/div/a/span[2]/span")
    private WebElement supplyLocationInDropdown;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void closeAutomationLocationTab() throws InterruptedException {
        do {
            try {
                WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
                click(closetab);
                Thread.sleep(2000);
            } catch (NoSuchElementException e) {
                log("/*---there are no Tab's to close anymore");
            }
        } while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
    }

    public void selectHealthConnectApp() throws InterruptedException {
        waitForElementToBeLocated(driver, select_app_launcher1, 10);
        WebElement element = driver.findElement(select_app_launcher1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        waitForElementToBeLocated(driver, healthconnectApp1, 10);
        WebElement element1 = driver.findElement(healthconnectApp1);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
        Thread.sleep(2000);
    }
    
    public void goToSupplyPageIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        closeAutomationLocationTab();
        Thread.sleep(5000);
        if (isDisplayed(supplyConsoleApp)) {
            log("/*-- User already on Health Connect - Supply Console --*/");
        } else {
            log("/*-- Navigate to Health Connect - Supply Console --*/");
            selectHealthConnectApp();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        log("/*-- Close all open tabs --*/");
        closeAutomationLocationTab();
        if (isDisplayed(supplyLocation)) {
            log("/*-- User is already on Supply loc--*/");
        } else {
            log("/*-- Click Dropdown Menu --*/");
            click(dropdownMenu);
            Thread.sleep(5000);
            log("/*-- Navigate and Select Supply Locations --*/");
            click(supplyLocationInDropdown);
            Thread.sleep(2000);
        }
        closeAutomationLocationTab();
        log("/*-- Close all open tabs --*/");
        Thread.sleep(2000);
    }
}