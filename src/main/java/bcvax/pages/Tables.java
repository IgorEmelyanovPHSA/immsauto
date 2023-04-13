package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.text.DecimalFormat;
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
    @FindBy(xpath = ".//lightning-card")
    private List<WebElement> transactionTables;
    @FindBy(xpath = ".//table[@class='slds-table slds-table_header-fixed slds-table_bordered slds-table_edit resizable-cols slds-table_resizable-cols']")
    private List<WebElement> requisitionTables;

    @FindBy(xpath = ".//table[@class='slds-table slds-table_cell-buffer slds-table_bordered scrollClass']")
    private WebElement requisitionLineItemsTable;

    @FindBy(xpath = "//c-bc-hc-client-immunization-records")
    private WebElement immunizationRecordsTable;
    @FindBy(xpath = "//c-bc-hc-historical-doses-c-i-b")
    private WebElement historicalImmunizationRecordsTable;
    @FindBy(xpath = "//c-patient-immunization-records")
    private WebElement relevantImmunizationHistoryTable;
    @FindBy(xpath = "//table[@aria-label = 'Deferrals']")
    private WebElement deferralsTable;
    public Tables(WebDriver driver) {
        super(driver);
    }

    // ------  TABLES -------
    public GenericTable getSupplyLocationTable() {
        waitForTextToBePresent(driver, supplyLocationsTable ,30, "Location");
        return new GenericTable(supplyLocationsTable);
    }

    public GenericTable getContainerTransferTable() {
        waitForTextToBePresent(driver, containerTransfer ,30, "Distribution");
        return new GenericTable(containerTransfer);
    }

    public GenericTable getSupplyContainerTable() {
        waitForTextToBePresent(driver, supplyContainerTable ,30, "Container");
        return new GenericTable(supplyContainerTable);
    }

    public GenericTable getRequisitionLineItemsTable() {
        waitForTextToBePresent(driver, requisitionLineItemsTable ,30, "Dose Conversion Factor");
        return new GenericTable(requisitionLineItemsTable);
    }

    public GenericTable getDeferralsTable() {
        return new GenericTable(deferralsTable);
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
    public Map<String, WebElement> getShippedTransactionsIncomingRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSingleTransactionsTable("Incoming"));
    }

    public Map<String, WebElement> getSupplyLocationRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSupplyLocationTable());
    }

    @Step
    public Map<String, WebElement> getContainerTransferRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getContainerTransferTable());
    }
    @Step
    public Map<String, WebElement> getShippedTransactionsOutgoingRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSingleTransactionsTable("Outgoing"));
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
    public Tables clickSupplyLocationCheckBox(Map<String, String> searchCriteria) {
        WebElement element = getSupplyContainerRow(searchCriteria).get(SELECT_ALL);
        moveToElement(element);
        element.click();
        return this;
    }

    @Step
    public void getSupplyLocationActions(Map<String, String> searchCriteria) {
        WebElement element = getSupplyContainerRow(searchCriteria).get("Actions");
        moveToElement(element);
        element.click();
    }

    @Step
    public void openShippedTransactionsIncomingActions(Map<String, String> searchCriteria) {
        getShippedTransactionsIncomingRow(searchCriteria).get("").click();
    }

    @Step
    public void openShippedTransactionsOutgoingActions(Map<String, String> searchCriteria) {
        getShippedTransactionsOutgoingRow(searchCriteria).get("").click();
    }

    @Step
    public void checkShippedTransactionsIncomingCheckbox(Map<String, String> searchCriteria) {
        WebElement element = getShippedTransactionsIncomingRow(searchCriteria).get(SELECT_ALL);
        scrollTop(element);
        element.click();
    }
    @Step
    public void checkShippedTransactionsOutgoingCheckbox(Map<String, String> searchCriteria) {
        WebElement element = getShippedTransactionsOutgoingRow(searchCriteria).get(SELECT_ALL);
        scrollTop(element);
        element.click();
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
    public Double getConversionRateFromTransferRow(Map<String, String> searchCriteria) {
        String text = getContainerTransferRow(searchCriteria).get("Dose Conversion Factor").getText();
        log(" -- Conversation factor " + text + " -- " + " for search criteria" + searchCriteria.values());
        return Double.parseDouble(text.replace(",", ""));
    }

    @Step
    public void clickOnSupplyLocationTableRow(Map<String, String> searchCriteria) {
        clickOnTableRow(searchCriteria, getSupplyLocationTable());
    }

    @Step
    public void clickOnSupplyContainerTableRow(Map<String, String> searchCriteria) {
        clickOnTableRow(searchCriteria, getSupplyContainerTable());
    }

    // ------  PRIVATE -------
    private Map<String, WebElement> getTableRow(Map<String, String> searchCriteria, GenericTable table) {
        return table.getMappedRow(searchCriteria);
    }

    private WebElement getTableCell(Map<String, String> searchCriteria, GenericTable table) {
        return table.getCellElement(searchCriteria);
    }

    private void clickOnTableRow(Map<String, String> searchCriteria, GenericTable table) {
        WebElement element = getTableCell(searchCriteria, table).findElement(By.xpath(".//a"));
        clickUsingJS(element);
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

    public GenericTable getSingleRequisitionTable(String dataTable) {
        WebElement singleTable = null;
        waitForTextToBePresentInTable(driver, requisitionTables ,20, "Requisition");
        for (WebElement e : requisitionTables) {
            if (e.getText().contains(dataTable)) {
                singleTable = e;
                break;
            }
        }
        return new GenericTable(singleTable);
    }

    public GenericTable getImmunizationRecordsTable() {
        return new GenericTable(immunizationRecordsTable);
    }
    public GenericTable getHistoricalImmunizationRecordsTable() {
        return new GenericTable(historicalImmunizationRecordsTable);
    }
    public GenericTable getRelevantImmunizationHistoryTable() {
        return new GenericTable(relevantImmunizationHistoryTable);
    }
}
