package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class UserArrivingWrongClinicRebookAppointment extends BaseTest {
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_DupsJodie_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
	}
	
	@Test(priority = 2)
	public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "219910"; //C219910
		log("/*-- 1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
		Thread.sleep(5000);
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(5000);
		inClinicExperiencePage.selectICEFromApp();
		log("/*-- 11. Navigate to In Clinic Experience App --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickUserDefaultsTab();
		log("/*-- 12. Click on User Defaults Tab  --*/");
		Thread.sleep(2000);
		log("/*-- 13. Enter current date for UserDefaults --*/");
		inClinicExperiencePage.inputPreviousDateUserDefaults();
		Thread.sleep(2000);
		System.out.println("/*-- 14.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		Thread.sleep(2000);
		log("/*-- 15. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(2000);
//		System.out.println("/*-- 16.----- Click on Save changes defaults button Modal window --*/");
//		inClinicExperiencePage.clickSaveModalDefaultsButton();
//		Thread.sleep(2000);
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 17. Close any open Tabs --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 18. Register New User --> Jodie --*/");
		String firstName = "Jodie";
		inClinicExperiencePage.enterFirstName(firstName);
		log("/*-- 19.----Enter First Name Jodie --*/");
		Thread.sleep(2000);
		String lastName = "BCVaxCluff";
		inClinicExperiencePage.enterLastName(lastName);
		log("/*-- 20.----Enter Last Name BCVaxCluff --*/");
		Thread.sleep(2000);
		String dateOfBirth = "May 13, 2006";//2006-05-13
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		log("/*-- 21.----Enter Date of birth --*/");
		Thread.sleep(2000);
		String postalCode = "V7N3K1";
		inClinicExperiencePage.enterPostalCode(postalCode);
		log("/*-- 22.----Enter Postal code --*/");
		Thread.sleep(2000);
		String phnNumber = "9746172488";
		inClinicExperiencePage.enterPNH(phnNumber);
		log("/*-- 23.----Enter PHN --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickNonIndigenousRadioButton();
		log("/*-- 24.----click on non-Indigenous person radiobutton --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickVerifyPHNButton();
		log("/*-- 25.----click Verify PHN button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.successMessage();
		log("/*-- 26.'PNH match successful' --*/");
		inClinicExperiencePage.clickNextButton();
		log("/*-- 27.'Click next button --*/");
		Thread.sleep(5000);
		String email = "test@qa.com";
		inClinicExperiencePage.enterEmail(email);
		log("/*-- 28.'Enter email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		inClinicExperiencePage.confirmEmail(email1);
		log("/*-- 29.'Confirm email address --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickReviewDetails();
		log("/*-- 30.Click review details Button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		log("/*-- 31.Click register Button on confirmation page --*/");
		Thread.sleep(5000);
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		log("/*-- 32.Navigate to Appointment Scheduling Tab --*/");
		Thread.sleep(2000);
		System.out.println("/*--33.----click on reason Early Booking Reason - Travel --*/");
		inClinicExperiencePage.selectEarlyBookingReason();
		Thread.sleep(2000);
		log("/*--34.----select 'Search clinic name' tab --*/");
		//inClinicExperiencePage.clickToSearchClinic();
		Thread.sleep(2000);
		log("/*-- 35.Search for and select clinic Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		inClinicExperiencePage.SearchForClinic();
		Thread.sleep(2000);
		log("/*--36.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*--37.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*--38.---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		log("/*--39.---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		log("/*40.----click Verify Contact Info checkbox  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*-- 41.---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		log("/*-- 42.---Appointment Confirmed! Message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		log("/*-- 43.---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*-- 44.---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		Thread.sleep(5000);
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_DupsJodie_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
	}
}
