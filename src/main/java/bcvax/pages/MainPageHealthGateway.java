package bcvax.pages;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.Headers;
import org.openqa.selenium.devtools.v120.network.model.Response;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constansts.Header.SUPPLY_LOCATION_NAME;

public class MainPageHealthGateway extends BasePage{

    @FindBy(xpath = "//span[text() = 'Log in with BC Services Card']")
    private WebElement btnLoginWithBCServiceCard;

    //Next screen //Test Banner
    @FindBy(xpath = "//span[text() = 'BC Services Card']")
    private WebElement btnBCServiceCard;

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

    //Main Gateway page after login
    @FindBy(xpath = "//div[text() = 'Timeline']")
    private WebElement tabTimeLine;


    public MainPageHealthGateway(WebDriver driver) {
        super(driver);
    }

//    @Step
//    public SupplyConsolePage goTo1() throws InterruptedException {
//        Thread.sleep(500);
//        btnLoginWithBCServiceCard.click();
//        return new SupplyConsolePage(driver);
//    }

    @Step
    public void loginWithBCServiceCard() throws InterruptedException {
        String userName = "HTHGTWY11";
        String password = "00098911";
        Thread.sleep(500);
        click(btnLoginWithBCServiceCard);
        Thread.sleep(1000);
        click(btnBCServiceCard);
        Thread.sleep(1000);
        click(btnTestWithUserNameAndPassword);
        Thread.sleep(1000);
        typeIn(emailOrUsernameId, userName);
        typeIn(passwordId, password);
        click(btnContinue);
    }

    @Step
    public void goToTabTimeLine() throws InterruptedException {
       // waitForElementToBeClickable(driver, tabTimeLine,5);
        Thread.sleep(5000);
        click(tabTimeLine);
    }


    public void getNetWorkValue(){
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        //listener
        devTools.addListener(Network.responseReceived(), response ->
                {
                    Response res = response.getResponse();
                  //  Headers tempLog = res.getHeaders();

                    Optional<Headers> tempLog2 = res.getRequestHeaders();
                  //  log(res.getUrl());
                  //  log("value of string " +tempLog.toString());
                    log("value of string " +tempLog2.toString());
//                    System.out.println (res.getStatus());
//                    System.out.println (res.getHeaders());
                }
                );

 //       devTools.addListener(Network.requestWillBeSent()

        // Cleanup (optional)
 //       devTools.send(Network.disable());

    }

    public void getNetWorkValue2(){
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        //listener
        devTools.addListener(Network.responseReceived(), response ->
                {
                    Response res = response.getResponse();
                    log(res.getUrl());
//                    System.out.println (res.getStatus());
//                    System.out.println (res.getHeaders());
                }
        );

        // Cleanup (optional)
        //       devTools.send(Network.disable());

    }


    public void getNetWorkValue22(){
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        //listener
        devTools.addListener(Network.responseReceived(), response ->
        {
                Response res = response.getResponse();
                System.out.println(res.getHeaders());

        }
        );

        // Cleanup (optional)
        //       devTools.send(Network.disable());

    }
















    @Step
    public SupplyConsolePage goToSupplyLocation() throws InterruptedException {
        Thread.sleep(500);
        By tab_supply_location_path = By.xpath("//a[text()='Supply Locations']");
        System.out.println("/*----Locate Dropdown Menu --*/");
        try {
            waitForElementToBeEnabled(driver, tab_supply_location_path, 30);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("--- Try again... ---");
            driver.navigate().refresh();
            Thread.sleep(500);
            waitForElementToBeEnabled(driver, tab_supply_location_path, 30);
        }
        WebElement tab_supply_location = driver.findElement(tab_supply_location_path);
        scrollCenter(tab_supply_location);
        Thread.sleep(1000);
        tab_supply_location.click();
        Thread.sleep(1000);
        By supply_location_header_path = By.xpath("//lst-breadcrumbs//span[text()='Supply Locations']");
        waitForElementToBeEnabled(driver, supply_location_header_path, 10);
        return new SupplyConsolePage(driver);
    }




}
