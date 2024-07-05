package bcvax.tests.InClinicExperience;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import bcvax.tests.Preconditions;
import constansts.Apps;
import java.util.List;
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
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
    }
    @Test
    public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        ////////////////////////////////////////////////////////
        //Precondition: Load the Citizen Vaccine Administration
        ////////////////////////////////////////////////////////
        TestcaseID = "225659";
        String env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);

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
        String citizenName = client_data.get("legalFirstName") + " " + (client_data.get("legalMiddleName").equals("") ? "" : client_data.get("legalMiddleName") + " ") + client_data.get("legalLastName");
        MainPageOrg.search(driver, citizenName);
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

        ///////////////////////////////////////////
        //End Of Precondition
        ///////////////////////////////////////////
        String active_deferrals_number = InClinicExperienceVaccineAdministrationPage.getActiveDeferralsNumber(driver);
        InClinicExperienceVaccineAdministrationPage.getDeferralsTable(driver);
        InClinicExperienceVaccineAdministrationPage.clickAddDeferralButton(driver);
        List<String> reasons = AddDeferralDialog.getListOfReasons(driver);
        AddDeferralDialog.selectAgent(driver, "COVID-19 mRNA");
        AddDeferralDialog.selectReasonForDeferral(driver, "Vaccine supply issues");
        AddDeferralDialog.setEffectiveFromDate(driver, 10);
        AddDeferralDialog.clickSaveButton(driver);
//--- Go back to Citizen profile->Related Tab
        MainPageOrg.search(driver, client_data.get("personalHealthNumber"));
        PersonAccountPage.goToRelatedTab(driver);
        PersonAccountRelatedPage.scrollToDeferrals(driver);
        System.out.println();
    }
}
