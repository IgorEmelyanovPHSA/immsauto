package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.List;

@Listeners({TestListener.class})
public class Existing_Consent_In_DIWA_Flow extends BaseTest {
    String env;
    String consumptionRoute;
    Map<String, Object> testData;
    String participant_name;
    String consentProvider;
    String agent;
    String lot_to_select;
    String dosage_to_select;
    String site_to_select;
    String clinic_location;
    MainPageOrg orgMainPage;
    Map<String, String> client_data;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "consent");
    }
    @Test(testName = "Create DIWA Immunisation record with Active Consent for the selected agent")
    public void Can_Create_DIWA_Immunisation_record_with_Active_consent() throws Exception {
        TestcaseID = "275966";
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinic_location = String.valueOf(testData.get("diwaLocation"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        dosage_to_select = String.valueOf(testData.get("covidDose"));
        site_to_select = String.valueOf(testData.get("siteConsumption"));
        agent = String.valueOf(testData.get("vaccineAgent"));
        lot_to_select = String.valueOf(testData.get("covidLot"));
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
        log("Target Environment: "+ env);
        log("Test Case Id: " +"C"+TestcaseID);
        log("/*----1. Login as an DIWA to CIB  --*/");
        participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
        loginPage.loginAsImmsBCAdmin();
        ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
        }
        MainPageOrg.selectFromNavigationMenu(driver, "Home");
        log("/*----3. Close all previously opened Tabs --*/");
        clinicInBoxPage.closeAllTabs();

        log("/*----4. Search for Participant account: " +participant_name +" ---*/");
        MainPageOrg.search(driver, participant_name);
        log("/*----5. select Citizen from search results --*/");

        log("/*----6. Navigated to Person Account related tab ---*/");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch (NotFoundException ex) {
            driver.navigate().refresh();
            Thread.sleep(1000);
        }
        PersonAccountPage.goToRelatedTab(driver);
        int immunization_records_count = PersonAccountRelatedPage.getImmunizationRecords(driver).size();

        String active_consent_resp = PersonAccountRelatedPage.getActiveConsentsResponse(driver, agent);
        Assert.assertEquals(active_consent_resp, "Grant");
        log("/*----7. Click Create Immunization Record ---*/");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);
        log("/*----8. Click confirm Button on the popup window---*/");
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch(Exception ex) {
            System.out.println("No Confirm dialog");
        }
        //log("/*----9. Select an Option ---*/)");
        //DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
        log("/*----10. Select Pneumo-P-23 as an Option  ---*/");
        DiwaImmunizationRecord.selectAgent(driver, agent);
        log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
        DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
        log("/*---12. Select a Date and Time of Administration ---*/");
        DiwaImmunizationRecord.clickTimeBox(driver);
        log("/*---13. Click Record Immunization ---*/");
        DiwaImmunizationRecord.clickRecordImmunization(driver);
        List<Map<String, WebElement>> consent_table = DiwaImmunizationRecord.getInformedConsentTable(driver);
        Assert.assertTrue(consent_table.size() > 1, "Active Consent record is not displayed");
        boolean record_consent_btn_exists = DiwaImmunizationRecord.recordConsentBtnExists(driver);
        Assert.assertTrue(record_consent_btn_exists, "Record Consent button is not displayed");
        boolean record_consent_message_exists = DiwaImmunizationRecord.recordExistingConsentMessageExists(driver);
        Assert.assertTrue(record_consent_message_exists, "Record Consent Message not found");
        boolean confirm_and_save_btn_enabled = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);
        Assert.assertFalse(confirm_and_save_btn_enabled, "Confirm and Save button is erroneously Active");

        DiwaImmunizationRecord.setProvider(driver, consentProvider);
        DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);
        DiwaImmunizationRecord.setLotNumber(driver, lot_to_select);
        DiwaImmunizationRecord.setDosage(driver, dosage_to_select);
        DiwaImmunizationRecord.setSite(driver, site_to_select);
        DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);
        Thread.sleep(1000);

        try {
            DiwaImmunizationRecord.clickOkForExpiredLot(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("No Expired Lot Warning");
        }
        confirm_and_save_btn_enabled = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);
        Assert.assertTrue(confirm_and_save_btn_enabled, "Confirm and Save button is erroneously disabled");
        DiwaImmunizationRecord.clickConfirmAndSaveAdministration(driver);
        DiwaImmunizationRecord.clickSaveAdministrationSummary(driver);
        Thread.sleep(2000);
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToRelatedTab(driver);
        } catch(NotFoundException ex) {
            Thread.sleep(2000);
            PersonAccountPage.goToRelatedTab(driver);
        }
        int immunization_records_count_new = PersonAccountRelatedPage.getImmunizationRecords(driver).size();
        Assert.assertTrue(immunization_records_count_new == immunization_records_count + 1, "Expected: " + (immunization_records_count + 1) + ";  Actual: " + immunization_records_count_new);
        System.out.println();
    }
}
