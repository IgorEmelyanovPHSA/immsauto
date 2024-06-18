package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;

public class SupplyItemsPage extends BasePage {
    public SupplyItemsPage(WebDriver driver) {
        super(driver);
    }

    public static void selectSupplyItemName(WebDriver driver, String item) throws InterruptedException {
        By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Items']");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, select_list_view_btn_path, 10);
        WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
        select_list_view_btn.click();
        Thread.sleep(500);
        By all_supply_items_path = By.xpath("//lightning-base-combobox-item[@role='option' and @data-value='All']");
        waitForElementToBeEnabled(driver, all_supply_items_path, 10);
        WebElement all_supply_items =  driver.findElement(all_supply_items_path);
        all_supply_items.click();
        try {
            SupplyConsolePage.switchToTableView(driver);
        } catch (Exception ex) {
            System.out.println("Cannot switch the view");
        }
        By search_field_path = By.xpath("//input[@name = 'Supply Item-search-input']");
        waitForElementToBeEnabled(driver, search_field_path, 10);
        WebElement search_location_field = driver.findElement(search_field_path);
        try {
            WebElement clear_btn = driver.findElement(By.xpath("//input[@name = 'Supply Item-search-input']/..//button[@data-element-id = 'searchClear']"));
            clear_btn.click();
            Thread.sleep(2000);
        } catch(Exception ex) {
            System.out.println("Search field is empty. Continue...");
        }
        search_location_field.sendKeys(item);
        Thread.sleep(1000);
        search_location_field.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        GenericTable supply_items_table = getSupplyItemsTable(driver);
        List<Map<String, WebElement>> my_rows = supply_items_table.getRowsMappedToHeadings();
        WebElement my_link = my_rows.get(1).get("Supply Item Name").findElement(By.xpath(".//a"));
        my_link.click();
    }

    public static void selectShowAllSupplyItems(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Items']");
        waitForElementToBeEnabled(driver, select_list_view_btn_path, 10);
        WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
        select_list_view_btn.click();
        Thread.sleep(2000);
        By all_items_path = By.xpath("//lightning-base-combobox-item[@role='option' and @data-value='All']");
        waitForElementToBeEnabled(driver, all_items_path, 10);
        WebElement all_items = driver.findElement(all_items_path);
        all_items.click();
    }

    public static GenericTable getSupplyItemsTable(WebDriver driver) throws InterruptedException {
        boolean loaded = false;
        By supply_locations_table_path = By.xpath("//div[contains(@class, 'listViewContainer')]");
        waitForElementToBeEnabled(driver, supply_locations_table_path, 10);
        WebElement supply_locations_table_node = driver.findElement(supply_locations_table_path);
        while(!loaded) {
            try {
                GenericTable supply_locations_table = new GenericTable(supply_locations_table_node);
                loaded = supply_locations_table.getHeadings().get(0).isEnabled();
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
                Thread.sleep(1000);
            }
        }
        return new GenericTable(supply_locations_table_node);
    }
}
