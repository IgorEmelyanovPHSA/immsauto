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


    @Test(testName = "Create DIWA Immunisation record without Appointments(Java)")
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_BCVAXDEVIT() throws InterruptedException {
        TestcaseID = "221409"; //C221409
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

        clinicInBoxPage.SearchDIWACitizen("Maegan bcvaxvillage");
        System.out.println("/*----Search for Maegan BCVaxVillage ---*/");
        Thread.sleep(4000);
        clinicInBoxPage.userClickCitizen();
        Thread.sleep(4000);
        clinicInBoxPage.userClickInTable();

//        if (!clinicInBoxPage.userFound()) {
//            System.out.println("/*----User --> Maegan BCVaxVillage not found and return---*/");
//        }
//        while (clinicInBoxPage.userFound()) {
//            System.out.println("/*----User found and Navigated to record page ---*/");
//            Thread.sleep(2000);
//            clinicInBoxPage.clickRelatedTab();
//            System.out.println("/*---- Navigated to Person Account related tab ---*/");
//            Thread.sleep(2000);
//        }
        Thread.sleep(2000);
        System.out.println("/*----6. Locate The Related Tab Under Person Account ---*/");
        System.out.println("/*----7. Click Confirm ---*/");
        clinicInBoxPage.clickConfirmBtn();
        Thread.sleep(3000);
        System.out.println("/*----8. Click Creat Immunization Record ---*/");
        clinicInBoxPage.clickCreatImmunizationRecord();
        System.out.println("/*----9. Select an Option ---*/");
        clinicInBoxPage.clickSelectAnOptionDropdown();
        Thread.sleep(3000);
        System.out.println("/*----9. Select COVID19-mRNA as an Option  ---*/");
        clinicInBoxPage.selectOption("COVID19-mRNA");
        Thread.sleep(3000);
        System.out.println("/*----10. Enter a Clinic Location ---*/");
        clinicInBoxPage.searchClinicLocation("All Ages - Atlin Health Centre");
        Thread.sleep(3000);
        System.out.println("/*---11. Select a Date and Time Administration ---*/");
        clinicInBoxPage.selectDateAndTime();
        Thread.sleep(3000);
        System.out.println("/*---12. Click Record Immunization ---*/");
        clinicInBoxPage.clickRecordImmunization();
        Thread.sleep(3000);
        System.out.println("/*---13. Select Informed Consent Provider ---*/");
        clinicInBoxPage.informedConsentProvider();
        System.out.println("/*---14. Enter Consent Effective To Date---*/");
        clinicInBoxPage.enterConsentEffectiveToDate();
        System.out.println("/*---15. Save Consent---*/");
        clinicInBoxPage.clickSaveConsent();
        System.out.println("/*---16. Scroll down to Immunization Information and Click Edit---*/");
        Thread.sleep(3000);
        System.out.println("/*---17. Select Provider ---*/");
        //clinicInBoxPage.selectProvider();
        Thread.sleep(3000);
        System.out.println("/*---18. Reset Lot Number ---*/");
        //clinicInBoxPage.resetLotNumber();
        Thread.sleep(3000);
        System.out.println("/*---19. Select Injection Site ---*/");
        //clinicInBoxPage.selectInjectionSite();
        Thread.sleep(3000);
        System.out.println("/*---20. Write Revision Reason ---*/");
        //clinicInBoxPage.writeRevisionReason();
        Thread.sleep(3000);
        System.out.println("/*---21. Save Immunization Information ---*/");
        //clinicInBoxPage.saveImmunizationInformation();
        Thread.sleep(3000);
        System.out.println("/*---22. Confirm and Save Administration ---*/");
        //clinicInBoxPage.confirmAndSaveAdministration();
        Thread.sleep(3000);
        System.out.println("/*---23. Vaccine Administration Summary Confirm and Save ---*/");
        //clinicInBoxPage.summaryConfirmAndSave();
        Thread.sleep(3000);








    }
}
