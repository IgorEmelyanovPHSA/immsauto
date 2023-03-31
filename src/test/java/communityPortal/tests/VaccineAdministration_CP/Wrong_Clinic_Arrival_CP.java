package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.MainPageCP;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class Wrong_Clinic_Arrival_CP extends BaseTest {

    private String legalFirstName = "Hugues";
    private String legalLastName = "BCVaxLampard";
    private String legalMiddleName = "Fawn";
    private String dateOfBirth = "March 3, 1904";
    private String postalCode = "V1N3Q3";
    private String personalHealthNumber = "9746171121";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "All Ages - Atlin Health Centre";
    String citizenName = "Hugues BCVaxLampard";

    @Test
    public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician_CP() throws Exception {
        TestcaseID = "219910"; //C219910
        // TestCase update for CP 245216??
        log("Target Environment: " + Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianWrongClinic();
        Thread.sleep(10000);

        log("/*2.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*3.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();

        log("/*4.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience_CP.enterFirstName(legalFirstName);

        log("/*5.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience_CP.enterLastName(legalLastName);

        log("/*6.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience_CP.enterDateOfBirth(dateOfBirth);

        log("/*7.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience_CP.enterPostalCode(postalCode);

        log("/*8.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience_CP.enterPNH(personalHealthNumber);

        log("/*9.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience_CP.clickNonIndigenousRadioButton();

        log("/*10.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();
        Thread.sleep(2000);

        log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessage();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*12.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();

        log("/*13.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);

        log("/*14.----'Confirm email address " +email +"--*/");
        inClinicExperience_CP.confirmEmail(email);

        log("/*15.---Click review details Button--*/");
        inClinicExperience_CP.clickReviewDetails();
        //  Thread.sleep(1000);

        log("/*16.----Click register Button on confirmation page--*/");
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);

        log("/*17.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*18.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();
        Thread.sleep(5000);//wait for accordion loading

        log("/*19----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateAppointmentSchedulingTab_CP();
        Thread.sleep(5000);

        log("/*20.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();
        Thread.sleep(2000);

        log("/*21----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();
        Thread.sleep(2000);

        log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);
        Thread.sleep(2000);

        log("/*23----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();
        Thread.sleep(2000);

        log("/*24----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();
        Thread.sleep(2000);

        log("/*25----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();
        Thread.sleep(2000);

        log("/*26----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);

        log("/*27----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation_CP();
        Thread.sleep(2000);

        log("/*28----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();
        Thread.sleep(2000);

        log("/*29. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();
        Thread.sleep(3000);

        log("/*30.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();
        Thread.sleep(2000);

// Blocked by GO TO in clinic Experience btn is broken

    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
