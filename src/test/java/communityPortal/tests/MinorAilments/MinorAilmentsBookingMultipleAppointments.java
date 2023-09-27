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
    private String minorAilmentsToSelect = "Contraception";
    private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    private String notesToPharmacist = "C259543 - This message created by automation";

    @Test
    public void MinorAilmentsBookingMultipleAppointmentsTest() throws Exception {
        TestcaseID = "259543"; //C259543
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("API call to remove appointments from participant account by PHN if found");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(personalHealthNumber);

        BookAnAppointmentPage bookAnAppointmentPage = new BookAnAppointmentPage(getDriver());

        log("1. Open Minor Ailments portal");
        MinorAilmentsPage minorAilmentsPage = loginPage.openMinorAilmentsPortal();

        log("2. Fill all identification information and click btn continue");
        minorAilmentsPage.fillMandatoryFieldsOnIdentificationSection(legalFirstName, legalLastName, dateOfBirth, personalHealthNumber);

        log("3. Select minor ailment type: " + minorAilmentsToSelect + " ");
        minorAilmentsPage.selectOneOption(minorAilmentsToSelect);

        log("4. Add notes for the pharmacist: " + notesToPharmacist + " ");
        minorAilmentsPage.enterNotesForPharmacist(notesToPharmacist);

        log("5. Go to tab search by clinic name and select clinic " + clinicNameToSearch + " ");
        bookAnAppointmentPage.searchByClinicName(clinicNameToSearch);

        log("6. Select date and time for appointment and click btn Next ");
        bookAnAppointmentPage.selectDateAndTimeForAppointmentAndClickBtnNext();

        log("7. Click the checkboxes: 1)I verify that the contact information and 2)I consent to notifications");
        bookAnAppointmentPage.clickCheckBoxVerifyContactInformationAndConsentToNotifications();

        log("8. Click btn Confirm Appointment");
        bookAnAppointmentPage.clickBtnConfirmAppointment();

        log("9. Validate booking is confirmed");
        Assert.assertTrue(bookAnAppointmentPage.isBookingConfirmedDisplayed());

        log("10. Click btn Book another appointment");
        minorAilmentsPage.clickBtnBookAnotherAppointment();

        log("11. Validate Book a Pharmacy Appointment page is displayed");
        Assert.assertTrue(minorAilmentsPage.isBookAPharmacyAppointmentDisplayed());

    }



}
