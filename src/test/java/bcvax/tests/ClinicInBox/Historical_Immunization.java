package bcvax.tests.ClinicInBox;

import bcvax.pages.ImmunizationPage;
import bcvax.pages.ProfilesPage;
import bcvax.tests.BaseTest;
import bcvax.pages.ClinicInBoxPage;
import bcvax.pages.Utils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Historical_Immunization extends BaseTest {
	
	private String legalFirstName = "Maegan";
	private String legalLastName = "bcvaxvillage";
	private String legalMiddleName = "Tanya";
	private String pirSubmissionField = "Submitted";
	private String patwayStatusFieldValidation = "Pathway Status";
	private String pirSubmissionStatusFieldValidation = "PIR Submission Status";
	
	@Test(priority = 1)
	public void Validate_Historical_Immunization_PIR_Status_as_Clinician_CIB() throws Exception {
		TestcaseID = "225704"; //C225704 -- CIB - Historical Immunization Records - PIR Submission Status
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		Thread.sleep(10000);
		log("/*2.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		if (clinicInBox.displayClinicInBoxApp()) {
			log("/*-- 3. User already on CIB App --*/");
		} else {
			log("/*-- 3.1. Navigate to CIB --*/");
			Thread.sleep(5000);
			clinicInBox.cIBApp();
			Thread.sleep(2000);
		}
		log("/*4.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		clinicInBox.SearchForCitizen(legalFirstName + " " + legalLastName);
		ProfilesPage profilePage = new ProfilesPage(driver);
		log("/* 5.----Search for " + legalFirstName + " " + legalLastName + " is Successful ---*/");
		Thread.sleep(2000);
		profilePage.userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName);
		log("/* 6.----User found and Navigated to record page ---*/");
		Thread.sleep(2000);
		log("/*7----Go to back to the Citizen Related Tab --*/");
		profilePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*8----Navigate to Historical Immunization Records --*/");
		profilePage.navigateToHistoricalImmunizationRecords();
		Thread.sleep(2000);
		String field = profilePage.pirSubmissionStatusFieldIsDisplayed();
		log("/*9---- " + field + "is displayed --*/");
		Thread.sleep(2000);
		String status = profilePage.pirSubmissionFieldStatus();
		log("/*10---- Pir Submission Field status is: " + status + " --*/");
		assertEquals(pirSubmissionField, status);
		Thread.sleep(5000);
		log("/*11---- Click Historical Immunization record --*/");
		int record_num = 1;
		profilePage.ClickHistoricalImmunizationRecord(record_num);
		ImmunizationPage immunizationPage = new ImmunizationPage(driver);
		Thread.sleep(5000);
		String pirSubmissionStatus = immunizationPage.validatePirSubmissionStatusFieldIsDisplayed();
		log("/*12---- Field " + pirSubmissionStatus + "is displayed --*/");
		assertEquals(pirSubmissionStatusFieldValidation, pirSubmissionStatus);
		Thread.sleep(2000);
		String pathwayStatus = immunizationPage.validatePathwayStatusFieldIsDisplayed();
		log("/*13---- Field " + pathwayStatus + "is displayed --*/");
		assertEquals(patwayStatusFieldValidation, pathwayStatus);
		Thread.sleep(2000);
		log("/*14---- remediation needed checkbox is not checked --*/");
		immunizationPage.remidiationNeededCheckBox();
		Thread.sleep(2000);
		String pirId = immunizationPage.pirImmunizationId();
		log("/*15---- Pir ID is: " + pirId + "--*/");
		assertNotNull(pirId);
		Thread.sleep(2000);
		String pirSubmissionStatusFieldValue = immunizationPage.pirSubmissionStatusFieldValue();
		log("/*16---- Pir Submission Field status is: " + pirSubmissionStatusFieldValue + " --*/");
		assertEquals(pirSubmissionField, pirSubmissionStatusFieldValue);
		Thread.sleep(2000);
		
	}

	//@Test(priority = 2)
	public void Can_Create_Historical_Immunization_Record_via_RelatedTab_as_Clinician_BCVAXDEVIT() throws Exception {
		//TestcaseID = "??????"; //C??????
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		Thread.sleep(10000);
		log("/*2.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		if (clinicInBox.displayClinicInBoxApp()) {
			log("/*-- 3. User already on CIB App --*/");
		} else {
			log("/*-- 3.1. Navigate to CIB --*/");
			Thread.sleep(5000);
			clinicInBox.cIBApp();
			Thread.sleep(2000);
		}
		log("/*4.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		clinicInBox.SearchForCitizen(legalFirstName + " " + legalLastName);
		log("/*----5. select Citizen Maegan from search results --*/");
		clinicInBox.userClickCitizen();
		Thread.sleep(2000);
		ProfilesPage profilePage = new ProfilesPage(driver);
		log("/*----6. Navigated to Person Account related tab ---*/");
		profilePage.clickRelatedTab();
		int historicalDosesbefore = Integer.parseInt(profilePage.getHistoricalDosesValue());
		log("/*----7. get historical doses Value + ---*/" + historicalDosesbefore);
		Thread.sleep(2000);
		log("/*----8. Click Create Immunization Record ---*/ ");
		profilePage.clickToCreatHistoricalImmunizationRecord();
		Thread.sleep(2000);
		log("/*-- 9---Click to select Agent --*/");
		profilePage.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 10--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		profilePage.SelectAgent();
		Thread.sleep(2000);
		log("/*-- 11--- Select historical date from 6 months ago --*/");
		profilePage.selectHistoricalDateAndTime();
		Thread.sleep(2000);
		log("/*-- 12--- Click Save Button --*/");
		profilePage.ClickSaveButton();
		Thread.sleep(2000);
		int historicalDoses_after = Integer.parseInt(profilePage.getHistoricalDosesValue());
		log("/*-- 13. remaining doses After creating Historical Doses: -->" + historicalDoses_after);
		assertEquals(historicalDoses_after, historicalDosesbefore + 1);
		Thread.sleep(2000);
		log("/*-- 14. Navigate to ICE App--*/");
		clinicInBox.selectICEFromApp();
		Thread.sleep(2000);
		log("/*15.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		log("/*16.----Click register Tab --*/");
		clinicInBox.clickRegisterTab();
		Thread.sleep(2000);
		log("/*17.----Search for Citizen --*/");
		clinicInBox.SearchForCitizen("Adams BCVaxKenna");
		Thread.sleep(2000);
		log("/*18.----Search for User Adams --*/");
		clinicInBox.userAdamsFound();
		Thread.sleep(2000);
		log("/*----19. Navigate to Person Account related tab ---*/");
		clinicInBox.clickRelatedTab();
		int historicalDosesbefore1 = Integer.parseInt(profilePage.getHistoricalDosesValue());
		log("/*----20. get historical doses Value + ---*/" + historicalDosesbefore1);
		Thread.sleep(2000);
		log("/*21.---- Navigate to Home page --*/");
		clinicInBox.clickHomePage();
		Thread.sleep(2000);
		log("/*22.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		log("/*23.---the Home - Client Search showing up  --*/");
		clinicInBox.validateHomePageShownUp();
		Thread.sleep(2000);
		log("/*24.----Enter PHN--*/");
		String phnNumber = "9746172051";
		clinicInBox.enterPNHToSearch(phnNumber);
		Thread.sleep(2000);
		log("/*25.--- Search by Clinic --*/");
		clinicInBox.ClickSearchButtonHomePage();
		Thread.sleep(2000);
		log("/*26.--- Select user on Home Page and select user for historical Immunization --*/");
		clinicInBox.ClickUserOnHomePage();
		Thread.sleep(2000);
		String user = profilePage.getUserDetailsOnVaccineAdministration();
		log("/*-- 27. remaining doses Before: -->" + user);
		String expected = "Adams BCVaxKenna";
		assertEquals(expected, user);
		Thread.sleep(2000);
		log("/*28.--- Select add button for adding historical Immunization --*/");
		profilePage.ClickAddBtntoAddHistoricalImmunization();
		Thread.sleep(2000);
		log("/*-- 29---Click to select Agent --*/");
		profilePage.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 30--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		profilePage.SelectAgent();
		Thread.sleep(2000);
		log("/*-- 31--- Select historical date from 6 months ago on Vaccine Administration page --*/");
		profilePage.selectHistoricalDateAndTimeOnVaccineAdministration();
		Thread.sleep(2000);
		log("/*-- 32--- Click Save Button on Vaccine Admin page for historical doses --*/");
		profilePage.ClickSaveButtonOnVaccineAdministrationPage();
		Thread.sleep(2000);
		log("/*33.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(2000);
		log("/*34.----Click register Tab --*/");
		clinicInBox.clickRegisterTab();
		Thread.sleep(2000);
		log("/*35.----Search for Citizen --*/");
		clinicInBox.SearchForCitizen("Adams BCVaxKenna");
		Thread.sleep(2000);
		log("/*36.----Search for User Adams --*/");
		clinicInBox.userAdamsFound();
		Thread.sleep(2000);
		profilePage = new ProfilesPage(driver);
		log("/*----37. Navigate to Person Account related tab ---*/");
		profilePage.clickRelatedTab();
		int historicalAfter1 = Integer.parseInt(profilePage.getHistoricalDosesValue());
		log("/*----38. get historical doses Value + ---*/" + historicalAfter1);
		Thread.sleep(2000);
		assertEquals(historicalAfter1, historicalDosesbefore1 + 1);
	}

	//@Test(priority = 3)
	public void Can_Create_Historical_Immunization_Record_by_Vaccine_Administration_as_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "225761"; //C225761
	}

}
