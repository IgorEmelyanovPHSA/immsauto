package healthgateway.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class MainPageHealthGateway extends BasePage {

    @FindBy(xpath = "//a[@title = 'Home']")
    private WebElement tabHome;

    @FindBy(xpath = "//a[@title = 'Timeline']")
    private WebElement tabTimeLine;

    @FindBy(xpath = "//a[@title = 'Dependents']")
    private WebElement tabDependents;

    @FindBy(xpath = "//a[@title = 'Services']")
    private WebElement tabServices;

    @FindBy(xpath = "//a[@title = 'Export']")
    private WebElement tabExport;

    @FindBy(xpath = "//a[@title = 'Feedback']")
    private WebElement tabFeedBack;

    @FindBy(xpath = "//div[@data-region-name ='themeHeaderProfileMenu']")
    private WebElement profileIcon;

    @FindBy(xpath = "//span[@title ='Logout']")
    private WebElement btnLogOutUnderProfile;

    @FindBy(xpath = "//span[@title ='Profile']")
    private WebElement dropDownProfile;

    @FindBy(xpath = "//*[text()='Organ Donor Registration']/../..//span")
    private WebElement widgetOrganDonorRegistration;

    public MainPageHealthGateway(WebDriver driver) {
        super(driver);
    }

    @Step
    public TimeLineTabPage goToTabTimeLine() throws InterruptedException {
        click(tabTimeLine);
        Thread.sleep(22000);
        return new TimeLineTabPage(driver);
    }

    @Step
    public DependentsTabPage goToTabDependents() throws InterruptedException {
        click(tabDependents);
        Thread.sleep(7000);
        return new DependentsTabPage(driver);
    }

    @Step
    public ServicesTabPage goToTabServices() throws InterruptedException {
        click(tabServices);
        Thread.sleep(7000);
        return new ServicesTabPage(driver);
    }

    @Step
    public ExportTabPage goToTabExport() throws InterruptedException {
        click(tabExport);
        Thread.sleep(5000);
        return new ExportTabPage(driver);
    }

    @Step
    public ProfilePage goToProfilePage() throws InterruptedException {
        click(profileIcon);
        click(dropDownProfile);
        Thread.sleep(5000);
        return new ProfilePage(driver);
    }

    @Step
    public ProfilePage goToLogOut() throws InterruptedException {
        click(profileIcon);
        click(btnLogOutUnderProfile);
        Thread.sleep(5000);
        return new ProfilePage(driver);
    }

}