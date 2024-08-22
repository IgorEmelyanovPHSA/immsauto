package healthgateway.tests;

import Utilities.TestListener;
import healthgateway.pages.ExportTabPage;
import healthgateway.pages.MainPageHealthGateway;
import healthgateway.pages.Utils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class ExportFilteringByRecommendedImmunization extends BaseTest {

	//Data for user11
	private int numberOfRecordsExpectedForDateRange = 7;
	private String textSearchStartDate = "Jan 1, 2020";
	private String textSearchEndDate = "Jun 1, 2024";
	private String textBCVaccineScheduleURL = "https://immunizebc.ca/tools-resources/immunization-schedules";
	private String textBCGovernmentImmunizationPageURL = "https://www2.gov.bc.ca/gov/content/health/managing-your-health/immunizations#resources";

	@Test
	public void ExportFilteringDataByImmunizationsRecommendedImmunization() throws Exception {
		TestcaseID = "322314"; //Original TC C322314
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Target Browser: " + Utils.getTargetBrowser());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo Export tab
		ExportTabPage export = mainPageHealthGateway.goToTabExport();

		//Immunization dropDown must be selected first
		export.filterByImmunization();

		//Search by the date range
		export.filterByStartAndEndDate(textSearchStartDate, textSearchEndDate);

		//Validate number of records matched by date range
		Assert.assertTrue( export.getNumberOfRecords() == numberOfRecordsExpectedForDateRange, "Number of records displayed after date range search didn't match!");

		//Validate the links:
		Assert.assertTrue(export.getLinkValueBCVaccineSchedule().equalsIgnoreCase(textBCVaccineScheduleURL),  "BC Vaccine Schedule link is not valid");
		Assert.assertTrue(export.getLinkValueBCGovernmentImmunizationPage().equalsIgnoreCase(textBCGovernmentImmunizationPageURL),  "BC Government Immunization Page link is not valid");

		log("Filtering using the search box pass successfully");
		}

	}