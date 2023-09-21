package communityPortal.tests.CitizenPortalFace_Integration;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Utilities.ApiQueries.queryToGetUniqueLink;

@Listeners({TestListener.class})
public class E2E_Dose1_Self_Citizen_Booking_Covid19 extends BaseTest {

    private String legalFirstName = "Anne-marie";
    private String legalLastName = "BCVaxJacketts";
    private String legalMiddleName = "Elissa";
    private String dateOfBirth = "Aug 16, 1903";
    private String postalCode = "V3B0J5";
    private String personalHealthNumber = "9746173988";
    private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    private String phoneNumber = "6041234568";
    private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String vaccineToSelect = "Covid19Vaccine";

    //Login as an admin for now, needs to be updated to ICE
    //Needs to update TestcaseId
    @Test(priority = 1)
    public void CP_CitizenPortalBookDoseOneCovid19() throws Exception {
        TestcaseID = "245217"; //C245217
        log("Target Environment: " + Utils.getTargetEnvironment());
        CommonMethods com = new CommonMethods(getDriver());

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        //Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        //tilities.ApiQueries.apiCallToRemoveCitizenParticipantAndPIRAccount(email, legalLastName, legalFirstName);
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(personalHealthNumber);

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

        log("/*6.---Login as an Clinician--*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*7.---Search for Participant account by conformation number " + conformationNumberText + "--*/");
        com.globalSearchCP(conformationNumberText);

        log("/*7.1---Validation, isUserFound account validation --*/");
        boolean isUserFound =  com.isUserFoundValidation(legalFirstName, legalMiddleName, legalLastName);
        if (!isUserFound){
            throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
        }
        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(getDriver());
        log("/*14.----click Verify PHN button --*/");
        inClinicExperience_CP.clickVerifyPHNButton();
        Thread.sleep(2000);
        log("/*15.--Expecting to see the toast success message - 'PNH match successful' --*/");
        inClinicExperience_CP.successMessageAppear();


        //Extra step to log out from CP
        loginPage.logOutCommunityPortal();

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

        log("/*15.---Click verify contact information checkbox--*/");
        bookAnAppointmentPage.clickCheckBoxVerifyContactInformationAndConfirmAppointment();

        log("/*16.---Verify appointment conformation message is displayed--*/");
        bookAnAppointmentPage.appointmentConfirmationPageDisplayed();
    }

    @Test(priority = 2)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
        TestcaseID = "219865"; //C219865
        log("/---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
    }
}
