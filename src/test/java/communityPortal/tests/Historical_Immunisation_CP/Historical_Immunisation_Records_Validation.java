package communityPortal.tests.Historical_Immunisation_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import bcvax.pages.MainPageCP;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertFalse;

@Listeners({TestListener.class})
public class Historical_Immunisation_Records_Validation extends BaseTest {
    MainPageCP cpMainPage;
    MainPageOrg orgMainPage;
    ProfilesPage profilePage;
    String env;
    private String legalFirstName = "Maegan";
    private String legalLastName = "bcvaxvillage";
    private String legalMiddleName = "Tanya";
    private String pirSubmissionField = "Submitted";
    private String patwayStatusFieldValidation = "Pathway Status";
    private String pirSubmissionStatusFieldValidation = "PIR Submission Status";

    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
    }

    @Test(priority = 1)
    public void Validate_Historical_Immunization_PIR_Status_as_Clinician_ComunityQA() throws Exception {
        TestcaseID = "243177"; //C243177 -- CIB - Historical Immunization Records - PIR Submission Status
        precondition();
        log("Target Environment: "+ env);
        profilePage = cpMainPage.navigateToProfilesPage();
        log("/* 2.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
        profilePage.selectAllParticipantsOption();
        cpMainPage.search(legalFirstName + " " + legalMiddleName + " " + legalLastName);
        profilePage.openProfile(legalFirstName + " " + legalMiddleName + " " + legalLastName);
        profilePage.clickRelatedTab();
        profilePage.navigateToHistoricalImmunizationRecords();
        Thread.sleep(1000);

        String field = profilePage.pirSubmissionStatusFieldIsDisplayed();
        log("/*9---- " + field + "is displayed --*/");
        Thread.sleep(2000);
        int record_num = 1;
        String status = profilePage.pirSubmissionFieldStatus(record_num);
        log("/*10---- Pir Submission Field status is: " + status + " --*/");
        assertFalse(status.isEmpty());
        Thread.sleep(5000);
        log("/*11---- Click Historical Immunization record --*/");

        profilePage.ClickHistoricalImmunizationRecord(record_num);
        ImmunizationPageCP immunizationPage = new ImmunizationPageCP(driver);
        Thread.sleep(5000);
        String pirSubmissionStatus = immunizationPage.validatePirSubmissionStatusFieldIsDisplayed();
        log("/*12---- Field " + pirSubmissionStatus + "is displayed --*/");
        assertEquals(pirSubmissionStatusFieldValidation, pirSubmissionStatus);
        Thread.sleep(2000);
        String pathwayStatus = immunizationPage.validatePathwayStatusFieldIsDisplayed();
        log("/*13---- Field " + pathwayStatus + "is displayed --*/");
        assertEquals(patwayStatusFieldValidation, pathwayStatus);
        Thread.sleep(2000);
        log("/*14---- remediation needed checkbox is not checked --*/");
        immunizationPage.remidiationNeededCheckBox();
        Thread.sleep(2000);
        String pirId = immunizationPage.pirImmunizationId();
        log("/*15---- Pir ID is: " + pirId + "--*/");
        assertNotNull(pirId);
        Thread.sleep(2000);
        String pirSubmissionStatusFieldValue = immunizationPage.pirSubmissionStatusFieldValue();
        log("/*16---- Pir Submission Field status is: " + pirSubmissionStatusFieldValue + " --*/");
        assertEquals(pirSubmissionStatusFieldValue, status);
        //assertEquals(pirSubmissionField, pirSubmissionStatusFieldValue);
        Thread.sleep(2000);
    }

    //@Test(priority = 2)
    public void Can_Create_Historical_Immunization_Record_via_RelatedTab_as_Clinician_BCVAXDEVIT() throws Exception {
        //TestcaseID = "??????"; //C??????
        log("Target Environment: " + Utils.getTargetEnvironment());
        precondition();
        log("Target Environment: "+ env);
        profilePage = cpMainPage.navigateToProfilesPage();

        log("/* 2.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
        profilePage.selectAllParticipantsOption();
        cpMainPage.search(legalFirstName + " " + legalMiddleName + " " + legalLastName);
        profilePage.openProfile(legalFirstName + " " + legalMiddleName + " " + legalLastName);
        profilePage.clickRelatedTab();
        profilePage.navigateToHistoricalImmunizationRecords();
        Thread.sleep(1000);
        int historicalDosesbefore = Integer.parseInt(profilePage.getHistoricalDosesValue());
        log("/*----7. get historical doses Value + ---*/" + historicalDosesbefore);
        log("/*----8. Click Create Immunization Record ---*/ ");
        profilePage.clickToCreatHistoricalImmunizationRecord();
        Thread.sleep(2000);
        log("/*-- 9---Click to select Agent --*/");
        profilePage.ClickAgentValue();
        Thread.sleep(2000);
        log("/*-- 10--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
        profilePage.SelectAgent();
        Thread.sleep(2000);
        log("/*-- 11--- Select historical date from 6 months ago --*/");
        profilePage.selectHistoricalDateAndTime();
        Thread.sleep(2000);
        log("/*-- 12--- Click Save Button --*/");
        profilePage.ClickSaveButton();
        Thread.sleep(2000);
        int historicalDoses_after = Integer.parseInt(profilePage.getHistoricalDosesValue());
        log("/*-- 13. remaining doses After creating Historical Doses: -->" + historicalDoses_after);
        assertEquals(historicalDoses_after, historicalDosesbefore + 1);
    }

    public void precondition() throws Exception {
//        log("/*1.----Login --*/");
//        switch (Utils.getTargetEnvironment()) {
//            case "comunityqa_immsbc_admin":
//                log("Login as ImmsBCAdmin");
//                TestcaseID = "243177"; //C243177
//                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
//                break;
//            default:
//                log("Login as Clinician user");
//                TestcaseID = "243177"; //C243177
//                loginPage.loginIntoCommunityPortalAsClinician();
//
//        }
//        Thread.sleep(10000);
        if(env.contains("immsbc_admin")) {
            log("/*1.----Login to CP (newUI) as ImmsBC_Admin --*/");
            orgMainPage = loginPage.orgLoginAsImmsBCAdminCP();
            Thread.sleep(1000);
            orgMainPage.switchApp(Apps.BCH_VACCINATION_PORTAL.value);
            Thread.sleep(3000);
            cpMainPage = new MainPageCP(driver);
            cpMainPage.clickGoToUserDefaultsButton();
        } else {
            log("/*1.----Login to CP (newUI) as Clinician --*/");
            cpMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();
        }
        Thread.sleep(5000);
    }
}
