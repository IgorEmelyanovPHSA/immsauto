package communityPortal.tests.DigitalConsent;

import Utilities.TestListener;
import bcvax.pages.DigitalConsentChildInformationPage;
import bcvax.pages.DigitalConsentPersonalInformationPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

@Listeners({TestListener.class})
public class PersonalInformationScreen extends BaseTest {
    String env;
    Map<String, Object> testData;
    Map<String, String> client_data;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "digital_consent");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
    }

    @Test(priority = 1)
    public void verifyPersonalInformationHomePageAndProvidingConsentScreen() throws Exception {
        TestcaseID = "310370";
        log("TestCase: 310370");
        log("Target Environment: " + Utils.getTargetEnvironment());
        SoftAssert softAssert = new SoftAssert();
        loginPage.openDigitalConsentPortal();
        Map<String, String> public_unit_click_result = DigitalConsentPersonalInformationPage.clickPublicHealthUnitLink(driver);
        softAssert.assertEquals(public_unit_click_result.get("new_tab"), "yes");
        softAssert.assertEquals(public_unit_click_result.get("window_title"), "Find an immunization clinic | Immunize BC");
        softAssert.assertTrue(public_unit_click_result.get("window_url").startsWith("https://immunizebc.ca/finder"));

        DigitalConsentPersonalInformationPage.clickContinue(driver);
        String first_name_error = DigitalConsentPersonalInformationPage.getFirstNameError(driver);
        String last_name_error = DigitalConsentPersonalInformationPage.getLastNameError(driver);
        String phn_error = DigitalConsentPersonalInformationPage.getPhnError(driver);
        String dob_error = DigitalConsentPersonalInformationPage.getDOBError(driver);
        softAssert.assertEquals(first_name_error, "Please enter a valid First Name");
        softAssert.assertEquals(last_name_error, "Please enter a valid Last Name");
        softAssert.assertEquals(phn_error, "Please enter a valid Personal Health Number");
        softAssert.assertEquals(dob_error, "Please enter a valid Date of Birth in the format Feb 12, 2020");

        String first_name = client_data.get("legalFirstName");
        String last_name = client_data.get("legalLastName");
        String phn = client_data.get("personalHealthNumber");
        String dob = client_data.get("dateOfBirth");
        DigitalConsentPersonalInformationPage.enterPHN(driver, phn.substring(1));

        DigitalConsentPersonalInformationPage.clickContinue(driver);
        phn_error = DigitalConsentPersonalInformationPage.getPhnError(driver);
        softAssert.assertEquals(phn_error, "Please check and re-enter your PHN. It is 10 digits, without spaces or dashes");

        DigitalConsentPersonalInformationPage.enterFirstName(driver, first_name);
        DigitalConsentPersonalInformationPage.enterLastName(driver, last_name);
        DigitalConsentPersonalInformationPage.enterDOB(driver, dob);
        DigitalConsentPersonalInformationPage.enterPHN(driver, phn);

        boolean relationship_section_appeared = DigitalConsentPersonalInformationPage.selectConsentForChild(driver);
        softAssert.assertTrue(relationship_section_appeared);

        DigitalConsentPersonalInformationPage.selectChildParent(driver);
        DigitalConsentPersonalInformationPage.clickContinue(driver);

        boolean consent_page_loaded = DigitalConsentChildInformationPage.consentForPersonToBeImmunizedPageLoaded(driver);
        softAssert.assertTrue(consent_page_loaded);

        loginPage.openDigitalConsentPortal();
        DigitalConsentPersonalInformationPage.enterFirstName(driver, first_name);
        DigitalConsentPersonalInformationPage.enterLastName(driver, last_name);
        DigitalConsentPersonalInformationPage.enterDOB(driver, dob);
        DigitalConsentPersonalInformationPage.enterPHN(driver, phn);

        DigitalConsentPersonalInformationPage.selectConsentForMyself(driver);
        DigitalConsentPersonalInformationPage.clickContinue(driver);
        consent_page_loaded = DigitalConsentChildInformationPage.consentConfirmContactPageLoaded(driver);
        softAssert.assertTrue(consent_page_loaded);

        softAssert.assertAll();
    }
}
