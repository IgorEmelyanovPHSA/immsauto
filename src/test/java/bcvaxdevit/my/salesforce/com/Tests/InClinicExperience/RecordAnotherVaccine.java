package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;


@Listeners({TestListener.class})
public class RecordAnotherVaccine extends BaseTest {
    private String legalFirstName = "Holden";
    private String legalLastName = "BCVaxCollingdon";
    private String dateOfBirth = "October 29, 1983";
    private String postalCode = "V5G2V4";
    private String personalHealthNumber = "9746172345";
    private String email = "accountToDelete@phsa.ca";
    private String recordAnotherVaccineValidation = "Select an option";

    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test(priority = 1)
    public void Can_Complete_Vaccine_Administration_as_Clinician_ICE_BCVAXDEVIT() throws Exception {
        TestcaseID = "C222505";
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        System.out.println("/*1.----Login as an Clinician to ICE --*/");
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICEWithParameters();
        Thread.sleep(10000);
        System.out.println("/*2.----In Clinic Experience(ICE) page displayed --*/");
        inClinicExperience.verifyIsICEpageDisplayed();
        Thread.sleep(5000);
        System.out.println("/*3.--- Navigate to In Clinic Experience App --*/");
        inClinicExperience.selectICEFromApp();
        Thread.sleep(5000);
        System.out.println("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        Thread.sleep(5000);
        System.out.println("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        Thread.sleep(2000);
        System.out.println("/*6.----- Enter current date for UserDefaults --*/");
        inClinicExperience.inputCurrentDateUserDefaults();
        Thread.sleep(2000);
        System.out.println("/*7.----- Click on Save defaults button --*/");
        inClinicExperience.clickSaveDefaultsButton();
        Thread.sleep(2000);
        System.out.println("/*8.----- Click on register Tab --*/");
        inClinicExperience.clickRegisterTab();
        Thread.sleep(2000);
        //System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
        //inClinicExperience.clickSaveModalDefaultsButton();
        //Thread.sleep(2000);
        System.out.println("/*10.----click Register button New Citizen --*/");
        inClinicExperience.clickRegisterButton();
        Thread.sleep(2000);
        System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
        inClinicExperience.enterFirstName(legalFirstName);
        Thread.sleep(2000);
        System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
        inClinicExperience.enterLastName(legalLastName);
        Thread.sleep(2000);
        System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
        inClinicExperience.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
        inClinicExperience.enterPostalCode(postalCode);
        Thread.sleep(2000);
        System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
        inClinicExperience.enterPNH(personalHealthNumber);
        Thread.sleep(2000);
        System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
        inClinicExperience.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        System.out.println("/*17.----click Verify PHN button --*/");
        inClinicExperience.clickVerifyPHNButton();
        Thread.sleep(2000);
        System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience.successMessage();
        Thread.sleep(5000);
        System.out.println("/*19.----click Next button --*/");
        inClinicExperience.clickNextButton();
        Thread.sleep(2000);
        System.out.println("/*20.----'Enter email address " +email +"--*/");
        inClinicExperience.enterEmail(email);
        System.out.println("/*21.----'Confirm email address " +email +"--*/");
        Thread.sleep(2000);
        inClinicExperience.confirmEmail(email);
        System.out.println("/*22.---Click review details Button--*/");
        Thread.sleep(2000);
        inClinicExperience.clickReviewDetails();
        System.out.println("/*23.----Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        inClinicExperience.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        System.out.println("/*24.--toast success message - 'Success' --*/");
        inClinicExperience.successRegisteredMessageAppear();
        Thread.sleep(5000);
        System.out.println("/*25.----click on person Account Related Tab --*/");
        inClinicExperience.clickOnPersonAccountRelatedTab();
        Thread.sleep(2000);
        System.out.println("/*26----Go to Appointment Tab --*/");
        inClinicExperience.navigateAppointmentSchedulingTab();
        Thread.sleep(2000);
        System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
        inClinicExperience.clickOnVaccinationCheckbox();
        Thread.sleep(2000);
        System.out.println("/*28----select 'Search by Clinic name' tab --*/");
        inClinicExperience.selectSearchByClinicNameTab();
        Thread.sleep(2000);
        log("/*29.----search the Clinic " +clinicNameToSearch +" --*/");
        inClinicExperience.searchClinicName(clinicNameToSearch);
        Thread.sleep(2000);
        System.out.println("/*30----click on Option Facility location  --*/");
        inClinicExperience.clickOnFacilityOptionLocation();
        Thread.sleep(2000);
        System.out.println("/*31----select Active booking appointment day  --*/");
        inClinicExperience.selectBookingAppointmentDay();
        Thread.sleep(2000);
        System.out.println("/*32----select the time slot  --*/");
        inClinicExperience.selectTimeSlotForAppointment();
        Thread.sleep(2000);
        System.out.println("/*33----click Next button  --*/");
        inClinicExperience.clickNextButtonApptSchedulingPage();
        Thread.sleep(2000);
        System.out.println("/*34----click Verify Contact Information Checkbox  --*/");
        inClinicExperience.clickVerifyContactInformation();
        Thread.sleep(2000);
        System.out.println("/*35----click Confirm Appointment button  --*/");
        inClinicExperience.clickAppointmentConfirmButton();
        Thread.sleep(2000);
        System.out.println("/*36. ----see 'Appointment confirmed!' screen --*/");
        inClinicExperience.AppointmentConfirmationMessage();
        Thread.sleep(2000);
        System.out.println("/*37.----Refresh page --*/");
        inClinicExperience.refreshBrowser();
        Thread.sleep(2000);
        System.out.println("/*38.----Go to back to the Citizen Related Tab --*/");
        inClinicExperience.clickRelatedTab();
        Thread.sleep(2000);
        System.out.println("/*39.----click on In-clinic Experience button --*/");
        inClinicExperience.ClickGoToInClinicExperienceButton();
        Thread.sleep(2000);
        System.out.println("/*40.----In-clinic Experience ->Vaccine Admin page appears up --*/");
        inClinicExperience.validateVaccineAdminPageOpen();
        Thread.sleep(5000);
        System.out.println("/*41.---Click confirm and Save Button --*/");
        inClinicExperience.HomePageClickConfirmAndSaveButton();
        Thread.sleep(5000);
        System.out.println("/*42.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
        inClinicExperience.selectVaccineAgent();
        Thread.sleep(3000);
        System.out.println("/*43.---Click Save Consent Button --*/");
        inClinicExperience.ClickSaveConsentButton();
        Thread.sleep(5000);
        System.out.println("/*44.---Click Save button for Immunisation Information --*/");
        inClinicExperience.ClickSaveImmuneInfoSaveButton();
        Thread.sleep(5000);
        System.out.println("/*45.---Click Save Administration & Record Another Vaccine Button --*/");
        inClinicExperience.ClickSaveAdministrationButtonAndRecordAnotherVaccine();
        Thread.sleep(3000);
        System.out.println("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
        inClinicExperience.ClickModalConfirmSaveAnotherVaccineButton();
        Thread.sleep(3000);
        System.out.println("/*45.---the Home - Client Search showing up  --*/");
        inClinicExperience.validateHomePageShownUp();
        Thread.sleep(3000);

        String agentSelection = inClinicExperience.validateVaccineAgentSelectionFieldIsDisplayed();
        log("/*46.---" + agentSelection + "field is displayed --*/");
        assertEquals(recordAnotherVaccineValidation, agentSelection);

    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "C222505";
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);



    }
}
