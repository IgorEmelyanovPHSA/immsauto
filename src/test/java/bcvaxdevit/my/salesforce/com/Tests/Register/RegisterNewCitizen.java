package bcvaxdevit.my.salesforce.com.Tests.Register;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;

@Listeners({TestListener.class})
public class RegisterNewCitizen extends BaseTest {


	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	private String personalHealthNumber = "9746170911";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	private String vaccineToSelect = "Covid19Vaccine";

	@Test(priority = 1)
	public void Can_register_new_participant_Citizen_account_Linked_with_PIR() throws Exception {
		TestcaseID = "219927"; //C219927
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		Thread.sleep(10000);

		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		if (clinicInBox.displayCIBApp()) {
			log("/*---- User already on CIB Page--*/");
			Thread.sleep(2000);
		} else {
			log("/*---- Navigate to CIB App --*/");
			clinicInBox.selectCIBApp();
			Thread.sleep(2000);
		}
		Thread.sleep(5000);

		log("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);

		log("/*4.----click Register New Citizen --*/");
		clinicInBox.clickRegisterButton();
		Thread.sleep(2000);

		log("/*5.----Enter First Name: " +legalFirstName +"--*/");
		clinicInBox.enterFirstName(legalFirstName);

		log("/*6.----Enter Last Name: " +legalLastName +"--*/");
		clinicInBox.enterLastName(legalLastName);

		log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
		clinicInBox.enterDateOfBirth(dateOfBirth);

		log("/*7.----Enter Postal code: " +postalCode +"--*/");
		clinicInBox.enterPostalCode(postalCode);

		log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
		clinicInBox.enterPNH(personalHealthNumber);

		log("/*9.----click on non-Indigenous person radiobutton --*/");
		clinicInBox.clickNonIndigenousRadioButton();

		log("/*10.----click Verify PHN button --*/");
		clinicInBox.clickVerifyPHNButton();
		Thread.sleep(2000);

		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		clinicInBox.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

		log("/*12.----click Next button --*/");
		clinicInBox.clickNextButton();
		Thread.sleep(2000);

		log("/*13.'Enter email address: " +email +"--*/");
		clinicInBox.enterEmail(email);

		log("/*14.'Confirm email address: " +email +"--*/");
		clinicInBox.confirmEmail(email);

		log("/*15.Click review details Button--*/");
		clinicInBox.clickReviewDetails();
		Thread.sleep(2000);

		log("/*16.Click register Button on confirmation page--*/");
		clinicInBox.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);

		log("/*17.--toast success message - 'Success' --*/");
		clinicInBox.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
	}


	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
	
}
