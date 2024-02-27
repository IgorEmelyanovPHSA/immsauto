package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class PersonAccountRelatedPage extends BasePage {
    public PersonAccountRelatedPage(WebDriver driver) {
        super(driver);
    }
    public static int getImmunizationRecordsSize(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By immunization_records_table_path = By.xpath("//c-bc-hc-client-immunization-records");
        waitForElementToBeEnabled(driver, immunization_records_table_path, 10);
        WebElement immunization_records_table = driver.findElement(immunization_records_table_path);
        GenericTable immuizationRecordsTable = new GenericTable(immunization_records_table);
        int table_size = immuizationRecordsTable.getRows().size() - 1;
        return table_size;
    }

    public static String getImmunizationRecordPathwayStatus(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By immunization_records_table_path = By.xpath("//c-bc-hc-client-immunization-records");
        waitForElementToBeEnabled(driver, immunization_records_table_path, 10);
        WebElement immunization_records_table = driver.findElement(immunization_records_table_path);
        GenericTable immuizationRecordsTable = new GenericTable(immunization_records_table);
        List<Map<String, WebElement>> my_table = immuizationRecordsTable.getRowsMappedToHeadings();
        System.out.println("Found " + my_table.size() + "rows");
        WebElement my_view = my_table.get(1).get("Pathway Status");
        String my_pathway_status = my_view.getText();
        return my_pathway_status;
    }

    public static String getActiveConsentsResponse(WebDriver driver, String agent) throws InterruptedException {
        Thread.sleep(500);
        By active_consents_path = By.xpath("//c-bchc-active-consent-table");
        By active_consent_new_btn_path = By.xpath("//c-bchc-active-consent-table//button[text()='New']");
        waitForElementToBeEnabled(driver, active_consent_new_btn_path, 10);
        WebElement active_consent_new_btn = driver.findElement(active_consent_new_btn_path);
        scrollCenter(driver, active_consent_new_btn);
        Thread.sleep(1000);
        WebElement active_consent_table_element = driver.findElement(active_consents_path);
        GenericTable related_active_consents = new GenericTable(active_consent_table_element);
        List<Map<String, WebElement>> my_rows = related_active_consents.getRowsMappedToHeadings();
        String my_response = null;
        for(Map<String, WebElement> my_row : my_rows) {
            if(my_row.get("Agent").getText().equals(agent)) {
                my_response = my_row.get("Response").getText();
                break;
            }
        }
        return my_response;
    }

    public static void clickNewActiveConsentButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By active_consent_new_btn_path = By.xpath("//c-bchc-active-consent-table//button[text()='New']");
        waitForElementToBeEnabled(driver, active_consent_new_btn_path, 10);
        WebElement active_consent_new_btn = driver.findElement(active_consent_new_btn_path);
        scrollCenter(driver, active_consent_new_btn);
        Thread.sleep(1000);
        scrollCenter(driver, active_consent_new_btn);
        Thread.sleep(1000);
        active_consent_new_btn.click();
    }

    public static List<Map<String, WebElement>> getImmunizationRecords(WebDriver driver) throws InterruptedException {
        By immunization_records_table_path = By.xpath("//c-bc-hc-client-immunization-records");
        WebElement immunization_records_table_node = driver.findElement(immunization_records_table_path);
        GenericTable immunization_table = new GenericTable(immunization_records_table_node);
        return immunization_table.getRowsMappedToHeadings();
    }
}
