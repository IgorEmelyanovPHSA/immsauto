package bcvaxdevit.my.salesforce.com.Tests.CallCenter;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.CallCenterConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		System.out.println("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		Thread.sleep(10000);
		if (inClinicExperiencePage.displayIceApp()) {
			System.out.println("/*---- User already on ICE--*/");
		} else {
			System.out.println("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
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
	public void Can_Book_Dose1_Appointment_as_Call_Center_Agent_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "153418"; //C153418
		System.out.println("/*1.----Login as an Cal Center Agent to the Call Center Console --*/");
		CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
		Thread.sleep(5000);
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
		System.out.println("/*6.----Enter Last Name BCVaxLimeburn--*/");
		String lastName = "BCVaxLimeburn";
		callCenterConsole.enterLastName(lastName);
		Thread.sleep(2000);
		System.out.println("/*6.----Enter Date of birth--*/");
		String dateOfBirth = "Sep 21, 1923";
		callCenterConsole.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		System.out.println("/*7.----Enter Postal code--*/");
		String postalCode = "V3L5L2";
		callCenterConsole.enterPostalCode(postalCode);
		Thread.sleep(2000);
		System.out.println("/*8.----Enter PHN--*/");
		String phnNumber = "9746170911";
		callCenterConsole.enterPNH(phnNumber);
		Thread.sleep(2000);
		System.out.println("/*9.----click on non-Indigenous person radiobutton --*/");
		callCenterConsole.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
		System.out.println("/*10.----click Verify PHN button --*/");
		callCenterConsole.clickVerifyPHNButton();
		Thread.sleep(2000);
		System.out.println("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		callCenterConsole.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*12.----click Next button --*/");
		callCenterConsole.clickNextButton();
		Thread.sleep(2000);
		System.out.println("/*13.'Enter email address --*/");
		String email = "test@qa.com";
		callCenterConsole.enterEmail(email);
		System.out.println("/*14.'Confirm email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		callCenterConsole.confirmEmail(email1);
		System.out.println("/*15.Click review details Button--*/");
		Thread.sleep(2000);
		callCenterConsole.clickReviewDetails();
		System.out.println("/*16.Click register Button on confirmation page--*/");
		Thread.sleep(2000);
		callCenterConsole.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);
		System.out.println("/*17.--toast success message - 'Success' --*/");
		callCenterConsole.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*18.----click on person Account Related Tab --*/");
		callCenterConsole.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);
		//System.out.println("/*18.----click on Eligibility button --*/");
		//callCenterConsole.clickOnEligibilityButton();
		//Thread.sleep(2000);
		//System.out.println("/*19----select vaccination option -> COVID_19_Vaccination --*/");
		//callCenterConsole.selectEligibilityOption();
		//Thread.sleep(2000);
		//System.out.println("/*20.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
		//callCenterConsole.successEligibilityMessageAppear();
		//Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*21----Go to Appointment Tab --*/");
		callCenterConsole.clickAppointmentTab();
		Thread.sleep(2000);
		System.out.println("/*22----click on reason for Visit for visit 'Covid-19 Vaccine' radiobutton --*/");
		callCenterConsole.clickOnReasonForVisit();
		Thread.sleep(2000);
		//System.out.println("/*23----click on reason Early Booking Reason --*/");
		//callCenterConsole.selectEarlyBookingReason();
		//Thread.sleep(2000);
		//System.out.println("/*23----click on 'More' search tab --*/");
		//callCenterConsole.clickOnMoreSearchTabs();
		//Thread.sleep(2000);
		System.out.println("/*24----select 'Search clinic name' tab --*/");
		callCenterConsole.selectSearchClinicNameTab();
		Thread.sleep(2000);
		System.out.println("/*25----search the Clinic --*/");
		callCenterConsole.searchClinicName();
		Thread.sleep(2000);
		System.out.println("/*26----click on Option Facility location  --*/");
		callCenterConsole.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		System.out.println("/*27----select Active booking appointment day  --*/");
		callCenterConsole.selectBookingAppointmentDay();
		Thread.sleep(2000);
		System.out.println("/*28----select the time slot  --*/");
		callCenterConsole.selectTimeSlotAppointment();
		Thread.sleep(2000);
		System.out.println("/*29----click Next button  --*/");
		callCenterConsole.clickOnNextButton();
		Thread.sleep(2000);
		System.out.println("/*30----click Verify Contact Information Checkbox  --*/");
		callCenterConsole.clickVerifyContactInformation();
		Thread.sleep(2000);
		System.out.println("/*31----click Confirm Appointment button  --*/");
		callCenterConsole.clickOnConfirmButton();
		Thread.sleep(2000);
		System.out.println("/*32----see 'Appointment Confirmed!' screen --*/");
		callCenterConsole.validateAppointmentConfirmedScreen();
		Thread.sleep(2000);
		System.out.println("/*33----Refresh page --*/");
		callCenterConsole.refreshBrowser();
		Thread.sleep(2000);
		System.out.println("/*34----Go to back to the Citizen Related Tab --*/");
		callCenterConsole.clickRelatedTab();
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		System.out.println("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		if (inClinicExperiencePage.displayIceApp()) {
			System.out.println("/*---- User already on ICE--*/");
		} else {
			System.out.println("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
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
