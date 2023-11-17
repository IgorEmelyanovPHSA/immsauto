package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

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
        waitForElementToBeEnabled(driver, override_eligibility_btn_path, 30);
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
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, refresh_forecast_btn_path, 10);
    }

    public static void checkBookingVaccineCheckbox(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By covid19_vaccine_checkbox_path = By.xpath("//span[text() = 'Covid-19 Vaccine']");
        By influenza_checkbox_path = By.xpath("//span[text() = 'Influenza Vaccine']");
        if (vaccine.equalsIgnoreCase("Covid19Vaccine")) {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 60);
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

    public static void cancelProfileNotLinkedToPIRWarning(WebDriver driver) throws InterruptedException{
        By cancel_btn_path = By.xpath("//button[@c-bchcmodal_bchcmodal and text()='Cancel']");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, cancel_btn_path, 10);
        WebElement cancel_btn = driver.findElement(cancel_btn_path);
        cancel_btn.click();
        Thread.sleep(500);
    }

    public static void confirmNoForecastWarning(WebDriver driver) throws InterruptedException{
        By confirm_btn_path = By.xpath("//button[@c-bchcmodal_bchcmodal and text()='Confirm']");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, confirm_btn_path, 10);
        WebElement confirm_btn = driver.findElement(confirm_btn_path);
        confirm_btn.click();
    }

    public static void clickVerifyPHNButton(WebDriver driver) throws InterruptedException {
        By verify_phn_btn_path = By.xpath("//button[@title='Verify Personal Health Number']");
        waitForElementToBeEnabled(driver, verify_phn_btn_path, 10);
        WebElement verify_phn_btn = driver.findElement(verify_phn_btn_path);
        verify_phn_btn.click();
    }

    public static void successMessageAppear(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By message_path = By.xpath("//div[text() = 'Success'] | //h2[@c-bchcvacinnepreregistrationinternal_bchcvacinnepreregistrationinternal and text() = 'Match Unsuccessful']");
        log("  -- success message has been Appears. Closing... - /");
        try {
            waitForElementToBeEnabled(driver, message_path, 10);
            String message = driver.findElement(message_path).getText();
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("Probably alert already closed. Continue...");
        }
    }

    public static void clickCheckInButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By checkin_btn_path = By.xpath("//button[@class = 'slds-button slds-button_brand' and @title = 'Check-in Client']");
        waitForElementToBeEnabled(driver, checkin_btn_path, 10);
        WebElement check_in_button = driver.findElement(checkin_btn_path);
        check_in_button.click();
    }

    public static int getImmunizationRecordsSize(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By immunization_records_table_path = By.xpath("//c-bc-hc-client-immunization-records");
        waitForElementToBeEnabled(driver, immunization_records_table_path, 10);
        WebElement immunization_records_table = driver.findElement(immunization_records_table_path);
        GenericTable immuizationRecordsTable = new GenericTable(immunization_records_table);
        int table_size = immuizationRecordsTable.getRows().size() - 1;
        return table_size;
    }

    public static String getImmunizationRecordPathwayStatus(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By immunization_records_table_path = By.xpath("//c-bc-hc-client-immunization-records");
        waitForElementToBeEnabled(driver, immunization_records_table_path, 10);
        WebElement immunization_records_table = driver.findElement(immunization_records_table_path);
        GenericTable immuizationRecordsTable = new GenericTable(immunization_records_table);
        List<Map<String, WebElement>> my_table = immuizationRecordsTable.getRowsMappedToHeadings();
        System.out.println("Found " + my_table.size() + "rows");
        WebElement my_view = my_table.get(1).get("Pathway Status");
        String my_pathway_status = my_view.getText();
        return my_pathway_status;
    }
}
