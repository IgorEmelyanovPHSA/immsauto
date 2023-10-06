package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
		loginPage.loginAsClerk();
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
			orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
		}
		supplyConsolePage = new SupplyConsolePage(driver);

		log("/*-- 3. Close all open tabs --*/");
		log("/*2.----Supply Console Page displayed --*/");
		supplyConsolePage.verifyIsSupplyPageDisplayed();
		log("/*3.----Close All previously opened Tab's --*/");
		supplyConsolePage.closeTabsHCA();
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
		supplyConsolePage.closeTabsHCA();
		log("/*-- 11. Navigate to In Clinic Experience App --*/");
		orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		log("/*-- 12. Click on User Defaults Tab  --*/");
		InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
		inClinicExperiencePage.clickUserDefaultsTab();
		UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
		log("/*-- 13. Enter current date for UserDefaults --*/");
		userDefaultsPage.inputCurrentDateUserDefaults();
		userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
		System.out.println("/*-- 14.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		log("/*-- 15. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();

		log("/*-- 17. Close any open Tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 18. Register New User --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 19.----Enter First Name " +legalFirstName +"--*/");
		inClinicExperiencePage.enterFirstName(legalFirstName);
		log("/*-- 20.----Enter Last Name " + legalLastName +"--*/");
		inClinicExperiencePage.enterLastName(legalLastName);
		log("/*-- 21.----Enter Date of birth " +dateOfBirth + "--*/");
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		log("/*-- 22.----Enter Postal code " +postalCode  +"--*/");
		inClinicExperiencePage.enterPostalCode(postalCode);
		log("/*-- 23.----Enter PHN --*/");
		inClinicExperiencePage.enterPNH(personalHealthNumber);
		log("/*-- 24.----click on non-Indigenous person radiobutton --*/");
		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
			inClinicExperiencePage.clickNonIndigenousRadioButton();
		}
		log("/*-- 25.----click Verify PHN button --*/");
		inClinicExperiencePage.clickVerifyPHNButton();
		log("/*-- 26.'PNH match successful' --*/");
		inClinicExperiencePage.successMessageAppear();
		log("/*-- 27.'Click next button --*/");
		inClinicExperiencePage.clickNextButton();
		log("/*-- 28.'Enter email address " +email +"--*/");
		inClinicExperiencePage.enterEmail(email);
		log("/*-- 29.'Confirm email address " +email +"--*/");
		inClinicExperiencePage.confirmEmail(email);
		log("/*-- 30.Click review details Button --*/");
		inClinicExperiencePage.clickReviewDetails();
		log("/*-- 31.Click register Button on confirmation page --*/");
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		log("/*-- 32.Navigate to Appointment Scheduling Tab --*/");
		inClinicExperiencePage.navigateToVaccineSchedulingTab();
		ProfilesPage profilesPage = new ProfilesPage(driver);
		log("/*-- 33.Select Early Booking Reason --*/");
//		try {
//			PersonAccountPage.selectEarlyBookingReason(driver);
//		} catch(Exception ex) {
//			System.out.println("***Warning***");
//			System.out.println("***No Early Booking Option***");
//			System.out.println("***Warning***");
//		}

		//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountPage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}

		log("/*33.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		inClinicExperiencePage.clickOnVaccinationCheckbox();
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*--34.----select 'Search by Clinic name' tab --*/");
		inClinicExperiencePage.selectSearchByClinicNameTab();

		log("/*35.----search the Clinic " +clinicNameToSearch +" --*/");
		log("/*----scroll down a bit --*/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		inClinicExperiencePage.searchClinicName(clinicNameToSearch);

		log("/*--36.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickOnFacilityOptionLocation();
		log("/*--37.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		log("/*--38.---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		log("/*--39.---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		log("/*40.----click Verify Contact Info checkbox  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		log("/*-- 41.---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		log("/*-- 42.---'Appointment confirmed!' - message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		log("/*-- 43.---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		log("/*-- 44.---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.clickCheckInButton();
		Thread.sleep(2000);
		log("/*-- 45---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		System.out.println("/*46.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");
		inClinicExperiencePage.clickTodayAppointments();
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		Thread.sleep(2000);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		String agent = inClinicExperiencePage.getVaccineAgent();
		if(agent.equals("")) {
			inClinicExperiencePage.selectVaccineAgent(consumptionAgent);
		}
		Thread.sleep(2000);

		//If Incorrect vaccine warning is displayed
		try {
			ProfilesPage.confirm_warning(driver);
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
			ProfilesPage.checkExistingConsent(driver);
		} catch(Exception ex) {
			System.out.println("No Checkbox. Continue...");
		}

		try {
			ProfilesPage.clickEditImmunizationInformation(driver);
		} catch(Exception ex) {
			System.out.println("Edit Button disabled. Continue...");
		}

		System.out.println("/*48_.---Click Save button for Immunisation Information --*/");


		String provider =  inClinicExperiencePage.getProvider();
		String route = inClinicExperiencePage.getRoute();
		String site = inClinicExperiencePage.getSite();
		String lot = inClinicExperiencePage.getLotNumber();

		if(agent.equals("")) {
			inClinicExperiencePage.setVaccineAgent(consumptionAgent);
		}
		if(provider.equals("")) {
			inClinicExperiencePage.setProvider(consentProvider);
		}
		if(route.equals("")) {
			inClinicExperiencePage.setRoute(consumptionRoute);
		}
		if(site.equals("")) {
			inClinicExperiencePage.setSite(consumptionSite);
		}
		if(!lot.equals(consumptionLot)) {
			inClinicExperiencePage.setLotNumber(consumptionLot);
		}
		String dose = inClinicExperiencePage.getDosage();
		if(!dose.equals(consumptionDose)) {
			inClinicExperiencePage.setDosage(consumptionDose);
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
		inClinicExperiencePage.selectHealthConnectApp();
		supplyConsolePage = new SupplyConsolePage(driver);
		log("/*-- 52. Close any open tabs --*/");
		supplyConsolePage.closeTabsHCA();
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
		supplyConsolePage.closeTabsHCA();
		log("/*-- 58. Close all open tabs --*/");
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
	}

}