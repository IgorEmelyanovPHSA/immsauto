package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ChromeDriverService service;
    private static ChromeOptions options;
    private static DesiredCapabilities capabilities;

    public static WebDriver getDriver(String browserType) {
        WebDriver driver;
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "jenkins":
                driver = initJenkinsChromeDriver();
                break;
            case "param":
                driver = initSpecificChromeDriver();
                break;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice . . .");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        System.out.println(TestUtil.getLogTime()+" Launching google chrome with new profile . . .");
        System.setProperty("webdriver.chrome.driver", TestUtil.PATH_TO_LOCAL_CHROME_DRIVER);
//
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(TestUtil.PATH_TO_LOCAL_CHROME_DRIVER))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-web-security");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(service, options);
//

//        service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("path/to/my/chromedriver.exe"))
//                .usingAnyFreePort()
//                .build();
//        chromeDriverService.start();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("...", true);
//        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--incognito");
//        option.merge(capabilities);
//        driver = new RemoteWebDriver(service.getUrl(), options);




//        capabilities = DesiredCapabilities.chrome();
//        options = new ChromeOptions();

//        options.addArguments("--disable-backgrounding-occluded-windows");

//        WebDriver driver = new ChromeDriver(options);

        //WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
        TestUtil.turnOnImplicitWaits(driver);

        return driver;
    }

    private static WebDriver initSpecificChromeDriver() {
        System.out.println(TestUtil.getLogTime()+" Launching google chrome with new profile . . .");
        System.setProperty("webdriver.chrome.driver", TestUtil.getPathToSpecificDriver("101"));
//
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(TestUtil.PATH_TO_LOCAL_CHROME_DRIVER))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(service, options);
//
 //       capabilities = DesiredCapabilities.chrome();
//        options = new ChromeOptions();
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-web-security");
//        options.addArguments("--disable-backgrounding-occluded-windows");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        WebDriver driver = new ChromeDriver(options);

        //WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
        TestUtil.turnOnImplicitWaits(driver);

        return driver;
    }

    private static WebDriver initChromeDriver2() throws IOException {
        System.out.println(TestUtil.getLogTime()+" Launching google chrome service with new profile . . .");

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(TestUtil.PATH_TO_LOCAL_CHROME_DRIVER))
                .usingPort(4445)
                .build();

        service.start();

        WebDriver driver = new RemoteWebDriver(service.getUrl(), new ChromeOptions());

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
        TestUtil.turnOnImplicitWaits(driver);

        return driver;
    }

    private static WebDriver initJenkinsChromeDriver() {
        System.out.println(TestUtil.getLogTime()+" Launching Jenkins google chrome with new profile . . .");
        System.setProperty("webdriver.chrome.driver", TestUtil.PATH_TO_JENKINS_CHROME_DRIVER);

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(TestUtil.PATH_TO_LOCAL_CHROME_DRIVER))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(service, options);

//        capabilities = DesiredCapabilities.chrome();
//        options = new ChromeOptions();
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-web-security");
//        options.addArguments("--disable-backgrounding-occluded-windows");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        WebDriver driver = new ChromeDriver(options);

        //WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
        TestUtil.turnOnImplicitWaits(driver);

        return driver;
    }

    public static void stopService(){
        if (service == null){
            TestUtil.log("Service is not running. There is nothing to stop.");
        } else {
            TestUtil.log("Stopping Chrome Driver Service . . .");
            service.stop();
        }
    }
}