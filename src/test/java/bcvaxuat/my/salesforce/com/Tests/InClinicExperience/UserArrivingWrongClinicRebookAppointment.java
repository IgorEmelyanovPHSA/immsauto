package bcvaxuat.my.salesforce.com.Tests.InClinicExperience;

import Utilities.TestListener;
import bcvaxuat.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxuat.my.salesforce.com.Pages.Utils;
import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class UserArrivingWrongClinicRebookAppointment extends BaseTest {
	
	private String legalFirstName = "Jodie";
	private String legalLastName = "BCVaxCluff";
	private String legalLastNameASCII = "BCVaxCluff";
	private String dateOfBirth = "May 13, 2006";
	private String postalCode = "V8W7P2";
	private String personalHealthNumber = "9746172488";
	private String email = "test@qa.com";
	
	@Test
	public void Can_Rebook_Walk_In_Appointment_Arrive_At_Wrong_Clinic_as_Clinician_BCVAXDEVIT() throws Exception {
		TestcaseID = "219910"; //C219910
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);
		log("/*-- 1.Login as an Clinician In-Clinic Experience --*/");
		InClinicExperiencePage inClinicExperiencePage = loginPage.loginWithClinicianCon();
		Thread.sleep(5000);
		inClinicExperiencePage.closeTabsHCA();
		Thread.sleep(5000);
		inClinicExperiencePage.selectICEFromApp();
		log("/*-- 2. Navigate to In Clinic Experience App --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickUserDefaultsTab();
		log("/*-- 3. Click on User Defaults Tab  --*/");
		Thread.sleep(2000);
		log("/*-- 4. Enter current date for UserDefaults --*/");
		inClinicExperiencePage.inputPreviousDateUserDefaults();
		Thread.sleep(2000);
		log("/*-- 5.----- Click on Save defaults button --*/");
		inClinicExperiencePage.clickSaveDefaultsButton();
		Thread.sleep(2000);
		log("/*-- 6. Click on register Tab --*/");
		inClinicExperiencePage.clickRegisterTab();
		Thread.sleep(2000);//
		inClinicExperiencePage.closeTabsHCA();
		log("/*-- 7. Close any open Tabs --*/");
		inClinicExperiencePage.clickRegisterButton();
		log("/*-- 8. Register New User -->" + legalFirstName + legalLastName + "--*/");
		inClinicExperiencePage.enterFirstName(legalFirstName);
		log("/*-- 9.----Enter First Name " + legalFirstName + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterLastName(legalLastName);
		log("/*-- 10.----Enter Last Name " + legalLastName + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterDateOfBirth(dateOfBirth);
		log("/*-- 11.----Enter Date of birth " + dateOfBirth + "--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterPostalCode(postalCode);
		log("/*-- 12.----Enter Postal code" + postalCode + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.enterPNH(personalHealthNumber);
		log("/*-- 13.----Enter PHN " + personalHealthNumber + "--*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickNonIndigenousRadioButton();
		log("/*-- 14.----click on non-Indigenous person radiobutton --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickVerifyPHNButton();
		log("/*-- 15.----click Verify PHN button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.successMessage();
		log("/*-- 16.'PNH match successful' --*/");
		inClinicExperiencePage.clickNextButton();
		log("/*-- 17.'Click next button --*/");
		Thread.sleep(5000);
		inClinicExperiencePage.enterEmail(email);
		log("/*-- 18.'Enter email address" + email + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.confirmEmail(email);
		log("/*-- 19.'Confirm email address" + email + " --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickReviewDetails();
		log("/*-- 20.Click review details Button --*/");
		Thread.sleep(2000);
		inClinicExperiencePage.clickRegisterButtonOnConfirmationPage();
		log("/*-- 21.Click register Button on confirmation page --*/");
		Thread.sleep(5000);
		inClinicExperiencePage.navigateAppointmentSchedulingTab();
		log("/*-- 22.Navigate to Appointment Scheduling Tab --*/");
		Thread.sleep(3000);
		String vaccineToSelect = "Covid19Vaccine";
		log("/*23.A---Select vaccination type: " + vaccineToSelect + "--*/");
		inClinicExperiencePage.selectOneOption(vaccineToSelect);
		log("/*--24.----select 'Search clinic name' tab --*/");
		inClinicExperiencePage.clickToSearchClinic();
		Thread.sleep(2000);
		log("/*-- 25.Search for and select clinic Age 12 and Above - Coquitlam - Lincoln Pharmacy & Coquitlam Travel Clinic --*/");
		inClinicExperiencePage.SearchForClinic();
		Thread.sleep(2000);
		log("/*--26.----click on Option Facility location  --*/");
		inClinicExperiencePage.clickFacilityOptionLocation();
		Thread.sleep(2000);
		log("/*--27.----select Appointment Day --*/");
		inClinicExperiencePage.selectBookingAppointmentDay();
		Thread.sleep(2000);
		log("/*--28.---- select time slot for Appointment --*/");
		inClinicExperiencePage.selectTimeSlotForAppointment();
		Thread.sleep(2000);
		log("/*--29.---Click Next Button to Schedule Appointment --*/");
		inClinicExperiencePage.clickNextButtonApptSchedulingPage();
		Thread.sleep(2000);
		log("/*30.----Verify Contact Information  --*/");
		inClinicExperiencePage.clickVerifyContactInformation();
		Thread.sleep(2000);
		log("/*-- 31.---Click Appointment Confirm Button --*/");
		inClinicExperiencePage.clickAppointmentConfirmButton();
		Thread.sleep(2000);
		log("/*-- 32.---Appointment Confirmed! Message Displayed --*/");
		inClinicExperiencePage.AppointmentConfirmationMessage();
		Thread.sleep(2000);
		log("/*-- 33.---Navigate to person account Related Tab --*/");
		inClinicExperiencePage.clickRelatedTab();
		Thread.sleep(2000);
		log("/*-- 34.---Click Go To In clinic experience button --*/");
		inClinicExperiencePage.ClickGoToInClinicExperienceButton();
		Thread.sleep(5000);
		log("/*-- 35.--- User can click Rebook Appointment button to book an appointment --*/");
		inClinicExperiencePage.ClickRebookAppointment();
		Thread.sleep(5000);
		log("/*-- 36---Click confirm and Save Button on Home Page --*/");
		inClinicExperiencePage.HomePageClickConfirmAndSaveButton();
		Thread.sleep(5000);
		log("/*-- 37---Click to select Agent --*/");
		inClinicExperiencePage.ClickAgentValue();
		Thread.sleep(2000);
		log("/*-- 38--- Select Agent From the Picklist Value ->COVID-19 mRNA --*/");
		inClinicExperiencePage.SelectAgentValue("COVID-19 mRNA");
		Thread.sleep(2000);
		log("/*-- 39---Click Save Consent Button --*/");
		inClinicExperiencePage.ClickSaveConsentButton();
		Thread.sleep(5000);
		log("/*-- 39--- Select Dosage --*/");
		inClinicExperiencePage.selectDosage();
		Thread.sleep(5000);
		log("/*---40. Save Immunization Information ---*/");
		inClinicExperiencePage.saveImmunizationInformation();
		Thread.sleep(2000);
		log("/*-- 41---Click Confirm and Save Administration Button --*/");
		inClinicExperiencePage.ClickConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*42.---Click Modal screen Confirm&Save Administration Button --*/");
		inClinicExperiencePage.ClickModalConfirmAndSaveAdministrationButton();
		Thread.sleep(3000);
		log("/*-- 43---the Home - Client Search supposed to showing up  --*/");
		inClinicExperiencePage.validateHomePageShownUp();
		Thread.sleep(3000);
		log("/*44.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveDuplicateCitizenParticipantAccount(email, legalLastNameASCII, legalFirstName);
	}
}

