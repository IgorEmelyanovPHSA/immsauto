package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SupplyItemDetailsPage extends BasePage {
    public SupplyItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public static Double getRemainingQuantity(WebDriver driver) throws InterruptedException {
        By remaining_quantity_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Supply_Item__c.HC_Remaining_Quantity__c']//lightning-formatted-number");
        waitForElementToBeEnabled(driver, remaining_quantity_path, 10);
        WebElement element = driver.findElement(remaining_quantity_path);
        String Quantity = element.getText();
        Double doses = Double.parseDouble(Quantity.replaceAll(",", ""));
        return (doses);
    }

    public static Double getRemainingDoses(WebDriver driver) throws InterruptedException {
        By remaining_dose_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.HC_Supply_Item__c.HC_Remaining_Measures__c']//lightning-formatted-number");
        waitForElementToBeEnabled(driver, remaining_dose_path, 10);
        WebElement element = driver.findElement(remaining_dose_path);
        String Doses = element.getText();
        Double doses = Double.parseDouble(Doses.replaceAll(",", ""));
        return (doses);
    }
}
