package prodsuppqa.my.salesforce.com.Tests.ClinicInBox;

import prodsuppqa.my.salesforce.com.Pages.ClinicInBoxPage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class DIWA extends BaseTest {


	@Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_PRODSUPPQA() throws InterruptedException {
		TestcaseID = "221409"; //C221409
		System.out.println("/*----1. Login as an Clinician to CIB  --*/");
		ClinicInBoxPage clinicInBoxPage = loginPage.loginAsDIWA();
		Thread.sleep(2000);
		System.out.println("/*-- 2. Clinic In Box page displayed --*/");
		clinicInBoxPage.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*----3. Close all previously opened Tabs --*/");
		//clinicInBoxPage.closeAllTabs();
		Thread.sleep(2000);
		System.out.println("/*----4. Search for Participant account Maegan BCVaxVillage ---*/");
		clinicInBoxPage.SearchDIWACitizen("Maegan bcvaxvillage");
		Thread.sleep(2000);
		System.out.println("/*----5. select Citizen from search results --*/");
		clinicInBoxPage.userClickCitizen();
		Thread.sleep(4000);
		System.out.println("/*----6. Navigated to Person Account related tab ---*/");
		clinicInBoxPage.clickRelatedTab();
		System.out.println("/*----7. Click Create Immunization Record ---*/");
		clinicInBoxPage.clickCreatImmunizationRecord();
		Thread.sleep(4000);
		System.out.println("/*----8. Click confirm Button on the popup window---*/");
		//clinicInBoxPage.clickConfirmButton();
		//Thread.sleep(3000);
		System.out.println("/*----9. Select an Option ---*/)");
		clinicInBoxPage.clickSelectAnOptionDropdown();
		Thread.sleep(3000);
		System.out.println("/*----10. Select COVID19-mRNA as an Option  ---*/");
		clinicInBoxPage.selectOption("COVID19-mRNA");
		Thread.sleep(3000);
		System.out.println("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		clinicInBoxPage.searchClinicLocation("Atlin Health Centre");
		Thread.sleep(3000);
		System.out.println("/*---12. Select a Date and Time of Administration ---*/");
		clinicInBoxPage.clickTimeBox();
		Thread.sleep(3000);
		System.out.println("/*---13. Click Record Immunization ---*/");
		clinicInBoxPage.clickRecordImmunization();
		Thread.sleep(3000);
		if (clinicInBoxPage.clickPopupYesButtonIfDisplayed())
			System.out.println("/*---13.1. Pop up window is displayed and clicked  ---*/");
		Thread.sleep(5000);
		System.out.println("/*---14. select date of Administration ---*/");
		if (clinicInBoxPage.selectDateOfAdministration())
			Thread.sleep(2000);
		System.out.println("/*---15. Select Informed Consent Provider ---*/");
		//clinicInBoxPage.enterConsentEffectiveToDate();
		Thread.sleep(3000);
		clinicInBoxPage.selectInformedConsentProvider();
		Thread.sleep(2000);
		clinicInBoxPage.informedConsentProvider("JY-Auto Prodsupp");
		Thread.sleep(2000);
		clinicInBoxPage.clickSaveConsent();
		Thread.sleep(2000);
		System.out.println("/*---16. Click on the Immunization provider field ---*/");
		clinicInBoxPage.selectImmunizingAgentProvider();
		Thread.sleep(2000);
		System.out.println("/*---17. Select Provider-->JY Automation ---*/");
		clinicInBoxPage.selectProvider("JY-Auto Prodsupp");
		Thread.sleep(20000);
		System.out.println("/*---18. Select Lot Number Field value set a Lot ---*/");
		clinicInBoxPage.selectToSetLot();
		Thread.sleep(2000);
		System.out.println("/*---19. Select Lot -->300042460 - Exp. 2021 May 29 ---*/");
		clinicInBoxPage.selectLot("300042460 - Exp. 2021 May 29");
		Thread.sleep(2000);
		System.out.println("/*---20. Select Injection Site ---*/");
		clinicInBoxPage.selectInjectionSite();
		Thread.sleep(2000);
		System.out.println("/*---21. Select Dosage---*/");
		clinicInBoxPage.selectDosage();
		Thread.sleep(2000);
		System.out.println("/*---22. Save Immunization Information ---*/");
		clinicInBoxPage.saveImmunizationInformation();
		Thread.sleep(2000);
		System.out.println("/*---23. Confirm and Save Administration ---*/");
		clinicInBoxPage.confirmAndSaveAdministration();
		Thread.sleep(2000);
		System.out.println("/*---24. Vaccine Administration Summary Confirm and Save ---*/");
		clinicInBoxPage.summaryConfirmAndSave();
		Thread.sleep(2000);
		System.out.println("/*---25. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		clinicInBoxPage.clickRelatedTab();
		Thread.sleep(2000);
	}

}