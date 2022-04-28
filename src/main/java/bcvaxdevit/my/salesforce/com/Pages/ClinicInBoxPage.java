package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClinicInBoxPage extends BasePage {

    @FindBy(xpath = ".//button[text() = 'Register New Citizen']")
    private WebElement register_button;
    private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");

    @FindBy(xpath = ".//span[@title='Clinic in a Box (IPM)']")
    private WebElement clinicinbox_page_displayed;

    @FindBy(xpath = ".//span[text() = 'Register']")
    private WebElement register_tab;
    private By register_tab1 = By.xpath("(.//a[@title = 'Register'])");

    @FindBy(xpath = ".//button[@aria-label = 'Search']")
    private WebElement search_assistant;
    private By search_assistant1 = By.xpath(".//button[@aria-label = 'Search']");

    @FindBy(xpath = "//input[@placeholder = 'Search...']")
    private WebElement search_input;
    private By search_input1 = By.xpath("//input[@placeholder = 'Search...']");

    @FindBy(xpath = ".//a[@title='Maegan BCVaxVillage']")
    private WebElement user_found;
    private By user_found1 = By.xpath(".//a[@title='Maegan BCVaxVillage']");

    @FindBy(xpath = "(//a[@data-label='Related'])")
    private WebElement click_related_tab;
    private By click_related_tab1 = By.xpath("//a[@data-label='Related']");

    @FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
    private WebElement creat_Immunization_Record;
    private By creat_Immunization_Record1 = By.xpath("/button[contains(text(),'Create Immunization Record')]");

    @FindBy(xpath = "//option[contains(text(),'Select an option')]")
    private WebElement select_an_option;
    private By select_an_option1 =By.xpath("//option[contains(text(),'Select an option')]");

    @FindBy(xpath = "(.//input[@name = 'FirstName'])")
    private WebElement first_name;
    private By first_name1 =By.xpath("(.//input[@name = 'FirstName'])");

    @FindBy(xpath = "(.//input[@name = 'LastName'])")
    private WebElement last_name;
    private By last_name1 =By.xpath("(.//input[@name = 'LastName'])");

    public ClinicInBoxPage(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterButton() throws InterruptedException {
        waitForElementToBeVisible(driver, register_button, 10);
        WebElement element = driver.findElement(register_button_1);
        register_button.click();
    }

    public void closeAllTabs() throws InterruptedException {
        do {
            try {
                WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
                closetab.click();
                Thread.sleep(5000);
            } catch (NoSuchElementException e) {
                System.out.println("/*---there are no Tab's to close anymore");
            }
        } while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
    }

    public void verifyIsClinicInBoxPageDisplayed() {
        waitForElementToBeVisible(driver, clinicinbox_page_displayed, 10);
        this.clinicinbox_page_displayed.isDisplayed();
    }

    public void SearchDIWACitizen(String citizen ) throws InterruptedException {
        //waitForElementToBeLocated(driver,search_assistant1,10);
        //waitForElementToBeVisible(driver, search_assistant, 15);
        WebElement search_navigator = driver.findElement(search_assistant1);
        search_navigator.click();
        //  waitForElementToBeVisible(driver, search_input, 10);
        Thread.sleep(5000);
        WebElement search_input = driver.findElement(search_input1);
        search_input.sendKeys(citizen,Keys.ENTER);
        // search_input.sendKeys(Keys.RETURN);

    }

    public boolean userFound() throws InterruptedException {
        waitForElementToBeLocated(driver, user_found1, 10);
        WebElement element = driver.findElement(user_found1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        return true;
    }

    public void clickRelatedTab() throws InterruptedException {
        waitForElementToBeLocated(driver, click_related_tab1, 10);
        WebElement element = driver.findElement(click_related_tab1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickCreatImmunizationRecord() throws InterruptedException {
        waitForElementToBeLocated(driver, creat_Immunization_Record1, 10);
        WebElement element = driver.findElement(creat_Immunization_Record1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickSelectAnOptionDropdown() {
        this.select_an_option.click();
    }
//    public void inputRequestDate() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        Date tomorrow = calendar.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
//
//        String tomorrowAsString = dateFormat.format(tomorrow);
//        this.inputDate.sendKeys(tomorrowAsString, Keys.ENTER);
//    }

    public void enterFirstName(String firstname) throws InterruptedException {
        //private By doses_1 = By.xpath("(.//input[@class = 'slds-input'])[2]");
        //By dose_1_ = By.xpath("(.//input[@class = 'slds-input'])[" + k + "]");
        waitForElementToBeLocated(driver, first_name1, 10);
        //WebElement element = driver.findElement(dose_1_);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollRight = arguments[0].scrollWidth", element);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        //click(dose_1_);
        first_name.sendKeys(firstname);
    }



}




