package communityPortal.tests.VaccineAdministration_CP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import java.lang.Math;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Check_In_Client_CP extends BaseTest {
    String env;
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
    }
    @Test(priority = 1)
    public void Can_do_Check_In_Citizen_to_start_vaccine_administration_process_for_citizen_without_appointment_CP() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "250544" : "242265";

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

        log("/*1.----Login as Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        MainPageCP.clickUserDefaultsTab(driver);
        log("/*9.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*10.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        Thread.sleep(7000);
        log("/*7.----click Register button New Citizen --*/");
        log("/*6.----Navigate to More -> Register --*/");
        MainPageCP.navigateToRegisterClientPage(driver);
        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(driver);
        InClinicExperiencePage.clickRegisterButton(driver);
        log("/*8.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        log("/*9.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        log("/*10.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);

        log("/*14.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);

        log("/*16.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        log("/*17.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        log("/*18.----'Confirm email address " +email +"--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);
        log("/*19.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        log("/*20.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        log("/*21.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }

        Thread.sleep(1000);

        log("/*22.--Check if check-in button available --*/");
        assertTrue(inClinicExperience_CP.checkInButtonAvailable());
        //Get Date/Time of Check-In
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        String expectedDate = df.format(currentTime).replace(".", "");
        String expectedTime = tf.format(currentTime).replace("a.m.", "AM").replace("p.m.","PM");
        log("/*23.--Click check-in button --*/");
        PersonAccountPage.clickCheckInButton(driver);
        log("/*24.--Verify if the landing tab is IDENTIFICATION --*/");
        String currentTab = inClinicExperience_CP.getCurrentTab();
        assertEquals(currentTab, "Identification");
        log("/*25.--Get new appointment location, date and time --*/");
        String appointmentDate = inClinicExperience_CP.getAppointmentDate();
        String appointmentTime = inClinicExperience_CP.getAppointmentTime();
        String appointmentLocation = inClinicExperience_CP.getAppointmentLocation();

        LocalTime appointmentTimeActual = LocalTime.parse(appointmentTime, tf);
        assertEquals(appointmentDate, expectedDate);
        //Verify the time difference between expect and actual appointment time is less than 2 minutes
        assertTrue(Math.abs(appointmentTimeActual.getMinute() - currentTime.toLocalTime().getMinute()) <= 2, "Expected Time:" + currentTime.toLocalTime() + "; Actual Time: " + appointmentTime);
        assertEquals(appointmentLocation, clinicNameToSearch);
        System.out.println("Here");
    }
}
