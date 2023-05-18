package primarycare.tests;

import bcvax.pages.TestRailManager;
import bcvax.pages.Utils;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.output.TeeOutputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import primarycare.pages.LoginPage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import static constansts.Header.SUPPLY_CONTAINER_NAME_FULL;
import static constansts.Header.SUPPLY_DISTRIBUTION_DESCRIPTION_FULL;

public class API_BaseTest_PrimaryCare {
    public final static SimpleDateFormat LOG_TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    protected DecimalFormat df = new DecimalFormat("0.00");
    protected String TestcaseID;
    //public WebDriver driver;
    private ByteArrayOutputStream logOutputSteps;
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        log("Environment: "+ Utils.getTargetEnvironment());
        captureBothStreams();
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
        //loginPage = new LoginPage(getDriver());
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
        //driver.manage().deleteAllCookies();
        //driver.quit();
    }

    //public WebDriver getDriver() {
        //return driver;
    //}

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

    protected static Map<String, String> searchCriteria(String vaccine, String fromDistributionLocation) {
        return ImmutableMap.of(SUPPLY_CONTAINER_NAME_FULL, vaccine,
                SUPPLY_DISTRIBUTION_DESCRIPTION_FULL, fromDistributionLocation);
    }

    public static void log(String msg) {
        System.out.println(getLogTime() + " " + msg);
    }

}
