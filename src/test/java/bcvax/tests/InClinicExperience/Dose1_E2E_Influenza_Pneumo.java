package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class Dose1_E2E_Influenza_Pneumo extends BaseTest {
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    String citizenName = "Ludovika BcvaxLimeburn";
    String nameToSearch = "Ludovika BcvaxLimeburn";


    @Test(priority = 1)
    public void Can_do_Multiple_Dose1_Influenza_Pneumo_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        TestcaseID = "229063"; //C229063
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        System.out.println("/*1.----Login as an Clinician to ICE --*/");
        loginPage.loginAsImmsBCAdmin();
        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        Thread.sleep(10000);

        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        if (inClinicExperience.displayIceApp()) {
            log("/*-- User already on In-Clinic Experience page --*/");
        } else {
            log("/*-- Navigate to In-Clinic Experience page --*/");
            Thread.sleep(5000);
            inClinicExperience.selectICEFromApp();
            Thread.sleep(5000);
        }

        Thread.sleep(5000);
        System.out.println("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        Thread.sleep(5000);

        System.out.println("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        Thread.sleep(2000);
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
        System.out.println("/*6.----- Enter current date for UserDefaults --*/");
        userDefaultPage.inputCurrentDateUserDefaults();
        Thread.sleep(2000);

        System.out.println("/*7.----- Click on Save defaults button --*/");
        userDefaultPage.clickBtnSave();
        Thread.sleep(2000);

        System.out.println("/*8.----- Click on register Tab --*/");
        inClinicExperience.clickRegisterTab();
        Thread.sleep(2000);

        System.out.println("/*10.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();
        Thread.sleep(2000);
        System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience.enterFirstName(legalFirstName);
        Thread.sleep(2000);
        System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience.enterLastName(legalLastName);
        Thread.sleep(2000);
        System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience.enterPostalCode(postalCode);
        Thread.sleep(2000);
        System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience.enterPNH(personalHealthNumber);
        Thread.sleep(2000);
        System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        System.out.println("/*17.----click Verify PHN button --*/");
        inClinicExperience.clickVerifyPHNButton();
        Thread.sleep(2000);
        System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience.successMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        System.out.println("/*19.----click Next button --*/");
        inClinicExperience.clickNextButton();
        Thread.sleep(2000);
        System.out.println("/*20.----'Enter email address " +email +"--*/");
        inClinicExperience.enterEmail(email);
        System.out.println("/*21.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        inClinicExperience.confirmEmail(email);
        System.out.println("/*22.---Click review details Button--*/");
        Thread.sleep(2000);
        inClinicExperience.clickReviewDetails();
        System.out.println("/*23.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        inClinicExperience.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        System.out.println("/*24.--toast success message - 'Success' --*/");
        inClinicExperience.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        System.out.println("/*25.----click on person Account Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(5000);

        System.out.println("/*26----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);
        Thread.sleep(15000);

        log("/*27.----click on the Vaccine 'Influenza' checkbox --*/");
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Influenza Vaccine");
        Thread.sleep(2000);

        //System.out.println("/*29----click on 'More' search tab --*/");
        //inClinicExperience.clickOnMoreSearchTabs();
        //Thread.sleep(2000);

        System.out.println("/*27----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        Thread.sleep(2000);

        log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        Thread.sleep(2000);
        System.out.println("/*29----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
        Thread.sleep(2000);
        System.out.println("/*30----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        Thread.sleep(2000);
        System.out.println("/*31----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        Thread.sleep(2000);
        System.out.println("/*32----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
        Thread.sleep(5000);
        System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        Thread.sleep(2000);
        System.out.println("/*34----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);
        Thread.sleep(2000);
        System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience.AppointmentConfirmationMessage();
        Thread.sleep(5000);
        System.out.println("/*36.----Refresh page --*/");
        inClinicExperience.refreshBrowser();
        Thread.sleep(5000);
        System.out.println("/*37.----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(5000);

        // Administrate first Vaccine -> Influenza
        System.out.println("/*-----Administrate another Vaccine -> Influenza --*/");
        System.out.println("/*38.----click on In-clinic Experience button --*/");
        inClinicExperience.ClickGoToInClinicExperienceButton();
        //InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
        Thread.sleep(2000);
        System.out.println("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience.validateVaccineAdminPageOpen();
        Thread.sleep(5000);
        System.out.println("/*40.---Click confirm and Save Button --*/");
        inClinicExperience.HomePageClickConfirmAndSaveButton();
        Thread.sleep(7000);
        System.out.println("/*41.---select Vaccine Agent picklist Value ->  Influenza-LAIV --*/");
        inClinicExperience.selectVaccineAgentInfluenza();
        Thread.sleep(3000);
        System.out.println("/*42.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);

        System.out.println("/*43.---select Dosage ml from DropDown -> 0.2 mL --*/");
        inClinicExperience.selectDosageVaccineAdmin();
        Thread.sleep(5000);

        System.out.println("/*44.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);


        // Administrate another Vaccine -> Pneumo
        System.out.println("/*-----Administrate another Vaccine -> Pneumo --*/");
        System.out.println("/*45.---Click Save Administration & Record Another Vaccine button --*/");
        inClinicExperience.ClickSaveAdministratorAndRecordAnotherVaccineButton();
        Thread.sleep(5000);

        //click - Confirm on "Do you want to administer another vaccine for this client?" modal screen
        System.out.println("/*46.---Click Confirm Button - to administer another(Pneumo) vaccine --*/");
        inClinicExperience.ClickConfirmAdminAnotherVaccineModalScreenButton();
        Thread.sleep(15000);

        //System.out.println("/*47.---Click Modal screen Confirm & Save Administration Button --*/");
        //inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
        //Thread.sleep(3000);

        System.out.println("/*47.---select Vaccine Agent picklist Value ->  Pneumo --*/");
        inClinicExperience.selectVaccineAgentPneumo();
        Thread.sleep(3000);

        System.out.println("/*48.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);

        System.out.println("/*49.---Select Pneumo Lot Number -> T005729-CC07 --*/");
        inClinicExperience.selectPneumoLotNumber();
        Thread.sleep(5000);

        System.out.println("/*50.---Select Route from DropDown -> Intranasal --*/");
        inClinicExperience.selectRouteIntranasal();
        Thread.sleep(5000);

        System.out.println("/*51.---Select Site -> Arm - Right deltoid --*/");
        inClinicExperience.selectSiteLeftDeltoid();
        Thread.sleep(5000);

        System.out.println("/*52.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);

        System.out.println("/*53.---Click Confirm and Save Administration Button --*/");
        inClinicExperience.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(5000);

        System.out.println("/*54.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
        Thread.sleep(3000);

        System.out.println("/*55.---the Home - Client Search showing up  --*/");
        inClinicExperience.validateHomePageShownUp();
        Thread.sleep(3000);

        //////Validation for both Influenza and Pneumo AfterCare status
        log("/*----56.0 Jump to CIB App from ICE, because Global search doesn't work from ICE --*/");
        inClinicExperience.selectCIBApp();
        Thread.sleep(4000);

        log("/*----56. Search for Participant account: " +citizenName +" ---*/");
        inClinicExperience.SearchForCitizen(citizenName);
        Thread.sleep(3000);

        log("/*----57. select Citizen from search results --*/");
        inClinicExperience.userClickCitizen(nameToSearch);
        Thread.sleep(4000);

        log("/*---- 58. Navigate to Person Account related tab ---*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(2000);

        log("/*-- 59. Validate Aftercare status for Influenza --*/");
        inClinicExperience.ValidateCreateImmunizationRecordInfluenzaDisplayed();
        Thread.sleep(2000);
        inClinicExperience.ValidateAfterCareStatusImmunizationRecordInfluenza();
        Thread.sleep(2000);

        log("/*-- 60. Validate Aftercare status for Pneumo --*/");
        inClinicExperience.ValidateCreateImmunizationRecordPneumoDisplayed();
        Thread.sleep(2000);
        inClinicExperience.ValidateAfterCareStatusImmunizationRecordPneumo();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
