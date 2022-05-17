package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class Consumption extends BaseTest {
	
	ClinicInBoxPage clinicInBoxPage;
	
	@Test(priority = 1)
	public void Pre_conditions_step_Remove_Dups_Citizen_participant_account() throws InterruptedException {
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
		inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
		System.out.println("/*----Search for Dacia is Successful ---*/");
		if (!inClinicExperiencePage.userDaciaFound()) {
			System.out.println("/*----User --> Dacia not found and return---*/");
		}
		while (inClinicExperiencePage.userDaciaFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
			System.out.println("/*----Search for Dacia is Successful ---*/");
		}
	}
	
	@Test(priority = 2)
	public void Validate_Consumption_as_an_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "219969"; //C219969
		System.out.println("/*----1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();//
		Thread.sleep(2000);
		if (inClinicExperiencePage.displaySupplyConsolePage()) {
			System.out.println("/*----2. User already on Health Connect - Supply Console --*/");
		} else {
			System.out.println("/*----2.1. Navigate to Health Connect - Supply Console --*/");
			inClinicExperiencePage.selectHealthConnectApp();
			Thread.sleep(2000);
		}
		inClinicExperiencePage.closeTabsHCA();
		System.out.println("/*----3. Close all open tabs --*/");
		if (inClinicExperiencePage.supplyLocDisplayed()) {
			System.out.println("/*----4. User is already on Supply loc--*/");
		} else {
			System.out.println("/*----4.1. Click Dropdown Menu --*/");
			inClinicExperiencePage.clickDropdownMenu();
			Thread.sleep(5000);
			System.out.println("/*----4.2. Navigate and Select Supply Locations --*/");
			inClinicExperiencePage.selectSupplyLocationFromDropdown();
			Thread.sleep(2000);
		}
		System.out.println("/*----5. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		System.out.println("/*----6. Click and navigate to the supply container --> 'Medicago TradeName - MedicagoTestLot001'--*/");
		inClinicExperiencePage.selectSupplyContainer();
		Thread.sleep(2000);
		String remainingDoses = inClinicExperiencePage.getValueOfRemainingDoses();
		System.out.println("/*----7. remaining doses are: -->" + remainingDoses);
		Thread.sleep(2000);
		String remainingQty = inClinicExperiencePage.getValueOfRemainingQty();
		System.out.println("/*----8. remaining doses are: -->" + remainingQty);
		Thread.sleep(2000);
		inClinicExperiencePage.closeTabsHCA();
		System.out.println("/*----9. Close all open tabs --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.selectICEFromApp();
		System.out.println("/*----10. Navigate to In Clinic Experience App --*/");
		inClinicExperiencePage.clickRegisterTab();
		System.out.println("/*----11. Click on register Tab --*/");
		inClinicExperiencePage.closeTabsHCA();
		System.out.println("/*----12. Close any open Tabs--*/");
		inClinicExperiencePage.clickRegisterButton();
		System.out.println("/*----13. Register New User --> Dacia --*/");
		String firstName = "Dacia";
		inClinicExperiencePage.enterFirstName(firstName);
		System.out.println("/*14.----Enter First Name Dacia--*/");
		Thread.sleep(2000);
		String lastName = "Bcvaxdod";
		inClinicExperiencePage.enterLastName(lastName);
		System.out.println("/*15.----Enter Last Name Bcvaxdod--*/");
		Thread.sleep(2000);
		String dateOfBirth = "May 19, 1904";//1904-05-19
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		System.out.println("/*16.----Enter Date of birth--*/");
		Thread.sleep(2000);
		String postalCode = "V7N3K1";
		inClinicExperiencePage.enterPostalCode(postalCode);
		System.out.println("/*17.----Enter Postal code--*/");
		Thread.sleep(2000);
		String phnNumber = "9746172456";
		inClinicExperiencePage.enterPNH(phnNumber);
		System.out.println("/*18.----Enter PHN--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickNonIndigenousRadioButton();
		System.out.println("/*19.----click on non-Indigenous person radiobutton --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickVerifyPHNButton();
		System.out.println("/*20.----click Verify PHN button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.successMessage();
		System.out.println("/*21.'PNH match successful' --*/");
		inClinicExperiencePage.clickNextButton();
		System.out.println("/*22.'Click next button --*/");
		Thread.sleep(5000);
		String email = "test@qa.com";
		inClinicExperiencePage.enterEmail(email);
		System.out.println("/*23.'Enter email address --*/");
		Thread.sleep(2000);
		String email1 = "test@qa.com";
		inClinicExperiencePage.confirmEmail(email1);
		System.out.println("/*24.'Confirm email address --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickReviewDetails();
		System.out.println("/*25.Click review details Button--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		System.out.println("/*25.Click register Button on confirmation page--*/");
		Thread.sleep(5000);
		inClinicExperiencePage.clickEligibilityButton();
		System.out.println("/*26.click on check eligibility button on person account and select covid19 option--*/");
		Thread.sleep(2000);
//		inClinicExperiencePage.userIsEligibleSuccessMsg();
//		System.out.println("/*27.User Eligible Message displayed--*/");
//		Thread.sleep(2000);
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		System.out.println("/*28.Navigate to Appointment Scheduling Tab--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickToSearchClinic();
		System.out.println("/*29.Click on search clinic tab --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.SearchForClinic("Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic");
		System.out.println("/*29.Search for and select clinic Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		Thread.sleep(2000);
		
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
		inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
		System.out.println("/*----Search for Dacia is Successful ---*/");
		if (!inClinicExperiencePage.userDaciaFound()) {
			System.out.println("/*----User --> Dacia not found and return---*/");
		}
		while (inClinicExperiencePage.userDaciaFound()) {
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
			inClinicExperiencePage.SearchForCitizen("Dacia Bcvaxdod");
			System.out.println("/*----Search for Dacia is Successful ---*/");
		}
		
	}
	
}
