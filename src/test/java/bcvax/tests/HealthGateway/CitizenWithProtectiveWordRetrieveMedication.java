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

	private String timeStamp = String.valueOf(System.currentTimeMillis());
	private String protectiveWord = "KEYWORD";

	@Test
	public void citizenCanAccessProtectiveInformation() throws Exception {
		TestcaseID = "296513"; //C296513
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Test Case " +"C" +TestcaseID);

		//CITIZEN with protective word to retrieve MEDICATION

		//Login as user 14 ONLY, data sets are for user 14
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser14();

		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		//Happy Path
		timeLine.enterProtectiveWordAndContinue(protectiveWord);

		timeLine.selectFilterMyNotes();
		log(timeLine.getDisplayingNumberOfRecords());

		//Delete any previously created notes
		if(timeLine.getNumberOfMyNotes()>1);{
			timeLine.deleteMyNotes();}

		//Validate all My Notes are deleted
		Assert.assertTrue(timeLine.getNumberOfMyNotes() <1, "Error. My Notes found!");

		//Creating a new note and validate note count =1 and note body
		timeLine.createANote(timeStamp);
		Assert.assertTrue(timeLine.getNumberOfMyNotes() ==1, "Error. Note is not saved!");
		timeLine.clickToExpendFirstNote();
		Assert.assertTrue(timeLine.getTextBodyFromFirstMyNote().equals(timeStamp), "Note text body didn't match");

		//Delete Note and validate
		timeLine.clickToExpendFirstNote(); // Need to minimize note
		timeLine.deleteMyNotes();
		Assert.assertTrue(timeLine.getNumberOfMyNotes() <1, "Error. My Notes found!");

		log("Create, update, delete Notes performed successfully");
		}

	}