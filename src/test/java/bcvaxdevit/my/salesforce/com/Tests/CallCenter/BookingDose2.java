package bcvaxdevit.my.salesforce.com.Tests.CallCenter;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.CallCenterConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {
	
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
		inClinicExperiencePage.SearchForCitizen("Gill Ashely BCVaxOrigan");
		log("/*----Search for Gill is Successful ---*/");
		if (!inClinicExperiencePage.userGillFound()) {
			log("/*----User --> User Gill not found and return---*/");
		}
		while (inClinicExperiencePage.userGillFound()) {
			log("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(2000);
			while (inClinicExperiencePage.selectImmsRecord()) {
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
			inClinicExperiencePage.SearchForCitizen("Gill Ashely BCVaxOrigan");
			log("/*----Search for Gill is Successful ---*/");
		}
	}
	
	@Test(priority = 2)
	public void Can_Book_Dose2_Appointment_as_Call_Center_Agent_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "222323"; //C222323
		log("/*1.----Login as an Cal Center Agent to the Call Center Console --*/");
		CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
		Thread.sleep(10000);
		log("/*2.----CallCenter Console page displayed --*/");
		callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		callCenterConsole.closeAllTabs();
		Thread.sleep(5000);
		log("/*4.----click Register New Citizen -Gill BCVaxOrigan --*/");
		callCenterConsole.clickRegisterButton();
		Thread.sleep(2000);
		log("/*5.----Enter First Name Gill--*/");
		String firstName = "Gill";
		callCenterConsole.enterFirstName(firstName);
		Thread.sleep(2000);
		log("/*6.----Enter Last Name BCVaxOrigan--*/");
		String lastName = "BCVaxOrigan";
		callCenterConsole.enterLastName(lastName);
		Thread.sleep(2000);
		log("/*6.----Enter Date of birth--*/");
		String dateOfBirth = "Feb 14, 1915";
		callCenterConsole.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		log("/*7.----Enter Postal code--*/");
		String postalCode = "V3L5L2";
		callCenterConsole.enterPostalCode(postalCode);
		Thread.sleep(2000);
		log("/*8.----Enter PHN--*/");
		String phnNumber = "9746172463";
		callCenterConsole.enterPNH(phnNumber);
		Thread.sleep(2000);
		log("/*9.----click on non-Indigenous person radiobutton --*/");
		callCenterConsole.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
		log("/*10.----click Verify PHN button --*/");
		callCenterConsole.clickVerifyPHNButton();
		Thread.sleep(2000);
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		callCenterConsole.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*12.----click Next button --*/");
		callCenterConsole.clickNextButton();
		Thread.sleep(2000);
		log("/*13.'Enter email address --*/");
		String email = "test@qa.com";
		callCenterConsole.enterEmail(email);
		Thread.sleep(2000);
		log("/*14.'Confirm email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		callCenterConsole.confirmEmail(email1);
		log("/*15.Click review details Button--*/");
		Thread.sleep(2000);
		callCenterConsole.clickReviewDetails();
		log("/*16.Click register Button on confirmation page--*/");
		Thread.sleep(2000);
		callCenterConsole.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);
		log("/*17.--toast success message - 'Success' --*/");
		callCenterConsole.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*18.----click refresh forecast button to showcase 2nd dose --*/");
		callCenterConsole.clickRefreshForecastButton();
		Thread.sleep(10000);//wait for refresh
		log("/*21----Go to Appointment Tab --*/");
		callCenterConsole.clickAppointmentTab();
		Thread.sleep(2000);
		System.out.println("/*22.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		callCenterConsole.clickOnVaccinationCheckbox();
		Thread.sleep(2000);
		log("/*24----select 'Search clinic name' tab --*/");
		callCenterConsole.selectSearchClinicNameTab();
		Thread.sleep(2000);
		log("/*25----search the Clinic --*/");
		callCenterConsole.searchClinicName();
		Thread.sleep(2000);
		log("/*26----click on Option Facility location  --*/");
		callCenterConsole.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*27----select Active booking appointment day  --*/");
		callCenterConsole.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*28----select the time slot  --*/");
		callCenterConsole.selectTimeSlotAppointment();
		Thread.sleep(2000);
		log("/*29----click Next button  --*/");
		callCenterConsole.clickOnNextButton();
		Thread.sleep(2000);
		log("/*30----click Verify Contact Information Checkbox  --*/");
		callCenterConsole.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*31----click Confirm Appointment button  --*/");
		callCenterConsole.clickOnConfirmButton();
		Thread.sleep(2000);
		log("/*32----see 'Appointment Confirmed!' screen --*/");
		callCenterConsole.validateAppointmentConfirmedScreen();
		Thread.sleep(2000);
		log("/*33----Refresh page --*/");
		callCenterConsole.refreshBrowser();
		Thread.sleep(2000);
		log("/*34----Go to back to the Citizen Related Tab --*/");
		callCenterConsole.clickRelatedTab();
		Thread.sleep(5000);
		log("/*34----Navigate to Appointment record --*/");
		callCenterConsole.selectAppointmentRecord();
		Thread.sleep(2000);
/*		Double dosage = callCenterConsole.getValueOfDosesNumber();
		log("/*-- 49. remaining doses are: -->" + dosage);
		Double expectedDosage = Double.valueOf("2");
		assertEquals(dosage, expectedDosage);*/
	}
	
	@Test(priority = 3)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
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
		inClinicExperiencePage.SearchForCitizen("Gill Ashely BCVaxOrigan");
		log("/*----Search for Gill is Successful ---*/");
		if (!inClinicExperiencePage.userGillFound()) {
			log("/*----User --> User Gill not found and return---*/");
		}
		while (inClinicExperiencePage.userGillFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Gill Ashely BCVaxOrigan");
			log("/*----Search for Gill is Successful ---*/");
		}
	}
	
	
}
