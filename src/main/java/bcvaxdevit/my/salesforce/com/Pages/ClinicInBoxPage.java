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

    @FindBy(xpath = "(//a[@data-label='Related'])")
    private WebElement click_related_tab;
    private By click_related_tab1 = By.xpath("//a[@data-label='Related']");

    @FindBy(xpath = "//button[contains(text(),'Create Immunization Record')]")
    private WebElement creat_Immunization_Record;
   //private By creat_Immunization_Record1 = By.xpath("/button[contains(text(),'Create Immunization Record')]");

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

    @FindBy(xpath = "(.//button[@title = 'Next'])")
    private WebElement next_button;
    private By next_button1 = By.xpath("(.//button[@title = 'Next'])");

    @FindBy(xpath = ".//a[@title='Maegan bcvaxvillage']")
    private WebElement user_found;
    private By user_found1 = By.xpath(".//a[@title='Maegan bcvaxvillage']");

    @FindBy(xpath = "//input[@name=\"BCH_Requested_Delivery_Date__c\"]")
    private WebElement inputDiwaDate;

    @FindBy(xpath = "//option[contains(text(),'COVID-19 mRNA')]")
    private WebElement covidmRna;
    private By covidmRna2 = By.xpath("//option[contains(text(),'COVID-19 mRNA')]");

    @FindBy(xpath = "// button[@title = 'icon']")
    private WebElement search_location;
    private By search_location1 = By.xpath("// button[@title = 'icon']");

    @FindBy(xpath = "//input[@data-id = 'userinput']")
    private WebElement search_clinic;
    private By search_clinic1 = By.xpath("//input[@data-id = 'userinput']");

    @FindBy(xpath = "//button[@title=\"Select a date for undefined\"]")
    private WebElement enterDate;

    @FindBy(xpath = "(.//input[@name = 'PersonEmail'])")
    private WebElement email;
    private By email1 = By.xpath("(.//input[@name = 'PersonEmail'])");

    @FindBy(xpath = "(.//input[@name = 'ConfirmEmail'])")
    private WebElement confirm_email;
    private By confirm_email1 = By.xpath("(.//input[@name = 'ConfirmEmail'])");

    @FindBy(xpath = ".//button[text()= 'Review Details']")
    private WebElement review_details;
    private By review_details1 = By.xpath("(.//button[text()= 'Review Details'])");

    @FindBy(xpath = ".//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination']")
    private WebElement select_covid19_option_from_dropdown;
    private By select_covid19_option_from_dropdown1 = By.xpath("(.//select[@name = 'typeId']/option[text() = 'COVID_19_Vaccination'])");

    @FindBy(xpath = ".//button[text() = 'Register']")
    private WebElement register_confirmation_page_button;
    private By register_confirmation_page_button1 = By.xpath(".//button[text() = 'Register']");

    @FindBy(xpath = "//a[contains(text(),'Maegan bcvaxvillage')]")
    private WebElement clickCitizen;

    public void userClickCitizen() throws InterruptedException {
        clickCitizen.click();
    }

    @FindBy(xpath = "//a[@id='relatedListsTab__item']")
    private WebElement selectCitizenInTable; //

    public void userClickInTable() throws InterruptedException {
        selectCitizenInTable.click(); //
    }

    @FindBy(xpath = "//footer//div//button[2]")
    private WebElement confirmBtn;

    @FindBy(xpath = "//button[contains(text(),'Record Immunization')]")
    private WebElement  recordImmunizationBtn;

    @FindBy(xpath = "//input[@placeholder = 'Search People...']")
    private WebElement informedConsentProvider;


    @FindBy(xpath = "//input[@name=\"effectiveToDate\"]")
    private WebElement consentEffectiveToDate;


    @FindBy(xpath = "//button[contains(text(),'Save Consent')]")
    private WebElement saveConsentBtn;



    public void clickConfirmBtn() throws InterruptedException {
        confirmBtn.click();
    }
    @FindBy(xpath = "(.//a[text() = 'Related'])")
    private WebElement person_account_Related_tab;
    private By person_account_Related_tab_1 = By.xpath("(.//a[text() = 'Related'])");

    @FindBy(xpath = "//button[@title='Check Eligibility']")
    private WebElement click_eligibility_button;
    private By click_eligibility_button1 = By.xpath("(//button[@title='Check Eligibility'])");


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
//        waitForElementToBeLocated(driver, creat_Immunization_Record1, 10);
//        WebElement element = driver.findElement(creat_Immunization_Record1);
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click();", element);
        creat_Immunization_Record.click();
    }

    public void clickSelectAnOptionDropdown() {
        this.select_an_option.click();
    }
    public void selectOption(String vaccine) throws InterruptedException {
        //waitForElementToBeLocated(driver,clinicName,10);
        waitForElementToBeVisible(driver, covidmRna, 10);
        WebElement search_input = driver.findElement(covidmRna2);
        search_input.click();
    }

    public void searchClinicLocation(String clinic) throws InterruptedException {
        //waitForElementToBeLocated(driver,search_location1,10);
        waitForElementToBeVisible(driver, search_location, 10);
        WebElement search_navigator = driver.findElement(search_location1);
        search_navigator.click();
        waitForElementToBeVisible(driver, search_clinic, 10);
        WebElement search_input = driver.findElement(search_clinic1);
        search_input.sendKeys(clinic);
        //search_input.sendKeys(Keys.RETURN);
        search_input.click();
        Thread.sleep(3000);
    }
    public void selectDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

        String todayAsString = dateFormat.format(today);
        this.enterDate.sendKeys(todayAsString, Keys.ENTER);
    }
    public void clickRecordImmunization() throws InterruptedException {
        recordImmunizationBtn.click();
    }

    public void informedConsentProvider() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", informedConsentProvider);
        waitForElementToBeVisible(driver, informedConsentProvider, 10);
        informedConsentProvider.click();
    }


    public void enterConsentEffectiveToDate() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

