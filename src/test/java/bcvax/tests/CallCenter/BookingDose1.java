package bcvax.tests.CallCenter;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {
	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	MainPageOrg orgMainPage;
	private String personalHealthNumber = "9746170911";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

	@Test(priority = 1)
	public void Can_Book_Dose1_Appointment_as_Call_Center_Agent() throws Exception {
		TestcaseID = "222524"; //C222524
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
		CommonMethods commn = new CommonMethods(getDriver());
		System.out.println("/*1.----Login as an Call Center Agent to the Call Center Console --*/");
		CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
		System.out.println("/*2.----CallCenter Console page displayed --*/");
		orgMainPage = new MainPageOrg(driver);
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CALL_CENTER_CONSOLE.value)) {
			orgMainPage.switchApp(Apps.CALL_CENTER_CONSOLE.value);
		}

		callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
		System.out.println("/*3.----Close All previously opened Tab's --*/");
		callCenterConsole.closeAllTabs();
		System.out.println("/*4.----click Register New Citizen--*/");
		try {
			callCenterConsole.clickRegisterButton();
		} catch(Exception ex) {
			Thread.sleep(2000);
			callCenterConsole.closeAllTabs();
			callCenterConsole.clickRegisterButton();
		}
		System.out.println("/*5.----Enter First Name " +legalFirstName +"--*/");
		callCenterConsole.enterFirstName(legalFirstName);
		System.out.println("/*6.----Enter Last Name " +legalLastName +"--*/");
		callCenterConsole.enterLastName(legalLastName);
		System.out.println("/*6.----Enter Date of birth " +dateOfBirth +"--*/");
		callCenterConsole.enterDateOfBirth(dateOfBirth);
		System.out.println("/*7.----Enter Postal code " +postalCode +"--*/");
		callCenterConsole.enterPostalCode(postalCode);
		System.out.println("/*8.----Enter PHN " +personalHealthNumber +"--*/");
		callCenterConsole.enterPNH(personalHealthNumber);
		System.out.println("/*9.----click on non-Indigenous person radiobutton --*/");
		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
			callCenterConsole.clickNonIndigenousRadioButton();
		}
		System.out.println("/*10.----click Verify PHN button --*/");
		callCenterConsole.clickVerifyPHNButton();
		System.out.println("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		callCenterConsole.successMessageAppear();
		System.out.println("/*12.----click Next button --*/");
		callCenterConsole.clickNextButton();
		System.out.println("/*13.'Enter email address " +email +"--*/");
		callCenterConsole.enterEmail(email);
		System.out.println("/*14.'Confirm email address " +email +"--*/");
		callCenterConsole.confirmEmail(email);
		System.out.println("/*15.Click review details Button--*/");
		callCenterConsole.clickReviewDetails();
		System.out.println("/*16.Click register Button on confirmation page--*/");
		callCenterConsole.clickRegisterButtonOnConfirmationPage();
		System.out.println("/*17.--toast success message - 'Success' --*/");
		callCenterConsole.successRegisteredMessageAppear();

		System.out.println("/*18.----click on person Account Related Tab --*/");
		PersonAccountPage.goToRelatedTab(driver);
		//callCenterConsole.clickOnPersonAccountRelatedTab();

		System.out.println("/*21----Go to Appointment Tab --*/");
		PersonAccountPage.goToVaccineScheduleTab(driver);
		//callCenterConsole.navigateToVaccineSchedulingTab();

		try {
			System.out.println("---click on reason Early Booking Reason - Travel --*/");
			commn.selectEarlyBookingReason();
		} catch(Exception ex) {
			System.out.println("There is not Early Booking Option");
		}

		System.out.println("/*22.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		callCenterConsole.clickOnVaccinationCheckbox();
		////////////////////
		//May will be removed
		PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		System.out.println("/*24----select 'Search by Clinic name' tab --*/");
		callCenterConsole.selectSearchClinicNameTab();
		log("/*25----search the Clinic " +clinicNameToSearch +" --*/");
		callCenterConsole.searchClinicName(clinicNameToSearch);
		System.out.println("/*26----click on Option Facility location  --*/");
		callCenterConsole.clickOnFacilityOptionLocation();
		System.out.println("/*27----select Active booking appointment day  --*/");
		callCenterConsole.selectBookingAppointmentDay();
		System.out.println("/*28----select the time slot  --*/");
		callCenterConsole.selectTimeSlotAppointment();
		System.out.println("/*29----click Next button  --*/");
		callCenterConsole.clickOnNextButton();
		System.out.println("/*30----click Verify Contact Information Checkbox  --*/");
		callCenterConsole.clickVerifyContactInformation();
		System.out.println("/*31----click Confirm Appointment button  --*/");
		callCenterConsole.clickOnConfirmButton();
		System.out.println("/*32----see 'Appointment confirmed!' screen --*/");
		boolean appointment_result = callCenterConsole.validateAppointmentConfirmedScreen();
		assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		System.out.println("/*33----Refresh page --*/");

		callCenterConsole.refreshBrowser();
		System.out.println("/*34----Go to back to the Citizen Related Tab --*/");
		callCenterConsole.clickRelatedTab();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
	
}
