package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.util.Map;

@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	//String clinicNameToSearch = "Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic";
	private String vaccineToSelect;
	MainPageOrg orgMainPage;
	Map<String, String> client_data;
	@BeforeMethod
	public void beforeMethod() throws Exception {
		String client_data_file = Utils.getClientsDataFile();
		client_data = Utils.getTestClientData(client_data_file, "dose1");
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
		Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
	}

	@DataProvider(name="booking_data")
	public Object[][] dpMethod() {
		//return new Object[][] {{"225652", "Covid19Vaccine"}};
		return new Object[][] {{"225652", "Covid19Vaccine", true}, {"228857", "InfluenzaVaccine", false}};
	}

	@Test(dataProvider = "booking_data")
	public void Can_Book_Dose1_Appointment_as_Clinician_CIB(String testcase_id, String vaccine_agent, boolean vaccine_available) throws Exception {
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("------------------------------");
		log("Testcase ID: " + testcase_id);
		log("Vaccine Agent: " + vaccine_agent);
		log("------------------------------");
		log("/---API call to remove duplicate citizen participant account if found--*/");
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
		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

		Thread.sleep(500);
		log("/*18.----click on person Account Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementNotInteractableException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(2000);
			PersonAccountPage.goToRelatedTab(driver);
		}
		log("/*21----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);

		//If override Eligibility is shown
		try {
			log("/*21.A---Select vaccination type: " + vaccineToSelect + "--*/");
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
		} catch(Exception ex) {
			if(vaccine_available) {
				System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
				PersonAccountSchedulePage.overrideEligibility(driver);
				Thread.sleep(500);
				log("/*21.A---Select vaccination type: " + vaccineToSelect + "--*/");
				PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccineToSelect);
			} else {
				//---If vaccine is disabled and not available in UI then Pass
				Assert.assertTrue(1==1);
				return;
			}
		}

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
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountPage.goToRelatedTab(driver);
		}
		log("/*35----click on Check-In button --*/");
		inClinicExperience = new InClinicExperiencePage(driver);
		PersonAccountPage.clickCheckInButton(driver);
		Thread.sleep(2000);
		InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);
		log("/*46.---Open Today's appointments from Home page --*/");

		InClinicExperiencePage.clickTodayAppointments(driver);
		log("/*47.---Open Today appointment Details --*/");
		Thread.sleep(2000);
		Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
		ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		log("/*36----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
	}
}
