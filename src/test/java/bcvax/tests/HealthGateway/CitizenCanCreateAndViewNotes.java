package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.MainPageHealthGateway;
import bcvax.pages.ProfilePage;
import bcvax.pages.TimeLineTabPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanCreateAndViewNotes extends BaseTest {

	private String timeStamp = String.valueOf(System.currentTimeMillis());
	private String updatedNote = "Updated " +timeStamp;

	@Test
	public void citizenCreateMyNote() throws Exception {
		TestcaseID = "291362"; //C291362
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11 ONLY, data sets are for user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

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

		timeLine.editAndUpdateANote(updatedNote);
		Assert.assertTrue(timeLine.getTextBodyFromFirstMyNote().equals(updatedNote), "Updated Note text body didn't match");

		//Delete Note and validate
		timeLine.clickToExpendFirstNote(); // Need to minimize note
		timeLine.deleteMyNotes();
		Assert.assertTrue(timeLine.getNumberOfMyNotes() <1, "Error. My Notes found!");

		log("Create, update, delete Notes performed successfully");
		}

	}