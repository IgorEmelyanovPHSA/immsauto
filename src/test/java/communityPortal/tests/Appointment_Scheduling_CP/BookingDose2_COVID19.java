package communityPortal.tests.Appointment_Scheduling_CP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
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
    //workaround
    //private String clinicNameToSearch = "All Ages - Atlin Health Centre";
    private String vaccineToSelect = "Covid19Vaccine";

    @Test(priority = 1)
    public void Can_Book_Dose2_Appointment_as_Clerk_CP() throws Exception {
        TestcaseID = "243155";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

        log("/*1.----Login as Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

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
        if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
            inClinicExperience_CP.clickNonIndigenousRadioButton();
        }

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
        PersonAccountPage.goToVaccineScheduleTab(driver);
        //In case of Early Booking
//        try {
//            PersonAccountPage.selectEarlyBookingReason(driver);
//        } catch(Exception ex) {
//            System.out.println("No early Booking screen. Continue...");
//        }

        //If override Eligibility is shown
        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }
        log("/*20.---Select vaccination type: " + vaccineToSelect + "--*/");

        try {
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
        } catch(NotFoundException ex) {
            Thread.sleep(2000);
            PersonAccountSchedulePage.overrideEligibility(driver);
            Thread.sleep(2000);
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
        }

        ////////////////////
        //May will be removed
        //PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
        ///////////////////

        log("/*21.----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

        log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);

        log("/*23.----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("/*24.----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);

        log("/*25.----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);

        log("/*26.----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("/*27.----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);

        log("/*28.----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("/*29.----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = inClinicExperience_CP.AppointmentConfirmationMessage();
        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
    }
}
