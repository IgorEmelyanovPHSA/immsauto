package bcvaxuat.my.salesforce.com.Tests.Precondition;

import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;

public class CitizenDuplicates extends BaseTest {
	
	@Test(testName = "Login to InClinicExperience as Clinician and search for Dupls BCVAXDEVIT")
	public void Can_search_and_remove_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		log("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		log("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		if (inClinicExperiencePage.displayIceApp()) {
			log("/*---- User already on ICE--*/");
		} else {
			log("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
		/*----Go to Register Tab ---*/
		log("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		log("/*----Search for Participant account ---*/");
		inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
		log("/*----Search for Ludovika is Successful ---*/");
		if (!inClinicExperiencePage.userFound()) {
			log("/*----User --> Ludovika not found and return---*/");
		}
		while (inClinicExperiencePage.userFound()) {
			log("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(2000);
			if (!inClinicExperiencePage.selectImmsRecord()) {
				log("/*----No Imms Record found and return---*/");
			} else {
				log("/*---- User navigated to Imms record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteImmsRecord();
				log("/*---- Imms record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			inClinicExperiencePage.clickRelatedTab();
			log("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
			Thread.sleep(5000);
			if (!inClinicExperiencePage.selectRERNRecord()) {
				log("/*----No RERN Record found and return---*/");
			} else {
				log("/*---- User navigated to RERN record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteRERNRecord();
				log("/*---- RERN record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			log("/*---- Navigated to Person Account related tab ---*/");
			inClinicExperiencePage.deletePersonAccount();
			log("/*---- Person Account deleted Successfully ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRegisterTab();
			Thread.sleep(5000);
			inClinicExperiencePage.closeOpenTabs();
			log("/*---- Close the deleted Person Account ---*/");
			Thread.sleep(2000);
			log("/*----Re Searching for the Participant account ---*/");
			inClinicExperiencePage.SearchForCitizen("Ludovika BCVaxLimeburn");
			log("/*----Search for Ludovika is Successful ---*/");
		}
	}
	
}



