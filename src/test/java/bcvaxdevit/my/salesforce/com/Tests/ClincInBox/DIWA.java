package bcvaxdevit.my.salesforce.com.Tests.ClincInBox;

import bcvaxdevit.my.salesforce.com.Pages.ClinicInBoxPage;
import bcvaxdevit.my.salesforce.com.Pages.InClinicExperiencePage;
import bcvaxdevit.my.salesforce.com.Tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;

public class DIWA  extends BaseTest {


    @Test(testName = "DIWA Create DIWA Immunisation record without Appointments(Java)")
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "220554"; //C22054
        System.out.println("/*----1. Login as an Clinician to CIB  --*/");
        ClinicInBoxPage clinicInBoxPage = loginPage.loginAsDIWA();
        Thread.sleep(4000);
        System.out.println("/*----2. Close all previously opened Tabs --*/");
        clinicInBoxPage.closeAllTabs();
        Thread.sleep(3000);
        System.out.println("/*----3. Close Other Tabs --*/");
        System.out.println("/*----3. Navigate to global search bar--*/");
        System.out.println("/*----4. Search for Participant account--*/");

        System.out.println("/*----5. Select DIWA Citizen ---*/");

        clinicInBoxPage.SearchDIWACitizen("Maegan BCVaxVillage");
        System.out.println("/*----Search for Maegan BCVaxVillage ---*/");
        if (!clinicInBoxPage.userFound()) {
            System.out.println("/*----User --> Maegan BCVaxVillage not found and return---*/");
        }
        while (clinicInBoxPage.userFound()) {
            System.out.println("/*----User found and Navigated to record page ---*/");
            Thread.sleep(2000);
            clinicInBoxPage.clickRelatedTab();
            System.out.println("/*---- Navigated to Person Account related tab ---*/");
            Thread.sleep(2000);
        }

        System.out.println("/*----6. Locate The Related Tab Under Person Account ---*/");
        clinicInBoxPage.clickCreatImmunizationRecord();
        Thread.sleep(3000);
        System.out.println("/*----7. Click Creat Immunization Record ---*/");
        clinicInBoxPage.clickSelectAnOptionDropdown();
        Thread.sleep(3000);
        System.out.println("/*----8. Select COVID19-mRNA as an Option  ---*/");
        //clinicInBoxPage.clickCovid19mRNA();
        System.out.println("/*----9. Enter a Clinic Location ---*/");
        //clinicInBoxPage.enterClinicLocation();
        System.out.println("/*---10. Select a Date and Time Administration ---*/");
        //clinicInBoxPage.SelectDateAndTime();
        Thread.sleep(3000);
        System.out.println("/*---11. Fill the Consent Fields ---*/");



    }
}
