package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AddReturnLineItemsDialog {
    public static String getReturnId(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_info_path = By.xpath("//b[@c-addreturnlineitemsmodal_addreturnlineitemsmodal]");
        BasePage.waitForElementToBeEnabled(driver, return_info_path, 10);
        List<WebElement> return_ifo = driver.findElements(return_info_path);
        return return_ifo.get(0).getText();
    }

    public static String getReturnFrom(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By return_info_path = By.xpath("//b[@c-addreturnlineitemsmodal_addreturnlineitemsmodal]");
        BasePage.waitForElementToBeEnabled(driver, return_info_path, 10);
        List<WebElement> return_ifo = driver.findElements(return_info_path);
        return return_ifo.get(1).getText();
    }

    public static void checkWastageItem(WebDriver driver, int index) throws InterruptedException {
        Thread.sleep(500);
        By wastage_item_checkbox_path = By.xpath("//lightning-input[@data-id='checkbox']");
        BasePage.waitForElementToBeEnabled(driver, wastage_item_checkbox_path, 10);
        List<WebElement> checkboxes = driver.findElements(wastage_item_checkbox_path);
        WebElement my_checkbox = checkboxes.get(index);
        my_checkbox.click();
    }

    public static void checkWastageAllItems(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By wastage_item_checkbox_path = By.xpath("//lightning-input[@data-id='selectAllChkBox']");
        BasePage.waitForElementToBeEnabled(driver, wastage_item_checkbox_path, 10);
        WebElement select_all_checkbox = driver.findElement(wastage_item_checkbox_path);
        select_all_checkbox.click();
    }

    public static void clickNextBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By next_btn_path = By.xpath("//button[text() = 'Next']");
        BasePage.waitForElementToBeEnabled(driver, next_btn_path, 10);
        WebElement next_btn = driver.findElement(next_btn_path);
        BasePage.scrollIfNeeded(driver, next_btn);
        Thread.sleep(500);
        next_btn.click();
    }

    public static void clickSaveBtn(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//button[text() = 'Save']");
        BasePage.waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_btn = driver.findElement(save_btn_path);
        save_btn.click();
    }
}
