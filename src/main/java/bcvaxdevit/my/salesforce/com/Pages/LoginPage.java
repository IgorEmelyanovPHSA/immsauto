// Login page
package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	private final String Precondition_BCVAXDEVIT = "sunil.anne@phsa.ca.bcvaxdevit";
	private final String Precondition_PW_BCVAXDEVIT = "Sqlserver@3";
	private final String CLINICIAN_ICE_BCVAXDEVIT = "auto_clinician@deloitte.ca.bcvaxdevit";
	private final String CLINICIAN_PW_ICE_BCVAXDEVIT = "Technology1990!!!!!!!!";
	private final String PPHIS_BCVAXDEVIT = "autooperationpphis@deloitte.ca.bcvaxdevit";
	private final String PPHIS_PW_BCVAXDEVIT = "Technology1990!!!!!!!";
	private final String CLINICIAN_CIB_BCVAXDEVIT = "auto_clinician_cib@phsa.ca.bcvaxdevit";
	private final String CLINICIAN_PW_CIB_BCVAXDEVIT = "Technology1990!!!!!!";
	private final String CLINICIAN_Consumption_BCVAXDEVIT = "auto_clinician_consumption@phsa.ca.bcvaxdevit";
	private final String CLINICIAN_PW_Consumption_BCVAXDEVIT = "Technology1990!!!!!!";
	private final String DIWA_BCVAXDEVIT = "autocliniciandiwa2@phsa.ca.bcvaxdevit";
	private final String DIWA_PW_BCVAXDEVIT = "phsa*phsa7";
	private final String CALLCENTERAGENT_CC_BCVAXDEVIT = "autocallcenteragent@deloitte.ca.bcvaxdevit";
	private final String CALLCENTERAGENT_PW_CC_BCVAXDEVIT = "Technology1990!!!!!!!!";
	private final String CALLCENTERAGENT_Portal_BCVAXDEVIT = "auto_callcenter_portal@phsa.ca.bcvaxdevit";
	private final String CALLCENTERAGENT_PW_Portal_BCVAXDEVIT = "Technology1990!!!!!!";
	
	@FindBy(id = "username")
	private WebElement clinician_bcvaxdevit;
	@FindBy(id = "username")
	private WebElement pphis_bcvaxdevit;
	@FindBy(id = "username")
	private WebElement clinician_cib_bcvaxdevit;
	@FindBy(id = "username")
	private WebElement Precondition_bcvaxdevit;
	
	@FindBy(id = "username")
	private WebElement Consumption_bcvaxdevit;
	
	@FindBy(id = "username")
	private WebElement diwa_bcvaxdevit;
	
	@FindBy(id = "username")
	private WebElement clinician_wrongclinic;
	
	@FindBy(id = "password")
	private WebElement clinician_pw_wrongclinic;
	@FindBy(id = "password")
	private WebElement clinician_pw_bcvaxdevit;
	@FindBy(id = "password")
	private WebElement pphis_pw_bcvaxdevit;
	@FindBy(id = "password")
	private WebElement clinician_pw_cib_bcvaxdevit;
	@FindBy(id = "password")
	private WebElement Precondition_pw_bcvaxdevit;
	
	@FindBy(id = "password")
	private WebElement Cosumption_pw_bcvaxdevit;
	@FindBy(id = "password")
	private WebElement diwa_pw_bcvaxdevit;
	
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
		this.clinician_bcvaxdevit.sendKeys(CLINICIAN_ICE_BCVAXDEVIT);
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
	
	public void enterClinicianConUserName() {
		this.Consumption_bcvaxdevit.sendKeys(CLINICIAN_Consumption_BCVAXDEVIT);
	}
	
	public void enterClinicianICEPW() {
		this.clinician_pw_bcvaxdevit.sendKeys(CLINICIAN_PW_ICE_BCVAXDEVIT);
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
	
	public void enterConsumption_PW() {
		this.Cosumption_pw_bcvaxdevit.sendKeys(CLINICIAN_PW_Consumption_BCVAXDEVIT);
	}
	
	public void pressSubmitButton() {
		this.submit_button.click();
	}
	
	public void enterDIWA_UserName() {
		this.diwa_bcvaxdevit.sendKeys(DIWA_BCVAXDEVIT);
	}
	
	public void enterDIWA_PW() {
		this.diwa_pw_bcvaxdevit.sendKeys(DIWA_PW_BCVAXDEVIT);
	}
	
	public void enterCalCenterAgentCC_UserName() {
		this.diwa_bcvaxdevit.sendKeys(CALLCENTERAGENT_CC_BCVAXDEVIT);
	}
	
	public void enterCalCenterAgentCC_PW() {
		this.diwa_pw_bcvaxdevit.sendKeys(CALLCENTERAGENT_PW_CC_BCVAXDEVIT);
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
	
	public InClinicExperiencePage loginAsClinicianICEWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_ICE"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_CLINICIAN_PW_ICE"));
		click(login_button);
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
	
	public InClinicExperiencePage loginAsPreconditionWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_Precondition"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_PreconditionPW"));
		click(login_button);
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
	
	public SupplyConsolePage loginAsPPHISWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_PPHIS"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_PPHIS_PW"));
		click(login_button);
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
	
	public RequisitionPage loginAsPPHIS1WithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_PPHIS"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_PPHIS_PW"));
		click(login_button);
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
	
	public InClinicExperiencePage loginWithClinicianConWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_Consumption"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_Consumption"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}
	
	public InClinicExperiencePage loginWithClinicianWrongClinicWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_wrongclinic.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_WrongClinic"));
		clinician_pw_wrongclinic.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_WrongClinic"));
		click(login_button);
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
	
	public ClinicInBoxPage loginAsClinicianCIBWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_CIB"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_CLINICIAN_PW_CIB"));
		click(login_button);
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
	
	public ClinicInBoxPage loginAsDIWAWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_DIWA"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_DIWA_PW"));
		click(login_button);
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
	
	public CallCenterConsolePage loginAsCalCenterAgentCCWithParameters() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		clinician_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("user_CALLCENTERAGENT_CC"));
		clinician_pw_bcvaxdevit.sendKeys(Utils.getEnvConfigProperty("password_CALLCENTERAGENT_PW_CC"));
		click(login_button);
		return new CallCenterConsolePage(driver);
	}
	
	public RegisterToGetVaccinatedPage openRegisterToGetVaccinatedPage() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url_citizenportal"));
		return new RegisterToGetVaccinatedPage(driver);
	}
	
	public BookAnAppointmentPage openBookAnAppointmentPage(String uniqueLink) throws Exception {
		driver.navigate().to(uniqueLink);
		return new BookAnAppointmentPage(driver);
	}
	
}