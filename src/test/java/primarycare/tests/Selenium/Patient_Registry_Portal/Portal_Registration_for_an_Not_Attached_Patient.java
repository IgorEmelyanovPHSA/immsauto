package primarycare.tests.Selenium.Patient_Registry_Portal;


import primarycare.tests.Utilities.TestListener;
import bcvax.pages.Utils;
import primarycare.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.pages.PortalHealthConnectRegistryPage;

@Listeners({TestListener.class})
public class Portal_Registration_for_an_Not_Attached_Patient extends BaseTest {
    private String legalFirstName = "Sandy";
    private String legalLastName = "Prior";
    private String dateOfBirth = "March 1, 1975";
    private String postalCode = "V3L5L1";
    private String personalHealthNumber = "9873010063";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";

    @Test(priority = 1)
    public void Can_do_Registration_for_an_Not_Attached_Patient_in_Portal () throws Exception {
        TestcaseID = "251780"; //C251780
        log("Target Environment: "+ Utils.getTargetEnvironment());

        //CommonMethods com = new CommonMethods(getDriver());

        log("/*0.---Pre-conditioning API call to remove duplicate Patient account if found--*/");
       //Utilities.ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);

        log("/*1.---Open Patient Registry Portal (Health Connect Registry site)--*/");
        PortalHealthConnectRegistryPage portalHealthConnectRegistryPage= loginPage.openPortalHealthConnectRegistryPage();
        Thread.sleep(5000);

        log("/*2.---- Validate that the Register to get doctor page has displayed --*/");
        PortalHealthConnectRegistryPage.validateRegisterToGetDoctorPageDisplayed();
        Thread.sleep(5000);

        log("/*3.---Click Next button--*/");
        portalHealthConnectRegistryPage.clickNextButton();
        Thread.sleep(5000);

        log("/*4.---- Validate that the Who is Registering page has displayed --*/");
        PortalHealthConnectRegistryPage.validateWhoIsRegisteringPageDisplayed();
        Thread.sleep(5000);

        log("/*5.---Click Register my household button--*/");
        //portalHealthConnectRegistryPage.clickNextButton();
        Thread.sleep(5000);

    }



    //@Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Patient_account() throws Exception {
        //TestcaseID = "251434"; //C251434
        log("/---API call to remove duplicate Patient's account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        Utilities.ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }
}
