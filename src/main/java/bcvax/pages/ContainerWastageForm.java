package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContainerWastageForm extends BasePage {
    public ContainerWastageForm(WebDriver driver) {
        super(driver);
    }

    public static void enterAdjustmentDosages(WebDriver driver, String doses) throws InterruptedException {
        Thread.sleep(500);
        By doses_input_path = By.xpath("//lightning-input//label[text()='Doses']//following-sibling::div/input[@class='slds-input']");
        waitForElementToBeEnabled(driver, doses_input_path,10);
        WebElement doses_input = driver.findElement(doses_input_path);
        scrollCenter(driver, doses_input);
        Thread.sleep(500);
        doses_input.clear();
        Thread.sleep(500);
        doses_input.sendKeys(doses);
    }

    public static void enterAdjustmentQuantity(WebDriver driver, String quantity) throws InterruptedException {
        By quantity_path = By.xpath("//lightning-input//label[text()='Quantity']//following-sibling::div/input[@class='slds-input']");
        waitForElementToBePresent(driver, quantity_path,10);
        WebElement quantity_field = driver.findElement(quantity_path);
        scrollCenter(driver, quantity_field);
        Thread.sleep(500);
        quantity_field.clear();
        Thread.sleep(500);
        quantity_field.sendKeys(quantity);
    }

}
