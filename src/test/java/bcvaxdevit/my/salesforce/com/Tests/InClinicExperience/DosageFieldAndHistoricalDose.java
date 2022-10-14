package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;



import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class DosageFieldAndHistoricalDose extends BaseTest {

    private String legalFirstName = "Holden";
    private String legalLastName = "BCVaxCollingdon";
    private String dateOfBirth = "October 29, 1983";
    private String postalCode = "V5G2V4";
    private String personalHealthNumber = "9746172345";
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test(priority = 1)
    public void Can_Book_Dose1_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws Exception {
        //TestcaseID = "777777";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        log("/*1.----Login as an Clinician to CIB --*/");
        ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIBWithParameters();
        Thread.sleep(10000);
        log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
        if (clinicInBox.displayCIBApp()) {
            log("/*---- User already on CIB Page--*/");
            Thread.sleep(2000);
        } else {
            log("/*---- Navigate to CIB App --*/");
            clinicInBox.selectCIBApp();
            Thread.sleep(2000);
        }
        //clinicInBox.verifyIsClinicInBoxPageDisplayed();
        Thread.sleep(5000);
        log("/*3.----Close All previously opened Tab's --*/");
        clinicInBox.closeAllTabs();
        Thread.sleep(5000);
        log("/*4.----click Register New Citizen --*/");
        clinicInBox.clickRegisterButton();
        Thread.sleep(2000);
        log("/*5.----Enter First Name: " +legalFirstName +"--*/");
        clinicInBox.enterFirstName(legalFirstName);
        Thread.sleep(2000);
        log("/*6.----Enter Last Name: " +legalLastName +"--*/");
        clinicInBox.enterLastName(legalLastName);
        Thread.sleep(2000);
        log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
        clinicInBox.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        log("/*7.----Enter Postal code: " +postalCode +"--*/");
        clinicInBox.enterPostalCode(postalCode);
        Thread.sleep(2000);
        log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
        clinicInBox.enterPNH(personalHealthNumber);
        Thread.sleep(2000);
        log("/*9.----click on non-Indigenous person radiobutton --*/");
        clinicInBox.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        log("/*10.----click Verify PHN button --*/");
        clinicInBox.clickVerifyPHNButton();
        Thread.sleep(2000);
        log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        clinicInBox.successMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        log("/*12.----click Next button --*/");
        clinicInBox.clickNextButton();
        Thread.sleep(2000);
        log("/*13.'Enter email address: " +email +"--*/");
        clinicInBox.enterEmail(email);
        log("/*14.'Confirm email address: " +email +"--*/");
        Thread.sleep(2000);
        clinicInBox.confirmEmail(email);
        log("/*15.Click review details Button--*/");
        Thread.sleep(2000);
        clinicInBox.clickReviewDetails();
        log("/*16.Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        clinicInBox.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        log("/*17.--toast success message - 'Success' --*/");
        clinicInBox.successRegisteredMessageAppear();
        Thread.sleep(5000);
        log("/*18.----click on person Account Related Tab --*/");
        clinicInBox.clickOnPersonAccountRelatedTab();
        Thread.sleep(2000);
        log("/*19----Go to Appointment Tab --*/");
        clinicInBox.clickAppointmentTab();
        Thread.sleep(2000);
        log("/*20----click on reason for visit 'Covid-19 Vaccine' radiobutton --*/");
        clinicInBox.clickOnVaccinationCheckbox();
        Thread.sleep(2000);
        log("/*21----select 'Search by Clinic name' tab --*/");
        clinicInBox.selectSearchByClinicNameTab();
        Thread.sleep(2000);
        log("/*22----search the Clinic " +clinicNameToSearch +" --*/");
        clinicInBox.searchClinicName(clinicNameToSearch);
        Thread.sleep(2000);
        log("/*23----click on Option Facility location  --*/");
        clinicInBox.clickOnFacilityOptionLocation();
        Thread.sleep(2000);
        log("/*24----select Active booking appointment day  --*/");
        clinicInBox.selectBookingAppointmentDay();
        Thread.sleep(2000);
        log("/*25----select the time slot  --*/");
        clinicInBox.selectTimeSlotAppointment();
        Thread.sleep(2000);
        log("/*26----click Next button  --*/");
        clinicInBox.clickOnNextButton();
        Thread.sleep(2000);
        log("/*27----click Verify Contact Information Checkbox  --*/");
        clinicInBox.clickVerifyContactInformation();
        Thread.sleep(2000);
        log("/*28----click Confirm Appointment button  --*/");
        clinicInBox.clickOnConfirmButton();
        Thread.sleep(2000);
        log("/*29----see 'Appointment confirmed!' screen --*/");
        clinicInBox.validateAppointmentConfirmedScreen();
        Thread.sleep(2000);
        log("/*30----Refresh page --*/");
        clinicInBox.refreshBrowser();
        Thread.sleep(2000);
        log("/*31----Go to back to the Citizen Related Tab --*/");
        clinicInBox.clickRelatedTab();
        Thread.sleep(2000);
        log("/*32----click on In-clinic Experience button --*/");
        InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
        Thread.sleep(2000);
        log("/*33----In-clinic Experience ->Vaccine Admin page appears up --*/");
        InClinicExperience.validateVaccineAdminPageOpen();
        Thread.sleep(5000);
        log("/*34----Vaccine administration and rebook appointment --*/");
        InClinicExperience.ClickRebookAppointment();
        Thread.sleep(3000);
        log("/*35----Confirm and Save booking --*/");
        InClinicExperience.HomePageClickConfirmAndSaveButton();
        Thread.sleep(3000);
        log("/*36----Consent Provider --*/");
        InClinicExperience.ClickAgentValue();
        Thread.sleep(3000);
        InClinicExperience.SelectAgentValue();
        Thread.sleep(3000);
        log("/*37----Save Consent --*/");
        InClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);
        log("/*38----Immunization Information with Lot number and Dosage--*/");
        InClinicExperience.selectToSetLot();
        Thread.sleep(2000);
        log("/*39---- Select Lot -->300042698 - Exp. 2021 June 18 ---*/");
        InClinicExperience.selectLot();
        Thread.sleep(2000);
        log("/*40---- Select Injection Site ---*/");
        InClinicExperience.selectInjectionSite();
        Thread.sleep(3000);
        log("/*41---- Select Dosage ---*/");
        InClinicExperience.selectDosage();
        Thread.sleep(3000);
        InClinicExperience.saveImmunizationInformation();
        Thread.sleep(3000);
        //InClinicExperience.clickEditAgentInformation();
//        Thread.sleep(2000);
//        log("/*42---- Edit Agent Information To Change Different Agent ---*/");
//        InClinicExperience.selectToSetLot();
//        Thread.sleep(2000);
//        log("/*43---- Select Lot -->SPIKEVAX6-5Test001 ---*/");
//        InClinicExperience.selectLot();
//        Thread.sleep(2000);
//        log("/*44---- Select Injection Site ---*/");
//        InClinicExperience.selectInjectionSite();
//        Thread.sleep(3000);
//        log("/*45---- Select Dosage ---*/");
//        InClinicExperience.selectNewDosage();
//        Thread.sleep(3000);
//        InClinicExperience.saveImmunizationInformation();
//        Thread.sleep(3000);


    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        //TestcaseID = "777777";
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

}
