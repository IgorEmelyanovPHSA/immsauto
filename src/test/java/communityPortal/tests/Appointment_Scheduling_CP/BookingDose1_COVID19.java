package communityPortal.tests.Appointment_Scheduling_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class BookingDose1_COVID19 extends BaseTest {
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String vaccineToSelect = "Covid19Vaccine";

    @Test(priority = 1)
    public void Can_Book_Dose1_Appointment_as_Clerk_CP() throws Exception {
        TestcaseID = "243154"; //C243154

        log("Target Environment: "+ Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as a Clerk to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClerk();
        Thread.sleep(10000);

        log("/*2.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();
        Thread.sleep(2000);

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();
        Thread.sleep(2000);

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
        inClinicExperience_CP.clickNonIndigenousRadioButton();

        log("/*14.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();
        Thread.sleep(2000);

        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessage();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*16.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();
        Thread.sleep(2000);

        log("/*17.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);

        log("/*18.----'Confirm email address " +email +"--*/");
        inClinicExperience_CP.confirmEmail(email);

        log("/*19.---Click review details Button--*/");
        inClinicExperience_CP.clickReviewDetails();
        Thread.sleep(2000);

        log("/*20.----Click register Button on confirmation page--*/");
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);

        log("/*21.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        Thread.sleep(10000); //wait for Related Tab showing up

        log("/*22.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();
        Thread.sleep(5000);//wait for accordion loading

        log("/*23----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateAppointmentSchedulingTab_CP();
        Thread.sleep(5000);

        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();
        Thread.sleep(2000);

        log("/*25----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();
        Thread.sleep(2000);

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);
        Thread.sleep(2000);

        log("/*27----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();
        Thread.sleep(2000);

        log("/*28----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();
        Thread.sleep(2000);

        log("/*29----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();
        Thread.sleep(2000);

        log("/*30----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);

        log("/*31----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation_CP();
        Thread.sleep(2000);

        log("/*32----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();
        Thread.sleep(2000);

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();
        Thread.sleep(3000);

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();
        Thread.sleep(5000);
        //////
        log("/*35_1.----Refresh page again - should not be like that again --*/");
        inClinicExperience_CP.refreshBrowser();
        Thread.sleep(5000);
        Thread.sleep(10000); //wait for Related Tab accordion loading
        ///////

        //Validation Steps
        log("/ 36. --- We need Validation that Booking Record " +
                "in New Status has created and In-Clinic Experience button is Visible, Active, Clickable");
    }


    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }


}
