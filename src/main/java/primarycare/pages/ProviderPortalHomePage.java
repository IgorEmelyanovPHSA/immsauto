package primarycare.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProviderPortalHomePage extends BasePage{
    /*---------Properties-------*/
    @FindBy(xpath = ".//strong[text() = 'Register to get a doctor or nurse practitioner']")
    private static WebElement register_to_get_doctor_page_validation;

    /*---------Constructor-------*/
    public ProviderPortalHomePage(WebDriver driver) {
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

}
