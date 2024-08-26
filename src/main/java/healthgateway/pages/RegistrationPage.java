package healthgateway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class RegistrationPage extends BasePage {

    @FindBy(xpath = "//span[text()='Email Notifications']/..//span[@class='slds-checkbox_faux']")
    private WebElement checkboxEmailNotifications;

    @FindBy(xpath = "//span[text()='Text Notifications']/..//span[@class='slds-checkbox_faux']")
    private WebElement checkboxTextNotifications;

    @FindBy(xpath = "//input[@name='emailUpdates1']")
    private WebElement textEmail;

    @FindBy(xpath = "//input[@name='emailUpdates2']")
    private WebElement textEmailConfirmation;

    @FindBy(xpath = "//input[@name='phoneNumber']")
    private WebElement textPhoneNumber;

    By warningInvalidEMailXpath = By.xpath("//span[text()='Invalid email']");

    By warningInvalidPhoneNumberXpath = By.xpath("//span[text()='Invalid phone number']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnEmailNotificationCheckbox() throws InterruptedException {
        click(checkboxEmailNotifications);
    }

    public void clickOnTextNotificationCheckbox() throws InterruptedException {
        click(checkboxTextNotifications);
    }

    public void emailCheckTest(String email) throws InterruptedException {
        clickOnEmailNotificationCheckbox();
        Thread.sleep(1000);
        typeIn(textEmail,email);
        typeIn(textEmailConfirmation,email);

        //Click anywhere outside the box to trigger validation
        click(checkboxTextNotifications);
        Thread.sleep(1000);
    }

    public void phoneCheckTest(String phoneNumber) throws InterruptedException {
        clickOnTextNotificationCheckbox();
        Thread.sleep(1000);
        typeIn(textPhoneNumber,phoneNumber);

        //Click anywhere outside the box to trigger validation
        //click(checkboxEmailNotifications);
        Thread.sleep(2000);
    }

    public int checkForEmailValidationErrorMessages(){
        List<WebElement> errorMessages = driver.findElements(warningInvalidEMailXpath);
        return errorMessages.size();
    }

    public int checkForPhoneNumberValidationErrorMessages(){
        List<WebElement> errorMessages = driver.findElements(warningInvalidPhoneNumberXpath);
        return errorMessages.size();
    }

}