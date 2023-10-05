package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class E2E_Dose1_Covid19_CP extends BaseTest{
    String env;
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch;
    Map<String, Object> testData;
    String consumptionAgent;
    String consumptionLot;
    String consumptionDose;
    String consumptionProvider;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;


    @Test(priority = 1)
    public void Can_do_Dose1_Covid19_Vaccine_Administration_as_Clinician_CP() throws Exception {
        TestcaseID = "243203"; //C243203 needs to changed for CP e2E Manual Regresion
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        CommonMethods commn = new CommonMethods(getDriver());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        consumptionAgent = String.valueOf(testData.get("agentConsumption"));
        consumptionDose = String.valueOf(testData.get("consumptionDose"));
        consumptionLot = String.valueOf(testData.get("consumptionLot"));
        consumptionProvider = String.valueOf(testData.get("providerConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        log("/*1.----Login as an Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();

        log("/*3.----- Click on User Defaults Tab --*/");
        cpMainPage.clickUserDefaultsTab();

        log("/*4.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
        userDefaultPage.inputCurrentDateUserDefaults();
        userDefaultPage.selectUserDefaultLocation(clinicNameToSearch);
        log("/*5.----- Click on Save defaults button --*/");
        userDefaultPage.clickBtnSave();
        AlertDialog.closeAlert(driver);
        Thread.sleep(2000);

        log("/*6.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();
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
        if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
            inClinicExperience_CP.clickNonIndigenousRadioButton();
        }
        log("/*14.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessageAppear();

        log("/*16.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();
        log("/*17.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);
        log("/*18.----'Confirm email address " +email +"--*/");
        inClinicExperience_CP.confirmEmail(email);
        log("/*19.---Click review details Button--*/");
        inClinicExperience_CP.clickReviewDetails();
        log("/*20.----Click register Button on confirmation page--*/");
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();
        log("/*21.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();

        log("/*22.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*23----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateToVaccineSchedulingTab();

//        try {
//            System.out.println("---click on reason Early Booking Reason - Travel --*/");
//            PersonAccountPage.selectEarlyBookingReason(driver);
//        } catch(Exception ex) {
//            System.out.println("There is not Early Booking Option");
//        }

        //If override Eligibility is shown
        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountPage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }
        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();

        ////////////////////
        //May will be removed
        //PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
        ///////////////////

        System.out.println("/*25----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);

        log("/*27----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();

        log("/*28----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();

        log("/*29----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();

        log("/*30----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();

        log("/*31----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation();

        log("/*32----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = inClinicExperience_CP.AppointmentConfirmationMessage();
        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
        inClinicExperience_CP.clickCheckInButton();
        log("/*35.----Go to back to the Citizen Related Tab --*/");
        //inClinicExperience_CP.clickOnPersonAccountRelatedTab();
        //////
        //log("/*35_1.----Refresh page again - should not be like that again --*/");
        //inClinicExperience_CP.refreshBrowser();
        ///////
        //Thread.sleep(2000);
        //log("/*36.----click on In-clinic Experience button --*/");
        //inClinicExperience_CP.ClickGoToInClinicExperienceButton();

        //log("/*37.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        //inClinicExperience_CP.validateVaccineAdminPageOpen();
        //Thread.sleep(2000);
        log("/*38.---Click confirm and Save Button --*/");
        inClinicExperience_CP.HomePageClickConfirmAndSaveButton();
        Thread.sleep(2000);

        log("/*39.---Open Today's appointments from Home page --*/");
        Thread.sleep(2000);
        inClinicExperience_CP.clickTodayAppointments();
        Thread.sleep(2000);
        log("/*40.---Open Today appointment Details --*/");
        inClinicExperience_CP.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
        log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
        Thread.sleep(2000);
        Thread.sleep(1000);
        inClinicExperience_CP.selectVaccineAgentValue(consumptionAgent);
//        String consentProvider = inClinicExperience_CP.consentProviderSelected();
//        Thread.sleep(2000);
//        if(consentProvider.equals("")) {
//            consentProvider = inClinicExperience_CP.selectConsentProvider();
//        }
//        Thread.sleep(2000);
//        inClinicExperience_CP.ClickSaveConsentButton();
//        Thread.sleep(2000);
//        log("/*---Click Save button for Immunisation Information --*/");
//        if(consentProvider.equals("")) {
//            consentProvider = inClinicExperience_CP.selectConsentProvider();
//        }
        try {
            ProfilesPage.checkExistingConsent(driver);
        } catch(Exception ex) {
            System.out.println("No Checkbox. Continue...");
        }

        ProfilesPage.clickEditImmunizationInformation(driver);


        Thread.sleep(1000);
        String agent = inClinicExperience_CP.getVaccineAgent();
        Thread.sleep(2000);
        String provider =  inClinicExperience_CP.getProvider();
        String route = inClinicExperience_CP.getRoute();
        String site = inClinicExperience_CP.getSite();

        String lot = inClinicExperience_CP.getLotNumber();

        log("/*42.---Click Save Consent Button --*/");

        if(!provider.equals(consentProvider)) {
            inClinicExperience_CP.setProvider(consentProvider);
        }

        log("/*43.---select Dosage ->  -.5 --*/");
        if(!lot.equals(consumptionLot)) {
            inClinicExperience_CP.setLotNumber(consumptionLot);
        }
        Thread.sleep(2000);
        String dose = inClinicExperience_CP.getDosage();

        if(!dose.equals(consumptionDose)) {
            inClinicExperience_CP.setDosage(consumptionDose);
        }
        if(route.equals("")) {
            inClinicExperience_CP.setRoute(consumptionRoute);
        }
        if(site.equals("")) {
            inClinicExperience_CP.setSite(consumptionSite);
        }
        log("/*41.---Click Save button for Immunisation Information --*/");
        inClinicExperience_CP.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(2000);
        inClinicExperience_CP.clickOkForExpiredLot();
        Thread.sleep(2000);
        log("/*42.---Click Confirm and Save Administration Button --*/");
        inClinicExperience_CP.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(2000);
        log("/*43.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience_CP.ClickModalConfirmAndSaveAdministrationButton();

        log("/*44.---the Home - Client Search showing up  --*/");
        inClinicExperience_CP.validateHomePageShownUp();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
    }

}
