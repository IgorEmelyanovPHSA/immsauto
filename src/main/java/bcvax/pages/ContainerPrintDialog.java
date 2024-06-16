package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContainerPrintDialog {
    public static void clickCloseButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        By bulk_dialog_close_button_path = By.xpath("//section[@role='dialog']//button[text()='Close']");
        BasePage.waitForElementToBeEnabled(driver, bulk_dialog_close_button_path, 10);
        WebElement bulk_dialog_close_button = driver.findElement(bulk_dialog_close_button_path);
        bulk_dialog_close_button.click();
        try {
            Thread.sleep(500);
            WebElement javascript_error_close_button = driver.findElement(By.xpath("//button[@title='Close this window']"));
            javascript_error_close_button.click();
            System.out.println("***WARNING. Probable Javascript performance ***");
        } catch(Exception ex) {
            ;
        }
        BasePage.waitForElementNotToBeVisible(driver, bulk_dialog_close_button_path, 10);
        Thread.sleep(2000);
    }
}
