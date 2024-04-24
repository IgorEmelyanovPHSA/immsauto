package bcvax.tests.InClinicExperience;
import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Map;

@Listeners({TestListener.class})
public class Alerts_ICE_CIB extends BaseTest {
    String env;
    private String legalFirstName = "Ludovika";
    private String legalLastName = "BcvaxLimeburn";
    private String dateOfBirth = "Sep 21, 1923";
    private String postalCode = "V3L5L2";
    Map<String, Object> testData;
    private String personalHealthNumber = "9746170911";
    private boolean isIndigenous = false;
    private String email = "accountToDelete@phsa.ca";
    //private String email = "jason.yulghun@phsa.ca";
    String clinicNameToSearch;
    MainPageOrg orgMainPage;
    String consumptionLot;
    String consumptionDose;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;
    String consumptionAgent;

    @Test(priority = 1)
    public void verifyAlerts_ICE_CIB() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(personalHealthNumber);
        env = Utils.getTargetEnvironment();
        testData = Utils.getTestData(env);
        clinicNameToSearch = String.valueOf(testData.get("supplyLocationConsumption"));
        consumptionDose = String.valueOf(testData.get("covidDose"));
        consumptionLot = String.valueOf(testData.get("covidLot"));
        consumptionRoute = String.valueOf(testData.get("routeConsumption"));
        consumptionSite = String.valueOf(testData.get("siteConsumption"));
        consentProvider = String.valueOf(testData.get("consentProvider"));
        consumptionAgent = String.valueOf(testData.get("agentConsumption"));
        log("/*1.----Login --*/");

        loginPage.loginAsImmsBCAdmin();
        orgMainPage = new MainPageOrg(driver);
        //orgMainPage = loginPage.orgLoginAsPPHIS();
        TestcaseID = "225636"; //

        log("TestRail test case ID: C" +TestcaseID);

