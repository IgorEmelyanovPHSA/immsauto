package communityPortal.tests.CitizenPortalFace_Integration;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static Utilities.ApiQueries.queryToGetUniqueLink;

public class E2E_Dose2_Self_Citizen_Booking_Covid19 extends BaseTest {
    String env;
    Map<String, Object> testData;
    String clinicNameToSearch;
    private boolean isIndigenous = false;
    private String vaccineToSelect = "Covid19Vaccine";

    Map<String, String> client_data;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose2");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void citizenPortalFlowDoseTwo() throws Exception {
        TestcaseID = "245218"; //C245218
        log("TestCase: C245218");
        log("Target Environment: "+ Utils.getTargetEnvironment());

        log("/*1.---Open citizen portal and click btn Register Now--*/");
        RegisterToGetVaccinatedPage registerToGetVaccinatedPage = loginPage.openRegisterToGetVaccinatedPage();
        RegisterToGetVaccinatedPage.clickBtnRegisterNow(driver);

        log("/*2.---Fill all registration information and click btn continue--*/");
        RegisterToGetVaccinatedPage.fillMandatoryFieldsOnRegistrationSection(driver, client_data);

        log("/*3.---Fill email and phone number on contact information section and click btn continue--*/");
        RegisterToGetVaccinatedPage.fillMandatoryFieldsOnContactInformationSection(driver, client_data);

        log("/*4.---Check checkbox certify and click btn submit--*/");
        registerToGetVaccinatedPage.certifyAndSubmit();

        log("/*5.---Check for registration successful message and save conformation number--*/");
        String conformationNumberText = registerToGetVaccinatedPage.registrationSuccessfulPageDisplayed();

        log("/*6.---Login as an Clinician--*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*7.---Search for Participant account by conformation number " + conformationNumberText + "--*/");
        MainPageCP.search(driver, conformationNumberText);

        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }

        PersonAccountPage.clickVerifyPHNButton(driver);
        PersonAccountPage.successMessageAppear(driver);
        //Extra step to log out from CP
        cpMainPage.logout();

        log("/*8.---Get unique link using Sales Force query over API--*/");
        String uniqueLink = queryToGetUniqueLink(conformationNumberText);

        log("/*9.---Open book an appointment portal from unique link--*/");
        BookAppointmentPage.openBookAnAppointmentPage(driver, uniqueLink);
        BookAppointmentPage.bookAnAppointmentPageDisplayed(driver);

        //Unique registration code validation
        String registrationConfirmationNumber = BookAppointmentPage.getRegistrationConfirmationNumber(driver);
        log("Compering registration confirmation number from registration page: " + conformationNumberText
                + " vs registration confirmation number from book an appointment page " + registrationConfirmationNumber);
        Assert.assertTrue(conformationNumberText.equalsIgnoreCase(registrationConfirmationNumber));

        log("/*10.---Open book an appointment portal from unique link--*/");
        BookAppointmentPage.enterPhnNumberAndClickBtnBookAppointment(driver, client_data.get("personalHealthNumber"));

        log("/*11.---Schedule vaccination page is displayed--*/");
        BookAppointmentPage.scheduleVaccinationAppointmentPageDisplayed(driver);

        log("/*12.---Select vaccination type: " + vaccineToSelect + "--*/");
        PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);

        log("/*13.---Go to tab search by clinic and select clinic " + clinicNameToSearch + "--*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("/*14.---Select date and time for appointment and click btn Next--*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("/*15.---Click verify contact information checkbox--*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("/*16.---Verify appointment conformation message is displayed--*/");
        PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
    }
}