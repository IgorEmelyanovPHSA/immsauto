package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class New_Consent_In_Citizen_Profile_Errors  extends BaseTest {
    String env;
    String consumptionRoute;
    Map<String, Object> testData;
    String participant_name;
    String consentProvider;
    private String consent_effective_date = "November 29, 2023";
    private String lot_to_select = "0486AA-CC01";
    private String dosage_to_select = "0.5";
    String clinic_location = "All Ages - Atlin Health Centre";
    MainPageOrg orgMainPage;
    String consent_agent = "COVID-19 mRNA";
    Map<String, String> client_data;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "no_consent");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
    }
    @Test(testName = "Verify Errors when creating new Consent from Citizen Profile")
    public void Verify_Errors_When_Create_New_Consent_From_Citizen_Profile() throws Exception {
        //Nov5, the bug is open  https://www.jira.healthcarebc.ca/browse/BCVAX-42699

        TestcaseID = "278835";
        env = Utils.getTargetEnvironment();
        log("Target Environment: "+ env);
        log("Test Case Id: " + "C" + TestcaseID);
        testData = Utils.getTestData(env);
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
        log("/*----1. Login as an DIWA to CIB  --*/");
        loginPage.loginAsImmsBCAdmin();
        log("/*-- 2. Clinic In Box page displayed --*/");
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
        }
        try {
            MainPageOrg.selectFromNavigationMenu(driver, "Home");
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            MainPageOrg.selectFromNavigationMenu(driver, "Home");
        }
        ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
        log("/*----3. Close all previously opened Tabs --*/");
        clinicInBoxPage.closeAllTabs();

        clinicInBoxPage.clickRegisterButton();
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
        log("/*----6. Navigated to Person Account related tab ---*/");
        PersonAccountPage.goToRelatedTab(driver);
        log("/*----7. Click Create Immunization Record ---*/");
        PersonAccountRelatedPage.clickNewActiveConsentButton(driver);
        boolean add_consent_dialog_exists = AddConsentDialog.dialogExists(driver);
        //Assert.assertTrue(add_consent_dialog_exists, "Add Consent Dialog doesn't exist");
        //Verify Headers in Infromed Consent Table
        List<String> expected_headers = new ArrayList<String>();
        expected_headers.add("Consent Number");
        expected_headers.add("Response");
        expected_headers.add("Agent");
        expected_headers.add("Consent Given By");
        expected_headers.add("Consent Given To");
        expected_headers.add("Consent Effective From Date");
        expected_headers.add("Consent Effective To Date");
        List<String> actual_headers = InformedConsentDialog.getInformedConsentTableHeaders(driver);

        for(String header : expected_headers) {
            Assert.assertTrue(actual_headers.contains(header), "Table doesn't contain header " + header);
        }

        //Verify Error when Response is not selected
        InformedConsentDialog.selectImmsBCProviderRadioButton(driver);
        InformedConsentDialog.selectObtainedFromClient(driver);
        InformedConsentDialog.selectFormOfConsentInPerson(driver);
        InformedConsentDialog.selectInformedConsentProvider(driver, consentProvider);
        String date_effective_from = InformedConsentDialog.getConsentEffectiveDateFromSelected(driver);
        if(!date_effective_from.equals("Nov 29, 2023")) {
            InformedConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
        }
        InformedConsentDialog.clickNextButton(driver);
        String response_error = InformedConsentDialog.getResponseMundatoryError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");

        //Verify Error when Refusal Reason is missing
        InformedConsentDialog.selectResponseRefuseRadioButton(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getResponseRefuseReasonMundatoryError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");
        InformedConsentDialog.clickCloseButton(driver);
        Thread.sleep(500);
        PersonAccountRelatedPage.clickNewActiveConsentButton(driver);
        //Verify Error when Infromed Consent Obtained From is missing
        InformedConsentDialog.selectResponseGrantRadioButton(driver);
        InformedConsentDialog.selectImmsBCProviderRadioButton(driver);
        InformedConsentDialog.selectFormOfConsentInPerson(driver);
        InformedConsentDialog.selectInformedConsentProvider(driver, consentProvider);
        date_effective_from = InformedConsentDialog.getConsentEffectiveDateFromSelected(driver);
        if(!date_effective_from.equals("Nov 29, 2023")) {
            InformedConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
        }
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getObtainedFromMundatoryError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");

        //Verify Error when Parent/Gardian First/Last names are missing
        InformedConsentDialog.selectObtainedFromClientGuardian(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getRelationshipToClientError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");
        response_error = InformedConsentDialog.getRelationshipFirstNameError(driver);
        Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");
        response_error = InformedConsentDialog.getRelationshipLastNameError(driver);
        Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");

        //Verify Missing Consent Provider Error
        InformedConsentDialog.selectObtainedFromClient(driver);
        InformedConsentDialog.cleanupInformedConsentProvider(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentProviderMissingError(driver);
        Assert.assertEquals(response_error, "A value is required.");

        //Verify Missing Consent Provider Contact Error
        InformedConsentDialog.selectNonImmsBCProviderRadioButton(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentProviderContactMissingError(driver);
        Assert.assertEquals(response_error, "A value is required.");

        //Verify Missing Effective Date From Error
        InformedConsentDialog.selectInformedConsentAgent(driver, consent_agent);
        InformedConsentDialog.selectImmsBCProviderRadioButton(driver);
        InformedConsentDialog.selectInformedConsentProvider(driver, consentProvider);
        InformedConsentDialog.clearConsentEffectiveDateFrom(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentEffectiveFromMundatoryError(driver);
        Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");

        //Verify Effective Date From in Future Error
        LocalDate today_date = LocalDate.now();
        today_date = today_date.plusDays(1);
        DateTimeFormatter dtf_today = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String my_date = today_date.format(dtf_today);
        InformedConsentDialog.setConsentEffectiveDateFrom(driver, my_date);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentEffectiveDateFromError(driver);
        Assert.assertEquals(response_error, "Effective From Date cannot be in the future and cannot overlap with the Effective to Date of an existing Consent record for the same Agent");

        //Verify Effective Date To Earlier than Effective Date From Error
        LocalDate from_date = LocalDate.now().minusDays(1);
        LocalDate to_date = from_date.minusDays(1);
        String my_from_date = from_date.format(dtf_today);
        String my_to_date = to_date.format(dtf_today);
        InformedConsentDialog.setConsentEffectiveDateFrom(driver, my_from_date);
        InformedConsentDialog.setConsentEffectiveDateTo(driver, my_to_date);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentEffectiveDateToError(driver);
        Assert.assertEquals(response_error, "Effective To Date must be greater than or equal to Effective From Date or blank");
    }

    @Test(testName = "Verify Errors when creating New Consent Record from Vaccine Administration")
    public void Verify_Errors_When_Create_Consent_record_from_Vaccine_administration() throws Exception {
        TestcaseID = "278971";
        env = Utils.getTargetEnvironment();
        log("/---API call to remove duplicate citizen participant account if found--*/");
        testData = Utils.getTestData(env);
        log("Target Environment: "+ env);
        log("/*----1. Login to CIB  --*/");
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
        loginPage.loginAsImmsBCAdmin();
        log("/*-- 2. Clinic In Box page displayed --*/");
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
        }
        MainPageOrg.selectFromNavigationMenu(driver, "Home");
        ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
        log("/*----3. Close all previously opened Tabs --*/");
        clinicInBoxPage.closeAllTabs();

        clinicInBoxPage.clickRegisterButton();
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

        log("/*----5. select Citizen from search results --*/");
        //profilesPage.openProfile(participant_name);
        log("/*----6. Navigated to Person Account related tab ---*/");
        PersonAccountPage.goToRelatedTab(driver);
        log("/*----7. Click Create Immunization Record ---*/");
        PersonAccountPage.clickCheckInButton(driver);
        log("/*----8. Click confirm Button on the popup window---*/");

        InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
        InClinicExperiencePage.clickTodayAppointments(driver);
        log("/*47.---Open Today appointment Details --*/");
        Thread.sleep(2000);
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
        log("/*----9. Select an Option ---*/)");
        InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consent_agent);

        InClinicExperienceVaccineAdministrationPage.clickRecordConsent(driver);
        boolean add_consent_dialog_exists = InformedConsentDialog.dialogExists(driver);
        Assert.assertTrue(add_consent_dialog_exists, "Add Consent Dialog doesn't exist");
        //Verify Headers in Infromed Consent Table
        List<String> expected_headers = new ArrayList<String>();
        expected_headers.add("Consent Number");
        expected_headers.add("Response");
        expected_headers.add("Agent");
        expected_headers.add("Consent Given By");
        expected_headers.add("Consent Given To");
        expected_headers.add("Consent Effective From Date");
        expected_headers.add("Consent Effective To Date");
        List<String> actual_headers = InformedConsentDialog.getInformedConsentTableHeaders(driver);

        for(String header : expected_headers) {
            Assert.assertTrue(actual_headers.contains(header), "Table doesn't contain header " + header);
        }

        //Verify Error when Response is not selected
        InformedConsentDialog.selectImmsBCProviderRadioButton(driver);
        InformedConsentDialog.selectObtainedFromClient(driver);
        InformedConsentDialog.selectFormOfConsentInPerson(driver);
        InformedConsentDialog.selectInformedConsentProvider(driver, consentProvider);
        String date_effective_from = InformedConsentDialog.getConsentEffectiveDateFromSelected(driver);
        if(!date_effective_from.equals("Nov 29, 2023")) {
            InformedConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
        }
        InformedConsentDialog.clickNextButton(driver);
        String response_error = InformedConsentDialog.getResponseMundatoryError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");

        //Verify Error when Refusal Reason is missing
        InformedConsentDialog.selectResponseRefuseRadioButton(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getResponseRefuseReasonMundatoryError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");
        InformedConsentDialog.clickCloseButton(driver);

        InClinicExperienceVaccineAdministrationPage.clickRecordConsent(driver);
        add_consent_dialog_exists = InformedConsentDialog.dialogExists(driver);
        Assert.assertTrue(add_consent_dialog_exists, "Add Consent Dialog doesn't exist");

        //Verify Error when Infromed Consent Obtained From is missing
        InformedConsentDialog.selectResponseGrantRadioButton(driver);
        InformedConsentDialog.selectImmsBCProviderRadioButton(driver);
        InformedConsentDialog.selectFormOfConsentInPerson(driver);
        InformedConsentDialog.selectInformedConsentProvider(driver, consentProvider);
        date_effective_from = InformedConsentDialog.getConsentEffectiveDateFromSelected(driver);
        if(!date_effective_from.equals("Nov 29, 2023")) {
            InformedConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
        }
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getObtainedFromMundatoryError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");

        //Verify Error when Parent/Gardian First/Last names are missing
        InformedConsentDialog.selectObtainedFromClientGuardian(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getRelationshipToClientError(driver);
        Assert.assertEquals(response_error, "Please select a choice.");
        response_error = InformedConsentDialog.getRelationshipFirstNameError(driver);
        Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");
        response_error = InformedConsentDialog.getRelationshipLastNameError(driver);
        Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");

        //Verify Missing Consent Provider Error
        InformedConsentDialog.selectObtainedFromClient(driver);
        InformedConsentDialog.cleanupInformedConsentProvider(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentProviderMissingError(driver);
        Assert.assertEquals(response_error, "A value is required.");

        //Verify Missing Consent Provider Contact Error
        InformedConsentDialog.selectNonImmsBCProviderRadioButton(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = AddConsentDialog.getConsentProviderContactMissingError(driver);
        Assert.assertEquals(response_error, "A value is required.");

        //Verify Missing Effective Date From Error
        InformedConsentDialog.selectImmsBCProviderRadioButton(driver);
        InformedConsentDialog.selectInformedConsentProvider(driver, consentProvider);
        InformedConsentDialog.clearConsentEffectiveDateFrom(driver);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentEffectiveFromMundatoryError(driver);
        Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");

        //Verify Effective Date From in Future Error
        LocalDate today_date = LocalDate.now();
        today_date = today_date.plusDays(1);
        DateTimeFormatter dtf_today = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String my_date = today_date.format(dtf_today);
        InformedConsentDialog.setConsentEffectiveDateFrom(driver, my_date);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentEffectiveDateFromError(driver);
        Assert.assertEquals(response_error, "Effective From Date cannot be in the future and cannot overlap with the Effective to Date of an existing Consent record for the same Agent");

        //Verify Effective Date To Earlier than Effective Date From Error
        LocalDate from_date = LocalDate.now().minusDays(1);
        LocalDate to_date = from_date.minusDays(1);
        String my_from_date = from_date.format(dtf_today);
        String my_to_date = to_date.format(dtf_today);
        InformedConsentDialog.setConsentEffectiveDateFrom(driver, my_from_date);
        InformedConsentDialog.setConsentEffectiveDateTo(driver, my_to_date);
        InformedConsentDialog.clickNextButton(driver);
        response_error = InformedConsentDialog.getConsentEffectiveDateToError(driver);
        Assert.assertEquals(response_error, "Effective To Date must be greater than or equal to Effective From Date or blank");
    }
}
