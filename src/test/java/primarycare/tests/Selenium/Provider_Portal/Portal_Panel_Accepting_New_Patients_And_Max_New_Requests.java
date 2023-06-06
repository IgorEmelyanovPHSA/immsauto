package primarycare.tests.Selenium.Provider_Portal;

import bcvax.pages.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import primarycare.pages.ProviderPortalHomePage;
import primarycare.tests.BaseTest_PrimaryCare;
import primarycare.tests.Utilities.TestListener;

import java.text.DecimalFormat;
import java.util.Random;

import static org.testng.Assert.assertEquals;

@Listeners({TestListener.class})
public class Portal_Panel_Accepting_New_Patients_And_Max_New_Requests extends BaseTest_PrimaryCare {
    private String clinicName_1 = "CASTLEGAR MEDICAL CLINIC ASSOCIATES";
    private String physicalAddress_Street_1 = "1008 COLUMBIA AVE,";
    private String physicalAddress_City_PostCode_1 = "Castlegar, BC, V1N 1H2";
    private String clinicPhoneNumber_1 = "2503652161";
    private String Role = "Director";
    //private String isPrimary = "True"; is it Primary for Practitioner?

    //private String clinic_Name_2 = "CASTLEGAR MEDICAL CLINIC11111";

    //private String desiredPanelSize = "77777";
    private int desiredPanelSize = ((1 + new Random().nextInt(2)) * 10000) + new Random().nextInt(10000);
    private String isAcceptingNewPatients = "Yes";
    //private String maxNewRequests = "88888";
    int maxNew = new Random().nextInt(10000) + 10000;
    String maxNewRequests = String.format("%05d", maxNew);


    @Test(priority = 1)
    public void Can_View_Edit_DIRECTORs_fields_Panel_Size_Accepting_New_Patients_And_Max_New_Requests_in_Portal () throws Exception {
        TestcaseID = "254213"; //C254213
        log("Target Environment: "+ Utils.getTargetEnvironment());

        //CommonMethods com = new CommonMethods(getDriver());

        log("/*1.---Login to Provider Portal Home page as an Director --*/");
        ProviderPortalHomePage providerPortalHomePage= loginPage.loginProviderPortalHomePageAsDirector();
        Thread.sleep(1000);

        log("/*2.----Click Home link to see all Clinic Associated Panels --*/");
        //cpMainPage.verifyIsCommunityPortalHomePageDisplayed();
        providerPortalHomePage.clickHomeLink();
        Thread.sleep(1000);

        log("/*3.----Click View in CASTLEGAR Medical Associated Facility Panel --*/");
        providerPortalHomePage.clickView();
        Thread.sleep(1000);

        log("/*4.----Click Edit --*/");
        providerPortalHomePage.clickEdit();
        Thread.sleep(1000);

        log("/*5.----Enter random Desired Panel Size: " + desiredPanelSize + "--*/");
        providerPortalHomePage.enterDesiredPanelSize(desiredPanelSize);
        Thread.sleep(1000);

        log("/*6.----Select Accepting new Patients: " + isAcceptingNewPatients + "--*/");
        providerPortalHomePage.selectAcceptingNewPatientsOption(isAcceptingNewPatients);
        Thread.sleep(1000);

        log("/*7.----Enter random Max New Requests: " + maxNewRequests + "--*/");
        providerPortalHomePage.enterMaxNewRequests(maxNewRequests);
        Thread.sleep(1000);

        log("/*8.----Click Save --*/");
        providerPortalHomePage.clickSave();
        Thread.sleep(1000);

//        log("/*9.---- Validate Desired Panel Size  ---*/");
//        String desiredPanelSizeExpected = new DecimalFormat("##,###").format(desiredPanelSize);
//        String desiredPanelSizeActual = providerPortalHomePage.getActualDesiredPanelSizeForValidation();
//        log("/*---Desired Panel Size actual is: " + desiredPanelSizeActual + " --*/");
//        assertEquals(desiredPanelSizeActual, desiredPanelSizeExpected);
//        Thread.sleep(2000);

        log("/*10.---- Validate isAccepting new Patients  ---*/");
        String acceptingNewPatientsActual = providerPortalHomePage.getActualIsAcceptingNewPatientsForValidation();
        log("/*---Accepting new Patients actual is: " + acceptingNewPatientsActual + " --*/");
        assertEquals(acceptingNewPatientsActual, isAcceptingNewPatients);
        Thread.sleep(2000);

        log("/*11.---- Validate Max New Requests  ---*/");
        double maxNewRequests_To_Double = Double.parseDouble(maxNewRequests);
        DecimalFormat formatter = new DecimalFormat("##,###");
        String maxNewRequestsExpected = formatter.format(maxNewRequests_To_Double);
        String maxNewRequestsActual = providerPortalHomePage.getActualMaxNewRequestsForValidation();
        log("/*---Max New Requests actual is: " + maxNewRequestsActual + " --*/");
        assertEquals(maxNewRequestsActual, maxNewRequestsExpected);
        Thread.sleep(2000);

    }





}
