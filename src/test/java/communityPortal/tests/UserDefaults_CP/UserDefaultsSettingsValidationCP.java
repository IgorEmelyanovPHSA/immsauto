package communityPortal.tests.UserDefaults_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

@Listeners({TestListener.class})
public class UserDefaultsSettingsValidationCP extends BaseTest {
    String env;
    Map<String, Object> testData;
    private final String clinicLocation = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test()
    public void UserDefaultsSettingsValidationTest_CP() throws Exception {
        TestcaseID = "222176"; //C222176
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        testData = Utils.getTestData(env);
        String[] lots = ((ArrayList<String>)testData.get("useDefaultSettingsLots")).toArray(new String[0]);
        log("/*1.----Login as Clinician --*/");
        MainPageCP cpMainPage = loginPage.loginIntoCommunityPortalAsClinician();

        log("/*2.----- Navigate to User Defaults Tab --*/");
        MainPageCP.clickUserDefaultsTab(driver);

        log("/*3.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);

        log("/*4.----- Enter clinic for UserDefaults: " + clinicLocation + "--*/");
     //   userDefaultsPage.selectClinicUserDefaults(clinicLocation);

        log("/*5.----- Open Advanced Settings--*/");
        UserDefaultsPage.clickOnAdvancedSettings(driver);

        log("/*6.----- Delete lots if any present and save--*/");
        Boolean isAnyLotsPresent = UserDefaultsPage.isAnyLotsPresent(driver);
             if(isAnyLotsPresent==true){
                 UserDefaultsPage.deleteAllLotsIfAnyHasBeenSavedPreviously(driver);
                 log("All lots are deleted");
                 UserDefaultsPage.clickBtnSaveWithSuccessMsgValidation(driver);
             }

        log("/*7.---- Navigate to Supply Console Page --*/");
        SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyLocation(clinicLocation);

        log("/*8.---- Validating results, given lot numbers should match --*/");
        for(int i = 0; i < lots.length; i++) {
            Assert.assertTrue(supplyConsolePage.validateLotUserDefaults(lots[i]));
        }

        log("/*9.----- Navigate to User Defaults Tab --*/");
        MainPageCP.clickUserDefaultsTab(driver);

        log("/*10.----- Open Advanced Settings --*/");
        UserDefaultsPage.clickOnAdvancedSettings(driver);

        log("/*11.----- Populate Lots and Sites --*/");
        UserDefaultsPage.populateLotsAndSite(driver, lots);

        log("/*12.----- Click btn Save and validate success msg --*/");
        UserDefaultsPage.clickBtnSaveWithSuccessMsgValidation(driver);
    }
}