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

    private By appsLauncher = By.xpath("//div[@class='slds-icon-waffle']");

    private By appsBCHVaccinationPortal = By.xpath("//p[text()='BCH Vaccination Portal']");

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public void globalSearchCP(String textToSearch) throws InterruptedException {
        Thread.sleep(500);
        By search_field_path = By.xpath("//input[@aria-label = 'Search...']");
        waitForElementToBeEnabled(driver, search_field_path, 30);
        WebElement search_field = driver.findElement(search_field_path);
        search_field.sendKeys(textToSearch);
        search_field.sendKeys(Keys.RETURN);
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

    public void goToVaccinationPortalIfNeededAndConfirmPageIsDisplayed() throws InterruptedException {
        log("/*-- Navigate to BCH Vaccination Portal --*/");
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        click(appsLauncher);
        Thread.sleep(3500);
        click(appsBCHVaccinationPortal);
        Thread.sleep(10000);

        //Wait for the new window or tab
        wait.until(numberOfWindowsToBe(2));
        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void expiredVaxHandler() throws InterruptedException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//h1[contains(text(),'Confirm')]"));
        if(listOfElements.size()>=1){
            WebElement btnOk = driver.findElement(By.xpath("//button[@data-ok-button]"));
            click(btnOk);
            Thread.sleep(2000);
        }
    }
}