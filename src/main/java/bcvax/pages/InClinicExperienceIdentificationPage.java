package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;
import java.util.List;

public class InClinicExperienceIdentificationPage extends BasePage {
    public InClinicExperienceIdentificationPage(WebDriver driver) {
        super(driver);
    }

    public static void clickConfirmAndSaveIdentificationButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("(//button[@title='Confirm & Save Identification'])");
        waitForElementToBeEnabled(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        scrollCenter(driver, confirm_and_save_btn);
        Thread.sleep(500);
        confirm_and_save_btn.click();
    }

    public static String getSidebarAlertText(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By sidebar_alerts_text_path = By.xpath("//a[@c-bchcsidebar_bchcsidebar]");
        waitForElementToBeEnabled(driver, sidebar_alerts_text_path, 10);
        WebElement sidebar_alerts_text = driver.findElement(sidebar_alerts_text_path);
        String alert_text = sidebar_alerts_text.getText();
        return alert_text;
    }

    public static boolean alertSectionMinimized(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_section_icon_closed_path = By.xpath("//lightning-icon[@c-bchcalert_bchcalert and @class='icon-close slds-icon-utility-chevronright slds-icon_container']");
        By alert_section_icon_open_path = By.xpath("//lightning-icon[@c-bchcalert_bchcalert and @class='icon-open slds-icon-utility-chevrondown slds-icon_container']");
        waitForElementToBeEnabled(driver, alert_section_icon_closed_path, 10);
        WebElement alert_section_icon_closed = driver.findElement(alert_section_icon_closed_path);
        WebElement alert_section_icon_open = driver.findElement(alert_section_icon_open_path);
        boolean section_minimized = alert_section_icon_closed.isDisplayed();
        return section_minimized;
    }

    public static void expandAlertSection(WebDriver driver) throws InterruptedException {
        By alert_section_icon_closed_path = By.xpath("//lightning-icon[@c-bchcalert_bchcalert and @class='icon-close slds-icon-utility-chevronright slds-icon_container']");
        By alert_section_icon_open_path = By.xpath("//lightning-icon[@c-bchcalert_bchcalert and @class='icon-open slds-icon-utility-chevrondown slds-icon_container']");
        waitForElementToBeEnabled(driver, alert_section_icon_closed_path, 10);
        WebElement alert_section_icon_closed = driver.findElement(alert_section_icon_closed_path);
        WebElement alert_section_icon_open = driver.findElement(alert_section_icon_open_path);
        if(alert_section_icon_closed.isDisplayed()) {
            scrollCenter(driver, alert_section_icon_closed);
            alert_section_icon_closed.click();
        }
    }

    public static void clickAddAlertButton(WebDriver driver) throws InterruptedException {
        By add_alert_button_path = By.xpath("//c-bc-hc-alert//button[@title='Add']");
        waitForElementToBeEnabled(driver, add_alert_button_path, 10);
        WebElement add_alert_button = driver.findElement(add_alert_button_path);
        scrollCenter(driver, add_alert_button);
        Thread.sleep(500);
        add_alert_button.click();
    }

    public static boolean alertSectionEmpty(WebDriver driver) throws InterruptedException {
        By alerts_datatable_path = By.xpath("//c-bc-hc-alert-datatable");
        waitForElementToBeEnabled(driver, alerts_datatable_path, 10);
        WebElement alerts_datatable = driver.findElement(alerts_datatable_path);
        GenericTable my_alerts = new GenericTable(alerts_datatable);
        if(my_alerts.getRows().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Map<String, WebElement>> getAlertSectionTable(WebDriver driver) throws InterruptedException {
        By alerts_datatable_path = By.xpath("//c-bc-hc-alert-datatable");
        waitForElementToBeEnabled(driver, alerts_datatable_path, 10);
        WebElement alerts_datatable = driver.findElement(alerts_datatable_path);
        GenericTable my_alerts = new GenericTable(alerts_datatable);
        return my_alerts.getRowsMappedToHeadings();
    }

    public static void clickViewEditAlert(WebDriver driver, String message) throws InterruptedException {
        By alerts_datatable_path = By.xpath("//c-bc-hc-alert-datatable");
        waitForElementToBeEnabled(driver, alerts_datatable_path, 10);
        WebElement alerts_datatable = driver.findElement(alerts_datatable_path);
        GenericTable my_alerts = new GenericTable(alerts_datatable);
        for(Map<String, WebElement> my_row: my_alerts.getRowsMappedToHeadings()) {
            if (my_row.get("Message").getText().equals(message)) {
                WebElement my_view_btn = my_row.get("View/Edit").findElement(By.xpath(".//button"));
                my_view_btn.click();
                break;
            }
        }
    }

    public static String getAlertIcon(List<Map<String, WebElement>> alert_table, String message) {
        String my_icon = "";
        for(Map<String, WebElement> alert_row: alert_table) {
            if(alert_row.get("Message").getText().equals(message)) {
                String my_icon_src = alert_row.get("Alert Type").findElement(By.xpath(".//img")).getAttribute("src");
                String[] ref_parts = my_icon_src.split("/");
                my_icon = ref_parts[ref_parts.length - 1];
                break;
            }
        }
        return my_icon;
    }

    public static String getAppointmentDate(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By appointment_date_path = By.xpath("//label[text() = 'Date']/../../../lightning-formatted-text");
        waitForElementToBeEnabled(driver, appointment_date_path, 10);
        WebElement appointment_date = driver.findElement(appointment_date_path);
        return appointment_date.getText();
    }

    public static String getAppointmentTime(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By appointment_time_path = By.xpath("//label[text() = 'Appointment Time']/../../../lightning-formatted-text");
        waitForElementToBeEnabled(driver, appointment_time_path, 10);
        WebElement appointment_time = driver.findElement(appointment_time_path);
        return appointment_time.getText();
    }

    public static String getAppointmentLocation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By appointment_location_path = By.xpath("//label[text() = 'Clinic Name']/../../../lightning-formatted-text");
        waitForElementToBeEnabled(driver, appointment_location_path, 10);
        WebElement appointment_location = driver.findElement(appointment_location_path);
        return appointment_location.getText();
    }
}
