package bcvax.tests.InClinicExperience;
import Utilities.TestListener;
import bcvax.pages.*;
import bcvax.tests.BaseTest;
import constansts.Apps;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
    Map<String, Object> testData;
    String client_name;
    String clinicNameToSearch;
    MainPageOrg orgMainPage;
    String consumptionLot;
    String consumptionDose;
    String consumptionRoute;
    String consumptionSite;
    String consentProvider;
    String consumptionAgent;
    Map<String, String> client_data;
    @BeforeMethod
    public void beforeMethod() throws Exception {
        String client_data_file = Utils.getClientsDataFile();
        client_data = Utils.getTestClientData(client_data_file, "dose1");
        log("/*0.---API call to remove duplicate citizen participant account if found--*/");
        Utilities.ApiQueries.apiCallToRemoveParticipantAccountByPHN(client_data.get("personalHealthNumber"));
        client_name = client_data.get("legalFirstName") + (client_data.get("legalMiddleName").isEmpty() ? "" : " " + client_data.get("legalMiddleName")) + " " +
                client_data.get("legalLastName");
    }

    @Test(priority = 1)
    public void verifyAlerts_ICE_CIB() throws Exception {
        log("Target Environment: "+ Utils.getTargetEnvironment());
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
        InClinicExperiencePage.clickUserDefaultsTab(driver);
        log("/*6.----- Enter current date for UserDefaults --*/");
        log("/*-- 13. Enter current date for UserDefaults --*/");
        UserDefaultsPage.inputCurrentDateUserDefaults(driver);
        UserDefaultsPage.selectUserDefaultLocation(driver, clinicNameToSearch);
        log("/*7.----- Click on Save defaults button --*/");
        UserDefaultsPage.clickBtnSave(driver);
        System.out.println("/*8.----- Click on register Tab --*/");
        InClinicExperiencePage.clickRegisterTab(driver);
        //System.out.println("/*9.----- Click on Save changes defaults button Modal window --*/");
        //inClinicExperience.clickSaveModalDefaultsButton();
        //Thread.sleep(2000);
        System.out.println("/*10.----click Register button New Citizen --*/");
        InClinicExperiencePage.clickRegisterButton(driver);
        CitizenPrimaryInfo.fillUpRegistrationForm(driver, client_data);
        System.out.println("/*25.----click on CheckIn button --*/");
        try {
            PersonAccountPage.clickCheckInButton(driver);
        } catch(ElementNotInteractableException ex) {
            PersonAccountPage.cancelProfileNotLinkedToPIRWarning(driver);
            PersonAccountPage.clickCheckInButton(driver);
        }
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
        AlertDialog.closeAllAlerts(driver);
        ViewEditAlertPage.clickSaveButton(driver);
        List<String> my_errors = AlertDialog.getAllAlertsText(driver);
        AlertDialog.closeAllAlerts(driver);
        System.out.println("/*.----Verify Alert creation missing mandatory Fields --*/");
        Assert.assertTrue(my_errors.get(0).contains("Error\n" +
                "Unable to update Alert You must provide a reason for update to edit this alert"));

        ViewEditAlertPage.selectAlertReasonForUpdate(driver, "Other, specify");
        ViewEditAlertPage.clickSaveButton(driver);
        List<String> my_other_errors = AlertDialog.getAllAlertsText(driver);
        AlertDialog.closeAllAlerts(driver);
        Assert.assertTrue(my_other_errors.get(0).contains("Error\n" +
                "Unable to update Alert You must enter details in Update Comment if Reason for Update is Other"));

        ViewEditAlertPage.setAlertUpdateComments(driver, "Alert Update Comment");
        ViewEditAlertPage.clickSaveButton(driver);
        Thread.sleep(1000);
        InClinicExperienceIdentificationPage.clickConfirmAndSaveIdentificationButton(driver);

        InClinicExperiencePage.clickTodayAppointments(driver);
        Thread.sleep(2000);
        Map<String, WebElement> my_appointment_info = ClientListTodayAppointmentsTab.getTodayAppoitmentsTableRow(driver, client_data.get("personalHealthNumber"));
        ClientListTodayAppointmentsTab.clickViewButton(driver, my_appointment_info);

        String final_alerts_from_sidebar = InClinicExperienceIdentificationPage.getSidebarAlertText(driver);

        System.out.println("/*.----Verify Sidebar contains Alerts(2) after alert updated to be not active --*/");
        Assert.assertEquals(final_alerts_from_sidebar, "Alerts(2)");

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
        MainPageOrg.search(driver, client_name);
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
        MainPageOrg.search(driver, client_name);
        PersonAccountPage.goToRelatedTab(driver);
        PersonAccountRelatedPage.scrollToAlertsSection(driver);
        List<Map<String, WebElement>> alerts_table = PersonAccountRelatedPage.getAlertSectionTable(driver);

        System.out.println("/*.----Verify Alert can be created from CIB Related --*/");
        Assert.assertEquals(6, alerts_table.size());
        System.out.println();
    }
}
