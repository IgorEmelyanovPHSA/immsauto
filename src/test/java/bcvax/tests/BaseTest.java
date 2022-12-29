package bcvax.tests;

import bcvax.pages.LoginPage;
import bcvax.pages.TestRailManager;
import bcvax.pages.Utils;
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
	protected LoginPage loginPage;

	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		log("Environment: "+Utils.getTargetEnvironment());
		captureBothStreams();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		loginPage = new LoginPage(getDriver());
	}
	
	/////////////////After///////////////////
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result){
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
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
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

	public ByteArrayOutputStream getLogOutputSteps() {
		return logOutputSteps;
	}

	public static String getLogTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return LOG_TIMESTAMP_FORMAT.format(timestamp);
	}
	
	public static void log(String msg) {
		System.out.println(getLogTime() + " " + msg);
	}
	
}