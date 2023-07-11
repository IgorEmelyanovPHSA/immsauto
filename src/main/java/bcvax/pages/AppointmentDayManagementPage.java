package bcvax.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import java.util.List;
import java.util.Map;

public class AppointmentDayManagementPage extends BasePage {
    Tables tables;
    public AppointmentDayManagementPage(WebDriver driver) {
        super(driver);
    }

    public void findAppointmentDay(String appointment_day, String appointment_type, String provider) {
        GenericTable appointment_day_table = tables.getAppointmentDayTable();
        List<Map<String, WebElement>> rows = appointment_day_table.getRowsMappedToHeadings();
        System.out.println("Here");
    }
}
