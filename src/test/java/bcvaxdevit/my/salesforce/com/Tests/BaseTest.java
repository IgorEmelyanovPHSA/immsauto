package bcvaxdevit.my.salesforce.com.Tests;

import bcvaxdevit.my.salesforce.com.Pages.LoginPage;
import bcvaxdevit.my.salesforce.com.Pages.TestRailManager;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class BaseTest {
	protected String TestcaseID;
	public WebDriver driver;
	//private static WebDriver driver;
	//private static final WebDriver driver = new ChromeDriver();
	private final String BCVAXDEVIT_URL = "https://bcphsa--bcvaxdevit.my.salesforce.com/";
	protected LoginPage loginPage;
	PrintStream old;
	ByteArrayOutputStream logOutputSteps;
	
	@BeforeSuite
	public void beforeSuite() {
		//---This will execute before the Suite
		System.out.println("This will execute before the Suite");
	}
	
	@BeforeClass
	public void setUp() {
		System.out.println("This will execute before the Class");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("This will execute before the Test");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This will execute before the Method");
		// Create a stream to hold the log output
		logOutputSteps = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(logOutputSteps);
		// Save the old System.out!
		old = System.out;
		// Redirect log special stream to logOutput for TestRail
		System.setOut(ps);
		// ChromeDriver location set up in Utils class
		// System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(BCVAXDEVIT_URL);
		loginPage = new LoginPage(driver);
	}
	
	/////////////////After///////////////////
	@AfterMethod
	public void afterMethod(ITestResult result) throws Throwable {
		System.out.println("This will execute after the Method");
		System.out.flush();
		System.setOut(old);
		if (result.getStatus() == ITestResult.SUCCESS) {
			TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_PASSED_STATUS, "", logOutputSteps.toString());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_FAILED_STATUS, result.getThrowable().toString(), logOutputSteps.toString());
		}
		driver.manage().deleteAllCookies();
		driver.close();
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("This will execute after the Test");
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("This will execute after the Class");
	}
	
	@AfterSuite
	public void cleanUp() {
		System.out.println("This will execute after the Suite");
	}
	
	
}