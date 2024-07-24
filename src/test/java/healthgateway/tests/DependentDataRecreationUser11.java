package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.DependentsTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentDataRecreationUser11 extends BaseTest {

	//Needs to run after all 'Dependents' TC to make sure all data restored for USER11
	private Object[][] testData = {
			{"John", "Tester", "", ""},
			{"JENNIFER", "TESTFOUR", "Aug 20, 2015", "9874307175"},
			{"ROMIL", "SWAN", "Feb 21, 2021", "9746208843"},
			{"MICHAEL", "TESTERTWO", "Dec 01, 2016", "9874307208"},
			{"Jeffrey Lawrence", "Stallings", "Oct 18, 2020", "9872868095"}
	};

	@Test()
	public void AddMissingDependentsForUser11() throws Exception {
		TestcaseID = "315206"; //Original TC C315206
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " + "C" + TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Dependents
		DependentsTabPage dependent = mainPageHealthGateway.goToTabDependents();

		log("Total number of dependents: " + String.valueOf(dependent.getNumberOfDependents()));

		//Check if dependent is present, if missing add it
		for (Object[] personData : testData) {
			String firstName = (String) personData[0];
			String lastName = (String) personData[1];
			String dateOfBirth = (String) personData[2];
			String PHN = (String) personData[3];

			if (dependent.iSDependentPresentByName(firstName + " " + lastName) == false) {
				dependent.addDependent(firstName, lastName, dateOfBirth, PHN);
				Assert.assertTrue(dependent.iSDependentPresentByName(firstName + " " + lastName), "Error. Dependent not added!");
				log("Dependent " + firstName + " " + lastName + " is added successfully");
			} else {
				log("Dependent " + firstName + " " + lastName + " is present");
			}
		}

		log("Total number of dependents after test: " + String.valueOf(dependent.getNumberOfDependents()));
		log("All dependents for user11 are present, TC finished successfully");
		}

	}

