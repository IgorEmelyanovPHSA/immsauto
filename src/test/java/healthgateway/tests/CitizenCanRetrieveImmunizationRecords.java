package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.TimeLineTabPage;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class CitizenCanRetrieveImmunizationRecords extends BaseTest {

	//Data for first record to match
	private String dateHeaderOfFirstRecord = "2021-Jul-04";
	private String product = "Pfizer mRNA BNT162b2";
	private String immunizationAgent = "COVID-19 mRNA";
	private String lotNumber = "EK4241HX";

	@Test
	public void viewAndValidateImmunizationRecords() throws Exception {
		TestcaseID = "314672"; //Original TC C314672
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterImmunizations();

		timeLine.openFirstImmunizationRecord();

		//Validations
		log(timeLine.getDisplayingNumberOfRecords());
		Assert.assertTrue(timeLine.getHeaderDate().equals(dateHeaderOfFirstRecord), "Date in header of the record didn't match!");
		Assert.assertTrue(timeLine.getProduct().equals(product), "Product didn't match!");
		Assert.assertTrue(timeLine.getImmunizingAgent().equals(immunizationAgent), "Immunization Agent didn't match!");
		Assert.assertTrue(timeLine.getLotNumber().equals(lotNumber), "Lot number didn't match!");

		log("Record date, product, immunization agent and lot number are matched successfully");
		}

	}