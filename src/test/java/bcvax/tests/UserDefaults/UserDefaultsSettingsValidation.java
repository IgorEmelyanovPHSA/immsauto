package bcvax.tests.UserDefaults;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class UserDefaultsSettingsValidation extends BaseTest {

    private final String[] lots = {"016F21A-CC07", "0649AA"};
    private final String clinicLocation = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @Test()
    public void UserDefaultsSettingsValidationTest() throws Exception {
        TestcaseID = "222176"; //C222176
        log("Target Environment: " + Utils.getTargetEnvironment());
        UserDefaultsPage userDefaultsPage = new UserDefaultsPage(getDriver());
        CommonMethods common = new CommonMethods(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());

        log("/*1.----Login as clinician ICE --*/");
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICEUserDefaults();
        Thread.sleep(15000);

        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        if (inClinicExperience.displayIceApp()) {
            log("/*-- User already on In-Clinic Experience page --*/");
        } else {
            log("/*-- Navigate to In-Clinic Experience page --*/");
            Thread.sleep(5000);
            inClinicExperience.selectICEFromApp();
            Thread.sleep(5000);
        }

        log("/*3.----Close All previously opened Tab's --*/");
        inClinicExperience.closeTabsHCA();
        Thread.sleep(5000);

        log("/*4.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        Thread.sleep(2000);

        log("/*5.----- Enter current date for UserDefaults --*/");
        userDefaultsPage.inputCurrentDateUserDefaults();
        Thread.sleep(2000);

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
        common.goToSupplyPageIfNeededAndConfirmPageIsDisplayedNew();

        Thread.sleep(3000);
        log("/*10.---- Validating results, given lot numbers should match --*/");
        for(int i = 0; i < lots.length; i++) {
            Assert.assertTrue(supplyConsolePage.validateLotUserDefaults(lots[i]));
        }

        log("/*12.---- Navigate User Defaults Page --*/");
        common.goToUserDefaultsIfNeededAndConfirmPageIsDisplayed();

        log("/*13.----- Open Advanced Settings --*/");
        userDefaultsPage.clickOnAdvancedSettings();

        log("/*14.----- Populate Lots and Sites --*/");
        userDefaultsPage.populateLotsAndSite(lots);

        log("/*15.----- Click btn Save and validate success msg --*/");
        userDefaultsPage.clickBtnSaveWithSuccessMsgValidation();
    }
}

