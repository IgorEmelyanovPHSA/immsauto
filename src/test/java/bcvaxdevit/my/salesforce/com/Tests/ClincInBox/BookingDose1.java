package bcvaxdevit.my.salesforce.com.Tests.ClincInBox;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class BookingDose1 extends BaseTest {

    @Test
    public void Can_Book_Dose1_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "192878"; //C192878
        System.out.println("/*1.----Login as an Clinician to CIB --*/");
        ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
        Thread.sleep(2000);
        System.out.println("/*2.----Clinic In Box(IPM) page displayed --*/");
        clinicInBox.verifyIsClinicInBoxPageDisplayed();
        Thread.sleep(5000);
        System.out.println("/*3.----Close All previously opened Tab's --*/");
        clinicInBox.closeAllTabs();
        Thread.sleep(5000);
        System.out.println("/*4.----click Register New Citizen -Ludovika --*/");
        clinicInBox.clickRegisterButton();
        Thread.sleep(2000);


    }

}
