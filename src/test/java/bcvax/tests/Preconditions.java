package bcvax.tests;

import bcvax.pages.*;
import constansts.Apps;
import org.openqa.selenium.WebDriver;

public class Preconditions extends BasePage {
    public Preconditions(WebDriver driver) {
        super(driver);
    }

    public static boolean citizenRegistered(WebDriver driver, VaccinationTestDataStruct data) throws InterruptedException {
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        try {
            Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(data.getPersonalHealthNumber());
        } catch(Exception ex) {
            System.out.println("Exception was caught while deleting the existing citizen" + ex.getMessage());
        }

        log("/*2.----In Clinic Experience(ICE) page displayed --*/");
        String currentApp = MainPageOrg.currentApp(driver);
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        if(!currentApp.equals(Apps.IN_CLINIC_EXPERIENCE.value)) {
            MainPageOrg.switchApp(driver, Apps.IN_CLINIC_EXPERIENCE.value);
        }

        InClinicExperiencePage inClinicExperience = new InClinicExperiencePage(driver);
        log("/*4.----Close All previously opened Tab's --*/");
        InClinicExperiencePage.closeTabsHCA(driver);
        log("/*5.----- Click on User Defaults Tab --*/");
        inClinicExperience.clickUserDefaultsTab();
        log("/*6.----- Enter current date for UserDefaults --*/");
        log("/*-- 13. Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, data.getClinicName());
        log("/*7.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        AlertDialog.closeAlert(driver);
        System.out.println("/*8.----- Click on register Tab --*/");
        InClinicExperiencePage.clickRegisterTab(driver);
        //System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
        //inClinicExperience.clickSaveModalDefaultsButton();
        //Thread.sleep(2000);
        System.out.println("/*10.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);
        System.out.println("/*11.----Enter First Name " + data.getFirstName() + "--*/");
        CitizenPrimaryInfo.enterFirstName(driver, data.getFirstName());
        System.out.println("/*12.----Enter Last Name " + data.getLastName() + "--*/");
        CitizenPrimaryInfo.enterLastName(driver, data.getLastName());
        System.out.println("/*13.----Enter Date of birth " + data.getDateOfBirth() + "--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, data.getDateOfBirth());
        System.out.println("/*14.----Enter Postal code " + data.getPostalCode() + "--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, data.getPostalCode());
        System.out.println("/*15.----Enter PHN " + data.getPersonalHealthNumber() + "--*/");
        CitizenPrimaryInfo.enterPHN(driver, data.getPersonalHealthNumber());
        System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
        System.out.println("/*17.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);
        System.out.println("/*19.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        System.out.println("/*20.----'Enter email address " + data.getEmail() + "--*/");
        CitizenPrimaryInfo.enterEmail(driver, data.getEmail());
        System.out.println("/*21.----'Confirm email address " + data.getEmail() + "--*/");
        CitizenPrimaryInfo.confirmEmail(driver, data.getEmail());
        System.out.println("/*22.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        System.out.println("/*23.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        System.out.println("/*24.--toast success message - 'Success' --*/");
        boolean result;
        try {
            CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
            result = true;
        } catch(Exception ex) {
            System.out.println("No Success Message. Contrinue ...");
            System.out.println(ex.getMessage());
            result = false;
        }
        return result;
    }
    public static boolean appointmentBookedNewCitizen(WebDriver driver, VaccinationTestDataStruct data) throws InterruptedException {
        citizenRegistered(driver, data);
        return true;
    }

    public static boolean appointmentBookedExistingCitizen(WebDriver driver, VaccinationTestDataStruct data) throws InterruptedException {

        return true;
    }
}
