package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.ProfilePage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class ProfileCheckPhoneNumber extends BaseTest {

	@Test
	public void profilePhoneVerificationFromTheList() throws Exception {
		TestcaseID = "322437"; //Original TC C320343
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " + "C" + TestcaseID);

		String[] validPhoneNumbersList = { "604-456-7890", "236-456-7890", "672-456-7890", "778-456-7890", "779-456-7890" };

		String[] invalidPhoneNumbersList = { "345-456-7890", "555-456-7890" };

		//Login as user 11 ONLY, data sets are for user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Profile
		ProfilePage profile = mainPageHealthGateway.goToProfilePage();

		//To test valid phone number
		for (int i = 0; i < validPhoneNumbersList.length; i++) {
			profile.phoneNumberCheck(validPhoneNumbersList[i]);
			int errorCountCheck = profile.checkForNumberValidationErrorMessages();

			//Validation for valid phone number
			Assert.assertTrue(errorCountCheck == 0, "Valid phone number validation failed for " + validPhoneNumbersList[i]);

			//Print the status of checked phone number
			log("Validation pass for valid phone number: " + validPhoneNumbersList[i]);
		}

		//To test invalid phone number
		for (int i = 0; i < invalidPhoneNumbersList.length; i++) {
			profile.phoneNumberCheck(invalidPhoneNumbersList[i]);
			int errorCountCheck = profile.checkForNumberValidationErrorMessages();

			//Validation for invalid phone number
			Assert.assertTrue(errorCountCheck == 1, "Invalid phone number validation failed for " + invalidPhoneNumbersList[i]);

			//Print the status of checked phone number
			log("Validation pass for invalid phone number, 'Invalid phone number' message displayed for: " + invalidPhoneNumbersList[i]);
		}

		log("Validation PASS for " + validPhoneNumbersList.length + " valid phone number");
		log("Validation PASS for " + invalidPhoneNumbersList.length + " invalid phone number, 'Valid SMS number is required' message was displayed");
		log("Test case PASS for valid and invalid phone number on user profile page");
	}

}