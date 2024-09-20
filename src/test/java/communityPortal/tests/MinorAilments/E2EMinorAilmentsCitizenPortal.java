package communityPortal.tests.MinorAilments;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class E2EMinorAilmentsCitizenPortal extends BaseTest {
    String env;
    Map<String, Object> testData;
    Map<String, String> client_data;
    String legalFirstName;
    String legalLastName;
    String dateOfBirth;
    String personalHealthNumber;
    String postalCode;
    String email;
    String minorAilmentsToSelect = "Contraception";
    String clinicNameToSearch;
    String notesToPharmacist = "This message created by automation";

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "minor_ailment");
        legalFirstName = client_data.get("legalFirstName");
        legalLastName = client_data.get("legalLastName");
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationMinorAilment"));
        dateOfBirth = Utils.convertDate(client_data.get("dateOfBirth"), "MMM dd, yyyy");
        personalHealthNumber = client_data.get("personalHealthNumber");
        postalCode = client_data.get("postalCode");
        email = client_data.get("email");
    }
    @Test
    public void MinorAilmentsE2EBooking_C259525() throws Exception {
        TestcaseID = "259525";
        log("TestCase: C259525");
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("API call to remove appointments from participant account by PHN if found");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(personalHealthNumber);

        log("1. Open Minor Ailments portal");
        MinorAilmentsPage minorAilmentsPage = loginPage.openMinorAilmentsPortal();

        log("2. Fill all identification information and click btn continue");
        MinorAilmentsPage.fillMandatoryFieldsOnIdentificationSection(driver, legalFirstName, legalLastName, dateOfBirth, personalHealthNumber);

        log("3. Verify that there are 22 items and the order in the picklist");
        MinorAilmentsPage.verifyCountAndOrderOfTheList(driver);

        log("4. Select minor ailment type: " + minorAilmentsToSelect + " ");
        MinorAilmentsPage.selectOneOption(driver, minorAilmentsToSelect);

        //TR Step3 verify link for btn More Info

        //TR Step4 verify the list of checkbox options for clinic features; Spoke to Ann, can skip this validation

        log("5. Add notes for the pharmacist: " + notesToPharmacist + " ");
        MinorAilmentsPage.enterNotesForPharmacist(driver, notesToPharmacist);

        log("6. Go to tab search by clinic name and select clinic " + clinicNameToSearch + " ");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("7. Select date and time for appointment and click btn Next ");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("8. Click btn Confirm Appointment without consents");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("9. Validate the error message is displayed: Please complete all required fields before proceeding.");
        boolean toastErrorShown = BookAppointmentPage.isToastErrorMessageCompleteAllRequiredFieldsDisplayed(driver);
        Assert.assertTrue(toastErrorShown, "No Error Found");

        log("10. Click the checkboxes: 1)I verify that the contact information and 2)I consent to notifications");
        PersonAccountSchedulePage.clickVerifyConsentInformation(driver);
        PersonAccountSchedulePage.checkEmailPreferredContactMethod(driver);
        log("11. Click btn Confirm Appointment");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("12. Validate booking is confirmed");
        Assert.assertTrue(PersonAccountSchedulePage.appointmentConfirmationMessage(driver));

        log("13. Get booked date and time");
        //String confirmedAppointmentDataTime = bookAnAppointmentPage.getConfirmedAppointmentDateTime();

        //TR Step11 verify link for the Home page link

        log("14. Click btn Book another appointment");
        minorAilmentsPage.clickBtnBookAnotherAppointment();

        log("15. Validate Book a Pharmacy Appointment page is displayed");
        Assert.assertTrue(minorAilmentsPage.isBookAPharmacyAppointmentDisplayed());

        log("16. Login as an Clinician into CP");
        loginPage.loginIntoCommunityPortalAsClinician();

        log("17. Search for citizen account by first and last name: " +legalFirstName +" " +legalLastName);
        MainPageCP.search(driver, legalFirstName + " " +legalLastName);

        log("18. Validation, if account is found open it");
//        boolean isUserFound =  com.isUserFoundValidation(legalFirstName, "", legalLastName);
//        if (!isUserFound){
//            throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
//        }

        log("19. Navigate to Related tab");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            PersonAccountPage.goToRelatedTab(driver);
        }

        log("20. Navigate to Appointments and click view all");
        PersonAccountRelatedPage.scrollToAppointmentsSection(driver);
        //PersonAccountRelatedPage.clickAppointmentViewAllButton(driver);
        log("21. Open created appointment");
        PersonAccountRelatedPage.openFirstAppointmentRecord(driver);

        log("22. Verify Citizen Comment: " +notesToPharmacist);
        String actual_citizen_comment = AppointmentDetailsPage.getCitizenComment(driver);
        Assert.assertEquals(actual_citizen_comment, notesToPharmacist);

        log("23. Verify that Client Reason for Visit: Minor Ailments and Contraception");
        String actual_reason_for_visit = AppointmentDetailsPage.getReasonForVisit(driver);
        Assert.assertEquals(actual_reason_for_visit, "Minor Ailments and Contraception");
    }
}
