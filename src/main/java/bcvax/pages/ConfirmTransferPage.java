package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmTransferPage extends BasePage {
    public ConfirmTransferPage(WebDriver driver) {
        super(driver);
    }

    public static void selectTransferSupplyDistributionFromDropdown(WebDriver driver, String supplyDistribution) throws InterruptedException {
        By searchSupplyDistributionPath = By.xpath("//span[text() = 'Select Supply Distributor']");
        waitForElementToBePresent(driver, searchSupplyDistributionPath, 10);
        WebElement searchDistributionField = driver.findElement(searchSupplyDistributionPath);
        scrollCenter(driver, searchDistributionField);
        searchDistributionField.click();
        Thread.sleep(2000);
        By supplyDistributor = By.xpath("//span[contains(text(),'" + supplyDistribution + "')]");
        waitForElementToBePresent(driver, supplyDistributor, 10);
        WebElement supplyDistributorItem = driver.findElement(supplyDistributor);
        supplyDistributorItem.click();
    }

    public static void clickConfirmTransactionButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_incoming_transfers_modal_button_path = By.xpath("//button[text() = 'Confirm Transaction']");
        waitForElementToBeEnabled(driver, confirm_incoming_transfers_modal_button_path, 10);
        WebElement confirm_incoming_transfers_modal_button = driver.findElement(confirm_incoming_transfers_modal_button_path);
        scrollCenter(driver, confirm_incoming_transfers_modal_button);
        Thread.sleep(500);
        confirm_incoming_transfers_modal_button.click();
    }
}
