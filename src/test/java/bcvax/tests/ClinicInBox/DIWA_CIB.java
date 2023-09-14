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
	private String personal_health_nunber = "9746173039";
	private String date_of_birth = "1959-01-23";
	private String postal_code = "V2X9T1";
	//String participant_name = "Rawley BCVaxIsmirnioglou";
	//String participant_name = "Ping an Penelope BCVaxZhang";
	String participant_name;

	String clinic_location = "All Ages - Atlin Health Centre";
	MainPageOrg orgMainPage;
	@Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "222289"; //C222289
		env = Utils.getTargetEnvironment();
		testData = Utils.getTestData(env);
		log("Target Environment: "+ env);
		log("/*----1. Login as an DIWA to CIB  --*/");
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		participant_name = String.valueOf(testData.get("diwaCitizen"));
		ClinicInBoxPage clinicInBoxPage = loginPage.loginAsClerk();
		CommonMethods commonMethods = new CommonMethods(getDriver());
		MainPageOrg mainPageOrg = new MainPageOrg(driver);
		log("/*-- 2. Clinic In Box page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
		}
		log("/*----3. Close all previously opened Tabs --*/");
		clinicInBoxPage.closeAllTabs();
		log("/*----4. Search for Participant account: " +participant_name +" ---*/");
		mainPageOrg.globalSearch(participant_name);
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
		profilesPage.clickSelectAnOptionDropdown();
		log("/*----10. Select COVID19-mRNA as an Option  ---*/");
		profilesPage.selectOption("COVID19-mRNA");
		log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		profilesPage.searchClinicLocation(clinic_location);
		log("/*---12. Select a Date and Time of Administration ---*/");
		profilesPage.clickTimeBox();
		log("/*---13. Click Record Immunization ---*/");
		profilesPage.clickRecordImmunization();
		Thread.sleep(2000);

		if (profilesPage.clickPopupYesButtonIfDisplayed())
			log("/*---13.1. Pop up window is displayed and clicked  ---*/");
		log("/*---12. Click X button on Diwa flow ---*/");
		//If Incorrect vaccine warning is displayed
		try {
			ProfilesPage.confirm_warning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}
		profilesPage.clickToClose();

		log("/*---13. Validate message on clicking close button on modal popup ---*/");
		profilesPage.validateoopsMessage();
		log("/*---14. click on continue editing button to continue with the flow ---*/");
		profilesPage.ContinueEditingButton();
		log("/*---15. select Informed Consent Provider -> Auto Clinician DIWA_CIB  ---*/");
		String consentProvider = profilesPage.consentProviderSelected();
		Thread.sleep(2000);
		String myConsentProvider = "Auto Bchcomclinician";
		if(consentProvider.equals("")) {
			consentProvider = profilesPage.selectConsentProvider(myConsentProvider);
			try {
				profilesPage.confirmConsentProvider(myConsentProvider);
			} catch(Exception ex) {
				System.out.println("Env Feature: No consent confirmation dialog. Continue...");
			}
		}
		profilesPage.selectConsentEffectiveToDate();
		log("/*---16. click Save Consent button ---*/");
		profilesPage.clickSaveConsent();

		log("/*---17. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");
		profilesPage.selectImmunizingAgentProvider(consentProvider);

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