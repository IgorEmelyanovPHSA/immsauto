package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class E2E_Dose2_Covid19_CP extends BaseTest{

    @Test
    public void Can_do_Dose1_Covid19_Vaccine_Administration_as_Clinician_ComunityQA() throws Exception {
        TestcaseID = "223187"; //C223187
        log("Target Environment: " + Utils.getTargetEnvironment());
        String clinicLocation = "All Ages - Atlin Health Centre";

        log("/*1.----Login as an Clinician to Community Portal --*/");
        CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
        Thread.sleep(10000);

    }

}
