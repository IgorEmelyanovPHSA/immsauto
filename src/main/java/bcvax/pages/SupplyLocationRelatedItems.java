package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
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

    public static String checkSupplyContainer(WebDriver driver, int row) throws InterruptedException {
        Thread.sleep(500);
        By supply_container_table_path = By.xpath("//lightning-datatable[@c-hccrossobjectrelationrecordslist_hccrossobjectrelationrecordslist]");
        waitForElementToBeEnabled(driver, supply_container_table_path, 10);
        WebElement supply_container_table_node = driver.findElement(supply_container_table_path);
        GenericTable supply_container_table = new GenericTable(supply_container_table_node);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        String my_container_name = my_records.get(row).get("Supply Container Name").getText();
        WebElement my_checkbox = my_records.get(row).get("Choose a Row\n" +
                "Select All");
        my_checkbox.click();
        return my_container_name;
    }
}
