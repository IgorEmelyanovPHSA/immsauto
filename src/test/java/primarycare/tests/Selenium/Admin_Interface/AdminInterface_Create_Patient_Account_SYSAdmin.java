package primarycare.tests.Selenium.Admin_Interface;

import Utilities.TestListener;
import bcvax.pages.CallCenterConsolePage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class AdminInterface_Create_Patient_Account_SYSAdmin extends BaseTest {
    private String legalFirstName = "Sandy";
    private String legalLastName = "Prior";
    private String dateOfBirth = "March 1, 1975";
    private String postalCode = "V3L5L1";
    private String personalHealthNumber = "9873010063";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";



    @Test(priority = 1)
    public void Can_Create_Patient_Account_in_Health_Cloud_Console_as_an_SYSAdmin () throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
        System.out.println("/*1.----Login as an PCN_Coordinator to Admin Interface --*/");
        CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
        Thread.sleep(10000);
        System.out.println("/*2.----Health Cloud Console page displayed --*/");
        callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
        Thread.sleep(5000);
    }



    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Patient_account() throws Exception {
        TestcaseID = "251434"; //C251434
        log("/---API call to remove duplicate Patient's account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        Utilities.ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }


}
