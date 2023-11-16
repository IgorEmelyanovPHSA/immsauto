package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;


@Listeners({TestListener.class})
public class Dose1_E2E_Influenza extends BaseTest {
    String env;
    Map<String, Object> testData;
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    MainPageOrg orgMainPage;
    String influenzaLot;
    String influenzaDose;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;
    String influenzaAgent;

    @Test(priority = 1)
    public void Can_do_Dose1_Influenza_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        TestcaseID = "228859"; //C228859
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        influenzaDose = String.valueOf(testData.get("influenzaDose"));
        influenzaLot = String.valueOf(testData.get("influenzaLot"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        influenzaAgent = String.valueOf(testData.get("agentInfluenza"));
        log("/*1.----Login as an Clinician to ICE --*/");

        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        //orgMainPage = loginPage.orgLoginAsPPHIS();
        TestcaseID = "222694"; //

        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        log("TestRail test case ID: C" +TestcaseID);

        log("/*2.----In Clinic Experience(ICE) page displayed --*/");
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }
        log("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();

        log("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
        log("/*6.----- Enter current date for UserDefaults --*/");
        userDefaultPage.inputCurrentDateUserDefaults();

        log("/*7.----- Click on Save defaults button --*/");
        userDefaultPage.clickBtnSave();

        log("/*inClinicExperience.clickSaveDefaultsButton();8.----- Click on register Tab --*/");
        inClinicExperience.clickRegisterTab();

        log("/*9.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();

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

        log("/*16.----click Verify PHN button --*/");
        inClinicExperience.clickVerifyPHNButton();

        log("/*17.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience.successMessageAppear();

        log("/*18.----click Next button --*/");
        inClinicExperience.clickNextButton();

        log("/*19.----'Enter email address " +email +"--*/");
        inClinicExperience.enterEmail(email);

        log("/*20.----'Confirm email address " +email +"--*/");
        inClinicExperience.confirmEmail(email);

        log("/*21.---Click review details Button--*/");
        inClinicExperience.clickReviewDetails();

        log("/*22.----Click register Button on confirmation page--*/");
        inClinicExperience.clickRegisterButtonOnConfirmationPage();

        log("/*23.--toast success message - 'Success' --*/");
        inClinicExperience.successRegisteredMessageAppear();

        log("/*24.----click on person Account Related Tab --*/");
        inClinicExperience.clickOnPersonAccountRelatedTab();

        log("/*25.----Go to Appointment Tab --*/");
        inClinicExperience.navigateToVaccineSchedulingTab();

        log("/*26.----click on the Vaccine 'Influenza' checkbox --*/");
        inClinicExperience.clickOnVaccinationInfluenzaCheckbox();

        log("/*27.----select 'Search by Clinic name' tab --*/");
        inClinicExperience.selectSearchByClinicNameTab();

        log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience.searchClinicName(clinicNameToSearch);

        log("/*29.----click on Option Facility location  --*/");
        inClinicExperience.clickOnFacilityOptionLocation();

        log("/*30.----select Active booking appointment day  --*/");
        inClinicExperience.selectBookingAppointmentDay();

        log("/*31.----select the time slot  --*/");
        inClinicExperience.selectTimeSlotForAppointment();

        log("/*32.----click Next button  --*/");
        inClinicExperience.clickNextButtonApptSchedulingPage();

        log("/*33.----click Verify Contact Information Checkbox  --*/");
        inClinicExperience.clickVerifyContactInformation();

        log("/*34.----click Confirm Appointment button  --*/");
        inClinicExperience.clickAppointmentConfirmButton();

        log("/*35. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience.AppointmentConfirmationMessage();

        log("/*36.----Refresh page --*/");
        inClinicExperience.refreshBrowser();

        log("/*37.----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);

        log("/*38.----click on In-clinic Experience button --*/");
        PersonAccountPage.clickCheckInButton(driver);

        log("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience.validateVaccineAdminPageOpen();

        log("/*40.---Click confirm and Save Button --*/");
        inClinicExperience.HomePageClickConfirmAndSaveButton();
        log("/*46.---Open Today's appointments from Home page --*/");

        inClinicExperience.clickTodayAppointments();
        log("/*47.---Open Today appointment Details --*/");
        Thread.sleep(2000);
        inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
        log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
        log("/*41.---select Vaccine Agent picklist Value ->  Influenza-LAIV --*/");
        inClinicExperience.selectVaccineAgent(influenzaAgent);


        try {
            ProfilesPage.confirm_warning(driver);
        } catch(Exception ex) {
            System.out.println("No Warning found");
        }

        try {
            ProfilesPage.checkExistingConsent(driver);
        } catch(Exception ex) {
            System.out.println("No Checkbox. Continue...");
        }

        String agent = inClinicExperience.getVaccineAgent();
        String provider =  inClinicExperience.getProvider();
        String route = inClinicExperience.getRoute();
        String site = inClinicExperience.getSite();

        String lot = inClinicExperience.getLotNumber();

        log("/*42.---Click Save Consent Button --*/");

        if(!provider.equals(consentProvider)) {
            inClinicExperience.setProvider(consentProvider);
        }

        log("/*43.---select Dosage ->  -.5 --*/");
        if(!lot.equals(influenzaLot)) {
            inClinicExperience.setLotNumber(influenzaLot);
        }
        String dose = inClinicExperience.getDosage();

        if(!dose.equals(influenzaDose)) {
            inClinicExperience.setDosage(influenzaDose);
        }
        if(route.equals("")) {
            inClinicExperience.setRoute(consumptionRoute);
        }
        if(site.equals("")) {
            inClinicExperience.setSite(consumptionSite);
        }

        log("/*43.---select Dosage ml from DropDown -> 0.2 mL --*/");
        inClinicExperience.selectDosageVaccineAdmin();
        inClinicExperience.selectNotApprovedAdministrationReason();
        log("/*44.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        inClinicExperience.clickOkForExpiredLot();
        log("/*45.---Click Confirm and Save Administration Button --*/");
        inClinicExperience.ClickConfirmAndSaveAdministrationButton();

        log("/*46.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();

        log("/*47.---the Home - Client Search showing up  --*/");
        inClinicExperience.validateHomePageShownUp();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

}
