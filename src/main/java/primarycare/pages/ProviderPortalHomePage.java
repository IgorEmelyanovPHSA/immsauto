package primarycare.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProviderPortalHomePage extends BasePage{
    /*---------Properties-------*/
    @FindBy(xpath = ".//strong[text() = 'Register to get a doctor or nurse practitioner']")
    private static WebElement register_to_get_doctor_page_validation;

    @FindBy(xpath = "//a[text() = 'Home']")
    private WebElement home_link;
    private By home_link_1 = By.xpath("//a[text() = 'Home']");

    @FindBy(xpath = "(//span[text()='View'])[2]")
    private WebElement view_link;
    private By view_link_1 = By.xpath("(//span[text()='View'])[2]");

    /*---------Constructor-------*/
    public ProviderPortalHomePage(WebDriver driver) {
        super(driver);
    }


    /*-------------Methods--------------*/
    public static boolean validateRegisterToGetDoctorPageDisplayed() throws InterruptedException {
        try {
            waitForElementToBeVisible(driver, register_to_get_doctor_page_validation, 10);
            log("/*---Home Register to get a doctor/nurse page - shown up");
        } catch (Exception e) {
            log("/*---Home Register to get a doctor/nurse page has NOT show up");
            throw e;
        }
        return false;
    }

    public void clickHomeLink() throws InterruptedException {
        waitForElementToBeVisible(driver, home_link, 10);
        Thread.sleep(1000);
        WebElement element = driver.findElement(home_link_1);
        Thread.sleep(1000);
        home_link.click();
    }

    public void clickView() throws InterruptedException {
        waitForElementToBeVisible(driver, view_link, 10);
        Thread.sleep(1000);
        view_link.click();
    }

}
