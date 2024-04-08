package bcvax.pages;

import io.qameta.allure.Step;
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

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement input_username_CP;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement input_password_CP;

	@FindBy(xpath = "//button[@type = 'button']")
	private WebElement login_button_CP;

	public LoginPage(WebDriver driver) {
		super(driver);
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

	public MainPageOrg orgLoginAsPPHIS() throws Exception {
		log("/*----Login to ORG (oldUI) As PPHIS--*/");
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_PPHIS"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_PPHIS_PW"));
		click(login_button);
		return new MainPageOrg(driver);
	}

	public SupplyConsolePage loginAsImmsBCAdmin() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_IMMSBC_ADMIN"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_IMMSBC_ADMIN_PW"));
		click(login_button);
		return new SupplyConsolePage(driver);
	}

	public MainPageOrg orgLoginAsImmsBCAdmin() throws Exception {
		log("/*----Login to ORG (oldUI) as Imms BC Admin --*/");
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_IMMSBC_ADMIN_ICE"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_IMMCBC_ADMIN_ICE_PW"));
		click(login_button);
		return new MainPageOrg(driver);
	}

	public MainPageOrg orgLoginAsImmsBCAdminCP() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("api_url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_IMMSBC_ADMIN_CP"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_IMMSBC_ADMIN_CP_PW"));
		click(login_button);
		return new MainPageOrg(driver);
	}
	public Tables getTables(){
		return new Tables(driver);
	}

	public CallCenterConsolePage loginAsCalCenterAgentCC() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_CALLCENTERAGENT"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("password_CALLCENTERAGENT_PW"));
		click(login_button);
		return new CallCenterConsolePage(driver);
	}
	
	public RegisterToGetVaccinatedPage openRegisterToGetVaccinatedPage() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url_citizenportal"));
		return new RegisterToGetVaccinatedPage(driver);
	}

	public MinorAilmentsPage openMinorAilmentsPortal() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url_minorail"));
		return new MinorAilmentsPage(driver);
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

	@FindBy(xpath = "//img[@src='/bchvaxportal/profilephoto/005/T']")
	private WebElement btnProfile;

	@FindBy(xpath = "//span[@title='Log Out']")
	private WebElement logOut;

	@Step
	public MainPageCP loginIntoCommunityPortalAsImmsBCAdmin() throws Exception {
		//To be able to login as Admin into community portal (new UI) we have to use old UI url and select portal in menu
		CommonMethods common = new CommonMethods(driver);
		driver.navigate().to(Utils.getEnvConfigProperty("url_old_ui"));
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_IMMSBC_ADMIN_CP"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_IMMSBC_ADMIN_CP_PW"));
		click(login_button);
		Thread.sleep(15000);
		common.goToVaccinationPortalIfNeededAndConfirmPageIsDisplayed();
		return new MainPageCP(driver);
	}

	@Step public MainPageCP loginIntoCommunityPortalAsSysAdmin() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		Thread.sleep(2000);
		click(btnLoginHere);
		waitForElementToBeClickable(textUserName);
		textUserName.sendKeys(Utils.getEnvConfigProperty("user_SYS_ADMIN_CP"));
		textPassword.sendKeys(Utils.getEnvConfigProperty("user_SYS_ADMIN_CP_PW"));
		click(login_button);
		log("  -- Login AS " + Utils.getEnvConfigProperty("user_SYS_ADMIN_CP") +  " +--*/");
		return new MainPageCP(driver);
	}

	public void logOutCommunityPortal() throws Exception {
		click(btnProfile);
		click(logOut);
		Thread.sleep(1000);
	}

	public MainPageCP loginIntoCommunityPortalAsClinician() throws Exception {
		loginIntoCommunityPortalAs("user_CLINICIAN_CP", "password_CLINICIAN_PW_CP");
		return new MainPageCP(driver);
	}

	public void loginIntoCommunityPortalAs(String user, String pass) throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		Thread.sleep(2000);
		waitForElementToBeVisible(driver, input_username_CP, 20);
		input_username_CP.sendKeys(Utils.getEnvConfigProperty(user));
		input_password_CP.sendKeys(Utils.getEnvConfigProperty(pass));
		login_button_CP.click();
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//HEALTH GATEWAY

	@FindBy(xpath = "//button[@value='BC_Services_Card_Login']")
	private WebElement btnLoginWithBCServiceCard;

	//Next screen //BC Services Card Login
	@FindBy(xpath = "//h2[text() = 'Test with username and password']")
	private WebElement btnTestWithUserNameAndPassword;

	//Next screen //BC Services Card Login page2 USER/PASSWORD
	@FindBy(xpath = "//input[@id='username']")
	private WebElement emailOrUsernameId;  ///Work from here

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordId;

	@FindBy(xpath = "//button[text() = 'Continue']")
	private WebElement btnContinue;

	public MainPageHealthGateway openHealthGatewayPortal() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		return new MainPageHealthGateway(driver);
	}

	public void enterCredentialsForHGPortalAs(String user, String pass) throws Exception {
		click(btnTestWithUserNameAndPassword);
		Thread.sleep(1000);
		typeIn(emailOrUsernameId, Utils.getEnvConfigProperty(user));
		typeIn(passwordId, Utils.getEnvConfigProperty(pass));
		Thread.sleep(500);
		click(btnContinue);
	}

	public MainPageHealthGateway loginIntoHGWithBCServiceCardAsUser11() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		Thread.sleep(4000);
		click(btnLoginWithBCServiceCard);
		Thread.sleep(3000);
		enterCredentialsForHGPortalAs("user_HTHGTWY11","password_HTHGTWY11_PW");
		Thread.sleep(3000);
		return new MainPageHealthGateway(driver);
	}

//	private void loginWithBCServiceCard() throws InterruptedException {
//		String userName = "HTHGTWY11";
//		String password = "00098911";
//
//		click(btnLoginWithBCServiceCard);
//		Thread.sleep(3000);
//		click(btnTestWithUserNameAndPassword);
//		Thread.sleep(1000);
//		typeIn(emailOrUsernameId, userName);
//		typeIn(passwordId, password);
//		Thread.sleep(500);
//
//		click(btnContinue);
//	}

}