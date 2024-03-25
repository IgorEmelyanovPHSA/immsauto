package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class SupplyLocationRelatedItems extends BasePage {
    public SupplyLocationRelatedItems(WebDriver driver) {
        super(driver);
    }

    public static int countSupplyContainers(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_container_table_path = By.xpath("//lightning-datatable[@c-hccrossobjectrelationrecordslist_hccrossobjectrelationrecordslist]");
        waitForElementToBeEnabled(driver, supply_container_table_path, 10);
        int count_before = 0;
        WebElement supply_container_table_node = driver.findElement(supply_container_table_path);
        GenericTable supply_container_table = new GenericTable(supply_container_table_node);
        int count_after = supply_container_table.getRows().size();

        //---Wait until the table is populated
        for(int i = 0; i < 10; i++) {
            if (count_before == count_after && count_before != 0) {
                break;
            } else {
                Thread.sleep(1000);
                count_before = count_after;
                supply_container_table_node = driver.findElement(supply_container_table_path);
                supply_container_table = new GenericTable(supply_container_table_node);
                count_after = supply_container_table.getRows().size();
            }
        }
        return supply_container_table.getRows().size() - 1;
    }

    public static Map<String, Map<String, String>> checkSupplyContainer(WebDriver driver, int row) throws InterruptedException {
        Thread.sleep(500);
        Map<String, Map<String, String>> my_container_rec = new HashMap<>();
        By supply_container_table_path = By.xpath("//lightning-datatable[@c-hccrossobjectrelationrecordslist_hccrossobjectrelationrecordslist]");
        waitForElementToBeEnabled(driver, supply_container_table_path, 10);
        WebElement supply_container_table_node = driver.findElement(supply_container_table_path);
        GenericTable supply_container_table = new GenericTable(supply_container_table_node);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        String my_container_name = my_records.get(row).get("Supply Container Name").getText();
        String my_dose = my_records.get(row).get("Remaining Doses").getText();
        String my_quantity = my_records.get(row).get("Remaining Quantity").getText();
        Map<String, String> my_rec = new HashMap<>();
        my_rec.put("Remaining Doses", my_dose);
        my_rec.put("Remaining Quantity", my_quantity);
        my_container_rec.put(my_container_name, my_rec);
        WebElement my_checkbox = my_records.get(row).get("Choose a Row\n" +
                "Select All");
        my_checkbox.click();
        return my_container_rec;
    }

    public static Map<String, Map<String, String>> getSupplyContainerDoses(WebDriver driver, Set<String> containers) throws InterruptedException {
        Thread.sleep(500);
        Map<String, Map<String, String>> my_container_rec = new HashMap<>();
        By supply_container_table_path = By.xpath("//lightning-datatable[@c-hccrossobjectrelationrecordslist_hccrossobjectrelationrecordslist]");
        waitForElementToBeEnabled(driver, supply_container_table_path, 10);
        WebElement supply_container_table_node = driver.findElement(supply_container_table_path);
        GenericTable supply_container_table = new GenericTable(supply_container_table_node);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        for(Map<String, WebElement> my_record: my_records) {
            if(containers.contains(my_record.keySet().toString())) {
                String my_container_name = my_record.get("Supply Container Name").getText();
                String my_dose = my_record.get("Remaining Doses").getText();
                String my_quantity = my_record.get("Remaining Quantity").getText();
                Map<String, String> my_rec = new HashMap<>();
                my_rec.put("Remaining Doses", my_dose);
                my_rec.put("Remaining Quantity", my_quantity);
                my_container_rec.put(my_container_name, my_rec);
            }
        }

        return my_container_rec;
    }

    public static void clickAdjustmentButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By adjustment_button_path = By.xpath("//button[text() = 'Adjustment']");
        waitForElementToBeEnabled(driver, adjustment_button_path, 10);
        WebElement adjustment_button = driver.findElement(adjustment_button_path);
        scrollCenter(driver, adjustment_button);
        Thread.sleep(500);
        adjustment_button.click();
    }

    public static void clickWastageButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By wastage_button_path = By.xpath("//button[text() = 'Wastage']");
        waitForElementToBeEnabled(driver, wastage_button_path, 10);
        WebElement wastage_button = driver.findElement(wastage_button_path);
        scrollCenter(driver, wastage_button);
        Thread.sleep(500);
        wastage_button.click();
    }
}
