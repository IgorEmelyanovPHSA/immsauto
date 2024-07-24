package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class AuthenticationWithBCServicesCard extends BaseTest {


	private String pageTitle = "Home";

	@Test
	public void citizenCanLoginIntoThePortal() throws Exception {
		TestcaseID = "314671"; //Original TC C314671
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//Validations
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase(pageTitle), "Title of the page didn't match! " +driver.getTitle());

		log("User able to login with own credentials successfully");
		}

	}