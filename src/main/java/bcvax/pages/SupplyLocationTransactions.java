package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SupplyLocationTransactions extends BasePage {
    public SupplyLocationTransactions(WebDriver driver) {
        super(driver);
    }

    public static void clickCancelButton(WebDriver driver) throws InterruptedException {
        By cancel_transfer_button_path = By.xpath("//button[text() = 'Cancel Transfer']");
        By transfer_button_path = By.xpath(".//button[text() = 'Transfer']");
        waitForElementToBeEnabled(driver, cancel_transfer_button_path, 10);
        WebElement bulk_cancel_button = driver.findElement(cancel_transfer_button_path);
        waitForElementToBeEnabled(driver, transfer_button_path, 10);
        scrollCenter(driver, bulk_cancel_button);
        Thread.sleep(1000);
        bulk_cancel_button.click();
    }

    public static void clickConfirmIncomingTransfersButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By bulk_confirm_incoming_transfers_button_path = By.xpath(".//button[text() = 'Confirm Transfer']");
        waitForElementToBeEnabled(driver, bulk_confirm_incoming_transfers_button_path, 10);
        WebElement bulk_confirm_incoming_transfers_button = driver.findElement(bulk_confirm_incoming_transfers_button_path);
        scrollCenter(driver, bulk_confirm_incoming_transfers_button);
        Thread.sleep(500);
        bulk_confirm_incoming_transfers_button.click();
    }
}
