package bcvaxdevit.my.salesforce.com.Tests.ClincInBox;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger;

public class BookingDose1 extends BaseTest {
    static Logger log = Logger.getLogger(BookingDose1.class);

    @Test(priority = 1)
    public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
        TestcaseID = "219865"; //C219865
        System.out.println("Searching and Removing Citizen Duplicates BCVAXDEVIT");
        /*----Login as an Clinician In-Clinic Experience --*/
        System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
        InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
        /*----Go to Register Tab ---*/
        System.out.println("/*----Go to Register Tab ---*/");
        inClinicExperiencePage.clickRegisterTab();
        Thread.sleep(5000);
        /*----Search for Participant account ---*/
        System.out.println("/*----Search for Participant account ---*/");
        inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
        System.out.println("/*----Search for Ludovika is Successful ---*/");
        if (!inClinicExperiencePage.userFound()) {
            System.out.println("/*----User --> Ludovika not found and return---*/");
        }
        while (inClinicExperiencePage.userFound()) {
            System.out.println("/*----User found and Navigated to record page ---*/");
            Thread.sleep(2000);
            inClinicExperiencePage.clickRelatedTab();
            System.out.println("/*---- Navigated to Person Account related tab ---*/");
            Thread.sleep(2000);
            if (!inClinicExperiencePage.selectImmsRecord()) {
                System.out.println("/*----No Imms Record found and return---*/");
            } else {
                System.out.println("/*---- User navigated to Imms record ---*/");
                Thread.sleep(2000);
                inClinicExperiencePage.deleteImmsRecord();
                System.out.println("/*---- Imms record deleted Successfully ---*/");
                Thread.sleep(2000);
            }
            inClinicExperiencePage.clickRelatedTab();
            System.out.println("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
            Thread.sleep(5000);
            if (!inClinicExperiencePage.selectRERNRecord()) {
                System.out.println("/*----No RERN Record found and return---*/");
            } else {
                System.out.println("/*---- User navigated to RERN record ---*/");
                Thread.sleep(2000);
                inClinicExperiencePage.deleteRERNRecord();
                System.out.println("/*---- RERN record deleted Successfully ---*/");
                Thread.sleep(2000);
            }
            System.out.println("/*---- Navigated to Person Account related tab ---*/");
            inClinicExperiencePage.deletePersonAccount();
            System.out.println("/*---- Person Account deleted Successfully ---*/");
            Thread.sleep(2000);
            inClinicExperiencePage.clickRegisterTab();
            Thread.sleep(5000);
            inClinicExperiencePage.closeOpenTabs();
            System.out.println("/*---- Close the deleted Person Account ---*/");
            Thread.sleep(2000);
            System.out.println("/*----Re Searching for the Participant account ---*/");
            inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
            System.out.println("/*----Search for Ludovika is Successful ---*/");
        }
    }

