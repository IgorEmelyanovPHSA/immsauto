package prodsuppqa.my.salesforce.com.Tests.InClinicExperience;
import prodsuppqa.my.salesforce.com.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import prodsuppqa.my.salesforce.com.Pages.Utils;

public class Dose1VaccineAdmimistration {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    void beforeSuite()
    {
        System.out.println("This will execute before the Suite");
    }
    public static void main(String[] args)
    {
        System.out.println("This will execute before the Suite");
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @BeforeClass
    void beforeClass()
    {
        System.out.println("This will execute before the Class");
    }

    @BeforeMethod
    void beforeMethod()
    {
        System.out.println("This will execute before the Method");
    }


    @AfterMethod
    void afterMethod()
    {
        System.out.println("This will execute after the Method");
    }

    @AfterClass
    void afterClass()
    {
        System.out.println("This will execute after the Class");
    }

    @AfterSuite
    public static void cleanUp(){
        System.out.println("This will execute after the Suite");
        driver.manage().deleteAllCookies();
        driver.close();
    }

    @Test(testName ="Login to InclincExperienceas as Admin")
    public static void LoginAsAdmin() throws InterruptedException {
        System.out.println("This is Precondition TC for removing Duplicates as Admin");
    }

    @Test(testName ="Removing Duplicates as Clinician")
    public static void LoginAsClinician() throws InterruptedException {
        System.out.println("This is Precondition TC for removing Duplicates as Clinitian");
        //System.out.println("Hello folks!");
        //System.setProperty("webdriver.chrome.driver","chromedriver");
        //WebDriver driver = new ChromeDriver();
        //driver.get("https://www.google.ca");
        //driver.get("https://formy-project.herokuapp.com/form");
        //driver.findElement(By.id("first-name")).sendKeys("First Name");
        //driver.findElement(By.id("last-name")).sendKeys("Last Name");
        //driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();
        //driver.close();

        driver.get(Utils.BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.enterFirstName();
        //loginPage.enterLastName();
        loginPage.pressSubmitButton();
        Thread.sleep(5000);
        loginPage.verifyIsLoginPageDisplayed();

    }




}
