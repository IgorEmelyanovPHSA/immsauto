package prodsuppqa.my.salesforce.com.Tests.Precondition;

import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.InClinicExperiencePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

public class CitizenDuplicates extends BaseTest {

    @Test(testName ="Login to InClinicExperience as Clinician and search for Dupls PRODSUPPQA")
    public void Can_search_and_remove_Citizen_participant_account() throws InterruptedException {
        System.out.println("Searching and Removing Citizen Duplicates PRODSUPPQA");
        /*----Login as an Clinician In-Clinic Experience --*/
        System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
        //loginPage.enterClinicianUserName();
        //loginPage.enterClinicianPW();
        //loginPage.clickLoginButton();
        //InClinicExperiencePage inClinicExperiencePage = loginPage.clickLoginButton();
        InClinicExperiencePage inClinicExperiencePage = loginPage.loginWith();
        //Thread.sleep(10000);
        /*----Go to Register Tab ---*/
        System.out.println("/*----Go to Register Tab ---*/");
        inClinicExperiencePage.clickRegisterTab();
        Thread.sleep(5000);
        /*----Search for Participant account ---*/
        System.out.println("/*----Search for Participant account ---*/");
        inClinicExperiencePage.SearchForCitizen("Giacinta BCVaxCaudelier");
        System.out.println("/*----Search for Giacinta is Successful ---*/");
        if (!inClinicExperiencePage.userFound()) {
            System.out.println("/*----User --> Giacinta not found and return---*/");
        } else {
            System.out.println("/*----User found and Navigated to record page ---*/");
            Thread.sleep(2000);
            inClinicExperiencePage.clickRelatedTab();
            System.out.println("/*---- Navigated to Person Account related tab ---*/");
            Thread.sleep(2000);
            if (!inClinicExperiencePage.selectImmsRecord()) {
                System.out.println("/*----No Imms Record found and return---*/");
            } else {
                System.out.println("/*---- User navigated to Imms record ---*/");
                Thread.sleep(2000);
                inClinicExperiencePage.deleteImmsRecord();
                System.out.println("/*---- Imms record deleted Successfully ---*/");
                Thread.sleep(2000);
            }
            inClinicExperiencePage.clickRelatedTab();
            System.out.println("/*---- Navigate back to Person Account related tab after deleting imms record---*/");
            Thread.sleep(5000);
            if (!inClinicExperiencePage.selectRERNRecord()) {
                System.out.println("/*----No RERN Record found and return---*/");
            } else {
                System.out.println("/*---- User navigated to RERN record ---*/");
                Thread.sleep(2000);
                inClinicExperiencePage.deleteRERNRecord();
                System.out.println("/*---- RERN record deleted Successfully ---*/");
                Thread.sleep(2000);
            }
                inClinicExperiencePage.clickRelatedTab();
                Thread.sleep(2000);
                System.out.println("/*---- Navigated to Person Account related tab ---*/");
                inClinicExperiencePage.deletePersonAccount();
                System.out.println("/*---- Person Account deleted Successfully ---*/");
        }
    }
    
}

