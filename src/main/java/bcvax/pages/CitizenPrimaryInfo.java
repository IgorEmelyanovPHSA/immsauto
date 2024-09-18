package bcvax.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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

    public static void enterMiddleName(WebDriver driver, String middlename) throws InterruptedException {
        Thread.sleep(500);
        By middle_name_field_path = By.xpath("//input[@name = 'MiddleName']");
        waitForElementToBeEnabled(driver, middle_name_field_path, 10);
        WebElement last_name_field = driver.findElement(middle_name_field_path);
        last_name_field.sendKeys(middlename);
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
        By postal_code_field_path = By.xpath("//input[@name = 'DDH_HC_Zip_Code' or @name='postalCode']");
        waitForElementToBeEnabled(driver, postal_code_field_path, 10);
        WebElement postal_code_field = driver.findElement(postal_code_field_path);
        postal_code_field.sendKeys(postalcode);
    }

    public static void enterPHN(WebDriver driver, String phn_number) throws InterruptedException {
        Thread.sleep(500);
        By phn_field_path = By.xpath("//input[@name = 'HC_Personal_Health_Number' or @name='phn']");
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
        scrollCenter(driver, next_btn);
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
        By email_field_path = By.xpath("//input[@name = 'PersonEmail' or @name='personEmail']");
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
        By confirm_email_field_path = By.xpath("//input[@name = 'ConfirmEmail' or @name='confirmEmail']");
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

    public static String successMessageAppear(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By message_path = By.xpath("//div[text() = 'Success'] | //h2[@c-bchcvacinnepreregistrationinternal_bchcvacinnepreregistrationinternal and text() = 'Match Unsuccessful']");
        log("  -- success message has been Appears. Closing... - /");
        String message = "";
        try {
            waitForElementToBeEnabled(driver, message_path, 10);
            message = driver.findElement(message_path).getText();
//		Assert.assertEquals(message, "Success", "Expected PHN Match Success but found '" + message + "'");
            AlertDialog.closeAlert(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("Probably alert already closed. Continue...");
            return ex.getMessage();
        }
        return message;
    }

    public static void successRegisteredMessageAppear(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        boolean alert_found = AlertDialog.alertFound(driver);
        //WebElement alert_content = AlertDialog.getAlertContent(driver);
        //String alert_text = alert_content.getText();
        List<String> all_alert_texts = AlertDialog.getAllAlertsText(driver);
        String all_alert_text_joined = String.join("//n", all_alert_texts);
        //Assert.assertTrue(alert_text.contains( "Citizen Successfully Registered"), "Expected Alert text: Citizen Successfully Registered; Actual Alert Text: " + alert_text);
        Assert.assertTrue((all_alert_text_joined.contains("Citizen Successfully Registered") | all_alert_text_joined.contains("Registration Confirmation Number can now be shared with Citizen over phone")), "Expected Alert text: Citizen Successfully Registered; Actual Alert Text: " + all_alert_texts);
        try {
            AlertDialog.closeAlert(driver);
        } catch(ElementClickInterceptedException ex) {
            Thread.sleep(1000);
            AlertDialog.closeAlert(driver);
        }
    }

    public static void fillUpRegistrationForm(WebDriver driver, Map<String, String> client_data) throws InterruptedException, ParseException {
        System.out.println("/*5.----Enter First Name " + client_data.get("legalFirstName") + "--*/");
        boolean first_name_field_found = false;
        //////// No need ///////
//        try {
//            CitizenPrimaryInfo.enterFirstName(driver, client_data.get("legalFirstName"));
//            first_name_field_found = true;
//        } catch(NotFoundException ex) {
//            first_name_field_found = false;
//        }
        System.out.println("/*7.----Enter Postal code " + client_data.get("postalCode") + "--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, client_data.get("postalCode"));
        System.out.println("/*8.----Enter PHN " + client_data.get("personalHealthNumber") + "--*/");
        CitizenPrimaryInfo.enterPHN(driver, client_data.get("personalHealthNumber"));
        if(first_name_field_found) {
            System.out.println("/*6.----Enter Last Name " + client_data.get("legalLastName") + "--*/");
            CitizenPrimaryInfo.enterLastName(driver, client_data.get("legalLastName"));
            System.out.println("/*6.----Enter Middle Name " + client_data.get("legalMiddleName") + "--*/");
            CitizenPrimaryInfo.enterMiddleName(driver, client_data.get("legalMiddleName"));
            System.out.println("/*6.----Enter Date of birth " + Utils.convertDate(client_data.get("dateOfBirth"), "MMM dd, yyyy") + "--*/");
            CitizenPrimaryInfo.enterDateOfBirth(driver, Utils.convertDate(client_data.get("dateOfBirth"), "MMM dd, yyyy"));

            System.out.println("/*9.----click on non-Indigenous person radiobutton --*/");
            System.out.println("/*10.----click Verify PHN button --*/");
            CitizenPrimaryInfo.clickVerifyPHNButton(driver);
            System.out.println("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
            CitizenPrimaryInfo.successMessageAppear(driver);
            System.out.println("/*12.----click Next button --*/");
        }
        CitizenPrimaryInfo.clickNextButton(driver);
        System.out.println("/*13.'Enter email address " + client_data.get("email") + "--*/");
        try {
            CitizenPrimaryInfo.enterEmail(driver, client_data.get("email"));
        } catch(ElementNotInteractableException ex) {
            Thread.sleep(2000);
            CitizenPrimaryInfo.enterEmail(driver, client_data.get("email"));
        }
        catch(NotFoundException ex) {
            Thread.sleep(2000);
            CitizenPrimaryInfo.enterEmail(driver, client_data.get("email"));
        }
        System.out.println("/*14.'Confirm email address " + client_data.get("email") + "--*/");
        CitizenPrimaryInfo.confirmEmail(driver, client_data.get("email"));
        System.out.println("/*15.Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        System.out.println("/*16.Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        System.out.println("/*17.--toast success message - 'Success' --*/");
        try {
            CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        } catch (NotFoundException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        }
    }
}
