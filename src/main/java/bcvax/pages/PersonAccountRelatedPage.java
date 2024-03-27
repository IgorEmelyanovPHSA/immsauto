package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class PersonAccountRelatedPage extends BasePage {
    public PersonAccountRelatedPage(WebDriver driver) {
        super(driver);
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

    public static String getActiveConsentsResponse(WebDriver driver, String agent) throws InterruptedException {
        Thread.sleep(500);
        By active_consents_path = By.xpath("//c-bchc-active-consent-table");
        By active_consent_new_btn_path = By.xpath("//c-bchc-active-consent-table//button[text()='New']");
        waitForElementToBeEnabled(driver, active_consent_new_btn_path, 10);
        WebElement active_consent_new_btn = driver.findElement(active_consent_new_btn_path);
        scrollCenter(driver, active_consent_new_btn);
        Thread.sleep(1000);
        WebElement active_consent_table_element = driver.findElement(active_consents_path);
        GenericTable related_active_consents = new GenericTable(active_consent_table_element);
        List<Map<String, WebElement>> my_rows = related_active_consents.getRowsMappedToHeadings();
        String my_response = null;
        for(Map<String, WebElement> my_row : my_rows) {
            if(my_row.get("Agent").getText().equals(agent)) {
                my_response = my_row.get("Response").getText();
                break;
            }
        }
        return my_response;
    }

    public static void clickNewActiveConsentButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By active_consent_new_btn_path = By.xpath("//c-bchc-active-consent-table//button[text()='New']");
        waitForElementToBeEnabled(driver, active_consent_new_btn_path, 10);
        WebElement active_consent_new_btn = driver.findElement(active_consent_new_btn_path);
        scrollCenter(driver, active_consent_new_btn);
        Thread.sleep(1000);
        scrollCenter(driver, active_consent_new_btn);
        Thread.sleep(1000);
        active_consent_new_btn.click();
    }

    public static List<Map<String, WebElement>> getImmunizationRecords(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        By immunization_records_table_path = By.xpath("//c-bc-hc-client-immunization-records");
        WebElement immunization_records_table_node = driver.findElement(immunization_records_table_path);
        GenericTable immunization_table = new GenericTable(immunization_records_table_node);
        return immunization_table.getRowsMappedToHeadings();
    }

    public static void scrollToAlertsSection(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        int counter = 0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By alert_section_path = By.xpath("//article[@aria-label='Alerts']");
        boolean found = false;
        while(!found) {
            try {
                waitForElementToBeEnabled(driver, alert_section_path, 1);
                found = true;

            } catch (NotFoundException ex) {
                Thread.sleep(500);
                js.executeScript("window.scrollBy(0,200)");
                counter++;
                if(counter > 20) {
                    break;
                }
            }
        }
        WebElement alert_section = driver.findElement(alert_section_path);
        scrollCenter(driver, alert_section);
        Thread.sleep(500);
    }

    public static void scrollToAppointmentsSection(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        int counter = 0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By appointment_section_path = By.xpath("//div[@aria-label='Appointments|Appointments|List View']");
        boolean found = false;
        while(!found) {
            try {
                waitForElementToBeEnabled(driver, appointment_section_path, 2);
                found = true;

            } catch (NotFoundException ex) {
                Thread.sleep(500);
                js.executeScript("window.scrollBy(0,200)");
                counter++;
                if(counter > 20) {
                    break;
                }
            }
        }
        WebElement appointment_section = driver.findElement(appointment_section_path);
        scrollCenter(driver, appointment_section);
        Thread.sleep(500);
    }

    public static void scrollToActiveConsentsSection(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        int counter = 0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By active_consent_section_path = By.xpath("//c-bchc-active-consent-table//span[contains(text(), 'Active Consent')]");
        boolean found = false;
        while(!found) {
            try {
                waitForElementToBeEnabled(driver, active_consent_section_path, 1);
                found = true;

            } catch (NotFoundException ex) {
                Thread.sleep(500);
                js.executeScript("window.scrollBy(0,200)");
                counter++;
                if(counter > 20) {
                    break;
                }
            }
        }
        WebElement active_consent_section = driver.findElement(active_consent_section_path);
        scrollCenter(driver, active_consent_section);
        Thread.sleep(1000);
        scrollCenter(driver, active_consent_section);
        Thread.sleep(500);
    }

    public static void openFirstAppointmentRecord(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By appointment_section_path = By.xpath("//div[@aria-label='Appointments|Appointments|List View']");
        waitForElementToBeEnabled(driver, appointment_section_path, 10);
        WebElement appointemnt_section = driver.findElement(appointment_section_path);
        GenericTable appointments_table = new GenericTable(appointemnt_section);
        List<Map<String, WebElement>> appointment_table_map = appointments_table.getRowsMappedToHeadings();
        WebElement appointmentDataTimeWebElement = appointment_table_map.get(1).get("Appointment Code").findElement(By.xpath(".//a"));
        appointmentDataTimeWebElement.click();
    }

    public static void clickAppointmentViewAllButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By view_all_appointments_button_path = By.xpath("//div[@aria-label='Appointments|Appointments|List View']/..//span[@class='view-all-label']");
        waitForElementToBeEnabled(driver, view_all_appointments_button_path, 10);
        WebElement view_all_appointments_button = driver.findElement(view_all_appointments_button_path);
        scrollCenter(driver, view_all_appointments_button);
        Thread.sleep(500);
        view_all_appointments_button.click();
    }

    public static void clickNewAlertButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By new_alert_button_path = By.xpath("//article[@aria-label='Alerts']//button[@name='New']");
        waitForElementToBeEnabled(driver, new_alert_button_path, 10);
        WebElement new_alert_button = driver.findElement(new_alert_button_path);
        new_alert_button.click();
    }

    public static List<Map<String, WebElement>> getAlertSectionTable(WebDriver driver) throws InterruptedException {
        By alerts_datatable_path = By.xpath("//article[@aria-label='Alerts']");
        waitForElementToBeEnabled(driver, alerts_datatable_path, 10);
        WebElement alerts_datatable = driver.findElement(alerts_datatable_path);
        GenericTable my_alerts = new GenericTable(alerts_datatable);
        return my_alerts.getRowsMappedToHeadings();
    }

    public static void clickCreateImmunizationRecord(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By create_immunization_record_btn_path = By.xpath("//button[contains(text(),'Create Immunization Record')]");
        waitForElementToBeEnabled(driver, create_immunization_record_btn_path, 10);
        WebElement create_immunization_record_btn = driver.findElement(create_immunization_record_btn_path);
        scrollCenter(driver, create_immunization_record_btn);
        Thread.sleep(500);
        create_immunization_record_btn.click();
    }

    public static void checkExistingConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By existing_consent_checkbox_path = By.xpath("//span[@part='label' and text()='Consent Previously Obtained (per BCCDC Standard)']/../../../..");
        waitForElementToBeEnabled(driver, existing_consent_checkbox_path, 10);
        WebElement existing_consent_checkbox = driver.findElement(existing_consent_checkbox_path);
        scrollCenter(driver, existing_consent_checkbox);
        Thread.sleep(500);
        if(existing_consent_checkbox.getAttribute("checked") == null) {
            WebElement chkbox = existing_consent_checkbox.findElement(By.xpath(".//span[@part='indicator']"));
            chkbox.click();
        }
    }

    public static void navigateToHistoricalImmunizationRecords(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        By historical_records_title = By.xpath("//span[@title and contains(text(), 'Historical Immunization Records')]");
        waitForElementToBePresent(driver, historical_records_title, 10);
        By historical_records_table_path = By.xpath("//c-bc-hc-historical-doses-c-i-b");
        WebElement historical_immunization_records_table = driver.findElement(historical_records_table_path);
        GenericTable historical_immunization_records_table_gt = new GenericTable(historical_immunization_records_table);
        WebElement element = historical_immunization_records_table_gt.getRowsMappedToHeadings().get(0).get("PIR Submission Status");

        for(int i = 0; i < 100; i++) {
            try {
                scrollCenter(driver, element);
                Thread.sleep(1000);
                historical_immunization_records_table_gt.getRowsMappedToHeadings().get(1);
                break;
            } catch (Exception ex) {
                System.out.println("Try " + i + "; Table is still empty");
                //driver.navigate().refresh();
                waitForElementToBePresent(driver, historical_records_title, 10);
                Thread.sleep(1000);
                element = historical_immunization_records_table_gt.getRowsMappedToHeadings().get(0).get("PIR Submission Status");
            }
        }
    }
}
