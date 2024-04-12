package bcvax.tests.CallCenter;

import Utilities.TestListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    MainPageOrg orgMainPage;
    private String personalHealthNumber = "9746170911";
    //private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    String clinicNameToSearch = "Age 12 and Above - Abbotsford - Abby Pharmacy";

    @DataProvider(name = "booking_data")
    public Object[][] dpMethod() {
        return new Object[][]{{"222524", "Covid19Vaccine", true}, {"228856", "InfluenzaVaccine", false}};
    }

    @Test(dataProvider = "booking_data")
    public void Can_Book_Dose1_Appointment_as_Call_Center_Agent(String testcase_id, String vaccine_agent, boolean vaccine_available) throws Exception {
        //TestcaseID = "222524"; //C222524
        TestcaseID = testcase_id;
        log("Target Environment: " + Utils.getTargetEnvironment());
        log("------------------------------");
        log("Testcase ID: " + testcase_id);
        log("Vaccine Agent: " + vaccine_agent);
        log("------------------------------");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        System.out.println("/*1.----Login as an Call Center Agent to the Call Center Console --*/");
        CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
        System.out.println("/*2.----CallCenter Console page displayed --*/");
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if (!currentApp.equals(Apps.CALL_CENTER_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.CALL_CENTER_CONSOLE.value);
        }
        callCenterConsole.closeAllTabs();
        MainPageOrg.selectFromNavigationMenu(driver, "Home");
        callCenterConsole.verifyIsCallCenterConsolePageDisplayed();
        System.out.println("/*3.----Close All previously opened Tab's --*/");

        System.out.println("/*4.----click Register New Citizen--*/");
        try {
            callCenterConsole.clickRegisterButton();
        } catch (Exception ex) {
            Thread.sleep(2000);
            callCenterConsole.closeAllTabs();
            callCenterConsole.clickRegisterButton();
        }
        System.out.println("/*5.----Enter First Name " + legalFirstName + "--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        System.out.println("/*6.----Enter Last Name " + legalLastName + "--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        System.out.println("/*6.----Enter Date of birth " + dateOfBirth + "--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        System.out.println("/*7.----Enter Postal code " + postalCode + "--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        System.out.println("/*8.----Enter PHN " + personalHealthNumber + "--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
        System.out.println("/*9.----click on non-Indigenous person radiobutton --*/");
        System.out.println("/*10.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        System.out.println("/*11.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);
        System.out.println("/*12.----click Next button --*/");
        CitizenPrimaryInfo.clickNextButton(driver);
        System.out.println("/*13.'Enter email address " + email + "--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        System.out.println("/*14.'Confirm email address " + email + "--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);
        System.out.println("/*15.Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        System.out.println("/*16.Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        System.out.println("/*17.--toast success message - 'Success' --*/");
        CitizenPrimaryInfo.successRegisteredMessageAppear(driver);

        System.out.println("/*18.----click on person Account Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
        //callCenterConsole.clickOnPersonAccountRelatedTab();

        System.out.println("/*21----Go to Appointment Tab --*/");
        PersonAccountPage.goToVaccineScheduleTab(driver);
        //callCenterConsole.navigateToVaccineSchedulingTab();

        //If override Eligibility is shown
        try {
            System.out.println("/*22.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
            PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine_agent);
        } catch (Exception ex) {
            if (vaccine_available) {
                System.out.println("---click on reason Override Eligibility Reason - Travel --*/");
                PersonAccountSchedulePage.overrideEligibility(driver);
                Thread.sleep(500);
                System.out.println("/*22.----click on the Vaccine 'Covid-19 Vaccine' checkbox --*/");
                PersonAccountSchedulePage.checkBookingVaccineCheckbox(driver, vaccine_agent);
            } else {
                //---If vaccine is disabled and not available in UI then Pass
                Assert.assertTrue(1 == 1);
                return;
            }
        }

        ////////////////////
        //May will be removed
        //PersonAccountPage.select_covid_19_agent(driver, "COVID-19 mRNA Vaccine (Pfizer-BioNTech Comirnaty/Moderna Spikevax)");
        ///////////////////
        System.out.println("/*24----select 'Search by Clinic name' tab --*/");
        PersonAccountSchedulePage.selectSearchByClinicNameTab(driver);
        log("/*25----search the Clinic " + clinicNameToSearch + " --*/");
        PersonAccountSchedulePage.searchClinicName(driver, clinicNameToSearch);
        System.out.println("/*26----click on Option Facility location  --*/");
        PersonAccountSchedulePage.clickOnFacilityOptionLocation(driver);
        System.out.println("/*27----select Active booking appointment day  --*/");
        PersonAccountSchedulePage.selectBookingAppointmentDay(driver);
        System.out.println("/*28----select the time slot  --*/");
        PersonAccountSchedulePage.selectTimeSlotForAppointment(driver);
        System.out.println("/*29----click Next button  --*/");
        PersonAccountSchedulePage.clickNextButtonApptSchedulingPage(driver);
        System.out.println("/*30----click Verify Contact Information Checkbox  --*/");
        PersonAccountSchedulePage.clickVerifyContactInformation(driver);
        System.out.println("/*31----click Confirm Appointment button  --*/");
        PersonAccountSchedulePage.clickOnConfirmButton(driver);
        System.out.println("/*32----see 'Appointment confirmed!' screen --*/");
        boolean appointment_result = PersonAccountSchedulePage.appointmentConfirmationMessage(driver);
        assertTrue(appointment_result, "Appointment Confirmation screen didn't appear");
        System.out.println("/*33----Refresh page --*/");

        driver.navigate().refresh();
        System.out.println("/*34----Go to back to the Citizen Related Tab --*/");
        PersonAccountPage.goToRelatedTab(driver);
    }
}