        log("/*2.----In Clinic Experience(ICE) page displayed --*/");
        String currentApp = MainPageOrg.currentApp(driver);
        try {
            MainPageOrg.closeAllTabs(driver);
        } catch(Exception ex) {
            ;
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
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
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
        System.out.println("/*11.----Enter First Name " +legalFirstName +"--*/");
        CitizenPrimaryInfo.enterFirstName(driver, legalFirstName);
        System.out.println("/*12.----Enter Last Name " +legalLastName +"--*/");
        CitizenPrimaryInfo.enterLastName(driver, legalLastName);
        System.out.println("/*13.----Enter Date of birth " +dateOfBirth +"--*/");
        CitizenPrimaryInfo.enterDateOfBirth(driver, dateOfBirth);
        System.out.println("/*14.----Enter Postal code " +postalCode +"--*/");
        CitizenPrimaryInfo.enterPostalCode(driver, postalCode);
        System.out.println("/*15.----Enter PHN " +personalHealthNumber +"--*/");
        CitizenPrimaryInfo.enterPHN(driver, personalHealthNumber);
        System.out.println("/*16.----click on non-Indigenous person radiobutton --*/");
        System.out.println("/*17.----click Verify PHN button --*/");
        CitizenPrimaryInfo.clickVerifyPHNButton(driver);
        System.out.println("/*18.--Expecting to see the toast success message - 'PNH match successful' --*/");
        CitizenPrimaryInfo.successMessageAppear(driver);
        System.out.println("/*19.----click Next button --*/");
        try {
            CitizenPrimaryInfo.clickNextButton(driver);
        } catch(ElementClickInterceptedException ex) {
            CitizenPrimaryInfo.successMessageAppear(driver);
            CitizenPrimaryInfo.clickNextButton(driver);
        }
        System.out.println("/*20.----'Enter email address " +email +"--*/");
        CitizenPrimaryInfo.enterEmail(driver, email);
        System.out.println("/*21.----'Confirm email address " +email +"--*/");
        CitizenPrimaryInfo.confirmEmail(driver, email);
        System.out.println("/*22.---Click review details Button--*/");
        CitizenPrimaryInfo.clickReviewDetails(driver);
        System.out.println("/*23.----Click register Button on confirmation page--*/");
        CitizenPrimaryInfo.clickRegisterButtonOnConfirmationPage(driver);
        System.out.println("/*24.--toast success message - 'Success' --*/");
        try {
            CitizenPrimaryInfo.successRegisteredMessageAppear(driver);
        } catch(Exception ex) {
            System.out.println("No Success Message. Contrinue ...");
            System.out.println(ex.getMessage());
        }
        System.out.println("/*25.----click on CheckIn button --*/");
        PersonAccountPage.clickCheckInButton(driver);
        String sidebar_alerts_text = InClinicExperienceIdentificationPage.getSidebarAlertText(driver);

        System.out.println("/*.----Verify Sidebar contains Alerts(0) --*/");
        Assert.assertEquals("Alerts(0)", sidebar_alerts_text);
        boolean alert_section_minimized = InClinicExperienceIdentificationPage.alertSectionMinimized(driver);

        System.out.println("/*.----Verify Alerts section is minimized --*/");
        Assert.assertTrue(alert_section_minimized, "Alerts Section is EXPANDED");

        InClinicExperienceIdentificationPage.expandAlertSection(driver);
        boolean alert_section_empty = InClinicExperienceIdentificationPage.alertSectionEmpty(driver);

        System.out.println("/*.----Verify Alerts Section is Empty --*/");
        Assert.assertTrue(alert_section_empty, "Alerts Section is not Empty");

        LocalDate today_date = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        InClinicExperienceIdentificationPage.clickAddAlertButton(driver);
        List<String> my_alert_types = AddAlertDialog.getTypesOfAlert(driver);

        System.out.println("/*.----Add Alert --*/");
        AddAlertDialog.setAlertEffectiveFrom(driver, sdf.format(sdf.parse(today_date.minusDays(1).toString())));
        AddAlertDialog.setAlertEffectiveTo(driver, sdf.format(sdf.parse(today_date.plusDays(1).toString())));
        AddAlertDialog.setTypesOfAlert(driver, "Sensitive Record");
        AddAlertDialog.setAlertMessage(driver, "Alert For Editing");
        Thread.sleep(1000);
        AddAlertDialog.clickSaveButton(driver);

        List<Map<String, String>> alert_data = new ArrayList<>();
        Map<String, String> alert_data_row = new HashMap<String, String>();
        alert_data_row.put("Name", "First Alert");
        alert_data_row.put("DateFrom", sdf.format(sdf.parse(today_date.minusDays(1).toString())));
        alert_data_row.put("DateTo", sdf.format(sdf.parse(today_date.plusDays(1).toString())));
        alert_data_row.put("AlertType", "Sensitive Record");
        alert_data_row.put("AlertMessage", "Sensitive Record");
        alert_data.add(alert_data_row);

        alert_data_row = new HashMap<String, String>();
        alert_data_row.put("Name", "Second Alert");
        alert_data_row.put("DateFrom", sdf.format(sdf.parse(today_date.minusDays(2).toString())));
        alert_data_row.put("DateTo", sdf.format(sdf.parse(today_date.plusDays(2).toString())));
        alert_data_row.put("AlertType", "Safety Concern for Staff");
        alert_data_row.put("AlertMessage", "Safety Concern for Staff");
        alert_data.add(alert_data_row);

        alert_data_row = new HashMap<String, String>();
        alert_data_row.put("Name", "Third Alert");
        alert_data_row.put("DateFrom", sdf.format(sdf.parse(today_date.minusDays(3).toString())));
        alert_data_row.put("DateTo", sdf.format(sdf.parse(today_date.minusDays(1).toString())));
        alert_data_row.put("AlertType", "Other (Specify)");
        alert_data_row.put("AlertMessage", "Alert Not Active Message");
        alert_data.add(alert_data_row);

        for(int i = 0; i < alert_data.size(); i++) {
            InClinicExperienceIdentificationPage.clickAddAlertButton(driver);
            AddAlertDialog.setAlertEffectiveFrom(driver, alert_data.get(i).get("DateFrom"));
            AddAlertDialog.setAlertEffectiveTo(driver, alert_data.get(i).get("DateTo"));
            AddAlertDialog.setTypesOfAlert(driver, alert_data.get(i).get("AlertType"));
            AddAlertDialog.setAlertMessage(driver, alert_data.get(i).get("AlertMessage"));
            AddAlertDialog.clickSaveButton(driver);
        }

        driver.navigate().refresh();
        Thread.sleep(2000);
        InClinicExperienceIdentificationPage.expandAlertSection(driver);
        List<Map<String, WebElement>> alert_table = InClinicExperienceIdentificationPage.getAlertSectionTable(driver);

        System.out.println("/*.----Verify 4 alerts in alerts table --*/");
        Assert.assertEquals(5, alert_table.size());
        String my_alerts_from_sidebar = InClinicExperienceIdentificationPage.getSidebarAlertText(driver);

        System.out.println("/*.----Verify Sidebar contains Alerts(3). Only active --*/");
        Assert.assertEquals("Alerts(3)", my_alerts_from_sidebar);

        String icon_not_active = InClinicExperienceIdentificationPage.getAlertIcon(alert_table, "Alert Not Active Message");

        System.out.println("/*.----Verify Alert icons --*/");
        Assert.assertEquals("BCH_GreyTriangleIcon", icon_not_active);

        String icon_safety = InClinicExperienceIdentificationPage.getAlertIcon(alert_table, "Safety Concern for Staff");
        Assert.assertEquals(icon_safety, "BCH_PurpleWarningIcon");

        String icon_sensitive = InClinicExperienceIdentificationPage.getAlertIcon(alert_table, "Sensitive Record");
        Assert.assertEquals(icon_sensitive, "BCH_YellowWarningIcon");

        InClinicExperienceIdentificationPage.clickViewEditAlert(driver, "Alert For Editing");
        ViewEditAlertPage.setAlertEffectiveTo(driver, sdf.format(sdf.parse(today_date.minusDays(1).toString())));
        ViewEditAlertPage.clickSaveButton(driver);
        List<String> my_errors = AlertDialog.getAllAlertsText(driver);
        AlertDialog.closeAllAlerts(driver);

        System.out.println("/*.----Verify Alert creation missing mandatory Fields --*/");
        Assert.assertEquals("Error\n" +
                "Unable to update Alert You must provide a reason for update to edit this alert", my_errors.get(0));

        ViewEditAlertPage.selectAlertReasonForUpdate(driver, "Other, specify");
        ViewEditAlertPage.clickSaveButton(driver);
        List<String> my_other_errors = AlertDialog.getAllAlertsText(driver);
        AlertDialog.closeAllAlerts(driver);
        Assert.assertEquals("Error\n" +
                "Unable to update Alert You must enter details in Update Comment if Reason for Update is Other", my_other_errors.get(0));

        ViewEditAlertPage.setAlertUpdateComments(driver, "Alert Update Comment");
        ViewEditAlertPage.clickSaveButton(driver);
        Thread.sleep(1000);
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

        inClinicExperience.clickTodayAppointments();
        Thread.sleep(2000);
        inClinicExperience.clickTodayAppointmentCaseViewButton(legalFirstName + " " + legalLastName);

        String final_alerts_from_sidebar = InClinicExperienceIdentificationPage.getSidebarAlertText(driver);

        System.out.println("/*.----Verify Sidebar contains Alerts(2) after alert updated to be not active --*/");
        Assert.assertEquals("Alerts(2)", final_alerts_from_sidebar);

        alert_section_minimized = InClinicExperienceIdentificationPage.alertSectionMinimized(driver);

        System.out.println("/*.----Verify Alert Section is minimized --*/");
        Assert.assertTrue(alert_section_minimized, "Alerts Section is EXPANDED");

        InClinicExperienceIdentificationPage.expandAlertSection(driver);
        alert_table = InClinicExperienceIdentificationPage.getAlertSectionTable(driver);

        System.out.println("/*.----Verify Alerts section contains 4 alerts --*/");
        Assert.assertEquals(5, alert_table.size());

        currentApp = MainPageOrg.currentApp(driver);
        if(!currentApp.equals(Apps.CLINIC_IN_BOX.value)) {
            MainPageOrg.switchApp(driver, Apps.CLINIC_IN_BOX.value);
        }
        MainPageOrg.closeAllTabs(driver);
        MainPageOrg.search(driver, legalFirstName + " " + legalLastName);
        PersonAccountPage.goToRelatedTab(driver);
        String alerts_text = PersonAccountPage.getClientAlerts(driver);

        System.out.println("/*.----Verify Header contains Alerts(2) --*/");
        Assert.assertEquals("Active(2)", alerts_text);
        List<String> my_images = PersonAccountPage.getClientAlertImages(driver);

        System.out.println("/*.----Verify Alerts Images in header --*/");
        Assert.assertTrue(my_images.contains("BCH_PurpleWarningIcon"));
        Assert.assertTrue(my_images.contains("BCH_YellowWarningIcon"));
        PersonAccountRelatedPage.scrollToAlertsSection(driver);
        Thread.sleep(500);
        PersonAccountRelatedPage.clickNewAlertButton(driver);
        NewAlertPage.setAlertName(driver, "Alert from CIB");
        NewAlertPage.setAlertEffectiveFrom(driver, sdf.format(sdf.parse(today_date.minusDays(5).toString())));
        NewAlertPage.setAlertEffectiveTo(driver, sdf.format(sdf.parse(today_date.plusDays(5).toString())));
        NewAlertPage.setTypesOfAlert(driver, "Safety Concern for Client");
        NewAlertPage.setAlertMessage(driver, "Alert Message CIB");
        NewAlertPage.clickSaveButton(driver);
        Thread.sleep(2000);
        MainPageOrg.closeAllTabs(driver);
        MainPageOrg.search(driver, legalFirstName + " " + legalLastName);
        PersonAccountPage.goToRelatedTab(driver);
        PersonAccountRelatedPage.scrollToAlertsSection(driver);
        List<Map<String, WebElement>> alerts_table = PersonAccountRelatedPage.getAlertSectionTable(driver);

        System.out.println("/*.----Verify Alert can be created from CIB Related --*/");
        Assert.assertEquals(6, alerts_table.size());
        System.out.println();
    }


}
