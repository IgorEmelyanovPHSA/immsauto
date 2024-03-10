package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class E2E_Dose1_Influenza_CP extends BaseTest{

    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test(priority = 1)
    public void Can_do_Dose1_Influenza_Vaccine_Administration_as_Clinician_CP() throws Exception {
        TestcaseID = "243208"; //C243208
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as an Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();
        Thread.sleep(10000);

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        Thread.sleep(5000);

        log("/*3.----- Click on User Defaults Tab --*/");
        cpMainPage.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*4.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
        userDefaultPage.inputCurrentDateUserDefaults();
        userDefaultPage.selectUserDefaultLocation(clinicNameToSearch);

        log("/*5.----- Click on Save defaults button --*/");
        userDefaultPage.clickBtnSave();
        Thread.sleep(2000);

        log("/*6.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();
        Thread.sleep(2000);

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();
        Thread.sleep(2000);
        log("/*8.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        Thread.sleep(2000);
        log("/*9.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        Thread.sleep(2000);
        log("/*10.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        Thread.sleep(2000);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        Thread.sleep(2000);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
        Thread.sleep(2000);

        log("/*14.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        Thread.sleep(2000);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*16.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        Thread.sleep(2000);
        log("/*17.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        log("/*18.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        CitizenPrimaryInfo.confirmEmail(driver, email);
        log("/*19.---Click review details Button--*/");
        Thread.sleep(2000);
        CitizenPrimaryInfo.clickReviewDetails(driver);
        log("/*20.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        Thread.sleep(2000);
        log("/*21.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        Thread.sleep(10000); //wait for Related Tab showing up

        log("/*22.----click on person Account Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(5000);//wait for accordion loading

        log("/*23----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);
        Thread.sleep(5000);

        log("/*24.----click on the Vaccine 'Influenza Vaccine' checkbox --*/");
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Influenza Vaccine");
        Thread.sleep(2000);

        System.out.println("/*25----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        Thread.sleep(2000);

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        Thread.sleep(2000);

        log("/*27----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
        Thread.sleep(2000);

        log("/*28----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        Thread.sleep(2000);

        log("/*29----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        Thread.sleep(2000);

        log("/*30----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
        Thread.sleep(2000);

        log("/*31----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        Thread.sleep(2000);

        log("/*32----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);
        Thread.sleep(2000);

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();
        Thread.sleep(3000);

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(5000);
        //////
        log("/*35_1.----Refresh page again - should not be like that again --*/");
        inClinicExperience_CP.refreshBrowser();
        Thread.sleep(5000);
        Thread.sleep(10000); //wait for Related Tab accordion loading
        ///////

        log("/*36.----click on In-clinic Experience button --*/");
        inClinicExperience_CP.ClickGoToInClinicExperienceButton();
        Thread.sleep(5000);

        log("/*37.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience_CP.validateVaccineAdminPageOpen();
        Thread.sleep(5000);

        log("/*38.---Click confirm and Save Button --*/");
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
        Thread.sleep(5000);

        log("/*39.---select Vaccine Agent picklist Value ->  Influenza-LAIV --*/");
        inClinicExperience_CP.selectVaccineAgentInfluenza();
        Thread.sleep(3000);

        log("/*40.---Click Save Consent Button --*/");
        inClinicExperience_CP.ClickSaveConsentButton();
        Thread.sleep(5000);

        log("/*41.---Click Save button for Immunisation Information --*/");
        inClinicExperience_CP.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);

        log("/*42.---Click Confirm and Save Administration Button --*/");
        inClinicExperience_CP.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(5000);

        log("/*43.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience_CP.ClickModalConfirmAndSaveAdministrationButton();
        Thread.sleep(3000);

        log("/*44.---the Home - Client Search showing up  --*/");
        inClinicExperience_CP.validateHomePageShownUp();
        Thread.sleep(3000);

    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }



}
