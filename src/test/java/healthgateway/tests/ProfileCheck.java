package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.ProfilePage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class ProfileCheck extends BaseTest {

	//Data for user 11
	private String fullName = "GATEWAY Carlos ELEVEN";
	private String personalHealthNumber = "9735353315";
	private String mailingAddress = "3815 HILLSPOINT STREET, CHATHAM, BC, V0G8B8";
	private String physicalAddress = "3815 HILLSPOINT STREET, CHATHAM, BC, V0G8B8";

	@Test
	public void citizenChekOwnProfileData() throws Exception {
		TestcaseID = "306817"; //Original TC C299850
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11 ONLY, data sets are for user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Profile
		ProfilePage profile = mainPageHealthGateway.goToProfilePage();

		//Validations
		Assert.assertTrue(profile.getFullName().equalsIgnoreCase(fullName), "Full name didn't match!");
		Assert.assertTrue(profile.getPersonalHealthNumber().equalsIgnoreCase(personalHealthNumber), "Personal Health Number didn't match!");
		Assert.assertTrue(profile.getMailingAddress().equalsIgnoreCase(mailingAddress), "Mailing Address didn't match!");
		Assert.assertTrue(profile.getMailingAddress().equalsIgnoreCase(physicalAddress), "Physical Address didn't match!");

		log("Full Name, Personal Health Number, Mailing address, Physical matched successfully.");
		}

	}