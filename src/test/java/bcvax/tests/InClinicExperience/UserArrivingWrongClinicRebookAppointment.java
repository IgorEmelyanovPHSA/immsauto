package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.LoginPage;
import bcvax.tests.BaseTest;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class UserArrivingWrongClinicRebookAppointment extends BaseTest {
	
	private String legalFirstName = "Hugues";
	private String legalLastName = "BCVaxLampard";
	private String legalMiddleName = "Fawn";
	private String dateOfBirth = "March 3, 1904";
	private String postalCode = "V1N3Q3";
	private String personalHealthNumber = "9746171121";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch = "All Ages - Atlin Health Centre";

	String citizenName = "Hugues BCVaxLampard";

	@Test
	public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician() throws Exception {
		TestcaseID = "219910"; //C219910
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
		log("/*-- 1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianWrongClinic();
		Thread.sleep(10000);
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
		log("/*-- 5.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		Thread.sleep(2000);
		log("/*-- 6. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(2000);//
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 7. Close any open Tabs --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 8. Register New User -->" + legalFirstName + legalLastName + "--*/");
		inClinicExperiencePage.enterFirstName(legalFirstName);
		log("/*-- 9.----Enter First Name " + legalFirstName + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterLastName(legalLastName);
		log("/*-- 10.----Enter Last Name " + legalLastName + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		log("/*-- 11.----Enter Date of birth " + dateOfBirth + "--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterPostalCode(postalCode);
		log("/*-- 12.----Enter Postal code" + postalCode + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterPNH(personalHealthNumber);
		log("/*-- 13.----Enter PHN " + personalHealthNumber + "--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickNonIndigenousRadioButton();
		log("/*-- 14.----click on non-Indigenous person radiobutton --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickVerifyPHNButton();
		log("/*-- 15.----click Verify PHN button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.successMessage();
		log("/*-- 16.'PNH match successful' --*/");
		inClinicExperiencePage.clickNextButton();
		log("/*-- 17.'Click next button --*/");
		Thread.sleep(5000);
		inClinicExperiencePage.enterEmail(email);
		log("/*-- 18.'Enter email address" + email + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.confirmEmail(email);
		log("/*-- 19.'Confirm email address" + email + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickReviewDetails();
		log("/*-- 20.Click review details Button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		log("/*-- 21.Click register Button on confirmation page --*/");
		Thread.sleep(5000);
		log("/*-- 22.Navigate to Appointment Scheduling Tab --*/");
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		Thread.sleep(5000);
		//System.out.println("/*23.----click on reason Early Booking Reason - Travel --*/");
		//inClinicExperiencePage.selectEarlyBookingReason();
		//Thread.sleep(2000);

		System.out.println("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		Thread.sleep(3000);
		inClinicExperiencePage.clickOnVaccinationCheckbox();
		Thread.sleep(2000);

		log("/*25.----search the Clinic " + clinicNameToSearch + " --*/");
		inClinicExperiencePage.clickToSearchClinic();
		Thread.sleep(2000);

		log("/*25__.----search the Clinic " +clinicNameToSearch +" --*/");
		log("/*----scroll down a bit --*/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(2000);
		inClinicExperiencePage.searchClinicName(clinicNameToSearch);
		Thread.sleep(2000);

		log("/*--26.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*--27.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*--28.---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		log("/*--29.---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		log("/*30.----Verify Contact Information  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*-- 31.---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		log("/*-- 32.---Appointment Confirmed! Message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		log("/*-- 33.---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*-- 34.---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		Thread.sleep(5000);
		log("/*-- 34.1---Validate the Clinic's name before click rebook button --*/");
		String beforeBooking = inClinicExperiencePage.ValidateClinicNameBeforeRebook();
		String before = beforeBooking;
		log("/*-- 35.: --> Before Booking clinic Value is:" + beforeBooking + "");
		Thread.sleep(2000);
		log("/*-- 36.--- User can click Rebook Appointment button to book an appointment --*/");
		inClinicExperiencePage.ClickRebookAppointment();
		log("/*--  We need to add Validation for 1.(Clinic has changed & address has changed) --*/");
		log("/*--                                2. Rebook at Current Location button is disabled --*/");
		Thread.sleep(5000);
		String afterBooking = inClinicExperiencePage.ValidateclinicNameAfterRebook();
		String after = afterBooking;
		log("/*-- 37: --> After Booking clinic value is:" + afterBooking + "");
		Assert.assertNotEquals((before), (after));
		Thread.sleep(2000);
		log("/*-- 38---'Rebook at Current Location button is disabled after user books appointment --*/");
		inClinicExperiencePage.ValidateClickRebookAppointmentButtonIsDisabled();
		Thread.sleep(2000);
		log("/*-- 39---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		log("/*-- 40---Click to select Agent --*/");
		inClinicExperiencePage.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 41--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		inClinicExperiencePage.SelectAgentValue();
		Thread.sleep(2000);
		log("/*-- 42---Click Save Consent Button --*/");
		inClinicExperiencePage.ClickSaveConsentButton();
		Thread.sleep(5000);
		log("/*---43. Save Immunization Information ---*/");
		inClinicExperiencePage.saveImmunizationInformation();
		Thread.sleep(2000);
		inClinicExperiencePage.clickOkForExpiredLot();
		Thread.sleep(2000);
		log("/*-- 44---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*45.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperiencePage.ClickModalConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*-- 46---the Home - Client Search supposed to showing up  --*/");
		inClinicExperiencePage.validateHomePageShownUp();
		Thread.sleep(3000);
		inClinicExperiencePage.refreshBrowser();
		Thread.sleep(5000);
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);

		/*----Search for Participant account ---*/
		//log("/* 47.----Search for Participant account ---*/");
		//log("/* 48.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		//inClinicExperiencePage.SearchForCitizen(legalFirstName + " " + legalLastName);
		log("/*----47. Global Search for Participant account: " +citizenName +" ---*/");
		inClinicExperiencePage.SearchForCitizenAlternativeWay(citizenName);
		Thread.sleep(2000);

		log("/* 49.----User found and Navigated to record page ---*/");
		inClinicExperiencePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName);
		Thread.sleep(2000);

		log("/*50.---- Navigated to Person Account related tab ---*/");
		inClinicExperiencePage.clickRelatedTab();
		Thread.sleep(5000);
		log("/*51.---- Immunization status is in After Care ---*/");
		inClinicExperiencePage.ValidateStatusisInAftercare();
		Thread.sleep(2000);
		log("/*52.---- User Navigated to Appointment Section  ---*/");
		inClinicExperiencePage.NavigateToAppointmentsSection();
		Thread.sleep(2000);
		log("/*53.---- An previous appointment for the user has been cancelled with reebooking of an appointment ---*/");
		inClinicExperiencePage.ValidateAppointmentCancelledIsPresent();
		Thread.sleep(2000);
		log("/*54.---- An confirmed appointmrnt is found for the user  ---*/");
		inClinicExperiencePage.ValidateAppointmentConfirmIsPresent();
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
}
