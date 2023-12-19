package communityPortal.tests.DIWA_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class DIWA_Covid19_CP extends BaseTest {
    String env;
    private String legalFirstName = "John";
    private String legalLastName = "BCVaxChan";
    private String legalMiddleName = "Yuan bo";
    private String personal_health_nunber = "9746170785";
    private String date_of_birth = "1934-02-28";
    private String postal_code = "V2T0N1";
    Map<String, Object> testData;
    private String consentProvider;
    @Test
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_CP() throws Exception {
        //TestcaseID = "223187"; //C223187
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        log("Target Environment: "+ Utils.getTargetEnvironment());
        CommonMethods commonMethods = new CommonMethods(getDriver());
        String nameToSearch = "John Yuan bo BCVaxChan";
        String clinicLocation = "All Ages - Atlin Health Centre";
        consentProvider = String.valueOf(testData.get("consentProvider"));
        MainPageCP cpMainPage = new MainPageCP(getDriver());

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login as ImmsBCAdmin");
                TestcaseID = "245099"; //C245099
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login as Clinician user");
                TestcaseID = "243108"; //C223187
                loginPage.loginIntoCommunityPortalAsClinician();
        }
        log("TestRail test case ID: C" +TestcaseID);

        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        log("/*2.---Search for Participant account --*/");
        ProfilesPage profilesPage = cpMainPage.globalSearch_CP(nameToSearch);

        log("/*----3. select Citizen Participant acc from search results --*/");
        profilesPage.selectCitizenParticipantAcc(nameToSearch);

        //If the PIR Warning is shown close it
        try {
            profilesPage.closePIRWarningDialog();
        } catch(Exception ex) {
            System.out.println("No PIR Warning. Continue...");
        }
        log("/*---- 4. Navigate to Person Account related tab ---*/");
        profilesPage.clickRelatedTab();

        log("/*----5. Click to Create Immunization Record Button ---*/");
        profilesPage.clickCreateImmunizationRecord();

        try {
            log("/*----6. Click confirm Button on the popup window---*/");
            profilesPage.clickConfirmButton();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        log("/*----8. Select COVID19-mRNA as an Option  ---*/");
        DiwaImmunizationRecord.selectOption(driver, "COVID-19 mRNA");

        log("/*----9. Enter a Clinic Location: " +clinicLocation +"---*/");
        DiwaImmunizationRecord.searchClinicLocation(driver, clinicLocation);

        log("/*---10. Select a Date and Time of Administration ---*/");
        DiwaImmunizationRecord.clickTimeBox(driver);

        log("/*---11. Click Record Immunization ---*/");
        DiwaImmunizationRecord.clickRecordImmunization(driver);

        //If the Potential Duplicate Warning is shown Click yes it
        try {
            profilesPage.clickPotentialDuplicateYes();
        } catch(Exception ex) {
            System.out.println("No Potential Duplicate Warning. Continue...");
        }

        log("/*---12. Click X button on Diwa flow ---*/");
        profilesPage.clickToClose();

        log("/*---13. Validate message on clicking close button on modal popup ---*/");
        profilesPage.validateoopsMessage();

        log("/*---14. click on continue editing button to continue with the flow ---*/");
        profilesPage.ContinueEditingButton();

//        log("/*---15. select date of Administration ---*/");
//        if (profilesPage.selectConsentEffectiveDate())
//            Thread.sleep(3000);
//
//        log("/*---16. select Informed Consent Provider -> Auto Clinician_DIWA_CP ---*/");
//        String consentProvider = ProfilesPage.consentProviderSelected(driver);
//        Thread.sleep(2000);
//        if(consentProvider.equals("")) {
//            consentProvider = profilesPage.selectConsentProvider();
//        }
//        //profilesPage.selectInformedConsentProvider("Auto Clinician_DIWA_CP");
//
//        log("/*---17. click Save Consent ---*/");
//        profilesPage.clickSaveConsent();
//        Thread.sleep(2000);

        try {
            ProfilesPage.checkExistingConsent(driver);
        } catch(Exception ex) {
            System.out.println("No Checkbox. Continue...");
        }

        log("/*---18. Select Immunizing Agent Provider ->: Auto Clinician_DIWA_CP ---*/");
        try {
            profilesPage.selectImmunizingAgentProvider(consentProvider);
        } catch(Exception ex) {
            ProfilesPage.clickEditImmunizationInformation(driver);
            Thread.sleep(500);
            profilesPage.selectImmunizingAgentProvider(consentProvider);
        }

        log("/*---19. Click Show all lot numbers Checkbox---*/");
        profilesPage.clickShowAllLotNumbersCheckBox();

        log("/*---20. click Lot Number dropdown component ---*/");
        profilesPage.clickLotNumberDropDown();

        log("/*---21. Select SPIKEVAX (Moderna) -> Lot -->300042698 - Exp. 2021 June 18 ---*/");
        profilesPage.selectLot();

        log("/*---22. Select Injection Site ---*/");
        profilesPage.selectInjectionSite();

        log("/*---23. Select Dosage---*/");
        profilesPage.selectDosage();

        log("/*---24. Save Immunization Information ---*/");
        profilesPage.saveImmunizationInformation();

        //Expired vax handler
        commonMethods.expiredVaxHandler();

        log("/*---25. Confirm and Save Administration ---*/");
        profilesPage.confirmAndSaveAdministration();

        log("/*---26. Vaccine Administration Summary Confirm and Save ---*/");
        profilesPage.summaryConfirmAndSave();
        Thread.sleep(2000);
        //If the PIR Warning is shown close it
        try {
            profilesPage.closePIRWarningDialog();
        } catch(Exception ex) {
            System.out.println("No PIR Warning. Continue...");
        }
        log("/*---27. Navigate to Related tab and Confirm new Imms Record is created ---*/");
        profilesPage.clickRelatedTab();
        log("We need to validate that Documented Without Appointment created - not validated yet  ---*/");
    }

}