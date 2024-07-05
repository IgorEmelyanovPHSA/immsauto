package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class E2E_Dose2_Covid19_CP extends BaseTest {
    String env;
    String clinicNameToSearch;
    Map<String, Object> testData;
    String consumptionAgent;
    String vaccineAgent;
    String consumptionLot;
    String consumptionDose;
    String consumptionProvider;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;
    Map<String, String> client_data;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose2");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        consumptionAgent = String.valueOf(testData.get("vaccineAgent"));
        vaccineAgent = String.valueOf(testData.get("vaccineAgent"));
        consumptionDose = String.valueOf(testData.get("consumptionDose"));
        consumptionLot = String.valueOf(testData.get("consumptionLot"));
        consumptionProvider = String.valueOf(testData.get("providerConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
    }

    @Test(priority = 1)
    public void Can_do_Dose2_Covid19_Vaccine_Administration_as_Clinician_CP() throws Exception {
        TestcaseID = "243156"; //C243156
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*1.----Login as an Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();

        log("/*3.----- Click on User Defaults Tab --*/");
        MainPageCP.clickUserDefaultsTab(driver);
        Thread.sleep(2000);
        log("/*4.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*5.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        Thread.sleep(7000);
        log("/*6.----Navigate to More -> Register --*/");
        MainPageCP.navigateToRegisterClientPage(driver);
        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(driver);

        log("/*7.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

        log("/*23----Go to Appointment Tab --*/");
        try {
            PersonAccountPage.goToVaccineScheduleTab(driver);
        } catch (ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToVaccineScheduleTab(driver);
        }

        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");

        try {
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        } catch(Exception ex) {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
            Thread.sleep(500);
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        }


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
        PersonAccountSchedulePage.appointmentConfirmationMessage(driver);

        log("/*35.----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);

        Thread.sleep(2000);
        log("/*36.----click on In-clinic Experience button --*/");

        PersonAccountPage.clickCheckInButton(driver);

        log("/*37.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience_CP.validateVaccineAdminPageOpen();
        Thread.sleep(2000);
        log("/*38.---Click confirm and Save Button --*/");
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
        Thread.sleep(2000);

        log("/*39.---Open Today's appointments from Home page --*/");
        Thread.sleep(2000);
        log("/*40.---Open Today appointment Details --*/");

        log("/*40.---Open Today's appointments from Home page --*/");
        Thread.sleep(2000);
        InClinicExperiencePage.clickTodayAppointments(driver);
        Thread.sleep(2000);
        log("/*41.---Open Today appointment Details --*/");
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
        Thread.sleep(2000);
        log("/*42.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
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

        Thread.sleep(2000);
        String agent = InClinicExperienceVaccineAdministrationPage.getVaccineAgent(driver);
        Thread.sleep(2000);
        String provider =  InClinicExperienceVaccineAdministrationPage.getProvider(driver);
        String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
        String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);

        String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);

        log("/*44.---Click Save Consent Button --*/");

        if(!provider.equals(consentProvider)) {
            InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
        }

        log("/*45.---select Dosage ->  -.5 --*/");
        if(!lot.equals(consumptionLot)) {
            try {
                InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
            } catch (NotFoundException ex) {
                InClinicExperienceVaccineAdministrationPage.checkShowDepletedLots(driver);
                Thread.sleep(2000);
                InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
            }
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
        log("/*46.---Click Save button for Immunisation Information --*/");
        InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
        Thread.sleep(2000);
        InClinicExperiencePage.clickOkForExpiredLot(driver);
        Thread.sleep(2000);
        log("/*47.---Click Confirm and Save Administration Button --*/");
        InClinicExperienceVaccineAdministrationPage.ClickConfirmAndSaveAdministrationButton(driver);
        Thread.sleep(2000);
        log("/*48.---Click Modal screen Confirm&Save Administration Button --*/");
        InClinicExperienceVaccineAdministrationPage.ClickModalConfirmAndSaveAdministrationButton(driver);

        log("/*49.---the Home - Client Search showing up  --*/");
        InClinicExperiencePage.validateHomePageShownUp(driver);
    }
}
