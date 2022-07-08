package bcvaxuat.my.salesforce.com.Tests.InClinicExperience;

import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class Dose1_VaccineAdministration extends BaseTest {
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		log("Searching and Removing Citizen Duplicates BCVAXUAT");
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
		inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
		log("/*----Search for Ludovika is Successful ---*/");
		if (!inClinicExperiencePage.userFound()) {
			log("/*----User --> Ludovika not found and return---*/");
		}
		while (inClinicExperiencePage.userFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
			log("/*----Search for Ludovika is Successful ---*/");
		}
	}
	
	@Test(priority = 2)
	public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE_BCVAXUAT() throws InterruptedException {
		TestcaseID = "153420"; //C153419
		log("/*1.----Login as an Clinician to ICE --*/");
		InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICE();
		Thread.sleep(2000);
		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		inClinicExperience.verifyIsICEpageDisplayed();
		Thread.sleep(5000);
		log("/*3.--- Navigate to In Clinic Experience App --*/");
		inClinicExperience.selectICEFromApp();
		Thread.sleep(5000);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		Thread.sleep(5000);
		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		Thread.sleep(2000);
		log("/*6.----- Enter current date for UserDefaults --*/");
		inClinicExperience.inputCurrentDateUserDefaults();
		Thread.sleep(2000);
		log("/*7.----- Click on register Tab --*/");
		Thread.sleep(2000);
		inClinicExperience.clickRegisterTab();
		log("/*8.----click Register button New Citizen -Ludovika --*/");
		inClinicExperience.clickRegisterButton();
		Thread.sleep(2000);
		log("/*9.----Enter First Name Ludovika--*/");
		String firstName = "Ludovika";
		inClinicExperience.enterFirstName(firstName);
		Thread.sleep(2000);
		log("/*10.----Enter Last Name BCVaxLimeburn--*/");
		String lastName = "BCVaxLimeburn";
		inClinicExperience.enterLastName(lastName);
		Thread.sleep(2000);
		log("/*11.----Enter Date of birth--*/");
		String dateOfBirth = "Sep 21, 1923";
		inClinicExperience.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		log("/*12.----Enter Postal code--*/");
		String postalCode = "V3L5L2";
		inClinicExperience.enterPostalCode(postalCode);
		Thread.sleep(2000);
		log("/*13.----Enter PHN--*/");
		String phnNumber = "9746170911";
		inClinicExperience.enterPNH(phnNumber);
		Thread.sleep(2000);
		log("/*14.----click on non-Indigenous person radiobutton --*/");
		inClinicExperience.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
		log("/*15.----click Verify PHN button --*/");
		inClinicExperience.clickVerifyPHNButton();
		Thread.sleep(2000);
		log("/*16.--Expecting to see the toast success message - 'PNH match successful' --*/");
		inClinicExperience.successMessage();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*17.----click Next button --*/");
		inClinicExperience.clickNextButton();
		Thread.sleep(2000);
		log("/*18.----'Enter email address --*/");
		String email = "test@qa.com";
		inClinicExperience.enterEmail(email);
		log("/*19.----'Confirm email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		inClinicExperience.confirmEmail(email1);
		log("/*20.---Click review details Button--*/");
		Thread.sleep(2000);
		inClinicExperience.clickReviewDetails();
		log("/*21.----Click register Button on confirmation page--*/");
		Thread.sleep(2000);
		inClinicExperience.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);
		log("/*22.--toast success message - 'Success' --*/");
		inClinicExperience.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*23.----click on person Account Related Tab --*/");
		inClinicExperience.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);
		//log("/*24.----click on Eligibility button --*/");
		//inClinicExperience.clickEligibilityButton();
		//Thread.sleep(2000);
		//log("/*25----select vaccination option -> COVID_19_Vaccination --*/");
		//inClinicExperience.selectCovid19option();
		//Thread.sleep(2000);
		//log("/*26.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
		//inClinicExperience.userIsEligibleSuccessMsg();
		//Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*27----Go to Appointment Tab --*/");
		inClinicExperience.navigateAppointmentSchedulingTab();
		Thread.sleep(2000);
		log("/*28----click on reason for visit 'Covid-19 Vaccine' radiobutton --*/");
		inClinicExperience.clickReasonForVisit();
		Thread.sleep(2000);
		//log("/*29----click on 'More' search tab --*/");
		//inClinicExperience.clickOnMoreSearchTabs();
		//Thread.sleep(2000);
		log("/*30----select 'Search clinic name' tab --*/");
		inClinicExperience.selectSearchClinicNameTab();
		Thread.sleep(2000);
		log("/*31----search the Clinic --*/");
		inClinicExperience.searchClinicName();
		Thread.sleep(2000);
		log("/*32----click on Option Facility location  --*/");
		inClinicExperience.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*33----select Active booking appointment day  --*/");
		inClinicExperience.selectAppointment();
		Thread.sleep(2000);
		log("/*34----select the time slot  --*/");
		inClinicExperience.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		log("/*35----click Next button  --*/");
		inClinicExperience.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		log("/*36----click Verify Contact Information Checkbox  --*/");
		inClinicExperience.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*37----click Confirm Appointment button  --*/");
		inClinicExperience.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		log("/*38----see 'Appointment Confirmed!' screen --*/");
		inClinicExperience.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		log("/*39----Refresh page --*/");
		inClinicExperience.refreshBrowser();
		Thread.sleep(2000);
		log("/*40----Go to back to the Citizen Related Tab --*/");
		inClinicExperience.clickRelatedTab();
		Thread.sleep(2000);
		log("/*41----click on In-clinic Experience button --*/");
		inClinicExperience.ClickGoToInClinicExperienceButton();
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);
		log("/*42----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
		Thread.sleep(5000);
		log("/*-- 43---Click confirm and Save Button --*/");
		inClinicExperience.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		log("/*-- 44---select Vaccine Agent  COVID-19 mRNA --*/");
		inClinicExperience.selectVaccineAgent();
		Thread.sleep(3000);
		log("/*-- 45---Click Save Consent Button --*/");
		inClinicExperience.ClickSaveConsentButton();
		Thread.sleep(5000);
		log("/*-- 46---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*-- 47---the Home - Client Search showing up  --*/");
		inClinicExperience.validateHomePageShownUp();
		Thread.sleep(3000);
		
		
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		log("Searching and Removing Citizen Duplicates BCVAXUAT");
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
		inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
		log("/*----Search for Ludovika is Successful ---*/");
		if (!inClinicExperiencePage.userFound()) {
			log("/*----User --> Ludovika not found and return---*/");
		}
		while (inClinicExperiencePage.userFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
			log("/*----Search for Ludovika is Successful ---*/");
		}
	}
	
	
}