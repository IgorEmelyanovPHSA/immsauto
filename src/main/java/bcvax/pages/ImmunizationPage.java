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

    @FindBy(xpath = "//span[text() = 'Remediation Needed']")
    private WebElement remediaation_needed;

    public ImmunizationPage(WebDriver driver) {
        super(driver);
    }
    public String validatePirSubmissionStatusFieldIsDisplayed() throws InterruptedException {
        By pir_submission_status_field_displayed = By.xpath("//span[@class='test-id__field-label' and text()='PIR Submission Status']");
        waitForElementToBeLocated(driver, pir_submission_status_field_displayed, 10);
        WebElement element = driver.findElement(pir_submission_status_field_displayed);
        return element.getText();
    }

    public String validatePathwayStatusFieldIsDisplayed() throws InterruptedException {
        By pathway_status_field_displayed = By.xpath("//span[@class='test-id__field-label' and text()='Pathway Status']");
        waitForElementToBeEnabled(driver, pathway_status_field_displayed, 10);
        WebElement element = driver.findElement(pathway_status_field_displayed);
        return element.getText();
    }

    public boolean remidiationNeededCheckBox() throws InterruptedException {
        if (!remediaation_needed.isSelected())
            return true;
        else
            return false;
    }

    public String pirImmunizationId() throws InterruptedException {
        By pir_immunization_id_field = By.xpath("//span[@class='test-id__field-label' and text()='PIR Immunization ID']/../..//lightning-formatted-text[@data-output-element-id='output-field']");
        waitForElementToBeEnabled(driver, pir_immunization_id_field, 10);
        WebElement element = driver.findElement(pir_immunization_id_field);
        return element.getText();
    }

    public String pirSubmissionStatusFieldValue() throws InterruptedException {
        By pir_submission_status_field_value = By.xpath("//span[@class='test-id__field-label' and text()='PIR Submission Status']/../..//lightning-formatted-text");
        waitForElementToBeEnabled(driver, pir_submission_status_field_value, 10);
        WebElement element = driver.findElement(pir_submission_status_field_value);
        return element.getText();
    }
}
