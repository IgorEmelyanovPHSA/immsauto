package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientListPage extends BasePage {
    public ClientListPage(WebDriver driver) {
        super(driver);
    }

    public static void clickMyListTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By my_list_tab_path = By.xpath("//ul[@role='tablist']//a[@data-tab-value='myList']");
        waitForElementToBeEnabled(driver, my_list_tab_path, 10);
        WebElement my_list_tab = driver.findElement(my_list_tab_path);
        scrollCenter(driver, my_list_tab);
        Thread.sleep(500);
        my_list_tab.click();
    }

    public static void clickTodayAppointmentsTab(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By today_appointments_tab_path = By.xpath("//ul[@role='tablist']//a[@data-tab-value='todayAppointments']");
        waitForElementToBeEnabled(driver, today_appointments_tab_path, 10);
        WebElement today_appointments_tab = driver.findElement(today_appointments_tab_path);
        scrollCenter(driver, today_appointments_tab);
        Thread.sleep(500);
        today_appointments_tab.click();
    }

    public static String getClinicLocation(WebDriver driver) throws InterruptedException {
        By clinic_location_path = By.xpath("//p[@c-bchcheader_bchcheader and text()='Clinic Location']/../p[@c-bchcheader_bchcheader]");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, clinic_location_path, 10);
        WebElement clinic_location = driver.findElements(clinic_location_path).get(1);
        return clinic_location.getText();
    }

    public static String getDate(WebDriver driver) throws InterruptedException {
        By date_path = By.xpath("//p[@c-bchcheader_bchcheader and text()='Date']/../p[@c-bchcheader_bchcheader]");
        Thread.sleep(500);
        waitForElementToBeEnabled(driver, date_path, 10);
        WebElement date = driver.findElements(date_path).get(1);
        return date.getText();
    }
}
