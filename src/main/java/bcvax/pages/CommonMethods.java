package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class CommonMethods extends BasePage{

    public CommonMethods(WebDriver driver) {
        super(driver);
    }

    public boolean userFoundWithParameters(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
        By userFoundWithParameter = null;
        //To handle scenario where user doesn't have middle name
        if(legalMiddleName.length() == 0){
            userFoundWithParameter = By.xpath("//a[@title='" + legalFirstName + " " + legalLastName + "']");
        }
        else
            {
                userFoundWithParameter = By.xpath("//a[@title='" + legalFirstName + " " + legalMiddleName + " " + legalLastName + "']");
            }
        if (!isDisplayed(userFoundWithParameter)) {
            return false;
        }
        WebElement userFoundWithParameterId = driver.findElement(userFoundWithParameter);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", userFoundWithParameterId);
        Thread.sleep(5000);
        return true;
    }

    public boolean isUserFoundValidation(String legalFirstName, String legalMiddleName, String legalLastName) throws InterruptedException {
        boolean isUserFound = false;
        for(int i = 1; i<=7; i++ ) {
            if (!userFoundWithParameters(legalFirstName, legalMiddleName, legalLastName)) {
                log(i +"-try to find user: " + legalFirstName + " " + legalLastName + " not found, re-try!");
                driver.navigate().refresh();
                Thread.sleep(15000);
            } else {
                log("/*---User --> " + legalFirstName + " " + legalLastName + " present on the page--*/");
                isUserFound = true;
                break;
            }
        }
        return isUserFound;
    }
}