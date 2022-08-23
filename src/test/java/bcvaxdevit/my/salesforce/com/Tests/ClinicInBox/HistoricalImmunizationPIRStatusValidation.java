package bcvaxdevit.my.salesforce.com.Tests.ClinicInBox;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class HistoricalImmunizationPIRStatusValidation extends BaseTest {
	
	private String legalFirstName = "Maegan";
	private String legalLastName = "bcvaxvillage";
	private String legalMiddleName = "Tanya";
	private String pirSubmissionField = "Submitted";
	private String patwayStatusFieldValidation = "Pathway Status";
	private String pirSubmissionStatusFieldValidation = "PIR Submission Status";
	
	@Test
	public void Validate_Historical_Immunization_Status_as_Clinician_CIB_BCVAXDEVIT() throws Exception {
		TestcaseID = "225704";
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIBWithParameters();
		Thread.sleep(10000);
		log("/*2.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		if (clinicInBox.displayClinicInBoxApp()) {
			log("/*-- 3. User already on CIB App --*/");
		} else {
			log("/*-- 3.1. Navigate to CIB --*/");
			clinicInBox.cIBApp();
			Thread.sleep(2000);
		}
		log("/*4.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		clinicInBox.SearchForCitizen(legalFirstName + " " + legalLastName);
		log("/* 5.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		Thread.sleep(2000);
		clinicInBox.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName);
		log("/* 6.----User found and Navigated to record page ---*/");
		Thread.sleep(2000);
		log("/*7----Go to back to the Citizen Related Tab --*/");
		clinicInBox.clickRelatedTab();
		Thread.sleep(2000);
		log("/*8----Navigate to Historical Immunization Records --*/");
		clinicInBox.navigateToHistoricalImmunizationRecords();
		Thread.sleep(2000);
		String field = clinicInBox.pirSubmissionStatusFieldIsDisplayed();
		log("/*9---- " + field + "is displayed --*/");
		Thread.sleep(2000);
		String status = clinicInBox.pirSubmissionFieldStatus();
		log("/*10---- Pir Submission Field status is: " + status + " --*/");
		assertEquals(pirSubmissionField, status);
		Thread.sleep(2000);
		clinicInBox.ClickHistoricalImmunizationRecord();
		log("/*11---- Click Historical Immunization record --*/");
		Thread.sleep(5000);
		String pirSubmissionStatus = clinicInBox.validatePirubmissionStatusFieldIsDisplayed();
		log("/*12---- Field " + pirSubmissionStatus + "is displayed --*/");
		assertEquals(pirSubmissionStatusFieldValidation, pirSubmissionStatus);
		Thread.sleep(2000);
		String pathwayStatus = clinicInBox.validatePathwayStatusFieldIsDisplayed();
		log("/*13---- Field " + pathwayStatus + "is displayed --*/");
		assertEquals(patwayStatusFieldValidation, pathwayStatus);
		Thread.sleep(2000);
		clinicInBox.remidiationNeededCheckBox();
		log("/*14---- remediation needed checkbox is not checked --*/");
		Thread.sleep(2000);
		String pirId = clinicInBox.pirImmunizationId();
		log("/*15---- Pir ID is: " + pirId + "--*/");
		assertNotNull(pirId);
		Thread.sleep(2000);
		String pirSubmissionStatusFieldValue = clinicInBox.pirSubmissionStatusFieldValue();
		log("/*16---- Pir Submission Field status is: " + pirSubmissionStatusFieldValue + " --*/");
		assertEquals(pirSubmissionField, pirSubmissionStatusFieldValue);
		Thread.sleep(2000);
		
	}
	
}
