package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {

	private String legalFirstName = "Alexandro";
	private String legalLastName = "BCVaxDa Costa";
	private String legalLastNameASCII = "BCVaxDa%20Costa";
	private String dateOfBirth = "May 06, 1977";
	private String postalCode = "V8W7P2";
	private String personalHealthNumber = "9746172069";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	private String vaccineToSelect = "Covid19Vaccine";
	MainPageOrg orgMainPage;

	@Test(priority = 1)
	public void Can_Book_Dose2_Appointment_as_Clinician_CIB() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);

		log("/*1.----Login --*/");
		switch (Utils.getTargetEnvironment()) {
			case "comunityqa_immsbc_admin_org":
				loginPage.loginAsImmsBCAdmin();
				log("Login AS comunityqa_org_immsbc_admin");
				TestcaseID = "244875"; //C244875
				break;
			default:
				log("Login AS default user in CIB");
				loginPage.loginAsClinicianCIB();
				TestcaseID = "225653"; //C225653
		}
		log("TestRail test case ID: C" +TestcaseID);
		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
		}

		ClinicInBoxPage clinicInBox = new ClinicInBoxPage(driver);
		clinicInBox.verifyIsClinicInBoxPageDisplayed();
		log("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		log("/*4.----click Register New Citizen --*/");
		clinicInBox.clickRegisterButton();
		log("/*5.----Enter First Name: " +legalFirstName +"--*/");
		clinicInBox.enterFirstName(legalFirstName);
		log("/*6.----Enter Last Name: " +legalLastName +"--*/");
		clinicInBox.enterLastName(legalLastName);
		log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
		clinicInBox.enterDateOfBirth(dateOfBirth);
		log("/*7.----Enter Postal code: " +postalCode +"--*/");
		clinicInBox.enterPostalCode(postalCode);
		log("/*8.----Enter PHN: " +personalHealthNumber +"--*/");
		clinicInBox.enterPNH(personalHealthNumber);
		log("/*9.----click on non-Indigenous person radiobutton --*/");
		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
			clinicInBox.clickNonIndigenousRadioButton();
		}
		log("/*10.----click Verify PHN button --*/");
		clinicInBox.clickVerifyPHNButton();
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		clinicInBox.successMessageAppear();
		log("/*12.----click Next button --*/");
		clinicInBox.clickNextButton();
		log("/*13.'Enter email address: " +email +"--*/");
		clinicInBox.enterEmail(email);
		log("/*14.'Confirm email address: " +email +"--*/");
		clinicInBox.confirmEmail(email);
		log("/*15.Click review details Button--*/");
		clinicInBox.clickReviewDetails();
		log("/*16.Click register Button on confirmation page--*/");
		clinicInBox.clickRegisterButtonOnConfirmationPage();
		log("/*17.--toast success message - 'Success' --*/");
		clinicInBox.successRegisteredMessageAppear();
		log("/*18.----click on person Account Related Tab --*/");
		clinicInBox.clickOnPersonAccountRelatedTab();
		log("/*19----Go to Appointment Tab --*/");
		clinicInBox.clickAppointmentTab();
		try {
			System.out.println("/*20.----click on reason Early Booking Reason - Travel --*/");
			clinicInBox.selectEarlyBookingReason();
		} catch(Exception ex) {
			System.out.println("There is not Early Booking Option");
		}
		log("/*20.A---Select vaccination type: " + vaccineToSelect + "--*/");
		clinicInBox.selectOneOption(vaccineToSelect);
		log("/*21----select 'Search clinic name' tab --*/");
		clinicInBox.selectSearchByClinicNameTab();
		log("/*22----search the Clinic " +clinicNameToSearch +" --*/");
		clinicInBox.searchClinicName(clinicNameToSearch);
		log("/*23----click on Option Facility location  --*/");
		clinicInBox.clickOnFacilityOptionLocation();
		log("/*24----select Active booking appointment day  --*/");
		clinicInBox.selectBookingAppointmentDay();
		log("/*25----select the time slot  --*/");
		clinicInBox.selectTimeSlotAppointment();
		log("/*26----click Next button  --*/");
		clinicInBox.clickOnNextButton();
		log("/*27----click Verify Contact Information Checkbox  --*/");
		clinicInBox.clickVerifyContactInformation();
		log("/*28----click Confirm Appointment button  --*/");
		clinicInBox.clickOnConfirmButton();
		log("/*29----see 'Appointment Confirmed!' screen --*/");
		clinicInBox.validateAppointmentConfirmedScreen();
		log("/*30----Refresh page --*/");
		clinicInBox.refreshBrowser();
		log("/*31----Go to back to the Citizen Related Tab --*/");
		clinicInBox.clickRelatedTab();
		log("/*32----click on In-clinic Experience button --*/");
		InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		log("/*33----In-clinic Experience ->Vaccine Admin page appears up --*/");
		InClinicExperience.validateVaccineAdminPageOpen();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);
	}
}
