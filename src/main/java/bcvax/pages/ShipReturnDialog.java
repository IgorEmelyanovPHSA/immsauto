package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShipReturnDialog {
    public static void clickSaveBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//h2[text() = 'Ship Return']/../../footer/button[@class = 'slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }

    public static void clickSaveBtnCP(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//h2[text() = 'Ship Return']/../../div[@class='modal-footer slds-modal__footer']/button[@class = 'slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }
}
