package communityPortal.tests.VaccineAdministration_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class E2E_Dose1_Covid19_CP extends BaseTest{
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

        log("/*3.----- Click on User Defaults Tab --*/");
        cpMainPage.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*4.----- Enter current date for UserDefaults --*/");
        cpMainPage.inputCurrentDateUserDefaults();

        log("/*5.----- Click on Save defaults button --*/");
        cpMainPage.clickSaveDefaultsButton();
        Thread.sleep(2000);

        log("/*6.----Navigate to More -> Register --*/");
        InClinicExperiencePage inClinicExperience_CP = cpMainPage.navigateToRegisterClientPage();
        Thread.sleep(2000);

        log("/*7.----click Register button New Citizen --*/");
        inClinicExperience_CP.clickRegisterButton();
        Thread.sleep(2000);
        log("/*8.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience_CP.enterFirstName(legalFirstName);
        Thread.sleep(2000);
        log("/*9.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience_CP.enterLastName(legalLastName);
        Thread.sleep(2000);
        log("/*10.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience_CP.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        log("/*11.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience_CP.enterPostalCode(postalCode);
        Thread.sleep(2000);
        log("/*12.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience_CP.enterPNH(personalHealthNumber);
        Thread.sleep(2000);
        log("/*13.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience_CP.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        log("/*14.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();
        Thread.sleep(2000);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessage();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        log("/*16.----click Next button --*/");
        inClinicExperience_CP.clickNextButton();
        Thread.sleep(2000);
        log("/*17.----'Enter email address " +email +"--*/");
        inClinicExperience_CP.enterEmail(email);
        log("/*18.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        inClinicExperience_CP.confirmEmail(email);
        log("/*19.---Click review details Button--*/");
        Thread.sleep(2000);
        inClinicExperience_CP.clickReviewDetails();
        log("/*20.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        inClinicExperience_CP.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        log("/*21.--toast success message - 'Success' --*/");
        inClinicExperience_CP.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

        Thread.sleep(15000); //wait for Related Tab accordion showing up

        log("/*22.----click on person Account Related Tab --*/");
        inClinicExperience_CP.clickOnPersonAccountRelatedTab();
        Thread.sleep(2000);



    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

}
