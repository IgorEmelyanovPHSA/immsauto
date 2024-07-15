package communityPortal.tests.VaccineAdministration_CP;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class E2E_Deferrals_CP extends BaseTest {
    MainPageCP cpMainPage;
    MainPageOrg orgMainPage;
    String env;
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    Map<String, String> client_data;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "deferral");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void can_create_new_deferral() throws Exception {
        TestcaseID = "243157";
        log("/*1.----Login to CP (newUI) as Clinician --*/");
        cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();;
        Thread.sleep(500);
        MainPageCP.search(driver, client_data.get("personalHealthNumber"));
        log("/*22.--Click Related Tab --*/");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToRelatedTab(driver);
        }
        PersonAccountRelatedPage.scrollToDeferrals(driver);
        int deferralsCountBefore = PersonAccountRelatedPage.getDeferralsCount(driver);
        PersonAccountRelatedPage.newDeferral(driver);
        DeferralForm newDeferral = new DeferralForm(driver);
        DeferralForm.cleanupProfile(driver);
        DeferralForm.saveDeferral(driver);
        String[] myErrors = DeferralForm.getMissingValuesError(driver);
        Arrays.sort(myErrors);
        String[] expectedErrors = {"Effective From", "Profile", "Agent", "Reason for Deferral"};
        Arrays.sort(expectedErrors);
        Assert.assertTrue(Arrays.equals(myErrors, expectedErrors));

        DeferralForm.setProfile(driver, client_data.get("legalFirstName") + " " + client_data.get("legalLastName"));
        DeferralForm.setAgent(driver, "COVID-19 mRNA");
        newDeferral.setReasonForDeferral("No Valid Consent");
        DeferralForm.setEffectiveFrom(driver);
        DeferralForm.saveDeferral(driver);
        Thread.sleep(2000);
        int deferralsCountAfter = PersonAccountRelatedPage.getDeferralsCount(driver);

        assertEquals(deferralsCountAfter, deferralsCountBefore + 1);
    }

    @Test(priority = 3)
    public void can_edit_existing_deferral() throws Exception {

    }
}

