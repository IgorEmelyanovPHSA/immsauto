package bcvax.tests.Portal;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Utilities.ApiQueries.queryToGetUniqueLink;

@Listeners({TestListener.class})
public class Dose2CitizenBookingAppointmentCovid19 extends BaseTest {

    private String legalFirstName = "Alexandro";
    private String legalLastName = "BCVaxDa Costa";
    private String legalLastNameASCII = "BCVaxDa%20Costa";
    private String legalMiddleName = "";
    private String dateOfBirth = "May 06, 1977";
    private String postalCode = "V8W7P2";
    private String personalHealthNumber = "9746172069";
    private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    private String phoneNumber = "6041234568";
    private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String vaccineToSelect = "Covid19Vaccine";
    MainPageOrg orgMainPage;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        log("/*0.---API call to remove duplicate citizen participant account after test finished--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }

    @Test(priority = 1)
    public void citizenPortalFlowDoseTwo() throws Exception {
        TestcaseID = "222522"; //C222522
        log("Target Environment: "+ Utils.getTargetEnvironment());
        CommonMethods com = new CommonMethods(getDriver());

        //log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);

        log("/*1.---Open citizen portal and click btn Register Now--*/");
        RegisterToGetVaccinatedPage registerToGetVaccinatedPage = loginPage.openRegisterToGetVaccinatedPage();
        registerToGetVaccinatedPage.clickBtnRegisterNow();

        log("/*2.---Fill all registration information and click btn continue--*/");
        registerToGetVaccinatedPage.fillMandatoryFieldsOnRegistrationSection(legalFirstName, legalLastName, legalMiddleName, dateOfBirth, postalCode,
                personalHealthNumber, isIndigenous);

        log("/*3.---Fill email and phone number on contact information section and click btn continue--*/");
        registerToGetVaccinatedPage.fillMandatoryFieldsOnContactInformationSection(email, phoneNumber);

        log("/*4.---Check checkbox certify and click btn submit--*/");
        registerToGetVaccinatedPage.certifyAndSubmit();

        log("/*5.---Check for registration successful message and save conformation number--*/");
        String conformationNumberText = registerToGetVaccinatedPage.registrationSuccessfulPageDisplayed();

        //log("/*6.---Login as an Clinician to ICE--*/");
        //InClinicExperiencePage inClinicExperiencePage = loginPage.loginAsClinicianICE();
        //Thread.sleep(5000);
        log("/*6.----Login as an Clinician to CIB --*/");
        ClinicInBoxPage clinicInBox = loginPage.loginAsClerk();
        orgMainPage = new MainPageOrg(driver);
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
        }
        clinicInBox.verifyIsClinicInBoxPageDisplayed();

        log("/*6.1.----Close All previously opened Tab's --*/");
        clinicInBox.closeAllTabs();

        log("/*7.---Search for Participant account by conformation number " + conformationNumberText + "--*/");
        //inClinicExperiencePage.SearchForCitizen(conformationNumberText);
        com.globalSearch(conformationNumberText);

        log("/*7.1---Validation, isUserFound account validation --*/");
        boolean isUserFound =  com.isUserFoundValidation(legalFirstName, legalMiddleName, legalLastName);
        if (!isUserFound){
            throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
        }

        log("/*8.---Get unique link using Sales Force query over API--*/");
        String uniqueLink = queryToGetUniqueLink(conformationNumberText);

        log("/*9.---Open book an appointment portal from unique link--*/");
        BookAnAppointmentPage bookAnAppointmentPage = loginPage.openBookAnAppointmentPage(uniqueLink);
        bookAnAppointmentPage.bookAnAppointmentPageDisplayed();

        //Unique registration code validation
        String registrationConfirmationNumber = bookAnAppointmentPage.getRegistrationConfirmationNumber();
        log("Compering registration confirmation number from registration page: " + conformationNumberText
                + " vs registration confirmation number from book an appointment page " + registrationConfirmationNumber);
        Assert.assertTrue(conformationNumberText.equalsIgnoreCase(registrationConfirmationNumber));

        log("/*10.---Open book an appointment portal from unique link--*/");
        bookAnAppointmentPage.enterPhnNumberAndClickBtnBookAppointment(personalHealthNumber);

        log("/*11.---Schedule vaccination page is displayed--*/");
        bookAnAppointmentPage.scheduleVaccinationAppointmentPageDisplayed();

        //If override Eligibility is shown
        try {
            System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
            PersonAccountPage.overrideEligibility(driver);
        } catch(Exception ex) {
            System.out.println("There is not Override Eligibility Option");
        }

        log("/*12.---Select vaccination type: " + vaccineToSelect + "--*/");
        bookAnAppointmentPage.selectOneOption(vaccineToSelect);

        log("/*13.---Go to tab search by clinic and select clinic " + clinicNameToSearch + "--*/");
        bookAnAppointmentPage.searchByClinicName(clinicNameToSearch);

        log("/*14.---Select date and time for appointment and click btn Next--*/");
        bookAnAppointmentPage.selectDateAndTimeForAppointmentAndClickBtnNext();

        log("/*15---Click verify contact information checkbox--*/");
        bookAnAppointmentPage.clickCheckBoxVerifyContactInformationAndConfirmAppointment();

        log("/*16---Verify appointment conformation message is displayed--*/");
        boolean appointment_result = bookAnAppointmentPage.appointmentConfirmationPageDisplayed();
        Assert.assertTrue(appointment_result);
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);
    }

}
