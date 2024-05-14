package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static constansts.Domain.SELECT_ALL;
import static constansts.Header.*;


public class Tables extends BasePage {

    @FindBy(xpath = "//div[@class='listViewContent slds-table--header-fixed_container']")
    private WebElement supplyLocationsTable;

    @FindBy(xpath = ".//div[@class='slds-table_header-fixed_container slds-scrollable_x']")
    private WebElement supplyContainerTable;
    @FindBy(xpath = ".//table[@class='slds-table slds-table_cell-buffer slds-table_bordered scrollClass']")
    private WebElement containerTransfer;
    @FindBy(xpath = ".//table[@class='slds-table slds-table_cell-buffer slds-table_bordered']")
    private WebElement containerAdjustmentWastage;
    @FindBy(xpath = ".//lightning-card")
    private List<WebElement> transactionTables;

    public Tables(WebDriver driver) {
        super(driver);
    }

    // ------  TABLES -------
    public GenericTable getSupplyLocationTable() {
        waitForTextToBePresent(driver, supplyLocationsTable ,30, "Location");
        return new GenericTable(supplyLocationsTable);
    }

    public GenericTable getAppointmentDayTable() throws InterruptedException {
        Thread.sleep(500);
        By appointment_day_table_path = By.xpath("//div[@class='listViewContent slds-table--header-fixed_container']");
        waitForElementToBeEnabled(driver, appointment_day_table_path, 30);
        WebElement appointment_day_table = driver.findElement(appointment_day_table_path);
        return new GenericTable(appointment_day_table);
    }

    public GenericTable getContainerTransferTable() {
        waitForTextToBePresent(driver, containerTransfer ,30, "Distribution");
        return new GenericTable(containerTransfer);
    }

    public GenericTable getContainerAdjustmentWastageTable() {
        waitForTextToBePresent(driver, containerAdjustmentWastage,30, "Distribution");
        return new GenericTable(containerAdjustmentWastage);
    }

    public GenericTable getSupplyContainerTable() {
        waitForTextToBePresent(driver, supplyContainerTable ,30, "Container");
        return new GenericTable(supplyContainerTable);
    }

    public GenericTable getSingleTransactionsTable(String table) {
        WebElement shippedTransactionsOutgoing = getSingleTableFromMultipleTables(table).findElement(By.xpath(".//div[@class='slds-table_header-fixed_container slds-scrollable_x']"));
        return new GenericTable(shippedTransactionsOutgoing);
    }

    // ------  TABLE ROWS -------
    public Map<String, WebElement> getSupplyContainerRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSupplyContainerTable());
    }

    @Step
    public Map<String, WebElement> getContainerTransferRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getContainerTransferTable());
    }

    // ------  TABLE ACTIONS -------
    @Step
    public Double getRemainingDoses(Map<String, String> searchCriteria) {
        Map<String, WebElement> stringWebElementMap = getSupplyContainerRow(searchCriteria);
        String doses = stringWebElementMap.get(REMAINING_DOSES).getText();
        log(" -- Remaining Doses " + doses + " -- ");
        DecimalFormat df = new DecimalFormat("####0.00");
        double dosesValue = Double.parseDouble(doses.replace(",", ""));
        return Double.parseDouble(df.format(dosesValue));
    }

    @Step
    public Double getRemainingQty(Map<String, String> searchCriteria) {
        Map<String, WebElement> stringWebElementMap = getSupplyContainerRow(searchCriteria);
        String qty = stringWebElementMap.get(REMAINING_QUANTITY).getText();
        log(" -- Remaining Qty " + qty + " -- ");
        DecimalFormat df = new DecimalFormat("####0.00");
        double qtyValue = Double.parseDouble(qty.replace(",", ""));
        return Double.parseDouble(df.format(qtyValue));
    }

    @Step
    public void typeQtyIntoTransferRow(Map<String, String> searchCriteria, String data) {
        WebElement element = getContainerTransferRow(searchCriteria).get("Quantity");
        moveToElement(element);
        element.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).sendKeys(data).perform();
    }

    @Step
    public void typeDosesIntoTransferRow(Map<String, String> searchCriteria, String data) {
        WebElement element = getContainerTransferRow(searchCriteria).get("Doses");
        moveToElement(element);
        element.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).sendKeys(data).perform();
    }

    @Step
    public void clickOnSupplyLocationTableRow(Map<String, String> searchCriteria) throws InterruptedException {
        clickOnTableRow(searchCriteria, getSupplyLocationTable());
    }

    // ------  PRIVATE -------
    private Map<String, WebElement> getTableRow(Map<String, String> searchCriteria, GenericTable table) {
        return table.getMappedRow(searchCriteria);
    }

    private WebElement getTableCell(Map<String, String> searchCriteria, GenericTable table) {
        return table.getCellElement(searchCriteria);
    }

    private void clickOnTableRow(Map<String, String> searchCriteria, GenericTable table) throws InterruptedException {
        Thread.sleep(500);
        int count = 0;
        while(count < 10) {
            try {
                WebElement myCell = getTableCell(searchCriteria, table);
                WebElement element = myCell.findElement(By.xpath(".//a"));
                element.click();
                break;
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("Cell not found. Try again...");
                count = count + 1;
            }
        }
    }

    private WebElement getSingleTableFromMultipleTables(String dataTable) {
        WebElement singleTable = null;
        waitForTextToBePresentInTable(driver, transactionTables ,20, "Transaction");
        for (WebElement e : transactionTables) {
            if (e.getText().contains(dataTable)) {
                singleTable = e;
                break;
            }
        }
        return singleTable;
    }

    public GenericTable getReturnLineItemsTable() {
        WebElement singleTable = null;
        By returns_line_items_table_path = By.xpath("//article[@aria-label='Return Line Items']");
        waitForElementToBeLocated(driver, returns_line_items_table_path, 10);
        singleTable = driver.findElement(returns_line_items_table_path);
        return new GenericTable(singleTable);
    }

    public GenericTable getReturnLineItemsTableCP() {
        WebElement singleTable = null;
        By returns_line_items_table_path = By.xpath("//div[@class='simpleEmptyState autoHeight  test-listViewManager slds-card slds-card_boundary slds-grid slds-grid--vertical forceListViewManager' and @aria-label='Return Line Items|Return Line Items|List View']");
        waitForElementToBeLocated(driver, returns_line_items_table_path, 10);
        singleTable = driver.findElement(returns_line_items_table_path);
        return new GenericTable(singleTable);
    }

    public GenericTable getReturnsLocationHistoryTable() {
        WebElement singleTable = null;
        By returns_location_history_table_path = By.xpath("//article[@aria-label='Returns Location History']");
        waitForElementToBeLocated(driver, returns_location_history_table_path, 10);
        singleTable = driver.findElement(returns_location_history_table_path);
        return new GenericTable(singleTable);
    }

    public GenericTable getReturnsLocationHistoryTableCP() {
        WebElement singleTable = null;
        By returns_location_history_table_path = By.xpath("//div[@class='simpleEmptyState autoHeight  test-listViewManager slds-card slds-card_boundary slds-grid slds-grid--vertical forceListViewManager' and @aria-label='Returns Location History|Returns Location History|List View']");
        waitForElementToBeLocated(driver, returns_location_history_table_path, 10);
        singleTable = driver.findElement(returns_location_history_table_path);
        return new GenericTable(singleTable);
    }
}
