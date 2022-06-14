package utility;


import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static pages.TestRailManager.TEST_RUN_ID;

public class TestUtil {
    public final static String PATH_TO_LOCAL_CHROME_DRIVER = Paths.get(System.getProperty("user.dir"), "resources", "102", "chromedriver.exe").toString();
    public final static String PATH_TO_JENKINS_CHROME_DRIVER = Paths.get(System.getProperty("user.dir"), "resources", "102", "chromedriver.exe").toString();
    public final static String PATH_TO_TEST_UPLOAD_FILE = Paths.get(System.getProperty("user.dir"), "resources", "testUploadFiles", "cat.jpg").toString();
    public final static SimpleDateFormat LOG_TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    public final static String PATH_TO_CONFIG = Paths.get(System.getProperty("user.dir"), "config.properties").toString();
    public final static int PAGE_LOAD_WAIT = 30;
    public final static int ELEMENT_PRESENT_WAIT = 15;
    private String testRailRunId = TestUtil.getEnvConfigProperty("testrail_run_id");
    private static String RAILS_ENGINE_URL = "https://qmsoftwaretest.testrail.io/";
    private final static String TESTRAIL_USERNAME = "Igor.Emelyanov@phsa.ca";
    private final static String TESTRAIL_PASSWORD = "Bcvaxtestrail07*";
//    private String testRailRunId = TestUtil.getEnvConfigProperty("testrail_run_id");
//    private final static String TESTRAIL_USERNAME = "rodion.stratov@phsa.ca";
//    private final static String TESTRAIL_PASSWORD = "ZdFZr4aTk4ol36TwItLd-WYQNNeZmQQ5R6uo6XaN1";
//public static String TEST_RUN_ID = "4038";
//    public static String TESTRAIL_USERNAME = "Igor.Emelyanov@phsa.ca";
//    public static String TESTRAIL_PASSWORD = "Bcvaxtestrail07*";
//    public static String RAILS_ENGINE_URL = "https://qmsoftwaretest.testrail.io/";
//    public static final int TEST_CASE_PASSED_STATUS = 1;
//    public static final int TEST_CASE_FAILED_STATUS = 5;




    private static Properties config = new Properties();

    public TestUtil() throws Exception {
    }

    public static String getPathToSpecificDriver(String targetDriverVersion) {
        return Paths.get(System.getProperty("user.dir"), "resources", targetDriverVersion, "chromedriver.exe").toString();
    }

    public static String getTargetEnvironment() throws Exception {
        String env = System.getProperty("environment");
        if (env == null)
            env = getConfigProperty("environment");
        return env;
    }

    public static Boolean shoudIUpdateTestRail() throws Exception {
        String yesORno = System.getProperty("test_rail");
        if (yesORno == null){
            yesORno = getConfigProperty("test_rail");
            return yesORno.equalsIgnoreCase("yes");
        } else
            return yesORno.equalsIgnoreCase("yes");
    }

    public static String getTargetBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null)
            browser = "chrome";
        return browser;
    }

    public static String getEnvConfigProperty(String propertyToGet) throws Exception {
        return getConfigProperty(getTargetEnvironment()+"."+propertyToGet);
    }

    public static String getLogTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return LOG_TIMESTAMP_FORMAT.format(timestamp);
    }

    public static void log(String msg){
        System.out.println(getLogTime()+" "+msg);
    }

    private static String getConfigProperty(String targetProperty) throws Exception {
        config.load(new FileInputStream(PATH_TO_CONFIG));
        return config.getProperty(targetProperty);
    }

    public static void turnOffImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void turnOnImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(ELEMENT_PRESENT_WAIT, TimeUnit.SECONDS);
    }

    public static void updateTestRail(int status, String testCaseId, String error,
                                      ByteArrayOutputStream output) throws IOException, APIException {

        com.gurock.testrail.APIClient client = new APIClient(RAILS_ENGINE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        Map data = new HashMap();
        data.put("status_id", status);
        data.put("comment", "Test Executed - Status updated automatically from Selenium Java test automation."
                + "\r\n" + output.toString());
        client.sendPost("add_result_for_case/" + TEST_RUN_ID + "/" + testCaseId + "", data);
        log("TestRail was updated");
    }


//    public static void updateTestRail(int statusId, String testId) throws Exception {
//        utility.APIClient client = new utility.APIClient("https://qmsoftwaretest.testrail.io/");
//        client.setUser(TESTRAIL_USERNAME);
//        client.setPassword(TESTRAIL_PASSWORD);
//
//        Map data = new HashMap();
//        data.put("status_id", statusId);
//        data.put("comment", "Result produced by automation on '"+getTargetEnvironment()+"' environment ");
//        JSONObject r = (JSONObject) client.sendPost("add_result/"+testId, data);
//    }

//    public static void updateTestRail(int statusId, String testId, String message) throws Exception {
//        utility.APIClient client = new utility.APIClient("https://qmsoftwaretest.testrail.io/");
//        client.setUser(TESTRAIL_USERNAME);
//        client.setPassword(TESTRAIL_PASSWORD);
//
//        Map data = new HashMap();
//        data.put("status_id", statusId);
//        data.put("comment", "Result produced by automation on '"+getTargetEnvironment()+"' environment "+message);
//        JSONObject r = (JSONObject) client.sendPost("add_result/"+testId, data);
//    }
//
//    public static String getTestRunId(String runId, String targetCaseId) throws Exception {
//        utility.APIClient client = new utility.APIClient("https://qmsoftwaretest.testrail.io/");
//        client.setUser(TESTRAIL_USERNAME);
//        client.setPassword(TESTRAIL_PASSWORD);
//
//        JSONObject r = (JSONObject) client.sendGet("get_tests/"+runId);
//        JSONArray allTests = (JSONArray) r.get("tests");
//
//        Long targetCaseIdLong = Long.valueOf(targetCaseId.replaceAll("C", ""));
//
//        for (int i = 0; i < allTests.size(); i++) {
//            JSONObject test = (JSONObject) allTests.get(i);
//            Long id = (Long) test.get("id");
//            Long case_id = (Long) test.get("case_id");
//            if(case_id.equals(targetCaseIdLong)){
//                log(String.format("https://qmsoftwaretest.testrail.io/index.php?/runs/view/%s, Case Id %s, Test Run Id %d", runId, targetCaseId, id));
//                return String.valueOf(id);
//            }
//        }
//
//        log("Test Run Id was not found for Case Id "+targetCaseId+" in https://qmsoftwaretest.testrail.io/index.php?/runs/view/"+runId);
//        return "nothing_found";
//    }

}