package bcvax.tests.HealthGateway;

import Utilities.TestListener;
import bcvax.pages.MainPageHealthGateway;
import bcvax.pages.TimeLineTabPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.RequestId;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

@Listeners({TestListener.class})
public class CitizenGettingLabResults extends BaseTest {


	@Test
	public void viewLabResult() throws Exception {
		//Test Id
		TestcaseID = "291524"; //C291524

		//Original TC id
		//	TestcaseID = "291524"; //C291524


		//TimeLine Lab results
		log("Target Environment: " + Utils.getTargetEnvironment());
		log("Test Case " +"C" +TestcaseID);

		//Login as user 11
		MainPageHealthGateway mainPageHealthGateway = loginPage.loginIntoHGWithBCServiceCardAsUser11();

		//GoTo TimeLines
		TimeLineTabPage timeLine = mainPageHealthGateway.goToTabTimeLine();

		timeLine.selectFilterLabResults();

		timeLine.openFirstLabResultRecordAndValidate();

		//MainPageHealthGateway mainPageHealthGateway = loginPage.openHealthGatewayPortal();
		Thread.sleep(5000);


		log("Exit test");

		}

	}