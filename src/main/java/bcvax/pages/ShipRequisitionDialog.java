package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShipRequisitionDialog extends BasePage {
    public ShipRequisitionDialog(WebDriver driver) { super(driver); }

    public static boolean opened(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By verified_label_path = By.xpath("//h2[contains(text(),'Ship Requisition')]");
        try {
            waitForElementToBeEnabled(driver, verified_label_path, 10);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public static void clickSaveShipRequisition(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//button[text()='Save']");
        waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        scrollCenter(driver, save_btn);
        save_btn.click();
    }
}
