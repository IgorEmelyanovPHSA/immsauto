package primarycare.pages;


import org.openqa.selenium.*;

public class CommonMethods extends BasePage {
    /*---------Properties-------*/
    //@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
    //private WebElement register_button;
    //private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");

    private By appsLauncher = By.xpath("//div[@class='slds-icon-waffle']");
    private By healthCloudConsoleTitle = By.xpath(".//span[@title='Health Cloud Console']");
    private By appsHealthCloudConsole = By.xpath("//p[text()='Health Cloud Console']");



    /*---------Constructor-------*/
    public CommonMethods(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public void goToHealthCloudConsolePageIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        //log("/*-- Close all open tabs --*/");
        //closeAllHealthCloudConsoleTabs();
        //Thread.sleep(5000);
        if (isDisplayed(healthCloudConsoleTitle)) {
            log("/*-- User already on Health Cloud Console Page --*/");
        } else {
            log("/*-- Navigate to Health Cloud Console Page --*/");
            selectHealthCloudConsoleApp();
            Thread.sleep(2000);
        }
       // Thread.sleep(2000);
       // log("/*-- Close all open tabs --*/");
       // closeAllHealthCloudConsoleTabs();
        //if (isDisplayed(supplyLocation)) {
        //    log("/*-- User is already on Supply loc--*/");
       // } else {
        //    log("/*-- Click Dropdown Menu --*/");
         //   click(dropdownMenu);
        //    Thread.sleep(5000);
        //    log("/*-- Navigate and Select Supply Locations --*/");
        //    click(supplyLocationInDropdown);
        //    Thread.sleep(2000);
        //}
        //log("/*-- Close all open tabs --*/");
        //closeAllHealthCloudConsoleTabs();
        //Thread.sleep(2000);
    }

    public void closeAllHealthCloudConsoleTabs() throws InterruptedException {
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

    public void selectHealthCloudConsoleApp() throws InterruptedException {
        waitForElementToBeLocated(driver, appsLauncher, 10);
        WebElement element = driver.findElement(appsLauncher);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        waitForElementToBeLocated(driver, appsHealthCloudConsole, 10);
        WebElement element1 = driver.findElement(appsHealthCloudConsole);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
        Thread.sleep(2000);
    }



}
