package communityPortal.tests.DIWA_CP;

import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import org.openqa.selenium.NotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({TestListener.class})
public class DIWA_Covid19_CP extends BaseTest {
    String env;
    Map<String, String> client_data;
    Map<String, Object> testData;
    private String consentProvider;
    private String lot_to_select;
    private String dosage_to_select;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "consent");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
    }
    @Test
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_CP() throws Exception {
        //TestcaseID = "223187"; //C223187

        log("Target Environment: "+ Utils.getTargetEnvironment());
        String nameToSearch = client_data.get("legalFirstName") + " " + client_data.get("legalMiddleName") + " " + client_data.get("legalLastName");;
        String clinicLocation = String.valueOf(testData.get("diwaLocation"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        lot_to_select = String.valueOf(testData.get("covidLot"));
        dosage_to_select = String.valueOf(testData.get("covidDose"));
        MainPageCP cpMainPage = new MainPageCP(getDriver());

        log("/*1.---Login as Clinician user---*");
        TestcaseID = "243108"; //C223187
        loginPage.loginIntoCommunityPortalAsClinician();

        log("TestRail test case ID: C" +TestcaseID);

        cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        log("/*2.---Search for Participant account --*/");
        MainPageCP.search(driver, nameToSearch);

        //If the PIR Warning is shown close it
        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("No PIR Warning. Continue...");
        }
        log("/*---- 4. Navigate to Person Account related tab ---*/");
        PersonAccountPage.goToRelatedTab(driver);

        log("/*----5. Click to Create Immunization Record Button ---*/");
        PersonAccountRelatedPage.clickCreateImmunizationRecord(driver);

        try {
            log("/*----6. Click confirm Button on the popup window---*/");
            PersonAccountPage.confirmNoForecastWarning(driver);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Thread.sleep(2000);
        log("/*----8. Select COVID19-mRNA as an Option  ---*/");
        DiwaImmunizationRecord.selectAgent(driver, "COVID-19 mRNA");

        log("/*----9. Enter a Clinic Location: " +clinicLocation +"---*/");
        DiwaImmunizationRecord.searchClinicLocation(driver, clinicLocation);

        log("/*---10. Select a Date and Time of Administration ---*/");
        DiwaImmunizationRecord.clickTimeBox(driver);

        log("/*---11. Click Record Immunization ---*/");
        DiwaImmunizationRecord.clickRecordImmunization(driver);

        log("/*---12. Click X button on Diwa flow ---*/");
        try {
            DiwaImmunizationRecord.clickPotentialDuplicateYes(driver);
        } catch(NotFoundException ex) {
            System.out.println("---No Duplications. Continue---");
        }

        log("/*---13. Validate message on clicking close button on modal popup ---*/");
        try {
            DiwaImmunizationRecord.clickOopsCancelAndClose(driver);
        } catch(Exception ex) {
            ;
        }

        //log("/*---14. click on continue editing button to continue with the flow ---*/");
        //DiwaImmunizationRecord.clickEditImmunizationInformation(driver);

        try {
            PersonAccountRelatedPage.checkExistingConsent(driver);
        } catch(Exception ex) {
            System.out.println("No Checkbox. Continue...");
        }

        log("/*---18. Select Immunizing Agent Provider ->: Auto Clinician_DIWA_CP ---*/");
        try {
            DiwaImmunizationRecord.setProvider(driver, consentProvider);
        } catch(Exception ex) {
            System.out.println("*** Exception: " + ex.getMessage());
            DiwaImmunizationRecord.clickEditImmunizationInformation(driver);
            Thread.sleep(500);
            DiwaImmunizationRecord.setProvider(driver, consentProvider);
        }

        log("/*---19. Click Show all lot numbers Checkbox---*/");
        DiwaImmunizationRecord.clickShowAllLotNumbersCheckBox(driver);

        log("/*---20. click Lot Number dropdown component ---*/");
        DiwaImmunizationRecord.setLotNumber(driver, lot_to_select);

        log("/*---22. Select Injection Site ---*/");
        DiwaImmunizationRecord.setSite(driver, "Arm - Right deltoid");
        DiwaImmunizationRecord.setRoute(driver, "Intramuscular");

        log("/*---23. Select Dosage---*/");
        DiwaImmunizationRecord.setDosage(driver, dosage_to_select);

        log("/*---24. Save Immunization Information ---*/");
        DiwaImmunizationRecord.clickSaveImmunizationInfo(driver);

        //Expired vax handler
        DiwaImmunizationRecord.clickOkForExpiredLot(driver);

        log("/*---25. Confirm and Save Administration ---*/");
        DiwaImmunizationRecord.clickConfirmAndSaveAdministration(driver);

        log("/*---26. Vaccine Administration Summary Confirm and Save ---*/");
        DiwaImmunizationRecord.clickSaveAdministrationSummary(driver);
        Thread.sleep(2000);
        //If the PIR Warning is shown close it
        try {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
        } catch(Exception ex) {
            System.out.println("No PIR Warning. Continue...");
        }
        log("/*---27. Navigate to Related tab and Confirm new Imms Record is created ---*/");
        PersonAccountPage.goToRelatedTab(driver);
        log("We need to validate that Documented Without Appointment created - not validated yet  ---*/");
    }

}