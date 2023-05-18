package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.tests.BaseTest;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Dose1_E2E_Influenza extends BaseTest {
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test(priority = 1)
    public void Can_do_Dose1_Influenza_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        TestcaseID = "228859"; //C228859
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as an Clinician to ICE --*/");
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICE();
       // InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianInfluenzaICE();
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
        log("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        Thread.sleep(5000);

        log("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*6.----- Enter current date for UserDefaults --*/");
        inClinicExperience.inputCurrentDateUserDefaults();
        Thread.sleep(2000);

        log("/*7.----- Click on Save defaults button --*/");

        Thread.sleep(2000);

        log("/*inClinicExperience.clickSaveDefaultsButton();8.----- Click on register Tab --*/");
        inClinicExperience.clickRegisterTab();
        Thread.sleep(2000);

        log("/*9.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();
        Thread.sleep(2000);

        log("/*10.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience.enterFirstName(legalFirstName);

        log("/*11.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience.enterLastName(legalLastName);

        log("/*12.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience.enterDateOfBirth(dateOfBirth);

        log("/*13.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience.enterPostalCode(postalCode);

        log("/*14.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience.enterPNH(personalHealthNumber);

        log("/*15.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience.clickNonIndigenousRadioButton();

        log("/*16.----click Verify PHN button --*/");
        inClinicExperience.clickVerifyPHNButton();
        Thread.sleep(2000);

        log("/*17.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience.successMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*18.----click Next button --*/");
        inClinicExperience.clickNextButton();
        Thread.sleep(2000);

        log("/*19.----'Enter email address " +email +"--*/");
        inClinicExperience.enterEmail(email);

        log("/*20.----'Confirm email address " +email +"--*/");
        inClinicExperience.confirmEmail(email);

        log("/*21.---Click review details Button--*/");
        inClinicExperience.clickReviewDetails();
        Thread.sleep(2000);

        log("/*22.----Click register Button on confirmation page--*/");
        inClinicExperience.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);

        log("/*23.--toast success message - 'Success' --*/");
        inClinicExperience.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*24.----click on person Account Related Tab --*/");
        inClinicExperience.clickOnPersonAccountRelatedTab();
        Thread.sleep(5000);

        log("/*25.----Go to Appointment Tab --*/");
        inClinicExperience.navigateAppointmentSchedulingTab();
        Thread.sleep(5000);

        log("/*26.----click on the Vaccine 'Influenza' checkbox --*/");
        inClinicExperience.clickOnVaccinationInfluenzaCheckbox();
        Thread.sleep(5000);
        //System.out.println("/*29----click on 'More' search tab --*/");
        //inClinicExperience.clickOnMoreSearchTabs();
        //Thread.sleep(2000);
        log("/*27.----select 'Search by Clinic name' tab --*/");
        inClinicExperience.selectSearchByClinicNameTab();
        Thread.sleep(5000);

        log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience.searchClinicName(clinicNameToSearch);
        Thread.sleep(5000);

        log("/*29.----click on Option Facility location  --*/");
        inClinicExperience.clickOnFacilityOptionLocation();
        Thread.sleep(5000);

        log("/*30.----select Active booking appointment day  --*/");
        inClinicExperience.selectBookingAppointmentDay();
        Thread.sleep(2000);

        log("/*31.----select the time slot  --*/");
        inClinicExperience.selectTimeSlotForAppointment();
        Thread.sleep(2000);

        log("/*32.----click Next button  --*/");
        inClinicExperience.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);

        log("/*33.----click Verify Contact Information Checkbox  --*/");
        inClinicExperience.clickVerifyContactInformation();
        Thread.sleep(2000);

        log("/*34.----click Confirm Appointment button  --*/");
        inClinicExperience.clickAppointmentConfirmButton();
        Thread.sleep(2000);

        log("/*35. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience.AppointmentConfirmationMessage();
        Thread.sleep(2000);

        log("/*36.----Refresh page --*/");
        inClinicExperience.refreshBrowser();
        Thread.sleep(2000);

        log("/*37.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience.clickRelatedTab();
        Thread.sleep(2000);

        log("/*38.----click on In-clinic Experience button --*/");
        inClinicExperience.ClickGoToInClinicExperienceButton();
        //InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
        Thread.sleep(2000);

        log("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience.validateVaccineAdminPageOpen();
        Thread.sleep(5000);

        log("/*40.---Click confirm and Save Button --*/");
        inClinicExperience.HomePageClickConfirmAndSaveButton();
        Thread.sleep(5000);

        log("/*41.---select Vaccine Agent picklist Value ->  Influenza-LAIV --*/");
        inClinicExperience.selectVaccineAgentInfluenza();


        log("/*42.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);

        log("/*43.---select Dosage ml from DropDown -> 0.2 mL --*/");
        inClinicExperience.selectDosageVaccineAdmin();
        Thread.sleep(5000);

        log("/*44.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);

        log("/*45.---Click Confirm and Save Administration Button --*/");
        inClinicExperience.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(5000);

        log("/*46.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
        Thread.sleep(3000);

        log("/*47.---the Home - Client Search showing up  --*/");
        inClinicExperience.validateHomePageShownUp();
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

}
