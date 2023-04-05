package bcvax.tests.InClinicExperience;

import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.MainPageCP;
import bcvax.pages.MainPageOrg;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bcvax.pages.UserDefaultsPage;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Check_In_Client_ICE extends BaseTest {
    MainPageCP cpMainPage;
    MainPageOrg orgMainPage;
    String env;
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    Map<String, Object> testData;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
    }
    @Test(priority = 1)
    public void Can_do_Check_In_Citizen_to_start_vaccine_administration_process_for_citizen_without_appointment() throws Exception {
        //TestcaseID = (env.contains("immsbc_admin")) ? "250544" : "242265";
        TestcaseID = "223614"; //C223614
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        log("/*6.----Navigate to More -> Register --*/");
        //InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
        loginPage.loginAsPPHIS();
        InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
        Thread.sleep(10000);
        inClinicExperiencePage.closeTabsHCA();
        Thread.sleep(5000);
        log("/*-- 11. Navigate to In Clinic Experience App --*/");
        inClinicExperiencePage.selectICEFromApp();
        Thread.sleep(2000);
        log("/*-- 12. Click on User Defaults Tab  --*/");
        inClinicExperiencePage.clickUserDefaultsTab();
        Thread.sleep(2000);
        UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
        log("/*-- 13. Enter current date for UserDefaults --*/");
        userDefaultsPage.inputUserDefaultsCurrentDate();
        Thread.sleep(2000);
        userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
        System.out.println("/*-- 14.----- Click on Save defaults button --*/");
        inClinicExperiencePage.clickSaveDefaultsButton();
        Thread.sleep(2000);
        log("/*7.----click Register button New Citizen --*/");
        inClinicExperiencePage.clickRegisterTab();
        Thread.sleep(2000);
        inClinicExperiencePage.clickRegisterButton();
        Thread.sleep(2000);
        log("/*8.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperiencePage.enterFirstName(legalFirstName);
        Thread.sleep(2000);
        log("/*9.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperiencePage.enterLastName(legalLastName);
        Thread.sleep(2000);
        log("/*10.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        inClinicExperiencePage.enterPostalCode(postalCode);
        Thread.sleep(2000);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperiencePage.enterPNH(personalHealthNumber);
        Thread.sleep(2000);
        log("/*13.----click on non-Indigenous person radiobutton --*/");
        inClinicExperiencePage.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        log("/*14.----click Verify PHN button --*/");
        inClinicExperiencePage.clickVerifyPHNButton();
        Thread.sleep(2000);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperiencePage.successMessage();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*16.----click Next button --*/");
        inClinicExperiencePage.clickNextButton();
        Thread.sleep(2000);
        log("/*17.----'Enter email address " +email +"--*/");
        inClinicExperiencePage.enterEmail(email);
        log("/*18.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        inClinicExperiencePage.confirmEmail(email);
        log("/*19.---Click review details Button--*/");
        Thread.sleep(2000);
        inClinicExperiencePage.clickReviewDetails();
        log("/*20.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        log("/*21.--toast success message - 'Success' --*/");
        inClinicExperiencePage.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        log("/*22.--Check if check-in button available --*/");
        assertTrue(inClinicExperiencePage.checkInButtonAvailable());
        //Get Date/Time of Check-In
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        String expectedDate = df.format(currentTime).replace(".", "");
        String expectedTime = tf.format(currentTime).replace("a.m.", "AM").replace("p.m.","PM");
        log("/*23.--Click check-in button --*/");
        inClinicExperiencePage.clickCheckInButton();
        Thread.sleep(10000);
        log("/*24.--Verify if the landing tab is IDENTIFICATION --*/");
        String currentTab = inClinicExperiencePage.getCurrentTab();
        assertEquals(currentTab, "Identification");
        log("/*25.--Get new appointment location, date and time --*/");
        String appointmentDate = inClinicExperiencePage.getAppointmentDate();
        String appointmentTime = inClinicExperiencePage.getAppointmentTime();
        String appointmentLocation = inClinicExperiencePage.getAppointmentLocation();

        LocalTime appointmentTimeActual = LocalTime.parse(appointmentTime, tf);
        assertEquals(appointmentDate, expectedDate);
        //Verify the time difference between expect and actual appointment time is less than 2 minutes
        log("/*26.--Verify the Appointment Time is close to Check-In time --*/");
        assertTrue(Math.abs(appointmentTimeActual.getMinute() - currentTime.toLocalTime().getMinute()) <= 2, "Expected Time:" + currentTime.toLocalTime() + "; Actual Time: " + appointmentTime);
        log("/*27.--Verify the Location is Age 12 and Above - Abbotsford - Abby Pharmacy --*/");
        assertEquals(appointmentLocation, clinicNameToSearch);
        System.out.println("Here");
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
