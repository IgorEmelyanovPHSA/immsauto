package communityPortal.tests.Registration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSearch_Dojo {
        public static void main(String[] args) throws InterruptedException {
            // Set the system property for the Chrome driver
            System.setProperty("webdriver.chrome.driver", "/immsauto/chromedriver.exe");
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");

            // Create a new instance of the Chrome driver
            ChromeDriver driver = new ChromeDriver(ops);
            //WebDriver driver = new ChromeDriver();

            // Navigate to Google.com
            driver.get("https://www.google.com");

            // Find the search box element and enter "Dojo computer"
            WebElement searchBox = driver.findElement(By.name("q"));
            Thread.sleep(3000);
            searchBox.sendKeys("Dojo computer");
            Thread.sleep(3000);

            // Press the Enter key to perform the search
            searchBox.sendKeys(Keys.ENTER);
            Thread.sleep(10000);

            // Wait for the search results to load
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Print the title of the first search result
            WebElement firstResult = driver.findElement(By.cssSelector("h3.LC20lb.DKV0Md"));
            System.out.println(firstResult.getText());

            // Close the browser
            driver.quit();
        }
    }

