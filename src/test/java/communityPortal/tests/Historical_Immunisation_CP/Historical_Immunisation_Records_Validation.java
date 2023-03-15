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

    @Test
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
        String status = profilePage.pirSubmissionFieldStatus();
        log("/*10---- Pir Submission Field status is: " + status + " --*/");
        assertEquals(pirSubmissionField, status);
        Thread.sleep(5000);
        log("/*11---- Click Historical Immunization record --*/");
        int record_num = 1;
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
        assertEquals(pirSubmissionField, pirSubmissionStatusFieldValue);
        Thread.sleep(2000);
    }
    public void precondition() throws Exception {
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
            cpMainPage = loginPage.loginIntoCommunityPortalAsInventoryClinician();;
        }
        Thread.sleep(5000);
    }
}
