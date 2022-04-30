package bcvaxdevit.my.salesforce.com.Tests.Inventory;

import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;

public class Consumption extends BaseTest {
	
	
	@Test(testName = "Inventory Management - Consumption (vaccination)")
	public void Validate_Consumption_as_an_Clinician_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "219969"; //C219969
		System.out.println("/*----1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();//9746172456
		Thread.sleep(2000);
		System.out.println("/*----2. Navigate to Health Connect - Supply Console --*/");
		inClinicExperiencePage.selectHealthConnectApp();
		Thread.sleep(2000);
		inClinicExperiencePage.closeTabsHCA();
		System.out.println("/*----3. Click Dropdown Menu --*/");
		inClinicExperiencePage.clickDropdownMenu();
		Thread.sleep(2000);
		System.out.println("/*----5. Navigate and Select Supply Locations --*/");
		inClinicExperiencePage.selectSupplyLocationFromDropdown();
		Thread.sleep(2000);
		System.out.println("/*----5. Locate and click Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic location --*/");
		inClinicExperiencePage.selectSupplyLocationName();
		Thread.sleep(2000);
		inClinicExperiencePage.selectSupplyContainer();
		System.out.println("/*----6. Click and navigate to the supply container --> 'Automation Java - SDST-0000001558'--*/");
		Thread.sleep(2000);
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


