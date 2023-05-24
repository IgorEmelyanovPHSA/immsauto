package bcvax.tests.Inventory;

import Utilities.TestListener;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.UserDefaultsPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
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
	private String legalFirstName = "Dacia";
	private String legalLastName = "Bcvaxdod";
	private String dateOfBirth = "May 19, 1904";
	private String postalCode = "V7N3K1";
	private String personalHealthNumber = "9746172456";
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

	@Test(priority = 1)
	public void Validate_Consumption_as_an_Clinician() throws Exception {
		TestcaseID = "223355"; //C223355
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
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
		log("/*-- 1.Login as an Clinician for Consumption in Supply Console--*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
		inClinicExperiencePage.closeTabsHCA();
		if (inClinicExperiencePage.displaySupplyConsolePage()) {
			log("/*-- 2. User already on Health Connect - Supply Console --*/");
		} else {
			log("/*-- 2.1. Navigate to Health Connect - Supply Console --*/");
			inClinicExperiencePage.selectHealthConnectApp();
		}
		log("/*-- 3. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		if (inClinicExperiencePage.supplyLocDisplayed()) {
			log("/*-- 4. User is already on Supply loc--*/");
		} else {
			log("/*-- 4.1. Click Dropdown Menu --*/");
			inClinicExperiencePage.clickDropdownMenu();
			log("/*-- 4.2. Navigate and Select Supply Locations --*/");
			inClinicExperiencePage.selectSupplyLocationFromDropdown();
		}
		log("/*-- 5. Close all open tabs --*/");
		inClinicExperiencePage.closeTabsHCA();
		SupplyConsolePage supplyConsolePage = new SupplyConsolePage(driver);
		log("/*4.----Go to Supply Locations Tab --*/");
		supplyConsolePage.clickSupplyLocationsTab();

		log("/*-- 6. Locate and Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		supplyConsolePage.clickOnSupplyLocation(clinicNameToSearch);
		Thread.sleep(2000);
		log("/*-- 7. Click and navigate to the supply container --> 'COMIRNATY (Pfizer) - EL0203 (2022-08-02 03:12 p.m)' --*/");
		double remainingDoses_before = supplyConsolePage.getValueOfRemainingDoses(container, distribution);
		log("/*-- 8. remaining doses Before: -->" + remainingDoses_before);
		double remainingQty_before = supplyConsolePage.getValueOfRemainingQty(container, distribution);
		log("/*-- 9. remaining Qty Before: -->" + remainingQty_before);
		log("/*-- 10. Close all open tabs --*/");
		supplyConsolePage.closeTabsHCA();
		log("/*-- 11. Navigate to In Clinic Experience App --*/");
		inClinicExperiencePage.selectICEFromApp();
		log("/*-- 12. Click on User Defaults Tab  --*/");
		inClinicExperiencePage.clickUserDefaultsTab();
		UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
		log("/*-- 13. Enter current date for UserDefaults --*/");
		userDefaultsPage.inputUserDefaultsCurrentDate();
		userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
		System.out.println("/*-- 14.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		log("/*-- 15. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();
		//System.out.println("/*-- 16.----- Click on Save changes defaults button Modal window --*/");
		//inClinicExperiencePage.clickSaveModalDefaultsButton();
		//Thread.sleep(2000);
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
		inClinicExperiencePage.clickNonIndigenousRadioButton();
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
		Thread.sleep(2000);
		log("/*-- 32.Navigate to Appointment Scheduling Tab --*/");
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		log("/*-- 33.Select Early Booking Reason --*/");
		inClinicExperiencePage.selectEarlyBookingReason();
		log("/*33.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		inClinicExperiencePage.clickOnVaccinationCheckbox();

		//System.out.println("/*--33.----click on reason Early Booking Reason - Travel --*/");
		//inClinicExperiencePage.selectEarlyBookingReason();
		//Thread.sleep(2000);
		log("/*--34.----select 'Search by Clinic name' tab --*/");
		inClinicExperiencePage.selectSearchByClinicNameTab();

		log("/*35.----search the Clinic " +clinicNameToSearch +" --*/");
		log("/*----scroll down a bit --*/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		inClinicExperiencePage.searchClinicName(clinicNameToSearch);

		log("/*--36.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
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
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);
		log("/*-- 45---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		Thread.sleep(2000);
		System.out.println("/*46.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		try {
			log("/*46.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
			inClinicExperiencePage.selectVaccineAgent();
		} catch(Exception ex) {
			log("/*46.---Open Today's appointments from Home page --*/");
			System.out.println(ex.getMessage());
			inClinicExperiencePage.clickTodayAppointments();
			log("/*47.---Open Today appointment Details --*/");
			inClinicExperiencePage.clickTodayAppointmentCaseViewButton();
			log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
			inClinicExperiencePage.selectVaccineAgent();
		}
		log("/*-- 48---Click Save Consent Button --*/");
		if(inClinicExperiencePage.consentProviderSelected().equals("")) {
			inClinicExperiencePage.selectConsentProvider();
		}
		inClinicExperiencePage.ClickSaveConsentButton();
		Thread.sleep(2000);
		System.out.println("/*48_.---Click Save button for Immunisation Information --*/");
		if(inClinicExperiencePage.consentProviderSelected().equals("")) {
			inClinicExperiencePage.selectConsentProvider();
		}

		String agent = inClinicExperiencePage.getVaccineAgent();
		Thread.sleep(2000);
		String provider =  inClinicExperiencePage.getProvider();
		String route = inClinicExperiencePage.getRoute();
		String site = inClinicExperiencePage.getSite();
		String lot = inClinicExperiencePage.getLotNumber();

		if(agent.equals("")) {
			inClinicExperiencePage.setVaccineAgent(consumptionAgent);
		}
		if(provider.equals("")) {
			inClinicExperiencePage.setProvider(consumptionProvider);
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
		Thread.sleep(2000);
		//If expired lop click Ok
		Thread.sleep(1000);
		inClinicExperiencePage.clickOkForExpiredLot();
		/////////
		log("/*-- 49---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(2000);
		System.out.println("/*49.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperiencePage.ClickModalConfirmAndSaveAdministrationButton();
		Thread.sleep(2000);
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
		assertEquals(remainingQty_after, round((remainingDoses_before - 1)/5), 2);
		supplyConsolePage.closeTabsHCA();
		log("/*-- 58. Close all open tabs --*/");
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}

}