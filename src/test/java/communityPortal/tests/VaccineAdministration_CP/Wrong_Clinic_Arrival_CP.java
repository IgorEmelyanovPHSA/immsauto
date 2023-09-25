package communityPortal.tests.VaccineAdministration_CP;

import Utilities.ApiQueries;
import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class Wrong_Clinic_Arrival_CP extends BaseTest {
    String env;
    Map<String, Object> testData;
    private String legalFirstName = "Hugues";
    private String legalLastName = "BCVaxLampard";
    private String legalMiddleName = "Fawn";
    private String dateOfBirth = "March 3, 1904";
    private String postalCode = "V1N3Q3";
    private String personalHealthNumber = "9746171121";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic";
    String citizenName = "Hugues Fawn BCVaxLampard";
    String consumptionLot;
    String consumptionDose;
    String consumptionAgent;
    String consumptionProvider;
    String consumptionRoute;
    String consumptionSite;

    @Test
    public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician_CP() throws Exception {
        TestcaseID = "245216"; //C245216
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + Utils.getTargetEnvironment());
        testData = Utils.getTestData(env);
        consumptionDose = String.valueOf(testData.get("consumptionDose"));
        consumptionAgent = String.valueOf(testData.get("agentConsumption"));
        consumptionProvider = String.valueOf(testData.get("providerConsumption"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        consumptionLot = String.valueOf(testData.get("consumptionLot"));
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
        if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
            inClinicExperience_CP.clickNonIndigenousRadioButton();
        }

        log("/*10.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();

        log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessageAppear();

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

//        log("/*18.----click on person Account Related Tab --*/");
//        inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*19----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateToVaccineSchedulingTab();

        //In case of early booking screen appeared
        try {
            PersonAccountPage.selectEarlyBookingReason(driver);
        } catch(Exception ex) {
            System.out.println("No early booking button. Continue...");
        }
        log("/*20.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();

        ////////////////////
        //May will be removed
        //PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
        ///////////////////

        log("/*21----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();

        log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);

        log("/*23----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();

        log("/*24----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay(1);

        log("/*25----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();

        log("/*26----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();

        log("/*27----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation();

        log("/*28----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();

        log("/*29. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();

        log("/*30.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*31.----Refresh page - need to be fixed by dev's --*/");
        inClinicExperience_CP.refreshBrowser();

        log("/*32.---Click Go To In clinic experience button --*/");

        inClinicExperience_CP.ClickGoToInClinicExperienceButton();

        String originalBooking = inClinicExperience_CP.getAppointmentClinicName();
        log("/*--- Before Booking clinic Value is:" + originalBooking + "");

        //Assert originalBooking are match to actual booking (clinicNameToSearch)
        Assert.assertTrue(clinicNameToSearch.equalsIgnoreCase(originalBooking));

        log("/*33.--- User can click Rebook Appointment button to book an appointment --*/");
        inClinicExperience_CP.ClickRebookAppointment();
        Thread.sleep(1000);
        try {
            inClinicExperience_CP.clickCloseAlert();
        } catch(Exception ex) {
            System.out.println("Alert not found. Proceed...");
        }
        inClinicExperience_CP.ValidateClickRebookAppointmentButtonIsDisabled();
        log("/*--  We need to add Validation for 1.(Clinic has changed & address has changed) --*/");
        log("/*--                                2. Rebook at Current Location button is disabled --*/");

        String afterRebooking = inClinicExperience_CP.getAppointmentClinicName();
        log("/*--- After Booking clinic value is:" + afterRebooking + "");

        //Assert false clinic originalBooking is not equals to afterRebooking
        Assert.assertFalse(originalBooking.equalsIgnoreCase(afterRebooking));

        log("/*34.---'Rebook at Current Location button is disabled after user books appointment --*/");
        inClinicExperience_CP.ValidateClickRebookAppointmentButtonIsDisabled();
        log("/*35.---Click confirm and Save Button on Home Page --*/");
        inClinicExperience_CP.HomePageClickConfirmAndSaveButton();

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

        String consentProvider = inClinicExperience_CP.consentProviderSelected();
        if(consentProvider.equals("")) {
            inClinicExperience_CP.selectConsentProvider();
            consentProvider = inClinicExperience_CP.consentProviderSelected();
        }
        inClinicExperience_CP.ClickSaveConsentButton();
        System.out.println("/*48_.---Click Save button for Immunisation Information --*/");
        String agent = inClinicExperience_CP.getVaccineAgent();
        System.out.println("Current Agent: " + agent);
        String provider =  inClinicExperience_CP.getProvider();
        System.out.println("Current Provider: " + provider);
        String route = inClinicExperience_CP.getRoute();
        System.out.println("Current Route: " + route);
        String site = inClinicExperience_CP.getSite();
        System.out.println("Current Site: " + site);
        String lot = inClinicExperience_CP.getLotNumber();
        System.out.println("Current Lot: " + lot);
        log("/*42.---Click Save Consent Button --*/");

        if(!provider.equals(consentProvider)) {
            inClinicExperience_CP.setProvider(consentProvider);
        }

        log("/*43.---select Dosage ->  -.5 --*/");
        if(!lot.equals(consumptionLot)) {
            inClinicExperience_CP.setLotNumber(consumptionLot);
        }
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
//No save consent button?
//        log("/*38.---Click Save Consent Button --*/");
//        inClinicExperience_CP.ClickSaveConsentButton();
        log("/*39.---Save Immunization Information ---*/");
        inClinicExperience_CP.saveImmunizationInformation();

        //If expired lop click Ok
        inClinicExperience_CP.clickOkForExpiredLot();
        /////////

        log("/*40.---Click Confirm and Save Administration Button --*/");
        inClinicExperience_CP.ClickConfirmAndSaveAdministrationButton();
        log("/*41.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience_CP.ClickModalConfirmAndSaveAdministrationButton();

        log("/*42.---the Home - Client Search supposed to showing up  --*/");
        inClinicExperience_CP.validateHomePageShownUp();
        inClinicExperience_CP.refreshBrowser();
        log("/*43.---Search for Participant account --*/");
        //ProfilesPage profilesPage = cpMainPage.globalSearch_CP(citizenName);
        cpMainPage.search(legalFirstName + " " + legalLastName);
        log("/*44.---select Citizen Participant acc from search results --*/");
        ProfilesPage profilesPage = new ProfilesPage(driver);
        profilesPage.selectCitizenParticipant(legalFirstName + " " + legalLastName);

        log("/*45.---Navigate to Person Account related tab ---*/");
        profilesPage.clickRelatedTab();
        inClinicExperience_CP = new InClinicExperiencePage(driver);
        log("/*46.---Immunization status is in After Care ---*/");
        inClinicExperience_CP.ValidateStatusisInAftercare();

        log("/*47.---User Navigated to Appointment Section  ---*/");
        inClinicExperience_CP.NavigateToAppointmentsSection();

        log("/*48.---- An previous appointment for the user has been cancelled with reebooking of an appointment ---*/");
        inClinicExperience_CP.ValidateAppointmentCancelledIsPresentCP();

        log("/*49.---- An confirmed appointmrnt is found for the user  ---*/");
        inClinicExperience_CP.ValidateAppointmentConfirmIsPresentCP();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
