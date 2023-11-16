package bcvax.tests.ClinicInBox;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertFalse;

public class Historical_Immunization extends BaseTest {

	private String legalFirstName = "Rochester";
	private String legalLastName = "BCVaxStonbridge";
	private String personal_health_nunber = "9746173132";
	private String date_of_birth = "Apr 20, 1945";
	private String postal_code = "V1Y8U9";
	private String legalMiddleName = "Sheelagh";
	private String pirSubmissionField = "Submitted";
	private String patwayStatusFieldValidation = "Pathway Status";
	private String pirSubmissionStatusFieldValidation = "PIR Submission Status";
	private ClinicInBoxPage clinicInBox;
	MainPageOrg orgMainPage;
	
	@Test(priority = 1)
	public void Validate_Historical_Immunization_PIR_Status_as_Clinician_CIB() throws Exception {
		TestcaseID = "225704"; //C225704 -- CIB - Historical Immunization Records - PIR Submission Status
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		orgMainPage = loginPage.orgLoginAsPPHIS();
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
		}
		orgMainPage.closeAllTabs();
		Thread.sleep(2000);
		log("/*2.----Close All previously opened Tab's --*/");
		orgMainPage.globalSearch(legalFirstName + " " + legalMiddleName + " " + legalLastName);
		Thread.sleep(2000);
		ProfilesPage profilePage = new ProfilesPage(driver);
		log("/* 5.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		profilePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName);
		log("/* 6.----User found and Navigated to record page ---*/");
		log("/*7----Go to back to the Citizen Related Tab --*/");
		Thread.sleep(2000);
		profilePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*8----Navigate to Historical Immunization Records --*/");
		profilePage.navigateToHistoricalImmunizationRecords();
		String field = profilePage.pirSubmissionStatusFieldIsDisplayed();
		log("/*9---- " + field + "is displayed --*/");
		int record_num = 1;
		String status = profilePage.pirSubmissionFieldStatus(record_num);
		log("/*10---- Pir Submission Field status is: " + status + " --*/");
		assertFalse(status.isEmpty());
		log("/*11---- Click Historical Immunization record --*/");

		profilePage.ClickHistoricalImmunizationRecord(record_num);
		Thread.sleep(2000);
		ImmunizationPage immunizationPage = new ImmunizationPage(driver);
		Thread.sleep(2000);
		String pirSubmissionStatus = immunizationPage.validatePirSubmissionStatusFieldIsDisplayed();
		log("/*12---- Field " + pirSubmissionStatus + "is displayed --*/");
		assertEquals(pirSubmissionStatusFieldValidation, pirSubmissionStatus);
		String pathwayStatus = immunizationPage.validatePathwayStatusFieldIsDisplayed();
		log("/*13---- Field " + pathwayStatus + "is displayed --*/");
		assertEquals(patwayStatusFieldValidation, pathwayStatus);
		log("/*14---- remediation needed checkbox is not checked --*/");
		immunizationPage.remidiationNeededCheckBox();
		String pirId = immunizationPage.pirImmunizationId();
		log("/*15---- Pir ID is: " + pirId + "--*/");
		assertNotNull(pirId);
		String pirSubmissionStatusFieldValue = immunizationPage.pirSubmissionStatusFieldValue();
		log("/*16---- Pir Submission Field status is: " + pirSubmissionStatusFieldValue + " --*/");
		//assertEquals(pirSubmissionField, pirSubmissionStatusFieldValue);
		assertEquals(status, pirSubmissionStatusFieldValue);

	}
}
