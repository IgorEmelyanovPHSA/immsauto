package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanRetrieveMedicationNoProtectiveWordSet extends BaseTest {

	@Test
	public void citizenCanRetrieveMedicationRecords() throws Exception {
		TestcaseID = "314633"; //Original TC C314633
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//CITIZEN without protective word set to retrieve MEDICATION
		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilerMedications();

		log(timeLine.getDisplayingNumberOfRecords());

		//Validate medications records displayed
		log("Number of records: " +timeLine.getNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecords() >= 1, "Error. No medications records found");

		//Validation open first medication record for user 11
		timeLine.openFirstMedicationRecordUser11();

		log("Citizen can view their medication records successfully");
		}

	}