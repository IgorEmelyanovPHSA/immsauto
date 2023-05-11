package primarycare.tests.Selenium.Provider_Salesforce;

import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.pages.CommonMethods;
import primarycare.pages.HealthCloudConsolePage;
import primarycare.pages.ProviderPortalHomePage;
import primarycare.tests.BaseTest_PrimaryCare;
import primarycare.tests.Utilities.ApiQueries;
import primarycare.tests.Utilities.TestListener;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Salesforce_Practitioner_Account_Info_Verification extends BaseTest_PrimaryCare {
    private String clinicName_1 = "CASTLEGAR MEDICAL CLINIC ASSOCIATES";
    private String physicalAddress_Street_1 = "1008 COLUMBIA AVE,";
    private String physicalAddress_City_PostCode_1 = "Castlegar, BC, V1N 1H2";
    private String clinicPhoneNumber_1 = "2503652161";
    private String Role = "Director";
    //private String isPrimary = "True"; is it Primary for Practitioner?

    //private String clinic_Name_2 = "CASTLEGAR MEDICAL CLINIC11111";

    @Test(priority = 1)
    public void Can_Verify_DIRECTOR_Practitioner_Account_Info_in_SF () throws Exception {
        TestcaseID = "252896"; //C252896
        log("Target Environment: "+ Utils.getTargetEnvironment());

        //CommonMethods com = new CommonMethods(getDriver());

        log("/*1.---Login to Salesforce as SysAdmin --*/");
        //ProviderPortalHomePage providerPortalHomePage= loginPage.loginProviderPortalHomePageAsDirector();
        Thread.sleep(1000);

        log("/*2.----SF Account page showing up --*/");
        //cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        Thread.sleep(1000);

    }




}
