package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.RegistrationPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class RegistrationEmailVerification extends BaseTest {


	@Test
	public void registrationEmailVerificationFromTheList() throws Exception {
		TestcaseID = "314563"; //Original TC C314563
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		String[] validEmails = {
				"\"vijaya<pitta\"@gmail.com", "\"vijaya>pitta\"@gmail.com", "\"vijaya(pitta\"@gmail.com", "\"vijaya)pitta\"@gmail.com", "\"vijaya[pitta\"@gmail.com",
				"\"vijaya]pitta\"@gmail.com", "\"vijaya\\pitta\"@gmail.com", "\"vijaya,pitta\"@gmail.com", "\"vijaya;pitta\"@gmail.com", "\"vijaya:pitta\"@gmail.com",
				"\"vijaya pitta\"@gmail.com", "\"vijaya@pitta\"@gmail.com", "\"vijaya\"pitta\"@gmail.com", "vijaya.pitta@gmail.com", "vijaya_pitta@gmail.com",
				"vijaya.pitta00123@gmail.com", "john#doe@gmail.com", "vijaya.pitta.test@gmail.com", "vijaya!pitta@gmail.com", "vijaya$pitta@gmail.com",
				"vijaya%@gmail.com", "vijaya^pitta@gmail.com", "vijaya&pitta@gmail.com", "vijaya*pittta@gmail.com", "vijaya-pitta@gmail.com", "vijaya+pitta@gmail.com",
				"vijaya=pitta@gmail.com", "vijaya~pitta@gmail.com", "vijaya`pitta@gmail.com", "vijaya{pitta@gmail.com", "vijaya}pitta@gmail.com",
				"vijaya'pitta@gmail.com", "VIJAYA.PITTA@gmail.com", "vijaya'pitta'test@gmail.com", "vijaya.pitta@gmail.com", "vijaya.pitta@1234.com",
				"vijaya.pitta@GMAIL.com", "vijaya.pitta@zzz.YT", "user@email.co.uk"};

		String[] invalidEmails = {
				"vijaya<pitta@gmail.com", "vijaya>pitta@gmail.com", "vijaya(pitta@gmail.com", "vijaya)pitta@gmail.com", "vijaya[pitta@gmail.com",
				"vijaya]pitta@gmail.com", "vijaya\\pitta@gmail.com", "vijaya,pitta@gmail.com", "vijaya;pitta@gmail.com", "vijaya:pitta@gmail.com",
				"vijaya pitta@gmail.com", "vijaya@pitta@gmail.com", "vijaya\"pitta@gmail.com", "vijaya.pitta@", "vijaya..pitta@gmail.com", "vijaya@gmai l.com",
				"vijaya@gmail..com", "user@", "vijaya.pitta@gmail@com", "vijaya.pitta@gmail_com", "vijaya.pitta@gmail>com", "vijaya.pitta@gmail<com",
				"vijaya.pitta@gmail(com", "vijaya.pitta@gmail)com", "vijaya.pitta@gmail[com", "vijaya.pitta@gmail]com", "vijaya.pitta@gmail\\com",
				"vijaya.pitta@gmail,com", "vijaya.pitta@gmail;com", "vijaya.pitta@gmail:com", "vijaya.pitta@gmail com", "@gmail.com"};

		//Login as user 08
		RegistrationPage registration = loginPage.loginIntoHGWithBCServiceCardAsUser08();

		//Site is slow
		Thread.sleep(2000);
		registration.clickOnEmailNotificationCheckbox();

		//To test valid emails
		for (int i = 0; i < validEmails.length; i++) {
			registration.emailCheckTest(validEmails[i]);
			int errorCountCheck = registration.checkForEmailValidationErrorMessages();

			//Validation for valid email
			Assert.assertTrue(errorCountCheck == 0,  "Valid email validation failed for " +validEmails[i]);

			//Print the status of checked email
			log("Validation pass for valid email: " +validEmails[i]);

			//Click on email notifications checkbox to refresh the last entered email
			registration.clickOnEmailNotificationCheckbox();
		}

		//To test invalid emails
		for (int i = 0; i < invalidEmails.length; i++) {
			registration.emailCheckTest(invalidEmails[i]);
			int errorCountCheck = registration.checkForEmailValidationErrorMessages();

			//Validation for invalid email
			Assert.assertTrue(errorCountCheck == 2,  "Invalid email validation failed for " +invalidEmails[i]);

			//Print the status of checked email
			log("Validation pass for invalid email, 'Invalid email' message displayed for: " +invalidEmails[i]);

			//Click on email notifications checkbox to refresh the last entered email
			registration.clickOnEmailNotificationCheckbox();
		}

		log("Validation PASS for " +validEmails.length +" valid emails");
		log("Validation PASS for " +invalidEmails.length +" invalid emails, 'Invalid email' message was displayed");
		log("Test case PASS for valid and invalid email");
		}

	}