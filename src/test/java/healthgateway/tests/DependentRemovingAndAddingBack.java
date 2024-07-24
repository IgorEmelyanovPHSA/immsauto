package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.DependentsTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentRemovingAndAddingBack extends BaseTest {

	private String firstName = "ROMIL";
	private String lastName = "SWAN";
	private String dateOfBirth = "Feb 21, 2021"; //Feb 21, 2021
	private String PHN = "9746208843";

	@Test
	public void deleteAndAddDependentBack() throws Exception {
		TestcaseID = "314683"; //Original TC C314683
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Dependents
		DependentsTabPage dependent = mainPageHealthGateway.goToTabDependents();

		log("Total number of dependents " +String.valueOf(dependent.getNumberOfDependents()));

		//Add dependent if not present
		if(dependent.iSDependentPresentByName(firstName +" " +lastName) == false){
			dependent.addDependent(firstName,lastName,dateOfBirth,PHN);
			Assert.assertTrue(dependent.iSDependentPresentByName(firstName +" " +lastName), "Error. Dependent not added!");
			log("Dependent is added successfully");
		}

		//Click delete dependent and click cancel, check dependent is not deleted
		dependent.deleteDependentByName(firstName + " " + lastName, "cancel");
		Assert.assertTrue(dependent.iSDependentPresentByName(firstName +" " +lastName), "Dependent got deleted when shouldn't");

		//Click delete dependent and click Remove to confirm, check dependent got deleted
		dependent.deleteDependentByName(firstName + " " + lastName, "remove");
		Assert.assertFalse(dependent.iSDependentPresentByName(firstName +" " +lastName), "Dependent is NOT Deleted");
		log("Dependent is deleted successfully");

		//Add back dependent and exit
		dependent.addDependent(firstName,lastName,dateOfBirth,PHN);
		Assert.assertTrue(dependent.iSDependentPresentByName(firstName +" " +lastName), "Error. Dependent not added!");
		log("Dependent is added successfully after deletion");

		log("Total number of dependents " +String.valueOf(dependent.getNumberOfDependents()));
		log("Citizen was able to delete and add dependant successfully");
		}

	}