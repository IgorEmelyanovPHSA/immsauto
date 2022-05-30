package bcvaxdevit.my.salesforce.com.Tests.CallCenter;

import bcvaxdevit.my.salesforce.com.Pages.CallCenterConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class BookingDose1 extends BaseTest {

    @Test
    public void Can_Book_Dose1_Appointment_as_Call_Center_Agent_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "153418"; //C153418
        System.out.println("/*1.----Login as an Cal Center Agent to the Call Center Console --*/");
        CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
        Thread.sleep(2000);
        System.out.println("/*2.----CallCenter Console page displayed --*/");
        callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
        Thread.sleep(5000);
        System.out.println("/*3.----Close All previously opened Tab's --*/");
        callCenterConsole.closeAllTabs();
        Thread.sleep(5000);
        System.out.println("/*4.----click Register New Citizen -Ludovika --*/");
        callCenterConsole.clickRegisterButton();
        Thread.sleep(2000);
        System.out.println("/*5.----Enter First Name Ludovika--*/");
        String firstName = "Ludovika";
        callCenterConsole.enterFirstName(firstName);
        Thread.sleep(2000);
        //System.out.println("/*6.----Enter Last Name BCVaxLimeburn--*/");
        //String lastName = "BCVaxLimeburn";
        //clinicInBox.enterLastName(lastName);
        //Thread.sleep(2000);
        //System.out.println("/*6.----Enter Date of birth--*/");
        //String dateOfBirth = "Sep 21, 1923";
        //clinicInBox.enterDateOfBirth(dateOfBirth);
        //Thread.sleep(2000);
        //System.out.println("/*7.----Enter Postal code--*/");
        //String postalCode = "V3L5L2";
        //clinicInBox.enterPostalCode(postalCode);
        //Thread.sleep(2000);
        //System.out.println("/*8.----Enter PHN--*/");
        //String phnNumber = "9746170911";
        //clinicInBox.enterPNH(phnNumber);
        //Thread.sleep(2000);
        //System.out.println("/*9.----click on non-Indigenous person radiobutton --*/");
        //clinicInBox.clickNonIndigenousRadioButton();
        //Thread.sleep(2000);
        //System.out.println("/*10.----click Verify PHN button --*/");
        //clinicInBox.clickVerifyPHNButton();
        //Thread.sleep(2000);
        //System.out.println("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        //clinicInBox.successMessageAppear();
        //Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        //System.out.println("/*12.----click Next button --*/");
        //clinicInBox.clickNextButton();
        //Thread.sleep(2000);
        //System.out.println("/*13.'Enter email address --*/");
        //String email = "test@qa.com";
        ///clinicInBox.enterEmail(email);
        //System.out.println("/*14.'Confirm email address --*/");
        //Thread.sleep(2000);
        //String email1 = "test@qa.com";
        //clinicInBox.confirmEmail(email1);
       //System.out.println("/*15.Click review details Button--*/");
        //Thread.sleep(2000);
        //clinicInBox.clickReviewDetails();
        //System.out.println("/*16.Click register Button on confirmation page--*/");
        //Thread.sleep(2000);
        //clinicInBox.clickRegisterButtonOnConfirmationPage();
        //Thread.sleep(2000);
        //System.out.println("/*17.--toast success message - 'Success' --*/");
        //clinicInBox.successRegisteredMessageAppear();
        //Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        //System.out.println("/*18.----click on person Account Related Tab --*/");
        //clinicInBox.clickOnPersonAccountRelatedTab();
       // Thread.sleep(2000);
       // System.out.println("/*18.----click on Eligibility button --*/");
       // clinicInBox.clickOnEligibilityButton();
        //Thread.sleep(2000);
       // System.out.println("/*19----select vaccination option -> COVID_19_Vaccination --*/");
        //clinicInBox.selectEligibilityOption();
        //Thread.sleep(2000);
        //System.out.println("/*20.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
       // clinicInBox.successEligibilityMessageAppear();
        //Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        //System.out.println("/*21----Go to Appointment Tab --*/");
       // clinicInBox.clickAppointmentTab();
        //Thread.sleep(2000);
        //System.out.println("/*22----click on reason for visit 'Covid-19 Vaccine' radiobutton --*/");
       // clinicInBox.clickOnReasonForVisit();
        //Thread.sleep(2000);
       // System.out.println("/*23----click on 'More' search tab --*/");
       // clinicInBox.clickOnMoreSearchTabs();
        //Thread.sleep(2000);
        //System.out.println("/*24----select 'Search clinic name' tab --*/");
        //clinicInBox.selectSearchClinicNameTab();
        //Thread.sleep(2000);
       // System.out.println("/*25----search the Clinic --*/");
       // clinicInBox.searchClinicName();
       // Thread.sleep(2000);
       // System.out.println("/*26----click on Option Facility location  --*/");
        //clinicInBox.clickOnFacilityOptionLocation();
       // Thread.sleep(2000);
       // System.out.println("/*27----select Active booking appointment day  --*/");
        //clinicInBox.selectBookingAppointmentDay();
        //Thread.sleep(2000);
       // System.out.println("/*28----select the time slot  --*/");
        //clinicInBox.selectTimeSlotAppointment();
       // Thread.sleep(2000);
       // System.out.println("/*29----click Next button  --*/");
        //clinicInBox.clickOnNextButton();
       // Thread.sleep(2000);
       // System.out.println("/*30----click Confirm Appointment button  --*/");
        //clinicInBox.clickOnConfirmButton();
       // Thread.sleep(2000);
       // System.out.println("/*31----see 'Appointment Confirmed!' screen --*/");
        //clinicInBox.validateAppointmentConfirmedScreen();
        //Thread.sleep(2000);
       // System.out.println("/*32----Refresh page --*/");
        //clinicInBox.refreshBrowser();
        //Thread.sleep(2000);
        ///System.out.println("/*33----Go to back to the Citizen Related Tab --*/");
       // clinicInBox.clickRelatedTab();
        //Thread.sleep(2000);
       // System.out.println("/*34----click on In-clinic Experience button --*/");
        //clinicInBox.ClickGoToInClinicExperienceButton();
       // InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
        //Thread.sleep(2000);
       // System.out.println("/*35----In-clinic Experience ->Vaccine Admin page appears up --*/");
        //InClinicExperience.validateVaccineAdminPageOpen();


    }

}
