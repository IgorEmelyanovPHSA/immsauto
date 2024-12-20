package bcvax.tests.UserDefaults;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

@Listeners({TestListener.class})
public class UserDefaultsSettingsValidation extends BaseTest {
    String env;
    //private final String[] lots = {"016F21A-CC07", "0649AA"};
    //private final String[] lots = {"016F21A-CC07", "T005729-CC07"};
    String clinicLocation;
    Map<String, Object> testData;
    MainPageOrg orgMainPage;

    @Test()
    public void UserDefaultsSettingsValidationTest() throws Exception {
        TestcaseID = "222176"; //C222176
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        testData = Utils.getTestData(env);
        String[] lots = ((ArrayList<String>)testData.get("useDefaultSettingsLots")).toArray(new String[0]);
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        clinicLocation = String.valueOf(testData.get("supplyLocationConsumption"));
        log("/*1.----Login as clinician ICE --*/");
        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        String currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }
        log("/*3.----Close All previously opened Tab's --*/");
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
        }

        log("/*4.----- Click on User Defaults Tab --*/");
        InClinicExperiencePage.clickUserDefaultsTab(driver);

        log("/*5.----- Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);

        log("/*6.----- Enter clinic for UserDefaults: " + clinicLocation + "--*/");
       // userDefaultsPage.selectClinicUserDefaults(clinicLocation);

        log("/*7.----- Open Advanced Settings--*/");
        UserDefaultsPage.clickOnAdvancedSettings(driver);

        log("/*8.----- Delete lots if any present and save--*/");
        Boolean isAnyLotsPresent = UserDefaultsPage.isAnyLotsPresent(driver);
        if(isAnyLotsPresent) {
            UserDefaultsPage.deleteAllLotsIfAnyHasBeenSavedPreviously(driver);
            log("All lots are deleted");
            UserDefaultsPage.clickBtnSaveWithSuccessMsgValidation(driver);
        }

        log("/*9.---- Navigate to Supply Console Page --*/");
        orgMainPage = new MainPageOrg(getDriver());
        currentApp = MainPageOrg.currentApp(driver);
        if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }

        log("/*9.2----Close All previously opened Tab's --*/");
        SupplyConsolePage.closeTabsHCA(driver);
        log("/*9.3----Go to Supply Locations Tab --*/");
        SupplyConsolePage.clickSupplyLocationsTab(driver);
        log("/*9.4----Click on Automation Supply Location_1 --*/");
        SupplyLocationsPage.selectSupplyLocationName(driver, clinicLocation);

        log("/*10.---- Validating results, given lot numbers should match --*/");
        for(int i = 0; i < lots.length; i++) {
            boolean lot_exist = supplyConsolePage.validateLotUserDefaults(lots[i]);
            Assert.assertTrue(lot_exist);
        }

        log("/*12.---- Navigate User Defaults Page --*/");
        MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
        InClinicExperiencePage.clickUserDefaultsTab(driver);
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicLocation);

        log("/*13.----- Open Advanced Settings --*/");
        UserDefaultsPage.clickOnAdvancedSettings(driver);

        log("/*14.----- Populate Lots and Sites --*/");
        UserDefaultsPage.populateLotsAndSite(driver, lots);

        log("/*15.----- Click btn Save and validate success msg --*/");
        UserDefaultsPage.clickBtnSave(driver);
        UserDefaultsPage.clickOkForExpiredLot(driver);
    }
}

