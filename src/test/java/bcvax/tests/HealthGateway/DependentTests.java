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
public class DependentTests extends BaseTest {

// You no longer have access to this dependent as they have turned 12.

//	private String firstName = "John";
//	private String lastName = "Tester";
//	private String dateOfBirth = "";
//	private String PHN = "";
// Your access has expired

//	private String firstName = "JENNIFER";
//	private String lastName = "TESTFOUR";
//	private String dateOfBirth = "Aug 20, 2015";   //Aug 20, 2015
//	private String dateOfBirth2 = "2015-Aug-20";   //Aug 20, 2015
//	private String PHN = "9874307175";

//	private String firstName = "ROMIL";
//	private String lastName = "SWAN";
//	private String dateOfBirth = "2021-Feb-21";
//	private String PHN = "9746208843";

//	private String firstName = "MICHAEL";
//	private String lastName = "TESTERTWO";
//	private String dateOfBirth = "2016-Dec-01";
//	private String PHN = "9874307208";

//	private String firstName = "Jeffrey Lawrence Stallings";
//	private String lastName = "Jeffrey Lawrence Stallings";
//	private String dateOfBirth = "2020-Oct-18";
//	private String PHN = "9872868095";

	@Test
	public void dependentInformationRetrieval() throws Exception {
		TestcaseID = "314677"; //Original TC C314677
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		String firstAndLastNameOfFirstDependent = "John Tester";

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Dependents
		DependentsTabPage dependent = mainPageHealthGateway.goToTabDependents();

		log("Number of dependents " +String.valueOf(dependent.getNumberOfDependents()));

		//Validation for user11
		Assert.assertTrue(dependent.getFirstAndLastNameOfFirstDependent().equals(firstAndLastNameOfFirstDependent), "First dependent not found");
		//Needs to be updated in the future to be dynamic
		Assert.assertTrue(dependent.getNumberOfDependents() ==5, "Error. Not all dependent found!");

		log("Citizen was able to retrieve dependant information successfully");
		}

	@Test
	public void dependentHasReached12AccessToTheirRecordsDenied() throws Exception {
		TestcaseID = "C314682"; //Original TC C314682
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//User with age 12
		String firstAndLastNameOver12YearOld = "John Tester";

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Dependents
		DependentsTabPage dependent = mainPageHealthGateway.goToTabDependents();

		log("Number of dependents " +String.valueOf(dependent.getNumberOfDependents()));

		//Open Profile of dependant who is over 12 years old
		dependent.clickOnProfileOfDependentByName(firstAndLastNameOver12YearOld);

		//Validation of the wording for the dependent access has expired
		dependent.validateAccessExpired(firstAndLastNameOver12YearOld);

		log("Citizen has no access to dependant who is over 12 years old. Test case finished successfully");
	}


	//@Test  Needs work, do not run
	public void dependentInformationUpdate() throws Exception {
		TestcaseID = "306815"; //Original TC C305201
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
//		if(dependent.getNumberOfDependents()>=1);{
//			dependent.deleteDependents();}

		//Validate all dependents are deleted
		Assert.assertTrue(dependent.getNumberOfDependents() <1, "Error. Dependents found!");

		//Add dependent and validation
		//	dependent.addDependent(firstName, lastName, dateOfBirth, PHN);

		Assert.assertTrue(dependent.getNumberOfDependents() ==1, "Error. Dependent is not saved!");
		log("first and last name " +dependent.getFirstAndLastNameOfFirstDependent());
		Assert.assertTrue(dependent.getFirstAndLastNameOfFirstDependent().equals(timeStamp), "Comment text didn't match");


		log("Citizen was able to retrieve dependant information successfully");
	}

	}