package bcvax.pages;

import org.openqa.selenium.*;

public class BookAppointmentPage extends BasePage {
    public BookAppointmentPage(WebDriver driver) {
        super(driver);
    }

    public static void bookAnAppointmentPageDisplayed(WebDriver driver) {
        By vaccinationAppointmentTxt = By.xpath("//button[contains(text(),'Book appointment')]");
        try {
            waitForElementToBeLocated(driver, vaccinationAppointmentTxt, 10);
        } catch (Exception e) {
            log("/*---Book An Appointment Page is NOT displayed--*/");
        }
        log("/*---Book An Appointment Page Successfully displayed--*/");
    }

    public static String getRegistrationConfirmationNumber(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By text_registration_confirmation_number_path = By.xpath("//input[@name='RegistrationCode']");
        waitForElementToBeEnabled(driver, text_registration_confirmation_number_path, 10);
        WebElement text_registration_confirmation_number = driver.findElement(text_registration_confirmation_number_path);
        return text_registration_confirmation_number.getAttribute("value");
    }

    public static void enterPhnNumberAndClickBtnBookAppointment(WebDriver driver, String personalHealthNumber) throws InterruptedException {
        Thread.sleep(500);
        By text_personal_health_number_path = By.xpath("//input[@name='PersonalHealthNumber']");
        By book_appointment_button_path = By.xpath("//button[contains(text(),'Book appointment')]");
        waitForElementToBeEnabled(driver, text_personal_health_number_path, 10);
        WebElement text_personal_health_number = driver.findElement(text_personal_health_number_path);
        text_personal_health_number.sendKeys(personalHealthNumber);
        WebElement book_appointment_button = driver.findElement(book_appointment_button_path);
        book_appointment_button.click();
    }

    public static void scheduleVaccinationAppointmentPageDisplayed(WebDriver driver) {
        By vaccinationAppointmentTxt = By.xpath("//h1[contains(text(),'vaccination appointment')]");
        try {
            waitForElementToBeLocated(driver, vaccinationAppointmentTxt, 10);
        } catch (Exception e) {
            log("/*---Schedule Vaccination Appointment Page is NOT displayed--*/");
        }
        log("/*---Schedule Vaccination Appointment Page Successfully displayed--*/");
    }

    public static boolean isToastErrorMessageCompleteAllRequiredFieldsDisplayed(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By close_toast_message_button_path = By.xpath("//button[@title='Close']");
        waitForElementToBeEnabled(driver, close_toast_message_button_path, 10);
        WebElement close_toast_message_button = driver.findElement(close_toast_message_button_path);

        try {
            By toast_error_message_path = By.xpath("//span[contains(text(),'Please complete all required fields before proceeding.')]");
            waitForElementToBeEnabled(driver, toast_error_message_path, 10);
            log("Toast message is displayed");
            close_toast_message_button.click();
            log("Closing (X) the toast message");
            return true;
        } catch (Exception e) {
            log("Could not catch toast error message");
            return false;
        }
    }
}