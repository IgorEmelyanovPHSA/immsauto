package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class Consumption extends BaseTest {
	
	ClinicInBoxPage clinicInBoxPage;
	
	@Test(testName = "Inventory Management - Consumption (vaccination)")
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
		Thread.sleep(12000);
		
		
		
		
		
		
		System.out.println("/*----10. Close all open tabs --*/");
		System.out.println("/*----2. Navigate to Health Connect - Supply Console --*/");
		System.out.println("/*----3. Identify Supply location with some stock --*/");
		System.out.println("/*----4. Identify matching clinic with the supply location--*/");
		System.out.println("/*----5. Register a user--*/");
		System.out.println("/*----6. Click to verify if the user is eligible--*/");
		System.out.println("/*----7. Go through the vaccination process till vaccine admin--*/");
		System.out.println("/*----8. Go through the vaccination process--*/");
		System.out.println("/*----9. Navigate back to Health Connect - Supply Console--*/");
		System.out.println("/*----10. Get the values for Supply Container, Default Supply Distribution and Supply  Item where you can see Consumed Doses field--*/");
		
		
	}
}


