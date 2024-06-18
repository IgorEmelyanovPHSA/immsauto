package communityPortal.tests.VaccineAdministration_CP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.List;
import org.openqa.selenium.WebElement;

public class CheckInBetterManagement_CP extends BaseTest {
    String env;
    Map<String, String> client_data;
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose1");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void verifyCheckInAvoidMultipleImmunizationRecords_CP() throws Exception {
        TestcaseID = "273420";

        /////////////////////////////////////////////////////
        //*** No Exisiting Imms Records in Statu New ***
        //////////////////////////////////////////////////////

        log("/*1.----Login as Clinician to Community Portal --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();
//--- Preconditions. Register New Client ---
        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        MainPageCP.clickUserDefaultsTab(driver);
        log("/*9.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*10.----- Click on Save defaults button --*/");
        Thread.sleep(500);
        try {
            UserDefaultsPage.clickBtnSave(driver);
        } catch (StaleElementReferenceException ex) {
            Thread.sleep(2000);
            UserDefaultsPage.clickBtnSave(driver);
        }

        log("/*6.----Navigate to More -> Register --*/");
        Thread.sleep(500);
        MainPageCP.navigateToRegisterClientPage(driver);

        log("/*7.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
//        log("/*8.----Enter First Name " + client_data.get("legalFirstName") +"--*/");
//        CitizenPrimaryInfo.enterFirstName(driver, client_data.get("legalFirstName"));
//        log("/*9.----Enter Last Name " + client_data.get("legalLastName") +"--*/");
//        CitizenPrimaryInfo.enterLastName(driver, client_data.get("legalLastName"));
//        log("/*10.----Enter Date of birth " + Utils.convertDate(client_data.get("dateOfBirth"),"MMM dd, yyyy") +"--*/");
//        CitizenPrimaryInfo.enterDateOfBirth(driver, Utils.convertDate(client_data.get("dateOfBirth"),"MMM dd, yyyy"));
//        log("/*11.----Enter Postal code " + client_data.get("postalCode") +"--*/");
//        CitizenPrimaryInfo.enterPostalCode(driver, client_data.get("postalCode"));
//        log("/*12.----Enter PHN " + client_data.get("personalHealthNumber") +"--*/");
//        CitizenPrimaryInfo.enterPHN(driver, client_data.get("personalHealthNumber"));
//
//        log("/*14.----click Verify PHN button --*/");
//        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
//        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
//        CitizenPrimaryInfo.successMessageAppear(driver);
//
//        log("/*16.----click Next button --*/");
//        CitizenPrimaryInfo.clickNextButton(driver);
//        log("/*17.----'Enter email address " + client_data.get("email") +"--*/");
//        CitizenPrimaryInfo.enterEmail(driver, client_data.get("email"));
//        log("/*18.----'Confirm email address " + client_data.get("email") +"--*/");
//        CitizenPrimaryInfo.confirmEmail(driver, client_data.get("email"));
//        log("/*19.---Click review details Button--*/");
//        CitizenPrimaryInfo.clickReviewDetails(driver);
//        log("/*20.----Click register Button on confirmation page--*/");
//        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
//        log("/*21.--toast success message - 'Success' --*/");
//        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

        //--- Navigate to Citizen Profile->Related Tab
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            PersonAccountPage.goToRelatedTab(driver);
        }

        List<Map<String, WebElement>> immunization_records = PersonAccountRelatedPage.getImmunizationRecords(driver);
        //--- Verify No Immunization records in Related Tab (Table has only Headers
        Assert.assertEquals(immunization_records.size(), 1);

        //--- Book the Appointment for Covid
        PersonAccountPage.goToVaccineScheduleTab(driver);
        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        try {
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        } catch (Exception ex) {
            PersonAccountSchedulePage.overrideEligibility(driver);
            Thread.sleep(500);
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        }

        log("/*25----select 'Search by Clinic name' tab --*/");
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

        //--- Verify that the appointemnt was booked successfully
        log("/*33. ----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");

        //--- Go to User Defaults and setup Date and Clinic Location
        log("/*33. ----Click User Defaults Tab --*/");
        MainPageCP.clickUserDefaultsTab(driver);
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);

