package Utilities;

import bcvaxuat.my.salesforce.com.Tests.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(ITestResult result){
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot of {1}", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver dr, String testCaseName) throws IOException{
            //Remove this, only for testing
//            File fileSrc = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
//            try {
//                FileUtils.copyFile(fileSrc, new File("C:\\temp\\scren.png"));
//            } catch (IOException e) {
//                log(e.getMessage());
//            }
            //Remove this block
            return ((TakesScreenshot)dr).getScreenshotAs(OutputType.BYTES);
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

        try {
            saveScreenshotPNG(driver, testCaseName);
        } catch (Exception e){
            log("Couldn't take screenshot.");
            log(e.getMessage());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult var1){
        // TODO Auto-generated method stub
    }

    public void onTestStart(ITestResult iTestResult){
        String testCaseName = getTestMethodName(iTestResult);
        log("Test case '"+testCaseName+"' has started.");
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