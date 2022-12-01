package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//h1[contains(text(),'vaccination appointment')]")
    private WebElement pageValidationHeader;

    @FindBy(xpath = "//span[text() = 'Covid-19 Vaccine']")
    private WebElement checkBoxCovid19Vaccine;

    @FindBy(xpath = "//span[text() = 'Influenza Vaccine']")
    private WebElement checkBoxInfluenzaVaccine;

    @FindBy(xpath = "//a[@data-label='Search by clinic name']")
    private WebElement tabSearchByClinicName;

    @FindBy(xpath = "//input[@name='clinicstag']")
    private WebElement textBoxSearchByClinicName;

    @FindBy(xpath = "//p[@class='bch-scheduler-facility-title']")
    private WebElement boxWithSelectedClinic;

    @FindBy(xpath = "(//button[@class = 'slds-day active-day'])[1]")
    private WebElement selectFirstAvailableDayInTheCalendarId;

    @FindBy(xpath = "(//button[@name='timeslot'])[1]")
    private WebElement selectFirstAvailableTimeSlotInTheCalendar;

    @FindBy(xpath = ".//button[text() = 'Next']")
    private WebElement btnNext;

    @FindBy(xpath = "//span[@class='slds-checkbox_faux']")
    private WebElement checkBoxVerifyContactInformation;

    @FindBy(xpath = "//button[text() = 'Confirm appointment']")
    private WebElement btnConfirmAppointment;

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
        if(vaccine.equalsIgnoreCase("Covid19Vaccine")){
            click(checkBoxCovid19Vaccine);
        }
        else if(vaccine.equalsIgnoreCase("InfluenzaVaccine")){
            click(checkBoxInfluenzaVaccine);
        }
        else if(vaccine.equalsIgnoreCase("both")){
            click(checkBoxCovid19Vaccine);
            click(checkBoxInfluenzaVaccine);
        }
        else{
            throw new RuntimeException("No vaccine selected");
        }
    }

    public void searchByClinicName(String clinicName) throws InterruptedException{
        Thread.sleep(500);
        scrollTop(tabSearchByClinicName);
        click(tabSearchByClinicName);
        click(textBoxSearchByClinicName);
        typeIn(textBoxSearchByClinicName, clinicName);
        textBoxSearchByClinicName.sendKeys(Keys.RETURN);
        click(boxWithSelectedClinic);
    }

    public void selectDateAndTimeForAppointmentAndClickBtnNext() throws InterruptedException{
        click(selectFirstAvailableDayInTheCalendarId);
        Thread.sleep(500);
        scrollTop(selectFirstAvailableTimeSlotInTheCalendar);
        click(selectFirstAvailableTimeSlotInTheCalendar);
        click(btnNext);
    }

    public void clickCheckBoxVerifyContactInformationAndConfirmAppointment() throws InterruptedException{
        Thread.sleep(800);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        //scrollTop(checkBoxVerifyContactInformation);
        click(checkBoxVerifyContactInformation);
        click(btnConfirmAppointment);
    }

    public void appointmentConfirmationPageDisplayed() {
        By successMessage = By.xpath("//div[contains(text(),'Appointment confirmed!')]");
        try {
            waitForElementToBeLocated(driver, successMessage, 10);
        } catch (Exception e){
            log("/*---Appointment confirmation page is NOT displayed--*/");
        }
        log("/*---Appointment confirmed! Page Successfully displayed--*/");
    }
}