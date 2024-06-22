package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static String getOutgoingTransactionId(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable outgoing_table = getShippedTransactionsOutgoingTable(driver);
        String outgoingTransactionId = outgoing_table.getRowsMappedToHeadings().get(row_num).get("Supply Transaction Name").getText();
        return outgoingTransactionId;
    }

    public static String getIncomingTransactionId(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable incoming_table = getShippedTransactionsIncomingTable(driver);
        String incomingTransactionId = incoming_table.getRowsMappedToHeadings().get(row_num).get("Supply Transaction Name").getText();
        return incomingTransactionId;
    }

    public static void clickOnIncomingTransactionsDropDownMenu(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable incoming_table = getShippedTransactionsIncomingTable(driver);
        WebElement my_action = incoming_table.getRowsMappedToHeadings().get(row_num).get("Actions");
        scrollCenter(driver, my_action);
        Thread.sleep(500);
        my_action.click();
    }

    public static void clickOnOutgoingTransactionsDropDownMenu(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable outgoing_table = getShippedTransactionsOutgoingTable(driver);
        WebElement my_action = outgoing_table.getRowsMappedToHeadings().get(row_num).get("Actions");
        scrollCenter(driver, my_action);
        Thread.sleep(500);
        my_action.click();
    }

    public static void clickOnDraftTransactionsDropDownMenu(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsDraftTable(driver);
        WebElement my_action = draft_table.getRowsMappedToHeadings().get(row_num).get("Actions");
        scrollCenter(driver, my_action);
        Thread.sleep(500);
        my_action.click();
    }

    public static String checkDraftTransaction(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsDraftTable(driver);
        Map<String, WebElement> my_map = draft_table.getRowsMappedToHeadings().get(row_num);
        WebElement my_checkbox = my_map.get("Choose a Row\n" +
                "Select All");
        String draftTransactionId = my_map.get("Supply Transaction Name").getText();
        my_checkbox.click();
        return draftTransactionId;
    }

    public static List<String> checkFirstDraftTransactions(WebDriver driver, int num_of_rows) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsDraftTable(driver);
        List<Map<String, WebElement>> my_list_map = draft_table.getRowsMappedToHeadings();
        List<String> my_transactions = new ArrayList<>();
        for(int i = 1; i <= num_of_rows; i++) {
            WebElement my_checkbox = my_list_map.get(i).get("Choose a Row\n" +
                    "Select All");
            String draftTransactionId = my_list_map.get(i).get("Supply Transaction Name").getText();
            my_transactions.add(draftTransactionId);
            my_checkbox.click();
        }
        return my_transactions;
    }

    public static List<String> checkLastDraftTransactions(WebDriver driver, int num_of_rows) throws InterruptedException {
        Thread.sleep(500);
        GenericTable draft_table = getShippedTransactionsDraftTable(driver);
        List<Map<String, WebElement>> my_list_map = draft_table.getRowsMappedToHeadings();
        int table_size = my_list_map.size();
        List<String> my_transactions = new ArrayList<>();
        for(int i = 1; i <= num_of_rows; i++) {
            WebElement my_checkbox = my_list_map.get(table_size - i).get("Choose a Row\n" +
                    "Select All");
            String draftTransactionId = my_list_map.get(table_size - i).get("Supply Transaction Name").getText();
            my_transactions.add(draftTransactionId);
            my_checkbox.click();
        }
        return my_transactions;
    }

    public static String checkOutgoingTransaction(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable outgoing_table = getShippedTransactionsOutgoingTable(driver);
        Map<String, WebElement> my_map = outgoing_table.getRowsMappedToHeadings().get(row_num);
        WebElement my_checkbox = my_map.get("Choose a Row\n" +
                "Select All");
        String outgoingTransactionId = my_map.get("Supply Transaction Name").getText();
        my_checkbox.click();
        return outgoingTransactionId;
    }

    public static List<String> checkFirstOutgoingTransactions(WebDriver driver, int num_of_rows) throws InterruptedException {
        Thread.sleep(500);
        GenericTable outgoing_table = getShippedTransactionsOutgoingTable(driver);
        List<Map<String, WebElement>> my_list_map = outgoing_table.getRowsMappedToHeadings();
        List<String> my_transactions = new ArrayList<>();
        for(int i = 1; i <= num_of_rows; i++) {
            WebElement my_checkbox = my_list_map.get(i).get("Choose a Row\n" +
                    "Select All");
            String outgoingTransactionId = my_list_map.get(i).get("Supply Transaction Name").getText();
            my_transactions.add(outgoingTransactionId);
            my_checkbox.click();
        }
        return my_transactions;
    }

    public static List<String> checkLastOutgoingTransactions(WebDriver driver, int num_of_rows) throws InterruptedException {
        Thread.sleep(500);
        GenericTable outgoing_table = getShippedTransactionsOutgoingTable(driver);
        List<Map<String, WebElement>> my_list_map = outgoing_table.getRowsMappedToHeadings();
        List<String> my_transactions = new ArrayList<>();
        int table_size = my_list_map.size();
        for(int i = 1; i <= num_of_rows; i++) {
            WebElement my_checkbox = my_list_map.get(table_size - i).get("Choose a Row\n" +
                    "Select All");
            String outgoingTransactionId = my_list_map.get(table_size - i).get("Supply Transaction Name").getText();
            my_transactions.add(outgoingTransactionId);
            my_checkbox.click();
        }
        return my_transactions;
    }

    public static String checkIncomingTransaction(WebDriver driver, int row_num) throws InterruptedException {
        Thread.sleep(500);
        GenericTable incoming_table = getShippedTransactionsIncomingTable(driver);
        Map<String, WebElement> my_map = incoming_table.getRowsMappedToHeadings().get(row_num);
        WebElement my_checkbox = my_map.get("Choose a Row\n" +
                "Select All");
        String incomingTransactionId = my_map.get("Supply Transaction Name").getText();
        my_checkbox.click();
        return incomingTransactionId;
    }

    public static List<String> checkFirstIncomingTransactions(WebDriver driver, int num_of_rows) throws InterruptedException {
        Thread.sleep(500);
        GenericTable incoming_table = getShippedTransactionsIncomingTable(driver);
        List<Map<String, WebElement>> my_list_map = incoming_table.getRowsMappedToHeadings();
        List<String> my_transactions = new ArrayList<>();
        for(int i = 1; i <= num_of_rows; i++) {
            WebElement my_checkbox = my_list_map.get(i).get("Choose a Row\n" +
                    "Select All");
            String incomingTransactionId = my_list_map.get(i).get("Supply Transaction Name").getText();
            my_transactions.add(incomingTransactionId);
            my_checkbox.click();
        }
        return my_transactions;
    }

    public static List<String> checkLastIncomingTransactions(WebDriver driver, int num_of_rows) throws InterruptedException {
        Thread.sleep(500);
        GenericTable incoming_table = getShippedTransactionsIncomingTable(driver);
        List<Map<String, WebElement>> my_list_map = incoming_table.getRowsMappedToHeadings();
        List<String> my_transactions = new ArrayList<>();
        int table_size = my_list_map.size();
        for(int i = 1; i <= num_of_rows; i++) {
            WebElement my_checkbox = my_list_map.get(table_size - i).get("Choose a Row\n" +
                    "Select All");
            String incomingTransactionId = my_list_map.get(table_size - i).get("Supply Transaction Name").getText();
            my_transactions.add(incomingTransactionId);
            my_checkbox.click();
        }
        return my_transactions;
    }


    public static void selectConfirmIncomingDropDown(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By select_confirm_in_dropdown_path = By.xpath("//span[text() = 'Confirm']/..");
        waitForElementToBeEnabled(driver, select_confirm_in_dropdown_path, 10);
        WebElement select_confirm_in_dropdown = driver.findElement(select_confirm_in_dropdown_path);
        scrollCenter(driver, select_confirm_in_dropdown);
        Thread.sleep(500);
        select_confirm_in_dropdown.click();
    }

    public static void selectCancelInDropDown(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By drd_cancel_btn_path = By.xpath("//a/span[text() = 'Cancel Transfer']");
        waitForElementToBeEnabled(driver, drd_cancel_btn_path, 10);
        WebElement drd_cance_btn = driver.findElement(drd_cancel_btn_path);
        scrollCenter(driver, drd_cance_btn);
        Thread.sleep(500);
        drd_cance_btn.click();
    }

    public static void selectEditInDropDown(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By drd_cancel_btn_path = By.xpath("//a/span[text() = 'Edit']");
        waitForElementToBeEnabled(driver, drd_cancel_btn_path, 10);
        WebElement drd_cance_btn = driver.findElement(drd_cancel_btn_path);
        scrollCenter(driver, drd_cance_btn);
        Thread.sleep(500);
        drd_cance_btn.click();
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

    public static void clickTransferDraftButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By transfer_draft_btn_path = By.xpath("//span[contains(text(),'Draft')]/../../../../../../..//button[text() = 'Transfer']");
        waitForElementToBeEnabled(driver, transfer_draft_btn_path, 10);
        WebElement transfer_draft_btn = driver.findElement(transfer_draft_btn_path);
        scrollCenter(driver, transfer_draft_btn);
        Thread.sleep(500);
        transfer_draft_btn.click();
    }
}
