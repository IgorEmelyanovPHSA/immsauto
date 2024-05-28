package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Keys;


public class AddAdHocLineItemsDialog {


    public static void enterReturnedDosesValue(WebDriver driver, double wastageDoses) throws InterruptedException {
        Thread.sleep(500);
        By returnedDoses_path = By.xpath("//input[@class='input  uiInputSmartNumber']");
        BasePage.waitForElementToBeEnabled(driver, returnedDoses_path, 10);
        WebElement returnedDoses = driver.findElement(returnedDoses_path);
        returnedDoses.click();
        returnedDoses.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        returnedDoses.sendKeys(String.valueOf(wastageDoses));
    }

    public static void clickSaveBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }
}
