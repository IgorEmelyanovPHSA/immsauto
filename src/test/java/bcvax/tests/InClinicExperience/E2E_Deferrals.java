package bcvax.tests.InClinicExperience;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import bcvax.tests.Preconditions;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class E2E_Deferrals extends BaseTest {

    Map<String, String> client_data;
    Map<String, Object> testData;
    MainPageOrg orgMainPage;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "deferral");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }
    @Test
    public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        String env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
//        test_data.setFirstName("Ab");
//        test_data.setLastName("Said");
//        test_data.setDateOfBirth("Jan 02, 2023");
//        test_data.setPostalCode("V3L5L2");
//        test_data.setPersonalHealthNumber("9879450975");
//        test_data.setEmail("accountToDelete@phsa.ca");
//        test_data.setClinicName(String.valueOf(testData.get("supplyLocationConsumption")));
//        test_data.setDose(String.valueOf(testData.get("covidDose")));
//        test_data.setLot(String.valueOf(testData.get("covidLot")));
//        test_data.setRoute(String.valueOf(testData.get("routeConsumption")));
//        test_data.setSite(String.valueOf(testData.get("siteConsumption")));
//        test_data.setConsentProvider(String.valueOf(testData.get("consentProvider")));
//        test_data.setAgent("Covid19Vaccine");
        log("/*1.----Login --*/");
        try {
            LoginPage.loginAsImmsBCAdmin(driver);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        String currentApp = MainPageOrg.currentApp(driver);
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
        }
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }
        InClinicExperiencePage.closeTabsHCA(driver);
        log("/*5.----- Click on User Defaults Tab --*/");
        InClinicExperiencePage.clickUserDefaultsTab(driver);
        log("/*6.----- Enter current date for UserDefaults --*/");
        log("/*-- 13. Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, String.valueOf(testData.get("supplyLocationConsumption")));
        log("/*7.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
        }
        MainPageOrg.closeAllTabs(driver);
        MainPageOrg.selectFromNavigationMenu(driver, "Home");
        log("/*3.----Close All previously opened Tab's --*/");

        log("/*4.----click Register New Citizen --*/");

        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
        Thread.sleep(500);
        try {
            PersonAccountPage.goToVaccineScheduleTab(driver);
        } catch (ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToVaccineScheduleTab(driver);
        }
        try {
            System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");

        } catch(Exception ex) {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
            Thread.sleep(500);
            System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        }



        System.out.println("/*27----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

        log("/*28.----search the Clinic " + String.valueOf(testData.get("supplyLocationConsumption")) + " --*/");
        PersonAccountSchedulePage.searchClinicName(driver, String.valueOf(testData.get("supplyLocationConsumption")));

        System.out.println("/*29----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
        System.out.println("/*30----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        System.out.println("/*31----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        System.out.println("/*32----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
        System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        System.out.println("/*34----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);
        System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
        System.out.println("/*38.----click on In-clinic Experience button --*/");
        PersonAccountPage.clickCheckInButton(driver);

        System.out.println("/*40.---Click confirm and Save Button --*/");
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

        log("/*46.---Open Today's appointments from Home page --*/");
        InClinicExperiencePage.clickClientListTab(driver);
        ClientListPage.clickTodayAppointmentsTab(driver);
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
        System.out.println();
    }
}
