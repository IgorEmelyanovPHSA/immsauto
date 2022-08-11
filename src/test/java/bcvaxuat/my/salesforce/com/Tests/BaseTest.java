package bcvaxuat.my.salesforce.com.Tests;

import bcvaxuat.my.salesforce.com.Pages.LoginPage;
import bcvaxuat.my.salesforce.com.Pages.TestRailManager;
import bcvaxuat.my.salesforce.com.Pages.Utils;
import org.apache.commons.io.output.TeeOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class BaseTest {
	public final static SimpleDateFormat LOG_TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
	protected String TestcaseID;
	public WebDriver driver;
	private ByteArrayOutputStream logOutputSteps;
	private final String BCVAXUAT_URL = "https://bcphsa--bcvaxuat.my.salesforce.com/";
	protected LoginPage loginPage;

//	@BeforeSuite
//	public void beforeSuite() {
//		//---This will execute before the Suite
//		log("This will execute before the Suite");
//	}
//
//	@BeforeClass
//	public void setUp() {
//		log("This will execute before the Class");
//	}
//
//	@BeforeTest
//	public void beforeTest() {
//		log("This will execute before the Test");
//	}
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		log("This will execute before the Method");
		captureBothStreams();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(BCVAXUAT_URL);
		loginPage = new LoginPage(getDriver());
	}
	
	/////////////////After///////////////////
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws Throwable {
		log("This will execute after the Method");
		try {
			if(Utils.shoudIUpdateTestRail()){
				if (result.getStatus() == ITestResult.SUCCESS) {
					TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_PASSED_STATUS, "", logOutputSteps.toString());
				} else if (result.getStatus() == ITestResult.FAILURE) {
					TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_FAILED_STATUS, result.getThrowable().toString(), logOutputSteps.toString());
				}
			} else {
				log("Test Rail will not be updated.");
			}
		} catch (Exception e) {
			log("Test Rail was not updated: "+e);
		}
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_PASSED_STATUS, "", logOutputSteps.toString());
//		} else if (result.getStatus() == ITestResult.FAILURE) {
//			TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_FAILED_STATUS, result.getThrowable().toString(), logOutputSteps.toString());
//		}
		driver.manage().deleteAllCookies();
		driver.close();
	}

//	@AfterTest
//	public void afterTest() {
//		log("This will execute after the Test");
//	}
//
//	@AfterClass
//	public void tearDown() {
//		log("This will execute after the Class");
//	}
//
//	@AfterSuite
//	public void cleanUp() {
//		log("This will execute after the Suite");
//	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	private void captureBothStreams() {
		logOutputSteps = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(logOutputSteps);
		TeeOutputStream bothStreams = new TeeOutputStream(ps, System.out);
		PrintStream both = new PrintStream(bothStreams);
		System.setOut(both);
	}
	
	public static String getLogTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return LOG_TIMESTAMP_FORMAT.format(timestamp);
	}
	
	public static void log(String msg) {
		System.out.println(getLogTime() + " " + msg);
	}
	
}