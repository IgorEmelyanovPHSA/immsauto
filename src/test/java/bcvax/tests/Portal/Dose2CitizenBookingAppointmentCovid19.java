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

import java.util.Map;

import static Utilities.ApiQueries.queryToGetUniqueLink;

@Listeners({TestListener.class})
public class Dose2CitizenBookingAppointmentCovid19 extends BaseTest {
    String env;
    Map<String, Object> testData;
    String clinicNameToSearch;
    private String vaccineToSelect = "Covid19Vaccine";
    MainPageOrg orgMainPage;

    Map<String, String> client_data;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose2");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test()
    public void citizenPortalFlowDoseTwo() throws Exception {
        TestcaseID = "222522";
        log("TestCase: C222522");
        log("Target Environment: "+ Utils.getTargetEnvironment());
        //CommonMethods com = new CommonMethods(getDriver());

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

        //log("/*6.---Login as an Clinician to ICE--*/");
        //InClinicExperiencePage inClinicExperiencePage = loginPage.loginAsClinicianICE();
        //Thread.sleep(5000);
        log("/*6.----Login as an Clinician to CIB --*/");
        //ClinicInBoxPage clinicInBox = loginPage.loginAsClerk();
        loginPage.loginAsImmsBCAdmin();
        ClinicInBoxPage clinicInBox = new ClinicInBoxPage(driver);
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
        }
        MainPageOrg.closeAllTabs(driver);
        MainPageOrg.selectFromNavigationMenu(driver, "Home");
        clinicInBox.verifyIsClinicInBoxPageDisplayed();

        log("/*6.1.----Close All previously opened Tab's --*/");
        clinicInBox.closeAllTabs();

        log("/*7.---Search for Participant account by conformation number " + conformationNumberText + "--*/");
        //inClinicExperiencePage.SearchForCitizen(conformationNumberText);
        MainPageOrg.search(driver, client_data.get("legalFirstName") + " " + (client_data.get("legalMiddleName") == "" ? "" : client_data.get("legalMiddleName") + " ") + client_data.get("legalLastName"));

//        log("/*7.1---Validation, isUserFound account validation --*/");
//        boolean isUserFound =  com.isUserFoundValidation(legalFirstName, legalMiddleName, legalLastName);
//        if (!isUserFound){
//            throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
//        }

        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }

        PersonAccountPage.clickVerifyPHNButton(driver);
        PersonAccountPage.successMessageAppear(driver);
        Thread.sleep(2000);
        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            PersonAccountPage.clickVerifyPHNButton(driver);
        } catch(Exception ex) {
            System.out.println("Warning dialog didn't appear");
        }
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

        log("/*15---Click verify contact information checkbox--*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("/*16---Verify appointment conformation message is displayed--*/");
        boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
        Assert.assertTrue(appointment_result);
    }

}