//    @FindBy(xpath = ".//a[@title='Maegan bcvaxvillage']")
//    private WebElement user_found;
//    private By user_found1 = By.xpath(".//a[@title='Maegan bcvaxvillage']");


        String tomorrowAsString = dateFormat.format(tomorrow);
        this.consentEffectiveToDate.sendKeys(tomorrowAsString, Keys.ENTER);
    }

    public void clickSaveConsent() throws InterruptedException {
        saveConsentBtn.click();
    }



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

    public void clickNextButton() throws InterruptedException {
        waitForElementToBeVisible(driver, next_button, 10);
        WebElement element = driver.findElement(next_button1);
        next_button.click();
    }

    public void enterEmail(String enteremail) throws InterruptedException {
        waitForElementToBeLocated(driver, email1, 10);
        email.sendKeys(enteremail);
    }

    public void confirmEmail(String confirmemail) throws InterruptedException {
        waitForElementToBeLocated(driver, confirm_email1, 10);
        confirm_email.sendKeys(confirmemail);
    }

    public void clickReviewDetails() throws InterruptedException {
        waitForElementToBeVisible(driver, review_details, 10);
        WebElement element = driver.findElement(review_details1);
        review_details.click();
    }

    public void clickRegisterButtonOnConfirmationPage() throws InterruptedException {
        waitForElementToBeLocated(driver, register_confirmation_page_button1, 10);
        WebElement element = driver.findElement(register_confirmation_page_button1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void successRegisteredMessageAppear() throws InterruptedException {
        waitForElementToBeLocated(driver, By.xpath(".//div[text() = 'Citizen Successfully Registered']"), 20);
        driver.findElement(By.xpath(".//div[text() = 'Citizen Successfully Registered']"));
        Thread.sleep(2000);
        System.out.println("/* ----the toast success Citizen Registered message has been Appears");
    }

    public void clickOnPersonAccountRelatedTab() throws InterruptedException {
        Thread.sleep(2000);
        WebElement element = driver.findElement(person_account_Related_tab_1);
        isDisplayed(person_account_Related_tab_1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickOnEligibilityButton() throws InterruptedException {
        waitForElementToBeLocated(driver, click_eligibility_button1, 10);
        WebElement element = driver.findElement(click_eligibility_button1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
		/*waitForElementToBeLocated(driver, click_eligibility_dropdown1, 10);
		WebElement element2 = driver.findElement(click_eligibility_dropdown1);
		JavascriptExecutor executor2 = (JavascriptExecutor) driver;
		executor2.executeScript("arguments[0].click();", element2);*/
       /* Thread.sleep(2000);
        waitForElementToBeLocated(driver, select_covid19_option_from_dropdown1, 10);
        WebElement element1 = driver.findElement(select_covid19_option_from_dropdown1);
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", element1);
        Thread.sleep(2000);*/
    }




}




