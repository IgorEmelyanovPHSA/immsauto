package bcvax.pages;

import io.qameta.allure.Step;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.Request;
import org.openqa.selenium.devtools.v120.network.model.RequestId;
import org.openqa.selenium.devtools.v120.network.model.Response;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;


public class TimeLineTabPage extends BasePage{

    @FindBy(xpath = "//button[@name = 'progress']")
    private WebElement btnDropDownFilterAll;

    @FindBy(xpath = "//span[text() = 'Lab Results']")
    private WebElement dropDownSelectionLabResults;

    @FindBy(xpath = "//button[text() = 'Apply']")
    private WebElement btnApply;

    @FindBy(xpath = "(//*[contains(text(), 'Chemistry Lab Results')])[1]")
    private WebElement selectFirstLabResult;

    @FindBy(xpath = "//button[(text() = 'View test results')]")
    private WebElement btnViewTestResults;

    public TimeLineTabPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void selectFilterLabResults() throws InterruptedException {
        // waitForElementToBeClickable(driver, tabTimeLine,5);
        Thread.sleep(12000);
        click(btnDropDownFilterAll);
        waitForElementToBeClickable(driver, dropDownSelectionLabResults, 5);
        Thread.sleep(2000);
        click(dropDownSelectionLabResults);
        click(btnApply);
        Thread.sleep(2000);
    }
    @Step
    public void openFirstLabResultRecordAndValidate() throws InterruptedException {
        click(selectFirstLabResult);
        Thread.sleep(8000);
    }

}
