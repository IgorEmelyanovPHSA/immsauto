package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.DependentsTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentOver12YearsOldAccessDenied extends BaseTest {

// You no longer have access to this dependent as they have turned 12.
	private String firstName = "John";
	private String lastName = "Tester";
	private String dateOfBirth = "";
	private String PHN = "";

	@Test
	public void dependentHasReached12AccessToTheirRecordsDenied() throws Exception {
		TestcaseID = "314682"; //Original TC C314682
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//User with age 12
		String firstAndLastNameOver12YearOld = firstName +" " +lastName ;

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

	}