package bcvax.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "username")
	private WebElement textUserName;

	@FindBy(id = "password")
	private WebElement textPassword;

	@FindBy(id = "Login")
	private WebElement login_button;
	
	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	private WebElement submit_button;
	
	@FindBy(xpath = "//div[contains(text(),'The form was successfully submitted!')]")
	private WebElement alertSuccess;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public InClinicExperiencePage loginAsClinicianICE() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_ICE"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_CLINICIAN_PW_ICE"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}

	public InClinicExperiencePage loginAsClinicianICEUserDefaults() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_UserDefaults"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_UserDefaults"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}

	public InClinicExperiencePage loginAsClinician_DIWA_ICE() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_DIWA_ICE"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_DIWA_ICE_PW"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}
	
	public InClinicExperiencePage loginAsPrecondition() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_Precondition"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_PreconditionPW"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}
	
	public SupplyConsolePage loginAsPPHIS() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_PPHIS"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_PPHIS_PW"));
		click(login_button);
		return new SupplyConsolePage(driver);
	}

	public RequisitionPage loginAsPPHIS1() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_PPHIS"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_PPHIS_PW"));
		click(login_button);
		return new RequisitionPage(driver);
	}
	
	public InClinicExperiencePage loginWithClinicianCon() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_Consumption"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_Consumption"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}
	
	public InClinicExperiencePage loginWithClinicianWrongClinic() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_WrongClinic"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_WrongClinic"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}
	
	public ClinicInBoxPage loginAsClinicianCIB() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_CIB"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_CLINICIAN_PW_CIB"));
		click(login_button);
		return new ClinicInBoxPage(driver);
	}
	
	public ClinicInBoxPage loginAsClinicianDIWACIB() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_DIWA_CIB"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_DIWA_CIB_PW"));
		click(login_button);
		return new ClinicInBoxPage(driver);
	}

	public CallCenterConsolePage loginAsCalCenterAgentCC() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CALLCENTERAGENT_CC"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_CALLCENTERAGENT_PW_CC"));
		click(login_button);
		return new CallCenterConsolePage(driver);
	}
	
	public RegisterToGetVaccinatedPage openRegisterToGetVaccinatedPage() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url_citizenportal"));
		return new RegisterToGetVaccinatedPage(driver);
	}

	public ClinicInBoxPage loginAsClinicianPneumo() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_Pneumo_ICE"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_Pneumo_ICE"));
		click(login_button);
		return new ClinicInBoxPage(driver);
	}

	public InClinicExperiencePage loginAsClinicianInfluenzaICE() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_Influenza_ICE"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_Influenza_ICE"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}

	public InClinicExperiencePage loginAsClinicianPneumoICE() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_Pneumo_ICE"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_CLINICIAN_PW_Pneumo_ICE"));
		click(login_button);
		return new InClinicExperiencePage(driver);
	}
	
	public BookAnAppointmentPage openBookAnAppointmentPage(String uniqueLink) throws Exception {
		driver.navigate().to(uniqueLink);
		return new BookAnAppointmentPage(driver);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Community Portal section //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@FindBy(xpath = "//a[@class='inverseTextColor' and contains(text(),'Login here')]")
	private WebElement btnLoginHere;

	public CommunityPortalMainPage loginIntoCommunityPortalAsAdmin() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		Thread.sleep(2000);
		click(btnLoginHere);
		waitForElementToBeClickable(textUserName);
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_ADMIN_CP"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_ADMIN_CP_PW"));
		click(login_button);
		return new CommunityPortalMainPage(driver);
	}

	public CommunityPortalMainPage loginIntoCommunityPortalAsPPHIS() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		Thread.sleep(2000);
		click(btnLoginHere);
		waitForElementToBeClickable(textUserName);
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_PPHIS"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_PPHIS_PW"));
		click(login_button);
		return new CommunityPortalMainPage(driver);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}