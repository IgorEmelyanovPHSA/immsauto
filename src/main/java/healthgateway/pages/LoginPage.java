package healthgateway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

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

	@FindBy(xpath = "//span[contains(text(),'I agree to the Terms of Service above')]")
	private WebElement checkBoxIAgreeToTheTermsOfService;

	public MainPageHealthGateway openHealthGatewayPortal() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		return new MainPageHealthGateway(driver);
	}

	public void enterCredentialsForHGPortalAs(String user, String pass) throws Exception {
		//	click(btnTestWithUserNameAndPassword);
		//	Thread.sleep(1000);
		typeIn(emailOrUsernameId, Utils.getEnvConfigProperty(user));
		typeIn(passwordId, Utils.getEnvConfigProperty(pass));
		Thread.sleep(500);
		click(btnContinue);
	}

	public MainPageHealthGateway loginIntoHGWithBCServiceCardAsUser11() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		if (Utils.getEnvConfigProperty("url").equalsIgnoreCase("https://uat-beta.healthgateway.gov.bc.ca/s/")) {
			Thread.sleep(4000);
			click(btnLoginWithBCServiceCard);
		}
		Thread.sleep(4000);
		click(btnTestWithUserNameAndPassword);
		Thread.sleep(3000);
		enterCredentialsForHGPortalAs("user_HTHGTWY11", "password_HTHGTWY11_PW");
	//	Temp fix July 22, 2024
		Thread.sleep(10000);
		By xpathUpdateTermsAndConditionsBannerPresentXpath = By.xpath("//h2[contains(text(),'Update to our Terms of Service')]");
		List<WebElement> TAndCBanner = driver.findElements(xpathUpdateTermsAndConditionsBannerPresentXpath);
		if (TAndCBanner.size() == 1) {
			click(checkBoxIAgreeToTheTermsOfService);
			Thread.sleep(1000);
			click(btnContinue);
		}
		///
//		//	Thread.sleep(15000);
		wait.until(ExpectedConditions.titleIs("Home"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title = 'Timeline']")));
		return new MainPageHealthGateway(driver);
	}

	public MainPageHealthGateway loginIntoHGWithBCServiceCardAsUser14() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		if (Utils.getEnvConfigProperty("url").equalsIgnoreCase("https://uat-beta.healthgateway.gov.bc.ca/s/")) {
			Thread.sleep(4000);
			click(btnLoginWithBCServiceCard);
		}
//		//Temp fix July 23, 2024 after env refresh
//		Thread.sleep(4000);
//		click(btnLoginWithBCServiceCard);
//		///
		Thread.sleep(4000);
		click(btnTestWithUserNameAndPassword);
		Thread.sleep(3000);
		enterCredentialsForHGPortalAs("user_HTHGTWY14", "password_HTHGTWY14_PW");
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.titleIs("Home"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title = 'Timeline']")));
		return new MainPageHealthGateway(driver);
	}

	//This login method ONLY for unregistered user test
	public RegistrationPage loginIntoHGWithBCServiceCardAsUser08() throws Exception {
		driver.navigate().to(Utils.getEnvConfigProperty("url"));
		if (Utils.getEnvConfigProperty("url").equalsIgnoreCase("https://uat-beta.healthgateway.gov.bc.ca/s/")) {
			Thread.sleep(4000);
			click(btnLoginWithBCServiceCard);
		}
		Thread.sleep(4000);
		click(btnTestWithUserNameAndPassword);
		Thread.sleep(3000);
		enterCredentialsForHGPortalAs("user_HTHGTWY08", "password_HTHGTWY08_PW");
		Thread.sleep(3000);
		return new RegistrationPage(driver);
	}
}