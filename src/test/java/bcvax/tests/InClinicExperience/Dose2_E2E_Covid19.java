package bcvax.tests.InClinicExperience;

import Utilities.TestListener;
import bcvax.tests.BaseTest;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class Dose2_E2E_Covid19 extends BaseTest {
	private String legalFirstName = "Hugues";
	private String legalLastName = "BCVaxLampard";
	private String dateOfBirth = "March 3, 1904";
	private String postalCode = "V1N3Q3";
	private String personalHealthNumber = "9746171121";
	//private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

	@Test(priority = 1)
	public void Can_do_Dose2_Covid19_Vaccine_Administration_as_Clinician_ICE() throws Exception {
		TestcaseID = "222811"; //C222811
		log("Target Environment: "+ Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
		System.out.println("/*1.----Login as an Clinician to ICE --*/");
		InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICE();
		Thread.sleep(10000);
		System.out.println("/*2.----In Clinic Experience(ICE) page displayed --*/");
		inClinicExperience.verifyIsICEpageDisplayed();
		Thread.sleep(5000);
		System.out.println("/*3.--- Navigate to In Clinic Experience App --*/");
		inClinicExperience.selectICEFromApp();
		Thread.sleep(5000);
		System.out.println("/*4.----Close All previously opened Tab's --*/");
		inClinicExperience.closeTabsHCA();
		Thread.sleep(5000);
		System.out.println("/*5.----- Click on User Defaults Tab --*/");
		inClinicExperience.clickUserDefaultsTab();
		Thread.sleep(2000);
		System.out.println("/*6.----- Enter current date for UserDefaults --*/");
		inClinicExperience.inputCurrentDateUserDefaults();
		Thread.sleep(2000);
		System.out.println("/*7.----- Click on Save defaults button --*/");
		inClinicExperience.clickSaveDefaultsButton();
		Thread.sleep(2000);
		System.out.println("/*8.----- Click on register Tab --*/");
		inClinicExperience.clickRegisterTab();
		Thread.sleep(2000);
		//System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
		//inClinicExperience.clickSaveModalDefaultsButton();
		//Thread.sleep(2000);
		System.out.println("/*10.----click Register button New Citizen -Hugues BCVaxLampard --*/");
		inClinicExperience.clickRegisterButton();
		Thread.sleep(2000);
		System.out.println("/*11.----Enter First Name " +legalFirstName  +"--*/");
		inClinicExperience.enterFirstName(legalFirstName);
		Thread.sleep(2000);
		System.out.println("/*12.----Enter Last Name " +legalLastName  +"--*/");
		inClinicExperience.enterLastName(legalLastName);
		Thread.sleep(2000);
		System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
		inClinicExperience.enterDateOfBirth(dateOfBirth);
		Thread.sleep(2000);
		System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
		inClinicExperience.enterPostalCode(postalCode);
		Thread.sleep(2000);
		System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
		inClinicExperience.enterPNH(personalHealthNumber);
		Thread.sleep(2000);
		System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
		inClinicExperience.clickNonIndigenousRadioButton();
		Thread.sleep(2000);
		System.out.println("/*17.----click Verify PHN button --*/");
		inClinicExperience.clickVerifyPHNButton();
		Thread.sleep(2000);
		System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
		inClinicExperience.successMessage();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*19.----click Next button --*/");
		inClinicExperience.clickNextButton();
		Thread.sleep(2000);
		System.out.println("/*20.----'Enter email address " +email +"--*/");
		inClinicExperience.enterEmail(email);
		System.out.println("/*21.----'Confirm email address " +email +"--*/");
		Thread.sleep(2000);
		inClinicExperience.confirmEmail(email);
		System.out.println("/*22.---Click review details Button--*/");
		Thread.sleep(2000);
		inClinicExperience.clickReviewDetails();
		System.out.println("/*23.----Click register Button on confirmation page--*/");
		Thread.sleep(2000);
		inClinicExperience.clickRegisterButtonOnConfirmationPage();
		Thread.sleep(2000);
		System.out.println("/*24.--toast success message - 'Success' --*/");
		inClinicExperience.successRegisteredMessageAppear();
		Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*25.----click on person Account Related Tab --*/");
		inClinicExperience.clickOnPersonAccountRelatedTab();
		Thread.sleep(2000);
		//System.out.println("/*24.----click on Eligibility button --*/");
		//inClinicExperience.clickEligibilityButton();
		//Thread.sleep(2000);
		//System.out.println("/*25----select vaccination option -> COVID_19_Vaccination --*/");
		//inClinicExperience.selectCovid19option();
		//Thread.sleep(2000);
		//System.out.println("/*26.--toast success message - 'Eligibility check completed. Participant is eligible for COVID_19_Vaccination.' --*/");
		//inClinicExperience.userIsEligibleSuccessMsg();
		//Thread.sleep(5000); //wait for the popup toast success message disappeared before closing all Tabs
		System.out.println("/*26.----Go to Appointment Tab --*/");
		inClinicExperience.navigateAppointmentSchedulingTab();
		Thread.sleep(5000);
		//System.out.println("/*27.----click on reason Early Booking Reason - Travel --*/");
		//inClinicExperience.selectEarlyBookingReason();
		//Thread.sleep(2000);

		System.out.println("/*28.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
		log("/*----scroll down a bit --*/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
		Thread.sleep(3000);
		inClinicExperience.clickOnVaccinationCheckbox();
		Thread.sleep(2000);

		//System.out.println("/*29----click on 'More' search tab --*/");
		//inClinicExperience.clickOnMoreSearchTabs();
		//Thread.sleep(2000);
		System.out.println("/*28.----select 'Search by Clinic name' tab --*/");
		inClinicExperience.selectSearchByClinicNameTab();
		Thread.sleep(2000);

		log("/*29.----search the Clinic " +clinicNameToSearch +" --*/");
		log("/*----scroll down a bit --*/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
		Thread.sleep(3000);
		inClinicExperience.searchClinicName(clinicNameToSearch);
		Thread.sleep(2000);

		System.out.println("/*30.----click on Option Facility location  --*/");
		inClinicExperience.clickOnFacilityOptionLocation();
		Thread.sleep(2000);
		System.out.println("/*31.----select Active booking appointment day  --*/");
		inClinicExperience.selectBookingAppointmentDay();
		Thread.sleep(2000);
		System.out.println("/*32.----select the time slot  --*/");
		inClinicExperience.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		System.out.println("/*33.----click Next button  --*/");
		inClinicExperience.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		System.out.println("/*34.----click Verify Contact Info checkbox  --*/");
		inClinicExperience.clickVerifyContactInformation();
		Thread.sleep(2000);
		inClinicExperience.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		System.out.println("/*35.----see 'Appointment confirmed!' screen --*/");
		inClinicExperience.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		System.out.println("/*36.----Refresh page --*/");
		inClinicExperience.refreshBrowser();
		Thread.sleep(2000);
		System.out.println("/*37.----Go to back to the Citizen Related Tab --*/");
		inClinicExperience.clickRelatedTab();
		Thread.sleep(2000);
		System.out.println("/*38.----click on In-clinic Experience button --*/");
		inClinicExperience.ClickGoToInClinicExperienceButton();
		//InClinicExperiencePage InClinicExperience = clinicInBox.ClickGoToInClinicExperienceButton();
		Thread.sleep(2000);
		System.out.println("/*39.----In-clinic Experience ->Vaccine Admin page appears up --*/");
		inClinicExperience.validateVaccineAdminPageOpen();
		Thread.sleep(5000);
		System.out.println("/*40.---Click confirm and Save Button --*/");
		inClinicExperience.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		System.out.println("/*41.---select Vaccine Agent picklist value -> COVID-19 mRNA --*/");
		inClinicExperience.selectVaccineAgent();
		Thread.sleep(3000);
		System.out.println("/*42.---Click Save Consent Button --*/");
		inClinicExperience.ClickSaveConsentButton();
		Thread.sleep(5000);
		System.out.println("/*42_.---Click Save button for Immunisation Information --*/");
		inClinicExperience.ClickSaveImmuneInfoSaveButton();
		Thread.sleep(5000);
		System.out.println("/*43.---Click Confirm and Save Administration Button --*/");
		inClinicExperience.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		System.out.println("/*44.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperience.ClickModalConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		System.out.println("/*45.---the Home - Client Search showing up  --*/");
		inClinicExperience.validateHomePageShownUp();
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void Post_conditions_step_Remove_Dups_Citizen_participant_account() throws Exception {
		TestcaseID = "219865"; //C219865
		log("/---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastName, legalFirstName);
	}
}