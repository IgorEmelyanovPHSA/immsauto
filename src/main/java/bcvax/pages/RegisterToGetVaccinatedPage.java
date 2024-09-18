package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.ParseException;
import java.util.Map;

public class RegisterToGetVaccinatedPage extends BasePage{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Registration information section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "//input[@class='inputclassRadio' and @value='Yes']")
    private WebElement radioBtnYesIndigenous;

    @FindBy(xpath = "//input[@class='inputclassRadio' and @value='No']")
    private WebElement radioBtnNoIndigenous;



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Contact information section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Review and submit section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//input[@name='DDH_HC_Patient_Consent']")
    private WebElement checkBoxCertify;

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement btnSubmit;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public RegisterToGetVaccinatedPage(WebDriver driver) {super(driver);}

    public static void clickBtnRegisterNow(WebDriver driver) throws InterruptedException {
        By btn_register_now_path = By.xpath("//button[text()='Register Now'] | //span[text()='Register now']/..");
        waitForElementToBeEnabled(driver, btn_register_now_path, 10);
        WebElement btnRegisterNow = driver.findElement(btn_register_now_path);
        scrollCenter(driver, btnRegisterNow);
        Thread.sleep(1000);
        btnRegisterNow.click();
    }

    public static void fillMandatoryFieldsOnRegistrationSection(WebDriver driver, Map<String, String> client_data) throws InterruptedException, ParseException {
        By legal_first_name_path = By.xpath("//input[@name='FirstName']");
        waitForElementToBeEnabled(driver, legal_first_name_path, 10);
        WebElement legal_first_name = driver.findElement(legal_first_name_path);
        legal_first_name.sendKeys(client_data.get("legalFirstName"));

        By legal_last_name_path = By.xpath("//input[@name='LastName']");
        waitForElementToBeEnabled(driver, legal_last_name_path, 10);
        WebElement legal_last_name = driver.findElement(legal_last_name_path);
        legal_last_name.sendKeys(client_data.get("legalLastName"));

        By legal_middle_name_path = By.xpath("//input[@name='MiddleName']");
        waitForElementToBeEnabled(driver, legal_middle_name_path, 10);
        WebElement legal_middle_name = driver.findElement(legal_middle_name_path);
        legal_middle_name.sendKeys(client_data.get("legalMiddleName"));

        By preferred_name_path = By.xpath("//input[@name='HealthCloudGA_PreferredName']");
        waitForElementToBeEnabled(driver, preferred_name_path, 10);
        WebElement preferred_name = driver.findElement(preferred_name_path);

        By date_of_birth_path = By.xpath("//input[@name='PersonBirthdate']");
        waitForElementToBeEnabled(driver, date_of_birth_path, 10);
        WebElement date_of_birth = driver.findElement(date_of_birth_path);
        date_of_birth.sendKeys(Utils.convertDate(client_data.get("dateOfBirth"),"MMM dd, yyyy"));

        By postal_code_path = By.xpath("//input[@name='DDH_HC_Zip_Code']");
        waitForElementToBeEnabled(driver, postal_code_path, 10);
        WebElement postal_code = driver.findElement(postal_code_path);
        postal_code.sendKeys(client_data.get("postalCode"));

        By personal_health_number_path = By.xpath("//input[@name='HC_Personal_Health_Number']");
        waitForElementToBeEnabled(driver, personal_health_number_path, 10);
        WebElement personal_health_number = driver.findElement(personal_health_number_path);
        personal_health_number.sendKeys(client_data.get("personalHealthNumber"));

        By continue_registration_btn_path = By.xpath("(//button[contains(text(),'Continue')])[1]");
        WebElement continue_registration_btn = driver.findElement(continue_registration_btn_path);
        continue_registration_btn.click();

        By success_error_msg_path = By.xpath("//div[@class='toastTitle slds-text-heading--small' and text()='Error']");
        try {
            waitForElementToBeLocated(driver, success_error_msg_path, 2);
            WebElement success_error_msg = driver.findElement(success_error_msg_path);
            Assert.assertFalse(success_error_msg.getText().equals("Error"), "Registration not Successful...");
        } catch(Exception ex) {
            System.out.println("No Registration Errors. Continue...");
        }
    }

    public static void fillMandatoryFieldsOnContactInformationSection(WebDriver driver, Map<String, String> client_data) throws InterruptedException {
        Thread.sleep(500);
        By email_path = By.xpath("//input[@name='PersonEmail']");
        waitForElementToBeEnabled(driver, email_path, 10);
        WebElement email = driver.findElement(email_path);
        try {
            email.sendKeys(client_data.get("email"));
        } catch(ElementNotInteractableException ex) {
            Thread.sleep(2000);
            email.sendKeys(client_data.get("email"));
        }

        By email_confirm_path = By.xpath("//input[@name='ConfirmEmail']");
        waitForElementToBeEnabled(driver, email_confirm_path, 10);
        WebElement email_confirm = driver.findElement(email_confirm_path);
        email_confirm.sendKeys(client_data.get("email"));

        By phone_num_path = By.xpath("//input[@name='PersonMobilePhone']");
        WebElement phone_number = driver.findElement(phone_num_path);
        phone_number.sendKeys(client_data.get("phone"));

        By phone_num_confirm_path = By.xpath("//input[@name='ConfirmPhone']");
        WebElement phone_num_confirm = driver.findElement(phone_num_confirm_path);
        phone_num_confirm.sendKeys(client_data.get("phone"));

        By continue_btn_path = By.xpath("(//button[contains(text(),'Continue')])[2]");
        WebElement continue_btn = driver.findElement(continue_btn_path);
        continue_btn.click();
    }

    public void certifyAndSubmit() throws InterruptedException {
        click(checkBoxCertify);
        click(btnSubmit);
    }

    public String registrationSuccessfulPageDisplayed () {
        By successMessageId = By.xpath("//div[text() = 'Registration successful!']");
        try {
            waitForElementToBeLocated(driver, successMessageId, 10);
        } catch (Exception e){
            log("/*---Registration is NOT Successful, check for duplicates ---*/");
        }
        By confirmation_number_path = By.xpath("//div[@class='confirmation-number']");
        waitForElementToBeLocated(driver, confirmation_number_path, 10);
        WebElement confirmation_number = driver.findElement(confirmation_number_path);
        String confirmation_number_value = (confirmation_number.getText()).replaceAll("\\s+","");
        log("/*---Registration Successful message displayed, conformation number: " + confirmation_number_value +" ---*/");
        return confirmation_number_value;
    }

}
