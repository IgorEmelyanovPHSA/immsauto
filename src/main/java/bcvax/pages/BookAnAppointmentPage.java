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

    public void selectOneOption(String vaccine) throws InterruptedException{
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine);
    }

    public void searchByClinicName(String clinicName) throws InterruptedException{
        Thread.sleep(500);
        By tabSearchByClinicNamePath = By.xpath("//a[@data-label='Search by clinic name']");
        waitForElementToBeEnabled(driver, tabSearchByClinicNamePath, 10);
        WebElement tabSearchByClinicName = driver.findElement(tabSearchByClinicNamePath);
        scrollCenter(tabSearchByClinicName);
        Thread.sleep(500);
        tabSearchByClinicName.click();
        Thread.sleep(500);
        By textBoxSearchByClinicNamePath = By.xpath("//input[@name='clinicstag']");
        waitForElementToBeEnabled(driver, textBoxSearchByClinicNamePath, 10);
        WebElement textBoxSearchByClinicName = driver.findElement(textBoxSearchByClinicNamePath);
        textBoxSearchByClinicName.click();
        typeIn(textBoxSearchByClinicName, clinicName);
        textBoxSearchByClinicName.sendKeys(Keys.RETURN);
        Thread.sleep(500);
        By boxWithSelectedClinicPath = By.xpath("//p[@class='bch-scheduler-facility-title']");
        waitForElementToBeEnabled(driver, boxWithSelectedClinicPath, 10);
        WebElement boxWithSelectedClinic = driver.findElement(boxWithSelectedClinicPath);
        boxWithSelectedClinic.click();
    }

    public void selectDateAndTimeForAppointmentAndClickBtnNext() throws InterruptedException{
        Thread.sleep(500);
        By booking_days_path = By.xpath("//button[@class = 'slds-day active-day']");
        waitForElementToBeEnabled(driver, booking_days_path, 10);
        List<WebElement> booking_days = driver.findElements(booking_days_path);
        scrollCenter(booking_days.get(0));
        booking_days.get(0).click();
        //click(selectFirstAvailableDayInTheCalendarId);
        //Thread.sleep(2000);
        //scrollTop(selectFirstAvailableTimeSlotInTheCalendar);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        Thread.sleep(500);
        //click(selectFirstAvailableTimeSlotInTheCalendar);
        By booking_time_slots_path = By.xpath("//button[@name='timeslot']");
        waitForElementToBeEnabled(driver, booking_time_slots_path, 10);
        List<WebElement> myDays = driver.findElements(booking_time_slots_path);
        scrollCenter(driver, myDays.get(0));
        myDays.get(0).click();
        click(btnNext);
    }

    public void clickCheckBoxVerifyContactInformationAndConfirmAppointment() throws InterruptedException{
        Thread.sleep(500);
        By verify_contact_info_chkbox_path = By.xpath("//span[text()='I verify that the contact information (email address and phone number) entered is accurate and up to date.']/../span[@class='slds-checkbox_faux']");
        waitForElementToBeEnabled(driver, verify_contact_info_chkbox_path, 10);
        WebElement verify_contact_chkbox = driver.findElement(verify_contact_info_chkbox_path);
        scrollIfNeeded(driver, verify_contact_chkbox);
        verify_contact_chkbox.click();
        Thread.sleep(500);
        By confirm_appointment_btn_path = By.xpath("//button[text() = 'Confirm appointment']");
        WebElement confirm_appointment_btn = driver.findElement(confirm_appointment_btn_path);
        scrollIfNeeded(driver, confirm_appointment_btn);
        confirm_appointment_btn.click();
    }

    public boolean appointmentConfirmationPageDisplayed() {
        By successMessage = By.xpath("//div[contains(text(),'Appointment confirmed!')] | //h1[text() = 'Appointment confirmed!']");
        try {
            waitForElementToBeLocated(driver, successMessage, 10);
            log("/*---Appointment confirmed! Page Successfully displayed--*/");
            return true;
        } catch (Exception e) {
            log("/*---Appointment confirmation page is NOT displayed--*/");
            return false;
        }
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


        public void clickCheckBoxVerifyContactInformationAndConsentToNotifications() throws InterruptedException{
            Thread.sleep(500);
            By checkbox_i_consent_path = By.xpath("//span[text()='I consent to notifications being sent to my preferred contact method for the purpose of informing me about my pharmacy appointment.']/../../../..");
            By checkbox_i_verify_path = By.xpath("//span[text()='I verify that the contact information (email address and phone number) entered is accurate and up to date.']/../../../..");
            waitForElementToBeEnabled(driver, checkbox_i_verify_path, 10);
            WebElement checkBoxIVerifyThatTheContactInformation = driver.findElement(checkbox_i_verify_path);
            scrollIfNeeded(driver,checkBoxIVerifyThatTheContactInformation);

            Thread.sleep(500);
            if(checkBoxIVerifyThatTheContactInformation.getAttribute("checked") == null) {
                checkBoxIVerifyThatTheContactInformation.findElement(By.xpath("./div/span")).click();
            }
            checkBoxIVerifyThatTheContactInformation.sendKeys(Keys.TAB);
            Thread.sleep(500);
            WebElement checkBoxIConsentToNotifications = driver.findElement(checkbox_i_consent_path);
            if(checkBoxIConsentToNotifications.getAttribute("checked") == null) {
                checkBoxIConsentToNotifications.findElement(By.xpath("./div[@class='slds-form-element__control slds-grow']/span[@part='input-checkbox']/input")).sendKeys(Keys.SPACE);
            }
        }

        public void clickBtnConfirmAppointment() throws InterruptedException {
            Thread.sleep(500);
            By confirm_appointment_btn_path = By.xpath("//button[text() = 'Confirm appointment']");
            waitForElementToBeEnabled(driver, confirm_appointment_btn_path, 10);
            WebElement btnConfirmAppointment = driver.findElement(confirm_appointment_btn_path);
            btnConfirmAppointment.click();
        }

        public boolean isBookingConfirmedDisplayed() throws InterruptedException {
        boolean isBookingDisplayedFlag = false;
            for(int i = 1; i<=15; i++ ) {
                if(textBookConfirmed.isDisplayed()==false){
                    Thread.sleep(1000);
                    log("Re-try: Booking confirmation page is NOT displayed");
                }else {
                    isBookingDisplayedFlag = true;
                    log("Booking Confirmed! Page Successfully displayed");
                    break;
                }
            }
            return isBookingDisplayedFlag;
        }

        public String getConfirmedAppointmentDateTime() {
            String dateAndTime = textGetDateAndTime.getText();
            log("Confirmed Appointment Date and Time: " +dateAndTime);
            return dateAndTime;
         }

    }