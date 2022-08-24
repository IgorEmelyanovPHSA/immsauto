package bcvaxdevit.my.salesforce.com.Tests.ClinicInBox;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class HistoricalImmunization_via_RelatedTab extends BaseTest {

	@Test(priority = 1)
	public void Can_Create_Historical_Immunization_Record_via_RelatedTab_as_Clinician_BCVAXDEVIT() throws Exception {
		//TestcaseID = "??????"; //C??????
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIBWithParameters();
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
		clinicInBox.SearchForCitizen("Maegan bcvaxvillage");
		Thread.sleep(2000);
		log("/*----5. select Citizen Maegan from search results --*/");
		clinicInBox.userClickCitizen();
		Thread.sleep(2000);
		log("/*----6. Navigated to Person Account related tab ---*/");
		clinicInBox.clickRelatedTab();
		int historicalDosesbefore = Integer.parseInt(clinicInBox.getHistoricalDosesValue());
		log("/*----7. get historical doses Value + ---*/" + historicalDosesbefore);
		Thread.sleep(2000);
		log("/*----8. Click Create Immunization Record ---*/ ");
		clinicInBox.clickToCreatHistoricalImmunizationRecord();
		Thread.sleep(2000);
		log("/*-- 9---Click to select Agent --*/");
		clinicInBox.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 10--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		clinicInBox.SelectAgent();
		Thread.sleep(2000);
		log("/*-- 11--- Select historical date from 6 months ago --*/");
		clinicInBox.selectHistoricalDateAndTime();
		Thread.sleep(2000);
		log("/*-- 12--- Click Save Button --*/");
		clinicInBox.ClickSaveButton();
		Thread.sleep(2000);
		int historicalDoses_after = Integer.parseInt(clinicInBox.getHistoricalDosesValue());
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
		int historicalDosesbefore1 = Integer.parseInt(clinicInBox.getHistoricalDosesValue());
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
		String user = clinicInBox.getUserDetailsOnVaccineAdministration();
		log("/*-- 27. remaining doses Before: -->" + user);
		String expected = "Adams BCVaxKenna";
		assertEquals(expected, user);
		Thread.sleep(2000);
		log("/*28.--- Select add button for adding historical Immunization --*/");
		clinicInBox.ClickAddBtntoAddHistoricalImmunization();
		Thread.sleep(2000);
		log("/*-- 29---Click to select Agent --*/");
		clinicInBox.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 30--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		clinicInBox.SelectAgent();
		Thread.sleep(2000);
		log("/*-- 31--- Select historical date from 6 months ago on Vaccine Administration page --*/");
		clinicInBox.selectHistoricalDateAndTimeOnVaccineAdministration();
		Thread.sleep(2000);
		log("/*-- 32--- Click Save Button on Vaccine Admin page for historical doses --*/");
		clinicInBox.ClickSaveButtonOnVaccineAdministrationPage();
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
		log("/*----37. Navigate to Person Account related tab ---*/");
		clinicInBox.clickRelatedTab();
		int historicalAfter1 = Integer.parseInt(clinicInBox.getHistoricalDosesValue());
		log("/*----38. get historical doses Value + ---*/" + historicalAfter1);
		Thread.sleep(2000);
		assertEquals(historicalAfter1, historicalDosesbefore1 + 1);
	}

/*	@Test(priority = 2)
	public void Can_Create_Historical_Immunization_Record_by_Vaccine_Administration_as_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "225761"; //C225761
	}*/


}
