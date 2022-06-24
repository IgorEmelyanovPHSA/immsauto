package bcvaxuat.my.salesforce.com.Pages;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailManager {
	public static String TEST_RUN_ID = "4039";
	public static String TESTRAIL_USERNAME = "Igor.Emelyanov@phsa.ca";
	public static String TESTRAIL_PASSWORD = "Bcvaxtestrail07*";
	public static String RAILS_ENGINE_URL = "https://qmsoftwaretest.testrail.io/";
	public static final int TEST_CASE_PASSED_STATUS = 1;
	public static final int TEST_CASE_FAILED_STATUS = 5;
	
	
	public static void addResultsForTestCase(String testCaseId, int status,
	                                         String error, String logOutputTestSteps) throws IOException, APIException {
		String testRunId = TEST_RUN_ID;
		
		APIClient client = new APIClient(RAILS_ENGINE_URL);
		client.setUser(TESTRAIL_USERNAME);
		client.setPassword(TESTRAIL_PASSWORD);
		Map data = new HashMap();
		data.put("status_id", status);
		//data.put("comment",  "Test Executed - Status updated automatically from Selenium Java test automation.");
		data.put("comment", "Test Executed - Status updated automatically from Selenium Java test automation."
				+ "\r\n" + logOutputTestSteps);
		client.sendPost("add_result_for_case/" + testRunId + "/" + testCaseId + "", data);
		
	}
	
	
}