package primarycare.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class CommonMethods extends BasePage {
    /*---------Properties-------*/
    private By appsLauncher = By.xpath("//div[@class='slds-icon-waffle']");
    private By healthCloudConsoleTitle = By.xpath(".//span[@title='Health Cloud Console']");
    private By appsHealthCloudConsole = By.xpath("//p[text()='Health Cloud Console']");

    @FindBy(xpath = "//button[@title = 'Show Navigation Menu']")
    private WebElement navigator_menu_dropdown;
    private By navigator_menu_dropdown_1 = By.xpath("//button[@title = 'Show Navigation Menu']");

    @FindBy(xpath = "//span/span[text() = 'Home']")
    private WebElement select_Home_from_dropdown;
    private By select_Home_from_dropdown_1 = By.xpath("//span/span[text() = 'Home']");


    @FindBy(xpath = "//button[@aria-label = 'Search']")
    private WebElement searchAssistant;

    //@FindBy(xpath = "//input[@placeholder = 'Search Accounts and more...']")
    //private WebElement searchInput;
    @FindBy(xpath = "//input[@placeholder = 'Search...']")
    private WebElement searchInput;


    @FindBy(xpath = "(//a[@title='Sandy Prior'])[2]")
    private WebElement patient_founded;
    private By patient_founded_1 = By.xpath("(//a[@title='Sandy Prior'])[2]");


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

    public void selectHomeFromNavigationMenuDropdown() throws InterruptedException {
        waitForElementToBeVisible(driver, navigator_menu_dropdown, 10);
        navigator_menu_dropdown.click();
        Thread.sleep(2000);
        waitForElementToBeVisible(driver, select_Home_from_dropdown, 10);
        Thread.sleep(2000);
        select_Home_from_dropdown.click();
        Thread.sleep(2000);
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

    public void globalSearch(String textToSearch) throws InterruptedException {
        waitForElementToBeVisible(driver, searchAssistant, 10);
        searchAssistant.click();
        Thread.sleep(3000);
        waitForElementToBeVisible(driver, searchInput, 10);
        Thread.sleep(3000);
        searchInput.clear();
        Thread.sleep(3000);
        searchInput.sendKeys(textToSearch);
        Thread.sleep(2000);
        searchInput.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
    }

    public void clickOnFondedPatient(String legalFirstName, String legalLastName) throws InterruptedException {
        waitForElementToBeLocated(driver, patient_founded_1, 10);
        Thread.sleep(2000);
        patient_founded.click();
    }



}
