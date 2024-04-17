package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientListTodayAppointmentsTab extends BasePage {
    public ClientListTodayAppointmentsTab(WebDriver driver) {
        super(driver);
    }

    public static String getPathwayStatus(Map<String, WebElement> my_client_appointment) throws InterruptedException {
        String my_pathway_status = my_client_appointment.get("Pathway Status").findElement(By.xpath(".//lightning-base-formatted-text")).getText();
        return my_pathway_status;
    }

    public static boolean viewButtonIsDisplayed(Map<String, WebElement> my_client_appointment) throws InterruptedException {
        String view_button_class = my_client_appointment.get("View").findElement(By.xpath(".//lightning-button")).getAttribute("class");
        if(view_button_class.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkInButtonIsDisplayed(Map<String, WebElement> my_client_appointment) throws InterruptedException {
        String checkin_button_class = my_client_appointment.get("Check-in Client").findElement(By.xpath(".//lightning-button")).getAttribute("class");
        if(checkin_button_class.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Map<String, WebElement>> getTodayAppoitmentsTable(WebDriver driver) throws InterruptedException {
        By today_appointments_table_path = By.xpath("//c-bc-hc-datatable-custom-types");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, today_appointments_table_path, 10);
        WebElement today_appointments_table_node = driver.findElement(today_appointments_table_path);
        GenericTable today_appointments_table = new GenericTable(today_appointments_table_node);
        List<Map<String, WebElement>> today_appointments_map = today_appointments_table.getRowsMappedToHeadings();
        return today_appointments_map;
    }

    public static Map<String, WebElement> getTodayAppoitmentsTableRow(WebDriver driver, String client_phn) throws InterruptedException {
        By today_appointments_table_path = By.xpath("//c-bc-hc-datatable-custom-types");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, today_appointments_table_path, 10);
        WebElement today_appointments_table_node = driver.findElement(today_appointments_table_path);
        GenericTable today_appointments_table = new GenericTable(today_appointments_table_node);
        List<Map<String, WebElement>> today_appointments_map = today_appointments_table.getRowsMappedToHeadings();
        for(Map<String, WebElement> my_row: today_appointments_map) {
            if(my_row.get("PHN").getText().equals(client_phn)) {
                return my_row;
            }
        }
        Map<String, WebElement> empty_map = new HashMap<String, WebElement>();
        return empty_map;
    }
}
