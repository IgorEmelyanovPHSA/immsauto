package bcvax.tests.CallCenter;

import Utilities.TestListener;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class BookingDose1 extends BaseTest {
    MainPageOrg orgMainPage;
    String env;
    Map<String, Object> testData;
    String clinicNameToSearch;
    Map<String, String> client_data;
    @DataProvider(name = "booking_data")
    public Object[][] dpMethod() {
        return new Object[][]{{"222524", "Covid19Vaccine", true}, {"228856", "InfluenzaVaccine", false}};
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose1");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveAppointmentsFromParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveAllImmunizationRecordsByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        Utilities.ApiQueries.apiCallToRemovePIRAccountByPHN(client_data.get("personalHealthNumber"));
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


        System.out.println("/*1.----Login as an Call Center Agent to the Call Center Console --*/");
        CallCenterConsolePage callCenterConsole = loginPage.loginAsCalCenterAgentCC();
        System.out.println("/*2.----CallCenter Console page displayed --*/");
        orgMainPage = new MainPageOrg(driver);
        String currentApp = MainPageOrg.currentApp(driver);
        if (!currentApp.equals(Apps.CALL_CENTER_CONSOLE.value)) {
            MainPageOrg.switchApp(driver, Apps.CALL_CENTER_CONSOLE.value);
        }
        callCenterConsole.closeAllTabs();
        try {
            MainPageOrg.selectFromNavigationMenu(driver, "Home");
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            Thread.sleep(500);
            MainPageOrg.selectFromNavigationMenu(driver, "Home");
        }
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
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);

        System.out.println("/*18.----click on person Account Related Tab --*/");
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch (ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToRelatedTab(driver);
        }
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
        try {
            PersonAccountPage.goToRelatedTab(driver);
        } catch(ElementClickInterceptedException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.goToRelatedTab(driver);
        }
    }
}
