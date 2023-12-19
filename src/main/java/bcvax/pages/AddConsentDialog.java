package bcvax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddConsentDialog extends BasePage {
    public AddConsentDialog(WebDriver driver) {
        super(driver);
    }
    public static boolean dialogExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By dialog_path = By.xpath("//div[@class='slds-modal__container']/header[@class='slds-modal__header']/h1[text()='Add Consent']");
        try {
            waitForElementToBeLocated(driver, dialog_path, 10);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static List<String> getInformedConsentTableHeaders(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_table_path = By.xpath("//div[@class='slds-modal__container']/header[@class='slds-modal__header']/h1[text()='Add Consent']/../..//flowruntime-datatable");
        waitForElementToBeEnabled(driver, informed_consent_table_path, 10);
        WebElement informed_consent_table_node = driver.findElement(informed_consent_table_path);
        GenericTable informed_consent_table = new GenericTable(informed_consent_table_node);
        List<String> headers = informed_consent_table.getHeadingsAsString();
        return headers;
    }

    public static List<Map<String, WebElement>> getInformedConsentTable(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_table_path = By.xpath("//div[@class='slds-modal__container']/header[@class='slds-modal__header']/h1[text()='Add Consent']/../..//flowruntime-datatable");
        waitForElementToBeEnabled(driver, informed_consent_table_path, 10);
        WebElement informed_consent_table_node = driver.findElement(informed_consent_table_path);
        GenericTable informed_consent_table = new GenericTable(informed_consent_table_node);
        List<Map<String, WebElement>> table = informed_consent_table.getRowsMappedToHeadings();
        return table;
    }

    public static void selectResponseGrantRadioButton(WebDriver driver) {
        WebElement grant_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Grant']/..//span[@class='slds-radio_faux']"));
        grant_radio.click();
    }

    public static void selectResponseRefuseRadioButton(WebDriver driver) {
        WebElement response_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Refuse']/..//span[@class='slds-radio_faux']"));
        response_radio.click();
    }

    public static void selectImmsBCProviderRadioButton(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.ImmsBC Provider (User)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectNonImmsBCProviderRadioButton(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.Non-ImmsBC Provider (Contact)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClient(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientMinor(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientSensitive(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor - Sensitive)']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectObtainedFromClientGuardian(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Substitute Decision Maker']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectFormOfConsentInPerson(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.In Person']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectFormOfConsentWritten(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Written']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static void selectFormOfConsentTelephone(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Telephone']/..//span[@class='slds-radio_faux']"));
        provider_type_radio.click();
    }

    public static boolean getResponseGrantRadioButtonSelected(WebDriver driver) {
        WebElement grant_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Grant']"));
        return grant_radio.isSelected();
    }

    public static boolean getResponseRefuseRadioButtonSelected(WebDriver driver) {
        WebElement response_radio = driver.findElement(By.xpath("//input[@value='ConsentResponseValues.Refuse']"));
        return response_radio.isSelected();
    }

    public static boolean getImmsBCProviderRadioButtonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.ImmsBC Provider (User)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getNonImmsBCProviderRadioButtonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='ProviderTypeValues.Non-ImmsBC Provider (Contact)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientMinorSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientSensitiveSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Client (Mature Minor - Sensitive)']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getObtainedFromClientGuardianSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='InformedConsentForSeriesObtainedFrom.Substitute Decision Maker']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getFormOfConsentInPersonSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.In Person']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getFormOfConsentWrittenSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Written']"));
        return provider_type_radio.isSelected();
    }

    public static boolean getFormOfConsentTelephoneSelected(WebDriver driver) {
        WebElement provider_type_radio = driver.findElement(By.xpath("//input[@value='FormofConsentValues.Telephone']"));
        return provider_type_radio.isSelected();
    }

    public static void selectInformedConsentProvider(WebDriver driver, String provider) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_provider_path = By.xpath("//label[text()='Informed Consent Provider (User)']/..//input");
        waitForElementToBeEnabled(driver, informed_consent_provider_path, 10);
        WebElement informed_consent_provider_input = driver.findElement(informed_consent_provider_path);
        informed_consent_provider_input.sendKeys(provider);
        Thread.sleep(500);
        By consent_provider_item_path = By.xpath("//lightning-base-combobox-formatted-text[@title='" + provider + "']");
        waitForElementToBeEnabled(driver, consent_provider_item_path, 10);
        WebElement provider_item = driver.findElement(consent_provider_item_path);
        provider_item.click();
    }

    public static String getInformedConsentProviderSelected(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By informed_consent_provider_path = By.xpath("//label[text()='Informed Consent Provider (User)']/..//input");
        waitForElementToBeEnabled(driver, informed_consent_provider_path, 10);
        WebElement informed_consent_provider_input = driver.findElement(informed_consent_provider_path);
        return informed_consent_provider_input.getAttribute("data-value");
    }

    public static void setConsentEffectiveDateFrom(WebDriver driver, String date) throws InterruptedException {
        Thread.sleep(500);
        By consent_from_date_path = By.xpath("//span[text()='Consent Effective From Date']/../../..//input[@class='slds-input']");
        waitForElementToBeEnabled(driver, consent_from_date_path, 10);
        WebElement informed_consent_provider_input = driver.findElement(consent_from_date_path);
        informed_consent_provider_input.clear();
        informed_consent_provider_input.sendKeys(date);
    }
    public static String getConsentEffectiveDateFromSelected(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By consent_from_date_path = By.xpath("//span[text()='Consent Effective From Date']/../../..//input[@class='slds-input']");
        waitForElementToBeEnabled(driver, consent_from_date_path, 10);
        WebElement informed_consent_provider_input = driver.findElement(consent_from_date_path);
        return informed_consent_provider_input.getAttribute("value");
    }

    public static void clickNextButton(WebDriver driver) throws InterruptedException {
        WebElement next_button = driver.findElement(By.xpath("//h1[text()='Add Consent']/../..//button[text()='Next']"));
        scrollCenter(driver, next_button);
        Thread.sleep(500);
        next_button.click();
    }

    public static void click_confirm_info(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By confirm_info_checkbox_path = By.xpath("//span[text()='I confirm the above information is accurate and understand that it cannot be edited after it has been submitted.']/../../../lightning-input");
        waitForElementToBeEnabled(driver, confirm_info_checkbox_path, 10);
        WebElement confirm_info_checkbox = driver.findElement(confirm_info_checkbox_path);
        confirm_info_checkbox.click();
    }

    public static void click_create_consent_record(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By create_consent_record_btn_path = By.xpath("//button[text()='Create Consent Record']");
        waitForElementToBeEnabled(driver, create_consent_record_btn_path, 10);
        WebElement create_consent_record_btn = driver.findElement(create_consent_record_btn_path);
        create_consent_record_btn.click();
    }

    public static String getProfile(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By profile_path = By.xpath("//strong[text()='Profile']/..");
        waitForElementToBeEnabled(driver, profile_path, 10);
        WebElement profile = driver.findElement(profile_path);
        String[] profile_text = profile.getText().split(": ");
        if(profile_text.length < 2) {
            return "";
        } else {
            return profile_text[1];
        }
    }

    public static String getPHN(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By phn_path = By.xpath("//strong[text()='PHN']/..");
        waitForElementToBeEnabled(driver, phn_path, 10);
        WebElement phn = driver.findElement(phn_path);
        String[] phn_text = phn.getText().split(": ");
        if(phn_text.length < 2) {
            return "";
        } else {
            return phn_text[1];
        }
    }

    public static String getAgent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By agent_path = By.xpath("//strong[text()='Agent']/..");
        waitForElementToBeEnabled(driver, agent_path, 10);
        WebElement agent = driver.findElement(agent_path);
        String[] agent_text = agent.getText().split(": ");
        if(agent_text.length < 2) {
            return "";
        } else {
            return agent_text[1];
        }
    }

    public static String getResponse(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By response_path = By.xpath("//strong[text()='Response']/..");
        waitForElementToBeEnabled(driver, response_path, 10);
        WebElement response = driver.findElement(response_path);
        String[] response_text = response.getText().split(": ");
        if(response_text.length < 2) {
            return "";
        } else {
            return response_text[1];
        }
    }

    public static String getProviderType(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By provider_type_path = By.xpath("//strong[text()='Provider Type']/..");
        waitForElementToBeEnabled(driver, provider_type_path, 10);
        WebElement provider_type = driver.findElement(provider_type_path);
        String[] provider_type_text = provider_type.getText().split(": ");
        if(provider_type_text.length < 2) {
            return "";
        } else {
            return provider_type_text[1];
        }
    }

    public static String getProviderName(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By provider_name_path = By.xpath("//strong[text()='Provider Name']/..");
        waitForElementToBeEnabled(driver, provider_name_path, 10);
        WebElement provider_name = driver.findElement(provider_name_path);
        String[] provider_name_text = provider_name.getText().split(": ");
        if(provider_name_text.length < 2) {
            return "";
        } else {
            return provider_name_text[1];
        }
    }

    public static String getInformedConsentObtainedFrom(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By consent_obtained_from_path = By.xpath("//strong[text()='Informed Consent for Series Obtained from: ']/..");
        waitForElementToBeEnabled(driver, consent_obtained_from_path, 10);
        WebElement consent_obtained_from = driver.findElement(consent_obtained_from_path);
        String[] consent_obtained_from_text = consent_obtained_from.getText().split(": ");
        if(consent_obtained_from_text.length < 2) {
            return "";
        } else {
            return consent_obtained_from_text[1];
        }
    }

    public static String getFormOfConsent(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By form_of_consent_path = By.xpath("//strong[text()='Form of Consent']/..");
        waitForElementToBeEnabled(driver, form_of_consent_path, 10);
        WebElement form_of_consent = driver.findElement(form_of_consent_path);
        String[] form_of_consent_text = form_of_consent.getText().split(": ");
        if(form_of_consent_text.length < 2) {
            return "";
        } else {
            return form_of_consent_text[1];
        }
    }

    public static String getConsentEffectiveFrom(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By consent_effective_from_path = By.xpath("//strong[text()='Consent Effective From Date']/..");
        waitForElementToBeEnabled(driver, consent_effective_from_path, 10);
        WebElement consent_effective_from = driver.findElement(consent_effective_from_path);
        String[] consent_effective_from_text = consent_effective_from.getText().split(": ");
        if(consent_effective_from_text.length < 2) {
            return "";
        } else {
            return consent_effective_from_text[1];
        }
    }

    public static String getConsentEffectiveTo(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By consent_effective_to_path = By.xpath("//strong[text()='Consent Effective To Date']/..");
        waitForElementToBeEnabled(driver, consent_effective_to_path, 10);
        WebElement consent_effective_to = driver.findElement(consent_effective_to_path);
        String[] consent_effective_to_text = consent_effective_to.getText().split(": ");
        if(consent_effective_to_text.length < 2) {
            return "";
        } else {
            return consent_effective_to_text[1];
        }
    }

    public static boolean errorConfirmExists(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        By consent_error_path = By.xpath("//span[text()='You must review and confirm before this consent record can be created']");
        try {
            WebElement consent_error = driver.findElement(consent_error_path);
            return true;
        } catch(NotFoundException ex) {
            return false;
        }
    }
}
