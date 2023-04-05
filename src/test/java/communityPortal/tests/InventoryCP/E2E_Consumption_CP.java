package communityPortal.tests.InventoryCP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class E2E_Consumption_CP extends BaseTest {
    String env;
    private String legalFirstName = "Dacia";
    private String legalLastName = "Bcvaxdod";
    private String dateOfBirth = "May 19, 1904";
    private String postalCode = "V7N3K1";
    private String personalHealthNumber = "9746172456";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    Map<String, Object> testData;
    String clinicNameToSearch;
    String supplyContainer;
    String supplyDistribution;
    String dose;

    @Test(priority = 1)
    public void Validate_Consumption_as_an_Inventory_Clinician_CP() throws Exception {
        TestcaseID = "243115"; //C243115
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + Utils.getTargetEnvironment());
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        supplyDistribution = String.valueOf(testData.get("distributionConsumption"));
        supplyContainer = String.valueOf(testData.get("containerConsumption"));
        dose = String.valueOf(testData.get("consumptionDose"));
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as an Inventory Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianInventory();
        Thread.sleep(10000);

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        Thread.sleep(5000);

        log("/*3.----Navigate to Supply Console Page --*/");
        SupplyConsolePage supplyConsolePage = cpMainPage.goToSupplyLocation();
        Thread.sleep(5000);

        log("/*4. Locate and Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
        supplyConsolePage.selectSupplyLocationName(clinicNameToSearch);
        Thread.sleep(5000);

        //log("/*5. Click and navigate to the supply container --> 'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)' --*/");
        //supplyConsolePage.selectSupplyContainer();
        //Thread.sleep(5000);

        double remainingDoses_before = supplyConsolePage.getValueOfRemainingDoses(supplyContainer, supplyDistribution);
        log("/*6. remaining doses Before: -->" + remainingDoses_before);
        Thread.sleep(5000);

        double remainingQty_before = supplyConsolePage.getValueOfRemainingQty(supplyContainer, supplyDistribution);
        log("/*7. remaining Qty Before: -->" + remainingQty_before);
        Thread.sleep(5000);
        long conversionFactor = round(remainingDoses_before / remainingQty_before);
        log("/*8.----- Click on User Defaults Tab --*/");
        cpMainPage.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*9.----- Enter current date for UserDefaults --*/");
        cpMainPage.inputCurrentDateUserDefaults();
        cpMainPage.selectUserDefaultLocation(clinicNameToSearch);
        log("/*10.----- Click on Save defaults button --*/");
        cpMainPage.clickSaveDefaultsButton();
        Thread.sleep(5000);

        log("/*11.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();
        Thread.sleep(2000);

        log("/*12.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();
        Thread.sleep(2000);

        log("/*13.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience_CP.enterFirstName(legalFirstName);
        Thread.sleep(2000);

        log("/*14.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience_CP.enterLastName(legalLastName);
        Thread.sleep(2000);

        log("/*15.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience_CP.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience_CP.enterPostalCode(postalCode);
        Thread.sleep(2000);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience_CP.enterPNH(personalHealthNumber);
        Thread.sleep(2000);
        log("/*13.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience_CP.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        log("/*14.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();
        Thread.sleep(2000);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessage();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*16.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();
        Thread.sleep(2000);
        log("/*17.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);
        log("/*18.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        inClinicExperience_CP.confirmEmail(email);
        log("/*19.---Click review details Button--*/");
        Thread.sleep(2000);
        inClinicExperience_CP.clickReviewDetails();
        log("/*20.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        log("/*21.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        Thread.sleep(10000); //wait for Related Tab showing up

        log("/*22.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();
        Thread.sleep(5000);//wait for accordion loading

        log("/*23----Go to Appointment Tab --*/");
        inClinicExperience_CP.navigateAppointmentSchedulingTab_CP();
        Thread.sleep(5000);

        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience_CP.clickOnVaccinationCheckbox();
        Thread.sleep(2000);

        System.out.println("/*25----select 'Search by Clinic name' tab --*/");
        inClinicExperience_CP.selectSearchByClinicNameTab();
        Thread.sleep(2000);

        log("/*26.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience_CP.searchClinicName(clinicNameToSearch);
        Thread.sleep(2000);

        log("/*27----click on Option Facility location  --*/");
        inClinicExperience_CP.clickOnFacilityOptionLocation();
        Thread.sleep(2000);

        log("/*28----select Active booking appointment day  --*/");
        inClinicExperience_CP.selectBookingAppointmentDay();
        Thread.sleep(2000);

        log("/*29----select the time slot  --*/");
        inClinicExperience_CP.selectTimeSlotForAppointment();
        Thread.sleep(2000);

        log("/*30----click Next button  --*/");
        inClinicExperience_CP.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);

        log("/*31----click Verify Contact Information Checkbox  --*/");
        inClinicExperience_CP.clickVerifyContactInformation_CP();
        Thread.sleep(2000);

        log("/*32----click Confirm Appointment button  --*/");
        inClinicExperience_CP.clickAppointmentConfirmButton();
        Thread.sleep(2000);

        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience_CP.AppointmentConfirmationMessage();
        Thread.sleep(3000);

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab_CP();
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
        inClinicExperience_CP.clickCloseAlert();
        Thread.sleep(2000);
        log("/*38.---Click confirm and Save Button --*/");
        inClinicExperience_CP.HomePageClickConfirmAndSaveButton();
        Thread.sleep(5000);

        try {
            log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
            inClinicExperience_CP.selectVaccineAgent();
            Thread.sleep(3000);
        } catch(Exception ex) {
            log("/*39.---Open Today's appointments from Home page --*/");

            inClinicExperience_CP.clickTodayAppointments();
            Thread.sleep(3000);
            log("/*40.---Open Today appointment Details --*/");
            inClinicExperience_CP.clickTodayAppointmentCaseViewButton();
            Thread.sleep(5000);
            log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
            inClinicExperience_CP.selectVaccineAgent();
            Thread.sleep(3000);
        }

        log("/*42.---Click Save Consent Button --*/");
        inClinicExperience_CP.ClickSaveConsentButton();
        Thread.sleep(5000);
        log("/*43.---select Dosage ->  -.5 --*/");
        inClinicExperience_CP.selectConsumptionDosage(dose);
        Thread.sleep(2000);
        log("/*44.---Click Save button for Immunisation Information --*/");
        inClinicExperience_CP.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);

        log("/*45.---Click Confirm and Save Administration Button --*/");
        inClinicExperience_CP.ClickConfirmAndSaveAdministrationButton();
        Thread.sleep(5000);

        log("/*46.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience_CP.ClickModalConfirmAndSaveAdministrationButton();
        Thread.sleep(3000);

        log("/*47.---the Home - Client Search showing up  --*/");
        inClinicExperience_CP.validateHomePageShownUp();
        Thread.sleep(3000);

        supplyConsolePage = new SupplyConsolePage(driver);
//		if (inClinicExperiencePage.supplyLocDisplayed()) {
//			log("/*-- 52.1 User is already on Supply loc --*/");
//		} else {
//			log("/*-- 52.1. Click Dropdown Menu --*/");
//			inClinicExperiencePage.clickDropdownMenu();
//			Thread.sleep(5000);
//			log("/*-- 52.2. Navigate and Select Supply Locations --*/");
//			inClinicExperiencePage.selectSupplyLocationFromDropdown();
//			Thread.sleep(2000);
//		}

        supplyConsolePage = cpMainPage.goToSupplyLocation();
        log("/*-- 53. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
        supplyConsolePage.selectSupplyLocationName(clinicNameToSearch);
        Thread.sleep(2000);
//		log("/*-- 54. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EL0203' --*/");
//		inClinicExperiencePage.selectSupplyContainer();
//		Thread.sleep(2000);
        //////////Validation for Dosages and Qty After Consumption
        System.out.println("/*--55.----Validate Remaining Doses and Remaining Quantities values after Consuming --*/");
        double remainingDoses_after = supplyConsolePage.getValueOfRemainingDoses(supplyContainer, supplyDistribution);
        log("/*-- 56. remaining doses After Consumption: -->" + remainingDoses_after);
        assertEquals(remainingDoses_after, remainingDoses_before - 1);
        Thread.sleep(2000);
        double remainingQty_after = supplyConsolePage.getValueOfRemainingQty(supplyContainer, supplyDistribution);
        log("/*-- 57. remaining Qty After: -->" + remainingQty_after);
        assertEquals(remainingQty_after, round((remainingDoses_before - 1)/conversionFactor), 2);
        Thread.sleep(2000);
        supplyConsolePage.closeTabsHCA();
        log("/*-- 58. Close all open tabs --*/");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }



}
