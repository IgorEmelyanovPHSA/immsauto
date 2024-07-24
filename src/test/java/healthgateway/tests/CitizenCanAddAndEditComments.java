package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanAddAndEditComments extends BaseTest {

	private String status = "Final";

	@Test
	public void createUpdateDeleteComment() throws Exception {
		TestcaseID = "306815"; //Original TC C295751
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);
		String timeStamp = String.valueOf(System.currentTimeMillis());

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterImagingReports();

		timeLine.openFirstImagingReport();

		//Validations
		log(timeLine.getDisplayingNumberOfRecords());

		Assert.assertTrue(timeLine.getStatusImgReport().equals(status), "Status didn't match!");

		//Delete any previously created comment for the record
		if(timeLine.getNumberOfCommentsForFirstImgReport()>1);{
			timeLine.deleteCommentsForRecord();	}

		//Validate all comments are deleted for firstRecord
		Assert.assertTrue(timeLine.getNumberOfCommentsForFirstImgReport() <1, "Error. Comment for first record found");

		//Creating a new comment for the first record and validate comment count =1 and comment body match
		timeLine.createAComment(timeStamp);
		Assert.assertTrue(timeLine.getNumberOfCommentsForFirstImgReport() ==1, "Error. Comment is not saved!");
		Assert.assertTrue(timeLine.getTextFromFirstComment().equals(timeStamp), "Comment text didn't match");

		//Update comment and validate
		String updateComment = "updated " +timeStamp;
		timeLine.updateAComment(updateComment);
		Assert.assertTrue(timeLine.getTextFromFirstComment().equals(updateComment), "Comment text didn't match");

		//Delete comment
		timeLine.deleteCommentsForRecord();
		Assert.assertTrue(timeLine.getNumberOfCommentsForFirstImgReport() ==0, "Error. Comment is not deleted");

		log("Create, update, delete comment performed successfully");
		}

	}