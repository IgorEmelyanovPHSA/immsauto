package communityPortal.tests.Appointment_Scheduling_CP;

import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.MainPageCP;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Test;

public class BookingDose2_COVID19 extends BaseTest {
    private String legalFirstName = "Hugues";
    private String legalLastName = "BCVaxLampard";
    private String dateOfBirth = "March 3, 1904";
    private String postalCode = "V1N3Q3";
    private String personalHealthNumber = "9746171121";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";

    private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String vaccineToSelect = "Covid19Vaccine";

    @Test(priority = 1)
    public void Can_Book_Dose2_Appointment_as_Clerk_CP() throws Exception {
        TestcaseID = "243155";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as a Clerk to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClerk();

        log("/*2.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*3.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();

        log("/*4.----Enter First Name: " +legalFirstName +"--*/");
        inClinicExperience_CP.enterFirstName(legalFirstName);

        log("/*5.----Enter Last Name: " +legalLastName +"--*/");
        inClinicExperience_CP.enterLastName(legalLastName);

        log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
        inClinicExperience_CP.enterDateOfBirth(dateOfBirth);

        log("/*7.----Enter Postal code: " +postalCode +"--*/");
        inClinicExperience_CP.enterPostalCode(postalCode);

        log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
        inClinicExperience_CP.enterPNH(personalHealthNumber);

        log("/*9.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience_CP.clickNonIndigenousRadioButton();

        log("/*10.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();

        log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessageAppear();

        log("/*12.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();

        log("/*13.'Enter email address: " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);

        log("/*14.'Confirm email address: " +email +"--*/");
        inClinicExperience_CP.confirmEmail(email);

        log("/*15.Click review details Button--*/");
        inClinicExperience_CP.clickReviewDetails();

        log("/*16.Click register Button on confirmation page--*/");
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();

        log("/*17.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();

        //log("/*18.----click on person Account Related Tab --*/");
        //inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*19.----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateToVaccineSchedulingTab();
        //In case of Early Booking
        try {
            inClinicExperience_CP.selectEarlyBookingReason();
        } catch(Exception ex) {
            System.out.println("No early Booking screen. Continue...");
        }
        log("/*20.---Select vaccination type: " + vaccineToSelect + "--*/");
        inClinicExperience_CP.selectOneOption(vaccineToSelect);

        log("/*21.----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();

        log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);

        log("/*23.----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();

        log("/*24.----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();

        log("/*25.----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();

        log("/*26.----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();

        log("/*27.----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation();

        log("/*28.----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();

        log("/*29.----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
