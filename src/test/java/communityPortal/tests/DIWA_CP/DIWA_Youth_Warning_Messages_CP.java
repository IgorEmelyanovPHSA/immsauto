package communityPortal.tests.DIWA_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Listeners({TestListener.class})
public class DIWA_Youth_Warning_Messages_CP extends BaseTest {
    String env;
    String consumptionRoute;
    Map<String, Object> testData;
    String participant_name;
    private String provider = "Auto Bchcomclinician";
    String clinicLocation;
    Map<String, String> client_data;


    @DataProvider(name = "userData")
    public Object[][] loginDataProvider() {
        return new Object[][] {
                {"under5year", "NewSPIKEVAXTestLot001", "0.25", false, "0.5", true},
                //	{"over6under11year", "NewSPIKEVAXTestLot001", "0.25", false, "0.5", true},
                {"over12years", "NewSPIKEVAXTestLot001", "0.25", true, "0.5", false}
        };
    }

    @Test(dataProvider = "userData")
    public void CP_Warning_Messages_Check_For_Youth_Vaccinations_DIWA(String client, String lot, String dose1, boolean warningFlag1,
                                                                      String dose2, boolean warningFlag2) throws Exception {
        TestcaseID = "243128"; //C243128
        env = Utils.getTargetEnvironment();
        log("Target Environment: " + env);
        log("Test Case Id: " + "C" + TestcaseID);
        log("Test for: " +client);

        //Data setup and cleanup before test
        testData = Utils.getTestData(env);
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, client);
        clinicLocation = String.valueOf(testData.get("diwaLocation"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        participant_name = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");

        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));

        log("---1. Login as Clinician ---");
        loginPage.loginIntoCommunityPortalAsClinician();
        MainPageCP cpMainPage = new MainPageCP(driver);
        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();

        log("---2. Navigate to More -> Register ---");
        MainPageCP.navigateToRegisterClientPage(driver);

        log("---3. Click Register button New Citizen ---");
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

        log("---4. Click on person Account Related Tab --*/");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch (ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            PersonAccountPage.goToRelatedTab(driver);
        }

        log("---5. Click to Create Immunization Record Button---");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);

        try {
            log("---6. Click confirm Button on the popup window---*/");
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Thread.sleep(2000);

        log("---7. Select COVID19-mRNA as an Option  ---");
        DiwaImmunizationRecord.selectAgent(driver, "COVID-19 mRNA");

        log("---8. Enter a Clinic Location: " +clinicLocation +"---");
        DiwaImmunizationRecord.searchClinicLocation(driver, clinicLocation);

        log("---9. Select a Date and Time of Administration ---");
        DiwaImmunizationRecord.clickTimeBox(driver);

        log("---10. Click Record Immunization ---");
        DiwaImmunizationRecord.clickRecordImmunization(driver);

        log("---11. Click X button on Diwa flow ---");
        try {
            DiwaImmunizationRecord.clickPotentialDuplicateYes(driver);
        } catch(NotFoundException ex) {
            System.out.println("---No Duplications. Continue---");
        }

        log("---12. Validate message on clicking close button on modal popup ---");
        try {
            DiwaImmunizationRecord.clickOopsCancelAndClose(driver);
        } catch(Exception ex) {
        }

        try {
            PersonAccountRelatedPage.checkExistingConsent(driver);
        } catch(Exception ex) {
            System.out.println("No Checkbox. Continue...");
        }

        log("---13. Select Immunizing Agent Provider: " +provider);
        try {
            DiwaImmunizationRecord.setProvider(driver, provider);
        } catch(Exception ex) {
            System.out.println("*** Exception: " + ex.getMessage());
            DiwaImmunizationRecord.clickEditImmunizationInformation(driver);
            Thread.sleep(500);
            DiwaImmunizationRecord.setProvider(driver, provider);
        }

        log("---14. Click Show all lot numbers Checkbox---");
        DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);

        log("---15. Click Lot Number dropdown component: " +lot);
        DiwaImmunizationRecord.setLotNumber(driver, lot);

        log("---16. Select Dosage: " +dose1);
        DiwaImmunizationRecord.setDosage(driver, dose1);

        log("---17. Validate present yes/no warning message, flag value: " +warningFlag1) ;
        Assert.assertTrue(warningFlag1 == DiwaImmunizationRecord.youthWarningMessage(driver), "The two boolean values are not equal!");

        log("---18. Update: Select Dosage: " +dose2);
        DiwaImmunizationRecord.setDosage(driver, dose2);
        Thread.sleep(1500);

        log("---19. Validate present yes/no warning message, flag value: " +warningFlag2) ;
        Assert.assertTrue(warningFlag2 == DiwaImmunizationRecord.youthWarningMessage(driver), "The two boolean values are not equal!");
    }

}
