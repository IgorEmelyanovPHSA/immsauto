package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Utilities.ApiQueries.queryToGetUniqueLink;

@Listeners({TestListener.class})
public class ApiTest1 extends BaseTest {

	private String legalFirstName = "Anne-marie";
	private String legalLastName = "BCVaxJacketts";
	private String legalMiddleName = "Elissa";
	private String dateOfBirth = "Aug 16, 1903";
	private String postalCode = "V3B0J5";
	private String personalHealthNumber = "9746173988";
	private boolean isIndigenous = false;
	private String email = "accountToDelete@phsa.ca";
	private String phoneNumber = "6041234568";
	private String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";
	private String vaccineToSelect = "InfluenzaVaccine";

	@Test
	public void getCitizenInfo() throws Exception {
		TestcaseID = "228855"; //C228855
		log("Target Environment: " + Utils.getTargetEnvironment());

		//Open Health Gateway portal
		MainPageHealthGateway mainPageHealthGateway = loginPage.openHealthGatewayPortal();

		mainPageHealthGateway.loginWithBCServiceCard();
		Thread.sleep(7000);
		mainPageHealthGateway.getNetWorkValue();
		Thread.sleep(2000);
		log("no bearer");
		mainPageHealthGateway.goToTabTimeLine();
		mainPageHealthGateway.getNetWorkValue();



		CommonMethods com = new CommonMethods(getDriver());

		log("/*0.---API call to remove duplicate citizen participant account if found--*/");
		Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);

		log("/*1.---Open citizen portal and click btn Register Now--*/");
		RegisterToGetVaccinatedPage registerToGetVaccinatedPage = loginPage.openRegisterToGetVaccinatedPage();
		registerToGetVaccinatedPage.clickBtnRegisterNow();

		log("/*2.---Fill all registration information and click btn continue--*/");
		registerToGetVaccinatedPage.fillMandatoryFieldsOnRegistrationSection(legalFirstName, legalLastName, legalMiddleName, dateOfBirth, postalCode,
				personalHealthNumber, isIndigenous);

		log("/*3.---Fill email and phone number on contact information section and click btn continue--*/");
		registerToGetVaccinatedPage.fillMandatoryFieldsOnContactInformationSection(email, phoneNumber);

		log("/*4.---Check checkbox certify and click btn submit--*/");
		registerToGetVaccinatedPage.certifyAndSubmit();

		log("/*5.---Check for registration successful message and save conformation number--*/");
		String conformationNumberText = registerToGetVaccinatedPage.registrationSuccessfulPageDisplayed();

		//log("/*6.---Login as an Clinician to ICE--*/");
		//InClinicExperiencePage inClinicExperiencePage = loginPage.loginAsClinicianICE();
		//Thread.sleep(5000);

		log("/*6.----Login as an Clinician to CIB --*/");
		loginPage.loginAsImmsBCAdmin();
		ClinicInBoxPage clinicInBox = new ClinicInBoxPage(driver);
		Thread.sleep(10000);

		//inClinicExperiencePage.verifyIsICEpageDisplayed();
		clinicInBox.verifyIsClinicInBoxPageDisplayed();
		Thread.sleep(10000);

		log("/*6.1.----Close All previously opened Tab's --*/");
		clinicInBox.closeAllTabs();
		Thread.sleep(5000);

		log("/*7.---Search for Participant account by conformation number " + conformationNumberText + "--*/");
		com.globalSearch(conformationNumberText);

		log("/*7.1---Validation, isUserFound account validation --*/");
		boolean isUserFound = com.isUserFoundValidation(legalFirstName, legalMiddleName, legalLastName);
		if (!isUserFound) {
			throw new RuntimeException("Exception: User " + legalFirstName + " " + legalLastName + " not found!!!");
		}

		log("/*8.---Get unique link using Sales Force query over API--*/");
		String uniqueLink = queryToGetUniqueLink(conformationNumberText);

		log("/*9.---Open book an appointment portal from unique link--*/");
		BookAnAppointmentPage bookAnAppointmentPage = loginPage.openBookAnAppointmentPage(uniqueLink);
		bookAnAppointmentPage.bookAnAppointmentPageDisplayed();

		}

	}