package bcvaxdevit.my.salesforce.com.Tests.UserDefaults;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;

@Listeners({TestListener.class})
public class UserDefaultsSettingsValidation extends BaseTest {

    @Test()
    public void UserDefaultsSettingsValidationTest() throws Exception {
        TestcaseID = "222176"; //C222176
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*1.----Login as clinician ICE --*/");
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICEUserDefaults();
        Thread.sleep(10000);

        log("/*2.----In Clinic Experience(ICE) page displayed --*/");
        inClinicExperience.verifyIsICEpageDisplayed();
        Thread.sleep(5000);

        log("/*3.--- Navigate to In Clinic Experience App --*/");
        inClinicExperience.selectICEFromApp();
        Thread.sleep(5000);

        log("/*4.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        Thread.sleep(5000);

        log("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*6.----- Enter current date for UserDefaults --*/");
        inClinicExperience.inputCurrentDateUserDefaults();
        Thread.sleep(2000);

        log("/*7.----- Enter clinic for UserDefaults --*/");
        inClinicExperience.selectClinicUserDefaults();

        log("/*8.----- Open Advanced Settings--*/");
        HashMap<String, String> agentLotTradeNameMap = inClinicExperience.clickOnAdvancedSettingsBtnAndSaveData();

        log("/*9.----- Click on Save defaults button --*/");
        inClinicExperience.clickSaveDefaultsButton();
        Thread.sleep(2000);

        //Need some more work:
        //Go to supply console and validate agent / lot / tradeName
    }

}
