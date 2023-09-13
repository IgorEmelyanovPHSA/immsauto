package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonAccountPage extends BasePage {
    public PersonAccountPage(WebDriver driver) {
        super(driver);
    }

    public static void goToAppointmentScheduleTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By appointment_schedule_tab_path = By.xpath("//a[@data-label='Appointment Scheduling'] | //span[text() = 'Appointment Scheduling']");
        waitForElementToBeEnabled(driver, appointment_schedule_tab_path, 10);
        WebElement appointment_schedule_tab = driver.findElement(appointment_schedule_tab_path);
        appointment_schedule_tab.click();
    }

    public static void goToVaccineScheduleTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By vaccine_schedule_tab_path = By.xpath("//a[@data-label='Vaccine Scheduling'] | //a[@title = 'Vaccine Scheduling']");
        waitForElementToBeEnabled(driver, vaccine_schedule_tab_path, 30);
        WebElement vaccine_schedule_tab = driver.findElement(vaccine_schedule_tab_path);
        waitForElementToBeVisible(driver, vaccine_schedule_tab, 10);
        vaccine_schedule_tab.click();
        waitForAttribute(driver, vaccine_schedule_tab_path, "aria-selected", "true", 10);
    }

    public static void selectEarlyBookingReason(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By select_early_booking_reason_field_path = By.xpath("//span[text() = 'Select One']");
        waitForElementToBeEnabled(driver, select_early_booking_reason_field_path, 10);
        WebElement select_early_booking_dropdown = driver.findElement(select_early_booking_reason_field_path);
        select_early_booking_dropdown.click();
        Thread.sleep(500);
        By early_booking_reason_travel_path = By.xpath("//span[text() = 'Travel']");
        waitForElementToBeEnabled(driver, early_booking_reason_travel_path, 10);
        WebElement early_booking_reason_travel = driver.findElement(early_booking_reason_travel_path);
        waitForElementToBeVisible(driver, early_booking_reason_travel, 10);
        early_booking_reason_travel.click();
    }

    public static void overrideEligibility(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By override_eligibility_btn_path = By.xpath("//button[@title='Primary action' and text()='Override Eligibility']");
        waitForElementToBeEnabled(driver, override_eligibility_btn_path, 10);
        WebElement override_eligibility_btn = driver.findElement(override_eligibility_btn_path);
        override_eligibility_btn.click();
        Thread.sleep(500);
        By reason_for_override_path = By.xpath("//button[@aria-label='Reason for Override, Select One']");
        waitForElementToBeEnabled(driver, reason_for_override_path, 10);
        WebElement reason_for_verride_btn = driver.findElement(reason_for_override_path);
        scrollTop(driver, reason_for_verride_btn, true);
        Thread.sleep(500);
        reason_for_verride_btn.click();
        Thread.sleep(500);
        By travel_item_path = By.xpath("//lightning-base-combobox-item[@data-value='Travel']");
        waitForElementToBeEnabled(driver, travel_item_path, 10);
        WebElement travel_item = driver.findElement(travel_item_path);
        scrollTop(driver, travel_item, true);
        Thread.sleep(500);
        travel_item.click();
    }

    public static void goToRelatedTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By related_tab_path = By.xpath("//a[text() = 'Related'] | //span[text() = 'Related']");
        waitForElementToBeEnabled(driver, related_tab_path, 10);
        WebElement related_tab = driver.findElement(related_tab_path);
        scrollTop(driver, related_tab, false);
        Thread.sleep(500);
        related_tab.click();
        Thread.sleep(2000);
    }

    public static void clickRefreshForecastButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By refresh_forecast_btn_path = By.xpath("//button[@title='Primary action' and text()='Refresh Forecast']");
        waitForElementToBeEnabled(driver, refresh_forecast_btn_path, 10);
        WebElement refresh_forecast_btn = driver.findElement(refresh_forecast_btn_path);
        refresh_forecast_btn.click();
        Thread.sleep(5000);
        waitForElementToBeEnabled(driver, refresh_forecast_btn_path, 10);
    }

    public static void checkBookingVaccineCheckbox(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By covid19_vaccine_checkbox_path = By.xpath("//span[text() = 'Covid-19 Vaccine']");
        By influenza_checkbox_path = By.xpath("//span[text() = 'Influenza Vaccine']");
        if (vaccine.equalsIgnoreCase("Covid19Vaccine")) {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 30);
            WebElement covid19_vaccine_checkbox = driver.findElement(covid19_vaccine_checkbox_path);
            scrollTop(driver, covid19_vaccine_checkbox, true);
            Thread.sleep(500);
            covid19_vaccine_checkbox.click();
        } else if (vaccine.equalsIgnoreCase("InfluenzaVaccine")) {
            waitForElementToBeEnabled(driver, influenza_checkbox_path, 10);
            WebElement influenza_checkbox = driver.findElement(influenza_checkbox_path);
            scrollTop(driver, influenza_checkbox, true);
            Thread.sleep(500);
            influenza_checkbox.click();
        } else {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 10);
            WebElement covid19_vaccine_checkbox = driver.findElement(covid19_vaccine_checkbox_path);
            scrollTop(driver, covid19_vaccine_checkbox, true);
            Thread.sleep(500);
            covid19_vaccine_checkbox.click();
            WebElement influenza_checkbox = driver.findElement(influenza_checkbox_path);
            scrollTop(driver, influenza_checkbox, true);
            Thread.sleep(500);
            influenza_checkbox.click();
        }
    }

    public static void select_covid_19_agent(WebDriver driver, String covid_19_agent) throws InterruptedException {
        Thread.sleep(500);
        By covid_19_agent_button_path = By.xpath("//button[@name='covidvaccine']");
        waitForElementToBeEnabled(driver, covid_19_agent_button_path, 10);
        WebElement covid_10_agent_button = driver.findElement(covid_19_agent_button_path);
        scrollTop(driver, covid_10_agent_button, true);
        Thread.sleep(500);
        covid_10_agent_button.click();
        Thread.sleep(500);
        By my_item_path = By.xpath("//lightning-base-combobox-item[@data-value='" + covid_19_agent + "']");
        waitForElementToBeEnabled(driver, my_item_path, 10);
        WebElement my_item = driver.findElement(my_item_path);
        my_item.click();
    }
}
