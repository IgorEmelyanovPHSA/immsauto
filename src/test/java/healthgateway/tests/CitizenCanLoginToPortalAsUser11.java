package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.ProfilePage;
import healthgateway.pages.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;


@Listeners({TestListener.class})
public class CitizenCanLoginToPortalAsUser11 extends BaseTest {

	//Data for user 11
	private String fullName = "GATEWAY Carlos ELEVEN";
	private String personalHealthNumber = "9735353315";
	private String emailAddress = "nobody@healthgateway.gov.bc.ca";
	private String cellNumber = "2506715000";
	private String mailingAddress = "3815 HILLSPOINT STREET, CHATHAM, BC, V0G8B8";
	private String physicalAddress = "3815 HILLSPOINT STREET, CHATHAM, BC, V0G8B8";

	@Test
	public void citizenCanLoginIntoThePortal() throws Exception {
		TestcaseID = "318220"; //Original TC C318220
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//Validation
		log("Title: " +getDriver().getTitle());
		Assert.assertTrue(getDriver().getTitle().equals("Home"), "Title didn't match!");
		if(getDriver().findElements(By.xpath("//a[@title = 'Timeline']")).size()!=1){
			throw new RuntimeException("TimeLine tab not found");
		}

		log("Citizen successfully logged-in into test env: " +Utils.getTargetEnvironment());
		}

	}