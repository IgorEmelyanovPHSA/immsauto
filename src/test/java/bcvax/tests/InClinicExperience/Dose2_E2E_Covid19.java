package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class Dose2_E2E_Covid19 extends BaseTest {
	String env;
	Map<String, Object> testData;
	String supplyLocationConsumption;
	MainPageOrg orgMainPage;
	String consumptionLot;
	String consumptionDose;
	String consumptionRoute;
	String consumptionSite;
	String consentProvider;
	String consumptionAgent;
	String refusalAgent;
	Map<String, String> client_data;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "dose2");
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
	}
	@Test(priority = 1)
	public void Can_do_Dose2_Covid19_Vaccine_Administration_as_Clinician_ICE() throws Exception {
		TestcaseID = "222811"; //C222811
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("TestRail test case ID: C" +TestcaseID);
		log("Target Environment: "+ Utils.getTargetEnvironment());
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
		String currentApp = MainPageOrg.currentApp(driver);
		try {
			MainPageOrg.closeAllTabs(driver);
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			log("/*3.--- Navigate to In Clinic Experience App --*/");
			MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		InClinicExperiencePage.closeTabsHCA(driver);

		log("/*5.----- Click on User Defaults Tab --*/");
		InClinicExperiencePage.clickUserDefaultsTab(driver);
		log("/*6.----- Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		Thread.sleep(2000);
		UserDefaultsPage.selectUserDefaultLocation(driver, supplyLocationConsumption);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		Thread.sleep(500);
		log("/*8.----- Click on register Tab --*/");
		InClinicExperiencePage.clickRegisterTab(driver);

		log("/*10.----click Register button New Citizen -Hugues BCVaxLampard --*/");
		InClinicExperiencePage.clickRegisterButton(driver);
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

		log("/*25.----click on person Account Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountPage.goToRelatedTab(driver);
		}

		log("/*26.----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);

		try {
			log("/*28.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		} catch(Exception ex) {
			//If override Eligibility is shown
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
			Thread.sleep(500);
			log("/*28.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		}

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
		driver.navigate().refresh();

		log("/*37.----Go to back to the Citizen Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			PersonAccountPage.goToRelatedTab(driver);
		}

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

		InClinicExperiencePage.clickTodayAppointments(driver);
		Thread.sleep(2000);
		log("/*47.---Open Today appointment Details --*/");
		Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
		ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);

		String lot = null;
		try {
			lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);
		} catch(Exception ex) {
			PersonAccountRelatedPage.checkExistingConsent(driver);
			lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);
		}
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
		InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
		InClinicExperiencePage.clickOkForExpiredLot(driver);
		log("/*43.---Click Confirm and Save Administration Button --*/");
		InClinicExperienceVaccineAdministrationPage.ClickConfirmAndSaveAdministrationButton(driver);

		log("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		InClinicExperienceVaccineAdministrationPage.ClickModalConfirmAndSaveAdministrationButton(driver);

		log("/*45.---the Home - Client Search showing up  --*/");
		InClinicExperiencePage.validateHomePageShownUp(driver);
	}

	@Test(priority = 2)
	public void verify_cannot_proceed_with_consent_refusal() throws Exception {
		TestcaseID = "276033"; //C222811
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("TestRail test case ID: C" +TestcaseID);
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to ICE --*/");
		consumptionLot = String.valueOf(testData.get("consumptionLot"));
		consumptionDose = String.valueOf(testData.get("consumptionDose"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		supplyLocationConsumption = String.valueOf(testData.get("supplyLocationConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		refusalAgent = String.valueOf(testData.get("agentRefusal"));
		consumptionAgent = String.valueOf(testData.get("agentConsumption"));
		loginPage.loginAsImmsBCAdmin();
		orgMainPage = new MainPageOrg(driver);
		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		String currentApp = MainPageOrg.currentApp(driver);
		try {
			MainPageOrg.closeAllTabs(driver);
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			log("/*3.--- Navigate to In Clinic Experience App --*/");
			MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		InClinicExperiencePage.closeTabsHCA(driver);

		log("/*5.----- Click on User Defaults Tab --*/");
		InClinicExperiencePage.clickUserDefaultsTab(driver);
		log("/*6.----- Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		Thread.sleep(2000);
		UserDefaultsPage.selectUserDefaultLocation(driver, supplyLocationConsumption);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		Thread.sleep(500);
		log("/*8.----- Click on register Tab --*/");
		InClinicExperiencePage.clickRegisterTab(driver);

		log("/*10.----click Register button New Citizen -Hugues BCVaxLampard --*/");
		InClinicExperiencePage.clickRegisterButton(driver);
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

		log("/*25.----click on person Account Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			PersonAccountPage.goToRelatedTab(driver);
		}

		log("/*26.----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);


		//If override Eligibility is shown
		try {
			log("/*28.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");

		} catch(Exception ex) {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		}

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
		driver.navigate().refresh();

		log("/*37.----Go to back to the Citizen Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountPage.goToRelatedTab(driver);
		}

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

		InClinicExperiencePage.clickTodayAppointments(driver);
		Thread.sleep(2000);
		log("/*47.---Open Today appointment Details --*/");
		Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
		ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
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
			InClinicExperienceVaccineAdministrationPage.clickEditImmunizationInformation(driver);
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
		InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
		InClinicExperiencePage.clickOkForExpiredLot(driver);
		log("/*43.---Click Confirm and Save Administration Button --*/");
		InClinicExperienceVaccineAdministrationPage.ClickConfirmAndSaveAdministrationButton(driver);

		log("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		InClinicExperienceVaccineAdministrationPage.ClickModalConfirmAndSaveAdministrationButton(driver);

		log("/*45.---the Home - Client Search showing up  --*/");
		InClinicExperiencePage.validateHomePageShownUp(driver);
	}
}