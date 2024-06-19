package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.*;



@Listeners({TestListener.class})
public class DIWA_ICE extends BaseTest {
	MainPageOrg mainPageOrg;
	String env;
	Map<String, Object> testData;
	Map<String, String> client_data;
	String citizenName;
	private String vaccine_to_select = "COVID-19 mRNA";
	String consentProvider;
	private String lot_to_select;
	private String dosage_to_select;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "consent");
		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
	}

	@Test()
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_in_ICE() throws Exception {
		env = Utils.getTargetEnvironment();
		log("Target Environment: "+ Utils.getTargetEnvironment());
		testData = Utils.getTestData(env);
		citizenName = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
		consentProvider = String.valueOf(testData.get("consentProvider"));

		lot_to_select = String.valueOf(testData.get("covidLot"));
		dosage_to_select = String.valueOf(testData.get("covidDose"));
		String clinicLocation = "All Ages - Atlin Health Centre";
		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(getDriver());

		log("/*1.----Login --*/");
		loginPage.loginAsPPHIS();

		log("Login AS default user (Clinician to ICE)");
		TestcaseID = "223187"; //C223187

		log("/*2.--- Navigate to In Clinic Experience App --*/");
		mainPageOrg = new MainPageOrg(driver);
		String currentApp = MainPageOrg.currentApp(driver);
		if (!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			try {
				MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
			} catch(Exception ex) {
				Thread.sleep(5000);
				MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
			}
		}
		//log("/*3.----In Clinic Experience(ICE) page displayed --*/");
		//inClinicExperience.verifyIsICEpageDisplayed();

		log("/*4.----Close All previously opened Tab's --*/");
		InClinicExperiencePage.closeTabsHCA(driver);
		log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
		log("/*----6. select Citizen from search results --*/");
		MainPageOrg.search(driver, citizenName);

		log("/*---- 7. Navigate to Person Account related tab ---*/");
		PersonAccountPage.goToRelatedTab(driver);
		log("/*----9. Click to Create Immunization Record Button ---*/");
		PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);
		log("/*----10. Click confirm Button on the popup window---*/");
		try {
			log("/*----12. Select COVID19-mRNA as an Option  ---*/");
			DiwaImmunizationRecord.selectAgent(driver, vaccine_to_select);
		} catch(Exception ex) {
			PersonAccountPage.confirmNoForecastWarning(driver);
			DiwaImmunizationRecord.selectAgent(driver, vaccine_to_select);
		}


		log("/*----13. Enter a Clinic Location: " +clinicLocation +"---*/");
		DiwaImmunizationRecord.searchClinicLocation(driver, clinicLocation);
		log("/*---14. Select a Date and Time of Administration ---*/");
		DiwaImmunizationRecord.clickTimeBox(driver);
		log("/*---15. Click Record Immunization ---*/");
		DiwaImmunizationRecord.clickRecordImmunization(driver);

		//Just in case of Duplicated warning
		try {
			DiwaImmunizationRecord.clickPotentialDuplicateYes(driver);
		} catch(Exception ex) {
			;
		}
		log("/*---15.1. Pop up window is displayed and clicked  ---*/");
		log("/*---16. Click X button on Diwa flow ---*/");

		//If Incorrect vaccine warning is displayed
		try {
			PersonAccountPage.confirmNoForecastWarning(driver);
		} catch(Exception ex) {
			System.out.println("No Warning found");
		}

		log("/*---18. click on continue editing button to continue with the flow ---*/");

		try {
			PersonAccountRelatedPage.checkExistingConsent(driver);
		} catch(Exception ex) {
			System.out.println("No Checkbox. Continue...");
		}

		log("/*---22. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");
		//log("/*---19. select date of Administration ---*/");
		//profilesPage.selectDateOfAdministration();
		log("/*---20. select Informed Consent Provider -> Auto Clinician DIWA_ICE ---*/");
		try {
			DiwaImmunizationRecord.setProvider(driver, consentProvider);
		} catch(Exception ex) {
			DiwaImmunizationRecord.clickEditImmunizationInformation(driver);
			Thread.sleep(500);
			DiwaImmunizationRecord.setProvider(driver, consentProvider);
		}

		log("/*---23. Click Show all lot numbers Checkbox---*/");
		DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);

		log("/*---25. Select SPIKEVAX (Moderna) -> Lot -->300042698 - Exp. 2021 June 18 ---*/");
		DiwaImmunizationRecord.setLotNumber(driver, lot_to_select);

		log("/*---26. Select Injection Site ---*/");
		DiwaImmunizationRecord.setSite(driver, "Arm - Right deltoid");
		DiwaImmunizationRecord.setRoute(driver, "Intramuscular");
		log("/*---27. Select Dosage---*/");
		DiwaImmunizationRecord.setDosage(driver, dosage_to_select);
		log("/*---28. Save Immunization Information ---*/");
		DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);

		log("/*---29. Confirm and Save Administration ---*/");
		try {
			DiwaImmunizationRecord.clickConfirmAndSaveAdministration(driver);
		} catch(ElementClickInterceptedException ex) {
			DiwaImmunizationRecord.clickOkForExpiredLot(driver);
			DiwaImmunizationRecord.clickConfirmAndSaveAdministration(driver);
		}

		log("/*---30. Vaccine Administration Summary Confirm and Save ---*/");
		DiwaImmunizationRecord.clickSaveAdministrationSummary(driver);

		log("/*---31. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		PersonAccountPage.goToRelatedTab(driver);
	}
}