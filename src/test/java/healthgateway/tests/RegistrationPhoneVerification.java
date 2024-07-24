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
		TestcaseID = "310904"; //Original TC C310904
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);


		String[] validPhoneNumbersList = {
				"1234567890", "123-456-7890", "123.456.7890", "123 456 7890", "(123) 456-7890", "+1 123-456-7890", "1 123 456 7890"};

		String[] invalidPhoneNumbersList = {
				"123456789", "12345678900", "1234a56789", "1234 456 122", "0012345674", " +1 345 777 1111", "1234--123445", "1234@56789", "qwertqwert",
				"123!@#$%^&7", "!7783201122", "0000000000"};

//		String[] validPhoneNumbersList = {
//				"1234567890", "123-456-7890"};
//
//		String[] invalidPhoneNumbersList = {
//				"1234a56789", "1234 456 122"};

		//Login as user 08
		RegistrationPage registration = loginPage.loginIntoHGWithBCServiceCardAsUser08();

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
		log("Test case for valid and invalid phone number pass");
		}

	}