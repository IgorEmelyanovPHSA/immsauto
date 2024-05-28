package bcvax.tests.InClinicExperience;

import bcvax.pages.*;
import bcvax.tests.BaseTest;
import bcvax.tests.Preconditions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Map;

public class E2E_Deferrals extends BaseTest {

    @Test
    public void Can_do_Dose1_Vaccine_Administration_as_Clinician_ICE() throws Exception {
        Map<String, Object> testData;
        VaccinationTestDataStruct test_data = new VaccinationTestDataStruct();

        String env = Utils.getTargetEnvironment();
        test_data.setEnv(env);

        testData = Utils.getTestData(env);
        test_data.setFirstName("Ab");
        test_data.setLastName("Said");
        test_data.setDateOfBirth("Jan 02, 2023");
        test_data.setPostalCode("V3L5L2");
        test_data.setPersonalHealthNumber("9879450975");
        test_data.setEmail("accountToDelete@phsa.ca");
        test_data.setClinicName(String.valueOf(testData.get("supplyLocationConsumption")));
        test_data.setDose(String.valueOf(testData.get("covidDose")));
        test_data.setLot(String.valueOf(testData.get("covidLot")));
        test_data.setRoute(String.valueOf(testData.get("routeConsumption")));
        test_data.setSite(String.valueOf(testData.get("siteConsumption")));
        test_data.setConsentProvider(String.valueOf(testData.get("consentProvider")));
        test_data.setAgent("Covid19Vaccine");
        log("/*1.----Login --*/");
        try {
            LoginPage.loginAsImmsBCAdmin(driver);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Preconditions.citizenRegistered(driver, test_data);
        boolean res = Preconditions.appointmentBookedNewCitizen(driver, test_data);
        System.out.println("/*38.----click on In-clinic Experience button --*/");
        PersonAccountPage.clickCheckInButton(driver);

        System.out.println("/*40.---Click confirm and Save Button --*/");
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

        log("/*46.---Open Today's appointments from Home page --*/");
        InClinicExperiencePage.clickClientListTab(driver);
        ClientListPage.clickTodayAppointmentsTab(driver);
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, test_data.getPersonalHealthNumber());
        ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);
        System.out.println(res);
    }
}
