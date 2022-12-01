package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.tests.BaseTest;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Scheduler extends BaseTest {
    private String legalFirstName = "Dacia";
    private String legalLastName = "Bcvaxdod";
    private String dateOfBirth = "May 19, 1904";
    private String postalCode = "V7N3K1";
    private String personalHealthNumber = "9746172456";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Chilliwack - Sardis Pharmacy";

    @Test(priority = 1)
    public void Can_not_book_two_people_if_only_one_slot_available_as_ADMIN_CIB_BCVAXDEVIT() throws Exception {
        TestcaseID = "225666"; //C225666 --Booking capacity - Full capacity & Cancel appointment
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
        log("/*-- 1.Login as an Clinician In-Clinic Experience --*/");
        InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
        Thread.sleep(10000);
        inClinicExperiencePage.closeTabsHCA();
        Thread.sleep(5000);
        if (inClinicExperiencePage.displaySupplyConsolePage()) {
            log("/*-- 2. User already on Health Connect - Supply Console --*/");
        } else {
            log("/*-- 2.1. Navigate to Health Connect - Supply Console --*/");
            inClinicExperiencePage.selectHealthConnectApp();
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        log("/*-- 3. Close all open tabs --*/");
        inClinicExperiencePage.closeTabsHCA();
    }

    //@Test(priority = 2)
    public void Can_book_if_people_Cancelled_booking_appointment_as_ADMIN_CIB_BCVAXDEVIT() throws Exception {
        TestcaseID = "225666"; //C225666 --Booking capacity - Full capacity & Cancel appointment
    }

    //@Test(priority = 3)
    public void Can_crate_Appointments_Blocks_as_ADMIN_CIB_BCVAXDEVIT() throws Exception {
        TestcaseID = "126911"; //C126911 --Appointment Day Management & Appointment Block creation
    }

    //@Test(priority = 4)
    public void Can_edit_Appointments_Blocks_as_ADMIN_CIB_BCVAXDEVIT() throws Exception {
        TestcaseID = "126911"; //C126911  --Appointment Day Management & Appointment Block creation
    }



}
