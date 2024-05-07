package bcvax.tests.InClinicExperience;

import bcvax.pages.LoginPage;
import bcvax.pages.Utils;
import bcvax.pages.VaccinationTestDataStruct;
import bcvax.tests.BaseTest;
import bcvax.tests.Preconditions;
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
        test_data.setFirstName("Ludovika");
        test_data.setLastName("BcvaxLimeburn");
        test_data.setDateOfBirth("Sep 21, 1923");
        test_data.setPostalCode("V3L5L2");
        test_data.setPersonalHealthNumber("9746170911");
        test_data.setEmail("accountToDelete@phsa.ca");
        test_data.setClinicName(String.valueOf(testData.get("supplyLocationConsumption")));
        test_data.setDose(String.valueOf(testData.get("covidDose")));
        test_data.setLot(String.valueOf(testData.get("covidLot")));
        test_data.setRoute(String.valueOf(testData.get("routeConsumption")));
        test_data.setSite(String.valueOf(testData.get("siteConsumption")));
        test_data.setConsentProvider(String.valueOf(testData.get("consentProvider")));
        test_data.setAgent(String.valueOf(testData.get("vaccineAgent")));
        log("/*1.----Login --*/");
        try {
            LoginPage.loginAsImmsBCAdmin(driver);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Preconditions.citizenRegistered(driver, test_data);
        Preconditions.appointmentBookedNewCitizen(driver, test_data);
    }
}
