package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import org.testng.annotations.Listeners;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class Dose1_E2E_Influenza_Pneumo extends BaseTest{
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
        TestcaseID = "229061"; //C229061
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        System.out.println("/*1.----Login as an Clinician to ICE --*/");
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianInfluenzaICE();
        Thread.sleep(10000);

        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        if (inClinicExperience.displayIceApp()) {
            log("/*-- User already on In-Clinic Experience page --*/");
        } else {
            log("/*-- Navigate to In-Clinic Experience page --*/");
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

        System.out.println("/*6.----- Enter current date for UserDefaults --*/");
        inClinicExperience.inputCurrentDateUserDefaults();
        Thread.sleep(2000);

        System.out.println("/*7.----- Click on Save defaults button --*/");
        inClinicExperience.clickSaveDefaultsButton();
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
        inClinicExperience.successMessage();
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
        inClinicExperience.clickOnPersonAccountRelatedTab();
        Thread.sleep(5000);

        System.out.println("/*26----Go to Appointment Tab --*/");
        inClinicExperience.navigateAppointmentSchedulingTab();
        Thread.sleep(5000);

        log("/*27.----click on the Vaccine 'Influenza' checkbox --*/");
        inClinicExperience.clickOnVaccinationInfluenzaCheckbox();
        Thread.sleep(2000);

        //System.out.println("/*29----click on 'More' search tab --*/");
        //inClinicExperience.clickOnMoreSearchTabs();
        //Thread.sleep(2000);

        System.out.println("/*27----select 'Search by Clinic name' tab --*/");
        inClinicExperience.selectSearchByClinicNameTab();
        Thread.sleep(2000);

        log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience.searchClinicName(clinicNameToSearch);
        Thread.sleep(2000);
        System.out.println("/*29----click on Option Facility location  --*/");
        inClinicExperience.clickOnFacilityOptionLocation();
        Thread.sleep(2000);
        System.out.println("/*30----select Active booking appointment day  --*/");
        inClinicExperience.selectBookingAppointmentDay();
        Thread.sleep(2000);
        System.out.println("/*31----select the time slot  --*/");
        inClinicExperience.selectTimeSlotForAppointment();
        Thread.sleep(2000);
        System.out.println("/*32----click Next button  --*/");
        inClinicExperience.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);
        System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
        inClinicExperience.clickVerifyContactInformation();
        Thread.sleep(2000);
        System.out.println("/*34----click Confirm Appointment button  --*/");
        inClinicExperience.clickAppointmentConfirmButton();
        Thread.sleep(2000);
        System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience.AppointmentConfirmationMessage();
        Thread.sleep(2000);
        System.out.println("/*36.----Refresh page --*/");
        inClinicExperience.refreshBrowser();
        Thread.sleep(2000);
        System.out.println("/*37.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience.clickRelatedTab();
        Thread.sleep(2000);

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
        Thread.sleep(5000);
        System.out.println("/*41.---select Vaccine Agent picklist Value ->  Influenza-LAIV --*/");
        inClinicExperience.selectVaccineAgentInfluenza();
        Thread.sleep(3000);
        System.out.println("/*42.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);
        System.out.println("/*43.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);

        // Administrate another Vaccine -> Pneumo
        System.out.println("/*-----Administrate another Vaccine -> Pneumo --*/");
        System.out.println("/*44.---Click Save Administration & Record Another Vaccine button --*/");
        inClinicExperience.ClickSaveAdministratorAndRecordAnotherVaccineButton();
        Thread.sleep(5000);

        //click - Confirm on "Do you want to administer another vaccine for this client?" modal screen
        System.out.println("/*45.---Click Confirm Button - to administer another(Pneumo) vaccine --*/");
        inClinicExperience.ClickConfirmAdminAnotherVaccineModalScreenButton();
        Thread.sleep(10000);

        //System.out.println("/*46.---Click Modal screen Confirm & Save Administration Button --*/");
        //inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
        //Thread.sleep(3000);

        System.out.println("/*46.---select Vaccine Agent picklist Value ->  Pneumo --*/");
        inClinicExperience.selectVaccineAgentPneumo();
        Thread.sleep(3000);

        System.out.println("/*47.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);

        System.out.println("/*48.---Select Pneumo Lot Number -> T005729-CC07 --*/");
        inClinicExperience.selectPneumoLotNumber();
        Thread.sleep(5000);

        System.out.println("/*48_.---Select Route from DropDown -> Intranasal --*/");
        inClinicExperience.selectRouteIntranasal();
        Thread.sleep(5000);

        System.out.println("/*48__.---Select Site -> Arm - Right deltoid --*/");
        inClinicExperience.selectRouteIntranasal();
        Thread.sleep(5000);

        System.out.println("/*49.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);

        System.out.println("/*50.---Click Confirm and Save Administration Button --*/");
        inClinicExperience.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(5000);

        System.out.println("/*51.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
        Thread.sleep(3000);

        System.out.println("/*52.---the Home - Client Search showing up  --*/");
        inClinicExperience.validateHomePageShownUp();
        Thread.sleep(3000);

        //////Validation for both Influenza and Pneumo AfterCare status
        log("/*----53. Search for Participant account: " +citizenName +" ---*/");
        inClinicExperience.SearchForCitizen(citizenName);
        Thread.sleep(3000);

        log("/*----54. select Citizen from search results --*/");
        inClinicExperience.userClickCitizenNew(nameToSearch);
        Thread.sleep(4000);

        log("/*---- 55. Navigate to Person Account related tab ---*/");
        inClinicExperience.clickRelatedTab();
        Thread.sleep(2000);

        log("/*-- 56. Validate Aftercare status for Influenza --*/");
        //inClinicExperience.ValidateCreateImmunizationRecordButtonIsDisplayed();
        //Thread.sleep(2000);

        log("/*-- 57. Validate Aftercare status for Pneumo --*/");
        //inClinicExperience.ValidateCreateImmunizationRecordButtonIsDisplayed();
        //Thread.sleep(2000);

    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}