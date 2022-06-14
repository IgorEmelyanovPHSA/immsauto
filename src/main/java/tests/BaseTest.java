package tests;


import org.apache.commons.io.output.TeeOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utility.DriverFactory;
import utility.Pages;
import utility.TestUtil;
import utility.WebEventListener;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class BaseTest {

	public String testRailRunId;

	{
		try {
			testRailRunId = TestUtil.getEnvConfigProperty("testrail_run_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ByteArrayOutputStream outputStream;
	private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	protected String TestcaseID;
	public WebDriver driver;
	public Pages on;
	//private static WebDriver driver;
	//private static final WebDriver driver = new ChromeDriver();
	private final String BCVAXDEVIT_URL = "https://bcphsa--bcvaxdevit.my.salesforce.com/";
	protected LoginPage loginPage;
	PrintStream old;
	ByteArrayOutputStream logOutputSteps;


	public WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}

	public void setDriver(WebDriver dr) {
		WEB_DRIVER_THREAD_LOCAL.set(dr);
	}

	public ByteArrayOutputStream getOutputStream() {
		return outputStream;
	}

	private void captureBothStreams(){
		outputStream = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(outputStream);
		TeeOutputStream bothStreams = new TeeOutputStream(ps, System.out);
		PrintStream both = new PrintStream(bothStreams);
		System.setOut(both);
	}

	@BeforeSuite
	public void beforeSuite() {
		//---This will execute before the Suite
		System.out.println("This will execute before the Suite");
	}
	
//	@BeforeClass
//	public void setUp() {
//		System.out.println("This will execute before the Class");
//	}
//
//	@BeforeTest
//	public void beforeTest() {
//		System.out.println("This will execute before the Test");
//	}
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		System.out.println("This will execute before the Method");
		captureBothStreams();
		log("Java version is "+System.getProperty("java.version"));
		log("Target environment is "+ utility.TestUtil.getTargetEnvironment());
		log("Target browser is "+ utility.TestUtil.getTargetBrowser());

		WEB_DRIVER_THREAD_LOCAL.set(new EventFiringWebDriver(DriverFactory.getDriver(TestUtil.getTargetBrowser())).register(new WebEventListener()));
		on = new Pages(WEB_DRIVER_THREAD_LOCAL.get());

		// Create a stream to hold the log output
//		logOutputSteps = new ByteArrayOutputStream();
//		PrintStream ps = new PrintStream(logOutputSteps);
//		// Save the old System.out!
//		old = System.out;
		// Redirect log special stream to logOutput for TestRail
		//System.setOut(ps);
		// ChromeDriver location set up in Utils class
		// System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get(BCVAXDEVIT_URL);

		getDriver().navigate().to(TestUtil.getEnvConfigProperty("url"));
		loginPage = new LoginPage(getDriver());
	}
	
	/////////////////After///////////////////
	@AfterMethod
	public void tearDown(ITestResult result) throws Throwable {
		System.out.println("This will execute after the Method");
//		System.out.flush();
//		System.setOut(old);
/*		if (result.getStatus() == ITestResult.SUCCESS) {
			TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_PASSED_STATUS, "", logOutputSteps.toString());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			TestRailManager.addResultsForTestCase(TestcaseID, TestRailManager.TEST_CASE_FAILED_STATUS, result.getThrowable().toString(), logOutputSteps.toString());
		}*/
//		driver.manage().deleteAllCookies();
//		driver.close();
		WEB_DRIVER_THREAD_LOCAL.get().quit();
		WEB_DRIVER_THREAD_LOCAL.remove();
		DriverFactory.stopService();
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

	public static void log(String msg) {
		System.out.println(msg);
	}

}