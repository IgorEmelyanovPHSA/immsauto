package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.tests.BaseTest;
import bcvax.pages.ClinicInBoxPage;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class DIWA_CIB extends BaseTest {
	
	
	@Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician() throws Exception {
		TestcaseID = "222289"; //C222289
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*----1. Login as an DIWA to CIB  --*/");
		ClinicInBoxPage clinicInBoxPage = loginPage.loginAsClinicianDIWACIB();
		Thread.sleep(5000);
		log("/*-- 2. Clinic In Box page displayed --*/");
		clinicInBoxPage.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(5000);
		log("/*----3. Close all previously opened Tabs --*/");
		clinicInBoxPage.closeAllTabs();
		Thread.sleep(2000);
		log("/*----4. Search for Participant account Maegan BCVaxVillage ---*/");
		clinicInBoxPage.SearchDIWACitizen("Maegan Tanya bcvaxvillage");
		Thread.sleep(2000);
		log("/*----5. select Citizen from search results --*/");
		clinicInBoxPage.userClickCitizen();
		Thread.sleep(4000);
		log("/*----6. Navigated to Person Account related tab ---*/");
		clinicInBoxPage.clickRelatedTab();
		log("/*----7. Click Create Immunization Record ---*/");
		clinicInBoxPage.clickCreatImmunizationRecord();
		Thread.sleep(4000);
		log("/*----8. Click confirm Button on the popup window---*/");
		clinicInBoxPage.clickConfirmButton();
		Thread.sleep(3000);
		log("/*----9. Select an Option ---*/)");
		clinicInBoxPage.clickSelectAnOptionDropdown();
		Thread.sleep(3000);
		log("/*----10. Select COVID19-mRNA as an Option  ---*/");
		clinicInBoxPage.selectOption("COVID19-mRNA");
		Thread.sleep(3000);
		log("/*----11. Enter a Clinic Location --> All Ages - Atlin Health Centre ---*/");
		clinicInBoxPage.searchClinicLocation("All Ages - Atlin Health Centre");
		Thread.sleep(3000);
		log("/*---12. Select a Date and Time of Administration ---*/");
		clinicInBoxPage.clickTimeBox();
		Thread.sleep(3000);
		log("/*---13. Click Record Immunization ---*/");
		clinicInBoxPage.clickRecordImmunization();
		Thread.sleep(3000);
		if (clinicInBoxPage.clickPopupYesButtonIfDisplayed())
			log("/*---13.1. Pop up window is displayed and clicked  ---*/");
		Thread.sleep(5000);
		log("/*---14. select date of Administration ---*/");
		if (clinicInBoxPage.selectDateOfAdministration())
			Thread.sleep(3000);

		log("/*---15. select Informed Consent Provider -> Auto Clinician DIWA_CIB  ---*/");
		clinicInBoxPage.selectInformedConsentProvider("Auto Clinician DIWA_CIB");
		Thread.sleep(5000);

		log("/*---16. click Save Consent button ---*/");
		clinicInBoxPage.clickSaveConsent();
		Thread.sleep(5000);

		log("/*---17. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");
		clinicInBoxPage.selectImmunizingAgentProvider("Auto Clinician DIWA_CIB");
		Thread.sleep(6000);

		log("/*---18. Click Show all lot numbers Checkbox ---*/");
		clinicInBoxPage.clickShowAllLotNumbersCheckBox();
		Thread.sleep(2000);

		log("/*---19. click Lot Number DropDown component ---*/");
		clinicInBoxPage.clickLotNumberDropDown();
		Thread.sleep(2000);

		log("/*---20. Select SPIKEVAX (Moderna) ->Lot --> 300042698 - Exp. 2021 June 18 ---*/");
		clinicInBoxPage.selectLot();
		Thread.sleep(2000);

		log("/*---21. Select Injection Site ---*/");
		clinicInBoxPage.selectInjectionSite();
		Thread.sleep(2000);
		log("/*---22. Select Dosage---*/");
		clinicInBoxPage.selectDosage();
		Thread.sleep(2000);
		log("/*---23. Save Immunization Information ---*/");
		clinicInBoxPage.saveImmunizationInformation();
		Thread.sleep(2000);
		log("/*---24. Confirm and Save Administration ---*/");
		clinicInBoxPage.confirmAndSaveAdministration();
		Thread.sleep(2000);
		log("/*---25. Vaccine Administration Summary Confirm and Save ---*/");
		clinicInBoxPage.summaryConfirmAndSave();
		Thread.sleep(2000);
		log("/*---26. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		clinicInBoxPage.clickRelatedTab();
		Thread.sleep(2000);
	}
	
}