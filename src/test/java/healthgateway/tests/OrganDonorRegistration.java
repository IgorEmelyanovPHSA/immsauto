package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.ServicesTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class OrganDonorRegistration extends BaseTest {

	private String textSensitiveWording = "The file that you are downloading contains personal information. If you are on a public computer," +
			" please ensure that the file is deleted before you log off.";
	private String textExternalLink = "http://www.transplant.bc.ca/organ-donation/register-as-an-organ-donor/register-your-decision";

	@Test
	public void organDonorRegistrationValidation() throws Exception {
		TestcaseID = "314664"; //Original TC C314664
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		String timeStamp = String.valueOf(System.currentTimeMillis());

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Services Page
		ServicesTabPage services = mainPageHealthGateway.goToTabServices();

		//Validate Status: Registered
		services.validateStatusRegistered();

		//Validation Sensitive wording
		Assert.assertTrue(services.checkSensitiveWording().equalsIgnoreCase(textSensitiveWording),  "Sensitive wording didn't match");

		services.clickBtnCancel();

		//Validation external link
		Assert.assertTrue(services.getLinkValue().equalsIgnoreCase(textExternalLink),  "External link is not valid");

		log("Organ Donor Registration performed successfully");
		}

	}