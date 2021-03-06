// Login page
package prodsuppqa.my.salesforce.com.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private final String CLINICIAN_PRODSUPPQA = "clinician_igor1@bcvaxprodsuppqa.ca";
    private final String CLINICIAN_PW_PRODSUPPQA = "Technology1990!!!!!";

    @FindBy(id = "username")
    private WebElement clinician_prodsuppqa;

    @FindBy(id = "password")
    private WebElement clinician_pw_prodsuppqa;

    @FindBy(id = "Login")
    private WebElement login_button;

    @FindBy(xpath = "//a[contains(text(),'Submit')]")
    private WebElement submit_button;

    @FindBy(xpath = "//div[contains(text(),'The form was successfully submitted!')]")
    private WebElement alertSuccess;

    //private By clinicianField = By.id("username");
    //private By passwordField = By.id("password");
    //private By loginButton = By.id("Login");



    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterClinicianUserName(){
        this.clinician_prodsuppqa.sendKeys(CLINICIAN_PRODSUPPQA);
    }

    public void enterClinicianPW(){
        this.clinician_pw_prodsuppqa.sendKeys(CLINICIAN_PW_PRODSUPPQA);
    }

    public void pressSubmitButton(){
        this.submit_button.click();
    }

    //public void setUsername (String username){
      //  type(username,clinicianField);
    //}

    //public void setPassword (String password){
      //  type(password,passwordField);
    //}

    //public void clickLoginButton(){
        //this.login_button.click();
    //}

    public InClinicExperiencePage clickLoginButton(){
        this.login_button.click();
        return new InClinicExperiencePage(driver);
    }

    public InClinicExperiencePage loginWith() {
        this.enterClinicianUserName();
        //setUsername(username);
        this.enterClinicianPW();
        //return clickLoginButton();
        this.login_button.click();
        return new InClinicExperiencePage(driver);
    }

    public void verifyAlertSuccess(){
        this.alertSuccess.isDisplayed();
    }



}