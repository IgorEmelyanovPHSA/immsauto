package communityPortal.tests.MinorAilments;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class MinorAilmentsBookingMultipleAppointments extends BaseTest {

    private String legalFirstName = "Ivy";
    private String legalLastName = "BCVaxHanna";
    private String dateOfBirth = "Dec 17, 1992";
    private String personalHealthNumber = "9746173078";
    private String postalCode = "V9L6G8";
    private String email = "accountToDelete@phsa.ca";
    private String minorAilmentsToSelectFirstAppointment = "Contraception";
    private String minorAilmentsToSelectSecondAppointment = "Headaches";
    private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String notesToPharmacist = "C259543 - This message was created by automation";

    @Test
    public void MinorAilmentsBookingMultipleAppointments_C259543() throws Exception {
        TestcaseID = "259543"; //C259543
        log("TestCase: C259543");
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("API call to remove appointments from participant account by PHN if found");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(personalHealthNumber);

        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(getDriver());

        log("1. Open Minor Ailments portal");
        MinorAilmentsPage minorAilmentsPage = loginPage.openMinorAilmentsPortal();

        log("2. Fill all identification information and click btn continue");
        minorAilmentsPage.fillMandatoryFieldsOnIdentificationSection(legalFirstName, legalLastName, dateOfBirth, personalHealthNumber);

        log("3. Select minor ailment type: " + minorAilmentsToSelectFirstAppointment);
        minorAilmentsPage.selectOneOption(minorAilmentsToSelectFirstAppointment);

        log("4. Add notes for the pharmacist: " + notesToPharmacist);
        minorAilmentsPage.enterNotesForPharmacist(notesToPharmacist);

        log("5. Go to tab search by clinic name and select clinic " + clinicNameToSearch + " ");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("6. Select date and time for appointment and click btn Next ");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("7. Click the checkboxes: 1)I verify that the contact information and 2)I consent to notifications");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        PersonAccountSchedulePage.clickVerifyConsentInformation(driver);

        log("8. Click btn Confirm Appointment");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("9. Validate booking is confirmed");
        Assert.assertTrue(PersonAccountSchedulePage.appointmentConfirmationMessage(driver));

        log("10. Get booked date and time for first appointment");
        //String confirmedAppointmentOneDataTime = bookAnAppointmentPage.getConfirmedAppointmentDateTime();

        log("11. Click btn Book another appointment");
        minorAilmentsPage.clickBtnBookAnotherAppointment();

        log("12. Validate Book a Pharmacy Appointment page is displayed");
        Assert.assertTrue(minorAilmentsPage.isBookAPharmacyAppointmentDisplayed());

        log("13. Select minor ailment type for 2nd appointment: " + minorAilmentsToSelectSecondAppointment);
        minorAilmentsPage.selectOneOption(minorAilmentsToSelectSecondAppointment);

        log("14. Add notes for the pharmacist: " + notesToPharmacist);
        minorAilmentsPage.enterNotesForPharmacist(notesToPharmacist);

        log("15. Go to tab search by clinic name and select clinic " + clinicNameToSearch + " ");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

        log("16. Select date and time for appointment and click btn Next ");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

        log("17. Click the checkboxes: 1)I verify that the contact information and 2)I consent to notifications");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        PersonAccountSchedulePage.clickVerifyConsentInformation(driver);

        log("18. Click btn Confirm Appointment");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);

        log("19. Validate booking is confirmed");
        Assert.assertTrue(PersonAccountSchedulePage.appointmentConfirmationMessage(driver));

        log("20. Get booked date and time for second appointment");
        //String confirmedAppointmentTwoDataTime = bookAnAppointmentPage.getConfirmedAppointmentDateTime();

        log("21. Login as an Clinician into CP");
        loginPage.loginIntoCommunityPortalAsClinician();

        log("22. Search for citizen account by first and last name: " +legalFirstName +" " +legalLastName);
        MainPageCP.search(driver, legalFirstName + " " + legalLastName);

//        log("23. Validation, if account is found open it");
//        boolean isUserFound =  com.isUserFoundValidation(legalFirstName, "", legalLastName);
//        if (!isUserFound){
//            throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
//        }

        log("24. Navigate to Related tab");
        PersonAccountPage.goToRelatedTab(driver);

        log("25. Navigate to Appointments and click view all");
        PersonAccountRelatedPage.scrollToAppointmentsSection(driver);
        //PersonAccountRelatedPage.clickAppointmentViewAllButton(driver);
        //Check status confirmed for 2 appointments

        log("26. Open second created appointment");
        PersonAccountRelatedPage.openFirstAppointmentRecord(driver);

        log("27. Verify Citizen Comment: " +notesToPharmacist);
        Assert.assertEquals(AppointmentDetailsPage.getCitizenComment(driver), notesToPharmacist);

//        log("28. Verify Minor Ailments type selected for second appointment (confirmed first appointment is open): " +minorAilmentsToSelectSecondAppointment);
//        Assert.assertEquals(inClinicExperience_CP.getMinorAilmentsAndContraceptionAppointmentType(), minorAilmentsToSelectSecondAppointment);

        log("29. Verify Appointment status is 'Confirmed'");
        String actual_appointment_status = AppointmentDetailsPage.getAppointmentStatus(driver);
        Assert.assertEquals(actual_appointment_status, "Confirmed");

        log("30. Cancel second appointment");
        AppointmentPage.clickBtnCancelAppointment(driver);

        //work around due to the bug: https://jira.phsa.ca/browse/BCVAX-33747

        log("31.----Refresh page --");
        inClinicExperience_CP.refreshBrowser();
        Thread.sleep(3000);

        log("32. Verify Appointment status is 'Cancelled'");
        Assert.assertEquals(AppointmentDetailsPage. getAppointmentStatus(driver), "Cancelled");

    }
}
