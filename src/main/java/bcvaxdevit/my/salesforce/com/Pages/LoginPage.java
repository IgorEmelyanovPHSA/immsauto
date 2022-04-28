// Login page
package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	private final String Precondition_BCVAXDEVIT = "sunil.anne@phsa.ca.bcvaxdevit";
	private final String Precondition_PW_BCVAXDEVIT = "Sqlserver@3";
	private final String CLINICIAN_BCVAXDEVIT = "auto_clinician@deloitte.ca.bcvaxdevit";
	private final String CLINICIAN_PW_BCVAXDEVIT = "Technology1990!!!!!";
	private final String PPHIS_BCVAXDEVIT = "autooperationpphis@deloitte.ca.bcvaxdevit";
	private final String PPHIS_PW_BCVAXDEVIT = "Technology1990!!!!!";
	private final String CLINICIAN_CIB_BCVAXDEVIT = "auto_clinician_cib@phsa.ca.bcvaxdevit";
	private final String CLINICIAN_PW_CIB_BCVAXDEVIT = "Technology1990!!!!!";
	
	@FindBy(id = "username")
	private WebElement clinician_bcvaxdevit;
	@FindBy(id = "username")
	private WebElement pphis_bcvaxdevit;
	@FindBy(id = "username")
	private WebElement clinician_cib_bcvaxdevit;
	@FindBy(id = "username")
	private WebElement Precondition_bcvaxdevit;
	
	@FindBy(id = "password")
	private WebElement clinician_pw_bcvaxdevit;
	@FindBy(id = "password")
	private WebElement pphis_pw_bcvaxdevit;

	@FindBy(id = "password")
	private WebElement clinician_pw_cib_bcvaxdevit;
	@FindBy(id = "password")
	private WebElement Precondition_pw_bcvaxdevit;
	
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
		this.clinician_bcvaxdevit.sendKeys(CLINICIAN_BCVAXDEVIT);
	}
	
	public void Precondition_BCVAXDEVIT() {
		this.Precondition_bcvaxdevit.sendKeys(Precondition_BCVAXDEVIT);
	}
	
	public void enterPPHISUserName() {
		this.pphis_bcvaxdevit.sendKeys(PPHIS_BCVAXDEVIT);
	}
	
	public void enterClinicianCIBUserName() {
		this.clinician_cib_bcvaxdevit.sendKeys(CLINICIAN_CIB_BCVAXDEVIT);
	}
	
	public void enterClinicianPW() {
		this.clinician_pw_bcvaxdevit.sendKeys(CLINICIAN_PW_BCVAXDEVIT);
	}
	
	public void enterPPHIS_PW() {
		this.pphis_pw_bcvaxdevit.sendKeys(PPHIS_PW_BCVAXDEVIT);
	}
	
	public void enterPrecond_PW() {
		this.Precondition_pw_bcvaxdevit.sendKeys(Precondition_PW_BCVAXDEVIT);
	}
	
	public void enterClinicianCIBPW() {
		this.clinician_pw_cib_bcvaxdevit.sendKeys(CLINICIAN_PW_CIB_BCVAXDEVIT);
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
		this.Precondition_BCVAXDEVIT();
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
	public ClinicInBoxPage loginAsDIWA() {
		this.enterClinicianCIBUserName();
		//setUsername(username);
		this.enterClinicianCIBPW();
		//return clickLoginButton();
		this.login_button.click();
		return new ClinicInBoxPage(driver);
	}

	
	
}