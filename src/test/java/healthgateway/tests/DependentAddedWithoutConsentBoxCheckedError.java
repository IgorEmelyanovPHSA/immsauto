package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.DependentsTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class DependentAddedWithoutConsentBoxCheckedError extends BaseTest {

	private String firstName = "ROMIL";
	private String lastName = "SWAN";
	private String dateOfBirth = "Feb 21, 2021"; //Feb 21, 2021
	private String PHN = "9746208843";

	@Test
	public void dependentAddedWithoutConsentBoxCheckedErrorMessageValidation() throws Exception {
		TestcaseID = "314684"; //Original TC C314684
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
			dependent.deleteDependentByName(firstName + " " + lastName, "Remove");
			Assert.assertFalse(dependent.iSDependentPresentByName(firstName +" " +lastName), "Dependent was not deleted!");
			log("Dependent is deleted successfully");
		}

		//Add dependent without consent
		dependent.addDependentWithoutConsent(firstName,lastName,dateOfBirth,PHN);
		Thread.sleep(1000);
		Assert.assertFalse(dependent.isBtnRegisterDependentEnabled(), "Button registration is enabled");

		//Check the registration page is still open
		Assert.assertTrue(dependent.iSDependentRegistrationPageOpen(), "Dependent registration page is not open");

		//Check consent checkbox and register dependent
		dependent.clickOnBtnConsentDependentRegistration();
		dependent.clickOnBtnRegisterDependent();

		Assert.assertTrue(dependent.iSDependentPresentByName(firstName +" " +lastName), "Dependent is not added!");
		log("Dependent added successfully");

		log("Citizen was able to validate consent checkbox and added dependent successfully");
		}

	}