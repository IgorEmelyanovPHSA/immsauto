package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.RequestId;
import org.openqa.selenium.devtools.v121.log.Log;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static Utilities.ApiQueries.queryToGetUniqueLink;

@Listeners({TestListener.class})
public class ApiTest1 extends BaseTest {


	@Test
	public void getCitizenInfo() throws Exception {
		TestcaseID = "228855"; //C228855
		log("Target Environment: " + Utils.getTargetEnvironment());
		ArrayList<String> cars = new ArrayList<String>();
//
//		//Open Health Gateway portal
		MainPageHealthGateway mainPageHealthGateway = loginPage.openHealthGatewayPortal();
//		Thread.sleep(3000);
//
		// Enable Network tab
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();

		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Listener
		final RequestId[] requestIds = new RequestId[1];
		devTools.addListener(Network.responseReceived(), responseReceived -> {
			requestIds[0] = responseReceived.getRequestId();
			String responseBody = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
			JSONObject jsonObject = new JSONObject(responseBody);
			log("first 2" +jsonObject.get("access_token").toString());
			cars.add(jsonObject.get("access_token").toString());
		});
//		//Login
		mainPageHealthGateway.loginWithBCServiceCard();

		//Print token
//		log("final token " +cars.get(1));

		Thread.sleep(500);

		//mainPageHealthGateway.goToTabTimeLine();
	//	mainPageHealthGateway.getNetWorkValue222();
		log("no bearer");

		Thread.sleep(10000);
		log("final token " +cars.get(0));


		log("/*0.---API call to remove duplicate citizen participant account if found--*/");

		}
		
	}