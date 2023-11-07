package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;


@Listeners({TestListener.class})
public class Dose1_ICE_E2E extends BaseTest {
	String env;
	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	Map<String, Object> testData;
	private String personalHealthNumber = "9746170911";
	private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	//private String email = "jason.yulghun@phsa.ca";
	String clinicNameToSearch;
	MainPageOrg orgMainPage;
	String consumptionLot;
	String consumptionDose;
	String consumptionRoute;
	String consumptionSite;
	String consentProvider;
	String consumptionAgent;

	@DataProvider(name="booking_data")
	public Object[][] dpMethod() {
		return new Object[][] {{"222694", "Covid19Vaccine", "agentConsumption", "covidLot", "covidDose"}, {"228859", "InfluenzaVaccine", "agentInfluenza", "influenzaLot", "influenzaDose"}};
		//return new Object[][] {{"228859", "InfluenzaVaccine", "agentInfluenza", "influenzaLot", "influenzaDose"}};
	}

	@Test(dataProvider = "booking_data")
	public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE(String testcase_id, String vaccine_agent, String administration_agent, String administration_lot, String administration_dose) throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
		consumptionDose = String.valueOf(testData.get(administration_dose));
		consumptionLot = String.valueOf(testData.get(administration_lot));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consumptionSite = String.valueOf(testData.get("siteConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		consumptionAgent = String.valueOf(testData.get(administration_agent));
		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				log("Login AS comunityqa_org_immsbc_admin");
				loginPage.loginAsImmsBCAdmin();
				TestcaseID = "244843"; //244843
				break;
			default:
				log("Login AS default user (Clinician to ICE)");
				loginPage.loginAsClerk();
				orgMainPage = new MainPageOrg(driver);
						//orgMainPage = loginPage.orgLoginAsPPHIS();
				TestcaseID = testcase_id; //
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
		userDefaultsPage.clickBtnSave();
		AlertDialog.closeAlert(driver);
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
		try {
			inClinicExperience.successRegisteredMessageAppear();
		} catch(Exception ex) {
			System.out.println("No Success Message. Contrinue ...");
			System.out.println(ex.getMessage());
		}
		//System.out.println("/*25.----click on person Account Related Tab --*/");
		//inClinicExperience.clickOnPersonAccountRelatedTab();
		System.out.println("/*26----Go to Appointment Tab --*/");
		inClinicExperience.navigateToVaccineSchedulingTab();
		Thread.sleep(2000);
//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountPage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}
		Thread.sleep(2000);
		System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		inClinicExperience.selectOneOption(vaccine_agent);

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
		PersonAccountPage.goToRelatedTab(driver);
		System.out.println("/*38.----click on In-clinic Experience button --*/");
		PersonAccountPage.clickCheckInButton(driver);

		System.out.println("/*40.---Click confirm and Save Button --*/");
		inClinicExperience.HomePageClickConfirmAndSaveButton();
		System.out.println("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");

		inClinicExperience.clickTodayAppointments();
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		inClinicExperience.selectVaccineAgent(consumptionAgent);

		//If Incorrect vaccine warning is displayed
		try {
			ProfilesPage.confirm_warning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}

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
		//String consentProviderSelected = ProfilesPage.consentProviderSelected(driver);
		//if(consentProviderSelected.equals("")) {
		//	consentProviderSelected = ProfilesPage.selectConsentProvider(driver, consentProvider);
		//}

		//System.out.println("/*42.---Click Save Consent Button --*/");

		//inClinicExperience.ClickSaveConsentButton();
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

		try {
			inClinicExperience.selectNotApprovedAdministrationReason();
		} catch(Exception ex) {
			System.out.println("Continue...");
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
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
	}

}
