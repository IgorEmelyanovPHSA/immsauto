package bcvaxuat.my.salesforce.com.Tests.Portal;

import Utilities.ApiQueries;
import Utilities.TestListener;
import bcvaxuat.my.salesforce.com.Pages.BookAnAppointmentPage;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxuat.my.salesforce.com.Pages.RegisterToGetVaccinatedPage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Utilities.ApiQueries.*;

@Listeners({TestListener.class})
public class Dose1_CitizenBookingAppointment extends BaseTest {

	//Test data
//		private String legalFirstName = "Ludovika";
//		private String legalLastName = "BCVaxLimeburn";
//		private String dateOfBirth = "Sep 21, 1923";
//		private String postalCode = "V3L5L2";
//		private String personalHealthNumber = "9746170911";
//		private boolean isIndigenous = false;
//		private String email = "test2@phsa.ca";
//		private String phoneNumber = "6041234568";

	private String legalFirstName = "Anne-marie";
	private String legalLastName = "BCVaxJacketts";
	private String legalMiddleName = "Elissa";
	private String dateOfBirth = "Aug 16, 1903";
	private String postalCode = "V3B0J5";
	private String personalHealthNumber = "9746173988";
	private boolean isIndigenous = false;
	private String email = "accounttodelete@phsa.ca";
	private String phoneNumber = "6041234568";

	//Not in use instead implemented API call to remove participant account
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865 //153419?
		log("Searching and Removing Citizen Duplicates ");
		log("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginAsPrecoconditionWithParameters();
		if (inClinicExperiencePage.displayIceApp()) {
			System.out.println("/*---- User already on ICE--*/");
		} else {
			System.out.println("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
		/*----Go to Register Tab ---*/
		log("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		log("/*----Search for Participant account ---*/");
		inClinicExperiencePage.SearchForCitizen(legalFirstName + " " + legalLastName);
		log("/*----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		if (!inClinicExperiencePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName)) {
			log("/*----User --> " + legalFirstName + " " + legalLastName + " not found and return---*/");
		}
		while (inClinicExperiencePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName)) {
			log("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(7000);
			if (!inClinicExperiencePage.selectImmsRecord()) {
				log("/*----No Imms Record found and return---*/");
			} else {
				log("/*---- User navigated to Imms record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteImmsRecord();
				System.out.println("/*---- Imms record deleted Successfully ---*/");
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
				Thread.sleep(5000);
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
			inClinicExperiencePage.SearchForCitizen(legalFirstName + " " + legalLastName);
			log("/*----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		}
	}

	@Test
	public void citizenPortalFlowDoseOne() throws Exception {
		TestcaseID = "153419"; //C153419
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

		log("/*1.---Open citizen portal and click btn Register Now--*/");
		RegisterToGetVaccinatedPage registerToGetVaccinatedPage = loginPage.openRegisterToGetVaccinatedPage();
		registerToGetVaccinatedPage.clickBtnRegisterNow();

		log("/*2.---Fill all registration information and click btn continue--*/");
		registerToGetVaccinatedPage.fillMandatoryFieldsOnRegistrationSection(legalFirstName, legalLastName, legalMiddleName, dateOfBirth, postalCode,
				personalHealthNumber, isIndigenous);

		log("/*3.---Fill email and phone number on contact information section and click btn continue--*/");
		registerToGetVaccinatedPage.fillMandatoryFieldsOnContactInformationSection(email, phoneNumber);

		log("/*4.---Check checkbox certify and click btn submit--*/");
		registerToGetVaccinatedPage.certifyAndSubmit();

		log("/*5.---Check for registration successful message and save conformation number--*/");
		String conformationNumberText = registerToGetVaccinatedPage.registrationSuccessfulPageDisplayed();

		log("/*6.---Login as an Clinician to ICE--*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginAsClinicianICEWithParameters();
		Thread.sleep(5000);

		log("/*7.---Search for Participant account by conformation number " + conformationNumberText + "--*/");
		inClinicExperiencePage.SearchForCitizen(conformationNumberText);

		if (!inClinicExperiencePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName)) {
			log("/*---User --> " + legalFirstName + " " + legalLastName + " not found!!!--*/");
		} else {
			log("/*---User --> " + legalFirstName + " " + legalLastName + " present on the page--*/");
		}

		log("/*8.---Get unique link using Sales Force query over API--*/");
		String uniqueLink = queryToGetUniqueLink(conformationNumberText);

		log("/*9.---Open book an appointment portal from unique link--*/");
		BookAnAppointmentPage bookAnAppointmentPage = loginPage.openBookAnAppointmentPage(uniqueLink);
		bookAnAppointmentPage.bookAnAppointmentPageDisplayed();

		//Unique registration code validation
		String registrationConfirmationNumber = bookAnAppointmentPage.getRegistrationConfirmationNumber();
		log("Compering registration confirmation number from registration page: " + conformationNumberText
				+ " vs registration confirmation number from book an appointment page " + registrationConfirmationNumber);
		Assert.assertTrue(conformationNumberText.equalsIgnoreCase(registrationConfirmationNumber));

		log("/*10.---Open book an appointment portal from unique link--*/");
		bookAnAppointmentPage.enterPhnNumberAndClickBtnBookAppointment(personalHealthNumber);

		log("/*11.---Schedule vaccination page is displayed--*/");
		bookAnAppointmentPage.scheduleVaccinationAppointmentPageDisplayed();

		String vaccineToSelect = "Covid19Vaccine";
		log("/*12.---Select vaccination type: " + vaccineToSelect + "--*/");
		bookAnAppointmentPage.selectOneOption(vaccineToSelect);

		String clinicName = "Age 12 and Above - Abbotsford - Abby Pharmacy";
		log("/*13.---Go to tab search by clinic and select clinic " + clinicName + "--*/");
		bookAnAppointmentPage.searchByClinicName(clinicName);

		log("/*14.---Select date and time for appointment and click btn Next--*/");
		bookAnAppointmentPage.selectDateAndTimeForAppointmentAndClickBtnNext();

		log("/*15---Click verify contact information checkbox--*/");
		bookAnAppointmentPage.clickCheckBoxVerifyContactInformationAndConfirmAppointment();

		log("/*16---Verify appointment conformation message is displayed--*/");
		bookAnAppointmentPage.appointmentConfirmationPageDisplayed();
		}
	}
		/*
		Go through the citizen flow as someone who does not have a PHN number
		Go through the citizen flow as someone who has a PHN number
		Go through the citizen flow as someone who put a wrong date of birth by mistake/wrong name
		Go through the citizen flow where Booking capacity is full on a particular clinic at a specific time slot
		Go through the citizen flow where Booking capacity is one less than the capacity and two citizen booking at almost the same time
		Go through the citizen flow where Booking capacity is full but one citizen cancelled so it became available for booking, then it gets booked and meets the capacity and should not be available anymore
		*/
