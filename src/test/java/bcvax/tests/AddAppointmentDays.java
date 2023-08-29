package bcvax.tests;

import bcvax.pages.AppointmentDayManagementPage;
import bcvax.pages.BasePage;
import bcvax.pages.MainPageOrg;
import bcvax.pages.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddAppointmentDays extends BaseTest {
    String env;
    @Test()
    public void createAppointmentDays() throws Exception {
        String appointment_date = "2023-8-27";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");

        LocalDate start_date = LocalDate.parse("2023-8-28", dtf);
        LocalDate end_date = LocalDate.parse("2023-9-2", dtf);
        ArrayList<String> appointment_dates = new ArrayList();
        for (LocalDate my_appointment_date = start_date; !my_appointment_date.isAfter(end_date); my_appointment_date = my_appointment_date.plusDays(1))
        {
            appointment_dates.add(my_appointment_date.format(dtf));
        }
        //String appointment_type = "COVID-19 Vaccination";
        String appointment_type = "BC Immunization Program";

        ArrayList<HashMap> providers = new ArrayList<HashMap>();
        HashMap<String, String> provider = new HashMap<String, String>();

        provider.put("provider", "Age 12 and Above - Abbotsford - Abby Pharmacy");
        provider.put("address_id", "AD-0000142140");
        provider.put("appointment_city", "Abbotsford");
        providers.add(provider);

        provider = new HashMap<String, String>();
        provider.put("provider", "All Ages - Atlin Health Centre");
        provider.put("address_id", "AD-0004045718");
        provider.put("appointment_city", "Atlin");
        providers.add(provider);

        provider = new HashMap<String, String>();
        provider.put("provider", "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic");
        provider.put("address_id", "AD-0004045603");
        provider.put("appointment_city", "Coquitlam");
        providers.add(provider);

        String appointment_name = appointment_type + " " + appointment_date;


        String localization = "Pacific Localization";
        env = Utils.getTargetEnvironment();
        MainPageOrg orgMainPage = loginPage.orgLoginAsPPHIS();
        orgMainPage.closeAllTabs();
        orgMainPage.switchApp("Appointment Day Management");
        AppointmentDayManagementPage appointment_day_page = new AppointmentDayManagementPage(driver);
        appointment_day_page.selectShowAllAppointmentDays();
        for(int d = 0; d < appointment_dates.size(); d++) {
            for (int i = 0; i < providers.size(); i++) {
                Map<String, WebElement> my_row = appointment_day_page.findAppointmentDay(appointment_dates.get(d), appointment_type, (String) providers.get(i).get("provider"));
                if (my_row.size() > 0) {
                    WebElement my_name_link = my_row.get("Name");
                    BasePage.scrollIfNeeded(driver, my_name_link);
                    Thread.sleep(500);
                    my_name_link.click();
                } else {
                    appointment_day_page.addAppointmentDay();
                    appointment_day_page.fillUpNewAppointmentDay(appointment_name, (String) providers.get(i).get("provider"), (String) providers.get(i).get("address_id"), appointment_dates.get(d), (String) providers.get(i).get("appointment_city"), appointment_type, localization);
                }
                appointment_day_page.selectAppointmentDayRelatedTab();
                appointment_day_page.addAppointmentTime();
                Thread.sleep(10000);
                orgMainPage.closeLastTab();
                Thread.sleep(2000);
            }
        }
        System.out.println("Here");
    }
}
