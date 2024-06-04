package bcvax.tests.InClinicExperience;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Check_In_Client_ICE extends BaseTest {
    MainPageOrg orgMainPage;
    String env;
    private String legalFirstName = "Courtnay";
    private String legalLastName = "BCVaxGoncaves";
    private String dateOfBirth = "Nov 29, 1949";
    private String postalCode = "V3J3Y1";
    private String personalHealthNumber = "9746172961";

    Map<String, Object> testData;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    Map<String, String> client_data;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "consumption");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void Can_do_Check_In_Citizen_to_start_vaccine_administration_process_for_citizen_without_appointment() throws Exception {
        //TestcaseID = (env.contains("immsbc_admin")) ? "250544" : "242265";
        TestcaseID = "223614"; //C223614
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);

        log("/*1.----Navigate to More -> Register --*/");
        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }
        InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
        InClinicExperiencePage.closeTabsHCA(driver);
        log("/*-- 2. Navigate to In Clinic Experience App --*/");
        Thread.sleep(2000);
        log("/*-- 3. Click on User Defaults Tab  --*/");
        InClinicExperiencePage.clickUserDefaultsTab(driver);
        Thread.sleep(2000);
        log("/*-- 4. Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        System.out.println("/*----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        log("/*5.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterTab(driver);
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
        Thread.sleep(2000);
        log("/*20.--Check if check-in button available --*/");
        assertTrue(PersonAccountPage.checkInButtonAvailable(driver));
        //Get Date/Time of Check-In
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        String expectedDate = df.format(currentTime).replace(".", "");
        String expectedTime = tf.format(currentTime).replace("a.m.", "AM").replace("p.m.","PM");
        log("/*21.--Click check-in button --*/");
        PersonAccountPage.clickCheckInButton(driver);
        Thread.sleep(500);
        log("/*22.--Verify if the landing tab is IDENTIFICATION --*/");
        String currentTab = InClinicExperiencePage.getCurrentTab(driver);
        assertEquals(currentTab, "Identification");
        log("/*23.--Get new appointment location, date and time --*/");
        String appointmentDate = InClinicExperienceIdentificationPage.getAppointmentDate(driver);
        String appointmentTime = InClinicExperienceIdentificationPage.getAppointmentTime(driver);
        String appointmentLocation = InClinicExperienceIdentificationPage.getAppointmentLocation(driver);

        LocalTime appointmentTimeActual = LocalTime.parse(appointmentTime, tf);
        assertEquals(appointmentDate, expectedDate);
        //Verify the time difference between expect and actual appointment time is less than 2 minutes
        log("/*24.--Verify the Appointment Time is close to Check-In time --*/");
        assertTrue(Math.abs(appointmentTimeActual.getMinute() - currentTime.toLocalTime().getMinute()) <= 2, "Expected Time:" + currentTime.toLocalTime() + "; Actual Time: " + appointmentTime);
        log("/*25.--Verify the Location is Age 12 and Above - Abbotsford - Abby Pharmacy --*/");
        assertEquals(appointmentLocation, clinicNameToSearch);
    }
}
