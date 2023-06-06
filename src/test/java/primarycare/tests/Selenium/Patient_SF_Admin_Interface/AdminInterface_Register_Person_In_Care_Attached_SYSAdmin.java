package primarycare.tests.Selenium.Patient_SF_Admin_Interface;

import primarycare.pages.CommonMethods;
import primarycare.pages.HealthCloudConsolePage;
import primarycare.tests.Utilities.TestListener;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.tests.BaseTest_PrimaryCare;
import primarycare.tests.Utilities.ApiQueries;


@Listeners({TestListener.class})
public class AdminInterface_Register_Person_In_Care_Attached_SYSAdmin extends BaseTest_PrimaryCare {
    private String legalFirstName = "Kenton";
    private String legalLastName = "Troup";
    private String dateOfBirth = "December 5, 1959";
    private String postalCode = "V3L5L2";
    private String personalHealthNumber = "9873010088";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";

    private String callersRelationships = "Social worker";
    private String callerName = "SELENIUM Social Worker behalf Caller";



    @Test(priority = 1)
    public void Can_Register_Person_In_Care_for_Attached_in_SF_AdminInterface_as_an_SYSAdmin () throws Exception {
        TestcaseID = "256267"; //C256267
        log("Target Environment: "+ Utils.getTargetEnvironment());

        log("/*0.---Pre-conditioning API call to remove duplicate Patient account if found--*/");
        ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);


        log("/*1.--- Login as an SysAdmin to Health Cloud Console of SF Admin side --*/");
        HealthCloudConsolePage healthCloudConsolePage = loginPage.loginAsSysAdmin();
        Thread.sleep(10000);// wait for sf loading


        log("/*2.----Validate if Health Cloud Console Page displayed --*/");
        CommonMethods common = new CommonMethods(getDriver());
        common.goToHealthCloudConsolePageIfNeededAndConfirmPageIsDisplayed();
        Thread.sleep(5000);

        log("/*3.----Close All previously opened Tab's --*/");
        common.closeAllHealthCloudConsoleTabs();
        Thread.sleep(2000);

        log("/*4.----Select Create 'New Registration' from Navigation Menu Dropdown --*/");
        common.selectCreateNewRegistrationFromNavigationMenuDropdown();
        Thread.sleep(2000);

        log("/*5.----Click registration type radiobutton -> 'A person in care' --*/");
        healthCloudConsolePage.selectRegistrationTypePersonInCare();
        Thread.sleep(2000);

        log("/*6.----Click 'Next' button' --*/");
        healthCloudConsolePage.clickNext();
        Thread.sleep(5000);

        log("/*6.----Select the Caller Identity checkbox' --*/");
        healthCloudConsolePage.selectCallerOnThePatientBehalf();
        Thread.sleep(5000);

        log("/*6.----Select the Caller's relationship with Patient -> Social worker' --*/");
        healthCloudConsolePage.selectCallerRelationshipWithPatient(callersRelationships);
        Thread.sleep(2000);

    }



    //@Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Patient_account() throws Exception {
        TestcaseID = "251434"; //C251434
        log("/---API call to remove duplicate Patient's account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }


}
