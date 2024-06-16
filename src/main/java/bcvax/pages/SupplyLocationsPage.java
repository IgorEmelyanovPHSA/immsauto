package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static constansts.Header.SUPPLY_LOCATION_NAME;

public class SupplyLocationsPage extends BasePage {
    public SupplyLocationsPage(WebDriver driver) {

        super(driver);
    }

    public static void selectSupplyLocationName(WebDriver driver, String location) throws InterruptedException {
        By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Supply Locations']");
        //By select_list_view_btn_path = By.xpath("//button[@title='Select list display']");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, select_list_view_btn_path, 10);
        WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
        select_list_view_btn.click();
        Thread.sleep(500);
        By active_supply_locations_path = By.xpath("//a/span[text() = 'Active Supply Locations']");
        waitForElementToBeEnabled(driver, active_supply_locations_path, 10);
        WebElement active_supply_locations_item =  driver.findElement(active_supply_locations_path);
        active_supply_locations_item.click();
        Thread.sleep(2000);
        try {
            SupplyConsolePage.switchToTableView(driver);
        } catch(Exception ex) {
            System.out.println("---Cannot switch the view---");
        }
        Thread.sleep(1000);
        By search_field_path = By.xpath("//input[@name = 'HC_Supply_Location__c-search-input']");
        waitForElementToBeEnabled(driver, search_field_path, 10);
        WebElement search_location_field = driver.findElement(search_field_path);
        try {
            WebElement clear_btn = driver.findElement(By.xpath("//input[@name = 'HC_Supply_Location__c-search-input']/..//button[@data-element-id = 'searchClear']"));
            clear_btn.click();
            Thread.sleep(2000);
        } catch(Exception ex) {
            System.out.println("Search field is empty. Continue...");
        }
        search_location_field.sendKeys(location);
        Thread.sleep(1000);
        search_location_field.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //By supply_location_table_path = By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']");
        By supply_location_table_path = By.xpath("//div[contains(@class, 'listViewContent')]");
        waitForElementToBeEnabled(driver, supply_location_table_path, 10);
        WebElement supply_location_table_node = driver.findElement(supply_location_table_path);
        GenericTable supply_items_table = new GenericTable(supply_location_table_node);
        Map<String, WebElement> my_row = supply_items_table.getMappedRow(ImmutableMap.of(SUPPLY_LOCATION_NAME, location));
        WebElement my_link = my_row.get(SUPPLY_LOCATION_NAME).findElement(By.xpath(".//a"));
        waitForElementToBeVisible(driver, my_link, 10);
        scrollCenter(driver, my_link);
        Thread.sleep(500);
        my_link.click();
        Thread.sleep(2000);
    }
}
