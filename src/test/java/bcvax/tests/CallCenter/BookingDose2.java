package bcvax.tests.CallCenter;

import Utilities.TestListener;
import bcvax.pages.PersonAccountPage;
import bcvax.tests.BaseTest;
import bcvax.pages.CallCenterConsolePage;
import bcvax.pages.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BookingDose2 extends BaseTest {
	private String legalFirstName = "Gill";
	private String legalLastName = "BCVaxOrigan";
	private String dateOfBirth = "Feb 14, 1915";
	private String postalCode = "V3L5L2";
	private String personalHealthNumber = "9746172463";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

	@AfterMethod
	public void afterMethod() throws Exception {
		log("/*0.---API call to remove duplicate citizen participant account after test finished--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}

	@Test(priority = 1)
	public void Can_Book_Dose2_Appointment_as_Call_Center_Agent() throws Exception {
		TestcaseID = "222525"; //C222525
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
		log("/*1.----Login as an Call Center Agent to the Call Center Console --*/");
		CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
		log("/*2.----CallCenter Console page displayed --*/");
		callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
		log("/*3.----Close All previously opened Tab's --*/");
		callCenterConsole.closeAllTabs();
		log("/*4.----click Register New Citizen --*/");
		callCenterConsole.clickRegisterButton();
		log("/*5.----Enter First Name " +legalFirstName +"--*/");
		callCenterConsole.enterFirstName(legalFirstName);
		log("/*6.----Enter Last Name " +legalLastName +"--*/");
		callCenterConsole.enterLastName(legalLastName);
		log("/*6.----Enter Date of birth " +dateOfBirth +"--*/");
		callCenterConsole.enterDateOfBirth(dateOfBirth);
		log("/*7.----Enter Postal code " +postalCode +"--*/");
		callCenterConsole.enterPostalCode(postalCode);
		log("/*8.----Enter PHN " +personalHealthNumber +"--*/");
		callCenterConsole.enterPNH(personalHealthNumber);
		log("/*9.----click on non-Indigenous person radiobutton --*/");
		if(Utils.getEnvConfigProperty("nonIndigenousDialog").equals("yes")) {
			callCenterConsole.clickNonIndigenousRadioButton();
		}
		log("/*10.----click Verify PHN button --*/");
		callCenterConsole.clickVerifyPHNButton();
		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		String successMessageText = callCenterConsole.successMessageAppear();
		assertTrue(successMessageText.equals("Success") || successMessageText.equals(""), "Actual Result: " + successMessageText);
		log("/*12.----click Next button --*/");
		callCenterConsole.clickNextButton();
		log("/*13.'Enter email address " +email +"--*/");
		callCenterConsole.enterEmail(email);
		log("/*14.'Confirm email address " +email +"--*/");
		callCenterConsole.confirmEmail(email);
		log("/*15.Click review details Button--*/");
		callCenterConsole.clickReviewDetails();
		log("/*16.Click register Button on confirmation page--*/");
		callCenterConsole.clickRegisterButtonOnConfirmationPage();
		log("/*17.--toast success message - 'Success' --*/");
		callCenterConsole.successRegisteredMessageAppear();
		log("/*18.----click refresh forecast button to showcase 2nd dose --*/");
		callCenterConsole.clickRefreshForecastButton();
		Thread.sleep(2000);
		log("/*21----Go to Appointment Tab --*/");
		callCenterConsole.navigateToVaccineSchedulingTab();

		System.out.println("/*22.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		callCenterConsole.clickOnVaccinationCheckbox();
		////////////////////
		//May will be removed
		//PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
		///////////////////
		log("/*24----select 'Search clinic name' tab --*/");
		callCenterConsole.selectSearchClinicNameTab();

		log("/*25----search the Clinic " +clinicNameToSearch +" --*/");
		callCenterConsole.searchClinicName(clinicNameToSearch);
		log("/*26----click on Option Facility location  --*/");
		callCenterConsole.clickOnFacilityOptionLocation();
		log("/*27----select Active booking appointment day  --*/");
		callCenterConsole.selectBookingAppointmentDay();
		log("/*28----select the time slot  --*/");
		callCenterConsole.selectTimeSlotAppointment();
		log("/*29----click Next button  --*/");
		callCenterConsole.clickOnNextButton();
		log("/*30----click Verify Contact Information Checkbox  --*/");
		callCenterConsole.clickVerifyContactInformation();
		log("/*31----click Confirm Appointment button  --*/");
		callCenterConsole.clickOnConfirmButton();
		log("/*32----see 'Appointment Confirmed!' screen --*/");
		boolean appointment_result = callCenterConsole.validateAppointmentConfirmedScreen();
		assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
		log("/*33----Refresh page --*/");
		callCenterConsole.refreshBrowser();
		log("/*34----Go to back to the Citizen Related Tab --*/");
		callCenterConsole.clickRelatedTab();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
	
}
