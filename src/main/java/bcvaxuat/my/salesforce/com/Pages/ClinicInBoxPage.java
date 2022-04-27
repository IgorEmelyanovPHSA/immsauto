package bcvaxuat.my.salesforce.com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ClinicInBoxPage extends BasePage {
	
	@FindBy(xpath = ".//button[text() = 'Register New Citizen']")
	private WebElement register_button;
	private By register_button_1 = By.xpath(".//button[text() = 'Register New Citizen']");
	
	@FindBy(xpath = ".//span[@title='Clinic in a Box (IPM)']")
	private WebElement clinicinbox_page_displayed;
	
	public ClinicInBoxPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickRegisterButton() throws InterruptedException {
		waitForElementToBeVisible(driver, register_button, 10);
		WebElement element = driver.findElement(register_button_1);
		register_button.click();
	}
	
	public void closeAllTabs() throws InterruptedException {
		do {
			try {
				WebElement closetab = driver.findElement(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])"));
				closetab.click();
				Thread.sleep(5000);
			} catch (NoSuchElementException e) {
				System.out.println("/*---there are no Tab's to close anymore");
			}
		} while (isDisplayed(By.xpath("(.//button[@class = 'slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container'])")));
	}
	
	public void verifyIsClinicInBoxPageDisplayed() {
		waitForElementToBeVisible(driver, clinicinbox_page_displayed, 10);
		this.clinicinbox_page_displayed.isDisplayed();
	}
}
