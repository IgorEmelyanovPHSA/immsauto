package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Listeners({TestListener.class})
public class New_Consent_In_DIWA_Flow extends BaseTest {
	String env;
	String consumptionRoute;
	Map<String, Object> testData;
	String participant_name;
	String consentProvider;
	private String consent_effective_date = "November 29, 2023";
	private String lot_to_select;
	private String dosage_to_select;
	String clinic_location;
	MainPageOrg orgMainPage;
	Map<String, String> client_data;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "new_consent");
		clinic_location = String.valueOf(testData.get("diwaLocation"));
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
	}

	@Test(priority = 1, testName = "Create DIWA Immunisation record without Appointments")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "273661";
		lot_to_select = String.valueOf(testData.get("pneumoLot"));
		dosage_to_select = String.valueOf(testData.get("pneumoDose"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");

		log("Target Environment: " + env);
		log("Test Case Id: " + "C" + TestcaseID);
		log("---Before Method API call to remove duplicate citizen participant account if found---");

		log("---1. Login as an DIWA to CIB---");
		loginPage.loginAsImmsBCAdmin();

		log("---2. Clinic In Box page displayed---");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = MainPageOrg.currentApp(driver);
		if (!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
		}
		MainPageOrg.selectFromNavigationMenu(driver, "Home");
		ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);

		log("---3. Close all previously opened Tabs---");
		clinicInBoxPage.closeAllTabs();
		clinicInBoxPage.clickRegisterButton();
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

		log("---4. Navigated to Person Account related tab---");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch (ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			PersonAccountPage.goToRelatedTab(driver);
		}

		log("---5. Click Create Immunization Record---");
		PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);
		log("---6. Click confirm Button on the popup window---");
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch (Exception ex) {
			System.out.println("No Confirm dialog");
		}
		log("---7. Select an Option ---");
		DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
		log("---8. Select Pneumo-P-23 as an Option  ---");
		DiwaImmunizationRecord.selectAgent(driver, "Pneumo-P-23");
		log("---9. Enter a Clinic Location: " + clinic_location);
		DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
		log("---10. Select a Date and Time of Administration---");
		DiwaImmunizationRecord.clickTimeBox(driver);
		log("---11. Click Record Immunization ---*/");
		DiwaImmunizationRecord.clickRecordImmunization(driver);
		boolean recordConsentBtnExists = DiwaImmunizationRecord.recordConsentBtnExists(driver);
		Assert.assertTrue(recordConsentBtnExists, "Record Consent Button doesn't exist");
		DiwaImmunizationRecord.clickRecordConsent(driver);
		boolean add_consent_dialog_exists = AddConsentDialog.dialogExists(driver);

		//Verify Headers in Infromed Consent Table
		List<String> expected_headers = new ArrayList<String>();
		expected_headers.add("Consent Number");
		expected_headers.add("Response");
		expected_headers.add("Agent");
		expected_headers.add("Consent Given By");
		expected_headers.add("Consent Given To");
		expected_headers.add("Consent Effective From Date");
		expected_headers.add("Consent Effective To Date");
		List<String> actual_headers = AddConsentDialog.getInformedConsentTableHeaders(driver);

		for (String header : expected_headers) {
			Assert.assertTrue(actual_headers.contains(header), "Table doesn't contain header " + header);
		}

		AddConsentDialog.selectResponseGrantRadioButton(driver);
		AddConsentDialog.selectImmsBCProviderRadioButton(driver);
		AddConsentDialog.selectObtainedFromClient(driver);
		AddConsentDialog.selectFormOfConsentInPerson(driver);
		AddConsentDialog.selectInformedConsentProvider(driver, consentProvider);
		String date_effective_from = AddConsentDialog.getConsentEffectiveDateFromSelected(driver);
		if (!date_effective_from.equals("Nov 29, 2023")) {
			AddConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
		}
		AddConsentDialog.clickNextButton(driver);

		String profile = AddConsentDialog.getProfile(driver);
		Assert.assertEquals(profile, client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName"));
		String phn = AddConsentDialog.getPHN(driver);
		Assert.assertEquals(phn, client_data.get("personalHealthNumber"));
		String agent = AddConsentDialog.getAgent(driver);
		Assert.assertEquals(agent, "Pneumo-P-23");
		String response = AddConsentDialog.getResponse(driver);
		Assert.assertEquals(response, "Grant");
		String provider_type = AddConsentDialog.getProviderType(driver);
		Assert.assertEquals(provider_type, "ImmsBC Provider (User)");
		String obtained_from = AddConsentDialog.getInformedConsentObtainedFrom(driver);
		Assert.assertEquals(obtained_from, "Client");
		String form_of_consent = AddConsentDialog.getFormOfConsent(driver);
		Assert.assertEquals(form_of_consent, "In Person");
		String effective_from = AddConsentDialog.getConsentEffectiveFrom(driver);
		Assert.assertEquals(effective_from, consent_effective_date);
		String effective_to = AddConsentDialog.getConsentEffectiveTo(driver);

		AddConsentDialog.click_create_consent_record(driver);
		boolean error_exists = AddConsentDialog.errorConfirmExists(driver);
		Assert.assertTrue(error_exists);
		AddConsentDialog.click_confirm_info(driver);

		AddConsentDialog.click_create_consent_record(driver);

		List<Map<String, WebElement>> consent_records = DiwaImmunizationRecord.getInformedConsentTable(driver);
		String consent_number_col = consent_records.get(1).get("Consent Number").getText();
		String response_col = consent_records.get(1).get("Response").getText();
		String agent_col = consent_records.get(1).get("Agent").getText();
		String given_by_col = consent_records.get(1).get("Consent Given By").getText();
		String given_to_col = consent_records.get(1).get("Consent Given To").getText();
		String effective_from_col = consent_records.get(1).get("Effective From Date").getText();
		String effective_to_col = consent_records.get(1).get("Effective To Date").getText();

		log("---12. Select Informed Consent Provider -> Auto Clinician DIWA_CIB ---");
		boolean record_consent_message_exists = DiwaImmunizationRecord.recordExistingConsentMessageExists(driver);
		boolean confirm_and_save_btn_status = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);


		log("---13. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---");
		try {
			DiwaImmunizationRecord.setProvider(driver, consentProvider);
		} catch (Exception ex) {
			DiwaImmunizationRecord.clickEditImmunizationInformation(driver);
			Thread.sleep(500);
			DiwaImmunizationRecord.setProvider(driver, consentProvider);
		}

		log("---14. Click Show all lot numbers Checkbox ---");
		DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);

		log("---15. click Lot Number DropDown component ---");
		DiwaImmunizationRecord.setLotNumber(driver, lot_to_select);

		//profilesPage.setRoute(consumptionRoute);
		log("--16. Select Injection Site ---");
		DiwaImmunizationRecord.setSite(driver, "Arm - Right deltoid");
		log("--17. Select Dosage---");
		DiwaImmunizationRecord.setDosage(driver, dosage_to_select);
		DiwaImmunizationRecord.setRoute(driver, "Intramuscular");
		log("---18. Save Immunization Information ---");
		DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);
		try {
			DiwaImmunizationRecord.clickOkForExpiredLot(driver);
		} catch (Exception ex) {
			//Do nothing
			;
		}
		confirm_and_save_btn_status = DiwaImmunizationRecord.confirmAndSaveButtonIsActive(driver);
		Assert.assertTrue(confirm_and_save_btn_status);
		log("---19. Confirm and Save Administration---");
		DiwaImmunizationRecord.clickConfirmAndSaveAdministration(driver);
		log("---20. Vaccine Administration Summary Confirm and Save---");
		DiwaImmunizationRecord.clickSaveAdministrationSummary(driver);
		Thread.sleep(2000);
		log("---21. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch (NotFoundException ex) {
			Thread.sleep(2000);
			PersonAccountPage.goToRelatedTab(driver);
		} catch (ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			PersonAccountPage.goToRelatedTab(driver);
		}
		//Clean up data
		afterMethod();
	}

	public void afterMethod() throws Exception {
		log("--- AFTER METHOD TO DELETE THE CREATED DATA ---");
		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
	}

}