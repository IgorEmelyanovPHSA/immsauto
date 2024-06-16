package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContainerTransferForm extends BasePage {
    public ContainerTransferForm(WebDriver driver) {
        super(driver);
    }

    public static void enterTransferDosages(WebDriver driver, String doses) throws InterruptedException {
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

    public static void enterTransferQuantity(WebDriver driver, String quantity) throws InterruptedException {
        By quantity_path = By.xpath("//lightning-input//label[text()='Quantity']//following-sibling::div/input[@class='slds-input']");
        waitForElementToBePresent(driver, quantity_path,10);
        WebElement quantity_field = driver.findElement(quantity_path);
        scrollCenter(driver, quantity_field);
        Thread.sleep(500);
        quantity_field.clear();
        Thread.sleep(500);
        quantity_field.sendKeys(quantity);
    }

    public static String getVaccineName(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By  trade_name_path = By.xpath("//input[@name = 'BCH_Product_Name__c']");
        waitForElementToBeLocated(driver, trade_name_path, 10);
        WebElement trade_name = driver.findElement(trade_name_path);
        log("/*9.----Picked up the Trade Vaccine Name --*/");
        String trade_name_value = trade_name.getAttribute("value");
        return trade_name_value;
    }

    public static double getConversationFactor(WebDriver driver) throws InterruptedException {
        By conversion_factor_path = By.xpath("//input[@name = 'HC_Product_Measure__c']");
        waitForElementToBeLocated(driver, conversion_factor_path, 10);
        WebElement conversation_factor = driver.findElement(conversion_factor_path);

        String conversioon_factor_value = conversation_factor.getAttribute("value");
        double factor = Double.parseDouble(conversioon_factor_value.replaceAll(",", ""));
        return factor;
    }

    public static void selectSupplyLocationToFromDropdown(WebDriver driver, String supplyLocation) throws InterruptedException {
        log(" -- select 'To' " + supplyLocation + "  -");
        Thread.sleep(500);
        By search_supplu_location_path = By.xpath("//label[@class='slds-form-element__label' and text()='Supply Location']/..//input[@class='slds-combobox__input slds-input']");
        waitForElementToBeEnabled(driver, search_supplu_location_path, 30);
        WebElement searchSupplyLocationCombobox = driver.findElement(search_supplu_location_path);
        log(" -- Combobox Supply Location To is found  -");
        searchSupplyLocationCombobox.sendKeys(supplyLocation);
        log(" -- Start typing into Search Combobox  -");
        By supplyLocationItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title='" + supplyLocation + "']");
        waitForElementToBeEnabled(driver, supplyLocationItemPath, 10);
        WebElement supplyLocationItem = driver.findElement(supplyLocationItemPath);
        scrollCenter(driver, supplyLocationItem);
        log(" -- Drop down with supply required Supply location appeared  -");
        try {
            supplyLocationItem.click();
        } catch(Exception ex) {
            searchSupplyLocationCombobox.click();
            supplyLocationItem.click();
        }
        //Probable delay when select supply location causing teh javascript error
        Thread.sleep(2000);
        log(" -- Selected Supply location successfully  -");
    }

    public static void clickSaveAsDraftButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_draft_btn_path = By.xpath("//button[text()='Save as draft']");
        waitForElementToBeEnabled(driver, save_draft_btn_path, 10);
        WebElement btnSaveAsDraftOnContainerWastagePopUp = driver.findElement(save_draft_btn_path);
        scrollCenter(driver, btnSaveAsDraftOnContainerWastagePopUp);
        Thread.sleep(500);
        btnSaveAsDraftOnContainerWastagePopUp.click();
    }

    public static void selectSupplyDistributionFromDropdown(WebDriver driver, String supplyDistribution) throws InterruptedException {
        By searchSupplyDistributionPath = By.xpath(".//span[contains(text(),'Select an Option')]");
        waitForElementToBePresent(driver, searchSupplyDistributionPath, 10);
        WebElement searchDistributionField = driver.findElement(searchSupplyDistributionPath);
        scrollCenter(driver, searchDistributionField);
        searchDistributionField.click();
        Thread.sleep(2000);
        By supplyDistributor = By.xpath("//span[contains(text(),'" + supplyDistribution + "')]");
        waitForElementToBePresent(driver, supplyDistributor, 10);
        WebElement supplyDistributorItem = driver.findElement(supplyDistributor);
        scrollCenter(driver, supplyDistributorItem);
        supplyDistributorItem.click();
    }

    public static void clickTransferButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By transfer_btn_path = By.xpath("(//section[@role='dialog']//button[text() = 'Transfer'])");
        waitForElementToBeEnabled(driver, transfer_btn_path, 10);
        WebElement transfer_btn = driver.findElement(transfer_btn_path);
        scrollCenter(driver, transfer_btn);
        transfer_btn.click();
    }
}

