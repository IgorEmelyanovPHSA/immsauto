package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.MainPageOrg;
import bcvax.pages.UserDefaultsPage;
import bcvax.tests.BaseTest;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import constansts.Apps;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Dose1_E2E_Pneumo extends BaseTest {
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    String lotNumber = "T005729-CC07";
    MainPageOrg orgMainPage;

    @Test(priority = 1)
    public void Can_do_Dose1_Pneumo_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        TestcaseID = "229058"; //C229058
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        System.out.println("/*1.----Login as an Clinician to ICE --*/");
        loginPage.loginAsClinicianICE();
        Thread.sleep(2000);
        orgMainPage = new MainPageOrg(driver);

        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }
        Thread.sleep(2000);
        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        System.out.println("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        Thread.sleep(2000);
        System.out.println("/*6.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
        userDefaultPage.inputCurrentDateUserDefaults();

        System.out.println("/*7.----- Click on Save defaults button --*/");
        userDefaultPage.clickBtnSave();

        System.out.println("/*8.----- Click on register Tab --*/");
        inClinicExperience.clickRegisterTab();
        //System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
        //inClinicExperience.clickSaveModalDefaultsButton();
        //Thread.sleep(2000);
        System.out.println("/*10.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();
        System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience.enterFirstName(legalFirstName);
        System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience.enterLastName(legalLastName);
        System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience.enterDateOfBirth(dateOfBirth);
        System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience.enterPostalCode(postalCode);
        System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience.enterPNH(personalHealthNumber);
        System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience.clickNonIndigenousRadioButton();
        System.out.println("/*17.----click Verify PHN button --*/");
        inClinicExperience.clickVerifyPHNButton();
        System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience.successMessageAppear();
        System.out.println("/*19.----click Next button --*/");
        inClinicExperience.clickNextButton();
        System.out.println("/*20.----'Enter email address " +email +"--*/");
        inClinicExperience.enterEmail(email);
        System.out.println("/*21.----'Confirm email address " +email +"--*/");
        inClinicExperience.confirmEmail(email);
        System.out.println("/*22.---Click review details Button--*/");
        inClinicExperience.clickReviewDetails();
        System.out.println("/*23.----Click register Button on confirmation page--*/");
        inClinicExperience.clickRegisterButtonOnConfirmationPage();
        System.out.println("/*24.--toast success message - 'Success' --*/");
        inClinicExperience.successRegisteredMessageAppear();
        Thread.sleep(3000);
        System.out.println("/*25.----click on person Account Related Tab --*/");
        inClinicExperience.clickOnPersonAccountRelatedTab();
        //System.out.println("/*24.----click on Eligibility button --*/");s
        //inClinicExperience.clickEligibilityButton();
        //Thread.sleep(2000);
        //System.out.println("/*25----select vaccination option -> COVID_19_Vaccination --*/");
        //inClinicExperience.selectCovid19option();
        //Thread.sleep(2000);
        //System.out.println("/*26.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
        //inClinicExperience.userIsEligibleSuccessMsg();
        //Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        System.out.println("/*26----Go to Appointment Tab --*/");
        inClinicExperience.navigateToVaccineSchedulingTab();

        log("/*27.----click on the Vaccine 'COVID-19' checkbox --*/");
        log("/*----scroll down a bit --*/");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
        inClinicExperience.clickOnVaccinationCheckbox();

        //System.out.println("/*29----click on 'More' search tab --*/");
        //inClinicExperience.clickOnMoreSearchTabs();
        //Thread.sleep(2000);

        System.out.println("/*27----select 'Search by Clinic name' tab --*/");
        log("/*----scroll down a bit --*/");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
        inClinicExperience.selectSearchByClinicNameTab();

        log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience.searchClinicName(clinicNameToSearch);
        System.out.println("/*29----click on Option Facility location  --*/");
        inClinicExperience.clickOnFacilityOptionLocation();
        System.out.println("/*30----select Active booking appointment day  --*/");
        inClinicExperience.selectBookingAppointmentDay();
        System.out.println("/*31----select the time slot  --*/");
        inClinicExperience.selectTimeSlotForAppointment();
        System.out.println("/*32----click Next button  --*/");
        inClinicExperience.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);
        System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
        inClinicExperience.clickVerifyContactInformation();
        System.out.println("/*34----click Confirm Appointment button  --*/");
        inClinicExperience.clickAppointmentConfirmButton();

        System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience.AppointmentConfirmationMessage();

        System.out.println("/*36.----Refresh page --*/");
        inClinicExperience.refreshBrowser();
        System.out.println("/*37.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience.clickRelatedTab();
        System.out.println("/*38.----click on In-clinic Experience button --*/");
        inClinicExperience.ClickGoToInClinicExperienceButton();
        //InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
        System.out.println("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience.validateVaccineAdminPageOpen();
        System.out.println("/*40.---Click confirm and Save Button --*/");
        inClinicExperience.HomePageClickConfirmAndSaveButton();
        System.out.println("/*41.---select Vaccine Agent picklist Value ->  Pneumo-P-23 --*/");
        inClinicExperience.selectVaccineAgentPneumo();

        System.out.println("/*42.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(2000);

        System.out.println("/*42_.---Select Route from DropDown -> Intranasal --*/");
        //inClinicExperience.checkShowDepletedLots();
        Thread.sleep(1000);
        inClinicExperience.setLotNumber(lotNumber);
        inClinicExperience.selectRouteIntranasal();
        inClinicExperience.setSite("Nares - Left");


        System.out.println("/*42__.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        System.out.println("/*43.---Click Confirm and Save Administration Button --*/");
        inClinicExperience.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(2000);
        System.out.println("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
        System.out.println("/*45.---the Home - Client Search showing up  --*/");
        Thread.sleep(2000);
        inClinicExperience.validateHomePageShownUp();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

}
