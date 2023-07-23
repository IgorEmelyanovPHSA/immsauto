package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertDialog {
    public static boolean alertFound(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By dialog_path = By.xpath("//div[@role = 'alertdialog']");
        try {
            BasePage.waitForElementToBeLocated(driver, dialog_path, 10);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static WebElement getAlertContent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_content_path = By.xpath("//div[@role = 'alertdialog']//div[@class='toastContent slds-notify__content']");
        BasePage.waitForElementToBeEnabled(driver, alert_content_path, 10);
        WebElement alert_content = driver.findElement(alert_content_path);
        return alert_content;
    }

    public static void closeAlert(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_close_btn_path = By.xpath("//div[@role = 'alertdialog']//button[@title='Close']");
        BasePage.waitForElementToBeEnabled(driver, alert_close_btn_path, 10);
        WebElement close_alert_btn = driver. findElement(alert_close_btn_path);
        close_alert_btn.click();
    }

    public static String clickAlertLink(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By alert_link_path = By.xpath("//div[@role = 'alertdialog']//a[@data-aura-class='forceActionLink']");
        BasePage.waitForElementToBeEnabled(driver, alert_link_path, 10);
        WebElement alert_link = driver.findElement(alert_link_path);
        alert_link.click();
        closeAlert(driver);
        return alert_link.getAttribute("title");
    }
}
