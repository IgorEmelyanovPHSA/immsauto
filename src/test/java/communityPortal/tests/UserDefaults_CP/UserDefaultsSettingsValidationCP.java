package communityPortal.tests.UserDefaults_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

@Listeners({TestListener.class})
public class UserDefaultsSettingsValidationCP extends BaseTest {

    private final String[] lots = {"016F21A-CC07", "LAIV-QTestLot001"};
    private final String clinicLocation = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test()
    public void UserDefaultsSettingsValidationTest_CP() throws Exception {
        TestcaseID = "222176"; //C222176
        log("Target Environment: " + Utils.getTargetEnvironment());

        log("/*1.----Login --*/");
        CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsClinicianInventory();
        Thread.sleep(10000);

        log("/*2.----- Navigate to User Defaults Tab --*/");
        UserDefaultsPage userDefaultsPage = cpMainPage.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*3.----- Enter current date for UserDefaults --*/");
        userDefaultsPage.inputCurrentDateUserDefaults();

        log("/*4.----- Enter clinic for UserDefaults: " + clinicLocation + "--*/");
     //   userDefaultsPage.selectClinicUserDefaults(clinicLocation);

        log("/*5.----- Open Advanced Settings--*/");
        userDefaultsPage.clickOnAdvancedSettings();

        log("/*6.----- Delete lots if any present and save--*/");
        Boolean isAnyLotsPresent = userDefaultsPage.isAnyLotsPresent();
             if(isAnyLotsPresent==true){
                 userDefaultsPage.deleteAllLotsIfAnyHasBeenSavedPreviously();
                 log("All lots are deleted");
                 userDefaultsPage.clickBtnSaveWithSuccessMsgValidation();
             }

        log("/*7.---- Navigate to Supply Console Page --*/");
        SupplyConsolePage supplyConsolePage = cpMainPage.navigateToSupplyLocation(clinicLocation);
        Thread.sleep(5000);

        log("/*8.---- Validating results, given lot numbers should match --*/");
        for(int i = 0; i < lots.length; i++) {
            Assert.assertTrue(supplyConsolePage.validateLotUserDefaults(lots[i]));
        }

        log("/*9.----- Navigate to User Defaults Tab --*/");
        cpMainPage.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*10.----- Open Advanced Settings --*/");
        userDefaultsPage.clickOnAdvancedSettings();

        log("/*11.----- Populate Lots and Sites --*/");
        userDefaultsPage.populateLotsAndSite(lots);

        log("/*12.----- Click btn Save and validate success msg --*/");
        userDefaultsPage.clickBtnSaveWithSuccessMsgValidation();
    }
}