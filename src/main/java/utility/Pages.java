package utility;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class Pages {
    private WebDriver driver;
    private LoginPage loginPage;
//    private MainPage mainPage;


    public Pages(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage LoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

//    public MainPage MainPage() {
//        if (mainPage == null) {
//            mainPage = new MainPage(driver);
//        }
//        return mainPage;
//    }


}
