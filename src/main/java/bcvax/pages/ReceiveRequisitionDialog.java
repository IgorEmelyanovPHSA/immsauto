package bcvax.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class ReceiveRequisitionDialog extends BasePage {
    public ReceiveRequisitionDialog(WebDriver driver) {
        super(driver);
    }

    public static void clickSupplyDistributionsTo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_distribution_to_field_path = By.xpath("//input[@title='Search Supply Distributions']");
        waitForElementToBeEnabled(driver, supply_distribution_to_field_path, 10);
        WebElement supply_distribution_to_field = driver.findElement(supply_distribution_to_field_path);
        scrollCenter(driver, supply_distribution_to_field);
        Thread.sleep(500);
        supply_distribution_to_field.click();
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_btn_path = By.xpath("//footer[@class='slds-modal__footer']//span[text()='Save']/..");
        waitForElementToBeEnabled(driver, save_btn_path, 10);
        WebElement save_button = driver.findElement(save_btn_path);
        scrollCenter(driver, save_button);
        Thread.sleep(500);
        save_button.click();
    }

    public static void selectSupplyDistributionTo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_distribution_to_field_path = By.xpath("//input[@title='Search Supply Distributions']");
        waitForElementToBeEnabled(driver, supply_distribution_to_field_path, 10);
        WebElement supply_distribution_to_field = driver.findElement(supply_distribution_to_field_path);
        scrollCenter(driver, supply_distribution_to_field);
        supply_distribution_to_field.sendKeys("SDST");
        By distribution_item_path = By.xpath("//div[@class='primaryLabel slds-truncate slds-lookup__result-text'][1]");
        waitForElementToBeEnabled(driver, distribution_item_path, 10);
        WebElement distribution_item = driver.findElement(distribution_item_path);
        distribution_item.click();
    }
}
