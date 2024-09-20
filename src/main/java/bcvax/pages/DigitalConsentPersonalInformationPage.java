package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;
import java.util.HashMap;
import java.text.ParseException;
import java.util.HashMap;

public class DigitalConsentPersonalInformationPage extends BasePage {
    public DigitalConsentPersonalInformationPage(WebDriver driver) {
        super(driver);
    }

    public static void enterFirstName(WebDriver driver, String first_name) throws InterruptedException {
        By first_name_field_path = By.xpath("//input[@name='FirstName']");
        waitForElementToBeEnabled(driver, first_name_field_path, 10);
        WebElement first_name_field = driver.findElement(first_name_field_path);
        scrollCenter(driver, first_name_field);
        first_name_field.sendKeys(first_name);
    }

    public static String getFirstNameError(WebDriver driver) throws InterruptedException {
        By first_name_error_path = By.xpath("//lightning-input[@data-name='FirstName']//div[@role='status']");
        waitForElementToBeEnabled(driver, first_name_error_path, 10);
        WebElement first_name_error = driver.findElement(first_name_error_path);
        return first_name_error.getText();
    }

    public static void enterLastName(WebDriver driver, String last_name) throws InterruptedException {
        By last_name_field_path = By.xpath("//input[@name='LastName']");
        WebElement last_name_field = driver.findElement(last_name_field_path);
        waitForElementToBeEnabled(driver, last_name_field_path, 10);
        scrollCenter(driver, last_name_field);
        last_name_field.sendKeys(last_name);
    }

    public static String getLastNameError(WebDriver driver) throws InterruptedException {
        By last_name_error_path = By.xpath("//lightning-input[@data-name='LastName']//div[@role='status']");
        waitForElementToBeEnabled(driver, last_name_error_path, 10);
        WebElement last_name_error = driver.findElement(last_name_error_path);
        return last_name_error.getText();
    }

    public static void enterPHN(WebDriver driver, String phn) throws InterruptedException {
        By phn_field_path = By.xpath("//input[@name='HC_Personal_Health_Number']");
        WebElement phn_field = driver.findElement(phn_field_path);
        waitForElementToBeEnabled(driver, phn_field_path, 10);
        phn_field.clear();
        scrollCenter(driver, phn_field);
        phn_field.sendKeys(phn);
    }

    public static String getPhnError(WebDriver driver) throws InterruptedException {
        By phn_error_path = By.xpath("//lightning-input[@id='HC_Personal_Health_Number-8']//div[@role='status']");
        waitForElementToBeEnabled(driver, phn_error_path, 10);
        WebElement phn_error = driver.findElement(phn_error_path);
        return phn_error.getText();
    }

    public static void enterDOB(WebDriver driver, String dob) throws InterruptedException, ParseException {
        By dob_field_path = By.xpath("//input[@name='PersonBirthdate']");
        waitForElementToBeEnabled(driver, dob_field_path, 10);
        WebElement dob_field = driver.findElement(dob_field_path);
        scrollCenter(driver, dob_field);
        dob_field.sendKeys(Utils.convertDate(dob,"MMM dd, yyyy"));
    }

    public static String getDOBError(WebDriver driver) throws InterruptedException {
        By dob_error_path = By.xpath("//lightning-input[@data-name='PersonBirthdate']//div[@data-error-message]");
        waitForElementToBeEnabled(driver, dob_error_path, 10);
        WebElement dob_error = driver.findElement(dob_error_path);
        return dob_error.getText();
    }

    public static boolean selectConsentForChild(WebDriver driver) throws InterruptedException, ParseException {
        Thread.sleep(500);
        By radio_consent_for_child_path = By.xpath("//span[text()='I am consenting for a child']");
        waitForElementToBeEnabled(driver, radio_consent_for_child_path, 10);
        WebElement radio_consent_for_child = driver.findElement(radio_consent_for_child_path);
        scrollCenter(driver, radio_consent_for_child);
        radio_consent_for_child.click();
        Thread.sleep(500);
        radio_consent_for_child = driver.findElement(radio_consent_for_child_path);
        if(!radio_consent_for_child.isSelected()) {
            radio_consent_for_child = driver.findElement(radio_consent_for_child_path);
            radio_consent_for_child.click();
        }
        return childRelationshipExists(driver);
    }

    public static void selectConsentForMyself(WebDriver driver) throws InterruptedException, ParseException {
        By radio_consent_for_myself_path = By.xpath("//span[text()='I am consenting for myself']/..");
        waitForElementToBeEnabled(driver, radio_consent_for_myself_path, 10);
        WebElement radio_consent_for_myself = driver.findElement(radio_consent_for_myself_path);
        scrollCenter(driver, radio_consent_for_myself);
        Thread.sleep(500);
        radio_consent_for_myself.click();
        Thread.sleep(500);
        radio_consent_for_myself = driver.findElement(radio_consent_for_myself_path);
        if(!radio_consent_for_myself.isSelected()) {
            radio_consent_for_myself.click();
        }
    }

    public static void selectChildParent(WebDriver driver) throws InterruptedException, ParseException {
        By radio_parent_path = By.xpath("//span[text()='I am the parent']/..");
        waitForElementToBeEnabled(driver, radio_parent_path, 10);
        WebElement radio_parent = driver.findElement(radio_parent_path);
        scrollCenter(driver, radio_parent);
        radio_parent.click();
    }

