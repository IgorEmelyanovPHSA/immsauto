package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenGettingLabResults extends BaseTest {

	//Data for first record to match
	private String orderStatus = "Completed w/Modification";
	private String collectionDate = "2021-Jul-04, 6:45 PM";
	private String orderingProvider = "PLISVCI, BROOKS";
	private String reportingLab = "VIHA - Department of Laboratory Medicine, Pathology and Medical Genetics";

	@Test
	public void viewAndValidateLabResults() throws Exception {
		TestcaseID = "306813"; //Original TC C291524
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterLabResults();

		timeLine.openFirstLabResultRecord();

		//Validations
		log(timeLine.getDisplayingNumberOfRecords());
		Assert.assertTrue(timeLine.getOrderStatus().equals(orderStatus), "Order Status didn't match!");
		Assert.assertTrue(timeLine.getCollectionDate().equals(collectionDate), "Collection date didn't match!");
		Assert.assertTrue(timeLine.getOrderingProvider().equals(orderingProvider), "Ordering Provider didn't match!");
		Assert.assertTrue(timeLine.getReportingLab().equals(reportingLab), "Reporting Lab didn't match!");

		log("Order status, Collection date, Ordering Provider and Reporting Lab matched successfully");
		}

	}