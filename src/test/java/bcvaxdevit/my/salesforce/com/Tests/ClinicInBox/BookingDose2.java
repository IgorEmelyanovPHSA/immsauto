package bcvaxdevit.my.salesforce.com.Tests.ClinicInBox;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {

	private String legalFirstName = "Alexandro";
	private String legalLastName = "BCVaxDa Costa";
	private String legalLastNameASCII = "BCVaxDa%20Costa";
	private String dateOfBirth = "May 06, 1977";
	private String postalCode = "V8W7P2";
	private String personalHealthNumber = "9746172069";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";

	//Not in use instead implemented API call to remove participant account, DO NOT DELETE for now August 13, 2022
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
		inClinicExperiencePage.SearchForCitizen("Alexandro Corry BCVaxDa Costa");
		System.out.println("/*----Search for Alexandro Corry BCVaxDa Costa is Successful ---*/");
		if (!inClinicExperiencePage.userCostaFound()) {
			System.out.println("/*----User --> Alexandro Corry BCVaxDa Costa not found and return---*/");
		}
		while (inClinicExperiencePage.userCostaFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Alexandro Corry BCVaxDa Costa");
			System.out.println("/*----Search for Alexandro Corry BCVaxDa Costa is Successful ---*/");
		}
	}
	
	@Test
	public void Can_Book_Dose2_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws Exception {
		TestcaseID = "222368";
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);
		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIBWithParameters();
		Thread.sleep(10000);
		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		if (clinicInBox.displayCIBApp()) {
			log("/*---- User already on CIB Page--*/");
			Thread.sleep(2000);
		} else {
			log("/*---- Navigate to CIB App --*/");
			clinicInBox.selectCIBApp();
			Thread.sleep(2000);
		}
		//clinicInBox.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);
		log("/*4.----click Register New Citizen --*/");
		clinicInBox.clickRegisterButton();
		Thread.sleep(2000);
		log("/*5.----Enter First Name: " +legalFirstName +"--*/");
		clinicInBox.enterFirstName(legalFirstName);
		Thread.sleep(2000);
		log("/*6.----Enter Last Name: " +legalLastName +"--*/");
		clinicInBox.enterLastName(legalLastName);
		Thread.sleep(2000);
		log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
		clinicInBox.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		log("/*7.----Enter Postal code: " +postalCode +"--*/");
		clinicInBox.enterPostalCode(postalCode);
		Thread.sleep(2000);
		log("/*8.----Enter PHN: " +personalHealthNumber +"--*/");
		clinicInBox.enterPNH(personalHealthNumber);
		Thread.sleep(2000);
		log("/*9.----click on non-Indigenous person radiobutton --*/");
		clinicInBox.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
		log("/*10.----click Verify PHN button --*/");
		clinicInBox.clickVerifyPHNButton();
		Thread.sleep(2000);
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		clinicInBox.successMessageAppear();
		Thread.sleep(5000);
		log("/*12.----click Next button --*/");
		clinicInBox.clickNextButton();
		Thread.sleep(2000);
		log("/*13.'Enter email address: " +email +"--*/");
		clinicInBox.enterEmail(email);
		log("/*14.'Confirm email address: " +email +"--*/");
		clinicInBox.confirmEmail(email);
		log("/*15.Click review details Button--*/");
		Thread.sleep(2000);
		clinicInBox.clickReviewDetails();
		log("/*16.Click register Button on confirmation page--*/");
		Thread.sleep(2000);
		clinicInBox.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);
		log("/*17.--toast success message - 'Success' --*/");
		clinicInBox.successRegisteredMessageAppear();
		Thread.sleep(5000);
		log("/*18.----click on person Account Related Tab --*/");
		clinicInBox.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);
		log("/*19----Go to Appointment Tab --*/");
		clinicInBox.clickAppointmentTab();
		Thread.sleep(2000);
		//System.out.println("/*20----click on reason for visit 'Covid-19 Vaccine' radiobutton --*/");
		//clinicInBox.clickOnReasonForVisit();
		//Thread.sleep(2000);
		System.out.println("/*20.----click on reason Early Booking Reason - Travel --*/");
		clinicInBox.selectEarlyBookingReason();
		Thread.sleep(2000);
		String vaccineToSelect = "Covid19Vaccine";
		log("/*20.A---Select vaccination type: " + vaccineToSelect + "--*/");
		clinicInBox.selectOneOption(vaccineToSelect);
		log("/*21----select 'Search clinic name' tab --*/");
		clinicInBox.selectSearchByClinicNameTab();
		Thread.sleep(2000);
		String clinicNameToSearch = "All Ages - Atlin Health Centre";
		log("/*22----search the Clinic " +clinicNameToSearch +" --*/");
		clinicInBox.searchClinicName(clinicNameToSearch);
		Thread.sleep(2000);
		log("/*23----click on Option Facility location  --*/");
		clinicInBox.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*24----select Active booking appointment day  --*/");
		clinicInBox.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*25----select the time slot  --*/");
		clinicInBox.selectTimeSlotAppointment();
		Thread.sleep(2000);
		log("/*26----click Next button  --*/");
		clinicInBox.clickOnNextButton();
		Thread.sleep(2000);
		log("/*27----click Verify Contact Information Checkbox  --*/");
		clinicInBox.clickVerifyContactInformationNew();
		Thread.sleep(2000);
		log("/*28----click Confirm Appointment button  --*/");
		clinicInBox.clickOnConfirmButton();
		Thread.sleep(2000);
		log("/*29----see 'Appointment Confirmed!' screen --*/");
		clinicInBox.validateAppointmentConfirmedScreen();
		Thread.sleep(2000);
		log("/*30----Refresh page --*/");
		clinicInBox.refreshBrowser();
		Thread.sleep(2000);
		log("/*31----Go to back to the Citizen Related Tab --*/");
		clinicInBox.clickRelatedTab();
		Thread.sleep(2000);
		log("/*32----click on In-clinic Experience button --*/");
		InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);
		log("/*33----In-clinic Experience ->Vaccine Admin page appears up --*/");
		InClinicExperience.validateVaccineAdminPageOpen();
		Thread.sleep(5000);
		log("/*34.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);
	}

	//Not in use instead implemented API call to remove participant account, DO NOT DELETE for now August 13, 2022
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
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
		inClinicExperiencePage.SearchForCitizen("Alexandro Corry BCVaxDa Costa");
		System.out.println("/*----Search for Alexandro Corry BCVaxDa Costa is Successful ---*/");
		if (!inClinicExperiencePage.userCostaFound()) {
			System.out.println("/*----User --> Alexandro Corry BCVaxDa Costa not found and return---*/");
		}
		while (inClinicExperiencePage.userCostaFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Alexandro Corry BCVaxDa Costa");
			System.out.println("/*----Search for Alexandro Corry BCVaxDa Costa is Successful ---*/");
		}
	}
	
	
}
