package communityPortal.tests.VaccineAdministration_CP;

import Utilities.ApiQueries;
import Utilities.TestListener;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.MainPageCP;
import bcvax.pages.ProfilesPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
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
    String citizenName = "Hugues Fawn BCVaxLampard";

    @Test
    public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician_CP() throws Exception {
        TestcaseID = "245216"; //C245216
        log("Target Environment: " + Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        log("TestRail test case ID: C" +TestcaseID);

        log("/*1.----Login to Community Portal --*/");
       // MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianWrongClinic();
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

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

        log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessage();

        log("/*12.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();

        log("/*13.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);

        log("/*14.----'Confirm email address " +email +"--*/");
        inClinicExperience_CP.confirmEmail(email);

        log("/*15.---Click review details Button--*/");
        inClinicExperience_CP.clickReviewDetails();

        log("/*16.----Click register Button on confirmation page--*/");
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();

        log("/*17.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();

        log("/*18.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();

        log("/*19----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateAppointmentSchedulingTab_CP();

        log("/*20.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();

        log("/*21----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();

        log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);

        log("/*23----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();

        log("/*24----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();

        log("/*25----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();

        log("/*26----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();

        log("/*27----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation_CP();

        log("/*28----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();

        log("/*29. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();

        log("/*30.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();

        log("/*31.----Refresh page - need to be fixed by dev's --*/");
        inClinicExperience_CP.refreshBrowser();

        log("/*32.---Click Go To In clinic experience button --*/");
        //Create classic object of inClinicExperiencePage
        InClinicExperiencePage inClinicExperiencePageClassic = new InClinicExperiencePage(getDriver());
        inClinicExperiencePageClassic.ClickGoToInClinicExperienceButton();

        String originalBooking = inClinicExperiencePageClassic.ValidateClinicNameBeforeRebook();
        log("/*--- Before Booking clinic Value is:" + originalBooking + "");

        //Assert originalBooking are match to actual booking (clinicNameToSearch)
        Assert.assertTrue(clinicNameToSearch.equalsIgnoreCase(originalBooking));

        log("/*33.--- User can click Rebook Appointment button to book an appointment --*/");
        inClinicExperiencePageClassic.ClickRebookAppointment();
        Thread.sleep(1000);
        try {
            inClinicExperiencePageClassic.clickCloseAlert();
        } catch(Exception ex) {
            System.out.println("Alert not found. Proceed...");
        }
        log("/*--  We need to add Validation for 1.(Clinic has changed & address has changed) --*/");
        log("/*--                                2. Rebook at Current Location button is disabled --*/");

        String afterRebooking = inClinicExperiencePageClassic.ValidateclinicNameAfterRebook();
        log("/*--- After Booking clinic value is:" + afterRebooking + "");

        //Assert false clinic originalBooking is not equals to afterRebooking
        Assert.assertFalse(originalBooking.equalsIgnoreCase(afterRebooking));

        log("/*34.---'Rebook at Current Location button is disabled after user books appointment --*/");
        inClinicExperiencePageClassic.ValidateClickRebookAppointmentButtonIsDisabled();
        Thread.sleep(2000);
        log("/*35.---Click confirm and Save Button on Home Page --*/");
        inClinicExperiencePageClassic.HomePageClickConfirmAndSaveButton();

        log("/*36.---Click to select Agent --*/");
        try {
            log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
            inClinicExperience_CP.selectVaccineAgent();
        } catch(Exception ex) {
            log("/*39.---Open Today's appointments from Home page --*/");
            System.out.println(ex.getMessage());
            Thread.sleep(2000);
            inClinicExperience_CP.clickTodayAppointments();
            Thread.sleep(2000);
            log("/*40.---Open Today appointment Details --*/");
            inClinicExperience_CP.clickTodayAppointmentCaseViewButton();
            log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
            Thread.sleep(2000);
            inClinicExperience_CP.selectVaccineAgent();
        }

        log("/*38.---Click Save Consent Button --*/");
        inClinicExperiencePageClassic.ClickSaveConsentButton();
        Thread.sleep(2000);
        log("/*39.---Save Immunization Information ---*/");
        inClinicExperiencePageClassic.saveImmunizationInformation();

        //If expired lop click Ok
        Thread.sleep(1000);
        inClinicExperience_CP.clickOkForExpiredLot();
        /////////

        log("/*40.---Click Confirm and Save Administration Button --*/");
        inClinicExperiencePageClassic.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(2000);
        log("/*41.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperiencePageClassic.ClickModalConfirmAndSaveAdministrationButton();

        log("/*42.---the Home - Client Search supposed to showing up  --*/");
        inClinicExperiencePageClassic.validateHomePageShownUp();
        inClinicExperiencePageClassic.refreshBrowser();
        Thread.sleep(2000);
        log("/*43.---Search for Participant account --*/");
        //ProfilesPage profilesPage = cpMainPage.globalSearch_CP(citizenName);
        cpMainPage.search(legalFirstName + " " + legalLastName);
        Thread.sleep(2000);
        log("/*44.---select Citizen Participant acc from search results --*/");
        ProfilesPage profilesPage = new ProfilesPage(driver);
        profilesPage.selectCitizenParticipant(legalFirstName + " " + legalLastName);

        log("/*45.---Navigate to Person Account related tab ---*/");
        profilesPage.clickRelatedTab();

        log("/*46.---Immunization status is in After Care ---*/");
        inClinicExperiencePageClassic.ValidateStatusisInAftercare();

        log("/*47.---User Navigated to Appointment Section  ---*/");
        inClinicExperiencePageClassic.NavigateToAppointmentsSection();

        log("/*48.---- An previous appointment for the user has been cancelled with reebooking of an appointment ---*/");
        inClinicExperiencePageClassic.ValidateAppointmentCancelledIsPresentCP();

        log("/*49.---- An confirmed appointmrnt is found for the user  ---*/");
        inClinicExperiencePageClassic.ValidateAppointmentConfirmIsPresentCP();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
