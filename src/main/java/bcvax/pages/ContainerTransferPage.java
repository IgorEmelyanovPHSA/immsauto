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
}
