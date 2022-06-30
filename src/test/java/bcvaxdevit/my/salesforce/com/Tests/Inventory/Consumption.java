package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Consumption extends BaseTest {
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
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
		inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
		log("/*----Search for Dacia is Successful ---*/");
		if (!inClinicExperiencePage.userDaciaFound()) {
			log("/*----User --> Dacia not found and return---*/");
		}
		while (inClinicExperiencePage.userDaciaFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
			log("/*----Search for Dacia is Successful ---*/");
		}
	}
	
	@Test(priority = 2)
	public void Validate_Consumption_as_an_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "222359"; //C219969->C222359
		log("/*-- 1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
		Thread.sleep(5000);
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
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 3. Close all open tabs --*/");
		if (inClinicExperiencePage.supplyLocDisplayed()) {
			log("/*-- 4. User is already on Supply loc--*/");
		} else {
			log("/*-- 4.1. Click Dropdown Menu --*/");
			inClinicExperiencePage.clickDropdownMenu();
			Thread.sleep(5000);
			log("/*-- 4.2. Navigate and Select Supply Locations --*/");
			inClinicExperiencePage.selectSupplyLocationFromDropdown();
			Thread.sleep(2000);
		}
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 5. Close all open tabs --*/");
		Thread.sleep(2000);
		log("/*-- 6. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		log("/*-- 7. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EL0203 (2022-06-29 04:07 p.m)' --*/");
		inClinicExperiencePage.selectSupplyContainer();
		Thread.sleep(2000);
		double remainingDoses_before = inClinicExperiencePage.getValueOfRemainingDoses();
		log("/*-- 8. remaining doses Before: -->" + remainingDoses_before);
		Thread.sleep(2000);
		double remainingQty_before = inClinicExperiencePage.getValueOfRemainingQty();
		log("/*-- 9. remaining Qty Before: -->" + remainingQty_before);
		Thread.sleep(2000);
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 10. Close all open tabs --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.selectICEFromApp();
		log("/*-- 11. Navigate to In Clinic Experience App --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickUserDefaultsTab();
		log("/*-- 11.1 Click on User Defaults Tab  --*/");
		Thread.sleep(2000);
		System.out.println("/*-- 11.2 Enter current date for UserDefaults --*/");
		inClinicExperiencePage.inputCurrentDateUserDefaults();
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterTab();
		log("/*-- 12. Click on register Tab --*/");
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 13. Close any open Tabs --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 14. Register New User --> Dacia --*/");
		String firstName = "Dacia";
		inClinicExperiencePage.enterFirstName(firstName);
		log("/*-- 15.----Enter First Name Dacia --*/");
		Thread.sleep(2000);
		String lastName = "Bcvaxdod";
		inClinicExperiencePage.enterLastName(lastName);
		log("/*-- 16.----Enter Last Name Bcvaxdod --*/");
		Thread.sleep(2000);
		String dateOfBirth = "May 19, 1904";//1904-05-19
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		log("/*-- 17.----Enter Date of birth --*/");
		Thread.sleep(2000);
		String postalCode = "V7N3K1";
		inClinicExperiencePage.enterPostalCode(postalCode);
		log("/*-- 18.----Enter Postal code --*/");
		Thread.sleep(2000);
		String phnNumber = "9746172456";
		inClinicExperiencePage.enterPNH(phnNumber);
		log("/*-- 19.----Enter PHN --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickNonIndigenousRadioButton();
		log("/*-- 20.----click on non-Indigenous person radiobutton --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickVerifyPHNButton();
		log("/*-- 21.----click Verify PHN button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.successMessage();
		log("/*-- 22.'PNH match successful' --*/");
		inClinicExperiencePage.clickNextButton();
		log("/*-- 23.'Click next button --*/");
		Thread.sleep(5000);
		String email = "test@qa.com";
		inClinicExperiencePage.enterEmail(email);
		log("/*-- 24.'Enter email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		inClinicExperiencePage.confirmEmail(email1);
		log("/*-- 25.'Confirm email address --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickReviewDetails();
		log("/*-- 26.Click review details Button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		log("/*-- 27.Click register Button on confirmation page --*/");
		Thread.sleep(5000);
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		log("/*-- 28.Navigate to Appointment Scheduling Tab --*/");
		Thread.sleep(2000);
		//log("/*-- 29.Click on reason for visit --*/");
		//inClinicExperiencePage.clickReasonForVisit();
		//Thread.sleep(2000);
		System.out.println("/*30----click on reason Early Booking Reason - Travel --*/");
		inClinicExperiencePage.selectEarlyBookingReason();
		Thread.sleep(2000);
		log("/*31----select 'Search clinic name' tab --*/");
		inClinicExperiencePage.clickToSearchClinic();
		Thread.sleep(2000);
		log("/*-- 32.Search for and select clinic Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		inClinicExperiencePage.SearchForClinic();
		Thread.sleep(2000);
		log("/*33----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*-- 34----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*-- 35---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		log("/*-- 36---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		log("/*37----click Verify Contact Info checkbox  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*-- 38---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		log("/*-- 39---Appointment Confirmed! Message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		log("/*-- 40---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*-- 41---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		Thread.sleep(5000);
		log("/*-- 42---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		log("/*-- 43---Click to select Agent --*/");
		inClinicExperiencePage.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 44--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		inClinicExperiencePage.SelectAgentValue();
		Thread.sleep(2000);
		//System.out.println("/*-- 41---select Vaccine Agent  COVID-19 mRNA --*/");
		//inClinicExperiencePage.selectVaccineAgent();
		log("/*-- 44---Click Save Consent Button --*/");
		inClinicExperiencePage.ClickSaveConsentButton();
		Thread.sleep(5000);
		log("/*-- 43---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*-- 47---the Home - Client Search supposed to showing up  --*/");
		inClinicExperiencePage.validateHomePageShownUp();
		Thread.sleep(3000);
		log("/*-- 44. Navigate to Health Connect - Supply Console --*/");
		inClinicExperiencePage.selectHealthConnectApp();
		Thread.sleep(2000);
		log("/*-- 45. Close any open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(2000);
		if (inClinicExperiencePage.supplyLocDisplayed()) {
			log("/*-- 46. User is already on Supply loc --*/");
		} else {
			log("/*-- 46.1. Click Dropdown Menu --*/");
			inClinicExperiencePage.clickDropdownMenu();
			Thread.sleep(5000);
			log("/*-- 46.2. Navigate and Select Supply Locations --*/");
			inClinicExperiencePage.selectSupplyLocationFromDropdown();
			Thread.sleep(2000);
		}
		log("/*-- 47. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		log("/*-- 48. Click and navigate to the supply container --> 'Pfizer mRNA BNT162b2 - EL0203' --*/");
		inClinicExperiencePage.selectSupplyContainer();
		Thread.sleep(2000);
		//////////Validation for Dosages and Qty After Consumption
		System.out.println("/*--49.----Validate Remaining Doses and Remaining Quantities values after Consuming --*/");
		double remainingDoses_after = inClinicExperiencePage.getValueOfRemainingDoses();
		log("/*-- 50. remaining doses After Consumption: -->" + remainingDoses_after);
		assertEquals(remainingDoses_after, remainingDoses_before + 1);
		Thread.sleep(2000);
		double remainingQty_after = inClinicExperiencePage.getValueOfRemainingQty();
		log("/*-- 51. remaining Qty After: -->" + remainingQty_after);
		assertEquals(remainingQty_after, (remainingDoses_before + 1)/5);
		Thread.sleep(2000);
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 52. Close all open tabs --*/");
		
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		log("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		log("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
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
		inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
		log("/*----Search for Dacia is Successful ---*/");
		if (!inClinicExperiencePage.userDaciaFound()) {
			log("/*----User --> Dacia not found and return---*/");
		}
		while (inClinicExperiencePage.userDaciaFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
			log("/*----Search for Dacia is Successful ---*/");
		}
		
	}
	
}