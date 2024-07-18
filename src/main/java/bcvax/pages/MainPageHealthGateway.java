package bcvax.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class MainPageHealthGateway extends BasePage {

    @FindBy(xpath = "//a[@title = 'Timeline']")
    private WebElement tabTimeLine;

    @FindBy(xpath = "//a[@title = 'Dependents']")
    private WebElement tabDependents;

    @FindBy(xpath = "//div[@data-region-name ='themeHeaderProfileMenu']")
    private WebElement profileIcon;

    @FindBy(xpath = "//span[@title ='Profile']")
    private WebElement dropDownProfile;

    public MainPageHealthGateway(WebDriver driver) {
        super(driver);
    }

    @Step
    public TimeLineTabPage goToTabTimeLine() throws InterruptedException {
        // waitForElementToBeClickable(driver, tabTimeLine,5);
        Thread.sleep(10000);
        click(tabTimeLine);
        return new TimeLineTabPage(driver);
    }

    @Step
    public DependentsTabPage goToTabDependents() throws InterruptedException {
        // waitForElementToBeClickable(driver, tabTimeLine,5);
        Thread.sleep(5000);
        click(tabDependents);
        Thread.sleep(5000);
        return new DependentsTabPage(driver);
    }

    @Step
    public ProfilePage goToProfilePage() throws InterruptedException {
        // waitForElementToBeClickable(driver, tabTimeLine,5);
        Thread.sleep(7000);
        click(profileIcon);
        click(dropDownProfile);
        Thread.sleep(5000);
        return new ProfilePage(driver);
    }

}