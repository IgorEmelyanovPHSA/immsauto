package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrintReturnFormDialog {
    public static boolean printReturnLabelBtnExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By print_return_label_btn_path = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title='Print Return Label']");
        try {
            BasePage.waitForElementToBeEnabled(driver, print_return_label_btn_path, 10);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static boolean printReturnManifestBtnExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By print_return_manifest_btn_path = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title='Print Return Manifest']");
        try {
            BasePage.waitForElementToBeEnabled(driver, print_return_manifest_btn_path, 10);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static boolean closeBtnExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By close_btn_path = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title='Close']");
        try {
            BasePage.waitForElementToBeEnabled(driver, close_btn_path, 10);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static void clickCloseBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By close_btn_path = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title='Close']");
        BasePage.waitForElementToBeEnabled(driver, close_btn_path, 10);
        WebElement close_btn = driver.findElement(close_btn_path);
        close_btn.click();
    }
}
