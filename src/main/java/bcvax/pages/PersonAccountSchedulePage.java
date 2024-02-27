package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonAccountSchedulePage extends BasePage {
    public PersonAccountSchedulePage(WebDriver driver) {
        super(driver);
    }

    public static void selectEarlyBookingReason(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By select_early_booking_reason_field_path = By.xpath("//span[text() = 'Select One']");
        waitForElementToBeEnabled(driver, select_early_booking_reason_field_path, 10);
        WebElement select_early_booking_dropdown = driver.findElement(select_early_booking_reason_field_path);
        select_early_booking_dropdown.click();
        Thread.sleep(500);
        By early_booking_reason_travel_path = By.xpath("//span[text() = 'Travel']");
        waitForElementToBeEnabled(driver, early_booking_reason_travel_path, 10);
        WebElement early_booking_reason_travel = driver.findElement(early_booking_reason_travel_path);
        waitForElementToBeVisible(driver, early_booking_reason_travel, 10);
        early_booking_reason_travel.click();
    }

    public static void overrideEligibility(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By override_eligibility_btn_path = By.xpath("//button[@title='Primary action' and text()='Override Eligibility']");
        waitForElementToBeEnabled(driver, override_eligibility_btn_path, 30);
        WebElement override_eligibility_btn = driver.findElement(override_eligibility_btn_path);
        override_eligibility_btn.click();
        Thread.sleep(500);
        By reason_for_override_path = By.xpath("//button[(@aria-label='Reason for Override, Select One') or (@aria-label='Reason for Override - Current Selection: Select One')]");
        waitForElementToBeEnabled(driver, reason_for_override_path, 10);
        WebElement reason_for_verride_btn = driver.findElement(reason_for_override_path);
        scrollCenter(driver, reason_for_verride_btn);
        Thread.sleep(500);
        reason_for_verride_btn.click();
        Thread.sleep(500);
        By travel_item_path = By.xpath("//lightning-base-combobox-item[@data-value='Travel']");
        waitForElementToBeEnabled(driver, travel_item_path, 10);
        WebElement travel_item = driver.findElement(travel_item_path);
        scrollCenter(driver, travel_item);
        Thread.sleep(500);
        travel_item.click();
    }

    public static void checkBookingVaccineCheckbox(WebDriver driver, String vaccine) throws InterruptedException {
        Thread.sleep(500);
        By covid19_vaccine_checkbox_path = By.xpath("//span[text() = 'Covid-19 Vaccine']");
        By influenza_checkbox_path = By.xpath("//span[text() = 'Influenza Vaccine']");
        if (vaccine.equalsIgnoreCase("Covid19Vaccine")) {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 60);
            WebElement covid19_vaccine_checkbox = driver.findElement(covid19_vaccine_checkbox_path);
            scrollCenter(driver, covid19_vaccine_checkbox);
            Thread.sleep(500);
            covid19_vaccine_checkbox.click();
        } else if (vaccine.equalsIgnoreCase("InfluenzaVaccine")) {
            waitForElementToBeEnabled(driver, influenza_checkbox_path, 10);
            WebElement influenza_checkbox = driver.findElement(influenza_checkbox_path);
            scrollCenter(driver, influenza_checkbox);
            Thread.sleep(500);
            influenza_checkbox.click();
        } else {
            waitForElementToBeEnabled(driver, covid19_vaccine_checkbox_path, 10);
            WebElement covid19_vaccine_checkbox = driver.findElement(covid19_vaccine_checkbox_path);
            scrollCenter(driver, covid19_vaccine_checkbox);
            Thread.sleep(500);
            covid19_vaccine_checkbox.click();
            WebElement influenza_checkbox = driver.findElement(influenza_checkbox_path);
            scrollCenter(driver, influenza_checkbox);
            Thread.sleep(500);
            influenza_checkbox.click();
        }
    }

    public static void selectSearchByClinicNameTab(WebDriver driver) throws InterruptedException {
        By search_by_clinic_name_tab_path = By.xpath(".//a[text()='Search by clinic name']");
        waitForElementToBeEnabled(driver, search_by_clinic_name_tab_path, 10);
        Thread.sleep(500);
        WebElement search_by_clinic_name_tab = driver.findElement(search_by_clinic_name_tab_path);
        scrollCenter(driver, search_by_clinic_name_tab);
        Thread.sleep(500);
        search_by_clinic_name_tab.click();
    }

    public static void searchClinicName(WebDriver driver, String clinicNameToSearch) throws InterruptedException {
        By search_clinic_input_path = By.xpath("//input[@name='clinicstag']");
        waitForElementToBeEnabled(driver, search_clinic_input_path, 10);
        WebElement search_clinic_name = driver.findElement(search_clinic_input_path);
        scrollCenter(driver, search_clinic_name);
        Thread.sleep(500);
        search_clinic_name.click();
        Thread.sleep(500);
        search_clinic_name.sendKeys(clinicNameToSearch);
        search_clinic_name.sendKeys(Keys.RETURN);
    }
}
