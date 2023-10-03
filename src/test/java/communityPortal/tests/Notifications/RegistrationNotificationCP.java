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
    public void VerifyRegistrationNotification_CP() throws Exception {
        TestcaseID = "246562"; //C246562
        CommonMethods commn = new CommonMethods(getDriver());
        log("Target Environment: "+ Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*2.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();

        log("/*8.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience_CP.enterFirstName(legalFirstName);

        log("/*9.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience_CP.enterLastName(legalLastName);

        log("/*10.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience_CP.enterDateOfBirth(dateOfBirth);

        log("/*11.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience_CP.enterPostalCode(postalCode);

        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience_CP.enterPNH(personalHealthNumber);

        log("/*13.----click on non-Indigenous person radiobutton --*/");
        if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
            inClinicExperience_CP.clickNonIndigenousRadioButton();
        }

        log("/*14.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();

        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessageAppear();

        log("/*16.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();

        log("/*17.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);

        log("/*18.----'Confirm email address " +email +"--*/");
        inClinicExperience_CP.confirmEmail(email);

        log("/*19.---Click review details Button--*/");
        inClinicExperience_CP.clickReviewDetails();

        log("/*20.----Click register Button on confirmation page--*/");
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();

        log("/*21.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();
        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }

        Thread.sleep(1000);
        log("/*22.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*23----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateToVaccineSchedulingTab();

        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountPage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }

        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();

        log("/*25----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);

        log("/*27----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();

        log("/*28----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();

        log("/*29----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();

        log("/*30----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();

        log("/*31----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation();

        log("/*32----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*35_1.----Refresh page again - should not be like that again --*/");
        inClinicExperience_CP.refreshBrowser();

        log("To validate the actual email, please login into gmail account with following credentials:" +
                "\nemail: " +email +"\npassword: Technology1990!!!!!!");
    }
}
