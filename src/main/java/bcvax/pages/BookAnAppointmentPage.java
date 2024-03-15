package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class BookAnAppointmentPage extends BasePage{

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Book an appointment section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//input[@name='RegistrationCode']")
    private WebElement textRegistrationConfirmationNumber;

    @FindBy(xpath = "//input[@name='PersonalHealthNumber']")
    private WebElement textPersonalHealthNumber;

    @FindBy(xpath = "//button[contains(text(),'Book appointment')]")
    private WebElement btnBookAppointment;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Schedule appointment section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = ".//button[text() = 'Next']")
    private WebElement btnNext;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Confirm Booking section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private By toastErrorMessage = By.xpath("//span[contains(text(),'Please complete all required fields before proceeding.')]");

    @FindBy(xpath = "//button[@title='Close']")
    private WebElement btnCloseToastMessage;

    @FindBy(xpath = "//*[contains(text(), 'Booking Confirmed')]")
    private WebElement textBookConfirmed;

    @FindBy(xpath = "//div[@class='slds-col slds-p-left_small slds-p-bottom_xx-large']/div")
    private WebElement textGetDateAndTime;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BookAnAppointmentPage(WebDriver driver) {super(driver);}

    public void bookAnAppointmentPageDisplayed() {
        By vaccinationAppointmentTxt = By.xpath("//button[contains(text(),'Book appointment')]");
        try {
            waitForElementToBeLocated(driver, vaccinationAppointmentTxt, 10);
        } catch (Exception e){
            log("/*---Book An Appointment Page is NOT displayed--*/");
        }
        log("/*---Book An Appointment Page Successfully displayed--*/");
    }

    public String getRegistrationConfirmationNumber() {
        String registrationConfirmationNumber = getValue(textRegistrationConfirmationNumber);
        return registrationConfirmationNumber;
    }

    public void enterPhnNumberAndClickBtnBookAppointment(String personalHealthNumber) throws InterruptedException {
        typeIn(textPersonalHealthNumber, personalHealthNumber);
        click(btnBookAppointment);
    }

    public void scheduleVaccinationAppointmentPageDisplayed() {
        By vaccinationAppointmentTxt = By.xpath("//h1[contains(text(),'vaccination appointment')]");
        try {
            waitForElementToBeLocated(driver, vaccinationAppointmentTxt, 10);
        } catch (Exception e){
            log("/*---Schedule Vaccination Appointment Page is NOT displayed--*/");
        }
        log("/*---Schedule Vaccination Appointment Page Successfully displayed--*/");
    }
        public boolean isToastErrorMessageCompleteAllRequiredFieldsDisplayed() throws InterruptedException {
            try {
                waitForElementToBeLocated(driver, toastErrorMessage, 10);
                log("Toast message is displayed");
                click(btnCloseToastMessage);
                log("Closing (X) the toast message");
                return true;
            } catch (Exception e) {
                log("Could not catch toast error message");
                return false;
            }
    }
}