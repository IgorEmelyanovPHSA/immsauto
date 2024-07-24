package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenGettingImagingReports extends BaseTest {

	//Data for first record to match
	private String dateHeaderOfFirstRecord = "2022-Oct-08";
	private String status = "Final";
	private String healthAuthority = "VCH";

	@Test
	public void viewAndValidateImagingReports() throws Exception {
		TestcaseID = "306814"; //Original TC C293392
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterImagingReports();

		timeLine.openFirstImagingReport();

		//Validations
		log(timeLine.getDisplayingNumberOfRecords());
		log(timeLine.getHealthAuthority());
		Assert.assertTrue(timeLine.getHeaderDate().equals(dateHeaderOfFirstRecord), "Date in header of the record didn't match");
		Assert.assertTrue(timeLine.getStatusImgReport().equals(status), "Status didn't match!");
		Assert.assertTrue(timeLine.getHealthAuthority().equals(healthAuthority), "Health Authority didn't match!");

		log("Date in header, Status and Health authority matched successfully");
		}

	}