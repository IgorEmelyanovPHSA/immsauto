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
//    private String legalFirstName = "Ludovika";
//    private String legalLastName = "BcvaxLimeburn";
//    private String dateOfBirth = "Sep 21, 1923";
//    private String postalCode = "V3L5L2";
//    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    Map<String, Object> testData;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test(priority = 1)
    public void Can_do_Check_In_Citizen_to_start_vaccine_administration_process_for_citizen_without_appointment() throws Exception {
        //TestcaseID = (env.contains("immsbc_admin")) ? "250544" : "242265";
        TestcaseID = "223614"; //C223614
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

        log("/*1.----Navigate to More -> Register --*/");
        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }
        InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
        inClinicExperiencePage.closeTabsHCA();
        log("/*-- 2. Navigate to In Clinic Experience App --*/");
        Thread.sleep(2000);
        log("/*-- 3. Click on User Defaults Tab  --*/");
        inClinicExperiencePage.clickUserDefaultsTab();
        Thread.sleep(2000);
        log("/*-- 4. Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        System.out.println("/*----- Click on Save defaults button --*/");
        inClinicExperiencePage.clickSaveDefaultsButton();
        log("/*5.----click Register button New Citizen --*/");
        inClinicExperiencePage.clickRegisterTab();
        inClinicExperiencePage.clickRegisterButton();
        log("/*6.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        log("/*7.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        log("/*8.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        log("/*9.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        log("/*10.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
        log("/*11.----click on non-Indigenous person radiobutton --*/");
//        if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
//            inClinicExperiencePage.clickNonIndigenousRadioButton();
//        }
        log("/*12.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        log("/*13.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);

        log("/*14.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        log("/*15.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        log("/*16.----'Confirm email address " +email +"--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);
        log("/*17.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        Thread.sleep(2000);
        log("/*18.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        Thread.sleep(2000);
        log("/*19.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        Thread.sleep(2000);
        log("/*20.--Check if check-in button available --*/");
        assertTrue(inClinicExperiencePage.checkInButtonAvailable());
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
        String currentTab = inClinicExperiencePage.getCurrentTab();
        assertEquals(currentTab, "Identification");
        log("/*23.--Get new appointment location, date and time --*/");
        String appointmentDate = inClinicExperiencePage.getAppointmentDate();
        String appointmentTime = inClinicExperiencePage.getAppointmentTime();
        String appointmentLocation = inClinicExperiencePage.getAppointmentLocation();

        LocalTime appointmentTimeActual = LocalTime.parse(appointmentTime, tf);
        assertEquals(appointmentDate, expectedDate);
        //Verify the time difference between expect and actual appointment time is less than 2 minutes
        log("/*24.--Verify the Appointment Time is close to Check-In time --*/");
        assertTrue(Math.abs(appointmentTimeActual.getMinute() - currentTime.toLocalTime().getMinute()) <= 2, "Expected Time:" + currentTime.toLocalTime() + "; Actual Time: " + appointmentTime);
        log("/*25.--Verify the Location is Age 12 and Above - Abbotsford - Abby Pharmacy --*/");
        assertEquals(appointmentLocation, clinicNameToSearch);
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
    }
}
