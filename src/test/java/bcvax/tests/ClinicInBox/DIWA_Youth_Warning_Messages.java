package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class DIWA_Youth_Warning_Messages extends BaseTest {
	String env;
	String consumptionRoute;
	Map<String, Object> testData;
	String participant_name;
	private String provider = "ImmsBCAdmin Auto";
	String clinic_location;
	MainPageOrg orgMainPage;
	Map<String, String> client_data;

//	@BeforeMethod
//	public void beforeMethod() throws Exception {
//		env = Utils.getTargetEnvironment();
//		testData = Utils.getTestData(env);
//		String client_data_file = Utils.getClientsDataFile();
//		log("LOG " +client_data_file );
//		client_data = Utils.getTestClientData(client_data_file, "five_years_old");
//		clinic_location = String.valueOf(testData.get("diwaLocation"));
//		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
//		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
//		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
//		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
//		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
//	}


	// Trade name	           Dosage	6 mos - 5 yrs		6-11 yrs		12+ yrs
	// NewSPIKEVAXTestLot001	0.25	No warning			No warning		Warning
	// NewSPIKEVAXTestLot001	0.5  	Warning				Warning			No warning
//	@DataProvider(name = "loginData")
//	public Object[][] loginDataProvider() {
//		return new Object[][] {
//				{"under5year", "NewSPIKEVAXTestLot001", "0.25", false, "0.5", true},
//				{"over6under11year", "NewSPIKEVAXTestLot001", "0.25", false, "0.5", true},
//				{"over12years", "NewSPIKEVAXTestLot001", "0.25", true, "0.5", false}
//		};
//	}

	@DataProvider(name = "userData")
	public Object[][] loginDataProvider() {
		return new Object[][] {
				{"under5year", "NewSPIKEVAXTestLot001", "0.25", false, "0.5", true},
			//	{"over6under11year", "NewSPIKEVAXTestLot001", "0.25", false, "0.5", true},
				{"over12years", "NewSPIKEVAXTestLot001", "0.25", true, "0.5", false}
		};
	}


	@Test(priority = 1, dataProvider = "userData")
	public void Warning_Messages_Check_For_Youth_Vaccinations_DIWA(String client, String lot, String dose1, boolean warningFlag1,
																   String dose2, boolean warningFlag2) throws Exception {
		TestcaseID = "271134"; //C271134
		env = Utils.getTargetEnvironment();
		log("Target Environment: " + env);
		log("Test Case Id: " + "C" + TestcaseID);
		log("Test for: " +client);
		//Data setup and cleanUp before test
		testData = Utils.getTestData(env);
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, client);
		clinic_location = String.valueOf(testData.get("diwaLocation"));
		consumptionRoute = String.valueOf(testData.get("routeConsumption"));
		participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));

		log("---1. Login as an DIWA to CIB---");
		loginPage.loginAsImmsBCAdmin();
	//	loginPage.loginAsPPHIS();

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

		log("---8. Select COVID-19 mRNA as an Option  ---");
		DiwaImmunizationRecord.selectAgent(driver, "COVID-19 mRNA");

		log("---9. Enter a Clinic Location: " + clinic_location);
		DiwaImmunizationRecord.searchClinicLocation(driver, clinic_location);

		log("---10. Select a Date and Time of Administration---");
		DiwaImmunizationRecord.clickTimeBox(driver);

		log("---11. Click Record Immunization ---*/");
		DiwaImmunizationRecord.clickRecordImmunization(driver);

		log("---12. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---");
		try {
			DiwaImmunizationRecord.setProvider(driver, provider);
		} catch (Exception ex) {
			DiwaImmunizationRecord.clickEditImmunizationInformation(driver);
			Thread.sleep(500);
			DiwaImmunizationRecord.setProvider(driver, provider);
		}

		log("---13. Click Show all lot numbers Checkbox ---");
		DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);

		log("---14. Select lot: " +lot);
		DiwaImmunizationRecord.setLotNumber(driver, lot);

		log("---15. Select Dosage: " +dose1);
		DiwaImmunizationRecord.setDosage(driver, dose1);
		Thread.sleep(1500);

		log("---16. Validate present yes/no warning message, flag value: " +warningFlag1) ;
		Assert.assertTrue(warningFlag1 == DiwaImmunizationRecord.youthWarningMessage(driver), "The two boolean values are not equal!");

		log("---17. Update: Select Dosage: " +dose2);
		DiwaImmunizationRecord.setDosage(driver, dose2);
		Thread.sleep(1500);

		log("---18. Validate present yes/no warning message, flag value: " +warningFlag2) ;
		Assert.assertTrue(warningFlag2 == DiwaImmunizationRecord.youthWarningMessage(driver), "The two boolean values are not equal!");
	}

//	public void afterMethod() throws Exception {
//		log("--- AFTER METHOD TO DELETE THE CREATED DATA ---");
//		Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
//		Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
//		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
//		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
//	}

}