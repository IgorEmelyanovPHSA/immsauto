package bcvaxuat.my.salesforce.com.Tests.Register;

import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Test;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;

public class RegisterNewCitizen extends BaseTest {
	
	@Test
	public void Can_register_new_participant_Citizen_account_Linked_with_PIR() throws InterruptedException {
		log("Register a new Citizen PRODSUPPQA");
		/*----Login as an Clinician In-Clinic Experience --*/
		log("/*----Login as an Clinician In-Clinic Experience --*/");
		//loginPage.enterClinicianUserName();
		//loginPage.enterClinicianPW();
		//loginPage.clickLoginButton();
		//InClinicExperiencePage inClinicExperiencePage = loginPage.clickLoginButton();
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginAsClinicianICE();
		//Thread.sleep(10000);
		/*----Go to Register Tab ---*/
		log("/*----Go to Register Tab ---*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(5000);
		/*----Search for Participant account ---*/
		log("/*----Search for Participant account ---*/");
	}
	
	
}
