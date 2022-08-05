package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterToGetVaccinatedPage extends BasePage{

    @FindBy(xpath = "//span[@class='button-label-primary' and contains(text(), 'Register')]")
    private WebElement btnRegisterNow;

    @FindBy(xpath = "//div[@class='confirmation-number']")
    private WebElement conformationNumber;

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

        if (isIndigenous==false){
            click(radioBtnNoIndigenous);
        }
        else{
            click(radioBtnYesIndigenous);
        }
            click(btnContinueRegistration);
    }

    public void fillMandatoryFieldsOnContactInformationSection(String email, String phoneNumber) throws InterruptedException {
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
        String conformationNumberText = (conformationNumber.getText()).replaceAll("\\s+","");
        log("/*---Registration Successful message displayed, conformation number: " +conformationNumberText +" ---*/");
        return conformationNumberText;
    }

}
