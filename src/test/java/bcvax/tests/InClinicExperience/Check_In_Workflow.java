package bcvax.tests.InClinicExperience;

import bcvax.tests.BaseTest;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

public class Check_In_Workflow extends BaseTest {
    MainPageOrg orgMainPage;
    String env;
    private String legalFirstName = "Courtnay";
    private String legalLastName = "BCVaxGoncaves";
    private String dateOfBirth = "Nov 29, 1949";
    private String postalCode = "V3J3Y1";
    private String personalHealthNumber = "9746172961";
    private String citizenName;
    Map<String, Object> testData;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String vaccineToSelect = "Covid19Vaccine";
    InClinicExperiencePage inClinicExperience;
    @Test(priority = 1)
    public void verify_Check_In_Citizen_Workflow_From_Appointment() throws Exception {
        TestcaseID = "273419"; //C223614
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        registerCitizen();
        citizenName = legalFirstName + " " + legalLastName;
        orgMainPage.logout();
        loginPage.loginAsImmsBCAdmin();
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }
        inClinicExperience = new InClinicExperiencePage(driver);
        log("/*2.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
        log("/*----6. select Citizen from search results --*/");
        orgMainPage.globalSearch(citizenName);
        log("/*3.----click on person Account Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        log("/*4.----Verify no Imms record was created --*/");
        int records_count = PersonAccountRelatedPage.getImmunizationRecordsSize(driver);
        Assert.assertTrue(records_count == 0);
        log("/*5.----Verify nteh appointment is booked successfully --*/");
        bookAppointment();
        inClinicExperience.clickUserDefaultsTab();
        inClinicExperience.clickClientListTab();
        inClinicExperience.clickTodayAppointments();
        String my_status = inClinicExperience.getTodayAppointmentPathwayStatus(citizenName);
        log("/*6.----Verify no Imms record was created --*/");
        Assert.assertTrue(my_status.isEmpty());
        boolean view_button_exist = inClinicExperience.todayAppointmentViewButtonExists(citizenName);
        log("/*7.----Verify View button is not shown --*/");
        Assert.assertFalse(view_button_exist);
        boolean checkin_button_exist = inClinicExperience.todayAppointmentCheckinButtonExists(citizenName);
        log("/*8.----Verify Checkin button is shown --*/");
        Assert.assertTrue(checkin_button_exist);
        inClinicExperience.clickTodayAppointmentCheckinButton(citizenName);
        String current_tab = inClinicExperience.getCurrentTab();
        log("/*9.----Verify User is redirected to Identification --*/");
        Assert.assertTrue(current_tab.equals("Identification"));
        orgMainPage.globalSearch(citizenName);
        PersonAccountPage.goToRelatedTab(driver);
        String pathway_status = PersonAccountRelatedPage.getImmunizationRecordPathwayStatus(driver);
        log("/*10.----Verify Pathway Status is New --*/");
        Assert.assertTrue(pathway_status.equals("New"));
        inClinicExperience.clickClientListTab();
        inClinicExperience.clickTodayAppointments();
        my_status = inClinicExperience.getTodayAppointmentPathwayStatus(citizenName);
        log("/*11.----Verify Pthway Status is New --*/");
        Assert.assertTrue(my_status.equals("New"));
        view_button_exist = inClinicExperience.todayAppointmentViewButtonExists(citizenName);
        log("/*12.----Verify View button is shown --*/");
        Assert.assertTrue(view_button_exist);
        checkin_button_exist = inClinicExperience.todayAppointmentCheckinButtonExists(citizenName);
        log("/*12.----Verify Checkin button is not shown --*/");
        Assert.assertFalse(checkin_button_exist);
        System.out.println();
    }

    @Test(priority = 2)
    public void verify_Check_In_Citizen_Workflow() throws Exception {
        TestcaseID = "273419";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        registerCitizen();
        citizenName = legalFirstName + " " + legalLastName;
        orgMainPage.logout();
        loginPage.loginAsImmsBCAdmin();
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }
        inClinicExperience = new InClinicExperiencePage(driver);
        log("/*2.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
        log("/*----6. select Citizen Previously registered citizen --*/");
        orgMainPage.globalSearch(citizenName);
        log("/*----7. Click Checkin button --*/");
        PersonAccountPage.clickCheckInButton(driver);
        String current_tab = inClinicExperience.getCurrentTab();
        log("/*----8. Verify the User is redirected to Identification page --*/");
        Assert.assertTrue(current_tab.equals("Identification"));
        orgMainPage.globalSearch(citizenName);
        PersonAccountPage.goToRelatedTab(driver);
        String pathway_status = PersonAccountRelatedPage.getImmunizationRecordPathwayStatus(driver);
        log("/*----9. Verify the Pathway Status is New --*/");
        Assert.assertTrue(pathway_status.equals("New"));
        inClinicExperience.clickClientListTab();
        inClinicExperience.clickTodayAppointments();
        String my_status = inClinicExperience.getTodayAppointmentPathwayStatus(citizenName);
        log("/*----10. Verify the Pathway Status is New --*/");
        Assert.assertTrue(my_status.equals("New"));
        boolean view_button_exist = inClinicExperience.todayAppointmentViewButtonExists(citizenName);
        log("/*----11. Verify View Button exists --*/");
        Assert.assertTrue(view_button_exist);
        boolean checkin_button_exist = inClinicExperience.todayAppointmentCheckinButtonExists(citizenName);
        log("/*----12. Verify Checkin Button doesn't exist --*/");
        Assert.assertFalse(checkin_button_exist);
        orgMainPage.globalSearch(citizenName);
        PersonAccountPage.goToRelatedTab(driver);
        PersonAccountPage.clickCheckInButton(driver);
        current_tab = inClinicExperience.getCurrentTab();
        log("/*----13. Verify the User is redirected to Identification page --*/");
        Assert.assertTrue(current_tab.equals("Identification"));
        inClinicExperience.HomePageClickConfirmAndSaveButton();
        inClinicExperience.clickTodayAppointments();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);
        my_status = inClinicExperience.getTodayAppointmentPathwayStatus(citizenName);
        log("/*----14. Verify the Pathway Status is Vaccine_Administration --*/");
        Assert.assertEquals(my_status,"Vaccine_Administration");
        view_button_exist = inClinicExperience.todayAppointmentViewButtonExists(citizenName);
        log("/*----15. Verify View Button exist --*/");
        Assert.assertTrue(view_button_exist);
        checkin_button_exist = inClinicExperience.todayAppointmentCheckinButtonExists(citizenName);
        log("/*----16. Verify Checkin Button doesn't exist --*/");
        Assert.assertFalse(checkin_button_exist);
        System.out.println();
    }

