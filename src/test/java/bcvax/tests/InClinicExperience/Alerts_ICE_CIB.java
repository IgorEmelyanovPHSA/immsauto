package bcvax.tests.InClinicExperience;
import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class Alerts_ICE_CIB extends BaseTest {
    String env;
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    Map<String, Object> testData;
    private String personalHealthNumber = "9746170911";
    private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    //private String email = "jason.yulghun@phsa.ca";
    String clinicNameToSearch;
    MainPageOrg orgMainPage;
    String consumptionLot;
    String consumptionDose;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;
    String consumptionAgent;

    @Test(priority = 1)
    public void verifyAlerts_ICE_CIB() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        consumptionDose = String.valueOf(testData.get("covidDose"));
        consumptionLot = String.valueOf(testData.get("covidLot"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        consumptionAgent = String.valueOf(testData.get("agentConsumption"));
        log("/*1.----Login --*/");

        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        //orgMainPage = loginPage.orgLoginAsPPHIS();
        TestcaseID = "225636"; //

        log("TestRail test case ID: C" +TestcaseID);

        log("/*2.----In Clinic Experience(ICE) page displayed --*/");
        String currentApp = orgMainPage.currentApp();
        try {
            orgMainPage.closeAllTabs();
        } catch(Exception ex) {
            ;
        }
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }

        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        log("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        log("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        log("/*6.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
        log("/*-- 13. Enter current date for UserDefaults --*/");
        userDefaultsPage.inputCurrentDateUserDefaults();
        userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
        log("/*7.----- Click on Save defaults button --*/");
        userDefaultsPage.clickBtnSave();
        AlertDialog.closeAlert(driver);
        System.out.println("/*8.----- Click on register Tab --*/");
        inClinicExperience.clickRegisterTab();
        //System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
        //inClinicExperience.clickSaveModalDefaultsButton();
        //Thread.sleep(2000);
        System.out.println("/*10.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();
        System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
        System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
        System.out.println("/*17.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);
        System.out.println("/*19.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        System.out.println("/*20.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        System.out.println("/*21.----'Confirm email address " +email +"--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);
        System.out.println("/*22.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        System.out.println("/*23.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        System.out.println("/*24.--toast success message - 'Success' --*/");
        try {
            CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        } catch(Exception ex) {
            System.out.println("No Success Message. Contrinue ...");
            System.out.println(ex.getMessage());
        }
        //System.out.println("/*25.----click on person Account Related Tab --*/");
        //inClinicExperience.clickOnPersonAccountRelatedTab();
        System.out.println("/*26----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);
        Thread.sleep(2000);
//If override Eligibility is shown
        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountSchedulePage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }
        Thread.sleep(2000);
        System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");

        System.out.println("/*27----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

        log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);

        System.out.println("/*29----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
        System.out.println("/*30----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        System.out.println("/*31----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        System.out.println("/*32----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
        System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        System.out.println("/*34----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);
        System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = inClinicExperience.AppointmentConfirmationMessage();

        Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
    }
}
