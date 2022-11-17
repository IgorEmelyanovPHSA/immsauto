package bcvaxdevit.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DIWA_ICE extends BaseTest {
	
	
	@Test(testName = "Create DIWA Immunisation record without Appointments in ICE")
	public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_in_ICE_BCVAXDEVIT() throws Exception {
		TestcaseID = "223187"; //C223187
		log("Target Environment: "+ Utils.getTargetEnvironment());
		String citizenName = "Benoite BCVaxD'Hooge";
		//String nameToSearch = "Benoite Denna BCVaxD";
		//1.
		//String citizenName = "BCVaxChan John";
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

		log("/*1.----Login as an Clinician to ICE --*/");
		InClinicExperiencePage inClinicExperience = loginPage.loginAsClinician_DIWA_ICE();
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
		log("/*----5. Global Search for Participant account: " +citizenName +" ---*/");
		//inClinicExperience.SearchForCitizen(citizenName);
		inClinicExperience.SearchForCitizenAlternativeWay(citizenName);
		Thread.sleep(3000);
		//log("/*----6. select Citizen from search results --*/");
		//inClinicExperience.userClickCitizenNew(nameToSearch);
		//Thread.sleep(4000);
		log("/*---- 7. Navigate to Person Account related tab ---*/");
		inClinicExperience.clickRelatedTab();
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
		log("/*----13. Enter a Clinic Location: " +clinicLocation +"---*/");
		inClinicExperience.searchClinicLocation(clinicLocation);
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
		log("/*---18. click on continue editing button to continue with the flow ---*/");
		inClinicExperience.ContinueEditingButton();
		Thread.sleep(2000);
		log("/*---19. select date of Administration ---*/");
		if (inClinicExperience.selectDateOfAdministration())
			Thread.sleep(3000);

		log("/*---20. select Informed Consent Provider -> Auto Clinician DIWA_CIB ---*/");
		inClinicExperience.selectInformedConsentProvider("Auto Clinician DIWA_ICE");
		Thread.sleep(5000);

		log("/*---21. click Save Consent ---*/");
		inClinicExperience.clickSaveConsent();
		Thread.sleep(5000);

		log("/*---22. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");
		inClinicExperience.selectImmunizingAgentProvider("Auto Clinician DIWA_ICE");
		Thread.sleep(6000);

		log("/*---23. Click Show all lot numbers Checkbox---*/");
		inClinicExperience.clickShowAllLotNumbersCheckBox();
		Thread.sleep(2000);

		log("/*---24. click Lot Number dropdown component ---*/");
		inClinicExperience.clickLotNumberDropDown();
		Thread.sleep(2000);

		log("/*---25. Select SPIKEVAX (Moderna) -> Lot -->300042698 - Exp. 2021 June 18 ---*/");
		inClinicExperience.selectLot();
		Thread.sleep(2000);

		log("/*---26. Select Injection Site ---*/");
		inClinicExperience.selectInjectionSite();
		Thread.sleep(2000);
		log("/*---27. Select Dosage---*/");
		inClinicExperience.selectDosage();
		Thread.sleep(2000);
		log("/*---28. Save Immunization Information ---*/");
		inClinicExperience.saveImmunizationInformation();
		Thread.sleep(2000);
		log("/*---29. Confirm and Save Administration ---*/");
		inClinicExperience.confirmAndSaveAdministration();
		Thread.sleep(2000);
		log("/*---30. Vaccine Administration Summary Confirm and Save ---*/");
		inClinicExperience.summaryConfirmAndSave();
		Thread.sleep(2000);
		log("/*---31. Navigate to Related tab and Confirm new Imms Record is created ---*/");
		inClinicExperience.clickRelatedTab();
	}
	
}
