package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;
import java.util.List;

public class ContainerTransferPage extends BasePage {
    public ContainerTransferPage(WebDriver driver) {
        super(driver);
    }

    public static void enterTransferDose(WebDriver driver, String container, String dose) throws InterruptedException {
        By transfer_table_path = By.xpath("//table[@c-bchbulktransfermodal_bchbulktransfermodal]");
        waitForElementToBeEnabled(driver, transfer_table_path, 10);
        WebElement transfer_table_node = driver.findElement(transfer_table_path);
        GenericTable transfer_table = new GenericTable(transfer_table_node);
        List<Map<String, WebElement>> transfer_table_records = transfer_table.getRowsMappedToHeadings();
        for(Map<String, WebElement> my_row: transfer_table_records) {
            if(my_row.get("Supply Container Name").getText().equals(container)) {
                WebElement my_dose_input = my_row.get("Doses").findElement(By.xpath(".//input"));
                my_dose_input.sendKeys(dose);
            }
        }
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

    public static void clickSaveAsDraftButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_draft_btn_path = By.xpath("//button[text()='Save as draft']");
        waitForElementToBeEnabled(driver, save_draft_btn_path, 10);
        WebElement btnSaveAsDraftOnContainerWastagePopUp = driver.findElement(save_draft_btn_path);
        scrollCenter(driver, btnSaveAsDraftOnContainerWastagePopUp);
        Thread.sleep(500);
        btnSaveAsDraftOnContainerWastagePopUp.click();
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
