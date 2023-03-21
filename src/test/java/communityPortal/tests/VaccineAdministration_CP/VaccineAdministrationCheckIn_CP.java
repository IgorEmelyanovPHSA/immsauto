package communityPortal.tests.VaccineAdministration_CP;

import bcvax.pages.MainPageCP;
import bcvax.pages.MainPageOrg;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VaccineAdministrationCheckIn_CP extends BaseTest {
    MainPageCP cpMainPage;
    MainPageOrg orgMainPage;
    String env;
    @BeforeMethod
    public void setUpClass() throws Exception {
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
    }
    @Test(priority = 1)
    public void can_start_vaccine_administration_process_for_citizen_without_appointment() throws Exception {
        TestcaseID = (env.contains("immsbc_admin")) ? "245096" : "242265";
        precondition();
        System.out.println("Here");
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
            cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();;
        }
        Thread.sleep(5000);
    }
}
