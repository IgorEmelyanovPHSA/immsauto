package bcvax.tests;

import bcvax.pages.AppointmentDayManagementPage;
import bcvax.pages.BasePage;
import bcvax.pages.MainPageOrg;
import bcvax.pages.Utils;
import constansts.Apps;
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
    Map<String, Object> testData;
    @Test()
    public void createAppointmentDays() throws Exception {
        String appointment_date = "2023-8-27";
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");

        LocalDate start_date = LocalDate.parse("2023-12-18", dtf);
        LocalDate end_date = LocalDate.parse("2023-12-24", dtf);
        ArrayList<String> appointment_dates = new ArrayList();
        for (LocalDate my_appointment_date = start_date; !my_appointment_date.isAfter(end_date); my_appointment_date = my_appointment_date.plusDays(1))
        {
            appointment_dates.add(my_appointment_date.format(dtf));
        }
        //String appointment_type = "COVID-19 Vaccination";
        //String appointment_type = "Minor Ailments and Contraception";
        String appointment_type = "BC Immunization Program";

        ArrayList<HashMap> providers = new ArrayList<HashMap>();
        String appointment_name = appointment_type + " " + appointment_date;

        String localization = "Pacific Localization";
        MainPageOrg orgMainPage = loginPage.orgLoginAsPPHIS();
        String currentApp = orgMainPage.currentApp();
        if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            try {
                orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
            } catch(Exception ex) {
                Thread.sleep(5000);
                orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
            }
        }
        orgMainPage.closeAllTabs();
        orgMainPage.switchApp("Appointment Day Management");
        AppointmentDayManagementPage appointment_day_page = new AppointmentDayManagementPage(driver);
        appointment_day_page.selectShowAllAppointmentDays();
        for(int d = 0; d < appointment_dates.size(); d++) {
            providers = ((ArrayList)(testData.get("supplyLocationsDayManagement")));
            for (int i = 0; i < providers.size(); i++) {
                String provider_name = providers.get(i).keySet().toArray()[0].toString();
                String address_id = ((HashMap<String, String>)providers.get(i).get(provider_name)).get("address_id");
                String appointment_city = ((HashMap<String, String>)providers.get(i).get(provider_name)).get("appointment_city");

                String appointment_day_id = Utilities.ApiQueries.getAppointmentDays(appointment_dates.get(d), appointment_type, appointment_city);
                if (!appointment_day_id.equals("")) {
                    Map<String, WebElement> my_row = appointment_day_page.findAppointmentDay(appointment_dates.get(d), appointment_type, provider_name);

                    WebElement my_name_link = my_row.get("Name");
                    BasePage.scrollIfNeeded(driver, my_name_link);
                    Thread.sleep(500);
                    my_name_link.click();
                } else {
                    appointment_day_page.addAppointmentDay();
                    appointment_name = appointment_type + " " + appointment_dates.get(d);
                    appointment_day_page.fillUpNewAppointmentDay(appointment_name, provider_name, address_id, appointment_dates.get(d), appointment_city, appointment_type, localization);
                    appointment_day_id = Utilities.ApiQueries.getAppointmentDays(appointment_dates.get(d), appointment_type, appointment_city);
                }
                appointment_day_page.selectAppointmentDayRelatedTab();
                ArrayList<HashMap> day_times = Utilities.ApiQueries.getAppointmentDayTimes(appointment_day_id);
                appointment_day_page.addAppointmentTime(day_times);
                Thread.sleep(10000);
                orgMainPage.closeLastTab();
                Thread.sleep(2000);
            }
        }
        System.out.println("Here");
    }
}
