// Login page
package prodsuppqa.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	private final String Precondition_PRODSUPPQA = "sunil.anne@phsa.ca.prodsuppqa";
	private final String Precondition_PW_PRODSUPPQA = "Sqlserver@3";
	private final String CLINICIAN_ICE_PRODSUPPQA = "";
	private final String CLINICIAN_PW_PRODSUPPQA = "Technology1990!!!!!!!";
	private final String PPHIS_PRODSUPPQA = "auto_operationpphis@deloitte.ca.bcvaxprodsuppqa";
	private final String PPHIS_PW_PRODSUPPQA = "Technology1990!!!!!!";
	private final String CLINICIAN_CIB_PRODSUPPQA = "";
	private final String CLINICIAN_PW_CIB_PRODSUPPQA = "Technology1990!!!!!";
	private final String CLINICIAN_Consumption_PRODSUPPQA = "";
	private final String CLINICIAN_PW_Consumption_PRODSUPPQA = "Technology1990!!!!!!";
	private final String DIWA_PRODSUPPQA = "a";
	private final String DIWA_PW_PRODSUPPQA = "phsa7phsa*";
	private final String CALLCENTERAGENT_CC_PRODSUPPQA = "";
	private final String CALLCENTERAGENT_PW_CC_PRODSUPPQA = "Technology1990!!!!!!!";
	
	
	@FindBy(id = "username")
	private WebElement clinician_prodsuppqa;
	@FindBy(id = "username")
	private WebElement pphis_prodsuppqa;
	@FindBy(id = "username")
	private WebElement clinician_cib_prodsuppqa;
	@FindBy(id = "username")
	private WebElement Precondition_prodsuppqa;
	
	@FindBy(id = "username")
	private WebElement Consumption_prodsuppqa;
	
	
	@FindBy(id = "username")
	private WebElement diwa_prodsuppqa;
	
	@FindBy(id = "password")
	private WebElement clinician_pw_prodsuppqa;
	@FindBy(id = "password")
	private WebElement pphis_pw_prodsuppqa;
	@FindBy(id = "password")
	private WebElement clinician_pw_cib_prodsuppqa;
	@FindBy(id = "password")
	private WebElement Precondition_pw_prodsuppqa;
	
	@FindBy(id = "password")
	private WebElement Cosumption_pw_prodsuppqa;
	@FindBy(id = "password")
	private WebElement diwa_pw_prodsuppqa;
	
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
	
	public void enterClinicianICEUserName() {
		this.clinician_prodsuppqa.sendKeys(CLINICIAN_ICE_PRODSUPPQA);
	}
	
	public void Precondition_PRODSUPPQA() {
		this.Precondition_prodsuppqa.sendKeys(Precondition_PRODSUPPQA);
	}
	
	public void enterPPHISUserName() {
		this.pphis_prodsuppqa.sendKeys(PPHIS_PRODSUPPQA);
	}
	
	public void enterClinicianCIBUserName() {
		this.clinician_cib_prodsuppqa.sendKeys(CLINICIAN_CIB_PRODSUPPQA);
	}
	
	public void enterClinicianConUserName() {
		this.Consumption_prodsuppqa.sendKeys(CLINICIAN_Consumption_PRODSUPPQA);
	}
	
	public void enterClinicianICEPW() {
		this.clinician_pw_prodsuppqa.sendKeys(CLINICIAN_PW_PRODSUPPQA);
	}
	
	public void enterPPHIS_PW() {
		this.pphis_pw_prodsuppqa.sendKeys(PPHIS_PW_PRODSUPPQA);
	}
	
	public void enterPrecond_PW() {
		this.Precondition_pw_prodsuppqa.sendKeys(Precondition_PW_PRODSUPPQA);
	}
	
	public void enterClinicianCIBPW() {
		this.clinician_pw_cib_prodsuppqa.sendKeys(CLINICIAN_PW_CIB_PRODSUPPQA);
	}
	
	public void enterConsumption_PW() {
		this.Cosumption_pw_prodsuppqa.sendKeys(CLINICIAN_PW_Consumption_PRODSUPPQA);
	}
	
	public void pressSubmitButton() {
		this.submit_button.click();
	}
	
	public void enterDIWA_UserName() {
		this.diwa_prodsuppqa.sendKeys(DIWA_PRODSUPPQA);
	}
	
	public void enterDIWA_PW() {
		this.diwa_pw_prodsuppqa.sendKeys(DIWA_PW_PRODSUPPQA);
	}
	
	public void enterCalCenterAgentCC_UserName() {
		this.diwa_prodsuppqa.sendKeys(CALLCENTERAGENT_CC_PRODSUPPQA);
	}
	
	public void enterCalCenterAgentCC_PW() {
		this.diwa_pw_prodsuppqa.sendKeys(CALLCENTERAGENT_PW_CC_PRODSUPPQA);
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
	
	public InClinicExperiencePage loginAsClinicianICE() {
		this.enterClinicianICEUserName();
		//setUsername(username);
		this.enterClinicianICEPW();
		//return clickLoginButton();
		this.login_button.click();
		return new InClinicExperiencePage(driver);
	}
	
	public InClinicExperiencePage loginasPrecocondition() {
		this.Precondition_PRODSUPPQA();
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
	
	public CallCenterConsolePage loginAsCalCenterAgentCC() {
		this.enterCalCenterAgentCC_UserName();
		//setUsername(username);
		this.enterCalCenterAgentCC_PW();
		//return clickLoginButton();
		this.login_button.click();
		return new CallCenterConsolePage(driver);
	}
	
	
}