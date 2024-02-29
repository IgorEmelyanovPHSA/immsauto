package communityPortal.tests.MinorAilments;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class E2EMinorAilmentsCitizenPortal extends BaseTest {

    private String legalFirstName = "Ivy";
    private String legalLastName = "BCVaxHanna";
    private String dateOfBirth = "Dec 17, 1992";
    private String personalHealthNumber = "9746173078";
    private String postalCode = "V9L6G8";
    private String minorAilmentsToSelect = "Contraception";
    private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String notesToPharmacist = "This message created by automation";

    @Test
    public void MinorAilmentsE2EBooking_C259525() throws Exception {
        TestcaseID = "259525";
        log("TestCase: C259525");
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("API call to remove appointments from participant account by PHN if found");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(personalHealthNumber);

        BookAnAppointmentPage bookAnAppointmentPage = new BookAnAppointmentPage(getDriver());
        CommonMethods com = new CommonMethods(getDriver());
        InClinicExperiencePage inClinicExperience_CP = new InClinicExperiencePage(getDriver());

        log("1. Open Minor Ailments portal");
        MinorAilmentsPage minorAilmentsPage = loginPage.openMinorAilmentsPortal();

        log("2. Fill all identification information and click btn continue");
        minorAilmentsPage.fillMandatoryFieldsOnIdentificationSection(legalFirstName, legalLastName, dateOfBirth, personalHealthNumber);

        log("3. Verify that there are 22 items and the order in the picklist");
        minorAilmentsPage.verifyCountAndOrderOfTheList();

        log("4. Select minor ailment type: " + minorAilmentsToSelect + " ");
        minorAilmentsPage.selectOneOption(minorAilmentsToSelect);

        //TR Step3 verify link for btn More Info

        //TR Step4 verify the list of checkbox options for clinic features; Spoke to Ann, can skip this validation

        log("5. Add notes for the pharmacist: " + notesToPharmacist + " ");
        minorAilmentsPage.enterNotesForPharmacist(notesToPharmacist);

        log("6. Go to tab search by clinic name and select clinic " + clinicNameToSearch + " ");
        bookAnAppointmentPage.searchByClinicName(clinicNameToSearch);

        log("7. Select date and time for appointment and click btn Next ");
        bookAnAppointmentPage.selectDateAndTimeForAppointmentAndClickBtnNext();

        log("8. Click btn Confirm Appointment without consents");
        bookAnAppointmentPage.clickBtnConfirmAppointment();

        log("9. Validate the error message is displayed: Please complete all required fields before proceeding.");
        Assert.assertTrue(bookAnAppointmentPage.isToastErrorMessageCompleteAllRequiredFieldsDisplayed());

        log("10. Click the checkboxes: 1)I verify that the contact information and 2)I consent to notifications");
        bookAnAppointmentPage.clickCheckBoxVerifyContactInformationAndConsentToNotifications();

        log("11. Click btn Confirm Appointment");
        bookAnAppointmentPage.clickBtnConfirmAppointment();

        log("12. Validate booking is confirmed");
        Assert.assertTrue(bookAnAppointmentPage.isBookingConfirmedDisplayed());

        log("13. Get booked date and time");
        String confirmedAppointmentDataTime = bookAnAppointmentPage.getConfirmedAppointmentDateTime();

        //TR Step11 verify link for the Home page link

        log("14. Click btn Book another appointment");
        minorAilmentsPage.clickBtnBookAnotherAppointment();

        log("15. Validate Book a Pharmacy Appointment page is displayed");
        Assert.assertTrue(minorAilmentsPage.isBookAPharmacyAppointmentDisplayed());

        log("16. Login as an Clinician into CP");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("17. Search for citizen account by first and last name: " +legalFirstName +" " +legalLastName);
        com.globalSearchCP(legalFirstName + " " +legalLastName);

        log("18. Validation, if account is found open it");
        boolean isUserFound =  com.isUserFoundValidation(legalFirstName, "", legalLastName);
        if (!isUserFound){
            throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
        }

        log("19. Navigate to Related tab");
        PersonAccountPage.goToRelatedTab(driver);

        log("20. Navigate to Appointments and click view all");
        inClinicExperience_CP.navigateToAppointmentRecords();

        log("21. Open created appointment");
        inClinicExperience_CP.openAppointmentRecord(confirmedAppointmentDataTime);

        log("22. Verify Citizen Comment: " +notesToPharmacist);
        String actual_citizen_comment = inClinicExperience_CP.getCitizenComment();
        Assert.assertEquals(actual_citizen_comment, notesToPharmacist);

        log("23. Verify that Client Reason for Visit: Minor Ailments and Contraception");
        String actual_reason_for_visit = inClinicExperience_CP.getReasonForVisit();
        Assert.assertEquals(actual_reason_for_visit, "Minor Ailments and Contraception");
    }
}
