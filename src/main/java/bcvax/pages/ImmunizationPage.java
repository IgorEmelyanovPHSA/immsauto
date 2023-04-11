package bcvax.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class ImmunizationPage extends BasePage{

    private By pir_submission_status_field_displayed = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='PIR Submission Status']");
    private By pathway_status_field_displayed = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='Pathway Status']");
    private By pir_immunization_id_field = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='PIR Immunization ID']/../..//LIGHTNING-FORMATTED-TEXT[@data-output-element-id='output-field']");
    private By pir_submission_status_field_value = By.xpath("//SPAN[@records-recordlayoutitem_recordlayoutitem=''][text()='PIR Submission Status']/../..//LIGHTNING-FORMATTED-TEXT");
    @FindBy(xpath = "//span[text() = 'Remediation Needed']")
    private WebElement remediaation_needed;

    public ImmunizationPage(WebDriver driver) {
        super(driver);
    }
    public String validatePirSubmissionStatusFieldIsDisplayed() throws InterruptedException {
        waitForElementToBeLocated(driver, pir_submission_status_field_displayed, 10);
        WebElement element = driver.findElement(pir_submission_status_field_displayed);
        element.getText();
        return (element.getText());
    }

    public String validatePathwayStatusFieldIsDisplayed() throws InterruptedException {
        WebElement element = driver.findElement(pathway_status_field_displayed);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public boolean remidiationNeededCheckBox() throws InterruptedException {
        if (!remediaation_needed.isSelected())
            return true;
        else
            return false;
    }

    public String pirImmunizationId() throws InterruptedException {
        WebElement element = driver.findElement(pir_immunization_id_field);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }

    public String pirSubmissionStatusFieldValue() throws InterruptedException {
        WebElement element = driver.findElement(pir_submission_status_field_value);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        Thread.sleep(2000);
        element.getText();
        return (element.getText());
    }
}
