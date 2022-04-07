package prodsuppqa.my.salesforce.com.Tests;

import prodsuppqa.my.salesforce.com.Pages.LoginPage;
import prodsuppqa.my.salesforce.com.Pages.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    private WebDriver driver;
    //private static final WebDriver driver = new ChromeDriver();
    private final String PRODSUPPQA_URL = "https://bcphsa--prodsuppqa.my.salesforce.com/";
    protected LoginPage loginPage;

    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("This will execute before the Suite");
        // ChromeDriver location set up in Utils class
        //System.setProperty("webdriver.chrome.whitelistedIps", "");
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
        driver.manage().window().maximize();
        driver.get(PRODSUPPQA_URL);
        loginPage=new LoginPage(driver);
    }

    @BeforeClass
    public void setUp()
    {
        System.out.println("This will execute before the Class");
        // ChromeDriver location set up in Utils class
        //driver = new ChromeDriver();
        //System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
        //driver.manage().window().maximize();
        //driver.get(PRODSUPPQA_URL);
        //loginPage=new LoginPage(driver);

    }

    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("This will execute before the Method");
    }


    @AfterMethod
    public void afterMethod()
    {
        System.out.println("This will execute after the Method");
    }


    @AfterClass
    public void tearDown() {
        System.out.println("This will execute after the Class");
        //driver.manage().deleteAllCookies();
        //driver.close();
        //driver.quit();
    }

    @AfterSuite
    public void cleanUp(){
        System.out.println("This will execute after the Suite");
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
