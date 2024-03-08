package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InClinicExperienceIdentificationPage extends BasePage {
    public InClinicExperienceIdentificationPage(WebDriver driver) {
        super(driver);
    }

    public static void clickConfirmAndSaveIdentificationButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_and_save_btn_path = By.xpath("(//button[@title='Confirm & Save Identification'])");
        waitForElementToBeEnabled(driver, confirm_and_save_btn_path, 10);
        WebElement confirm_and_save_btn = driver.findElement(confirm_and_save_btn_path);
        scrollCenter(driver, confirm_and_save_btn);
        Thread.sleep(500);
        confirm_and_save_btn.click();
    }

    public static String getSidebarAlertText(WebDriver driver) throws InterruptedException {
        String alert_text = "";
        return alert_text;
    }

    public static void clickAddAlertButton(WebDriver driver) throws InterruptedException {

    }
}
