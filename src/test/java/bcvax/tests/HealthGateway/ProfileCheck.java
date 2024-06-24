package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.MainPageHealthGateway;
import bcvax.pages.ProfilePage;
import bcvax.pages.TimeLineTabPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class ProfileCheck extends BaseTest {

	//Data for user 11
	private String fullName = "GATEWAY Carlos ELEVEN";
	private String personalHealthNumber = "9735353315";
	private String emailAddress = "nobody@healthgateway.gov.bc.ca";
	private String cellNumber = "2506715000";
	private String mailingAddress = "3815 HILLSPOINT STREET, CHATHAM, BC, V0G8B8";
	private String physicalAddress = "3815 HILLSPOINT STREET, CHATHAM, BC, V0G8B8";

	@Test
	public void citizenChekOwnProfileData() throws Exception {
		TestcaseID = "306817"; //Original TC C299850
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11 ONLY, data sets are for user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Profile
		ProfilePage profile = mainPageHealthGateway.goToProfilePage();

		//Validations
		Assert.assertTrue(profile.getFullName().equalsIgnoreCase(fullName), "Full name didn't match!");
		Assert.assertTrue(profile.getPersonalHealthNumber().equalsIgnoreCase(personalHealthNumber), "Personal Health Number didn't match!");
		Assert.assertTrue(profile.getEmailAddress().equalsIgnoreCase(emailAddress), "Email Address didn't match!");
		Assert.assertTrue(profile.getCellNumber().equalsIgnoreCase(cellNumber), "Cell Number didn't match!");
		Assert.assertTrue(profile.getMailingAddress().equalsIgnoreCase(mailingAddress), "Mailing Address didn't match!");
		Assert.assertTrue(profile.getMailingAddress().equalsIgnoreCase(physicalAddress), "Physical Address didn't match!");


		log("Full Name, Personal Health Number, Email address, Cell number (SMS notifications), Mailing address, Physical matched successfully.");
		}

	}