package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterToGetVaccinatedPage extends BasePage{

    @FindBy(xpath = "//span[@class='button-label-primary' and contains(text(), 'Register')]")
    private WebElement btnRegisterNow;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Registration information section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//input[@name='FirstName']")
    private WebElement textLegalFirstName;

    @FindBy(xpath = "//input[@name='LastName']")
    private WebElement textLegalLastName;

    @FindBy(xpath = "//input[@name='MiddleName']")
    private WebElement textLegaMiddleName;

    @FindBy(xpath = "//input[@name='HealthCloudGA_PreferredName']")
    private WebElement textPreferredName;

    @FindBy(xpath = "//input[@name='PersonBirthdate']")
    private WebElement textDateOfBirth;

    @FindBy(xpath = "//input[@name='DDH_HC_Zip_Code']")
    private WebElement textPostalCode;

    @FindBy(xpath = "//input[@name='HC_Personal_Health_Number']")
    private WebElement textPersonalHealthNumber;

    @FindBy(xpath = "//input[@class='inputclassRadio' and @value='Yes']")
    private WebElement radioBtnYesIndigenous;

    @FindBy(xpath = "//input[@class='inputclassRadio' and @value='No']")
    private WebElement radioBtnNoIndigenous;

    @FindBy(xpath = "(//button[contains(text(),'Continue')])[1]")
    private WebElement btnContinueRegistration;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Contact information section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//input[@name='PersonEmail']")
    private WebElement textEmail;

    @FindBy(xpath = "//input[@name='ConfirmEmail']")
    private WebElement textEmailConfirm;

    @FindBy(xpath = "//input[@name='PersonMobilePhone']")
    private WebElement textPhoneNumber;

    @FindBy(xpath = "//input[@name='ConfirmPhone']")
    private WebElement textPhoneNumberConfirm;

    @FindBy(xpath = "(//button[contains(text(),'Continue')])[2]")
    private WebElement btnContinueContact;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Review and submit section //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FindBy(xpath = "//input[@name='DDH_HC_Patient_Consent']")
    private WebElement checkBoxCertify;

    @FindBy(xpath = "//button[contains(text(),'Edit')]")
    private WebElement btnEdit;

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement btnSubmit;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public RegisterToGetVaccinatedPage(WebDriver driver) {super(driver);}

    public void clickBtnRegisterNow() throws InterruptedException {
        click(btnRegisterNow);
    }

    public void fillMandatoryFieldsOnRegistrationSection(String firstName, String lastName, String middleName, String dob, String postalCode,
                                                         String phn, boolean isIndigenous) throws InterruptedException {
        typeIn(textLegalFirstName, firstName);
        typeIn(textLegalLastName, lastName);
        typeIn(textLegaMiddleName, middleName);
        typeIn(textDateOfBirth, dob);
        typeIn(textPostalCode, postalCode);
        typeIn(textPersonalHealthNumber, phn);
        String nonIndigenousDialog = "";
        try {
            nonIndigenousDialog = Utils.getEnvConfigProperty("nonIndigenousDialog");
        } catch(Exception ex) {
            nonIndigenousDialog = "";
        }
        if(nonIndigenousDialog.equals("yes")) {
            if (isIndigenous == false) {
                click(radioBtnNoIndigenous);
            } else {
                click(radioBtnYesIndigenous);
            }
        }
        click(btnContinueRegistration);
        By success_error_msg_path = By.xpath("//div[@class='toastTitle slds-text-heading--small' and text()='Error']");
        try {
            waitForElementToBeLocated(driver, success_error_msg_path, 2);
            WebElement success_error_msg = driver.findElement(success_error_msg_path);
            Assert.assertFalse(success_error_msg.getText().equals("Error"), "Registration not Successful...");
        } catch(Exception ex) {
            System.out.println("No Registration Errors. Continue...");
        }

    }

    public void fillMandatoryFieldsOnContactInformationSection(String email, String phoneNumber) throws InterruptedException {
        Thread.sleep(500);
        typeIn(textEmail, email);
        typeIn(textEmailConfirm, email);
        typeIn(textPhoneNumber, phoneNumber);
        typeIn(textPhoneNumberConfirm, phoneNumber);
        click(btnContinueContact);
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