    public static void selectChildGuardian(WebDriver driver) throws InterruptedException, ParseException {
        By radio_guardian_path = By.xpath("//span[text()='I am the legal guardian']/..");
        waitForElementToBeEnabled(driver, radio_guardian_path, 10);
        WebElement radio_guardian = driver.findElement(radio_guardian_path);
        scrollCenter(driver, radio_guardian);
        radio_guardian.click();
    }

    public static void selectChildRepresentative(WebDriver driver) throws InterruptedException, ParseException {
        By radio_representative_path = By.xpath("//span[text()='I am the representative']/..");
        waitForElementToBeEnabled(driver, radio_representative_path, 10);
        WebElement radio_representative = driver.findElement(radio_representative_path);
        scrollCenter(driver, radio_representative);
        radio_representative.click();
    }

    public static boolean childRelationshipExists(WebDriver driver) throws InterruptedException {
        By radio_parent_path = By.xpath("//span[text()='I am the parent']/..");
        By radio_guardian_path = By.xpath("//span[text()='I am the legal guardian']/..");
        By radio_representative_path = By.xpath("//span[text()='I am the representative']/..");
        waitForElementToBeEnabled(driver, radio_parent_path, 1);
        WebElement radio_parent = driver.findElement(radio_parent_path);
        WebElement radio_guardian = driver.findElement(radio_guardian_path);
        WebElement radio_representative = driver.findElement(radio_representative_path);
        return true;
    }

    public static void clickContinue(WebDriver driver) throws InterruptedException {
        By continue_btn_path = By.xpath("//button[text()='Continue']");
        waitForElementToBeEnabled(driver, continue_btn_path, 10);
        WebElement continue_btn = driver.findElement(continue_btn_path);
        scrollCenter(driver, continue_btn);
        continue_btn.click();
    }

    public static void enterEmail(WebDriver driver, String email) throws InterruptedException {
        By email_field_path = By.xpath("//input[@name='PersonEmail']");
        waitForElementToBeEnabled(driver, email_field_path, 10);
        WebElement email_field = driver.findElement(email_field_path);
        scrollCenter(driver, email_field);
        email_field.sendKeys(email);
    }

    public static void enterPhone(WebDriver driver, String phone) throws InterruptedException {
        By phone_field_path = By.xpath("//input[@name='PersonMobilePhone']");
        waitForElementToBeEnabled(driver, phone_field_path, 10);
        WebElement phone_field = driver.findElement(phone_field_path);
        scrollCenter(driver, phone_field);
        phone_field.sendKeys(phone);
    }

    public static void checkContactEmail(WebDriver driver) throws InterruptedException {
        By email_checkbox_path = By.xpath("//lightning-input[@data-id='email-radio']");
        waitForElementToBeEnabled(driver, email_checkbox_path, 10);
        WebElement email_checkbox = driver.findElement(email_checkbox_path);
        scrollCenter(driver, email_checkbox);
        email_checkbox.click();
    }

    public static void checkContactTextMessage(WebDriver driver) throws InterruptedException {
        By text_checkbox_path = By.xpath("//lightning-input[@data-id='sms-radio']");
        waitForElementToBeEnabled(driver, text_checkbox_path, 10);
        WebElement text_checkbox = driver.findElement(text_checkbox_path);
        scrollCenter(driver, text_checkbox);
        text_checkbox.click();
    }

    public static void checkIVerify(WebDriver driver) throws InterruptedException {
        By i_verify_checkbox_path = By.xpath("//span[text()='I verify that the contact information entered is accurate and up to date.']/..");
        waitForElementToBeEnabled(driver, i_verify_checkbox_path, 10);
        WebElement i_verify_checkbox = driver.findElement(i_verify_checkbox_path);
        scrollCenter(driver, i_verify_checkbox);
        i_verify_checkbox.click();
    }

    public static String getPublicHealthUnitLink(WebDriver driver) throws InterruptedException {
        By public_health_unit_link_path = By.xpath("//a[text()='public health unit']");
        waitForElementToBeEnabled(driver, public_health_unit_link_path, 10);
        WebElement my_link = driver.findElement(public_health_unit_link_path);
        String my_url =  my_link.getAttribute("href");
        return my_url;
    }

    public static Map<String, String> clickPublicHealthUnitLink(WebDriver driver) throws InterruptedException {
        Map<String, String> result = new HashMap<>();
        By public_health_unit_link_path = By.xpath("//a[text()='public health unit']");
        waitForElementToBeEnabled(driver, public_health_unit_link_path, 10);
        Object[] window_handles = driver.getWindowHandles().toArray();
        int windows_number = window_handles.length;
        WebElement my_link = driver.findElement(public_health_unit_link_path);
        my_link.click();
        window_handles = driver.getWindowHandles().toArray();
        int windows_number_after = window_handles.length;
        if(windows_number_after == windows_number + 1) {
            result.put("new_tab", "yes");
        } else {
            result.put("new_tab", "no");
        }
        driver.switchTo().window((String)window_handles[windows_number_after - 1]);
        String link_title = driver.getTitle();
        result.put("window_title", link_title);

        String link = driver.getCurrentUrl();
        result.put("window_url", link);
        driver.close();
        window_handles = driver.getWindowHandles().toArray();
        windows_number_after = window_handles.length;
        driver.switchTo().window((String)window_handles[windows_number_after - 1]);
        return result;
    }
}
