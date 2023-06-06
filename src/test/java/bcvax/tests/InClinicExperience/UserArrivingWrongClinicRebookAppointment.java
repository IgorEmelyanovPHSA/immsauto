package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.LoginPage;
import bcvax.pages.MainPageOrg;
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

import java.util.Map;

@Listeners({TestListener.class})
public class UserArrivingWrongClinicRebookAppointment extends BaseTest {
	String env;
	Map<String, Object> testData;
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
	String consumptionLot;
	String consumptionDose;
	String consumptionAgent;
	String consumptionProvider;
	String consumptionRoute;
	String consumptionSite;

	@Test
	public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician() throws Exception {
		TestcaseID = "219910"; //C219910
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("Target Environment: " + Utils.getTargetEnvironment());
		consumptionDose = String.valueOf(testData.get("consumptionDose"));
		consumptionAgent = String.valueOf(testData.get("agentConsumption"));
		consumptionProvider = String.valueOf(testData.get("providerConsumption"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		consumptionLot = String.valueOf(testData.get("consumptionLot"));
		log("TestRail test case ID: C" +TestcaseID);

		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

		log("/*-- 1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianWrongClinic();
		inClinicExperiencePage.closeTabsHCA();

		log("/*-- 2. Navigate to In Clinic Experience App --*/");
		inClinicExperiencePage.selectICEFromApp();

		log("/*-- 3. Click on User Defaults Tab  --*/");
		inClinicExperiencePage.clickUserDefaultsTab();

		log("/*-- 4. Enter current date for UserDefaults --*/");
		inClinicExperiencePage.inputPreviousDateUserDefaults();

		log("/*-- 5.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();

		log("/*-- 6. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();

		log("/*-- 7. Close any open Tabs --*/");
		inClinicExperiencePage.closeTabsHCA();

		log("/*-- 8. Register New User -->" + legalFirstName + legalLastName + "--*/");
		inClinicExperiencePage.clickRegisterButton();

		log("/*-- 9.----Enter First Name " + legalFirstName + " --*/");
		inClinicExperiencePage.enterFirstName(legalFirstName);

		log("/*-- 10.----Enter Last Name " + legalLastName + " --*/");
		inClinicExperiencePage.enterLastName(legalLastName);

		log("/*-- 11.----Enter Date of birth " + dateOfBirth + "--*/");
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);

		log("/*-- 12.----Enter Postal code" + postalCode + " --*/");
		inClinicExperiencePage.enterPostalCode(postalCode);

		log("/*-- 13.----Enter PHN " + personalHealthNumber + "--*/");
		inClinicExperiencePage.enterPNH(personalHealthNumber);

		log("/*-- 14.----click on non-Indigenous person radiobutton --*/");
		inClinicExperiencePage.clickNonIndigenousRadioButton();

		log("/*-- 15.----click Verify PHN button --*/");
		inClinicExperiencePage.clickVerifyPHNButton();

		log("/*-- 16.'PNH match successful' --*/");
		inClinicExperiencePage.successMessageAppear();

		log("/*-- 17.'Click next button --*/");
		inClinicExperiencePage.clickNextButton();

		log("/*-- 18.'Enter email address" + email + " --*/");
		inClinicExperiencePage.enterEmail(email);

		log("/*-- 19.'Confirm email address" + email + " --*/");
		inClinicExperiencePage.confirmEmail(email);

		log("/*-- 20.Click review details Button --*/");
		inClinicExperiencePage.clickReviewDetails();

		log("/*-- 21.Click register Button on confirmation page --*/");
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(5000);

		log("/*-- 22.Navigate to Appointment Scheduling Tab --*/");
		inClinicExperiencePage.navigateToVaccineSchedulingTab();

		System.out.println("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		inClinicExperiencePage.clickOnVaccinationCheckbox();

		log("/*25.----search the Clinic " + clinicNameToSearch + " --*/");
		inClinicExperiencePage.clickToSearchClinic();

		log("/*25__.----search the Clinic " +clinicNameToSearch +" --*/");
		inClinicExperiencePage.searchClinicName(clinicNameToSearch);

		log("/*--26.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		log("/*--27.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		log("/*--28.---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		log("/*--29.---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		log("/*30.----Verify Contact Information  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		log("/*-- 31.---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		log("/*-- 32.---Appointment Confirmed! Message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		log("/*-- 33.---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		log("/*-- 34.---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		log("/*-- 34.1---Validate the Clinic's name before click rebook button --*/");
		String beforeBooking = inClinicExperiencePage.ValidateClinicNameBeforeRebook();
		String before = beforeBooking;
		log("/*-- 35.: --> Before Booking clinic Value is:" + beforeBooking + "");
		log("/*-- 36.--- User can click Rebook Appointment button to book an appointment --*/");
		inClinicExperiencePage.ClickRebookAppointment();
		log("/*--  We need to add Validation for 1.(Clinic has changed & address has changed) --*/");
		log("/*--                                2. Rebook at Current Location button is disabled --*/");
		String afterBooking = inClinicExperiencePage.ValidateclinicNameAfterRebook();
		String after = afterBooking;
		log("/*-- 37: --> After Booking clinic value is:" + afterBooking + "");
		Assert.assertNotEquals((before), (after));
		log("/*-- 38---'Rebook at Current Location button is disabled after user books appointment --*/");
		inClinicExperiencePage.ValidateClickRebookAppointmentButtonIsDisabled();
		log("/*-- 39---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		log("/*-- 40---Click to select Agent --*/");
		inClinicExperiencePage.ClickAgentValue();
		log("/*-- 41--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		inClinicExperiencePage.SelectAgentValue();
		String consentProvider = inClinicExperiencePage.consentProviderSelected();
		if(consentProvider.equals("")) {
			consentProvider = inClinicExperiencePage.selectConsentProvider();
		}
		log("/*-- 42---Click Save Consent Button --*/");
		inClinicExperiencePage.ClickSaveConsentButton();

		String agent = inClinicExperiencePage.getVaccineAgent();
		String provider =  inClinicExperiencePage.getProvider();
		String route = inClinicExperiencePage.getRoute();
		String site = inClinicExperiencePage.getSite();

		String lot = inClinicExperiencePage.getLotNumber();

		log("/*42.---Click Save Consent Button --*/");

		if(!provider.equals(consentProvider)) {
			inClinicExperiencePage.setProvider(consentProvider);
		}

		log("/*43.---select Dosage ->  -.5 --*/");
		if(!lot.equals(consumptionLot)) {
			inClinicExperiencePage.setLotNumber(consumptionLot);
		}
		String dose = inClinicExperiencePage.getDosage();

		if(!dose.equals(consumptionDose)) {
			inClinicExperiencePage.setDosage(consumptionDose);
		}
		if(route.equals("")) {
			inClinicExperiencePage.setRoute(consumptionRoute);
		}
		if(site.equals("")) {
			inClinicExperiencePage.setSite(consumptionSite);
		}
		log("/*---43. Save Immunization Information ---*/");
		inClinicExperiencePage.saveImmunizationInformation();
		inClinicExperiencePage.clickOkForExpiredLot();
		log("/*-- 44---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		log("/*45.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperiencePage.ClickModalConfirmAndSaveAdministrationButton();
		log("/*-- 46---the Home - Client Search supposed to showing up  --*/");
		inClinicExperiencePage.validateHomePageShownUp();
		inClinicExperiencePage.refreshBrowser();
		inClinicExperiencePage.clickRegisterTab();

		log("/*----47. Global Search for Participant account: " +citizenName +" ---*/");
		MainPageOrg mainPageOrg = new MainPageOrg(driver);
		mainPageOrg.globalSearch(citizenName);
		//inClinicExperiencePage.SearchForCitizenAlternativeWay(citizenName);

		log("/* 49.----User found and Navigated to record page ---*/");
		inClinicExperiencePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName);

		log("/*50.---- Navigated to Person Account related tab ---*/");
		inClinicExperiencePage.clickRelatedTab();
		log("/*51.---- Immunization status is in After Care ---*/");
		inClinicExperiencePage.ValidateStatusisInAftercare();
		log("/*52.---- User Navigated to Appointment Section  ---*/");
		inClinicExperiencePage.NavigateToAppointmentsSection();
		log("/*53.---- An previous appointment for the user has been cancelled with reebooking of an appointment ---*/");
		inClinicExperiencePage.ValidateAppointmentCancelledIsPresent();
		log("/*54.---- An confirmed appointmrnt is found for the user  ---*/");
		inClinicExperiencePage.ValidateAppointmentConfirmIsPresent();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
}
