package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReceiveReturnDialog {
    public static void clickSaveBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//h2[text() = 'Receive Return']/../../footer/button[@class = 'slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }

    public static void typeReceiverComment(WebDriver driver, String comment) throws InterruptedException {
        Thread.sleep(500);
        By receiver_comment_path = By.xpath("//h2[text() = 'Receive Return']/../..//textarea");
        BasePage.waitForElementToBeEnabled(driver, receiver_comment_path, 10);
        WebElement receiver_comment_field = driver.findElement(receiver_comment_path);
        receiver_comment_field.sendKeys(comment);
    }
}
