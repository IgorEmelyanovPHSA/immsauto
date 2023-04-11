package bcvax.tests.ClinicInBox;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertFalse;

public class Historical_Immunization extends BaseTest {
	
	private String legalFirstName = "Maegan";
	private String legalLastName = "bcvaxvillage";
	private String legalMiddleName = "Tanya";
	private String pirSubmissionField = "Submitted";
	private String patwayStatusFieldValidation = "Pathway Status";
	private String pirSubmissionStatusFieldValidation = "PIR Submission Status";
	private ClinicInBoxPage clinicInBox;
	
	@Test(priority = 1)
	public void Validate_Historical_Immunization_PIR_Status_as_Clinician_CIB() throws Exception {
		TestcaseID = "225704"; //C225704 -- CIB - Historical Immunization Records - PIR Submission Status
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		loginPage.orgLoginAsPPHIS();
		clinicInBox = new ClinicInBoxPage(driver);
		log("/*2.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
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
		Thread.sleep(2000);
		ProfilesPage profilePage = new ProfilesPage(driver);
		log("/* 5.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		profilePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName);
		log("/* 6.----User found and Navigated to record page ---*/");
		log("/*7----Go to back to the Citizen Related Tab --*/");
		Thread.sleep(2000);
		profilePage.clickRelatedTab();
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

	//@Test(priority = 2)
	public void Can_Create_Historical_Immunization_Record_via_RelatedTab_as_Clinician_BCVAXDEVIT() throws Exception {
		//TestcaseID = "??????"; //C??????
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		loginPage.orgLoginAsPPHIS();
		clinicInBox = new ClinicInBoxPage(driver);
		log("/*2.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		if (clinicInBox.displayClinicInBoxApp()) {
			log("/*-- 3. User already on CIB App --*/");
		} else {
			log("/*-- 3.1. Navigate to CIB --*/");
			clinicInBox.cIBApp();
		}
		log("/*4.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		clinicInBox.SearchForCitizen(legalFirstName + " " + legalLastName);
		log("/*----5. select Citizen Maegan from search results --*/");
		clinicInBox.userClickCitizen();
		ProfilesPage profilePage = new ProfilesPage(driver);
		log("/*----6. Navigated to Person Account related tab ---*/");
		profilePage.clickRelatedTab();
		int historicalDosesbefore = Integer.parseInt(profilePage.getHistoricalDosesValue());
		log("/*----7. get historical doses Value + ---*/" + historicalDosesbefore);
		log("/*----8. Click Create Immunization Record ---*/ ");
		profilePage.clickToCreatHistoricalImmunizationRecord();
		log("/*-- 9---Click to select Agent --*/");
		profilePage.ClickAgentValue();
		log("/*-- 10--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		profilePage.SelectAgent();
		log("/*-- 11--- Select historical date from 6 months ago --*/");
		profilePage.selectHistoricalDateAndTime();
		log("/*-- 12--- Click Save Button --*/");
		profilePage.ClickSaveButton();
		int historicalDoses_after = Integer.parseInt(profilePage.getHistoricalDosesValue());
		log("/*-- 13. remaining doses After creating Historical Doses: -->" + historicalDoses_after);
		assertEquals(historicalDoses_after, historicalDosesbefore + 1);
//		Thread.sleep(2000);
//		log("/*-- 14. Navigate to ICE App--*/");
//		clinicInBox.selectICEFromApp();
//		Thread.sleep(2000);
//		log("/*15.----Close All previously opened Tab's --*/");
//		clinicInBox.closeAllTabs();
//		Thread.sleep(2000);
//		log("/*16.----Click register Tab --*/");
//		clinicInBox.clickRegisterTab();
//		Thread.sleep(2000);
//		log("/*17.----Search for Citizen --*/");
//		clinicInBox.SearchForCitizen("Adams BCVaxKenna");
//		Thread.sleep(2000);
//		log("/*18.----Search for User Adams --*/");
//		clinicInBox.userAdamsFound();
//		Thread.sleep(2000);
//		log("/*----19. Navigate to Person Account related tab ---*/");
//		clinicInBox.clickRelatedTab();
//		int historicalDosesbefore1 = Integer.parseInt(profilePage.getHistoricalDosesValue());
//		log("/*----20. get historical doses Value + ---*/" + historicalDosesbefore1);
//		Thread.sleep(2000);
//		log("/*21.---- Navigate to Home page --*/");
//		clinicInBox.clickHomePage();
//		Thread.sleep(2000);
//		log("/*22.----Close All previously opened Tab's --*/");
//		clinicInBox.closeAllTabs();
//		Thread.sleep(2000);
//		log("/*23.---the Home - Client Search showing up  --*/");
//		clinicInBox.validateHomePageShownUp();
//		Thread.sleep(2000);
//		log("/*24.----Enter PHN--*/");
//		String phnNumber = "9746172051";
//		clinicInBox.enterPNHToSearch(phnNumber);
//		Thread.sleep(2000);
//		log("/*25.--- Search by Clinic --*/");
//		clinicInBox.ClickSearchButtonHomePage();
//		Thread.sleep(2000);
//		log("/*26.--- Select user on Home Page and select user for historical Immunization --*/");
//		clinicInBox.ClickUserOnHomePage();
//		Thread.sleep(2000);
//		String user = profilePage.getUserDetailsOnVaccineAdministration();
//		log("/*-- 27. remaining doses Before: -->" + user);
//		String expected = "Adams BCVaxKenna";
//		assertEquals(expected, user);
//		Thread.sleep(2000);
//		log("/*28.--- Select add button for adding historical Immunization --*/");
//		profilePage.ClickAddBtntoAddHistoricalImmunization();
//		Thread.sleep(2000);
//		log("/*-- 29---Click to select Agent --*/");
//		profilePage.ClickAgentValue();
//		Thread.sleep(2000);
//		log("/*-- 30--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
//		profilePage.SelectAgent();
//		Thread.sleep(2000);
//		log("/*-- 31--- Select historical date from 6 months ago on Vaccine Administration page --*/");
//		profilePage.selectHistoricalDateAndTimeOnVaccineAdministration();
//		Thread.sleep(2000);
//		log("/*-- 32--- Click Save Button on Vaccine Admin page for historical doses --*/");
//		profilePage.ClickSaveButtonOnVaccineAdministrationPage();
//		Thread.sleep(2000);
//		log("/*33.----Close All previously opened Tab's --*/");
//		clinicInBox.closeAllTabs();
//		Thread.sleep(2000);
//		log("/*34.----Click register Tab --*/");
//		clinicInBox.clickRegisterTab();
//		Thread.sleep(2000);
//		log("/*35.----Search for Citizen --*/");
//		clinicInBox.SearchForCitizen("Adams BCVaxKenna");
//		Thread.sleep(2000);
//		log("/*36.----Search for User Adams --*/");
//		clinicInBox.userAdamsFound();
//		Thread.sleep(2000);
//		profilePage = new ProfilesPage(driver);
//		log("/*----37. Navigate to Person Account related tab ---*/");
//		profilePage.clickRelatedTab();
//		int historicalAfter1 = Integer.parseInt(profilePage.getHistoricalDosesValue());
//		log("/*----38. get historical doses Value + ---*/" + historicalAfter1);
//		Thread.sleep(2000);
//		assertEquals(historicalAfter1, historicalDosesbefore1 + 1);
	}

	//@Test(priority = 3)
	public void Can_Create_Historical_Immunization_Record_by_Vaccine_Administration_as_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "225761"; //C225761
	}

}
