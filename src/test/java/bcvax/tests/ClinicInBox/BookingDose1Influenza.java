package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.tests.BaseTest;
import bcvax.pages.ClinicInBoxPage;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class BookingDose1Influenza extends BaseTest {

	private String legalFirstName = "Lockwood";
	private String legalLastName = "BCVaxPenketh";
	private String dateOfBirth = "Jan 23, 1993";
	private String postalCode = "V8W2Z5";
	private String personalHealthNumber = "9746173963";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	private String vaccineToSelect = "InfluenzaVaccine";

	@Test(priority = 1)
	public void Can_Book_Dose1_Influenza_Appointment_as_Clinician_CIB() throws Exception {
		TestcaseID = "228857"; //C228857
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		Thread.sleep(10000);

		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		if (clinicInBox.displayCIBApp()) {
			log("/*---- User already on CIB Page--*/");
			Thread.sleep(2000);
		} else {
			log("/*---- Navigate to CIB App --*/");
			Thread.sleep(5000);
			clinicInBox.selectCIBApp();
			Thread.sleep(2000);
		}
		Thread.sleep(5000);

		log("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);

		log("/*4.----click Register New Citizen --*/");
		clinicInBox.clickRegisterButton();
		Thread.sleep(2000);

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

		log("/*9.----click on non-Indigenous person radiobutton --*/");
		clinicInBox.clickNonIndigenousRadioButton();

		log("/*10.----click Verify PHN button --*/");
		clinicInBox.clickVerifyPHNButton();
		Thread.sleep(2000);

		log("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
		clinicInBox.successMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

		log("/*12.----click Next button --*/");
		clinicInBox.clickNextButton();
		Thread.sleep(2000);

		log("/*13.'Enter email address: " +email +"--*/");
		clinicInBox.enterEmail(email);

		log("/*14.'Confirm email address: " +email +"--*/");
		clinicInBox.confirmEmail(email);

		log("/*15.Click review details Button--*/");
		clinicInBox.clickReviewDetails();
		Thread.sleep(2000);

		log("/*16.Click register Button on confirmation page--*/");
		clinicInBox.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);

		log("/*17.--toast success message - 'Success' --*/");
		clinicInBox.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

		log("/*18.----click on person Account Related Tab --*/");
		clinicInBox.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);
		//System.out.println("/*18.----click on Eligibility button --*/");
		//clinicInBox.clickOnEligibilityButton();
		//Thread.sleep(2000);
		//System.out.println("/*19----select vaccination option -> COVID_19_Vaccination --*/");
		//clinicInBox.selectEligibilityOption();
		//Thread.sleep(2000);
		//System.out.println("/*20.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
		//clinicInBox.successEligibilityMessageAppear();
		//Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs

		log("/*19.----Go to Appointment Tab --*/");
		clinicInBox.clickAppointmentTab();
		Thread.sleep(2000);

		log("/*20.---Select vaccination type: " + vaccineToSelect + "--*/");
		clinicInBox.selectOneOption(vaccineToSelect);
		Thread.sleep(1000);

		log("/*21.----select 'Search by Clinic name' tab --*/");
		clinicInBox.selectSearchByClinicNameTab();
		Thread.sleep(2000);

		log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
		clinicInBox.searchClinicName(clinicNameToSearch);
		Thread.sleep(2000);

		log("/*23.----click on Option Facility location  --*/");
		clinicInBox.clickOnFacilityOptionLocation();
		Thread.sleep(2000);

		log("/*24.----select Active booking appointment day  --*/");
		clinicInBox.selectBookingAppointmentDay();
		Thread.sleep(2000);

		log("/*25.----select the time slot  --*/");
		clinicInBox.selectTimeSlotAppointment();
		Thread.sleep(2000);

		log("/*26.----click Next button  --*/");
		clinicInBox.clickOnNextButton();
		Thread.sleep(2000);

		log("/*27.----click Verify Contact Information Checkbox  --*/");
		clinicInBox.clickVerifyContactInformation();
		Thread.sleep(2000);

		log("/*28.----click Confirm Appointment button  --*/");
		clinicInBox.clickOnConfirmButton();
		Thread.sleep(2000);

		log("/*29.----see 'Appointment confirmed!' screen --*/");
		clinicInBox.validateAppointmentConfirmedScreen();
		Thread.sleep(2000);

		log("/*30.----Refresh page --*/");
		clinicInBox.refreshBrowser();
		Thread.sleep(2000);

		log("/*31.----Go to back to the Citizen Related Tab --*/");
		clinicInBox.clickRelatedTab();
		Thread.sleep(2000);

		log("/*32.----click on In-clinic Experience button --*/");
		InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);

		log("/*33.----In-clinic Experience ->Vaccine Admin page appears up --*/");
		InClinicExperience.validateVaccineAdminPageOpen();
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}

}
