package communityPortal.tests.DIWA_CP;

import Utilities.TestListener;
import bcvax.pages.MainPageCP;
import bcvax.pages.ProfilesPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class DIWA_Covid19_CP extends BaseTest {

    @Test
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_CP() throws Exception {
        TestcaseID = "223187"; //C223187
        log("Target Environment: "+ Utils.getTargetEnvironment());
        String nameToSearch = "Benoite Denna BCVaxD";
        String clinicLocation = "All Ages - Atlin Health Centre";

        MainPageCP cpMainPage = new MainPageCP(getDriver());

        log("/*1.----Login --*/");
        switch (Utils.getTargetEnvironment()) {
            case "comunityqa_immsbc_admin":
                log("Login as ImmsBCAdmin");
                TestcaseID = "245099"; //C245099
                loginPage.loginIntoCommunityPortalAsImmsBCAdmin();
                break;
            default:
                log("Login as DIWA Clinician user");
                TestcaseID = "223187"; //C223187
                loginPage.loginIntoCommunityPortalAsClinician();

        }
        Thread.sleep(10000);

        log("/*2.----Navigate to More -> Participants -> Profiles Page --*/");
        ProfilesPage profilesPage = cpMainPage.navigateToProfilesPage();
        Thread.sleep(10000);
        //log("/*2.----Navigate to Participants -> Clients Profiles Page --*/");
        //ProfilesPage profilesPage = cpMainPage.navigateToProfilesPage();
        //Thread.sleep(10000);

        log("/*----3. select Citizen Participant acc from search results --*/");
        profilesPage.selectCitizenParticipantAcc(nameToSearch);
        Thread.sleep(5000);

        log("/*---- 4. Navigate to Person Account related tab ---*/");
        profilesPage.clickRelatedTab();
        Thread.sleep(5000);

        //log("/*-- 8. Create Immunization Record Button is Present on Layout --*/");
        //inClinicExperience.ValidateCreateImmunizationRecordButtonIsDisplayed();
        //Thread.sleep(2000);

        log("/*----5. Click to Create Immunization Record Button ---*/");
        profilesPage.clickCreateImmunizationRecord();
        Thread.sleep(4000);

        log("/*----6. Click confirm Button on the popup window---*/");
        profilesPage.clickConfirmButton();
        Thread.sleep(3000);

        log("/*----7. Select an Option from the DropDown ---*/)");
        profilesPage.clickSelectAnOptionDropdown();
        Thread.sleep(3000);

        log("/*----8. Select COVID19-mRNA as an Option  ---*/");
        profilesPage.selectOption("COVID19-mRNA");
        Thread.sleep(3000);

        log("/*----9. Enter a Clinic Location: " +clinicLocation +"---*/");
        profilesPage.searchClinicLocation(clinicLocation);
        Thread.sleep(3000);

        log("/*---10. Select a Date and Time of Administration ---*/");
        profilesPage.clickTimeBox();
        Thread.sleep(3000);

        log("/*---11. Click Record Immunization ---*/");
        profilesPage.clickRecordImmunization();
        Thread.sleep(3000);

        if (profilesPage.clickPopupYesButtonIfDisplayed())
            log("/*---11.1. Pop up window is displayed and clicked  ---*/");
        Thread.sleep(5000);

        log("/*---12. Click X button on Diwa flow ---*/");
        profilesPage.clickToClose();
        Thread.sleep(2000);

        log("/*---13. Validate message on clicking close button on modal popup ---*/");
        profilesPage.validateoopsMessage();
        Thread.sleep(2000);

        log("/*---14. click on continue editing button to continue with the flow ---*/");
        profilesPage.ContinueEditingButton();
        Thread.sleep(2000);

        log("/*---15. select date of Administration ---*/");
        if (profilesPage.selectDateOfAdministration())
            Thread.sleep(3000);

        log("/*---16. select Informed Consent Provider -> Auto Clinician_DIWA_CP ---*/");
        profilesPage.selectInformedConsentProvider("Auto Clinician_DIWA_CP");
        Thread.sleep(5000);

        log("/*---17. click Save Consent ---*/");
        profilesPage.clickSaveConsent();
        Thread.sleep(5000);

        log("/*---18. Select Immunizing Agent Provider ->: Auto Clinician_DIWA_CP ---*/");
        profilesPage.selectImmunizingAgentProvider("Auto Clinician_DIWA_CP");
        Thread.sleep(6000);

        log("/*---19. Click Show all lot numbers Checkbox---*/");
        profilesPage.clickShowAllLotNumbersCheckBox();
        Thread.sleep(2000);

        log("/*---20. click Lot Number dropdown component ---*/");
        profilesPage.clickLotNumberDropDown();
        Thread.sleep(2000);

        log("/*---21. Select SPIKEVAX (Moderna) -> Lot -->300042698 - Exp. 2021 June 18 ---*/");
        profilesPage.selectLot();
        Thread.sleep(2000);

        log("/*---22. Select Injection Site ---*/");
        profilesPage.selectInjectionSite();
        Thread.sleep(2000);

        log("/*---23. Select Dosage---*/");
        profilesPage.selectDosage();
        Thread.sleep(2000);

        log("/*---24. Save Immunization Information ---*/");
        profilesPage.saveImmunizationInformation();
        Thread.sleep(2000);

        log("/*---25. Confirm and Save Administration ---*/");
        profilesPage.confirmAndSaveAdministration();
        Thread.sleep(2000);

        log("/*---26. Vaccine Administration Summary Confirm and Save ---*/");
        profilesPage.summaryConfirmAndSave();
        Thread.sleep(10000);

        log("/*---27. Navigate to Related tab and Confirm new Imms Record is created ---*/");
        profilesPage.clickRelatedTab();
        Thread.sleep(2000);
        log("We need to validate that Documented Without Appointment created - not validated yet  ---*/");
        Thread.sleep(2000);

    }

}