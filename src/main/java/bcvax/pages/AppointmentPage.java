package bcvax.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class AppointmentPage extends BasePage {
    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    public static void clickBtnCancelAppointment(WebDriver driver) throws InterruptedException {
        By cancel_appointment_button_path = By.xpath("//button[text()='Cancel Appointment']");
        waitForElementToBeEnabled(driver, cancel_appointment_button_path, 10);
        WebElement cancel_appointment_button = driver.findElement(cancel_appointment_button_path);
        cancel_appointment_button.click();
        Thread.sleep(500);

        By yes_button_path = By.xpath("//button[text()='Yes']");
        waitForElementToBeEnabled(driver, yes_button_path, 10);
        WebElement yes_button = driver.findElement(yes_button_path);
        yes_button.click();
        Thread.sleep(500);

        By close_window_button_path = By.xpath("//button[text()='Close Window']");
        waitForElementToBeEnabled(driver, close_window_button_path, 10);
        WebElement close_window_button = driver.findElement(close_window_button_path);
        close_window_button.click();
    }
}
