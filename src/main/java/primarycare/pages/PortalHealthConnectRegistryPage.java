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
}
