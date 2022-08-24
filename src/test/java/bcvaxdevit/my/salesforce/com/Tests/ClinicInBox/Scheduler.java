package bcvaxdevit.my.salesforce.com.Tests.ClinicInBox;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class Scheduler extends BaseTest{
    @Test(priority = 1)
    public void Can_not_book_two_people_if_only_one_slot_available_as_PPHIS_CIB_BCVAXDEVIT() throws Exception {
        TestcaseID = "225666"; //C225666 --Booking capacity - Full capacity & Cancel appointment
    }

    //@Test(priority = 2)
    //public void Can_book_if_people_Cancelled_booking_appointment_as_PPHIS_CIB_BCVAXDEVIT() throws Exception {
     //   TestcaseID = "225666"; //C225666 --Booking capacity - Full capacity & Cancel appointment
    //}


    //@Test(priority = 3)
    //public void Can_crate_Appointments_Blocks_as_ADMIN_CIB_BCVAXDEVIT() throws Exception {
    //    TestcaseID = "126911"; //C126911 --Appointment Day Management & Appointment Block creation
    //}

    //@Test(priority = 4)
   //public void Can_edit_Appointments_Blocks_as_ADMIN_CIB_BCVAXDEVIT() throws Exception {
    //   TestcaseID = "126911"; //C126911  --Appointment Day Management & Appointment Block creation
    //}



}
