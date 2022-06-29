package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class Dose2_VaccineAdministration extends BaseTest {
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219918"; //C219918
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
		inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
		System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		if (!inClinicExperiencePage.userLampardFound()) {
			System.out.println("/*----User --> Hugues BCVaxLampard not found and return---*/");
		}
		while (inClinicExperiencePage.userLampardFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
			System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		}
	}
	
	@Test(priority = 2)
	public void Can_do_Dose2_Vaccine_Administration_as_Clinician_ICE_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "219918"; //C219918
		System.out.println("/*1.----Login as an Clinician to ICE --*/");
		InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICE();
		Thread.sleep(10000);
		System.out.println("/*2.----In Clinic Experience(ICE) page displayed --*/");
		inClinicExperience.verifyIsICEpageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*3.--- Navigate to In Clinic Experience App --*/");
		inClinicExperience.selectICEFromApp();
		Thread.sleep(5000);
		System.out.println("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		Thread.sleep(5000);
		System.out.println("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		Thread.sleep(2000);
		System.out.println("/*6.----- Enter current date for UserDefaults --*/");
		inClinicExperience.inputCurrentDateUserDefaults();
		Thread.sleep(2000);
		System.out.println("/*7.----- Click on register Tab --*/");
		Thread.sleep(2000);
		inClinicExperience.clickRegisterTab();
		System.out.println("/*8.----click Register button New Citizen -Hugues BCVaxLampard --*/");
		inClinicExperience.clickRegisterButton();
		Thread.sleep(2000);
		System.out.println("/*9.----Enter First Name Hugues--*/");
		String firstName = "Hugues";
		inClinicExperience.enterFirstName(firstName);
		Thread.sleep(2000);
		System.out.println("/*10.----Enter Last Name BCVaxLampard--*/");
		String lastName = "BCVaxLampard";
		inClinicExperience.enterLastName(lastName);
		Thread.sleep(2000);
		System.out.println("/*11.----Enter Date of birth--*/");
		String dateOfBirth = "Mar 03, 1904";
		inClinicExperience.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		System.out.println("/*12.----Enter Postal code--*/");
		String postalCode = "V1N3Q3";
		inClinicExperience.enterPostalCode(postalCode);
		Thread.sleep(2000);
		System.out.println("/*13.----Enter PHN--*/");
		String phnNumber = "9746171121";
		inClinicExperience.enterPNH(phnNumber);
		Thread.sleep(2000);
		System.out.println("/*14.----click on non-Indigenous person radiobutton --*/");
		inClinicExperience.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
		System.out.println("/*15.----click Verify PHN button --*/");
		inClinicExperience.clickVerifyPHNButton();
		Thread.sleep(2000);
		System.out.println("/*16.--Expecting to see the toast success message - 'PNH match successful' --*/");
		inClinicExperience.successMessage();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*17.----click Next button --*/");
		inClinicExperience.clickNextButton();
		Thread.sleep(2000);
		System.out.println("/*18.----'Enter email address --*/");
		String email = "test@qa.com";
		inClinicExperience.enterEmail(email);
		System.out.println("/*19.----'Confirm email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		inClinicExperience.confirmEmail(email1);
		System.out.println("/*20.---Click review details Button--*/");
		Thread.sleep(2000);
		inClinicExperience.clickReviewDetails();
		System.out.println("/*21.----Click register Button on confirmation page--*/");
		Thread.sleep(2000);
		inClinicExperience.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);
		System.out.println("/*22.--toast success message - 'Success' --*/");
		inClinicExperience.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*23.----click on person Account Related Tab --*/");
		inClinicExperience.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);
		//System.out.println("/*24.----click on Eligibility button --*/");
		//inClinicExperience.clickEligibilityButton();
		//Thread.sleep(2000);
		//System.out.println("/*25----select vaccination option -> COVID_19_Vaccination --*/");
		//inClinicExperience.selectCovid19option();
		//Thread.sleep(2000);
		//System.out.println("/*26.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
		//inClinicExperience.userIsEligibleSuccessMsg();
		//Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*27----Go to Appointment Tab --*/");
		inClinicExperience.navigateAppointmentSchedulingTab();
		Thread.sleep(2000);
		System.out.println("/*28----click on reason for visit 'Covid-19 Vaccine' radiobutton --*/");
		inClinicExperience.clickReasonForVisit();
		Thread.sleep(2000);
		System.out.println("/*28----click on reason Early Booking Reason - Travel --*/");
		inClinicExperience.selectEarlyBookingReason();
		Thread.sleep(2000);
		//System.out.println("/*29----click on 'More' search tab --*/");
		//inClinicExperience.clickOnMoreSearchTabs();
		//Thread.sleep(2000);
		System.out.println("/*30----select 'Search clinic name' tab --*/");
		inClinicExperience.selectSearchClinicNameTab();
		Thread.sleep(2000);
		System.out.println("/*31----search the Clinic Age 5-11 Only - Indigenous Clinic - Victoria Native Friendship Center--*/");
		inClinicExperience.searchClinicName();
		Thread.sleep(2000);
		System.out.println("/*32----click on Option Facility location  --*/");
		inClinicExperience.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		System.out.println("/*33----select Active booking appointment day  --*/");
		inClinicExperience.selectBookingAppointmentDay();
		Thread.sleep(2000);
		System.out.println("/*34----select the time slot  --*/");
		inClinicExperience.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		System.out.println("/*35----click Next button  --*/");
		inClinicExperience.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		System.out.println("/*36----click Verify Contact Info checkbox  --*/");
		inClinicExperience.clickVerifyContactInformation();
		Thread.sleep(2000);
		inClinicExperience.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		System.out.println("/*37----see 'Appointment Confirmed!' screen --*/");
		inClinicExperience.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		System.out.println("/*38----Refresh page --*/");
		inClinicExperience.refreshBrowser();
		Thread.sleep(2000);
		System.out.println("/*39----Go to back to the Citizen Related Tab --*/");
		inClinicExperience.clickRelatedTab();
		Thread.sleep(2000);
		System.out.println("/*40----click on In-clinic Experience button --*/");
		inClinicExperience.ClickGoToInClinicExperienceButton();
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);
		System.out.println("/*41----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
		Thread.sleep(5000);
		System.out.println("/*-- 42---Click confirm and Save Button --*/");
		inClinicExperience.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		System.out.println("/*-- 43---select Vaccine Agent picklist value -> COVID-19 mRNA --*/");
		inClinicExperience.selectVaccineAgent();
		Thread.sleep(3000);
		System.out.println("/*-- 44---Click Save Consent Button --*/");
		inClinicExperience.ClickSaveConsentButton();
		Thread.sleep(5000);
		System.out.println("/*-- 45---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		System.out.println("/*-- 46---the Home - Client Search showing up  --*/");
		inClinicExperience.validateHomePageShownUp();
		Thread.sleep(3000);
		
		
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
		inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
		System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		if (!inClinicExperiencePage.userLampardFound()) {
			System.out.println("/*----User --> Hugues BCVaxLampard not found and return---*/");
		}
		while (inClinicExperiencePage.userLampardFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
			System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		}
	}
	
}