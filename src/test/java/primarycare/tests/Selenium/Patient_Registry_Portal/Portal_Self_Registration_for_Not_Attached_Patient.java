package primarycare.tests.Selenium.Patient_Registry_Portal;

import primarycare.pages.CommonMethods;
import primarycare.pages.HealthCloudConsolePage;
import primarycare.tests.Utilities.ApiQueries;
import primarycare.tests.Utilities.TestListener;
import bcvax.pages.Utils;
import primarycare.tests.BaseTest_PrimaryCare;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.pages.PortalHealthConnectRegistryPage;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Portal_Self_Registration_for_Not_Attached_Patient extends BaseTest_PrimaryCare {
    private String legalFirstName = "Sandy";
    private String legalLastName = "Prior";
    private String personalHealthNumber = "9873010063";
    private String dateOfBirth_MM = "03";//March
    private String dateOfBirth_DD = "01";
    private String dateOfBirth_YYYY = "1975";
    private String streetAddress = "307-7631 Francis Rd";
    private String City = "Richmond";
    private String province = "BC";
    private String postalCode = "V6Y 1A3";
    private String email = "accountToDelete@phsa.ca";
    private String mobilePhone = "7788797898";
    private String communicationPreference = "Email";

    private String caseOriginExpectedValue = "Web";
    private String priorityExpectedValue = "None";//"Medium" for UAT;
    private String statusExpected = "Active";
    private String accountNameExpected = "3113 Broadmoor";
    private String caseReasonExpected = "Unattached - Requires attachment to family doctor or nurse practitioner";

    @Test(priority = 1)
    public void Can_do_Self_Registration_for_Not_Attached_Patient_in_Portal () throws Exception {
        TestcaseID = "251780"; //C251780
        log("Target Environment: "+ Utils.getTargetEnvironment());

        //CommonMethods com = new CommonMethods(getDriver());

        log("/*0.---Pre-conditioning API call to remove duplicate Patient account if found--*/");
        ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);

        log("/*1.---Open Patient Registry Portal (Health Connect Registry site)--*/");
        PortalHealthConnectRegistryPage portalHealthConnectRegistryPage= loginPage.openPortalHealthConnectRegistryPage();
        Thread.sleep(10000);

        log("/*2.---- Validate that the Register to get doctor page has displayed --*/");
        PortalHealthConnectRegistryPage.validateRegisterToGetDoctorPageDisplayed();
        //Thread.sleep(1000);

        log("/*3.---Click Next button--*/");
        portalHealthConnectRegistryPage.clickNextButton();
        //Thread.sleep(1000);

        log("/*4.--- Validate that the Who is Registering page has displayed --*/");
        PortalHealthConnectRegistryPage.validateWhoIsRegisteringPageDisplayed();
        //Thread.sleep(1000);

        log("/*5.---Click Register my household button--*/");
        portalHealthConnectRegistryPage.clickRegisterMyHouseholdButton();
        Thread.sleep(10000);

        log("/*6.---Enter First Name " +legalFirstName +"--*/");
        portalHealthConnectRegistryPage.enterFirstName(legalFirstName);
        //Thread.sleep(1000);


        log("/*7.---Enter Last Name " +legalLastName +"--*/");
        portalHealthConnectRegistryPage.enterLastName(legalLastName);
        //Thread.sleep(1000);

        log("/*8.---Enter PHN " +personalHealthNumber +"--*/");
        portalHealthConnectRegistryPage.enterPHN(personalHealthNumber);
        //Thread.sleep(1000);

        log("/*9.---Enter Date of Birth - Month" +dateOfBirth_MM +"--*/");
        portalHealthConnectRegistryPage.enterMonth(dateOfBirth_MM);
        //Thread.sleep(1000);

        log("/*10.---Enter Date of Birth - Day" +dateOfBirth_DD +"--*/");
        portalHealthConnectRegistryPage.enterDay(dateOfBirth_DD);
        //Thread.sleep(1000);

        log("/*11.---Enter Date of Birth - Year" +dateOfBirth_YYYY +"--*/");
        portalHealthConnectRegistryPage.enterYear(dateOfBirth_YYYY);
        //Thread.sleep(1000);

        log("/*12.---Click Continue--*/");
        portalHealthConnectRegistryPage.clickContinueButton();
        //Thread.sleep(1000);

        log("/*13.---Enter Street address " +streetAddress +"----*/");
        portalHealthConnectRegistryPage.enterStreetAddress(streetAddress);
        //Thread.sleep(1000);

        log("/*14.---Enter City " +City +"----*/");
        portalHealthConnectRegistryPage.enterCity(City);
       // Thread.sleep(1000);

        log("/*14_1.---Select Province option from dropdown" +province +"----*/");
        portalHealthConnectRegistryPage.enterProvince(province);
        //Thread.sleep(1000);

        log("/*15.---Enter Postal Code" +postalCode +"----*/");
        portalHealthConnectRegistryPage.enterPostalCode(postalCode);
        //Thread.sleep(1000);

        log("/*16.---Click Continue--*/");
        portalHealthConnectRegistryPage.clickContinueButton();
        //Thread.sleep(1000);

        //log("/*17.---Select Sex female----*/");
        //portalHealthConnectRegistryPage.selectSex();
        //Thread.sleep(1000);

        log("/*17.---Enter email" +email +"----*/");
        portalHealthConnectRegistryPage.enterEmailAddress(email);
        //Thread.sleep(1000);

        log("/*18.---Enter email" +email +"----*/");
        portalHealthConnectRegistryPage.re_enterEmailAddress(email);
        //Thread.sleep(1000);

        log("/*19.---Enter mobile" +mobilePhone +"----*/");
        portalHealthConnectRegistryPage.enterMobilePhoneNumber(mobilePhone);
        //Thread.sleep(1000);

        log("/*20.---Select Communication Preference - Email----*/");
        portalHealthConnectRegistryPage.selectCommunicationPreference();
        //Thread.sleep(1000);

        log("/*21.---Click Continue--*/");
        portalHealthConnectRegistryPage.clickContinueButton();
        //Thread.sleep(1000);

        log("/*22.---Click 'No' currently having family doctor--*/");
        portalHealthConnectRegistryPage.clickNoFamilyDoctorRadiobutton();
        //Thread.sleep(1000);

        log("/*23.---Click 'No' need a translator--*/");
        portalHealthConnectRegistryPage.clickNoNeedTranslatorRadiobutton();
        //Thread.sleep(1000);

        log("/*24.---Click Continue--*/");
        portalHealthConnectRegistryPage.clickContinueButton();
        //Thread.sleep(1000);

        log("/*25.---Click Submit registration --*/");
        portalHealthConnectRegistryPage.clickSubmitRegistrationButton();
        Thread.sleep(10000);

        log("/*26.--- Validate is 'Registration successful!' page displayed? --*/");
        PortalHealthConnectRegistryPage.validateRegisterSuccessfulPageDisplayed();
        Thread.sleep(5000);

        log("/*27.--- Login as an SysAdmin to Health Cloud Console of SF Admin side --*/");
        HealthCloudConsolePage healthCloudConsolePage = loginPage.loginAsSysAdmin();
        Thread.sleep(10000);// wait for sf loading

        log("/*28.----Validate if Health Cloud Console Page displayed --*/");
        CommonMethods common = new CommonMethods(getDriver());
        common.goToHealthCloudConsolePageIfNeededAndConfirmPageIsDisplayed();
        Thread.sleep(5000);

        log("/*29.----Close All previously opened Tab's --*/");
        common.closeAllHealthCloudConsoleTabs();
        Thread.sleep(2000);

        log("/*29_1.----Select Home from Navigation Menu Dropdown --*/");
        common.selectHomeFromNavigationMenuDropdown();
        Thread.sleep(2000);

        Thread.sleep(5000);
        log("/*29_2 ----Refresh page --*/");
        common.refreshBrowser();
        Thread.sleep(5000);

        log("/*30.---Search for Patient by PHN " + legalFirstName + " "+ legalLastName +"--*/");
        common.globalSearch(personalHealthNumber);
        Thread.sleep(2000);

        log("/*31.---Click on founded Patient--*/");
        common.clickOnFondedPatient(legalFirstName, legalLastName);
        Thread.sleep(2000);

        log("/*32.---Go to Related Tab--*/");
        healthCloudConsolePage.clickOnRelatedTab();
        Thread.sleep(2000);

        log("/*33.---Click on Case record link--*/");
        healthCloudConsolePage.clickOnCaseRecord();
        Thread.sleep(2000);

        log("/*34.---- Validate field Case origin - 'Web'  ---*/");
        String caseOriginActualValue = healthCloudConsolePage.getCaseOriginActualForValidation();
        log("/*---- Case Origin actual value is: " + caseOriginActualValue + " --*/");
        assertEquals(caseOriginActualValue, caseOriginExpectedValue);
        Thread.sleep(2000);

        log("/*35.---- Validate Priority  - 'None'  ---*/");
        String priorityActualValue = healthCloudConsolePage.getPriorityActualForValidation();
        log("/*---- Priority actual value is: " + priorityActualValue + " --*/");
        assertEquals(priorityActualValue, priorityExpectedValue);
        Thread.sleep(2000);

        log("/*36.---- Validate Account name - '3113 Broadmoor'  ---*/");
        String accountNameActual = healthCloudConsolePage.getAccountNameActualForValidation();
        log("/*---- Account Name actual is: " + accountNameActual + " --*/");
        assertEquals(accountNameActual, accountNameExpected);
        Thread.sleep(2000);

        log("/*37.---- Validate Case Reason - 'Unattached - Requires attachment to family doctor or nurse practitioner'  ---*/");
        String caseReasonActual = healthCloudConsolePage.getCaseReasonActualForValidation();
        log("/*---Case Reason actual is: " + caseReasonActual + " --*/");
        assertEquals(caseReasonActual, caseReasonExpected);
        Thread.sleep(2000);

        log("/*38.---- Validate Status - 'Active'  ---*/");
        //log("/*----scroll down a bit --*/");
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
        //Thread.sleep(2000);
        String statusActual = healthCloudConsolePage.getStatusActualForValidation();
        log("/*---- Status actual is: " + statusActual + " --*/");
        assertEquals(statusActual, statusExpected);
        Thread.sleep(2000);

    }



    //@Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Patient_account() throws Exception {
        TestcaseID = "251774"; //C251774
        log("/---API call to remove duplicate Patient's account if found--*/");
        ApiQueries.apiCallToRemovePatientAccount(email, legalLastName, legalFirstName);
    }
}
