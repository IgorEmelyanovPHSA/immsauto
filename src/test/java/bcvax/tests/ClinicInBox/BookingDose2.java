package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
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
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

		log("/*1.----Login --*/");
		loginPage.loginAsImmsBCAdmin();
		orgMainPage = new MainPageOrg(driver);
		TestcaseID = "225653"; //C225653

		log("TestRail test case ID: C" +TestcaseID);
		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		log("/*6.----- Enter current date for UserDefaults --*/");
		UserDefaultsPage userDefaultsPage = new UserDefaultsPage(driver);
		log("/*-- 13. Enter current date for UserDefaults --*/");
		userDefaultsPage.inputCurrentDateUserDefaults();
		userDefaultsPage.selectUserDefaultLocation(clinicNameToSearch);
		log("/*7.----- Click on Save defaults button --*/");
		userDefaultsPage.clickBtnSave();
		AlertDialog.closeAlert(driver);
		currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
		}
		orgMainPage.closeAllTabs();
		orgMainPage.selectFromNavigationMenu("Home");
		ClinicInBoxPage clinicInBox = new ClinicInBoxPage(driver);
		clinicInBox.verifyIsClinicInBoxPageDisplayed();
		log("/*3.----Close All previously opened Tab's --*/");
		orgMainPage.closeAllTabs();
		Thread.sleep(2000);
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

		try {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
		} catch(Exception ex) {
			System.out.println("Warning dialog didn't appear");
		}

		log("/*18.----click on person Account Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		log("/*19----Go to Appointment Tab --*/");
		clinicInBox.clickAppointmentTab();
//		try {
//			System.out.println("/*20.----click on reason Early Booking Reason - Travel --*/");
//			clinicInBox.selectEarlyBookingReason();
//		} catch(Exception ex) {
//			System.out.println("There is not Early Booking Option");
//		}
		//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountPage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}
		log("/*20.A---Select vaccination type: " + vaccineToSelect + "--*/");
		clinicInBox.selectOneOption(vaccineToSelect);

		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////


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
		boolean appointment_result = clinicInBox.validateAppointmentConfirmedScreen();
		Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		log("/*30----Refresh page --*/");
		clinicInBox.refreshBrowser();

		try {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
		} catch(Exception ex) {
			System.out.println("Warning dialog didn't appear");
		}
		log("/*31----Go to back to the Citizen Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		Thread.sleep(1000);
		log("/*32----click on In-clinic Experience button --*/");
		inClinicExperience = new InClinicExperiencePage(driver);

		PersonAccountPage.clickCheckInButton(driver);

		inClinicExperience.HomePageClickConfirmAndSaveButton();
		log("/*46.---Open Today's appointments from Home page --*/");

		inClinicExperience.clickTodayAppointments();
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		log("/*33----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
	}
}
