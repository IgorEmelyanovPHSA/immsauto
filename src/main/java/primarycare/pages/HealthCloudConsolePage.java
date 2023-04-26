package primarycare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import primarycare.pages.BasePage;

public class HealthCloudConsolePage extends BasePage {

    /*---------Properties-------*/
    @FindBy(xpath = ".//a[text() = 'Related']")
    private WebElement patient_account_Related_tab;
    private By patient_account_Related_tab_1 = By.xpath(".//a[text() = 'Related']");

    @FindBy(xpath = ".//a[@title='Attachment Registry']")
    private WebElement case_record;
    private By case_record_1 = By.xpath(".//a[@title='Attachment Registry']");

    @FindBy(xpath = ".//flexipage-field[@data-field-id='RecordOriginField']//slot[1]//slot[1]/lightning-formatted-text")
    private WebElement case_origin_actual_field_value;
    private By case_origin_actual_field_value_1 = By.xpath(".//flexipage-field[@data-field-id='RecordOriginField']//slot[1]//slot[1]/lightning-formatted-text");



    /*---------Constructor-------*/
    public HealthCloudConsolePage(WebDriver driver) {
        super(driver);
    }



    /*-------------Methods--------------*/
    public void clickOnRelatedTab() throws InterruptedException {
        WebElement element = driver.findElement(patient_account_Related_tab_1);
        Thread.sleep(2000);
        isDisplayed(patient_account_Related_tab_1);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickOnCaseRecord() throws InterruptedException {
        waitForElementToBeLocated(driver, case_record_1, 10);
        Thread.sleep(2000);
        case_record.click();
    }

    public String getCaseOriginActualForValidation() throws InterruptedException {
        waitForElementToBeLocated(driver, case_origin_actual_field_value_1, 10);
        Thread.sleep(2000);
        case_origin_actual_field_value.isDisplayed();
        return (case_origin_actual_field_value.getText());
    }

}
