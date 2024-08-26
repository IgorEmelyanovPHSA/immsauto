package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.ProfilePage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class ProfileCheckEmail extends BaseTest {

	@Test
	public void profileEmailVerificationFromTheList() throws Exception {
		TestcaseID = "322436"; //Original TC C320052
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " + "C" + TestcaseID);

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

		//Login as user 11 ONLY, data sets are for user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Profile
		ProfilePage profile = mainPageHealthGateway.goToProfilePage();

		//To test valid emails
		for (int i = 0; i < validEmails.length; i++) {
			profile.emailCheck(validEmails[i]);
			int errorCountCheck = profile.checkForEmailValidationErrorMessages();

			//Validation for valid email
			Assert.assertTrue(errorCountCheck == 0, "Valid email validation failed for " + validEmails[i]);

			//Print the status of checked email
			log("Validation pass for valid email: " + validEmails[i]);
		}

		//To test invalid emails
		for (int i = 0; i < invalidEmails.length; i++) {
			profile.emailCheck(invalidEmails[i]);
			int errorCountCheck = profile.checkForEmailValidationErrorMessages();

			//Validation for invalid email
			Assert.assertTrue(errorCountCheck == 1, "Invalid email validation failed for " + invalidEmails[i]);

			//Print the status of checked email
			log("Validation pass for invalid email, 'Invalid email' message displayed for: " + invalidEmails[i]);
		}

		log("Validation PASS for " + validEmails.length + " valid emails");
		log("Validation PASS for " + invalidEmails.length + " invalid emails, 'Valid email is required' message was displayed");
		log("Test case PASS for valid and invalid email on user profile page");
	}

}