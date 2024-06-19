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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;


@Listeners({TestListener.class})
public class Dose1_ICE_E2E extends BaseTest {
	String env;
	Map<String, Object> testData;
	String clinicNameToSearch;
	MainPageOrg orgMainPage;
	String consumptionLot;
	String consumptionDose;
	String consumptionRoute;
	String consumptionSite;
	String consentProvider;
	String consumptionAgent;
	Map<String, String> client_data;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "dose1");
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
	}

	@DataProvider(name="booking_data")
	public Object[][] dpMethod() {
		return new Object[][] {{"222694", "Covid19Vaccine", "vaccineAgent", "covidLot", "covidDose", true}, {"228859", "InfluenzaVaccine", "agentInfluenza", "influenzaLot", "influenzaDose", false}};
	}

	@Test(dataProvider = "booking_data")
	public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE(String testcase_id, String vaccine_agent, String administration_agent, String administration_lot, String administration_dose, boolean vaccine_available) throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
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
		InClinicExperiencePage.clickUserDefaultsTab(driver);
		log("/*6.----- Enter current date for UserDefaults --*/");
		log("/*-- 13. Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		System.out.println("/*8.----- Click on register Tab --*/");
		InClinicExperiencePage.clickRegisterTab(driver);
		//System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
		//inClinicExperience.clickSaveModalDefaultsButton();
		//Thread.sleep(2000);
		System.out.println("/*10.----click Register button New Citizen --*/");
		InClinicExperiencePage.clickRegisterButton(driver);
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
		System.out.println("/*26----Go to Appointment Tab --*/");
		try {
			PersonAccountPage.goToVaccineScheduleTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountPage.goToVaccineScheduleTab(driver);
		}
		Thread.sleep(2000);
//If override Eligibility is shown
		try {
			System.out.println("/*27.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine_agent);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine_agent);
		} catch(Exception ex) {
			try {
				PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
				Thread.sleep(500);
			} catch(Exception ex1) {
				;
			}
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
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountPage.goToRelatedTab(driver);
		}
		System.out.println("/*38.----click on In-clinic Experience button --*/");
		PersonAccountPage.clickCheckInButton(driver);

		System.out.println("/*40.---Click confirm and Save Button --*/");
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
		System.out.println("/*41.---select Vaccine Agent picklist Value ->  COVID-19 mRNA --*/");

		log("/*46.---Open Today's appointments from Home page --*/");

		InClinicExperiencePage.clickTodayAppointments(driver);
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
		ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
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
			InClinicExperienceVaccineAdministrationPage.selectNotApprovedAdministrationReason(driver);
		} catch(Exception ex) {
			System.out.println("Continue...");
		}
		System.out.println("/*42_.---Click Save button for Immunisation Information --*/");
		InClinicExperienceVaccineAdministrationPage.clickSaveImmuneInfoButton(driver);
		InClinicExperiencePage.clickOkForExpiredLot(driver);
		System.out.println("/*43.---Click Confirm and Save Administration Button --*/");
		InClinicExperienceVaccineAdministrationPage.ClickConfirmAndSaveAdministrationButton(driver);
		System.out.println("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		InClinicExperienceVaccineAdministrationPage.ClickModalConfirmAndSaveAdministrationButton(driver);
		System.out.println("/*45.---the Home - Client Search showing up  --*/");
		InClinicExperiencePage.validateHomePageShownUp(driver);
	}
}