        //--- Go to Client List->Today's Appointments
        log("/*33. ----Click Client Lists Tab --*/");
        MainPageCP.clickClientListTab(driver);

        log("/*33. ----Click Client Lists Todays Appointments --*/");
        ClientListPage.clickTodayAppointmentsTab(driver);

        //---Verify that in Client List the Pathway Status is blank
        Map<String, WebElement> my_client_appointment = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        String my_pathway_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_client_appointment);
        Assert.assertEquals(my_pathway_status, "",  "Pathway Status is NOT Blank");

        //---Verify the View Button is Not Displayed
        boolean view_btn_displayed = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_client_appointment);
        Assert.assertFalse(view_btn_displayed, "View Button is Displayed erroneously");

        //---Verify that CheckIn Button is Displayed
        boolean check_in_btn_displayed = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_client_appointment);
        Assert.assertTrue(check_in_btn_displayed, "Check-In Button is not Displayed");

        /////////////////////////////////////////////////////
        //*** First Time to do Check In Client - Citizen Profile
        /////////////////////////////////////////////////////

        MainPageCP.search(driver, client_data.get("personalHealthNumber"));

        //---In Citizen Profile click CheckIn button
        PersonAccountPage.clickCheckInButton(driver);

        //--- Verify that you are redirected to Identification Page
        String current_tab = InClinicExperiencePage.getCurrentTab(driver);
        Assert.assertEquals("Identification", current_tab, "Current Tab is not IDENTIFICATION");

        //--- Go back to Citizen profile->Related Tab
        MainPageCP.search(driver, client_data.get("personalHealthNumber"));
        PersonAccountPage.goToRelatedTab(driver);

        //--- Verify that in Citizen profile->Related Tab New Imms Record was created in Statud New
        List<Map<String, WebElement>> imms_records = PersonAccountRelatedPage.getImmunizationRecords(driver);
        if(imms_records.size() < 2) {
            Thread.sleep(2000);
            imms_records = PersonAccountRelatedPage.getImmunizationRecords(driver);
        }
        String imms_record_status = imms_records.get(1).get("Pathway Status").getText();
        Assert.assertEquals("New", imms_record_status, "Pathway Status is incorrect");

        //--- Go to Client List->Today's Appointments
        MainPageCP.clickClientListTab(driver);
        ClientListPage.clickTodayAppointmentsTab(driver);

        //--- Verify that in Client List Pathway Statud is New
        ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        Map<String, WebElement> my_row = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        String pathway_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_row);
        Assert.assertEquals("New", pathway_status, "Pathway Status Is Not NEW");

        //--- Verify that View Button is Displayed
        boolean view_button_displayed = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_row);
        Assert.assertTrue(view_button_displayed, "View Button Is Not Displayed");

        //--- Verify that CheckIn button is Not Displayed
        boolean check_in_button_displayed = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_row);
        Assert.assertFalse(check_in_button_displayed, "CheckIn Button Is Displayed");


