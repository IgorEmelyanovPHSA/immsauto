package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SupplyLocationTransactions extends BasePage {
    public SupplyLocationTransactions(WebDriver driver) {
        super(driver);
    }

    public static void clickCancelButton(WebDriver driver) throws InterruptedException {
        By cancel_transfer_button_path = By.xpath("//button[text() = 'Cancel Transfer']");
        By transfer_button_path = By.xpath(".//button[text() = 'Transfer']");
        waitForElementToBeEnabled(driver, cancel_transfer_button_path, 10);
        WebElement bulk_cancel_button = driver.findElement(cancel_transfer_button_path);
        waitForElementToBeEnabled(driver, transfer_button_path, 10);
        scrollCenter(driver, bulk_cancel_button);
        Thread.sleep(1000);
        bulk_cancel_button.click();
    }

    public static void clickConfirmIncomingTransfersButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By bulk_confirm_incoming_transfers_button_path = By.xpath("//button[text() = 'Confirm Transfer']");
        waitForElementToBeEnabled(driver, bulk_confirm_incoming_transfers_button_path, 10);
        WebElement bulk_confirm_incoming_transfers_button = driver.findElement(bulk_confirm_incoming_transfers_button_path);
        scrollCenter(driver, bulk_confirm_incoming_transfers_button);
        Thread.sleep(500);
        bulk_confirm_incoming_transfers_button.click();
    }

    public static int getRowsDraftTransactionsCount(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsDraftTable(driver);
        int rows_count = draft_table.getRows().size();
        return rows_count - 1;
    }

    public static int getRowsOutgoingTransactionsCount(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsOutgoingTable(driver);
        int rows_count = draft_table.getRows().size();
        return rows_count - 1;
    }

    public static int getRowsIncomingTransactionsCount(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsIncomingTable(driver);
        int rows_count = draft_table.getRows().size();
        return rows_count - 1;
    }

    public static String getDraftTransactionId(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsDraftTable(driver);
        String draftTransactionId = draft_table.getRowsMappedToHeadings().get(row_num).get("Supply Transaction Name").getText();
        return draftTransactionId;
    }

    public static GenericTable getShippedTransactionsIncomingTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By transactions_incoming_table_path = By.xpath("//flexipage-component2[@data-component-id='hcShippedSupplyTransactions']//table | //span[contains(text(), 'Shipped Transactions - Incoming')]/../../../../..//table");
        waitForElementToBeEnabled(driver, transactions_incoming_table_path, 10);
        WebElement transactions_incoming_table_node = driver.findElement(transactions_incoming_table_path);
        GenericTable transactions_incoming_table = new GenericTable(transactions_incoming_table_node);
        return transactions_incoming_table;
    }

    public static GenericTable getShippedTransactionsOutgoingTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By transactions_outgoing_table_path = By.xpath("//flexipage-component2[@data-component-id='hcShippedSupplyTransactions2']//table | //span[contains(text(), 'Shipped Transactions - Outgoing')]/../../../../..//table");
        waitForElementToBeEnabled(driver, transactions_outgoing_table_path, 10);
        WebElement transactions_outgoing_table_node = driver.findElement(transactions_outgoing_table_path);
        GenericTable transactions_outgoing_table = new GenericTable(transactions_outgoing_table_node);
        return transactions_outgoing_table;
    }

    public static GenericTable getShippedTransactionsDraftTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By transactions_draft_table_path = By.xpath("//flexipage-component2[@data-component-id='hcShippedSupplyTransactions3']//table | //span[contains(text(), 'Shipped Transactions - Draft')]/../../../../..//table");
        waitForElementToBeEnabled(driver, transactions_draft_table_path, 10);
        WebElement transactions_draft_table_node = driver.findElement(transactions_draft_table_path);
        GenericTable transactions_draft_table = new GenericTable(transactions_draft_table_node);
        return transactions_draft_table;
    }
}
