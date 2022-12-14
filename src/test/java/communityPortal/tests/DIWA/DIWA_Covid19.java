package communityPortal.tests.DIWA;

import Utilities.TestListener;
import bcvax.pages.CommunityPortalMainPage;
import bcvax.pages.InClinicExperiencePage;
import bcvax.pages.SupplyConsolePage;
import bcvax.pages.ProfilesPage;
import bcvax.pages.Utils;
import bcvax.tests.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListener.class})
public class DIWA_Covid19 extends BaseTest {

    @Test
    public void Can_Create_DIWA_Immunisation_record_without_Appointments_as_Clinician_PRACTICE() throws Exception {
        TestcaseID = "223187"; //C223187
        log("Target Environment: "+ Utils.getTargetEnvironment());
        String nameToSearch = "Benoite Denna BCVaxD";
        String clinicLocation = "All Ages - Atlin Health Centre";

        log("/*1.----Login as an Clinician to ICE --*/");
        CommunityPortalMainPage cpMainPage = loginPage.loginIntoCommunityPortalAsAdmin();
        InClinicExperiencePage inClinicExperience = loginPage.loginAsClinician_DIWA_ICE();
        Thread.sleep(10000);

        log("/*2.----Navigate to More -> All Clients -> Profiles Page --*/");
        ProfilesPage profilesPage = cpMainPage.navigateToProfilesPage();
        Thread.sleep(10000);

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
        profilesPage.clickTimeBox();
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

        log("/*---20. select Informed Consent Provider -> Auto Clinician DIWA_ICE ---*/");
        //inClinicExperience.selectInformedConsentProvider("Auto Clinician DIWA_ICE");
        Thread.sleep(5000);

        log("/*---21. click Save Consent ---*/");
        //inClinicExperience.clickSaveConsent();
        Thread.sleep(5000);

        log("/*---22. Select Immunizing Agent Provider ->: Auto Clinician DIWA_CIB ---*/");
        //inClinicExperience.selectImmunizingAgentProvider("Auto Clinician DIWA_ICE");
        Thread.sleep(6000);

        log("/*---23. Click Show all lot numbers Checkbox---*/");
        //inClinicExperience.clickShowAllLotNumbersCheckBox();
        Thread.sleep(2000);

        log("/*---24. click Lot Number dropdown component ---*/");
        //inClinicExperience.clickLotNumberDropDown();
        Thread.sleep(2000);

        log("/*---25. Select SPIKEVAX (Moderna) -> Lot -->300042698 - Exp. 2021 June 18 ---*/");
        //inClinicExperience.selectLot();
        Thread.sleep(2000);

        log("/*---26. Select Injection Site ---*/");
        //inClinicExperience.selectInjectionSite();
        Thread.sleep(2000);
        log("/*---27. Select Dosage---*/");
        //inClinicExperience.selectDosage();
        Thread.sleep(2000);
        log("/*---28. Save Immunization Information ---*/");
        //inClinicExperience.saveImmunizationInformation();
        Thread.sleep(2000);
        log("/*---29. Confirm and Save Administration ---*/");
        //inClinicExperience.confirmAndSaveAdministration();
        Thread.sleep(2000);
        log("/*---30. Vaccine Administration Summary Confirm and Save ---*/");
        //inClinicExperience.summaryConfirmAndSave();
        Thread.sleep(2000);
        log("/*---31. Navigate to Related tab and Confirm new Imms Record is created ---*/");
        //inClinicExperience.clickRelatedTab();
    }


}
