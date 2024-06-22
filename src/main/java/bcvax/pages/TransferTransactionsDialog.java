package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransferTransactionsDialog extends BasePage {
    public TransferTransactionsDialog(WebDriver driver) {
        super(driver);
    }

    public static void clickTransferTransactionsButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By transfer_transaction_btn_path = By.xpath("//button[contains(text(),'Transfer Transactions')]");
        waitForElementToBeEnabled(driver, transfer_transaction_btn_path, 10);
        WebElement transfer_transaction_btn = driver.findElement(transfer_transaction_btn_path);
        scrollCenter(driver, transfer_transaction_btn);
        Thread.sleep(500);
        transfer_transaction_btn.click();
    }
}
