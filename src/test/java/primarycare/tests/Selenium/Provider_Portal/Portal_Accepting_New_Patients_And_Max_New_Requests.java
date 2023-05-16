package primarycare.tests.Selenium.Provider_Portal;

import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.pages.ProviderPortalHomePage;
import primarycare.tests.BaseTest_PrimaryCare;
import primarycare.tests.Utilities.TestListener;

@Listeners({TestListener.class})
public class Portal_Accepting_New_Patients_And_Max_New_Requests extends BaseTest_PrimaryCare {
    private String clinicName_1 = "CASTLEGAR MEDICAL CLINIC ASSOCIATES";
    private String physicalAddress_Street_1 = "1008 COLUMBIA AVE,";
    private String physicalAddress_City_PostCode_1 = "Castlegar, BC, V1N 1H2";
    private String clinicPhoneNumber_1 = "2503652161";
    private String Role = "Director";
    //private String isPrimary = "True"; is it Primary for Practitioner?

    //private String clinic_Name_2 = "CASTLEGAR MEDICAL CLINIC11111";


    @Test(priority = 1)
    public void Can_View_Edit_fields_Accepting_New_Patients_And_Max_New_Requests_as_an_DIRECTOR_in_Portal () throws Exception {
        TestcaseID = "254213"; //C254213
        log("Target Environment: "+ Utils.getTargetEnvironment());

        //CommonMethods com = new CommonMethods(getDriver());

        log("/*1.---Login to Provider Portal Home page as an Director --*/");
        ProviderPortalHomePage providerPortalHomePage= loginPage.loginProviderPortalHomePageAsDirector();
        Thread.sleep(1000);

        log("/*2.----Click Home link to see all Clinic Associated Panels --*/");
        //cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        providerPortalHomePage.clickHomeLink();
        Thread.sleep(1000);

        log("/*3.----Click View in CASTLEGAR Medical Associated Facility Panel --*/");
        providerPortalHomePage.clickView();
        Thread.sleep(1000);

    }





}
