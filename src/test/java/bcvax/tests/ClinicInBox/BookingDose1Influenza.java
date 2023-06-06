package bcvax.tests.ClinicInBox;

import Utilities.TestListener;
import bcvax.pages.MainPageOrg;
import bcvax.tests.BaseTest;
import bcvax.pages.ClinicInBoxPage;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import constansts.Apps;
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
	MainPageOrg orgMainPage;
	@Test(priority = 1)
	public void Can_Book_Dose1_Influenza_Appointment_as_Clinician_CIB() throws Exception {
		TestcaseID = "228857"; //C228857
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);

		log("/*1.----Login as an Clinician to CIB --*/");
		ClinicInBoxPage clinicInBox = loginPage.loginAsClinicianCIB();
		orgMainPage = new MainPageOrg(driver);

		log("/*2.----Check that Clinic In Box(IPM) page displayed --*/");
		String currentApp = orgMainPage.currentApp();
		if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
			orgMainPage.switchApp(Apps.CLINIC_IN_BOX.value);
		}

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

		log("/*9.----click on non-Indigenous person radiobutton --*/");
		clinicInBox.clickNonIndigenousRadioButton();

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
		clinicInBox.clickOnPersonAccountRelatedTab();

		log("/*19.----Go to Appointment Tab --*/");
		clinicInBox.clickAppointmentTab();

		log("/*20.---Select vaccination type: " + vaccineToSelect + "--*/");
		clinicInBox.selectOneOption(vaccineToSelect);

		log("/*21.----select 'Search by Clinic name' tab --*/");
		clinicInBox.selectSearchByClinicNameTab();

		log("/*22.----search the Clinic " +clinicNameToSearch +" --*/");
		clinicInBox.searchClinicName(clinicNameToSearch);

		log("/*23.----click on Option Facility location  --*/");
		clinicInBox.clickOnFacilityOptionLocation();

		log("/*24.----select Active booking appointment day  --*/");
		clinicInBox.selectBookingAppointmentDay();

		log("/*25.----select the time slot  --*/");
		clinicInBox.selectTimeSlotAppointment();

		log("/*26.----click Next button  --*/");
		clinicInBox.clickOnNextButton();

		log("/*27.----click Verify Contact Information Checkbox  --*/");
		clinicInBox.clickVerifyContactInformation();

		log("/*28.----click Confirm Appointment button  --*/");
		clinicInBox.clickOnConfirmButton();

		log("/*29.----see 'Appointment confirmed!' screen --*/");
		clinicInBox.validateAppointmentConfirmedScreen();

		log("/*30.----Refresh page --*/");
		clinicInBox.refreshBrowser();

		log("/*31.----Go to back to the Citizen Related Tab --*/");
		clinicInBox.clickRelatedTab();

		log("/*32.----click on In-clinic Experience button --*/");
		InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();

		log("/*33.----In-clinic Experience ->Vaccine Admin page appears up --*/");
		InClinicExperience.validateVaccineAdminPageOpen();
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}

}
