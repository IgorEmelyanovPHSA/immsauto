package primarycare.pages;

import bcvax.pages.RegisterToGetVaccinatedPage;
import bcvax.pages.Utils;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    /*---------Properties-------*/


    /*---------Constructor-------*/
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public PortalHealthConnectRegistryPage openPortalHealthConnectRegistryPage() throws Exception {
        driver.navigate().to(Utils.getEnvConfigProperty("url_citizenportal"));
        return new PortalHealthConnectRegistryPage(driver);
    }
}
