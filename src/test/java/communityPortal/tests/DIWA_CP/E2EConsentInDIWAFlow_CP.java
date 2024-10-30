package communityPortal.tests.DIWA_CP;

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
import java.util.List;

@Listeners({TestListener.class})
public class E2EConsentInDIWAFlow_CP extends BaseTest {
    String env;
    String consumptionRoute;
    Map<String, Object> testData;
    String participant_name;
    String consentProvider;
    String agent = "COVID-19 mRNA";
    private String lot_to_select;
    private String dosage_to_select;
    private String site_to_select = "Arm - Right deltoid";
    private String route_to_select = "Intramuscular";
    String clinic_location;
    Map<String, String> client_data;
    Map<String, String> client_data_new;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "consent");
        client_data_new = Utils.getTestClientData(client_data_file, "new_consent");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data_new.get("personalHealthNumber"));
    }
    @Test(testName = "Document Consent in DIWA flow CP. Existing Consent")
    public void Can_Create_DIWA_Immunisation_record_with_Active_consent_CP() throws Exception {
        TestcaseID = "273416";
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        consentProvider = String.valueOf(testData.get("consentProvider"));
        clinic_location = String.valueOf(testData.get("diwaLocation"));
        lot_to_select = String.valueOf(testData.get("pneumoLot"));
        dosage_to_select = String.valueOf(testData.get("pneumoDose"));
        log("Target Environment: " + env);
        log("Test Case Id: " +"C"+TestcaseID);

        //---Verify the EXISTING CONSENT flows---

        log("---1. Login as an DIWA to CIB ---");
        loginPage.loginIntoCommunityPortalAsClinician();
        MainPageCP cpMainPage = new MainPageCP(driver);
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
        log("Citizen to search: " +participant_name);
        MainPageCP.search(driver, participant_name);

        log("---2. Navigated to Person Account related tab ---");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch (NotFoundException ex) {
            driver.navigate().refresh();
            Thread.sleep(1000);
        } catch (ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToRelatedTab(driver);
        }
        PersonAccountPage.goToRelatedTab(driver);
        PersonAccountRelatedPage.scrollToActiveConsentsSection(driver);
        Thread.sleep(2000);
        String active_consent_resp = PersonAccountRelatedPage.getActiveConsentsResponse(driver, agent);
        int count = 0;
        while (active_consent_resp == null) {
            Thread.sleep(1000);
            active_consent_resp = PersonAccountRelatedPage.getActiveConsentsResponse(driver, agent);
            count++;
            if (count >= 30) {
                break;
            }
        }
        Assert.assertEquals(active_consent_resp, "Grant");
        log("---3. Click Create Immunization Record ---");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);
        log("---4. Click confirm Button on the popup window---");
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch (Exception ex) {
            System.out.println("No Confirm dialog");
        }
        log("---5. Select an Option ---*/)");
        DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
        log("---6. Select option: " +agent);
        DiwaImmunizationRecord.selectAgent(driver, agent);
        log("---7. Enter a Clinic Location: " +clinic_location);
        DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
        log("---8. Select a Date and Time of Administration ---");
        DiwaImmunizationRecord.clickTimeBox(driver);
        log("---9. Click Record Immunization ---");
        DiwaImmunizationRecord.clickRecordImmunization(driver);
        try {
            List<Map<String, WebElement>> consent_table = DiwaImmunizationRecord.getInformedConsentTable(driver);
            Assert.assertTrue(consent_table.size() > 1, "Active Consent record is not displayed");
        } catch(NotFoundException ex) {
            DiwaImmunizationRecord.clickPotentialDuplicateYes(driver);
            Thread.sleep(500);
            List<Map<String, WebElement>> consent_table = DiwaImmunizationRecord.getInformedConsentTable(driver);
            Assert.assertTrue(consent_table.size() > 1, "Active Consent record is not displayed");
        }
        boolean record_consent_btn_exists = DiwaImmunizationRecord.recordConsentBtnExists(driver);
        Assert.assertTrue(record_consent_btn_exists, "Record Consent button is not displayed");

        boolean use_prev_consent_chkbox_exists = DiwaImmunizationRecord.usePrviousConsentChkboxExists(driver);
        Assert.assertFalse(use_prev_consent_chkbox_exists, "Consent Previously Obtained checkbox is Incorrectly Displayed");

        boolean record_consent_msg_exists = DiwaImmunizationRecord.recordExistingConsentMessageExists(driver);
        Assert.assertTrue(record_consent_msg_exists, "The Record Consent message is not Displayed");

        DiwaImmunizationRecord.clickCancelAndCloseImmunization(driver);
        Thread.sleep(500);
        DiwaImmunizationRecord.clickOopsCancelAndClose(driver);

        //---Verify the NEW CONSENT flows---

    //    participant_name = client_data_new.get("legalFirstName" + " " + client_data_new.get("legalMiddleName") + " " + client_data_new.get("legalLastName"));
        MainPageCP.navigateToRegisterClientPage(driver);

        log("---10. Click Register button New Citizen ---");
        InClinicExperiencePage.clickRegisterButton(driver);

        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data_new);

        log("---11. Navigated to Person Account related tab ---");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToRelatedTab(driver);
        }

        log("---12. Click Create Immunization Record---");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);

        log("---13. Click confirm Button on the popup window---");
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch(Exception ex) {
            System.out.println("No Confirm dialog");
        }
        log("---14. Select an Option ---");
        DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
        log("---15. Select Pneumo-P-23 as an Option  ---");
        DiwaImmunizationRecord.selectAgent(driver, "Pneumo-P-23");
        log("---16. Enter a Clinic Location: " +clinic_location);
        DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
        log("---17. Select a Date and Time of Administration ---*/");
        DiwaImmunizationRecord.clickTimeBox(driver);
        log("---18. Click Record Immunization ---");
        DiwaImmunizationRecord.clickRecordImmunization(driver);
        boolean recordConsentBtnExists = DiwaImmunizationRecord.recordConsentBtnExists(driver);
        Assert.assertTrue(recordConsentBtnExists, "Record Consent Button doesn't exist");

        boolean recordConsentBtnActive = DiwaImmunizationRecord.recordConsentBtnIsActive(driver);
        Assert.assertTrue(recordConsentBtnActive, "Record Consent Button is not Enabled");

        boolean use_previous_consent = DiwaImmunizationRecord.usePrviousConsentChkboxExists(driver);
        Assert.assertTrue(use_previous_consent, "Use Previous Consent checkbox is not Displayed");

        boolean record_new_consent_msg_exists = DiwaImmunizationRecord.recordNewConsentMessageExists(driver);
        Assert.assertTrue(record_new_consent_msg_exists, "New Consent Message is not Displayed");

        DiwaImmunizationRecord.clickRecordConsent(driver);
        boolean new_consent_screen_displayed = AddConsentDialog.dialogExists(driver);
        Assert.assertTrue(new_consent_screen_displayed, "New Consent Dialog not Displayed");

        AddConsentDialog.clickCloseButton(driver);

        //---Check the Consent Previouly Obtain checkbox---
        DiwaImmunizationRecord.checkExistingConsent(driver);
        boolean record_consent_button_is_active = DiwaImmunizationRecord.recordConsentBtnIsActive(driver);
        Assert.assertFalse(record_consent_button_is_active, "Record Consent button is Active Incorrectly");

        //---UnCheck the Consent Previouly Obtain checkbo---
        DiwaImmunizationRecord.uncheckExistingConsent(driver);
        record_consent_button_is_active = DiwaImmunizationRecord.recordConsentBtnIsActive(driver);
        Assert.assertTrue(record_consent_button_is_active, "Record Consent button is Inactive Incorrectly");

        boolean immunization_edit_enabled = DiwaImmunizationRecord.getEditImmunizationInfoButtonDisabled(driver);
        Assert.assertFalse(immunization_edit_enabled, "Edit Immunization is Disabled");

        DiwaImmunizationRecord.checkExistingConsent(driver);
        DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);
        boolean confirm_button_active = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);
        Assert.assertFalse(confirm_button_active, "Confirm and Save button Incorrectly Enabled");

        DiwaImmunizationRecord.setProvider(driver, consentProvider);
        DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);
        Thread.sleep(1000);
        DiwaImmunizationRecord.setLotNumber(driver, lot_to_select);
        DiwaImmunizationRecord.setDosage(driver, dosage_to_select);
        DiwaImmunizationRecord.setSite(driver, site_to_select);
        DiwaImmunizationRecord.setRoute(driver, route_to_select);
        Thread.sleep(1000);

        DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);
        try {
            confirm_button_active = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);
            Assert.assertTrue(confirm_button_active, "Confirm and Save button Incorrectly Disabled");
        } catch(AssertionError ex) {
            //---The save button sometimes is not clicked properly. Try again---
            DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);
            Thread.sleep(1000);
            DiwaImmunizationRecord.clickOkForExpiredLot(driver);
            Thread.sleep(500);
            confirm_button_active = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);
            Assert.assertTrue(confirm_button_active, "Confirm and Save button Incorrectly Disabled");
        }
    }

}
