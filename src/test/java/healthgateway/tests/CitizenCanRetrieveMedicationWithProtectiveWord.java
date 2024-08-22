package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanRetrieveMedicationWithProtectiveWord extends BaseTest {

	private String protectiveWord = "KEYWORD";

	@Test
	public void citizenCanAccessProtectiveInformation() throws Exception {
		TestcaseID = "306816"; //Original TC C296513
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
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

		//Thread sleep to load 1869 records for the user14
		timeLine.selectFilerMedications();

		log(timeLine.getDisplayingNumberOfRecords());

		//Validate medications records displayed
		log("Number of medication records displayed " +timeLine.getNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecords() >= 1, "Error. No medications records found");

		//Validation open first medication record for user14
		timeLine.openFirstMedicationRecordUser14();

		log("Citizen with protective word retrieved medication successfully");
		}

	}