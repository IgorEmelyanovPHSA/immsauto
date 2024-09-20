package bcvax.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DeferralForm extends BasePage {
    @FindBy(xpath = "//span[text() = 'Reason for Deferral']/../..//a[@class = 'select']")
    private WebElement reasoForDeferralField;

    public DeferralForm(WebDriver driver) {
        super(driver);
    }

    public static void cleanupProfile(WebDriver driver) throws InterruptedException {
        By cleanup_profile_button_path = By.xpath("//label[text()='Profile']/..//button[@title='Clear Selection']");
        waitForElementToBeEnabled(driver, cleanup_profile_button_path, 10);
        WebElement cleanup_profile_button = driver.findElement(cleanup_profile_button_path);
        cleanup_profile_button.click();
    }

    public static void saveDeferral(WebDriver driver) throws InterruptedException {
        By save_deferral_button_path = By.xpath("//records-base-record-form//button[@name='SaveEdit']");
        waitForElementToBeEnabled(driver, save_deferral_button_path, 10);
        WebElement save_profile_button = driver.findElement(save_deferral_button_path);
        save_profile_button.click();
    }

    public static String[] getMissingValuesError(WebDriver driver) throws InterruptedException {
        String errorText = driver.findElement(By.xpath("//ul[@class = 'errorsList']/li")).getText();
        String[] missingValues = StringUtils.stripAll(errorText.split(":")[1].split(","));
        return missingValues;
    }

    public static void setProfile(WebDriver driver, String profile) throws InterruptedException {
        By deferral_profile_field_path = By.xpath("//label[text()='Profile']/..//input");
        waitForElementToBeEnabled(driver, deferral_profile_field_path, 10);
        WebElement deferral_profile_field = driver.findElement(deferral_profile_field_path);
        deferral_profile_field.sendKeys(profile);
        Thread.sleep(500);
        By my_profile_path = By.xpath("//label[text()='Profile']/..//lightning-base-combobox-formatted-text[@title='" + profile + "']");
        WebElement my_profile = driver.findElement(my_profile_path);
        my_profile.click();
    }

    public static void setAgent(WebDriver driver, String agent) throws InterruptedException {
        By deferral_agent_field_path = By.xpath("//label[text()='Agent']/..//input");
        waitForElementToBeEnabled(driver, deferral_agent_field_path, 10);
        WebElement deferral_agent_field = driver.findElement(deferral_agent_field_path);
        deferral_agent_field.sendKeys(agent);
        Thread.sleep(500);
        By my_agent_path = By.xpath("//label[text()='Agent']/..//lightning-base-combobox-formatted-text[@title='" + agent + "']");
        WebElement my_agent = driver.findElement(my_agent_path);
        my_agent.click();
    }

    public void setReasonForDeferral(String reason) throws InterruptedException {
        waitForElementToBeVisible(driver, reasoForDeferralField, 10);
        reasoForDeferralField.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title = '" + reason + "']")).click();
    }

    public static void setEffectiveFrom(WebDriver driver) throws InterruptedException {
        By effective_from_path = By.xpath("//input[@name='Effective_From__c']");
        waitForElementToBeEnabled(driver, effective_from_path, 10);
        WebElement effective_from_field = driver.findElement(effective_from_path);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        effective_from_field.sendKeys(df.format(currentTime));
    }
}
