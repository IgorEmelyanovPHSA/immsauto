package bcvax.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;

public class AppointmentDayManagementPage extends BasePage {
    Tables tables;
    public AppointmentDayManagementPage(WebDriver driver) {
        super(driver);
    }

    public Map<String, WebElement> findAppointmentDay(String appointment_date, String appointment_type, String provider) throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        Map<String, WebElement> appointment_days_res = new HashMap<>();
        tables = new Tables(driver);
        String appointment_name = appointment_type + " " + appointment_date;
        Map<String, String> search_criteria = new HashMap<String, String>();
        search_criteria.put("Name", appointment_name);
        search_criteria.put("Provider", provider);
        GenericTable appointment_days_table = tables.getAppointmentDayTable();
        int retries = 5;
        int retry = 0;
        try {
            Thread.sleep(2000);
            appointment_days_res = appointment_days_table.getMappedRow(search_criteria);
        } catch(AssertionError ex) {
            for(int i = 0; i < retries; i++) {
                int mySize = tables.getAppointmentDayTable().getRows().size();
                WebElement lastRow = tables.getAppointmentDayTable().getRows().get(mySize - 1).get(0);
                //Scrool to the last row
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", lastRow);
                try {
                    appointment_days_res = appointment_days_table.getMappedRow(search_criteria);
                    break;
                } catch(AssertionError myEx1) {
                    System.out.println("***DEBUG*** Table size currently " + mySize);
                }
            }
        }
        return appointment_days_res;
    }

    public void addAppointmentDay() throws InterruptedException {
        By new_btn_path = By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.DDH__HC_Appointment_Day_Management__c.New']");
        waitForElementToBeEnabled(driver, new_btn_path, 10);
        WebElement new_btn = driver.findElement(new_btn_path);
        new_btn.click();
    }

    public void fillUpNewAppointmentDay(String name, String provider, String address, String date, String city, String type, String localization) throws InterruptedException {
        By name_field_path = By.xpath("//input[@name='Name']");
        By provider_field_path = By.xpath("//label[text()='Provider']/..//input");
        By address_field_path = By.xpath("//label[text()='Address']/..//input");
        By city_field_path = By.xpath("//label[text()='Appointment City']/..//input");
        By type_field_path = By.xpath("//label[text()='Appointment Type']/..//input");
        By localization_field_path = By.xpath("//label[text()='Localization']/..//input");
        By date_field_path = By.xpath("//input[@name='DDH__HC_Appointments_Date__c']");
        waitForElementToBeEnabled(driver, name_field_path, 10);
        WebElement name_field = driver.findElement(name_field_path);
        WebElement provider_field = driver.findElement(provider_field_path);
        WebElement address_field = driver.findElement(address_field_path);
        WebElement city_field = driver.findElement(city_field_path);
        WebElement type_field = driver.findElement(type_field_path);
        WebElement localization_field = driver.findElement(localization_field_path);
        WebElement date_field = driver.findElement(date_field_path);
        name_field.sendKeys(name);
        provider_field.sendKeys(provider);
        Thread.sleep(500);
        By my_provider_path = By.xpath("//lightning-base-combobox-formatted-text[@title='" + provider + "']");
        waitForElementToBeEnabled(driver, my_provider_path, 10);
        Thread.sleep(1000);
        WebElement my_provider = driver.findElement(my_provider_path);
        my_provider.click();

        address_field.sendKeys(address);
        By my_address_path = By.xpath("//lightning-base-combobox-formatted-text[@title='" + address + "']");
        waitForElementToBeEnabled(driver, my_address_path, 10);
        WebElement my_address = driver.findElement(my_address_path);
        my_address.click();

        city_field.sendKeys(city);
        By my_city_path = By.xpath("//lightning-base-combobox-formatted-text[@title='" + city + "']");
        waitForElementToBeEnabled(driver, my_city_path, 10);
        WebElement my_city = driver.findElement(my_city_path);
        my_city.click();

        localization_field.sendKeys(localization);
        By my_localization_path = By.xpath("//lightning-base-combobox-formatted-text[@title='" + localization + "']");
        waitForElementToBeEnabled(driver, my_localization_path, 10);
        WebElement my_locatization = driver.findElement(my_localization_path);
        my_locatization.click();

        date_field.sendKeys(date);

        type_field.sendKeys(type);
        By my_search_type_path = By.xpath("//span[@title='Show All Results for \"" + type + "\"']");
        waitForElementToBeEnabled(driver, my_search_type_path, 10);
        WebElement my_search_type = driver.findElement(my_search_type_path);
        my_search_type.click();

        Thread.sleep(500);
        By my_type_path = By.xpath("//table[@data-aura-class='uiVirtualDataGrid--default uiVirtualDataGrid']//a[@title='" + type + "']");
        waitForElementToBeEnabled(driver, my_type_path, 10);
        WebElement my_type = driver.findElement(my_type_path);
        my_type.click();

        By save_appointment_day_btn_path = By.xpath("//button[@name='SaveEdit']");
        waitForElementToBeEnabled(driver, save_appointment_day_btn_path, 10);
        WebElement save_btn = driver.findElement(save_appointment_day_btn_path);
        scrollCenter(save_btn);
        Thread.sleep(500);
        save_btn.click();
    }

    public void selectShowAllAppointmentDays() throws InterruptedException {
        Thread.sleep(2000);
        By select_list_view_btn_path = By.xpath("//button[@title='Select a List View: Appointment Day Management']");
        waitForElementToBeEnabled(driver, select_list_view_btn_path, 10);
        WebElement select_list_view_btn = driver.findElement(select_list_view_btn_path);
        select_list_view_btn.click();
        Thread.sleep(2000);
        By all_items_path = By.xpath("//a[@role='option']/span[text()='All']");
        waitForElementToBeEnabled(driver, all_items_path, 10);
        WebElement all_items = driver.findElement(all_items_path);
        all_items.click();
    }

    public void selectAppointmentDayRelatedTab() throws InterruptedException {
        Thread.sleep(500);
        By related_tab_path = By.xpath("//a[@class='slds-tabs_default__link' and @data-label='Related']");
        waitForElementToBeEnabled(driver, related_tab_path, 10);
        WebElement related_tab = driver.findElement(related_tab_path);
        try {
            related_tab.click();
        } catch(ElementNotInteractableException ex) {
            Thread.sleep(2000);
            related_tab.click();
        }
    }

    public void addAppointmentTime(ArrayList<HashMap> day_times) throws InterruptedException, ParseException {
        Thread.sleep(500);
        List<Map<String, String>> time_slots = new ArrayList();
        HashMap<String, String> time_slot = new HashMap<>();
        time_slot.put("start_time", "8:00 a.m.");
        time_slot.put("end_time", "9:00 a.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "9:00 a.m.");
        time_slot.put("end_time", "10:00 a.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "10:00 a.m.");
        time_slot.put("end_time", "11:00 a.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "11:00 a.m.");
        time_slot.put("end_time", "12:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "12:00 p.m.");
        time_slot.put("end_time", "1:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "1:00 p.m.");
        time_slot.put("end_time", "2:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "2:00 p.m.");
        time_slot.put("end_time", "3:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "3:00 p.m.");
        time_slot.put("end_time", "4:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "4:00 p.m.");
        time_slot.put("end_time", "5:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "5:00 p.m.");
        time_slot.put("end_time", "6:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "6:00 p.m.");
        time_slot.put("end_time", "7:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "7:00 p.m.");
        time_slot.put("end_time", "8:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "8:00 p.m.");
        time_slot.put("end_time", "9:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "9:00 p.m.");
        time_slot.put("end_time", "10:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "10:00 p.m.");
        time_slot.put("end_time", "11:00 p.m.");
        time_slots.add(time_slot);
        time_slot = new HashMap<>();
        time_slot.put("start_time", "11:00 p.m.");
        time_slot.put("end_time", "11:59 p.m.");
        time_slots.add(time_slot);

        DateFormat formatter = new SimpleDateFormat("HH:mm");
        DateFormat formatter24 = new SimpleDateFormat("hh:mm a");

        for(int i = 0; i < time_slots.size(); i++) {
            Time slot_start_time = new Time(formatter24.parse(time_slots.get(i).get("start_time")).getTime());
            Time slot_end_time = new Time(formatter24.parse(time_slots.get(i).get("end_time")).getTime());

            for(int j = 0; j < day_times.size(); j++) {

                Time db_start_time = new Time(formatter.parse(day_times.get(j).get("start_time").toString()).getTime());
                Time db_end_time = new Time(formatter.parse(day_times.get(j).get("end_time").toString()).getTime());

                if(db_start_time.getTime() >= slot_start_time.getTime() && db_start_time.getTime() < slot_end_time.getTime()) {
                    time_slots.get(i).put("skip", "yes");
                    break;
                }
                if(db_start_time.getTime() < slot_start_time.getTime() && db_end_time.getTime() >= slot_end_time.getTime()) {
                    time_slots.get(i).put("skip", "yes");
                    break;
                }
            }
        }

        List<Map<String, String>> time_slots_cleaned = new ArrayList();
        for(int i = 0; i < time_slots.size(); i++) {
            if(time_slots.get(i).get("skip") != "yes") {
                time_slots_cleaned.add(time_slots.get(i));
            }
        }
        By add_time_slot_btn_path = By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.DDH__HC_Appointment_Block_Time__c.New']");
        waitForElementToBeEnabled(driver, add_time_slot_btn_path, 10);
        WebElement add_time_slot_btn = driver.findElement(add_time_slot_btn_path);
        if(time_slots_cleaned.size() > 0) {
            add_time_slot_btn.click();
        }
        for(int i = 0; i < time_slots_cleaned.size(); i++) {
            Thread.sleep(1000);
            By start_time_path = By.xpath("//input[@name='DDH__HC_Start_Time__c']");
            waitForElementToBeEnabled(driver, start_time_path, 10);
            WebElement start_time = driver.findElement(start_time_path);
            try {
                start_time.sendKeys(time_slots_cleaned.get(i).get("start_time"));
            } catch(StaleElementReferenceException ex) {
                Thread.sleep(1000);
                waitForElementToBeEnabled(driver, start_time_path, 10);
                start_time = driver.findElement(start_time_path);
                start_time.sendKeys(time_slots_cleaned.get(i).get("start_time"));
            }
            Thread.sleep(1000);
            By end_time_path = By.xpath("//input[@name='DDH__HC_End_Time__c']");
            waitForElementToBeEnabled(driver, end_time_path, 10);
            WebElement end_time = driver.findElement(end_time_path);
            end_time.sendKeys(time_slots_cleaned.get(i).get("end_time"));
            Thread.sleep(500);
            By block_capacity_path = By.xpath("//input[@name='DDH__HC_Block_Capacity__c']");
            waitForElementToBeEnabled(driver, block_capacity_path, 10);
            WebElement block_capacity = driver.findElement(block_capacity_path);
            block_capacity.sendKeys("10");
            Thread.sleep(500);
            //By booking_counter_path = By.xpath("//input[@name='DDH__HC_Block_Booking_Counter__c']");
            //WebElement booking_counter = driver.findElement(booking_counter_path);
            //booking_counter.sendKeys("1");
            By save_btn_path = By.xpath("//button[@name='SaveEdit']");
            By save_and_new_btn_path = By.xpath("//button[@name='SaveAndNew']");
            if(i < time_slots_cleaned.size() - 1) {
                waitForElementToBeEnabled(driver, save_and_new_btn_path, 10);
                WebElement save_and_new_btn = driver.findElement(save_and_new_btn_path);
                Thread.sleep(500);
                save_and_new_btn.click();
            } else {
                waitForElementToBeEnabled(driver, save_btn_path, 10);
                WebElement save_btn = driver.findElement(save_btn_path);
                Thread.sleep(500);
                save_btn.click();
            }
        }
        System.out.println();
    }
}
