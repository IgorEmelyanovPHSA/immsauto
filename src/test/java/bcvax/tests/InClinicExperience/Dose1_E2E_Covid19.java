package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
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
public class Dose1_E2E_Covid19 extends BaseTest {
	String env;
	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	Map<String, Object> testData;
	private String personalHealthNumber = "9746170911";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch;
	MainPageOrg orgMainPage;
	String consumptionLot;
	String consumptionDose;
	String consumptionRoute;
	String consumptionSite;

	@Test(priority = 1)
	public void Can_do_Dose1_Covid19_Vaccine_Administration_as_Clinician_ICE() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
		consumptionDose = String.valueOf(testData.get("consumptionDose"));
		consumptionLot = String.valueOf(testData.get("consumptionLot"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				log("Login AS comunityqa_org_immsbc_admin");
				loginPage.loginAsImmsBCAdmin();
				TestcaseID = "244843"; //244843
				break;
			default:
				log("Login AS default user (Clinician to ICE)");
				orgMainPage = loginPage.orgLoginAsClinicianICE();
				//orgMainPage = loginPage.orgLoginAsPPHIS();
				TestcaseID = "222694"; //
		}
		log("TestRail test case ID: C" +TestcaseID);

		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		}
		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		log("/*6.----- Enter current date for UserDefaults --*/");
		UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
		log("/*-- 13. Enter current date for UserDefaults --*/");
		userDefaultsPage.inputCurrentDateUserDefaults();
		userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
		log("/*7.----- Click on Save defaults button --*/");
		inClinicExperience.clickSaveDefaultsButton();
		System.out.println("/*8.----- Click on register Tab --*/");
		inClinicExperience.clickRegisterTab();
		//System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
		//inClinicExperience.clickSaveModalDefaultsButton();
		//Thread.sleep(2000);
		System.out.println("/*10.----click Register button New Citizen --*/");
		inClinicExperience.clickRegisterButton();
		System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
		inClinicExperience.enterFirstName(legalFirstName);
		System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
		inClinicExperience.enterLastName(legalLastName);
		System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
		inClinicExperience.enterDateOfBirth(dateOfBirth);
		System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
		inClinicExperience.enterPostalCode(postalCode);
		System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
		inClinicExperience.enterPNH(personalHealthNumber);
		System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
			inClinicExperience.clickNonIndigenousRadioButton();
		}
		System.out.println("/*17.----click Verify PHN button --*/");
		inClinicExperience.clickVerifyPHNButton();
		System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
		inClinicExperience.successMessageAppear();
		System.out.println("/*19.----click Next button --*/");
		inClinicExperience.clickNextButton();
		System.out.println("/*20.----'Enter email address " +email +"--*/");
		inClinicExperience.enterEmail(email);
		System.out.println("/*21.----'Confirm email address " +email +"--*/");
		inClinicExperience.confirmEmail(email);
		System.out.println("/*22.---Click review details Button--*/");
		inClinicExperience.clickReviewDetails();
		System.out.println("/*23.----Click register Button on confirmation page--*/");
		inClinicExperience.clickRegisterButtonOnConfirmationPage();
		System.out.println("/*24.--toast success message - 'Success' --*/");
		inClinicExperience.successRegisteredMessageAppear();
		//System.out.println("/*25.----click on person Account Related Tab --*/");
		//inClinicExperience.clickOnPersonAccountRelatedTab();
		System.out.println("/*26----Go to Appointment Tab --*/");
		inClinicExperience.navigateToVaccineSchedulingTab();

		try {
			System.out.println("---click on reason Early Booking Reason - Travel --*/");
			inClinicExperience.selectEarlyBookingReason();
			//commn.selectEarlyBookingReason();
		} catch(Exception ex) {
			System.out.println("There is not Early Booking Option");
		}

		System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		inClinicExperience.clickOnVaccinationCheckbox();
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		//System.out.println("/*29----click on 'More' search tab --*/");
		//inClinicExperience.clickOnMoreSearchTabs();
		//Thread.sleep(2000);
		System.out.println("/*27----select 'Search by Clinic name' tab --*/");
		inClinicExperience.selectSearchByClinicNameTab();

		log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
		inClinicExperience.searchClinicName(clinicNameToSearch);

		System.out.println("/*29----click on Option Facility location  --*/");
		inClinicExperience.clickOnFacilityOptionLocation();
		System.out.println("/*30----select Active booking appointment day  --*/");
		inClinicExperience.selectBookingAppointmentDay();
		System.out.println("/*31----select the time slot  --*/");
		inClinicExperience.selectTimeSlotForAppointment();
		System.out.println("/*32----click Next button  --*/");
		inClinicExperience.clickNextButtonApptSchedulingPage();
		System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
		inClinicExperience.clickVerifyContactInformation();
		System.out.println("/*34----click Confirm Appointment button  --*/");
		inClinicExperience.clickAppointmentConfirmButton();
		System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
		boolean appointment_result = inClinicExperience.AppointmentConfirmationMessage();
		Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		System.out.println("/*36.----Refresh page --*/");
		inClinicExperience.refreshBrowser();
		System.out.println("/*37.----Go to back to the Citizen Related Tab --*/");
		inClinicExperience.clickRelatedTab();
		System.out.println("/*38.----click on In-clinic Experience button --*/");
		inClinicExperience.ClickGoToInClinicExperienceButton();
		try {
			System.out.println("---click on reason Early Booking Reason - Travel --*/");
			inClinicExperience.selectEarlyBookingReason();
		} catch(Exception ex) {
			System.out.println("There is not Early Booking Option");
		}
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		System.out.println("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
		System.out.println("/*40.---Click confirm and Save Button --*/");
		inClinicExperience.HomePageClickConfirmAndSaveButton();
		System.out.println("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		try {
			log("/*46.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
			inClinicExperience.selectVaccineAgent();
		} catch(Exception ex) {
			log("/*46.---Open Today's appointments from Home page --*/");

			inClinicExperience.clickTodayAppointments();
			log("/*47.---Open Today appointment Details --*/");
			inClinicExperience.clickTodayAppointmentCaseViewButton();
			log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
			inClinicExperience.selectVaccineAgent();
		}
		String consentProvider = inClinicExperience.consentProviderSelected();
		if(consentProvider.equals("")) {
			consentProvider = inClinicExperience.selectConsentProvider();
		}

		System.out.println("/*42.---Click Save Consent Button --*/");

		inClinicExperience.ClickSaveConsentButton();
		String agent = inClinicExperience.getVaccineAgent();
		String provider =  inClinicExperience.getProvider();
		String route = inClinicExperience.getRoute();
		String site = inClinicExperience.getSite();

		String lot = inClinicExperience.getLotNumber();

		log("/*42.---Click Save Consent Button --*/");

		if(!provider.equals(consentProvider)) {
			inClinicExperience.setProvider(consentProvider);
		}

		log("/*43.---select Dosage ->  -.5 --*/");
		if(!lot.equals(consumptionLot)) {
			inClinicExperience.setLotNumber(consumptionLot);
		}
		String dose = inClinicExperience.getDosage();

		if(!dose.equals(consumptionDose)) {
			inClinicExperience.setDosage(consumptionDose);
		}
		if(route.equals("")) {
			inClinicExperience.setRoute(consumptionRoute);
		}
		if(site.equals("")) {
			inClinicExperience.setSite(consumptionSite);
		}
		System.out.println("/*42_.---Click Save button for Immunisation Information --*/");
		inClinicExperience.ClickSaveImmuneInfoSaveButton();
		inClinicExperience.clickOkForExpiredLot();
		System.out.println("/*43.---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();
		System.out.println("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
		System.out.println("/*45.---the Home - Client Search showing up  --*/");
		inClinicExperience.validateHomePageShownUp();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}

}
