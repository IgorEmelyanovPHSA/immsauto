package communityPortal.tests.VaccineAdministration_CP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class E2E_Deferrals_CP extends BaseTest {
    MainPageCP cpMainPage;
    MainPageOrg orgMainPage;
    String env;
    private String legalFirstName = "AB";
    //private String legalLastName = "Said";
    private String legalLastName = "SAID";
    private String dateOfBirth = "Jan 02, 2023";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9879450975";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
    }
    @Test(priority = 1)
    public void can_create_new_deferral() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "245096" : "235260";
        precondition();
        log("/*6.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();
        Thread.sleep(2000);

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();
        Thread.sleep(2000);
        log("/*8.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        Thread.sleep(2000);
        log("/*9.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        Thread.sleep(2000);
        log("/*10.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        Thread.sleep(2000);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        Thread.sleep(2000);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
        Thread.sleep(2000);

        Thread.sleep(2000);
        log("/*14.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        Thread.sleep(2000);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*16.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        Thread.sleep(2000);
        log("/*17.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        log("/*18.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        CitizenPrimaryInfo.confirmEmail(driver, email);
        log("/*19.---Click review details Button--*/");
        Thread.sleep(2000);
        CitizenPrimaryInfo.clickReviewDetails(driver);
        log("/*20.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        Thread.sleep(2000);
        log("/*21.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        log("/*22.--Click Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        Thread.sleep(3000);
        inClinicExperience_CP.scrollToDeferrals();
        Thread.sleep(2000);
        int deferralsCountBefore = inClinicExperience_CP.getDeferralsCount();
        inClinicExperience_CP.newDeferral();
        DeferralForm newDeferral = new DeferralForm(driver);
        newDeferral.cleanupProfile();
        newDeferral.saveDeferral();
        String[] myErrors = newDeferral.getMissingValuesError();
        Arrays.sort(myErrors);
        String[] expectedErrors = {"Effective From", "Profile", "Agent", "Reason for Deferral"};
        Arrays.sort(expectedErrors);
        Assert.assertTrue(Arrays.equals(myErrors, expectedErrors));

        newDeferral.setProfile(legalFirstName + " " + legalLastName);
        newDeferral.setAgent("COVID-19 mRNA");
        newDeferral.setReasonForDeferral("No Valid Consent");
        newDeferral.setEffectiveFrom();
        newDeferral.saveDeferral();
        Thread.sleep(2000);
        int deferralsCountAfter = inClinicExperience_CP.getDeferralsCount();

        assertEquals(deferralsCountAfter, deferralsCountBefore + 1);
    }

    @Test(priority = 2)
    public void verify_existing_deferral_is_displayed() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "245096" : "235260";
        precondition();

    }
    @Test(priority = 3)
    public void can_edit_existing_deferral() throws Exception {

    }
    @Test(priority = 4)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

    public void precondition() throws Exception {
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
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
            cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();;
        }
        Thread.sleep(5000);
    }
}

