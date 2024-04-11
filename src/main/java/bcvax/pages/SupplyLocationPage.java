package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class SupplyLocationPage extends BasePage {
    public SupplyLocationPage(WebDriver driver) {

        super(driver);
    }

    public static List<String> getHiddenActionButtons(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By menu_button_items_path = By.xpath("//runtime_platform_actions-actions-ribbon//lightning-button-menu[@class='menu-button-item slds-dropdown-trigger slds-dropdown-trigger_click']");
        waitForElementToBeEnabled(driver, menu_button_items_path, 10);
        WebElement menu_button_items = driver.findElement(menu_button_items_path);
        List<String> hidden_actions = Arrays.asList(menu_button_items.getAttribute("data-target-reveals").split(","));
        return hidden_actions;
    }

    public static void revealHiddenActions(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By menu_button_items_path = By.xpath("//runtime_platform_actions-actions-ribbon//lightning-button-menu[@class='menu-button-item slds-dropdown-trigger slds-dropdown-trigger_click']");
        waitForElementToBeEnabled(driver, menu_button_items_path, 10);
        WebElement menu_button_items = driver.findElement(menu_button_items_path);
        menu_button_items.click();
    }

    public static void clickCreateRequisitionButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        List<String> hidden_items = getHiddenActionButtons(driver);
        if(!hidden_items.contains("sfdc:QuickAction.HC_Supply_Location__c.Request_Supplies_Action") && !hidden_items.contains("sfdc:QuickAction.HC_Supply_Location__c.Request_Supplies")) {
            By return_button_path = By.xpath("//runtime_platform_actions-actions-ribbon//li[@data-target-selection-name='sfdc:QuickAction.HC_Supply_Location__c.Request_Supplies_Action' or @data-target-selection-name='sfdc:QuickAction.HC_Supply_Location__c.Request_Supplies']");
            WebElement return_button = driver.findElement(return_button_path);
            scrollCenter(driver, return_button);
            Thread.sleep(500);
            return_button.click();
        }
    }

    public static void clickReturnButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        List<String> hidden_items = getHiddenActionButtons(driver);
        if(!hidden_items.contains("sfdc:QuickAction.HC_Supply_Location__c.Return")) {
            By return_button_path = By.xpath("//runtime_platform_actions-actions-ribbon//li[@data-target-selection-name='sfdc:QuickAction.HC_Supply_Location__c.Return']");
            WebElement return_button = driver.findElement(return_button_path);
            return_button.click();
        }
    }

    public static void clickReceiveSuppliesButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        List<String> hidden_items = getHiddenActionButtons(driver);
        if(!hidden_items.contains("sfdc:QuickAction.HC_Supply_Location__c.HC_Receive_Supplies")) {
            By receive_supplies_button_path = By.xpath("//runtime_platform_actions-actions-ribbon//li[@data-target-selection-name='sfdc:QuickAction.HC_Supply_Location__c.HC_Receive_Supplies']");
            WebElement receive_supplies_button = driver.findElement(receive_supplies_button_path);
            scrollCenter(driver, receive_supplies_button);
            Thread.sleep(500);
            receive_supplies_button.click();
        } else {
            revealHiddenActions(driver);
            Thread.sleep(500);
            By receive_supplies_item_path = By.xpath("//runtime_platform_actions-actions-ribbon//lightning-menu-item[@data-target-selection-name='sfdc:QuickAction.HC_Supply_Location__c.HC_Receive_Supplies']");
            waitForElementToBeEnabled(driver, receive_supplies_item_path, 10);
            WebElement receive_supplies_item = driver.findElement(receive_supplies_item_path);
            scrollCenter(driver, receive_supplies_item);
            Thread.sleep(500);
            receive_supplies_item.click();
        }
    }
}
