package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class E2E_Consumption_CP extends BaseTest {
    String env;
    private String legalFirstName = "Courtnay";
    private String legalLastName = "BCVaxGoncaves";
    private String dateOfBirth = "Nov 29, 1949";
    private String postalCode = "V3J3Y1";
    private String personalHealthNumber = "9746172961";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    Map<String, Object> testData;
    String clinicNameToSearch;
    String supplyContainer;
    String supplyDistribution;
    String consumptionLot;
    String consumptionDose;
    String consumptionAgent;
    String consumptionProvider;
    String consumptionRoute;
    String consumptionSite;

    @Test(priority = 1)
    public void Validate_Consumption_as_an_Inventory_Clinician_CP() throws Exception {
        TestcaseID = "243115"; //C243115
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + Utils.getTargetEnvironment());
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        supplyDistribution = String.valueOf(testData.get("distributionConsumption"));
        supplyContainer = String.valueOf(testData.get("containerConsumption"));
        consumptionDose = String.valueOf(testData.get("consumptionDose"));
        consumptionAgent = String.valueOf(testData.get("agentConsumption"));
        consumptionProvider = String.valueOf(testData.get("providerConsumption"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        consumptionLot = String.valueOf(testData.get("consumptionLot"));
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as an Inventory Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianInventory();

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();

        log("/*3.----Navigate to Supply Console Page --*/");
        SupplyConsolePage supplyConsolePage = cpMainPage.goToSupplyLocation();

        log("/*4. Locate and Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
        supplyConsolePage.selectSupplyLocationName(clinicNameToSearch);

        double remainingDoses_before = supplyConsolePage.getValueOfRemainingDoses(supplyContainer, supplyDistribution);
        log("/*6. remaining doses Before: -->" + remainingDoses_before);

        double remainingQty_before = supplyConsolePage.getValueOfRemainingQty(supplyContainer, supplyDistribution);
        log("/*7. remaining Qty Before: -->" + remainingQty_before);
        long conversionFactor = round(remainingDoses_before / remainingQty_before);
        log("/*8.----- Click on User Defaults Tab --*/");
        cpMainPage.clickUserDefaultsTab();
        log("/*9.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);

        userDefaultPage.inputCurrentDateUserDefaults();
        userDefaultPage.selectUserDefaultLocation(clinicNameToSearch);
        log("/*10.----- Click on Save defaults button --*/");
        userDefaultPage.clickBtnSave();
        log("/*11.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*12.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();

        log("/*13.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience_CP.enterFirstName(legalFirstName);

        log("/*14.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience_CP.enterLastName(legalLastName);

        log("/*15.----Enter Date of birth " +dateOfBirth +"--*/");
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
        //log("/*22.----click on person Account Related Tab --*/");
        //inClinicExperience_CP.clickOnPersonAccountRelatedTab();

        log("/*23----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateToVaccineSchedulingTab();
        try {
            inClinicExperience_CP.selectEarlyBookingReason();
        } catch(Exception ex) {
            System.out.println("Early Booking reason option is not found. Continue...");
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

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab();
        Thread.sleep(500);
        log("/*36.----click on In-clinic Experience button --*/");
        inClinicExperience_CP.clickCheckInButton();
        Thread.sleep(2000);
        inClinicExperience_CP.HomePageClickConfirmAndSaveButton();
        Thread.sleep(2000);
        //log("/*37.----In-clinic Experience ->Vaccine Admin page appears up --*/");
       // inClinicExperience_CP.validateVaccineAdminPageOpen();
        //inClinicExperience_CP.clickCloseAlert();
        //log("/*38.---Click confirm and Save Button --*/");


        log("/*39.---Open Today's appointments from Home page --*/");
        inClinicExperience_CP.clickTodayAppointments();
        log("/*40.---Open Today appointment Details --*/");
        inClinicExperience_CP.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
        log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");


        inClinicExperience_CP.selectVaccineAgent();
        Thread.sleep(2000);

        String consentProvider = inClinicExperience_CP.consentProviderSelected();
        if(consentProvider.equals("")) {
            consentProvider = inClinicExperience_CP.selectConsentProvider();
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
        log("/*44.---Click Save button for Immunisation Information --*/");
        inClinicExperience_CP.ClickSaveImmuneInfoSaveButton();
        // If expired vaccine click OK in confirmation dialogue
        inClinicExperience_CP.clickOkForExpiredLot();

        log("/*45.---Click Confirm and Save Administration Button --*/");
        inClinicExperience_CP.ClickConfirmAndSaveAdministrationButton();
        log("/*46.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience_CP.ClickModalConfirmAndSaveAdministrationButton();
        log("/*47.---the Home - Client Search showing up  --*/");
        inClinicExperience_CP.validateHomePageShownUp();

        supplyConsolePage = new SupplyConsolePage(driver);
        supplyConsolePage = cpMainPage.goToSupplyLocation();
        log("/*-- 53. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
        supplyConsolePage.selectSupplyLocationName(clinicNameToSearch);
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        //////////Validation for Dosages and Qty After Consumption
        System.out.println("/*--55.----Validate Remaining Doses and Remaining Quantities values after Consuming --*/");
        double remainingDoses_after = supplyConsolePage.getValueOfRemainingDoses(supplyContainer, supplyDistribution);
        log("/*-- 56. remaining doses After Consumption: -->" + remainingDoses_after);
        assertEquals(remainingDoses_after, remainingDoses_before - 1);
        double remainingQty_after = supplyConsolePage.getValueOfRemainingQty(supplyContainer, supplyDistribution);
        log("/*-- 57. remaining Qty After: -->" + remainingQty_after);
        assertEquals(remainingQty_after, round((remainingDoses_before - 1)/conversionFactor), 2);
        supplyConsolePage.closeTabsHCA();
        log("/*-- 58. Close all open tabs --*/");
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }



}
