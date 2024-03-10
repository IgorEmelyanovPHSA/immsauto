package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentDetailsPage extends BasePage {
    public AppointmentDetailsPage(WebDriver driver) {
        super(driver);
    }

    public static String getCitizenComment(WebDriver driver) throws InterruptedException {
        By citizenCommentElement = By.xpath("//span[text()='Citizen Comment']/../..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only'] | //span[@class='test-id__field-label' and text()='Citizen Comment']/../../..//lightning-formatted-text");
        waitForElementToBePresent(driver, citizenCommentElement, 10);
        String getCitizenCommentString = driver.findElement(citizenCommentElement).getText();
        return getCitizenCommentString;
    }

    public static String getAppointmentStatus(WebDriver driver) throws InterruptedException {
        By appointmentStatusElement = By.xpath("//span[contains(text(),'Status')]/../..//span[@class='test-id__field-value slds-form-element__static slds-grow ']  | //span[@class='test-id__field-label' and text()='Status']/../../..//lightning-formatted-text");
        waitForElementToBePresent(driver, appointmentStatusElement, 10);
        String getAppointmentStatusString = driver.findElement(appointmentStatusElement).getText();
        return getAppointmentStatusString;
    }

    public static String getReasonForVisit(WebDriver driver) throws InterruptedException {
        By clientReasonForVisitElement = By.xpath("//span[contains(text(),'Client Reason for Visit')]/../..//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']  | //span[@class='test-id__field-label' and text()='Client Reason for Visit']/../../..//lightning-formatted-text");
        waitForElementToBePresent(driver, clientReasonForVisitElement, 10);
        String getClientReasonForVisitString = driver.findElement(clientReasonForVisitElement).getText();
        return getClientReasonForVisitString;
    }
}
