package bcvax.pages;

import io.qameta.allure.Step;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;



public class MainPageHealthGateway extends BasePage{

    @FindBy(xpath = "//button[@value='BC_Services_Card_Login']")
    private WebElement btnLoginWithBCServiceCard;

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
    @FindBy(xpath = "//a[@title = 'Timeline']")
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

        click(btnLoginWithBCServiceCard);
        Thread.sleep(3000);
        click(btnTestWithUserNameAndPassword);
        Thread.sleep(1000);
        typeIn(emailOrUsernameId, userName);
        typeIn(passwordId, password);
        Thread.sleep(500);

        click(btnContinue);
    }

    @Step
    public TimeLineTabPage goToTabTimeLine() throws InterruptedException {
       // waitForElementToBeClickable(driver, tabTimeLine,5);
        Thread.sleep(5000);
        click(tabTimeLine);
        return new TimeLineTabPage(driver);
    }

    public void getNetWorkValue222() {
        String[] tokenArray = new String[1];
        String token;
       // final AtomicReference<AtomicReference<String>>[] abc = new AtomicReference<AtomicReference<String>>[1];
        final String[] strCopy = new String[1];
        final CountDownLatch latch = new CountDownLatch(1);

        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //listener
        final RequestId[] requestIds = new RequestId[1];
      //  devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        log("time1");
        devTools.addListener(Network.responseReceived(), responseReceived -> {

                    requestIds[0] = responseReceived.getRequestId();
              //      String url = responseReceived.getResponse().getUrl();

              //      int status = responseReceived.getResponse().getStatus();
             //       String type = responseReceived.getType().toJson();
             //       String headers = responseReceived.getResponse().getHeaders().toString();

                    String responseBody = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
               //     log("header1 " + headers);
                 //   log("response body " + responseBody);
                    JSONObject jsonObject = new JSONObject(responseBody);
                   // String abc = jsonObject.get("access_token").toString();
                   // tokenArray[0] = jsonObject.get("access_token").toString();
                    //String abc = (String) jsonObject.get("access_token"); // this will get you the value for the key "token"
                  //  log("qwer " + tokenArray[0]);


                     strCopy[0] = String.valueOf(jsonObject.get("access_token").toString());
                     log("time2 "+strCopy[0]);
                   // abc[0] =  jsonObject.get("access_token").toString();

        });


        log("outside " +strCopy[0]);

        if(tokenArray[0] == null){
            token = tokenArray[0];
            log("this is token " +token);
        }
    }


    @Step
    public String enableNetworkListener() throws InterruptedException {
         String[] tokenArray = new String[1];
         ArrayList<String> cars = new ArrayList<String>();
         String token = "not found";

        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
     //   devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        //listener
        final RequestId[] requestIds = new RequestId[1];
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {

                    requestIds[0] = responseReceived.getRequestId();
//                    String url = responseReceived.getResponse().getUrl();
//
//                    int status = responseReceived.getResponse().getStatus();
//                    String type = responseReceived.getType().toJson();
//                    String headers = responseReceived.getResponse().getHeaders().toString();

                    String responseBody = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
                    //   log("header1 " +headers);
                   // log("response body " +responseBody);

                   JSONObject jsonObject = new JSONObject(responseBody);
                   tokenArray[0] = jsonObject.get("access_token").toString();
                   cars.add(jsonObject.get("access_token").toString());

                    //String abc = (String) jsonObject.get("access_token"); // this will get you the value for the key "token"

                }
        );
        // Cleanup (optional)
       //        devTools.send(Network.disable());

        if(tokenArray[0] == null){
            token = tokenArray[0];
            log("this is token " +token);
        }

    return token;
    }


    @Step
    public void captureNetworkTokenCloseNetworkListener() throws InterruptedException {
    // Cleanup (optionaldevTools.send(Network.disable());
    }


    public void getNetWorkValue(){
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        //listener
        final RequestId[] requestIds = new RequestId[1];
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {

                    requestIds[0] = responseReceived.getRequestId();
                    String url = responseReceived.getResponse().getUrl();

                    int status = responseReceived.getResponse().getStatus();
                    String type = responseReceived.getType().toJson();
                    String headers = responseReceived.getResponse().getHeaders().toString();

                    String  responseBody = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
                    log("header1 " +headers);
                    log("response body " +responseBody);
            JSONObject jsonObject = new JSONObject(responseBody);
            String abc = jsonObject.get("access_token").toString();
            //String abc = (String) jsonObject.get("access_token"); // this will get you the value for the key "token"
            log("qwer " +abc);
        }
        );


//        devTools.addListener(Network.responseReceived(), response ->
//                {
//                    Response res = response.getResponse();
//                  //  Headers tempLog = res.getHeaders();
//
//                    Optional<Headers> tempLog2 = res.getRequestHeaders();
//                  //  log(res.getUrl());
//                  //  log("value of string " +tempLog.toString());
//                    log("value of string " +tempLog2.toString());
////                    System.out.println (res.getStatus());
////                    System.out.println (res.getHeaders());
//                }
//                );

 //       devTools.addListener(Network.requestWillBeSent()

        // Cleanup (optional)
 //       devTools.send(Network.disable());

    }

    public void getNetWorkValue2(){
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));


        //listener
        devTools.addListener(Network.requestWillBeSent(), request ->
                {
                    Request req = request.getRequest();
                    log("req url " +req.getUrl());
                 //   System.out.println ("request status " +req.());
//                    System.out.println (res.getHeaders());
                }
        );


        //listener
        devTools.addListener(Network.responseReceived(), response ->
                {
                    Response res = response.getResponse();
                    log("res url " +res.getUrl());
                    System.out.println ("res status " +res.getStatus());
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
