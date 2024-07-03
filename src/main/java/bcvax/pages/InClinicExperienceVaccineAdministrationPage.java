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
        By vaccine_agent_dropdown_path = By.xpath("//button[@aria-label = 'Agent, Select an option' or @aria-label='Agent - Current Selection: Select an option' or @aria-label='Agent']");
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
        scrollCenter(driver, lotSearchInputField);
        Thread.sleep(500);
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

    public static void clickEditImmunizationInformation(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By edit_immunization_info_btn_path = By.xpath("//c-bc-hc-immunization-info//button[@title='Edit']");
        waitForElementToBeEnabled(driver, edit_immunization_info_btn_path, 10);
        WebElement edit_immunization_info_btn = driver.findElement(edit_immunization_info_btn_path);
        scrollIfNeeded(driver, edit_immunization_info_btn);
        Thread.sleep(500);
        edit_immunization_info_btn.click();
    }

    public static void clickSaveImmuneInfoButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By save_immunization_info_btn_path = By.xpath("//button[text()='Save']");
        waitForElementToBeEnabled(driver, save_immunization_info_btn_path, 10);
        WebElement save_immunization_info_btn = driver.findElement(save_immunization_info_btn_path);
        scrollCenter(driver, save_immunization_info_btn);
        Thread.sleep(500);
        save_immunization_info_btn.click();
    }

    public static void clickRecordConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By record_consent_btn_path = By.xpath("//button[@title='Primary action' and text()='Record Consent']");
        waitForElementToBeEnabled(driver, record_consent_btn_path, 10);
        WebElement record_consent_btn = driver.findElement(record_consent_btn_path);
        scrollCenter(driver, record_consent_btn);
        Thread.sleep(500);
        record_consent_btn.click();
    }

    public static void ClickConfirmAndSaveAdministrationButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_save_adm_btn_path = By.xpath("//button[@title='Confirm & Save Administration']");
        waitForElementToBeEnabled(driver, confirm_save_adm_btn_path, 10);
        WebElement confirm_save_adm_btn = driver.findElement(confirm_save_adm_btn_path);
        scrollIfNeeded(driver, confirm_save_adm_btn);
        Thread.sleep(1000);
        confirm_save_adm_btn.click();
    }

    public static void ClickModalConfirmAndSaveAdministrationButton(WebDriver driver) throws InterruptedException {
        By confirm_save_btn_path = By.xpath("//button[text()='Confirm & Save Administration']");
        waitForElementToBeEnabled(driver, confirm_save_btn_path, 10);
        WebElement confirm_save_btn = driver.findElement(confirm_save_btn_path);
        scrollCenter(driver, confirm_save_btn);
        Thread.sleep(500);
        confirm_save_btn.click();
        Thread.sleep(500);
        By go_to_user_default_dialog_path = By.xpath("//button[@c-bchcmodal_bchcmodal and text()='Go to User Defaults']");

        try {
            AlertDialog.closeAllAlerts(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("Couldn't close the success dialog. Continue...");
        }

        try {
            waitForElementToBeEnabled(driver, go_to_user_default_dialog_path, 10);
            System.out.println("Dialog Go to User Default is found. Wait until disappear...");
            waitForElementNotToBePresent(driver, go_to_user_default_dialog_path, 5);
        } catch(Exception ex) {
            System.out.println("Go to User default dialog didn't appear or already gone. Continue...");
        }
//Try again to close success dialog
        try {
            System.out.println("Try again to close the success dialog...");
            AlertDialog.closeAllAlerts(driver);
            Thread.sleep(500);
        } catch(Exception ex) {
            System.out.println("Success dialog didn't appear or already gone. Continue...");
        }
    }

    public static void selectNotApprovedAdministrationReason(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By administration_reason_path = By.xpath("//input[@type='radio' and @value='Intentional administration']/..//span[@part='indicator']");
        waitForElementToBeEnabled(driver, administration_reason_path, 2);
        WebElement administration_reason = driver.findElement(administration_reason_path);
        scrollCenter(driver, administration_reason);
        Thread.sleep(500);
        administration_reason.click();
    }

    public static String getActiveDeferralsNumber(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By active_deferrals_number_path = By.xpath("//c-deferrals//span[@c-deferrals_deferrals and contains(@style, 'float:right')]");
        waitForElementToBeEnabled(driver, active_deferrals_number_path, 10);
        WebElement active_deferrals_number_text = driver.findElement(active_deferrals_number_path);
        scrollCenter(driver, active_deferrals_number_text);
        Thread.sleep(500);
        String active_deferrals_number = active_deferrals_number_text.getText();
        return active_deferrals_number;
    }

    public static void clickAddDeferralButton(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By add_deferral_button_path = By.xpath("//c-deferrals//button[@title='Add']");
        waitForElementToBeEnabled(driver, add_deferral_button_path, 10);
        WebElement add_deferral_button = driver.findElement(add_deferral_button_path);
        scrollCenter(driver, add_deferral_button);
        Thread.sleep(500);
        add_deferral_button.click();
    }

    public static GenericTable getDeferralsTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By deferrals_table_path = By.xpath("//c-deferrals");
        waitForElementToBeEnabled(driver, deferrals_table_path, 10);
        WebElement deferrals_table_node = driver.findElement(deferrals_table_path);
        GenericTable deferrals_table = new GenericTable(deferrals_table_node);
        return deferrals_table;
    }

    public static void checkShowDepletedLots(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By show_depeleted_chkbox_path = By.xpath("//span[text()='Show depleted lot numbers']/../../input");
        By show_depeleted_chkbox_click_path = By.xpath("//span[text()='Show depleted lot numbers']");
        WebElement chkbox_status = driver.findElement(show_depeleted_chkbox_path);
        boolean my_checkbox_status = chkbox_status.isSelected();
        if(!my_checkbox_status) {
            WebElement my_checkbox = driver.findElement(show_depeleted_chkbox_click_path);
            my_checkbox.click();
        }
    }
}
