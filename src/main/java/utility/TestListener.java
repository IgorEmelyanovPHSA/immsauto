package utility;

import io.qameta.allure.Attachment;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import tests.BaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestListener extends BaseTest implements ITestListener {

    private static final int TEST_CASE_PASSED_STATUS = 1;
    private static final int TEST_CASE_FAILED_STATUS = 5;

    private static String getTestMethodName(ITestResult result){
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot of {1}", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver dr, String testCaseName) throws IOException{
        try {
            return ((TakesScreenshot)dr).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log(e.getMessage());
            log("Ashot didn't work. Taking screen shot with selenium instead.");
            BufferedImage originalImage = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(dr).getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        }
    }

    @Attachment(value = "Log of {1}", type = "text/plain")
    public static String saveTextLog (String message, String testCaseName){
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testCaseName = getTestMethodName(iTestResult);
        log("Test case '"+testCaseName+"' has failed.");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        ByteArrayOutputStream output = ((BaseTest) testClass).getOutputStream();

        try {
            saveScreenshotPNG(driver, testCaseName);
        } catch (Exception e){
            log("Couldn't take screenshot.");
            log(e.getMessage());
        }

        if (output != null)
            saveTextLog(output.toString(), testCaseName);

        String testrailID = (String) iTestResult.getTestContext().getAttribute("testrailID");

        try {
            if(TestUtil.shoudIUpdateTestRail()){
                TestUtil.updateTestRail(TEST_CASE_FAILED_STATUS, testrailID, iTestResult.getThrowable().toString(), output);
            } else {
                log("TestRail will not be updated.");
            }
        } catch (Exception e) {
            log("TestRail was not updated: "+e.toString());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult var1){
        // TODO Auto-generated method stub
    }

    public void onTestStart(ITestResult iTestResult){
        String testCaseName = getTestMethodName(iTestResult);
        log("Test case '"+testCaseName+"' has started.");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        String testCaseName = getTestMethodName(iTestResult);
        log("Test case '"+testCaseName+"' has successfully finished.");
        String testrailID = (String) iTestResult.getTestContext().getAttribute("testrailID");
        String message = (String) iTestResult.getTestContext().getAttribute("message");
        Object testClass = iTestResult.getInstance();
        ByteArrayOutputStream output = ((BaseTest) testClass).getOutputStream();
        try {
            if(TestUtil.shoudIUpdateTestRail()){
                if(StringUtils.isEmpty(message))
                    TestUtil.updateTestRail(TEST_CASE_PASSED_STATUS, testrailID, "", output );
                else
                    TestUtil.updateTestRail(TEST_CASE_PASSED_STATUS, testrailID, message, output);
            } else {
                log("Test Rail will not be updated.");
            }
        } catch (Exception e) {
            log("Test Rail was not updated: "+e.toString());
        }
    }

    public void onTestSkipped(ITestResult var1){
        // TODO Auto-generated method stub
    }

    public void onStart(ITestContext var1){
        // TODO Auto-generated method stub
    }

    public void onFinish(ITestContext var1){
        // TODO Auto-generated method stub
    }
}