    private void registerCitizen() throws Exception {
        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }

        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        log("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        log("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        log("/*6.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
        log("/*-- 13. Enter current date for UserDefaults --*/");
        userDefaultsPage.inputCurrentDateUserDefaults();
        userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
        log("/*7.----- Click on Save defaults button --*/");
        userDefaultsPage.clickBtnSave();
        AlertDialog.closeAlert(driver);

        log("/*3.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        log("/*4.----click Register New Citizen --*/");

        inClinicExperience.clickRegisterTab();
        System.out.println("/*10.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();
        log("/*5.----Enter First Name: " +legalFirstName +"--*/");
        inClinicExperience.enterFirstName(legalFirstName);
        log("/*6.----Enter Last Name: " +legalLastName +"--*/");
        inClinicExperience.enterLastName(legalLastName);
        log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
        inClinicExperience.enterDateOfBirth(dateOfBirth);
        log("/*7.----Enter Postal code: " +postalCode +"--*/");
        inClinicExperience.enterPostalCode(postalCode);
        log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
        inClinicExperience.enterPNH(personalHealthNumber);

        log("/*10.----click Verify PHN button --*/");
        inClinicExperience.clickVerifyPHNButton();
        log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience.successMessageAppear();
        log("/*12.----click Next button --*/");
        inClinicExperience.clickNextButton();
        log("/*13.'Enter email address: " +email +"--*/");
        inClinicExperience.enterEmail(email);
        log("/*14.'Confirm email address: " +email +"--*/");
        inClinicExperience.confirmEmail(email);
        log("/*15.Click review details Button--*/");
        inClinicExperience.clickReviewDetails();
        log("/*16.Click register Button on confirmation page--*/");
        inClinicExperience.clickRegisterButtonOnConfirmationPage();
        log("/*17.--toast success message - 'Success' --*/");
        inClinicExperience.successRegisteredMessageAppear();
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
        boolean appointment_result = inClinicExperience.AppointmentConfirmationMessage();
        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
    }
}
