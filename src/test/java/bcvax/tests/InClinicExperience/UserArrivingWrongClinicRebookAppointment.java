package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

@Listeners({TestListener.class})
public class UserArrivingWrongClinicRebookAppointment extends BaseTest {
	String env;
	Map<String, Object> testData;
	MainPageOrg orgMainPage;
	private String legalFirstName = "Hugues";
	private String legalLastName = "BCVaxLampard";
	private String legalMiddleName = "Fawn";
	private String dateOfBirth = "March 3, 1904";
	private String postalCode = "V1N3Q3";
	private String personalHealthNumber = "9746171121";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToBook = "All Ages - Atlin Health Centre";
	String clinicNameToSearch = "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic";

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
		//InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianWrongClinic();
		orgMainPage = loginPage.orgLoginAsClinicianICE();

		log("/*-- 2. Navigate to In Clinic Experience App --*/");
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		}

		log("/*-- 3. Click on User Defaults Tab  --*/");
		InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
		inClinicExperiencePage.clickUserDefaultsTab();
		UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
		log("/*-- 4. Enter current date for UserDefaults --*/");
		userDefaultPage.inputCurrentDateUserDefaults();
		//userDefaultPage.inputPreviousDateUserDefaults();
		userDefaultPage.selectUserDefaultLocation(clinicNameToSearch);
		log("/*-- 5.----- Click on Save defaults button --*/");
		userDefaultPage.clickBtnSave();
		AlertDialog.closeAlert(driver);
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
		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
			inClinicExperiencePage.clickNonIndigenousRadioButton();
		}

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
		AlertDialog.closeAlert(driver);
		log("/*-- 22.Navigate to Appointment Scheduling Tab --*/");
		inClinicExperiencePage.navigateToVaccineSchedulingTab();

		//If there is early reason screen select Early Reason
		try {
			inClinicExperiencePage.selectEarlyBookingReason();
		} catch (Exception ex) {
			System.out.println("Tried to select early reason if exist. Continue...");
		}
		System.out.println("/*24.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		inClinicExperiencePage.clickOnVaccinationCheckbox();
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*25.----search the Clinic " + clinicNameToSearch + " --*/");
		inClinicExperiencePage.clickToSearchClinic();

		log("/*25__.----search the Clinic " +clinicNameToSearch +" --*/");
		inClinicExperiencePage.searchClinicName(clinicNameToBook);

		log("/*--26.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		log("/*--27.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay(1);
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
		String beforeBooking = inClinicExperiencePage.getAppointmentClinicName();
		log("/*-- 35.: --> Before Booking clinic Value is:" + beforeBooking + "");
		log("/*-- 36.--- User can click Rebook Appointment button to book an appointment --*/");
		inClinicExperiencePage.ClickRebookAppointment();
		log("/*-- 36.1.--- Close Success Dialog --*/");
		List<String> alert_texts = AlertDialog.getAllAlertsText(driver);
		for (String alert_text: alert_texts) {
			System.out.println("Alert Text: " + alert_text);
		}
		AlertDialog.closeAllAlerts(driver);
		//In case another alert
		log("/*--  We need to add Validation for 1.(Clinic has changed & address has changed) --*/");
		inClinicExperiencePage.ValidateClickRebookAppointmentButtonIsDisabled();
		log("/*--                                2. Rebook at Current Location button is disabled --*/");
		String afterBooking = inClinicExperiencePage.getAppointmentClinicName();
		log("/*-- 37: --> After Booking clinic value is:" + afterBooking + "");
		Assert.assertNotEquals((beforeBooking), (afterBooking));
		log("/*-- 38---'Rebook at Current Location button is disabled after user books appointment --*/");

		log("/*-- 39---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		log("/*-- 39.1.---Close Success Dialog --*/");
		alert_texts = AlertDialog.getAllAlertsText(driver);
		for (String alert_text: alert_texts) {
			System.out.println("Alert Text: " + alert_text);
		}
		AlertDialog.closeAllAlerts(driver);
		log("/*-- 40---Click to select Agent --*/");
		try {
			log("/*46.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
			inClinicExperiencePage.selectVaccineAgent();
		} catch(Exception ex) {
			inClinicExperiencePage.refreshBrowser();
			Thread.sleep(2000);
			log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
			inClinicExperiencePage.selectVaccineAgent();
		}
		log("/*-- 41--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		try {
			inClinicExperiencePage.SelectAgentValue();
		} catch(Exception ex) {
			System.out.println("Probably agent is already selected. Continue...");
		}
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
		String alert_text = AlertDialog.getAlertContent(driver).getText();
		System.out.println("Alert Text: " + alert_text);
		AlertDialog.closeAlert(driver);
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

		//log("/* 49.----User found and Navigated to record page ---*/");
		//inClinicExperiencePage.userFoundWithParameters(citizenName);

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
