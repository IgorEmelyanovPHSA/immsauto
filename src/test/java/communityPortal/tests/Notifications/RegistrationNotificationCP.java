package communityPortal.tests.Notifications;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class RegistrationNotificationCP extends BaseTest {

    // Gmail registered to verify email notifications:
    // email: verify.notifications.now@gmail.com
    // password: Technology1990!!!!!!
    // email registration details, for password recovery only: 01 Jan 1980, Male

    private String legalFirstName = "Lockwood";
    private String legalLastName = "BCVaxPenketh";
    private String dateOfBirth = "Jan 23, 1993";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746173963";
    private String email = "verify.notifications.now@gmail.com";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    //String clinicNameToSearch = "All Ages - Atlin Health Centre";
    private String vaccineToSelect = "Covid19Vaccine";

    @Test()
    public void CP_VerifyRegistrationNotification_C246562() throws Exception {
        TestcaseID = "246562"; //C246562
        log("TestCase: C246562");
        log("Target Environment: "+ Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

        log("/*1.----Login as Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*2.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();

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
        Thread.sleep(2000);
        log("/*21.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }

        Thread.sleep(1000);
        log("/*22.----click on person Account Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);

        log("/*23----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);

        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }

        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");

        log("/*25----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);

        log("/*27----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("/*28----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);

        log("/*29----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);

        log("/*30----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("/*31----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);

        log("/*32----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        PersonAccountSchedulePage.appointmentConfirmationMessage(driver);

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);

        log("/*35_1.----Refresh page again - should not be like that again --*/");
        inClinicExperience_CP.refreshBrowser();

        log("To validate the actual email, please login into gmail account with following credentials:" +
                "\nemail: " +email +"\npassword: Technology1990!!!!!!");
    }
}
