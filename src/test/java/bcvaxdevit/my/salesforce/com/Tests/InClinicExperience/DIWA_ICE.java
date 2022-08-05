package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DIWA_ICE extends BaseTest {
	
	
	@Test(testName = "Create DIWA Immunisation record without Appointments in ICE")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_in_ICE_BCVAXDEVIT() throws
			InterruptedException {
		TestcaseID = "223187"; //C223187
		log("/*1.----Login as an Clinician to ICE --*/");
		InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICE();
		Thread.sleep(5000);
		log("/*2.----In Clinic Experience(ICE) page displayed --*/");
		inClinicExperience.verifyIsICEpageDisplayed();
		Thread.sleep(5000);
		log("/*3.--- Navigate to In Clinic Experience App --*/");
		inClinicExperience.selectICEFromApp();
		Thread.sleep(5000);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		Thread.sleep(5000);
		log("/*----5. Search for Participant account Maegan BCVaxVillage ---*/");
		inClinicExperience.SearchForCitizen("Maegan bcvaxvillage");
		Thread.sleep(2000);
		log("/*----6. select Citizen from search results --*/");
		inClinicExperience.userClickCitizen();
		Thread.sleep(4000);
		inClinicExperience.clickRelatedTab();
		log("/*---- 7. Navigate to Person Account related tab ---*/");
		Thread.sleep(2000);
		log("/*-- 8. Create Immunization Record Button is Present on Layout --*/");
		inClinicExperience.ValidateCreateImmunizationRecordButtonIsDisplayed();
		Thread.sleep(2000);
		log("/*----9. Click to Create Immunization Record Button ---*/");
		inClinicExperience.clickCreatImmunizationRecord();
		Thread.sleep(4000);
		log("/*----10. Click confirm Button on the popup window---*/");
		inClinicExperience.clickConfirmButton();
		Thread.sleep(3000);
		log("/*----11. Select an Option from the DropDown ---*/)");
		inClinicExperience.clickSelectAnOptionDropdown();
		Thread.sleep(3000);
		log("/*----12. Select COVID19-mRNA as an Option  ---*/");
		inClinicExperience.selectOption("COVID19-mRNA");
		Thread.sleep(3000);
		log("/*----13. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		inClinicExperience.searchClinicLocation("All Ages - Atlin Health Centre");
		Thread.sleep(3000);
		log("/*---14. Select a Date and Time of Administration ---*/");
		inClinicExperience.clickTimeBox();
		Thread.sleep(3000);
		log("/*---15. Click Record Immunization ---*/");
		inClinicExperience.clickRecordImmunization();
		Thread.sleep(3000);
		if (inClinicExperience.clickPopupYesButtonIfDisplayed())
			log("/*---15.1. Pop up window is displayed and clicked  ---*/");
		Thread.sleep(5000);
		log("/*---16. Click X button on Diwa flow ---*/");
		inClinicExperience.clickToClose();
		Thread.sleep(2000);
		log("/*---17. Validate message on clicking close button on modal popup ---*/");
		inClinicExperience.validateoopsMessage();
		Thread.sleep(2000);
		log("/*---18. click on continue editing button to continuw with the flow ---*/");
		inClinicExperience.ContinueEditingButton();
		Thread.sleep(2000);
		log("/*---19. select date of Administration ---*/");
		if (inClinicExperience.selectDateOfAdministration())
			Thread.sleep(2000);
		log("/*---19.1. Select Informed Consent Provider ---*/");
		inClinicExperience.informedConsentProvider();
		Thread.sleep(2000);
		log("/*---20. Select Provider ---*/");
		inClinicExperience.selectProvider("Clinician ICE Automation");
		Thread.sleep(2000);
		log("/*---21. click Save Consent ---*/");
		inClinicExperience.clickSaveConsent();
		Thread.sleep(2000);
		log("/*---22. Click on the Immunization provider field ---*/");
		inClinicExperience.selectImmunizingAgentProvider();
		Thread.sleep(2000);
		log("/*---23. Select Provider--> Clinician ICE Automation ---*/");
		inClinicExperience.selectProvider("Clinician ICE Automation");
		Thread.sleep(2000);
		log("/*---24. Click Show all lot numbers Checkbox---*/");
		inClinicExperience.clickShowAllLotNumbers();
		Thread.sleep(2000);
		log("/*---25. Select Lot Number Field value set a Lot ---*/");
		inClinicExperience.selectToSetLot();
		Thread.sleep(2000);
		log("/*---26. Select Lot -->045D21A ---*/");
		inClinicExperience.selectLot("045D21A");
		Thread.sleep(2000);
		log("/*---27. Select Injection Site ---*/");
		inClinicExperience.selectInjectionSite();
		Thread.sleep(2000);
		log("/*---28. Select Dosage---*/");
		inClinicExperience.selectDosage();
		Thread.sleep(2000);
		log("/*---29. Save Immunization Information ---*/");
		inClinicExperience.saveImmunizationInformation();
		Thread.sleep(2000);
		log("/*---30. Confirm and Save Administration ---*/");
		inClinicExperience.confirmAndSaveAdministration();
		Thread.sleep(2000);
		log("/*---31. Vaccine Administration Summary Confirm and Save ---*/");
		inClinicExperience.summaryConfirmAndSave();
		Thread.sleep(2000);
		log("/*---32. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		inClinicExperience.clickRelatedTab();
	}
	
}