    @Test(priority = 2)
    public void Can_Book_Dose1_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "192878"; //C192878
        //System.setProperty("log4j.properties", "hjjfhjf");
        PropertyConfigurator.configure("log4j.properties");
        System.out.println("/*1.----Login as an Clinician to CIB --*/");
        log.info("/*1.----Login as an Clinician to CIB --*/");
        ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
        Thread.sleep(2000);
        System.out.println("/*2.----Clinic In Box(IPM) page displayed --*/");
        log.info("/*2.----Clinic In Box(IPM) page displayed --*/");
        clinicInBox.verifyIsClinicInBoxPageDisplayed();
        Thread.sleep(5000);
        System.out.println("/*3.----Close All previously opened Tab's --*/");
        clinicInBox.closeAllTabs();
        Thread.sleep(5000);
        System.out.println("/*4.----click Register New Citizen -Ludovika --*/");
        clinicInBox.clickRegisterButton();
        Thread.sleep(2000);
        System.out.println("/*5.----Enter First Name Ludovika--*/");
        String firstName = "Ludovika";
        clinicInBox.enterFirstName(firstName);
        Thread.sleep(2000);
        System.out.println("/*6.----Enter Last Name BCVaxLimeburn--*/");
        String lastName = "BCVaxLimeburn";
        clinicInBox.enterLastName(lastName);
        Thread.sleep(2000);
        System.out.println("/*6.----Enter Date of birth--*/");
        String dateOfBirth = "Sep 21, 1923";
        clinicInBox.enterDateOfBirth(dateOfBirth);
        Thread.sleep(2000);
        System.out.println("/*7.----Enter Postal code--*/");
        String postalCode = "V3L5L2";
        clinicInBox.enterPostalCode(postalCode);
        Thread.sleep(2000);
        System.out.println("/*8.----Enter PHN--*/");
        String phnNumber = "9746170911";
        clinicInBox.enterPNH(phnNumber);
        Thread.sleep(2000);
        System.out.println("/*9.----click on non-Indigenous person radiobutton --*/");
        clinicInBox.clickNonIndigenousRadioButton();
        Thread.sleep(2000);
        System.out.println("/*10.----click Verify PHN button --*/");
        clinicInBox.clickVerifyPHNButton();
        Thread.sleep(2000);
        System.out.println("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        clinicInBox.successMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        System.out.println("/*12.----click Next button --*/");
        clinicInBox.clickNextButton();
        Thread.sleep(2000);
        System.out.println("/*13.'Enter email address --*/");
        String email = "test@qa.com";
        clinicInBox.enterEmail(email);
        System.out.println("/*14.'Confirm email address --*/");
        Thread.sleep(2000);
        String email1 = "test@qa.com";
        clinicInBox.confirmEmail(email1);
        System.out.println("/*15.Click review details Button--*/");
        Thread.sleep(2000);
        clinicInBox.clickReviewDetails();
        System.out.println("/*16.Click register Button on confirmation page--*/");
        Thread.sleep(2000);
        clinicInBox.clickRegisterButtonOnConfirmationPage();
        Thread.sleep(2000);
        System.out.println("/*17.--toast success message - 'Success' --*/");
        clinicInBox.successRegisteredMessageAppear();
        Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
        System.out.println("/*18.----click on person Account Related Tab --*/");
        clinicInBox.clickOnPersonAccountRelatedTab();
        Thread.sleep(2000);
        System.out.println("/*18.----click on Eligibility button --*/");
        clinicInBox.clickOnEligibilityButton();
        Thread.sleep(2000);
        System.out.println("/*19----select vaccination option -> COVID_19_Vaccination --*/");
        clinicInBox.selectEligibilityOption();
        Thread.sleep(2000);
        System.out.println("/*20----Go to Appointment Tab --*/");
        clinicInBox.clickAppointmentTab();
        Thread.sleep(2000);


    }

    @Test(priority = 3)
    public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
        TestcaseID = "219865"; //C219865
        System.out.println("Searching and Removing Citizen Duplicates BCVAXDEVIT");
        /*----Login as an Clinician In-Clinic Experience --*/
        System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
        InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
        /*----Go to Register Tab ---*/
        System.out.println("/*----Go to Register Tab ---*/");
        inClinicExperiencePage.clickRegisterTab();
        Thread.sleep(5000);
        /*----Search for Participant account ---*/
        System.out.println("/*----Search for Participant account ---*/");
        inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
        System.out.println("/*----Search for Ludovika is Successful ---*/");
        if (!inClinicExperiencePage.userFound()) {
            System.out.println("/*----User --> Ludovika not found and return---*/");
        }
        while (inClinicExperiencePage.userFound()) {
            System.out.println("/*----User found and Navigated to record page ---*/");
            Thread.sleep(2000);
            inClinicExperiencePage.clickRelatedTab();
            System.out.println("/*---- Navigated to Person Account related tab ---*/");
            Thread.sleep(2000);
            if (!inClinicExperiencePage.selectImmsRecord()) {
                System.out.println("/*----No Imms Record found and return---*/");
            } else {
                System.out.println("/*---- User navigated to Imms record ---*/");
                Thread.sleep(2000);
                inClinicExperiencePage.deleteImmsRecord();
                System.out.println("/*---- Imms record deleted Successfully ---*/");
                Thread.sleep(2000);
            }
            inClinicExperiencePage.clickRelatedTab();
            System.out.println("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
            Thread.sleep(5000);
            if (!inClinicExperiencePage.selectRERNRecord()) {
                System.out.println("/*----No RERN Record found and return---*/");
            } else {
                System.out.println("/*---- User navigated to RERN record ---*/");
                Thread.sleep(2000);
                inClinicExperiencePage.deleteRERNRecord();
                System.out.println("/*---- RERN record deleted Successfully ---*/");
                Thread.sleep(2000);
            }
            System.out.println("/*---- Navigated to Person Account related tab ---*/");
            inClinicExperiencePage.deletePersonAccount();
            System.out.println("/*---- Person Account deleted Successfully ---*/");
            Thread.sleep(2000);
            inClinicExperiencePage.clickRegisterTab();
            Thread.sleep(5000);
            inClinicExperiencePage.closeOpenTabs();
            System.out.println("/*---- Close the deleted Person Account ---*/");
            Thread.sleep(2000);
            System.out.println("/*----Re Searching for the Participant account ---*/");
            inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
            System.out.println("/*----Search for Ludovika is Successful ---*/");
        }
    }

}
