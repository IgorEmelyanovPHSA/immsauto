package bcvaxdevit.my.salesforce.com.Tests.ClinicInBox;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {

	@Test(priority = 1)
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219918"; //C219918
		System.out.println("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		if (inClinicExperiencePage.displayIceApp()) {
			System.out.println("/*---- User already on ICE--*/");
		} else {
			System.out.println("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
		/*----Go to Register Tab ---*/
		System.out.println("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		System.out.println("/*----Search for Participant account ---*/");
		inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
		System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		if (!inClinicExperiencePage.userLampardFound()) {
			System.out.println("/*----User --> Hugues BCVaxLampard not found and return---*/");
		}
		while (inClinicExperiencePage.userLampardFound()) {
			System.out.println("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			System.out.println("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(2000);
			if (!inClinicExperiencePage.selectImmsRecord()) {
				System.out.println("/*----No Imms Record found and return---*/");
			} else {
				System.out.println("/*---- User navigated to Imms record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteImmsRecord();
				System.out.println("/*---- Imms record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			inClinicExperiencePage.clickRelatedTab();
			System.out.println("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
			Thread.sleep(5000);
			if (!inClinicExperiencePage.selectRERNRecord()) {
				System.out.println("/*----No RERN Record found and return---*/");
			} else {
				System.out.println("/*---- User navigated to RERN record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteRERNRecord();
				System.out.println("/*---- RERN record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			System.out.println("/*---- Navigated to Person Account related tab ---*/");
			inClinicExperiencePage.deletePersonAccount();
			System.out.println("/*---- Person Account deleted Successfully ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRegisterTab();
			Thread.sleep(5000);
			inClinicExperiencePage.closeOpenTabs();
			System.out.println("/*---- Close the deleted Person Account ---*/");
			Thread.sleep(2000);
			System.out.println("/*----Re Searching for the Participant account ---*/");
			inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
			System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		}
	}

	@Test(priority = 2)
	public void Can_Book_Dose2_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "222368";
		System.out.println("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		Thread.sleep(5000);
		System.out.println("/*2.----Clinic In Box(IPM) page displayed --*/");
		clinicInBox.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);
		System.out.println("/*----3. Search for Participant account Hugues BCVaxLampard ---*/");
		Thread.sleep(2000);
		clinicInBox.SearchDose2Citizen("Hugues BCVaxLampard");
		Thread.sleep(2000);
		//System.out.println("/*----4. select Citizen from search results --*/");
		//clinicInBox.clickDose2Citizen();
		//Thread.sleep(2000);


	}

	@Test(priority = 3)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
		TestcaseID = "219865"; //C219865
		System.out.println("Searching and Removing Citizen Duplicates BCVAXDEVIT");
		/*----Login as an Clinician In-Clinic Experience --*/
		System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginasPrecocondition();
		if (inClinicExperiencePage.displayIceApp()) {
			System.out.println("/*---- User already on ICE--*/");
		} else {
			System.out.println("/*---- Navigate to ICE APP --*/");
			inClinicExperiencePage.selectIceApp();
			Thread.sleep(2000);
		}
		/*----Go to Register Tab ---*/
		System.out.println("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		System.out.println("/*----Search for Participant account ---*/");
		inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
		System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		if (!inClinicExperiencePage.userLampardFound()) {
			System.out.println("/*----User --> Hugues BCVaxLampard not found and return---*/");
		}
		while (inClinicExperiencePage.userLampardFound()) {
			System.out.println("/*----User found and Navigated to record page ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRelatedTab();
			System.out.println("/*---- Navigated to Person Account related tab ---*/");
			Thread.sleep(2000);
			if (!inClinicExperiencePage.selectImmsRecord()) {
				System.out.println("/*----No Imms Record found and return---*/");
			} else {
				System.out.println("/*---- User navigated to Imms record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteImmsRecord();
				System.out.println("/*---- Imms record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			inClinicExperiencePage.clickRelatedTab();
			System.out.println("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
			Thread.sleep(5000);
			if (!inClinicExperiencePage.selectRERNRecord()) {
				System.out.println("/*----No RERN Record found and return---*/");
			} else {
				System.out.println("/*---- User navigated to RERN record ---*/");
				Thread.sleep(2000);
				inClinicExperiencePage.deleteRERNRecord();
				System.out.println("/*---- RERN record deleted Successfully ---*/");
				Thread.sleep(2000);
			}
			System.out.println("/*---- Navigated to Person Account related tab ---*/");
			inClinicExperiencePage.deletePersonAccount();
			System.out.println("/*---- Person Account deleted Successfully ---*/");
			Thread.sleep(2000);
			inClinicExperiencePage.clickRegisterTab();
			Thread.sleep(5000);
			inClinicExperiencePage.closeOpenTabs();
			System.out.println("/*---- Close the deleted Person Account ---*/");
			Thread.sleep(2000);
			System.out.println("/*----Re Searching for the Participant account ---*/");
			inClinicExperiencePage.SearchForCitizen("Hugues BCVaxLampard");
			System.out.println("/*----Search for Hugues BCVaxLampard is Successful ---*/");
		}
	}


}
