package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class Dose2_E2E_Covid19 extends BaseTest {
	String env;
	Map<String, Object> testData;
	private String legalFirstName = "Hugues";
	private String legalLastName = "BCVaxLampard";
	private String dateOfBirth = "March 3, 1904";
	private String postalCode = "V1N3Q3";
	private String personalHealthNumber = "9746171121";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String supplyLocationConsumption;
	MainPageOrg orgMainPage;
	String consumptionLot;
	String consumptionDose;
	String consumptionRoute;
	String consumptionSite;
	String consentProvider;
	String consumptionAgent;
	String refusalAgent;
	@Test(priority = 1)
	public void Can_do_Dose2_Covid19_Vaccine_Administration_as_Clinician_ICE() throws Exception {
		TestcaseID = "222811"; //C222811
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("TestRail test case ID: C" +TestcaseID);
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
		log("/*1.----Login as an Clinician to ICE --*/");
		consumptionLot = String.valueOf(testData.get("consumptionLot"));
		consumptionDose = String.valueOf(testData.get("consumptionDose"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		supplyLocationConsumption = String.valueOf(testData.get("supplyLocationConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		consumptionAgent = String.valueOf(testData.get("agentConsumption"));
		loginPage.loginAsImmsBCAdmin();
		orgMainPage = new MainPageOrg(driver);
		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		String currentApp = orgMainPage.currentApp();
		try {
			orgMainPage.closeAllTabs();
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			log("/*3.--- Navigate to In Clinic Experience App --*/");
			orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();

		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		log("/*6.----- Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		Thread.sleep(2000);
		UserDefaultsPage.selectUserDefaultLocation(driver, supplyLocationConsumption);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		Thread.sleep(500);
		log("/*8.----- Click on register Tab --*/");
		inClinicExperience.clickRegisterTab();

		log("/*10.----click Register button New Citizen -Hugues BCVaxLampard --*/");
		inClinicExperience.clickRegisterButton();

		log("/*11.----Enter First Name " +legalFirstName  +"--*/");
		CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);

		log("/*12.----Enter Last Name " +legalLastName  +"--*/");
		CitizenPrimaryInfo.enterLastName(driver, legalLastName);

		log("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
		CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);

		log("/*14.----Enter Postal code " +postalCode +"--*/");
		CitizenPrimaryInfo.enterPostalCode(driver, postalCode);

		log("/*15.----Enter PHN " +personalHealthNumber +"--*/");
		CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);

		log("/*17.----click Verify PHN button --*/");
		CitizenPrimaryInfo.clickVerifyPHNButton(driver);

		log("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
		CitizenPrimaryInfo.successMessageAppear(driver);

		log("/*19.----click Next button --*/");
		try {
			CitizenPrimaryInfo.clickNextButton(driver);
		} catch(ElementClickInterceptedException ex) {
			CitizenPrimaryInfo.successMessageAppear(driver);
			CitizenPrimaryInfo.clickNextButton(driver);
		}

		log("/*20.----'Enter email address " +email +"--*/");
		CitizenPrimaryInfo.enterEmail(driver, email);

		log("/*21.----'Confirm email address " +email +"--*/");
		CitizenPrimaryInfo.confirmEmail(driver, email);

		log("/*22.---Click review details Button--*/");
		CitizenPrimaryInfo.clickReviewDetails(driver);

		log("/*23.----Click register Button on confirmation page--*/");
		CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);

		log("/*24.--toast success message - 'Success' --*/");
		CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

		log("/*25.----click on person Account Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);

		log("/*26.----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);

		log("/*28.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");

		//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}
		PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*28.----select 'Search by Clinic name' tab --*/");
		PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

		log("/*29.----search the Clinic " + supplyLocationConsumption +" --*/");
		PersonAccountSchedulePage.searchClinicName(driver, supplyLocationConsumption);
		log("/*30.----click on Option Facility location  --*/");
		PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

		log("/*31.----select Active booking appointment day  --*/");
		PersonAccountSchedulePage.selectBookingAppointmentDay(driver);

		log("/*32.----select the time slot  --*/");
		PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);

		log("/*33.----click Next button  --*/");
		PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

		log("/*34.----click Verify Contact Info checkbox  --*/");
		PersonAccountSchedulePage.clickVerifyContactInformation(driver);
		PersonAccountSchedulePage.clickOnConfirmButton(driver);

		log("/*35.----see 'Appointment confirmed!' screen --*/");
		boolean apointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
		Assert.assertTrue(apointment_result, "Appointment Confirmation screen didn't appear");

		log("/*36.----Refresh page --*/");
		inClinicExperience.refreshBrowser();

		log("/*37.----Go to back to the Citizen Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);

		log("/*38.----click on In-clinic Experience button --*/");
		PersonAccountPage.clickCheckInButton(driver);
		//inClinicExperience.ClickGoToInClinicExperienceButton();

		log("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
		//inClinicExperience.validateVaccineAdminPageOpen();

		log("/*40.---Click confirm and Save Button --*/");
		Thread.sleep(2000);
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

		log("/*41.---select Vaccine Agent picklist value -> COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");

		inClinicExperience.clickTodayAppointments();
		Thread.sleep(2000);
		log("/*47.---Open Today appointment Details --*/");
		inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);

		String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);
		if(!lot.equals(consumptionLot)) {
			InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
		}

		String provider =  InClinicExperienceVaccineAdministrationPage.getProvider(driver);
		if(!provider.equals(consentProvider)) {
			InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
		}

		String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
		String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);
		String dose = InClinicExperienceVaccineAdministrationPage.getDosage(driver);
		if(!dose.equals(consumptionDose)) {
			InClinicExperienceVaccineAdministrationPage.setDosage(driver, consumptionDose);
		}
		if(route.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setRoute(driver, consumptionRoute);
		}
		if(site.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setSite(driver, consumptionSite);
		}
		log("/*42_.---Click Save button for Immunisation Information --*/");
		inClinicExperience.ClickSaveImmuneInfoSaveButton();
		inClinicExperience.clickOkForExpiredLot();
		log("/*43.---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();

		log("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();

		log("/*45.---the Home - Client Search showing up  --*/");
		inClinicExperience.validateHomePageShownUp();
	}

	@Test(priority = 2)
	public void verify_cannot_proceed_with_consent_refusal() throws Exception {
		TestcaseID = "276033"; //C222811
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("TestRail test case ID: C" +TestcaseID);
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
		log("/*1.----Login as an Clinician to ICE --*/");
		consumptionLot = String.valueOf(testData.get("consumptionLot"));
		consumptionDose = String.valueOf(testData.get("consumptionDose"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		supplyLocationConsumption = String.valueOf(testData.get("supplyLocationConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		refusalAgent = String.valueOf(testData.get("agentRefusal"));
		loginPage.loginAsImmsBCAdmin();
		orgMainPage = new MainPageOrg(driver);
		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		String currentApp = orgMainPage.currentApp();
		try {
			orgMainPage.closeAllTabs();
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			log("/*3.--- Navigate to In Clinic Experience App --*/");
			orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();

		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		log("/*6.----- Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		Thread.sleep(2000);
		UserDefaultsPage.selectUserDefaultLocation(driver, supplyLocationConsumption);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		Thread.sleep(500);
		log("/*8.----- Click on register Tab --*/");
		inClinicExperience.clickRegisterTab();

		log("/*10.----click Register button New Citizen -Hugues BCVaxLampard --*/");
		inClinicExperience.clickRegisterButton();

		log("/*11.----Enter First Name " +legalFirstName  +"--*/");
		CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);

		log("/*12.----Enter Last Name " +legalLastName  +"--*/");
		CitizenPrimaryInfo.enterLastName(driver, legalLastName);

		log("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
		CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);

		log("/*14.----Enter Postal code " +postalCode +"--*/");
		CitizenPrimaryInfo.enterPostalCode(driver, postalCode);

		log("/*15.----Enter PHN " +personalHealthNumber +"--*/");
		CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);

		//log("/*16.----click on non-Indigenous person radiobutton --*/");
		//if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
		//	inClinicExperience.clickNonIndigenousRadioButton();
		//}

		log("/*17.----click Verify PHN button --*/");
		CitizenPrimaryInfo.clickVerifyPHNButton(driver);

		log("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
		CitizenPrimaryInfo.successMessageAppear(driver);

		log("/*19.----click Next button --*/");
		CitizenPrimaryInfo.clickNextButton(driver);

		log("/*20.----'Enter email address " +email +"--*/");
		CitizenPrimaryInfo.enterEmail(driver, email);

		log("/*21.----'Confirm email address " +email +"--*/");
		CitizenPrimaryInfo.confirmEmail(driver, email);

		log("/*22.---Click review details Button--*/");
		CitizenPrimaryInfo.clickReviewDetails(driver);

		log("/*23.----Click register Button on confirmation page--*/");
		CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);

		log("/*24.--toast success message - 'Success' --*/");
		CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

		log("/*25.----click on person Account Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);

		log("/*26.----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);

		log("/*28.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");

		//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}
		PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*28.----select 'Search by Clinic name' tab --*/");
		PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

		log("/*29.----search the Clinic " + supplyLocationConsumption +" --*/");
		PersonAccountSchedulePage.searchClinicName(driver, supplyLocationConsumption);

		log("/*30.----click on Option Facility location  --*/");
		PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);

		log("/*31.----select Active booking appointment day  --*/");
		PersonAccountSchedulePage.selectBookingAppointmentDay(driver);

		log("/*32.----select the time slot  --*/");
		PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);

		log("/*33.----click Next button  --*/");
		PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);

		log("/*34.----click Verify Contact Info checkbox  --*/");
		PersonAccountSchedulePage.clickVerifyContactInformation(driver);
		PersonAccountSchedulePage.clickOnConfirmButton(driver);

		log("/*35.----see 'Appointment confirmed!' screen --*/");
		boolean apointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
		Assert.assertTrue(apointment_result, "Appointment Confirmation screen didn't appear");

		log("/*36.----Refresh page --*/");
		inClinicExperience.refreshBrowser();

		log("/*37.----Go to back to the Citizen Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);

		log("/*38.----click on In-clinic Experience button --*/");
		PersonAccountPage.clickCheckInButton(driver);
		//inClinicExperience.ClickGoToInClinicExperienceButton();

		log("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
		//inClinicExperience.validateVaccineAdminPageOpen();

		log("/*40.---Click confirm and Save Button --*/");
		Thread.sleep(2000);
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

		log("/*41.---select Vaccine Agent picklist value -> COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");

		inClinicExperience.clickTodayAppointments();
		Thread.sleep(2000);
		log("/*47.---Open Today appointment Details --*/");
		inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);

		Thread.sleep(2000);
		//If Incorrect vaccine warning is displayed
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}

//		String consentProviderSelected = ProfilesPage.consentProviderSelected(driver);
//		if(consentProviderSelected.equals("")) {
//			consentProviderSelected = ProfilesPage.selectConsentProvider(driver, consentProvider);
//		}
//
//		log("/*42.---Click Save Consent Button --*/");
//		inClinicExperience.ClickSaveConsentButton();

		try {
			PersonAccountRelatedPage.checkExistingConsent(driver);
		} catch(Exception ex) {
			System.out.println("No Checkbox. Continue...");
		}
		try {
			ProfilesPage.clickEditImmunizationInformation(driver);
		} catch(Exception ex) {
			System.out.println("Edit Button disabled. Continue...");
		}

		String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);
		if(!lot.equals(consumptionLot)) {
			InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
		}

		String provider =  InClinicExperienceVaccineAdministrationPage.getProvider(driver);
		if(!provider.equals(consentProvider)) {
			InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
		}

		String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
		String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);
		String dose = InClinicExperienceVaccineAdministrationPage.getDosage(driver);
		if(!dose.equals(consumptionDose)) {
			InClinicExperienceVaccineAdministrationPage.setDosage(driver, consumptionDose);
		}
		if(route.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setRoute(driver, consumptionRoute);
		}
		if(site.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setSite(driver, consumptionSite);
		}
		log("/*42_.---Click Save button for Immunisation Information --*/");
		inClinicExperience.ClickSaveImmuneInfoSaveButton();
		inClinicExperience.clickOkForExpiredLot();
		log("/*43.---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();

		log("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();

		log("/*45.---the Home - Client Search showing up  --*/");
		inClinicExperience.validateHomePageShownUp();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
	}
}