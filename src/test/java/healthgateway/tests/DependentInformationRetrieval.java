package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.DependentsTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentInformationRetrieval extends BaseTest {

// You no longer have access to this dependent as they have turned 12.
// List of all dependent for USER11

	private String firstName = "John";
	private String lastName = "Tester";
	private String dateOfBirth = "";
	private String PHN = "";
	// Your access has expired

//	private String firstName = "JENNIFER";
//	private String lastName = "TESTFOUR";
//	private String dateOfBirth = "Aug 20, 2015";
//	private String PHN = "9874307175";

//	private String firstName = "ROMIL";
//	private String lastName = "SWAN";
//	private String dateOfBirth = "Feb 21, 2021";
//	private String PHN = "9746208843";

//	private String firstName = "MICHAEL";
//	private String lastName = "TESTERTWO";
//	private String dateOfBirth = "Dec 01, 2016"; //2016-Dec-01
//	private String PHN = "9874307208";

//	private String firstName = "Jeffrey Lawrence";
//	private String lastName = "Stallings";
//	private String dateOfBirth = "Oct 18, 2020"; //"2020-Oct-18";
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
		Assert.assertTrue(dependent.iSDependentPresentByName(firstName +" " +lastName), "Dependent not found");

		log("Citizen was able to retrieve dependant information successfully");
		}

	}