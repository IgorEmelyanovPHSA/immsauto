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
        PersonAccountPage.checkBookingVaccineCheckbox(driver, vaccine);
//        if(vaccine.equalsIgnoreCase("Covid19Vaccine")){
//            Thread.sleep(5000);
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
//            Thread.sleep(5000);
//            click(checkBoxCovid19Vaccine);
//        }
//        else if(vaccine.equalsIgnoreCase("InfluenzaVaccine")){
//            Thread.sleep(5000);
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
//            Thread.sleep(5000);
//            click(checkBoxInfluenzaVaccine);
//        }
//        else if(vaccine.equalsIgnoreCase("both")){
//            click(checkBoxCovid19Vaccine);
//            click(checkBoxInfluenzaVaccine);
//        }
//        else{
//            throw new RuntimeException("No vaccine selected");
//        }
    }

    public void searchByClinicName(String clinicName) throws InterruptedException{
        Thread.sleep(500);
        By tabSearchByClinicNamePath = By.xpath("//a[@data-label='Search by clinic name']");
        waitForElementToBeEnabled(driver, tabSearchByClinicNamePath, 10);
        WebElement tabSearchByClinicName = driver.findElement(tabSearchByClinicNamePath);
        scrollTop(tabSearchByClinicName, true);
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
        click(selectFirstAvailableDayInTheCalendarId);
        Thread.sleep(2000);
        //scrollTop(selectFirstAvailableTimeSlotInTheCalendar);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        Thread.sleep(500);
        click(selectFirstAvailableTimeSlotInTheCalendar);
        click(btnNext);
    }

    public void clickCheckBoxVerifyContactInformationAndConfirmAppointment() throws InterruptedException{
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        //scrollTop(checkBoxVerifyContactInformation);
        click(checkBoxVerifyContactInformation);
        click(btnConfirmAppointment);
    }

    public boolean appointmentConfirmationPageDisplayed() {
        By successMessage = By.xpath("//div[contains(text(),'Appointment confirmed!')]");
        try {
            waitForElementToBeLocated(driver, successMessage, 10);
            log("/*---Appointment confirmed! Page Successfully displayed--*/");
            return true;
        } catch (Exception e){
            log("/*---Appointment confirmation page is NOT displayed--*/");
            return false;
        }

    }
}