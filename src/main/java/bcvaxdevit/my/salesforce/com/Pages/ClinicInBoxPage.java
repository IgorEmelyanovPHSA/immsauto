package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

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

//    @FindBy(xpath = ".//a[@title='Maegan BCVaxVillage']")
//    private WebElement user_found;
//    private By user_found1 = By.xpath(".//a[@title='Maegan BCVaxVillage']");

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

    @FindBy(xpath = "(.//input[@name = 'PersonBirthdate'])")
    private WebElement date_of_birth;
    private By date_of_birth1 =By.xpath("(.//input[@name = 'PersonBirthdate'])");

    @FindBy(xpath = "(.//input[@name = 'DDH_HC_Zip_Code'])")
    private WebElement postal_code;
    private By postal_code1 =By.xpath("(.//input[@name = 'DDH_HC_Zip_Code'])");

    @FindBy(xpath = "(.//input[@name = 'HC_Personal_Health_Number'])")
    private WebElement phn;
    private By phn1 =By.xpath("(.//input[@name = 'HC_Personal_Health_Number'])");

    @FindBy(xpath = "(.//input[@name = 'BCH_Indigenous'])[2]")
    private WebElement non_indigenous_radio_button;
    private By non_indigenous_radio_button1 = By.xpath("(.//input[@name = 'BCH_Indigenous'])[2]");

    @FindBy(xpath = ".//button[@title = 'Verify Personal Health Number']")
    private WebElement verify_phn_button;
    private By verify_phn_button1 = By.xpath("(.//button[@title = 'Verify Personal Health Number'])");

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
        waitForElementToBeVisible(driver, search_assistant, 10);
        WebElement search_navigator = driver.findElement(search_assistant1);
        search_navigator.click();
        waitForElementToBeVisible(driver, search_input, 10);
        WebElement search_input = driver.findElement(search_input1);
        search_input.sendKeys(citizen);
        search_input.sendKeys(Keys.RETURN);

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
        waitForElementToBeLocated(driver, first_name1, 10);
        first_name.sendKeys(firstname);
    }

    public void enterLastName(String lastname) throws InterruptedException {
        waitForElementToBeLocated(driver, last_name1, 10);
        last_name.sendKeys(lastname);
    }

    public void enterDateOfBirth(String dateofbirth) throws InterruptedException {
        waitForElementToBeLocated(driver, date_of_birth1, 10);
        date_of_birth.sendKeys(dateofbirth);
    }

    public void enterPostalCode(String postalcode) throws InterruptedException {
        waitForElementToBeLocated(driver, postal_code1, 10);
        postal_code.sendKeys(postalcode);
    }

    public void enterPNH(String phn_number) throws InterruptedException {
        waitForElementToBeLocated(driver, phn1, 10);
        phn.sendKeys(phn_number);
    }

    public void clickNonIndigenousRadioButton() throws InterruptedException {
        waitForElementToBeVisible(driver, non_indigenous_radio_button, 10);
        WebElement element = driver.findElement(non_indigenous_radio_button1);
        non_indigenous_radio_button.click();
    }

    public void clickVerifyPHNButton() throws InterruptedException {
        waitForElementToBeVisible(driver, verify_phn_button, 10);
        WebElement element = driver.findElement(verify_phn_button1);
        verify_phn_button.click();
    }

    public void successMessageAppear() throws InterruptedException {
        //try {
            waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Success']"), 20);
            driver.findElement(By.xpath(".//div[text() = 'Success']"));
            Thread.sleep(2000);
            System.out.println("/* ----the toast success message has been Appears");
       // } catch (NoSuchElementException e) {
            //System.out.println("/*---there are no success toast Message for Verify PHN to be Appears");
            //throw new RuntimeException("/*---there are no success Message to be Appears--*/");
       // }
    }

    @FindBy(xpath = ".//a[@title='Maegan bcvaxvillage']")
    private WebElement user_found;
    private By user_found1 = By.xpath(".//a[@title='Maegan bcvaxvillage']");





    @FindBy(xpath = "//input[@name=\"BCH_Requested_Delivery_Date__c\"]")
    private WebElement inputDiwaDate;

}




