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
    private final String clinicLocation = "Age 12 and Above - Abbotsford - Abby Pharmacy";
    Map<String, Object> testData;
    MainPageOrg orgMainPage;

    @Test()
    public void UserDefaultsSettingsValidationTest() throws Exception {
        TestcaseID = "222176"; //C222176
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        testData = Utils.getTestData(env);
        String[] lots = ((ArrayList<String>)testData.get("useDefaultSettingsLots")).toArray(new String[0]);
        UserDefaultsPage userDefaultsPage = new UserDefaultsPage(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

        log("/*1.----Login as clinician ICE --*/");
        orgMainPage = loginPage.orgLoginAsClinicianICE();

        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        String currentApp = orgMainPage.currentApp();
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        }
        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        log("/*3.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();

        log("/*4.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();

        log("/*5.----- Enter current date for UserDefaults --*/");
        userDefaultsPage.inputCurrentDateUserDefaults();

        log("/*6.----- Enter clinic for UserDefaults: " + clinicLocation + "--*/");
       // userDefaultsPage.selectClinicUserDefaults(clinicLocation);

        log("/*7.----- Open Advanced Settings--*/");
        userDefaultsPage.clickOnAdvancedSettings();

        log("/*8.----- Delete lots if any present and save--*/");
        Boolean isAnyLotsPresent = userDefaultsPage.isAnyLotsPresent();
        if(isAnyLotsPresent==true){
            userDefaultsPage.deleteAllLotsIfAnyHasBeenSavedPreviously();
            log("All lots are deleted");
            userDefaultsPage.clickBtnSaveWithSuccessMsgValidation();
        }

        log("/*9.---- Navigate to Supply Console Page --*/");
        orgMainPage = new MainPageOrg(getDriver());
        currentApp = orgMainPage.currentApp();
        if (!currentApp.equals(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value)) {
            orgMainPage.switchApp(Apps.HEALTH_CONNECT_SUPPLY_CONSOLE.value);
        }
        log("/*9.1----Supply Console Page displayed --*/");
        supplyConsolePage.verifyIsSupplyPageDisplayed();
        log("/*9.2----Close All previously opened Tab's --*/");
        supplyConsolePage.closeTabsHCA();
        log("/*9.3----Go to Supply Locations Tab --*/");
        supplyConsolePage.clickSupplyLocationsTab();
        log("/*9.4----Click on Automation Supply Location_1 --*/");
        supplyConsolePage.selectSupplyLocationName(clinicLocation);

        log("/*10.---- Validating results, given lot numbers should match --*/");
        for(int i = 0; i < lots.length; i++) {
            boolean lot_exist = supplyConsolePage.validateLotUserDefaults(lots[i]);
            Assert.assertTrue(lot_exist);
        }

        log("/*12.---- Navigate User Defaults Page --*/");
        orgMainPage.switchApp(Apps.IN_CLINIC_EXPERIENCE.value);
        InClinicExperiencePage inClinicExperiencePage = new InClinicExperiencePage(driver);
        inClinicExperiencePage.clickUserDefaultsTab();
        UserDefaultsPage userDefaultPage = new UserDefaultsPage(driver);
        userDefaultPage.inputCurrentDateUserDefaults();
        userDefaultPage.selectUserDefaultLocation(clinicLocation);

        log("/*13.----- Open Advanced Settings --*/");
        userDefaultsPage.clickOnAdvancedSettings();

        log("/*14.----- Populate Lots and Sites --*/");
        userDefaultsPage.populateLotsAndSite(lots);

        log("/*15.----- Click btn Save and validate success msg --*/");
        userDefaultsPage.clickBtnSave();
        userDefaultsPage.clickOkForExpiredLot();
        userDefaultsPage.validateSuccessfullyUpdatedMsg();
    }
}

