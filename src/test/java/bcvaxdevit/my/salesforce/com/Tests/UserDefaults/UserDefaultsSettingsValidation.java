package bcvaxdevit.my.salesforce.com.Tests.UserDefaults;

import Utilities.TestListener;
import bcvaxdevit.my.salesforce.com.Pages.CommonMethods;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Pages.SupplyConsolePage;
import bcvaxdevit.my.salesforce.com.Pages.Utils;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;

@Listeners({TestListener.class})
public class UserDefaultsSettingsValidation extends BaseTest {

    @Test()
    public void UserDefaultsSettingsValidationTest() throws Exception {
        TestcaseID = "222176"; //C222176
        // Needs more work, after requirements are clarified. Only Lot validation is implemented
        log("Target Environment: " + Utils.getTargetEnvironment());
        String clinicLocation = "Age 12 and Above - Abbotsford - Abby Pharmacy";

        log("/*1.----Login as clinician ICE --*/");
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinicianICEUserDefaults();
        Thread.sleep(10000);

        log("/*2.----In Clinic Experience(ICE) page is displayed --*/");
        if (inClinicExperience.displayIceApp()) {
            log("/*-- User already on In-Clinic Experience page --*/");
        } else {
            log("/*-- Navigate to In-Clinic Experience page --*/");
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
        inClinicExperience.inputCurrentDateUserDefaults();
        Thread.sleep(2000);

        log("/*6.----- Enter clinic for UserDefaults: " + clinicLocation + "--*/");
        inClinicExperience.selectClinicUserDefaults(clinicLocation);

        log("/*7.----- Open Advanced Settings and get details--*/");
        HashMap<String, String> agentLotTradeNameMap = inClinicExperience.clickBtnAdvancedSettingsAndSaveData();
        String lot = agentLotTradeNameMap.get("lot");

        log("/*8.----- Click on Save defaults button --*/");
        inClinicExperience.clickSaveDefaultsButton();
        Thread.sleep(2000);

        log("/*9.---- Navigate to Supply Console Page --*/");
        CommonMethods common = new CommonMethods(getDriver());
        SupplyConsolePage supplyConsolePage = new SupplyConsolePage(getDriver());
        common.goToSupplyPageIfNeededAndConfirmPageIsDisplayed();

        Thread.sleep(2000);
        log("/*10.---- Click on Automation Supply Location --*/");
        supplyConsolePage.clickOnSupplyLocationCustom(clinicLocation);

        log("/*11.---- Validating results, lot number should match --*/");
        Assert.assertTrue(supplyConsolePage.validateLotUserDefaults(lot));
    }
}

