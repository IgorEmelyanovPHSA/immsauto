package bcvax.tests;

import bcvax.pages.AppointmentDayManagementPage;
import bcvax.pages.MainPageOrg;
import bcvax.pages.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.Map;

public class AddAppointmentDays extends BaseTest {
    String env;
    @Test()
    public void createAppointmentDays() throws Exception {
        String appointment_date = "2023-7-20";
        String appointment_type = "COVID-19 Vaccination";

        //String provider = "Age 12 and Above - Abbotsford - Abby Pharmacy";
        //String address_id = "AD-0000142140";
        //String appointment_city = "Abbotsford";

        //String provider = "All Ages - Atlin Health Centre";
        //String address_id = "AD-0004045718";
        //String appointment_city = "Atlin";

        String provider = "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic";
        String address_id = "AD-0004045603";
        String appointment_city = "Coquitlam";

        String appointment_name = appointment_type + " " + appointment_date;


        String localization = "Pacific Localization";
        env = Utils.getTargetEnvironment();
        MainPageOrg orgMainPage = loginPage.orgLoginAsPPHIS();
        orgMainPage.closeAllTabs();
        orgMainPage.switchApp("Appointment Day Management");
        AppointmentDayManagementPage appointment_day_page = new AppointmentDayManagementPage(driver);
        appointment_day_page.selectShowAllAppointmentDays();
        Map<String, WebElement> my_row = appointment_day_page.findAppointmentDay(appointment_date, appointment_type, provider);
        if(my_row.size() > 0) {
            my_row.get("Name").click();
        } else {
            appointment_day_page.addAppointmentDay();
            appointment_day_page.fillUpNewAppointmentDay(appointment_name, provider, address_id, appointment_date, appointment_city, appointment_type, localization);
        }
        appointment_day_page.selectAppointmentDayRelatedTab();
        appointment_day_page.addAppointmentTime();
        System.out.println("Here");
    }
}
