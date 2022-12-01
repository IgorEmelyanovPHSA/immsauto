package bcvax.CallCenter;

import Utilities.TestListener;
import bcvax.BaseTest;
import bcvax.pages.CallCenterConsolePage;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class BookingDose1Pneumonia extends BaseTest {

	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	private String personalHealthNumber = "9746170911";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	private String vaccineToSelect = "InfluenzaVaccine"; // No UI btn for Pneumonia should select Influenza
	
	@Test(priority = 1)
	public void Can_Book_Dose1_Pneumonia_Appointment_as_Call_Center_Agent() throws Exception {
		TestcaseID = "228856"; //C228856
		log("Pneumonia testcase using influenza for now, will need to update testCaseId later");
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

		log("/*1.----Login as an Cal Center Agent to the Call Center Console --*/");
		CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
		Thread.sleep(10000);

		log("/*2.----CallCenter Console page displayed --*/");
		callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
		Thread.sleep(5000);

		log("/*3.----Close All previously opened Tab's --*/");
		callCenterConsole.closeAllTabs();
		Thread.sleep(5000);

		log("/*4.----click Register New Citizen--*/");
		callCenterConsole.clickRegisterButton();
		Thread.sleep(2000);

		log("/*5.----Enter First Name " +legalFirstName +"--*/");
		callCenterConsole.enterFirstName(legalFirstName);

		log("/*6.----Enter Last Name " +legalLastName +"--*/");
		callCenterConsole.enterLastName(legalLastName);

		log("/*7.----Enter Date of birth " +dateOfBirth +"--*/");
		callCenterConsole.enterDateOfBirth(dateOfBirth);

		log("/*8.----Enter Postal code " +postalCode +"--*/");
		callCenterConsole.enterPostalCode(postalCode);

		log("/*9.----Enter PHN " +personalHealthNumber +"--*/");
		callCenterConsole.enterPNH(personalHealthNumber);

		log("/*10.----click on non-Indigenous person radiobutton --*/");
		callCenterConsole.clickNonIndigenousRadioButton();

		log("/*11.----click Verify PHN button --*/");
		callCenterConsole.clickVerifyPHNButton();
		Thread.sleep(2000);

		log("/*12.--Expecting to see the toast success message - 'PNH match successful' --*/");
		callCenterConsole.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

		log("/*13.----click Next button --*/");
		callCenterConsole.clickNextButton();
		Thread.sleep(2000);

		log("/*14.'Enter email address " +email +"--*/");
		callCenterConsole.enterEmail(email);

		log("/*15.'Confirm email address " +email +"--*/");
		callCenterConsole.confirmEmail(email);

		log("/*16.Click review details Button--*/");
		callCenterConsole.clickReviewDetails();
		Thread.sleep(2000);

		log("/*17.Click register Button on confirmation page--*/");
		callCenterConsole.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);

		log("/*18.--toast success message - 'Success' --*/");
		callCenterConsole.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

		log("/*19.----click on person Account Related Tab --*/");
		callCenterConsole.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);

		//System.out.println("/*18.----click on Eligibility button --*/");
		//callCenterConsole.clickOnEligibilityButton();
		//Thread.sleep(2000);
		//System.out.println("/*19----select vaccination option -> COVID_19_Vaccination --*/");
		//callCenterConsole.selectEligibilityOption();
		//Thread.sleep(2000);
		//System.out.println("/*20.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
		//callCenterConsole.successEligibilityMessageAppear();
		//Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		log("/*20----Go to Appointment Tab --*/");
		callCenterConsole.clickAppointmentTab();
		Thread.sleep(2000);

		log("/*21.---Select vaccination type: " + vaccineToSelect + "--*/");
		callCenterConsole.selectOneOption(vaccineToSelect);
		Thread.sleep(1000);

		//System.out.println("/*23----click on reason Early Booking Reason --*/");
		//callCenterConsole.selectEarlyBookingReason();
		//Thread.sleep(2000);
		//System.out.println("/*23----click on 'More' search tab --*/");
		//callCenterConsole.clickOnMoreSearchTabs();
		//Thread.sleep(2000);

		log("/*22----select 'Search by Clinic name' tab --*/");
		callCenterConsole.selectSearchClinicNameTab();
		Thread.sleep(2000);

		log("/*23----search the Clinic " +clinicNameToSearch +" --*/");
		callCenterConsole.searchClinicName(clinicNameToSearch);
		Thread.sleep(2000);

		log("/*24----click on Option Facility location  --*/");
		callCenterConsole.clickOnFacilityOptionLocation();
		Thread.sleep(2000);

		log("/*25----select Active booking appointment day  --*/");
		callCenterConsole.selectBookingAppointmentDay();
		Thread.sleep(2000);

		log("/*26----select the time slot  --*/");
		callCenterConsole.selectTimeSlotAppointment();
		Thread.sleep(2000);

		log("/*27----click Next button  --*/");
		callCenterConsole.clickOnNextButton();
		Thread.sleep(2000);

		log("/*28----click Verify Contact Information Checkbox  --*/");
		callCenterConsole.clickVerifyContactInformation();
		Thread.sleep(2000);

		log("/*29----click Confirm Appointment button  --*/");
		callCenterConsole.clickOnConfirmButton();
		Thread.sleep(2000);

		log("/*30----see 'Appointment confirmed!' screen --*/");
		callCenterConsole.validateAppointmentConfirmedScreen();
		Thread.sleep(2000);

		log("/*31----Refresh page --*/");
		callCenterConsole.refreshBrowser();
		Thread.sleep(2000);

		log("/*32----Go to back to the Citizen Related Tab --*/");
		callCenterConsole.clickRelatedTab();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}

}
