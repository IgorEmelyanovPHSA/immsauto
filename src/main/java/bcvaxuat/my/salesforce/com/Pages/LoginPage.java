// Login page
package bcvaxuat.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	private final String Precondition_BCVAXUAT = "sunil.anne@phsa.ca.bcvaxuat";
	private final String Precondition_PW_BCVAXUAT = "Sqlserver@2";
	private final String CLINICIAN_BCVAXUAT = "ADD REQUIRED USERNAME";
	private final String CLINICIAN_PW_BCVAXUAT = "Technology1990!!!!!";
	private final String PPHIS_BCVAXUAT = "ADD REQUIRED USERNAME";
	private final String PPHIS_PW_BCVAXUAT = "Technology1990!!!!!";
	private final String CLINICIAN_CIB_BCVAXUAT = "ADD REQUIRED USERNAME";
	private final String CLINICIAN_PW_CIB_BCVAXUAT = "Technology1990!!!!!";
	
	@FindBy(id = "username")
	private WebElement clinician_bcvaxuat;
	@FindBy(id = "username")
	private WebElement pphis_bcvaxuat;
	@FindBy(id = "username")
	private WebElement clinician_cib_bcvaxuat;
	@FindBy(id = "username")
	private WebElement Precondition_bcvaxuat;
	
	@FindBy(id = "password")
	private WebElement clinician_pw_bcvaxuat;
	@FindBy(id = "password")
	private WebElement pphis_pw_bcvaxuat;
	
	@FindBy(id = "password")
	private WebElement clinician_pw_cib_bcvaxuat;
	@FindBy(id = "password")
	private WebElement Precondition_pw_bcvaxuat;
	
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
	
	public void enterClinicianUserName() {
		this.clinician_bcvaxuat.sendKeys(CLINICIAN_BCVAXUAT);
	}
	
	public void Precondition_BCVAXUAT() {
		this.Precondition_bcvaxuat.sendKeys(Precondition_BCVAXUAT);
	}
	
	public void enterPPHISUserName() {
		this.pphis_bcvaxuat.sendKeys(PPHIS_BCVAXUAT);
	}
	
	public void enterClinicianCIBUserName() {
		this.clinician_cib_bcvaxuat.sendKeys(CLINICIAN_CIB_BCVAXUAT);
	}
	
	public void enterClinicianPW() {
		this.clinician_pw_bcvaxuat.sendKeys(CLINICIAN_PW_BCVAXUAT);
	}
	
	public void enterPPHIS_PW() {
		this.pphis_pw_bcvaxuat.sendKeys(PPHIS_PW_BCVAXUAT);
	}
	
	public void enterPrecond_PW() {
		this.Precondition_pw_bcvaxuat.sendKeys(Precondition_PW_BCVAXUAT);
	}
	
	public void enterClinicianCIBPW() {
		this.clinician_pw_cib_bcvaxuat.sendKeys(CLINICIAN_PW_CIB_BCVAXUAT);
	}
	
	public void pressSubmitButton() {
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
	
	public InClinicExperiencePage clickLoginButton() {
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
	
	public InClinicExperiencePage loginasPrecocondition() {
		this.Precondition_BCVAXUAT();
		//setUsername(username);
		this.enterPrecond_PW();
		//return clickLoginButton();
		this.login_button.click();
		return new InClinicExperiencePage(driver);
	}
	
	public SupplyConsolePage loginAsPPHIS() {
		this.enterPPHISUserName();
		//setUsername(username);
		this.enterPPHIS_PW();
		//return clickLoginButton();
		this.login_button.click();
		return new SupplyConsolePage(driver);
	}
	
	public RequisitionPage loginAsPPHIS1() {
		this.enterPPHISUserName();
		//setUsername(username);
		this.enterPPHIS_PW();
		//return clickLoginButton();
		this.login_button.click();
		return new RequisitionPage(driver);
	}
	
	
	public void verifyAlertSuccess() {
		this.alertSuccess.isDisplayed();
	}
	
	public ClinicInBoxPage loginAsClinicianCIB() {
		this.enterClinicianCIBUserName();
		//setUsername(username);
		this.enterClinicianCIBPW();
		//return clickLoginButton();
		this.login_button.click();
		return new ClinicInBoxPage(driver);
	}
	
	
}