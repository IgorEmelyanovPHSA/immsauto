package primarycare.tests.Selenium.Patient_SF_Admin_Interface;

import primarycare.pages.CommonMethods;
import primarycare.pages.HealthCloudConsolePage;
import primarycare.tests.Utilities.TestListener;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.tests.BaseTest_PrimaryCare;
import primarycare.tests.Utilities.ApiQueries;

import static org.testng.Assert.assertEquals;


@Listeners({TestListener.class})
public class AdminInterface_Register_Person_In_Care_Attached_SYSAdmin extends BaseTest_PrimaryCare {
    private String callersRelationships = "Social worker";
    private String callerName = "SELENIUM Social Worker behalf Caller";

    private String legalFirstName = "Kenton";
    private String legalLastName = "Troup";
    //private String dateOfBirth = "December 5, 1959";
    private String dateOfBirth_MM = "12";//December
    private String dateOfBirth_DD = "05";
    private String dateOfBirth_YYYY = "1959";
    private String personalHealthNumber = "9873010088";

    private String empiStatusExpected = "EMPI Verified";

    private String streetAddress = "309-7631 Francis Rd";
    private String City = "Richmond";
    private String province = "BC";
    private String postalCode = "V6Y 1A3";

    private String primaryContactName = "Igor PrimaryContactName";

    private String email = "accountToDelete@phsa.ca";
    private String mobilePhone = "7788797899";



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

        log("/*5.----Click registration type radiobutton -> 'A person in my care' --*/");
        healthCloudConsolePage.selectRegistrationType();
        Thread.sleep(2000);

        log("/*6.----Check 'caller has obtained consent' checkbox --*/");
        healthCloudConsolePage.clickObtainedConsent();
        Thread.sleep(2000);

        log("/*7.----Click 'Continue' button' --*/");
        healthCloudConsolePage.clickContinue();
        Thread.sleep(5000);

        log("/*8.----Select the Caller Identity checkbox' --*/");
        healthCloudConsolePage.selectCallerOnThePatientBehalf();
        Thread.sleep(2000);

        log("/*9.----Select the Caller's relationship with Patient -> Social worker' --*/");
        healthCloudConsolePage.selectCallerRelationshipWithPatient(callersRelationships);
        Thread.sleep(2000);

        log("/*10.----Enter the Caller Name -> 'SELENIUM Social Worker behalf Caller' --*/");
        healthCloudConsolePage.enterNameOfCaller(callerName);
        Thread.sleep(2000);

        log("/*11.----Enter Patient First Name " +legalFirstName +" --*/");
        healthCloudConsolePage.enterPatientFirstName(legalFirstName);
        Thread.sleep(2000);

        log("/*12.---Enter Patient Last Name " +legalLastName +"--*/");
        healthCloudConsolePage.enterPatientLastName(legalLastName);
        Thread.sleep(1000);

        log("/*13.---Enter PHN " +personalHealthNumber +"--*/");
        healthCloudConsolePage.enterPatientPHN(personalHealthNumber);
        //Thread.sleep(1000);

        log("/*14.---Enter Date of Birth - Month" +dateOfBirth_MM +"--*/");
        healthCloudConsolePage.enterMonth(dateOfBirth_MM);
        //Thread.sleep(1000);

        log("/*15.---Enter Date of Birth - Day" +dateOfBirth_DD +"--*/");
        healthCloudConsolePage.enterDay(dateOfBirth_DD);
        //Thread.sleep(1000);

        log("/*16.---Enter Date of Birth - Year" +dateOfBirth_YYYY +"--*/");
        healthCloudConsolePage.enterYear(dateOfBirth_YYYY);
        //Thread.sleep(1000);

        log("/*17.---Click 'Verify PHN' button--*/");
        healthCloudConsolePage.clickVerifyPHN();
        //Thread.sleep(1000);

        log("/*18. Validate EMPI Verification status --*/");
        String empiStatusActual = healthCloudConsolePage.getEMPIStatusActualForValidation();
        log("/*---- Actual EMPI verification status is: " + empiStatusActual + " --*/");
        assertEquals(empiStatusActual, empiStatusExpected);
        Thread.sleep(2000);

        log("/*19.---Click sex 'Male' button--*/");
        healthCloudConsolePage.clickSexMale();
        Thread.sleep(1000);

        log("/*20.---Enter Street address " +streetAddress +"----*/");
        healthCloudConsolePage.enterStreetAddress(streetAddress);
        Thread.sleep(1000);

        log("/*20.---click info icon button----*/");
        healthCloudConsolePage.clickOnInfoIcon();
        Thread.sleep(1000);

        log("/*21.---Enter City " +City +"----*/");
        healthCloudConsolePage.enterCity(City);
        Thread.sleep(1000);

        log("/*22.---Select Province option from dropdown" +province +"----*/");
        healthCloudConsolePage.enterProvince(province);
        Thread.sleep(1000);

        log("/*23.---Enter Postal Code" +postalCode +"----*/");
        healthCloudConsolePage.enterPostalCode(postalCode);
        Thread.sleep(1000);

        log("/*24.---Enter Primary contact name" +primaryContactName +"----*/");
        healthCloudConsolePage.enterPrimaryContactName(primaryContactName);
        Thread.sleep(1000);

        log("/*25.---Enter email" +email +"----*/");
        healthCloudConsolePage.enterEmailAddress(email);
        Thread.sleep(1000);

        log("/*26.---Confirm email" +email +"----*/");
        healthCloudConsolePage.enterConfirmEmailAddress(email);
        Thread.sleep(1000);

        log("/*27.---Enter mobile" +mobilePhone +"----*/");
        healthCloudConsolePage.enterMobilePhoneNumber(mobilePhone);
        //Thread.sleep(1000);

        log("/*28.---Select Communication Preference - Email----*/");
        //healthCloudConsolePage.selectCommunicationPreference();
        //Thread.sleep(1000);



    }



    //@Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Patient_account() throws Exception {
        TestcaseID = "251434"; //C251434
        log("/---API call to remove duplicate Patient's account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }


}
