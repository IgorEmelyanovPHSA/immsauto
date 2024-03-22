package communityPortal.tests.DIWA_CP;

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
public class ExistingConsentInDIWAFlow_CP extends BaseTest {
    String env;
    String consumptionRoute;
    Map<String, Object> testData;
    private String legal_first_name = "Rawley";
    private String legal_last_name = "BCVaxIsmirnioglou";
    private String legal_middle_name = "Marijo";
    private String personal_health_number = "9746173039";
    private String date_of_birth = "1959-01-23";
    private String postal_code = "V2X9T1";

    private String legal_first_name_new = "Gill";
    private String legal_last_name_new = "BCVaxOrigan";
    private String legal_middle_name_new = "Ashely";
    private String personal_health_number_new = "9746172463";
    private String date_of_birth_new = "1915-02-14";
    private String postal_code_new = "V2T8T1";
    private String email = "accountToDelete@phsa.ca";
    String participant_name;
    String consentProvider;
    String agent = "COVID-19 mRNA";
    private String lot_to_select = "0486AA-CC01";
    private String dosage_to_select = "0.5";
    String clinic_location = "All Ages - Atlin Health Centre";

    @Test(testName = "Document Consent in DIWA flow CP. Existing Consent")
    public void Can_Create_DIWA_Immunisation_record_with_Active_consent() throws Exception {
        TestcaseID = "273416";
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        consentProvider = String.valueOf(testData.get("consentProvider"));
        log("Target Environment: " + env);
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(personal_health_number);
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personal_health_number_new);
        log("/*----1. Login as an DIWA to CIB  --*/");
        participant_name = legal_first_name + " " + legal_middle_name + " " + legal_last_name;
        loginPage.loginIntoCommunityPortalAsClinician();
        MainPageCP cpMainPage = new MainPageCP(driver);
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        ProfilesPage profilesPage = cpMainPage.globalSearch_CP(participant_name);

        log("/*----3. select Citizen Participant acc from search results --*/");
        profilesPage.selectCitizenParticipantAcc(participant_name);

        log("/*----6. Navigated to Person Account related tab ---*/");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch (NotFoundException ex) {
            driver.navigate().refresh();
            Thread.sleep(1000);
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
        log("/*----7. Click Create Immunization Record ---*/");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);
        log("/*----8. Click confirm Button on the popup window---*/");
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch (Exception ex) {
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
        DiwaImmunizationRecord.clickCancelAndCloseImmunization(driver);
        Thread.sleep(500);
        DiwaImmunizationRecord.clickOopsCancelAndClose(driver);

        participant_name = legal_first_name_new + " " + legal_middle_name_new + " " + legal_last_name_new;
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();

        log("/*8.----Enter First Name " +legal_first_name_new +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legal_first_name_new);

        log("/*9.----Enter Last Name " +legal_last_name_new +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legal_last_name_new);

        log("/*10.----Enter Date of birth " +date_of_birth_new +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, date_of_birth_new);

        log("/*11.----Enter Postal code " +postal_code_new +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postal_code_new);

        log("/*12.----Enter PHN " +personal_health_number_new +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personal_health_number_new);

        log("/*14.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);

        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);

        log("/*16.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);

        log("/*17.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);

        log("/*18.----'Confirm email address " +email +"--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);

        log("/*19.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);

        log("/*20.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);

        log("/*21.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

        log("/*----6. Navigated to Person Account related tab ---*/");
        PersonAccountPage.goToRelatedTab(driver);

        log("/*----7. Click Create Immunization Record ---*/");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);

        log("/*----8. Click confirm Button on the popup window---*/");
        try {
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch(Exception ex) {
            System.out.println("No Confirm dialog");
        }
        log("/*----9. Select an Option ---*/)");
        DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
        log("/*----10. Select Pneumo-P-23 as an Option  ---*/");
        DiwaImmunizationRecord.selectOption(driver, "Pneumo-P-23");
        log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
        DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
        log("/*---12. Select a Date and Time of Administration ---*/");
        DiwaImmunizationRecord.clickTimeBox(driver);
        log("/*---13. Click Record Immunization ---*/");
        DiwaImmunizationRecord.clickRecordImmunization(driver);
        boolean recordConsentBtnExists = DiwaImmunizationRecord.recordConsentBtnExists(driver);
        DiwaImmunizationRecord.recordConsentBtnIsActive(driver);
        Assert.assertTrue(recordConsentBtnExists, "Record Consent Button doesn't exist");
    }

}
