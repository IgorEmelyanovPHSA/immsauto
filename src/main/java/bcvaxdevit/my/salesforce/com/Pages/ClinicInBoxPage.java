package bcvaxdevit.my.salesforce.com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClinicInBoxPage extends BasePage {

    @FindBy(xpath = ".//span[text() = 'Register']")
    private WebElement register_button;

    public ClinicInBoxPage(WebDriver driver) {
        super(driver);
    }
}
