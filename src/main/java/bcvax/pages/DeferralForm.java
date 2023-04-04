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
    @FindBy(xpath = "//span[text() = 'Agent']/../..//input[@title = 'Search Code Sets']")
    private WebElement agentField;

    @FindBy(xpath = "//span[text() = 'Profile']/../..//a[@class = 'deleteAction']")
    private WebElement deleteProfileBtn;

    @FindBy(xpath = "//span[text() = 'Profile']/../..//input")
    private WebElement profileField;

    @FindBy(xpath = "//span[text() = 'Reason for Deferral']/../..//a[@class = 'select']")
    private WebElement reasoForDeferralField;

    @FindBy(xpath = "//span[text() = 'Effective From']/../..//input")
    private WebElement effectiveFromField;
    private WebElement effectiveToField;
    private WebElement saveReferralBtn;
    public DeferralForm(WebDriver driver) {
        super(driver);
    }

    public void cleanupProfile() {
        waitForElementToBeVisible(driver, deleteProfileBtn, 10);
        deleteProfileBtn.click();
    }

    public void saveDeferral() {
        saveReferralBtn = driver.findElement(By.xpath("//button[@title = 'Save']"));
        saveReferralBtn.click();
    }

    public String[] getMissingValuesError() {
        String errorText = driver.findElement(By.xpath("//ul[@class = 'errorsList']/li")).getText();
        String[] missingValues = StringUtils.stripAll(errorText.split(":")[1].split(","));
        return missingValues;
    }

    public void setProfile(String profile) throws InterruptedException {
        waitForElementToBeVisible(driver, profileField, 10);
        try {
            driver.findElement(By.xpath("//span[text() = 'Profile']/../..//div[@title = '" + profile + "']")).click();
        } catch(NoSuchElementException ex) {
            profileField.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text() = 'Profile']/../..//div[@title = '" + profile + "']")).click();
        }
    }

    public void setAgent(String agent) throws InterruptedException {
        waitForElementToBeVisible(driver, agentField, 10);
        agentField.click();
        Thread.sleep(2000);
        agentField.sendKeys(agent.split(" ")[0]);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text() = 'Agent']/../..//div[@title = '" + agent + "']")).click();

    }

    public void setReasonForDeferral(String reason) throws InterruptedException {
        waitForElementToBeVisible(driver, reasoForDeferralField, 10);
        reasoForDeferralField.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title = '" + reason + "']")).click();
    }

    public void setEffectiveFrom() throws InterruptedException {
        waitForElementToBeVisible(driver, effectiveFromField, 10);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        effectiveFromField.sendKeys(df.format(currentTime));
        Thread.sleep(2000);

    }
}
