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

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement input_username_CP;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement input_password_CP;

	@FindBy(xpath = "//button[@type = 'button']")
	private WebElement login_button_CP;

	public LoginPage(WebDriver driver) {
		super(driver);
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
		login_button.click();
		return new SupplyConsolePage(driver);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Community Portal section //
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
}