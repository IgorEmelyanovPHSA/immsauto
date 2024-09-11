package bcvax.tests.InClinicExperience;

import bcvax.tests.BaseTest;

import bcvax.pages.*;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class Check_In_Workflow extends BaseTest {
    MainPageOrg orgMainPage;
    String env;
    Map<String, Object> testData;
    String clinicNameToSearch;
    private String citizenName;
    private String vaccineToSelect = "Covid19Vaccine";
    InClinicExperiencePage inClinicExperience;
    Map<String, String> client_data;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "consent");
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        citizenName = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void verify_Check_In_Citizen_Workflow_From_Appointment() throws Exception {
        TestcaseID = "223614";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        registerCitizen();
        orgMainPage.logout();
        loginPage.loginAsImmsBCAdmin();
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }
        inClinicExperience = new InClinicExperiencePage(driver);
        log("/*2.----Close All previously opened Tab's --*/");
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
        }
        log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
        log("/*----6. select Citizen from search results --*/");
        MainPageOrg.search(driver, citizenName);
        log("/*3.----click on person Account Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        log("/*4.----Verify no Imms record was created --*/");
        int records_count = PersonAccountRelatedPage.getImmunizationRecordsSize(driver);
        Assert.assertTrue(records_count == 0);
        log("/*5.----Verify nteh appointment is booked successfully --*/");
        bookAppointment();
        InClinicExperiencePage.clickUserDefaultsTab(driver);
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*7.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        InClinicExperiencePage.clickClientListTab(driver);
        InClinicExperiencePage.clickTodayAppointments(driver);
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        if(my_appointment_info.size() == 0) {
            driver.navigate().refresh();
            Thread.sleep(500);
            InClinicExperiencePage.clickTodayAppointments(driver);
            my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        }
        String my_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_appointment_info);
        log("/*6.----Verify no Imms record was created --*/");
        Assert.assertTrue(my_status.isEmpty());
        boolean view_button_exist = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_appointment_info);
        log("/*7.----Verify View button is not shown --*/");
        Assert.assertFalse(view_button_exist);
        boolean checkin_button_exist = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_appointment_info);
        log("/*8.----Verify Checkin button is shown --*/");
        Assert.assertTrue(checkin_button_exist);
        ClientListTodayAppointmentsTab.clickCheckInButton(driver, my_appointment_info);
        String current_tab = InClinicExperiencePage.getCurrentTab(driver);
        log("/*9.----Verify User is redirected to Identification --*/");
        Assert.assertTrue(current_tab.equals("Identification"));
        MainPageOrg.search(driver, citizenName);
        PersonAccountPage.goToRelatedTab(driver);
        String pathway_status = PersonAccountRelatedPage.getImmunizationRecordPathwayStatus(driver);
        log("/*10.----Verify Pathway Status is New --*/");
        Assert.assertTrue(pathway_status.equals("New"));
        InClinicExperiencePage.clickClientListTab(driver);
        InClinicExperiencePage.clickTodayAppointments(driver);
        my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        my_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_appointment_info);
        log("/*11.----Verify Pthway Status is New --*/");
        Assert.assertTrue(my_status.equals("New"));
        view_button_exist = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_appointment_info);
        log("/*12.----Verify View button is shown --*/");
        Assert.assertTrue(view_button_exist);
        checkin_button_exist = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_appointment_info);
        log("/*12.----Verify Checkin button is not shown --*/");
        Assert.assertFalse(checkin_button_exist);
        System.out.println();
    }

    //@Test(priority = 2)
    public void verify_Check_In_Citizen_Workflow() throws Exception {
        TestcaseID = "273419";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        registerCitizen();
        try {
            orgMainPage.logout();
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            orgMainPage.logout();
        }
        loginPage.loginAsImmsBCAdmin();
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }
        inClinicExperience = new InClinicExperiencePage(driver);
        log("/*2.----Close All previously opened Tab's --*/");
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
        }
        log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
        log("/*----6. select Citizen Previously registered citizen --*/");
        MainPageOrg.search(driver, citizenName);
        log("/*----7. Click Checkin button --*/");
        PersonAccountPage.clickCheckInButton(driver);
        String current_tab = InClinicExperiencePage.getCurrentTab(driver);
        log("/*----8. Verify the User is redirected to Identification page --*/");
        Assert.assertTrue(current_tab.equals("Identification"));
        MainPageOrg.search(driver, citizenName);
        PersonAccountPage.goToRelatedTab(driver);
        String pathway_status = PersonAccountRelatedPage.getImmunizationRecordPathwayStatus(driver);
        log("/*----9. Verify the Pathway Status is New --*/");
        Assert.assertTrue(pathway_status.equals("New"));
        InClinicExperiencePage.clickClientListTab(driver);
        InClinicExperiencePage.clickTodayAppointments(driver);
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        String my_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_appointment_info);
        log("/*----10. Verify the Pathway Status is New --*/");
        Assert.assertTrue(my_status.equals("New"));
        boolean view_button_exist = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_appointment_info);
        log("/*----11. Verify View Button exists --*/");
        Assert.assertTrue(view_button_exist);
        boolean checkin_button_exist = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_appointment_info);
        log("/*----12. Verify Checkin Button doesn't exist --*/");
        Assert.assertFalse(checkin_button_exist);
        MainPageOrg.search(driver, citizenName);
        PersonAccountPage.goToRelatedTab(driver);
        PersonAccountPage.clickCheckInButton(driver);
        current_tab = InClinicExperiencePage.getCurrentTab(driver);
        log("/*----13. Verify the User is redirected to Identification page --*/");
        Assert.assertTrue(current_tab.equals("Identification"));
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
        InClinicExperiencePage.clickTodayAppointments(driver);
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);
        my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        my_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_appointment_info);
        log("/*----14. Verify the Pathway Status is Vaccine_Administration --*/");
        Assert.assertEquals(my_status,"Vaccine_Administration");
        view_button_exist = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_appointment_info);
        log("/*----15. Verify View Button exist --*/");
        Assert.assertTrue(view_button_exist);
        checkin_button_exist = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_appointment_info);
        log("/*----16. Verify Checkin Button doesn't exist --*/");
        Assert.assertFalse(checkin_button_exist);
        System.out.println();
    }

    private void registerCitizen() throws Exception {
        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }

        log("/*4.----Close All previously opened Tab's --*/");
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
        }
        log("/*5.----- Click on User Defaults Tab --*/");
        InClinicExperiencePage.clickUserDefaultsTab(driver);
        log("/*6.----- Enter current date for UserDefaults --*/");
        log("/*-- 13. Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*7.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);

        log("/*3.----Close All previously opened Tab's --*/");
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
        }
        log("/*4.----click Register New Citizen --*/");

        InClinicExperiencePage.clickRegisterTab(driver);
        System.out.println("/*10.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
    }

    private void bookAppointment() throws InterruptedException {
        log("/*4----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);
        Thread.sleep(2000);
        //If override Eligibility is shown
        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
        ////////////////////
        //May will be removed
        //PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
        ///////////////////
        log("/*24----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        log("/*25----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        log("/*26----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
        log("/*27----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        log("/*28----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        log("/*29----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
        log("/*30----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        log("/*31----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);
        log("/*32----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
    }
}
