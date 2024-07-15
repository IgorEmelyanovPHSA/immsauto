package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PersonAccountSchedulePage extends BasePage {
    public PersonAccountSchedulePage(WebDriver driver) {
        super(driver);
    }

    public static void overrideEligibility(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By override_eligibility_btn_path = By.xpath("//button[@title='Primary action' and text()='Override Eligibility']");
        waitForElementToBeEnabled(driver, override_eligibility_btn_path, 30);
        WebElement override_eligibility_btn = driver.findElement(override_eligibility_btn_path);
        override_eligibility_btn.click();
        Thread.sleep(500);
        By reason_for_override_path = By.xpath("//button[@aria-label='Reason for Override, Select One' or @aria-label='Reason for Override - Current Selection: Select One' or @aria-label='Reason for Override']");
        waitForElementToBeEnabled(driver, reason_for_override_path, 10);
        WebElement reason_for_verride_btn = driver.findElement(reason_for_override_path);
        scrollCenter(driver, reason_for_verride_btn);
        Thread.sleep(500);
        reason_for_verride_btn.click();
        Thread.sleep(500);
        By travel_item_path = By.xpath("//lightning-base-combobox-item[@data-value='Travel']");
        waitForElementToBeEnabled(driver, travel_item_path, 10);
        WebElement travel_item = driver.findElement(travel_item_path);
        scrollCenter(driver, travel_item);
        Thread.sleep(500);
        travel_item.click();
    }

    public static void checkBookingVaccineCheckbox(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By covid19_vaccine_checkbox_path = By.xpath("//span[text() = 'Covid-19 Vaccine']");
        By influenza_checkbox_path = By.xpath("//span[text() = 'Influenza Vaccine']");
        if (vaccine.equalsIgnoreCase("Covid19Vaccine")) {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 10);
            WebElement covid19_vaccine_checkbox = driver.findElement(covid19_vaccine_checkbox_path);
            scrollCenter(driver, covid19_vaccine_checkbox);
            Thread.sleep(500);
            covid19_vaccine_checkbox.click();
        } else if (vaccine.equalsIgnoreCase("InfluenzaVaccine")) {
            waitForElementToBeEnabled(driver, influenza_checkbox_path, 10);
            WebElement influenza_checkbox = driver.findElement(influenza_checkbox_path);
            scrollCenter(driver, influenza_checkbox);
            Thread.sleep(500);
            influenza_checkbox.click();
        } else {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 10);
            WebElement covid19_vaccine_checkbox = driver.findElement(covid19_vaccine_checkbox_path);
            scrollCenter(driver, covid19_vaccine_checkbox);
            Thread.sleep(500);
            covid19_vaccine_checkbox.click();
            WebElement influenza_checkbox = driver.findElement(influenza_checkbox_path);
            scrollCenter(driver, influenza_checkbox);
            Thread.sleep(500);
            influenza_checkbox.click();
        }
    }

    public static void selectCovidAgent(WebDriver driver, String agent) throws InterruptedException {
        Thread.sleep(500);
        By covid_agent_combo_path = By.xpath("//lightning-combobox[@data-id='Covid-19 Vaccine']");
        waitForElementToBeEnabled(driver, covid_agent_combo_path, 10);
        WebElement covid_agent_combo = driver.findElement(covid_agent_combo_path);
        scrollCenter(driver, covid_agent_combo);
        Thread.sleep(500);
        WebElement combo_button = covid_agent_combo.findElement(By.xpath(".//button"));
        combo_button.click();
        List<WebElement> my_items = covid_agent_combo.findElements(By.xpath(".//lightning-base-combobox-item"));
        for(WebElement my_item: my_items) {
            if(my_item.getAttribute("data-value").equals(agent)) {
                my_item.click();
                Thread.sleep(500);
                break;
            }
        }
    }

    public static void selectSearchByClinicNameTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By search_by_clinic_name_tab_path = By.xpath(".//a[text()='Search by clinic name']");
        waitForElementToBeEnabled(driver, search_by_clinic_name_tab_path, 10);
        WebElement search_by_clinic_name_tab = driver.findElement(search_by_clinic_name_tab_path);
        scrollCenter(driver, search_by_clinic_name_tab);
        Thread.sleep(500);
        search_by_clinic_name_tab.click();
    }

    public static void searchClinicName(WebDriver driver, String clinicNameToSearch) throws InterruptedException {
        By search_clinic_input_path = By.xpath("//input[@name='clinicstag']");
        waitForElementToBeEnabled(driver, search_clinic_input_path, 10);
        WebElement search_clinic_name = driver.findElement(search_clinic_input_path);
        scrollCenter(driver, search_clinic_name);
        Thread.sleep(500);
        search_clinic_name.click();
        Thread.sleep(500);
        search_clinic_name.sendKeys(clinicNameToSearch);
        search_clinic_name.sendKeys(Keys.RETURN);
    }

    public static void clickOnFacilityOptionLocation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By option_location__facility_path = By.xpath("//button[@name = 'facility']");
        waitForElementToBeEnabled(driver, option_location__facility_path, 10);
        WebElement option_location_facility = driver.findElement(option_location__facility_path);
        scrollCenter(driver, option_location_facility);
        option_location_facility.click();
    }

    public static void selectBookingAppointmentDay(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By booking_days_path = By.xpath("//button[@class = 'slds-day active-day']");
        waitForElementToBeEnabled(driver, booking_days_path, 10);
        List<WebElement> booking_days = driver.findElements(booking_days_path);
        scrollCenter(driver, booking_days.get(0));
        booking_days.get(0).click();
    }

    public static void selectBookingAppointmentDay(WebDriver driver, int day) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> myDays = driver.findElements(By.xpath("//button[@class = 'slds-day active-day']"));
        scrollCenter(driver, myDays.get(day));
        myDays.get(day).click();
    }

    public static void selectTimeSlotForAppointment(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By time_slot_appointment_path = By.xpath("//button[@name='timeslot']");
        waitForElementToBeEnabled(driver, time_slot_appointment_path, 10);
        List<WebElement> elements = driver.findElements(time_slot_appointment_path);
        scrollCenter(driver, elements.get(0));
        Thread.sleep(500);
        elements.get(0).click();
    }

    public static void clickNextButtonApptSchedulingPage(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By next_btn_path = By.xpath("//button[@c-bchappointmentscheduler_bchappointmentscheduler and text()='Next']");
        waitForElementToBeEnabled(driver, next_btn_path, 10);
        WebElement next_btn = driver.findElement(next_btn_path);
        next_btn.click();
        //Wait for Confirm button to appear
        Thread.sleep(500);
        By appt_confirm_btn_path = By.xpath("//button[text() = 'Confirm appointment']");
        waitForElementToBeEnabled(driver, appt_confirm_btn_path, 10);
    }

    public static void clickVerifyContactInformation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By verify_contact_information_checkbox_path = By.xpath("//span[text()='I verify that the contact information (email address and phone number) entered is accurate and up to date.']/../span[@class='slds-checkbox_faux']");
        waitForElementToBeEnabled(driver, verify_contact_information_checkbox_path, 10);
        WebElement verify_contact_information_checkbox = driver.findElement(verify_contact_information_checkbox_path);
        Thread.sleep(1000);
        scrollCenter(driver, verify_contact_information_checkbox);
        Thread.sleep(1000);
        verify_contact_information_checkbox.click();
        Thread.sleep(500);
    }

    public static void clickVerifyConsentInformation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By verify_contact_information_checkbox_path = By.xpath("//span[text()='I consent to notifications being sent to my preferred contact method for the purpose of informing me about my pharmacy appointment.']/../span[@class='slds-checkbox_faux']");
        waitForElementToBeEnabled(driver, verify_contact_information_checkbox_path, 10);
        WebElement verify_contact_information_checkbox = driver.findElement(verify_contact_information_checkbox_path);
        Thread.sleep(1000);
        scrollCenter(driver, verify_contact_information_checkbox);
        Thread.sleep(1000);
        verify_contact_information_checkbox.click();
        Thread.sleep(500);
    }

    public static void clickOnConfirmButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_appointment_btn_path = By.xpath("//button[@c-bchschedulerreviewfooter_bchschedulerreviewfooter and text() = 'Confirm appointment']");
        waitForElementToBeEnabled(driver, confirm_appointment_btn_path, 10);
        WebElement confirm_appointment_button = driver.findElement(confirm_appointment_btn_path);
        confirm_appointment_button.click();
    }

    public static boolean appointmentConfirmationMessage(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        try {
            By appointment_confirm_message_path = By.xpath("//h1[@class='bch-scheduler-title'] | //div[contains(@class, 'bch-scheduler-title')]");
            waitForElementToBeLocated(driver, appointment_confirm_message_path, 30);
            System.out.println("/*---'Appointment confirmed!' message shown up");
            return true;
        } catch (TimeoutException e) {
            System.out.println("/*---the screen does not show up 'Appointment confirmed!'");
            return false;
        }
    }
}
