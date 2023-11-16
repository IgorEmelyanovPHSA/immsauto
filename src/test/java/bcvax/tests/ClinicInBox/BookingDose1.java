package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {

	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	private String personalHealthNumber = "9746170911";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	//String clinicNameToSearch = "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic";
	private String vaccineToSelect;
	MainPageOrg orgMainPage;

	@DataProvider(name="booking_data")
	public Object[][] dpMethod() {
		return new Object[][] {{"225652", "Covid19Vaccine"}, {"228857", "InfluenzaVaccine"}};
	}

	@Test(dataProvider = "booking_data")
	public void Can_Book_Dose1_Appointment_as_Clinician_CIB(String testcase_id, String vaccine_agent) throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("------------------------------");
		log("Testcase ID: " + testcase_id);
		log("Vaccine Agent: " + vaccine_agent);
		log("------------------------------");
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
		ClinicInBoxPage clinicInBox = new ClinicInBoxPage(getDriver());

		log("/*1.----Login --*/");
		loginPage.loginAsImmsBCAdmin();
		//TestcaseID = "225652"; //C225652
		TestcaseID = testcase_id;
		vaccineToSelect = vaccine_agent;
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
		orgMainPage.selectFromNavigationMenu("Home");
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
		log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
		clinicInBox.enterPNH(personalHealthNumber);
//		log("/*9.----click on non-Indigenous person radiobutton --*/");
//		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
//			clinicInBox.clickNonIndigenousRadioButton();
//		}
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
		PersonAccountPage.goToRelatedTab(driver);
		log("/*21----Go to Appointment Tab --*/");
		clinicInBox.navigateToVaccineSchedulingTab();

		//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountPage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}
		log("/*21.A---Select vaccination type: " + vaccineToSelect + "--*/");
		clinicInBox.selectOneOption(vaccineToSelect);
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*24----select 'Search by Clinic name' tab --*/");
		clinicInBox.selectSearchByClinicNameTab();
		log("/*25----search the Clinic " +clinicNameToSearch +" --*/");
		clinicInBox.searchClinicName(clinicNameToSearch);
		log("/*26----click on Option Facility location  --*/");
		clinicInBox.clickOnFacilityOptionLocation();
		log("/*27----select Active booking appointment day  --*/");
		clinicInBox.selectBookingAppointmentDay();
		log("/*28----select the time slot  --*/");
		clinicInBox.selectTimeSlotAppointment();
		log("/*29----click Next button  --*/");
		clinicInBox.clickOnNextButton();
		log("/*30----click Verify Contact Information Checkbox  --*/");
		clinicInBox.clickVerifyContactInformation();
		log("/*31----click Confirm Appointment button  --*/");
		clinicInBox.clickOnConfirmButton();
		log("/*32----see 'Appointment confirmed!' screen --*/");
		boolean appointment_result = clinicInBox.validateAppointmentConfirmedScreen();
		Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		log("/*33----Refresh page --*/");
		clinicInBox.refreshBrowser();
		log("/*34----Go to back to the Citizen Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		log("/*35----click on Check-In button --*/");
		inClinicExperience = new InClinicExperiencePage(driver);
		PersonAccountPage.clickCheckInButton(driver);
		Thread.sleep(2000);
		inClinicExperience.HomePageClickConfirmAndSaveButton();
		log("/*46.---Open Today's appointments from Home page --*/");

		inClinicExperience.clickTodayAppointments();
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		log("/*36----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
	}

}
