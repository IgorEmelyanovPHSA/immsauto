package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.pages.MainPageOrg;
import bcvax.tests.BaseTest;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import constansts.Apps;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;



@Listeners({TestListener.class})
public class DIWA_ICE extends BaseTest {
	MainPageOrg mainPageOrg;
	@DataProvider(name = "testData")
	public static Object[][] names() {return new Object[][]{{"John Yuan bo BCVaxChan"}};
	}

	@Test(dataProvider = "testData")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_in_ICE(String citizenName) throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		//1.
		//String citizenName = "John Yuan bo BCVaxChan";
		//2.
		//String citizenName = "BCVaxZhang Ping an";
		//3.
		//String citizenName = "BCVaxFlay Leontine";
		//4.
		//String citizenName = "BCVaxFuke Fawne";
		//5.
		//String citizenName = "BCVaxDrake Warren";
		//6.
		//String citizenName = "BCVaxPerrelli Coletta";
		//7.
		//String citizenName = "BCVaxKnightley Vasily";
		//8.
		//String citizenName = "BCVaxGilbride Kania";
		//9.
		//String citizenName = "BCVaxJancik Tab";
		//10.
		//String citizenName = "BCVaxAleksandrev Nanete";

		String clinicLocation = "All Ages - Atlin Health Centre";
		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(getDriver());

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				loginPage.loginAsImmsBCAdmin_DIWA_ICE();
				log("Login AS comunityqa_org_immsbc_admin");
				TestcaseID = "244854"; //C244854
				break;
			default:
				mainPageOrg = loginPage.orgLoginAsClinicianICE();
				log("Login AS default user (Clinician to ICE)");
				TestcaseID = "223187"; //C223187
		}

		log("/*2.--- Navigate to In Clinic Experience App --*/");
		String currentApp = mainPageOrg.currentApp();
		if (!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			mainPageOrg.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		}

		log("/*3.----In Clinic Experience(ICE) page displayed --*/");
		inClinicExperience.verifyIsICEpageDisplayed();

		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
		log("/*----6. select Citizen from search results --*/");
		mainPageOrg.globalSearch(citizenName);

		//inClinicExperience.SearchForCitizenAlternativeWay(citizenName);

		//inClinicExperience.userClickCitizen(citizenName);
		//Thread.sleep(4000);
		log("/*---- 7. Navigate to Person Account related tab ---*/");
		inClinicExperience.clickRelatedTab();
		log("/*-- 8. Create Immunization Record Button is Present on Layout --*/");
		inClinicExperience.ValidateCreateImmunizationRecordButtonIsDisplayed();
		log("/*----9. Click to Create Immunization Record Button ---*/");
		inClinicExperience.clickCreatImmunizationRecord();
		log("/*----10. Click confirm Button on the popup window---*/");
		inClinicExperience.clickConfirmButton();
		log("/*----11. Select an Option from the DropDown ---*/)");
		inClinicExperience.clickSelectAnOptionDropdown();
		log("/*----12. Select COVID19-mRNA as an Option  ---*/");
		inClinicExperience.selectOption("COVID19-mRNA");
		log("/*----13. Enter a Clinic Location: " +clinicLocation +"---*/");
		inClinicExperience.searchClinicLocation(clinicLocation);
		log("/*---14. Select a Date and Time of Administration ---*/");
		inClinicExperience.clickTimeBox();
		log("/*---15. Click Record Immunization ---*/");
		inClinicExperience.clickRecordImmunization();
		Thread.sleep(2000);
		if (inClinicExperience.clickPopupYesButtonIfDisplayed())
			log("/*---15.1. Pop up window is displayed and clicked  ---*/");
		log("/*---16. Click X button on Diwa flow ---*/");
		Thread.sleep(2000);
		inClinicExperience.clickToClose();
		log("/*---17. Validate message on clicking close button on modal popup ---*/");
		inClinicExperience.validateoopsMessage();
		log("/*---18. click on continue editing button to continue with the flow ---*/");
		inClinicExperience.ContinueEditingButton();
		log("/*---19. select date of Administration ---*/");
		inClinicExperience.selectDateOfAdministration();
		String  informed_consent_provider = "Clinician ICE Automation";
		log("/*---20. select Informed Consent Provider -> Auto Clinician DIWA_ICE ---*/");
		inClinicExperience.setInformedConsentProvider(informed_consent_provider);

		log("/*---21. click Save Consent ---*/");
		inClinicExperience.clickSaveConsent();
		Thread.sleep(2000);
		log("/*---22. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");
		inClinicExperience.setProvider(informed_consent_provider);

		log("/*---23. Click Show all lot numbers Checkbox---*/");
		inClinicExperience.clickShowAllLotNumbersCheckBox();

		log("/*---24. click Lot Number dropdown component ---*/");
		inClinicExperience.clickLotNumberDropDown();

		log("/*---25. Select SPIKEVAX (Moderna) -> Lot -->300042698 - Exp. 2021 June 18 ---*/");
		inClinicExperience.selectLot();

		log("/*---26. Select Injection Site ---*/");
		inClinicExperience.selectInjectionSite();
		log("/*---27. Select Dosage---*/");
		inClinicExperience.selectDosageDIWA();
		log("/*---28. Save Immunization Information ---*/");
		inClinicExperience.saveImmunizationInformation();
		Thread.sleep(2000);

		//Click Ok if the lot is expired
		inClinicExperience.clickOkForExpiredLot();
		Thread.sleep(2000);
		///////

		log("/*---29. Confirm and Save Administration ---*/");
		inClinicExperience.confirmAndSaveAdministration();
		Thread.sleep(2000);
		log("/*---30. Vaccine Administration Summary Confirm and Save ---*/");
		inClinicExperience.summaryConfirmAndSave();
		Thread.sleep(2000);
		log("/*---31. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		inClinicExperience.clickRelatedTab();
	}

	//@Test()
	public void Bulk_Create_From_CSV_DIWA_Immunisation_record_without_Appointments_as_Clinician_in_ICE() throws Exception {
		String DEFAULT_FOLDER_PATH = Paths.get(System.getProperty("user.dir"), "resources", "upload").toString();
		String[] csvCell;
		String csvFileName = "ListOfNames.csv";

		CSVReader csvReader = new CSVReaderBuilder(new FileReader(DEFAULT_FOLDER_PATH + "/" + csvFileName)).withSkipLines(0).build();
		ArrayList<String> namesListTotal = new ArrayList<String>();
		ArrayList<String> namesListPass = new ArrayList<String>();
		ArrayList<String> namesListFailed = new ArrayList<String>();

		while ((csvCell = csvReader.readNext()) != null) {
			String name = csvCell[0];
			namesListTotal.add(name);
		}

		log("Total number of names to be processed in namesListTotal: " + namesListTotal.size());

		int countFailed = 0;
		int countPass = 0;
		int totalCountArray = 0;

		for (int i = 0; i < namesListTotal.size(); i++) {
			totalCountArray += 1;
			log("Name will be used in DIWA testCase = " + namesListTotal.get(i));
			try {
				Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_in_ICE(namesListTotal.get(i));
				countPass += 1;
				namesListPass.add(namesListTotal.get(i));
			} catch (Exception ex) {
				countFailed += 1;
				namesListFailed.add(namesListTotal.get(i));
			}
			//Close Chrome window after each test
			driver.close();
			//Restart a clean Chrome window
			setUp();
		}

		log("Total number of names in TC: " + totalCountArray);
		log("Number of pass TC: " + countPass);
		log("Number of failed TC: " + countFailed);

		if (namesListFailed.size() > 0) {
			log("List of name's when TC failed: ");
			for (int i = 0; i < namesListFailed.size(); i++) {
				log("TC failed with name = " + namesListFailed.get(i));
			}
		}
	}
}