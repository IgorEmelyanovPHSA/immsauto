package prodsuppqa.my.salesforce.com.Tests.Register;

import org.testng.annotations.Test;
import prodsuppqa.my.salesforce.com.Pages.InClinicExperiencePage;
import prodsuppqa.my.salesforce.com.Tests.BaseTest;

public class RegisterNewCitizen extends BaseTest {

    @Test
    public void Can_register_new_participant_Citizen_account_Linked_with_PIR() throws InterruptedException {
        System.out.println("Register a new Citizen PRODSUPPQA");
        /*----Login as an Clinician In-Clinic Experience --*/
        System.out.println("/*----Login as an Clinician In-Clinic Experience --*/");
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
