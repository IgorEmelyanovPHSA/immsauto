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
		log("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		log("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		Thread.sleep(10000);
		if (inClinicExperiencePage.displayIceApp()) {
			log("/*---- User already on ICE--*/");
		} else {
			log("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
		/*----Go to Register Tab ---*/
		log("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		log("/*----Search for Participant account ---*/");
		inClinicExperiencePage.SearchForCitizen("Jodie Morten BCVaxCluff");
		log("/*----Search for Jodie is Successful ---*/");
		if (!inClinicExperiencePage.userJodieFound()) {
			log("/*----User --> Jodie not found and return---*/");
		}
		while (inClinicExperiencePage.userJodieFound()) {
			log("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(2000);
			if (!inClinicExperiencePage.selectImmsRecord()) {
				log("/*----No Imms Record found and return---*/");
			} else {
				log("/*---- User navigated to Imms record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteImmsRecord();
				log("/*---- Imms record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
			Thread.sleep(5000);
			if (!inClinicExperiencePage.selectRERNRecord()) {
				log("/*----No RERN Record found and return---*/");
			} else {
				log("/*---- User navigated to RERN record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteRERNRecord();
				log("/*---- RERN record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			log("/*---- Navigated to Person Account related tab ---*/");
			inClinicExperiencePage.deletePersonAccount();
			log("/*---- Person Account deleted Successfully ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRegisterTab();
			Thread.sleep(5000);
			inClinicExperiencePage.closeOpenTabs();
			log("/*---- Close the deleted Person Account ---*/");
			Thread.sleep(2000);
			log("/*----Re Searching for the Participant account ---*/");
			inClinicExperiencePage.SearchForCitizen("Jodie BCVaxCluff");
			log("/*----Search for Jodie is Successful ---*/");
		}
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
		log("/*-- 2. Navigate to In Clinic Experience App --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickUserDefaultsTab();
		log("/*-- 3. Click on User Defaults Tab  --*/");
		Thread.sleep(2000);
		log("/*-- 4. Enter current date for UserDefaults --*/");
		inClinicExperiencePage.inputPreviousDateUserDefaults();
		Thread.sleep(2000);
		System.out.println("/*-- 14.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		Thread.sleep(2000);
		log("/*-- 5. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(2000);//
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 6. Close any open Tabs --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 7. Register New User --> Jodie --*/");
		String firstName = "Jodie";
		inClinicExperiencePage.enterFirstName(firstName);
		log("/*-- 8.----Enter First Name Jodie --*/");
		Thread.sleep(2000);
		String lastName = "BCVaxCluff";
		inClinicExperiencePage.enterLastName(lastName);
		log("/*-- 9.----Enter Last Name BCVaxCluff --*/");
		Thread.sleep(2000);
		String dateOfBirth = "May 13, 2006";//2006-05-13
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		log("/*-- 10.----Enter Date of birth --*/");
		Thread.sleep(2000);
		String postalCode = "V7N3K1";
		inClinicExperiencePage.enterPostalCode(postalCode);
		log("/*-- 11.----Enter Postal code --*/");
		Thread.sleep(2000);
		String phnNumber = "9746172488";
		inClinicExperiencePage.enterPNH(phnNumber);
		log("/*-- 12.----Enter PHN --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickNonIndigenousRadioButton();
		log("/*-- 13.----click on non-Indigenous person radiobutton --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickVerifyPHNButton();
		log("/*-- 14.----click Verify PHN button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.successMessage();
		log("/*-- 15.'PNH match successful' --*/");
		inClinicExperiencePage.clickNextButton();
		log("/*-- 16.'Click next button --*/");
		Thread.sleep(5000);
		String email = "test@qa.com";
		inClinicExperiencePage.enterEmail(email);
		log("/*-- 17.'Enter email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		inClinicExperiencePage.confirmEmail(email1);
		log("/*-- 18.'Confirm email address --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickReviewDetails();
		log("/*-- 19.Click review details Button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		log("/*-- 20.Click register Button on confirmation page --*/");
		Thread.sleep(5000);
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		log("/*-- 21.Navigate to Appointment Scheduling Tab --*/");
		Thread.sleep(2000);//
		log("/*--22.----select 'Search clinic name' tab --*/");
		inClinicExperiencePage.clickToSearchClinic();
		Thread.sleep(2000);
		log("/*-- 23.Search for and select clinic Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		inClinicExperiencePage.SearchForClinic();
		Thread.sleep(2000);
		log("/*--24.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*--25.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*--26.---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		log("/*--27.---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		log("/*28.----click Verify Contact Info checkbox  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*-- 29.---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		log("/*-- 30.---Appointment Confirmed! Message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		log("/*-- 31.---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*-- 32.---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		Thread.sleep(5000);
		log("/*-- 33.--- Rebook Appointment --*/");
		inClinicExperiencePage.ClickRebookAppointment();
		Thread.sleep(5000);
		log("/*-- 34---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		log("/*-- 35---Click to select Agent --*/");
		inClinicExperiencePage.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 36--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		inClinicExperiencePage.SelectAgentValue();
		Thread.sleep(2000);
		log("/*-- 37---Click Save Consent Button --*/");
		inClinicExperiencePage.ClickSaveConsentButton();
		Thread.sleep(5000);
		log("/*-- 38---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		System.out.println("/*39.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperiencePage.ClickModalConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*-- 40---the Home - Client Search supposed to showing up  --*/");
		inClinicExperiencePage.validateHomePageShownUp();
		Thread.sleep(3000);
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_DupsJodie_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		log("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		log("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		Thread.sleep(10000);
		if (inClinicExperiencePage.displayIceApp()) {
			log("/*---- User already on ICE--*/");
		} else {
			log("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
		/*----Go to Register Tab ---*/
		log("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		log("/*----Search for Participant account ---*/");
		inClinicExperiencePage.SearchForCitizen("Jodie BCVaxCluff");
		log("/*----Search for Jodie is Successful ---*/");
		if (!inClinicExperiencePage.userJodieFound()) {
			log("/*----User --> Jodie not found and return---*/");
		}
		while (inClinicExperiencePage.userJodieFound()) {
			log("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(2000);
			if (!inClinicExperiencePage.selectImmsRecord()) {
				log("/*----No Imms Record found and return---*/");
			} else {
				log("/*---- User navigated to Imms record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteImmsRecord();
				log("/*---- Imms record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
			Thread.sleep(5000);
			if (!inClinicExperiencePage.selectRERNRecord()) {
				log("/*----No RERN Record found and return---*/");
			} else {
				log("/*---- User navigated to RERN record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteRERNRecord();
				log("/*---- RERN record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			log("/*---- Navigated to Person Account related tab ---*/");
			inClinicExperiencePage.deletePersonAccount();
			log("/*---- Person Account deleted Successfully ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRegisterTab();
			Thread.sleep(5000);
			inClinicExperiencePage.closeOpenTabs();
			log("/*---- Close the deleted Person Account ---*/");
			Thread.sleep(2000);
			log("/*----Re Searching for the Participant account ---*/");
			inClinicExperiencePage.SearchForCitizen("Jodie BCVaxCluff");
			log("/*----Search for Jodie is Successful ---*/");
		}
	}
}

