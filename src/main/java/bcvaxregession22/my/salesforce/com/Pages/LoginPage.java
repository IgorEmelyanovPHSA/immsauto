// Login page
package bcvaxregession22.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	private final String Precondition_BCVAXRESSION22 = "sunil.anne@phsa.ca.regression";
	private final String Precondition_PW_BCVAXRESSION22 = "Sqlserver@1";
	private final String CLINICIAN_BCVAXRESSION22 = "auto_clinician@deloitte.ca.regression";
	private final String CLINICIAN_PW_BCVAXRESSION22 = "Technology1990!!!!!!!";
	private final String PPHIS_BCVAXRESSION22 = "autooperationpphis@deloitte.ca.bcvax";
	private final String PPHIS_PW_BCVAXRESSION22 = "Technology1990!!!!!!";
	private final String CLINICIAN_CIB_BCVAXRESSION22 = "auto_clinician_cib@phsa.ca.bcvax";
	private final String CLINICIAN_PW_CIB_BCVAXRESSION22 = "Technology1990!!!!!";
	private final String CLINICIAN_Consumption_BCVAXRESSION22 = "auto_clinician_consumption@phsa.ca.bcvax";
	private final String CLINICIAN_PW_Consumption_BCVAXRESSION22 = "Technology1990!!!!!!";
	private final String DIWA_BCVAXRESSION22 = "jason.yulghun@phsa.ca.bcvax";
	private final String DIWA_PW_BCVAXRESSION22 = "phsa*phsa7";
	
	@FindBy(id = "username")
	private WebElement clinician_bcvaxregression22;
	@FindBy(id = "username")
	private WebElement pphis_bcvaxregression22;
	@FindBy(id = "username")
	private WebElement clinician_cib_bcvaxregression22;
	@FindBy(id = "username")
	private WebElement Precondition_bcvaxregression22;
	@FindBy(id = "username")
	private WebElement Consumption_bcvaxregression22;
	@FindBy(id = "username")
	private WebElement diwa_bcvaxregression22;
	
	@FindBy(id = "password")
	private WebElement clinician_pw_bcvaxregression22;
	@FindBy(id = "password")
	private WebElement pphis_pw_bcvaxregression22;
	@FindBy(id = "password")
	private WebElement clinician_pw_cib_bcvaxregression22;
	@FindBy(id = "password")
	private WebElement Precondition_pw_bcvaxregression22;
	@FindBy(id = "password")
	private WebElement Cosumption_pw_bcvaxregression22;
	@FindBy(id = "password")

	private WebElement diwa_pw_bcvaxregression22;
	
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
		this.clinician_bcvaxregression22.sendKeys(CLINICIAN_BCVAXRESSION22);
	}
	
	public void Precondition_BCVAXREGRESSION22() {
		this.Precondition_bcvaxregression22.sendKeys(Precondition_BCVAXRESSION22);
	}
	
	public void enterPPHISUserName() {
		this.pphis_bcvaxregression22.sendKeys(PPHIS_BCVAXRESSION22);
	}
	
	public void enterClinicianCIBUserName() {
		this.clinician_cib_bcvaxregression22.sendKeys(CLINICIAN_CIB_BCVAXRESSION22);
	}
	
	public void enterClinicianConUserName() {
		this.Consumption_bcvaxregression22.sendKeys(CLINICIAN_Consumption_BCVAXRESSION22);
	}
	
	public void enterClinicianPW() {
		this.clinician_pw_bcvaxregression22.sendKeys(CLINICIAN_PW_BCVAXRESSION22);
	}
	
	public void enterPPHIS_PW() {
		this.pphis_pw_bcvaxregression22.sendKeys(PPHIS_PW_BCVAXRESSION22);
	}
	
	public void enterPrecond_PW() {
		this.Precondition_pw_bcvaxregression22.sendKeys(Precondition_PW_BCVAXRESSION22);
	}
	
	public void enterClinicianCIBPW() {
		this.clinician_pw_cib_bcvaxregression22.sendKeys(CLINICIAN_PW_CIB_BCVAXRESSION22);
	}
	
	public void enterConsumption_PW() {
		this.Cosumption_pw_bcvaxregression22.sendKeys(CLINICIAN_PW_Consumption_BCVAXRESSION22);
	}
	
	public void pressSubmitButton() {
		this.submit_button.click();
	}

	public void enterDIWA_UserName(){this.diwa_bcvaxregression22.sendKeys(DIWA_BCVAXRESSION22);}

	public void enterDIWA_PW(){this.diwa_pw_bcvaxregression22.sendKeys(DIWA_PW_BCVAXRESSION22);}
	
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
		this.Precondition_BCVAXREGRESSION22();
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
	
	public InClinicExperiencePage loginWithClinicianCon() {
		this.enterClinicianConUserName();
		//setUsername(username);
		this.enterConsumption_PW();
		//return clickLoginButton();
		this.login_button.click();
		return new InClinicExperiencePage(driver);
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
		this.enterDIWA_UserName();
		//setUsername(username);
		this.enterDIWA_PW();
		//return clickLoginButton();
		this.login_button.click();
		return new ClinicInBoxPage(driver);
	}
	
	
}