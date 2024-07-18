package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.DependentsTabPage;
import bcvax.pages.MainPageHealthGateway;
import bcvax.pages.TimeLineTabPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentTestsUser35 extends BaseTest {

	private String status = "Final";

	private String firstName = "JENNIFER";
	private String lastName = "TESTFOUR";
	private String dateOfBirth = "Aug 20, 2015";   //Aug 20, 2015
	private String dateOfBirth2 = "2015-Aug-20";   //Aug 20, 2015
	private String PHN = "9874307175";


//	private String firstName = "ROMIL";
//	private String lastName = "SWAN";
//	private String dateOfBirth = "2021-Feb-21";
//	private String PHN = "9746208843";


	@Test
	public void createUpdateDeleteComment() throws Exception {
		TestcaseID = "306815"; //Original TC C295751
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		String timeStamp = String.valueOf(System.currentTimeMillis());

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Dependents
		DependentsTabPage dependent = mainPageHealthGateway.goToTabDependents();


		log("Number of dependents " +String.valueOf(dependent.getNumberOfDependents()));

		//Delete any previously created dependents
		if(dependent.getNumberOfDependents()>=1);{
			dependent.deleteDependents();}

		//Validate all dependents are deleted
		Assert.assertTrue(dependent.getNumberOfDependents() <1, "Error. Dependents found!");

		//Add dependent and validation
		dependent.addDependent(firstName, lastName, dateOfBirth, PHN);

		Assert.assertTrue(dependent.getNumberOfDependents() ==1, "Error. Dependent is not saved!");
		log("first and last name " +dependent.getFirstAndLastNameOfFirstDependent());
		Assert.assertTrue(dependent.getFirstAndLastNameOfFirstDependent().equals(timeStamp), "Comment text didn't match");




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