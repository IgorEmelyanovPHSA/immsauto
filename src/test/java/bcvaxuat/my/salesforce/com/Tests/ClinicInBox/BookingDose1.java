package bcvaxuat.my.salesforce.com.Tests.ClinicInBox;

import Utilities.TestListener;
import bcvaxuat.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {

	private String legalFirstName = "Ludovika";
	private String legalLastName = "BcvaxLimeburn";
	private String dateOfBirth = "Sep 21, 1923";
	private String postalCode = "V3L5L2";
	private String personalHealthNumber = "9746170911";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";

	@Test
	public void Can_Book_Dose1_Appointment_as_Clinician_CIB_BCVAXDEVIT() throws InterruptedException {
		TestcaseID = "222364"; //C192878
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
			clinicInBox.selectCIBApp();
			Thread.sleep(2000);
		}
		//clinicInBox.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(5000);
		log("/*3.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);
		log("/*4.----click Register New Citizen --*/");
		clinicInBox.clickRegisterButton();
		Thread.sleep(2000);
		log("/*5.----Enter First Name: " +legalFirstName +"--*/");
		clinicInBox.enterFirstName(legalFirstName);
		Thread.sleep(2000);
		log("/*6.----Enter Last Name: " +legalLastName +"--*/");
		clinicInBox.enterLastName(legalLastName);
		Thread.sleep(2000);
		log("/*6.----Enter Date of birth: " +dateOfBirth +"--*/");
		clinicInBox.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		log("/*7.----Enter Postal code: " +postalCode +"--*/");
		clinicInBox.enterPostalCode(postalCode);
		Thread.sleep(2000);
		log("/*8.----Enter PHN: "+personalHealthNumber +"--*/");
		clinicInBox.enterPNH(personalHealthNumber);
		Thread.sleep(2000);
		log("/*9.----click on non-Indigenous person radiobutton --*/");
		clinicInBox.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
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
		Thread.sleep(2000);
		clinicInBox.confirmEmail(email);
		log("/*15.Click review details Button--*/");
		Thread.sleep(2000);
		clinicInBox.clickReviewDetails();
		log("/*16.Click register Button on confirmation page--*/");
		Thread.sleep(2000);
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
		log("/*21----Go to Appointment Tab --*/");
		clinicInBox.clickAppointmentTab();
		Thread.sleep(2000);
		log("/*22----click on reason for visit 'Covid-19 Vaccine' radiobutton --*/");
		clinicInBox.clickOnVaccinationCheckbox();
		Thread.sleep(2000);
		//System.out.println("/*23----click on 'More' search tab --*/");
		//clinicInBox.clickOnMoreSearchTabs();
		//Thread.sleep(2000);
		log("/*24----select 'Search by Clinic name' tab --*/");
		clinicInBox.selectSearchByClinicNameTab();
		Thread.sleep(2000);
		String clinicNameToSearch = "Age 5-11 Only - Indigenous Clinic - Victoria Native Friendship Center";
		log("/*25----search the Clinic " +clinicNameToSearch +" --*/");
		clinicInBox.searchClinicName(clinicNameToSearch);
		Thread.sleep(2000);
		log("/*26----click on Option Facility location  --*/");
		clinicInBox.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*27----select Active booking appointment day  --*/");
		clinicInBox.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*28----select the time slot  --*/");
		clinicInBox.selectTimeSlotAppointment();
		Thread.sleep(2000);
		log("/*29----click Next button  --*/");
		clinicInBox.clickOnNextButton();
		Thread.sleep(2000);
		log("/*30----click Verify Contact Information Checkbox  --*/");
		clinicInBox.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*31----click Confirm Appointment button  --*/");
		clinicInBox.clickOnConfirmButton();
		Thread.sleep(2000);
		log("/*32----see 'Appointment confirmed!' screen --*/");
		clinicInBox.validateAppointmentConfirmedScreen();
		Thread.sleep(2000);
		log("/*33----Refresh page --*/");
		clinicInBox.refreshBrowser();
		Thread.sleep(2000);
		log("/*34----Go to back to the Citizen Related Tab --*/");
		clinicInBox.clickRelatedTab();
		Thread.sleep(2000);
		log("/*35----click on In-clinic Experience button --*/");
		InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);
		log("/*36----In-clinic Experience ->Vaccine Admin page appears up --*/");
		InClinicExperience.validateVaccineAdminPageOpen();
		Thread.sleep(5000);
		log("/*37.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
}