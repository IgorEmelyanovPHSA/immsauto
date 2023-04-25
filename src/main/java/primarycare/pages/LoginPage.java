package primarycare.pages;

import bcvax.pages.ClinicInBoxPage;
import bcvax.pages.RegisterToGetVaccinatedPage;
import bcvax.pages.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    /*---------Properties-------*/
    @FindBy(id = "username")
    private WebElement textUserName;

    @FindBy(id = "password")
    private WebElement textPassword;

    @FindBy(id = "Login")
    private WebElement login_button;


    /*---------Constructor-------*/
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public PortalHealthConnectRegistryPage openPortalHealthConnectRegistryPage() throws Exception {
        driver.navigate().to(Utils.getEnvConfigProperty("url_patient_registration_portal"));
        return new PortalHealthConnectRegistryPage(driver);
    }

    public HealthCloudConsolePage loginAsSysAdmin() throws Exception {
        driver.navigate().to(Utils.getEnvConfigProperty("url_sf_admin_side"));
        textUserName.sendKeys(Utils.getEnvConfigProperty("user_SYS_ADMIN"));
        textPassword.sendKeys(Utils.getEnvConfigProperty("password_SYS_ADMIN_PW"));
        click(login_button);
        return new HealthCloudConsolePage(driver);
    }
}
