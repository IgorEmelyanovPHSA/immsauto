package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.RegistrationPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class RegistrationPhoneVerification extends BaseTest {


	@Test
	public void registrationPhoneVerificationFromTheList() throws Exception {
		TestcaseID = "314564"; //C314564
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		String[] validPhoneNumbersList = { "604-456-7890", "236-456-7890", "672-456-7890", "778-456-7890", "779-456-7890" };

		String[] invalidPhoneNumbersList = { "345-456-7890", "555-456-7890" };

		//Login as user 08
		RegistrationPage registration = loginPage.loginIntoHGWithBCServiceCardAsUser08();

		//Site is slow
		Thread.sleep(2000);

		//By default, checkbox is checked, to uncheck it
		registration.clickOnTextNotificationCheckbox();

		//To test valid phone number
		for (int i = 0; i < validPhoneNumbersList.length; i++) {
			registration.phoneCheckTest(validPhoneNumbersList[i]);
			int errorCountCheck = registration.checkForPhoneNumberValidationErrorMessages();

			//Validation for valid phone number
			Assert.assertTrue(errorCountCheck == 0,  "Valid phone number validation failed for " +validPhoneNumbersList[i]);

			//Print the status of checked phone number
			log("Validation pass for valid phone number: " +validPhoneNumbersList[i]);

			//Click on text notifications checkbox to refresh the last entered phone number
			registration.clickOnTextNotificationCheckbox();
			Thread.sleep(500);
		}

		//To test invalid phone number
		for (int i = 0; i < invalidPhoneNumbersList.length; i++) {
			registration.phoneCheckTest(invalidPhoneNumbersList[i]);
			int errorCountCheck = registration.checkForPhoneNumberValidationErrorMessages();

			//Validation for invalid phone number
			Assert.assertTrue(errorCountCheck == 1,  "Invalid phone number validation failed for " +invalidPhoneNumbersList[i]);

			//Print the status of checked phone number
			log("Validation pass for invalid phone number, 'Invalid phone number' message displayed for: " +invalidPhoneNumbersList[i]);

			//Click on text notifications checkbox to refresh the last entered phone number
			registration.clickOnTextNotificationCheckbox();
		}

		log("Validation PASS for " +validPhoneNumbersList.length +" valid phone numbers");
		log("Validation PASS for " +invalidPhoneNumbersList.length +" invalid phone number, 'Invalid phone number' message was displayed");
		log("Test case PASS for valid and invalid phone number");
		}

	}