package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImmunizationPageCP extends BasePage{

    private By pir_submission_status_field_displayed = By.xpath("//span[@class='test-id__field-label'][text()='PIR Submission Status']");
    private By pathway_status_field_displayed = By.xpath("//span[@class='test-id__field-label'][text()='Pathway Status']");
    private By pir_immunization_id_field = By.xpath("//span[@class='test-id__field-label'][text()='PIR Immunization ID']/../..//span[@class='uiOutputText']");
    private By pir_submission_status_field_value = By.xpath("//span[@class='test-id__field-label'][text()='PIR Submission Status']/../..//span[@class='uiOutputTextArea']");
    @FindBy(xpath = "//span[text() = 'Remediation Needed']/../..//span[@class='uiImage uiOutputCheckbox']")
    private WebElement remediation_needed;

    public ImmunizationPageCP(WebDriver driver) {
        super(driver);
    }
    public String validatePirSubmissionStatusFieldIsDisplayed() throws InterruptedException {
        WebElement element = driver.findElement(pir_submission_status_field_displayed);
        Thread.sleep(2000);
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
        if (!remediation_needed.isSelected())
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
