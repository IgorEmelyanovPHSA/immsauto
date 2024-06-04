package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;


@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {
	private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	private String vaccineToSelect = "Covid19Vaccine";
	Map<String, String> client_data;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "dose2");
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
	}

	@Test(priority = 1)
	public void Can_Book_Dose2_Appointment_as_Clinician_CIB() throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());

		log("/*1.----Login --*/");
		loginPage.loginAsImmsBCAdmin();
		TestcaseID = "225653"; //C225653

		log("TestRail test case ID: C" +TestcaseID);
		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		String currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
			MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
		}

		InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
		log("/*4.----Close All previously opened Tab's --*/");
		InClinicExperiencePage.closeTabsHCA(driver);
		log("/*5.----- Click on User Defaults Tab --*/");
		InClinicExperiencePage.clickUserDefaultsTab(driver);
		log("/*6.----- Enter current date for UserDefaults --*/");
		log("/*-- 13. Enter current date for UserDefaults --*/");
		UserDefaultsPage.inputCurrentDateUserDefaults(driver);
		UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
		log("/*7.----- Click on Save defaults button --*/");
		UserDefaultsPage.clickBtnSave(driver);
		currentApp = MainPageOrg.currentApp(driver);
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
		}
		MainPageOrg.closeAllTabs(driver);
		MainPageOrg.selectFromNavigationMenu(driver, "Home");
		ClinicInBoxPage clinicInBox = new ClinicInBoxPage(driver);
		clinicInBox.verifyIsClinicInBoxPageDisplayed();
		log("/*3.----Close All previously opened Tab's --*/");
		MainPageOrg.closeAllTabs(driver);
		Thread.sleep(2000);
		log("/*4.----click Register New Citizen --*/");
		clinicInBox.clickRegisterButton();
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

		log("/*18.----click on person Account Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(1000);
			PersonAccountPage.goToRelatedTab(driver);
		}
		log("/*19----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);
		//If override Eligibility is shown
		try {
			log("/*20.A---Select vaccination type: " + vaccineToSelect + "--*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
		} catch(Exception ex) {
			System.out.println("***Exception: " + ex.getMessage());
			System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
			PersonAccountSchedulePage.overrideEligibility(driver);
			Thread.sleep(500);
			log("/*20.A---Select vaccination type: " + vaccineToSelect + "--*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
		}
		log("/*20.A---Select vaccination type: " + vaccineToSelect + "--*/");
		PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);

		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////


		log("/*21----select 'Search clinic name' tab --*/");
		PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
		log("/*22----search the Clinic " +clinicNameToSearch +" --*/");
		PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
		log("/*23----click on Option Facility location  --*/");
		PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
		log("/*24----select Active booking appointment day  --*/");
		PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
		log("/*25----select the time slot  --*/");
		PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
		log("/*26----click Next button  --*/");
		PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
		log("/*27----click Verify Contact Information Checkbox  --*/");
		PersonAccountSchedulePage.clickVerifyContactInformation(driver);
		log("/*28----click Confirm Appointment button  --*/");
		PersonAccountSchedulePage.clickOnConfirmButton(driver);
		log("/*29----see 'Appointment Confirmed!' screen --*/");
		boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
		Assert.assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		log("/*30----Refresh page --*/");
		driver.navigate().refresh();

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

		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
		log("/*46.---Open Today's appointments from Home page --*/");

		InClinicExperiencePage.clickTodayAppointments(driver);
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
		ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		log("/*33----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
	}
}
