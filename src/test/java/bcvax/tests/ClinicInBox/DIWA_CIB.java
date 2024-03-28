package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class DIWA_CIB extends BaseTest {
	String env;
	String consumptionRoute;
	Map<String, Object> testData;
	private String legalFirstName = "Rawley";
	private String legalLastName = "BCVaxIsmirnioglou";
	private String legalMiddleName = "Marijo";
	private String personal_health_number = "9746173039";
	private String date_of_birth = "1959-01-23";
	private String postal_code = "V2X9T1";
	String participant_name;
	String consentProvider;

	String clinic_location = "All Ages - Atlin Health Centre";
	MainPageOrg orgMainPage;
	@Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "222289"; //C222289
		env = Utils.getTargetEnvironment();

		//Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personal_health_nunber);

		testData = Utils.getTestData(env);
		log("Target Environment: "+ env);
		log("/*----1. Login as an DIWA to CIB  --*/");
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		consentProvider = String.valueOf(testData.get("consentProvider"));
		participant_name = legalFirstName + " " + legalMiddleName + " " + legalLastName;
		loginPage.loginAsImmsBCAdmin();
		ClinicInBoxPage clinicInBoxPage = new ClinicInBoxPage(driver);
		log("/*-- 2. Clinic In Box page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
		}

		log("/*----3. Close all previously opened Tabs --*/");
		clinicInBoxPage.closeAllTabs();
		Thread.sleep(500);
		MainPageOrg.selectFromNavigationMenu(driver, "Home");
		log("/*----4. Search for Participant account: " +participant_name +" ---*/");
		orgMainPage.globalSearch(participant_name);
		log("/*----5. select Citizen from search results --*/");
		ProfilesPage profilesPage = new ProfilesPage(driver);
		//profilesPage.openProfile(participant_name);
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

		log("/*----10. Select COVID19-mRNA as an Option  ---*/");
		DiwaImmunizationRecord.selectOption(driver, "COVID-19 mRNA");
		log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);
		log("/*---12. Select a Date and Time of Administration ---*/");
		DiwaImmunizationRecord.clickTimeBox(driver);
		log("/*---13. Click Record Immunization ---*/");
		DiwaImmunizationRecord.clickRecordImmunization(driver);
		Thread.sleep(2000);

		if (profilesPage.clickPopupYesButtonIfDisplayed())
			log("/*---13.1. Pop up window is displayed and clicked  ---*/");
		log("/*---12. Click X button on Diwa flow ---*/");
		//If Incorrect vaccine warning is displayed
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}
		profilesPage.clickToClose();

		log("/*---13. Validate message on clicking close button on modal popup ---*/");
		profilesPage.validateoopsMessage();
		log("/*---14. click on continue editing button to continue with the flow ---*/");
		profilesPage.ContinueEditingButton();
		log("/*---15. select Informed Consent Provider -> Auto Clinician DIWA_CIB  ---*/");

		try {
			DiwaImmunizationRecord.checkExistingConsent(driver);
		} catch(Exception ex) {
			System.out.println("No Checkbox. Continue...");
		}



		log("/*---17. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");

		try {
			DiwaImmunizationRecord.setProvider(driver, consentProvider);
		} catch(Exception ex) {
			DiwaImmunizationRecord.clickEditImmunizationInformation(driver);
			Thread.sleep(500);
			DiwaImmunizationRecord.setProvider(driver, consentProvider);
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
		profilesPage.expiredVaxHandler();
		///////

		log("/*---24. Confirm and Save Administration ---*/");
		profilesPage.confirmAndSaveAdministration();
		log("/*---25. Vaccine Administration Summary Confirm and Save ---*/");
		profilesPage.summaryConfirmAndSave();
		log("/*---26. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		PersonAccountPage.goToRelatedTab(driver);
	}
	
}