//-----Give some time between tests

        Thread.sleep(5000);
        /////////////////////////////////////////////////////
        //*** First Time to do Check In Client - Client List
        /////////////////////////////////////////////////////

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));

        //---Preconditions: Newly Registered Citizen with Booked Appointment
        log("/*6.----Navigate to More -> Register --*/");
        AlertDialog.closeAllAlerts(driver);
        Thread.sleep(1000);
        MainPageCP.navigateToRegisterClientPage(driver);

        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

        //Book the Appointment for Covid
        PersonAccountPage.goToVaccineScheduleTab(driver);
        log("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        try {
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        } catch (Exception ex) {
            PersonAccountSchedulePage.overrideEligibility(driver);
            Thread.sleep(500);
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
        }
        log("/*25----select 'Search by Clinic name' tab --*/");
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

        //--- Go To Client List->Today's Appointment then click CheckIn Button
        MainPageCP.clickClientListTab(driver);
        driver.navigate().refresh();
        Thread.sleep(2000);
        ClientListPage.clickTodayAppointmentsTab(driver);
        ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        my_row = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        ClientListTodayAppointmentsTab.clickCheckInButton(driver, my_row);

        //--- Verify you are redirected to Identification Page
        current_tab = InClinicExperiencePage.getCurrentTab(driver);
        Assert.assertEquals("Identification", current_tab, "Current Tab is not IDENTIFICATION");

        //--- Go to Citizen Profile->Related Tab
        MainPageCP.search(driver, client_data.get("personalHealthNumber"));
        PersonAccountPage.goToRelatedTab(driver);

        //--- Verify that in Citizen Profile->Related Tab New Imms Record was created in Status New
        imms_records = PersonAccountRelatedPage.getImmunizationRecords(driver);
        imms_record_status = imms_records.get(1).get("Pathway Status").getText();
        Assert.assertEquals("New", imms_record_status, "Pathway Status is incorrect");

        //--- Go to Client List->Today's Appointment
        MainPageCP.clickClientListTab(driver);
        ClientListPage.clickTodayAppointmentsTab(driver);

        //--- Verify that in Client List Pathway Status in New
        ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        my_row = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));

        //--- Verify that View Button is displayed
        view_button_displayed = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_row);
        Assert.assertTrue(view_button_displayed, "View Button Is Not Displayed");

        //--- Verify that CheckIn Client button is Not Displayed
        check_in_button_displayed = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_row);
        Assert.assertFalse(check_in_button_displayed, "CheckIn Button Is Displayed");

        /////////////////////////////////////////////////////
        //*** With existing Imms Record in Status New - Citizen Profile
        /////////////////////////////////////////////////////

        MainPageCP.search(driver, client_data.get("personalHealthNumber"));

        //--- Navigate to Citizen Profile->Related Tab
        PersonAccountPage.goToRelatedTab(driver);

        //---In Citizen Profile click Check-In button
        PersonAccountPage.clickCheckInButton(driver);

        //--- Verify that you are redirected to Identification Page
        current_tab = InClinicExperiencePage.getCurrentTab(driver);
        Assert.assertEquals("Identification", current_tab, "Current Tab is not IDENTIFICATION");

        //--- Click Confirm & Save Identification button
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

        //--- Go back to Citizen Profile->Related Tab
        MainPageCP.search(driver, client_data.get("personalHealthNumber"));
        PersonAccountPage.goToRelatedTab(driver);

        //--- Verify that there is no New Imms Record created and the existing  Imms Record status is now Vaccine_Administration
        imms_records = PersonAccountRelatedPage.getImmunizationRecords(driver);
        imms_record_status = imms_records.get(1).get("Pathway Status").getText();
        Assert.assertEquals("Vaccine_Administration", imms_record_status, "Pathway Status is incorrect");

        //--- Go to Client List->Today's Appointment
        MainPageCP.clickClientListTab(driver);
        ClientListPage.clickTodayAppointmentsTab(driver);

        //---Verify that in Client List Pathway Status is in Vaccine_Administration
        my_row = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));

        pathway_status = ClientListTodayAppointmentsTab.getPathwayStatus(my_row);
        Assert.assertEquals(pathway_status, "Vaccine_Administration", "Pathway Status Is Not Vaccine Administration");

        //--- verify that View Button is Displayed
        view_button_displayed = ClientListTodayAppointmentsTab.viewButtonIsDisplayed(my_row);
        Assert.assertTrue(view_button_displayed, "View Button Is Not Displayed");

        //--- Verify that Check-In Vlient button is Not Displayed
        check_in_button_displayed = ClientListTodayAppointmentsTab.checkInButtonIsDisplayed(my_row);
        Assert.assertFalse(check_in_button_displayed, "CheckIn Button Is Displayed");
    }
}
