package utility;

import tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;


public class WebEventListener extends BaseTest implements WebDriverEventListener {

    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Before navigating to: '" + url + "'");
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Successfully navigated to: '" + url + "'");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Value of the: " + element.toString() + " before any changes made");
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Element value changed to: " + element.toString());
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Trying to click on: " + element.toString());
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Successfully clicked on: " + element.toString());
    }

    public void beforeNavigateBack(WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Navigating back to previous page");
    }

    public void afterNavigateBack(WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Navigated back to previous page");
    }

    public void beforeNavigateForward(WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Navigating forward to next page");
    }

    public void afterNavigateForward(WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Navigated forward to next page");
    }

    public void onException(Throwable error, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" WebEventListener: Exception occured at " + error.getMessage());
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        // TODO Auto-generated method stub
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        // TODO Auto-generated method stub
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        //TODO Auto-generated method stub
        System.out.println(TestUtil.getLogTime()+" Trying to find element " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        // TODO Auto-generated method stub
        System.out.println(TestUtil.getLogTime()+" Found element " + by.toString());
    }

    public void beforeScript(String script, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Executing script: " + script);
    }

    public void afterScript(String script, WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Finished executing script: " + script);
    }

    public void beforeAlertAccept(WebDriver driver) {
        System.out.println("! IMPLEMENT beforeAlertAccept !");
    }

    public void afterAlertAccept(WebDriver driver) {
        System.out.println("! IMPLEMENT afterAlertAccept !");
    }

    public void afterAlertDismiss(WebDriver driver) {
        System.out.println("! IMPLEMENT afterAlertDismiss !");
    }

    public void beforeAlertDismiss(WebDriver driver) {
        System.out.println("! IMPLEMENT beforeAlertDismiss !");
    }

    public void beforeNavigateRefresh(WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Reloading page . . .");
    }

    public void afterNavigateRefresh(WebDriver driver) {
        System.out.println(TestUtil.getLogTime()+" Finished reloading page.");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub
//    System.out.println("Value of the:" + element.toString() + " before any changes made");
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // TODO Auto-generated method stub
//    System.out.println("Element value changed to: " + element.toString());
    }

    public void beforeSwitchToWindow(String what, WebDriver driver) {
        // TODO Auto-generated method stub
    }

    public void afterSwitchToWindow(String what, WebDriver driver) {
        // TODO Auto-generated method stub
    }
}