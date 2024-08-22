package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.DependentsTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentRegistrationFailWhenDataNotMatching extends BaseTest {

	private String firstName = "MICHAEL";
	private String lastName = "TESTERTWO";
	private String dateOfBirth = "Dec 01, 2016"; //2016-Dec-01
	private String PHN = "9874307208";


	@Test
	public void dependentRegistrationFailWhenDataNotMatching() throws Exception {
		TestcaseID = "315205"; //Original TC C315205
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Dependents
		DependentsTabPage dependent = mainPageHealthGateway.goToTabDependents();

		log("Total number of dependents " +String.valueOf(dependent.getNumberOfDependents()));

		//Delete dependent if present
		if(dependent.iSDependentPresentByName(firstName +" " +lastName) == true){
			dependent.deleteDependentByName(firstName + " " + lastName, "remove");
			Assert.assertFalse(dependent.iSDependentPresentByName(firstName +" " +lastName), "Error. Dependent not removed");
			log("Dependent is removed successfully");
		}

		//Try to add dependent with incorrect firstName
		dependent.addDependent("MICHAEL Test",lastName,dateOfBirth,PHN);
		Assert.assertTrue(dependent.iSWarningInformationDoesNotMatchDisplayed(), "Warning message was not displayed. Incorrect firstName");
		dependent.clickOnBtnCancelDependentRegistration(); //Click btn Cancel to close registration pop-up

		//Try to add dependent with incorrect lastName
		dependent.addDependent(firstName,"TESTERTWO Test",dateOfBirth,PHN);
		Assert.assertTrue(dependent.iSWarningInformationDoesNotMatchDisplayed(), "Warning message was not displayed. Incorrect lastName");
		dependent.clickOnBtnCancelDependentRegistration(); //Click btn Cancel to close registration pop-up

		//Try to add dependent with incorrect phn
		dependent.addDependent(firstName,lastName,dateOfBirth,"9715491522");
		Assert.assertTrue(dependent.iSWarningInformationDoesNotMatchDisplayed(), "Warning message was not displayed. Incorrect phn");
		dependent.clickOnBtnCancelDependentRegistration(); //Click btn Cancel to close registration pop-up

		//Try to add dependent with incorrect dateOfBirth
		dependent.addDependent(firstName,lastName,"Dec 01, 2017",PHN);
		Assert.assertTrue(dependent.iSWarningInformationDoesNotMatchDisplayed(), "Warning message was not displayed. Incorrect dateOfBirth");
		dependent.clickOnBtnCancelDependentRegistration(); //Click btn Cancel to close registration pop-up

		//Add dependent with correct information and validate the result
		dependent.addDependent(firstName,lastName,dateOfBirth,PHN);
		Assert.assertTrue(dependent.iSDependentPresentByName(firstName +" " +lastName), "Error. Dependent not added!");
		log("Dependent is added successfully with correct data");

		log("Citizen was not able to register dependent with not matching data. Only with correct data was able to register dependent. TC run successfully");
		}

	}