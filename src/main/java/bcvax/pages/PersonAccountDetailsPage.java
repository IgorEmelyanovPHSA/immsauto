package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonAccountDetailsPage extends BasePage {
    public PersonAccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    public static void setBirthdate(WebDriver driver, int age_years, int age_months) throws InterruptedException, ParseException {
        Date today_date = new Date();
        Date current_birthdate = getBirtdate(driver);
        Date new_birthdate = new Date();
        // Creating Calendar class instance
        Calendar calendar_today = Calendar.getInstance();
        Calendar calendar_birth = Calendar.getInstance();
        // Converting Date to Calendar
        calendar_today.setTime(today_date);
        calendar_birth.setTime(current_birthdate);
        int client_age_years = Math.abs(calendar_today.get(Calendar.YEAR) - calendar_birth.get(Calendar.YEAR));

        //Actual Age of Client Today
        int client_age_months = client_age_years * 12 + Math.abs(calendar_today.get(Calendar.MONTH) - calendar_birth.get(Calendar.MONTH));

        //Months Criteria for Client Age Today
        int months_total = age_years * 12 + age_months;
        calendar_today.add(Calendar.YEAR, -age_years);
        calendar_today.add(Calendar.MONTH, -age_years);
        new_birthdate = calendar_today.getTime();
        By edit_birthdate_button_path = By.xpath("//button[@title='Edit Birthdate']");
        waitForElementToBeEnabled(driver, edit_birthdate_button_path, 10);
        WebElement edit_birthdate_button = driver.findElement(edit_birthdate_button_path);
        scrollCenter(driver, edit_birthdate_button);
        Thread.sleep(500);
        edit_birthdate_button.click();
        Thread.sleep(500);
        By birthdate_field_path = By.xpath("//input[@name='PersonBirthdate']");
        waitForElementToBeEnabled(driver, birthdate_field_path, 10);
        WebElement birthdate_field = driver.findElement(birthdate_field_path);
        scrollCenter(driver, birthdate_field);
        Thread.sleep(500);
        birthdate_field.clear();
        Thread.sleep(500);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        birthdate_field.sendKeys(df.format(new_birthdate));
        Thread.sleep(500);
        By save_edit_btn_path = By.xpath("//button[@name='SaveEdit']");
        waitForElementToBeEnabled(driver, save_edit_btn_path, 10);
        WebElement save_edit_button = driver.findElement(save_edit_btn_path);
        scrollCenter(driver, save_edit_button);
        save_edit_button.click();
    }

    public static Date getBirtdate(WebDriver driver) throws InterruptedException, ParseException {
        By birthdate_value_path = By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Account.PersonBirthdate']//lightning-formatted-text");
        waitForElementToBeEnabled(driver, birthdate_value_path, 10);
        WebElement birthdate_value = driver.findElement(birthdate_value_path);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = df.parse(birthdate_value.getText());
        return birthdate;
    }
}
