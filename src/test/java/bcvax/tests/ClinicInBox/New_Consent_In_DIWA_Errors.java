package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Listeners({TestListener.class})
public class New_Consent_In_DIWA_Errors extends BaseTest {
	String env;
	String consumptionRoute;
	Map<String, Object> testData;
	String participant_name;
	String consentProvider;
	private String consent_effective_date = "November 29, 2023";
	String clinic_location;
	MainPageOrg orgMainPage;
	String consent_agent = "COVID-19 mRNA";
	Map<String, String> client_data;
	@BeforeMethod
	public void beforeMethod() throws Exception {
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		clinic_location = String.valueOf(testData.get("diwaLocation"));
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "no_consent");
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
	}
	@Test(testName = "Verify Errors when creating New Consent Record from DIWA")
	public void Verify_Errors_When_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "278975";
		log("Target Environment: "+ env);
		log("Test Case Id: " + "C" + TestcaseID);
		log("/*----1. Login as an DIWA to CIB  --*/");
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
		loginPage.loginAsImmsBCAdmin();
		log("/*-- 2. Clinic In Box page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
		}
		MainPageOrg.selectFromNavigationMenu(driver, "Home");
		ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
		log("/*----3. Close all previously opened Tabs --*/");
		clinicInBoxPage.closeAllTabs();

		clinicInBoxPage.clickRegisterButton();
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
		log("/*----6. Navigated to Person Account related tab ---*/");
		PersonAccountPage.goToRelatedTab(driver);
		log("/*----7. Click Create Immunization Record ---*/");
		PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);
		log("/*----8. Click confirm Button on the popup window---*/");
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch(Exception ex) {
			System.out.println("No Confirm dialog");
		}
		log("/*----9. Select an Option ---*/)");
		DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
		log("/*----10. Select Pneumo-P-23 as an Option  ---*/");
		DiwaImmunizationRecord.selectAgent(driver, "Pneumo-P-23");
		log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
		log("/*---12. Select a Date and Time of Administration ---*/");
		DiwaImmunizationRecord.clickTimeBox(driver);
		log("/*---13. Click Record Immunization ---*/");
		DiwaImmunizationRecord.clickRecordImmunization(driver);
		boolean recordConsentBtnExists = DiwaImmunizationRecord.recordConsentBtnExists(driver);
		Assert.assertTrue(recordConsentBtnExists, "Record Consent Button doesn't exist");
		DiwaImmunizationRecord.clickRecordConsent(driver);
		boolean add_consent_dialog_exists = AddConsentDialog.dialogExists(driver);
		Assert.assertTrue(add_consent_dialog_exists, "Add Consent Dialog doesn't exist");
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

		for(String header : expected_headers) {
			Assert.assertTrue(actual_headers.contains(header), "Table doesn't contain header " + header);
		}

		//Verify Error when Response is not selected
		AddConsentDialog.selectImmsBCProviderRadioButton(driver);
		AddConsentDialog.selectObtainedFromClient(driver);
		AddConsentDialog.selectFormOfConsentInPerson(driver);
		AddConsentDialog.selectInformedConsentProvider(driver, consentProvider);
		String date_effective_from = AddConsentDialog.getConsentEffectiveDateFromSelected(driver);
		if(!date_effective_from.equals("Nov 29, 2023")) {
			AddConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
		}
		AddConsentDialog.clickNextButton(driver);
		String response_error = AddConsentDialog.getResponseMundatoryError(driver);
		Assert.assertEquals(response_error, "Please select a choice.");

		//Verify Error when Refusal Reason is missing
		AddConsentDialog.selectResponseRefuseRadioButton(driver);
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getResponseRefuseReasonMundatoryError(driver);
		Assert.assertEquals(response_error, "Please select a choice.");
		AddConsentDialog.clickCloseButton(driver);

		DiwaImmunizationRecord.clickRecordConsent(driver);
		add_consent_dialog_exists = AddConsentDialog.dialogExists(driver);
		Assert.assertTrue(add_consent_dialog_exists, "Add Consent Dialog doesn't exist");

		//Verify Error when Infromed Consent Obtained From is missing
		AddConsentDialog.selectResponseGrantRadioButton(driver);
		AddConsentDialog.selectImmsBCProviderRadioButton(driver);
		AddConsentDialog.selectFormOfConsentInPerson(driver);
		AddConsentDialog.selectInformedConsentProvider(driver, consentProvider);
		date_effective_from = AddConsentDialog.getConsentEffectiveDateFromSelected(driver);
		if(!date_effective_from.equals("Nov 29, 2023")) {
			AddConsentDialog.setConsentEffectiveDateFrom(driver, consent_effective_date);
		}
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getObtainedFromMundatoryError(driver);
		Assert.assertEquals(response_error, "Please select a choice.");

		//Verify Error when Parent/Gardian First/Last names are missing
		AddConsentDialog.selectObtainedFromClientGuardian(driver);
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getRelationshipToClientError(driver);
		Assert.assertEquals(response_error, "Please select a choice.");
		response_error = AddConsentDialog.getRelationshipFirstNameError(driver);
		Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");
		response_error = AddConsentDialog.getRelationshipLastNameError(driver);
		Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");

		//Verify Missing Consent Provider Error
		AddConsentDialog.selectObtainedFromClient(driver);
		AddConsentDialog.cleanupInformedConsentProvider(driver);
		AddConsentDialog.clickNextButton(driver);
		List<String> response_errors = AddConsentDialog.getConsentProviderMissingError(driver);
		Assert.assertTrue(response_errors.get(0).contains("Complete this field."));
		Assert.assertEquals(response_errors.get(1), "A value is required.");

		//Verify Missing Consent Provider Contact Error
		AddConsentDialog.selectNonImmsBCProviderRadioButton(driver);
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getConsentProviderContactMissingError(driver);
		Assert.assertEquals(response_error, "A value is required.");

		//Verify Missing Effective Date From Error
		AddConsentDialog.selectImmsBCProviderRadioButton(driver);
		AddConsentDialog.selectInformedConsentProvider(driver, consentProvider);
		AddConsentDialog.clearConsentEffectiveDateFrom(driver);
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getConsentEffectiveFromMundatoryError(driver);
		Assert.assertEquals(response_error, "Please enter some valid input. Input is not optional.");

		//Verify Effective Date From in Future Error
		LocalDate today_date = LocalDate.now();
		today_date = today_date.plusDays(1);
		DateTimeFormatter dtf_today = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
		String my_date = today_date.format(dtf_today);
		AddConsentDialog.setConsentEffectiveDateFrom(driver, my_date);
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getConsentEffectiveDateFromError(driver);
		Assert.assertEquals(response_error, "Effective From Date cannot be in the future and cannot overlap with the Effective to Date of an existing Consent record for the same Agent");

		//Verify Effective Date To Earlier than Effective Date From Error
		LocalDate from_date = LocalDate.now().minusDays(1);
		LocalDate to_date = from_date.minusDays(1);
		String my_from_date = from_date.format(dtf_today);
		String my_to_date = to_date.format(dtf_today);
		AddConsentDialog.setConsentEffectiveDateFrom(driver, my_from_date);
		AddConsentDialog.setConsentEffectiveDateTo(driver, my_to_date);
		AddConsentDialog.clickNextButton(driver);
		response_error = AddConsentDialog.getConsentEffectiveDateToError(driver);
		Assert.assertEquals(response_error, "Effective To Date must be greater than or equal to Effective From Date or blank");
	}
}