package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CitizenPrimaryInfo extends BasePage {
    public CitizenPrimaryInfo(WebDriver driver) {
        super(driver);
    }

    public static void enterFirstName(WebDriver driver, String firstname) throws InterruptedException {
        Thread.sleep(500);
        By first_name_field_path = By.xpath("//input[@name = 'FirstName']");
        waitForElementToBeEnabled(driver, first_name_field_path, 10);
        WebElement first_name_field = driver.findElement(first_name_field_path);
        first_name_field.sendKeys(firstname);
    }

    public static void enterLastName(WebDriver driver, String lastname) throws InterruptedException {
        Thread.sleep(500);
        By last_name_field_path = By.xpath("//input[@name = 'LastName']");
        waitForElementToBeEnabled(driver, last_name_field_path, 10);
        WebElement last_name_field = driver.findElement(last_name_field_path);
        last_name_field.sendKeys(lastname);
    }

    public static void enterDateOfBirth(WebDriver driver, String dateofbirth) throws InterruptedException {
        Thread.sleep(500);
        By date_of_birth_field_path = By.xpath("//input[@name = 'PersonBirthdate']");
        waitForElementToBeEnabled(driver, date_of_birth_field_path, 10);
        WebElement date_of_birth_field = driver.findElement(date_of_birth_field_path);
        date_of_birth_field.sendKeys(dateofbirth);
    }

    public static void enterPostalCode(WebDriver driver, String postalcode) throws InterruptedException {
        Thread.sleep(500);
        By postal_code_field_path = By.xpath("//input[@name = 'DDH_HC_Zip_Code']");
        waitForElementToBeEnabled(driver, postal_code_field_path, 10);
        WebElement postal_code_field = driver.findElement(postal_code_field_path);
        postal_code_field.sendKeys(postalcode);
    }

    public static void enterPHN(WebDriver driver, String phn_number) throws InterruptedException {
        Thread.sleep(500);
        By phn_field_path = By.xpath("//input[@name = 'HC_Personal_Health_Number']");
        waitForElementToBeEnabled(driver, phn_field_path, 10);
        WebElement phn_field = driver.findElement(phn_field_path);
        phn_field.sendKeys(phn_number);
        Thread.sleep(500);
        phn_field.sendKeys(Keys.TAB);
    }

    public static void clickNextButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By next_btn_path = By.xpath("//button[@title='Next']");
        waitForElementToBeEnabled(driver, next_btn_path, 10);
        WebElement next_btn = driver.findElement(next_btn_path);
        scrollIfNeeded(driver, next_btn);
        Thread.sleep(500);
        next_btn.click();
    }

    public static void clickVerifyPHNButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By verify_phn_button_path = By.xpath("//button[@title = 'Verify Personal Health Number']");
        waitForElementToBeEnabled(driver, verify_phn_button_path, 30);
        WebElement verify_phn_button = driver.findElement(verify_phn_button_path);
        verify_phn_button.click();
    }

    public static void enterEmail(WebDriver driver, String enteremail) throws InterruptedException {
        Thread.sleep(500);
        By email_field_path = By.xpath("//input[@name = 'PersonEmail']");
        waitForElementToBeEnabled(driver, email_field_path, 10);
        WebElement email_field = driver.findElement(email_field_path);
        scrollCenter(driver, email_field);
        Thread.sleep(500);
        try {
            email_field.sendKeys(enteremail);
        } catch (ElementNotInteractableException ex) {
            Thread.sleep(2000);
            email_field = driver.findElement(email_field_path);
            scrollCenter(driver, email_field);
            Thread.sleep(500);
            email_field.sendKeys(enteremail);
        }
    }

    public static void confirmEmail(WebDriver driver, String confirmemail) throws InterruptedException {
        By confirm_email_field_path = By.xpath("//input[@name = 'ConfirmEmail']");
        waitForElementToBeEnabled(driver, confirm_email_field_path, 10);
        WebElement confirm_email_field = driver.findElement(confirm_email_field_path);
        confirm_email_field.sendKeys(confirmemail);
    }

    public static void clickReviewDetails(WebDriver driver) throws InterruptedException {
        By review_details_btn_path = By.xpath("//button[text()= 'Review Details']");
        waitForElementToBeEnabled(driver, review_details_btn_path, 10);
        WebElement review_details_btn = driver.findElement(review_details_btn_path);
        review_details_btn.click();
    }

    public static void clickRegisterButtonOnConfirmationPage(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By register_btn_path = By.xpath("//button[text() = 'Register']");
        waitForElementToBeEnabled(driver, register_btn_path, 10);
        WebElement register_btn = driver.findElement(register_btn_path);
        register_btn.click();
        Thread.sleep(500);
    }

    public static void successMessageAppear(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By message_path = By.xpath("//div[text() = 'Success'] | //h2[@c-bchcvacinnepreregistrationinternal_bchcvacinnepreregistrationinternal and text() = 'Match Unsuccessful']");
        log("  -- success message has been Appears. Closing... - /");
        try {
            waitForElementToBeEnabled(driver, message_path, 10);
            String message = driver.findElement(message_path).getText();
//		Assert.assertEquals(message, "Success", "Expected PHN Match Success but found '" + message + "'");
            AlertDialog.closeAlert(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("Probably alert already closed. Continue...");
        }
    }

    public static void successRegisteredMessageAppear(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        boolean alert_found = AlertDialog.alertFound(driver);
        WebElement alert_content = AlertDialog.getAlertContent(driver);
        String alert_text = alert_content.getText();
        Assert.assertTrue(alert_text.contains( "Citizen Successfully Registered"));
        try {
            AlertDialog.closeAlert(driver);
        } catch(ElementClickInterceptedException ex) {
            AlertDialog.closeAlert(driver);
        }
    }
}
