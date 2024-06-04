package bcvax.tests.CallCenter;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	MainPageOrg orgMainPage;
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
	public void Can_Book_Dose2_Appointment_as_Call_Center_Agent() throws Exception {
		TestcaseID = "222525"; //C222525
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*1.----Login as an Call Center Agent to the Call Center Console --*/");
		CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
		orgMainPage = new MainPageOrg(driver);
		log("/*2.----CallCenter Console page displayed --*/");

		String currentApp = MainPageOrg.currentApp(driver);
		MainPageOrg.closeAllTabs(driver);
		if(!currentApp.equals(Apps.CALL_CENTER_CONSOLE.value)) {
			MainPageOrg.switchApp(driver, Apps.CALL_CENTER_CONSOLE.value);
		}
		MainPageOrg.selectFromNavigationMenu(driver, "Home");

		callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
		log("/*3.----Close All previously opened Tab's --*/");
		callCenterConsole.closeAllTabs();
		log("/*4.----click Register New Citizen --*/");
		callCenterConsole.clickRegisterButton();

		CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

		log("/*21----Go to Appointment Tab --*/");
		try {
			PersonAccountPage.goToVaccineScheduleTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			Thread.sleep(500);
			PersonAccountPage.goToVaccineScheduleTab(driver);
		}

		System.out.println("/*22.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		try {
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		} catch(NotFoundException ex) {
			Thread.sleep(500);
			PersonAccountPage.goToVaccineScheduleTab(driver);
			Thread.sleep(500);
			try {
				PersonAccountSchedulePage.overrideEligibility(driver);
			} catch(NotFoundException ex1) {
				System.out.println("Try to override eligibility");
			}
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		} catch(StaleElementReferenceException ex) {
			Thread.sleep(500);
			PersonAccountPage.goToVaccineScheduleTab(driver);
			Thread.sleep(500);
			PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, "Covid19Vaccine");
		}

		log("/*24----select 'Search clinic name' tab --*/");
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
		log("/*32----see 'Appointment Confirmed!' screen --*/");
		boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
		assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		log("/*33----Refresh page --*/");
		driver.navigate().refresh();
		log("/*34----Go to back to the Citizen Related Tab --*/");
		try {
			PersonAccountPage.goToRelatedTab(driver);
		} catch(ElementClickInterceptedException ex) {
			PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
			PersonAccountPage.goToRelatedTab(driver);
		}
	}
}
