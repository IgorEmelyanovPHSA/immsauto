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
    String consentProvider;
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
        consentProvider = String.valueOf(testData.get("consentProvider"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        consumptionLot = String.valueOf(testData.get("consumptionLot"));
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

        log("/*1.----Login as Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

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
        MainPageCP.clickUserDefaultsTab(driver);
        log("/*9.----- Enter current date for UserDefaults --*/");

        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*10.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        log("/*11.----Navigate to More -> Register --*/");
        MainPageCP.navigateToRegisterClientPage(driver);
        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(driver);

        log("/*12.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);

        log("/*13.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);

        log("/*14.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);

        log("/*15.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);

        log("/*14.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);

        log("/*16.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        log("/*17.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        log("/*18.----'Confirm email address " +email +"--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);
        log("/*19.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        log("/*20.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        log("/*21.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

        log("/*23----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);

        //If override Eligibility is shown
        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }
        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");

        ////////////////////
        //May will be removed
        //PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
        ///////////////////

        System.out.println("/*25----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);

        log("/*27----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("/*28----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);

        log("/*29----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);

        log("/*30----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("/*31----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);

        log("/*32----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(500);
        log("/*36.----click on In-clinic Experience button --*/");
        PersonAccountPage.clickCheckInButton(driver);
        Thread.sleep(2000);
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
        Thread.sleep(2000);

        log("/*39.---Open Today's appointments from Home page --*/");
        inClinicExperience_CP.clickTodayAppointments();
        log("/*40.---Open Today appointment Details --*/");
        inClinicExperience_CP.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
        log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");

        InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);
        Thread.sleep(2000);

        try {
            PersonAccountRelatedPage.checkExistingConsent(driver);
        } catch(Exception ex) {
            System.out.println("No Checkbox. Continue...");
        }

        try {
            InClinicExperienceVaccineAdministrationPage.clickEditImmunizationInformation(driver);
        } catch(Exception ex) {
            System.out.println("Edit Button disabled. Continue...");
        }

        System.out.println("/*48_.---Click Save button for Immunisation Information --*/");
        String agent = InClinicExperienceVaccineAdministrationPage.getVaccineAgent(driver);
        System.out.println("Current Agent: " + agent);
        String provider =  InClinicExperienceVaccineAdministrationPage.getProvider(driver);
        System.out.println("Current Provider: " + provider);
        String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
        System.out.println("Current Route: " + route);
        String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);
        System.out.println("Current Site: " + site);
        String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);
        System.out.println("Current Lot: " + lot);
        log("/*42.---Click Save Consent Button --*/");

        if(!provider.equals(consentProvider)) {
            InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
        }

        log("/*43.---select Dosage ->  -.5 --*/");
        if(!lot.equals(consumptionLot)) {
            InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
        }
        String dose = InClinicExperienceVaccineAdministrationPage.getDosage(driver);

        if(!dose.equals(consumptionDose)) {
            InClinicExperienceVaccineAdministrationPage.setDosage(driver, consumptionDose);
        }
        if(route.equals("")) {
            InClinicExperienceVaccineAdministrationPage.setRoute(driver, consumptionRoute);
        }
        if(site.equals("")) {
            InClinicExperienceVaccineAdministrationPage.setSite(driver, consumptionSite);
        }
        log("/*44.---Click Save button for Immunisation Information --*/");
        InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
        // If expired vaccine click OK in confirmation dialogue
        inClinicExperience_CP.clickOkForExpiredLot();

        log("/*45.---Click Confirm and Save Administration Button --*/");
        inClinicExperience_CP.ClickConfirmAndSaveAdministrationButton();
        log("/*46.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience_CP.ClickModalConfirmAndSaveAdministrationButton();
        log("/*47.---the Home - Client Search showing up  --*/");
        InClinicExperiencePage.validateHomePageShownUp(driver);

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
        SupplyConsolePage.closeTabsHCA(driver);
        log("/*-- 58. Close all open tabs --*/");
    }
}
