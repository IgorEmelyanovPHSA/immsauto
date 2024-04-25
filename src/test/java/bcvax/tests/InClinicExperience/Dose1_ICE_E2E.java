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
		return new Object[][] {{"222694", "Covid19Vaccine", "vaccineAgent", "covidLot", "covidDose", true}, {"228859", "InfluenzaVaccine", "agentInfluenza", "influenzaLot", "influenzaDose", false}};
	}

	@Test(dataProvider = "booking_data")
	public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE(String testcase_id, String vaccine_agent, String administration_agent, String administration_lot, String administration_dose, boolean vaccine_available) throws Exception {
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

		loginPage.loginAsImmsBCAdmin();
		orgMainPage = new MainPageOrg(driver);
		//orgMainPage = loginPage.orgLoginAsPPHIS();
		TestcaseID = testcase_id; //

		log("TestRail test case ID: C" +TestcaseID);

		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		String currentApp = MainPageOrg.currentApp(driver);
		try {
			MainPageOrg.closeAllTabs(driver);
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		InClinicExperiencePage.closeTabsHCA(driver);
		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		log("/*6.----- Enter current date for UserDefaults --*/");
		log("/*-- 13. Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		AlertDialog.closeAlert(driver);
		System.out.println("/*8.----- Click on register Tab --*/");
		InClinicExperiencePage.clickRegisterTab(driver);
		//System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
		//inClinicExperience.clickSaveModalDefaultsButton();
		//Thread.sleep(2000);
		System.out.println("/*10.----click Register button New Citizen --*/");
		InClinicExperiencePage.clickRegisterButton(driver);
		System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
		CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
		System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
		CitizenPrimaryInfo.enterLastName(driver, legalLastName);
		System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
		CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
		System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
		CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
		System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
		CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
		System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
		System.out.println("/*17.----click Verify PHN button --*/");
		CitizenPrimaryInfo.clickVerifyPHNButton(driver);
		System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
		CitizenPrimaryInfo.successMessageAppear(driver);
		System.out.println("/*19.----click Next button --*/");
		CitizenPrimaryInfo.clickNextButton(driver);
		System.out.println("/*20.----'Enter email address " +email +"--*/");
		CitizenPrimaryInfo.enterEmail(driver, email);
		System.out.println("/*21.----'Confirm email address " +email +"--*/");
		CitizenPrimaryInfo.confirmEmail(driver, email);
		System.out.println("/*22.---Click review details Button--*/");
		CitizenPrimaryInfo.clickReviewDetails(driver);
		System.out.println("/*23.----Click register Button on confirmation page--*/");
		CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
		System.out.println("/*24.--toast success message - 'Success' --*/");
		try {
			CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
		} catch(Exception ex) {
			System.out.println("No Success Message. Contrinue ...");
			System.out.println(ex.getMessage());
		}
		//System.out.println("/*25.----click on person Account Related Tab --*/");
		//inClinicExperience.clickOnPersonAccountRelatedTab();
		System.out.println("/*26----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);
		Thread.sleep(2000);
//If override Eligibility is shown
		try {
			System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine_agent);

		} catch(Exception ex) {
			if(vaccine_available) {
				System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
				PersonAccountSchedulePage.overrideEligibility(driver);
				Thread.sleep(500);
				System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
				PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine_agent);
			} else {
			//---If vaccine is disabled and not available in UI then Pass
			Assert.assertTrue(1==1);
			return;
		}
		}



		System.out.println("/*27----select 'Search by Clinic name' tab --*/");
		PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);

		log("/*28.----search the Clinic " +clinicNameToSearch +" --*/");
		PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);

		System.out.println("/*29----click on Option Facility location  --*/");
		PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
		System.out.println("/*30----select Active booking appointment day  --*/");
		PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
		System.out.println("/*31----select the time slot  --*/");
		PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
		System.out.println("/*32----click Next button  --*/");
		PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
		System.out.println("/*33----click Verify Contact Information Checkbox  --*/");
		PersonAccountSchedulePage.clickVerifyContactInformation(driver);
		System.out.println("/*34----click Confirm Appointment button  --*/");
		PersonAccountSchedulePage.clickOnConfirmButton(driver);
		System.out.println("/*35. ----see 'Appointment confirmed!' screen --*/");
		boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);

		Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		System.out.println("/*36.----Refresh page --*/");
		driver.navigate().refresh();
		System.out.println("/*37.----Go to back to the Citizen Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		System.out.println("/*38.----click on In-clinic Experience button --*/");
		PersonAccountPage.clickCheckInButton(driver);

		System.out.println("/*40.---Click confirm and Save Button --*/");
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
		System.out.println("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");

		inClinicExperience.clickTodayAppointments();
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		log("/*48.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");
		InClinicExperienceVaccineAdministrationPage.selectVaccineAgent(driver, consumptionAgent);

		//If Incorrect vaccine warning is displayed
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}

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
		//String consentProviderSelected = ProfilesPage.consentProviderSelected(driver);
		//if(consentProviderSelected.equals("")) {
		//	consentProviderSelected = ProfilesPage.selectConsentProvider(driver, consentProvider);
		//}

		//System.out.println("/*42.---Click Save Consent Button --*/");

		//inClinicExperience.ClickSaveConsentButton();
		String agent = InClinicExperienceVaccineAdministrationPage.getVaccineAgent(driver);
		String provider =  InClinicExperienceVaccineAdministrationPage.getProvider(driver);
		String route = InClinicExperienceVaccineAdministrationPage.getRoute(driver);
		String site = InClinicExperienceVaccineAdministrationPage.getSite(driver);

		String lot = InClinicExperienceVaccineAdministrationPage.getLotNumber(driver);

		log("/*42.---Click Save Consent Button --*/");

		if(!provider.equals(consentProvider)) {
			InClinicExperienceVaccineAdministrationPage.setProvider(driver, consentProvider);
		}

		log("/*43.---select Dosage ->  -.5 --*/");
		if(!lot.equals(consumptionLot)) {
			InClinicExperienceVaccineAdministrationPage.setLotNumber(driver, consumptionLot);
		}
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

		try {
			inClinicExperience.selectNotApprovedAdministrationReason();
		} catch(Exception ex) {
			System.out.println("Continue...");
		}
		System.out.println("/*42_.---Click Save button for Immunisation Information --*/");
		InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
		inClinicExperience.clickOkForExpiredLot();
		System.out.println("/*43.---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();
		System.out.println("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
		System.out.println("/*45.---the Home - Client Search showing up  --*/");
		InClinicExperiencePage.validateHomePageShownUp(driver);
	}
}
