package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanLoginToPortalAsUser11 extends BaseTest {

	@Test
	public void citizenCanLoginIntoThePortal() throws Exception {
		TestcaseID = "318220"; //Original TC C318220
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//Validation
		log("Title: " +getDriver().getTitle());
		Assert.assertTrue(getDriver().getTitle().equals("Home"), "Title didn't match!");
		if(getDriver().findElements(By.xpath("//a[@title = 'Timeline']")).size()!=1){
			throw new RuntimeException("TimeLine tab not found");
		}

		mainPageHealthGateway.goToLogOut();

		// Validation of successfully logout needed to be added


		log("Citizen successfully logged-in into test env: " +Utils.getTargetEnvironment());
		}

	}