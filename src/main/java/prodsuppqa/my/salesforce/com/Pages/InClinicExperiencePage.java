package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class InClinicExperiencePage extends BasePage {

    @FindBy(xpath = ".//span[text() = 'Register']")
    private WebElement register_tab;

    //@FindBy(xpath = ".//a[@title = 'Register']")
    //private WebElement register_tab;

    private By register_tab1 = By.xpath("(.//a[@title = 'Register'])");

    @FindBy(xpath = ".//button[@title = ' Create New Profile']")
    private WebElement register_button;

    @FindBy(xpath = ".//button[@aria-label = 'Search']")
    private WebElement search_assistant;
    private By search_assistant1 = By.xpath(".//button[@aria-label = 'Search']");

    @FindBy(xpath = ".//input[@placeholder = 'Search...']")
    private WebElement search_input;
    private By search_input1 = By.xpath(".//input[@placeholder = 'Search...']");

    @FindBy(xpath = "(.//A[@data-refid='recordId'])[1]")
    private WebElement user_found;
    private By user_found1 = By.xpath(".//a[@title='Giacinta BCVaxCaudelier']");

    @FindBy(xpath = "//*/div/a/lightning-icon/lightning-primitive-icon")
    private WebElement delete_Imms_record;
    private By delete_Imms_record1 = By.xpath("//*/div/a/lightning-icon/lightning-primitive-icon");


    public InClinicExperiencePage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterTab() throws InterruptedException {
        // explicit wait - to wait for the compose button to be click-able
        //WebDriverWait wait = new WebDriverWait(driver,30);
        //wait.until(ExpectedConditions.visibilityOf(register_tab));
        waitForElementToBeLocated(driver,register_tab1,10);
        WebElement element = driver.findElement(register_tab1);
        //waitForElementToBeVisible(driver, element, 10);
        JavascriptExecutor executor=(JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        //driver.findElement(By.xpath(".//a[@title = 'Register']")).click();
        //this.register_button.click();
        //this.register_tab1.click();
        //click(relatedTab2);
        //this.register_tab.click();
    }

    public boolean userFound() throws InterruptedException {
        waitForElementToBeLocated(driver,delete_Imms_record1,15);
        WebElement element = driver.findElement(delete_Imms_record1);
        JavascriptExecutor executor=(JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        return false;
    }

    public void deleteImmRecord() throws InterruptedException {
        waitForElementToBeLocated(driver,user_found1,15);
        WebElement element = driver.findElement(user_found1);
        JavascriptExecutor executor=(JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void SearchForCitizen(String citizen) throws InterruptedException {
       // waitForElementToBeLocated(driver,search_assistant1,10);
        waitForElementToBeVisible(driver, search_assistant, 10);
        WebElement search_navigator = driver.findElement(search_assistant1);
        search_navigator.click();
        waitForElementToBeVisible(driver, search_input, 10);
        WebElement search_input = driver.findElement(search_input1);
        search_input.sendKeys(citizen);
        search_input.sendKeys(Keys.RETURN);
        //Thread.sleep(5000);
        //element.clear();
        //JavascriptExecutor executor=(JavascriptExecutor)driver;
        //executor.executeScript("arguments[0].click();", element);
        //element.sendKeys(citizen);
        //element.sendKeys(citizen);
        //executor.executeScript("arguments[0].setAttribute('value', arguments[1])", element, citizen);
        //executor.executeScript("arguments[0].value='\"+ keysToSend +\"';", element);
        //Thread.sleep(5000);
        //element.click();
        //element.sendKeys(Keys.RETURN);
        Thread.sleep(5000);
    }





}
