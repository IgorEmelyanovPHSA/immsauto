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
    }

}
