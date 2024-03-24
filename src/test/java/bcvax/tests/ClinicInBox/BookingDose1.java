package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementNotInteractableException;
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
		String currentApp = MainPageOrg.currentApp(driver);
		try {
			MainPageOrg.closeAllTabs(driver);
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		log("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		log("/*6.----- Enter current date for UserDefaults --*/");
		log("/*-- 13. Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		AlertDialog.closeAlert(driver);
		currentApp = MainPageOrg.currentApp(driver);
		try {
			clinicInBox.closeAllTabs();
		} catch(Exception ex) {
			;
		}
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
		}
		MainPageOrg.selectFromNavigationMenu(driver, "Home");
		log("/*3.----Close All previously opened Tab's --*/");

		log("/*4.----click Register New Citizen --*/");

		clinicInBox.clickRegisterButton();
		log("/*5.----Enter First Name: " +legalFirstName +"--*/");
		CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
		log("/*6.----Enter Last Name: " +legalLastName +"--*/");
		CitizenPrimaryInfo.enterLastName(driver, legalLastName);
		log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
		CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
		log("/*7.----Enter Postal code: " +postalCode +"--*/");
		CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
		log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
		CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);

		log("/*10.----click Verify PHN button --*/");
		CitizenPrimaryInfo.clickVerifyPHNButton(driver);
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		CitizenPrimaryInfo.successMessageAppear(driver);
		log("/*12.----click Next button --*/");
		CitizenPrimaryInfo.clickNextButton(driver);
		log("/*13.'Enter email address: " +email +"--*/");
		CitizenPrimaryInfo.enterEmail(driver, email);
		log("/*14.'Confirm email address: " +email +"--*/");
		CitizenPrimaryInfo.confirmEmail(driver, email);
		log("/*15.Click review details Button--*/");
		CitizenPrimaryInfo.clickReviewDetails(driver);
		log("/*16.Click register Button on confirmation page--*/");
		CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
		log("/*17.--toast success message - 'Success' --*/");
		CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
		Thread.sleep(2000);
		log("/*18.----click on person Account Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementNotInteractableException ex) {
			driver.navigate().refresh();
			Thread.sleep(1000);
			PersonAccountPage.goToRelatedTab(driver);
		}
		log("/*21----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);

		//If override Eligibility is shown
		try {
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
		} catch(Exception ex) {
			System.out.println("There is not Override Eligibility Option");
		}
		log("/*21.A---Select vaccination type: " + vaccineToSelect + "--*/");
		PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*24----select 'Search by Clinic name' tab --*/");
		PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
		log("/*25----search the Clinic " +clinicNameToSearch +" --*/");
		PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
		log("/*26----click on Option Facility location  --*/");
		PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
		log("/*27----select Active booking appointment day  --*/");
		PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
		log("/*28----select the time slot  --*/");
		PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
		log("/*29----click Next button  --*/");
		PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
		log("/*30----click Verify Contact Information Checkbox  --*/");
		PersonAccountSchedulePage.clickVerifyContactInformation(driver);
		log("/*31----click Confirm Appointment button  --*/");
		PersonAccountSchedulePage.clickOnConfirmButton(driver);
		try {
			log("/*32----see 'Appointment confirmed!' screen --*/");
			boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
			Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		} catch(Exception ex) {
			PersonAccountSchedulePage.clickOnConfirmButton(driver);
			log("/*32----see 'Appointment confirmed!' screen. Second attempt --*/");
			boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
			Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		}
		log("/*33----Refresh page --*/");
		driver.navigate().refresh();
		log("/*34----Go to back to the Citizen Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		log("/*35----click on Check-In button --*/");
		inClinicExperience = new InClinicExperiencePage(driver);
		PersonAccountPage.clickCheckInButton(driver);
		Thread.sleep(2000);
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
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
