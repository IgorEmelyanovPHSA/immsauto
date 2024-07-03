package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddDeferralDialog extends BasePage {
    public AddDeferralDialog(WebDriver driver) {
        super(driver);
    }

    public static void selectAgent(WebDriver driver, String agent) throws InterruptedException {
        Thread.sleep(500);
        WebElement new_deferral_dialog = getNewDeferralDialog(driver);
        By select_agent_button_path = By.xpath(".//c-bc-hc-input-search-drop-down");
        WebElement select_agent_button = new_deferral_dialog.findElement(select_agent_button_path);
        scrollCenter(driver, select_agent_button);
        Thread.sleep(500);
        select_agent_button.click();
        WebElement search_input = select_agent_button.findElement(By.xpath(".//input[@class='slds-input search-input-class']"));
        search_input.sendKeys(agent);
        WebElement my_agent = select_agent_button.findElement(By.xpath(".//li[@title='" + agent + "']"));
        scrollCenter(driver, my_agent);
        my_agent.click();
    }

    public static void setEffectiveFromDate(WebDriver driver, int offset) throws InterruptedException {
        Thread.sleep(500);
        By effective_from_field_path = By.xpath(".//input[@name='Effective_From__c']");
        WebElement new_deferral_dialog = getNewDeferralDialog(driver);
        WebElement effective_from_field = new_deferral_dialog.findElement(effective_from_field_path);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime new_date = currentTime.minusDays(offset);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        effective_from_field.sendKeys(df.format(new_date));
    }

    public static void selectReasonForDeferral(WebDriver driver, String reason) throws InterruptedException {
        clickSelectDeferralReason(driver);
        Thread.sleep(500);
        By options_list_path = By.xpath("//c-new-deferral-modal//lightning-base-combobox-item");
        List<String> options = new ArrayList<String>();
        List<WebElement> options_list = driver.findElements(options_list_path);
        if(options_list.size() < 2) {
            clickSelectDeferralReason(driver);
            Thread.sleep(500);
        }
        for(WebElement reason_item: options_list) {
            String item = reason_item.getAttribute("data-value");
            if(item.equals(reason)) {
                scrollCenter(driver, reason_item);
                Thread.sleep(500);
                try {
                    reason_item.click();
                } catch (ElementNotInteractableException ex) {
                    clickSelectDeferralReason(driver);
                    Thread.sleep(500);
                    scrollCenter(driver, reason_item);
                    Thread.sleep(500);
                    reason_item.click();
                }
            }
        }
    }

    public static void clickSaveButton(WebDriver driver) throws InterruptedException {
        By save_button_path = By.xpath(".//button[text()='Save']");
        WebElement new_deferral_dialog = getNewDeferralDialog(driver);
        WebElement save_button = new_deferral_dialog.findElement(save_button_path);
        scrollCenter(driver, save_button);
        Thread.sleep(500);
        save_button.click();
    }

    public static List<String> getListOfReasons(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By options_list_path = By.xpath("//c-new-deferral-modal//lightning-base-combobox-item");
        List<String> options = new ArrayList<String>();
        List<WebElement> options_list = driver.findElements(options_list_path);
        if(options_list.size() < 2) {
            clickSelectDeferralReason(driver);
            Thread.sleep(500);
        }
        for(WebElement reason_item: options_list) {
            String item = reason_item.getAttribute("data-value");
            options.add(item);
        }
        return options;
    }

    public static void clickSelectDeferralReason(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        WebElement new_deferral_dialog = getNewDeferralDialog(driver);
        By select_reason_button_path = By.xpath(".//button[@name='Reason_for_Deferral__c']");
        WebElement select_reason_button = new_deferral_dialog.findElement(select_reason_button_path);
        scrollCenter(driver, select_reason_button);
        Thread.sleep(500);
        select_reason_button.click();
    }

    public static WebElement getNewDeferralDialog(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By new_deferral_dialog_path = By.xpath("//c-new-deferral-modal");
        waitForElementToBeEnabled(driver, new_deferral_dialog_path, 10);
        WebElement new_deferal_dialog = driver.findElement(new_deferral_dialog_path);
        return new_deferal_dialog;
    }
}
