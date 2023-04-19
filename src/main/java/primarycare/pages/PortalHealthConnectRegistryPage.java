package primarycare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalHealthConnectRegistryPage extends BasePage{

    /*---------Properties-------*/
    @FindBy(xpath = ".//strong[text() = 'Register to get a doctor or nurse practitioner']")
    private static WebElement register_to_get_doctor_page_validation;

    @FindBy(xpath = ".//span[text() = 'Next']")
    private WebElement next_button;
    private By next_button_1 = By.xpath(".//span[text() = 'Next']");

    @FindBy(xpath = ".//strong[text() = 'Who is registering']")
    private static WebElement who_is_registering_page_validation;

    @FindBy(xpath = ".//span[text() = 'Register my household']")
    private WebElement register_my_household_button;
    private By register_my_household_button_1 = By.xpath(".//span[text() = 'Register my household']");

    @FindBy(xpath = ".//omnistudio-omniscript-text[@data-omni-key='firstName']//input")
    private WebElement first_name;
    private By first_name1 = By.xpath("//omnistudio-omniscript-text[@data-omni-key='firstName']//input");

    @FindBy(xpath = ".//omnistudio-omniscript-text[@data-omni-key='lastName']//input")
    private WebElement last_name;
    private By last_name1 = By.xpath("//omnistudio-omniscript-text[@data-omni-key='lastName']//input");

    @FindBy(xpath = ".//omnistudio-omniscript-text[@data-omni-key='phn']//input")
    private WebElement phn;
    private By phn1 = By.xpath("//omnistudio-omniscript-text[@data-omni-key='phn']//input");

    @FindBy(xpath = ".//omnistudio-omniscript-text[@data-omni-key='Month']//input")
    private WebElement date_of_birth_MM;
    private By date_of_birth_MM1 = By.xpath("//omnistudio-omniscript-text[@data-omni-key='Month']//input");

    @FindBy(xpath = ".//omnistudio-omniscript-text[@data-omni-key='Day']//input")
    private WebElement date_of_birth_DD;
    private By date_of_birth_DD1 = By.xpath("//omnistudio-omniscript-text[@data-omni-key='Day']//input");

    @FindBy(xpath = ".//omnistudio-omniscript-text[@data-omni-key='Year']//input")
    private WebElement date_of_birth_YY;
    private By date_of_birth_YY1 = By.xpath("//omnistudio-omniscript-text[@data-omni-key='Year']//input");


    /*---------Constructor-------*/
    public PortalHealthConnectRegistryPage(WebDriver driver) {
        super(driver);
    }



    /*-------------Methods--------------*/
    public static boolean validateRegisterToGetDoctorPageDisplayed() throws InterruptedException {
        try {
            waitForElementToBeVisible(driver, register_to_get_doctor_page_validation, 10);
            log("/*---Home Register to get a doctor/nurse page - shown up");
        } catch (Exception e) {
            log("/*---Home Register to get a doctor/nurse page has NOT show up");
            throw e;
        }
        return false;
    }

    public void clickNextButton() throws InterruptedException {
        waitForElementToBeVisible(driver, next_button, 10);
        WebElement element = driver.findElement(next_button_1);
        next_button.click();
    }

    public static boolean validateWhoIsRegisteringPageDisplayed() throws InterruptedException {
        try {
            waitForElementToBeVisible(driver, who_is_registering_page_validation, 10);
            System.out.println("/*---Who is Registering page - shown up");
        } catch (NoSuchElementException e) {
            System.out.println("/*---Who is Registering page page has NOT show up");
            throw e;
        }
        return false;
    }

    public void clickRegisterMyHouseholdButton() throws InterruptedException {
        waitForElementToBeVisible(driver, register_my_household_button, 10);
        WebElement element = driver.findElement(register_my_household_button_1);
        register_my_household_button.click();
    }

    public void enterFirstName(String firstname) throws InterruptedException {
        waitForElementToBeLocated(driver, first_name1, 10);
        first_name.sendKeys(firstname);
    }

    public void enterLastName(String lastname) throws InterruptedException {
        waitForElementToBeLocated(driver, last_name1, 10);
        last_name.sendKeys(lastname);
    }

    public void enterPHN(String personalHealthNumber) throws InterruptedException {
        waitForElementToBeLocated(driver, phn1, 10);
        phn.sendKeys(personalHealthNumber);
    }

    public void enterMonth(String month) throws InterruptedException {
        waitForElementToBeLocated(driver, date_of_birth_MM1, 10);
        date_of_birth_MM.sendKeys(month);
    }

    public void enterDay(String day) throws InterruptedException {
        waitForElementToBeLocated(driver, date_of_birth_DD1, 10);
        date_of_birth_DD.sendKeys(day);
    }

    public void enterYear(String year) throws InterruptedException {
        waitForElementToBeLocated(driver, date_of_birth_YY1, 10);
        date_of_birth_YY.sendKeys(year);
    }
}
