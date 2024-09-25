package bcvax.pages;

import org.openqa.selenium.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplyLocationRelatedItems extends BasePage {
    public SupplyLocationRelatedItems(WebDriver driver) {
        super(driver);
    }

    public static int countSupplyContainers(WebDriver driver) throws InterruptedException {
        GenericTable supply_container_table = getContainersTable(driver);
        return supply_container_table.getRows().size() - 1;
    }

    public static Map<String, Map<String, Double>> checkSupplyContainer(WebDriver driver, int row) throws InterruptedException {
        Thread.sleep(500);
        DecimalFormat df = new DecimalFormat("0.00");
        Map<String, Map<String, Double>> my_container_rec = new HashMap<>();
        GenericTable supply_container_table = getContainersTable(driver);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        String my_container_name = my_records.get(row).get("Supply Container Name").getText();
        String my_dose = my_records.get(row).get("Remaining Doses").getText();
        Double dose_num = Double.parseDouble(my_dose.replace(",", ""));
        String my_quantity = my_records.get(row).get("Remaining Quantity").getText();
        Double quantity_num = Double.parseDouble(my_quantity.replace(",", ""));
        Double conversion_factor = Double.valueOf(df.format(dose_num / quantity_num));
        Map<String, Double> my_rec = new HashMap<>();
        my_rec.put("Remaining Doses", dose_num);
        my_rec.put("Remaining Quantity", quantity_num);
        my_rec.put("Conversion Factor", conversion_factor);

        my_container_rec.put(my_container_name, my_rec);
        WebElement my_checkbox = my_records.get(row).get("Choose a Row\n" +
                "Select All");
        my_checkbox.click();
        return my_container_rec;
    }

    public static Map<String, Double> checkSupplyContainer(WebDriver driver, String container) throws InterruptedException {
        ArrayList<String> containers = new ArrayList<>();
        containers.add(container);
        Map<String, Map<String, Double>> resp = checkSupplyContainers(driver, containers);
        return resp.get(container);
    }

    public static Map<String, Map<String, Double>> checkSupplyContainers(WebDriver driver, List<String> containers) throws InterruptedException {
        Thread.sleep(500);
        DecimalFormat df = new DecimalFormat("#.##");
        Map<String, Map<String, Double>> my_container_rec = new HashMap<>();
        GenericTable supply_container_table = getContainersTable(driver);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        for(String container: containers) {
            for (Map<String, WebElement> my_record : my_records) {
                String my_container_name = my_record.get("Supply Container Name").getText();
                if (my_container_name.equals(container)) {
                    String my_dose = my_record.get("Remaining Doses").getText();
                    Double dose_num = Double.parseDouble(my_dose.replace(",", ""));
                    String my_quantity = my_record.get("Remaining Quantity").getText();
                    Double quantity_num = Double.parseDouble(my_quantity.replace(",", ""));
                    Map<String, Double> my_rec = new HashMap<>();
                    my_rec.put("Remaining Doses", dose_num);
                    my_rec.put("Remaining Quantity", quantity_num);
                    Double conversion_factor = Double.valueOf(df.format(dose_num / quantity_num));
                    my_rec.put("Conversion Factor", conversion_factor);
                    my_container_rec.put(my_container_name, my_rec);
                    WebElement my_checkbox = my_record.get("Choose a Row\n" +
                            "Select All");
                    my_checkbox.click();
                    break;
                }
            }
        }
        return my_container_rec;
    }

    public static Map<String, Map<String, Double>> getSupplyContainers(WebDriver driver, List<String> containers) throws InterruptedException {
        Thread.sleep(500);
        DecimalFormat df = new DecimalFormat("#.##");
        Map<String, Map<String, Double>> my_container_rec = new HashMap<>();
        GenericTable supply_container_table = getContainersTable(driver);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        for(String container: containers) {
            for (Map<String, WebElement> my_record : my_records) {
                String my_container_name = my_record.get("Supply Container Name").getText();
                if (my_container_name.equals(container)) {
                    String my_dose = my_record.get("Remaining Doses").getText();
                    Double dose_num = Double.parseDouble(my_dose.replace(",", ""));
                    String my_quantity = my_record.get("Remaining Quantity").getText();
                    Double quantity_num = Double.parseDouble(my_quantity.replace(",", ""));
                    Map<String, Double> my_rec = new HashMap<>();
                    my_rec.put("Remaining Doses", dose_num);
                    my_rec.put("Remaining Quantity", quantity_num);
                    Double conversion_factor = Double.valueOf(df.format(dose_num / quantity_num));
                    my_rec.put("Conversion Factor", conversion_factor);
                    my_container_rec.put(my_container_name, my_rec);
                    break;
                }
            }
        }
        return my_container_rec;
    }

    public static Map<String, Double> getSupplyContainerDoseQuantity(WebDriver driver, String container) throws InterruptedException {
        ArrayList<String> containers = new ArrayList<>();
        containers.add(container);
        Map<String, Map<String, Double>> resp = getSupplyContainers(driver, containers);
        return resp.get(container);
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

    public static void clickTransfersButton(WebDriver driver) throws InterruptedException{
        Thread.sleep(500);
        By transfer_button_path = By.xpath(".//button[text() = 'Transfer']");
        waitForElementToBeEnabled(driver, transfer_button_path, 10);
        WebElement transfer_button = driver.findElement(transfer_button_path);
        scrollCenter(driver, transfer_button);
        Thread.sleep(500);
        transfer_button.click();
    }
    public static void clickOnFirstContainerDropDownMenu(WebDriver driver) throws InterruptedException {
        GenericTable supply_container_table = getContainersTable(driver);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        WebElement action = my_records.get(1).get("Actions");
        action.click();
        boolean menu_visible = false;
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            try {
                menu_visible = driver.findElement(By.xpath("//div[@part='overlay dropdown']")).isDisplayed();
                if(menu_visible) {
                    break;
                } else {
                    action.click();
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                menu_visible = false;
            }
        }
    }

    public static Map<String, WebElement> clickOnContainerDropDownMenu(WebDriver driver, String container) throws InterruptedException {
        GenericTable supply_container_table = getContainersTable(driver);
        List<Map<String, WebElement>> my_records = supply_container_table.getRowsMappedToHeadings();
        Map<String, WebElement> return_record = new HashMap<>();
        for(Map<String, WebElement> my_record: my_records) {
            if(my_record.get("Supply Container Name").getText().contains(container)) {
                WebElement action = my_record.get("Actions");
                action.click();
                boolean menu_visible = false;
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    try {
                        List<WebElement> my_menues = driver.findElements(By.xpath("//div[@part='overlay dropdown']"));
                        for(WebElement my_menu: my_menues) {
                            menu_visible = my_menu.isDisplayed();
                            if (menu_visible) {
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        menu_visible = false;
                    }
                }
                return_record = my_record;
                break;
            }
        }
        return return_record;
    }

    public static GenericTable getContainersTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By supply_container_table_path = By.xpath("//lightning-datatable[@c-hccrossobjectrelationrecordslist_hccrossobjectrelationrecordslist]");
        waitForElementToBeEnabled(driver, supply_container_table_path, 10);
        int count_before = 0;
        WebElement supply_container_table_node = driver.findElement(supply_container_table_path);
        GenericTable supply_container_table = new GenericTable(supply_container_table_node);
        int count_after = 0;
        for(int i = 0; i < 20; i++) {
            try {
                count_after = supply_container_table.getRows().size();
                break;
            } catch (StaleElementReferenceException ex) {
                Thread.sleep(500);
            }
        }
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
        return supply_container_table;
    }

    public static void selectTransferFromDropDown(WebDriver driver) throws InterruptedException {
        selectActionFromDropDown(driver, "Transfer");
    }

    public static void selectAdjustmentFromDropDown(WebDriver driver) throws InterruptedException {
        selectActionFromDropDown(driver, "Adjustment");
    }

    public static void selectWastageFromDropDown(WebDriver driver) throws InterruptedException {
        selectActionFromDropDown(driver, "Wastage");
    }
    public static void selectActionFromDropDown(WebDriver driver, String action) throws InterruptedException {
        Thread.sleep(500);
        By transfer_dropdawn_item_path = By.xpath("//a/span[text() = '" + action + "']");
        waitForElementToBeEnabled(driver, transfer_dropdawn_item_path, 10);
        WebElement transfer_item = driver.findElement(transfer_dropdawn_item_path);
        scrollCenter(driver, transfer_item);
        Thread.sleep(500);
        try {
            transfer_item.click();
        } catch(ElementNotInteractableException ex) {
            Thread.sleep(500);
            transfer_item.click();
        }
        Thread.sleep(500);
    }
}
