package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Listeners({TestListener.class})
public class Consent_In_DIWA_Flow extends BaseTest {
	String env;
	String consumptionRoute;
	Map<String, Object> testData;
	private String legalFirstName = "Gill";
	private String legalLastName = "BCVaxOrigan";
	private String legalMiddleName = "Ashely";
	private String personal_health_nunber = "9746172463";
	private String date_of_birth = "1915-02-14";
	private String postal_code = "V2T8T1";
	String participant_name;
	String consentProvider;
	private String email = "accountToDelete@phsa.ca";

	String clinic_location = "All Ages - Atlin Health Centre";
	MainPageOrg orgMainPage;
	@Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "273661";
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
		MainPageOrg mainPageOrg = new MainPageOrg(driver);
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
		clinicInBoxPage.enterFirstName(legalFirstName);
		log("/*6.----Enter Last Name: " + legalLastName +"--*/");
		clinicInBoxPage.enterLastName(legalLastName);
		log("/*6.----Enter Date of birth: " + date_of_birth +"--*/");
		clinicInBoxPage.enterDateOfBirth(date_of_birth);
		log("/*7.----Enter Postal code: " + postal_code +"--*/");
		clinicInBoxPage.enterPostalCode(postal_code);
		log("/*8.----Enter PHN: "+ personal_health_nunber +"--*/");
		clinicInBoxPage.enterPNH(personal_health_nunber);
		log("/*10.----click Verify PHN button --*/");
		clinicInBoxPage.clickVerifyPHNButton();
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		clinicInBoxPage.successMessageAppear();
		log("/*12.----click Next button --*/");
		clinicInBoxPage.clickNextButton();
		log("/*13.'Enter email address: " + email +"--*/");
		clinicInBoxPage.enterEmail(email);
		log("/*14.'Confirm email address: " + email +"--*/");
		clinicInBoxPage.confirmEmail(email);
		log("/*15.Click review details Button--*/");
		clinicInBoxPage.clickReviewDetails();
		log("/*16.Click register Button on confirmation page--*/");
		clinicInBoxPage.clickRegisterButtonOnConfirmationPage();
		log("/*17.--toast success message - 'Success' --*/");
		clinicInBoxPage.successRegisteredMessageAppear();

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
		log("/*----10. Select COVID19-mRNA as an Option  ---*/");
		DiwaImmunizationRecord.selectOption(driver, "Pneumo-P-23");
		log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
		log("/*---12. Select a Date and Time of Administration ---*/");
		DiwaImmunizationRecord.clickTimeBox(driver);
		log("/*---13. Click Record Immunization ---*/");
		DiwaImmunizationRecord.clickRecordImmunization(driver);
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

		for(String header : expected_headers) {
			Assert.assertTrue(actual_headers.contains(header), "Table doesn't contain header " + header);
		}

		AddConsentDialog.selectResponseGrantRadioButton(driver);
		AddConsentDialog.selectImmsBCProviderRadioButton(driver);
		AddConsentDialog.selectObtainedFromClient(driver);
		AddConsentDialog.selectFormOfConsentInPerson(driver);
		AddConsentDialog.selectInformedConsentProvider(driver, "ImmsBCAdmin Auto");
		String date_effective_from = AddConsentDialog.getConsentEffectiveDateFromSelected(driver);
		if(!date_effective_from.equals("Nov 29, 2023")) {
			AddConsentDialog.setConsentEffectiveDateFrom(driver, "Nov 29, 2023");
		}
		AddConsentDialog.clickNextButton(driver);

		String profile = AddConsentDialog.getProfile(driver);
		String phn = AddConsentDialog.getPHN(driver);
		String agent = AddConsentDialog.getAgent(driver);
		String response = AddConsentDialog.getResponse(driver);
		String provider_type = AddConsentDialog.getProviderType(driver);
		String obtained_from = AddConsentDialog.getInformedConsentObtainedFrom(driver);
		String form_of_consent = AddConsentDialog.getFormOfConsent(driver);
		String effective_from = AddConsentDialog.getConsentEffectiveFrom(driver);
		String effective_to = AddConsentDialog.getConsentEffectiveTo(driver);

		AddConsentDialog.click_create_consent_record(driver);
		boolean error_exists = AddConsentDialog.errorConfirmExists(driver);
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

		log("/*---15. select Informed Consent Provider -> Auto Clinician DIWA_CIB  ---*/");

		try {
			ProfilesPage.checkExistingConsent(driver);
		} catch(Exception ex) {
			System.out.println("No Checkbox. Continue...");
		}



		log("/*---17. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");

		try {
			profilesPage.selectImmunizingAgentProvider(consentProvider);
		} catch(Exception ex) {
			ProfilesPage.clickEditImmunizationInformation(driver);
			Thread.sleep(500);
			profilesPage.selectImmunizingAgentProvider(consentProvider);
		}

		log("/*---18. Click Show all lot numbers Checkbox ---*/");
		profilesPage.clickShowAllLotNumbersCheckBox();

		log("/*---19. click Lot Number DropDown component ---*/");
		profilesPage.clickLotNumberDropDown();

		log("/*---20. Select SPIKEVAX (Moderna) ->Lot --> 300042698 - Exp. 2021 June 18 ---*/");
		profilesPage.selectLot();
		//profilesPage.setRoute(consumptionRoute);
		log("/*---21. Select Injection Site ---*/");
		profilesPage.selectInjectionSite();
		log("/*---22. Select Dosage---*/");
		profilesPage.selectDosage();
		log("/*---23. Save Immunization Information ---*/");
		profilesPage.saveImmunizationInformation();

		//Click Ok if the lot is expired
		commonMethods.expiredVaxHandler();
		///////

		log("/*---24. Confirm and Save Administration ---*/");
		profilesPage.confirmAndSaveAdministration();
		log("/*---25. Vaccine Administration Summary Confirm and Save ---*/");
		profilesPage.summaryConfirmAndSave();
		log("/*---26. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		profilesPage.clickRelatedTab();
	}
	
}