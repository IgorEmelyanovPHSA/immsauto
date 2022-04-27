package bcvaxuat.my.salesforce.com.Tests.Login;

import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;
import org.testng.annotations.Test;

public class ClinicianLoginTest extends BaseTest {
	
	@Test
	public void Can_login_as_Clinician_to_PRODSUPPQA() throws InterruptedException {
		/*----Login as an Clinician In-Clinic Experience --*/
		System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
		loginPage.enterClinicianUserName();
		loginPage.enterClinicianPW();
		Thread.sleep(5000);
		//loginPage.clickLoginButton();
		InClinicExperiencePage inClinicExperiencePage = loginPage.clickLoginButton();
		Thread.sleep(5000);
		
	}
	
	
}
