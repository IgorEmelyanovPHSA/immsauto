package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class E2E_Dose1_Covid19_CP extends BaseTest{
    String env;
    Map<String, String> client_data;
    String clinicNameToSearch;
    Map<String, Object> testData;
    String consumptionAgent;
    String consumptionLot;
    String consumptionDose;
    String consumptionProvider;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;
//Pneumo TC "243211",

    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose1");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void Can_do_Dose1_Covid19_Vaccine_Administration_as_Clinician_CP() throws Exception {
        TestcaseID = "243203"; //C243203 needs to changed for CP e2E Manual Regresion
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
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
        MainPageCP.clickUserDefaultsTab(driver);

        log("/*4.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*5.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        Thread.sleep(2000);

        log("/*6.----Navigate to More -> Register --*/");
        MainPageCP.navigateToRegisterClientPage(driver);
        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(driver);

        log("/*7.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
        log("/*22.----click on person Account Related Tab --*/");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch (ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            PersonAccountPage.goToRelatedTab(driver);
        }

        log("/*23----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);

        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");

        try {
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        } catch(Exception ex) {
            PersonAccountSchedulePage.overrideEligibility(driver);
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        }

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

        PersonAccountPage.clickCheckInButton(driver);
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
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
        Thread.sleep(2000);

        log("/*39.---Open Today's appointments from Home page --*/");
        Thread.sleep(2000);
        InClinicExperiencePage.clickTodayAppointments(driver);
        Thread.sleep(2000);
        log("/*40.---Open Today appointment Details --*/");
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
        log("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
        Thread.sleep(2000);
        Thread.sleep(1000);
        InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);

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


        Thread.sleep(1000);
        String agent = InClinicExperienceVaccineAdministrationPage.getVaccineAgent(driver);
        Thread.sleep(2000);
        String provider =  InClinicExperienceVaccineAdministrationPage.getProvider(driver);
        String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
        String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);

        String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);

        log("/*42.---Click Save Consent Button --*/");

        if(!provider.equals(consentProvider)) {
            InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
        }

        log("/*43.---select Dosage ->  -.5 --*/");
        if(!lot.equals(consumptionLot)) {
            InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
        }
        Thread.sleep(2000);
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
        log("/*41.---Click Save button for Immunisation Information --*/");
        InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
        Thread.sleep(2000);
        InClinicExperiencePage.clickOkForExpiredLot(driver);
        Thread.sleep(2000);
        log("/*42.---Click Confirm and Save Administration Button --*/");
        InClinicExperienceVaccineAdministrationPage.ClickConfirmAndSaveAdministrationButton(driver);
        Thread.sleep(2000);
        log("/*43.---Click Modal screen Confirm&Save Administration Button --*/");
        InClinicExperienceVaccineAdministrationPage.ClickModalConfirmAndSaveAdministrationButton(driver);

        log("/*44.---the Home - Client Search showing up  --*/");
        InClinicExperiencePage.validateHomePageShownUp(driver);
    }
}
