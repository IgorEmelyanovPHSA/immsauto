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
		log("value " +timeLine.getNumberOfRecords());
		Assert.assertTrue(timeLine.getNumberOfRecords() >= 1, "Error. No medications records found");

		//Validation open first medication record
		timeLine.openFirstMedicationRecord();

		log("Citizen can view their medication records successfully");
		}

	}