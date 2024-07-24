package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanCreateAndViewNotes extends BaseTest {

	private String timeStamp = String.valueOf(System.currentTimeMillis());
	private String updatedNote = "Updated " +timeStamp;

	@Test
	public void citizenCreateMyNote() throws Exception {
		TestcaseID = "306812"; //Original TC C291362
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11 ONLY, data sets are for user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterMyNotes();
		log(timeLine.getDisplayingNumberOfRecords());

		//Delete any previously created notes
		if(timeLine.getNumberOfRecords()>1);{
			timeLine.deleteMyNotes();}

		//Validate all My Notes are deleted
		Assert.assertTrue(timeLine.getNumberOfRecords() <1, "Error. My Notes found!");

		//Creating a new note and validate note count =1 and note body
		timeLine.createANote(timeStamp);
		Assert.assertTrue(timeLine.getNumberOfRecords() ==1, "Error. Note is not saved!");
		timeLine.clickToExpendFirstNote();
		Assert.assertTrue(timeLine.getTextBodyFromFirstMyNote().equals(timeStamp), "Note text body didn't match");

		timeLine.editAndUpdateANote(updatedNote);
		Assert.assertTrue(timeLine.getTextBodyFromFirstMyNote().equals(updatedNote), "Updated Note text body didn't match");

		//Delete Note and validate
		timeLine.clickToExpendFirstNote(); // Need to minimize note
		timeLine.deleteMyNotes();
		Assert.assertTrue(timeLine.getNumberOfRecords() <1, "Error. My Notes found!");

		log("Create, update, delete Notes performed successfully");
		}

	}