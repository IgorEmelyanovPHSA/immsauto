package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static java.lang.Math.round;
import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Consumption extends BaseTest {
	String env;
	private String legalFirstName = "Courtnay";
	private String legalLastName = "BCVaxGoncaves";
	private String dateOfBirth = "Nov 29, 1949";
	private String postalCode = "V3J3Y1";
	private String personalHealthNumber = "9746172961";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch;
	Map<String, Object> testData;
	String distribution;
	String container;
	String consumptionAgent;
	String consumptionProvider;
	String consumptionRoute;
	String consumptionSite;
	String consumptionLot;
	String consumptionDose;
	SupplyConsolePage supplyConsolePage;
	MainPageOrg orgMainPage;
	String consentProvider;

	@Test(priority = 1)
	public void Validate_Consumption_as_an_Clinician() throws Exception {
		TestcaseID = "222359"; //C222359
		log("Target Environment: "+ Utils.getTargetEnvironment());
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
		distribution = String.valueOf(testData.get("distributionConsumption"));
		container = String.valueOf(testData.get("containerConsumption"));
		consumptionAgent = String.valueOf(testData.get("agentConsumption"));
		consumptionProvider = String.valueOf(testData.get("providerConsumption"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		consumptionLot = String.valueOf(testData.get("consumptionLot"));
		consumptionDose = String.valueOf(testData.get("consumptionDose"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
		log("/*-- 1.Login as an Clinician for Consumption in Supply Console--*/");
		loginPage.loginAsImmsBCAdmin();
		orgMainPage = new MainPageOrg(driver);
		String currentApp = MainPageOrg.currentApp(driver);
		if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		supplyConsolePage = new SupplyConsolePage(driver);

		log("/*-- 3. Close all open tabs --*/");

		log("/*3.----Close All previously opened Tab's --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();

		log("/*-- 6. Locate and Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		supplyConsolePage.selectSupplyLocationName(clinicNameToSearch);
		log("/*-- 7. Click and navigate to the supply container --> 'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)' --*/");
		double remainingDoses_before = supplyConsolePage.getValueOfRemainingDoses(container, distribution);
		log("/*-- 8. remaining doses Before: -->" + remainingDoses_before);
		double remainingQty_before = supplyConsolePage.getValueOfRemainingQty(container, distribution);
		log("/*-- 9. remaining Qty Before: -->" + remainingQty_before);
		log("/*-- 10. Close all open tabs --*/");
		double doseConversionFactor = Double.parseDouble(df.format(remainingDoses_before / remainingQty_before));
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*-- 11. Navigate to In Clinic Experience App --*/");
		MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);

		log("/*-- 12. Click on User Defaults Tab  --*/");
		InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
		inClinicExperiencePage.clickUserDefaultsTab();
		log("/*-- 13. Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
		System.out.println("/*-- 14.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		log("/*-- 15. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();

		log("/*-- 17. Close any open Tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 18. Register New User --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 19.----Enter First Name " +legalFirstName +"--*/");
		CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
		log("/*-- 20.----Enter Last Name " + legalLastName +"--*/");
		CitizenPrimaryInfo.enterLastName(driver, legalLastName);
		log("/*-- 21.----Enter Date of birth " +dateOfBirth + "--*/");
		CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
		log("/*-- 22.----Enter Postal code " +postalCode  +"--*/");
		CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
		log("/*-- 23.----Enter PHN --*/");
		CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
		log("/*-- 25.----click Verify PHN button --*/");
		CitizenPrimaryInfo.clickVerifyPHNButton(driver);
		log("/*-- 26.'PNH match successful' --*/");
		CitizenPrimaryInfo.successMessageAppear(driver);
		log("/*-- 27.'Click next button --*/");
		try {
			CitizenPrimaryInfo.clickNextButton(driver);
		} catch(ElementClickInterceptedException ex) {
			CitizenPrimaryInfo.successMessageAppear(driver);
			CitizenPrimaryInfo.clickNextButton(driver);
		}
		log("/*-- 28.'Enter email address " +email +"--*/");
		CitizenPrimaryInfo.enterEmail(driver, email);
		log("/*-- 29.'Confirm email address " +email +"--*/");
		CitizenPrimaryInfo.confirmEmail(driver, email);
		log("/*-- 30.Click review details Button --*/");
		CitizenPrimaryInfo.clickReviewDetails(driver);
		log("/*-- 31.Click register Button on confirmation page --*/");
		CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
		log("/*-- 32.Navigate to Appointment Scheduling Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);
		log("/*-- 33.Select Early Booking Reason --*/");

		//If override Eligibility is shown
		try {
			log("/*33.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		} catch(Exception ex) {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
			Thread.sleep(500);
			log("/*33.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		}

		log("/*--34.----select 'Search by Clinic name' tab --*/");
		PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

		log("/*35.----search the Clinic " +clinicNameToSearch +" --*/");
		PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);

		log("/*--36.----click on Option Facility location  --*/");
		PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
		log("/*--37.----select Appointment Day --*/");
		PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
		log("/*--38.---- select time slot for Appointment --*/");
		PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
		log("/*--39.---Click Next Button to Schedule Appointment --*/");
		PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
		log("/*40.----click Verify Contact Info checkbox  --*/");
		PersonAccountSchedulePage.clickVerifyContactInformation(driver);
		log("/*-- 41.---Click Appointment Confirm Button --*/");
		PersonAccountSchedulePage.clickOnConfirmButton(driver);
		log("/*-- 42.---'Appointment confirmed!' - message Displayed --*/");
		PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
		log("/*-- 43.---Navigate to person account Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		log("/*-- 44.---Click Go To In clinic experience button --*/");
		PersonAccountPage.clickCheckInButton(driver);
		Thread.sleep(2000);
		log("/*-- 45---Click confirm and Save Button on Home Page --*/");
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
		System.out.println("/*46.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");
		inClinicExperiencePage.clickTodayAppointments();
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		Thread.sleep(2000);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		String agent = InClinicExperienceVaccineAdministrationPage.getVaccineAgent(driver);
		if(agent.equals("")) {
			InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);
		}
		Thread.sleep(2000);

		//If Incorrect vaccine warning is displayed
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}

//		String consentProviderSelected = ProfilesPage.consentProviderSelected(driver);
//		log("/*-- 48---Click Save Consent Button --*/");
//		if(consentProviderSelected.equals("")) {
//			ProfilesPage.selectConsentProvider(driver, consentProvider);
//			consentProviderSelected = ProfilesPage.consentProviderSelected(driver);
//		}
//		inClinicExperiencePage.ClickSaveConsentButton();

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

		System.out.println("/*48_.---Click Save button for Immunisation Information --*/");


		String provider = null;
		try {
			provider = InClinicExperienceVaccineAdministrationPage.getProvider(driver);
		} catch(NotFoundException ex) {
			Thread.sleep(2000);
			provider = InClinicExperienceVaccineAdministrationPage.getProvider(driver);
		}
		String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
		String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);
		String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);

		if(agent.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setVaccineAgent(driver, consumptionAgent);
		}
		if(provider.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
		}
		if(route.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setRoute(driver, consumptionRoute);
		}
		if(site.equals("")) {
			InClinicExperienceVaccineAdministrationPage.setSite(driver, consumptionSite);
		}
		if(!lot.equals(consumptionLot)) {
			InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
		}
		String dose = InClinicExperienceVaccineAdministrationPage.getDosage(driver);
		if(!dose.equals(consumptionDose)) {
			InClinicExperienceVaccineAdministrationPage.setDosage(driver, consumptionDose);
		}
		inClinicExperiencePage.ClickSaveImmuneInfoSaveButton();
		inClinicExperiencePage.clickOkForExpiredLot();
		Thread.sleep(2000);
		log("/*-- 49---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		System.out.println("/*49.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperiencePage.ClickModalConfirmAndSaveAdministrationButton();
		log("/*-- 50---the Home - Client Search supposed to showing up  --*/");
		inClinicExperiencePage.validateHomePageShownUp();
		log("/*-- 51. Navigate to Health Connect - Supply Console --*/");
		MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		supplyConsolePage = new SupplyConsolePage(driver);
		log("/*-- 52. Close any open tabs --*/");
		SupplyConsolePage.closeTabsHCA(driver);
		supplyConsolePage.clickSupplyLocationsTab();
		log("/*-- 53. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		supplyConsolePage.selectSupplyLocationName(clinicNameToSearch);

		//////////Validation for Dosages and Qty After Consumption
		System.out.println("/*--55.----Validate Remaining Doses and Remaining Quantities values after Consuming --*/");
		double remainingDoses_after = supplyConsolePage.getValueOfRemainingDoses(container, distribution);
		log("/*-- 56. remaining doses After Consumption: -->" + remainingDoses_after);
		assertEquals(remainingDoses_after, remainingDoses_before - 1);
		double remainingQty_after = supplyConsolePage.getValueOfRemainingQty(container, distribution);
		log("/*-- 57. remaining Qty After: -->" + remainingQty_after);
		assertEquals(remainingQty_after, round((remainingDoses_before - 1)/doseConversionFactor), 2);
		SupplyConsolePage.closeTabsHCA(driver);
		log("/*-- 58. Close all open tabs --*/");
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
	}

}