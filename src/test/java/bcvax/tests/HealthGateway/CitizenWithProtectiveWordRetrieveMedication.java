package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.MainPageHealthGateway;
import bcvax.pages.TimeLineTabPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenWithProtectiveWordRetrieveMedication extends BaseTest {

	private String protectiveWord = "KEYWORD";

	@Test
	public void citizenCanAccessProtectiveInformation() throws Exception {
		TestcaseID = "306816"; //Original TC C296513
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Test Case " +"C" +TestcaseID);

		//CITIZEN with protective word to retrieve MEDICATION
		//Login as user 14 ONLY, user is set up with protective word
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser14();

		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		//Enter incorrect protective word
		timeLine.enterProtectiveWordAndContinue("incorrectPass");
		timeLine.textValidationInvalidProtectiveWord();

		//Happy Path
		timeLine.enterProtectiveWordAndContinue(protectiveWord);

		//Thread sleep to load 1869 records for the user
		timeLine.selectFilerMedications();

		log(timeLine.getDisplayingNumberOfRecords());

		//Validate medications records displayed
		log("value " +timeLine.getNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecords() >= 1, "Error. No medications records found");

		//Validation open first medication record
		timeLine.openFirstMedicationRecord();

		log("Citizen with protective word retrieved medication successfully");
		}

	}