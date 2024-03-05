package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Listeners({TestListener.class})
public class New_Consent_In_DIWA_Errors extends BaseTest {
	String env;
	String consumptionRoute;
	Map<String, Object> testData;
	private String legalFirstName = "Celestyna";
	private String legalLastName = "BCVaxHeffy";
	private String legalMiddleName = "Anastassia";
	private String personal_health_nunber = "9746173741";
	private String date_of_birth = "1962-07-09";
	private String postal_code = "V5Y9M1";
	String participant_name;
	String consentProvider;
	private String email = "accountToDelete@phsa.ca";
	private String consent_effective_date = "November 29, 2023";
	private String lot_to_select = "0486AA-CC01";
	private String dosage_to_select = "0.5";
	String clinic_location = "All Ages - Atlin Health Centre";
	MainPageOrg orgMainPage;
	String consent_agent = "COVID-19 mRNA";

	@Test(testName = "Verify Errors when creating New Consent Record from DIWA")
	public void Verify_Errors_When_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "278975";
		env = Utils.getTargetEnvironment();
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personal_health_nunber);
		testData = Utils.getTestData(env);
		log("Target Environment: "+ env);
		log("/*----1. Login as an DIWA to CIB  --*/");
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		participant_name = legalFirstName + " " + legalMiddleName + " " + legalLastName;
		loginPage.loginAsImmsBCAdmin();
		CommonMethods commonMethods = new CommonMethods(getDriver());
		log("/*-- 2. Clinic In Box page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
		}
		orgMainPage.selectFromNavigationMenu("Home");
		ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
		log("/*----3. Close all previously opened Tabs --*/");
		clinicInBoxPage.closeAllTabs();

		clinicInBoxPage.clickRegisterButton();
		log("/*5.----Enter First Name: " + legalFirstName +"--*/");
		CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
		log("/*6.----Enter Last Name: " + legalLastName +"--*/");
		CitizenPrimaryInfo.enterLastName(driver, legalLastName);
		log("/*6.----Enter Date of birth: " + date_of_birth +"--*/");
		CitizenPrimaryInfo.enterDateOfBirth(driver, date_of_birth);
		log("/*7.----Enter Postal code: " + postal_code +"--*/");
		CitizenPrimaryInfo.enterPostalCode(driver, postal_code);
		log("/*8.----Enter PHN: "+ personal_health_nunber +"--*/");
		CitizenPrimaryInfo.enterPHN(driver, personal_health_nunber);
		log("/*10.----click Verify PHN button --*/");
		CitizenPrimaryInfo.clickVerifyPHNButton(driver);
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		CitizenPrimaryInfo.successMessageAppear(driver);
		log("/*12.----click Next button --*/");
		CitizenPrimaryInfo.clickNextButton(driver);
		log("/*13.'Enter email address: " + email +"--*/");
		CitizenPrimaryInfo.enterEmail(driver, email);
		log("/*14.'Confirm email address: " + email +"--*/");
		CitizenPrimaryInfo.confirmEmail(driver, email);
		log("/*15.Click review details Button--*/");
		CitizenPrimaryInfo.clickReviewDetails(driver);
		log("/*16.Click register Button on confirmation page--*/");
		CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
		log("/*17.--toast success message - 'Success' --*/");
		CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

		log("/*----5. select Citizen from search results --*/");
		ProfilesPage profilesPage = new ProfilesPage(driver);
		//profilesPage.openProfile(participant_name);
		log("/*----6. Navigated to Person Account related tab ---*/");
		profilesPage.clickRelatedTab();
		log("/*----7. Click Create Immunization Record ---*/");
		profilesPage.clickCreateImmunizationRecord();
		log("/*----8. Click confirm Button on the popup window---*/");
		try {
			profilesPage.clickConfirmButton();
		} catch(Exception ex) {
			System.out.println("No Confirm dialog");
		}
		log("/*----9. Select an Option ---*/)");
		DiwaImmunizationRecord.clickSelectAnOptionDropdown(driver);
		log("/*----10. Select Pneumo-P-23 as an Option  ---*/");
		DiwaImmunizationRecord.selectOption(driver, "Pneumo-P-23");
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
		Assert.assertEquals(response_errors.get(0), "Complete this field.");
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