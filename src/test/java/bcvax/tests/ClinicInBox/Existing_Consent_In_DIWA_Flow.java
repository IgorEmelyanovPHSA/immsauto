package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
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
    private String lot_to_select = "0486AA-CC01";
    private String dosage_to_select = "0.5";
    String clinic_location = "All Ages - Atlin Health Centre";
    MainPageOrg orgMainPage;

    @Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
        TestcaseID = "275966";
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        log("Target Environment: "+ env);
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
        //profilesPage.openProfile(participant_name);
        log("/*----6. Navigated to Person Account related tab ---*/");
        profilesPage.clickRelatedTab();
        log("/*----7. Click Create Immunization Record ---*/");
        profilesPage.clickCreateImmunizationRecord();
        log("/*----8. Click confirm Button on the popup window---*/");
        try {
            profilesPage.clickConfirmButton();
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

    }
}
