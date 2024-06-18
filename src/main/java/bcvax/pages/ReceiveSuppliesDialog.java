package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class ReceiveSuppliesDialog {

    public static void clickSupplyItemTextBox(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_item_select_path = By.xpath("//label[text()='Supply Item']/..//input");
        BasePage.waitForElementToBeLocated(driver, supply_item_select_path, 10);
        WebElement supply_item_select = driver.findElement(supply_item_select_path);
        supply_item_select.click();
    }

    public static void selectSupplyItem(WebDriver driver, String supplyItem) throws InterruptedException {
        By supply_item_field = By.xpath("//label[@class='slds-form-element__label' and text() = 'Supply Item']/..//input");
        BasePage.waitForElementToBeLocated(driver, supply_item_field, 10);
        WebElement element = driver.findElement(supply_item_field);
        element.sendKeys(supplyItem);
        Thread.sleep(500);
        By supply_item = By.xpath("//lightning-base-combobox-formatted-text[contains(@title, '" + supplyItem + "')]");
        BasePage.waitForElementToBeLocated(driver, supply_item, 10);
        WebElement search_input = driver.findElement(supply_item);
        search_input.click();
        By close_modal_box_path = By.xpath("//button[@data-aura-class = 'uiButton--modal-closeBtn uiButton' and @title = 'Close this window']");
        try {
            Thread.sleep(500);
            driver.findElement(close_modal_box_path).click();
        } catch(Exception ex) {
            System.out.println("No modal box. Continue...");
        }
    }

    public static void enterTransferDosages(WebDriver driver, String doses) throws InterruptedException {
        Thread.sleep(500);
        By doses_input_path = By.xpath("//lightning-input//label[text()='Doses']//following-sibling::div/input[@class='slds-input']");
        BasePage.waitForElementToBeEnabled(driver, doses_input_path,10);
        WebElement doses_input = driver.findElement(doses_input_path);
        BasePage.scrollCenter(driver, doses_input);
        Thread.sleep(500);
        doses_input.clear();
        Thread.sleep(500);
        doses_input.sendKeys(doses);
    }

    public static void selectReasonForReception(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By select_reason_path = By.xpath("//button[@name='BCH_Reason_for_Reception__c']");
        BasePage.waitForElementToBeEnabled(driver, select_reason_path, 10);
        WebElement select_reason_btn = driver.findElement(select_reason_path);
        select_reason_btn.click();
        Thread.sleep(500);
        By select_other_reason_path = By.xpath("//lightning-base-combobox-item[@data-value='Other']");
        BasePage.waitForElementToBeEnabled(driver, select_other_reason_path, 10);
        WebElement reason_other_item = driver.findElement(select_other_reason_path);
        reason_other_item.click();
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_receive_supplies_btn_path = By.xpath("//button[contains(text(),'Save')]");
        BasePage.waitForElementToBeEnabled(driver, save_receive_supplies_btn_path, 10);
        WebElement save_receive_supplies_btn = driver.findElement(save_receive_supplies_btn_path);
        BasePage.scrollCenter(driver, save_receive_supplies_btn);
        Thread.sleep(500);
        save_receive_supplies_btn.click();
        Thread.sleep(500);
        //Try to find and close the Success Dialogue
        By success_message_close_btn_path = By.xpath("//div[@role='alertdialog']/button[@title='Close']");
        try {
            BasePage.waitForElementToBeEnabled(driver, success_message_close_btn_path, 5);
            WebElement close_success_dialog = driver.findElement(success_message_close_btn_path);
            close_success_dialog.click();
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("*** Warning *** No Receive Supplies success message Appeared. Continue...");
        }
    }

    public static void selectSupplyDistributionTo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_distribution_to_path = By.xpath("//button[@name='distributionBox']");
        BasePage.waitForElementToBeEnabled(driver, supply_distribution_to_path, 5);
        WebElement element = driver.findElement(supply_distribution_to_path);
        BasePage.scrollCenter(driver, element);
        element.click();
        Thread.sleep(500);
        By supply_distributor_path = By.xpath("(//span[contains(text(),'- SDST-000')])[1]");
        try {
            BasePage.waitForElementToBeEnabled(driver, supply_distributor_path, 5);
            WebElement myDistributor = driver.findElement(supply_distributor_path);
            myDistributor.click();
        } catch(Exception ex) {
            element.click();
            Thread.sleep(500);
            BasePage.waitForElementToBeEnabled(driver, supply_distributor_path, 10);
            WebElement myDistributor = driver.findElement(supply_distributor_path);
            myDistributor.click();
        }
    }

    public static boolean ValidateSaveButtonIsDisplayedOnReceiveSupplies(WebDriver driver) throws InterruptedException {
        By save_receive_supplies_btn_path = By.xpath("//button[contains(text(),'Save')]");
        BasePage.waitForElementToBeEnabled(driver, save_receive_supplies_btn_path, 10);
        WebElement save_receive_supplies_btn = driver.findElement(save_receive_supplies_btn_path);
        return save_receive_supplies_btn.isDisplayed();
    }

    public static boolean ValidateCancelButtonIsDisplayedOnReceiveSupplies(WebDriver driver) throws InterruptedException {
        By cancel_receive_supplies_btn_path = By.xpath("//div[@class='runtime_platform_actionsQuickActionWrapper']//span[text()='Cancel']");
        BasePage.waitForElementToBeEnabled(driver, cancel_receive_supplies_btn_path, 10);
        WebElement cancel_receive_supplies_btn = driver.findElement(cancel_receive_supplies_btn_path);
        return cancel_receive_supplies_btn.isDisplayed();
    }

    public static String validateSupplyItemField(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_item_label = By.xpath("//label[@class='slds-form-element__label' and text() = 'Supply Item']");
        BasePage. waitForElementToBeLocated(driver, supply_item_label, 10);
        WebElement element = driver.findElement(supply_item_label);
        return element.getText();
    }

    public static double getDoseConversionFactor(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By dose_conversion_factor_path = By.xpath("//label[contains(text(),'Dose Conversion Factor')]/parent::div//input");
        BasePage.waitForElementToBePresent(driver, dose_conversion_factor_path, 10);
        double value = Double.parseDouble(driver.findElement(dose_conversion_factor_path).getAttribute("value"));
        return value;
    }

    public static String getQuantityLabel(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By qty_field_path = By.xpath("//label[text()='Quantity']");
        BasePage.waitForElementToBeEnabled(driver, qty_field_path, 10);
        WebElement quantity_field = driver.findElement(qty_field_path);
        return quantity_field.getText();
    }

    public static String getDoseConversionFactorLabel(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By dose_conversion_factor_label_path = By.xpath("//label[text()='Dose Conversion Factor']");
        BasePage.waitForElementToBeEnabled(driver, dose_conversion_factor_label_path, 10);
        WebElement dose_conversion_factor_label = driver.findElement(dose_conversion_factor_label_path);
        return dose_conversion_factor_label.getText();
    }

    public static String getDosesLabel(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By dose_field_label_path = By.xpath("//label[text()='Doses']");
        BasePage.waitForElementToBeEnabled(driver, dose_field_label_path, 10);
        WebElement dose_field_label = driver.findElement(dose_field_label_path);
        return dose_field_label.getText();
    }

    public static String getSupplyDistributionToLabel(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_distribution_to_label_path = By.xpath("//label[text()='Supply Distribution To']");
        BasePage.waitForElementToBeEnabled(driver, supply_distribution_to_label_path, 10);
        WebElement supply_distribution_to_label = driver.findElement(supply_distribution_to_label_path);
        return supply_distribution_to_label.getText();
    }

    public static void enterQuantity(WebDriver driver, int quantity) throws InterruptedException {
        By quantity_field_path = By.xpath("//label[text()='Quantity']/..//input");
        BasePage.waitForElementToBeLocated(driver, quantity_field_path, 10);
        WebElement quantity_field = driver.findElement(quantity_field_path);
        BasePage.scrollCenter(driver, quantity_field);
        quantity_field.clear();
        Thread.sleep(500);
        quantity_field.sendKeys(Integer.toString(quantity));
    }
}
