package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;


public class Tables extends BasePage {

    @FindBy(xpath = "//div[@class='listViewContent slds-table--header-fixed_container']")
    private WebElement supplyLocationsTable;
    @FindBy(xpath = ".//div[@class='slds-table_header-fixed_container slds-scrollable_x']")
    private WebElement supplyContainerTable;
    @FindBy(xpath = "//article")
    private List<WebElement> transactionTables;

    public Tables(WebDriver driver) {
        super(driver);
    }

    // ------  TABLES -------
    public GenericTable getSupplyLocationTable() {
        waitForElementToBeVisible(driver, supplyLocationsTable, 20);
        return new GenericTable(supplyLocationsTable);
    }

    public GenericTable getSupplyContainerTable() {
        waitForElementToBeVisible(driver, supplyContainerTable, 20);
        return new GenericTable(supplyContainerTable);
    }

    public GenericTable getSingleTransactionsTable(String table) {
        WebElement shippedTransactionsOutgoing = getSingleTableFromMultipleTables(table).findElement(By.xpath(".//div[@class='slds-table_header-fixed_container slds-scrollable_x']"));
        return new GenericTable(shippedTransactionsOutgoing);
    }

    @Step
    public Map<String, WebElement> getShippedTransactionsOutgoingRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSingleTransactionsTable("Outgoing"));
    }

    public Map<String, WebElement> getTableRow(Map<String, String> searchCriteria, GenericTable table) {
        return table.getMappedRow(searchCriteria);
    }

    public WebElement getTableCell(Map<String, String> searchCriteria, GenericTable table) {
        return table.getCellElement(searchCriteria);
    }

    private void clickOnTableRow(Map<String, String> searchCriteria, GenericTable table) {
        WebElement element = getTableCell(searchCriteria, table).findElement(By.xpath(".//a"));
        clickUsingJS(element);
    }

    @Step
    public void clickOnSupplyLocationTableRow(Map<String, String> searchCriteria) {
        clickOnTableRow(searchCriteria, getSupplyLocationTable());
    }

    private WebElement getSingleTableFromMultipleTables(String dataTable) {
        WebElement singleTable = null;
        for (WebElement e : transactionTables) {
            if (e.getText().contains(dataTable)) {
                singleTable = e;
            }
        }
        return singleTable;
    }

    @Step
    public String getSupplyTransactionName(Map<String, String> searchCriteria) {
        Map<String, WebElement> webElementMap = getShippedTransactionsOutgoingRow(searchCriteria);
        String doses = webElementMap.get("Sort by:\nSupply Transaction Name").getText();
        log("----Transactions name  " + doses + "--*/");
        return doses;
    }

    @Step
    public Double getRemainingDoses(Map<String, String> searchCriteria) {
        Map<String, WebElement> stringWebElementMap = getSupplyContainerRow(searchCriteria);
        String doses = stringWebElementMap.get("Sort by:\nRemaining Doses").getText();
        log("/*4.----Remaining Doses " + doses + "--*/");
        return Double.parseDouble(doses.replace(",", ""));
    }

    @Step
    public Double getRemainingQty(Map<String, String> searchCriteria) {
        Map<String, WebElement> stringWebElementMap = getSupplyContainerRow(searchCriteria);
        String qty = stringWebElementMap.get("Sort by:\nRemaining Quantity").getText();
        log("/* ----Remaining Qty " + qty + "--*/");
        return Double.parseDouble(qty.replace(",", ""));

    }

    @Step
    public void getSupplyLocationActions(Map<String, String> searchCriteria) {
        getSupplyContainerRow(searchCriteria).get("").click();
    }

    @Step
    public Map<String, WebElement> getShippedTransactionsIncomingRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSingleTransactionsTable("Incoming"));
    }

    @Step
    public void openShippedTransactionsIncomingActions(Map<String, String> searchCriteria) {
        getShippedTransactionsIncomingRow(searchCriteria).get("").click();
    }

    @Step
    public Map<String, WebElement> getSupplyContainerRow(Map<String, String> searchCriteria) {
        return getTableRow(searchCriteria, getSupplyContainerTable());
    }


}
