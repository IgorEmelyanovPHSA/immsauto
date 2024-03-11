package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Listeners({TestListener.class})
public class Existing_Consent_In_DIWA_Flow extends BaseTest {
    String env;
    String consumptionRoute;
    Map<String, Object> testData;
    private String legalFirstName = "Rawley";
    private String legalLastName = "BCVaxIsmirnioglou";
    private String legalMiddleName = "Marijo";
    private String personal_health_number = "9746173039";
    private String date_of_birth = "1959-01-23";
    private String postal_code = "V2X9T1";
    String participant_name;
    String consentProvider;
    String agent = "COVID-19 mRNA";
    private String lot_to_select = "0486AA-CC01";
    private String dosage_to_select = "0.5";
    String clinic_location = "All Ages - Atlin Health Centre";
    MainPageOrg orgMainPage;

    @Test(testName = "Create DIWA Immunisation record with Active Consent for the selected agent")
    public void Can_Create_DIWA_Immunisation_record_with_Active_consent() throws Exception {
        TestcaseID = "275966";
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        consentProvider = String.valueOf(testData.get("consentProvider"));
        log("Target Environment: "+ env);
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(personal_health_number);
        log("/*----1. Login as an DIWA to CIB  --*/");
        participant_name = legalFirstName + " " + legalMiddleName + " " + legalLastName;
        loginPage.loginAsImmsBCAdmin();
        ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
        }
        orgMainPage.selectFromNavigationMenu("Home");
        log("/*----3. Close all previously opened Tabs --*/");
        clinicInBoxPage.closeAllTabs();
        log("/*----4. Search for Participant account: " +participant_name +" ---*/");
        orgMainPage.globalSearch(participant_name);
        log("/*----5. select Citizen from search results --*/");
        ProfilesPage profilesPage = new ProfilesPage(driver);

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
        profilesPage.clickCreateImmunizationRecord();
        log("/*----8. Click confirm Button on the popup window---*/");
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch(Exception ex) {
            System.out.println("No Confirm dialog");
        }
        log("/*----9. Select an Option ---*/)");
        DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
        log("/*----10. Select Pneumo-P-23 as an Option  ---*/");
        DiwaImmunizationRecord.selectOption(driver, agent);
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
        boolean record_consent_message_exists = DiwaImmunizationRecord.recordConsentMessageExists(driver);
        Assert.assertTrue(record_consent_message_exists, "Record Consent Message not found");
        boolean confirm_and_save_btn_enabled = DiwaImmunizationRecord.confirm_and_save_button_is_active(driver);
        Assert.assertFalse(confirm_and_save_btn_enabled, "Confirm and Save button is erroneously Active");

        DiwaImmunizationRecord.select_provider(driver, consentProvider);
        profilesPage.clickShowAllLotNumbersCheckBox();
        profilesPage.clickLotNumberDropDown();
        profilesPage.selectLot();
        profilesPage.selectDosage();
        DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);
        Thread.sleep(1000);

        try {
            DiwaImmunizationRecord.clickOkForExpiredLot(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("No Expired Lot Warning");
        }
        confirm_and_save_btn_enabled = DiwaImmunizationRecord.confirm_and_save_button_is_active(driver);
        Assert.assertTrue(confirm_and_save_btn_enabled, "Confirm and Save button is erroneously disabled");
        DiwaImmunizationRecord.clickConfirmAndSaveAdministration(driver);
        DiwaImmunizationRecord.clickSaveAdministrationSummary(driver);
        Thread.sleep(2000);
        PersonAccountPage.goToRelatedTab(driver);
        int immunization_records_count_new = PersonAccountRelatedPage.getImmunizationRecords(driver).size();
        Assert.assertTrue(immunization_records_count_new == immunization_records_count + 1, "Expected: " + (immunization_records_count + 1) + ";  Actual: " + immunization_records_count_new);
        System.out.println();
    }
}
