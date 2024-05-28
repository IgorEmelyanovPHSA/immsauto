package bcvax.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class SubmitRequisitionDialog extends BasePage {
    public SubmitRequisitionDialog(WebDriver driver) {
        super(driver);
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        By save_button_path = By.xpath("//footer[@class='slds-modal__footer']//span[text()='Save']/..");
        waitForElementToBeEnabled(driver, save_button_path, 10);
        WebElement save_button = driver.findElement(save_button_path);
        scrollCenter(driver, save_button);
        Thread.sleep(500);
        save_button.click();
    }
}
