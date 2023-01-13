package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.CommunityPortalMainPage_as_Clinician;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class E2E_Dose2_Covid19_CP extends BaseTest{
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test
    public void Can_do_Dose1_Covid19_Vaccine_Administration_as_Clinician_ComunityQA() throws Exception {
        TestcaseID = "226778"; //C226778
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

        log("/*1.----Login as an Clinician to Community Portal --*/");
        CommunityPortalMainPage_as_Clinician cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();
        Thread.sleep(10000);

        log("/*2.----Community Portal Home page displayed --*/");
        cpMainPage.verifyIsCallCenterConsolePageDisplayed();
        Thread.sleep(5000);

        System.out.println("/*5.----- Click on User Defaults Tab --*/");
        //cpMainPage.clickUserDefaultsTab();
        Thread.sleep(2000);



    }

}
