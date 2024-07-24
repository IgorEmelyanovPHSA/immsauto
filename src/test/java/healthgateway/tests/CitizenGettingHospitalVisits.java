package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenGettingHospitalVisits extends BaseTest {

	//Data for first record to match
	private String dateHeaderOfFirstRecord = "2022-May-03";
	private String location = "Sechelt Shishalh Hospital";

	@Test
	public void viewAndValidateHospitalVisits() throws Exception {
		TestcaseID = "314621"; //Original TC C314621
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterHospitalVisits();

		timeLine.openFirstHospitalVisit();

		//Validations
		log(timeLine.getDisplayingNumberOfRecords());

		Assert.assertTrue(timeLine.getHeaderDate().equals(dateHeaderOfFirstRecord), "Date in header of the record didn't match");
		Assert.assertTrue(timeLine.getLocation().equals(location), "Location didn't match!");

		log("Date in header and hospital location matched successfully");
		}

	}