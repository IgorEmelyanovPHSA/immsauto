package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InClinicExperienceVaccineAdministrationPage extends BasePage {
    public InClinicExperienceVaccineAdministrationPage(WebDriver driver) {
        super(driver);
    }

    public static void selectVaccineAgent(WebDriver driver, String agent) throws InterruptedException {
        Thread.sleep(500);
        By vaccine_agent_dropdown_path = By.xpath("//button[@aria-label = 'Agent, Select an option' or @aria-label='Agent - Current Selection: Select an option']");
        waitForElementToBeEnabled(driver, vaccine_agent_dropdown_path, 30);
        WebElement click_vaccine_agent_dropdown = driver.findElement(vaccine_agent_dropdown_path);
        scrollCenter(driver, click_vaccine_agent_dropdown);
        click_vaccine_agent_dropdown.click();
        Thread.sleep(500);
        By my_vaccine_path = By.xpath("//span[text() = '" + agent + "']");
        waitForElementToBeEnabled(driver, my_vaccine_path, 10);
        WebElement my_vaccine = driver.findElement(my_vaccine_path);
        try {
            my_vaccine.click();
        } catch(Exception ex) {
            click_vaccine_agent_dropdown.click();
            waitForElementToBeEnabled(driver, my_vaccine_path, 10);
            my_vaccine = driver.findElement(my_vaccine_path);
            my_vaccine.click();
        }
    }

    public static String getVaccineAgent(WebDriver driver) throws InterruptedException {
        By vaccine_agent_path = By.xpath("//label[text()='Agent']/..//button");
        waitForElementToBeEnabled(driver, vaccine_agent_path, 5);
        return driver.findElement(vaccine_agent_path).getAttribute("data-value");
    }

    public static String getProvider(WebDriver driver) throws InterruptedException {
        By provider_path = By.xpath("//label[text() = 'Provider']/..//input");
        waitForElementToBeEnabled(driver, provider_path, 10);
        return driver.findElement(provider_path).getAttribute("data-value");
    }

    public static String getRoute(WebDriver driver) throws InterruptedException {
        By route_path = By.xpath("//label[text() = 'Route']/..//button");
        waitForElementToBeEnabled(driver, route_path, 10);
        return driver.findElement(route_path).getAttribute("data-value");
    }

    public static String getSite(WebDriver driver) throws InterruptedException {
        By site_path = By.xpath("//label[text() = 'Site']/..//button");
        waitForElementToBeEnabled(driver, site_path, 5);
        return driver.findElement(site_path).getAttribute("data-value");
    }

    public static String getLotNumber(WebDriver driver) throws InterruptedException {
        By lot_number_path = By.xpath("//span[text() = 'Lot Number']/..//input");
        waitForElementToBeEnabled(driver, lot_number_path, 10);
        driver.findElement(lot_number_path).click();
        Thread.sleep(1000);
        return driver.findElement(lot_number_path).getAttribute("title");
    }

    public static String getDosage(WebDriver driver) throws InterruptedException {
        By dosage_path = By.xpath("//label[text() = 'Dosage']/..//button");
        waitForElementToBeEnabled(driver, dosage_path, 5);
        return driver.findElement(dosage_path).getAttribute("data-value");
    }

    public static void setVaccineAgent(WebDriver driver, String agent) {
        driver.findElement(By.xpath("//label[text()='Agent']/..//button")).getAttribute("data-value");
    }

    public static void setProvider(WebDriver driver, String provider) throws InterruptedException {
        Thread.sleep(500);
        By providerFieldPath = By.xpath("//label[text() = 'Provider' and @c-bchcimmunizationinfo_bchcimmunizationinfo]/..//input");
        waitForElementToBeEnabled(driver, providerFieldPath, 10);
        WebElement providerField =  driver.findElement(providerFieldPath);
        scrollCenter(driver, providerField);
        providerField.sendKeys(provider);
        By providerItemPath = By.xpath("//lightning-base-combobox-formatted-text[@title = '" + provider + "']");
        waitForElementToBeLocated(driver, providerItemPath, 10);
        WebElement provider_item = driver.findElement(providerItemPath);
        provider_item.click();

    }

    public static void setRoute(WebDriver driver, String route) throws InterruptedException {
        driver.findElement(By.xpath("//label[text() = 'Route']/..//button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@title = '" + route + "'] ")).click();
    }

    public static void setSite(WebDriver driver, String site) throws InterruptedException {
        WebElement siteBtn = driver.findElement(By.xpath("//label[text() = 'Site']/..//button"));
        scrollCenter(driver, siteBtn);
        siteBtn.click();
        Thread.sleep(2000);
        WebElement mySite = driver.findElement(By.xpath("//span[@title = '" + site + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", mySite );
        mySite.click();
    }

    public static void setLotNumber(WebDriver driver, String lot) throws InterruptedException {
        By lot_item_path = By.xpath("//li[contains(@title, '" + lot + "')]");
        By lot_field_path = By.xpath("//span[text() = 'Lot Number']/..//input");
        WebElement lotSearchInputField = driver.findElement(By.xpath("//span[text() = 'Lot Number']/..//input[@class = 'slds-input search-input-class']"));
        if(!lotSearchInputField.isDisplayed()) {
            waitForElementToBeEnabled(driver, lot_field_path, 10);
            WebElement lot_field = driver.findElement(lot_field_path);
            lot_field.click();
        }
        Thread.sleep(1000);
        lotSearchInputField.sendKeys(lot);
        Thread.sleep(1000);
        waitForElementToBeEnabled(driver, lot_item_path, 10);
        WebElement lot_item = driver.findElement(lot_item_path);
        lot_item.click();
    }

    public static void setDosage(WebDriver driver, String dose) throws InterruptedException {
        By dosage_field_path = By.xpath("//label[text() = 'Dosage']/..//button");
        waitForElementToBeEnabled(driver, dosage_field_path, 10);
        WebElement dosage_input_field = driver.findElement(dosage_field_path);
        dosage_input_field.click();
        Thread.sleep(500);
        By my_dosage_path = By.xpath("//span[@title = '" + dose + "']");
        waitForElementToBeEnabled(driver, my_dosage_path, 10);
        WebElement my_dosage = driver.findElement(my_dosage_path);
        my_dosage.click();
    }
}
